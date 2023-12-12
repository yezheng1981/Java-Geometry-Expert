/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:35:00
 * To change this template use File | Settings | File Templates.
 */
package gprover;

public class cong_seg extends cclass {

    int lemma;
    cond co;
    public int p1;
    public int p2;
    public int p3;
    public int p4;

    public int t1,t2;

    cong_seg nx;

    public cong_seg() {
        type = lemma = 0;
        t1 = t2 = 1;
        co = null;
        nx = null;
    }
}
