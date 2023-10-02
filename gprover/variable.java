package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-4-14
 * Time: 13:31:19
 * To change this template use File | Settings | File Templates.
 */
public class variable {
    int nm;
    char[] p = new char[9];
    public int[] pt = new int[4];
    variable nx;

    String sd = null;

    public variable() {

    }

    public variable(int n, int p1, int p2, int p3, int p4) {
        nm = n;
        pt[0] = p1;
        pt[1] = p2;
        pt[2] = p3;
        pt[3] = p4;
    }

    public void revert() {
        int k = pt[0];
        pt[0] = pt[2];
        pt[2] = k;

        k = pt[1];
        pt[1] = pt[3];
        pt[3] = k;
    }

    public variable(variable v) {
        nm = v.nm;
        pt[0] = v.p[0];
        pt[1] = v.p[1];
        pt[2] = v.p[2];
        pt[3] = v.p[3];
    }

    public String toString() {
        return sd;
    }

    public void setString(String s) {
        sd = s;
    }
}
