/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-15
 * Time: 14:18:38
 * To change this template use File | Settings | File Templates.
 */
package gprover;

public class Pro_point {
    final public static int MAX_GEO = 30;

    public int type, aux, type1;
    public String name = "";
    public int[] ps = new int[12];
    public int[] ps1 = new int[12];
    double x, y, x1, y1;
    cond co = null;
    String text;


    public Pro_point() {
        type = type1 = 0;
        for (int i = 0; i < 8; i++)
            ps[i] = ps1[i] = 0;
    }

    public Pro_point(int t) {
        this();
        type = t;
    }

    public int getAux() {
        return aux;
    }

    void setPS(int value, int index) {
        if (type1 == 0)
            ps[index] = value;
        else
            ps1[index] = value;
    }

    void setType(int t) {
        if ((type == 0 || type == gib.C_POINT) && t != 0)
            type = t;
        else
            type1 = t;
    }

    void set(int Type, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        if (type == 0) {
            type = Type;
            ps[0] = p1;
            ps[1] = p2;
            ps[2] = p3;
            ps[3] = p4;
            ps[4] = p5;
            ps[5] = p6;
            ps[6] = p7;
            ps[7] = p8;
        } else {
            type1 = Type;
            ps[0] = p1;
            ps[1] = p2;
            ps[2] = p3;
            ps[3] = p4;
            ps[4] = p5;
            ps[5] = p6;
            ps[6] = p7;
            ps[7] = p8;
        }
    }

    public Pro_point(int t, String s) {
        type = t;
        name = s;
    }

    public Pro_point(int Type, String ch, int p1, int p2, int p3, int p4,
                     int p5, int p6, int p7, int p8) {
        type = Type;
        name = (ch);
        ps[0] = p1;
        ps[1] = p2;
        ps[2] = p3;
        ps[3] = p4;
        ps[4] = p5;
        ps[5] = p6;
        ps[6] = p7;
        ps[7] = p8;
    }


    public void set_name(String s) {
        name = s;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setXY1(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public double getdx() {
        return x;
    }

    public double getdy() {
        return y;
    }

    public int getX1() {
        return (int) x1;

    }

    public int getY1() {
        return (int) y1;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public void add_nd(cond c) {
        if (co == null)
            co = c;
        c.nx = co;
        co = c;
    }

    public String getText() {
        return text;
    }
}

