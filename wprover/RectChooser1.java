package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-6-26
 * Time: 11:20:51
 * To change this template use File | Settings | File Templates.
 */
public class RectChooser1 extends JBaseDialog implements MouseListener, MouseMotionListener,
        ComponentListener, ActionListener, Runnable {

    private int x2, y2, x1, y1;
    private GExpert gxInstance;
    private JPanel contentPane;
    private JComponent content;
    private double dx, dy;
    private boolean result = false;

//    private int nnn = 1;

    public boolean getResult() {
        return result;
    }

    public RectChooser1(GExpert gx) {
        super(gx.getFrame(), "Choose Rect", true);
        JPanel ppp = new JPanel();
        ppp.setLayout(new BoxLayout(ppp, BoxLayout.Y_AXIS));


        gxInstance = gx;
        content = gx.getContent();
        Point pt = content.getLocation();
        dx = pt.getX();
        dy = pt.getY();

        contentPane = new JPanel() {
            public Dimension getPreferredSize() {
                return content.getSize();
            }


            public void paintComponent(Graphics g) {
                super.paintComponent(g);
//                super.paintAll();
                //this.pai
                Graphics2D g2 = (Graphics2D) g;
//                g2.translate(-dx, -dy);
                g2.drawImage(image, 0, 0, null);

//                gxInstance.getContentPane().paintComponents(g2);


                g2.translate(-dx, -dy);

                g2.setStroke(CMisc.DashedStroke1);

                g2.setColor(Color.red);
                g2.drawLine(x1, y1, x1, y2);
                g2.drawLine(x1, y1, x2, y1);
                g2.drawLine(x1, y2, x2, y2);
                g2.drawLine(x2, y1, x2, y2);


            }
        };
        contentPane.addMouseListener(this);
        contentPane.addMouseMotionListener(this);
        ppp.add(contentPane);


        JPanel bpane = new JPanel();
        bpane.setLayout(new BoxLayout(bpane, BoxLayout.X_AXIS));
        bpane.add(Box.createHorizontalGlue());
        JButton bok = new JButton(GExpert.getLanguage(3204, "OK"));
        JButton bcancel = new JButton(GExpert.getLanguage("Cancel"));
        bok.addActionListener(this);
        bcancel.addActionListener(this);
        bpane.add(bok);
        bpane.add(bcancel);
        ppp.add(bpane);

        this.getContentPane().add(ppp);


        this.pack();
        x1 = y1 = x2 = y2 = 0;
    }

    //
    BufferedImage image;

    public void setVisible(boolean r) {
        Dimension dm = content.getSize();
        image = gxInstance.getBufferedImage2(new Rectangle(0, 0, (int) dm.getWidth(), (int) dm.getHeight()));

        super.setVisible(r);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        x1 = (int) (e.getX() + dx);
        y1 = (int) (e.getY() + dy);
        x2 = x1;
        y2 = y1;
        this.repaint();
    }

    public Rectangle getRectangle() {
        return new Rectangle(x1 - (int) dx, y1 - (int) dy, x2 - x1, y2 - y1);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        x2 = (int) (e.getX() + dx);
        y2 = (int) (e.getY() + dy);
//        this.validate();

//        this.doLayout();
//        this.invalidate();
        this.repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        Point pt = content.getLocation();
        dx = pt.getX();
        dy = pt.getY();

    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void translate(int n) {
        x1 += n;
        y1 += n;
        x2 += n;
        y2 += n;
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                x1 -= 1;
                x2 -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                x1 += 1;
                x2 += 1;
                break;
            case KeyEvent.VK_UP:
                y1 -= 1;
                y2 -= 1;
                break;
            case KeyEvent.VK_DOWN:
                y1 += 1;
                y2 += 1;
                break;
            case KeyEvent.VK_ENTER:
                result = true;
                setVisible(false);
                break;
            default:
                return;

        }
        this.repaint();

    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equalsIgnoreCase("OK")) {
            result = true;
        } else {
            result = false;
        }
        this.setVisible(false);
    }


    public void paint(Graphics g) {
        super.paint(g);
    }

    public void run() {

    }
}
