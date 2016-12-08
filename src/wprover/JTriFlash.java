package wprover;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-4-5
 * Time: 13:12:36
 * To change this template use File | Settings | File Templates.
 */
public class JTriFlash extends JFlash implements ActionListener {

    int color;
    int n = 0;
    int nd = 0;
    int[] dax = new int[MAX];
    int[] day = new int[MAX];
    int[] dbx = new int[MAX];
    int[] dby = new int[MAX];
    int[] dcx = new int[MAX];
    int[] dcy = new int[MAX];
    protected CPoint A, B, C, A1, B1, C1;
    int fn = CMisc.POLYGON_ANIMATION_LAG * 6 - 1;

    int ndt, fnt = 0;


    public JTriFlash(JPanel p, CPoint a, CPoint b, CPoint c, CPoint a1,
                     CPoint b1, CPoint c1, boolean dir, int color) {
        super(p);
        this.color = color;
        if (a == null || b == null || c == null || a1 == null || b1 == null || c1 == null) return;

        A = a;
        B = b;
        C = c;
        A1 = a1;
        B1 = b1;
        C1 = c1;
//        dir = checkDirection();
//        init(dir);
        timer = new Timer(TIME_INTERVAL, this);
    }

    public void start() {
        boolean dir = checkDirection();
        init(dir);
        super.start();
    }


    private boolean checkDirection() {
        double d1 = (B.getx() - A.getx()) * (C.gety() - A.gety()) -
                (B.gety() - A.gety()) * (C.getx() - A.getx());
        double d2 = (B1.getx() - A1.getx()) * (C1.gety() - A1.gety()) -
                (B1.gety() - A1.gety()) * (C1.getx() - A1.getx());
        return d1 * d2 > 0;
    }

