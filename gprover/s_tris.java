package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Oct 23, 2006
 * Time: 10:45:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class s_tris extends cclass {
    static int MAX_TRI = 300;
    public int st = -1;
    public int no;
    public int[] dr;
    public int[] p1;
    public int[] p2;
    public int[] p3;

    s_tris nx;

    public s_tris() {
        type = 0;
        dr = new int[300];
        p1 = new int[300];
        p2 = new int[300];
        p3 = new int[300];
        nx = null;
    }
}
