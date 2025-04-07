package org.hls.examples;

import javax.swing.Icon;
import javax.swing.UIManager;

public class App {

    public static void main(String[] args) {
        Icon icon = UIManager.getIcon("FileView.directoryIcon");

        Class c = App.class;
    }
}
