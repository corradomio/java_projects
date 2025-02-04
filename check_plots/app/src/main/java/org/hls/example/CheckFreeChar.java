package org.hls.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class CheckFreeChar {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(50, "Category 1", "Series 1");
        dataset.addValue(60, "Category 1", "Series 2");
        dataset.addValue(40, "Category 2", "Series 1");
        dataset.addValue(70, "Category 2", "Series 2");

        JFreeChart barChart = ChartFactory.createBarChart(
            "Bar Chart Example",
            "Category",
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JFrame frame = new JFrame("Bar Chart");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
