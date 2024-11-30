//** Sid Shakya v1.1.**//

package optimtools.utilityplot;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>Title: </p>
 * <p>Description: This is a JFrame for Runtime performance plotter consisting of a JBufferedPanel </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @Sid
 * @version 1.0
 */

public class RuntimeAvgMaxPlotter extends JFrame {
    public JBufferedPanel performancePanel1;
    public double stepXp1;
    public double stepYp1;
    public double startXp1;
    public double startYp1;
    public double endXp1;
    public double endYp1;
    public int curMaxFitXp1;
    public int curMaxFitYp1;
    public int curAvgFitXp1;
    public int curAvgFitYp1;

    public double minGen;
    public double maxGen;
    public double minFit;
    public double maxFit;
    public Color col;

    private boolean initialized = false;

    public RuntimeAvgMaxPlotter(double minGen, double maxGen, double minFit, double maxFit, Color col, int width, int height){
        this.performancePanel1 = new JBufferedPanel();
        this.performancePanel1.setPreferredSize(new Dimension(290, 130));

        this.add(this.performancePanel1);
        this.setSize(width,height);

        // this.setVisible(true);
        // this.performancePanel1.clear(); //clear the main performance window

        this.minGen = minGen;
        this.maxGen = maxGen;
        this.minFit = minFit;
        this.maxFit = maxFit;
        this.col = col;

        // initPrefGraphs();
    }

    private void initPrefGraphs(){
        performancePanel1.bi = new BufferedImage(performancePanel1.getSize().width, performancePanel1.getSize().height, BufferedImage.TYPE_INT_ARGB);
        startXp1 = 20;
        startYp1 = performancePanel1.getSize().height-20;
        endXp1 = performancePanel1.getSize().width-20;
        endYp1 = 20;
        if (endXp1==0) endXp1=1;  //to avoid division by 0
        stepXp1 = (endXp1 - startXp1) / (maxGen-minGen);
        stepYp1 = (startYp1-endYp1) / (maxFit-minFit);

        Graphics g1 = performancePanel1.bi.getGraphics();

        g1.setColor(Color.BLACK);
        g1.setFont(new Font(null, Font.PLAIN,10));

        //draw rectangle for drawing area
        g1.drawRect((int)startXp1,(int)endYp1,(int)(endXp1 - startXp1),(int)(startYp1-endYp1));

        //print bounderies
        g1.drawString(Double.toString(maxFit),(int)(startXp1-10), (int)(endYp1-1));
        g1.drawString(Double.toString(minFit),(int)(startXp1-15), (int)(startYp1+10));
        g1.drawString(Double.toString(maxGen),(int)(endXp1-20),   (int)(startYp1+20));
        g1.drawString(Double.toString(minGen),(int)(startXp1),    (int)(startYp1+20));

        //draw grid in axis
        int division = 4;
        double gridSizeXp1 = (endXp1-startXp1) / division;
        double gridSizeYp1 = (startYp1-endYp1) / division;

        for (int i=0; i<division; i++) {
            g1.drawLine((int) (startXp1-2), (int) (startYp1 - (gridSizeYp1 * (i + 1))), (int) (startXp1+2), (int) (startYp1 - (gridSizeYp1 * (i + 1))));
            g1.drawLine((int) (startXp1 + (gridSizeXp1 * (i + 1))), (int) (startYp1-2), (int) (startXp1 + (gridSizeXp1 * (i + 1))), (int) (startYp1+2));
        }

        curMaxFitXp1 = (int) startXp1;
        curMaxFitYp1 = (int) startYp1;
        curAvgFitXp1 = (int) startXp1;
        curAvgFitYp1 = (int) startYp1;

        performancePanel1.getGraphics().drawImage(performancePanel1.bi,0,0,null);
    }

    /* public void setVisible(boolean visible) {
        super.setVisible(visible);
        this.performancePanel1.clear();
        this.initPrefGraphs();
    } */

    /* public void paintComponent(Graphics g) {
        if (!this.initialized) {
            super.setVisible(true);
            this.performancePanel1.clear();
            this.initPrefGraphs();
            this.initialized = true;
        }
        super.paintComponent(g);
    } */

    public void pointToPerfGraph(double curMaxFit, double curAvgFit, int curGen, int lineType)
    {
        Graphics g1bi = performancePanel1.bi.getGraphics();
        Graphics g1 = performancePanel1.getGraphics();  //for animation purpose

        //pointTo maxFit
        int newMaxFitXp1 = (int) (startXp1+((curGen - minGen) * stepXp1));
        int newMaxFitYp1 = (int) (startYp1-((curMaxFit - minFit) * stepYp1));

        //draw progress oval
        g1bi.setColor(Color.BLACK);
          g1.setColor(Color.BLACK);
        g1bi.drawOval(newMaxFitXp1, (int)endYp1, 1, 1);
          g1.drawOval(newMaxFitXp1, (int)endYp1, 1, 1);

        g1bi.setColor(col);
        g1.setColor(col);
        if (lineType==1){
            g1bi.drawLine(curMaxFitXp1, curMaxFitYp1, newMaxFitXp1, newMaxFitYp1);
              g1.drawLine(curMaxFitXp1, curMaxFitYp1, newMaxFitXp1, newMaxFitYp1);
        }

        if (newMaxFitYp1<startYp1) {
            g1bi.drawOval(newMaxFitXp1 - 1, newMaxFitYp1 - 2, 2, 2);
              g1.drawOval(newMaxFitXp1 - 1, newMaxFitYp1 - 2, 2, 2);
        }
        curMaxFitXp1 = newMaxFitXp1;
        curMaxFitYp1 = newMaxFitYp1;

        //pointTo avgFit
        g1bi.setColor(Color.BLACK);
        g1.setColor(Color.BLACK);
        int newAvgFitXp1 = (int) (startXp1+((curGen    - minGen) * stepXp1));
        int newAvgFitYp1 = (int) (startYp1-((curAvgFit - minFit) * stepYp1));

        if (lineType==1) {
            g1bi.drawLine(curAvgFitXp1, curAvgFitYp1, newAvgFitXp1, newAvgFitYp1);
              g1.drawLine(curAvgFitXp1, curAvgFitYp1, newAvgFitXp1, newAvgFitYp1);
        }
        if (newAvgFitYp1<startYp1) {
            g1bi.drawOval(newAvgFitXp1 - 1, newAvgFitYp1 - 1, 1, 1);
              g1.drawOval(newAvgFitXp1 - 1, newAvgFitYp1 - 1, 1, 1);
        }
        curAvgFitXp1 = newAvgFitXp1;
        curAvgFitYp1 = newAvgFitYp1;
    }


}
