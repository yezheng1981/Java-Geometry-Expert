package maths;


import java.util.Vector;
//import java.util.regex.Pattern;
//import java.math.BigDecimal;
//import java.math.MathContext;
import java.math.BigInteger;
//import java.math.RoundingMode;
import java.nio.charset.Charset;

public class PolyBasic {
    private static int MAXSTR = 100;
    final private static double ZERO = 10E-6;
    private static PolyBasic basic = new PolyBasic();
    private static boolean BB_STOP = false;
    private static boolean RM_SCOEF = true;


    public static PolyBasic getInstance() {
        return basic;
    }

    public static void setbbStop(boolean t) {
        BB_STOP = t;
    }

    public static void setRMCOEF(boolean s) {
        RM_SCOEF = s;
    }

    private TMono pp_plus(TMono p1, TMono p2, boolean es) {

        if (p1 == null) return p2;
        if (p2 == null) return p1;

        TMono poly = null;
        if (p1.x < p2.x || (p1.x == p2.x && p1.deg < p2.deg)) {
            TMono t = p1;
            p1 = p2;
            p2 = t;
        }
        poly = p1;

        if (p1.x > p2.x)//  ||(p1.x == p2.x && p1.deg > p2.deg))     append to the last one.
        {
            while (p1.next != null && p1.deg != 0) p1 = p1.next;

            if (p1.deg != 0) {
                p1.next = new TMono();
                p1.next.x = p1.x;
                p1 = p1.next;
                p1.coef = p2;
                p1.next = null;
            } else {
                p1.coef = pp_plus(p1.coef, p2, true);
                p1.next = null;
            }
            if (p1.coef == null) {
                if (poly == p1)
                    poly = null;
                else {
                    TMono t = poly;
                    while (t != null && t.next != p1)
                        t = t.next;
                    t.next = null;
                }
            }
        } else {
            if (p1.deg > p2.deg) {
                p1.next = pp_plus(p1.next, p2, false);
            } else if ((p1.x == 0)) {
                BigInteger v = p1.val.add(p2.val);
                if (v.compareTo(BigInteger.ZERO) == 0) return null;
                p1.val = v;
            } else {     //p1.deg == p2.deg
                p1.coef = pp_plus(p1.coef, p2.coef, true);
                p1.next = pp_plus(p1.next, p2.next, false);
                if (p1.coef == null) {
                    if (poly == p1)
                        poly = poly.next;
                    else {
                        TMono t = poly;
                        while (t != null && t.next != p1)
                            t = t.next;
                        t.next = p1.next;
                    }
                }

            }
        }


        if (poly != null && poly.coef == null && poly.deg != 0)
            poly = poly.next;

        while (es == true && poly != null && poly.deg == 0 && poly.x != 0)
            poly = poly.coef;

        return (poly);

    }

    private TMono pp_minus(TMono p1, TMono p2) {
        TMono m = pp_plus(p1, cp_times(-1, p2), true);
        return m;
    }

    public TMono cp_times(long c, TMono p1) {
        return cp_times(BigInteger.valueOf(c), p1);
    }

    private TMono cp_times(BigInteger c, TMono p1) {
        if (p1 == null || c.compareTo(BigInteger.ZERO) == 0) return null;
        if (c.compareTo(BigInteger.ONE) == 0) return p1;

        if ((p1.x == 0)) {
            p1.val = p1.val.multiply(c);
            return p1;
        }
        TMono m = p1;
        while (m != null) {
            m.coef = cp_times(c, m.coef);
            m = m.next;
        }
        return p1;
    }

    private TMono pp_times(TMono p1, TMono p2) //(m X n)
    {
        if (p1 == null || p2 == null) return null;
        TMono tp = null;
        if ((p2.x == 0))
            return cp_times(p2.val, p1);

        if ((p1.x == 0))
            return cp_times(p1.val, p2);

        if (p1.x > p2.x) {
            tp = p1;
            p1 = p2;
            p2 = tp;
        }

        TMono poly = null;
        while (p1 != null) {
            tp = p1;
            p1 = p1.next;
            tp.next = null;
            TMono tt;

            if (p1 != null)
                tt = mp_times(tp, p_copy(p2));
            else {
                if (tp.deg == 0 && tp.x != 0)
                    tp = tp.coef;
                tt = mp_times(tp, p2);
            }

            while (tt != null && tt.deg == 0 && tt.x != 0)
                tt = tt.coef;
            while (tt != null && tt.x != 0 && tt.coef == null)
                tt = tt.next;

            poly = pp_plus(poly, tt, true);
        }

        return (poly);
    }

    private TMono mp_times(TMono p1, TMono p2) // p1.x <= p2.x
    {
        TMono poly = p2;

        if (p1 == null || p2 == null) return null;
        if (Int(p1)) return cp_times(p1.val, p2);

        if (p1.x == p2.x) {
            while (p2 != null) {
                p2.deg += p1.deg;
                if (p2.next != null)
                    p2.coef = pp_times(p_copy(p1.coef), p2.coef);
                else
                    p2.coef = pp_times(p1.coef, p2.coef);

                p2 = p2.next;
            }
        } else if (p1.x < p2.x) {
            while (p2 != null) {
                if (p2.next != null)
                    p2.coef = pp_times(p_copy(p1), p2.coef);
                else
                    p2.coef = pp_times(p_copy(p1), p2.coef);
                p2 = p2.next;
            }
        } else {
            System.out.println("Error,must p1.x < p2.x");
        }
        return (poly);
    }

    public boolean check_zero(TMono m) {
        if (m == null) return false;
        if (m.x == 0 && m.value() == 0) return true;

        while (m != null) {
            if (check_zero(m.coef))
                return true;
            m = m.next;
        }
        return false;
    }

    public int deg(TMono p, int x) {
        if (p.x == x) return p.deg;
        if (p.x > x) {
            int d1 = deg(p.coef, x);
            int d2 = deg(p.next, x);
            return d1 > d2 ? d1 : d2;
        }
        return 0;
    }

    public TMono reduce(TMono m, param[] p) {
        if (m == null)
            return null;

        int x = m.x;
        int d = m.deg;
        m = p_copy(m);

        int n = 0;
        for (; n < p.length; n++) {
            param pm = p[n];
            if (pm == null)
                break;
            else if (pm.xindex == x) {
                n--;
                break;
            } else if (pm.xindex > x)
                break;
        }

        for (int i = n; i >= 0; i--) {
            param pm = p[i];
            if (pm != null && pm.m != null)
                m = prem(m, p_copy(pm.m));
        }
        return m;
    }

    // simplify  an tmono to low degree. Note here we don't remove coef.
    public TMono simplify(TMono m, param[] p) {
        if (m == null)
            return null;

        int x = m.x;
        int d = m.deg;
        m = p_copy(m);

        int n = 0;
        for (; n < p.length; n++) {
            param pm = p[n];
            if (pm == null)
                break;
            else if (pm.xindex == x) {
                break;
            } else if (pm.xindex > x)
                break;
        }

        for (int i = n; i >= 0; i--) {
            param pm = p[i];
            if (pm != null && pm.m != null)
                m = prem(m, p_copy(pm.m));
        }
        return m;
    }

    public TMono prem(TMono p1, TMono p2) {
        if (p1 == null)
            return p1;
        if (p2 == null)
            return p1;
        if (p1.x < p2.x)
            return p1;


        TMono result = null;
        if (p1.x == p2.x) {
            TMono m = p_copy(p1.coef);
            TMono n = p_copy(p2.coef);
            result = prem1(p1, p2);
            result = factor_remove(result, m);
            result = factor_remove(result, n);
        } else
            result = prem3(p1, p2);

        coefgcd(result);
        factor1(result);
        return result;
    }

    private int degree(TMono m, int x) {
        if (m == null || m.x < x)
            return 0;
        while (m.x > x)
            m = m.coef;
        if (m != null && m.x == x)
            return m.deg;
        return 0;
    }

    private boolean can_prem3(TMono p1, TMono p2) {
        if (p1 == null || p2 == null)
            return false;
        return prem3(p1, p2, false) == null;
    }

    private TMono prem3(TMono p1, TMono p2) {
        return prem3(p1, p2, true);
    }

    private TMono prem3(TMono p1, TMono p2, boolean r /*do all*/) {   // p1.x > p2.x.
        if (p2 == null)
            return p1;
        int x = p2.x;
        if (x == 0)
            return p1;
        TMono[] mm = new TMono[2];
        getm2(p1, p2, mm);//, x, p2.deg, mm);
        boolean rx = false;

        while (mm[0] != null && mm[1] != null) {
//            int d1 = degree(mm[0], x);
//            int d2 = degree(p2, x);
            if (p1 != null) {
//                if (d1 >= d2) {
//                    int dd = d1 - d2;
                {
//                    TMono l1 = ptimes(p_copy(mm[0].coef), mm[1]);
//                    if (rx) {
//                        print(p1);
//                        print(p2);
//                        print(mm[0]);
//                        print(mm[1]);
//                    }
                    TMono t1 = pp_times(p1, mm[0]);
                    TMono t2 = pp_times(p_copy(p2), mm[1]);
//                    if(rx)
//                    {
//                        print(t1);
//                        print(t2);
//                    }
                    p1 = pp_minus(t1, t2);
                    coefgcd(p1);
//                    if (rx) {
//                        print(p1);
//                        System.out.println("\n");
//                    }
                    if (p1 == null) break;
                }
            }
            if (p1 == null) break;
            mm[0] = mm[1] = null;
            getm2(p1, p2, mm);// x, p2.deg, mm); // mm[0]: coef ,  mm[1]. p.x
            if (!r && mm[1] != null && mm[1].x < p1.x)
                return p1;

        }
        return p1;
    }

    private void getm2(TMono p1, TMono p2, TMono[] mm) {// int x, int deg, TMono[] mm) {
        if (p1 == null || p2 == null || p1.x < p2.x || p1.x == p2.x && p1.deg < p2.deg)
            return;

        while (p1 != null) {
            if (p1.x < p2.x || p1.x == p2.x && p1.deg < p2.deg) return;

            if (p1.x == p2.x && p1.deg >= p2.deg) {
                mm[0] = pth(0, 1, 0);
                mm[1] = pth(0, 1, 0);
                get_mm(p1, p2, mm);
                int dd = p1.deg - p2.deg;
                mm[0] = p_copy(p2.coef);
                mm[1] = p_copy(p1.coef);
                if (dd > 0)
                    mm[1] = ptimes(mm[1], pth(p1.x, 1, dd));
                return;
            } else {
                getm2(p1.coef, p2, mm);
                if (mm[0] != null && mm[1] != null) {
                    mm[1] = ptimes(mm[1], pth(p1.x, 1, p1.deg));
                    return;
                }
            }

            p1 = p1.next;
            if (p1 != null && p1.deg == 0)
                p1 = p1.coef;
        }

//        if (p.x > x) {
//            while (p != null) {
//                getm2(p.coef, x, deg, mm);
//                if (mm[0] != null) {
//                    TMono m = pth(p.x, 1, p.deg);
//                    mm[1] = ptimes(mm[1], m);
//                    return;
//                }
//                p = p.next;
//            }
//        } else if (p.deg >= deg) {
//            mm[0] = p;
//            mm[1] = pth(0, 1, 0);
//            return;
//        }
    }

    private TMono prem1(TMono p1, TMono p2) {

        if (p1 == null)
            return null;

        TMono result = null;
        if (p1.x < p2.x)
            result = p1;
        else if (p1.x > p2.x) {
            result = p1;
            while (p1 != null) {
                TMono m1 = prem1(p1.coef, (p2));
                p1.coef = m1;
                p1 = p1.next;
            }
        } else if (p1.x == p2.x) {

            if (p1.deg < p2.deg) {
                TMono t = p1;
                p1 = p_copy(p2);
                p2 = t;
            } else p2 = p_copy(p2);

            if (p1.deg >= p2.deg) {
                int x = p2.x;

                while (p1 != null && p1.x >= x) {
                    int d1 = deg(p1);
                    int d2 = deg(p2);
                    if (d1 < d2) {
                        /*TMono m = p1;                   //comment by ye. 5.31.
                        p1 = p2;
                        p2 = m;
                        int t = d1;
                        d1 = d2;
                        d2 = t;*/
                        break;
                    }
                    if (d1 >= d2) {
                        int dd = d1 - d2;
                        TMono tp;
                        if (dd > 0) {
                            tp = pth(x, 1, dd);
                            tp = pp_times(tp, p_copy(p2));
                        } else
                            tp = p_copy(p2);
                        p1 = pp_minus(pp_times(p1.next, tp.coef), pp_times(p1.coef, tp.next));
                        coefgcd(p1);
                        if (p1 == null) break;
                    }
                }
                result = p1;
            }
        }
        while (result != null && result.x != 0 && result.coef == null)
            result = result.next;

        while (result != null && result.deg == 0 && result.x != 0)
            result = result.coef;

//        print(result);
        return result;
    }


