package com.example;
import javax.swing.*;
import java.awt.*;

public class JOptionPaneMultiInput {
    public static void main(String[] args) {
        JTextField xField = new JTextField(15);
        JTextField yField = new JTextField(15);
        JLabel xLabel = new JLabel("x:");
        // xLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // xLabel.setSize(50, -1);
        JLabel yLabel = new JLabel("y:");
        // yLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // yLabel.setSize(50, -1);

        JPanel myPanel = new JPanel();
        BoxLayout lm =
            // null;
            // new GridLayout(2, 2);
            new BoxLayout(myPanel, BoxLayout.Y_AXIS);

        myPanel.setLayout(lm);
        myPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // JPanel myPanel = new JPanel(lm)
        myPanel.add(xLabel);
        myPanel.add(xField);
        // myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(yLabel);
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
        }
    }
}
