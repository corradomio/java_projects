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
import java.util.List;
import java.util.Map;

public class PlotSolution {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        // List<Location> centers = Location.load(new File("centers.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));
        List<Location>[] vpaths = Visited.load(new File("clustering.csv"));

        PlanarGraph pg = PlanarGraph.of(locations, 6.0);
        List<Edge> edges = pg.getEdges();

        double[][] pxy = Coords.getPathsCoords(vpaths);
        double[] px = pxy[0];
        double[] py = pxy[1];

        double[][] lxy = Coords.getCoords(locations);
        double[] lx = lxy[0];
        double[] ly = lxy[1];

        double[][] txy = Coords.getCoords(toVisit);
        double[] tx = txy[0];
        double[] ty = txy[1];

        double[][] vxy = Coords.getCoords(vehicles);
        double[] vx = vxy[0];
        double[] vy = vxy[1];

        Double[][] exy = Coords.getEdgesCoords(edges);
        Column<Double> ex = DoubleColumn.create("x", exy[0]);
        Column<Double> ey = DoubleColumn.create("x", exy[1]);

        // -------------------------------------------------
        // Plotly

        // paths
        ScatterTrace paths = ScatterTrace.builder(px, py)
            .mode(ScatterTrace.Mode.LINE)
            .name(String.format("paths (%d)", vpaths.length))
            .marker(Marker.builder()
                .line(Line.builder()
                    .width(1)
                    .build()
                )
                .color("orange")
                .build()
            )
            .build();

        // graph
        ScatterTrace graph = ScatterTrace.builder(ex, ey)
            .mode(ScatterTrace.Mode.LINE_AND_MARKERS)
            .name("roads")
            .marker(Marker.builder()
                .line(Line.builder()
                    .width(1)
                    .build()
                )
                .color("AliceBlue")
                .build()
            )
            .build();

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
                        .build()
                )
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
                        .build()
                )
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
                        .build()
                )
                .name(String.format("vehicles (%d)", vehicles.size()))
                .build();

        Layout layout = Layout.builder()
            .title("UAE Locations and centers")
            .xAxis(Axis.builder().title("Longitude").build())
            .yAxis(Axis.builder().title("Latitude").build())
            // .width(700).height(600)
            .width((int)((700./500)*900)).height(900)
            .build();

        Plot.show(Figure.builder()
                // .addTraces(graph)       // roads
                .addTraces(paths)       // vehicle paths
                // .addTraces(ltrace)      // locations
                .addTraces(ttrace)      // locations to visit
                .addTraces(vtrace)      // vehicles
                .layout(layout).build(),
            "plot"
        );

    }
}
