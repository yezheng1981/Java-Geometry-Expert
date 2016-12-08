package wprover;

import java.util.Vector;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;

public class CTrace extends CClass {
    private static final int MAX_POINT = 501;

    private CPoint point, po;
    private CClass oObj;

    private int Num = 40;
    private int[] PX, PY;
    private int Radius = 2;
    private boolean dlns;
    private final static int MAXLEN = 300;


    public CTrace(CPoint p) {
        super(CClass.TRACE);
        m_name = "Trace of " + p;
        PX = new int[MAX_POINT];
        PY = new int[MAX_POINT];
        point = p;
        Num = -1;
    }

    public CTrace(CPoint p, CPoint po, CLine o) {
        super(CClass.TRACE);
        m_name = "Locus of " + p + " when " + po + " is on" + o;
        PX = new int[MAX_POINT];
        PY = new int[MAX_POINT];
        point = p;
        this.po = po;
        oObj = o;
    }

    public CTrace(CPoint p, CPoint po, Circle o) {
        super(CClass.TRACE);
        m_name = "Locus of " + p + " when " + po + " is on" + o;
        PX = new int[MAX_POINT];
        PY = new int[MAX_POINT];
        point = p;
        this.po = po;
        oObj = o;
    }

    public boolean isTracePt(CPoint pt) {
        return point == pt && po == null && oObj == null;

    }


    public void setDLns(boolean r) {
        dlns = r;
    }

    public boolean isDrawLines() {
        return dlns;
    }

    public void setNumPts(int n) {
        if (n < MAX_POINT)
            Num = n;
        else Num = MAX_POINT;
    }

    public void draw(Graphics2D g2, boolean selected) {
        if (!isdraw()) return;

        int radius = Radius;

        if (selected) {
            g2.setColor(CMisc.SelectObjectColor);
            g2.setStroke(CMisc.SelectObjectStroke);
            radius = Radius + 2;

        } else
            setDraw(g2);

        for (int i = 0; i < Num; i++) {
            if (!dlns)
                g2.fillOval(PX[i] - radius / 2, PY[i] - radius / 2, radius, radius);
            if (dlns) {
                if (oObj != null && oObj.get_type() == CClass.CIRCLE)
                    drawALN(PX[i], PY[i], PX[(i + 1) % Num], PY[(i + 1) % Num], g2);
                else if (i < Num - 1)
                    drawALN(PX[i], PY[i], PX[(i + 1) % Num], PY[(i + 1) % Num], g2);
            }
        }
    }

    public void drawALN(int x, int y, int x1, int y1, Graphics2D g2) {

        if ((x1 < -0 || x1 > 1000) && (x < -0 || x > 1000))
            return;

        if ((y1 < -0 || y1 > 1000) && (y < -0 || y > 1000))
            return;

        int dx = x - x1;
        int dy = y - y1;
        if(dx > MAXLEN || dx < - MAXLEN || dy > MAXLEN || dy < -MAXLEN)
            return;

        g2.drawLine(x, y, x1, y1);
    }

    public void draw(Graphics2D g2) {
        draw(g2, false);
    }

    public String TypeString() {
        if (m_name == null) return "Trace";
        return "Trace " + m_name;
    }

    public String getDescription() {
        return "Trace " + point.TypeString();
    }

    public boolean select(double x, double y) {
        if (!isdraw()) return false;
        double r2 = CMisc.PIXEPS * CMisc.PIXEPS;

        for (int i = 0; i < Num; i++)
            if (Math.pow(PX[i] - x, 2) + Math.pow(PY[i] - y, 2) < r2)
                return true;
        return false;
    }

    public void move(double dx, double dy) {
        for (int i = 0; i < Num; i++) {
            PX[i] += dx;
            PY[i] += dy;
        }
    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (!visible) return;

         for (int i = 0; i < Num; i++) {
                    if (dlns) {
                if (oObj != null && oObj.get_type() == CClass.CIRCLE || i < Num -1)
                {

                    int pos1x = PX[i];
                    int pos1y = PY[i];
                    int pos2x = PX[(i + 1) % Num];
                    int pos2y = PY[(i + 1) % Num];


                        String st1 = pos1x + " " + -pos1y + " moveto " + pos2x + " " + -pos2y + " lineto \n";
                        fp.write(st1.getBytes());
                        String st3 = "Color" + m_color + " stroke\n";
                        fp.write(st3.getBytes());
                }

            }
        }
    }