    private void init(boolean dir) {
        if (dir == true) {
            CPoint or, pd, pd1, pe, pe1;
            or = pd = pd1 = pe = pe1 = null;
            if (A == A1) {
                or = A;
                pd = B;
                pd1 = B1;
                pe = C;
                pe1 = C1;

            } else if (B == B1) {
                or = B;
                pd = A;
                pd1 = A1;
                pe = C;
                pe1 = C1;
            } else if (C == C1) {
                or = C;
                pd = A;
                pd1 = A1;
                pe = B;
                pe1 = B1;
            } else {
                or = null;
                pd = A;
                pd1 = A1;
                pe = B;
                pe1 = B1;
            }

            double cx, cy;
            if (or != null) {
                cx = or.getx();
                cy = or.gety();
            } else {
                cx = (A.getx() + B.getx() + C.getx()) / 3.0;
                cy = (A.gety() + B.gety() + C.gety()) / 3.0;
            }

            double dx1 = pd.getx() - cx;
            double dy1 = pd.gety() - cy;
            //dy1 = - dy1;
            double k1 = dy1 / dx1;
            double r1 = Math.atan(k1);
            if (dx1 < 0) {
                r1 += Math.PI;
            }
            double t = Math.sqrt(dx1 * dx1 + dy1 * dy1);
            dx1 = dx1 / t;
            dy1 = dy1 / t;

            double cx1, cy1;
            if (or != null) {
                cx1 = or.getx();
                cy1 = or.gety();
            } else {
                cx1 = (A1.getx() + B1.getx() + C1.getx()) / 3.0;
                cy1 = (A1.gety() + B1.gety() + C1.gety()) / 3.0;
            }

            double dx2 = pd1.getx() - cx1;
            double dy2 = pd1.gety() - cy1;
            //dy2 = - dy2;
            double k2 = dy2 / dx2;
            double r2 = Math.atan(k2);
            if (dx2 < 0) {
                r2 += Math.PI;
            }

            double dr = r2 - r1;
            if (dr > Math.PI) {
                dr = dr - 2 * Math.PI;
            } else if (dr < -Math.PI) {
                dr += 2 * Math.PI;
            }

            double astep = ASTEP;
            int na = (int) (Math.abs(dr) / astep);
            if (na < 30) {
                astep = astep * na / 30.0;
                na = 30;
            }

            double sax = A.getx() - cx;
            double say = A.gety() - cy;
            double sbx = B.getx() - cx;
            double sby = B.gety() - cy;
            double scx = C.getx() - cx;
            double scy = C.gety() - cy;

            double dx = (cx1 - cx) / na;
            double dy = (cy1 - cy) / na;

            double nn = (Math.sqrt(Math.pow(pd1.getx() - cx1, 2) + Math.pow(pd1.gety() - cy1, 2)) /
                    (Math.sqrt(Math.pow(pd.getx() - cx, 2) + Math.pow(pd.gety() - cy, 2))));

            double tn = (nn - 1) / na;
            double ag = 0;
            double st;
            if (dr < 0) {
                st = -astep;
            } else {
                st = astep;
            }
//            tn = 0;

            if (or == null) {
                for (int i = 0; i <= na; i++) {
                    double cs = Math.cos(ag);
                    double ss = Math.sin(ag);
                    double t1 = sax + sax * i * tn;
                    double t2 = say + say * i * tn;
                    double t3 = sbx + sbx * i * tn;
                    double t4 = sby + sby * i * tn;
                    double t5 = scx + scx * i * tn;
                    double t6 = scy + scy * i * tn;

                    dax[i] = (int) (cs * t1 - ss * t2 + cx + dx * (i));
                    day[i] = (int) (ss * t1 + cs * t2 + cy + dy * (i));
                    dbx[i] = (int) (cs * t3 - ss * t4 + cx + dx * (i));
                    dby[i] = (int) (ss * t3 + cs * t4 + cy + dy * (i));
                    dcx[i] = (int) (cs * t5 - ss * t6 + cx + dx * (i));
                    dcy[i] = (int) (ss * t5 + cs * t6 + cy + dy * (i));
                    ag += st;

                }
            } else {
                double mdd = 1.03;
                int nnn = (int) (Math.log(nn) / Math.log(mdd));
                for (int i = 0; i <= na; i++) {
                    double cs = Math.cos(ag);
                    double ss = Math.sin(ag);
                    double t1 = sax;
                    double t2 = say;
                    double t3 = sbx;
                    double t4 = sby;
                    double t5 = scx;
                    double t6 = scy;

                    dax[i] = (int) (cs * t1 - ss * t2 + cx + dx * (i));
                    day[i] = (int) (ss * t1 + cs * t2 + cy + dy * (i));
                    dbx[i] = (int) (cs * t3 - ss * t4 + cx + dx * (i));
                    dby[i] = (int) (ss * t3 + cs * t4 + cy + dy * (i));
                    dcx[i] = (int) (cs * t5 - ss * t6 + cx + dx * (i));
                    dcy[i] = (int) (ss * t5 + cs * t6 + cy + dy * (i));
                    ag += st;
                }
                double cs = Math.cos(dr);
                double ss = Math.sin(dr);
                double tn1 = (nn - 1) / nnn;

                for (int i = 0; i < nnn; i++) {
                    double t1 = sax + sax * i * tn1;
                    double t2 = say + say * i * tn1;
                    double t3 = sbx + sbx * i * tn1;
                    double t4 = sby + sby * i * tn1;
                    double t5 = scx + scx * i * tn1;
                    double t6 = scy + scy * i * tn1;

                    dax[i + na] = (int) (cs * t1 - ss * t2 + cx + dx * (i));
                    day[i + na] = (int) (ss * t1 + cs * t2 + cy + dy * (i));
                    dbx[i + na] = (int) (cs * t3 - ss * t4 + cx + dx * (i));
                    dby[i + na] = (int) (ss * t3 + cs * t4 + cy + dy * (i));
                    dcx[i + na] = (int) (cs * t5 - ss * t6 + cx + dx * (i));
                    dcy[i + na] = (int) (ss * t5 + cs * t6 + cy + dy * (i));
                }
                na += nnn;
            }

            dax[na] = (int) A1.getx();
            day[na] = (int) A1.gety();
            dbx[na] = (int) B1.getx();
            dby[na] = (int) B1.gety();
            dcx[na] = (int) C1.getx();
            dcy[na] = (int) C1.gety();

            n = na + 1;
            nd = -7;
        } else {
            nd = -7;
            n = 25;

            for (int i = 0; i < n; i++) {
                dax[i] = (int) (A.getx() * (n - i) / n + A1.getx() * i / n);
                day[i] = (int) (A.gety() * (n - i) / n + A1.gety() * i / n);
                dbx[i] = (int) (B.getx() * (n - i) / n + B1.getx() * i / n);
                dby[i] = (int) (B.gety() * (n - i) / n + B1.gety() * i / n);
                dcx[i] = (int) (C.getx() * (n - i) / n + C1.getx() * i / n);
                dcy[i] = (int) (C.gety() * (n - i) / n + C1.gety() * i / n);
            }
            dax[n] = (int) A1.getx();
            day[n] = (int) A1.gety();
            dbx[n] = (int) B1.getx();
            dby[n] = (int) B1.gety();
            dcx[n] = (int) C1.getx();
            dcy[n] = (int) C1.gety();
            n = n + 1;
        }

        ndt = nd;
        fnt = fn;

    }

