package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-4
 * Time: 11:32:50
 * To change this template use File | Settings | File Templates.
 */
public class xterm {
    public var var;                //   variable.
    long c;                 //   value is an Integer.
    dterm ps;              //  prefix
    xterm p;                //
    String sd;

    public xterm() {
        var = null;
        c = 0;
        ps = null;
        p = null;
    }

    public long getPV() {
        if (ps == null || ps.p == null) return 0;
        return ps.p.c;
    }

    public String toString() {
        return sd;
    }

    public void cutMark() {
        if (sd != null && sd.trim().startsWith("+"))
            sd = sd.trim().substring(1);
    }

    public String getString() {
        if (sd == null) return null;
        String t = sd.trim();
        if (t.startsWith("+"))
            return t.substring(1).trim();
        return t;
    }

    public int getTermNumber() {
        xterm t = this;
        int n = 0;
        while (t != null) {
            dterm d = t.ps;
            if (d == null || d.nx == null) return n;
            t = d.nx.p;
            n++;
        }
        return n;
    }
}
