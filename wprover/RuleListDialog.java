package wprover;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;

public class RuleListDialog extends JBaseDialog {

    private GExpert gxInstance;
    private JScrollPane scroll;
    private RuleViewPane rpane;
    private ruleDpanel dpane;
    private JPanel split;

    public RuleListDialog(GExpert gx) {
        super(gx.getFrame());
        gxInstance = gx;
        init();
    }

    public RuleListDialog(GApplet1 gx) {
        super(gx.getFrame());
        gxInstance = null;
        init();
    }


    public void init() {
        if (gxInstance != null && CMisc.isApplication())
            this.setAlwaysOnTop(true);

//        if (gxInstance != null)
//            gxInstance.addDependentDialog(this);


        this.setTitle("Rule");
        this.setModal(false);

        scroll = new JScrollPane((rpane = new RuleViewPane(gxInstance)));

//        {
//            public Dimension getPreferredSize() {
//                return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
//            }
//        }));

        dpane = new ruleDpanel();

        split = new JPanel();
        split.setLayout(new BoxLayout(split, BoxLayout.Y_AXIS));
        split.add(scroll);
        split.add(dpane);
        this.getContentPane().add(split);
        this.setSize(500, 500);
        if (gxInstance != null)
            this.setLocation(gxInstance.getX(), gxInstance.getY() + gxInstance.getHeight() - 500);
    }

    public boolean loadRule(int t, int n) {
        grule r;
        if (t == 0)
            r = RuleList.getGrule(n);
        else
            r = RuleList.getFrule(n);
        if (r == null)
            return false;

        if (t == 0)
            this.setTitle("Rule " + n + " for GDD Method");
        else
            this.setTitle("Rule " + n + " for Full Angle Method");

        dpane.setRule(t, r);
        boolean rf = rpane.loadRule(t, n);
        rpane.centerAllObject();
        rpane.scrollToCenter();
        return rf;
    }

    class ruleDpanel extends JPanel implements MouseListener {
        private JLabel label1, label2;
        private JEditorPane epane;
        private int rt1, rt2;

        public Dimension getMaximumSize() {
            Dimension dm = super.getMaximumSize();
            Dimension dm2 = super.getPreferredSize();
            dm2.setSize(dm.getWidth(), dm2.getHeight());
            return dm2;
        }

        public ruleDpanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
            label1 = new JLabel();
            label1.setForeground(new Color(0, 128, 0));
            label1.addMouseListener(this);
            label2 = new JLabel();
            epane = new JEditorPane();
            epane.setEditable(false);
            p.add(label1);
            p.add(Box.createHorizontalStrut(5));
            p.add(label2);
            this.add(p);
            this.add(epane);
        }

        public void setRule(int t, grule r) {
            rt1 = t;
            rt2 = r.type;

            String sh;
            if (t == 0)
                sh = "RULE " + r.type;//+ " for Deductive Database Method";
            else sh = "RULE " + r.type;//+ " for Full Angle Method";
            label1.setText(sh);
            label2.setText(r.name);
            label1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            String s = r.discription;
            if (r.exstring != null)
                s += '\n' + r.exstring;
            epane.setText(s);
        }

        public void mouseClicked(MouseEvent e) {
            String dr = GExpert.getUserDir();
            String sp = GExpert.getFileSeparator();
            if (rt1 == 0)
                GExpert.openURL("file:///" + dr + sp + "help" + sp + "GDD" + sp + "r" + rt2 + ".html");
            else
                GExpert.openURL("file:///" + dr + sp + "help" + sp + "FULL" + sp + "r" + rt2 + ".html");

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

    }

    class RuleViewPane extends JPanel implements MouseListener, ComponentListener, MouseMotionListener {
        drawTextProcess dx;
        int xx, yy;
        double scale = 1.0;
        protected Rectangle rc = new Rectangle(0, 0, 0, 0);

        public Dimension getPreferredSize() {
            return new Dimension((int) rc.getWidth() + 20, (int) rc.getHeight() + 20);
        }

        public void resetSize() {
            if (dx == null) return;
            Vector v1 = dx.pointlist;
            Rectangle rc = this.getPointsBounds(v1);
            double rx = RuleViewPane.this.getWidth();
            double ry = RuleViewPane.this.getHeight();
            if (rc.getWidth() > rx || rc.getHeight() > ry) {
                double r1 = rx / (rc.getWidth() * 1.1);
                double r2 = ry / (rc.getHeight() * 1.1);
                scale = r1 < r2 ? r1 : r2;
            } else
                scale = 1.0;
            centerAllObject();
            scrollToCenter();
        }

