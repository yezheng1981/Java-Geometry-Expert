package wprover;

import maths.param;

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
 * Time: 12:22:43
 * To change this template use File | Settings | File Templates.
 */
public class CPoint extends CClass {
    private int type = 0;
    public param x1, y1;
    private Vector cons = new Vector();
    boolean hasSetColor = false;
    int m_radius = -1; //default.
    private boolean freezed = false;
    CText ptext;


    public CPoint() {
        super(CClass.POINT);
        ptext = new CText(this, 5, -20, CText.NAME_TEXT);
    }

    public constraint getConstraint() {
        if (cons.size() == 0) return null;
        return (constraint) cons.get(0);
    }

    public CPoint(int type, param X, param Y) {
        super(type);
        x1 = X;
        y1 = Y;
    }

    public CPoint(String Name, param X, param Y) {
        super(CClass.POINT);
        m_name = Name;
        x1 = X;
        y1 = Y;
        ptext = new CText(this, 7, -24, CText.NAME_TEXT);
    }


    public CPoint(param X, param Y) {
        super(CClass.POINT);
        x1 = X;
        y1 = Y;
        ptext = new CText(this, 7, -24, CText.NAME_TEXT);
    }

    public CText getPText() {
        if (ptext == null) {
            return new CText(this, 7, -24, CText.NAME_TEXT);
        } else {
            return ptext;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            //          CMisc.print("A point without name");
            return false;
        }
        if (m_name == null || m_name.length() == 0) {
            return false;
        }
        return m_name.equals(obj.toString());
    }

    public void stopFlash() {
        super.stopFlash();
        if (ptext != null)
            ptext.stopFlash();
        //mode = 0;
    }

    public void setInFlashing(boolean flash) {
        super.setInFlashing(flash);
        if (ptext != null) {
            if (flash) {
                ptext.setInFlashing(true);
            } else {
                ptext.setInFlashing(false);
            }
        }
    }

    public boolean isAFixedPoint() {
        return x1.Solved && y1.Solved;
    }

    public boolean isAFreePoint() {
        return !x1.Solved && !y1.Solved;
    }

    public void setColor(int c) {
        super.setColor(c);
        this.hasSetColor = true;
    }

    public boolean select(double x, double y) {
        if (visible == false) {
            return false;
        }

        double dis = (Math.pow((getx() - x), 2) + Math.pow((gety() - y), 2));
        if (dis < CMisc.PIXEPS_PT * CMisc.PIXEPS_PT) {
            return true;
        }
        return false;
    }

    public boolean selectText(int x, int y) {
        return false;

    }

    public void draw(Graphics2D g2, boolean selected) {

        int radius = getRadius();
        setDrawSelect(g2);
        int x = (int) getx();
        int y = (int) gety();
        g2.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }


    public int POINT_RADIUS = CMisc.getPointRadius();

    public int getRadius() {
        int radius = m_radius;
//        if (radius < 0)
//            radius =  CMisc.getPointRadius();
        if (radius < 0) {
            if (CMisc.isApplication())
                radius = CMisc.getPointRadius();
            else
                radius = POINT_RADIUS;      //APPLET ONLY
        }
        return radius;
    }

    public int getRadiusValue() {
        return m_radius;
    }

    public void setRadius(int r) {
        if (r <= 0)
            m_radius = -1;
        m_radius = r;
    }

