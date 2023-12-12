package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-4-17
 * Time: 15:02:14
 * To change this template use File | Settings | File Templates.
 */
public class gr extends poly{
    el_term rev_elim(el_term v)
    {
        xterm p;
        p = v.p1;
        v.p1 = v.p2;
        v.p2 = p;
        return (v);
    }


/* ps operations */
    dterm ps_member(xterm p, dterm ps)
    {
        for (; ps != null; ps = ps.nx) {
            if (eq_poly(p, ps.p)) return (ps);
        }
        return (null);
    }

    dterm ps_inter(dterm ps1, dterm ps2)
    {
        dterm ps, ps0, ps3;

        if (ps1 == null)
            return (null);
        else if (ps2 == null)
            return (null);
        else {
            ps = new dterm();
            ps.nx = null;
            ps0 = ps;
            while (ps1 != null) {
                ps3 = ps_member(ps1.p, ps2);
                if (ps3 != null) {
                    ps0.nx = get_dt(min((ps1.deg), (ps3.deg)), cp_poly(ps1.p), null);
                    ps0 = ps0.nx;
                }
                ps1 = ps1.nx;
            }
            return (ps.nx);
        }
    }

    dterm ps_diff2(dterm ps1, dterm ps2)   /*destructive for ps1 */
    {
        dterm ps, ps0, ps3;

        if (ps1 == null)
            return (null);
        else if (ps2 == null)
            return (ps1);
        else {
            ps = new dterm();
            ps.nx = ps1;
            while (ps2 != null) {
                ps0 = ps;
                ps3 = ps0.nx;
                while ((ps3 != null) && !(eq_poly(ps2.p, ps3.p))) {
                    ps0 = ps3;
                    ps3 = ps3.nx;
                }
                if (ps3 != null) {
                    ps3.deg -= ps2.deg;
                    if (ps3.deg <= 0) {
                        ps0.nx = ps3.nx;
                        put_p(ps3.p);
                        put_d(ps3);
                    }
                }
                ps2 = ps2.nx;
            }
            return (ps.nx);
        }
    }

    dterm ps_append(dterm ps1, dterm ps2)
    {
        dterm ps;
        if (ps1 == null)
            return (ps2);
        else if (ps2 == null)
            return (ps1);
        else {
            for (ps = ps1; ps.nx != null; ps = ps.nx) {
            }
            ps.nx = ps2;
            return (ps1);
        }
    }

    dterm ps_diff(dterm ps1, dterm ps2)
    {
        dterm ps, ps0;
        ps = new dterm();
        if (ps1 == null) return (null);
        if (ps2 == null) return (ps1);
        {
            ps.nx = ps1;
            ps0 = ps;
            while (ps1 != null) {
                if (ps_member(ps1.p, ps2) != null) {
                    ps0.nx = ps1.nx;
                    put_p(ps1.p);
                    put_d(ps1);
                    ps1 = ps0.nx;
                } else {
                    ps0 = ps1;
                    ps1 = ps0.nx;
                }
            }
            return (ps.nx);
        }
    }

    dterm ps_union(dterm ps1, dterm ps2)
//dterm  ps1, ps2;
    {
        if (ps1 == null) return (ps2);
        if (ps2 == null) return (ps1);
        ps2 = ps_diff(ps2, ps1);
        return (ps_append(ps1, ps2));
    }

    int ps_term1(dterm dp) {
        int k = 0;
        for (dterm ps = dp; ps != null; ps = ps.nx) {
            if (!npoly(ps.p)) k++;
        }
        return k;
    }


    xterm ps_plus(dterm dp)
    {
        xterm p1;
        dterm dp1;

        p1 = get_n(0L);
        while (dp != null) {
            if (dp.deg <= 0) {
                put_p(dp.p);
            } else if (dp.deg == 1)
                p1 = pplus(p1, dp.p);
            else
                p1 = pplus(p1, ppower(dp.p, dp.deg));
            dp1 = dp;
            dp = dp.nx;
            put_d(dp1);
        }
        return (p1);
    }

