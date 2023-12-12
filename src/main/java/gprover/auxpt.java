package gprover;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Oct 4, 2006
 * Time: 7:13:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class auxpt {
    String name;
    int type;
    Vector vptlist = new Vector();
    String str;

    public auxpt(int t) {
        type = t;
    }

    public String getConstructedPoint() {
        return vptlist.get(0).toString();
    }

    public int getAux() {
        return type;
    }

    public void addAPt(Pro_point pt) {
        for (int i = 0; i < vptlist.size(); i++)
            if (pt == vptlist.get(i))
                return;
        vptlist.add(pt);
    }

    public int getPtsNo() {
        return vptlist.size();
    }

    public Pro_point getPtsbyNo(int n) {
        return (Pro_point) vptlist.get(n);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < vptlist.size(); i++) {
            Pro_point pt = (Pro_point) vptlist.get(i);
            s += pt.getText();
        }
        return "(A" + type + " ): " + s;
    }
}
