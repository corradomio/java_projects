package org.hls.examples;

import java.awt.EventQueue;

public class SwingMainApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SwingMainForm frame = new SwingMainForm();
            frame.setVisible(true);
        });
    }
}
