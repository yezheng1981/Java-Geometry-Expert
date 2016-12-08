package wprover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Mar 19, 2007
 * Time: 12:40:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class JPolygonFlash extends JFlash implements ActionListener {
    private int c1, c2;
    private Vector vlist, vlist1;
    private int FTYPE = 0;
    private int nd = 0;
    private int n = 0;
    private double dx, dy, da, dx1, dy1, dx2, dy2;
    private int[] mx, my;
    private CPolygon poly1, poly2;
    private boolean R_Center = false;
    private double xc, yc;

    public JPolygonFlash(JPanel p, CPolygon poly1, CPolygon poly2, boolean oc, double xc, double yc, int c1, int c2, int tt) {  // A B C D ....A
        super(p);

        vlist = new Vector();
        vlist.addAll(poly1.pointlist);
        vlist1 = new Vector();
        vlist1.addAll(poly2.pointlist);
        this.c1 = c1;
        this.c2 = c2;
        R_Center = oc;
        this.xc = xc;
        this.yc = yc;

        vType = true;
        if (tt == 0)
            init();
        else
            init1();
        FTYPE = tt;

        timer = new Timer(TIME_INTERVAL, this);

        this.poly1 = poly1;
        this.poly2 = poly2;
  //      poly1.setVisible(false);
        poly2.setVisible(false);
    }


    private void init1() {
        int len = vlist.size();
        if (len == 0) return;
        int n = 0;

        for (int i = 0; i < len; i++) {
            CPoint p = (CPoint) vlist.get(i);
            CPoint p1 = (CPoint) vlist1.get(i);
            double d = Math.sqrt(Math.pow(p.getx() - p1.getx(), 2) + Math.pow(p.gety() - p1.gety(), 2));
            int t = (int) (d / CMisc.getMoveStep());
            if (t > n)
                n = t;
        }
        this.n = n;

        mx = new int[len];
        my = new int[len];
        this.nd = 0;
    }

    private void init() {
        int len = vlist.size();
        if (len == 0) return;

        CPoint p1, p2, p3, p4;
        p1 = p2 = null;
        for (int i = 0; i < vlist.size(); i++) {
            if (vlist.get(i) != vlist1.get(i)) {
                p1 = (CPoint) vlist.get(i);
                p2 = (CPoint) vlist1.get(i);
                break;
            }
        }

        if (R_Center) {
            dx1 = xc;
            dy1 = yc;
            dx2 = xc;
            dy2 = yc;
        } else {
            dx1 = getCentroidX(vlist);
            dy1 = getCentroidY(vlist);
            dx2 = getCentroidX(vlist1);
            dy2 = getCentroidY(vlist1);
        }


        double x = dx2 - dx1;
        double y = dy2 - dy1;

        int s = (int) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / CMisc.getMoveStep());
        p1 = (CPoint) vlist.get(0);
        p2 = (CPoint) vlist1.get(0);
        p3 = (CPoint) vlist.get(1);
        p4 = (CPoint) vlist1.get(1);

//        double r = CAngle.get4pAngle(p1.getx() + x, p1.gety() + y, dx2, dy2, p2.getx(), p2.gety());
        double r = CAngle.get4pAngle(p1.getx(), p1.gety(), p3.getx(), p3.gety(), p2.getx(), p2.gety(), p4.getx(), p4.gety());
        int s1 = (int) Math.abs(r / ASTEP);
        n = Math.max(s, s1);
        if (n >= 100 || n < 10)
            n = 10;

        dx = (dx2 - dx1) / n;
        dy = (dy2 - dy1) / n;
        da = r / n;

        mx = new int[len];
        my = new int[len];
        this.nd = 0;
    }

    public double getCentroidX(Vector v) {
        double dx1 = 0;
        int n = v.size();
        for (int i = 0; i < n; i++) {
            CPoint pt = (CPoint) v.get(i);
            dx1 += pt.getx();
        }
        dx1 /= n;
        return dx1;
    }

    public double getCentroidY(Vector v) {
        double dy1 = 0;
        int n = v.size();
        for (int i = 0; i < n; i++) {
            CPoint pt = (CPoint) v.get(i);
            dy1 += pt.gety();
        }
        dy1 /= n;
        return dy1;
    }

    public void actionPerformed(ActionEvent e) {
        if (nd <= n)
            nd++;

        if (nd > n)
            stop();

        panel.repaint();
    }

    public void stop() {
        poly2.setVisible(true);
        super.stop();
    }

    public void start() {
        poly2.setVisible(false);
        super.start();
    }


    public void recalculate() {
        int t = nd;
        nd = t;
        this.init();
    }

    public boolean draw(Graphics2D g2) {

        int ln = vlist.size();
        int index = nd;
        if (!isrRunning() && !isfinished())
            return false;

        if (FTYPE == 0) {
            if (index <= this.n) {
                double a = index * da;
                double sin = Math.sin(a);
                double cos = Math.cos(a);
                for (int i = 0; i < ln; i++) {
                    CPoint p = (CPoint) vlist.get(i);
                    double tx = p.getx() - dx1;
                    double ty = p.gety() - dy1;
                    double x = tx * cos - ty * sin;
                    double y = tx * sin + ty * cos;
                    tx = x + dx1 + index * dx;
                    ty = y + dy1 + index * dy;
                    mx[i] = (int) tx;
                    my[i] = (int) ty;
                }
            } else
                return false;
        } else {
            if (index <= this.n) {
                for (int i = 0; i < ln; i++) {
                    CPoint p = (CPoint) vlist.get(i);
                    CPoint p1 = (CPoint) vlist1.get(i);
                    mx[i] = (int) (p.getx() + (p1.getx() - p.getx()) * index / n);
                    my[i] = (int) (p.gety() + (p1.gety() - p.gety()) * index / n);
                }
            } else
                return false;
        }

        Composite ac = g2.getComposite();
        g2.setComposite(CMisc.getFillComposite());

        Color o1 = drawData.getColor(c1);
        Color o2 = drawData.getColor(c2);
        double r1 = ((double) index) / n;
        double r2 = 1 - r1;

        int r = (int) (o1.getRed() * r2 + o2.getRed() * r1);
        int g = (int) (o1.getGreen() * r2 + o2.getGreen() * r1);
        int b = (int) (o1.getBlue() * r2 + o2.getBlue() * r1);
        Color c = new Color(r, g, b);

        g2.setColor(c);
        g2.fillPolygon(mx, my, ln);
        g2.setComposite(ac);

        g2.setColor(Color.black);
        g2.setStroke(CMisc.NormalLineStroke);
        g2.drawPolygon(mx, my, ln);
        return true;
    }


    public void drawColoredTriangle(int[] p1, int[] p2, Graphics2D g2) {
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.black);
        g2.drawPolygon(p1, p2, 3);
    }

}