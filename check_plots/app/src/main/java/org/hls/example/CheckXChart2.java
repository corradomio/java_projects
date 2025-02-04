package org.hls.example;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Gaussian Blob
 *
 * Demonstrates the following:
 * <ul>
 * <li>ChartType.Scatter
 * <li>Series data as a Set
 * <li>Setting marker size
 * <li>Formatting of negative numbers with large magnitude but small differences
 */
public class CheckXChart2 implements ExampleChart<XYChart> {

    public static void main(String[] args) {

        ExampleChart<XYChart> exampleChart = new CheckXChart2();
        XYChart chart = exampleChart.getChart();
        new SwingWrapper<XYChart>(chart).displayChart();
    }

    @Override
    public XYChart getChart() {

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);

        // Series
        List<Double> xData = new LinkedList<Double>();
        List<Double> yData = new LinkedList<Double>();
        Random random = new Random();
        int size = 1000;
        for (int i = 0; i < size; i++) {
            xData.add(random.nextGaussian() / 1000);
            yData.add(-1000000 + random.nextGaussian());
        }
        chart.addSeries("Gaussian Blob", xData, yData);

        return chart;
    }

}
