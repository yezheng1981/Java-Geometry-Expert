package wprover;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JTlineFlash extends JFlash implements ActionListener {
    J_Line ln1, ln2;

    public JTlineFlash(JPanel p) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
        ln1 = new J_Line();
        ln2 = new J_Line();
    }

    public JTlineFlash(JPanel p, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
        ln1 = new J_Line();
        ln2 = new J_Line();
        ln1.addAPoint(p1);
        ln1.addAPoint(p2);
        ln2.addAPoint(p3);
        ln2.addAPoint(p4);
    }

    public boolean draw(Graphics2D g2) {

        g2.setColor(color);

        if (n % 2 == 0) {
            g2.setStroke(BStroke2);
            CPoint[] l1 = ln1.getMaxMinPoint();
            CPoint[] l2 = ln2.getMaxMinPoint();
            CPoint p1 = l1[0];
            CPoint p2 = l1[1];
            CPoint p3 = l2[0];
            CPoint p4 = l2[1];
            double[] r = interect_LL(p1.getx(), p1.gety(), p2.getx(), p2.gety(), p3.getx(), p3.gety(), p4.getx(), p4.gety());
            if (r != null && r.length == 2) {
                CPoint m1, m2;
                if (d2(r[0], r[1], p1.getx(), p1.gety()) > d2(r[0], r[1], p2.getx(), p2.gety()))
                    m1 = p1;
                else m1 = p2;

                if (d2(r[0], r[1], p3.getx(), p3.gety()) > d2(r[0], r[1], p4.getx(), p4.gety()))
                    m2 = p3;
                else m2 = p4;
                drawTTFoot(g2, r[0], r[1], m1, m2);
                drawLL3(g2, (int) p1.getx(), (int) p1.gety(), (int) p2.getx(), (int) p2.gety(), (int) r[0], (int) r[1]);
                drawLL3(g2, (int) p3.getx(), (int) p3.gety(), (int) p4.getx(), (int) p4.gety(), (int) r[0], (int) r[1]);
                ln1.drawPt(g2);
                ln2.drawPt(g2);
            }
        }
        return true;
    }

    double d2(double x, double y, double x1, double y1) {
        return Math.pow(x - x1, 2) + Math.pow(y - y1, 2);
    }

    void drawLL3(Graphics2D g2, int x1, int y1, int x2, int y2, int x3, int y3) {
        int x, y, tx, ty;

        if (x1 < x2 || (x1 == x2 && y1 < y2)) {
            x = x1;
            y = y1;
            tx = x2;
            ty = y2;
        } else {
            x = x2;
            y = y2;
            tx = x1;
            ty = y1;
        }
        if (x3 < x) {
            x = x3;
            y = y3;
        } else if (x3 > tx) {
            tx = x3;
            ty = y3;
        } else  // x3 == x
        {
            if (y1 < y2) {
                x = x1;
                y = y1;
                tx = x2;
                ty = y2;
            } else {
                x = x2;
                y = y2;
                tx = x1;
                ty = y1;
            }
            if(y3 < y)
            {
                x = x3;
                y = y3;
            }else if(y3 > ty)
            {
                tx = x3;
                ty = y3;
            }
        }
        g2.drawLine(x, y, tx, ty);
    }

    public void drawTTFoot(Graphics2D g2, double x, double y, CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null) return;

        double step = 14;

        double dx = p1.getx() - x;
        double dy = p1.gety() - y;
        double len = Math.sqrt(dx * dx + dy * dy);
        if (len == 0.0) return;
        dx = (dx / len) * step;
        dy = (dy / len) * step;

        double dx1, dy1;
        dx1 = p2.getx() - x;
        dy1 = p2.gety() - y;
        len = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        if (len == 0.0) return;
        dx1 = (dx1 / len) * step;
        dy1 = (dy1 / len) * step;

        double fx = x;
        double fy = y;
        double ex = fx + dx1 + dx;
        double ey = fy + dy1 + dy;
        g2.drawLine((int) (fx + dx), (int) (fy + dy), (int) (ex), (int) (ey));
        g2.drawLine((int) (fx + dx1), (int) (fy + dy1), (int) (ex), (int) (ey));
    }

    public void actionPerformed(ActionEvent e) {
        n--;
        if (n <= 0)
            super.stop();
        panel.repaint();
    }

    public void stop() {
        super.stop();
    }

    public boolean containPt(CPoint p) {
        return ln1.containPt(p) && ln2.containPt(p);
    }

}
