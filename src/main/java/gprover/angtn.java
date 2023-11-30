package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 16, 2006
 * Time: 2:21:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class angtn extends cclass {
    int lemma;
    public l_line ln1, ln2, ln3, ln4;
    public int t1, t2;
    cond co;
    angtn nx;

    public angtn(l_line l1, l_line l2, l_line l3, l_line l4) {
        this();
        ln1 = l1;
        ln2 = l2;
        ln3 = l3;
        ln4 = l4;
    }

    public angtn() {
        ln1 = ln2 = ln3 = ln4 = null;
        co = null;
        nx = null;
        t1 = t2 = lemma = 0;

    }
}
