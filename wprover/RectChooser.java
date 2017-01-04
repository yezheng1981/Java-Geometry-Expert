package wprover;

import UI.DropShadowBorder;
import UI.EntityButtonUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-10
 * Time: 19:06:31
 * To change this template use File | Settings | File Templates.
 */
public class RectChooser extends JBaseDialog implements MouseListener, MouseMotionListener,
        MouseWheelListener, ActionListener {

    private static Color color = new Color(190, 210, 230);
    private GExpert gxInstance;
    private drawPane dpane;
    private int width, height;
    private int rx, ry;
    private double zoom;

    private Rectangle rc;
    private boolean pressed = false;
    private TextField field1, field2, field3, field4, fieldw, fieldh;
    private JButton bok, bcancel;
    private boolean result = false;

    public RectChooser(GExpert gx) {
        super(gx.getFrame(), true);
        this.setTitle(gx.getLanguage(2300, " Please Choose The Client Area."));
        this.gxInstance = gx;
//        this.setUndecorated(true);
        rx = ry = 0;
        zoom = 1.00;

//        this.addWindowListener((new WindowDispose(this));
        DPanel d = gx.d;
        width = d.getWidth();
        height = d.getHeight();
        dpane = new drawPane();
        Dimension dm = new Dimension(width, height);
        dpane.setPreferredSize(dm);
        dpane.setMinimumSize(dm);
        dpane.addMouseListener(this);
        dpane.addMouseMotionListener(this);
        dpane.addMouseWheelListener(this);
        dpane.setBackground(gx.d.getBackground());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(dpane);

        JPanel bpane = new JPanel();
        bpane.setLayout(new BoxLayout(bpane, BoxLayout.X_AXIS));
        bpane.add(new JLabel("X="));
        field1 = new TextField();

        bpane.add(field1);
        bpane.add(new JLabel("Y="));
        field2 = new TextField();
        bpane.add(field2);
        bpane.add(new JLabel("X1="));
        field3 = new TextField();
        bpane.add(field3);
        bpane.add(new JLabel("Y1="));
        field4 = new TextField();
        bpane.add(field4);
        bpane.add(new JLabel("W="));
        fieldw = new TextField();
        fieldw.setEditable(false);
        bpane.add(fieldw);
        bpane.add(new JLabel("H="));
        fieldh = new TextField();
        fieldh.setEditable(false);
        bpane.add(fieldh);
        bpane.add(Box.createHorizontalGlue());
        bok = new JButton(gx.getLanguage(3204, "OK"));
        bcancel = new JButton(gx.getLanguage("Cancel"));
        bpane.add(bok);
        bpane.add(bcancel);
        bok.addActionListener(this);
        bcancel.addActionListener(this);
        content.add(bpane);
        this.add(content);

        rc = gx.dp.getBounds();
        enlargeRect();
        rx = (int) (rc.getX() + rc.getWidth() / 2);
        ry = (int) (rc.getY() + rc.getHeight() / 2);


        updateField();
        updateButton();

        Dimension dm1 = content.getPreferredSize();
        double ww = dm1.getWidth();
        double hh = dm1.getHeight();

        int w1 = gx.getWidth();
        int h1 = gx.getHeight();

        if (ww > w1) {
            ww = w1;
        }
        if (hh > h1)
            hh = h1;


        dm1.setSize(ww, hh + 35);

        this.setSize(dm1);
        gx.centerDialog(this);
        this.setVisible(true);
    }


    public void enlargeRect() {
        int edge = 15;
        int x = (int) rc.getX() - edge;
        int y = (int) rc.getY() - edge;

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        rc.setLocation(x, y);
        rc.setSize((int) rc.getWidth() + 2 * edge, (int) rc.getHeight() + 2 * edge);
    }

    public boolean getReturnResult() {
        return result;
    }

    public Rectangle getSelectedRectangle() {
        return rc;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bok) {
            result = true;
        } else {
            result = false;
        }
        this.setVisible(false);

    }

    public void updateField() {
        field1.setText(Integer.toString((int) rc.getX()));
        field2.setText(Integer.toString((int) rc.getY()));
        field3.setText(Integer.toString((int) (rc.getX() + rc.getWidth())));
        field4.setText(Integer.toString((int) (rc.getX() + rc.getHeight())));
        fieldw.setText(Integer.toString((int) rc.getWidth()));
        fieldh.setText(Integer.toString((int) rc.getHeight()));
    }

    public void updateButton() {
        if (pressed)
            bok.setEnabled(false);
        else if (Math.abs(rc.getWidth()) < 10 || Math.abs(rc.getHeight()) < 10)
            bok.setEnabled(false);
        else bok.setEnabled(true);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        pressed = true;
        int x = e.getX();
        int y = e.getY();
        x = getZoomX(x);
        y = getZoomY(y);

        if (Math.abs(x) < 5)
            x = 0;
        if (Math.abs(y) < 5)
            y = 0;

        rc.setLocation(x, y);
        rc.setSize(0, 0);
        updateField();
        updateButton();
        dpane.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        pressed = false;
        updateButton();
        if (Math.abs(rc.getWidth()) < 10 || Math.abs(rc.getHeight()) < 10) {
            rc.setLocation(0, 0);
            rc.setSize(0, 0);
            this.updateField();
        }

        dpane.repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        dragto(x, y);
    }

    public void dragto(int x, int y) {
        int x1 = (int) rc.getX();
        int y1 = (int) rc.getY();
        x = getZoomX(x);
        y = getZoomY(y);

        int w = x - x1;
        int h = y - y1;
        rc.setSize(w, h);
        updateField();
        updateButton();
        dpane.repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int n = e.getScrollAmount();
        int r = e.getWheelRotation();
        n = Math.abs(n);

        double rz = 0.0;
        if (r > 0) {
            rz = 0.96;
        } else {
            rz = 1 / 0.96;
        }
        zoom *= Math.pow(rz, n);
        dpane.repaint();
    }

    public int getZoomX(int x) {
        x = x - rx;
        return (int) (x / zoom + rx);
    }

    public int getZoomY(int y) {
        y = y - ry;
        return (int) (y / zoom + ry);
    }


    class TextField extends JTextField {
        public TextField() {
            super();
        }

        public Dimension getPreferredSize() {
            Dimension dm = super.getPreferredSize();
//            if (dm.getWidth() > 30)
            dm.setSize(40, dm.getHeight());
            return dm;
        }

        public Dimension getMaximumSize() {
            Dimension dm = super.getMaximumSize();
//            if (dm.getWidth() > 30)
            dm.setSize(40, dm.getHeight());
            return dm;
        }

        public int getValue() {
            String s = this.getText();
            return Integer.parseInt(s);
        }

    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        double x = rc.getX();
        double y = rc.getY();

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                x -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                x += 1;
                break;
            case KeyEvent.VK_UP:
                y -= 1;
                break;
            case KeyEvent.VK_DOWN:
                y += 1;
                break;
            case KeyEvent.VK_ENTER:
                result = true;
                setVisible(false);
                break;
            default:
                return;
        }
        rc.setLocation((int) x, (int) y);
        this.updateField();
        this.repaint();

    }

    class drawPane extends JPanel {

        public drawPane() {
            this.addMouseListener(RectChooser.this);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(rx, ry);
            g2.scale(zoom, zoom);
            g2.translate(-rx, -ry);
            if (gxInstance != null) {
                gxInstance.dp.paintPoint(g);
                g2.setColor(Color.red);
                // g2.setStroke(CMisc.NormalLineStroke);
                g2.setStroke(CMisc.DashedStroke1);
                if (Math.abs(rc.getWidth()) > 10 && Math.abs(rc.getHeight()) > 10) {
                    g2.drawRect((int) rc.getX(), (int) rc.getY(), (int) rc.getWidth(), (int) rc.getHeight());
                }
            }
        }
    }
}
