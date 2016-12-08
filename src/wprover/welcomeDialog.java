package wprover;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-1
 * Time: 9:57:46
 * To change this template use File | Settings | File Templates.
 */
public class welcomeDialog extends JPanel implements MouseListener {

    JLabel b2;
    Color color = new Color(206, 223, 242);
    GExpert gx;


    public welcomeDialog(GExpert f) {

        gx = f;
        JPanel panex = new JPanel();
        panex.setBorder(BorderFactory.createLineBorder(color, 4));

        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.addMouseListener(this);

        JLabel label = new JLabel(GExpert.createImageIcon("images/about/headline.jpg"));
        label.addMouseListener(this);
        label.setBackground(color);
        label.setForeground(color);
        label.setOpaque(false);
        panel.add(label);


        JPanel panel2 = new JPanel();
        panel2.addMouseListener(this);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        JLabel lv = new JLabel("Java Geometry Expert" + Version.getVersionf());
        lv.setHorizontalTextPosition(JLabel.LEFT);
        panel2.add(lv);

        lv = new JLabel("Built on " + Version.getData());
        lv.setHorizontalTextPosition(JLabel.RIGHT);
        panel2.add(lv);

        lv = new JLabel("JDK Version: 1.5.0 or higher");
        lv.setHorizontalTextPosition(JLabel.LEFT);
        panel2.add(lv);


        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.addMouseListener(this);

        JLabel b1 = new JLabel("for more information,please visit:");
        b1.addMouseListener(this);
        b2 = new JLabel(" http://woody.cs.wichita.edu");
        b2.addMouseListener(this);
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel3.add(b1);
        panel3.add(b2);
        panel2.add(panel3);
        panel3.setBackground(Color.white);
        b2.setForeground(Color.blue);

        b2.addMouseListener(this);
        panel.add(panel2);
        panex.add(panel);
        this.add(panex);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == b2)
            GExpert.openURL("http://woody.cs.wichita.edu");
        this.setVisible(false);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        //Component c = (Component) e.getSource();
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
        Component c = (Component) e.getSource();
        setCursor(Cursor.getDefaultCursor());
    }


    private static JDialog dlg;
    private static GExpert gxInstance;

    public static void start(GExpert gx) {
        gxInstance = gx;
    }

    public static void stop() {
        dlg.setVisible(false);
    }

    public JDialog runx() {
        JDialog dialog = new JDialog(gx.getFrame(), false);
        dlg = dialog;
        dialog.setUndecorated(true);
        welcomeDialog w = this; //new welcomeDialog(gx);
        Dimension dm = w.getPreferredSize();

        dialog.getContentPane().add(w);
        dialog.setSize(dm);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension sz = toolkit.getScreenSize();
        int x = (int) (sz.getWidth() / 2 - dm.getWidth() / 2);
        int y = (int) (sz.getHeight() / 2 - dm.getHeight() / 2);
        dialog.setLocation(x, y);
        this.repaint();
        dialog.setVisible(true);
        return dialog;
    }


}
