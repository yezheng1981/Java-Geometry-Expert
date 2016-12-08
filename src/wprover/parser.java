package wprover;

import maths.TMono;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-8-30
 * Time: 10:10:17
 * To change this template use File | Settings | File Templates.
 */
public class parser {
    private static GeoPoly poly = GeoPoly.getPoly();

    TMono m1;
    String sname;
    String sfunc;
    int id;
    int param;

    public parser(String n, String f, int x) {
        m1 = null;
        sname = n;
        sfunc = f;
        id = 0;
        param = x;
    }

    public TMono parse() {
        byte[] bf = sfunc.getBytes();
        byte[] nm = sname.getBytes();
        id = 0;
        parseterm(true, bf, nm);
        return m1;
    }

    public boolean isNum(byte b) {
        return b >= '0' && b <= '9';
    }

    public boolean isAlpha(byte b) {
        return b >= 'a' && b <= 'z' || b >= 'A' && b <= 'z';
    }

    public void parseterm(boolean first, byte[] bf, byte[] nm) {
        while (true) {
            if (id >= bf.length)
                return;
            while (bf[id] == 32) {
                if (id >= bf.length)
                    return;
                id++;
            }

            if (id >= bf.length)
                return;

            if (!first) {
                if (bf[id] == '+') {
                    id++;
                    m1 = poly.padd(m1, getAterm(bf, nm));
                } else if (bf[id] == '-') {
                    id++;
                    m1 = poly.pdif(m1, getAterm(bf, nm));
                }
            } else {
                first = false;
                m1 = poly.padd(m1, getAterm(bf, nm));
            }
        }
    }

    public void parseBlank(byte[] bf, byte[] nm) {
        while (bf[id] == 32) {
            if (id >= bf.length)
                return;
            id++;
        }
    }

    public TMono getAterm(byte[] bf, byte[] nm) {
        parseBlank(bf, nm);

        int value = 0;
        int coef = 1;
        while (id < bf.length && isNum(bf[id])) {
            value = value * coef + (bf[id] - '0');
            coef *= 10;
            id++;
        }
        if (value == 0)
            value = 1;

        if (id < bf.length && isAlpha(bf[id])) {
            id += nm.length;
        }
        int n = 0;

        if (id < bf.length) {
            if (bf[id] == '^') {
                id++;
                coef = 1;
                while (id < bf.length && isNum(bf[id])) {
                    n = n * coef + (bf[id] - '0');
                    coef *= 10;
                    id++;
                }
            }
        }
        if (n != 0)
            return poly.pth(param, value, n);
        else return
                poly.pth(0, value, 0);
    }

}
