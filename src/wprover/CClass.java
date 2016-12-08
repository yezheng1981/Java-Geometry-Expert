package wprover;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-7-8
 * Time: 16:00:08
 * To change this template use File | Settings | File Templates.
 */
// class as the baseclass for all the geometry item.

abstract public class CClass {

    final public static int POINT = 1;
    final public static int LINE = 2;
    final public static int CIRCLE = 3;
    final public static int TRACE = 4;
    final public static int DISTANCE = 5;
    final public static int ANGLE = 6;
    final public static int POLYGON = 7;
    final public static int TEXT = 8;
    final public static int TVALUE = 9;
    final public static int PTEXT = 10;
    final public static int EQMARK = 11;
    final public static int TMARK = 12;
    final public static int ARROW = 13;

    final public static int TEMP_POINT = 99;

    int m_id;

    int m_type;
    String m_name;

    int m_color;
    int m_dash;    //if any
    int m_width;   //if any

    boolean visible = true;

    int mode = 0; // 0. normal  1. in flash.

    public int get_type() {
        return m_type;
    }

    public void setInFlashing(boolean flash) {
        if (flash)
            mode = 1;
        else
            mode = 0;
    }

    public void stopFlash() {
        mode = 0;
    }

    public boolean isdraw() {
        return (visible == true && mode == 0) || (visible == false && mode == 1);
    }

    public boolean visible() {
        return visible;
    }

    public CClass(CClass c) {
        m_type = c.m_type;
        m_id = CMisc.getObjectId();
        m_dash = c.m_dash;
        m_width = c.m_width;
        m_color = c.m_color;

    }

    public CClass(int type) {
        m_type = type;

        if (type != TEMP_POINT)
            m_id = CMisc.getObjectId();
        else m_id = -1;

        m_dash = drawData.dindex;
        m_width = drawData.windex;


        if (type == TEMP_POINT) {
            m_color = drawData.pointcolor;
            return;
        }
        if (type == POINT) {
            m_color = drawData.pointcolor;
            m_width = 2;
        } else if (type == ANGLE) {
            m_color = drawData.anglecolor;
            m_dash = drawData.angledash;
            m_width = drawData.anglewidth;
        } else if (type == POLYGON)
            m_color = drawData.polygoncolor;
        else if (type == TRACE)
            m_color = drawData.tractcolor;
        else if (type == ARROW)
            m_color = 16;
        else
            m_color = drawData.cindex;

        if (type == EQMARK) {
            m_width = 3;
            m_color = 3;
        }
    }

    public void setAttrAux() {
        m_color = drawData.RED;
        m_dash = drawData.DASH8;
        m_width = drawData.WIDTH2;
    }

    public void setAttr(CClass c) {
        if (c == null) return;
        this.m_color = c.m_color;
        this.m_dash = c.m_dash;
        this.m_width = c.m_width;
    }

    public void copy(CClass c) {
        if (c == null) return;
        this.m_color = c.m_color;
        this.m_dash = c.m_dash;
        this.m_width = c.m_width;
    }

    public void setDash(int d) {
        m_dash = d;
    }

    public void setWidth(int index) {
        m_width = index;
    }

    public String getname() {
        return m_name;
    }

    public boolean hasNameSet() {
        return m_name != null && m_name.length() != 0;
    }

    public Color getColor() {
        return drawData.getColor(m_color);
    }

    public int getColorIndex() {
        return m_color;
    }

    public void draw(Graphics2D g2) {
    }

    public void setColor(int c) {
        m_color = c;
    }

    abstract public String TypeString();

    abstract public String getDescription();

    abstract void draw(Graphics2D g2, boolean selected);

    abstract boolean select(double x, double y);


    void move(double dx, double dy) {
    }

    void setVisible(boolean v) {
        this.visible = v;
    }

    public String toString() {
        return m_name;
    }

    void setDrawSelect(Graphics2D g2) {
        float w = (float) drawData.getWidth(m_width);
        g2.setStroke(new BasicStroke(w + 5));
        Color c = CMisc.SelectObjectColor;
        g2.setColor(c);
    }

    void setDrawSelect(Graphics2D g2, int w) {
//        float w = (float) drawData.getWidth(m_width);
        g2.setStroke(new BasicStroke(w + 5));
        Color c = CMisc.SelectObjectColor;
        g2.setColor(c);
    }

    void setDraw(Graphics2D g2) {
        float w = (float) drawData.getWidth(m_width);
        if (m_dash > 0) {
            float d = (float) drawData.getDash(m_dash);
            float dash[] = {d};
            g2.setStroke(new BasicStroke(w, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));
        } else
            g2.setStroke(new BasicStroke(w));

        Color c = drawData.getColor(m_color);
        if (CMisc.ColorMode == 1) {
            float gray = (float) (0.11 * c.getRed() + 0.59 * c.getGreen() + 0.3 * c.getBlue()) / 255;
            c = new Color(gray, gray, gray);
        }

        double r = CMisc.getAlpha();
        if (r != 1.0) {
            Color cc = new Color(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, (float) r);
            g2.setPaint(cc);
        } else
            g2.setPaint(c);
    }

    public static void WriteString(DataOutputStream out, String s) throws IOException {
        out.writeInt(s.length());
        out.writeChars(s);
    }

    public void WriteFont(DataOutputStream out, Font f) throws IOException {
        String s = f.getName();
        WriteString(out, s);
        out.writeInt(f.getStyle());
        out.writeInt(f.getSize());
    }

    public static String ReadString(DataInputStream in) throws IOException {
        int size = in.readInt();
        if (size == 0) return new String("");
        String s = new String();
        for (int i = 0; i < size; i++)
            s += in.readChar();
        return s;
    }

    public Font ReadFont(DataInputStream in) throws IOException {
        String name = ReadString(in);
        int stye = in.readInt();
        int size = in.readInt();

        return new Font(name, stye, size);

    }

    public abstract void SavePS(FileOutputStream fp, int stype) throws IOException;

    public void saveSuperColor(FileOutputStream fp) throws IOException {
        String s = " Color" + new Integer(m_color).toString() + " ";
        fp.write(s.getBytes());
    }

    public void saveSuper(FileOutputStream fp) throws IOException {
        String s = " Color" + new Integer(m_color).toString() + " ";
        s += "Dash" + new Integer(m_dash).toString() + " ";
        s += "Width" + new Integer(m_width).toString() + " ";
        s += "stroke \n";
        fp.write(s.getBytes());
    }

    public String getPSLineString(int x1, int y1, int x2, int y2) {
        String s = x1 + " " + y1 + " moveto " + x2 + " " + y2 + " lineto ";
        return s;
    }


    public void Save(DataOutputStream out) throws IOException {
        out.writeInt(m_id);

        if (m_name == null)
            out.writeInt(0);
        else {
            int size = m_name.length();
            out.writeInt(size);

            char[] nn = new char[size];
            m_name.getChars(0, size, nn, 0);
            for (int i = 0; i < size; i++)
                out.writeChar(nn[i]);
//            out.writeChars(m_name);
        }

        out.writeInt(m_color);
        out.writeInt(m_dash);
        out.writeInt(m_width);
        out.writeBoolean(visible);


    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        m_id = in.readInt();

        int size = in.readInt();
        if (size != 0) {

            m_name = new String();
            for (int i = 0; i < size; i++)
                m_name += in.readChar();
        }
        if (m_name == null || m_name.length() == 0)
            m_name = " ";

        m_color = in.readInt();
        m_dash = in.readInt();
        m_width = in.readInt();
        if (CMisc.version_load_now >= 0.017)
            visible = in.readBoolean();
    }


}
