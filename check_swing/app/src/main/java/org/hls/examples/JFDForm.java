/*
 * Created by JFormDesigner on Wed Jan 29 19:34:29 GST 2025
 */

package org.hls.examples;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Corrado Mio
 */
public class JFDForm extends JFrame {
    public JFDForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Corrado Mio
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();

        //======== this ========
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setPreferredSize(new Dimension(900, 700));
        var contentPane = getContentPane();

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 883, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGap(0, 512, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Panel1", panel1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(tabbedPane1)
                    .addGap(0, 0, 0))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(tabbedPane1)
                    .addGap(0, 0, 0))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Corrado Mio
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
