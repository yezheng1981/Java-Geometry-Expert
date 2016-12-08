package wprover;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-4-5
 * Time: 13:33:53
 * To change this template use File | Settings | File Templates.
 */

public abstract class JFlash {

    protected Timer timer;
    protected JPanel panel;
    protected int n = 8;
    protected static double ZERO = 1.0;
    final static float[] DF = {8.0f};
    final static float[] DF1 = {2.0f};
    final static BasicStroke BStroke = new BasicStroke(1.0f);
    final static BasicStroke BStroke2 = new BasicStroke(3.0f);
    final public static BasicStroke Dash = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, DF, 0.0f);

    final public static BasicStroke DASH1 = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, DF, 0.0f);
    final static int RAD = 40;
    final static int MAX = 100;
    final static int MIN_STEP = 20;

    final static double ASTEP = 0.120; // angle step


    //protected static Vector linelist = new Vector();
    protected Color color = Color.red;
    protected boolean finished = false;
    protected boolean vType = false;
    protected boolean fb = false;
    protected int TIME_INTERVAL = CMisc.getFlashInterval();


    public void updateTimer() {
        if (timer != null)
            timer.setDelay(CMisc.getFlashInterval());
    }

    public void setFlashFb(boolean f) {
        fb = f;
    }

    public void setDealy(int n) {
        if (timer != null)
            timer.setDelay(n);
    }

    public boolean getFlashFb() {
        return fb;
    }

    public JFlash(JPanel p) {
        panel = p;
    }

    public void setvisibleType(boolean t) {
        vType = t;
    }

    public boolean getvisibleType() {
        return vType;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean draw(Graphics2D g2);

    public void recalculate() {
    }

    public void createTimer(int n) {
    }

    public void start() {
        if (timer != null) {
            finished = false;
            timer.start();
            n = 8;
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
            finished = true;
        }
    }

    public boolean isfinished() {
        return this.finished;
    }

    public boolean isrRunning() {
        return timer.isRunning();
    }

    final public void setDrawDash(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.setStroke(DASH1);
    }

    final public void setDrawDashFb(Graphics2D g2) {
        if (fb) {
            g2.setStroke(BStroke);
        } else {
            g2.setStroke(Dash);
        }
        g2.setColor(Color.red);
    }

    final public void setDrawDashFb2(Graphics2D g2) {
        if (fb) {
            g2.setStroke(BStroke2);
        } else {
            g2.setStroke(Dash);
        }
        g2.setColor(Color.red);
    }

    final public boolean isZero(double d) {
        return Math.abs(d) < ZERO;
    }

    final static double[] interect_LL(double x1, double y1, double x2,
                                      double y2, double x3, double y3,
                                      double x4, double y4) {

        double result[] = new double[2];
        if (Math.abs(x1 - x2) < ZERO) {
            if (Math.abs(x3 - x4) < ZERO) {
                return null;
            }
            double k = (y3 - y4) / (x3 - x4);
            result[0] = x1;
            result[1] = k * (x1 - x3) + y3;
            return result;
        }
        if (Math.abs(x3 - x4) < ZERO) {
            double k0 = (y1 - y2) / (x1 - x2);
            result[0] = x3;
            result[1] = k0 * (x3 - x1) + y1;
            return result;
        }
        double k0 = (y1 - y2) / (x1 - x2);
        double k1 = (y3 - y4) / (x3 - x4);

        double x = (y3 - y1 + k0 * x1 - k1 * x3) / (k0 - k1);
        double y = k0 * (x - x1) + y1;

        result[0] = x;
        result[1] = y;
        return result;
    }

    final public static void drawAngle(double x1, double y1, double x2, double y2,
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

        if (CMisc.ANGLE_TYPE == 1) {

            double lr = 12.0;

            double alpha = -Math.PI / 3;
            if (dr > 0) {
                alpha = -alpha;
                dr = (dr - 180 * Math.cos(Math.PI / 2.0 - alpha) * lr * 1 /
                        (3 * Math.PI * radius));
                if (dr < 0) {
                    dr = 0;
                }
            } else {
                dr = (dr + 180 * Math.cos(Math.PI / 2.0 + alpha) * lr * 1 /
                        (3 * Math.PI * radius));
                if (dr > 0) {
                    dr = 0;
                }
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

            if (dr >= 0) {
                dr += 1.5;
            } else {
                dr -= 1.5;
            }

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

        g2.drawArc((int) r[0] - radius, (int) r[1] - radius, radius * 2, radius * 2, ri1, (int) dr);
        g2.setColor(Color.black);
        g2.setFont(CMisc.font);
        if (CMisc.show_angle_text) {
            if (k > 0) {
                k = k * 100 + 50;
            } else {
                k = k * 100 - 50;
            }
            g2.drawString(new Float((int) ((k) / 100)).toString(), (int) cx, (int) cy);
        }

    }

    public static void drawAngle(CPoint p1, CPoint p2, CPoint p3, CPoint p4,
                                 int rad, Graphics2D g2) {
        if (p1 != null && p2 != null && p3 != null && p4 != null) {
            drawAngle(p1.getx(), p1.gety(), p2.getx(), p2.gety(), p3.getx(),
                    p3.gety(), p4.getx(), p4.gety(), rad, g2);
        }
    }

    static boolean spt(double x0, double y0, double x, double y) {
        return Math.pow(x0 - x, 2) + Math.pow(y0 - y, 2) <
                CMisc.ZERO * CMisc.ZERO;
    }

    static public void drawALine3Short(CPoint p1, CPoint p2, double x, double y,
                                       Graphics2D g2) {

        if ((x - p1.getx()) * (x - p2.getx()) < 0 ||
                (y - p1.gety()) * (y - p2.gety()) < 0) {
            return;
        }

        if (Math.pow(p1.getx() - x, 2) + Math.pow(p1.gety() - y, 2) <
                Math.pow(p2.getx() - x, 2) + Math.pow(p2.gety() - y, 2)) {
            g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) x, (int) y);
        } else {
            g2.drawLine((int) p2.getx(), (int) p2.gety(), (int) x, (int) y);
        }

    }

    static public void drawALine3(CPoint p1, CPoint p2, double x, double y,
                                  boolean ln, Graphics2D g2) {
        if (Math.abs(x) > 10000 || Math.abs(y) > 10000) {
            return;
        }

        if (p1.getx() > p2.getx()) {
            CPoint p = p1;
            p1 = p2;
            p2 = p;
        }
        double x1, y1, x2, y2;
        x1 = p1.getx();
        y1 = p1.gety();
        x2 = p2.getx();
        y2 = p2.gety();
        if (x < x1) {
            g2.drawLine((int) x, (int) y, (int) x2, (int) y2);
        } else if (x > x1 && x < x2) {
            if (ln) {
                g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            }
        } else if (x > x2) {
            g2.drawLine((int) x, (int) y, (int) x1, (int) y1);
        } else { // ==
            if (p1.gety() > p2.gety()) {
                CPoint p = p1;
                p1 = p2;
                p2 = p;
            }
            x1 = p1.getx();
            y1 = p1.gety();
            x2 = p2.getx();
            y2 = p2.gety();
            if (y < y1) {
                g2.drawLine((int) x, (int) y, (int) x2, (int) y2);
            } else if (y > y1 && y < y2) {
                g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            } else if (y > y2) {
                g2.drawLine((int) x, (int) y, (int) x1, (int) y1);
            } else {
                if (ln) {
                    g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                }
            }
        }
    }

    static double getcircleDr(double x1, double y1) {
        double k = y1 / x1;
        double r = Math.atan(k);
        if (x1 < 0) {
            r += Math.PI;
        }
        return r;
    }

    protected static Color getRatioColor(int c1, int c2, double ra) {
        Color o1 = drawData.getColor(c1);
        Color o2 = drawData.getColor(c2);
        double r1 = ra;
        double r2 = 1 - ra;

        int r = (int) (o1.getRed() * r2 + o2.getRed() * r1);
        int g = (int) (o1.getGreen() * r2 + o2.getGreen() * r1);
        int b = (int) (o1.getBlue() * r2 + o2.getBlue() * r1);
        Color c = new Color(r, g, b);
        return c;
    }

}
