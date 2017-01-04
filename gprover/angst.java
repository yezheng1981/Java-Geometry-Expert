package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Oct 11, 2006
 * Time: 10:32:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class angst extends cclass {
    public int no;
    public l_line[] ln1;
    public l_line[] ln2;
    long[] dep;
    public String sd;
    public angst nx;


    public angst() {
        type = 1;
        no = 0;
        ln1 = new l_line[1000];
        ln2 = new l_line[1000];
        dep = new long[1000];
    }

    public angst(int n) {
        no = 0;
        ln1 = new l_line[n];
        ln2 = new l_line[n];
        dep = new long[n];
    }

    public boolean contain(l_line l1, l_line l2) {
        for (int i = 0; i < no; i++) {
            if (ln1[i] == l1 && ln2[i] == l2 || ln1[i] == l2 && ln2[i] == l1)
                return true;
        }
        return false;
    }

    public int get_dr(l_line l1, l_line l2) {
        for (int i = 0; i < no; i++) {
            if (ln1[i] == l1 && ln2[i] == l2)
                return 1;
            if (ln1[i] == l2 && ln2[i] == l1)
                return -1;
        }
        return 0;
    }

    public boolean addAngle(angles as) {
        boolean r1, r2;
        l_line l1 = as.l1;
        l_line l2 = as.l2;
        l_line l3 = as.l3;
        l_line l4 = as.l4;

        r1 = r2 = false;
        for (int i = 0; i < no; i++) {
            if (ln1[i] == l1 && ln2[i] == l2)
                r1 = true;
            else if (ln1[i] == l2 && ln2[i] == l1) {
                r1 = true;
                l_line t = l3;
                l3 = l4;
                l4 = t;
            }

            if (ln1[i] == l3 && ln2[i] == l4)
                r2 = true;
            else if (ln1[i] == l4 && ln2[i] == l3) {
                l_line t = l1;
                l1 = l2;
                l2 = t;
                r2 = true;
            }
            if (r1 && r2) break;
        }
        if (r1 && r2) return true;
        if (r1) {
            ln1[no] = l3;
            ln2[no] = l4;
            dep[no] = as.dep;
            no++;

        } else if (r2) {
            ln1[no] = l1;
            ln2[no] = l2;
            dep[no] = as.dep;
            no++;
        } else if (no == 0) {
            ln1[no] = l1;
            ln2[no] = l2;
            dep[no] = as.dep;
            no++;
            ln1[no] = l3;
            ln2[no] = l4;
            dep[no] = as.dep;
            no++;

        } else
            return false;
        return true;
    }

    public String toString() {
        return sd;
    }
}