    public void div_factor1(TMono m, int x, int n) {
        if (x > m.x) return;

        if (x == m.x) {
            while (m != null && m.deg != 0) {
                m.deg -= n;
                if (m.deg < 0) {
                    int k = 0;
                }
                m = m.next;
            }
        } else {
            while (m != null) {
                TMono mx = m.coef;
                if (mx != null && mx.x != 0 && mx.deg != 0) {
                    div_factor1(mx, x, n);
                    if (mx.deg == 0)
                        m.coef = mx.coef;
                }
                m = m.next;
                if (m != null && m.x == 0)
                    m = m.coef;
            }
        }

    }

    public void factor2(TMono m1) {
        TMono m = this.get_factor2(m1);
        if (m == null)
            return;
        while (m != null) {
            if (m.x != 0) {
                this.div_factor1(m1, m.x, m.deg);
            }
            m = m.coef;
        }
    }

    public TMono get_factor2(TMono m) {
        if (m == null)
            return null;

        TMono mx = null;
        TMono m1 = m;
        long n = 0;
        while (m != null) {
            if (m.x != 0)
                n = factor_contain(m.x, m.deg, m1);
            if (n != 0) {
                if (mx == null)
                    mx = new TMono(m.x, 1, (int) n);
                else
                    mx = this.pp_times(mx, new TMono(m.x, 1, (int) n));
            }
            m = m.coef;
        }
        return mx;
    }

    public void factor1(TMono m1) {
        if (!RM_SCOEF)
            return;

        TMono m = this.get_factor1(m1);
        if (m == null)
            return;
        while (m != null) {
            if (m.x != 0) {
                this.div_factor1(m1, m.x, m.deg);
            }
            m = m.coef;
        }
    }

    public TMono get_factor1(TMono m) {
        if (m == null)
            return null;

        TMono mx = null;
        TMono m1 = m;
        m = m.coef;
        long n = 0;
        while (m != null) {
            if (m.x != 0)
                n = factor_contain(m.x, m.deg, m1);
            if (n != 0) {
                if (mx == null)
                    mx = new TMono(m.x, 1, (int) n);
                else
                    mx = this.pp_times(mx, new TMono(m.x, 1, (int) n));
            }
            m = m.coef;
        }
        return mx;
    }

    private boolean m_contain(long x, long d, TMono m) {
        if (m == null || x > m.x || x == m.x && d > m.deg)
            return false;
        if (m.x == x && m.deg <= d)
            return true;

        while (m != null) {
            if (m_contain(x, d, m.coef))
                return true;
            m = m.next;
        }
        return false;
    }

    private long factor_contain(long x, long n, TMono m) {
        if (m == null)
            return n;

        if (x > m.x) {
            return 0;
        } else if (x == m.x) {
            while (m.next != null)
                m = m.next;
            if (m.x == 0 && m.coef != null)
                return 0;
            else if (m.deg == 0 && m.coef == null)
                return n;
            else
                return Math.min(m.deg, n);
        } else if (x < m.x) {
            while (m != null) {
                long t = factor_contain(x, n, m.coef);
                if (t == 0)
                    return 0;
                else if (t < n)
                    n = t;
                m = m.next;
            }
        }
        return n;
    }

    public TMono factor_remove(TMono p1, TMono p2) {  // p1 ,p2 be destryoed.
        if (p1 == null || p2 == null || plength(p1) > 1000)
            return p1;

        if (p1.x == p2.x)
            return p1;

        if (plength(p2) <= 1)
            return p1;

        if (Int(p1) || Int(p2)) return p1;
        coefgcd(p2);
        factor1(p1);
        factor1(p2);
        boolean r = false;
        if (r) {
            print(p1);
            print(p2);
        }

        if (can_prem3(p_copy(p1), p2)) {

            if (CharSet.debug()) {
                System.out.println("p1 can be factored.");
                System.out.print("p1 = ");
                print(p1);
                System.out.print("p2 = ");
                print(p2);
            }
            TMono tp2 = p_copy(p2);
            TMono tp = p_copy(p1);
            TMono m = div((p1), (p2));
            TMono rm = this.pdif(tp, ptimes(tp2, p_copy(m)));
            if (rm != null) {

                if (CharSet.debug()) {
                    System.out.print("***********rm = ");
                    print(rm);
                }
            }
            if (CharSet.debug()) {
                System.out.print("result = ");
                print(m);
            }
            return m;
        }
        return p1;
    }


    TMono div(TMono m, TMono d) {
        if (m == null || d == null)
            return m;
        if (m.x < d.x)
            return null;
        if (m.x == d.x && m.deg < d.deg)
            return null;

        if (m.x == 0 && d.x == 0) {
            BigInteger n = m.val.divide(d.val);
            return pth(0, n, 0);
        }

        TMono result = null;

        if (m.x > d.x) {

            result = null;
            while (m != null) {
                TMono t = div(m.coef, d);
                if (m.deg != 0)
                    t = ptimes(t, pth(m.x, 1, m.deg));
                result = padd(result, t);
                m = m.next;
            }

        } else // m.x == d.x;
        {
            int x = m.x;
            while (m != null) {
                int dd = m.deg - d.deg;
                if (dd < 0)
                    return null;
                TMono m1 = div(m.coef, d.coef);
                if (m1 == null)
                    return null;         //failed.

                m1 = ptimes(m1, pth(x, 1, dd));
                result = padd(result, p_copy(m1));
                TMono mx = d.next;
                if (mx != null && mx.x != 0 && mx.deg == 0)
                    mx = mx.coef;

                m = pdif(m.next, ptimes((m1), p_copy(d.next)));
            }
        }
        while (result != null && result.x != 0 && result.coef == null)
            result = result.next;

        while (result != null && result.deg == 0 && result.x != 0)
            result = result.coef;
        return result;
    }

    public TMono p_copy(TMono p) {
        if (p == null) return null;

        TMono p1 = new TMono();
        p1.x = p.x;
        p1.deg = p.deg;
        p1.val = p.val;
        p1.coef = p_copy(p.coef);
        p1.next = p_copy(p.next);
        return p1;

    }


    private int pp_compare(TMono p1, TMono p2) {
        if (p1 == null && p2 == null)
            return 0;
        if (p1 == null && p2 != null)
            return -1;
        if (p1 != null && p2 == null)
            return 1;

        if (p1.x < p2.x || (p1.x == p2.x && p1.deg < p2.deg))
            return -1;
        if (p1.x > p2.x || (p1.x == p2.x && p1.deg > p2.deg))
            return 1;
        int c = pp_compare(p1.coef, p2.coef);
        if (c != 0)
            return c;
        return pp_compare(p1.next, p2.next);
    }

    public void ppush(TMono m, Vector v) {
        if (m == null) return;

        for (int i = 0; i < v.size(); i++) {
            TMono m1 = (TMono) v.get(i);
            int n = (pp_compare2(m, m1));
            if (n > 0) {
                v.add(i, m);
                return;
            }
        }
        v.add(m);
    }

    private int pp_compare2(TMono p1, TMono p2) {
        if (p1 == null && p2 == null)
            return 0;
        if (p1 == null && p2 != null)
            return -1;
        if (p1 != null && p2 == null)
            return 1;
        if (p1.x == 0 && p2.x == 0)
            return 0;

        int x1 = p1.x;
        int x2 = p2.x;

        if (x1 == 0 && x2 != 0)
            return -1;
        if (x1 != 0 && x2 == 0)
            return 1;


        if (p1.x > p2.x || p1.x == p2.x && p1.deg > p2.deg)
            return 1;
        if (p1.x < p2.x || p1.x == p2.x && p1.deg < p2.deg)
            return -1;
        int n = pp_compare2(p1.coef, p2.coef);
        if (n == 0)
            n = pp_compare2(p1.next, p2.next);
        return n;
    }

    private int pp_compare1(TMono p1, TMono p2) {
        if (p1 == null && p2 == null)
            return 0;
        if (p1 == null && p2 != null)
            return -1;
        if (p1 != null && p2 == null)
            return 1;
        int x = Math.max(p1.x, p2.x) + 1;
        while (x != 0) {
            int x1 = getNextX(x, -1, p1);
            int x2 = getNextX(x, -1, p2);
            if (x1 > x2)
                return 1;
            else if (x1 < x2)
                return -1;
            else {
                int d1 = this.getMaxDeg(x1, 1000, -1, p1);
                int d2 = this.getMaxDeg(x2, 1000, -1, p2);
                if (d1 > d2)
                    return 1;
                else if (d1 < d2)
                    return -1;
                else {
                    int d = d1;
                    while (d != 0) {
                        d1 = this.getMaxDeg(x1, d, -1, p1);
                        d2 = this.getMaxDeg(x2, d, -1, p2);
                        if (d1 > d2)
                            return 1;
                        else if (d1 < d2)
                            return -1;
                        d--;
                    }
                }
                x = x1;
            }
        }
        return 1;
    }

    private int getNextX(int x, int x1, TMono m) { //  x1 < x .
        if (m == null) return x1;
        if (m.x <= x1) return x1;
        while (m != null) {
            if (m.x <= x1) return x1;

            if (m.x < x && m.x > x1)
                x1 = m.x;
            x1 = getNextX(x, x1, m.coef);
            m = m.next;
            if (m == null)
                break;
            if (m.deg == 0)
                m = m.coef;
        }
        return x1;
    }

    private int getMaxDeg(int x, int dmax, int d, TMono m) {  // dd < d , dd > d1; return dd;
        if (m == null) return d;
        if (m.x < x) return d;

        while (m != null) {
            if (m.x < x) return d;

            if (m.x == x && m.deg > d && m.deg < dmax)
                d = m.deg;
            d = getMaxDeg(x, dmax, d, m.coef);
            m = m.next;
            if (m == null)
                break;

            if (m.deg == 0)
                m = m.coef;
        }
        return d;
    }

    private boolean Int(TMono p) {
        if (p == null)
            return false;

        if (p.x == 0)
            return true;
        if (p.deg == 0)
            return Int(p.coef);
        return false;
    }

    public void dprint(TMono p, int dx) {
        upValueTM(p, -dx);
        String s = getExpandedPrint(p);
        System.out.println(s);
//        print(p);
        upValueTM(p, dx);
    }

    public void print(TMono p) {
        if (p == null)
            return;

        int v = this.lv(p);
        int d = this.deg(p);

        System.out.print(String_p_print(p, false, true, true));

//        p_print(p, false, true);
        System.out.print("\n");
    }

    public void sprint(TMono p) {
        p_print(p, false, true);
    }

    private void p_print(TMono p, boolean ce, boolean first) { // coefficent ?      print "+" of first ?
        if (p == null) return;
        if (p.next == null) ce = false;

        if (ce) {
            if (first)
                System.out.print("(");
            else
                System.out.print(" + (");
            m_print(p, true);
            p = p.next;
            while (p != null) {
                m_print(p, false);
                p = p.next;
            }
            System.out.print(")");
        } else if (!first) {
            while (p != null) {
                m_print(p, false);
                p = p.next;
            }
        } else {
            m_print(p, true);
            p = p.next;
            while (p != null) {
                m_print(p, false);
                p = p.next;
            }
        }
    }

