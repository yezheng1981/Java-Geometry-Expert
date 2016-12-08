package wprover;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-1-26
 * Time: 15:29:29
 * To change this template use File | Settings | File Templates.
 */
public class CText extends CClass {

    final public static int NORMAL_TEXT = 0;
    final public static int NAME_TEXT = 1;
    final public static int CNAME_TEXT = 2;
    final public static int VALUE_TEXT = 3;


    private int type = NORMAL_TEXT;
    private int x, y;
    private Font font;
    private String str;
    public CTextValue tvalue;

    CClass father = null; // if any;


    double w, h;
    double height;
    private String svalue;
    private double posX, posY;


    public Font getFont() {
        return font;
    }

    public void setTextType(int t) {
        type = t;
        tvalue = CTextValue.parseString(str);
        if (tvalue == null)
            tvalue = new CTextValue();

        m_dash = 3;
        m_color = 16;
    }

    public boolean fontsizeChange(int n) {
        int size = font.getSize() + n;
        if (size <= 5)
            return false;

        font = new Font(font.getName(), font.getStyle(), size);
        return true;
    }

    public int getFontSize() {
        return font.getSize();
    }

    public void setBold() {
        if (!font.isBold())
            font = new Font(font.getName(), Font.BOLD, font.getSize());
    }

    public void setPlain() {
        if (!font.isPlain())
            font = new Font(font.getName(), Font.PLAIN, font.getSize());
    }

    public void setFont(Font f) {
        font = f;
    }

    public void setFontSize(int n) {
        if (n != font.getSize())
            font = new Font(font.getName(), font.getStyle(), n);
    }

    public CText() {
        super(CClass.TEXT);
        str = new String();

    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CText(CClass f, double dx, double dy, int type) {
        super(CClass.TEXT);
        str = new String();
        this.m_color = drawData.getColorIndex(Color.black);
        this.type = type;
        font = CMisc.nameFont;
        x += dx;
        y += dy;
        father = f;
    }

    public void move(double dx, double dy) {
        super.move(dx, dy);
        if (type == NORMAL_TEXT || type == VALUE_TEXT) {
            x += dx;
            y += dy;
        }

    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CText)) return false;
        CText t = (CText) obj;
        if (type != t.getType()) return false;
        if (str == null || str.length() == 0) return false;

        String s1 = obj.toString();
        if (s1 == null || s1.length() == 0) return false;

        return str.compareTo(obj.toString()) == 0;
    }

