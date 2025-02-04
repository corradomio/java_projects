package org.hls.examples;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlotSynthData {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        List<Location> centers = Location.load(new File("centers.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));

        PlanarGraph pg = PlanarGraph.of(locations, 10);
        List<Edge> edges = pg.getEdges();

        // -------------------------------------------------
        // Plotly

        double[][] lxy = Coords.getCoords(locations);
        double[] lx = lxy[0];
        double[] ly = lxy[1];

        double[][] txy = Coords.getCoords(toVisit);
        double[] tx = txy[0];
        double[] ty = txy[1];

        double[][] cxy = Coords.getCoords(centers);
        double[] cx = cxy[0];
        double[] cy = cxy[1];

        double[][] vxy = Coords.getCoords(vehicles);
        double[] vx = vxy[0];
        double[] vy = vxy[1];

        Double[][] exy = Coords.getEdgesCoords(edges);
        Column<Double> ex = DoubleColumn.create("x", exy[0]);
        Column<Double> ey = DoubleColumn.create("x", exy[1]);


        // locations
        ScatterTrace ltrace =
            ScatterTrace.builder(lx, ly)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                    Marker.builder()
                        // .size(size)
                        .size(2)
                        // .showScale(true)
                        // .color(y)
                        .color("aqua")
                        // .colorScale(Marker.Palette.BLUE_RED)
                        // .symbol(Symbol.DIAMOND_TALL)
                        .build())
                .name(String.format("location (%d)", locations.size()))
                .build();

        // locations to visit
        ScatterTrace ttrace =
            ScatterTrace.builder(tx, ty)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                    Marker.builder()
                        // .size(size)
                        .size(4)
                        // .showScale(true)
                        // .color(y)
                        .color("green")
                        // .colorScale(Marker.Palette.BLUE_RED)
                        // .symbol(Symbol.DIAMOND_TALL)
                        .build())
                .name(String.format("to_visit (%d)", toVisit.size()))
                .build();

        // vehicles
        ScatterTrace vtrace =
            ScatterTrace.builder(vx, vy)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                    Marker.builder()
                        // .size(size)
                        .size(6)
                        // .showScale(true)
                        // .color(y)
                        .color("red")
                        // .colorScale(Marker.Palette.BLUE_RED)
                        // .symbol(Symbol.DIAMOND_TALL)
                        .build())
                .name(String.format("vehicles (%d)", vehicles.size()))
                .build();

        // centers
        ScatterTrace ctrace =
            ScatterTrace.builder(vx, vy)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                    Marker.builder()
                        // .size(size)
                        .size(4)
                        // .showScale(true)
                        // .color(y)
                        .color("orange")
                        // .colorScale(Marker.Palette.BLUE_RED)
                        // .symbol(Symbol.DIAMOND_TALL)
                        .build())
                .name(String.format("centers (%d)", centers.size()))
                .build();

        // graph
        ScatterTrace graph = ScatterTrace.builder(ex, ey)
            .mode(ScatterTrace.Mode.LINE)
            .name(String.format("roads (%d)", exy.length/2))
            .marker(Marker.builder()
                .line(Line.builder()
                    .width(1)
                    .build())
                // .color("aliceblue")
                .color("gray")
                .build())
            .build();

        Layout layout = Layout.builder()
            .title("UAE Locations and centers")
            .xAxis(Axis.builder().title("Longitude").build())
            .yAxis(Axis.builder().title("Latitude").build())
            .width((int)(700*1.3)).height((int)(600*1.3))
            .build();

        Plot.show(Figure.builder()
            .addTraces(
                graph,
                ltrace,
                // ttrace,
                vtrace
                // ctrace
            )
            .layout(layout).build(),
            "plot"
        );

    }
}
