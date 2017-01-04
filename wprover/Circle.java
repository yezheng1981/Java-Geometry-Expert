package wprover;

import java.util.Vector;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ${Yezheng}
 * Date: 2004-12-9
 * Time: 12:29:48
 * To change this template use File | Settings | File Templates.
 */
public class Circle extends CClass {
    public static int PCircle = 0;
    public static int RCircle = 1;
    public static int SCircle = 2;

    int type = PCircle;

    CPoint o = new CPoint();
    Vector points = new Vector();
    Vector cons = new Vector();

    public int psize() {
        return points.size();
    }

    public CPoint getP(int i) {
        return (CPoint) points.get(i);
    }

    public boolean p_on_circle(CPoint p) {
        for (int i = 0; i < points.size(); i++)
            if (p == points.get(i))
                return true;
        return false;
    }

    public void draw(Graphics2D g2, boolean selected) {
        if (!isdraw()) return;
        if (selected) {
            g2.setColor(CMisc.SelectObjectColor);
            g2.setStroke(CMisc.SelectObjectStroke);
        } else
            super.setDraw(g2);

        double x1, y1, r;
        x1 = o.x1.value;
        y1 = o.y1.value;
        r = getRadius();
        if (r < CMisc.MAX_DRAW_LEN)
            g2.drawOval((int) (x1 - r), (int) (y1 - r), 2 * (int) r, 2 * (int) r);
        else {
            if (points.size() < 2) return;
            CPoint p1, p2;
            p1 = p2 = null;
            double len = 0.00;
            for (int i = 0; i < points.size(); i++) {
                CPoint tp1 = (CPoint) points.get(i);
                for (int j = 1; j < points.size(); j++) {

                    CPoint tp2 = (CPoint) points.get(j);
                    if (tp1 == tp2) continue;
                    double tlen = Math.pow(tp1.getx() - tp2.getx(), 2) + Math.pow(tp1.gety() - tp2.gety(), 2);
                    if (tlen > len) {
                        len = tlen;
                        p1 = tp1;
                        p2 = tp2;
                    }
                }
            }
            if (p1 == null || p2 == null) return;
            double dx = p2.getx() - p1.getx();
            double dy = p2.gety() - p1.gety();
            double sl = Math.sqrt(dx * dx + dy * dy);
            x1 = p1.getx() - dx * 2000 / sl;
            y1 = p1.gety() - dy * 2000 / sl;
            double x2 = p1.getx() + dx * 2000 / sl;
            double y2 = p1.gety() + dy * 2000 / sl;
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);


        }


    }

    public void draw(Graphics2D g2) {
        this.draw(g2, false);
    }

    public String TypeString() {
        if (m_name == null) return Language.getLs(50, "circle ");
        return Language.getLs(50, "circle ") + m_name;
    }

    public String getDescription() {
        String st =  Language.getLs(50, "circle ");

        if (type == PCircle)
            return st + "(" + o.m_name + "," + o.m_name + this.getSidePoint().getname() + ")";
        else if (type == SCircle) {
            CPoint p1, p2, p3;
            p1 = p2 = p3 = null;
            if (points.size() < 3) return st;

            p1 = (CPoint) points.get(0);
            p2 = (CPoint) points.get(1);
            p3 = (CPoint) points.get(2);
            return st + "(" + o.getname() + "," + p1.getname() + p2.getname() + p3.getname() + ")";
        } else if (type == RCircle) {
            constraint cs = (constraint) cons.get(0);
            if (cs.GetConstraintType() == constraint.RCIRCLE) {
                CClass p1 = (CClass) cs.getelement(0);
                CClass p2 = (CClass) cs.getelement(1);
                return st + "(" + o.m_name + "," + p1.getname() + p2.getname() + ")";
            }
        }
        return this.TypeString();
    }

    public boolean select(double x, double y) {
        if (!visible) return false;
        double ox, oy;
        ox = o.getx();
        oy = o.gety();

        double r = this.getRadius();
        double len = Math.sqrt(Math.pow(x - ox, 2) + Math.pow(y - oy, 2));
        if (Math.abs(r - len) < CMisc.PIXEPS)
            return true;
        return false;

    }

    public void setType(int type) {
        this.type = type;
    }

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


    public CPoint getSidePoint() // point that with least number
    {
        CPoint pt = null;

        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            if (p == null)
                continue;
            if (pt == null)
                pt = p;
            else if (pt.x1.xindex > p.x1.xindex)
                pt = p;

        }
        return pt;
    }

    public double getCenterOX() {
        return o.getx();
    }

    public double getCenterOY() {
        return o.gety();
    }

    public double getRadius() {
        if (type == RCircle) {
            //constraint cs = null;
            for (int i = 0; i < cons.size(); i++) {
                constraint c = (constraint) cons.get(i);
                if (c.GetConstraintType() == constraint.RCIRCLE) {
                    CPoint p1 = (CPoint) c.getelement(0);
                    CPoint p2 = (CPoint) c.getelement(1);
                    return Math.sqrt(Math.pow(p1.getx() - p2.getx(), 2) + Math.pow(p1.gety() - p2.gety(), 2));
                }
            }
            return -1;
        } else {
            CPoint p = getSidePoint();
            return Math.sqrt(Math.pow(p.getx() - o.getx(), 2) + Math.pow(p.gety() - o.gety(), 2));
        }
    }

    public CPoint[] getRadiusPoint() {
        CPoint[] pl = new CPoint[2];
        if (type == Circle.RCircle) {
            for (int i = 0; i < cons.size(); i++) {
                constraint cs = (constraint) cons.get(i);
                if (cs.GetConstraintType() == constraint.RCIRCLE) {
                    pl[0] = (CPoint) cs.getelement(0);
                    pl[1] = (CPoint) cs.getelement(1);
                    return pl;
                }
            }

        } else if (type == Circle.PCircle | type == Circle.SCircle) {
            pl[0] = o;
            pl[1] = this.getSidePoint();
        }
        return pl;

    }

    public Circle() {
        super(CClass.CIRCLE);
    }

    public Circle(CPoint O, CPoint A, CPoint B, CPoint C) {
        super(CClass.CIRCLE);
        type = SCircle;
        this.o = O;
        points.add(A);
        points.add(B);
        points.add(C);
    }

    public Circle(CPoint O, CPoint A, CPoint B) {
        super(CClass.CIRCLE);
        this.o = O;
        points.add(A);
        points.add(B);
    }

    public Circle(CPoint O, CPoint A) {
        super(CClass.CIRCLE);
        this.o = O;
        points.add(A);
    }

    public Circle(int type, CPoint O) {
        super(CClass.CIRCLE);
        this.o = O;
        this.type = type;
    }

    public void addConstraint(constraint cs) {
        cons.add(cs);
    }

    public boolean hasPoint(CPoint p) {
        for (int i = 0; i < points.size(); i++) {
            if (p.isEqual((CPoint) points.get(i))) return true;
        }
        return false;
    }

    public void addPoint(CPoint p) {
        if (!points.contains(p))
            points.add(p);
    }

    public void pointStickToCircle(CPoint p) {
        double x = p.getx();
        double y = p.gety();

        double xo = o.x1.value;
        double yo = o.y1.value;
        double R = this.getRadius();
        double R1 = Math.sqrt((xo - x) * (xo - x) + (yo - y) * (yo - y));

        double y1 = yo + (y - yo) * R / R1;
        double x1 = xo + (x - xo) * R / R1;
        p.setXY(x1, y1);

    }

    public boolean on_circle(double x, double y) {
        return this.select(x, y);
    }

    public boolean nearcircle(double x, double y, double eps) {
        double ox, oy;
        ox = o.getx();
        oy = o.gety();

        double r = getRadius();
        double len = Math.sqrt(Math.pow(x - ox, 2) + Math.pow(y - oy, 2));
        if (Math.abs(r - len) < eps)
            return true;
        return false;
    }

    public void SmartPonc(CPoint p) {
        //    CPoint pt = (CPoint) points.get(0);
        double ox, oy, x, y;
        ox = o.getx();
        oy = o.gety();
        x = p.getx();
        y = p.gety();

        double r = this.getRadius();//Math.sqrt(Math.pow(pt.getx() - ox, 2) + Math.pow(pt.gety() - oy, 2));
        double len = Math.sqrt(Math.pow(p.getx() - ox, 2) + Math.pow(p.gety() - oy, 2));


        if (Math.abs(x - o.getx()) < 0.001) {
            if (y > oy)
                p.setXY(ox, oy + r);
            else
                p.setXY(ox, oy - r);
        } else {
            double k = r / len;
            p.setXY(ox + k * (x - ox), oy + k * (y - oy));
        }
    }

    public static Vector CommonPoints(Circle c1, Circle c2) {
        Vector vlist = new Vector();
        for (int i = 0; i < c1.points.size(); i++) {
            Object obj = c1.points.get(i);
            if (c2.points.contains(obj)) {
                if (!vlist.contains(obj))
                    vlist.add(obj);
            }
        }
        return vlist;
    }

    public boolean Tangent(Object obj) {
        if (obj instanceof CLine) {
            return true;
        } else if (obj instanceof Circle) {
            Circle c2 = (Circle) obj;
            CPoint p1 = this.getSidePoint();
            CPoint p2 = c2.getSidePoint();
            double r1 = Math.sqrt(Math.pow(this.o.getx() - p1.getx(), 2) + Math.pow(this.o.gety() - p1.gety(), 2));
            double r2 = Math.sqrt(Math.pow(c2.o.getx() - p2.getx(), 2) + Math.pow(c2.o.gety() - p2.gety(), 2));
            double d = Math.sqrt(Math.pow(this.o.getx() - c2.o.getx(), 2) + Math.pow(this.o.gety() - c2.o.gety(), 2));
            if (Math.abs(r1 + r2 - d) < CMisc.PIXEPS)
                return true;
            else
                return false;
        } else
            return false;
    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (!visible) return;

        float r = (float) (((int) (getRadius() * 100)) / 100.0);
        String s = "newpath " + o.m_name + " " +
                new Float(r).toString() + " circle";
        fp.write(s.getBytes());
        this.saveSuper(fp);
    }

    public void Save(DataOutputStream out) throws IOException {
        super.Save(out);

        out.writeInt(type);
        out.writeInt(o.m_id);
        out.writeInt(points.size());
        for (int i = 0; i < points.size(); i++) {
            CPoint p = (CPoint) points.get(i);
            out.writeInt(p.m_id);
        }
        out.writeInt(cons.size());
        for (int i = 0; i < cons.size(); i++) {
            constraint cs = (constraint) cons.get(i);
            out.writeInt(cs.id);
        }


    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        if (CMisc.version_load_now < 0.010) {
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


            type = in.readInt();
            int size = in.readInt();
            m_name = new String();
            for (int i = 0; i < size; i++)
                m_name += in.readChar();
            int d = in.readInt();
            o = dp.getPointById(d);

            size = in.readInt();
            for (int i = 0; i < size; i++) {
                int dx = in.readInt();
                points.add(dp.getPointById(dx));
            }
            size = in.readInt();
            for (int i = 0; i < size; i++) {
                int dx = in.readInt();
                cons.add(dp.getConstraintByid(dx));
            }
        } else {
            super.Load(in, dp);

            type = in.readInt();

            int d = in.readInt();
            o = dp.getPointById(d);

            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                int dx = in.readInt();
                points.add(dp.getPointById(dx));
            }
            size = in.readInt();
            for (int i = 0; i < size; i++) {
                int dx = in.readInt();
                cons.add(dp.getConstraintByid(dx));
            }

        }


    }


}
