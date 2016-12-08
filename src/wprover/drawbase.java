package wprover;

import gprover.cndg;
import gprover.cons;
import gprover.gib;
import maths.CharSet;
import maths.TMono;
import maths.TPoly;
import maths.param;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-2
 * Time: 21:27:59
 * To change this template use File | Settings | File Templates.
 */
public class drawbase {
    final public static int D_POINT = 1;
    final public static int D_LINE = 2;
    final public static int D_PARELINE = 3;
    final public static int D_PERPLINE = 4;
    final public static int PERPWITHFOOT = 5;
    final public static int D_POLYGON = 6;
    final public static int D_CIRCLE = 9;
    final public static int D_3PCIRCLE = 10;
    final public static int D_MIDPOINT = 15;
    final public static int D_PSQUARE = 16;
    final public static int D_TEXT = 23;
    final public static int D_PFOOT = 25;
    final public static int D_CIRCLEBYRADIUS = 28;
    final public static int D_PTDISTANCE = 29;
    final public static int D_CCLINE = 21;
    final public static int D_SQUARE = 19;
    final public static int LRATIO = 30;
    final public static int D_PRATIO = 31;
    final public static int CIRCUMCENTER = 32;
    final public static int BARYCENTER = 33;
    final public static int ORTHOCENTER = 46;
    final public static int INCENTER = 47;
    final public static int D_TRATIO = 36;
    final public static int D_ANGLE = 17;
    final public static int SETEQANGLE = 18;
    final public static int MEET = 20;
    final public static int D_IOSTRI = 22;
    final public static int MIRROR = 24;
    final public static int DISTANCE = 26;

    final public static int H_LINE = 44;
    final public static int V_LINE = 45;
    final public static int D_ALINE = 50;
    final public static int D_ABLINE = 51;
    final public static int D_BLINE = 52;

    final public static int D_CIR_BY_DIM = 53;
    final public static int D_TCLINE = 54;
    final public static int CCTANGENT = 55;


    protected Vector pointlist = new Vector();
    protected Vector linelist = new Vector();
    protected Vector circlelist = new Vector();
    protected Vector anglelist = new Vector();
    protected Vector constraintlist = new Vector();
    protected Vector distancelist = new Vector();
    protected Vector polygonlist = new Vector();
    protected Vector textlist = new Vector();
    protected Vector tracelist = new Vector();
    protected Vector otherlist = new Vector();

    Vector flashlist = new Vector();


    protected Vector SelectList = new Vector();
    protected Vector CatchList = new Vector();
    protected CPoint CatchPoint = this.CreateATempPoint(0, 0);

    protected int MouseX, MouseY, mouseCatchX, mouseCatchY;
    protected int CatchType = 0;  // 1. middle ,  2. x pt,  3. y pt, 4: x & y.


    protected param[] parameter = new param[1024];
    protected double[] pptrans = new double[4];

    protected double[] paraBackup = new double[1024];
    protected TPoly polylist = null;
    protected TPoly pblist = null;

    protected int CurrentAction = 0;

    protected String name = "";
    protected double Width = 0;
    protected double Height = 0;

    protected int GridX = 40;
    protected int GridY = 40;
    protected boolean DRAWGRID = false;
    protected boolean SNAP = false;

    protected Color gridColor = CMisc.getGridColor(); //APPLET ONLY

    protected int paraCounter = 1;
    protected int pnameCounter = 0;
    protected int plineCounter = 1;
    protected int pcircleCounter = 1;

    protected static GeoPoly poly = GeoPoly.getPoly();
    protected static CharSet charset = CharSet.getinstance();

    protected boolean isPointOnObject = false;
    protected boolean isPointOnIntersection = false;
    protected double catchX, catchY;
    protected File file;
    protected boolean mouseInside = false;

    protected int STATUS = 0;

    protected GExpert gxInstance;
    protected Language lan;


    final public static int[][] POOL =  //1: pt
            //2. line 3. Circle. 4. LC (line or circle).   
            {
                    {D_LINE, 1, 1},
                    {D_PARELINE, 2, 1},
                    {D_PERPLINE, 2, 1},
                    {PERPWITHFOOT, 1, 2},
                    {D_POLYGON, 1},
                    {D_CIRCLE, 1, 1},
                    {D_3PCIRCLE, 1, 1, 1},
                    {D_MIDPOINT, 1, 1},
                    {D_PFOOT, 1, 1},
                    {D_CIRCLEBYRADIUS, 1, 1, 1},
                    {D_PTDISTANCE, 1, 1, 1, 4},
                    {D_CCLINE, 3, 3},
                    {D_ABLINE, 2, 2},
                    {D_PRATIO, 1, 1, 1},
                    {D_ALINE, 2, 2, 2, 1},
                    {D_BLINE, 1, 1},
                    {D_TCLINE, 3, 1},
                    {MEET, 4, 4},
                    {PERPWITHFOOT, 1, 2},
                    {D_ANGLE, 2, 2},
                    {60, 1, 4},
                    {79, 3, 3},
                    {76, 1, 1, 1, 1},
                    {D_SQUARE, 1, 1},
                    {D_CCLINE, 3, 3},
                    {D_IOSTRI, 1, 1},
                    {MIRROR, 4, 2},
//                    {D_PFOOT, 1, 1},
                    {87, 1, 1, 4},
                    {43, 1},
                    {D_PTDISTANCE, 1, 1},
                    {CIRCUMCENTER, 1, 1, 1},
                    {BARYCENTER, 1, 1, 1},
                    {ORTHOCENTER, 1, 1, 1},
                    {INCENTER, 1, 1, 1},
                    {DISTANCE, 1, 1},
                    {65, 1, 1},
                    {72, 1, 1, 1},
                    {73, 1, 1},
                    {74, 1, 1, 1, 1},
                    {75, 1, 1},
                    {82, 1, 1, 1, 1, 1, 1, 1, 1},
                    {81, 2, 1}

//                    {29, 1, 1, 1, 4}
//                    {D_PFOOT, 1, 1}


            };


    final public int getPooln(int a) {
        if (a == D_POLYGON) {
            if (STATUS < 10)
                return STATUS;
            else return 0;
        }

        for (int i = 0; i < POOL.length; i++) {
            if (POOL[i][0] == a)
                return POOL[i].length - 1;
        }

        return -1;
    }