    private void m_print(TMono p, boolean first) {
        if (p.x == 0) {
            if (first != true) {
                if (p.value() > 0)
                    System.out.print(" + ");
                else
                    System.out.print(" - ");
                long t = Math.abs(p.value());
                if (t != 1)
                    System.out.print(t);
            } else {
                if (p.value() != 1)
                    System.out.print(p.value());
            }
        } else if (p.deg == 0)
            p_print(p.coef, false, first);
        else {
            p_print(p.coef, true, first);
            if (p.coef == null)
                System.out.print("0");
            if (p.x >= 0) {
                if (p.deg != 1)
                    System.out.print("x" + p.x + "^" + p.deg);
                else
                    System.out.print("x" + p.x);
            } else {
                if (p.deg != 1)
                    System.out.print("u" + (-p.x) + "^" + p.deg);
                else
                    System.out.print("u" + (-p.x));

            }
        }
    }

    public TMono pth(int x, int c, int d) {
        return new TMono(x, c, d);
    }

    public TMono pth(int x, BigInteger c, int d) {
        return new TMono(x, c, d);
    }

    public int deg(TMono p) {
        if (p == null) {
            int k = 0;
        }
        return p.deg;
    }

    public int lv(TMono p) {
        if (p == null) return 0;
        return p.x;
    }

    public TMono pzero() {
        return null;
    }

    public int plength(TMono m) {
        if (m == null) return 0;

        if (Int(m))
            return 1;
        else {
            return plength(m.coef) + plength(m.next);
        }
    }

    boolean pzerom(TMono m) {
        if (m == null)
            return true;
        return (!pzerop(m.coef) && m.next == null);
    }

    public boolean pzerop(TMono m) {
        if (m == null) return true;

        if (Int(m))
            return m.value() == 0;
        return pzerop(m.coef) && pzerop(m.next);
    }


    TPoly addpoly(TMono t, TPoly p) {
        TPoly poly = new TPoly();
        poly.setNext(p);
        poly.setPoly(t);

        return poly;
    }

    TPoly addPolytoList(TPoly pl, TPoly pp) {
        if (pl == null) return pp;

        while (pl != null) {
            pp = ppush(pl.getPoly(), pp);
            pl = pl.getNext();
        }
        return pp;
    }

    public TPoly ppush(TMono t, TPoly pp) {  //  n, n-1,,,,,,1.
        if (t == null)
            return pp;

        int vra = this.lv(t);
        TPoly poly = new TPoly();
        poly.next = null;
        poly.poly = t;

        if (pp == null)
            return poly;

        TPoly former = null;
        TPoly p = pp;

        while (p != null) {
            int lee = this.lv(p.getPoly());
            if (lee > vra) {
                former = p;
                p = p.getNext();
            } else
                break;
        }
        if (p == null || this.lv(p.getPoly()) < vra) {
            poly.setNext(p);
            if (former == null)
                return poly;
            else {
                former.setNext(poly);
                return pp;
            }
        }
        //else ==
        while (p != null) {
            if (pp_compare(p.getPoly(), poly.getPoly()) < 0) {
                if (former == null) {
                    poly.setNext(p);
                    return poly;
                } else {
                    former.setNext(poly);
                    poly.setNext(p);
                    return pp;
                }
            } else {
                former = p;
                p = p.getNext();
            }
        }
        if (former == null) {
            poly.setNext(p);
            return poly;
        }
        former.setNext(poly);
        return pp;
    }

    double calpoly(TMono m, param[] p) {
        if (m == null || p == null)
            return 0.0;

        if (Int(m))
            return (double) m.value();
        double r = 0.0;

        while (m != null) {
            double v = calpoly(m.coef, p);
            int id = m.x - 1;
            if (id < 0 || id >= p.length || p[m.x - 1] == null) return 0.0;
            r += Math.pow(p[m.x - 1].value, m.deg) * v;
            m = m.next;
        }
        return r;
    }


    public double[] calculv(TMono mm, param[] p) {
        int x, d;
        double[] result = null;
        if (mm == null)
            return result;

        x = lv(mm);
        d = deg(mm, x);


        if (d == 1) {
            double val = calpoly(mm.coef, p);
            if (ZERO(val))
                return new double[0];

            result = new double[1];
            double v1 = calpoly(mm.next, p);
            val = (-1) * v1 / val;
            result[0] = val;
            return result;
        } else if (d == 2) {

            TMono a1 = mm.coef;
            TMono b1;
            mm = mm.next;
            if (mm != null && deg(mm) == 1) {
                b1 = mm.coef;
                mm = mm.next;
            } else
                b1 = null;

            TMono b2;
            if (mm != null && deg(mm) == 0)
                b2 = mm.coef;
            else
                b2 = null;

            double aa = calpoly(a1, p);
            double bb1 = calpoly(b1, p);
            double bb2 = calpoly(b2, p);
            if (ZERO(aa))
                return new double[0];

            return poly_solve_quadratic(aa, bb1, bb2);

        } else if (d == 3 || d == 4) {
            TMono a1, b1, c1, d1, e1;
            a1 = b1 = c1 = d1 = e1 = null;
            while (mm != null) {
                switch (deg(mm)) {
                    case 0:
                        d1 = mm.coef;
                        break;
                    case 1:
                        c1 = mm.coef;
                        break;
                    case 2:
                        b1 = mm.coef;
                        break;
                    case 3:
                        a1 = mm.coef;
                        break;
                    case 4:
                        e1 = mm.coef;
                        break;
                    default:
                        return null;
                }
                mm = mm.next;
            }
            double aa = calpoly(a1, p);
            double bb = calpoly(b1, p);
            double cc = calpoly(c1, p);
            double dd = calpoly(d1, p);
            double ee = calpoly(e1, p);
            double[] r = null;

            if (d == 3 && aa != 0.0)
                r = poly_solve_cubic(1, bb / aa, cc / aa, dd / aa);
            else if (d == 4 && ee != 0)
                r = poly_solve_quartic(aa / ee, bb / ee, cc / ee, dd / ee);
            return r;

        }
        return null;
    }

    public double[] calculv_2v(TMono mm, param[] p) {
        if (mm.next != null)
            return this.calculv(mm.next.coef, p);
        else
            return null;

    }
//    public double[] calculate_onlinex(TMono mm, param[] p, int dx, int dy) {
//        CLine ln = this.

//    }

    public double[] calculate_online(TMono mm, param[] p, int dx, int dy) {
        if (mm.deg != 1 && mm.x != dy) return null;

        double a = calpoly(mm.coef, p);
        double c = 0.0;
        double b = 0.0;

        if (mm.deg != 1 && mm.x != dx) return null;


        if (mm.next != null) {
            TMono m1 = mm.next.coef;
            if (m1.x != dx)
                return null;

            b = calpoly(m1.coef, p);
            if (m1.next != null) {
                m1 = m1.next.coef;
                c = calpoly(m1, p);
            }
        } else
            return null;

        double md = b * b + a * a;
        double x = p[dx - 1].value;
        double y = p[dy - 1].value;

        if (Math.abs(md) < ZERO)
            return null;
        double[] result = new double[2];

        result[0] = (a * a * x - a * b * y - b * c) / md;
        result[1] = (b * b * y - a * b * x - a * c) / md;
        return result;
    }

    public double[] calculate_oncr(TMono mm, param[] p, int dx, int dy) {

        if (mm.deg != 2 && mm.x != dy) return null;
        double b2 = calpoly(mm.coef, p);
        mm = mm.next;
        double b1 = 0.0;
        if (mm != null && mm.deg == 1 && mm.x == dy) {
            b1 = calpoly(mm.coef, p);
            mm = mm.next;
        }
        if (mm == null)
            return null;

        mm = mm.coef;
        if (mm.deg != 2 && mm.x != dx) return null;
        double a2 = calpoly(mm.coef, p);
        if (Math.abs(a2) < ZERO || Math.abs(b2) < ZERO || Math.abs(a2 - b2) > ZERO) return null;

        mm = mm.next;
        double a1 = 0.0;
        if (mm != null && mm.deg == 1 && mm.x == dx) {
            a1 = calpoly(mm.coef, p);
            mm = mm.next;
        }
        double c = 0.0;
        if (mm != null) {
            mm = mm.coef;
            c = calpoly(mm, p);
        }

        double a = a1 / a2;
        double b = b1 / a2;
        c = c / a2;
        double x = p[dx - 1].value;
        double y = p[dy - 1].value;

        double yd = y + b / 2;
        double xd = x + a / 2;
        double r = Math.sqrt(a * a / 4 + b * b / 4 - c);
        double ln = Math.sqrt(xd * xd + yd * yd);
        double[] result = new double[2];
        result[0] = -a / 2 + xd * r / ln;
        result[1] = -b / 2 + yd * r / ln;
        return result;
    }

    public double[] calculv2poly(TMono mm1, TMono mm2, param[] p)     //from two poly
    {
        int x, d;
        double[] result;

        TMono a1, b1, c1, m1;

        x = lv(mm1);
        if (deg(mm1, x) < deg(mm2, x)) {
            TMono m = mm1;
            mm1 = mm2;
            mm2 = m;
        }

        m1 = mm1;
        d = deg(m1, x);

        if (d == 2) {
            a1 = m1.coef;
            m1 = m1.next;
            if (deg(m1) == 1) {
                b1 = m1.coef;
                m1 = m1.next;
            } else
                b1 = null;

            if (m1 != null)
                c1 = m1.coef;
            else
                c1 = null;


            double ra1 = calpoly(a1, p);
            double rb1 = calpoly(b1, p);
            double rc1 = calpoly(c1, p);
            double dl = rb1 * rb1 - 4 * ra1 * rc1;


            if (Math.abs(dl) < ZERO) {
                result = new double[1];
                result[0] = ((-1) * rb1) / (2 * ra1);
                return result;
            }
            if (dl < 0) return null;
            dl = Math.sqrt(dl);
            if (Math.abs(ra1) < ZERO) return null;

            result = new double[2];
            result[0] = ((-1) * rb1 + dl) / (2 * ra1);
            result[1] = ((-1) * rb1 - dl) / (2 * ra1);
            return result;
        }


        result = calculv(mm1, p);
        if (result == null || result.length == 0)
            result = calculv(mm2, p);
        if (result == null || result.length == 0) {
//            System.out.println("parell two line");
            return null;
        }
        return result;


    }

    public TMono pRtimes(TMono p1, TMono p2) {
        return pp_times(p1, p2);
    }

    public TMono pQtimer(TMono t1, TMono t2, TMono t3, TMono t4) {
        return this.pp_times(p_copy(t1), pp_times(p_copy(t2), pp_times(p_copy(t3), p_copy(t4))));
    }

    public TMono padd(TMono p1, TMono p2) { //add
        TMono m = (pp_plus(p1, p2, true));
        return m;
    }

    public TMono pdif(TMono p1, TMono p2) {//minus
        TMono m = (pp_minus(p1, p2));
        return m;

    }

    TMono redundancy(TMono m) {
        return m;
    }

    boolean isZero(TMono m) {
        if (m == null) return true;
        if (Int(m)) {
            if (m.value() == 0)
                return true;
            else
                return false;
        }

        if (m.x != 0 && m.coef == null) return isZero(m.next);
        return false;
    }

    public TMono pcopy(TMono p) {
        return p_copy(p);
    }

    public TMono ptimes(TMono p1, TMono p2) {
        return pp_times(p1, p2);
    }

    public TMono pctimes(TMono p, long c) {
        return cp_times(BigInteger.valueOf(c), p);
    }

    void pr(TMono m) {
        print(m);
    }

    public void printpoly(TMono m) {
        print(m);
    }

    TMono getMinV(int x, TPoly p) {
        TMono poly = null;
        int exp = 0;
        while (p != null) {
            TMono m = p.getPoly();
            if (m == null || m.x != x) {
                p = p.getNext();
                continue;
            }

            int e = m.deg;
            if ((e > 0) && ((exp == 0) || (e < exp))) {
                exp = e;
                poly = p.getPoly();
            }
            p = p.getNext();
        }
        return poly;

    }


    public TPoly OptimizePoly(TPoly poly) {
        TPoly t = poly;
        while (poly != null) {
            TMono m = poly.getPoly();
            m = opt(m);
            poly.setPoly(m);
            poly = poly.getNext();
        }
        return t;
    }