    xterm ps_times(dterm dp)
    {
        xterm p1;
        dterm dp1;

        p1 = get_n(1L);
        while (dp != null) {
            if (dp.deg <= 0) {
                put_p(dp.p);
            } else if (dp.deg == 1)
                p1 = ptimes(p1, dp.p);
            else
                p1 = ptimes(p1, ppower(dp.p, dp.deg));
            dp1 = dp;
            dp = dp.nx;
            put_d(dp1);
        }
        return (p1);
    }

/* gr operations */

    gr_term cp_gr(gr_term gr)
    {
        gr_term gr1 = mk_gr(gr.c1, gr.ps1, gr.c2, gr.ps2, gr.c, gr.nx);
        if (gr.c == 0)
            gr1.ps = gr.ps;
        else
            gr1.el = gr.el;
        return (gr1);
    }

    gr_term mk_gr(long  c1, dterm ps1, long c2, dterm ps2, int c, gr_term nx) {
        gr_term gr = new gr_term();
        gr.c1 = c1;
        gr.c2 = c2;
        gr.ps1 = ps1;
        gr.ps2 = ps2;
        gr.c = c;
        gr.nx = nx;
        return (gr);
    }

    gr_term mk_gr1(long c1, xterm p1, long c2, xterm p2) {
        gr_term gr;
        gr = new gr_term();
        gr.c1 = c1;
        gr.c2 = c2;
        if (p1 == null)
            gr.ps1 = null;
        else
            gr.ps1 = get_dt(1, p1, null);
        if (p2 == null)
            gr.ps2 = null;
        else
            gr.ps2 = get_dt(1, p2, null);
        gr.c = 0;
        gr.ps = null;
        return (gr);
    }

    int gr_length(gr_term gr)
    {
        dterm ps;
        int k, m = 0;
        for (ps = gr.ps1; ps != null; ps = ps.nx) {
            k = plength(ps.p);
            if (k > m) m = k;
        }
        for (ps = gr.ps2; ps != null; ps = ps.nx) {
            k = plength(ps.p);
            if (k > m) m = k;
        }
        return (m);
    }

    void put_gr(gr_term gr)
    {
        dterm ps, ps1;
        ps = gr.ps1;
        while (ps != null) {
            ps1 = ps;
            ps = ps.nx;
            put_p(ps1.p);
            put_d(ps1);
        }
        ps = gr.ps2;
        while (ps != null) {
            ps1 = ps;
            ps = ps.nx;
            put_p(ps1.p);
            put_d(ps1);
        }
        if (gr.c == -1) {
            ps = gr.ps;
            while (ps != null) {
                ps1 = ps;
                ps = ps.nx;
                put_p(ps1.p);
                put_d(ps1);
            }
        } else if (gr.c > 0) {
            el_term el, e1;
            el = gr.el;
            while (el != null) {
                put_p(el.p1);
                put_p(el.p2);
                e1 = el;
                el = el.nx;
                //free((el_term)e1);
            }
        }
        //free((gr_term)gr);
    }


/* simplification  */
    static dterm ps_d1, ps_d2;