    public int getPoolA(int a, int index) {
        for (int i = 0; i < POOL.length; i++) {
            if (POOL[i][0] == a) {
                if (POOL[i].length > index)
                    return POOL[i][index];
            }
        }
        return 1;
    }

    public void setLanguage(Language lan) {
        this.lan = lan;
    }

    public String getLanguage(int n, String s) {
        if (lan == null)
            return s;

        String s1 = lan.getString(n);
        if (s1 != null && s1.length() > 0)
            return s1;
        return s;
    }

    public void DWButtonDown(double x, double y) {
        switch (CurrentAction) {
        }
    }

    public void setMouseInside(boolean t) {
        mouseInside = t;
    }

    final public CPoint CreateATempPoint(double x, double y) {
        param p1 = new param(-1, x);
        param p2 = new param(-1, y);
        return new CPoint(CPoint.TEMP_POINT, p1, p2);
    }

    public CLine fd_line(CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null) {
            return null;
        }
        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (ln.points.contains(p1) && ln.points.contains(p2)) {
                return ln;
            }
        }
        return null;
    }

    final public void setAntiAlias(Graphics2D g2) {
        if (CMisc.AntiAlias) {
            RenderingHints qualityHints = new RenderingHints(RenderingHints.
                    KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHints(qualityHints);
        } else {
            RenderingHints qualityHints = new RenderingHints(RenderingHints.
                    KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHints(qualityHints);

        }
    }

    void paint(Graphics2D g2) {
        drawList(polygonlist, g2);
        drawSelect(SelectList, g2);
        drawPerpFoot(g2, null, 0);
        drawList(tracelist, g2);
        drawList(distancelist, g2);
        drawList(anglelist, g2);
        drawList(circlelist, g2);
        drawList(linelist, g2);
        drawList(pointlist, g2);
        drawList(textlist, g2);
        drawList(otherlist, g2);
        drawCatch(g2);
    }

    final public void drawPerpFoot(Graphics2D g2, Vector vlist, int type) { // 0: draw ,1: ps
        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            double x, y;
            int n = cs.GetConstraintType();
            switch (n) {

                case constraint.PERPENDICULAR: {
                    if (cs.getelement(0) instanceof CPoint)
                        continue;

                    CPoint p1, p2;
                    CLine line1 = (CLine) cs.getelement(0);
                    CLine line2 = (CLine) cs.getelement(1);
                    if (!line1.isdraw() || !line2.isdraw())
                        continue;
                    CPoint pt = null;
                    if ((pt = CLine.commonPoint(line1, line2)) == null) {
                        double lc[] = CLine.Intersect(line1, line2);
                        if (lc == null) {
                            continue;
                        }
                        x = lc[0];
                        y = lc[1];
                        if (!line1.inside(x, y) || !(line2.inside(x, y))) {
                            continue;
                        }
                        p1 = line1.getMaxXPoint();
                        p2 = line2.getMaxXPoint();
                    } else {
                        x = pt.getx();
                        y = pt.gety();
                        p1 = line1.getAPointBut(pt);
                        p2 = line2.getAPointBut(pt);
                    }
                    drawTTFoot(type, vlist, g2, x, y, null, p1, p2);
                }
                break;
                case constraint.PFOOT: {
                    CPoint PC = null;
                    CPoint p1, p2;

                    PC = (CPoint) cs.getelement(0);
                    x = PC.getx();
                    y = PC.gety();
                    p1 = (CPoint) cs.getelement(1);
                    CPoint tp1 = (CPoint) cs.getelement(2);
                    CPoint tp2 = (CPoint) cs.getelement(3);
                    if (tp1.getx() > tp2.getx()) {
                        p2 = tp1;
                    } else {
                        p2 = tp2;
                    }
                    if (!this.find_tmark(PC, tp1, PC, tp2))
                        drawTTFoot(type, vlist, g2, x, y, PC, p1, p2);
                }
                break;
                case constraint.SQUARE:
                case constraint.RECTANGLE: {
                    CPoint p1 = (CPoint) cs.getelement(0);
                    CPoint p2 = (CPoint) cs.getelement(1);
                    CPoint p3 = (CPoint) cs.getelement(2);
                    CPoint p4 = (CPoint) cs.getelement(3);

//                    drawTTFoot(type, vlist, g2, p1.getx(), p1.gety(), p1, p2, p4);
//                    drawTTFoot(type, vlist, g2, p2.getx(), p2.gety(), p2, p1, p3);
//                    drawTTFoot(type, vlist, g2, p3.getx(), p3.gety(), p3, p2, p4);
//                    drawTTFoot(type, vlist, g2, p4.getx(), p4.gety(), p4, p1, p3);

                }
                break;
                case constraint.RIGHT_ANGLED_TRIANGLE: {
                    CPoint p1 = (CPoint) cs.getelement(0);
                    CPoint p2 = (CPoint) cs.getelement(1);
                    CPoint p3 = (CPoint) cs.getelement(2);
                    drawTTFoot(type, vlist, g2, p1.getx(), p1.gety(), p1, p2, p3);

                }
                break;
                case constraint.RIGHT_ANGLE_TRAPEZOID: {
                    CPoint p1 = (CPoint) cs.getelement(0);
                    CPoint p2 = (CPoint) cs.getelement(1);
                    CPoint p3 = (CPoint) cs.getelement(2);
                    CPoint p4 = (CPoint) cs.getelement(3);
                    drawTTFoot(type, vlist, g2, p1.getx(), p1.gety(), p1, p2, p4);
                    drawTTFoot(type, vlist, g2, p4.getx(), p4.gety(), p4, p1, p3);
                }
                break;
                default:
                    break;
            }
        }

    }

    final public void removeFromeListLastNElements(Vector v, int n) {
        if (v.size() < n) return;
        while (n-- > 0)
            v.remove(v.size() - 1);
    }

    final public int getPointSize() {
        return pointlist.size();
    }

    final public Vector getPointList() {
        Vector v = new Vector();
        v.addAll(pointlist);
        return v;
    }

    final public void drawList(Vector list, Graphics2D g2) {
        if (list == null || list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            CClass cc = (CClass) list.get(i);
            cc.draw(g2);
        }
    }

    final public void drawPointNameLocation(CPoint p, Graphics2D g2) {
        g2.drawString("(x: " + new Integer((int) p.getx()).toString() + ", y: " +
                new Integer((int) p.gety()).toString() + ")",
                (int) p.getx() + 23, (int) p.gety() - 5);
    }

    final public void setCurrentDrawEnvironment(Graphics2D g2) {
        g2.setColor(drawData.getCurrentColor());
        g2.setStroke(CMisc.NormalLineStroke);
    }


    final public void drawGrid(Graphics2D g2) {
        if (!this.DRAWGRID && !SNAP) {
            return;
        }
        if (CMisc.isApplication())
            g2.setColor(CMisc.getGridColor());
        else
            g2.setColor(gridColor); //APPLET ONLY.
        //g2.setColor(CMisc.getGridColor());
        int nx = (int) this.Width / this.GridX;
        int ny = (int) this.Height / this.GridY;

        int st = 0;

        int x, y;
        for (int i = 0; i <= nx; i++) {
            x = st + i * GridX;
            g2.drawLine(x, 0, x, (int) Height);
        }
        for (int i = 0; i <= ny; i++) {
            y = st + i * GridY;
            g2.drawLine(0, y, (int) Width, y);
        }

    }

    final public void drawTipTirangle(CPoint p1, CPoint p2, CPoint p,
                                      Graphics2D g2) {

        double x0 = p1.getx();
        double y0 = p1.gety();

        double rx = p2.getx() - x0;
        double ry = p2.gety() - y0;
        double dx = p.getx() - x0;
        double dy = p.gety() - y0;
        double rr = Math.sqrt(rx * rx + ry * ry);
        double cy = ry / rr;
        double cx = rx / rr;
        double r;
        boolean isleft = false;

        if (Math.abs(rx) < CMisc.ZERO) {
            if (ry * (p1.getx() - p.getx()) > 0) {
                isleft = false;
            } else {
                isleft = true;
            }
            r = Math.abs(p1.getx() - p.getx());
        } else {
            double k = ry / rx;
            r = Math.abs((p.gety() - k * p.getx() + k * p1.getx() - p1.gety())) /
                    Math.sqrt(1 + k * k);
            isleft = (rx * dy - ry * dx < 0);
        }

        double x1 = (x0 + p2.getx()) / 2;
        double y1 = (y0 + p2.gety()) / 2;
        double x2, y2;
        if (isleft) {
            x2 = (x1 + r * cy);
            y2 = (y1 - r * cx);
        } else {
            x2 = x1 - r * cy;
            y2 = y1 + r * cx;
        }
        CatchPoint.setXY(x2, y2);
        drawCatchRect(g2);

        g2.setColor(Color.red);
        g2.drawLine((int) (x0), (int) (y0), (int) (x2), (int) (y2));
        g2.drawLine((int) p2.getx(), (int) p2.gety(), (int) (x2), (int) (y2));
        g2.drawLine((int) (p1.getx()), (int) (p1.gety()), (int) (p2.getx()), (int) (p2.gety()));
        g2.setStroke(CMisc.DashedStroke);

        if (Math.abs(cy) < CMisc.ZERO) {
            g2.drawLine((int) x1, 0, (int) x1, (int) this.Height);
        } else {
            double k = -cx / cy;
            g2.drawLine((int) (0), (int) (y1 - x1 * k), (int) (this.Width),
                    (int) (y1 + (this.Width - x1) * k));
        }
        catchX = x2;
        catchY = y2;
    }

    final public void drawCross(int x, int y, int w, Graphics2D g2) {
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawLine(x - w, y - w, x + w, y + w);
        g2.drawLine(x + w, y - w, x - w, y + w);
    }

    public void drawCatchRect(Graphics2D g2) {
        if (!isPointOnObject || !mouseInside) return;
        int x = (int) CatchPoint.getx();
        int y = (int) CatchPoint.gety();
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1.0f));
        if (!isPointOnIntersection) {
            drawRect(x - 5, y - 5, x + 5, y + 5, g2);
            if (CatchType == 1)
                g2.drawString("Middle Point", x + 10, y);
        } else {
            drawCatchInterCross(g2);
        }
    }

    public void drawCatchInterCross(Graphics2D g2) {
        if (!isPointOnIntersection) return;
        int x = (int) CatchPoint.getx();
        int y = (int) CatchPoint.gety();
        g2.setColor(Color.red);
        this.drawCross(x, y, 5, g2);
        g2.setFont(CMisc.font);
        g2.drawString(getLanguage(232, "Intersection"), x + 10, y);
    }

    public void drawTipRect(int x, int y, Graphics2D g2) {
        g2.setColor(Color.red);
        this.drawRect(x - 5, y - 5, x + 5, y + 5, g2);
    }

    public void drawCatchCross(Graphics2D g2) {
        if (!isPointOnObject || !mouseInside) return;
        int x = (int) CatchPoint.getx();
        int y = (int) CatchPoint.gety();
        drawCross(x, y, 5, g2);
    }

    public void drawPointOrCross(Graphics2D g2) {
        if (this.isPointOnObject) {
            if (!isPointOnIntersection)
                this.drawCross((int) CatchPoint.getx(), (int) CatchPoint.gety(), 5, g2);
            else
                drawCatchInterCross(g2);
        } else {
            drawpoint(CatchPoint, g2);
        }
    }

    public void drawCatchObjName(Graphics2D g2) {
        if (CatchList.size() != 1)
            return;
        if (!CMisc.DRAW_CATCH_OBJECT_NAME)
            return;
        CClass c = (CClass) CatchList.get(0);
        g2.setColor(Color.red);
        g2.setFont(CMisc.font);
        String s = c.getname();
        if (s != null)
            g2.drawString(s, MouseX + 16, MouseY + 20);
    }

    final public void drawTipSquare(CPoint p1, CPoint p2, CPoint p,
                                    Graphics2D g2) {
        double x0 = p1.getx();
        double y0 = p1.gety();

        double rx = p2.getx() - x0;
        double ry = p2.gety() - y0;
        double dx = p.getx() - x0;
        double dy = p.gety() - y0;
        double rr = Math.sqrt(rx * rx + ry * ry);
        double cy = ry / rr;
        double cx = rx / rr;
        double r;
        boolean isleft = false;

        if (Math.abs(rx) < CMisc.ZERO) {
            if (ry * (p1.getx() - p.getx()) > 0) {
                isleft = false;
            } else {
                isleft = true;
            }
            r = Math.abs(p1.getx() - p.getx());
        } else {
            double k = ry / rx;
            r = Math.abs((p.gety() - k * p.getx() + k * p1.getx() - p1.gety())) /
                    Math.sqrt(1 + k * k);
            isleft = (rx * dy - ry * dx < 0); //((ry * dx / rx - dy > 0 && ry / rx > 0) || (ry * dx / rx - dy < 0 && ry / rx < 0));
        }

        int n = (int) (r / rr) + 1;
        if (Math.abs(n * rr - r) < 2 * CMisc.PIXEPS) {
            r = rr * n;
        }

        g2.setColor(Color.red);
        g2.drawLine((int) x0, (int) y0, (int) p2.getx(), (int) p2.gety());
        if (isleft) {
            for (int i = 1; i <= n; i++) {
                g2.drawLine((int) x0, (int) y0, (int) (x0 + i * ry),
                        (int) (y0 - i * rx));
                g2.drawLine((int) (x0 + i * ry), (int) (y0 - i * rx),
                        (int) (x0 + i * ry + rx), (int) (y0 - i * rx + ry));
                g2.drawLine((int) (x0 + rx), (int) (y0 + ry),
                        (int) (x0 + i * ry + rx), (int) (y0 - i * rx + ry));
            }
            g2.drawLine((int) (p1.getx() + r * cy), (int) (p1.gety() - r * cx),
                    (int) (p2.getx() + r * cy), (int) (p2.gety() - r * cx));
        } else {
            for (int i = 1; i <= n; i++) {
                g2.drawLine((int) x0, (int) y0, (int) (x0 - i * ry),
                        (int) (y0 + i * rx));
                g2.drawLine((int) (x0 + rx), (int) (y0 + ry),
                        (int) (x0 + rx - i * ry), (int) (y0 + ry + i * rx));
                g2.drawLine((int) (x0 + rx - i * ry), (int) (y0 + ry + i * rx),
                        (int) (x0 - i * ry), (int) (y0 + i * rx));
            }
            g2.drawLine((int) (p1.getx() - r * cy), (int) (p1.gety() + r * cx),
                    (int) (p2.getx() - r * cy), (int) (p2.gety() + r * cx));
        }
    }

    protected boolean footMarkShown = CMisc.isFootMarkShown();
    protected double footMarkLength = CMisc.FOOT_MARK_LENGTH;

    public void drawTTFoot(int type, Vector vlist, Graphics2D g2, double x, double y, CPoint pc, CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null) return;

        if (CMisc.isApplication() && !CMisc.isFootMarkShown()) return;

        if (CMisc.isApplet() && !footMarkShown) return; //APPLET ONLY.

        if (!isLineDrawn(pc, p1) || !isLineDrawn(pc, p2))
            return;
        if (this.findCTMark(pc, p1, pc, p2) != null)
            return;

        double step = footMarkLength;  //APPLET ONLY.
        if (CMisc.isApplication())
            step = CMisc.FOOT_MARK_LENGTH;

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
        if (type == 0) {
            g2.setColor(Color.red);
            g2.setStroke(CMisc.NormalLineStroke);
            g2.drawLine((int) (fx + dx), (int) (fy + dy), (int) (ex), (int) (ey));
            g2.drawLine((int) (fx + dx1), (int) (fy + dy1), (int) (ex), (int) (ey));
        } else {
            Point m1 = new Point((int) (fx + dx), (int) (fy + dy));
            Point m2 = new Point((int) (ex), (int) (ey));
            Point m3 = new Point((int) (fx + dx1), (int) (fy + dy1));
            Point m4 = new Point((int) (ex), (int) (ey));
            vlist.add(m1);
            vlist.add(m2);
            vlist.add(m3);
            vlist.add(m4);
        }
    }

    public void drawCatch(Graphics2D g2) {
        int size = CatchList.size();

        int x = (int) CatchPoint.getx();
        int y = (int) CatchPoint.gety();

        CClass cc = null;
        if (size == 0) {
            if (CMisc.SMART_HV_LINE_CATCH) {
                if (CatchType == 2 || CatchType == 4) {
                    CPoint pt = this.getCatchHVPoint(2);
                    if (pt != null) {
                        g2.setColor(Color.red);
                        g2.setStroke(CMisc.DashedStroke);
                        g2.drawLine((int) pt.getx(), (int) pt.gety(), (int) pt.getx(), (int) y);
                    }
                }
                if (CatchType == 3 || CatchType == 4) {
                    CPoint pt = this.getCatchHVPoint(3);
                    if (pt != null) {
                        g2.setColor(Color.red);
                        g2.setStroke(CMisc.DashedStroke);
                        g2.drawLine((int) pt.getx(), (int) pt.gety(), x, (int) pt.gety());
                    }
                }
            }
        } else if (size == 1) {
            cc = (CClass) CatchList.get(0);
            cc.setDraw(g2);
            if (cc.m_type == CClass.POLYGON) {
                Color c = cc.getColor();
                g2.setColor(new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
            }
        } else {
            if (!isPointOnIntersection) {
                g2.setFont(CMisc.font);
                g2.setColor(Color.red);
                g2.drawString("(" + size + ")Which ?", x + 10, y + 25);
            }
        }

    }

    public boolean isLineDrawn(CPoint p1, CPoint p2) {
        CLine ln = this.fd_line(p1, p2);
        return ln != null && ln.isdraw();
    }

    public void drawSelect(Vector list, Graphics2D g2) {
        for (int i = 0; i < list.size(); i++) {
            CClass cc = (CClass) list.get(i);
            if (cc != null)
                cc.draw(g2, true);
        }
    }


    public void drawRect(int x, int y, int x1, int y1, Graphics2D g2) {
        g2.drawLine(x, y, x1, y);
        g2.drawLine(x, y, x, y1);
        g2.drawLine(x, y1, x1, y1);
        g2.drawLine(x1, y, x1, y1);
    }


    public void drawcircle2p(double x1, double y1, double x2, double y2,
                             Graphics2D g2) {
        int r = (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        g2.drawOval((int) (x1 - r), (int) (y1 - r), 2 * r, 2 * r);
    }


    public void drawpoint(CPoint p, Graphics2D g2) {
        p.draw(g2);
    }

    public void addLine(CLine ln) {
        if (!linelist.contains(ln)) {
            linelist.add(ln);
        }
    }

    public void addCircle(Circle c) {
        if (!circlelist.contains(c)) {
            circlelist.add(c);
        }
    }

    public CPoint findPoint(String name) {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (p.getname().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Circle fd_circle(CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null) {
            return null;
        }

        for (int i = 0; i < circlelist.size(); i++) {
            Circle cc = (Circle) circlelist.get(i);
            if (cc.points.contains(p2) && cc.o == p1) {
                return cc;
            }
        }
        return null;

    }


    int getEMarkNum() {
        int k = 0;
        for (int i = 0; i < otherlist.size(); i++) {
            if (otherlist.get(i) instanceof Cedmark) {
                k++;
            }
        }
        return k;
    }

    public Rectangle getBounds() {

        Rectangle rc = new Rectangle(0, 0, 0, 0);
        Vector v = pointlist;
        double x, y, x1, y1;
        x = y = Integer.MIN_VALUE;
        x1 = y1 = Integer.MAX_VALUE;

        if (v.size() != 0) {
            for (int i = 0; i < v.size(); i++) {
                CPoint p = (CPoint) v.get(i);
                double x0 = p.getx();
                double y0 = p.gety();
                if (x1 > x0) {
                    x1 = x0;
                }
                if (x < x0) {
                    x = x0;
                }
                if (y1 > y0) {
                    y1 = y0;
                }
                if (y < y0) {
                    y = y0;
                }
            }
            for (int i = 0; i < circlelist.size(); i++) {
                Circle c = (Circle) circlelist.get(i);
                double r = c.getRadius();

                if (x1 > c.o.getx() - r) {
                    x1 = c.o.getx() - r;
                }
                if (y1 > c.o.gety() - r) {
                    y1 = c.o.gety() - r;
                }
                if (x < c.o.getx() + r) {
                    x = c.o.getx() + r;
                }
                if (y < c.o.gety() + r) {
                    y = c.o.gety() + r;
                }
            }
        }

        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            Dimension dm = t.getTextDimension();
            int w = (int) dm.getWidth();
            int h = (int) dm.getHeight();
            int xt = t.getSX();
            int yt = t.getSY();
            if (x < xt + w) {
                x = xt + w;
            }
            if (y < yt + h) {
                y = yt + h;
            }
            if (x1 > xt) {
                x1 = xt;
            }
            if (y1 > yt) {
                y1 = yt;
            }
        }
        if (x1 < 0)
            x1 = 0;
        if (y1 < 0)
            y1 = 0;
        if (x > Width)
            x = Width;
        if (y > Height)
            y = Height;
        rc.setBounds((int) x1, (int) y1, (int) (x - x1), (int) (y - y1));
        return rc;
    }

    static boolean check_Collinear(CPoint p1, CPoint p2, CPoint p3) {
        if (p1 == null || p2 == null || p3 == null) {
            return false;
        }
        return isZero((p2.getx() - p1.getx()) * (p3.gety() - p2.gety()) -
                (p2.gety() - p1.gety()) * (p3.getx() - p2.getx()));
    }

    static boolean check_Collinear(double x1, double y1, double x2, double y2, double x3, double y3) {
        return
                isZero((x2 - x1) * (y3 - y2) - (y2 - y1) * (x3 - x2));

    }

    public static double signArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x2 - x1) * (y3 - y2) - (y2 - y1) * (x3 - x2);
    }

    static boolean check_between(CPoint p1, CPoint p2, CPoint p3) {
        if (p1 == null || p2 == null || p3 == null) {
            return false;
        }
        if (!check_Collinear(p1, p2, p3))
            return false;

        return (p1.gety() - p2.gety()) * (p1.gety() - p3.gety()) < 0
                || (p1.getx() - p2.getx()) * (p1.getx() - p3.getx()) < 0;


    }

    static boolean check_para(CLine ln1, CLine ln2) {
        double k1 = ln1.getK();
        double k2 = ln2.getK();
        return isZero(k1 - k2);
    }

    static boolean check_para(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        return isZero((p2.getx() - p1.getx()) * (p4.gety() - p3.gety()) -
                (p2.gety() - p1.gety()) * (p4.getx() - p3.getx()));
    }

    static boolean check_perp(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        return isZero(Math.abs((p2.getx() - p1.getx()) * (p4.getx() - p3.getx()) +
                (p2.gety() - p1.gety()) * (p4.gety() - p3.gety())));
    }

    static boolean check_mid(CPoint p, CPoint p1, CPoint p2) {
        if (p == null || p1 == null || p2 == null) {
            return false;
        }
        return check_Collinear(p1, p2, p) && check_eqdistance(p, p1, p, p2);
    }

    static boolean check_cyclic(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        double k1 = (p2.gety() - p1.gety()) / (p2.getx() - p1.getx());
        double k2 = (p4.gety() - p3.gety()) / (p4.getx() - p3.getx());
        k1 = -1 / k1;
        k2 = -1 / k2;
        double x1 = (p1.getx() + p2.getx()) / 2;
        double y1 = (p1.gety() + p2.gety()) / 2;
        double x2 = (p3.getx() + p4.getx()) / 2;
        double y2 = (p3.gety() + p4.gety()) / 2;
        double x = (y2 - y1 + k1 * x1 - k2 * x2) / (k1 - k2);
        double y = y1 + k1 * (x - x1);

        double t1 = Math.pow(p1.getx() - x, 2) + Math.pow(p1.gety() - y, 2);
        double t2 = Math.pow(p2.getx() - x, 2) + Math.pow(p2.gety() - y, 2);
        double t3 = Math.pow(p3.getx() - x, 2) + Math.pow(p3.gety() - y, 2);
        double t4 = Math.pow(p4.getx() - x, 2) + Math.pow(p4.gety() - y, 2);
        return isZero(t1 - t2) && isZero(t2 - t3) && isZero(t3 - t4);
    }

    static boolean check_eqdistance(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        return isZero(sdistance(p1, p2) - sdistance(p3, p4));
    }

    static boolean check_eqdistance(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return isZero(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) - Math.pow(x3 - x4, 2) - Math.pow(y3 - y4, 2));

    }

    static boolean check_eqdistance(CPoint p1, CPoint p2, CPoint p3, CPoint p4, int t1, int t2) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        return isZero(sdistance(p1, p2) * t2 - sdistance(p3, p4) * t1);
    }

    static boolean check_eqangle(CPoint p1, CPoint p2, CPoint p3, CPoint p4, CPoint p5, CPoint p6) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        double t1 = CAngle.getAngleValue(p1, p2, p3);
        double t2 = CAngle.getAngleValue(p4, p5, p6);
        return isZero(t1 - t2);
    }

    static boolean check_eqangle(CPoint p1, CPoint p2, CPoint p3, CPoint p4, CPoint p5, CPoint p6, CPoint p7, CPoint p8) {
        if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null || p7 == null || p8 == null) {
            return false;
        }
        double t1 = CAngle.getAngleValue(p1, p2, p3, p4);
        double t2 = CAngle.getAngleValue(p5, p6, p7, p8);
        return isZero(t1 - t2);
    }


    public static boolean check_same_side(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p4 == null || p1 == null || p2 == null || p3 == null) {
            return false;
        }
        return collv(p1, p2, p3) * collv(p1, p2, p4) > 0;
    }

    public static double collv(CPoint A, CPoint B, CPoint C) {
        double d1 = (B.getx() - A.getx()) * (C.gety() - A.gety()) -
                (B.gety() - A.gety()) * (C.getx() - A.getx());
        return d1;
    }

    public static boolean check_triangle_inside(CPoint p, CPoint p1, CPoint p2, CPoint p3) {
        if (p == null || p1 == null || p2 == null || p3 == null) {
            return false;
        }

        double d1 = collv(p, p1, p2);
        double d2 = collv(p, p2, p3);
        double d3 = collv(p, p3, p1);

        return d1 * d2 > 0 && d2 * d3 > 0 && d1 * d3 > 0;

    }

    public static double areaTriangle(CPoint p1, CPoint p2, CPoint p3) {
        double a = drawbase.sdistance(p1, p2);
        double b = drawbase.sdistance(p1, p3);
        double c = drawbase.sdistance(p3, p2);

        return Math.sqrt(a * a * c * c - Math.pow(a * a + c * c - b * b, 2) / 4);


    }

    static boolean check_angle_less(CPoint p1, CPoint p2, CPoint p3, CPoint p4, CPoint p5, CPoint p6) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        double t1 = CAngle.getAngleValue(p1, p2, p3);
        double t2 = CAngle.getAngleValue(p4, p5, p6);
        return Math.abs(t1) > Math.abs(t2);
    }

    static boolean check_distance_less(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        return (sdistance(p1, p2) < sdistance(p3, p4));
    }

    static boolean check_bisect(CPoint p1, CPoint p2, CPoint p3) {
        if (p1 == null || p2 == null || p3 == null) {
            return false;
        }
        return isZero(sdistance(p1, p2) - sdistance(p1, p3));
    }

    static boolean check_simtri(CPoint p1, CPoint p2, CPoint p3, CPoint p4,
                                CPoint p5, CPoint p6) {
        if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null ||
                p6 == null) {
            return false;
        }
        double r1 = sdistance(p1, p2);
        double r2 = sdistance(p1, p3);
        double r3 = sdistance(p2, p3);
        double r4 = sdistance(p4, p5);
        double r5 = sdistance(p4, p6);
        double r6 = sdistance(p5, p6);
        double t1 = r1 / r4;
        double t2 = r2 / r5;
        double t3 = r3 / r6;
        return isZero(t1 - t2) && isZero(t1 - t3) && isZero(t2 - t3);
    }

    static boolean check_congtri(CPoint p1, CPoint p2, CPoint p3, CPoint p4,
                                 CPoint p5, CPoint p6) {
        if (p1 == null || p2 == null || p3 == null || p4 == null || p5 == null ||
                p6 == null) {
            return false;
        }
        double r1 = sdistance(p1, p2);
        double r2 = sdistance(p1, p3);
        double r3 = sdistance(p2, p3);
        double r4 = sdistance(p4, p5);
        double r5 = sdistance(p4, p6);
        double r6 = sdistance(p5, p6);

        return isZero(r1 - r4) && isZero(r2 - r5) && isZero(r3 - r6);
    }

    static boolean check_lc_tangent(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return false;
    }

    static boolean check_cc_tangent(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return isZero(sdistance(p1, p2) - sdistance(p3, p4));
    }

    static double sdistance(CPoint p1, CPoint p2) {
        return Math.sqrt(Math.pow(p1.getx() - p2.getx(), 2) + Math.pow(p1.gety() - p2.gety(), 2));
    }

    static boolean nearPt(double x, double y, CPoint pt) {
        return Math.abs(x - pt.getx()) < CMisc.PIXEPS && Math.abs(y - pt.gety()) < CMisc.PIXEPS;
    }


    double[] get_pt_dmcr(double x1, double y1, double x2, double y2, double x, double y) {

        double dis = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double xx = (x1 + x2) / 2;
        double yy = (y1 + y2) / 2;
        double rs = Math.sqrt((Math.pow(xx - x, 2) + Math.pow(yy - y, 2)));
        double dx = (x - xx) / rs;
        double dy = (y - yy) / rs;
        double xr = xx + dx * dis / 2;
        double yr = yy + dy * dis / 2;
        double[] r = new double[2];

        if (near(xr, yr, x1, y1) || near(xr, yr, x2, y2)
                || drawbase.check_Collinear(x1, y1, x2, y2, xr, yr)) {
        } else if (Math.abs(xr - x1) < CMisc.PIXEPS) {
            xr = x1;
            yr = y2;
        } else if (Math.abs(xr - x2) < CMisc.PIXEPS) {
            xr = x2;
            yr = y1;
        } else if (Math.abs(yr - y1) < CMisc.PIXEPS) {
            yr = y1;
            xr = x2;
        } else if (Math.abs(yr - y2) < CMisc.PIXEPS) {
            yr = y2;
            xr = x1;
        }
        r[0] = xr;
        r[1] = yr;
        return r;
    }

    double[] intersect_lc(CLine ln, Circle cr) {
        double r2 = cr.getRadius();
        r2 *= r2;
        double k = ln.getK();
        CPoint p = ln.getfirstPoint();
        if (p == null) return null;
        CPoint o = cr.o;
        double x2 = p.getx();
        double y2 = p.gety();
        double x3 = o.getx();
        double y3 = o.gety();


        if (Math.abs(k) < CMisc.MAX_SLOPE) {
            double t = y2 - y3 - k * x2;
            double a = k * k + 1;
            double b = 2 * k * t - 2 * x3;
            double c = t * t + x3 * x3 - r2;
            double d = b * b - 4 * a * c;
            if (d < 0) return new double[0];
            d = Math.sqrt(d);
            double t1 = (-b + d) / (2 * a);
            double t2 = (-b - d) / (2 * a);
            double m1 = (t1 - x2) * k + y2;
            double m2 = (t2 - x2) * k + y2;
            double[] r = new double[4];
            r[0] = t1;
            r[1] = m1;
            r[2] = t2;
            r[3] = m2;
            return r;

        } else {
            double t1 = x2;
            double dl = r2 - (t1 - x3) * (t1 - x3);
            if (dl < 0) return null;
            double d = Math.sqrt(dl);
            double m1 = y3 + d;
            double t2 = t1;
            double m2 = y3 - d;
            double[] r = new double[4];
            r[0] = t1;
            r[1] = m1;
            r[2] = t2;
            r[3] = m2;
            return r;
        }
    }

    public double[] intersect_ll(CLine ln1, CLine ln2) {
        CPoint p1 = ln1.getfirstPoint();
        double k1 = ln1.getK();
        CPoint p2 = ln2.getfirstPoint();
        double k2 = ln2.getK();
        if (p1 == null || p2 == null) return null;
        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();
        double x, y;
        if (Math.abs(k1) > CMisc.MAX_SLOPE) {
            x = x1;
            y = y2 + k2 * (x - x2);
        } else if (Math.abs(k2) > CMisc.MAX_SLOPE) {
            x = x2;
            y = y1 + k1 * (x - x1);
        } else {
            x = (y2 - y1 + k1 * x1 - k2 * x2) / (k1 - k2);
            y = y1 + k1 * (x - x1);
        }
        double[] r = new double[2];
        r[0] = x;
        r[1] = y;
        return r;
    }

    public double[] intersect_cc(Circle c1, Circle c2) {
        double r1 = c1.getRadius();
        CPoint o1 = c1.o;
        double r2 = c2.getRadius();
        CPoint o2 = c2.o;

        double x1 = o1.getx();
        double y1 = o1.gety();
        double x2 = o2.getx() - x1;
        double y2 = o2.gety() - y1;
        double a = 2 * x2;
        double b = 2 * y2;
        double c = -x2 * x2 - y2 * y2 - r1 * r1 + r2 * r2;
        double ma = a * a + b * b;
        double d = 4 * a * a * (r1 * r1 * (ma) - c * c);
        if (d < 0) return null;
        d = Math.sqrt(d);
        if (a != 0) {
            double yt1 = (-2 * b * c + d) / (2 * (ma));
            double yt2 = (-2 * b * c - d) / (2 * (ma));
            double xt1 = -(b * yt1 + c) / a;
            double xt2 = -(b * yt2 + c) / a;
            double[] r = new double[4];
            r[0] = xt1 + x1;
            r[1] = yt1 + y1;
            r[2] = xt2 + x1;
            r[3] = yt2 + y1;
            return r;
        } else {
            double yt1, yt2;
            yt1 = yt2 = -c / b;
            double xt1 = Math.sqrt(r1 * r1 - yt1 * yt1);
            double xt2 = -xt1;
            double[] r = new double[4];
            r[0] = xt1 + x1;
            r[1] = yt1 + y1;
            r[2] = xt2 + x1;
            r[3] = yt2 + y1;
            return r;

        }

    }


    protected boolean check_cc_inter(Circle c1, Circle c2) {
        double r1 = c1.getRadius();
        double r2 = c2.getRadius();
        double r = Math.sqrt(Math.pow(c1.getCenterOX() - c2.getCenterOX(), 2) +
                Math.pow(c1.getCenterOY() - c2.getCenterOY(), 2));
        double rx = r - r1 - r2;
        double rx1 = r1 - r - r2;
        double rx2 = r2 - r - r1;

        return rx < 0.1 && rx1 < 0.1 && rx2 < 0.1;

    }

    protected boolean check_lc_inter(CLine ln, Circle c2) {
        double r1 = ln.distance(c2.getCenterOX(), c2.getCenterOY());
        double r2 = c2.getRadius();
        return (r2 - r1) > 0;
    }

    protected static boolean isZero(double r) {
        return Math.abs(r) < CMisc.ZERO;
    }

    protected static boolean near(double x, double y, double x1, double y1) {
        return Math.abs(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)) < CMisc.PIXEPS * CMisc.PIXEPS;
    }

    public static void set_eps(double r) {
    }


    protected CPoint[] getPoints(cons c) {

        CPoint[] pp = new CPoint[8];
        int i = 0;
        while (true) {
            Object p1 = c.getPTN(i);
            if (p1 == null)
                break;

            pp[i] = findPoint(p1.toString());
            if (pp[i] == null) {
                JOptionPane.showMessageDialog(null, "Can not find point " + p1 + "\nPlease construct the diagram", "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            i++;
        }
        return pp;
    }

//    public int dxindex(int n)
//    {
//        gt
//    }
//
//    public TMono getTMono(cndg d)
//    {
//        if(d == null)
//            return null;
//
//

    //    }

    protected TMono getTMono(cons c) {
        if (c == null) return null;

        CPoint[] pp = getPoints(c);
        if (pp == null) return null;
        TMono m = null;

        switch (c.type) {
            case gib.CO_COLL:
                m = poly.collinear(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex);
                break;
            case gib.CO_PARA:
                m = poly.parallel(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex, pp[3].x1.xindex, pp[3].y1.xindex);
                break;
            case gib.CO_PERP:
                m = poly.perpendicular(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex, pp[3].x1.xindex, pp[3].y1.xindex);
                break;
            case gib.CO_MIDP:
                m = poly.midpoint(pp[1].x1.xindex, pp[0].x1.xindex, pp[2].x1.xindex);
                break;
            case gib.CO_CYCLIC:
                m = poly.cyclic(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex, pp[3].x1.xindex, pp[3].y1.xindex);
                break;
            case gib.CO_CONG:
                m = poly.eqdistance(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex, pp[3].x1.xindex, pp[3].y1.xindex);
                break;
            case gib.CO_ACONG: {
                if (pp[6] != null && pp[7] != null)
                    m = poly.eqangle(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex, pp[3].x1.xindex, pp[3].y1.xindex,
                            pp[4].x1.xindex, pp[4].y1.xindex, pp[5].x1.xindex, pp[5].y1.xindex, pp[6].x1.xindex, pp[6].y1.xindex, pp[7].x1.xindex, pp[7].y1.xindex);
                else
                    m = poly.eqangle(pp[0].x1.xindex, pp[0].y1.xindex, pp[1].x1.xindex, pp[1].y1.xindex, pp[2].x1.xindex, pp[2].y1.xindex,
                            pp[3].x1.xindex, pp[3].y1.xindex, pp[4].x1.xindex, pp[4].y1.xindex, pp[5].x1.xindex, pp[5].y1.xindex);
            }
            break;
            case gib.CO_PBISECT:
                break;
            case gib.CO_STRI:
                break;
            case gib.CO_CTRI:
                break;
        }

        if (m == null) return m;
        TMono m1 = m;
        while (m1.coef != null)
            m1 = m1.coef;
        if (m1.value() < 0)
            m = poly.cp_times(-1, m);
        return m;
    }

    public boolean decide_wu_identical(CPoint p1, CPoint p2) {

        TMono m1 = poly.ppdd(p1.x1.xindex, p2.x1.xindex); //poly.pdif(poly.pcopy(p1.x1.m), poly.pcopy(p2.x1.m));
        TMono m2 = poly.ppdd(p1.y1.xindex, p2.y1.xindex); //poly.pdif(poly.pcopy(p1.y1.m), poly.pcopy(p2.y1.m));
        return div_set(m1) && div_set(m2);

    }

    public boolean div_set(TMono m1) {
        if (m1 == null)
            return true;

        TPoly p1 = polylist;

        if (poly.pzerop(m1))
            return true;
        while (p1 != null) {
            TMono t = p1.poly;
            if (t.x == m1.x)
                break;
            p1 = p1.next;
        }

        while (true) {
            TMono m = p1.poly;
            TMono md = poly.pcopy(m);
            m1 = poly.prem(m1, md);
            if (poly.pzerop(m1))
                return true;
            TPoly p2 = polylist;
            if (p1 == p2)
                break;

            while (p2 != null) {
                if (p2.next == p1)
                    break;
                p2 = p2.next;
            }
            p1 = p2;
        }

        CMisc.print("======================");
        poly.printpoly(m1);
        this.printPoly(polylist);
        return false;
    }

    public void printPoly(TPoly p) {
        while (p != null) {
            poly.printpoly(p.getPoly());
            p = p.getNext();
        }
    }


    public boolean verify_ndg(TMono m) {
        if (m == null)
            return true;
        TPoly p1 = polylist;
        if (poly.pzerop(m))
            return false;


        return true;
    }

    public CTMark findCTMark(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        for (int i = 0; i < otherlist.size(); i++) {
            CClass c = (CClass) otherlist.get(i);
            if (c.get_type() == CClass.TMARK) {
                CTMark m = (CTMark) c;
                if (m.ln1.containPTs(p1, p2) && m.ln2.containPTs(p3, p4))
                    return m;
                if (m.ln2.containPTs(p1, p2) && m.ln1.containPTs(p3, p4))
                    return m;

            }
        }
        return null;
    }

    public boolean find_tmark(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash f = (JFlash) flashlist.get(i);
            if (f instanceof JTlineFlash) {
                JTlineFlash t = (JTlineFlash) f;
                if (t.containPt(p1) && t.containPt(p2) && t.containPt(p3) && t.containPt(p4))
                    return true;
            }
        }
        return false;
    }

    public boolean containFreezedPoint() {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (p.isFreezed()) {
                return true;
            }
        }
        return false;
    }

    public void unfreezeAllPoints() {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (p.isFreezed()) {
                p.setFreezed(false);
            }
        }
    }

    public boolean isFreezed() {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (p.isFreezed()) {
                gxInstance.setTextLabel2("The diagram is freezed, use right click menu to unfreeze!");
                return true;
            }
        }
        return false;
    }

    public void setAllFreezed() {
        for (int i = 0; i < pointlist.size(); i++) {
            {
                CPoint p = (CPoint) pointlist.get(i);
                p.setFreezed(true);
            }
        }
    }


    public void zoom_out(double x, double y, int zz) {

        if (isFreezed())
            return;

        double r = CMisc.ZOOM_RATIO;
        r = 1 + (r - 1) / zz;

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.setXY(p.getx() * 1.0 / r + (1.0 - 1.0 / r) * x, p.gety() * 1.0 / r + (1.0 - 1.0 / r) * y);
        }
    }

    public void zoom_in(double x, double y, int zz) {
        if (isFreezed())
            return;

        double r = CMisc.ZOOM_RATIO;
        r = 1 + (r - 1) / zz;
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.setXY(p.getx() * r + (1.0 - r) * x, p.gety() * r + (1.0 - r) * y);
        }
    }

    public void hvCatchPoint() {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint pt = (CPoint) pointlist.get(i);
            if (drawbase.sdistance(CatchPoint, pt) > CMisc.PIXEPS_PT) {
                if (Math.abs(pt.getx() - CatchPoint.getx()) < CMisc.PIXEPS_PT / 2) {
                    if (CatchType == 3) {
                        CatchType = 4;
                        return;
                    } else CatchType = 2;
                } else if (Math.abs(pt.gety() - CatchPoint.gety()) < CMisc.PIXEPS_PT / 2) {
                    if (CatchType == 2) {
                        CatchType = 4;
                        return;
                    } else
                        CatchType = 3;
                    return;
                }
            }
        }
    }

    public CPoint getCatchHVPoint(int CatchType) {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint pt = (CPoint) pointlist.get(i);
            if (drawbase.sdistance(CatchPoint, pt) > CMisc.PIXEPS_PT) {
                if (CatchType == 2 && Math.abs(pt.getx() - CatchPoint.getx()) < CMisc.PIXEPS_PT / 2) {
                    return pt;
                } else if (CatchType == 3 && Math.abs(pt.gety() - CatchPoint.gety()) < CMisc.PIXEPS_PT / 2) {
                    return pt;
                }
            }
        }
        return null;
    }

    public void setCatchHVPoint(CPoint pv) {
        if (CatchType != 2 && CatchType != 3 && CatchType != 4)
            return;

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint pt = (CPoint) pointlist.get(i);
            if (drawbase.sdistance(pv, pt) > CMisc.PIXEPS_PT) {
                if ((CatchType == 2 || CatchType == 4) && Math.abs(pt.getx() - pv.getx()) < CMisc.PIXEPS_PT / 2) {
                    pv.setXY(pt.getx(), pv.gety());
                    return;
                }
                if ((CatchType == 3 || CatchType == 4) && Math.abs(pt.gety() - pv.gety()) < CMisc.PIXEPS_PT / 2) {
                    pv.setXY(pv.getx(), pt.gety());
                    return;
                }
            }
        }
    }


}
