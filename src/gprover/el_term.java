package gprover;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-4-17
 * Time: 13:17:41
 * To change this template use File | Settings | File Templates.
 */
public class el_term {
    final public static int EL_CYCLIC = 11;
    final public static int EL_PARA = 2;
    final public static int EL_PERP = 3;
    public int etype = 0;
    public var v;
    public xterm p1, p2, p;
    public int np = 1;
    public cond co;
    public el_term nx;
    public String text = "";

    public el_term et;

    public el_term() {
        int k = 0;
    }

    public void setText(String s) {
        text = s;
    }

    public String toString() {
        return text;
    }

    public Vector getAllxterm() {
        Vector v = new Vector();
        v.add(p);

        xterm x = p1;
        while (x != null) {
            v.add(x);
            dterm d = x.ps;
            if (d != null)
                d = d.nx;
            if (d != null)
                x = d.p;
            else
                break;
        }
        
        if (v.size() > 0) {
            x = (xterm) v.get(0);
            x.cutMark();
        }
        return v;
    }

    public int getEType() {
        return etype;
    }

    public Vector getAllCond() {
        Vector v = new Vector();
        if (co != null) {
            cond c = co;
            while (c != null) {
                v.add(c);
                c = c.nx;
            }
        }
        if (et != null) {
            el_term e = et;
            while (e != null) {
                if (e.co != null) {
                    cond c = e.co;
                    while (c != null) {
                        v.add(c);
                        c = c.nx;
                    }
                }
                e = e.nx;
            }
        }
        return v;
    }
}