    public void Save(DataOutputStream out) throws IOException {
        super.Save(out);
        out.writeInt(point.m_id);
        out.writeInt(Num);
        for (int i = 0; i < Num; i++) {
            out.writeInt(PX[i]);
            out.writeInt(PY[i]);
        }

//          private CPoint point, po;
//    private CClass oObj;
//
//    private int Num = 16;
//    private int[] PX, PY;
//    private int Radius = 2;
//    private boolean dlns;
        int oid, mid;
        oid = mid = -1;
        if (po != null)
            oid = po.m_id;
        if (oObj != null)
            mid = oObj.m_id;
        out.writeInt(oid);
        out.writeInt(mid);

        out.writeInt(Num);
        out.writeInt(Radius);
        out.writeBoolean(dlns);
    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        super.Load(in, dp);
        if (CMisc.version_load_now >= 0.011) {
            int id = in.readInt();
            point = dp.getPointById(id);
        }
        Num = in.readInt();
        for (int i = 0; i < Num; i++) {
            PX[i] = in.readInt();
            PY[i] = in.readInt();
        }
        if (CMisc.version_load_now >= 0.044) {
            int id = in.readInt();
            po = dp.getPointById(id);
            id = in.readInt();
            oObj = dp.getOjbectById(id);
            Num = in.readInt();
            Radius = in.readInt();
            dlns = in.readBoolean();
        }
    }

    ///////////////////////////////////////////////////////


    public void addTracePoint(int i, double x, double y) {
        PX[i] = (int) x;
        PY[i] = (int) y;
        if (x < 0 || y < 0) {
            int k = 0;
        }
        soft(i);

    }

    public void addTracePoint(int x, int y, int i) {
        PX[i] = x;
        PY[i] = y;
        soft(i);
    }

    public void addTracePoint(int x, int y) {

        for (int i = 0; i <= Num; i++)
            if (PX[i] == x && PY[i] == y)
                return;
        if (Num >= MAX_POINT - 1)
            return;

        Num++;
        PX[Num] = x;
        PY[Num] = y;
    }


    public void softEdge() {

        for (int i = 2; i < Num - 1; i++) {
            int x = PX[i];
            int y = PY[i];

            int x0 = PX[i + 1];
            int y0 = PY[i + 1];

            int x1 = PX[i - 1];
            int y1 = PY[i - 1];

            int mx = Math.abs(x1 - x0);
            int my = Math.abs(y1 - y0);


            if (Math.abs(x - x0) > mx && Math.abs(x - x1) > mx
                    || Math.abs(y - y0) > my && Math.abs(y - y1) > my) {
                PX[i] = (x0 + x1) / 2;
                PY[i] = (y0 + y1) / 2;

            }


        }
    }

    public void soft(int i) {
    }


    public void trans(double dx, double dy) {
        for (int i = 0; i < Num; i++) {
            PX[i] += dx;
            PY[i] += dy;
        }
    }

    public double Roud_length() {
        if (Num == 0) return 0.0;

        double len = 0;
        int x, y;
        x = y = 0;

        for (int i = 0; i < Num; i++) {
            if (i != 0)
                len += Math.sqrt((x - PX[i]) * (x - PX[i]) + (y - PY[i]) * (y - PY[i]));
            x = PX[i];
            y = PY[i];
        }
        len += Math.sqrt((x - PX[0]) * (x - PX[0]) + (y - PY[0]) * (y - PY[0]));
        return len;
    }

    int getPtxi(int i) {
        return PX[i];
    }

    int getPtyi(int i) {
        return PY[i];
    }

    public CPoint getPoint() {
        return point;
    }

    public CPoint getonPoint() {
        return po;
    }

    public CClass getOnObject() {
        return oObj;
    }

    public int getPointSize() {
        return Num;
    }


}