    public void draw(Graphics2D g2) {
        if (!isdraw()) {
            return;
        }
        int x = (int) getx();
        int y = (int) gety();
        int radius = getRadius();

        if (radius <= 1) return;

        if (radius < 3) {
            setDraw(g2);
            g2.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
            return;
        }
        setDraw(g2);
        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);


//        g2.setColor(g2.getBackground());
//        g2.fillOval(x - radius + 1, y - radius + 1, 2 * radius - 2, 2 * radius - 2);
        setDraw(g2);
        g2.fillOval(x - radius + 1, y - radius + 1, 2 * radius - 2, 2 * radius - 2);
    }

    public void drawA0(Graphics2D g2) {
        if (!isdraw()) {
            return;
        }
        int radius = getRadius();
        int x = (int) getx();
        int y = (int) gety();
        setDraw(g2);
        g2.setColor(Color.black);
        g2.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g2.setColor(super.getColor());
        g2.fillOval(x - radius + 1, y - radius + 1, 2 * radius - 2, 2 * radius - 2);
    }

    public void draw_wt(Graphics2D g2) {
        this.drawA0(g2);
        if (ptext != null) {
            ptext.draw(g2);
        }
    }

    public void draw_ct(Graphics2D g2) {
        int x = (int) getx();
        int y = (int) gety();
        setDraw(g2);
        int radius = CMisc.getPointRadius() + 2;

        g2.setColor(Color.white);
        g2.fillOval(x - radius + 1, y - radius + 1, 2 * radius - 2, 2 * radius - 2);

        g2.setColor(Color.black);
        g2.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        radius -= 3;
        g2.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

    }

    public double distanceTo(CPoint pt)
    {
        return Math.sqrt(Math.pow(this.getx() - pt.getx(), 2) + Math.pow(this.gety() - pt.gety(), 2));
    }

    public String TypeString() {
        String s1 = Language.getLs(33, "Point");

        if (m_name == null) {
            return s1;
        }
        return s1 + " " + m_name;
    }

    public String getDescription() {
        if (this.isAFreePoint()) {
            String s1 = Language.getLs(1052, "Free Point");
            return s1 + " " + this.m_name;
        } else {
            String s1 = Language.getLs(33, "Point");
            return s1 + " " + m_name;
        }
    }

    //////////////////////////////////////////////////////

    public void setColorDefault() {
        if (this.hasSetColor) {
            return;
        }
        if (!x1.Solved && !y1.Solved) {
            this.m_color = drawData.pointcolor;
        } else if (x1.Solved && y1.Solved) {
            this.m_color = drawData.pointcolor_decided;
        } else {
            this.m_color = drawData.pointcolor_half_decided;
        }
    }

    public void addcstoPoint(constraint cs) {
        if (cs != null && !cons.contains(cs)) {
            cons.add(cs);
        }
    }

    public boolean check_xy_valid(double x, double y) {
        for (int i = 0; i < cons.size(); i++) {
            constraint cs = (constraint) cons.get(i);
            if (!cs.check_constraint(x, y))
                return false;
        }
        return true;
    }

    public boolean isEqual(CPoint p) {

        if ((p.x1 == this.x1) && (p.y1 == this.y1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEqual(int x, int y) {
        if ((x == this.x1.xindex) && (y == this.y1.xindex)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSame_Location(double x, double y) {
        if (Math.abs(x - this.getx()) < CMisc.ZERO &&
                Math.abs(y - this.gety()) < CMisc.ZERO) {
            return true;
        }
        return false;
    }

    public double getx() {
        if (x1 == null) {
            CMisc.print("CPoint error,x1 undefined");
            return -1;
        }
        return x1.value;
    }

    public int getTx() {
        return ptext.getX();
    }

    public int getTy() {
        return ptext.getY();
    }

    public double gety() {
        if (y1 == null) {
            CMisc.print("CPoint error,y1 undefined");
            return -1;
        }
        return y1.value;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public void setFreezed(boolean r) {
        freezed = r;
    }

    public void setXY(double x, double y) {
        if (true) {
            x1.value = x;
            y1.value = y;
        }
    }

    public void setFillColor(int index) {
        this.m_color = index;
    }

    public String toString() {
        return m_name;
    }

    public void SavePS_Define_Point(FileOutputStream fp) throws IOException {
        String st = m_name;

        if (st.length() == 0 || st.trim().length() == 0)
            st = "POINT" + m_id;


        String s = '/' + st + " {";

        fp.write(s.getBytes());

        float x1 = (float) (((int) (this.x1.value * 100)) / 100.0);
        float y1 = (float) (((int) (this.y1.value * 100)) / 100.0);

        fp.write((new Float(x1).toString() + ' ').getBytes());

        fp.write((new Float(-y1).toString() +
                "} def \n").getBytes());

    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (visible == false) {
            return;
        }

        String s = null;
        int n = getRadius();
        if (n == 0)
            return;

        String st = m_name;

        if (st.length() == 0 || st.trim().length() == 0)
            st = "POINT" + m_id;

        s = st + " " + n + " cirfill fill " + st + " " + n + " cir black" + " stroke \n";
        fp.write(s.getBytes());
    }

    public void SavePsOringinal(FileOutputStream fp) throws IOException {
        if (visible == false) {
            return;
        }

        String s = null;
        int n = getRadius();

        String st = m_name;

        if (st.length() == 0 || st.trim().length() == 0)
            st = "POINT" + m_id;

        s = st + " " + n + " cirfill ";
        fp.write(s.getBytes());
        this.saveSuperColor(fp);
        s = " fill " + st + " " + n + " cir black" + " stroke \n";
        fp.write(s.getBytes());
    }

    public void Save(DataOutputStream out) throws IOException {
        super.Save(out);
        out.writeInt(type);
        out.writeInt(x1.xindex);
        out.writeInt(y1.xindex);
        out.writeInt(/*OnCircleOrOnLine*/0);
        int size = cons.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            constraint cs = (constraint) cons.get(i);
            if (cs != null)
                out.writeInt(cs.id);
            else out.writeInt(-1);
        }
        out.writeBoolean(visible);
        out.writeInt(m_radius);
        out.writeBoolean(freezed);
    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        if (CMisc.version_load_now < 0.01) {
            m_id = in.readInt();
            drawType drawt;
            if (in.readInt() == 0) {
                drawt = null;
            } else {
                drawt = new drawType();
                drawt.Load(in);
                m_color = drawt.color_index;
                m_dash = drawt.dash;
                m_width = drawt.width;
            }

            int len = in.readInt();
            m_name = new String();
            for (int i = 0; i < len; i++) {
                m_name += in.readChar();
            }
            type = in.readInt();

            int ix = in.readInt();
            x1 = dp.getParameterByindex(ix);
            int iy = in.readInt();
            y1 = dp.getParameterByindex(iy);
            /*OnCircleOrOnLine = */
            in.readInt();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                int id = in.readInt();
                cons.add(dp.getConstraintByid(id));
            }
            visible = in.readBoolean();

            this.ptext = new CText(this, 5, -5, CText.NAME_TEXT);
            dp.addObjectToList(ptext, dp.textlist);

        } else {
            super.Load(in, dp);

            type = in.readInt();
            int ix = in.readInt();
            x1 = dp.getParameterByindex(ix);
            int iy = in.readInt();
            y1 = dp.getParameterByindex(iy);
            /*OnCircleOrOnLine = */
            in.readInt();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                int id = in.readInt();
                constraint cs = dp.getConstraintByid(id);
                addcstoPoint(cs);
            }
            visible = in.readBoolean();
            this.hasSetColor = true;
            if (CMisc.version_load_now >= 0.043)
                m_radius = in.readInt();
            else
                m_radius = -1;// default.
            if (CMisc.version_load_now >= 0.050)
                freezed = in.readBoolean();
        }
    }


}

