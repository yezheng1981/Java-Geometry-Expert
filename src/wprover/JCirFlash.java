package wprover;

import wprover.CPoint;
import wprover.CMisc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-3
 * Time: 12:12:16
 * To change this template use File | Settings | File Templates.
 */
public class JCirFlash extends JFlash implements ActionListener {

    CPoint o;
    Vector vlist = new Vector();

    public JCirFlash(JPanel p) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
    }

    public boolean draw(Graphics2D g2) {


        if (o != null && vlist.size() >= 1) {
            CPoint p1 = (CPoint) vlist.get(0);
            double radius = Math.sqrt(Math.pow(o.getx() - p1.getx(), 2) + Math.pow(o.gety() - p1.gety(), 2));
            g2.setStroke(BStroke2);
            g2.setColor(Color.white);
            g2.drawOval((int) (o.getx() - radius), (int) (o.gety() - radius), (int) (2 * radius), (int) (2 * radius));
            if (n % 2 == 0) {
                super.setDrawDashFb2(g2);
                g2.drawOval((int) (o.getx() - radius), (int) (o.gety() - radius), (int) (2 * radius), (int) (2 * radius));
            }

        } else if (vlist.size() >= 3) {
            CPoint p1 = (CPoint) vlist.get(0);
            CPoint p2 = (CPoint) vlist.get(1);
            CPoint p3 = (CPoint) vlist.get(2);

            double x_1 = p1.getx();
            double x_2 = p1.gety();
            double x_3 = p2.getx();
            double x_4 = p2.gety();
            double x_5 = p3.getx();
            double x_6 = p3.gety();
            if (isZero(x_6 - x_2)) {
                double t = x_3;
                x_3 = x_5;
                x_5 = t;
                t = x_4;
                x_4 = x_6;
                x_6 = t;
            }

            double m = (2 * (x_3 - x_1) * x_6 + (-2 * x_4 + 2 * x_2) * x_5 + 2 * x_1 * x_4 - 2 * x_2 * x_3);

            double x = (x_4 - x_2) * x_6 * x_6
                    + (-1 * x_4 * x_4 - x_3 * x_3 + x_2 * x_2 + x_1 * x_1) * x_6
                    + (x_4 - x_2) * x_5 * x_5 + x_2 * x_4 * x_4
                    + (-1 * x_2 * x_2 - x_1 * x_1) * x_4 + x_2 * x_3 * x_3;

            x = (-1) * x / m;

            double y = (-1) * ((2 * x_5 - 2 * x_1) * x
                    - x_6 * x_6 - x_5 * x_5 + x_2 * x_2 + x_1 * x_1) / ((2 * x_6 - 2 * x_2));

            double radius = Math.sqrt(Math.pow(x - x_1, 2) + Math.pow(y - x_2, 2));

            g2.setStroke(BStroke2);
            g2.setColor(Color.white);
            g2.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
            if (n % 2 == 0) {
                super.setDrawDashFb2(g2);
                g2.setColor(Color.red);
                g2.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
            }
        }


        if (n % 2 == 0) {
            if (o != null)
                o.draw(g2);
            for (int i = 0; i < vlist.size(); i++) {
                CPoint pt = (CPoint) vlist.get(i);
                pt.draw_wt(g2);
            }
        }
        return true;
    }

    public void setCenter(CPoint t) {
        if (t == null) return;
        if (t != null)
            this.o = (CPoint) t;
    }

//    public void addAPoint(CPoint pt) {
//
//        if (pt != null && !vlist.contains(pt))
//            vlist.add(pt);
//    }

    public void addAPoint(CPoint pt) {
        if (pt != null && !vlist.contains(pt))
            vlist.add(pt);
    }

    public void actionPerformed(ActionEvent e) {
        n--;
        if (n <= 0) super.stop();

        for (int i = 0; i < vlist.size(); i++) {
            CPoint pt = (CPoint) vlist.get(i);
            if (n % 2 != 0)
                pt.setInFlashing(true);
            else
                pt.setInFlashing(false);
        }

        panel.repaint();
    }

    public void stop() {
        super.stop();
        for (int i = 0; i < vlist.size(); i++) {
            CPoint ln = (CPoint) vlist.get(i);
            ln.setInFlashing(false);
        }
    }


}