    TMono opt(TMono m) {
        if (m == null) return null;

        if (Int(m)) return m;

        if (m.x <= 3 && m.x > 0)
            return null;

        m.coef = opt(m.coef);
        m.next = opt(m.next);

        if (m.coef == null && m.deg != 0) m = m.next;
        if (isZero(m)) return null;

        return m;
    }


    public String printHead(TMono m) {
        if (m == null)
            return "0";
        int v = this.lv(m);
        int d = this.deg(m);
        if (d != 1)
            return ("x" + v + "^" + d);
        else
            return "x" + v;
    }

    public String printSPoly(TMono m) {
        return printSPoly(m, MAXSTR);
    }


    public String printNPoly(TMono m) {
        if (m == null)
            return "";

        String s = String_p_print(m, false, true, true);
        if (s.length() > MAXSTR)
            return s.substring(0, MAXSTR) + ".... != 0";
        else s += " !=0";
        return s;
    }

    public String printNPoly(TMono m1, TMono m2) {
        int n1 = plength(m1);
        int n2 = plength(m2);
        String s1 = String_p_print(m1, false, true, true);
        String s2 = String_p_print(m2, false, true, true);
        if (n1 > 1)
            s1 = "(" + s1 + ")";
        if (n2 > 1)
            s2 = " (" + s2 + ")";
        return s1 + s2 + " != 0";
    }

    public String printSPoly(TMono m, int n) {
        if (m == null)
            return "0";

        String s = String_p_print(m, false, true, true);
        if (s.length() > n)
            return s.substring(0, n) + ".... = 0";
        else s += " =0";
        return s;
    }

    public String printMaxstrPoly(TMono m) {
        int n = MAXSTR;
        if (m == null)
            return "0";

        String s = String_p_print(m, false, true, true);
        if (s.length() > n)
            return s.substring(0, n) + "....0";
        return s;
    }

    public String printSPoly1(TMono m, int n) {
        if (m == null)
            return "";

        String s = StringPrint(m);
        if (s.length() > n)
            return s.substring(0, n) + "....";

        return s;
    }

    public String StringPrint(TMono p) {
        StringBuffer buffer = new StringBuffer();
        String_p_print(p, true, buffer);
        return buffer.toString();
    }

    public void String_p_print(TMono p, boolean nn, StringBuffer buffer) {
        if (p == null) return;
        while (p != null) {
            if (String_mprint(p.coef, buffer))
                buffer.insert(0, '+');
            buffer.append("x" + p.x + "" + p.deg);
            if (p.deg == 0)
                p = p.coef;
            else p = p.next;
        }
    }

    public String String_p_print(TMono p, boolean ce, boolean first, boolean nn) {
        if (p == null) return "";
        if (p.next == null) ce = false;
        String s = "";

        if (ce) {
            if (first)
                s += ("(");
            else
                s += (" + (");
            s += (String_m_print(p, true, nn));
            p = p.next;
            while (p != null) {
                if (s.length() > MAXSTR)
                    return s;
                s += String_m_print(p, false, true);
                p = p.next;
            }
            s += (")");
        } else if (!first) {
            while (p != null) {
                if (s.length() > MAXSTR)
                    return s;
                s += String_m_print(p, false, nn);
                p = p.next;
            }
        } else {
            s += String_m_print(p, true, nn);
            p = p.next;
            while (p != null) {
                if (s.length() > MAXSTR)
                    return s;
                s += String_m_print(p, false, nn);
                p = p.next;
            }
        }
        return s;
    }


    public boolean String_mprint(TMono m, StringBuffer buffer) {
        boolean br = false;
        if (m == null) return br;

        if (m.next != null)
            br = true;
        if (br)
            buffer.append("(");
        while (m != null) {
            String_mprint(m.coef, buffer);
            if (String_mprint(m.coef, buffer))
                buffer.insert(0, '+');
            buffer.append(m.x + "" + m.deg);
            if (m.deg == 0)
                m = m.coef;
            else m = m.next;
        }
        if (br)
            buffer.append(")");
        return br;
    }

    public String getExpandedPrint(TMono p) {
        String r = ep_print(p, "", true);
        if (r != null && (r.endsWith("-") || r.endsWith("+")))
            return r + "1";
        return r;
    }

    private String ep_print(TMono p, String s, boolean f) {
        String st = "";
        while (p != null) {
            if (p.next == null && p.deg == 0 && p.x != 0)
                p = p.coef;
            if (p == null)
                break;

            st += eprint(p, s, f);
            f = false;
            if (p.next == null && p.deg == 0)
                p = p.coef;
            else
                p = p.next;
        }
        return st;
    }

    private String eprint(TMono p, String s, boolean f) {
        if (p == null)
            return "";
        if (p.x == 0) // int value;
        {
            if (p.value() == 1) {
                if (f)
                    return s;
                else return "+" + s;
            } else if (p.value() == -1)
                return "-" + s;
            else {
                if (f || p.value() < 0)
                    return p.value() + "*" + s;
                else
                    return "+" + p.value() + "*" + s;
            }

        } else if (p.deg == 0) {
            return ep_print(p.coef, s, f);
        } else {
            String n = "";
            if (p.x > 0) {
                if (p.deg > 1)
                    n = "x" + p.x + "^" + p.deg;
                else
                    n = "x" + p.x;
            } else if (p.x < 0) {
                if (p.deg > 1)
                    n = "u" + (-p.x) + "^" + p.deg;
                else
                    n = "u" + (-p.x);
            }
            if (s.length() == 0)
                s = n;
            else s = n + "*" + s;
            return ep_print(p.coef, s, f);
        }
    }

    public String getAllPrinted(TMono p, boolean b) {
        int n = MAXSTR;
        MAXSTR = 1000000;
        String s = "";
        boolean f = true;
        while (p != null) {
            if (p.deg != 0) {
                if (f)
                    s += String_m_print(p, f, true);
                else {
                    if (b)
                        s += "\n" + String_m_print(p, f, true);
                    else
                        s += String_m_print(p, f, true);
                }
            }
            if (f)
                f = false;

            if (p.next == null && p.deg == 0)
                p = p.coef;
            else
                p = p.next;
        }
        MAXSTR = n;
        if (b)
            return s + "\n = 0";
        else return s;
    }

    public String getAllPrinted(TMono p) {
        return getAllPrinted(p, true);
    }


    private String String_m_print(TMono p, boolean first, boolean nn) {


        String s = new String();

        if (p.x == 0) {
            if (nn) {
                long t = p.value();
                if (t > 0)
                    s += "+" + t;
                else s += t;
            } else if (first != true) {
                if (p.value() > 0)
                    s += (" + ");
                else
                    s += (" - ");
                long t = Math.abs(p.value());
                if (t != 1)
                    s += (t);
            } else {
                long t = p.value();
                if (t == -1)
                    s += "-";
                else if (t != 1)
                    s += (t);
            }
        } else if (p.deg == 0) {
            s += String_p_print(p.coef, false, first, nn);
        } else {
            s += String_p_print(p.coef, true, first, false);
            if (p.x >= 0) {
                if (p.deg != 1)
                    s += ("x" + p.x + "^" + p.deg);
                else
                    s += ("x" + p.x);
            } else {
                if (p.deg != 1)
                    s += ("u" + (-p.x) + "^" + p.deg);
                else
                    s += ("u" + (-p.x));
            }
        }
        return s;
    }

    public BigInteger coefgcd(TMono p) {
        if (p == null) return BigInteger.ONE;

        BigInteger c = coefgcd(p, BigInteger.ZERO);

        TMono m = p;
        while (m != null && m.x != 0)
            m = m.coef;
        if (m == null)
            return c;

        if (m.val.compareTo(BigInteger.ZERO) < 0)
            c = c.negate();

        if (c.compareTo(BigInteger.ONE) != 0)
            coef_div(p, c);
        return c;
    }

    private boolean coef_div(TMono m, BigInteger c) {
        if (m == null) return true;
        if (m.x == 0) {
            m.val = m.val.divide(c);
            return true;
        } else {
            if (coef_div(m.coef, c))
                return coef_div(m.next, c);
            return false;
        }

    }

    BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    private long gcd(long a, long b) {
        long t;

        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private BigInteger coefgcd(TMono p, BigInteger c) {

        if (p == null) return c;
        if (c.compareTo(BigInteger.ONE) == 0) return c;

        while (p != null) {
            if ((p.x == 0)) {
                if (c.compareTo(BigInteger.ZERO) == 0)
                    c = p.val;
                else c = gcd(c, p.val);
            } else {
                BigInteger cc = coefgcd(p.coef, c);
                c = gcd(c, cc);
            }
            if (c.compareTo(BigInteger.ONE) == 0) return c;

            if (p.x != 0 && p.deg == 0)
                p = p.coef;
            else
                p = p.next;
        }
        return c;
    }

    private boolean ZERO(double r) {
        return Math.abs(r) < ZERO;
    }

    private double[] poly_solve_quadratic(double aa, double bb1, double bb2) {

        double[] result;
        double mo = Math.pow(Math.abs(aa * bb1 * bb2), 1.0 / 3);
        if (ZERO(mo))
            mo = 1.0;
        aa = aa / mo;
        bb1 = bb1 / mo;
        bb2 = bb2 / mo;

        double dl = (bb1 * bb1 - 4 * aa * bb2);


        double tdl = dl;/// (mo * mo);

        if (Math.abs(tdl) < ZERO) {
            result = new double[1];
            result[0] = ((-1) * bb1) / (2 * aa);
            return result;
        }

        if (dl < 0) return null;
        dl = Math.sqrt(dl);
        result = new double[2];

        double x1 = ((-1) * bb1 + dl) / (2 * aa);
        double x2 = ((-1) * bb1 - dl) / (2 * aa);

        result[0] = x1;
        result[1] = x2;
        return result;
    }

    double[] poly_solve_cubic(double a, double b, double c, double d) {
        double p = (3 * c / a - (b * b / (a * a))) / 3;
        double q = (2 * Math.pow(b / a, 3) - 9 * b * c / a / a + 27 * d / a) / 27;

        double D = Math.pow(p / 3, 3) + Math.pow(q / 2, 2);

        if (D >= 0) {
            double u = cubic_root(-q / 2 + Math.sqrt(D));
            double v = cubic_root(-q / 2 - Math.sqrt(D));
            double y1 = u + v;
//            double y2 = -(u + v) / 2 + i(u - v) * sqrt(3) / 2
//            double y3 = -(u + v) / 2 - i(u - v) * sqrt(3) / 2
            double[] r = new double[1];
            r[0] = y1 - b / a / 3;
            return r;

        } else if (D < 0) {
            p = Math.abs(p);
            double phi = Math.acos(-q / 2 / Math.sqrt(p * p * p / 27));
            double pi = Math.PI;
            double y1 = 2 * Math.sqrt(p / 3) * Math.cos(phi / 3);
            double y2 = -2 * Math.sqrt(p / 3) * Math.cos((phi + pi) / 3);
            double y3 = -2 * Math.sqrt(p / 3) * Math.cos((phi - pi) / 3);
//            x = y - b / a / 3
            double t = b / a / 3;
            double[] r = new double[3];
            r[0] = y1 - t;
            r[1] = y2 - t;
            r[2] = y3 - t;
            return r;
        }
        return null;
    }

    private double cubic_root(double r) {
        double r1 = Math.pow(Math.abs(r), 1.0 / 3.0);
        if (r < 0)
            r1 = -r1;
        return r1;
    }

//    double[] cal_e4(double A, double B, double C, double D, double E, double rt) {
//        double a = -3 * B * B / (8 * A * A) + C / A;
//        double b = B * B * B / (8 * A * A * A) - B * C / (2 * A * A) + D / A;
//        double c = -3 * B * B * B * B / (256 * A * A * A * A) + C * B * B / (16 * A * A * A) - B * D / (4 * A * A) + E / A;
//        double p = -a * a / 12 - c;
//        double q = -a * a * a / 108 + a * c / 3 - b * b / 8;
//        double r = q / 2 + Math.sqrt(q * q / 4 + p * p * p / 27);
//        double u = Math.pow(r, 1 / 3.0);
//        double y = -5 / 6 * a - u;
//        if (Math.abs(u) >  ZERO)
//            y += p / (3 * u);
//
//        double w = Math.sqrt(a + 2 * y);
//        double t1 = -(3 * a + 2 * y + 2 * b / w);
//        double t2 = -(3 * a + 2 * y - 2 * b / w);
//        int n = 0;
//        double v1, v2, v3, v4;
//        if (t1 < 0 && t2 < 0)
//            return null;
//        else if (t1 > 0 && t2 > 0)
//            n = 4;
//        else
//            n = 2;
//
//        int i = 0;
//        double d[] = new double[n];
//
//        if (t1 > 0) {
//            v1 = -b / (4 * a) + (w + Math.sqrt(t1)) / 2;
//            v2 = -b / (4 * a) + (w - Math.sqrt(t1)) / 2;
//            d[i++] = v1 / rt;
//            d[i++] = v2 / rt;
//
//        }
//
//        if (t2 > 0) {
//            v3 = -b / (4 * a) + (-w + Math.sqrt(t2)) / 2;
//            v4 = -b / (4 * a) + (-w - Math.sqrt(t2)) / 2;
//            d[i++] = v3 / rt;
//            d[i++] = v4 / rt;
//        }
//        return d;
//    }

    double[] poly_solve_quartic(double a, double b, double c, double d) {
        /*
         * This code is based on a simplification of
         * the algorithm from zsolve_quartic.c for real roots
         */
        double aa, pp, qq, rr, rc, sc, tc, mt, x1, x2, x3, x4;
        double w1r, w1i, w2r, w2i, w3r;
        double v1, v2, arg;
        double disc, h;
        int k1, k2;
        double[] u, v, zarr;
        u = new double[3];
        v = new double[3];
        zarr = new double[4];
        /////////////////////////////////////

        ////////////////////////////////

        k1 = k2 = 0;
        aa = a * a;
        pp = b - (3.0 / 8.0) * aa;
        qq = c - (1.0 / 2.0) * a * (b - (1.0 / 4.0) * aa);
        rr = d - (1.0 / 4.0) * (a * c - (1.0 / 4.0) * aa * (b - (3.0 / 16.0) * aa));
        rc = (1.0 / 2.0) * pp;
        sc = (1.0 / 4.0) * ((1.0 / 4.0) * pp * pp - rr);
        tc = -((1.0 / 8.0) * qq * (1.0 / 8.0) * qq);

        /* This code solves the resolvent cubic in a convenient fashion
         * for this implementation of the quartic. If there are three real
         * roots, then they are placed directly into u[].  If two are
         * complex, then the real root is put into u[0] and the real
         * and imaginary part of the complex roots are placed into
         * u[1] and u[2], respectively. Additionally, this
         * calculates the discriminant of the cubic and puts it into the
         * variable disc. */
        {
            double qcub = (rc * rc - 3 * sc);
            double rcub = (2 * rc * rc * rc - 9 * rc * sc + 27 * tc);

            double Q = qcub / 9;
            double R = rcub / 54;

            double Q3 = Q * Q * Q;
            double R2 = R * R;

            double CR2 = 729 * rcub * rcub;
            double CQ3 = 2916 * qcub * qcub * qcub;

            disc = (CR2 - CQ3) / 2125764.0;


            if (0 == R && 0 == Q) {
                u[0] = -rc / 3;
                u[1] = -rc / 3;
                u[2] = -rc / 3;
            } else if (CR2 == CQ3) {
                double sqrtQ = Math.sqrt(Q);
                if (R > 0) {
                    u[0] = -2 * sqrtQ - rc / 3;
                    u[1] = sqrtQ - rc / 3;
                    u[2] = sqrtQ - rc / 3;
                } else {
                    u[0] = -sqrtQ - rc / 3;
                    u[1] = -sqrtQ - rc / 3;
                    u[2] = 2 * sqrtQ - rc / 3;
                }
            } else if (CR2 < CQ3) {
                double sqrtQ = Math.sqrt(Q);
                double sqrtQ3 = sqrtQ * sqrtQ * sqrtQ;
                double theta = Math.acos(R / sqrtQ3);
                if (R / sqrtQ3 >= 1.0) theta = 0.0;
                {
                    double norm = -2 * sqrtQ;

                    u[0] = norm * Math.cos(theta / 3) - rc / 3;
                    u[1] = norm * Math.cos((theta + 2.0 * Math.PI) / 3) - rc / 3;
                    u[2] = norm * Math.cos((theta - 2.0 * Math.PI) / 3) - rc / 3;
                }
            } else {
                double sgnR = (R >= 0 ? 1 : -1);
                double modR = Math.abs(R);
                double x = R2 - Q3;
                if (x <= 0)
                    x = 0;
                double sqrt_disc = Math.sqrt(x);        // modified here. 2007.1.2
                double A = -sgnR * Math.pow(modR + sqrt_disc, 1.0 / 3.0);
                double B = Q / A;
                double mod_diffAB = Math.abs(A - B);

                u[0] = A + B - rc / 3;
                u[1] = -0.5 * (A + B) - rc / 3;
                u[2] = -(Math.sqrt(3.0) / 2.0) * mod_diffAB;
//                double sgnR = (R >= 0 ? 1 : -1);
//                double modR = Math.abs(R);
//                double sqrt_disc = Math.sqrt(R2 - Q3);
//                double A = -sgnR * Math.pow(modR + sqrt_disc, 1.0 / 3.0);
//                double B = Q / A;
//                double mod_diffAB = Math.abs(A - B);
//
//                u[0] = A + B - rc / 3;
//                u[1] = -0.5 * (A + B) - rc / 3;
//                u[2] = -(Math.sqrt(3.0) / 2.0) * mod_diffAB;
            }
        }

        /* End of solution to resolvent cubic */

        /* Combine the square roots of the roots of the cubic
         * resolvent appropriately. Also, calculate 'mt' which
         * designates the nature of the roots:
         * mt=1 : 4 real roots (disc == 0)
         * mt=2 : 0 real roots (disc < 0)
         * mt=3 : 2 real roots (disc > 0)
         */

        if (0.0 == disc)
            u[2] = u[1];

        if (0 >= disc) {
            mt = 2;

            /* One would think that we could return 0 here and exit,
             * since mt=2. However, this assignment is temporary and
             * changes to mt=1 under certain conditions below.
             */

            v[0] = Math.abs(u[0]);
            v[1] = Math.abs(u[1]);
            v[2] = Math.abs(u[2]);

            v1 = Math.max(Math.max(v[0], v[1]), v[2]);
            /* Work out which two roots have the largest moduli */
            k1 = 0;
            k2 = 0;
            if (v1 == v[0]) {
                k1 = 0;
                v2 = Math.max(v[1], v[2]);
            } else if (v1 == v[1]) {
                k1 = 1;
                v2 = Math.max(v[0], v[2]);
            } else {
                k1 = 2;
                v2 = Math.max(v[0], v[1]);
            }

            if (v2 == v[0]) {
                k2 = 0;
            } else if (v2 == v[1]) {
                k2 = 1;
            } else {
                k2 = 2;
            }

            if (0.0 <= u[k1]) {
                w1r = Math.sqrt(u[k1]);
                w1i = 0.0;
            } else {
                w1r = 0.0;
                w1i = Math.sqrt(-u[k1]);
            }
            if (0.0 <= u[k2]) {
                w2r = Math.sqrt(u[k2]);
                w2i = 0.0;
            } else {
                w2r = 0.0;
                w2i = Math.sqrt(-u[k2]);
            }
        } else {
            mt = 3;

            if (0.0 == u[1] && 0.0 == u[2]) {
                arg = 0.0;
            } else {
                arg = Math.sqrt(Math.sqrt(u[1] * u[1] + u[2] * u[2]));
            }
            double theta = Math.atan2(u[2], u[1]);

            w1r = arg * Math.cos(theta / 2.0);
            w1i = arg * Math.sin(theta / 2.0);
            w2r = w1r;
            w2i = -w1i;
        }

        /* Solve the quadratic to obtain the roots to the quartic */
        w3r = qq / 8.0 * (w1i * w2i - w1r * w2r) /
                (w1i * w1i + w1r * w1r) / (w2i * w2i + w2r * w2r);
        h = a / 4.0;

        zarr[0] = w1r + w2r + w3r - h;
        zarr[1] = -w1r - w2r + w3r - h;
        zarr[2] = -w1r + w2r - w3r - h;
        zarr[3] = w1r - w2r - w3r - h;

        /* Arrange the roots into the variables z0, z1, z2, z3 */
        if (2 == mt) {
            if (u[k1] >= 0 && u[k2] >= 0) {
                mt = 1;
                x1 = zarr[0];
                x2 = zarr[1];
                x3 = zarr[2];
                x4 = zarr[3];
                double[] x = new double[4];
                x[0] = x1;
                x[1] = x2;
                x[2] = x3;
                x[3] = x4;
                return x;
            } else {
                return null;
            }
        } else {
            x1 = zarr[0];
            x2 = zarr[1];
            double[] x = new double[2];
            x[0] = x1;
            x[1] = x2;
            return x;
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    BigFraction calpoly(TMono m, BigFraction[] p) {
        if (m == null || p == null)
            return BigFraction.ZERO;

        if (Int(m))
            return new BigFraction(m.val);
        BigFraction r = BigFraction.ZERO;

        while (m != null) {
            BigFraction v = calpoly(m.coef, p);
            if (m.deg == 0)
                r = r.add(v);
            else {
                int id = m.x - 1;
                if (id < 0 || id >= p.length || p[m.x - 1] == null)
                    return BigFraction.ZERO;
                r = r.add((p[m.x - 1]).pow(m.deg).multiply(v));
            }
            m = m.next;
        }
        return r;
    }

    public int check_ndg(TMono m, param[] pm) // 0. TRUE 1. FALSE 2.CAN NOT Verify, should be checked by floating point calculation.
    {
        if (m == null)
            return 1;

        int x = m.x;
        int n = 0;
        for (n = 0; n < pm.length; n++) {
            param p = pm[n];
            if (p == null || p.xindex >= x)
                break;
        }
        BigFraction[] bp = new BigFraction[n + 1];

        int r = ndg_valid(m, pm, bp, 0);
        if (r == -1) {
            long k = 1;
            for (int i = 0; i <= n; i++) {
                if (pm[i].m == null) {
                    bp[i] = bp[i].add(BigInteger.valueOf(2 * k + 1));
                    r = ndg_valid(m, pm, bp, 0);
                    if (r != -1)
                        return r;
                    k++;
                }
            }
        }

        for (int i = 0; i < bp.length; i++)
            if (bp[i] != null)
                System.out.println(bp[i]);
        return -1;
    }

    public int ndg_valid(TMono m, param[] pm, BigFraction[] bp, int n) {
        for (int i = n; i < bp.length && pm[i] != null; i++) {
            param p = pm[i];
            if (p.m == null) {
                if (bp[i] == null)
                    bp[i] = new BigFraction((long) p.value);
            } else {
                BigFraction[] bb = calcu_pm(p.m, bp, p);
                if (bb == null) // no solution.
                    return -1;
                else if (bb.length == 0)
                    return 1; // not equal..
                else if (bb.length == 1)
                    bp[i] = bb[0];
                else {
                    for (int j = 0; j < bb.length; j++) {
                        bp[i] = bb[j];
                        int b1 = ndg_valid(m, pm, bp, i + 1);
                        if (b1 != 1)
                            return b1;
                    }
                }
            }
        }
        boolean r = calpoly(m, bp).compareTo(BigInteger.ZERO) == 0;
        if (r)
            return 0;
        else return 1;
    }

    public BigFraction[] calcu_pm(TMono m, BigFraction[] bp, param pm) {    //    return null if m.coef == 0
        if (m.deg == 1) {
            BigFraction a = calpoly(m.coef, bp);
            if (a.isZero()) return null;

            BigFraction b = calpoly(m.next, bp);
            BigFraction[] bb = new BigFraction[1];
            bb[0] = b.divide(a).negate();
            return bb;
        } else if (m.deg == 2) {
            BigFraction a, b, c;
            a = calpoly(m.coef, bp);
            if (a.isZero()) return null;
            m = m.next;
            if (m.deg == 1) {
                b = calpoly(m.coef, bp);
                m = m.next;
            } else b = BigFraction.ZERO;
            c = calpoly(m.coef, bp);
            BigFraction dl = b.multiply(b).subtract(a.multiply(4).multiply(c));
            dl = dl.sqrt();
            if (dl != null) {
                BigFraction[] bb = new BigFraction[2];
                bb[0] = (b.negate().add(dl).divide(a.multiply(2)));
                bb[1] = (b.negate().subtract(dl).divide(a.multiply(2)));
                return bb;
            } else return null;
        } else if (m.deg == 3) {
            BigFraction a, b, c, d;
            a = b = c = d = BigFraction.ZERO;
            while (m != null) {
                BigFraction f = calpoly(m.coef, bp);
                switch (m.x) {
                    case 3:
                        a = f;
                        break;
                    case 2:
                        b = f;
                        break;
                    case 1:
                        c = f;
                        break;
                    case 0:
                        d = f;
                        break;
                }
                m = m.next;
            }
            if (a.isZero()) return null;
        }
        return new BigFraction[0];
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean n2dv(TMono m) {
        if (plength(m) != 2) return false;
        m = m.next;
        if (m == null) return false;
        return Int(m.coef);
    }

    public TPoly gb_reduce(TPoly poly) {
//        return bb_reduce(poly);
        return null;
    }

    public void nn_reduce(TPoly poly) {
        while (poly != null) {
            TMono m = poly.poly;
            if (n2dv(m)) {
                while (m != null && m.x != 0) {
                    nn_div1(m.x, poly.next);
                    m = m.coef;
                }
            }
            poly = poly.next;
        }
    }

    public void nn_div1(int x, TPoly poly) {

        while (poly != null) {
            TMono m = poly.poly;
            while (true) {
                long n = factor_contain(x, 1, m);
                if (n > 0)
                    div_factor1(m, x, (int) n);
                else break;
            }
            poly = poly.next;
        }
    }

    public TMono g_prem(TMono p1, TMono p2) {
//        if (p1 == null)
//            return p1;
//        if (p2 == null)
//            return p1;
//        if (p1.x < p2.x)
//            return p1;
//
//        TMono result = null;
//        if (p1.x == p2.x)
//            result = prem1(p1, p2);
//        else
//            result = prem3(p1, p2);
//
//        coefgcd(result);
//
//        return result;
        return null;
    }


    public Vector bb_reduce(Vector vlist, long t) {
        bb_reduce(vlist, t, false);
//        bb_reduce(vlist, t, false);
//        bb_reduce(vlist, t, true);

        return vlist;
    }

    public Vector bb_reduce(Vector vlist, long t, boolean s) {


        while (true) {
            boolean r = true;
            int size = vlist.size();

            for (int i = size - 2; i >= 0; i--) {
                boolean modified = false;
                TMono m2 = (TMono) vlist.get(i);
                for (int j = i + 1; j < vlist.size(); j++) {
                    TMono m1 = (TMono) vlist.get(j);
                    if (s && plength(m1.coef) != 1)
                        continue;

                    if (BB_STOP) return vlist;
                    TMono m = bb_divnh(m2, m1);
                    if (m != null) {
                        modified = true;
                        m1 = p_copy(m1);
                        BigInteger b1 = getLN(m1);
                        m2 = pdif(cp_times(b1, m2), pp_times(m, m1));
                        coefgcd(m2);
                        r = false;
                        if (m2 == null) break;
                        if (Int(m2)) return vlist;
                    }
                }


                if (modified) {
                    vlist.remove(i);
                    if (m2 != null) {
                        coefgcd(m2);
                        ppush(m2, vlist);
                    }
                    size = vlist.size();
                    //  i = size -2;
                }
            }
            if (r) break;
        }
        return vlist;
    }


    public void divm(TMono m1, TMono m) {
        if (m1 == null || m == null || m1.x <= m.x) return;

        while (m != null && m.x != 0) {
            while (true) {
                long n = factor_contain(m.x, 1, m1);
                if (n > 0)
                    div_factor1(m1, m.x, (int) n);
                else break;
            }
            m = m.coef;
        }
    }

    public int get_n_paraent(TMono m) {
        if (m == null)
            return 0;
        int n = 0;
        while (m != null)
            m = m.coef;
        return n;
    }

    public TMono sp_reduce(TMono m1, TMono m2) { //m1.x == m2.x


        while (true) {

            if (m1 == null) return m1;
            if (m2 == null) return m1;
            if (m1.x != m2.x)
                break;
            if (m2.coef == null || m2.coef.coef != null)
                break;
            if (m2.deg != 1)
                break;

            //           basic.print(m1);

            BigInteger b1 = getLN(m1);
            BigInteger b2 = getLN(m2);

            int n = m1.deg;

            BigInteger bc1 = BigInteger.ONE;
            BigInteger coefm2 = m2.coef.val;
            TMono e = p_copy(m2.next.coef);
            e = this.pctimes(e, -1);

            TMono cpm2 = pth(0, 1, 1);
            for (int i = 0; i < n; i++) {
                bc1 = bc1.multiply(coefm2);
                cpm2 = ptimes(cpm2, p_copy(e));
            }
            m1 = padd(ptimes(m1.coef, cpm2), this.pctimes(m1.next, bc1.intValue()));
//            basic.print(m1);
        }
        coefgcd(m1);
        return m1;
    }

    public TMono b_reduce(TMono m1, Vector vlist) {
        if (m1 == null) return null;

        while (true) {
            boolean r = true;
            for (int i = 0; i < vlist.size(); i++) {
                TMono m2 = (TMono) vlist.get(i);
                if (m1 == m2)
                    continue;
                //             if (m1.coef != null && m1.coef.coef == null)
                //                continue;

                //              m1 = this.sp_reduce(m1, m2);

                TMono m = bb_divn(m1, m2);
                while (m != null) {
                    BigInteger b2 = getLN(m2);
                    if (BB_STOP) return null;

                    m1 = pdif(cp_times(b2, m1), pp_times(m, p_copy(m2)));

                    if (m1 == null) return null;
                    r = false;
                    m = bb_divn(m1, m2);
                }
            }
            if (r) break;
        }
        coefgcd(m1);
        return m1;
    }

    private void ppmove(TPoly pp) {
        if (pp == null) return;
        TMono m = pp.poly;
        TPoly tp = pp.next;
        while (tp != null) {
            TMono mx = tp.poly;
            if (pp_compare(m, mx) < 0) {
                tp.poly = m;
                pp.poly = mx;
                pp = tp;
            } else break;

            tp = pp.next;
        }
    }

    private TPoly shink_poly(TPoly poly) {
        if (poly == null) return null;
        TPoly tp = poly;
        while (tp.next != null) {
            if (tp.next.poly == null)
                tp.next = tp.next.next;
            else
                tp = tp.next;
        }
        if (poly.poly != null)
            return poly;
        return poly.next;
    }


    private int compare(TMono m1, TMono m2) {
        if (m1 == null && m2 == null) return 0;
        if (m1 == null && m2 != null) return -1;
        if (m1 != null && m2 == null) return 1;

        if (m1.x == 0 && m2.x == 0) return 0;
        if (m1.x == 0 && m2.x != 0) return -1;
        if (m1.x != 0 && m2.x == 0) return 1;

        if (m1.x > m2.x || m1.x == m2.x && m1.deg > m2.deg)
            return 1;
        if (m1.x == m2.x && m1.deg == m2.deg)
            return 0;

        return -1;
    }

    private TMono m_head(TMono m) {
        if (m == null)
            return null;
        while (!Int(m))
            m = m.coef;
        return pth(0, m.val, 0);
    }

//    private TMono bb_divnh(TMono m1, TMono m2) {
//        if (m1 == null || m2 == null) return null;
//        int n = compare(m1, m2);
//
//        if (n < 0) return null;
//
//        if (Int(m1) && Int(m2))
//            return new TMono(0, m1.val, 0);
//
//        if (n == 0) {
//            TMono mx = bb_divn(m1.coef, m2.coef);
//            if (mx == null)
//                mx = this.m_head(m1);
//            return mx;
//        }
//
//        if (n > 0) {
//            if (m1.x == m2.x) {
//                TMono mx = bb_divn(m1.coef, m2.coef);
//                if (mx == null)
//                    mx = this.m_head(m1);
//                int dd = m1.deg - m2.deg;
//                if (dd > 0)
//                    mx = pp_times(pth(m1.x, 1, dd), mx);
//
//                return mx;
//            } else {
//                TMono mx = bb_divn(m1.coef, m2);
//                if (mx != null) {
//                    int dd = m1.deg;
//                    if (dd > 0)
//                        mx = pp_times(pth(m1.x, 1, dd), mx);
//                }
//                return mx;
//
//            }
//
//        }
//        return null;
//    }

    private TMono bb_divnh(TMono m1, TMono m2) {
        if (m1 == null || m2 == null) return null;

        if (m1.x < m2.x || m1.x == m2.x && m1.deg < m2.deg) return null;

        if (Int(m1) && Int(m2))
            return pth(0, m1.val, 0);

        TMono mx = null;
        if (m1.x == m2.x) {
            if (m1.deg == m2.deg)
                return bb_divn(m1.coef, m2.coef);
            else {
                mx = bb_divn(m1.coef, m2.coef);
                int dd = m1.deg - m2.deg;
                if (dd == 0)
                    return mx;
                else if (dd > 0)
                    return pp_times(pth(m1.x, 1, dd), mx);
            }
        }


        mx = bb_divn(m1.coef, m2);
        if (mx == null) return null;

        return pp_times(pth(m1.x, 1, m1.deg), mx);
    }

    private TMono bb_divn(TMono m1, TMono m2) {  //  get a term of m1 which diviid leading variable of m2.
        if (m1 == null || m2 == null) return null;

        if (m1.x < m2.x || m1.x == m2.x && m1.deg < m2.deg) return null;

        if (Int(m1) && Int(m2))
            return pth(0, m1.val, 0);

        TMono mx = null;


        if (m1.x == m2.x) {
            if (m1.deg == m2.deg)
                return bb_divn(m1.coef, m2.coef);
            else {

                while (m1 != null && m1.deg >= m2.deg) {
                    mx = bb_divn(m1.coef, m2.coef);
                    int dd = m1.deg - m2.deg;
                    if (mx != null) {
                        if (dd == 0)
                            return mx;
                        else if (dd > 0)
                            return pp_times(pth(m1.x, 1, dd), mx);
                    }

                    m1 = m1.next;
                }
            }
            return null;
        } else if (m1.x > m2.x) {
            while (m1 != null) { // m1.x > m2.x

                mx = bb_divn(m1.coef, m2);
                if (mx != null)
                    break;
                m1 = m1.next;
            }

            if (m1 == null) return null;
            if (mx == null) return null;
            if (m1.deg == 0) return mx;
            return pp_times(pth(m1.x, 1, m1.deg), mx);
        }

        return null;
    }

    public void printVpoly(Vector v) {
        for (int i = 0; i < v.size(); i++)
            this.print((TMono) v.get(i));
        System.out.println("\n");
    }

    public Vector g_basis(Vector v) {
        while (true) {
            bb_reduce(v, System.currentTimeMillis());

            if (gb_finished(v))
                break;

            //          this.printVpoly(v);
            Vector tp = s_polys(v);

            for (int i = 0; i < tp.size(); i++) {
                ppush((TMono) tp.get(i), v);
                this.printpoly((TMono) tp.get(i));
            }
            if (tp.size() == 0)
                break;
        }
        return v;
    }

    public Vector s_polys(Vector vlist) {

        Vector v = new Vector();
        for (int i = 0; i < vlist.size(); i++) {
            TMono m1 = (TMono) vlist.get(i);
            for (int j = i + 1; j < vlist.size(); j++) {
                TMono m2 = (TMono) vlist.get(j);

                TMono mx = s_poly1(m1, m2);

                mx = b_reduce(mx, vlist);
                coefgcd(mx);
                if (mx != null) {
                    ppush(mx, v);
                }
            }
        }
        return v;
    }

    private TMono s_poly(TMono m1, TMono m2) {
        if (m1.x == m2.x && m1.deg >= m2.deg) {

        } else if (m_contain(m2.x, m2.deg, m1.coef)) {

        } else return null;

        TMono result;
        m1 = p_copy(m1);
        m2 = p_copy(m2);
        if (m1.x == m2.x) {
            result = prem1(m1, m2);
        } else
            result = prem3(m1, m2);
        return result;
    }

    private TMono s_poly1(TMono m1, TMono m2) {
        return prem4(m1, m2);
    }

    private TMono prem4(TMono m1, TMono m2) {
        if (m1 == null || m2 == null) return null;
        if (m1.x < m2.x) return m1;

//        if (m1.x > m2.x)
        {
            TMono mm = gcd_h(m1, m2);
            if (mm == null || mm.x == 0) return null;

            if (mm != null && mm.x != 0) {
                TMono t1 = div_gcd(m1, mm);
                TMono t2 = div_gcd(m2, mm);
                TMono m = pdif(ptimes(t2, p_copy(m1)), ptimes(t1, p_copy(m2)));
                return m;
            }
        }
//        else {
//            return prem1(m1, m2);
//        }
        return null;
    }

    private TMono gcd_h(TMono m1, TMono m2) {           // gcd of m1, m2.   (HEAD);
        if (m1 == null || m2 == null) return null;
        TMono mx = null;

        while (m1 != null && m2 != null && m1.x != 0 && m2.x != 0) {
            if (m1.x == m2.x) {
                TMono m;
                if (m1.x > 0) {
                    int dd = m1.deg;
                    if (dd > m2.deg)
                        dd = m2.deg;
                    m = pth(m1.x, 1, dd);
                    if (mx == null)
                        mx = m;
                    else
                        mx = ptimes(mx, m);
                }

                m1 = m1.coef;
                m2 = m2.coef;
            } else if (m1.x > m2.x)
                m1 = m1.coef;
            else if (m1.x < m2.x)
                m2 = m2.coef;
        }
        return mx;
    }

    private TMono div_gcd(TMono m1, TMono m) {
        TMono mx = pth(0, 1, 0);

        while (m1 != null) {
            if (m1.x > m.x) {
                mx = ptimes(mx, pth(m1.x, 1, m1.deg));
                m1 = m1.coef;
            } else if (m1.x == m.x) {
                if (m1.x == 0) {
                    mx = ptimes(mx, pth(0, m1.val, 0));
                } else {
                    int dd = m1.deg - m.deg;
                    if (dd > 0)
                        mx = ptimes(mx, pth(m1.x, 1, dd));
                }
                m1 = m1.coef;
                m = m.coef;
            }
        }
        return mx;
    }

    private TMono[] mgcd(TMono m1, TMono m2) {
        if (m1 == null || m2 == null) return null;
        TMono[] mm = new TMono[2];
        bb_div2n(m1, m2, mm);
        return mm;
    }

    private void bb_div2n(TMono m1, TMono m2, TMono[] mm) {
        if (m1 == null || m2 == null) return;

        while (m1 != null) {
            if (m1.x > m2.x) {
                bb_div2n(m1.coef, m2, mm);
                if (mm[0] != null) {
                    mm[1] = ptimes(pth(m1.x, 1, m1.deg), mm[1]);
                    return;
                }
            } else if (m1.x == m2.x && m1.deg >= m2.deg) {
                mm[0] = pth(0, 1, 0);
                mm[1] = pth(0, 1, 0);
                get_mm(m1, m2, mm);
                return;
            } else break;

            m1 = m1.next;
            if (m1 != null && m1.deg == 0)
                m1 = m1.coef;
        }
    }

    private void get_mm(TMono m1, TMono m2, TMono[] mm) {
        if (m1 == null || m2 == null) return;


        if (m1.x > m2.x) {
            mm[1] = ptimes(mm[1], pth(m1.x, 1, m1.deg));
            m1 = m1.coef;
        } else if (m1.x < m2.x) {
            mm[0] = ptimes(mm[0], pth(m2.x, 1, m2.deg));
            m2 = m2.coef;
        } else {
            if (m1.x == 0) {
                mm[1] = this.cp_times(m1.val, mm[1]);
                mm[0] = this.cp_times(m2.val, mm[0]);
                return;
            } else {
                if (m1.deg > m2.deg)
                    mm[1] = ptimes(mm[1], pth(m1.x, 1, m1.deg - m2.deg));
                else if (m1.deg < m2.deg)
                    mm[0] = ptimes(mm[0], pth(m1.x, 1, m2.deg - m1.deg));

                m1 = m1.coef;
                m2 = m2.coef;
            }
        }
        get_mm(m1, m2, mm);
    }

    private BigInteger getLN(TMono m) {
        if (m == null) return null;
        while (!Int(m))
            m = m.coef;
        return m.val;
    }

//    public void n_reduce(Vector v1, Vector nlist) {
//        for (int i = 0; i < v1.size(); i++) {
//            TMono m1 = (TMono) v1.get(i);
//            boolean r = false;
//            while (true) {
//                boolean b = true;
//                for (int j = 0; j < nlist.size(); j++) {
//                    TMono m2 = (TMono) nlist.get(j);
//                    TMono mm = gcd_h(m1, m2);
//                    if (mm != null && mm.x != 0) {
//                        TMono t1 = div_gcd(m1, mm);
//                        TMono t2 = div_gcd(m2, mm);
//                        print(m1);
//                        print(m2);
//                        m1 = pdif(ptimes(t2, p_copy(m1)), ptimes(t1, p_copy(m2)));
//
//                        if (CharSet.debug()) {
//                            print(m1);
//                            System.out.println("\n");
//                        }
//
//                        r = true;
//                        b = false;
//                    }
//                }
//                if (b) break;
//            }
//            if (r) {
//                v1.remove(i);
//                if (m1 == null)
//                    i--;
//                else
//                    v1.add(i, m1);
//            }
//        }
//    }


    public TMono ll_gbasis(TMono m1, TMono md, int x, int para) {
        if (m1 == null)
            return null;

        if (m1.deg == 1) {
            TMono m11 = getxm1(x, 1, m1);    // u1
            TMono m12 = getxm1(x - 1, 1, m1);   // u2
            TMono c11 = pp_times(pth(para, 1, 1), p_copy(m11)); // zu1,
            TMono c12 = pp_times(pth(para, 1, 1), p_copy(m12)); // zu2.

            TMono c2 = pth(x, 1, 1); // x1
            TMono t11 = ptimes(c11, p_copy(m1)); // zu1*x1
            TMono t12 = ptimes(c12, p_copy(m12)); // zu2^2

            TMono t2 = pp_times(c2, p_copy(md)); // x1*md.

            t2 = pdif(t2, t11);
            t2 = pp_times(p_copy(m11), t2);
            TMono t13 = pp_times(p_copy(m12), pp_times(pth(para, 1, 1), p_copy(m12)));


            return pdif(t2, pp_times(t13, p_copy(m1)));
        }
        return null;
    }

    public TMono ll_delta(int x, TMono m1, TMono m2) {
        if (m1 == null) return null;
        if (m1.deg == 1) {
            TMono m11 = getxm1(x, 1, m1);
            TMono m12 = getxm1(x - 1, 1, m1);

            if (m2 == null) {
                return this.pp_minus(pp_times(p_copy(m11), p_copy(m11)), pp_times(p_copy(m12), p_copy(m12)));
            }
            TMono m21 = getxm1(x, 1, m2);
            TMono m22 = getxm1(x - 1, 1, m2);

            TMono mx = pp_minus(pp_times(p_copy(m11), p_copy(m22)), pp_times(p_copy(m12), p_copy(m21)));

            if (mx == null) return mx;
            if (getLN(mx).intValue() < 0)
                mx = cp_times(-1, mx);
            return mx;
        } else if (m1.deg == 2) {
            if (m2 == null) return null;
            if (m2.deg == 1) {

            } else if (m2.deg == 2) {
                TMono m11 = getxm1(x, 1, m1);
                TMono m12 = getxm1(x - 1, 1, m1);

                TMono m21 = getxm1(x, 1, m2);
                TMono m22 = getxm1(x - 1, 1, m2);

                TMono x1 = pp_minus(p_copy(m11), p_copy(m21));
                TMono x2 = pp_minus(p_copy(m12), p_copy(m22));
                TMono mx = padd(pp_times(x1, p_copy(x1)), pp_times(x2, p_copy(x2)));
                return mx;
            }
        }
        return null;
    }

    public TMono getxm1(int x, TMono m) {
        if (m == null)
            return null;

        if (m.x == x)
            return m.coef;

        while (m.next != null)
            m = m.next;
        if (m.deg != 0)
            return null;
        m = m.coef;
        if (m.deg == 1 && m.x == x)
            return m.coef;
        return null;
    }

    public TMono getxm1(int x, int d, TMono m) {
        if (m == null)
            return null;
        while (m != null) {
            if (m.x < x || m.x == x && m.deg < d)
                return null;

            if (m.x == x && m.deg == d)
                return m.coef;

            if (m.next == null) {
                if (m.deg != 0)
                    return null;
                else m = m.coef;
            } else
                m = m.next;
        }
        return null;
    }

    public void upValueTM(Vector v, int dx) {
        if (dx == 0)
            return;

        for (int i = 0; i < v.size(); i++) {
            upValueTM((TMono) v.get(i), dx);
        }
    }

    public void upValueDM(Vector v, int dx) {
        for (int i = 0; i < v.size(); i++) {
            TDono d = (TDono) v.get(i);

            upValueTM(d.p1, dx);
            upValueTM(d.p2, dx);
        }
    }

    public int getMaxX(Vector v) {
        int x = 0;

        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (x < m.x)
                x = m.x;
        }
        return x;
    }

    public void upValueTM(TMono m, int dx) {
        if (dx == 0)
            return;

        if (m == null)
            return;
        if (m.x == 0)
            return;

        while (m != null) {
            if (m.x != 0)
                m.x += dx;
            if (m.x == 0) {
                int n = 0;
            }

            upValueTM(m.coef, dx);
            m = m.next;
        }
    }


    public boolean gb_finished(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (plength(m) == 1 && m.x == 0 && m.value() != 0)
                return true;
        }
        return false;
    }

    public void ndg_reduce(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (m.deg == 0) {
                v.remove(m);
                i--;
            }
        }
    }


    public Vector getcnds(Vector v, int dx) {
        Vector v1 = new Vector();
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (ctLessdx1(m, dx)) {
                v1.add(p_copy(m));
                v.remove(m);
                i--;
            }
        }
        return v1;
    }

