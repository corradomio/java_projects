package jext.geotools;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.style.Style;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import org.geotools.styling.StyleBuilder;
import org.geotools.api.style.FeatureTypeStyle;
import org.geotools.api.style.Graphic;
import org.geotools.api.style.Mark;
import org.geotools.api.style.PointSymbolizer;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.Color;


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.InvalidParameterException;

/**
 * Convert a shape file in an image.
 * It supports '.png' '.jpg' and '.svg' (Simple Vector Graphics)
 */
public class ShapeFileToImage {

    /**
     * Comvert a '.shp' file into an image.
     * The image will be saved in the same directory containing the '.shp' file.
     * The default format usedis '.png'
     *
     * @param shapefile file to convert
     * @throws Exception
     */
    public static void writeImage(File shapefile ) throws Exception {
        writeImage(shapefile, ".png");
    }

    /**
     * Convert a '.shp' file into an image.
     * The image will be saved in the same directory containing the '.shp' file
     * The format of image is specified by the filename extension to use
     * Supported extensions: '.png', '.jpg', '.svg'
     *
     * @param shapefile file to convert
     * @param fileExt filename extension used to specify the format
     * @throws Exception
     */
    public static void writeImage(File shapefile, String fileExt) throws Exception {
        File parent = shapefile .getParentFile();
        String name = shapefile .getName();
        writeImage(shapefile , new File(parent, name + fileExt));
    }

    /**
     * Convert a '.shp' file into an image.
     * The format to use is based on the filename extension
     *
     * @param shapefile file to convert
     * @param imageFile where to save the file
     * @throws Exception
     */
    public static void writeImage(File shapefile , File imageFile) throws Exception {
        String imageFormat = "png";
        if (imageFile.getName().endsWith(".png"))
            imageFormat = "png";
        else if (imageFile.getName().endsWith(".jpg"))
            imageFormat = "jpg";
        else if (imageFile.getName().endsWith(".svg"))
            imageFormat = "svg";
        else
            throw new InvalidParameterException("Unsupported image format: " + imageFile.getName());

        // 1. Create a simple style for the layer
        Style style;

        GeometryType shapeType = ShapeFileType.analyze(shapefile);
        if (shapeType == GeometryType.POINT) {
            StyleBuilder sb = new StyleBuilder();
            Mark mark = sb.createMark(StyleBuilder.MARK_CIRCLE, Color.RED, Color.RED, 1);
            int pointSizeInPixels = 5;
            Graphic graphic = sb.createGraphic(null, mark, null, 1.0, pointSizeInPixels, 0.0);
            PointSymbolizer pointSymbolizer = sb.createPointSymbolizer(graphic);
            FeatureTypeStyle fts = sb.createFeatureTypeStyle(pointSymbolizer);
            style = sb.createStyle();
            style.featureTypeStyles().add(fts);
        } else  {
            style = SLD.createPolygonStyle(Color.BLUE, Color.BLACK, 0.5f);
        }

        // 1. Read the shapefile
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(shapefile);
        SimpleFeatureSource featureSource = dataStore.getFeatureSource();
        SimpleFeatureCollection featureCollection = featureSource.getFeatures();

        // 2. Create the layer
        Layer layer = new FeatureLayer(featureCollection, style);

        // 3. Set up MapContent
        MapContent mapContent = new MapContent();
        mapContent.addLayer(layer);

        // 4. Determine image dimensions based on bounding box
        ReferencedEnvelope bounds = featureCollection.getBounds();
        int imageWidth = 1000;
        int imageHeight = (int) (imageWidth * (bounds.getHeight() / bounds.getWidth()));
        Dimension canvasSize = new Dimension(imageWidth, imageHeight);

        if ("svg".equals(imageFormat)) {
            ShapeFileToImage.saveMapInSVG(mapContent, imageFile, bounds, canvasSize);
        } else {
            ShapeFileToImage.saveMapInImage(mapContent, imageFile, bounds, canvasSize);
        }

        // 7. Cleanup
        mapContent.dispose();
        dataStore.dispose();
    }

    private static void saveMapInImage(MapContent mapContent, File imageFile, ReferencedEnvelope bounds, Dimension canvasSize) throws Exception {String imageFormat = "png";
        if (imageFile.getName().endsWith(".png"))
            imageFormat = "png";
        else if (imageFile.getName().endsWith(".jpg"))
            imageFormat = "jpg";
        else
            throw new InvalidParameterException("Unsupported image format: " + imageFile.getName());

        int imageWidth = canvasSize.width;
        int imageHeight = canvasSize.height;

        Graphics2D graphics = null;
        try {
            BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
            graphics = bufferedImage.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fillRect(0, 0, imageWidth, imageHeight);

            // 5. Render the map content to the image
            StreamingRenderer renderer = new StreamingRenderer();
            renderer.setMapContent(mapContent);
            Rectangle imageBounds = new Rectangle(0, 0, imageWidth, imageHeight);
            renderer.paint(graphics, imageBounds, bounds);

            ImageIO.write(bufferedImage, imageFormat, imageFile);
        }
        finally{
            if (graphics != null)
                graphics.dispose();
        }
    }

    private static void saveMapInSVG(MapContent mapContent, File outputFile, ReferencedEnvelope bounds, Dimension canvasSize) throws Exception {
        // 1. Get a DOM Implementation from Batik
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNamespaceURI = "http://w3.org";

        // 2. Create an XML Document instance for your SVG file
        Document document = domImpl.createDocument(svgNamespaceURI, "svg", null);

        // 3. Initialize the SVGGraphics2D generator
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setSVGCanvasSize(canvasSize);

        // 4. Configure the GeoTools Renderer
        StreamingRenderer renderer = new StreamingRenderer();
        renderer.setMapContent(mapContent);

        // 5. Define drawing areas
        Rectangle outputArea = new Rectangle(0, 0, canvasSize.width, canvasSize.height);
        ReferencedEnvelope mapArea = mapContent.getViewport().getBounds();

        // 6. Paint the spatial layers onto the virtual vector graphics context
        renderer.paint(svgGenerator, outputArea, mapArea);

        // 7. Stream the generated SVG instructions out to disk
        try (OutputStream os = new FileOutputStream(outputFile);
             Writer out = new OutputStreamWriter(os, "UTF-8")) {

            // Optional: inject a viewBox attribute to make the SVG dynamically scalable
            org.w3c.dom.Element root = svgGenerator.getRoot();
            root.setAttributeNS(null, "viewBox", String.format("0 0 %d %d", canvasSize.width, canvasSize.height));

            svgGenerator.stream(root, out);
        }
    }
}
