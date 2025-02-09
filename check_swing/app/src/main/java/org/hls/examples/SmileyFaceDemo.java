package org.hls.examples;
import javax.swing.*;
import java.awt.EventQueue;

public class SmileyFaceDemo {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Artwork Component");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.add(new SmileyFaceComponent());
            frame.setVisible(true);
        });
    }
}