    gr_term simp_gr(gr_term gr) {
        dterm ps0, ps1, ps2, ps3;
        xterm p1, p2;
        gr_term gr0;

        if (num_zop(gr.c1) && num_zop(gr.c2)) return (null);

        ps1 = ps_simp(cp_pols(gr.ps1));
        ps2 = ps_simp(cp_pols(gr.ps2));

        /*printf("simplfi_gr\n");
           print_gr(gr);
           printf("\n sps1: ()"); print_ps(ps1);
           printf("\n sps2: ()"); print_ps(ps2); printf("\n");
           */
        ps0 = null;
        if ((num_zop(gr.c1) || pzerop(ps1.p)) &&
                (num_zop(gr.c2) || pzerop(ps2.p))) {
            put_ps(ps1);
            put_ps(ps2);
            gr0 = mk_gr(mk_num(0L), null, mk_num(0L), null, -2, null);
            gr0.ps = null;
        } else if (num_zop(gr.c1) || pzerop(ps1.p)) {
            put_ps(ps1);
            gr0 = mk_gr(mk_num(0L), null, mk_num(1L), ps2, -2, null);
            gr0.ps = null;
            if (ps_term1(ps2) <= ps_term1(gr.ps2)) {
                put_ps(ps2);
                return (null);
            }
        } else if (num_zop(gr.c2) || pzerop(ps2.p)) {
            put_ps(ps2);
            if (ps_term1(ps1) <= ps_term1(gr.ps1)) {
                put_ps(ps1);
                return (null);
            }
            gr0 = mk_gr(mk_num(1L), ps1, mk_num(0L), null, -2, null);
            gr0.ps = null;
        } else {
            ps1 = get_dt(1, get_num(gr.c1), ps1);
            ps2 = get_dt(1, get_num(gr.c2), ps2);
            ps3 = ps_div(ps1, ps2);
            if (ps3 != null) {
                ps1 = ps_d1;
                ps2 = ps_d2;
                ps0 = ps_append(ps0, ps3);
            }
            ps3 = ps_div(ps2, ps1);
            if (ps3 != null) {
                ps2 = ps_d1;
                ps1 = ps_d2;
                ps0 = ps_append(ps0, ps3);
            }
            if (ps0 == null) {
                put_ps(ps1);
                put_ps(ps2);
                return (null);
            }
            p1 = ps1.p;
            p2 = ps2.p;
            gr0 = mk_gr(p1.c, ps1.nx, p2.c, ps2.nx, -1, null);
            gr0.ps = ps0;
        }
        /*printf("\nsimplfi_gr out:\n"); print_gr(gr0);printf("\n");  */
        return (gr0);
    }

    dterm ps_div(dterm ps1, dterm ps2)
    {
        dterm ps_1, ps_0, ps00, ps02, ps3, ps4;
        xterm p1, p2;
        int d;
        long c;

/*  printf("\nps_div\n");print_ps(ps1); printf("\n\n");print_ps(ps2);  */

        if (ps1 == null || ps2 == null) return (null);
        ps_0 = new dterm();
        ps_0.nx = null;
        ps00 =ps_0;

        ps4 = ps2;
        ps02 = ps2;
        while (ps02.nx != null) ps02 = ps02.nx;
        ps_1 = new dterm();
        while (ps4 != null) {
            ps3 = ps1;
            ps_1.nx = null;
            do {
                if (ps3.deg > 0) {
                    if (npoly(ps3.p) && npoly(ps4.p)) {
                        p1 = ps3.p;
                        p2 = ps4.p;
                        c = num_gcd(p1.c, p2.c);
                        if (!num_unit(c)) {
                            d = min(ps4.deg, ps3.deg);
                            ps4.deg -= d;
                            ps3.deg -= d;
                            ps00.nx = get_dt(d, get_num(c), null);
                            ps00 = ps00.nx;
                            if (!num_eq(p1.c, c)) {
                                ps_1.nx = get_dt(d, get_num(num_d(p1.c, c)), ps_1.nx);
                            }
                            if (!num_eq(p2.c, c)) {
                                ps02.nx = get_dt(d, get_num(num_d(p2.c, c)), null);
                                ps02 = ps02.nx;
                            }
                        }
                    } else if (npoly(ps3.p)) {
                    } else if (npoly(ps4.p)) {
                    } else {
                        p1 = pdiv(cp_poly(ps3.p), ps4.p);
                        if (p1 != null) {
                            d = min(ps4.deg, ps3.deg);
                            ps4.deg -= d;
                            ps3.deg -= d;
                            ps00.nx = get_dt(d, cp_poly(ps4.p), null);
                            ps00 = ps00.nx;
                            if (p1.var == null) {
                                if (!num_unit(p1.c)) {
                                    gerror("ps_div\n");
                                    return null;
                                }
                                put_x(p1);
                            } else
                                ps_1.nx = get_dt(d, p1, ps_1.nx);
                        }
                    }
                }
                ps3 = ps3.nx;
            } while (ps3 != null && ps4.deg > 0);
            if (ps_1.nx != null) {
                ps1 = ps_red(ps_append(ps1, ps_1.nx));
            }
            ps4 = ps4.nx;
        }

        if (ps_0.nx == null) {
            return (null);
        }
        ps_d1 = ps_red(ps1);
        ps_d2 = ps_red(ps2);
        return (ps_0.nx);
    }

