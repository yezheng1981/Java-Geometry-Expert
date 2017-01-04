package wprover;

import java.util.Vector;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ${Yezheng}
 * Date: 2004-12-9
 * Time: 12:25:19
 * To change this template use File | Settings | File Templates.
 */
public class CLine extends CClass {
    final public static int LLine = 0;
    final public static int PLine = 1;
    final public static int TLine = 2;
    final public static int BLine = 3;
    final public static int CCLine = 4;
    final public static int NTALine = 5;
    final public static int ALine = 6;
    final public static int SALine = 7;
    final public static int ABLine = 8;
    final public static int TCLine = 9;


    final public static int ET_NORMAL = 0;
    final public static int ET_EXTENSION = 1;
    final public static int ET_ENDLESS = 2;

    int type = 0;                              // Type: 1)LLine 2)PLine 3)TLine 4)BLine
    int ext_type = 0; //0: normal, 1: extension ; 2: endless.
    int extent = CMisc.LINDE_DRAW_EXT;


    Vector points = new Vector();
    Vector cons = new Vector();


    final static int Width = 3000;
    final static int Height = 2000; // should be modified here.


    public void draw(Graphics2D g2, boolean selected) {
        if (!isdraw()) return;

        if (selected) {
            setDrawSelect(g2);
        } else
            setDraw(g2);

        switch (type) {
            case CLine.LLine:
                drawLLine(this, g2);
                break;
            case CLine.PLine:
                drawPLine(this, g2);
                break;
            case CLine.TLine:
                drawTLine(this, g2);
                break;
            case CLine.BLine:
                drawBLine(this, g2);
                break;
            case CLine.CCLine:
                drawCCLine(this, g2);
                break;
            case CLine.ALine:
                drawALine(this, g2);
                break;
            case CLine.SALine:
                drawSLine(this, g2);
                break;
            case CLine.ABLine:
                drawABLine(this, g2);
                break;
            case CLine.TCLine:
                drawTCLine(this, g2);
                break;
        }
    }

    public void draw(Graphics2D g2) {
        draw(g2, false);
    }

    public String TypeString() {
        String st = Language.getLs(40, "line");
        if (m_name == null) return st;
        return st + " " + m_name;
    }

    public String getSimpleName() {
        CPoint pl[] = this.getTowSideOfLine();

        String s = new String();
        if (pl == null) {
            for (int i = 0; i < points.size(); i++) {
                s += points.get(i);
            }
        } else {
            s += pl[0];
            if (s.length() > 1)
                s += " ";
            s += pl[1];
        }
        return s;
    }

    public String getDescription() {
        String s = this.getSimpleName();
        String st = Language.getLs(40, "line ");
        return st + s;
    }

    public void setExtent(int n) {
        extent = n;
    }

    public int getExtent() {
        return extent;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public static void drawALine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        double k = line.getK();
        CPoint pt = line.getfirstPoint();
        drawXLine(pt.getx(), pt.gety(), k, g2);
    }

