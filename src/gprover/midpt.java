
/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:31:33
 * To change this template use File | Settings | File Templates.
 */
package gprover;
public class midpt extends cclass
{
//    int type;
    int lemma;
    cond co;
    public int m, a, b;
    midpt nx;
    public midpt()
    {
        type = lemma = m = a = b = 0;
        co = null;
        nx = null;
    }
}
