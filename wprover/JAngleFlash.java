package wprover;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gprover.*;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-3
 * Time: 12:22:53
 * To change this template use File | Settings | File Templates.
 */
public class JAngleFlash extends JFlash implements ActionListener {
    private int radius = RAD;
    CPoint p1, p2, p3, p4;
    private boolean dl1, dl2, inter;
    private int ftype = 1; // full;.
    private int value1 = 1;


    public JAngleFlash(JPanel p) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
        inter = dl1 = dl2 = true;
    }

    public void setAngleTwoLineIntersected(boolean r) {
        inter = r;
    }

    public void setDrawLine1(boolean r) {
        dl1 = r;
    }

    public void setDrawLine2(boolean r) {
        dl2 = r;
    }

    public CPoint getCommonPoint() {
        if (p1 == p3 || p1 == p4) {
            return p1;
        }
        if (p2 == p3 || p2 == p4) {
            return p2;
        }
        return null;
    }

    public JAngleFlash(JPanel p, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
        this.p1 = (p1);
        this.p2 = (p2);
        this.p3 = (p3);
        this.p4 = (p4);
        dl1 = dl2 = true;
    }

    public void setFtype(int t) {
        ftype = t;
    }


    public void setRadius(int r) {
        radius = r;
    }

    public int getRadius() {
        return radius;
    }

    public void actionPerformed(ActionEvent e) {
        n--;
        if (n <= 0) {
            super.stop();
        }
        panel.repaint();
    }

    public boolean draw(Graphics2D g2) {
        if (n % 2 == 0) {
            g2.setColor(color);
            if (drawbase.check_para(p1, p2, p3, p4)) {
                g2.setStroke(BStroke2);
                g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(),
                        (int) p2.gety());
                g2.drawLine((int) p3.getx(), (int) p3.gety(), (int) p4.getx(),
                        (int) p4.gety());
            } else {

                g2.setStroke(BStroke);
                drawMAngle(p1.getx(), p1.gety(), p2.getx(), p2.gety(), p3.getx(), p3.gety(), p4.getx(), p4.gety(), radius, g2);
                drawAllLine(g2);
            }
        }
        return true;
    }

    public void drawMAngle(double x1, double y1, double x2, double y2,
                           double x3, double y3, double x4, double y4,
                           int rad, Graphics2D g2) {

        double r[] = interect_LL(x1, y1, x2, y2, x3, y3, x4, y4);
        if (r == null) {
            return;
        }
        if (Math.abs(r[0]) > 10000 || Math.abs(r[1]) > 10000) {
            return;
        }
        int radius = rad;
        double psx, psy;
        double pex, pey;
        psx = psy = pex = pey = 0;
        if (spt(x1, y1, r[0], r[1])) {
            psx = x2;
            psy = y2;

        } else {
            psx = x1;
            psy = y1;
        }

        if (spt(x3, y3, r[0], r[1])) {
            pex = x4;
            pey = y4;

        } else {
            pex = x3;
            pey = y3;
        }

        double dx1 = psx - r[0];
        double dy1 = psy - r[1];
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0) {
            r1 += Math.PI;
        }

        double t = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        dx1 = dx1 / t;
        dy1 = dy1 / t;

        double dx2 = pex - r[0];
        double dy2 = pey - r[1];

        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0) {
            r2 += Math.PI;
        }

        t = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        dx2 = dx2 / t;
        dy2 = dy2 / t;

        double jx = dx2 * radius + r[0];
        double jy = (-1) * dy2 * radius + r[1];

        int ri1 = (int) (180 * r1 / Math.PI);
        double dr = (180 * (r2 - r1) / Math.PI);
        double tr = dr;


        if (dr > 180) {
            dr = dr - 360;
        } else if (dr < -180) {
            dr = dr + 360;
        }


        double rc = dr * Math.PI / (360);
        double cx = (dx1 * Math.cos(rc) - dy1 * Math.sin(rc)) * (radius + 6) + r[0];
        double cy = -(dy1 * Math.cos(rc) + dx1 * Math.sin(rc)) * (radius + 6) + r[1];


        double dr1 = dr;
        double k = dr1;
        k = Math.abs(k);

        if (ftype == 1) {
            double lr = 12.0;
            double alpha = -Math.PI / 3;

            if (dr > 0) {
                alpha = -alpha;
                dr = (dr - 180 * Math.cos(Math.PI / 2.0 - alpha) * lr * 1 / (3 * Math.PI * radius));
                if (dr < 0) dr = 0;
            } else {
                dr = (dr + 180 * Math.cos(Math.PI / 2.0 + alpha) * lr * 1 / (3 * Math.PI * radius));
                if (dr > 0)
                    dr = 0;
            }
            dy2 = -dy2;

            double c1 = Math.cos(alpha);
            double s1 = Math.sin(alpha);
            double c2 = Math.cos(Math.PI - alpha);
            double s2 = Math.sin(Math.PI - alpha);


            double jx1 = (dx2 * c1 - dy2 * s1) * lr + jx;
            double jy1 = (dy2 * c1 + dx2 * s1) * lr + jy;
            double jx2 = (dx2 * c2 - dy2 * s2) * lr + jx;
            double jy2 = (dy2 * c2 + dx2 * s2) * lr + jy;


            if (dr1 * 10 - k > 0.5 && dr1 > 0)
                k = k + 1;
            else if (dr1 * 10 - k < -0.5 && dr1 < 0)
                k = k - 1;

            if (dr >= 0)
                dr += 0.5;
            else
                dr -= 0.5;

            int[] xp = new int[4];
            int[] yp = new int[4];
            xp[0] = (int) jx;
            xp[1] = (int) jx1;
            xp[2] = (int) (jx1 / 3 + jx2 / 3 + jx / 3);
            xp[3] = (int) jx2;
            yp[0] = (int) jy;
            yp[1] = (int) jy1;
            yp[2] = (int) (jy1 / 3 + jy2 / 3 + jy / 3);
            yp[3] = (int) jy2;
            g2.fillPolygon(xp, yp, 4);
        }

