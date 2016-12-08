package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 17, 2006
 * Time: 11:17:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class r_line extends cclass {
    int lemma;
    public int no;

    public int o;

    public int[] pt;

    l_line ln;  // the line that depend on...

    r_line nx;


    public r_line() {
        type = lemma = no = 0;
        pt = new int[MAX_GEO];
        nx = null;
    }

    public boolean containPt(int n) {
        if (n == 0) return false;

        for (int i = 0; i < MAX_GEO && pt[i] != 0; i++)
            if (pt[i] == n) return true;
        return false;
    }
}
