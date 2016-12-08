package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2007-5-9
 * Time: 15:30:31
 * To change this template use File | Settings | File Templates.
 */
public class grule {
    public int type;
    public int rx;  // 0. GDD,  1. FULL
    public String name;
    public String head;
    public String discription;
    public String exstring;

    public grule(int t, String t1, String t2, String t3, int tx) {
        type = t;
        head = t1;
        discription = t2;
        exstring = t3;
        if (t1.contains("#")) {
            String[] s = t1.split("#");
            name = s[1];
        }
        rx = tx;
    }

    public boolean isGDDRule() {
        return rx == 0;
    }

    public boolean isFullRule() {
        return rx == 1;
    }
}