//        g2.drawArc((int) r[0] - radius, (int) r[1] - radius, radius * 2, radius * 2, ri1, (int) dr);
        int x = (int) r[0] - radius;
        int y = (int) r[1] - radius;
        int w = radius * 2;


        if (ftype == 2) {
            for (int i = 1; i < value1; i++) {
                g2.drawArc(x + i * 4, y + i * 4, w - i * 8, w - i * 8, ri1, (int) dr);
            }
        } else if (ftype == 3) {
            Composite ac = g2.getComposite();
            g2.setComposite(CMisc.getFillComposite());
            g2.setColor(drawData.getColor(value1));
            g2.fillArc(x, y, w, w, ri1, (int) dr);
            g2.setComposite(ac);
            g2.setColor(super.getColor());
        }
        g2.drawArc(x, y, w, w, ri1, (int) dr);


        g2.setColor(Color.black);
        g2.setFont(CMisc.font);
        if (false && CMisc.show_angle_text) {
            k = dr1;
            if (k > 0) {
                k = k * 100 + 50;
            } else {
                k = k * 100 - 50;
            }
            g2.drawString(new Float((int) ((k) / 100)).toString(), (int) cx, (int) cy);
        }

    }

    public boolean drawAllLine(Graphics2D g2) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            this.stop();
            return false;
        }
        if (inter) {
            return true;
        }

        super.setDrawDash(g2);
        double[] r = interect_LL(p1.getx(), p1.gety(), p2.getx(),
                p2.gety(),
                p3.getx(), p3.gety(), p4.getx(),
                p4.gety());
        if (r == null) {
            if (dl1) {
                g2.drawLine((int) p1.getx(), (int) p1.gety(),
                        (int) p2.getx(),
                        (int) p2.gety());
            }
            if (dl2) {
                g2.drawLine((int) p3.getx(), (int) p3.gety(),
                        (int) p4.getx(),
                        (int) p4.gety());
            }
        } else {
            if (dl1) {
                drawALine3(p1, p2, r[0], r[1], dl1, g2);
            } else {
                drawALine3Short(p1, p2, r[0], r[1], g2);
            }

            if (dl2) {
                drawALine3(p3, p4, r[0], r[1], dl2, g2);
            } else {
                drawALine3Short(p3, p4, r[0], r[1], g2);
            }
        }

        return true;
    }
}
