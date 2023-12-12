package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-4-14
 * Time: 13:47:59
 * To change this template use File | Settings | File Templates.
 */
public class MathBase extends gdd_bc {
    int strcmp(char[] p1, char[] p2) {
        int l1, l2;
        if (p1 == null && p2 == null)
            return 0;
        else if (p1 == null)
            return -1;
        else if (p2 == null) return 1;

        l1 = p1.length;
        l2 = p2.length;
        for (int i = 0; i < p1.length && i < p2.length; i++) {
            if (p1[i] > p2[i])
                return 1;
            else if (p1[i] < p2[i]) return -1;
        }
        if (l1 == l2) return 0;
        if (l1 > l2) return 1;
        return -1;
    }

    int strcmp(String s1, String s2) {
        if (s1 != null)
            return s1.compareTo(s2);
        else if (s2 == null)
            return 0;
        else
            return -1;
    }

    boolean strcpy(char[] p, char[] s) {
        if (s == null) return true;

        int len = p.length;
        for (int i = 0; i < len; i++)
            p[i] = '\0';
        for (int i = 0; i < len && i < s.length; i++)
            p[i] = s[i];
        return true;
    }

    boolean numberp(xterm p) {
        return (p.var == null);
    }

    boolean npoly(xterm p) {
        return (p.var == null);
    }

    boolean pzerop(xterm p) {
        return ((p.var == null) && (num_zop(p.c)));
    }

    boolean unitp(xterm p) {
        return ((p.var == null) && (p.c == mk_num(1L)));
    }

    boolean nunitp(xterm p) {
        return ((p.var == null) && (p.c == mk_num(-1L)));
    }

    boolean num_zop(long x) {
        return (x) == 0L;
    }

    boolean num_posp(long x) {
        return (x) > 0L;
    }

    boolean num_negp(long x) {
        return x < 0L;
    }

    long mk_num(long x) {
        return x;
    }

    long num_m(long x, long y) {
        return ((x) - (y));
    }

    long num_p(long x, long y) {
        return x + y;
    }

    long num_t(long x, long y) {
        return x * y;
    }

    long num_d(long x, long y) {
        return x / y;
    }

    long num_neg(long x) {
        return -x;
    }

    long num_gcd(long x, long y) {
        return lgcd((x), (y));
    }

    long num_p3(long x, long y, long z) {
        return ((x) + (y) + (z));
    }

    long num_t3(long x, long y, long z) {
        return ((x) * (y) * (z));
    }

    long num_mod(long x, long y) {
        return ((x) % (y));
    }

    long num_modt(long x) {
        return ((x) % 2L);
    }

    boolean num_unit(long p) {
        return (p) == 1L;
    }

    boolean num_nunit(long p) {
        return (p) == (-1L);
    }

    double num_to_f(long x) {
        return (double) (x);
    }

    long num_digs(long x) {
        return int_digs(x);
    }

    void num_show(long x) {
        int_show(x);
    }

    int min(int a, int b) {
        return ((a < b) ? (a) : (b));
    }

    int int_digs(long x) {
        int i = 0;
        if (x < 0L) x = -x;
        while (x > 0L) {
            i++;
            x = x / 10;
        }
        return (i);
    }

    int num_int(long c) {
        return (int) c;
    }

    void int_show(long x) {
        gprint(Integer.toString((int) x));
    }

    long lgcd(long l1, long l2) {
        long l;
        if (l1 < 0L) {
            l1 = -l1;
        }
        if (l2 < 0L) {
            l2 = -l2;
        }
        if (l1 > l2) {
            l = l1;
            l1 = l2;
            l2 = l;
        }
        while (l1 != 0L) {
            l = l2 % l1;
            l2 = l1;
            l1 = l;
        }
        return (l2);
    }


    long ngcd(long l1, long l2) {
        long n = lgcd(l1, l2);
        if (n < 0L) n = -n;
        return (n);
    }

    long lpower(long l, long n) {
        long d = (1L);
        if (n <= 0)
            return ((1L));
        else {
            while (n > 1) {
                if (n % 2 == 0) {
                    n /= 2;
                    l = num_t(l, l);
                } else {
                    n -= 1;
                    d = num_t(d, l);
                }
            }
            return (num_t(d, l));
        }
    }


}