        public void componentResized(ComponentEvent e) {
            resetSize();
        }

        public void scrollToCenter() {
            Rectangle rc1 = scroll.getViewport().getBounds();
            JScrollBar b1 = scroll.getHorizontalScrollBar();
            b1.setValue((int) ((b1.getMaximum() - rc1.getWidth()) / 2));
            b1 = scroll.getVerticalScrollBar();
            b1.setValue(((int) (b1.getMaximum() - rc1.getHeight()) / 2));
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentHidden(ComponentEvent e) {
        }

        public void centerAllObject() {
            Vector v1 = dx.pointlist;
            this.getPointsBounds(v1);

            xx = (int) ((this.getWidth() - rc.getWidth() * scale) / 2 - rc.getX() * scale);
            yy = (int) ((this.getHeight() - rc.getHeight() * scale) / 2 - rc.getY() * scale);
        }

        public boolean loadRule(int t, int n) {
            String s = new Integer(n).toString();

            try {
                GeoPoly.clearZeroN();

                if (n < 10)
                    s = "0" + s;


                if (CMisc.isApplication()) {
                    String sh;
                    if (t == 0)
                        sh = "examples/rulers/GDD/" + s + ".gex";
                    else sh = "examples/rulers/FULL/" + s + ".gex";
                    dx.Load(sh);
                } else {
                    String sh;
                    if (t == 0)
                        sh = "Examples/Rulers/GDD/" + s + ".gex";
                    else sh = "Examples/Rulers/FULL/" + s + ".gex";
                    loadRemote(sh);
                }

                return true;
            } catch (IOException ee) {
                JOptionPane.showMessageDialog(gxInstance, "Can not find file " + s + ".gex", "Not Found", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }

        private void loadRemote(String sh) {
            try {
                URL ul = new URL(/*gxInstance.getDocumentBase()*/ CMisc.getHomeDirectory(), sh);
                URLConnection urlc = ul.openConnection();
                urlc.connect();
                InputStream instream = urlc.getInputStream();
                DataInputStream in = new DataInputStream(instream);
                dx.Load(in);
            }
            catch (IOException ee) {
            }
        }

        public RuleViewPane(GExpert gx) {
            gxInstance = gx;
            xx = yy = 0;
            dx = new drawTextProcess();
            dx.setCurrentDrawPanel(this);
            dx.setRecal(false);
            dx.SetCurrentAction(drawProcess.MOVE);
            this.addMouseListener(this);
            this.setBackground(Color.white);
            this.addMouseMotionListener(this);
            this.addComponentListener(this);
            this.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int n = e.getWheelRotation();
                    if (scale < 0.4 && n < 0 || scale > 3.0 && n > 0) return;
                    scale = scale + n * 0.04;
                    if (dx == null) return;
                    centerAllObject();
                    RuleViewPane.this.repaint();
                }
            });
            //this.setBackground(Color.lightGray);
        }

        public Rectangle getPointsBounds(Vector v) {
            if (v.size() == 0) return rc;
            CPoint p1 = (CPoint) v.get(0);
            double x, y, x1, y1;
            x = x1 = p1.getx();
            y = y1 = p1.gety();
            for (int i = 1; i < v.size(); i++) {
                CPoint p = (CPoint) v.get(i);
                double x0 = p.getx();
                double y0 = p.gety();
                if (x0 > x)
                    x = x0;
                else if (x0 < x1)
                    x1 = x0;
                if (y0 > y)
                    y = y0;
                else if (y0 < y1)
                    y1 = y0;
            }
            rc.setBounds((int) x1, (int) y1, (int) (x - x1), (int) (y - y1));
            return rc;
        }

        public void mouseDragged(MouseEvent e) {
            dx.DWMouseDrag((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dx.reCalculate();
            dx.recal_allFlash();
            this.repaint();
        }

        public void mouseMoved(MouseEvent e) {
            dx.DWMouseMove((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dx.reCalculate();
            this.repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }


        public void mousePressed(MouseEvent e) {
            dx.DWButtonDown((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dx.reCalculate();
            dx.recal_allFlash();
            this.repaint();
        }


        public void mouseReleased(MouseEvent e) {
            dx.DWButtonUp((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dx.reCalculate();
            dx.recal_allFlash();
            this.repaint();
        }


        public void mouseEntered(MouseEvent e) {
        }


        public void mouseExited(MouseEvent e) {
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            resetSize();

            g2.translate(xx, yy);
            g2.scale(scale, scale);
            dx.paintPoint(g);
        }
    }
}
