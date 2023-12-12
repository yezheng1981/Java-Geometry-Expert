package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 30, 2006
 * Time: 12:40:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class pnode {

    int id;

    int n = 0;
    long[] val = new long[30];
    pnode nx;

    public void add(long x) {
        val[n++] = x;
    }

    public void add(cclass x) {
        val[n++] = x.id;
    }

    public long get(int d) {
        return val[d];
    }
}
