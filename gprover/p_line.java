/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:33:19
 * To change this template use File | Settings | File Templates.
 */
package gprover;

public class p_line extends cclass {
    int lemma;
    cond co;
    public int no;
    public l_line[] ln;

    p_line nx;

    public p_line(l_line l1, l_line l2) {
        this();
        ln[0] = l1;
        ln[1] = l2;
        no = 1;
    }

    public p_line() {
        type = lemma = no = 0;
        co = null;
        ln = new l_line[MAX_GEO];
        nx = null;
    }
}
