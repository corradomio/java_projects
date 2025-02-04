package org.hls.example;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.*;
import tech.tablesaw.plotly.traces.ScatterTrace;

class CheckPlotly {

    private static final double[] x = {1, 2, 3, 4, 5, 6};
    private static final double[] y = {0, 1, 6, 14, 25, 39};
    private static final double[] size = {10.0, 33.0, 21.0, 40.0, 28.0, 16.0};

    public static void main(String[] args) {

        // -------------------------------------------------
        // Plotly

        ScatterTrace trace =
            ScatterTrace.builder(x, y)
                .mode(ScatterTrace.Mode.MARKERS)
                .marker(
                    Marker.builder()
                        // .size(size)
                        // .showScale(true)
                        // .color(y)
                        // .colorScale(Marker.Palette.BLUE_RED)
                        // .symbol(Symbol.DIAMOND_TALL)
                        .build()
                )
                .build();
        Layout layout = Layout.builder()
            .title("An example")
            .xAxis(Axis.builder().title("My X axis").build())
            .yAxis(Axis.builder().title("My Y axis").build())
            .build();
        Plot.show(Figure.builder().addTraces(trace).layout(layout).build());
    }
}
