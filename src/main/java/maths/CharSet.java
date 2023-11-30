package maths;

//import wprover.CMisc;

public class CharSet {
    final private static boolean DEBUG = false;
    private PolyBasic basic = PolyBasic.getInstance();
    private static CharSet charset = new CharSet();
    private static int REDUCE_LEN = 2;

    public static boolean debug() {
        return DEBUG;
    }

    public static CharSet getinstance() {
        return charset;
    }

    public long freemonos() {
        return 0;
    }

    public TPoly charset(TPoly pp) {
        TPoly rm, ch, chend, p, output;
        output = null;
        p = pp;
        rm = p;

        if (rm == null) return pp;
        pp = reduce1(pp);

        while (rm != null) {
            TPoly tp;
            ch = chend = tp = rm;

            int vra = basic.lv(tp.getPoly());
            tp = tp.getNext();
            if (tp == null) {
                output = basic.ppush(rm.getPoly(), output);
                break;
            }
            int v = basic.lv(tp.getPoly());

            while (vra == v) {
                chend = tp;
                tp = tp.getNext();
                if (tp == null)
                    break;
                v = basic.lv(tp.getPoly());
            }
            chend.setNext(null);
            rm = tp;


            if (ch == chend) {
                output = basic.ppush(ch.getPoly(), output);
            } else {
                TPoly poly = null;

                while (ch.getNext() != null) {
                    TMono divor = basic.getMinV(vra, ch);
                    do {
                        TMono out;
                        TMono div = ch.getPoly();
                        if (div == divor) continue;
                        out = basic.prem(div, basic.p_copy(divor));

                        int a = basic.lv(out);
                        if (a == 0) {
                            if (DEBUG) {
                                System.out.println("Condition redundancy---------------------:");
                                basic.print(div);
                                basic.print(divor);
                            }
                        } else if (vra > a)
                            rm = basic.ppush(out, rm);
                        else
                            poly = basic.addpoly(out, poly);
                    } while ((ch = ch.getNext()) != null);
                    if (poly == null) {
                        output = basic.ppush(divor, output);
                        break;
                    } else {
                        poly = basic.addpoly(divor, poly);
                        ch = poly;
                        poly = null;
                    }
                }

            }
        }

//        this.printpoly(output);
        reduce(output);
        TPoly tp = reverse(output);
        if (!cfinished(tp))
            tp = charset(tp);

        TPoly p1 = tp;
        while (p1 != null) {
            TMono m = p1.getPoly();
            basic.factor1(m);
            basic.coefgcd(m);
            p1 = p1.getNext();
        }
        return tp;
    }

    public TPoly reduce1(TPoly poly) // 1, 2, 3, 4, 5
    {
        poly = reverse(poly);
        reduce(poly);
        poly = reverse(poly);
        return poly;
    }

    public void reduce(TPoly poly) {    // n ,n-1,,,,,,, 1.
        TPoly p1 = poly;
        while (p1 != null) {
            TMono m = p1.poly;
            if (basic.plength(m) <= REDUCE_LEN) {
                TPoly tx = poly;
                while (tx != null && tx != p1) {
                    TMono m2 = tx.poly;
                    tx.poly = basic.prem(m2, basic.p_copy(m));
                    tx = tx.next;
                }
            }
            p1 = p1.next;
        }
    }


    public boolean cfinished(TPoly pp) {
        if (pp == null) return true;
        int a = basic.lv(pp.getPoly());
        pp = pp.getNext();
        while (pp != null) {
            int n = basic.lv(pp.getPoly());
            if (a == n)
                return false;
            else {
                a = n;
                pp = pp.getNext();
            }
        }
        return true;
    }

    public void printpoly(TPoly pp) {
        int i = 0;
        while (pp != null) {
            if (pp.getPoly() != null) {
                Integer s = new Integer(i);
                System.out.print("f" + s.toString() + "= ");
                basic.print(pp.getPoly());
                i++;
            }
            pp = pp.getNext();
        }
    }

    public static TPoly reverse(TPoly pp) {
        if (pp == null) return pp;
        TPoly out = null;

        while (pp != null) {
            TPoly p = pp;
            pp = pp.getNext();

            if (out == null) {
                out = p;
                out.setNext(null);
            } else {
                p.setNext(out);
                out = p;
            }
        }
        return out;

    }

    public TPoly pushpoly(TMono p, TPoly pp) {
        TPoly pt;

        pt = new TPoly();
        pt.setNext(pp);
        pt.setPoly(p);
        return pt;
    }

}
