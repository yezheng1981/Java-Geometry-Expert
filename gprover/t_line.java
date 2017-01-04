/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:33:44
 * To change this template use File | Settings | File Templates.
 */
package gprover;

public class t_line extends cclass {
    int lemma;
    cond co;
    public l_line l1, l2;
    t_line nx;

    public t_line(l_line l1, l_line l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    public t_line() {
        type = lemma = 0;
        co = null;
        l1 = l2 = null;
        nx = null;
    }
}
