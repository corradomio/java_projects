package org.hls.examples;

import java.awt.EventQueue;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AppForm frame = new AppForm();
            frame.setVisible(true);
        });
    }
}
