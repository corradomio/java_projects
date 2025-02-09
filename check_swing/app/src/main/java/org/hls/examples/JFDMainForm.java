/*
 * Created by JFormDesigner on Sun Feb 09 12:16:26 GST 2025
 */

package org.hls.examples;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Corrado Mio
 */
public class JFDMainForm extends JFrame {

    private JPanel contentPane;
    private JDesktopPane desktop = new JDesktopPane();

    public JFDMainForm() {
        initComponents();
        initFrame();
    }

    private void initFrame() {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        desktop = new JDesktopPane();
        contentPane.add(desktop, BorderLayout.CENTER);

        this.setSize(1440, 900);

        this.newSolverItem.addActionListener(
            new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JInternalFrame iframe = new JFDChildForm();
                     desktop.add(iframe);
                     iframe.setSize(900, 600);
                     iframe.show();
                     iframe.setVisible(true);
                 }
             }
        );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Corrado Mio
        mainMenuBar = new JMenuBar();
        mainMenu = new JMenu();
        newSolverItem = new JMenuItem();
        exitItem = new JMenuItem();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainMenuBar ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("File");

                //---- newSolverItem ----
                newSolverItem.setText("New Solver");
                mainMenu.add(newSolverItem);

                //---- exitItem ----
                exitItem.setText("Exit");
                mainMenu.add(exitItem);
            }
            mainMenuBar.add(mainMenu);
        }
        setJMenuBar(mainMenuBar);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 398, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 247, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Corrado Mio
    private JMenuBar mainMenuBar;
    private JMenu mainMenu;
    private JMenuItem newSolverItem;
    private JMenuItem exitItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