    public static void drawABLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        double k = line.getK();
        CPoint pt = line.getfirstPoint();
        drawXLine(pt.getx(), pt.gety(), k, g2);
    }

    public static void drawTCLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        double k = line.getK();
        CPoint pt = line.getfirstPoint();
        drawXLine(pt.getx(), pt.gety(), k, g2);
    }

    public static void drawBLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        constraint cs = line.getconsByType(constraint.BLINE);
        CPoint p1 = (CPoint) cs.getelement(1);
        CPoint p2 = (CPoint) cs.getelement(2);

        double k = line.getK();
        double x = (p1.getx() + p2.getx()) / 2.0;
        double y = (p1.gety() + p2.gety()) / 2.0;
        drawXLine(x, y, k, g2);
    }

    public static void drawCCLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        constraint cs = null;
        for (int i = 0; i < line.cons.size(); i++) {
            cs = (constraint) line.getcons(i);
            if (cs.GetConstraintType() == constraint.CCLine)
                break;
        }
        if (cs == null) return;
        Circle c1 = (Circle) cs.getelement(1);
        Circle c2 = (Circle) cs.getelement(2);
        CPoint p1 = c1.o;
        CPoint p2 = c2.o;

        double xa = 0;
        double xb = Width;


        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();

        double r1 = c1.getRadius();
        double r2 = c2.getRadius();

        double a = (x1 * x1 - x2 * x2 + y1 * y1 - y2 * y2 - r1 * r1 + r2 * r2);

        double ya, yb;
        if (Math.abs(y1 - y2) < CMisc.ZERO) {
            xa = xb = -(a) / (2 * (x2 - x1));
            ya = 0;
            yb = Height;
        } else {
            ya = (a - 2 * xa * (x1 - x2)) / (2 * (y1 - y2));
            yb = (a - 2 * xb * (x1 - x2)) / (2 * (y1 - y2));
        }

        g2.drawLine((int) xa, (int) ya, (int) xb, (int) yb);
    }

    public static void drawSLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }
        constraint cs = (constraint) line.getcons(0);
        CLine l = (CLine) cs.getelement(0);
        CLine l1 = (CLine) cs.getelement(1);
        CPoint p = l1.getfirstPoint();

        double k = line.getK();
        drawXLine(p.getx(), p.gety(), k, g2);


    }

    public static void drawXLine(double x0, double y0, double k, Graphics2D g2) {
        if (Math.abs(1 / k) < CMisc.ZERO) {
            double x = x0;
            double y1 = 0;
            double y2 = Height;
            g2.drawLine((int) x, (int) y1, (int) x, (int) y2);
        } else if (Math.abs(k) < CMisc.ZERO) {
            g2.drawLine(0, (int) y0, (int) Width, (int) y0);
        } else {
            double y1 = 0;
            double y2 = Height;
            double x1 = (y1 - y0 + k * x0) / k;
            double x2 = (y2 - y0 + k * x0) / k;
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    public static void drawTLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }

        constraint cs = (constraint) line.getcons(0);
        CLine l = (CLine) cs.getelement(1);
        CPoint p = (CPoint) line.points.get(0);
        double k = l.getK();
        drawXLine(p.getx(), p.gety(), -1 / k, g2);
    }

    public static void drawPLine(CLine line, Graphics2D g2) {
        if (line.points.size() >= 2) {
            drawLLine(line, g2);
            return;
        }

        constraint cs = (constraint) line.getcons(0);
        CLine l = (CLine) cs.getelement(1);
        if (line.points.size() == 0)
            return;

        CPoint p = (CPoint) line.points.get(0);
        drawXLine(p.getx(), p.gety(), l.getK(), g2);
    }

    public static void drawLLine(CLine line, Graphics2D g2) {

        CPoint[] pl = line.getMaxMinPoint();
        if (pl == null) return;

        if (line.ext_type == 0)
            g2.draw(new Line2D.Double(pl[0].getx(), pl[0].gety(), pl[1].getx(), pl[1].gety()));


        double dx = pl[1].getx() - pl[0].getx();
        double dy = pl[1].gety() - pl[0].gety();

        double dlt = Math.sqrt(dx * dx + dy * dy);
        if (line.ext_type == 1) {
            dx = dx * line.getExtent() / dlt;
            dy = dy * line.getExtent() / dlt;

            g2.draw(new Line2D.Double(pl[0].getx() - dx, pl[0].gety() - dy, pl[1].getx() + dx, pl[1].gety() + dy));
        } else if (line.ext_type == 2) {
            int len = Width > Height ? Width : Height;
            dx = len * dx / dlt;
            dy = len * dy / dlt;

            g2.draw(new Line2D.Double(pl[0].getx() - dx, pl[0].gety() - dy, pl[1].getx() + dx, pl[1].gety() + dy));
        }
    }

    ///   /////////////////////////////////////

    public static void drawPParaLine(CLine line, CPoint pt, Graphics2D g2) {
        if (line.isVertical()) {
            double x = pt.getx();
            double y1 = 0;
            double y2 = Height;
            g2.drawLine((int) x, (int) y1, (int) x, (int) y2);

        } else {
            double k = line.getK();
            double x1 = 0;
            double x2 = Width;
            double y1 = k * (0 - pt.getx()) + pt.gety();
            double y2 = k * (x2 - pt.getx()) + pt.gety();
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    public static void drawTPerpLine(CLine line, CPoint pt, Graphics2D g2) {
        if (line.isHorizonal()) {
            double x = pt.getx();
            double y1 = 0;
            double y2 = Height;
            g2.drawLine((int) x, (int) y1, (int) x, (int) y2);
        } else if (line.isVertical()) {
            g2.drawLine(0, (int) pt.gety(), (int) Width, (int) pt.gety());
        } else {
            double k = line.getK();
            k = -1.0 / k;
            double y1 = 0;
            double y2 = Height;
            double x1 = (y1 - pt.gety() + k * pt.getx()) / k;
            double x2 = (y2 - pt.gety() + k * pt.getx()) / k;
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }
    /////////////////////////////////////////////

    public String getAllPointName() {
        String s = new String();
        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (i == 0)
                s += p.m_name;
            else
                s += ", " + p.m_name;
        }
        return s;
    }

    public CPoint getSecondPoint(CPoint t) {
        CPoint p = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint pt = (CPoint) points.get(i);
            if (p == null && pt != t)
                p = pt;
            else if (p != null && p.x1.xindex > pt.x1.xindex && pt != t)
                p = pt;
        }
        return p;
    }

    public CPoint getfirstPoint() {
        CPoint p = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint pt = (CPoint) points.get(i);
            if (p == null)
                p = pt;
            else if (p.x1.xindex > pt.x1.xindex)
                p = pt;
        }
        return p;

    }

    public CPoint getAPointBut(CPoint t) {
        for (int i = 0; i < points.size(); i++) {
            CPoint pt = (CPoint) points.get(i);
            if (pt != t) return pt;
        }
        return null;
    }

    public CPoint getMaxXPoint() {
        CPoint p = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint pt = (CPoint) points.get(i);
            if (p == null)
                p = pt;
            else if (p.getx() < pt.getx())
                p = pt;
        }
        return p;
    }

    public boolean isTwoEndFreePoints() {
        CPoint p1, p2;
        p1 = p2 = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (p1 == null)
                p1 = p;
            else if (p.x1.xindex < p1.x1.xindex) {
                p2 = p1;
                p1 = p;
            } else if (p2 == null || p.x1.xindex < p1.x1.xindex)
                p2 = p;
        }
        if (p1 == null || p2 == null)
            return false;
        return p1.isAFreePoint() && p2.isAFreePoint();
    }

    public boolean isParallel(CLine line) {
        if (type != PLine)
            return false;
        for (int i = 0; i < cons.size(); i++) {
            constraint cs = (constraint) cons.get(i);
            if (cs.GetConstraintType() != constraint.PARALLEL)
                continue;
            CLine line1 = (CLine) cs.getelement(0);
            CLine line2 = (CLine) cs.getelement(1);
            if (line2 == this) {
                CLine l = line1;
                line1 = line2;
                line2 = l;
            }
            if (line2 == line)
                return true;
            if (line2.type == CLine.PLine)
                return line2.isParallel(line);
        }
        return false;
    }

    public boolean isVertical(CLine line) {
        for (int i = 0; i < cons.size(); i++) {
            constraint cs = (constraint) cons.get(i);
            if (cs.GetConstraintType() != constraint.PERPENDICULAR)
                continue;
            CLine line1 = (CLine) cs.getelement(0);
            CLine line2 = (CLine) cs.getelement(1);
            if (line2 == this) {
                CLine l = line1;
                line1 = line2;
                line2 = l;
            }
            if (line2 == line && line1 == this)
                return true;
            return false;
        }

        return false;
    }

    public CPoint get_Lpt1(CPoint px) {
        if (px == null) return null;
        CPoint p1 = null;
        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (p1 == null && p != px)
                p1 = p;
            else if (p != px && p.x1.xindex < p1.x1.xindex)
                p1 = p;
        }
        return p1;
    }

    public CPoint get_Lptv(CPoint px, double x, double y) {      // Vector (x,y),px == (x,y),p
        if (px == null) return null;
        CPoint p1 = null;
        double x0 = px.getx();
        double y0 = px.gety();
        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (px != p && ((x0 - x) * (x0 - p.getx()) < 0 || (y0 - y) * (y0 - p.gety()) < 0)) {
            } else if (p1 == null && p != px)
                p1 = p;
            else if (p != px && p.x1.xindex < p1.x1.xindex)
                p1 = p;
        }
        return p1;
    }

    public CPoint[] getTowSideOfLine() {
        CPoint p1, p2;
        p1 = p2 = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (p1 == null)
                p1 = p;
            else if (p.x1.xindex < p1.x1.xindex) {
                p2 = p1;
                p1 = p;
            } else if (p2 == null || p.x1.xindex < p1.x1.xindex)
                p2 = p;
        }
        if (p1 == null || p2 == null)
            return null;
        CPoint[] pl = new CPoint[2];
        pl[0] = p1;
        pl[1] = p2;
        return pl;
    }

    public String getDiscription() {
        CPoint[] s = this.getTowSideOfLine();
        if (s == null) return m_name;
        return s[0].m_name + s[1].m_name;
    }


    public CPoint[] getMaxMinPoint() {
        return getMaxMinPoint(true);
    }

    public CPoint[] getMaxMinPoint(boolean ckv) {
        if (points.size() < 2) return null;

        CPoint p1, p2;
        p1 = (CPoint) points.get(0);
        if (p1 == null) return null;

        p2 = null;
        for (int i = 1; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (p == null) continue;
            if (ckv && p.visible == false) continue;

            if (p.x1.value < p1.x1.value) {
                if (p2 == null) {
                    p2 = p1;
                    p1 = p;
                } else
                    p1 = p;
            } else if (p2 == null || p.x1.value > p2.x1.value)
                p2 = p;
        }

        if (p1 == null || p2 == null) return null;

        if (Math.abs(p1.x1.value - p2.x1.value) < CMisc.ZERO) {
            p1 = (CPoint) points.get(0);
            p2 = null;
            for (int i = 1; i < points.size(); i++) {
                CPoint p = (CPoint) points.get(i);
                if (p.y1.value < p1.y1.value) {
                    if (p2 == null) {
                        p2 = p1;
                        p1 = p;
                    } else
                        p1 = p;
                } else if (p2 == null || p.y1.value > p2.y1.value)
                    p2 = p;
            }

        }


        CPoint[] pl = new CPoint[2];
        pl[0] = p1;
        pl[1] = p2;
        return pl;
    }

    public constraint getcons(int i) {
        if (i >= 0 && i < cons.size())
            return (constraint) cons.get(i);
        return null;
    }

    public constraint getconsByType(int t) {
        for (int i = 0; i < cons.size(); i++) {
            constraint c = (constraint) cons.get(i);
            if (c.GetConstraintType() == t) return c;
        }
        return null;
    }

    public boolean containPTs(CPoint p1, CPoint p2) {
        return points.contains(p1) && points.contains(p2);
    }

    public boolean containPT(CPoint p) {
        return points.contains(p);
    }

    public int getconsSize() {
        return cons.size();
    }

    public int getPtsSize() {
        return points.size();
    }

    public CPoint getPoint(int n) {
        if (n < 0 || n >= points.size()) return null;
        return (CPoint) points.get(n);
    }


    public boolean isVertical() {
        if (points.size() >= 2) {
            CPoint p1 = (CPoint) points.get(0);
            CPoint p2 = (CPoint) points.get(1);
            if (Math.abs(p2.getx() - p1.getx()) < CMisc.HV_ZERO)
                return true;
            return false;
        }

        constraint cs = null;
        for (int i = 0; i < cons.size(); i++) {
            cs = (constraint) cons.get(i);
            switch (cs.GetConstraintType()) {
                case constraint.PARALLEL: {
                    CLine line = (CLine) cs.getelement(1);
                    return line.isVertical();
                }
                case constraint.PERPENDICULAR: {
                    CLine line = (CLine) cs.getelement(1);
                    return line.isHorizonal();
                }
                case constraint.CCLine: {
                    Circle c1 = (Circle) cs.getelement(1);
                    Circle c2 = (Circle) cs.getelement(2);
                    return (Math.abs(c1.o.getx() - c2.o.getx()) < CMisc.HV_ZERO);
                }
                case constraint.ALINE: {
                    CLine ln0 = (CLine) cs.getelement(0);
                    CLine ln1 = (CLine) cs.getelement(1);
                    CLine ln2 = (CLine) cs.getelement(2);
                    double k = CLine.getALineK(ln0, ln1, ln2);
                    return (Math.abs(k) > CMisc.MAX_K);
                }
            }
        }
        return false;

    }

    public boolean isHorizonal() {
        if (this.type == CLine.LLine) {
            CPoint p1 = (CPoint) points.get(0);
            CPoint p2 = (CPoint) points.get(1);
            if (Math.abs(p2.gety() - p1.gety()) < CMisc.HV_ZERO)
                return true;
            return false;
        }

        constraint cs = null;
        for (int i = 0; i < cons.size(); i++) {
            cs = (constraint) cons.get(i);
            switch (cs.GetConstraintType()) {
                case constraint.PARALLEL: {
                    CLine line = (CLine) cs.getelement(1);
                    return line.isHorizonal();
                }
                case constraint.PERPENDICULAR: {
                    CLine line = (CLine) cs.getelement(1);
                    return line.isVertical();
                }
                case constraint.CCLine: {
                    Circle c1 = (Circle) cs.getelement(1);
                    Circle c2 = (Circle) cs.getelement(2);
                    return (Math.abs(c1.o.gety() - c2.o.gety()) < CMisc.HV_ZERO);
                }
            }
        }
        return false;
    }

    public double getK() {
        if (points.size() >= 2) {
            CPoint p1 = (CPoint) points.get(0);
            CPoint p2 = (CPoint) points.get(1);
            return (p2.gety() - p1.gety()) / (p2.getx() - p1.getx());
        }

        constraint cs = null;
        for (int i = 0; i < cons.size(); i++) {
            cs = (constraint) cons.get(i);
            switch (cs.GetConstraintType()) {
                case constraint.PARALLEL: {
                    CLine line = (CLine) cs.getelement(1);
                    return line.getK();
                }
                case constraint.PERPENDICULAR: {
                    CLine line = (CLine) cs.getelement(1);
                    return -1.0 / line.getK();
                }
                case constraint.CCLine: {
                    Circle c1 = (Circle) cs.getelement(1);
                    Circle c2 = (Circle) cs.getelement(2);
                    return ((c1.o.getx() - c2.o.getx()) / (c1.o.gety() - c2.o.gety()));
                }
                case constraint.ALINE: {
                    CLine ln0 = (CLine) cs.getelement(0);
                    CLine ln1 = (CLine) cs.getelement(1);
                    CLine ln2 = (CLine) cs.getelement(2);
                    double k = CLine.getALineK(ln0, ln1, ln2);
                    return k;
                }
                case constraint.NTANGLE: {
                    CLine ln = (CLine) cs.getelement(0);
                    CLine ln1 = (CLine) cs.getelement(1);
                    CLine ln2 = (CLine) cs.getelement(2);
                    CLine ln3 = (CLine) cs.getelement(3);
                    CPoint pt = (CPoint) cs.getelement(4);
                    CPoint[] l1 = ln.getTowSideOfLine();
                    CPoint[] l2 = ln1.getTowSideOfLine();
                    CPoint[] l3 = ln2.getTowSideOfLine();
                    if (l1 == null || l2 == null || l3 == null) break;
                    CPoint c = ln3.getfirstPoint();
                    if (c == pt) break;
                    double k1 = ln.getK();
                    double k2 = ln1.getK();
                    double k3 = ln2.getK();
                    double k = (k3 * k2 * k1 + k3 + k2 - k1) / (1 + k3 * k1 + k2 * k1 - k3 * k2);
                    return k;
                }
                case constraint.SANGLE: {
                    CLine ln = (CLine) cs.getelement(0);
                    Integer I = (Integer) cs.getelement(2);
                    double k = ln.getK();
                    int v = I.intValue();
                    double k1 = -constraint.get_sp_ag_value(v);
                    if (ln.isVertical()) {
                        return -1 / k1;
                    } else
                        return (k1 + k) / (1 - k1 * k);
                }
                case constraint.BLINE: {
                    CPoint p1 = (CPoint) cs.getelement(1);
                    CPoint p2 = (CPoint) cs.getelement(2);
                    return -(p1.getx() - p2.getx()) / (p1.gety() - p2.gety());
                }
                case constraint.TCLINE: {
                    CLine ln = (CLine) cs.getelement(1);
                    Circle c = (Circle) cs.getelement(0);
                    CPoint p2 = (CPoint) cs.getelement(2);
                    CPoint p1 = c.o;
                    return -(p1.getx() - p2.getx()) / (p1.gety() - p2.gety());
                }
                case constraint.ANGLE_BISECTOR:
                    CPoint p1 = (CPoint) cs.getelement(0);
                    CPoint p2 = (CPoint) cs.getelement(1);
                    CPoint p3 = (CPoint) cs.getelement(2);

                    double k1 = (p2.gety() - p1.gety()) / (p2.getx() - p1.getx());
                    double k2 = (p2.gety() - p3.gety()) / (p2.getx() - p3.getx());
                    if (k1 > CMisc.MAX_SLOPE)
                        k1 = CMisc.MAX_SLOPE;
                    else if (k1 < -CMisc.MAX_SLOPE)
                        k1 = -CMisc.MAX_SLOPE;

                    if (k2 > CMisc.MAX_SLOPE)
                        k2 = CMisc.MAX_SLOPE;
                    else if (k2 < -CMisc.MAX_SLOPE)
                        k2 = -CMisc.MAX_SLOPE;
                    double a = k1 + k2;
                    if (a == 0) {
                        a = 10E-6;
                    }

                    double b = -2 * (k1 * k2 - 1) / a;
                    double c = -1;
                    a = 1;

                    double d = Math.sqrt(b * b - 4 * c);

                    k1 = (-b + d) / 2;
                    k2 = (-b - d) / 2;

                    double x0 = p2.getx();
                    double y0 = p2.gety();

                    double x1 = -0.4455;
                    double y1 = y0 + k1 * (x1 - x0);
                    if (cs.check_constraint(x1, y1)) return k1;

                    y1 = y0 + k2 * (x1 - x0);
                    if (cs.check_constraint(x1, y1))
                        return k2;
                    return 0.0;
            }
        }
        return 0.0;
    }

    public static double getALineK(CLine ln1, CLine ln2, CLine ln3) {
        CPoint lp1[] = ln1.getTowSideOfLine();
        CPoint lp2[] = ln2.getTowSideOfLine();
        CPoint lp3[] = ln3.getTowSideOfLine();
        return getALineK(lp1[0], lp1[1], lp2[0], lp2[1], lp3[0], lp3[1]);
    }

    public static double getALineK(CPoint p1, CPoint p2, CPoint p3, CPoint p4, CPoint p5, CPoint p6) {
        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();
        double x3 = p3.getx();
        double y3 = p3.gety();
        double x4 = p4.getx();
        double y4 = p4.gety();
        double x5 = p5.getx();
        double y5 = p5.gety();
        double x6 = p6.getx();
        double y6 = p6.gety();
        double t1 = (y6 - y5) * ((y2 - y1) * (y4 - y3) + (x2 - x1) * (x4 - x3)) + (y4 - y3) * (x6 - x5) * (x2 - x1) - (y2 - y1) * (x6 - x5) * (x4 - x3);
        double t2 = (x6 - x5) * (x4 - x3) * (x2 - x1) + (y2 - y1) * (y4 - y3) * (x6 - x5) - (y6 - y5) * (y4 - y3) * (x2 - x1) + (y6 - y5) * (y2 - y1) * (x4 - x3);
        return t1 / t2;
    }

    public void addApoint(CPoint a) {
        if (a == null) return;
        for (int i = 0; i < points.size(); i++)
            if (a == points.get(i))
                return;
        points.add(a);
    }

    public void addconstraint(constraint cs) {
        if (cs == null) return;
        if (!cons.contains(cs))
            cons.add(cs);
    }

    public void clearpoints() {
        points.clear();
    }

    public void setColorType(String c) {
        String str = new String(c);
    }

    public CLine(int type) {
        super(CClass.LINE);
        this.type = type;
    }

    public CLine(CPoint A, int type) {
        super(CClass.LINE);

        this.addApoint(A);
        this.type = type;
    }

    public CLine(CPoint A, CPoint B, int Type) {
        super(CClass.LINE);
        this.addApoint(A);
        this.addApoint(B);
        type = Type;
    }

    public CLine(CPoint A, CPoint B) {
        super(CClass.LINE);
        this.addApoint(A);
        this.addApoint(B);
    }

    public CLine(CPoint A, CPoint B, String color) {
        super(CClass.LINE);
        //  this.color = color;
        this.addApoint(A);
        this.addApoint(B);
    }


    public boolean sameLine(CPoint A, CPoint B) {
        CPoint p = new CPoint();
        int counter = 0;
        for (int i = 0; i < points.size(); i++) {
            p = (CPoint) points.get(i);
            if (isEqual(A, p) || isEqual(B, p)) {
                counter++;
            }
        }
        if (counter == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean pointOnLine(CPoint p) {
        return points.contains(p);
    }

    public boolean pointOnLineN(CPoint p) {
        return this.distance(p.getx(), p.gety()) < CMisc.ZERO;
    }

    public boolean pointOnLine(double x, double y) {
        return this.distance(x, y) < CMisc.ZERO;
    }

    public boolean isEqual(CPoint A, CPoint B) {
        if (A.x1.xindex == B.x1.xindex && A.y1.xindex == B.y1.xindex)
            return true;

        return false;
    }

    public CPoint getSideMostPoint(CPoint p, int x, int y) {
        CPoint pp = null;
        for (int i = 0; i < points.size(); i++) {
            CPoint tp = (CPoint) points.get(i);
            if ((x - tp.getx()) * (p.getx() - tp.getx()) > 0 || (y - tp.gety()) * (p.gety() - tp.gety()) > 0
                    && (pp == null || pp.x1.xindex > tp.x1.xindex))
                pp = tp;
        }
        return pp;
    }

    public static boolean mouse_on_line(double x, double y, double x1, double y1, double x2, double y2) {
        double k = -(y2 - y1) / (x2 - x1);

        if (Math.abs(k) > CMisc.ZERO && Math.abs(1 / k) < CMisc.ZERO) {
            return Math.abs(x - x1) < CMisc.PIXEPS;
        }
        double len = Math.abs(y + k * x - y1 - k * x1) / Math.sqrt(1 + k * k);
        return len < CMisc.PIXEPS;
    }

    public static double distanceToPoint(CLine ln, double x, double y) {

        int n = ln.getPtsSize();
        if (n < 2) {
            if (ln.type == CCLine) {
                constraint cs = null;
                for (int i = 0; i < ln.cons.size(); i++) {
                    cs = (constraint) ln.getcons(i);
                    if (cs.GetConstraintType() == constraint.CCLine)
                        break;
                }
                if (cs == null) return -1.0;
                Circle c1 = (Circle) cs.getelement(1);
                Circle c2 = (Circle) cs.getelement(2);
                CPoint p1 = c1.o;
                CPoint p2 = c2.o;

                double x1 = p1.getx();
                double y1 = p1.gety();
                double x2 = p2.getx();
                double y2 = p2.gety();
                double r1 = c1.getRadius();
                double r2 = c2.getRadius();
                double r = Math.abs(-2 * x * (x1 - x2) + x1 * x1 - x2 * x2 - 2 * y * (y1 - y2) + y1 * y1 - y2 * y2 - r1 * r1 + r2 * r2);
                r = r / (2 * Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                return r;
            } else if (ln.type == BLine) {
                constraint cs = ln.getconsByType(constraint.BLINE);
                CPoint p1 = (CPoint) cs.getelement(1);
                CPoint p2 = (CPoint) cs.getelement(2);
                double x0 = (p1.getx() + p2.getx()) / 2;
                double y0 = (p1.gety() + p2.gety()) / 2;

                double k = -ln.getK();
                if (Math.abs(k) > CMisc.ZERO && Math.abs(1 / k) < CMisc.ZERO) {
                    return Math.abs(x - x0);
                }
                double len = Math.abs(y + k * x - y0 - k * x0) / Math.sqrt(1 + k * k);
                return len;
            }
        }

        CPoint pt = ln.getfirstPoint();
        if (pt == null) {
            return Double.MAX_VALUE;
        }
        double k = -ln.getK();

        if (Math.abs(k) > CMisc.ZERO && Math.abs(1 / k) < CMisc.ZERO) {
            return Math.abs(x - pt.getx());
        }
        double len = Math.abs(y + k * x - pt.gety() - k * pt.getx()) / Math.sqrt(1 + k * k);
        return len;

    }


    public static double distanceToPoint(CPoint p, CPoint p1, CPoint p2) {
        return distanceToPoint(p.getx(), p.gety(), (p1.getx() - p2.getx()) / (p1.gety() - p2.gety()), p1.getx(), p1.gety());

    }

    public static double distanceToPoint(double x1, double y1, double k, double x, double y) {
        k = -k;

        if (Math.abs(k) > CMisc.ZERO && Math.abs(1 / k) < CMisc.ZERO) {
            return Math.abs(x - x1);
        }
        double len = Math.abs(y + k * x - y1 - k * x1) / Math.sqrt(1 + k * k);
        return len;

    }

    public boolean inside(double x, double y) {
        if (this.ext_type == ET_ENDLESS)
            return true;

        CPoint p1, p2;
        p1 = p2 = null;

        CPoint pl[] = this.getMaxMinPoint();
        if (pl == null)
            return true;

        p1 = pl[0];
        p2 = pl[1];

        double x1, y1, x2, y2;
        x1 = p1.getx();
        y1 = p1.gety();

        x2 = p2.getx();
        y2 = p2.gety();

        if (ext_type == ET_EXTENSION) {
            int len = extent;
            double dx = x2 - x1;
            double dy = y2 - y1;
            double l1 = Math.sqrt(dx * dx + dy * dy);
            dx = len * dx / l1;
            dy = len * dy / l1;
            x1 -= dx;
            y1 -= dy;
            x2 += dx;
            y2 += dy;
        }

        double e1 = (x - x1) * (x - x2);
        double e2 = (y - y1) * (y - y2);

        if (Math.abs(e1) < CMisc.ZERO && Math.abs(e2) < CMisc.ZERO)
            return true;
        if (Math.abs(e1) < CMisc.ZERO && e2 < 0 || Math.abs(e2) < CMisc.ZERO && e1 < 0)
            return true;
        return (e1 <= 0 && e2 <= 0);
    }

    public boolean inside(double x, double y, double eps) {
        if (this.ext_type == ET_ENDLESS)
            return true;

        CPoint p1, p2;
        p1 = p2 = null;

        CPoint pl[] = this.getMaxMinPoint();
        if (pl == null)
            return true;

        p1 = pl[0];
        p2 = pl[1];

        double x1, y1, x2, y2;
        x1 = p1.getx();
        y1 = p1.gety();

        x2 = p2.getx();
        y2 = p2.gety();

        if (ext_type == ET_EXTENSION) {
            int len = extent;
            double dx = x2 - x1;
            double dy = y2 - y1;
            double l1 = Math.sqrt(dx * dx + dy * dy);
            dx = len * dx / l1;
            dy = len * dy / l1;
            x1 -= dx;
            y1 -= dy;
            x2 += dx;
            y2 += dy;
        }

        double e1 = (x - x1) * (x - x2);
        double e2 = (y - y1) * (y - y2);

        eps *= eps;
        if (Math.abs(e1) < eps && Math.abs(e2) < eps)
            return true;
        if (Math.abs(e1) < eps && e2 < 0 || Math.abs(e2) < eps && e1 < 0)
            return true;
        return (e1 <= 0 && e2 <= 0);
    }

    public boolean nearline(double x, double y) {     // is the point near the line
        return distanceToPoint(this, x, y) < CMisc.PIXEPS;
    }

    public boolean select(double x, double y) {
        if (!visible) return false;

        if (inside(x, y, CMisc.PIXEPS)) {
            double d = distanceToPoint(this, x, y);
            if (d < CMisc.PIXEPS)
                return true;
        }
        return false;
    }

    public double distance(double x, double y) {
        return distanceToPoint(this, x, y);
    }

    public boolean isOnMiddle(double x, double y) {
        if (points.size() != 2)
            return false;
        CPoint p1 = (CPoint) points.get(0);
        CPoint p2 = (CPoint) points.get(1);

        double dx = (p1.getx() + p2.getx()) / 2;
        double dy = (p1.gety() + p2.gety()) / 2;
        if (Math.abs(p1.getx() - p2.getx()) < CMisc.PIXEPS &&
                Math.abs(p1.gety() - p2.gety()) < CMisc.PIXEPS)
            return false;

        return Math.abs(x - dx) < CMisc.PIXEPS && Math.abs(y - dy) < CMisc.PIXEPS;
    }

    public boolean pointonMiddle(CPoint pt) {
        if (points.size() != 2)
            return false;
        CPoint p1 = (CPoint) points.get(0);
        CPoint p2 = (CPoint) points.get(1);

        double dx = (p1.getx() + p2.getx()) / 2;
        double dy = (p1.gety() + p2.gety()) / 2;
        if (Math.abs(p1.getx() - p2.getx()) < CMisc.PIXEPS &&
                Math.abs(p1.gety() - p2.gety()) < CMisc.PIXEPS)
            return false;

        if (!(Math.abs(pt.getx() - dx) < CMisc.PIXEPS && Math.abs(pt.gety() - dy) < CMisc.PIXEPS))
            return false;
        pt.setXY(dx, dy);
        return true;
    }

    public void pointonline(CPoint pt) {      //set the location of the point to line
        if (this.type == CCLine)
            return;

        double x1, y1, x3, y3, xt, yt;

        CPoint p = this.getfirstPoint();
        x1 = y1 = 0;
        if (p == null) {
            if (type == BLine) {
                constraint cs = getconsByType(constraint.BLINE);
                CPoint p1 = (CPoint) cs.getelement(1);
                CPoint p2 = (CPoint) cs.getelement(2);
                x1 = (p1.getx() + p2.getx()) / 2;
                y1 = (p1.gety() + p2.gety()) / 2;
            }
        } else {
            x1 = p.getx();
            y1 = p.gety();
        }

        x3 = pt.x1.value;
        y3 = pt.y1.value;

        if (this.type == PLine || this.type == TLine || this.type == CCLine || type == ALine) {

            if (this.isVertical()) {
                xt = x1;
                yt = y3;
            } else if (this.isHorizonal()) {
                xt = x3;
                yt = y1;
            } else {
                double k = this.getK();
                xt = ((y3 - y1) * k + x1 * k * k + x3) / (1 + k * k);
                yt = y1 + (xt - x1) * k;
            }
        } else {
            if (this.isVertical()) {
                xt = x1;
                yt = y3;
            } else {
                double k = this.getK();

                double x0 = pt.getx();
                double y0 = pt.gety();

                xt = (k * (y0 - y1) + k * k * x1 + x0) / (k * k + 1);
                yt = y1 + k * (xt - x1);
            }
        }
        pt.setXY(xt, yt);

    }

    public static CPoint[] commonPoint(CLine ln, Circle c) {
        CPoint t1, t2;
        t1 = t2 = null;

        for (int i = 0; i < ln.points.size(); i++) {
            CPoint p1 = (CPoint) ln.points.get(i);
            for (int j = 0; j < c.points.size(); j++) {
                CPoint p2 = (CPoint) c.points.get(j);
                if (p1 == p2) {
                    if (t1 == null)
                        t1 = p1;
                    else
                        t2 = p1;
                }
            }
        }

        if (t1 == null)
            return new CPoint[0];
        else if (t2 == null) {
            CPoint[] l = new CPoint[1];
            l[0] = t1;
            return l;
        } else {
            CPoint[] l = new CPoint[2];
            l[0] = t1;
            l[1] = t2;
            return l;
        }
    }

    public static CPoint commonPoint(CLine line0, CLine line1) {
        for (int i = 0; i < line0.points.size(); i++) {
            CPoint p1 = (CPoint) line0.points.get(i);
            for (int j = 0; j < line1.points.size(); j++) {
                CPoint p2 = (CPoint) line1.points.get(j);
                if (p1 == p2)
                    return p1;

            }
        }
        return null;
    }

    public boolean sameLine(CLine line2) {
        if (line2 == null) return false;
        if (this.points.size() != line2.points.size()) return false;

        return this.points.containsAll(line2.points);
    }

    public static double[] Intersect(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        double result[] = new double[2];
        if (Math.abs(p1.getx() - p2.getx()) < CMisc.ZERO) {
            if (Math.abs(p3.getx() - p4.getx()) < CMisc.ZERO)
                return null;

            double k = (p4.gety() - p3.gety()) / (p4.getx() - p3.getx());
            result[0] = p1.getx();
            result[1] = k * (p1.getx() - p3.getx()) + p3.gety();
            return result;
        }
        if (Math.abs(p3.getx() - p4.getx()) < CMisc.ZERO) {
            double k0 = (p2.gety() - p1.gety()) / (p2.getx() - p1.getx());
            result[0] = p3.getx();
            result[1] = k0 * (p3.getx() - p1.getx()) + p1.gety();
            return result;
        }
        double k0 = (p2.gety() - p1.gety()) / (p2.getx() - p1.getx());
        double k1 = (p4.gety() - p3.gety()) / (p4.getx() - p3.getx());
        double x = (p3.gety() - p3.gety() + k0 * p1.getx() - k1 * p3.getx()) / (k0 - k1);
        double y = k0 * (x - p1.getx()) + p1.gety();
        result[0] = x;
        result[1] = y;
        return result;
    }

    public static boolean isVerticalSlop(double r) {
        return Math.abs(r) > CMisc.MAX_SLOPE;
    }

    public static boolean isPerp(CLine line0, CLine line1) {
        if (line0 == null || line1 == null)
            return false;
        double k0 = line0.getK();
        double k1 = line1.getK();
        if (Math.abs(k0) < CMisc.ZERO) {
            return Math.abs(k1) > 99;
        }
        if (Math.abs(k1) < CMisc.ZERO)
            return Math.abs(k0) > 99;
        return Math.abs(k0 * k1 + 1) < CMisc.ZERO;
    }

    public static double[] Intersect(CLine line0, CLine line1) {
        if (line0 == null || line1 == null)
            return null;

        double result[] = new double[2];
        double k0 = line0.getK();
        double k1 = line1.getK();

        if (line0.isVertical() || isVerticalSlop(k0)) {
            if (line1.isVertical() || isVerticalSlop(k1))
                return null;
            double k = line1.getK();
            CPoint p0 = (CPoint) line0.points.get(0);
            CPoint p1 = (CPoint) line1.points.get(0);
            result[0] = p0.getx();
            result[1] = k * (p0.getx() - p1.getx()) + p1.gety();
            return result;
        }


        if (line1.isVertical() || isVerticalSlop(k1)) {
            CPoint p1 = line0.getfirstPoint();
            CPoint p = line1.getfirstPoint();
            result[0] = p.getx();
            result[1] = k0 * (p.getx() - p1.getx()) + p1.gety();
            return result;
        }

        CPoint p0 = line0.getfirstPoint();
        CPoint p1 = line1.getfirstPoint();
        if (Math.abs(k0 - k1) > CMisc.ZERO) {
            double x = (p1.gety() - p0.gety() + k0 * p0.getx() - k1 * p1.getx()) / (k0 - k1);
            double y = k0 * (x - p0.getx()) + p0.gety();
            result[0] = x;
            result[1] = y;
        } else {
            double x = (p0.getx() + p1.getx()) / 2;
            double y = (p0.gety() + p1.gety()) / 2;
            result[0] = 999999;
            result[1] = (result[0] - x) * k0 + y;
        }
        return result;
    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (!visible) return;

        CPoint pl[] = this.getMaxMinPoint();
        if (pl != null) {
            String s = pl[0].m_name + " moveto " + pl[1].m_name + " lineto ";
            fp.write(s.getBytes());
            super.saveSuper(fp);
        }

    }


    public void Save(DataOutputStream out) throws IOException {
        super.Save(out);

        out.writeInt(type);
        out.writeInt(ext_type);
        out.writeInt(points.size());
        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            out.writeInt(p.m_id);
        }
        out.writeInt(cons.size());
        for (int i = 0; i < cons.size(); i++) {
            constraint cs = (constraint) cons.get(i);
            if (cs != null)
                out.writeInt(cs.id);
            else
                out.writeInt(-1);
        }
        out.writeInt(extent);
    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        if (CMisc.version_load_now < 0.01) {
            m_id = in.readInt();
            drawType drawt = new drawType();
            drawt.Load(in);
            m_color = drawt.color_index;
            {
                if (m_color == 1)
                    m_color = 3;
                else if (m_color == 2)
                    m_color = 5;
                else if (m_color == 3)
                    m_color = 11;
                else if (m_color == 7)
                    m_color = 8;
            }
            m_dash = drawt.dash;
            m_width = drawt.width;

            m_name = new String();
            int size = in.readInt();
            for (int i = 0; i < size; i++)
                m_name += in.readChar();
            type = in.readInt();

            size = in.readInt();
            for (int i = 0; i < size; i++) {
                int d = in.readInt();
                this.addApoint(dp.getPointById(d));
            }
            size = in.readInt();

            int nc = 0;
            for (int i = 0; i < size; i++) {
                int d = in.readInt();
                constraint c = dp.getConstraintByid(d);
                if (c == null)
                    nc++;
                else
                    cons.add(c);
            }
            size -= nc;
        } else {
            super.Load(in, dp);
            type = in.readInt();
            ext_type = in.readInt();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                int d = in.readInt();
                CPoint tp = dp.getPointById(d);
                if (tp == null) {
//                    CMisc.print("can not find point " + d);
                } else
                    this.addApoint(tp);
            }
            size = in.readInt();
            for (int i = 0; i < size; i++) {
                int d = in.readInt();
                cons.add(dp.getConstraintByid(d));
            }
        }

        if (CMisc.version_load_now >= 0.045)
            extent = in.readInt();
    }

}

