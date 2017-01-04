package wprover;

import gprover.cond;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.util.Vector;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-23
 * Time: 21:54:51
 * To change this template use File | Settings | File Templates.
 */
public class CProveText {
    final private static int HSpace = 4;
    private static int D_ROW = 0;
    private static Color cchid = Color.blue;
    private static Image arrow;
    private int m_row = -1;
    private boolean isMouseOnArrow = false;
    private double ax, ay;

    private static JPanel d;

    private String head;
    private String msg;
    private String rule;
    private String rpath;

    private Font font;
    private Color chead = Color.blue;
    private Color cmsg = Color.black;
    private double x, y;

    private double w, h;
    private double height;
    private double width;
    private double whead;

    private boolean visible = true;
    private boolean isexpand = false;


    private CProveField cpfield;

    private UndoStruct m_undo;
    private cond m_co = null;

    private String bidx = "";


    public static void setArrowImage(Image ico) {
        arrow = ico;
    }

    public static void setDrawPanel(JPanel panel) {
        d = panel;
    }

    public void setExpanded(boolean exp) {
        isexpand = exp;
    }

    public boolean isExpanded() {
        return isexpand;
    }

    public cond getcond() {
        return m_co;
    }

    public CProveText() {

    }

    public static void resetRow() {
        D_ROW = 0;
    }

    public static int getRow() {
        return D_ROW;
    }

    public CProveText(String s1, String s2) {
        //  objlist = new Vector();
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = s1;
        msg = s2;

    }

    public CProveText(UndoStruct un, String s) {
        // objlist = new Vector();
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = s;
        msg = un.msg;
        if (un.m_type == UndoStruct.T_COMBINED_NODE || (un.m_type == UndoStruct.T_PROVE_NODE) && un.childundolist.size() > 0) {
            cpfield = new CProveField(un.childundolist);
            cmsg = cchid;//new Color(34,100,0);
        }
        m_undo = un;
    }

    public CProveText(String s) {
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = "";
        msg = s;
    }


    public CProveText(cond co, String s) {
        // objlist = new Vector();
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = s;
        msg = co.getText();
        //m_undo = un;
    }

    public CProveText(UndoStruct un) {
        // objlist = new Vector();
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = "";
        msg = un.msg;
        if (un.m_type == UndoStruct.T_COMBINED_NODE || (un.m_type == UndoStruct.T_PROVE_NODE) && un.childundolist.size() > 0) {
            cpfield = new CProveField(un.childundolist);
            cmsg = cchid;//new Color(34,100,0);
        }
        m_undo = un;
    }

    public CProveText(Vector vl, cond co, int index, boolean gc) {
        m_co = co;

        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        if (index >= 0)
            head = new Integer(index + 1).toString();
        else
            head = "";

        if (index >= 0) {
            if (index < 10)
                head += "  ";
            else
                head += " ";
        }
        int n = co.getNo();
        cond c = co.getPCO();
        boolean cons = true;
        Vector vv = new Vector();
        while (c != null) {
            if (c.getNo() != 0) {
                cons = false;
                break;
            }
            vv.add(c);
            c = c.nx;
        }
        if (co.getPCO() == null) {
            msg = co.getText();
        } else if (cons) {
            msg = co.getText();
            cmsg = cchid;
            cpfield = new CProveField(vv, false);
        } else if (n > 0) {
            msg = "Hence " + co.getText();
            cmsg = cchid;

            cond tc = co.getPCO();
            String dix = "  by ";
            int nco = 0;
            while (tc != null) {
                int j = 0;
                if (tc.getNo() != 0)
                    for (j = 0; j < vl.size(); j++) {
                        cond c1 = (cond) vl.get(j);
                        if (tc.getNo() == c1.getNo())
                            break;
                    }
                else {
                    int k = vl.indexOf(co);
                    for (j = k; j >= 0; j--)
                        if (vl.get(j) == tc)
                            break;
                }
                dix += (j + 1);
                nco++;
                tc = tc.nx;
                if (tc != null)
                    dix += ",";

            }
            if (nco > 1) {
                //dix += ")";
                bidx = "   " + dix;
                msg += bidx;
            }
        } else {
            msg = co.getText();
        }
    }

    public CProveText(UndoStruct un, int index) {
        rule = "";
        rpath = "";
        font = new Font("Dialog", Font.PLAIN, 14);
        head = new Integer(index + 1).toString() + ":  ";
        msg = un.msg;
        if (un.m_type == UndoStruct.T_COMBINED_NODE || (un.m_type == UndoStruct.T_PROVE_NODE) && un.childundolist.size() > 0) {

            cpfield = new CProveField(un.childundolist);
            cmsg = cchid;//new Color(34,100,0);
        }
        m_undo = un;
    }

