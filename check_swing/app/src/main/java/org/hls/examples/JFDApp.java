package org.hls.examples;

import java.awt.*;

public class JFDApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFDForm frame = new JFDForm();
            frame.setVisible(true);
        });
    }
}
