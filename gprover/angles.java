
/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:34:29
 * To change this template use File | Settings | File Templates.
 */
package gprover;

public class angles extends cclass
{
   // int type;
    int lemma;
    cond co;
    int sa;
    public l_line l1,l2, l3, l4;
    angles nx;
    int atp = 0;

    public angles(l_line l1, l_line l2, l_line l3, l_line l4)
    {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
    }
    public angles()
    {
        type = lemma = sa = 0;
        co = null;
        nx = null;
        l1 = l2 = l3 = l4 = null;
    }

}
