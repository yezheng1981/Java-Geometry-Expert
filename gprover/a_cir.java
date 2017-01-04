
/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:34:08
 * To change this template use File | Settings | File Templates.
 */                          
package gprover;
public class a_cir extends cclass
{
    int lemma;
    cond co;
    public int no;
    public int o;
    public int []pt;
    public int []d;
    public a_cir nx;
//    private int type;

    public a_cir()
    {
        type = lemma =0;
        co = null;
        no = o = 0;
        pt = new int[MAX_GEO];
        d = new int[MAX_GEO];
        nx = null;

    }
}