    public void setFontSize(int size) {
        font = new Font(font.getName(), font.getStyle(), size);
        if (cpfield != null)
            cpfield.setFontSize(size);
    }


    public void setIndex(int index) {
        head = new Integer(index + 1).toString() + ":  ";
        if (cpfield != null)
            cpfield.reGenerateIndex();
    }

    public void setVisible(boolean v) {
        visible = v;
    }

    public boolean getVisible() {
        return visible;
    }


    public UndoStruct getUndoStruct() {
        return m_undo;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, (int) w, (int) height);
    }

    public Color getCaptainColor() {
        return chead;
    }

    public void setRule(String r) {
        rule = r;
    }

    public String getRule() {
        return rule;
    }

    public void setRulePath(String path) {
        rpath = path;
    }

    public String getRulePath() {
        return rpath;
    }

    public void setCaptainColor(Color c) {
        chead = c;
    }

    public Color getMessageColor() {
        return cmsg;
    }

    public void setMessageColor(Color c) {
        cmsg = c;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font f) {
        font = f;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String s) {
        head = s;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String s) {
        msg = s + "  " + this.bidx;
    }

    public Vector getObjectList() {
        if (m_undo == null) return new Vector();
        return m_undo.objectlist;
    }

    public void setObjectList(Vector v) {
        if (m_undo != null) {
            m_undo.objectlist.clear();
            m_undo.addObjectRelatedList(v);
        }
    }

    public void setWidth(double ww) {
        width = ww;
    }

    public double getWidth() {
        return w;
    }

    public double getHeadLength() {
        return whead;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String TypeString() {
        return "proof text";
    }

    public String getDescription() {
        return this.TypeString();
    }

    public CProveText selectChild(double x1, double y1, boolean onselect) {
        if (cpfield != null)
            return cpfield.select(x1, y1, onselect);
        return null;
    }

    public void clearSelection() {
        if (cpfield != null)
            cpfield.clearSelection();
    }

    public void expandAll() {
        if (this.isexpand)
            this.setExpanded(false);
        else
            this.setExpanded(true);

        if (cpfield != null)
            cpfield.expandAll();
    }

    public void expand() {
        if (this.isexpand)
            this.setExpanded(false);
        else
            this.setExpanded(true);
    }

    public CProveText redo_invisible_head(drawProcess dp) {
        if (cpfield == null) return this;
        if (!this.isexpand) return this;

        CProveText ct = cpfield.redo_invisible_head(dp);
        if (ct == null)
            return this;
        else
            return ct;
    }

    public void regenerateAll() {
        if (m_undo != null) {
            this.msg = m_undo.msg;
        }
    }

    public void getFlashObjectList(Vector v, drawProcess dp) {
        if (m_undo.m_type != UndoStruct.T_PROVE_NODE) {
            v.addAll(m_undo.getAllObjects(dp));
            return;
        }

        if (this.isexpand) {
            v.addAll(m_undo.objectlist);
        } else
            v.addAll(m_undo.getAllObjects(dp));
    }

//    public CProveText next(drawProcess dp)
//    {
//

//    }

    public CProveText findPText(UndoStruct un) {
        if (un == null)
            return null;
        if (un == m_undo)
            return this;
        if (cpfield == null)
            return null;

        return cpfield.findPText(un);
    }


    public CProveText next_prove_step(drawProcess dp, CProveText cpt, CBoolean find) {

        if (find.getValue() == false) {
            if (cpt == this) {
                find.setValue(true);
                if (this.visible) {
                    if (cpfield != null && this.isexpand)
                        return cpfield.next_prove_step(dp, cpt, find);

                    return null;
                } else
                    return null;
            } else {
                if (cpfield != null) return cpfield.next_prove_step(dp, cpt, find);
                return null;
            }
        } else {
            if (this.visible) {
                if (!this.isexpand || m_undo.m_type == UndoStruct.T_UNDO_NODE)//||m_undo.m_type ==UndoStruct.T_COMBINED_NODE)
                {
//                   dp.redo_step(m_undo);
                }
                return this;
            } else {
                {
//                  dp.redo_step(m_undo);
                }
                return null;
            }

        }
    }

    public boolean select(double x1, double y1) {
        double dx = x1 - x;
        double dy = y1 - y;

        if (dx > 0 && dx < width && dy > 0 && dy < height)
            return true;
        else
            return false;
    }

    public Point getPopExLocation() {
        return new Point((int) (ax + 16), (int) (ay + 16));
    }

    public CProveText mouseMove(double x, double y) {
        if (!visible) return null;

        double dx = x - ax;
        double dy = y - ay;
        this.isMouseOnArrow = dx >= 0 && dx <= 16 && dy >= 0 && dy <= 16;
        if (isMouseOnArrow)
            return this;
        if (cpfield != null)
            return cpfield.mouseMove(x, y);
        else
            return null;
    }

    public CProveText selectAll(double x1, double y1) {
        if (this.select(x1, y1)) return this;

        if (this.isExpanded())
            return this.selectChild(x1, y1, true);

        return null;
    }

    public void move(double dx, double dy) {
        x = x + (int) dx;
        y = y + (int) dy;
    }


    public void setCurrentPosition(Point p) {
        x = p.x;
        y = p.y;
    }


    public void getNextPosition(Point p) {
        p.setLocation((int) x, (int) (y + height));
    }

    public Point getNextPositionFromFirstNode() {
        return new Point((int) (x + whead), (int) (y + height));
    }

    public double getHeadwidth() {
        return whead;
    }

    public boolean run_to_begin(drawProcess dp) {
        if (m_undo == null) return false;
        if (cpfield != null)
            cpfield.run_to_begin(dp);
        else if (m_undo.m_type == UndoStruct.T_UNDO_NODE || m_undo.m_type == UndoStruct.T_COMBINED_NODE)
            dp.undo_step(m_undo);
        return true;
    }

    public boolean undo_default(drawProcess dp) {
        if (m_undo == null) return false;
        if (cpfield != null)
            cpfield.undo_default(dp);
        if (m_undo.m_type == UndoStruct.T_UNDO_NODE || m_undo.m_type == UndoStruct.T_COMBINED_NODE)
            dp.undo_step(m_undo);
        return true;
    }

    public boolean undo_to_head(drawProcess dp) {
        if (m_undo == null) return false;
        if (cpfield != null)
            cpfield.undo_to_head(dp);
        if (m_undo.m_type == UndoStruct.T_UNDO_NODE || m_undo.m_type == UndoStruct.T_COMBINED_NODE)
            dp.undo_step(m_undo);
        return true;
    }

    public void draw(Graphics2D g2, boolean selected) {
        if (selected == false)
            this.draw(g2);
        else {
            Rectangle rc = new Rectangle((int) (x - 2), (int) (y + 2), (int) width + 4, (int) height + 2);
            g2.setStroke(new BasicStroke(0.5f));
            g2.setColor(new Color(204, 255, 204));
            g2.fill(rc);
            g2.setColor(Color.black);
            g2.draw(rc);
        }
    }

    public void drawChild(Graphics2D g2, Point p) {
        if (cpfield != null) {
            cpfield.draw(g2, p);
        }
    }

    public CProveText fd_text(int i) {
        if (i == this.m_row)
            return this;
        if (cpfield != null)
            return cpfield.fd_text(i);
        else return null;
    }

    public void setStepRowDefault() {
        this.m_row = -1;
        if (cpfield != null)
            cpfield.setStepRowDefault();
    }

    public void draw(Graphics2D g2) {
        if (head == null) return;
        m_row = D_ROW++;

        g2.setFont(font);

        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics(head, frc);
        h = lm.getHeight();
        w = 0;
        Rectangle2D r1 = font.getStringBounds(head, frc);
        g2.setColor(chead);

        g2.drawString(head, (float) x, (float) (y + h));
        double tw = r1.getWidth();

        height = h;
        whead = w = tw;
        if (msg == null || msg.length() == 0) return;


        g2.setColor(cmsg);
        String[] sl = msg.split("\n");
        double start = x + tw + HSpace;
        for (int i = 0; i < sl.length; i++) {
            Rectangle2D r2 = font.getStringBounds(sl[i], frc);
            if (r2.getWidth() > w)
                w = r2.getWidth();
            g2.drawString(sl[i], (float) (start), (float) (y + (i + 1) * h));
        }
        height = h * sl.length;
        w = w + tw;
        ax = x + w + 10;
        ay = y + (h - 16);

        if (rule.length() > 0)
            if (isMouseOnArrow) {
                g2.setColor(Color.black);
                g2.drawRect((int) ax, (int) ay, 16, 16);
                g2.drawImage(arrow, (int) (ax), (int) (ay), Color.pink, d);
            } else
                g2.drawImage(arrow, (int) ax, (int) ay, d);

    }

    //////////////////////////////////////////////////////////////////////////
    public void saveText(DataOutputStream out, int space) throws IOException {
        if (m_undo.m_type == UndoStruct.T_TO_PROVE_NODE || m_undo.m_type == UndoStruct.T_PROVE_NODE) {
            if (msg != null && msg.length() != 0) {
                String tab = "";
                for (int i = 0; i < space; i++)
                    tab += " ";
                tab += head;
                String str = tab + msg + "\n";
                byte[] bt = str.getBytes();
                out.write(bt, 0, bt.length);
            }
            if (cpfield != null)
                cpfield.saveText(out, space + 5);
        }

    }

    public void SavePS(FileOutputStream fp, int stype, int ntype) throws IOException // 0 0. 1 20. 2 25.
    {
        if (visible == false) return;
        if (head == null) return;

        String sf = "/Times-Roman findfont " + font.getSize() + " fzoff add scalefont setfont\n";
        fp.write(sf.getBytes());

        if (head.length() != 0) {
            this.SavePsColor(chead, fp, stype);
            String sh = " " + x + " " + (-y) + " yoff add moveto (" + head + ") show\n";
            fp.write(sh.getBytes());
        }

        this.SavePsColor(cmsg, fp, stype);
        String[] sm = msg.split("\n");
        int sx = (int) (x + whead);
        String s1 = null;

        if (ntype == 1)
            s1 = " " + sx + " 20 add " + (-(int) y) + " yoff add moveto (" + sm[0] + ") show\n";
        else if (ntype == 2)
            s1 = " " + sx + " 25 add " + (-(int) y) + " yoff add moveto (" + sm[0] + ") show\n";
        else
            s1 = " " + sx + " " + (-(int) y) + " yoff add moveto (" + sm[0] + ") show\n";

        fp.write(s1.getBytes());
        for (int i = 1; i < sm.length; i++) {
            String sp = (int) (x + whead) + " " + (-(int) (y + h * i)) + " yoff add moveto (" + sm[i] + ") show\n";
            fp.write("   /yoff  yoff ystep add def\n".getBytes());
            fp.write(sp.getBytes());
        }
        fp.write("   /yoff  yoff ystep add def\n".getBytes());
        if (cpfield != null && this.isexpand)
            cpfield.SavePS(fp, stype);
    }

    public void SavePsColor(Color c, FileOutputStream fp, int stype) throws IOException {
        if (stype == 0)  //color
        {
            double r = ((double) (100 * c.getRed() / 255)) / 100;
            double g = ((double) (100 * c.getGreen() / 255)) / 100;
            double b = ((double) (100 * c.getBlue() / 255)) / 100;
            String s = new Double(r).toString() + " " + new Double(g).toString() + " " + new Double(b).toString();
            s += " setrgbcolor ";
            fp.write(s.getBytes());
        } else if (stype == 1)  //gray
        {
            String s = "";
            double gray = (int) ((0.11 * c.getRed() + 0.59 * c.getGreen() + 0.3 * c.getBlue()) / 2.55) / 100.0;
            s += " " + gray + " " + gray + " " + gray + " setrgbcolor ";
            fp.write(s.getBytes());
        } else if (stype == 2)  // black & white
        {
            String s = "0.0 0.0 0.0 setrgbcolor ";
            fp.write(s.getBytes());
        }
    }

    public void WriteString(DataOutputStream out, String s) throws IOException {
        out.writeInt(s.length());
        out.writeChars(s);
    }

    public void WriteFont(DataOutputStream out, Font f) throws IOException {
        String s = f.getName();
        WriteString(out, s);
        out.writeInt(f.getStyle());
        out.writeInt(f.getSize());
    }

    public String ReadString(DataInputStream in) throws IOException {
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

    public void Save(DataOutputStream out) throws IOException {
        this.WriteString(out, head);
        this.WriteString(out, msg);
        this.WriteString(out, rule);
        this.WriteString(out, rpath);

        this.WriteFont(out, font);

        out.writeInt(chead.getRGB());
        out.writeInt(cmsg.getRGB());

        out.writeDouble(x);
        out.writeDouble(y);

        out.writeBoolean(visible);
        out.writeBoolean(isexpand);

        if (cpfield != null) {
            out.writeBoolean(true);
            cpfield.Save(out);
        } else
            out.writeBoolean(false);

        if (m_undo == null)
            out.writeBoolean(false);
        else {
            out.writeBoolean(true);
            out.writeInt(m_undo.m_id);
        }

    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {

        head = this.ReadString(in);
        msg = this.ReadString(in);
        if (CMisc.version_load_now >= 0.033)
            rule = this.ReadString(in);
        else
            rule = "";
        if (CMisc.version_load_now >= 0.034)
            rpath = this.ReadString(in);
        else {
            if (rule.length() > 0) {
                String sp = System.getProperty("file.separator");
                rpath = "rules" + sp + rule + ".gex";
            } else
                rpath = "";
        }
        font = this.ReadFont(in);
        int c = in.readInt();
        chead = new Color(c);
        c = in.readInt();
        cmsg = new Color(c);

        x = in.readDouble();
        y = in.readDouble();

        visible = in.readBoolean();
        isexpand = in.readBoolean();

        boolean cp = in.readBoolean();
        if (cp) {
            cpfield = new CProveField();
            cpfield.Load(in, dp);
        }

        boolean isu = in.readBoolean();
        if (isu) {
            int id = in.readInt();
            m_undo = dp.getUndoById(id);
        } else
            m_undo = null;
    }
}
