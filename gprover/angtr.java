package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 17, 2006
 * Time: 11:06:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class angtr extends cclass {
    public int v, t1, t2;
    public l_line l1;
    public l_line l2;
    cond co;
    angtr nx;

    public angtr() {
        l1 = l2 = null;
        co = null;
        nx = null;
        v = 0;
    }


    public int get_lpt1() {
        if (t1 != 0) return t1;
        return l_line.get_lpt1(l1, v);
    }

    public int get_lpt2() {
        if (t2 != 0) return t2;
        return l_line.get_lpt1(l2, v);
    }
}