    public void actionPerformed(ActionEvent e) {
        if (nd < n)
            nd++;
        else
            fn--;

        if (nd == n && fn == 0) {
            super.stop();
        }
        panel.repaint();
    }


    public void setColorIndex(int c) {
        color = c;
    }

    int[] p1 = new int[3];
    int[] p2 = new int[3];

    public void recalculate() {
        boolean dir = checkDirection();
        init(dir);
        nd = n;

    }

    public boolean draw(Graphics2D g2) {

        int index = nd;
        if (index < 0) {
            if (index % 2 != 0)
                drawLastTriangle(g2);
            else
                drawFirstTriangle(g2);
            return true;
        }

        if (index >= n) {
            index = n - 1;
        }
        Composite ac = g2.getComposite();
        g2.setComposite(CMisc.getFillComposite());

        if (index > 0)
            drawFirstTriangle(g2);

        drawTiangle(g2, index);
        g2.setComposite(ac);

        if (nd == n) {
            int nc = CMisc.POLYGON_ANIMATION_LAG * 2;
            int x = fn / nc;
            if (nc != 0 && x != 3) {
                drawColoredTriangleLine(2 - x, g2);
            }
        }

        return true;
    }

    public void drawFirstTriangle(Graphics2D g2) {
        p1[0] = dax[0];
        p1[1] = dbx[0];
        p1[2] = dcx[0];
        p2[0] = day[0];
        p2[1] = dby[0];
        p2[2] = dcy[0];
        g2.setColor(drawData.getColor(color - 1));
        g2.fillPolygon(p1, p2, 3);
        drawColoredTriangle(p1, p2, g2);
    }


    private void drawColoredTriangleLine(int index, Graphics2D g2) {

        if (fn == 0) return;

        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(Color.white);
        int n = nd - 1;
        boolean d = (fn % 2 != 0);


        switch (index) {
            case 0:
                g2.drawLine(dax[0], day[0], dbx[0], dby[0]);
                g2.drawLine(dax[n], day[n], dbx[n], dby[n]);
                if (d) {
                    g2.setColor(Color.pink);
                    g2.drawLine(dax[0], day[0], dbx[0], dby[0]);
                    g2.drawLine(dax[n], day[n], dbx[n], dby[n]);
                }
                break;
            case 1:
                g2.drawLine(dcx[0], dcy[0], dbx[0], dby[0]);
                g2.drawLine(dcx[n], dcy[n], dbx[n], dby[n]);
                if (d) {
                    g2.setColor(Color.green);
                    g2.drawLine(dcx[0], dcy[0], dbx[0], dby[0]);
                    g2.drawLine(dcx[n], dcy[n], dbx[n], dby[n]);
                }
                break;
            case 2:
                g2.drawLine(dax[0], day[0], dcx[0], dcy[0]);
                g2.drawLine(dax[n], day[n], dcx[n], dcy[n]);
                if (d) {
                    g2.setColor(Color.orange);
                    g2.drawLine(dax[0], day[0], dcx[0], dcy[0]);
                    g2.drawLine(dax[n], day[n], dcx[n], dcy[n]);
                }
                break;
            default:
                break;
        }
    }

    public void drawColoredTriangle(int[] p1, int[] p2, Graphics2D g2) {
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.black);
        g2.drawPolygon(p1, p2, 3);
    }

    public void drawLastTriangle(Graphics2D g2) {
        int index = n - 1;
        drawTiangle(g2, index);

    }

    public void drawTiangle(Graphics2D g2, int index) {
        p1[0] = dax[index];
        p1[1] = dbx[index];
        p1[2] = dcx[index];
        p2[0] = day[index];
        p2[1] = dby[index];
        p2[2] = dcy[index];

        Color c = drawData.getColor(color);
        if (c == null)
            c = drawData.getColor(color - drawData.LIGHTCOLOR);
        g2.setColor(c);

        g2.fillPolygon(p1, p2, 3);
        drawColoredTriangle(p1, p2, g2);

    }

}