    public Vector specialTreatment(TMono m1, TMono m2, int dd) {
        Vector v = new Vector();
        if (m1 == null) return v;
        int x = m1.x;

        if (m1.deg == 1) {
            TMono m11 = getxm1(x, 1, m1);
            TMono m12 = getxm1(x - 1, 1, m1);
            if (m2 == null) {
                return v;
            }
            TMono m21 = getxm1(x, 1, m2);
            TMono m22 = getxm1(x - 1, 1, m2);


            TMono dmm = pdif(ptimes(p_copy(m1), p_copy(m21)), ptimes(p_copy(m11), p_copy(m2)));
            dmm = ptimes(pth(dd, 1, 1), dmm);

            TMono dmm1 = pdif(ptimes(p_copy(m1), p_copy(m22)), ptimes(p_copy(m12), p_copy(m2)));
            dmm1 = ptimes(pth(dd, 1, 1), dmm1);
            v.add(dmm);
            v.add(dmm1);
        }
        return v;
    }

    public Vector updateTMM(Vector v, int s, int e, int dx, boolean up) {
        Vector v3 = new Vector();

        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            TMono m2 = updateTMM(m, s, e, dx, up);
            ppush(m2, v3);
        }
        return v3;
    }

    private TMono updateTMM(TMono m, int s, int e, int dx, boolean up) {

        TMono mx = null;

        if (up) {
            while (m != null) {
                if (m.x < s && m.x != 0) {
                    TMono m1 = updateTMM(m.coef, s, e, dx, up);
                    m1 = ptimes(m1, pth(dx + m.x, 1, m.deg));
                    mx = padd(mx, m1);
                } else {
                    if (m.x == 0) {
                        TMono m1 = pth(0, m.val, m.deg);
                        mx = padd(mx, m1);
                    } else {
                        TMono m1 = updateTMM(m.coef, s, e, dx, up);
                        if (m.deg != 0)
                            m1 = ptimes(m1, pth(m.x, 1, m.deg));
                        mx = padd(mx, m1);
                    }

                }
                m = m.next;
            }
        } else {
            while (m != null) {
                if (m.x > e) {
                    TMono m1 = updateTMM(m.coef, s, e, dx, up);
                    m1 = ptimes(m1, pth(m.x - dx, 1, m.deg));
                    mx = padd(mx, m1);
                } else {
                    if (m.x == 0) {
                        TMono m1 = pth(0, m.val, m.deg);
                        mx = padd(mx, m1);
                    } else {
                        TMono m1 = updateTMM(m.coef, s, e, dx, up);
                        if (m.deg != 0)
                            m1 = ptimes(m1, pth(m.x, 1, m.deg));
                        mx = padd(mx, m1);
                    }
                }
                m = m.next;
            }
        }
        return mx;
    }

    //////////////////////////////////////////////////////////////////
    //TDono;

    public Vector parseCommonDono(Vector v, int dx) {
        Vector v1 = new Vector();
        for (int i = 0; i < v.size(); i++) {
            TDono d = (TDono) v.get(i);
            for (int j = i + 1; j < v.size(); j++) {
                TDono d1 = (TDono) v.get(j);
                TMono p1 = d.p2;
                TMono p2 = d1.p2;
                BigInteger b1 = this.getLN(p1);
                BigInteger b2 = this.getLN(p2);

                if (ck_eq(p1, p2)) {
                    TMono m1 = pp_times(p_copy(d.p1), p_copy(d1.c));
                    TMono m2 = pp_times(p_copy(d.c), p_copy(d1.p1));
                    TMono m = pdif(cp_times(b1, m1), cp_times(b2, m2));
                    v1.add(m);
                }
            }
        }
        return v1;
    }

    public void eraseCommonDono(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            TDono d = (TDono) v.get(i);
            for (int j = i + 1; j < v.size(); j++) {
                TDono d1 = (TDono) v.get(j);
                TMono p1 = d.p2;
                TMono p2 = d1.p2;
                if (ck_eq(p1, p2)) {
                    v.remove(j);
                    j--;
                }
            }
        }
    }

    public boolean ck_eq(TMono m1, TMono m2) {
        while (m1 != null && m2 != null) {
            if (m1.x != m2.x || m1.deg != m2.deg)
                return false;
            if (!ck_eq(m1.coef, m2.coef))
                return false;
            m1 = m1.next;
            m2 = m2.next;
        }

        return m1 == m2; // null
    }

    public Vector parseDono(Vector v, int dx) {
        Vector v1 = new Vector();
        Vector v2 = new Vector();

        int ldx = 0;
        for (int i = 0; i < v.size(); i++) {
            TDono d = (TDono) v.get(i);
            TMono m = d.p2;

            ldx = getLdx(m, dx);
            if (ldx == 0) {
                v1.add(d);
            } else
                v2.add(d);
        }

        if (v2.size() == 0) return v1;

        boolean r = true;

        while (true) {
            r = true;

            for (int i = 0; i < v2.size(); i++) {
                TDono d = (TDono) v2.get(i);
                reduceDono(d, v1, dx);
                if (getLdx(d.p2, dx) == 0) {
                    v1.add(d);
                    v2.remove(d);
                    i--;
                    r = false;
                }
            }
            if (r) break;
        }

        return v1;
    }

    private int getLdx(TMono m, int dx) {
        while (m != null) {

            if (m.x > 0 && m.x < dx)
                return m.x;

            int n = getLdx(m.coef, dx);
            if (n > 0)
                return n;
            m = m.next;
        }
        return 0;
    }

    public boolean ctLessdx1(TMono m, int dx) {
        while (m != null) {
            if (m.x > 0 && m.deg > 0 && m.x < dx)
                return true;
            m = m.coef;
        }
        return false;
    }

    public boolean ctLessdx(TMono m, int dx) {
        int r = 0;

        while (m != null) {
            if (m.x > 0 && m.deg > 0 && m.x < dx)
                return true;

            if (ctLessdx(m.coef, dx))
                return true;

            m = m.next;
        }

        return false;
    }


    private boolean ctLdx(TMono m, int i) {
        while (m != null) {

            if (m.x > 0 && m.x == i)
                return true;

            boolean n = ctLdx(m.coef, i);
            if (n)
                return n;
            m = m.next;
        }
        return false;
    }

    private int MinLdx(TMono m, int dx) {
        int r = MinLdx(m);
        if (r >= dx)
            return -1;
        return r;
    }

    private int MinLdx(TMono m) {
        int r = Integer.MAX_VALUE;

        while (m != null) {
            if (m.x == 0)
                return Integer.MAX_VALUE;
            if (r > m.x)
                r = m.x;
            int k = MinLdx(m.coef);
            if (k < r)
                r = k;
            m = m.next;
        }
        return r;

    }

    private int MaxLdx(TMono m, int dx) {
        int r = 0;

        while (m != null) {
            if (m.x == 0)
                return -1;
            if (m.x < dx) {
                if (r < m.x)
                    r = m.x;
            }

            int k = MaxLdx(m.coef, dx);
            if (k > 0 && r < k)
                r = k;
            m = m.next;
        }
        return r;
    }


    private int ctMLdx(TMono m, int i) {  // MAX
        int r = 0;

        while (m != null) {
            if (m.x < i)
                break;

            if (m.x == i) {
                if (r < m.deg)
                    r = m.deg;
                break;
            } else if (m.x > i) {
                int k = ctMLdx(m.coef, i);
                if (r < k)
                    r = k;
            }

            m = m.next;
        }
        return r;
    }


    public TMono reduceMDono(TMono mm, Vector v, int dx) {
        TMono m = mm;

        while (true) {
            int max = MinLdx(m, dx);

            if (max <= 0)
                break;

            TDono d1 = getDo(v, max);
            if (BB_STOP)
                return null;

            if (d1 != null) {


                int rd = ctMLdx(m, max);

                TMono m2 = padd(pp_times(p_copy(d1.p1), p_copy(d1.p2)), p_copy(d1.c));
                for (int k = 0; k < rd; k++) {
                    m = pp_times(m, p_copy(d1.p2));
                }
//                dprint(m, 9);
//                dprint(m2, 9);

                TMono dp = basic.p_copy(d1.p1);
                div_factor1(dp, max, 1);
                while (dp != null && dp.x != 0 && dp.deg == 0)
                    dp = dp.coef;

                if (dp != null && dp.x != 0 && dp.deg != 0) {
                    for (int k = 0; k < rd; k++) {
                        m = pp_times(m, p_copy(dp));
                    }
                }


                TMono mx = bb_divn(m, m2);

                while (mx != null && mx.x != 0) {
                    BigInteger b2 = getLN(m2);
                    m = pdif(cp_times(b2, m), pp_times(mx, p_copy(m2)));
                    mx = bb_divn(m, m2);
                    if (BB_STOP)
                        return null;
                }
//                this.print(m);
                coefgcd(m);
            }
        }
        return m;
    }

    public void reduceDono(TDono d, Vector v, int dx) {
        for (int i = 1; i < dx; i++) {
            if (!ctLdx(d.p2, i))
                continue;

            int n = i;

            TDono d1 = getDo(v, n);
            if (d1 != null) {
                TMono m = d.p2;
                TMono m2 = this.padd(pp_times(p_copy(d1.p1), p_copy(d1.p2)), p_copy(d1.c));
                TMono mx = bb_divn(m, m2);
                if (mx == null) {
                    m = pp_times(m, p_copy(d1.p2));
                    mx = bb_divn(m, m2);
                }


                while (mx != null && mx.x != 0) {
                    BigInteger b2 = getLN(m2);
                    m = pdif(cp_times(b2, m), pp_times(mx, p_copy(m2)));
                    mx = bb_divn(m, m2);
                }
                d.p2 = m;
            }
        }
    }

    public TDono getDo(Vector v, int n) {
        TDono xd = null;
        int nn = -1;

        for (int i = 0; i < v.size(); i++) {
            TDono d = (TDono) v.get(i);
            TMono m = d.p1;
            nn = -1;

            while (m != null) {
                if (m != null && m.x != 0) {
                    nn = m.x;
                } else
                    break;
                m = m.coef;
            }

            if (nn == n)
                return d;
        }

        return xd;
    }


    public void d_reduce(TDono d1, Vector vlist) {
        if (d1 == null) return;

        TMono m1 = d1.p2;
        BigInteger bb = BigInteger.ONE;


        while (true) {
            boolean r = true;
            for (int i = 0; i < vlist.size(); i++) {
                TMono m2 = (TMono) vlist.get(i);
                TMono m = bb_divnh(m1, m2);
                while (m != null) {
                    BigInteger b2 = getLN(m2);
                    bb.multiply(b2);
                    m1 = pdif(cp_times(b2, m1), pp_times(m, p_copy(m2)));
                    if (m1 == null) break;
                    r = false;
                    m = bb_divn(m1, m2);
                }
            }
            if (r) break;
        }
//        d1.p1 = cp_times(bb, d1.p1);
        d1.c = cp_times(bb, d1.c);
        d1.p2 = m1;
    }


    public void splitDonos(Vector vnn, Vector vnds, int dx) {
        Vector vtemp = new Vector();

        while (true) {
            while (vnds.size() != 0) {
                TMono tx = getMaxDMono(vnds, dx);
//                this.dprint(tx, 9);

                int max = MaxLdx(tx, dx);
                int min = MinLdx(tx, dx);
                vnds.remove(tx);


                if (max != min) {
                    tx = reduceMDono(tx, vnn, dx);
                    max = MaxLdx(tx, dx);
                }

                if (max == min) {
                    TDono d = splitDono(tx, dx);
                    if (d != null)
                        vnn.add(d);
                } else {
                    vtemp.add(tx);
                }
            }
            if (vtemp.size() == 0)
                break;
            else {
                vnds.addAll(vtemp);
                vtemp.clear();
            }
        }

    }

    public TMono getMaxDMono(Vector vnds, int dx) {
        int k = 0;
        TMono tx = null;

        for (int i = 0; i < vnds.size(); i++) {
            TMono m = (TMono) vnds.get(i);
            int x = this.MaxLdx(m, dx);
            if (x > k) {
                k = x;
                tx = m;
            }
        }
        return tx;
    }

    public TDono splitDono(TMono m, int dx) {
        TMono m1 = m;
        TMono c = null;

        while (m1 != null) {
            if (m1.x == 0) {
                c = pth(0, m1.val, 0);
                break;
            } else if (m1.deg == 0)
                m1 = m1.coef;
            else m1 = m1.next;
        }

        if (c == null)
            return null;

        TMono mx = this.pp_minus(p_copy(m), p_copy(c));

        int minX = this.MinLdx(mx, dx);

        TMono mo = pth(0, 1, 0);
        TMono mf = get_factor1(mx);
        while (mf != null && mf.x != 0) {
            if (mf.x < dx) {
                mo = pp_times(mo, pth(mf.x, 1, mf.deg));
                div_factor1(mx, mf.x, mf.deg);
            }
            mf = mf.coef;
        }

        if (mx != null && getLN(mx).intValue() < 0) {
            mx = cp_times(-1, mx);
            c = cp_times(-1, c);
        }

        return new TDono(mo, mx, c);
    }

    public void reduceMdo(Vector vrs, int dx) {
        for (int i = 0; i < vrs.size(); i++) {
            TMono m = (TMono) vrs.get(i);
            int max = this.MaxLdx(m, dx);
            int min = this.MinLdx(m, dx);
            if (max == min)
                continue;


        }

    }


    public TMono getMono(TDono d) {
        TMono m = padd(pp_times(p_copy(d.p1), p_copy(d.p2)), p_copy(d.c));
        coefgcd(m);
        if (m == null)
            return m;

        if (this.getLN(m).intValue() < 0) {
            m = cp_times(-1, m);
            return m;
        }
        return m;
    }

}