    dterm ps_red(dterm ps)
    {
        dterm ps_t, ps1, ps2, ps3;
        xterm p1;
        long c = mk_num(1L);
        ps_t = new dterm();
        ps_t.nx = ps;
        ps1 =ps_t;
        ps2 = ps1.nx;
        while (ps2 != null) {
            if (ps2.deg <= 0) {
                ps3 = ps2;
                ps2 = ps2.nx;
                ps1.nx = ps2;
                put_p(ps3.p);
                put_d(ps3);
            } else if (npoly(ps2.p)) {
                p1 = ps2.p;
                c = num_t(c, lpower(p1.c, ps2.deg));
                ps3 = ps2;
                ps2 = ps2.nx;
                ps1.nx = ps2;
                put_p(ps3.p);
                put_d(ps3);
            } else {
                ps1 = ps2;
                ps2 = ps2.nx;
            }
        }
        return (get_dt(1, get_num(c), ps_t.nx));
    }

    dterm ps_simp(dterm ps)
    {
        dterm ps_t, ps0, ps1, ps2, ps3;
        xterm p1;
        int sn = 1;
        ps_t = new dterm();
        ps_t.nx = null;
        ps1 = ps_t;
        while (ps != null) {
            ps2 = vfactor(ps.p);
            p1 = ps2.p;
            if (num_zop(p1.c)) {
                ps3 = ps2.nx;
                put_x(p1);
                put_d(ps2);
                sn = 0;
            } else if (num_unit(p1.c)) {
                ps3 = ps2.nx;
                put_x(p1);
                put_d(ps2);
            } else {
                ps3 = ps2;
            }
            while (ps3 != null) {
                ps3.deg *= ps.deg;
                ps2 = ps_t.nx;
                while ((ps2 != null) && !(eq_poly(ps2.p, ps3.p))) ps2 = ps2.nx;
                if (ps2 != null) {
                    put_p(ps3.p);
                    ps2.deg += ps3.deg;
                    ps0 = ps3;
                    ps3 = ps3.nx;
                    put_d(ps0);
                } else {
                    ps1.nx = ps3;
                    ps1 = ps3;
                    ps3 = ps3.nx;
                    ps1.nx = null;
                }
            }
            ps2 = ps;
            ps = ps.nx;
            put_d(ps2);
        }
        if (sn == 0) {
            put_ps(ps_t.nx);
            return (get_dt(1, get_n(0L), null));
        }
        if (ps_t.nx == null) ps_t.nx = get_dt(1, get_n(1L), null);
        return (ps_t.nx);
    }

/*destructive for p*/
    dterm vfactor(xterm p) {
        dterm ps, ps1, ps2;
        xterm p1;
        int d1;
        long c = mk_num(1L);

        ps = new dterm();
        ps.nx = null;
        ps1 = ps;
        p1 = p;
        while (p1.var != null) {
            d1 = mdeg(p, p1.var);
/*      printf("pdeg: %d\n",d1); */
            if (d1 > 0) {
                ps1.nx = get_dt(d1, get_m(p1.var), null);
                ps1 = ps1.nx;
            }
            ps2 = p1.ps;
            p1 = ps2.p;
        }
        if (ps.nx != null) {
            p1 = ps_times(cp_pols(ps.nx));
            p = pdiv(p, p1);
            put_p(p1);
        }
        if (p.var == null) {
            c = p.c;
            put_x(p);
        } else {
            c = lcontent(p);
            if (num_nunit(c)) {
                p1 = get_n(-1L);
                p = ptimes(p, p1);
            } else if (!num_unit(c)) {
                p1 = get_num(c);
                p = pcdiv(p, p1);
                put_x(p1);
            }
            ps1.nx = get_dt(1, p, null);
            ps1 = ps1.nx;
        }
        ps2 = get_dt(1, get_num(c), ps.nx);
        ps.nx = ps2;
        return (ps.nx);
    }

    
}