//    public CText(String s) {
//
//        super(CClass.TEXT);
//        x = y = 50;
//        str = s;
//        font = new Font("Dialog", Font.PLAIN, 14);
//    }

    public CText(int x, int y, String s) {
        super(CClass.TEXT);
        str = s;
        font = new Font("Dialog", Font.PLAIN, 14);
        this.x = x;
        this.y = y;
    }


    public String getText() {
        if (type == NORMAL_TEXT)
            return str;
        else if (type == NAME_TEXT)
            return father.m_name;
        else if (type == CNAME_TEXT)
            return str;
        else if (type == VALUE_TEXT)
            return str;

        return null;

    }

    public void setText1(String s) {
        str = s;
    }

    public void setText(String s) {

        if (type == NORMAL_TEXT)
            str = s;
        else if (type == NAME_TEXT)
            father.m_name = s;
        else if (type == CNAME_TEXT) {
            CAngle ag = (CAngle) father;
            ag.setShowType(2);
            father.m_name = s;
            str = s;
        } else if (type == VALUE_TEXT) {
            str = s;
            CTextValue dt = CTextValue.parseString(str);
            if (dt != null)
                tvalue = dt;
        }
    }

    public String getString() {
        return str;
    }

    public Dimension getTextDimension() {
        return new Dimension((int) w, (int) height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public int getType() {
        return this.type;
    }


    public int vlength() {
        if (str == null) return 0;
        return str.length();
    }

    public void setSvalue(String s) {
        svalue = s;
    }

    public boolean nameTextShown = CMisc.nameTextShown;

    public boolean isdraw() {
        if (!super.isdraw())
            return false;
        if (father != null && !father.isdraw())
            return false;

        if (CMisc.isApplication()) {
            if (type == NAME_TEXT)
                return CMisc.nameTextShown;
        } else {
            if (type == NAME_TEXT)
                return nameTextShown;          //APPLET ONLY
        }
        return true;
    }

    public int getSX() {
        double lx;
        lx = 0;
        if (type == NORMAL_TEXT) {
            lx = x;
        } else if (type == NAME_TEXT) {
            CPoint p = (CPoint) father;
            lx = p.getx() + x;
        } else if (type == CNAME_TEXT && CMisc.show_angle_text) {
            CAngle ag = (CAngle) father;
            lx = ag.getxForString() + x;
        }
        return (int) lx;
    }

    public int getSY() {
        double ly;
        ly = 0;
        if (type == NORMAL_TEXT) {
//            lx = x;
            ly = y;
        } else if (type == NAME_TEXT) {
            CPoint p = (CPoint) father;
//            lx = p.getx() + x;
            ly = p.gety() + y;
        } else if (type == CNAME_TEXT && CMisc.show_angle_text) {
            CAngle ag = (CAngle) father;
//            lx = ag.getxForString() + x;
            ly = ag.getyForString() + y;
        }
        return (int) ly;
    }


    public String getValueText() {
        double r = tvalue.dvalue; //CTextValue.calvalue(tvalue, null);
        String shead = "";
        switch (m_width) {
            case 0:
                break;
            case 1:
                shead = m_name;
                break;
            case 2:
                shead = str;
                break;
            case 3: {
                if (m_name == null || m_name.length() == 0)
                    shead = str;
                else
                    shead = m_name + " = " + str;
            }
            break;
            default:
                shead = str;
        }

        return shead + " = " + r;
    }

    public void draw(Graphics2D g2) {
        if (!isdraw()) return;

        String tstring = null;

        double lx, ly;
        lx = ly = 0;
        if (type == NORMAL_TEXT) {
            tstring = str;
            lx = x;
            ly = y;
            posX = lx;
            posY = ly;
        } else if (type == NAME_TEXT) {
            tstring = father.m_name;
            CPoint p = (CPoint) father;
            lx = p.getx() + x;
            ly = p.gety() + y;
            posX = lx;
            posY = ly;
        } else if (type == CNAME_TEXT && CMisc.show_angle_text) {
            CAngle ag = (CAngle) father;
            tstring = str;
            double r[] = CLine.Intersect(ag.lstart, ag.lend);
            if (r != null) {
                double dx = ag.getxForString() - r[0];
                double dy = ag.getyForString() - r[1];
                double rad = Math.sqrt(w * w + height * height) / 2;
                double len = Math.sqrt(dx * dx + dy * dy);
                dx += rad * dx / len;
                dy += rad * dy / len;
                lx = r[0] + x + dx - w / 2;
                ly = r[1] + y + dy - height / 2;
                posX = lx;
                posY = ly;
                //lx = ag.getxForString() + x - w / 2;
                //ly = ag.getyForString() + y - height / 2;
            }

        } else if (type == VALUE_TEXT) {

            tstring = getValueText();
            lx = x;
            ly = y;
            posX = lx;
            posY = ly;
        }


        if (tstring == null) return;
        if (tstring.length() == 0) return;


        String[] sl = tstring.split("\n");
        g2.setFont(font);
        super.setDraw(g2);
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = g2.getFont();
        LineMetrics lm = f.getLineMetrics(sl[0], frc);
        h = lm.getHeight();
        height = h * sl.length;
        w = 0;

        for (int i = 0; i < sl.length; i++) {
            Rectangle2D r2 = f.getStringBounds(sl[i], frc);
            if (r2.getWidth() > w)
                w = r2.getWidth();
            g2.drawString(sl[i], (float) lx, (float) (ly + (i + 1) * h));
        }

    }

    public void draw(Graphics2D g2, boolean select) {
        if (visible == false) return;

        int lx, ly;
        lx = ly = 0;

        if (type == NORMAL_TEXT || type == VALUE_TEXT) {
            lx = x;
            ly = y;
        } else if (type == NAME_TEXT) {
            CPoint p = (CPoint) father;
            lx = (int) p.getx() + x;
            ly = (int) p.gety() + y;
        } else if (type == CNAME_TEXT) {
            CAngle ag = (CAngle) father;
            double r[] = CLine.Intersect(ag.lstart, ag.lend);
            if (r != null) {
                double dx = ag.getxForString() - r[0];
                double dy = ag.getyForString() - r[1];
                double rad = Math.sqrt(w * w + height * height) / 2;
                double len = Math.sqrt(dx * dx + dy * dy);
                dx += rad * dx / len;
                dy += rad * dy / len;
                lx = (int) (r[0] + x + dx - w / 2);
                ly = (int) (r[1] + y + dy - height / 2);
                posX = lx;
                posY = ly;
            }
        }
        Rectangle rc = new Rectangle(lx - 2, ly + 2, (int) w + 2, (int) height + 2);
        g2.setColor(new Color(255, 200, 200));
        g2.fill(rc);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(0.5f));
        g2.draw(rc);
    }

    public String TypeString() {
        if (str == null)
            return "";

        int n = str.length();
        if (n >= 5)
            n = 4;
        else n--;
        if (n < 0)
            n = 0;

        if (str == null) {
        }

        if (type == NORMAL_TEXT) {
            String st = Language.getLs(355, "text");
            return st + "(\"" + str.substring(0, n) + "..\")";
        } else if (type == NAME_TEXT || type == CNAME_TEXT) {
            String st = Language.getLs(245, "name");
            return st + "(\"" + str.substring(0, n) + "..\")";
        } else if (type == VALUE_TEXT) {
            if (m_name == null) {
                String st = Language.getLs(355, "text");
                return st + "(\"" + str.substring(0, n) + "..\")";
            } else
                return m_name;
        }
        return null;
    }

    public String getDescription() {
        return this.TypeString();
    }

    public boolean inRect(double x0, double y0, double x1, double y1) {
        if (x0 > x1) {
            double r = x0;
            x0 = x1;
            x1 = r;
        }

        if (y0 > y1) {
            double r = y0;
            y0 = y1;
            y1 = r;

        }
        return x0 < x && y0 < y && x1 > x + w && y1 > y + height;
    }

    public boolean select(double x1, double y1) {
        if (visible == false) return false;

        if (type == NORMAL_TEXT || type == VALUE_TEXT) {
            double dx = x1 - x;
            double dy = y1 - y;

            if (dx > 0 && dx < w && dy > 0 && dy < height)
                return true;
            else
                return false;
        } else if (type == NAME_TEXT) {
            CPoint p = (CPoint) father;
            double dx = p.getx();
            double dy = p.gety();
            dx = x1 - dx - x;
            dy = y1 - dy - y;
            if (dx > 0 && dx < w && dy > 0 && dy < height)
                return true;
            else
                return false;
        } else if (type == CNAME_TEXT) {
            double dx = posX + w / 2 - x1;
            double dy = posY + height / 2 - y1;
            if (dx > -w / 2 && dx < w / 2 && dy > -height / 2 && dy < height / 2)
                return true;
            else
                return false;
        }
        return false;
    }

    public void drag(double dx, double dy) {
        x += dx;
        y += dy;
        if (type == NAME_TEXT) {
            if (x * x + y * y > CMisc.rlength * CMisc.rlength) {
                double r = Math.sqrt(x * x + y * y);
                x = (int) (x * CMisc.rlength / r);
                y = (int) (y * CMisc.rlength / r);
            }
        } else if (type == CNAME_TEXT) {
            CAngle ag = (CAngle) father;
            double x0 = ag.getxForString();
            double y0 = ag.getyForString();

            double x1 = x - x0;
            double y1 = y - y0;
            if (x1 * x1 + y1 * y1 > CMisc.rlength * CMisc.rlength) {
                double r = Math.sqrt(x1 * x1 + y1 * y1);
                x1 = (int) (x1 * CMisc.rlength / r);
                y1 = (int) (y1 * CMisc.rlength / r);
                x -= dx + x1;
                y -= dy + y1;
            }

        }
    }

    public void drag(double x0, double y0, double dx, double dy) {
        if (type == NORMAL_TEXT || type == VALUE_TEXT)
            drag(dx, dy);
        else if (type == NAME_TEXT) {
            CPoint p = (CPoint) father;
            double xx = p.getx();
            double yy = p.gety();

            double xp = x0 + dx - xx;
            double yp = y0 + dy - yy;

            double len = Math.sqrt(xp * xp + yp * yp);

            if (len > CMisc.rlength) {
                this.x = (int) (xp * CMisc.rlength / len);
                this.y = (int) (yp * CMisc.rlength / len);

            } else {
                this.x += dx;
                this.y += dy;
            }
        } else if (type == CNAME_TEXT) {
            CAngle p = (CAngle) father;
            double xx = p.getxForString() - w / 2;
            double yy = p.getyForString() - height / 2;

            double xp = x0 + dx - xx;
            double yp = y0 + dy - yy;

            double len = Math.sqrt(xp * xp + yp * yp);

            if (len > CMisc.rlength) {
                this.x = (int) (xp * CMisc.rlength / len);
                this.y = (int) (yp * CMisc.rlength / len);

            } else {
                this.x += dx;
                this.y += dy;
            }
        }


    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (!isdraw()) return;
        if (father != null && !father.isdraw()) return;


        String tstring = null;

        double lx, ly;
        lx = ly = 0;
        if (type == NORMAL_TEXT) {
            tstring = str;
            lx = x;
            ly = y;
        } else if (type == NAME_TEXT) {
            tstring = father.m_name;
            CPoint p = (CPoint) father;
            lx = p.getx() + x;
            ly = p.gety() + y;
        } else if (type == CNAME_TEXT) {
            CAngle ag = (CAngle) father;
            if (str == null || str.length() == 0)
                tstring = svalue;
            else
                tstring = str;
            lx = posX;
            ly = posY;
        } else if (type == VALUE_TEXT) {
            tstring = getValueText();
            lx = x;
            ly = y;
        }

        if (type == NORMAL_TEXT) {
            String s = "";
            if (font.getStyle() == Font.BOLD)
                s += "/" + font.getName() + "-Bold";
            else
                s += "/" + font.getName() + "-Plain";

            fp.write((s + " findfont " + font.getSize() + " scalefont setfont").getBytes());
            super.saveSuperColor(fp);
            fp.write("\n".getBytes());

            if (!tstring.contains("\n"))
                fp.write(("" + (int) lx + " " + (int) (-ly - 15) + " moveto (" + tstring + ") " + "show\n").getBytes());
            else {
                String[] str = tstring.split("\n");
                int h = (int) height / str.length;
                for (int i = 0; i < str.length; i++)
                    fp.write(((int) lx + " " + (int) (-ly - h * i - 15) + " moveto (" + str[i] + ") " + "show\n").getBytes());
            }
        } else {
            fp.write(("mf " + (int) lx + " " + (int) (-ly - 15) + " moveto (" + tstring + ") " + "show\n").getBytes());
        }

    }

    public void Save(DataOutputStream out) throws IOException {

        super.Save(out);

        out.writeInt(type);
        out.writeInt(x);
        out.writeInt(y);

        byte[] s = font.getName().getBytes();
        out.writeInt(s.length);
        out.write(s, 0, s.length);
        out.writeInt(font.getStyle());
        out.writeInt(font.getSize());

        if (type == NORMAL_TEXT) {
            s = str.getBytes();
            out.writeInt(s.length);
            out.write(s, 0, s.length);
        } else if (type == NAME_TEXT) {
            out.writeInt(father.m_id);
        } else if (type == CNAME_TEXT) {
            if (str == null)
                str = "";

            s = str.getBytes();
            out.writeInt(s.length);
            out.write(s, 0, s.length);
            out.writeInt(father.m_id);

        } else if (type == VALUE_TEXT) {
            s = str.getBytes();
            out.writeInt(s.length);
            out.write(s, 0, s.length);
            if (father == null)
                out.writeInt(-1);
            else out.writeInt(father.m_id);
        }


    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {

        if (CMisc.version_load_now < 0.010) {
            m_id = in.readInt();
            x = in.readInt();
            y = in.readInt();
            int size = in.readInt();
            byte[] s = new byte[size];
            in.read(s, 0, size);
            str = new String(s);

            if (CMisc.version_load_now >= 0.005) {
                int n = in.readInt();
                byte[] str = new byte[n];
                in.read(str, 0, n);
                String name = new String(str);
                int type = in.readInt();
                int sz = in.readInt();
                int rgb = in.readInt();

                font = new Font(name, type, sz);

                if (CMisc.version_load_now == 0.006) {
                    if (rgb <= 0)
                        m_color = drawData.getColorIndex(Color.black);
                    else
                        m_color = rgb;
                } else {
                    if (rgb < 0)
                        m_color = drawData.getColorIndex(Color.black);
                    else {
                        if (rgb == 9)
                            m_color = drawData.getColorIndex(Color.black);
                        else
                            m_color = (rgb);
                    }
                }
            }

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
        } else {
            super.Load(in, dp);


            type = in.readInt();
            x = in.readInt();
            y = in.readInt();

            int n = in.readInt();
            byte[] sstr = new byte[n];
            in.read(sstr, 0, n);
            String name = new String(sstr);

            int font_type = in.readInt();
            int sz = in.readInt();
            font = new Font(name, font_type, sz);

            if (type == NORMAL_TEXT) {
                int size = in.readInt();
                byte[] s = new byte[size];
                in.read(s, 0, size);
                str = new String(s);
            } else if (type == NAME_TEXT) {
                int id = in.readInt();
                CPoint p = dp.getPointById(id);
                p.ptext = this;
                father = p;
            } else if (type == CNAME_TEXT) {
                int size = in.readInt();
                byte[] s = new byte[size];
                in.read(s, 0, size);
                str = new String(s);

                int id = in.readInt();
                CAngle ag = dp.getAngleByid(id);
                ag.ptext = this;
                father = ag;
            } else if (type == VALUE_TEXT) {
                int size = in.readInt();
                byte[] s = new byte[size];
                in.read(s, 0, size);
                str = new String(s);
                tvalue = CTextValue.parseString(str);
                if (tvalue == null)
                    tvalue = new CTextValue();
                if (CMisc.version_load_now >= 0.052) {
                    int id = in.readInt();
                    father = dp.getOjbectById(id);
                }
            }


        }

    }

}
