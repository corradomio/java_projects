package optimtools.utilityplot;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;


public class JBufferedPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BufferedImage bi;

    public JBufferedPanel() {
        bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB); //just initialise to anysize
        // this.addMouseListener(new JBufferedPanel_itself_mouseAdapter(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi,0,0,null);
    }

    public void clear(){
        //interestingly this works
        int width = this.getSize().width;
        int height = this.getSize().height;

        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bi.getGraphics().setColor(Color.WHITE);
        bi.getGraphics().fillRect(0,0, width, height);
        this.getGraphics().drawImage(bi,0,0,null);
    }

}
