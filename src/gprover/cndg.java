package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-1-20
 * Time: 19:02:14
 * To change this template use File | Settings | File Templates.
 */
public class cndg {
    final public static int MAX_GEO = 16;

    public Object dep; //dependent construction.
    public Object equ; // equivalent conditions
    public boolean exists = false;

    public int type;
    public int no = -1;
    public int[] p = new int[MAX_GEO];

    String sd;

    public cndg() {
        dep = equ = sd = null;
        type = 0;
    }

    public int getMaxInt() {
        int n = 0;
        for (int i = 0; i <= no; i++) {
            if (n < p[i])
                n = p[i];
        }
        return n;
    }

    public cndg(cndg c1) {
        dep = c1.dep;
        equ = c1.equ;
        type = c1.type;
        no = c1.no;
        sd = c1.sd;
        exists = c1.exists;
        for (int i = 0; i <= no; i++)
            p[i] = c1.p[i];
    }

    public String toString() {
        return sd;
    }

    public boolean contain2(int a, int b) {  // 01, or 23
        if (a == 0 && b == 0)
            return true;
        return p[0] == a && p[1] == b || p[0] == b && p[1] == a
                || p[2] == a && p[3] == b || p[2] == b && p[3] == a;
    }

    public boolean contain(int pt) {
        if (pt == 0)
            return true;

        for (int i = 0; i <= no; i++)
            if (pt == p[i])
                return true;

        return false;
    }

    public boolean redundentPt() {
        for (int i = 0; i <= no; i++) {
            for (int j = i + 1; j <= no; j++)
                if (p[i] == p[j])
                    return true;
        }
        return false;
    }

    public void addAPt(int pt) {
        p[++no] = pt;
    }

    public void addAllPt(int[] p1) {
        for (int i = 0; i < p.length; i++)
            p[i] = 0;
        int i = 0;
        for (i = 0; i < p1.length && i < p.length; i++) {
            p[i] = p1[i];
            if (p[i] == 0) {
                i--;
                break;
            }

        }
        no = i;
    }

    public int getRedundentPt() {
        for (int i = 0; i <= no; i++) {
            for (int j = i + 1; j <= no; j++)
                if (p[i] == p[j])
                    return p[i];
        }
        return 0;
    }

}
