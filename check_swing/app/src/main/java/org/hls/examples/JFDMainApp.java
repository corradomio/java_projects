package org.hls.examples;

import java.awt.EventQueue;

public class JFDMainApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFDMainForm frame = new JFDMainForm();
            frame.setVisible(true);
        });
    }
}
