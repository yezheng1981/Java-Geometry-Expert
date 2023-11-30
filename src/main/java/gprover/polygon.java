package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 6, 2006
 * Time: 10:22:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class polygon extends cclass {
    int lemma;
    int qtype;
    cond co;
    int p[] = new int[10];
    int o;

    polygon nx;

    public polygon() {
        lemma = o = 0;
        co = null;
    }
    public polygon(int t) {
        qtype = t;
        lemma = o = 0;
        co = null;
    }
}
