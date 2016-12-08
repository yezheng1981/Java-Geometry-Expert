package wprover;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Mar 23, 2007
 * Time: 11:41:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSegmentMoveingFlash extends JFlash implements ActionListener {

    private CPoint p1, p2, p3, p4;
    private int c1, c2;
    private int nd, n, nstep;
    private double da = 0;
    private double xc, yc, dxc, dyc;


    public JSegmentMoveingFlash(JPanel p, CPoint p1, CPoint p2, CPoint p3, CPoint p4, int c1, int c2) {
        super(p);

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.c1 = c1;
        this.c2 = c2;
        init();
        nd = -3;
        nstep = -3;
        timer = new Timer(TIME_INTERVAL, this);
        super.vType = true;

    }

    private void init() {
        double r = CMisc.getMoveStep();
        double x1 = (p1.getx() + p2.getx()) / 2;
        double x2 = (p3.getx() + p4.getx()) / 2;
        double y1 = (p1.gety() + p2.gety()) / 2;
        double y2 = (p3.gety() + p4.gety()) / 2;

        int n1 = (int) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / r);
        int m1 = n1;

        boolean rt = false;
        if (p1 == p3 || p2 == p3) {
            xc = p3.getx();
            yc = p3.gety();
            dxc = dyc = 0;
            rt = true;
            if (p2 == p3) {
                CPoint pt = p2;
                p2 = p1;
                p1 = pt;
            }
        } else if (p1 == p4 || p2 == p4) {
            xc = p4.getx();
            yc = p4.gety();
            dxc = dyc = 0;
            rt = true;
            if (p1 == p4) {
                CPoint pt = p2;
                p2 = p1;
                p1 = pt;
            }
        } else {
            xc = x1;
            yc = y1;
            dxc = (x2 - x1);
            dyc = (y2 - y1);
        }

        n = m1;
        double xt2 = p2.getx() + p3.getx() - p1.getx();
        double yt2 = p2.gety() + p3.gety() - p1.gety();
        double r2 = Math.PI + CAngle.get3pAngle(xt2, yt2, p3.getx(), p3.gety(), p4.getx(), p4.gety());
        if (r2 >= Math.PI)
            r2 = r2 - 2 * Math.PI;
        if (r2 <= -Math.PI)
            r2 = r2 + 2 * Math.PI;

        if (p1 == p3 && p2 == p4 || p1 == p4 && p2 == p3)
            r2 = 0;
        else if (Math.abs(r2) < CMisc.ZERO && rt)
            r2 = Math.PI;

        int m2 = (int) (r2 / ASTEP);
        n = Math.max(m1, m2);
        if (n < MIN_STEP)
            n = MIN_STEP;
        da = r2 / n;
    }

    public boolean draw(Graphics2D g2) {
        double r = (double) (n - nd) / n;
        Color c = getRatioColor(c1, c2, r);
        g2.setStroke(JFlash.BStroke2);
        g2.setColor(c);

        if (nd >= 0 && nd != n) {

            double dxt = (dxc) * nd / n;
            double dyt = (dyc) * nd / n;
            double tx1 = p1.getx() - xc;
            double ty1 = p1.gety() - yc;
            double tx2 = p2.getx() - xc;
            double ty2 = p2.gety() - yc;

            double a = nd * da;
            double sin = Math.sin(a);
            double cos = Math.cos(a);
            double x1 = tx1 * cos - ty1 * sin + xc + dxt;
            double y1 = tx1 * sin + ty1 * cos + yc + dyt;

            double x2 = tx2 * cos - ty2 * sin + xc + dxt;
            double y2 = tx2 * sin + ty2 * cos + yc + dyt;
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        } else if (nd < 0) {
            g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(), (int) p2.gety());
        } else if (nd == n && nstep <= 0) {
            g2.drawLine((int) p3.getx(), (int) p3.gety(), (int) p4.getx(), (int) p4.gety());
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        nd++;
        if (nd >= n) {
            nd = n;
            if (nstep == 0)
                stop();
            else
                nstep++;
        }
        panel.repaint();
    }


}
