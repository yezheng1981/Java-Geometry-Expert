package gprover;

import java.util.Vector;


public class Full extends elim {

    int max_term;
    gr_term proof, last_pr;
    boolean qerror;
    int ertype = 0;

    boolean max_termp = false;
    xterm conc_p1, conc_p2;
    boolean print_conc = false;


    public Full() {
        P_STATUS = 0;
    }

    void prove_full() {
        gr_term gr1;
        dterm ps1;
        xterm p1;
        el_term e1;

        ertype = 0;
        pro_type = PRO_FULL;
        max_term = 0;
        last_pr = proof = new gr_term();
        proof.nx = null;
        d_base = 1;
        qerror = false;
        dbase();
        fconc();
        if (qerror) return;
        boolean first = true;        

        do {
            co_db.nx = null;
            ps1 = last_pr.ps1;
            if (ps1 == null) return;

            p1 = ps1.p;

            if (npoly(p1)) {
                print_fend();
                return;
            }
            if (((p1.var).nm) != 10) {
                gprint(Cm.s2810);
                qerror = true;
                return;
            }
            while (true) {
                if ((e1 = elim_q7(p1)) != null) {
                } else if ((e1 = elim_q8(p1)) != null) {
                } else if ((e1 = elim_f(p1.var)) != null) {
                } else if ((fcc(p1) % 2L) == 0 && (e1 = elim_d(p1.var)) != null) {
                } else if ((fcc(p1) % 3L) == 0 && (e1 = elim_t(p1.var)) != null) {
                } else if ((e1 = elim_tri(p1.var)) == null) {
                }
                if (e1 != null) {

                    el_term e = null;
                    if (first || show_detail && e1.et != null)
                        e = e1.et;
                    else
                        e = e1;
                    first = false;

                    while (e != null) {
                        int tn = p1.getTermNumber();
                        p1 = eprem(cp_poly(p1), e);
                        p1 = fpoly(p1);
                        if (max_termp) {
                            int tem = plength(p1);
                            if (tem > max_term) max_term = tem;
                        }
                        gr1 = mk_gr(mk_num(1L), get_dt(1, p1, null), mk_num(0L), null, 99, null);
                        gr1.setPTN(tn);
                        gr1.el = e;
                        e = e.nx;
                        gr1.el.nx = null;
                        last_pr.nx = gr1;
                        last_pr = gr1;
                    }
                    break;

                } else if (d_base == 1) {
                    d_base = 2;
                    dbase();
                } else
                    break;
            }
            //  fr = false;
        } while (e1 != null);
        print_fend();
    }

    void dbase() {
        midpt md;
        p_line pn;
        t_line tn;
        a_cir cr;
        angles as;
        for (md = all_md.nx; md != null; md = md.nx) {
            search_md(md);
        }
        for (tn = all_tn.nx; tn != null; tn = tn.nx) {
            search_tn(tn);
        }
        for (cr = all_cir.nx; cr != null; cr = cr.nx) {
            search_cr(cr);
        }
        if (d_base == 2) {
            for (pn = all_pn.nx; pn != null; pn = pn.nx) {
                search_pn(pn);
            }
            for (as = all_as.nx; as != null; as = as.nx) {
                search_as(as);
            }
            for (cr = all_cir.nx; cr != null; cr = cr.nx) {
                search_cr(cr);
            }
        }
    }

    boolean npoly(xterm p) {
        return (p.var == null);
    }

    void print_t() {
        if (print_conc)
            gprint(Cm.s2300);
    }

    void print_fend() {
        dterm ps1;
        xterm p1;
        ps1 = last_pr.ps1;
        p1 = ps1.p;
        if (pzerop(p1))
            print_t();
        else if (print_conc) {
            gprint(Cm.s2812);
        }
    }

    void conc_gr(long c1, xterm p1, long c2, xterm p2) {
        if (p1 != null && p1.getPV() < 0)
            p1 = this.neg_poly(p1);

        gr_term gr = mk_gr1((int) mk_num(c1), p1, (int) mk_num(c2), p2);
        gr.c = 0;
        last_pr.nx = gr;
        last_pr = gr;
    }

    void fconc() {
        fconc(conc);
    }

    void fconc(cond conc) {
        switch (conc.pred) {
            case CO_COLL:
                /* collinear */
                fconc_coll(conc.p[0], conc.p[1], conc.p[2]);
                break;
            case CO_PARA:
                /* parallel */
                conc_gr(1L, trim_full(conc.p[0], conc.p[1], conc.p[2], conc.p[3]), 0L, null);
                break;
            case CO_PERP:
                /* perpendicular */
                conc_gr(1L, pplus(trim_full(conc.p[0], conc.p[1], conc.p[2], conc.p[3]), get_n(1L)), 0L, null);
                break;
            case CO_ACONG:
                /* eqangle */
                conc_gr(1L, pminus(trim_f(conc.p[0], conc.p[1], conc.p[2], conc.p[3]), trim_f(conc.p[4], conc.p[5], conc.p[6], conc.p[7])),
                        0L, null);
                break;
            case CO_CYCLIC:
                /*cocircle */
                conc_gr(1L, pminus(trim_f(conc.p[3], conc.p[1], conc.p[3], conc.p[2]),
                        trim_f(conc.p[4], conc.p[1], conc.p[4], conc.p[2])),
                        0L, null);
                break;
            case -12:
                /* perp-b */
                conc_gr(1L, pminus(trim_f(conc.p[0], conc.p[1], conc.p[1], conc.p[2]),
                        trim_f(conc.p[1], conc.p[2], conc.p[0], conc.p[2])),
                        0L, null);
                break;
            case 49:
                break;
            case CO_EQ:
                /* constants8 */
                conc_gr(1L, pminus(conc_p1, conc_p2), 0L, null);
                break;

            case CO_PBISECT:
                conc_gr(1L, pminus(trim_f(conc.p[0], conc.p[1], conc.p[1], conc.p[2]),
                        trim_f(conc.p[1], conc.p[2], conc.p[2], conc.p[0])),
                        0L, null);
                break;

            case CO_CONG:
                fconc_cong(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                break;
            default: {
                conc_gr(1L, null, 1L, null);
                gprint(Cm.s2811);
                qerror = true;
                ertype = 1; // can not translate to full-angle epression.
            }
        }
    }

    public int getErrorType() {
        return ertype;
    }

    public void fconc_coll(int a, int b, int c) {
        if (a < b) {
            int k = a;
            a = b;
            b = k;
        }
        if (a < c) {
            int k = a;
            a = c;
            c = k;
        }
        conc_gr(1L, trim_f(a, b, a, c), 0L, null);
    }

    public boolean fconc_cong(int a, int b, int c, int d) {
        int l, m, n;
        if (a == c) {
            l = a;
            m = b;
            n = d;
        } else if (b == c) {
            l = b;
            m = a;
            n = d;
        } else if (a == d) {
            l = a;
            m = b;
            n = c;
        } else if (b == d) {
            l = b;
            m = a;
            n = c;
        } else {
            conc_gr(1L, null, 1L, null);
            return false;
        }

        conc_gr(1L, pminus(trim_f(l, m, m, n), trim_f(m, n, n, l)), 0L, null);
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    el_term mk_felim(var v, xterm p1, xterm p2) {
        el_term e1 = new el_term();
        if (this.var_reOrder(v)) {
            p1 = neg_poly(p1);
        }
        v = this.ad_var(v);
        e1.v = v;
        e1.p1 = p1;
        e1.p2 = p2;
        e1.p = get_m(v);
        e1.co = co_db.nx;
        return (e1);
    }

    el_term mk_felim(var v, xterm p1, xterm p2, int n, int t) {
        el_term e = mk_felim(v, p1, p2, t);
        if (n != 1)
            e.p = ptimes(get_n(n), e.p);
        return e;
    }


    el_term mk_felim(var v, xterm p1, xterm p2, int t) {
        el_term el = mk_felim(v, p1, p2);
        el.etype = t;
        return el;
    }

    el_term mk_feliminator(var v, xterm p1, xterm p2, int t) {
        el_term e1 = new el_term();
        e1.etype = t;
        e1.v = v;
        e1.p1 = p1;
        e1.p2 = p2;
        e1.p = get_m(v);
        e1.co = co_db.nx;
        return (e1);
    }

    el_term elim_qcs(xterm p) {                           // NO USAGE.
        var v1 = p.var;
        l_line ln1 = fadd_ln(v1.pt[0], v1.pt[1]);
        l_line ln2 = fadd_ln(v1.pt[2], v1.pt[3]);

        co_db.nx = null;
        int p1, p2, p3, p4;
        p1 = v1.pt[0];
        p2 = v1.pt[1];
        p3 = v1.pt[2];
        p4 = v1.pt[3];
        boolean t = false;
        if (ln1.pt[0] < v1.pt[0] && ln1.pt[1] < v1.pt[0]) {
            add_codb(CO_COLL, ln1.pt[0], ln1.pt[1], v1.pt[0], v1.pt[1], 0, 0, 0, 0);
            p1 = ln1.pt[0];
            p2 = ln1.pt[1];
            t = true;
        }
        if (ln2.pt[0] < v1.pt[2] && ln2.pt[1] < v1.pt[2]) {
            add_codb(CO_COLL, ln2.pt[0], ln2.pt[1], v1.pt[2], v1.pt[3], 0, 0, 0, 0);
            p3 = ln2.pt[0];
            p4 = ln2.pt[1];
            t = true;
        }
        if (t)
            return mk_felim(p.var, trim_f(p1, p2, p3, p4), get_n(1), 1);

        return null;
    }

    el_term elim_q7(xterm p) {
        l_line ln1, ln2, ln3, ln4;
        xterm p1 = p;

        while (true) {
            if (p1 == null || npoly(p1)) return (null);

            dterm ps1 = p1.ps;
            ps1 = ps1.nx;
            if (ps1 == null) return (null);

            var v1 = p1.var;
            ln1 = fadd_ln(v1.pt[0], v1.pt[1]);
            ln2 = fadd_ln(v1.pt[2], v1.pt[3]);
            dterm ps2 = ps1;

            while (ps2 != null) {
                xterm p2 = ps2.p;
                if (npoly(p2)) break;//goto l2;
                var v2 = p2.var;
                ln3 = fadd_ln(v2.pt[0], v2.pt[1]);
                ln4 = fadd_ln(v2.pt[2], v2.pt[3]);
                if (fcc(p1) == fcc(p2)) {
                    if (ln2 == ln3 && ln_less(ln4, ln2)) {
                        co_db.nx = null;
                        add_codb(CO_COLL, v1.pt[2], v1.pt[3], v2.pt[0], v2.pt[1], 0, 0, 0, 0);
                        xterm xt = trim_f(v1.pt[0], v1.pt[1], v2.pt[2], v2.pt[3]);
                        int r = RF_ADDITION;
                        if (pzerop(xt))
                            r = 2;
                        return (mk_felim(p1.var, pminus(xt, get_m(p2.var)), get_n(1L), r));
                    }
                    if (ln1 == ln4) {
                        co_db.nx = null;
                        add_codb(CO_COLL, v1.pt[0], v1.pt[1], v2.pt[2], v2.pt[3], 0, 0, 0, 0);
                        xterm xt = trim_f(v2.pt[0], v2.pt[1], v1.pt[2], v1.pt[3]);
                        int r = RF_ADDITION;
                        if (pzerop(xt))
                            r = 2;
                        return (mk_felim(p1.var, pminus(xt, get_m(p2.var)), get_n(1L), r));
                    }
                } else if (fcc(p1) == (-fcc(p2))) {
                    if (ln1 == ln3 && ln2 == ln4) {
                        co_db.nx = null;
                        add_codb(CO_COLL, v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1], 0, 0, 0, 0);
                        add_codb(CO_COLL, v1.pt[2], v1.pt[3], v2.pt[2], v2.pt[3], 0, 0, 0, 0);
                        return (mk_felim(p1.var, get_m(p2.var), get_n(1L), 1));
                    }

                    if (ln2 == ln4 && ln_less(ln3, ln2)) {
                        co_db.nx = null;
                        add_codb(CO_COLL, v1.pt[2], v1.pt[3], v2.pt[2], v2.pt[3], 0, 0, 0, 0);
                        xterm xt = trim_f(v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1]);
                        int r = RF_ADDITION;
                        if (pzerop(xt))
                            r = 1;
                        return (mk_felim(p1.var, pplus(get_m(p2.var), xt), get_n(1L), r));
                    }

                    if (ln1 == ln3) {
                        co_db.nx = null;
                        add_codb(CO_COLL, v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1], 0, 0, 0, 0);
                        xterm xt = trim_f(v2.pt[2], v2.pt[3], v1.pt[2], v1.pt[3]);
                        int r = RF_ADDITION;
                        if (pzerop(xt))
                            r = 1;
                        return (mk_felim(p1.var, pplus(get_m(p2.var), xt), get_n(1L), r));

                    }
                }
                ps2 = p2.ps;
                ps2 = ps2.nx;
            }
            ps1 = p1.ps;
            ps1 = ps1.nx;
            if (ps1 == null) return (null);
            p1 = ps1.p;
        }
    }

    el_term elim_q8(xterm p1) {
        dterm ps1;
        xterm p2;
        var v1, v2;
        l_line ln1, ln2;

        if (p1 == null || npoly(p1)) return (null);
        v1 = p1.var;
        ln1 = fadd_ln(v1.pt[0], v1.pt[1]);
        ps1 = p1.ps;
        ps1 = ps1.nx;
        if (ps1 == null) return (null);
        p2 = ps1.p;
        if (npoly(p2)) return (null);
        v2 = p2.var;
        ln2 = fadd_ln(v2.pt[0], v2.pt[1]);
        if (ln1 != ln2) return (null);
        {
            co_db.nx = null;
            add_codb(CO_COLL, v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1], 0, 0, 0, 0);
            return (mk_felim(p1.var, pplus(get_m(p2.var), trim_f(v2.pt[2], v2.pt[3], v1.pt[2], v1.pt[3])), get_n(1L), RF_ADDITION));  //addition.
        }
    }

    xterm fpoly(xterm p) {
        dterm ps1, ps2;
        xterm p1, p2;
        if (npoly(p)) {
            p.c = num_modt(p.c);
            return (p);
        }
        p1 = p;

        while (true) {
            ps1 = p1.ps;
            ps2 = ps1.nx;
            if (ps2 == null) return (p);
            p2 = ps2.p;
            if (!npoly(p2))
                p1 = p2;
            else
                break;
        }

        p2.c = num_modt(p2.c);
        if (pzerop(p2)) {
            put_p(p2);
            put_d(ps2);
            ps1.nx = null;
        }
        return (p);
    }

    el_term mk_felim11(var v, int a, int b, int c, int d, int o, int p1, int p2, int o1) { // <[ab, cd] = <[o1p1,o1,p2]
//        co_db.nx = null;
//
//        cond c1 = add_codb(CO_COLL, o, p1, a, b, 0, 0, 0, 0);
//        cond c2 = add_codb(CO_COLL, o, p2, c, d, 0, 0, 0, 0);
//        el_term e1 = null;
//        if (c1.pred != 0 || c2.pred != 0) {
//            e1 = mk_felim(v, trim_f(o, p1, o, p2), get_n(1L), 0);
//            co_db.nx = null;
//            add_codb(CO_CYCLIC, 0, o, o1, p1, p2, 0, 0, 0);
//            el_term e2 = mk_felim(new var(10, o, p1, o, p2), trim_f(o1, p1, o1, p2), get_n(1), 9);
//            e1.nx = e2;
//            co_db.nx = null;
//        } else {
//            add_codb(CO_CYCLIC, 0, o, o1, p1, p2, 0, 0, 0);
//        }
//        el_term el = (mk_felim(v, trim_f(o1, p1, o1, p2), get_n(1L), 9));
//        el.et = e1;
//        return el;
        co_db.nx = null;

        add_codb(CO_CYCLIC, 0, o, o1, p1, p2, 0, 0, 0);
        cond c1 = add_codb(CO_COLL, o, p1, a, b, 0, 0, 0, 0);
        cond c2 = add_codb(CO_COLL, o, p2, c, d, 0, 0, 0, 0);

//        el_term e1 = null;
//        if (c1.pred != 0 || c2.pred != 0) {
//            e1 = mk_felim(v, trim_f(o, p1, o, p2), get_n(1L), 0);
//            co_db.nx = null;
//            el_term e2 = mk_felim(new var(10, o, p1, o, p2), trim_f(o1, p1, o1, p2), get_n(1), 9);
//            e1.nx = e2;
//            co_db.nx = null;
//        } else {
//            add_codb(CO_CYCLIC, 0, o, o1, p1, p2, 0, 0, 0);
//        }
        el_term el = (mk_felim(v, trim_f(o1, p1, o1, p2), get_n(1L), RF_INSCRIBE));
        co_db.nx = null;

        return el;
    }

    el_term mk_felim6(var v, int a, int b, int c, int d) { // para
        co_db.nx = null;
        add_codb(CO_PARA, a, b, c, d, 0, 0, 0, 0);
        el_term e1 = mk_felim(v, get_n(0L), get_n(1L), 3);
        return e1;
    }

    el_term mk_felim7(var v, int a, int b, int c, int d) {
        co_db.nx = null;
        add_codb(CO_PERP, a, b, c, d, 0, 0, 0, 0);
        return mk_felim(v, get_n(1L), get_n(1L), 4);
    }

    el_term elim_f(var v) {
        el_term e1 = null;
        int a, b, c, d;

        a = v.pt[0];
        b = v.pt[1];
        c = v.pt[2];
        d = v.pt[3];
        if (xpara(a, b, c, d)) {
            e1 = mk_felim6(v, a, b, c, d);
        } else if (xperp(a, b, c, d)) {
            e1 = mk_felim7(v, a, b, c, d);
        } else if ((e1 = elim_f_pn(v, a, b, c, d)) != null) {
        } else if ((e1 = elim_f_tn(v, a, b, c, d)) != null) {
        } else {
            el_term e2 = null;
            e2 = elim_f_cir1(v, a, b, c, d);
            if (e2 != null && e2.etype != 14)
                e1 = e2;
            else {
                e1 = elim_f_cir2(v, a, b, c, d);
            }
            if (e1 == null)
                e1 = elim_f_cir3(v, a, b, c, d);
            if (e1 == null)
                e1 = elim_f_cir4(v, a, b, c, d);
            if (e1 == null)
                e1 = e2;
        }
        if (e1 == null) {
            if ((e1 = elim_f_center(v, a, b, c, d)) != null) {
            } else if ((e1 = elim_f_ans(v, a, b, c, d)) != null) {
            } else if ((e1 = elim_f_ln(v, a, b, c, d)) != null) {
            } else
                e1 = null;
        }

        return (e1);
    }

    el_term elim_f_ln(var v, int a, int b, int c, int d) {

        l_line ln1 = fd_ln(a, b);
        if (ln1 != null && a > ln1.pt[1]) {
            co_db.nx = null;
            add_codb(CO_COLL, ln1.pt[0], ln1.pt[1], a, b, 0, 0, 0, 0);
            return (mk_felim(v, trim_f(ln1.pt[0], ln1.pt[1], c, d), get_n(1L), 1));
        }
        return (null);
    }

    el_term elim_f_pn(var v, int a, int b, int c, int d) {

        l_line ln1 = fd_ln(a, b);
        p_line pn1 = fd_pn(a, b);
        if (pn1 == null) return (null);
        for (int i = 0; i <= pn1.no; i++) {
            l_line ln2 = pn1.ln[i];
            if (ln_less(ln2, ln1)) {
                co_db.nx = null;
                add_codb(CO_PARA, a, b, ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                var v1 = new var(10, a, b, ln2.pt[0], ln2.pt[1]);
                el_term e1 = this.mk_felim6(v1, a, b, ln2.pt[0], ln2.pt[1]);
                co_db.nx = null;
                el_term e = (mk_felim(v, trim_f(ln2.pt[0], ln2.pt[1], c, d), get_n(1L), 1));
                e.et = e1;
                return e;
            }
        }
        return (null);
    }


    el_term elim_f_tn(var v, int a, int b, int c, int d)    // could be more tn lines.
    {
        l_line ln2;
        l_line ln1 = fd_ln(a, b);
        t_line tn1 = fd_tn(ln1);
//        if (tn1 == null) return (null);
        if (tn1 != null) {
            if (tn1.l1 == ln1)
                ln2 = tn1.l2;
            else
                ln2 = tn1.l1;
            if (ln_less(ln2, ln1)) {
                co_db.nx = null;
                el_term e1 = mk_felim(v, pplus(trim_f(a, b, ln2.pt[0], ln2.pt[1]), trim_f(ln2.pt[0], ln2.pt[1], c, d)), get_n(1L), RF_ADDITION);
                add_codb(CO_PERP, a, b, ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                var v1 = new var(10, a, b, ln2.pt[0], ln2.pt[1]);
                el_term e2 = this.mk_felim7(v1, a, b, ln2.pt[0], ln2.pt[1]);
                e1.nx = e2;
                co_db.nx = null;
                el_term e = (mk_felim(v, pplus(trim_f(ln2.pt[0], ln2.pt[1], c, d), get_n(1L)), get_n(1L), RF_PERP_SPLIT));
                e.et = e1;
                return e;
            }
        } else if (a == c) {
            ln1 = fd_ln(c, d);
            tn1 = fd_tn(ln1);
            if (tn1 != null) {
                if (tn1.l1 == ln1)
                    ln2 = tn1.l2;
                else
                    ln2 = tn1.l1;

                if (ln_less(ln2, ln1)) {
                    co_db.nx = null;
                    el_term e1 = mk_felim(v, pplus(trim_f(a, b, ln2.pt[0], ln2.pt[1]), trim_f(ln2.pt[0], ln2.pt[1], c, d)), get_n(1L), RF_ADDITION);
                    add_codb(CO_PERP, c, d, ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                    var v1 = new var(10, c, d, ln2.pt[0], ln2.pt[1]);
                    el_term e2 = this.mk_felim7(v1, c, d, ln2.pt[0], ln2.pt[1]);
                    e1.nx = e2;
                    co_db.nx = null;
                    el_term e = (mk_felim(v, pplus(trim_f(a, b, ln2.pt[0], ln2.pt[1]), get_n(1L)), get_n(1L), RF_PERP_SPLIT));
                    e.et = e1;
                    return e;
                }
            }
        }
        return (null);
    }


    el_term elim_f_cir1(var v, int a, int b, int c, int d) {
        int o, p1, p2, p3, p4;
        l_line ln3, ln4, ln5, ln6;

        l_line ln1 = fadd_ln(a, b);
        l_line ln2 = fadd_ln(c, d);
        a_cir cr = all_cir.nx;
        while (cr != null)           //R11
        {
            if (cr.type == 0) {
                cr = cr.nx;
                continue;
            }
            o = inter_ll(ln1, ln2);
            p1 = inter_lc1(ln1, cr, o);
            p2 = inter_lc1(ln2, cr, o);
            if (o != 0 && p1 != 0 && p2 != 0 && on_cir(o, cr)) {
                for (int i = 0; i <= cr.no; i++) {
                    p3 = cr.pt[i];
                    if (p3 != o && p3 != p1 && p3 != p2 &&
                            ln_less((ln3 = fadd_ln(p3, p1)), ln1) &&
                            ln_less((ln4 = fadd_ln(p3, p2)), ln1)) {
                        return mk_felim11(v, a, b, c, d, o, p1, p2, p3);
                    }
                }
            }

            p1 = inter_lc(ln1, cr);
            p2 = inter_lc1(ln1, cr, p1);
            if (p1 == 0 || p2 == 0) {
                cr = cr.nx;
                continue;
            }

            ////////////////////////////////////////////////////////////////////////////////////
            el_term rel = null;

            for (int i = 0; i < cr.no; i++)
                for (int j = 0; j <= cr.no; j++) {
                    if (i == j) continue;
                    p3 = cr.pt[i];
                    p4 = cr.pt[j];

                    if (p3 != p1 && p3 != p2 && p4 != p1 && p4 != p2) {
                        ln3 = fadd_ln(p3, p1);
                        ln4 = fadd_ln(p3, p4);
                        ln5 = fadd_ln(p4, p2);
                        int tp1 = inter_ll(ln1, ln5);
                        int tp2 = inter_ll(ln2, ln5);

                        if (ln_less(ln3, ln1) && ln_less(ln4, ln1) && ln_less(ln5, ln1))// R12.
                        {
                            el_term e1 = mk_felim(v, pplus(trim_f(a, b, p2, p4), trim_f(p2, p4, c, d)), get_n(1L), RF_ADDITION);
                            var v1 = new var(10, a, b, p2, p4);
                            el_term e2 = this.mk_felim11(v1, a, b, p2, p4, p2, p1, p4, p3);
                            e1.nx = e2;
                            co_db.nx = null;
                            el_term el = (mk_felim(v, pplus(trim_f(p3, p1, p3, p4), trim_f(p4, p2, c, d)), get_n(1L), RF_9));
                            el.et = e1;
                            if (tp1 != 0 && tp2 != 0)
                                return el;
                            else if (rel == null)
                                rel = el;

                        } else if (ln_less(ln3, ln1) && ln_less(ln4, ln1)) {
                            ln6 = all_ln.nx;
                            while (ln6 != null) {
                                if (ln_para(ln6, ln5) && ln_less(ln6, ln1)) {
                                    el_term e1 = mk_felim(v, pplus(trim_f(a, b, p2, p4), trim_f(p2, p4, c, d)), get_n(1L), RF_ADDITION);
                                    var v2 = new var(10, a, b, p2, p4);
                                    el_term e2 = this.mk_felim11(v2, a, b, p2, p4, p2, p1, p4, p3);
                                    co_db.nx = null;
                                    var v3 = new var(10, p4, p2, ln6.pt[0], ln6.pt[1]);
                                    el_term e3 = this.mk_felim6(v3, p4, p2, ln6.pt[0], ln6.pt[1]);
                                    e1.nx = e2;
                                    e2.nx = e3;
                                    co_db.nx = null;
                                    el_term el = (mk_felim(v, pplus(trim_f(p3, p1, p3, p4), trim_f(ln6.pt[0], ln6.pt[1], c, d)), get_n(1L), RF_10));
                                    el.et = e1;
                                    return el;
                                }
                                ln6 = ln6.nx;
                            }
                            ln6 = all_ln.nx;
                            while (ln6 != null) {
                                if (ln_perp(ln6, ln5) && ln_less(ln6, ln1)) {
                                    el_term e1 = mk_felim(v, pplus(trim_f(a, b, p2, p4), trim_f(p2, p4, c, d)), get_n(1L), RF_ADDITION);
                                    var v2 = new var(10, a, b, p2, p4);
                                    el_term e2 = this.mk_felim11(v2, a, b, p2, p4, p2, p1, p4, p3);
                                    co_db.nx = null;
                                    var v3 = new var(10, p4, p2, ln6.pt[0], ln6.pt[1]);
                                    el_term e3 = this.mk_felim7(v3, p4, p2, ln6.pt[0], ln6.pt[1]);
                                    e1.nx = e2;
                                    e2.nx = e3;
                                    co_db.nx = null;
                                    el_term el = mk_felim(v, pplus3(trim_f(p3, p1, p3, p4), trim_f(ln6.pt[0], ln6.pt[1], c, d), get_n(1L)), get_n(1L), RF_DM_PERP);
                                    el.et = e1;
                                    return el;
                                }
                                ln6 = ln6.nx;
                            }
                        }
                    }
                }
            if (rel != null) return rel;
            l1:
            cr = cr.nx;
        }
        return (null);
    }

    el_term elim_f_cir2(var v, int a, int b, int c, int d) {
        a_cir cr1, cr2;
        int p1, p2, p3, p4;
        l_line ln3, ln4, ln5, ln6;

        l_line ln1 = fadd_ln(a, b);
        l_line ln2 = fadd_ln(c, d);
        int o = inter_ll(ln1, ln2);
        if (o == 0) return (null);
        cr1 = all_cir.nx;
        while (cr1 != null) {
            if (cr1.type == 0) {
                cr1 = cr1.nx;
                continue;
            }

            if (!on_cir(o, cr1)) {
                cr1 = cr1.nx;
                continue;
            }
            p1 = inter_lc1(ln1, cr1, o);
            if (p1 == 0) {
                cr1 = cr1.nx;
                continue;
            }
            cr2 = all_cir.nx;
            while (cr2 != null) {
                if (cr2.type == 0) {
                    cr2 = cr2.nx;
                    continue;
                }
                if (!on_cir(o, cr2)) {
                    cr2 = cr2.nx;
                    continue;
                }
                p2 = inter_lc1(ln2, cr2, o);
                if (p2 == 0) {
                    cr2 = cr2.nx;
                    continue;
                }

                for (int i = 0; i <= cr1.no; i++)
                    for (int j = 0; j <= cr2.no; j++) {
                        if (cr1.pt[i] != o && cr1.pt[i] != p1 && cr2.pt[j] != o && cr2.pt[j] != p2 && xcoll(o, cr1.pt[i], cr2.pt[j]) &&
                                (p3 = get_cpt3(cr1, o, p1, cr1.pt[i])) != 0 && (p4 = get_cpt3(cr2, o, p2, cr2.pt[j])) != 0) {
                            ln3 = fadd_ln(p3, p1);
                            ln4 = fadd_ln(p3, cr1.pt[i]);
                            ln5 = fadd_ln(p4, p2);
                            ln6 = fadd_ln(p4, cr2.pt[j]);
                            if (l2_less(ln3, ln4, ln1, ln2) && l2_less(ln5, ln6, ln1, ln2)) {
                                co_db.nx = null;
                                add_codb(CO_CYCLIC, 0, o, p2, cr2.pt[j], p4, 0, 0, 0);
                                add_codb(CO_CYCLIC, 0, o, p1, cr1.pt[i], p3, 0, 0, 0);
                                add_codb(CO_COLL, o, cr1.pt[i], cr2.pt[j], 0, 0, 0, 0, 0);
                                return (mk_felim(v, pminus(trim_f(p3, p1, p3, cr1.pt[i]), trim_f(p4, p2, p4, cr2.pt[j])), get_n(1L), RF_12));
                            }
                        }
                    }
                cr2 = cr2.nx;
            }
            cr1 = cr1.nx;
        }
        return (null);
    }

    el_term elim_f_cir3(var v, int a, int b, int c, int d) {
        a_cir cr1;
        int o, p1, p2, p3, p4;
        l_line ln1, ln2, ln3, ln4;
        int i, j;

        ln1 = fadd_ln(a, b);
        ln2 = fadd_ln(c, d);
        for (cr1 = all_cir.nx; cr1 != null; cr1 = cr1.nx) {
            if (cr1.type == 0) continue;
            o = cr1.o;
            if (o == 0) continue;
            if (!on_ln(o, ln1)) continue;
            p1 = inter_lc(ln1, cr1);
            if (p1 == 0) continue;

            if (o == inter_ll(ln1, ln2)) {
                p2 = inter_lc(ln2, cr1);
                if (p2 != 0) {
                    for (i = 0; i <= cr1.no; i++) {
                        p3 = cr1.pt[i];
                        if (p3 != p1 && p3 != p2 &&
                                ln_less((ln3 = fadd_ln(p3, p1)), ln1) &&
                                ln_less((ln4 = fadd_ln(p3, p2)), ln1)) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
                            return (mk_felim(v, ptimes(get_n(2L), trim_f(p3, p1, p3, p2)), get_n(1L), RF_13));      //R23
                        }
                    }
                    ln3 = fadd_ln(p1, p2);
                    if (ln_less(ln3, ln1)) {
                        co_db.nx = null;
                        add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                        return (mk_felim(v, ptimes(get_n(2L), trim_f(p1, p2, c, d)), get_n(1L), RF_14));       //r24
                    }
                }
            } else if (on_ln(p1, ln2)) {                                                                          //r25
                p2 = inter_lc1(ln2, cr1, p1);
                if (p2 != 0 && ln_less(fadd_ln(o, p2), ln1)) {
                    co_db.nx = null;
                    //add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    add_codb(CO_CONG, o, p1, o, p2, 0, 0, 0, 0);
                    return (mk_felim(v, trim_f(p1, p2, o, p2), get_n(1L), RF_ISO));
                }
            }
            for (i = 0; i < cr1.no; i++)
                for (j = i + 1; j <= cr1.no; j++) {
                    p2 = cr1.pt[i];
                    p3 = cr1.pt[j];
                    if (p2 != p1 && p3 != p1) {
                        if (xcoll(p1, c, d) && xcoll(p3, c, d)) {
                            p4 = p2;
                            p2 = p3;
                            p3 = p4;
                        }
                        ln2 = fadd_ln(p1, p2);
                        ln3 = fadd_ln(p3, p1);
                        ln4 = fadd_ln(p3, p2);
                        if (ln_less(ln2, ln1) && ln_less(ln3, ln1) && ln_less(ln4, ln1)) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
                            return (mk_felim(v, pplus3(trim_f(p3, p1, p3, p2), trim_f(ln2.pt[0], ln2.pt[1], c, d), get_n(1L)), get_n(1L), RF_16));
                        }
                    }
                }
        }
        return (null);
    }

    el_term elim_f_cir4(var v, int a, int b, int c, int d) {
        a_cir cr1;
        int o, p1, p2, p3, p4;
        l_line ln1;
        ln1 = fadd_ln(a, b);
        cr1 = all_cir.nx;
        while (cr1 != null) {
            if (cr1.type == 0) {
                cr1 = cr1.nx;
                continue;
            }

            o = cr1.o;
            if (o == 0 || o > a) {
                cr1 = cr1.nx;
                continue;
            }
            p1 = inter_lc(ln1, cr1);
            p2 = inter_lc1(ln1, cr1, p1);
            if (p1 == 0 || p2 == 0) {
                cr1 = cr1.nx;
                continue;
            }

            for (int i = 0; i <= cr1.no; i++)
                if (cr1.pt[i] != p1 && cr1.pt[i] != p2) {
                    p3 = cr1.pt[i];
                    p4 = fd_pt_md(p1, p3);
                    if (p4 != 0 && o != p4 &&
                            ln_less((fadd_ln(p1, o)), ln1) &&
                            ln_less((fadd_ln(o, p4)), ln1) &&
                            ln_less((fadd_ln(p2, p3)), ln1)) {
                        co_db.nx = null;
                        add_codb(CO_MIDP, p4, p1, p3, 0, 0, 0, 0, 0);
                        add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
                        return (mk_felim(v, pplus(trim_f(o, p1, o, p4), trim_f(p2, p3, c, d)), get_n(1L), RF_17));       //r27
                    }
                    if (p4 != 0 && o != p4 &&
                            ln_less((fadd_ln(p3, o)), ln1) &&
                            ln_less((fadd_ln(o, p4)), ln1) &&
                            ln_less((fadd_ln(p2, p3)), ln1)) {
                        co_db.nx = null;
                        add_codb(CO_MIDP, p4, p1, p3, 0, 0, 0, 0, 0);
                        add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
                        return (mk_felim(v, pplus(trim_f(o, p4, o, p3), trim_f(p2, p3, c, d)), get_n(1L), RF_17));
                    }
                    if (ln_less((fadd_ln(p1, o)), ln1) &&
                            ln_less((fadd_ln(p1, p3)), ln1) &&
                            ln_less((fadd_ln(p2, p3)), ln1)) {
                        co_db.nx = null;
                        add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);      //r33
                        return (mk_felim(v, pplus3(trim_f(p1, o, p1, p3), trim_f(p2, p3, c, d), get_n(1L)), //r28
                                get_n(1L), RF_18));
                    }
                    if (ln_less((fadd_ln(p3, o)), ln1) &&
                            ln_less((fadd_ln(p1, p3)), ln1) &&
                            ln_less((fadd_ln(p2, p3)), ln1)) {
                        co_db.nx = null;
                        add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
                        return (mk_felim(v, pplus3(trim_f(p3, p1, p3, o), trim_f(p2, p3, c, d), get_n(1L)),
                                get_n(1L), RF_18));
                    }
                }
            m1:
            cr1 = cr1.nx;
        }
        return (null);
    }

    el_term elim_f_center(var v, int a, int b, int c, int d) {
        l_line ln1, ln2;
        int p1, p2;
        char i, j, k, l;

        ln1 = fd_ln(a, b);
        for (i = 0; i <= ln1.no; i++)
            for (j = 0; j <= ln1.no; j++) {
                p1 = ln1.pt[i];
                p2 = ln1.pt[j];
                if (p1 == p2) {
                    k = 1;
                    continue;
                }

                for (k = 1; k <= cons_no; k++)
                    for (l = 1; l <= cons_no; l++) {
                        //orthocenter k p1 p2 l  /
                        if (k < l && p1 < p2 && k != p1 && k != p2 && l != p1 && l != p2 &&
                                xperp(p1, k, p2, l) && xperp(p2, k, p1, l) &&
                                ln_less((fadd_ln(k, l)), ln1)) {
                            co_db.nx = null;
                            add_codb(CO_ORTH, k, p1, p2, l, 0, 0, 0, 0);
                            return (mk_felim(v, pplus(trim_f(k, l, c, d), get_n(1L)), get_n(1L), RF_ORTH));
                        }

                        /*    incenter (p1) p2 k l */
                        /*gprint("cen1: %s %s %s %s\r\n",ANAME(p1),ANAME(p2),ANAME(k),ANAME(l)); */
                        if (k < l && k != p1 && k != p2 && l != p1 && l != p2 &&
                                xacong(k, l, p1, p1, l, p2) && xacong(l, k, p1, p1, k, p2) &&
                                ln_less((fadd_ln(k, p2)), ln1) &&
                                ln_less((fadd_ln(k, l)), ln1) &&
                                ln_less((fadd_ln(p1, k)), ln1) &&
                                ln_less((fadd_ln(p1, l)), ln1)) {
                            co_db.nx = null;
                            add_codb(CO_INCENT, p1, p2, k, l, 0, 0, 0, 0);
                            return (mk_felim(v, pplus4(trim_f(k, l, k, p1),
                                    trim_f(l, p1, l, k),
                                    trim_f(p2, k, c, d),
                                    get_n(1L)),
                                    get_n(1L), RF_20));
                        }
                    }
                m1:
                k = 1;
            }
        return (null);
    }

    el_term elim_f_ans(var v, int a, int b, int c, int d) {
        l_line l1, l2, ln0, ln1, ln2;
        angles as;

        l1 = fadd_ln(a, b);
        l2 = fadd_ln(c, d);

        for (as = all_as.nx; as != null; as = as.nx) {
            if (as.type == 0) continue;
            if ((l1 == as.l1) && (l2 == as.l2)) {
                ln1 = as.l3;
                ln2 = as.l4;
            } else if ((l1 == as.l2) && (l2 == as.l1)) {
                ln1 = as.l4;
                ln2 = as.l3;
            } else if ((l1 == as.l1) && (l2 == as.l3)) {
                ln1 = as.l2;
                ln2 = as.l4;
            } else if ((l1 == as.l3) && (l2 == as.l1)) {
                ln1 = as.l4;
                ln2 = as.l2;
            } else if ((l1 == as.l2) && (l2 == as.l4)) {
                ln1 = as.l1;
                ln2 = as.l3;
            } else if ((l1 == as.l4) && (l2 == as.l2)) {
                ln1 = as.l3;
                ln2 = as.l1;
            } else if ((l1 == as.l3) && (l2 == as.l4)) {
                ln1 = as.l1;
                ln2 = as.l2;
            } else if ((l1 == as.l4) && (l2 == as.l3)) {
                ln1 = as.l2;
                ln2 = as.l1;
            } else
                continue;

            if (ln_less(ln1, l1) && ln_less(ln2, l1)) {
                co_db.nx = null;
                add_codb(CO_ACONG, a, b, c, d, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1]);
                return (mk_felim(v, trim_fl(ln1, ln2), get_n(1L), RF_21));
            }
        }
        for (as = all_as.nx; as != null; as = as.nx) {
            if (l1 == as.l1 && ln_less(as.l2, l1) && ln_less(as.l3, l1) && ln_less(as.l4, l1)) {
                ln0 = as.l2;
                ln1 = as.l3;
                ln2 = as.l4;
            } else if (l1 == as.l2 && ln_less(as.l1, l1) && ln_less(as.l3, l1) && ln_less(as.l4, l1)) {
                ln0 = as.l1;
                ln1 = as.l4;
                ln2 = as.l3;
            } else if (l1 == as.l3 && ln_less(as.l1, l1) && ln_less(as.l2, l1) && ln_less(as.l4, l1)) {
                ln0 = as.l4;
                ln1 = as.l1;
                ln2 = as.l2;
            } else if (l1 == as.l4 && ln_less(as.l1, l1) && ln_less(as.l2, l1) && ln_less(as.l3, l1)) {
                ln0 = as.l3;
                ln1 = as.l2;
                ln2 = as.l1;
            } else
                continue;

            co_db.nx = null;
            add_codb(CO_ACONG, a, b, ln0.pt[0], ln0.pt[1],
                    ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1]);
            return (mk_felim(v, pplus(trim_fl(ln0, l2), trim_fl(ln1, ln2)), get_n(1L), 0));   //?????
        }
        return (null);
    }

    el_term elim_d(var v) {
        l_line ln1, ln2;
        a_cir cr, cr1;
        int o, p1, p2, p3, p4, p5, a, b, c, d;

        a = v.pt[0];
        b = v.pt[1];
        c = v.pt[2];
        d = v.pt[3];
        ln1 = fadd_ln(v.pt[0], v.pt[1]);
        ln2 = fadd_ln(v.pt[2], v.pt[3]);
        cr = all_cir.nx;
        while (cr != null) {
            if (cr.type == 0) {
                cr = cr.nx;
                continue;
            }

            o = cr.o;
            if (o == 0) {
                cr = cr.nx;
                continue;
            }
            /* gprint("45 du\r\n"); */
            p1 = inter_lc(ln1, cr);
            if (p1 != 0 && on_ln(o, ln1)) {
                if (on_ln(p1, ln2) && (p2 = inter_lc1(ln2, cr, p1)) != 0 && xperp(o, p1, o, p2)) {
                    co_db.nx = null;
                    add_codb(CO_PERP, o, p1, o, p2, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    return (mk_felim(v, get_n(1L), get_n(2L), RF_22));
                }
                for (int i = 0; i <= cr.no; i++) {
                    p2 = cr.pt[i];
                    if (xperp(o, p1, o, p2) && ln_less(fadd_ln(p1, p2), ln1)) {
                        co_db.nx = null;
                        add_codb(CO_CYCLIC, o, p1, o, p2, 0, 0, 0, 0);
                        add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                        return (mk_felim(v, pplus(ptimes(get_n(2L), trim_f(p1, p2, c, d)),
                                get_n(1L)),
                                get_n(2L), RF_TT));
                    }
                }
            }

            /* gprint("isoceles \r\n"); */
            p1 = inter_lc(ln1, cr);
            p2 = inter_lc1(ln1, cr, p1);
            if (p1 != 0 && p2 != 0 &&
                    ln_less(fadd_ln(o, p1), ln1) && ln_less(fadd_ln(o, p2), ln1))
                if (xpara(o, p1, c, d)) {
                    co_db.nx = null;
                    add_codb(CO_PARA, o, p1, c, d, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    return (mk_felim(v, trim_f(o, p2, o, p1), get_n(2L), RF_TT2));
                } else {
                    co_db.nx = null;
                    add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    return (mk_felim(v, pplus(trim_f(o, p1, o, p2),
                            ptimes(get_n(2L), trim_f(o, p2, c, d))),
                            get_n(2L), RF_PPO));
                }

            /* gprint("iso + cir \r\n"); */
            if (!on_ln(o, ln1)) {
                cr = cr.nx;
                continue;
            }

            cr1 = all_cir.nx;
            while (cr1 != null) {
                if (cr.type == 0) {
                    cr = cr.nx;
                    continue;
                }
                p1 = inter_cc(cr, cr1);
                p2 = inter_cc1(cr, cr1, p1);
                if (p1 == 0 || p2 == 0) {
                    cr1 = cr1.nx;
                    continue;
                }
                if (!on_cir(o, cr1)) {
                    cr1 = cr1.nx;
                    continue;
                }
                p3 = inter_lc1(ln1, cr1, o);
                if (p3 == 0) {
                    cr1 = cr1.nx;
                    continue;
                }
                p4 = 0;
                if (on_ln(p3, ln2)) p4 = inter_lc1(ln2, cr1, p3);

                if (p4 == p1 && xperp(p3, p1, p3, p2)) {
                    co_db.nx = null;
                    add_codb(CO_PERP, p3, p1, p3, p2, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, 0, o, p1, p2, p3, 0, 0, 0);
                    add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                    add_codb(CO_COLL, c, d, p1, 0, 0, 0, 0, 0);
                    return (mk_felim(v, get_n(1L), get_n(2L), RF_26));
                } else if (p4 == p2 && xperp(p3, p1, p3, p2)) {
                    co_db.nx = null;
                    add_codb(CO_PERP, p3, p1, p3, p2, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, 0, o, p1, p2, p3, 0, 0, 0);
                    add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                    add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                    add_codb(CO_COLL, c, d, p2, 0, 0, 0, 0, 0);
                    return (mk_felim(v, get_n(1L), get_n(2L), RF_26));
                }

                /* gprint("(iso + cir) double \r\n"); */
                for (int i = 0; i <= cr1.no; i++) {
                    p5 = cr1.pt[i];
                    if (p5 != p1 && p5 != p2 &&
                            ln_less(fadd_ln(p5, p1), ln1) &&
                            ln_less(fadd_ln(p5, p2), ln1)) {
                        if (p4 == p1) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, 0, o, p1, p2, p3, p5, 0, 0);
                            add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                            add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                            add_codb(CO_COLL, c, d, p1, 0, 0, 0, 0, 0);
                            return (mk_felim(v, trim_f(p5, p2, p5, p1), get_n(2L), RF_DM2));
                        } else if (p4 == p2) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, 0, o, p1, p2, p3, p5, 0, 0);
                            add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                            add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                            add_codb(CO_COLL, c, d, p2, 0, 0, 0, 0, 0);
                            return (mk_felim(v, trim_f(p5, p1, p5, p2), get_n(2L), RF_DM2));
                        }
                        /* check here */
                        else if (ln_less(fadd_ln(p3, p1), ln1)) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, 0, o, p1, p2, p3, p5, 0, 0);
                            add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                            add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                            return (mk_felim(v, pplus(trim_f(p5, p2, p5, p1),
                                    ptimes(get_n(2L), trim_f(p3, p1, c, d))),
                                    get_n(2L), RF_CY));
                        }
                        /* check here */
                        else if (ln_less(fadd_ln(p3, p2), ln1)) {
                            co_db.nx = null;
                            add_codb(CO_CYCLIC, 0, o, p1, p2, p3, p5, 0, 0);
                            add_codb(CO_CYCLIC, o, p1, p2, 0, 0, 0, 0, 0);
                            add_codb(CO_COLL, o, a, b, p3, 0, 0, 0, 0);
                            return (mk_felim(v, pplus(trim_f(p5, p1, p5, p2),
                                    ptimes(get_n(2L), trim_f(p3, p2, c, d))), get_n(2L), RF_CY));
                        }
                    }
                }
                cr1 = cr1.nx;
            }
            cr = cr.nx;
        }

        angles as = all_as.nx;
        ln2 = fd_ln(c, d);

        l_line l1, l2, l3, l4;


        while (as != null) {
            if (as.type != 0) {
                l1 = as.l1;
                l2 = as.l2;
                l3 = as.l3;
                l4 = as.l4;

                if (ln1 == l2 && ln2 == l1 || ln1 == l4 && ln2 == l3) {
                    l_line lx = l1;
                    l1 = l2;
                    l2 = lx;
                    lx = l3;
                    l3 = l4;
                    l4 = lx;
                }

                if (ln1 == l1 && ln2 == l2 || ln1 == l3 && ln2 == l4) {
                    if (l2 == l3 && ln_less(l1, l2) && ln_less(l4, l3)) {
                        add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
                        return mk_felim(v, trim_f(l1.pt[0], l1.pt[1], l4.pt[0], l4.pt[1]), get_n(2L), 2, 0);
                    } else if (l1 == l4 && ln_less(l2, l1) && ln_less(l3, l4)) {
                        add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
                        return mk_felim(v, trim_f(l3.pt[0], l3.pt[1], l2.pt[0], l2.pt[1]), get_n(2L), 2, 0);
                    }

                }
            }
            as = as.nx;
        }
        return (null);
    }

    el_term elim_t(var v) {
        l_line ln1, ln2;
        a_cir cr;
        int p1, p2, p3;
        angles as;

        ln1 = fadd_ln(v.pt[0], v.pt[1]);
        ln2 = fadd_ln(v.pt[2], v.pt[3]);
        p1 = inter_ll(ln1, ln2);
        if (p1 == 0) return (null);

        for (cr = all_cir.nx; cr != null; cr = cr.nx) {
            if (cr.type == 0) {
                continue;
            }
            if (cr.o != p1) continue;
            p2 = inter_lc1(ln1, cr, p1);
            p3 = inter_lc1(ln2, cr, p1);
            if (p2 == 0 || p3 == 0) continue;
            if (xcir2(p2, p1, p3) || xcir2(p3, p1, p2)) {
                co_db.nx = null;
                add_codb(CO_PET, p1, p2, p3, 0, 0, 0, 0, 0);
                return (mk_felim(v, get_n(0L), get_n(3L), RF_CY2));
            }
        }
        for (as = all_as.nx; as != null; as = as.nx) {
            if (as.sa == 60 && (onl_as(ln1, ln2, as) || onl_as(ln2, ln1, as))) {
                co_db.nx = null;
                add_codb(-(CO_ACONG), 60, v.pt[0], v.pt[1], v.pt[2], v.pt[3], 0, 0, 0);
                return (mk_felim(v, get_n(0L), get_n(3L), 0));
            }
        }
        return (null);
    }

    el_term elim_tri(var v) {
        int a = v.pt[0];
        int b = v.pt[1];
        int c = v.pt[2];
        int d = v.pt[3];
        l_line ln1 = fadd_ln(v.pt[0], v.pt[1]);
        l_line ln2 = fadd_ln(v.pt[2], v.pt[3]);

        int o = inter_lls(ln1, ln2);
        if (o != 0) {
            for (int i = 0; i <= ln1.no; i++)
                for (int j = 0; j <= ln2.no; j++) {
                    if (ln1.pt[i] != o && ln2.pt[j] != o) {
                        if (xcong(o, ln1.pt[i], ln1.pt[i], ln2.pt[j]) && xperp(o, ln1.pt[i], ln1.pt[i], ln2.pt[j])) {
                            add_codb(CO_PERP, o, ln1.pt[i], ln1.pt[i], ln2.pt[j], 0, 0, 0, 0);
                            add_codb(CO_CONG, o, ln1.pt[i], ln1.pt[i], ln2.pt[j], 0, 0, 0, 0);
                            return mk_felim(v, get_n(1), get_n(2), 2, 0);
                        } else
                        if (xcong(o, ln2.pt[j], ln2.pt[j], ln1.pt[i]) && xperp(o, ln2.pt[j], ln2.pt[j], ln1.pt[i])) {
                            add_codb(CO_PERP, o, ln2.pt[j], ln2.pt[j], ln1.pt[i], 0, 0, 0, 0);
                            add_codb(CO_CONG, o, ln2.pt[j], ln2.pt[j], ln1.pt[i], 0, 0, 0, 0);
                            return mk_felim(v, get_n(1), get_n(2), 2, 0);
                        }
                    }
                }
        }
        return null;
    }

    /////froem area
    xterm eprem(xterm p, el_term e) {
        xterm p1, p2, p3;
        if (e == null) return p;
        p2 = get_n(1L);
        if (e.p1 == null) {
            p1 = prem_var(p, e.p2, e.v);
            p3 = init_v(cp_poly(e.p2), e.v);
            if (eq_poly(p3, p2)) {
                init_deg = 0;
            }
            put_p(p3);
        } else {
            p1 = get_m(e.v);
            p1 = ptimes(p1, cp_poly(e.p2));
            p1 = pminus(p1, cp_poly(e.p1));
            p1 = prem_var(p, p1, e.v);
            if (eq_poly(e.p2, p2)) {
                init_deg = 0;
            }
        }
        put_x(p2);
        return (p1);
    }

    //////////////////////////////////////////////////////////////////////
    // print
    boolean pr_elim = true;

    public boolean canExpressedAsFullAngle() {
        return proof.nx != null;
    }

    public boolean isProvedTrue() {
        if (last_pr != null && last_pr.isZero()) return true;
        return false;
    }

    public gr_term getFullAngleProofHead() {

        gr_term gt = proof.nx;
        if (gt == null) return null;
        while (gt != null) {
            if (gt.ps1 != null)
                myprint_p1(gt.ps1.p, true);
            el_term el = gt.el;
            if (el != null) {
                myprint_p1(el.p1, true);
                myprint_p1(el.p2, true);
                myprint_p1(el.p, true);
                cond co = el.co;
                while (co != null) {
                    this.show_pred(co);
                    do_pred(co);
                    //forw_pred(co);
                    co = co.nx;
                }
            }
            if (el != null) {
                el = el.et;
                while (el != null) {
                    myprint_p1(el.p1, true);
                    myprint_p1(el.p2, true);
                    myprint_p1(el.p, true);
                    cond co = el.co;
                    while (co != null) {
                        this.show_pred(co);
                        do_pred(co);
                        // forw_pred(co);
                        co = co.nx;
                    }
                    el = el.nx;
                }
            }

            gt = gt.nx;
        }

        return proof.nx;
    }

    public boolean print_prooftext()  // added   MAY 4th 2006
    {
        char mk = 0;

        gr_term gr1 = proof.nx;
        if (gr1 == null) return false;

        while (gr1 != null) {
            if (gr1.c == -1) {
                this.setPrintToString();
                dterm dt = gr1.ps;
                print_ps(dt, mk);
                dt.text = this.getPrintedString();
            } else if (gr1.c == -2) {
            } else if (gr1.c == 0) {
            } else {
                el_term el = gr1.el;
                print_elims(el, mk);
            }

            if (gr1.c == 0) {
            } else {
            }

            this.setPrintToString();
            print_gr(gr1, mk);
            gr1.text = this.getPrintedString();
            gr1 = gr1.nx;
        }
        return true;
    }

    void print_proof(char mk) {
        gr_term gr1 = proof.nx;

        if (gr1 == null) {
            gprint(Cm.s2220);
            return;
        }
        gprint(Cm.s2221);
        //docc(3);
        gprint(Cm.s2222);
        gprint("  ");
        { // print the gr as an equation
            //for area, vector, and full-angle
            if (num_zop(gr1.c1)) {
                gprint("0");
            } else if (gr1.ps1 == null) {
                //sprintf(txt, "%ld", gr1.c1);
                gprint("" + gr1.c1);
            } else if (num_unit(gr1.c1))
                print_ps(gr1.ps1, mk);
            else {
                num_show(gr1.c1);
                print_ps(gr1.ps1, mk);
            }
            gprint(" = ");
            if (num_zop(gr1.c2)) {
                gprint("0");
            } else if (gr1.ps2 == null) {
                //sprintf(txt, "%ld", gr1.c2);
                gprint("" + gr1.c2);
            } else if (num_unit(gr1.c2))
                print_ps(gr1.ps2, mk);
            else {
                num_show(gr1.c2);
                print_ps(gr1.ps2, mk);
            }
        }

        gprint(Cm.s2072);
        gprint("\r\n\r\n");

        gprint("\r\n");
        while (gr1 != null) {
            if (pr_elim) {
                if (gr1.c == -1) {
                    gprint(Cm.s2223);
                    print_ps(gr1.ps, mk);
                    gprint("\r\n\r\n");
                } else if (gr1.c == -2) {
                } else if (gr1.c == 0) {
                } else {
                    print_elims(gr1.el, mk);
                    gprint("\r\n");
                }
            }
            if (gr1.c == 0) {
            } else {
                gprint(" = ");
            }

            print_gr(gr1, mk);
            gprint("\r\n");
            gr1 = gr1.nx;
        }
        gprint("\r\n");
    }


    void print_elims(el_term el, char mk) {
//        gprint(Cm.s2224);
        if (el == null)
            return;

        this.setPrintToString();
        print_elim(el, mk);
        el.setText(this.getPrintedString());

        print_elims(el.et, mk);

//        gprint("\r\n");
        for (el = el.nx; el != null; el = el.nx) {
            this.setPrintToString();
            print_elim(el, mk);
            el.setText(this.getPrintedString());
            print_elims(el.et, mk);
        }
    }


    static gr_term el_gr = new gr_term();
    static dterm el_d1 = new dterm();
    static dterm el_d2 = new dterm();

    void print_elim(el_term e, char mk) {
        xterm p1, p2;
        var v;
        if (e == null) {
            return;
        }
        v = e.v;
        if (v.nm < 0) return;

        if (e.p1 == null) {
            p2 = e.p2;
            if (pdeg(p2, e.v) == 1) {
                p1 = pminus(get_m(e.v), cp_poly(p2));
                print_var(e.v, mk);
                gprint(" = ");
                print_p(p1, mk);
                put_p(p1);
            } else {
                print_p(p2, mk);
                gprint(" = 0 ( ");
                gprint(Cm.s2226);
                print_var(e.v, mk);
                gprint(")");
            }
        } else if (pro_type == 0) {
            if (!unitp(e.p2)) print_p(e.p2, mk);
            print_var(e.v, mk);
            gprint(" = ");
            print_p(e.p1, mk);
        } else {
            print_var(e.v, mk);
            gprint(" = ");
            p1 = e.p1;
            p2 = e.p2;
            //  pprint(p1);
            // pprint(p2);
            if (p1.var == null) {
                el_gr.c1 = p1.c;
                el_gr.ps1 = null;
            } else {
                el_gr.c1 = mk_num(1L);
                el_d1.deg = 1;
                el_d1.p = e.p1;
                el_d1.nx = null;
                el_gr.ps1 = el_d1;
            }
            if (p2.var == null) {
                el_gr.c2 = p2.c;
                el_gr.ps2 = null;
            } else {
                el_gr.c2 = mk_num(1L);
                el_d2.deg = 1;
                el_d2.p = e.p2;
                el_d2.nx = null;
                el_gr.ps2 = el_d2;
            }
            print_gr(el_gr, mk);
        }
        if (pro_type == 0 && e.co != null) {
            gprint(" (");
            show_cos(e.co);
            gprint(")");
        }
    }

    void print_all_vars() {
        var e1;

        gprint(Cm.s2225);
        e1 = all_var.nx;
        while (e1 != null) {
            print_var(e1, 0);
            gprint("\r\n");
            e1 = e1.nx;
        }
    }

    boolean rgr(gr_term gr) {
        return (rps(gr.ps1) && rps(gr.ps2));
    }

    boolean rps(dterm ps1) {
        dterm ps2;
        xterm p1;
        var v1;
        while (ps1 != null) {
            p1 = ps1.p;
            v1 = p1.var;
            if (v1 != null) {
                if (v1.nm != 1) return (false);
                ps2 = p1.ps;
                if (ps2.nx != null) return (false);
                if (!(rps(ps2))) return (false);
            }
            ps1 = ps1.nx;
        }
        return (true);
    }


    void print_gr(gr_term gr, char mk) {
        boolean rg;
        long n;

        if (num_zop(gr.c2)) {
            if (num_zop(gr.c1)) {
                gprint("0");
            } else if (gr.ps1 == null) {
                gprint("" + gr.c1);
            } else {
                if (num_unit(gr.c1)) {
                } else if (num_unit(gr.c1)) {
                    gprint("-");       ///////////////////////////////////????????????????????????
                } else {
                    num_show(gr.c1);
                }
                print_ps(gr.ps1, mk);
            }
        } else if (gr.ps1 == null && gr.ps2 == null) {
            if (num_unit(gr.c2)) {
                num_show(gr.c1);
            } else if (num_nunit(gr.c2)) {
                n = num_neg(gr.c1);
                num_show(n);
            } else
                show_num2(gr.c1, gr.c2, mk);
        } else if (gr.ps2 == null) {
            if (num_unit(gr.c1) && num_unit(gr.c2)) {
            } else if (num_unit(gr.c1) && num_nunit(gr.c2)) {
                gprint("-");
            } else if (num_unit(gr.c2)) {
                num_show(gr.c1);
            } else if (num_nunit(gr.c2)) {
                n = num_neg(gr.c1);
                num_show(n);
            } else {
                show_num2(gr.c1, gr.c2, mk);
            }
            print_ps(gr.ps1, mk);
        } else {
            rg = rgr(gr);
            if (gr.ps1 == null) {
                num_show(gr.c1);
            } else {
                if (num_unit(gr.c1)) { /* c_mark = 0; */
                } else if (num_nunit(gr.c1)) {
                    gprint("-");
                } else {
                    num_show(gr.c1);
                }
                print_ps(gr.ps1, mk);
            }
            gprint(" / ");
            if (gr.ps2 == null) {
                num_show(gr.c2);
            } else {
                if (num_unit(gr.c2)) { /* c_mark = 0; */
                } else if (num_nunit(gr.c2)) {
                    gprint("-");
                } else {
                    num_show(gr.c2);
                }
                print_ps(gr.ps2, mk);
            }
        }
    }

    void show_num2(long c1, long c2, char mk) {
        num_show(c1);
        gprint("/");
        num_show(c2);
    }

    void print_ps(dterm dp1, char mk) {
        if (dp1 == null)
            gprint("");
        else {
            int k = 0;
            dterm ps1 = dp1;
            while (ps1 != null) {
                k++;
                ps1 = ps1.nx;
            }
            while (dp1 != null) {
                if (dp1.deg == 1) {
                    if (k >= 0 && pro_type != PRO_FULL && ((plength(dp1.p) > 1) || (lcc(dp1.p) < 0))) {
                        gprint("(");
                        print_p(dp1.p, mk);
                        gprint(")");
                    } else
                        print_p(dp1.p, mk);
                } else {
                    gprint("(");
                    print_p(dp1.p, mk);
                    //sprintf(txt, ")^{%d}", dp1.deg);
                    gprint(")^{" + dp1.deg + "}");
                }
                dp1 = dp1.nx;
            }
        }
    }

    boolean chord_p(xterm p) {
        var v;
        if (npoly(p)) return (true);
        v = p.var;
        return (v.nm == 5);
    }


    ///////////////////////////////////
    Vector getAllterms(xterm p1) {

        Vector list = new Vector();
        if (p1 == null)
            return list;
        if (p1.var == null) return list;
        list.add(p1);
        return list;
    }


    public void myprint_p1(xterm p1, boolean first) {

        this.setPrintToString();
        dterm dp1, dp2;
        xterm xp1;

        if (p1 == null)
            return;
        if (p1.var == null) {
            {
                if (!first && p1.c > 0.)
                    gprint(" + ");
                if (p1.c < 0)
                    gprint(" - ");
                gprint(Cm.s2078 + "[");
                num_show(Math.abs(p1.c));
                gprint("]");
                dp2 = null;
            }
        } else {
            dp1 = p1.ps;
            xp1 = dp1.p;
            if (numberp(xp1)) {
                if (nunitp(xp1))
                    gprint(" - ");
                else {
                    if (!unitp(xp1)) {
                        if (xp1.c > 0)
                            gprint(" + ");
                        num_show(xp1.c);
                    } else if (!first) gprint(" + ");
                }
            }
            print_var(p1.var, (char) 0);
            dp2 = dp1.nx;
        }

        String s = this.getPrintedString();
        p1.sd = s;

        if (dp2 != null)
            myprint_p1(dp2.p, false);
    }

    gr_term mk_el_gr(el_term el) {
//        gr_term gr1 = mk_gr(mk_num(1L), get_dt(1, p1, null), mk_num(0L), null, 99, null);
//
//        el_term e1 = el.et;
//        while (e1 != null) {
//            xterm p1 = get_m(el.v);
//            p1 = eprem(cp_poly(p1), e1);
//            fpoly(p1);
//        }
//        return gr;
        return null;
    }

    int getvarNum() {
        var v = all_var.nx;
        int t = 0;
        while (v != null) {
            v = v.nx;
            t++;
        }
        return t;
    }

    int getlnNum() {
        l_line ln = all_ln.nx;
        int t = 0;
        while (ln != null) {
            t++;
            ln = ln.nx;
        }
        return t;
    }

    boolean var_reOrder(var v) {
        int p1, p2, p3, p4, p;
        boolean sr = false;
        p1 = v.pt[0];
        p2 = v.pt[1];
        p3 = v.pt[2];
        p4 = v.pt[3];

        if (p1 < p2) {
            p = p1;
            p1 = p2;
            p2 = p;
        }
        if (p3 < p4) {
            p = p3;
            p3 = p4;
            p4 = p;
        }
        if (p1 < p3) {
            sr = true;
            p = p1;
            p1 = p3;
            p3 = p;
            p = p2;
            p2 = p4;
            p4 = p;
        } else if ((p1 == p3) && (p2 < p4)) {
            sr = true;
            p = p2;
            p2 = p4;
            p4 = p;
        }
        {
            v.pt[0] = p1;
            v.pt[1] = p2;
            v.pt[2] = p3;
            v.pt[3] = p4;
        }
        return sr;
    }

    xterm trim_full(int p1, int p2, int p3, int p4) {
        int t = 0;

        l_line ln1 = fd_ln(p1, p2);
        l_line ln2 = fd_ln(p3, p4);
        if (ln1 != null && ln2 != null && (t = inter_ll(ln1, ln2)) != 0) {
            if (p1 != t && p2 != t)
                p2 = t;
            if (p3 != t && p4 != t)
                p4 = t;
        } else if (ln1 == null && ln2 != null) {

        }
        return trim_f(p1, p2, p3, p4);
    }

    public static void set_showdetai(boolean d) {
        show_detail = d;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////NDGS
    /**
     * **********************************************
     */
    // V1.      Constructions.
    // V2.      Initial NDGS
    // V3.      Simplified.
    // V4       Final NDGS.
    public boolean isConstructionType(int type) {
        return (type > 0 && type < 50 || type > 100 && type < 150)
                && !freeCS(type);
    }

    public void get_ndgs(Vector v1, Vector v2, Vector v3, Vector v4) { // cndg

        int n = cns_no;
        for (int i = 1; i <= n; i++) {
            cons c = allcns[i];
            if (c != null && isConstructionType(c.type))
                v1.add(c);
        }

        vndgs.clear();
        init_ndgs(v2);          // Init NDGS.
        filter_ndg(v2, v3);     // Remove rudundent NDGS.
        ndg_deduction(v3, v4);
        filter_ndg(v4);
        filter_ndg(vndgs);

        parse_neq(v4);
        v4.clear();
        v4.addAll(vndgs);
    }


    protected cndg add_n_isotropic(int a, int b, Vector v1) {
        if (a == b)
            return null;

        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }

        if (APT(a) == null && APT(b) == null) return null;

        cndg n = new cndg();
        n.type = NDG_NON_ISOTROPIC;
        n.p[0] = a;
        n.p[1] = b;
        n.no = 1;
        add_ndgs(n, v1);
        return n;
    }

    protected cndg add_n_pt(int type, int a, int b, int c, int d, Vector v1) {
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (c > d) {
            int t = c;
            c = d;
            d = t;
        }

        if (a > c) {
            int t = a;
            a = c;
            c = t;
            t = b;
            b = d;
            d = t;
        }

        cndg n = null;

        if (type == NDG_PARA) {
            n = add_ndg_para(a, b, c, d);
        } else if (type == NDG_PERP) {
            n = add_ndg_perp(a, b, c, d);
        } else {
            n = new cndg();
            n.type = type;
            n.p[0] = a;
            n.p[1] = b;
            n.p[2] = c;
            n.p[3] = d;
            n.no = 3;
        }
        add_ndgs(n, v1);

        return n;
    }

    protected cndg add_n_coll(int a, int b, int c, Vector v1) {
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }

        if (a > c) {
            int t = a;
            a = c;
            c = t;
        }

        if (b > c) {
            int t = b;
            b = c;
            c = t;
        }

        cndg n = new cndg();
        n.type = NDG_COLL;
        n.p[0] = a;
        n.p[1] = b;
        n.p[2] = c;
        n.no = 2;
        add_ndgs(n, v1);
        return n;
    }

    public void init_ndgs(Vector v1) {
        cndg nd;

        int sz = v1.size();

        int n = cns_no;
        for (int i = 1; i <= n; i++) {
            cons c = allcns[i];
            switch (c.type) {
                case C_FOOT:
                    add_n_isotropic(c.ps[2], c.ps[3], v1);
                    break;
                case C_I_LL:
                    add_n_pt(NDG_PARA, c.ps[1], c.ps[2], c.ps[3], c.ps[4], v1);
                    break;
                case C_I_LB:
                    add_n_pt(NDG_PERP, c.ps[1], c.ps[2], c.ps[3], c.ps[4], v1);
                    break;
                case C_I_LP:
                    add_n_pt(NDG_PARA, c.ps[1], c.ps[2], c.ps[4], c.ps[5], v1);
                    break;
                case C_I_LT:
                    add_n_pt(NDG_PERP, c.ps[1], c.ps[2], c.ps[3], c.ps[4], v1);
                    break;
                case C_I_PP:
                    add_n_pt(NDG_PARA, c.ps[2], c.ps[3], c.ps[5], c.ps[6], v1);
                    break;

                case C_I_TT:
                    add_n_pt(NDG_PARA, c.ps[2], c.ps[3], c.ps[5], c.ps[6], v1);
                    break;
                case C_CIRCUM:
                case C_CENT:
                case C_ORTH:
                    add_n_coll(c.ps[1], c.ps[2], c.ps[3], v1);
                    break;
                case C_O_C:
                    break;
                case C_CIRCLE:
                    add_n_coll(c.ps[1], c.ps[2], c.ps[3], v1); ///...../...????
                    break;
                case C_PARALLELOGRAM:
                    add_coll_para(c, v1);
                    break;
                case C_I_CC:
                    nd = add_ndg_neq(c.ps[1], c.ps[3]);
                    this.add_ndgs(nd, v1);
                    break;
                case C_O_L:
                case C_I_LC:
                    nd = add_ndg_neq(c.ps[1], c.ps[2]);
                    this.add_ndgs(nd, v1);
                    break;

                    /////Special for aline.

                case C_I_PA: {
                    cndg dx = add_n_pt(NDG_PARA, c.ps[2], c.ps[3], c.ps[4], c.ps[5], v1);
                    if (dx != null)
                        dx.exists = true;


                    add_n_neq(c.ps[4], c.ps[5], vndgs);
                    add_n_neq(c.ps[6], c.ps[7], vndgs);
                    add_n_neq(c.ps[8], c.ps[9], vndgs);
                    xterm xt = pplus(trim_f(c.ps[2], c.ps[3], c.ps[4], c.ps[5]), trim_f(c.ps[6], c.ps[7], c.ps[7], c.ps[8]));
                    xt = add_deduction(xt);
                    addxtermndg(xt, vndgs);
                }
                break;
                case C_I_LA: {
                    cndg dx = add_n_coll(c.ps[0], c.ps[1], c.ps[3], v1);
                    if (dx != null)
                        dx.exists = true;

                    add_n_neq(c.ps[3], c.ps[4], vndgs);
                    add_n_neq(c.ps[5], c.ps[6], vndgs);
                    add_n_neq(c.ps[6], c.ps[7], vndgs);
                    xterm xt = pplus(trim_f(c.ps[1], c.ps[2], c.ps[3], c.ps[4]), trim_f(c.ps[7], c.ps[6], c.ps[6], c.ps[5]));
                    xt = add_deduction(xt);
                    addxtermndg(xt, vndgs);
                }
                break;
                case C_I_AA: {
                    cndg dx = add_n_pt(NDG_PARA, c.ps[0], c.ps[1], c.ps[0], c.ps[6], v1);
                    if (dx != null)
                        dx.exists = true;

                    add_n_neq(c.ps[1], c.ps[2], vndgs);
                    add_n_neq(c.ps[3], c.ps[4], vndgs);
                    add_n_neq(c.ps[4], c.ps[5], vndgs);
                    add_n_neq(c.ps[6], c.ps[7], vndgs);
                    add_n_neq(c.ps[8], c.ps[9], vndgs);
                    add_n_neq(c.ps[9], c.ps[10], vndgs);
                    xterm xt = pplus(trim_f(c.ps[3], c.ps[4], c.ps[4], c.ps[5]), trim_f(c.ps[1], c.ps[2], c.ps[6], c.ps[7]));
                    xt = pplus(xt, trim_f(c.ps[10], c.ps[9], c.ps[9], c.ps[8]));
                    xt = add_deduction(xt);
                    addxtermndg(xt, vndgs);
                }
                break;
            }

            if (sz < v1.size()) {
                sz = v1.size();
                cndg dd = (cndg) v1.get(v1.size() - 1);
                dd.dep = c;
            }
        }
    }


    public void add_n_neq(int a, int b, Vector v1) {
        cndg nd = add_ndg_neq(a, b);
        this.add_ndgs(nd, v1);
    }

    public void add_coll_para(cons cs, Vector v1) {
        int a, b;

        a = cs.ps[0];
        b = 0;
        for (int i = 0; i < 4; i++) {
            if (a < cs.ps[i]) {
                a = cs.ps[i];
                b = i;
            }
        }

        int c1, c2, c3;
        c1 = c2 = c3 = -1;

        for (int i = 0; i < 4; i++) {
            if (c1 < 0 && i != b)
                c1 = cs.ps[i];
            else if (c2 < 0 && i != b)
                c2 = cs.ps[i];
            else if (c3 < 0 && i != b)
                c3 = cs.ps[i];
        }

        add_n_coll(c1, c2, c3, v1);
    }

    public void angle_deduction(cndg c, Vector v4) {
        if (c == null)
            return;

        if (ck_allFree(c)) {  // all free Points.
            cndg d = new cndg(c);
            v4.add(d);
            return;
        }

        xterm xt = null;

        switch (c.type) {
            case NDG_COLL:
                xt = add_deduction(trim_f(c.p[0], c.p[1], c.p[1], c.p[2]));
                this.addxtermndg(xt, v4);
                xt = add_deduction(trim_f(c.p[0], c.p[2], c.p[2], c.p[1]));
                this.addxtermndg(xt, v4);
                xt = add_deduction(trim_f(c.p[1], c.p[0], c.p[0], c.p[2]));
                this.addxtermndg(xt, v4);
                add_neqTo(c.p[0], c.p[1], v4);
                add_neqTo(c.p[0], c.p[2], v4);
                add_neqTo(c.p[1], c.p[2], v4);
                break;
            case NDG_PARA:
                xt = add_deduction(trim_f(c.p[0], c.p[1], c.p[2], c.p[3]));
                this.addxtermndg(xt, v4);
                add_neqTo(c.p[0], c.p[1], v4);
                add_neqTo(c.p[2], c.p[3], v4);
                break;
            case NDG_PERP:
                xt = add_deduction(trim_f(c.p[0], c.p[1], c.p[2], c.p[3]));
                this.addxtermndg(xt, v4);
                add_neqTo(c.p[0], c.p[1], v4);
                add_neqTo(c.p[2], c.p[3], v4);
                break;
            case NDG_NEQ:
            case NDG_NON_ISOTROPIC:
                cndg d1 = new cndg(c);
                this.add_ndgs(d1, v4);
                break;
        }
    }


    protected void add_deduction(int a, int b, int c, int d, Vector v4) {
        xterm x = trim_f(a, b, c, d);
        xterm x1 = add_deduction(x);
        if (pzerop(pminus(cp_poly(x), cp_poly(x1)))) {

        } else {

        }

    }

    protected void add_neqTo(int a, int b, Vector v4) {
        for (int i = 0; i < v4.size(); i++) {
            cndg d = (cndg) v4.get(i);
            if (d.type == NDG_NEQ || d.type == NDG_NON_ISOTROPIC) {
                if (d.contain(a) && d.contain(b))
                    return;
            } else if (d.type == NDG_COLL) {
                if (d.contain(a) && d.contain(b))
                    return;
            } else if (d.type == NDG_PARA || d.type == NDG_PERP) {
                if (d.p[0] == a && d.p[1] == b || d.p[0] == b && d.p[1] == a
                        || d.p[2] == a && d.p[3] == b || d.p[2] == b && d.p[3] == a)
                    return;
            }
        }
        cndg d = new cndg();
        d.type = NDG_NEQ;
        d.no = 1;
        d.p[0] = a;
        d.p[1] = b;
        add_ndgs(d, v4);
    }

    protected boolean ck_allFree(cndg d) {
        if (d == null)
            return true;
        for (int i = 0; i <= d.no; i++) {
            if (!freeCSP(d.p[i]))
                return false;
        }
        return true;
    }

    protected xterm add_deduction(xterm x) {

        if (x.var == null)
            return x;

        xterm xt = angle_deduction(x);
        xt = final_deduction(xt);
        return xt;
    }

    protected xterm final_deduction(xterm p1) {
        if (p1 == null)
            return p1;

        if (fcc(p1) < 0)
            p1 = ptimes(get_n(-1), p1);

        return p1;
    }

    protected void filter_ndg(Vector v4) {

        for (int i = 0; i < v4.size(); i++) {
            cndg d = (cndg) v4.get(i);
            for (int j = i + 1; j < v4.size(); j++) {
                cndg nx = (cndg) v4.get(j);
                if (ndg_eq(d, nx) || ndg_less(nx, d)) {
                    v4.remove(j);
                    i = -1;
                    break;
                } else if (ndg_less(d, nx)) {
                    v4.remove(j);
                    v4.remove(i);
                    v4.add(i, nx);
                    i = -1;
                    break;
                }
            }
        }

    }

    protected void filter_ndg(Vector v2, Vector v3) {

        for (int i = 0; i < v2.size(); i++) {
            cndg d = (cndg) v2.get(i);
            boolean added = false;
            for (int j = 0; j < v3.size(); j++) {
                cndg nx = (cndg) v3.get(j);

                if (ndg_eq(d, nx) || ndg_less(d, nx)) {
                    d.equ = nx;
                    added = true;
                    break;
                } else if (ndg_less(nx, d)) {
                    cndg d1 = new cndg(d);
                    d.equ = d1;
                    v3.remove(j);
                    v3.add(j, d1);
                    added = true;
                    break;
                }
            }
            if (!added) {
                cndg d1 = new cndg(d);
                d.equ = d1;
                v3.add(d1);
            }
        }
    }

    protected void add_ndgs(cndg d, Vector vlist) {
        if (d == null)
            return;

        get_ndgstr(d);


        vlist.add(d);
    }

    protected boolean ndg_less(cndg n1, cndg n2) {
        if (n1.type == NDG_NEQ || n1.type == NDG_NON_ISOTROPIC) {
            if (n2.type == NDG_COLL) {
                if (n2.contain(n1.p[0]) && n2.contain(n1.p[1]))
                    return true;
            }
        }
        return false;
    }

    protected boolean ndg_eq(cndg n1, cndg n2) {
        if (n1.type != n2.type) {
            if ((n1.type == NDG_NEQ || n1.type == NDG_NON_ISOTROPIC)
                    && (n2.type == NDG_NEQ || n2.type == NDG_NON_ISOTROPIC)) {
            } else
                return false;
        }

        if (n1.no != n2.no)
            return false;

        for (int i = 0; i <= n1.no; i++)
            if (n1.p[i] != n2.p[i])
                return false;

        return true;
    }

    protected void addxtermndg(xterm x, Vector v4) {
        if (x == null)
            return;
        xterm2ndg(x, v4);

        this.setPrintToString();
        xprint(x);
        x.sd = this.getPrintedString();
    }


    protected void xterm2ndg(xterm x, Vector vlist) {
        if (x == null || x.var == null)
            return;

        int n = x.getTermNumber();

        if (n == 0)
            xterm_1term(x, vlist);
        else if (n == 1)
            xterm_2term(x, vlist);

    }

    protected void xterm_1term(xterm x, Vector vlist) {
        long n = fcc(x);

        if (x.var == null) {
            ///////xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        } else if (n == 1) {
            cndg d = add_ndg_para(x.var);
            if (d != null)
                d.dep = x;
            add_ndgs(d, vlist);
        } else if (n == 2) {
            cndg d = add_ndg_para(x.var);
            if (d != null)
                d.dep = x;
            add_ndgs(d, vlist);
            d = add_ndg_perp(x.var);
            if (d != null)
                d.dep = x;
            add_ndgs(d, vlist);
        } else if (n == 3) {
            cndg d = add_ndg_triplePI(x.var);
            if (d != null)
                d.dep = x;
            add_ndgs(d, vlist);
        }
    }

    protected cndg add_ndg_triplePI(var v) {
        cndg n = new cndg();
        n.type = NDG_TRIPLEPI;

        n.p[0] = v.pt[0];
        n.p[1] = v.pt[1];
        n.p[2] = v.pt[2];
        n.p[3] = v.pt[3];
        n.no = 3;
        reorder22(n);

        return n;
    }


    protected void xterm_2term(xterm x, Vector vlist) {
        long n = fcc(x);
        if (x.ps != null) {
            dterm dx1 = x.ps.nx;
            if (dx1 != null) {
                xterm x1 = dx1.p;
                long n1 = fcc(x1);

                var v1 = x.var;
                var v2 = x1.var;

                if (v2 != null) {  // v2 != null   <A + <B = 0.
                    if (n1 < 0) {
                        if (v1.pt[2] == v2.pt[0] && v1.pt[3] == v2.pt[1]) {
                            cndg d = add_ndg_cong(v1.pt[0], v1.pt[1], v2.pt[2], v2.pt[3]);
                            if (d != null) {
                                d.dep = x;
                                add_ndgs(d, vlist);
                            }
                            d = this.add_ndg_coll(v1.pt[0], v1.pt[1], v1.pt[3]);
                            if (d != null) {
                                d.dep = x;
                                add_ndgs(d, vlist);
                            }


                        }
                    } else if (n1 > 0) {
                        if (v1.pt[2] == v2.pt[2] && v1.pt[3] == v2.pt[3]) {
                            cndg d = add_ndg_cong(v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1]);
                            if (d != null) {
                                d.dep = x;
                                add_ndgs(d, vlist);
                            }
                            d = this.add_ndg_coll(v1.pt[0], v1.pt[1], v1.pt[3]);
                            if (d != null) {
                                d.dep = x;
                                add_ndgs(d, vlist);
                            }
                        }
                    }
                } else  // <A + <1 = 0.
                {
                    if (x1.var == null & x1.c == 1) {
                        cndg d = add_ndg_perp(v1);
                        if (d != null)
                            d.dep = x;
                        add_ndgs(d, vlist);
                    }
                }


            }
        }


    }


    protected void reorder2(cndg n) {
        if (n.p[0] > n.p[1]) {
            int d = n.p[0];
            n.p[0] = n.p[1];
            n.p[1] = d;
        }
    }

    protected void reorder3(cndg n) {
        for (int i = 0; i < 2; i++) {

            int d = n.p[i];
            if (d > n.p[i + 1]) {
                n.p[i] = n.p[i + 1];
                n.p[i + 1] = d;
                i = -1;
            }
        }
    }

    protected void reorder22(cndg n) {
        if (n.p[0] > n.p[1]) {
            int d = n.p[0];
            n.p[0] = n.p[1];
            n.p[1] = d;
        }

        if (n.p[2] > n.p[3]) {
            int d = n.p[2];
            n.p[2] = n.p[3];
            n.p[3] = d;
        }

        if (n.p[0] > n.p[2]) {
            int d = n.p[0];
            n.p[0] = n.p[2];
            n.p[2] = d;
            d = n.p[1];
            n.p[1] = n.p[3];
            n.p[3] = d;
        }
    }

    protected cndg add_ndg_neq(int a, int b) {
        if (a == b)
            return null;
        cndg n = new cndg();
        n.type = NDG_NEQ;
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }

        n.p[0] = a;
        n.p[1] = b;
        n.no = 1;

        return n;
    }

    protected void parse_ndg_neq(int a, int b, Vector v4) {
        if (a == b)
            return;

        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }

        if (APT(a) == null && APT(b) == null) return;

        if (freeCSP(a) && freeCSP(b)) {
            cndg n = new cndg();
            n.type = NDG_NON_ISOTROPIC;
            n.p[0] = a;
            n.p[1] = b;
            n.no = 1;
            add_ndgs(n, v4);
        } else {
            xterm x = parseNEQ(a, b, v4);
            if (x != null)
                addxtermndg(x, v4);
        }
    }

    private xterm parseNEQ(int a, int b, Vector v2) {

        for (int i = 0; i < v2.size(); i++) {
            cndg n = (cndg) v2.get(i);
            if (n.type == NDG_NEQ || n.type == NDG_NON_ISOTROPIC) {
                {
                    xterm x1 = parseNEQ(a, b, n.p[0], n.p[1], v2);
                    if (x1 != null)
                        return x1;
                }
            } else if (n.type == NDG_COLL) {
                {
                    xterm x1 = parseNEQ(a, b, n.p[0], n.p[1], v2);
                    if (x1 != null)
                        return x1;
                    x1 = parseNEQ(a, b, n.p[0], n.p[2], v2);
                    if (x1 != null)
                        return x1;
                    x1 = parseNEQ(a, b, n.p[1], n.p[2], v2);
                    if (x1 != null)
                        return x1;
                }
            }
        }
        return null;
    }


    private xterm parseNEQ(int a, int b, int a1, int b1, Vector v2) {
        if (a == a1 || a == b1) return null;
        if (b == a1 || b == b1) return null;
        if (!freeCSP(a1) || !freeCSP(b1))
            return null;
        if (check_coll(a1, b1, a) || check_coll(a1, b1, b))
            return null;

        xterm x1 = pminus(trim_f(a, a1, a1, b1), trim_f(b, a1, a1, b1));
        xterm x2 = pminus(trim_f(a, b1, a1, b1), trim_f(b, b1, a1, b1));
        this.setPrintToScreen();

        this.pprint(x1);
        this.pprint(x2);

        x1 = angle_deduction(x1);
        x2 = angle_deduction(x2);
        if (eq_poly(x1, x2)) return x1;
        x2 = this.neg_poly(x2);
        if (eq_poly(x1, x2)) return x1;
        this.pprint(x1);
        this.pprint(x2);
        return null;
    }

    private void addPairsToList(Object o, Object o1, Vector v) {
        int n = v.size() / 2;
        for (int i = 0; i < n; i++) {
            if (o == v.get(2 * i) && o1 == v.get(2 * i + 1))
                return;
            if (o1 == v.get(2 * i) && o == v.get(2 * i + 1))
                return;
        }
        v.add(o);
        v.add(o1);
    }

    public int getFreeSemiFix(int index) {
        Pro_point pt = APT(index);
        if (pt == null)
            return 0;

        int n = 0;
        for (int i = 1; i <= cns_no; i++) {
            cons c = allcns[i];
            if (c.ps[0] == index && !freeCS(c.type)) {
                n++;
            }
        }

        return n;
    }

    public boolean freeCSP(int index) {
        Pro_point pt = APT(index);
        if (pt == null)
            return false;

        int type = 0;
        for (int i = 1; i <= cns_no; i++) {
            cons c = allcns[i];
            if (c.type != C_POINT && c.ps[0] == index) {
                type = c.type;
                break;
            }
        }

        return freeCS(type);
    }

    public boolean freeCS(int t) {
        return t == 0 || t == C_POINT || t == C_LINE || t == C_TRIANGLE ||
                t == C_QUADRANGLE || t == C_PENTAGON || t == C_POLYGON || t == C_CIRCLE;
    }

    protected cndg add_ndg_coll(int a, int b, int c) {
        cndg n = new cndg();
        n.type = NDG_COLL;

        n.p[0] = a;
        n.p[1] = b;
        n.p[2] = c;
        n.no = 2;
        reorder3(n);
        if (n.redundentPt())
            return null;
        else return n;
    }

    protected cndg add_ndg_cong(int a, int b, int c, int d) {
        cndg n = new cndg();
        n.type = NDG_CONG;

        n.p[0] = a;
        n.p[1] = b;
        n.p[2] = c;
        n.p[3] = d;
        n.no = 3;
        reorder22(n);
        if (n.redundentPt())
            return n;
        else return null;
    }


    protected cndg add_ndg_para(var v) {
        return add_ndg_para(v.pt[0], v.pt[1], v.pt[2], v.pt[3]);
    }

    protected cndg add_ndg_para(int a, int b, int c, int d) {
        cndg n = new cndg();
        n.type = NDG_PARA;

        n.p[0] = a;
        n.p[1] = b;
        n.p[2] = c;
        n.p[3] = d;
        n.no = 3;

        reorder22(n);
        if (n.redundentPt()) {

            cndg n1 = new cndg();
            n1.type = NDG_COLL;
            for (int i = 0; i < 4; i++)
                if (!n1.contain(n.p[i]))
                    n1.addAPt(n.p[i]);
            if (n1.no == 1)
                n1.type = NDG_NEQ;

            reorder3(n1);
            return n1;
        }

        return n;
    }

    protected cndg add_ndg_perp(var v) {
        return add_ndg_perp(v.pt[0], v.pt[1], v.pt[2], v.pt[3]);
    }

    protected cndg add_ndg_perp(int a, int b, int c, int d) {
        cndg n = new cndg();
        n.type = NDG_PERP;

        n.p[0] = a;
        n.p[1] = b;
        n.p[2] = c;
        n.p[3] = d;
        n.no = 3;
        reorder22(n);
        if (n.p[0] == n.p[2] && n.p[1] == n.p[3]) {
            n.type = NDG_NON_ISOTROPIC;
            n.no = 1;
            n.p[2] = n.p[3] = 0;
        }
        return n;
    }


    protected void get_ndgstr(cndg d) {
        String sd = "";
        switch (d.type) {
            case NDG_COLL:
                sd = ANAME(d.p[0]) + ", " + ANAME(d.p[1]) + ", " + ANAME(d.p[2]) + " are not collinear";
                break;
            case NDG_NEQ:
                sd = ANAME(d.p[0]) + " != " + ANAME(d.p[1]);
                break;
            case NDG_NON_ISOTROPIC:
                sd = ANAME(d.p[0]) + ANAME(d.p[1]) + " is non-isotropic";
                break;
            case NDG_PARA:
                sd = ANAME(d.p[0]) + ANAME(d.p[1]) + " is not parallel to " + ANAME(d.p[2]) + ANAME(d.p[3]);
                break;
            case NDG_PERP:
                sd = ANAME(d.p[0]) + ANAME(d.p[1]) + " is not perpendicular to " + ANAME(d.p[2]) + ANAME(d.p[3]);
                break;
            case NDG_CYCLIC:
                sd = ANAME(d.p[0]) + ", " + ANAME(d.p[1]) + ", " + ANAME(d.p[2]) + ", " + ANAME(d.p[3]) + " are not cyclic";
                break;
            case NDG_CONG:
                sd = "|" + ANAME(d.p[0]) + ANAME(d.p[1]) + "| != |" + ANAME(d.p[2]) + ANAME(d.p[3]) + "|";
                break;
            case NDG_TRIPLEPI: {
                int n = d.getRedundentPt();
                if (n == 0) {
                    sd = Cm.sangle + "[" + ANAME(d.p[0]) + ANAME(d.p[1]) + ", " + ANAME(d.p[2]) + ANAME(d.p[3])
                            + "] != (n*PI) / 3 (n = 0, 1, 2, 3 ..)";
                } else {
                    int a, b;
                    if (d.p[0] == n)
                        a = d.p[1];
                    else a = d.p[0];
                    if (d.p[2] == n)
                        b = d.p[3];
                    else b = d.p[2];

                    sd = Cm.sangle + "[" + ANAME(a) + ANAME(n) + ANAME(b)
                            + "] != (n*PI) / 3 (n = 0, 1, 2, 3 ..)";
                }
            }
            break;
        }
        d.sd = sd;
    }

    protected xterm angle_deduction(xterm p1) {

        ertype = 0;
        pro_type = PRO_FULL;
        max_term = 0;
        d_base = 1;
        qerror = false;
        last_pr = proof = new gr_term();
        dbase();
        if (qerror) return null;
        el_term e1 = null;

        do {

            if (npoly(p1)) {
                return p1;
            }

            while (true) {
                if (p1.var == null) {
                    e1 = null;
                } else if ((e1 = elim_q7(p1)) != null) {
                } else if ((e1 = elim_q8(p1)) != null) {
                } else if ((e1 = elim_f(p1.var)) != null) {
                } else if ((fcc(p1) % 2L) == 0 && (e1 = elim_d(p1.var)) != null) {
                } else if ((fcc(p1) % 3L) == 0 && (e1 = elim_t(p1.var)) != null) {
                } else if ((e1 = elim_tri(p1.var)) == null) {
                }
                if (e1 != null) {
                    p1 = eprem(cp_poly(p1), e1);
                    p1 = fpoly(p1);
                } else if (d_base == 1) {
                    d_base = 2;
                    dbase();
                } else
                    break;
            }
        } while (e1 != null);


        p1 = opt_tri(p1);
        return p1;
    }


    public xterm opt_tri(xterm x) {
        long n = fcc(x);
        if (x.ps != null) {
            dterm dx1 = x.ps.nx;
            if (dx1 != null) {
                xterm x1 = dx1.p;
                long n1 = fcc(x1);
                var v1 = x.var;
                var v2 = x1.var;
                if (n == n1) {
                    if (v1.pt[2] == v2.pt[0] && v1.pt[3] == v2.pt[1]) {
                        xterm p1 = pplus(get_m(v1), get_m(v2));
                        p1 = pminus(p1, trim_f(v1.pt[0], v1.pt[1], v2.pt[2], v2.pt[3]));
                        if (n != 1) {
                            p1 = ptimes(this.get_n(n), p1);
                            return pminus(x, p1);
                        }

                    } else if (v1.pt[0] == v2.pt[2] && v1.pt[1] == v2.pt[3]) {
                        xterm p1 = pplus(get_m(v1), get_m(v2));
                        p1 = pminus(p1, trim_f(v2.pt[0], v2.pt[1], v1.pt[2], v1.pt[3]));
                        if (n != 1) {
                            p1 = ptimes(this.get_n(n), p1);
                            return pminus(x, p1);
                        }
                    }
                } else if (n + n1 == 0) {
                    if (v1.pt[0] == v2.pt[0] && v1.pt[1] == v2.pt[1]) {
                        xterm p1 = pminus(get_m(v1), get_m(v2));
                        p1 = pminus(p1, trim_f(v2.pt[2], v2.pt[3], v1.pt[2], v1.pt[3]));
                        if (n != 1) {
                            p1 = ptimes(this.get_n(n), p1);
                            return pminus(x, p1);
                        }
                    } else if (v1.pt[2] == v2.pt[2] && v1.pt[3] == v2.pt[3]) {
                        xterm p1 = pminus(get_m(v1), get_m(v2));
                        p1 = pminus(p1, trim_f(v1.pt[0], v1.pt[1], v2.pt[0], v2.pt[1]));
                        if (n != 1) {
                            p1 = ptimes(this.get_n(n), p1);
                            return pminus(x, p1);
                        }
                    }
                }
            }
        }
        return x;
    }

    ////////////////////////////////////////////////////////////////////////////////
    //ndg deduction.
    public void ndg_deduction(Vector v3, Vector v4) {

        for (int i = 0; i < v3.size(); i++) {
            cndg d = (cndg) v3.get(i);
            if (d.exists) {
            } else if (ck_allFree(d)) {
                cndg d1 = new cndg(d);
                v4.add(d1);
            } else
                angle_deduction(d, v4);
        }
    }


    public int compare(cndg d1, cndg d2) {
        if (d1 == d2) return 0;

        int n1 = d1.getMaxInt();
        int n2 = d2.getMaxInt();
        if (n1 > n2)
            return 1;
        if (n1 < n2)
            return -1;
        return 0;
    }

    public void sortVector(Vector v4) {
        for (int i = 1; i < v4.size(); i++) {
            cndg d = (cndg) v4.get(i);
            for (int j = 0; j < i; j++) {
                cndg d1 = (cndg) v4.get(j);
                if (compare(d, d1) < 0) {
                    v4.remove(i);
                    v4.add(j, d);
                    i--;
                    break;
                }
            }
        }
    }

    public void parse_neq(Vector v4) {

        sortVector(v4);
        Vector v5 = new Vector();

        for (int i = 0; i < v4.size(); i++) {
            cndg d1 = (cndg) v4.get(i);
            if (d1.type != NDG_NEQ && d1.type != NDG_NON_ISOTROPIC)
                continue;
            if (this.freeCSP(d1.p[0]) && this.freeCSP(d1.p[1]))
                continue;

            v5.add(d1);
            v4.remove(i);
            i--;
        }

//        vndgs.clear();
        vndgs.addAll(v4);

        for (int i = 0; i < v5.size(); i++) {
            cndg d = (cndg) v5.get(i);
            ndgcs c = parse_neq(d);
            updateSD(c);

            if (c.ntype == 0 && !addNdg(c, c.no))
                add_ndgs(d, vndgs);
        }

        this.filter_ndg(vndgs);
        return;
    }

    private void updateSD(ndgcs dd) {
        if (dd == null)
            return;

        for (int i = 0; i <= dd.no; i++) {
            cons c1 = dd.allcns[i];
            if (c1 != null) {
                updatePSS(c1);
                c1.setText(null);
            }
        }
        for (int i = 0; i < dd.child.length; i++)
            if (dd.child[i] != null)
                updateSD(dd.child[i]);
    }

    public ndgcs getCS(cndg d) {// d : type of neq.
        int n = d.getMaxInt();

        ndgcs c = new ndgcs();
        for (int i = 1; i <= cns_no; i++) {
            cons c1 = allcns[i];
            if (c1 == null)
                continue;

            if (construct_related(c1.type)) {
                if (c1.getLastPt() > n) {
                    break;
                }
                if (c1 != null) {
                    int x = c1.getLastPt();
                    if (x == d.p[0] || x == d.p[1])
                        add_RelatedCnsToDg(i, c);
                }
            }
        }

        ndgcs dd = new ndgcs();
        for (int i = 0; i <= cns_no; i++) {
            cons c2 = c.allcns[i];
            if (c2 != null)
                addConsToNdgcs(c2, dd);
        }

        for (int i = 0; i <= dd.no; i++) {
            cons c1 = dd.allcns[i];
            updatePSS(c1);
        }
        return dd;
    }

    private void updatePSS(cons c) {
        if (c == null)
            return;

        for (int i = 0; i <= c.no; i++) {
            c.pss[i] = this.allpts[c.ps[i]];
        }
    }

    private void add_RelatedCnsToDg(int nx, ndgcs d) {

        cons c = allcns[nx];
        if (c == null)
            return;

        for (int i = 0; i <= c.no; i++) {
            for (int j = 0; j < nx; j++) {
                cons c1 = allcns[j];
                if (c1 == null)
                    continue;
                if (!construct_related(c1.type))
                    continue;

                if (c1.getLastPt() == c.ps[i]) {
                    d.add(j, c1);
                    add_RelatedCnsToDg(j, d);
                }
            }
        }
        d.add(nx, c);
    }

    public void addConsToNdgcs(cons c, ndgcs d) {
        if (c == null)
            return;

        switch (c.type) {
            case C_O_L:
            case C_O_P:
            case C_O_T:
            case C_O_C:
                c = new cons(c);
                d.add(c);
                break;
            case C_FOOT:
                add_cons(C_O_L, c.ps[0], c.ps[2], c.ps[3], 0, d);
                add_cons(C_O_T, c.ps[0], c.ps[1], c.ps[2], c.ps[3], d);
                break;
            case C_I_PP:
                add_cons(C_O_P, c.ps[0], c.ps[1], c.ps[2], c.ps[3], d);
                add_cons(C_O_P, c.ps[0], c.ps[4], c.ps[5], c.ps[6], d);
                break;
            case C_I_LL:
                add_cons(C_O_L, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_L, c.ps[0], c.ps[3], c.ps[4], 0, d);
                break;
            case C_I_CC:
                add_cons(C_O_C, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_C, c.ps[0], c.ps[3], c.ps[4], 0, d);
                break;
            case C_CIRCUM:
                add_cons(C_CIRCUM, c.ps[0], c.ps[1], c.ps[2], c.ps[3], d);
                break;
            case C_I_LT:
                add_cons(C_O_L, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_T, c.ps[0], c.ps[3], c.ps[4], c.ps[5], d);
                break;
            case C_I_LP:
                add_cons(C_O_L, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_P, c.ps[0], c.ps[3], c.ps[4], c.ps[5], d);
                break;
            case C_I_LC:
                add_cons(C_O_L, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_C, c.ps[0], c.ps[3], c.ps[4], 0, d);
                break;
            case C_I_LA:
                add_cons(C_O_L, c.ps[0], c.ps[1], c.ps[2], 0, d);
                add_cons(C_O_A, c.ps[0], c.ps[3], c.ps[4], c.ps[5], c.ps[6], c.ps[7], c.ps[8], d);
                break;
            case C_I_PT:
                add_cons(C_O_P, c.ps[0], c.ps[1], c.ps[2], c.ps[3], d);
                add_cons(C_O_T, c.ps[0], c.ps[4], c.ps[5], c.ps[6], d);
                break;
        }
    }

    public void add_cons(int type, int a, int b, int c, int d, int e, int f, int g, ndgcs d1) {
        cons c1 = new cons(type);
        c1.add_pt(a);
        c1.add_pt(b);
        c1.add_pt(c);
        c1.add_pt(d);
        c1.add_pt(e);
        c1.add_pt(f);
        c1.add_pt(g);
        c1.reorder();
        d1.add(c1);
    }

    public void add_cons(int type, int a, int b, int c, int d, ndgcs d1) {
        cons c1 = new cons(type);
        c1.add_pt(a);
        c1.add_pt(b);
        c1.add_pt(c);
        c1.add_pt(d);
        c1.reorder();
        d1.add(c1);
    }

    public boolean ck_right(cons c, ndgcs cs, int type) {
        if (c == null || cs == null)
            return true;

        switch (c.type) {
            case C_I_EQ:
                return !ck_recursive_ndg(type, cs, NDG_NEQ, c.ps[0], c.ps[1], 0, 0);
            case C_O_L:
                return !ck_recursive_ndg(type, cs, NDG_COLL, c.ps[0], c.ps[1], c.ps[2], 0);
            case C_O_T:
                return !ck_recursive_ndg(type, cs, NDG_PARA, c.ps[0], c.ps[1], c.ps[2], c.ps[3]);
            case C_O_P:
                return !ck_recursive_ndg(type, cs, NDG_PERP, c.ps[0], c.ps[1], c.ps[2], c.ps[3]);
        }
        return true;
    }

    private void rm_null_ndgcs(ndgcs c) {
        if (c == null)
            return;


        int n = c.getCSindex();
        for (int i = 0; i <= n; i++) {
            ndgcs cc = c.child[i];
            if (cc == null)
                continue;
            rm_null_ndgcs(cc);
        }
        if (c.getNotNullNum() != 0)
            return;

        n = c.getCSindex();
        if (n < 0) {
            ndgcs cs = c.parent;
            if (cs != null)
                for (int i = 0; i <= cs.getCSindex(); i++) {
                    if (cs.child[i] == c)
                        cs.child[i] = null;
                }
        }
    }

    private void rm_excons(ndgcs c) {
        if (c == null)
            return;

        ndgcs[] css = c.child;
        int a = c.getCSindex();
        if (a < 0) {            // leaf node.
            for (int i = 0; i <= c.no; i++) {
                if (c.allcns[i] == null)
                    continue;
                if (cons_ex(c, i)) {
                    c.allcns[i] = null;
                }
            }
        } else {
            for (int i = 0; i <= a; i++) {
                rm_excons(css[i]);
            }
        }
    }

    private boolean cons_ex(ndgcs cs, int index) {
        cons c = cs.allcns[index];

        if (c == null)
            return true;

        while (cs.parent != null)
            cs = cs.parent;
        cons c1 = cs.allcns[index];
        if (c1 == null)
            return false;
        return c1.isEqual(c);
    }

    public boolean ck_right(ndgcs c, int type) {
        if (c == null)
            return true;

        ndgcs[] css = c.child;
        int a = c.getCSindex();
        if (a < 0) {            // leaf node.

            for (int i = 0; i <= c.no; i++) {
                if (c.allcns[i] == null)
                    continue;
                if (!ck_right(c.allcns[i], c, type)) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i <= a; i++) {
                if (css[i] == null)
                    continue;
                if (!ck_right(css[i], type))
                    css[i] = null;
            }
            for (int i = 0; i <= a; i++)
                if (css[i] != null)
                    return true;
                else return false;
        }
        return true;
    }

    public ndgcs parse_neq(cndg nd) {
        ndgcs c = getCS(nd);
        ndgcs cx = new ndgcs(c);
        cx.parent = c;
        c.addChild(cx);

        switch (nd.type) {
            case NDG_NEQ:
            case NDG_NON_ISOTROPIC:
                int a = nd.p[0];
                int b = nd.p[1];
                for (int i = 0; i <= c.no; i++) {
                    cons c1 = cx.allcns[i];
                    if (c1 == null)
                        break;
                    c1.replace(b, a);   // replace b with a.
                    c1.reorder();
                }
                break;
        }
        parseStep(cx);
        ck_leaf(c);
        rm_excons(c);               // remove exists cons
        rm_null_ndgcs(c);           // remove ndgcs without child.
        ck_right(c, 1);             // remove cons which is contradict with its parents.
        int n1 = c.getCSindex();
        ck_right(c, 0);
        if (n1 >= 0 && c.getCSindex() < 0)
            c.ntype = 1;
        rm_nleaf(c);
        cons_reorder(c);
        return c;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

    private int compare(cons c1, cons c2) {
        if (c1 == null) {
            if (c2 == null)
                return 0;
            return -1;
        }
        if (c2 == null)
            return 1;
        int n1 = c1.getLastPt();
        int n2 = c2.getLastPt();

        if (n1 > n2)
            return 1;
        if (n1 < n2)
            return -1;
        if (c1.type < c2.type)
            return -1;
        if (c1.type > c2.type)
            return 1;
        return 0;
    }

    private void cons_reorder(ndgcs c) {
        if (c == null)
            return;
        if (c.leaf) {
            for (int i = 0; i <= c.no; i++) {
                for (int j = i + 1; j <= c.no; j++) {
                    if (c.allcns[i] == null) {
                        if (c.allcns[j] != null) {
                            c.allcns[i] = c.allcns[j];
                            c.allcns[j] = null;
                        }

                    } else {
                        if (c.allcns[j] != null) {
                            if (compare(c.allcns[i], c.allcns[j]) > 0) {
                                cons cx = c.allcns[i];
                                c.allcns[i] = c.allcns[j];
                                c.allcns[j] = cx;
                            }
                        }
                    }
                }
            }
        } else {
            int n = c.getCSindex();
            for (int i = 0; i <= n; i++) {
                cons_reorder(c.child[i]);
            }
        }
    }

    private void ck_leaf(ndgcs c) {
        if (c == null)
            return;
        if (c.getCSindex() < 0) {
            c.leaf = true;
            return;
        }
        for (int i = 0; i <= c.getCSindex(); i++) {
            ck_leaf(c.child[i]);
        }
    }

    private void rm_nleaf(ndgcs c) {

        if (c == null || c.leaf)
            return;

        int n = c.getCSindex();
        if (n < 0) {
            ndgcs cs = c.parent;
            if (cs != null) {
                for (int i = 0; i <= cs.getCSindex(); i++) {
                    if (cs.child[i] == c)
                        cs.child[i] = null;
                }
            }
        } else {
            for (int i = 0; i <= n; i++) {
                rm_nleaf(c.child[i]);
            }

        }
    }

    private boolean parseStep(ndgcs c) {     //  Main Process of Parsing.
        if (c == null)
            return true;


        opt_cons(c); // reorder. P to L
        rm_redundent(c);

        if (c.getNotNullNum() <= 1)
            return true;

        int max = c.getMaxCnsInt();

        int n = c.no;
        for (int i = 0; i <= n; i++) {
            cons c1 = c.allcns[i];
            if (c1 == null)
                continue;
            if (c1.getLastPt() != max)
                continue;

            for (int j = i + 1; j <= n; j++) {
                cons c2 = c.allcns[j];

                if (c2 == null)
                    continue;
                if (c2.getLastPt() != max)
                    continue;
                switch (c1.type) {
                    case C_O_L:
                        switch (c2.type) {
                            case C_O_L:
                                if (parse_ndg_ll(c, c1, c2))
                                    return true;
                                break;
                            case C_O_T:
                                if (parse_ndg_lt(c, c1, c2))
                                    return true;
                                break;
                            default:
                                break;
                        }
                        break;
                    case C_O_T:
                        switch (c2.type) {
                            case C_O_L:
                                if (parse_ndg_lt(c, c2, c1))
                                    return true;
                                break;
                            default:
                                break;
                        }
                        break;

                    case C_CIRCUM: {
                        switch (c2.type) {
                            case C_CIRCUM:
                                parse_ndg_circums(c, c1, c2);
                                break;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean parse_ndg_circums(ndgcs c, cons c1, cons c2) {
        int o = c1.ps[0];

        int[] pp = new int[6];
        for (int i = 0; i < pp.length; i++)
            pp[i] = 0;

        for (int i = 0; i < 3; i++) {
            addPtNoRedunent(c1.ps[i + 1], pp);
            addPtNoRedunent(c2.ps[i + 1], pp);
        }

        for (int i = 0; i < pp.length; i++) {
            if (pp[i] == 0)
                break;
            for (int j = i + 1; j < pp.length; j++) {
                if (pp[j] == 0)
                    break;
                for (int k = j + 1; k < pp.length; k++) {
                    if (pp[k] == 0)
                        break;
                    {
                        for (int m = 0; m <= c.no; m++) {
                            cons cx = c.allcns[m];
                            if (cx == null)
                                break;
                            switch (cx.type) {
                                case C_O_L:
                                    if (xcoll(pp[i], pp[j], pp[k])) {

                                        if (!ck_recursive_ndg(1, c, NDG_NEQ, pp[i], pp[j], 0, 0)) {
                                            ndgcs nc1 = new ndgcs(c);                 // ! Collinear A, B, C
                                            nc1.parent = c;
                                            c.addChild(nc1);
                                            cons cc = new cons(C_I_EQ);
                                            cc.add_pt(pp[i]);
                                            cc.add_pt(pp[j]);
                                            cc.reorder();
                                            ndg_pteq_added(cc, nc1);
                                            parseStep(nc1);
                                        }

                                        if (!ck_recursive_ndg(1, c, NDG_NEQ, pp[i], pp[k], 0, 0)) {
                                            ndgcs nc1 = new ndgcs(c);                 // ! Collinear A, B, C
                                            nc1.parent = c;
                                            c.addChild(nc1);
                                            cons cc = new cons(C_I_EQ);
                                            cc.add_pt(pp[i]);
                                            cc.add_pt(pp[k]);
                                            cc.reorder();
                                            ndg_pteq_added(cc, nc1);
                                            parseStep(nc1);
                                        }

                                        if (!ck_recursive_ndg(1, c, NDG_NEQ, pp[i], pp[j], 0, 0)) {
                                            ndgcs nc1 = new ndgcs(c);                 // ! Collinear A, B, C
                                            nc1.parent = c;
                                            c.addChild(nc1);
                                            cons cc = new cons(C_I_EQ);
                                            cc.add_pt(pp[j]);
                                            cc.add_pt(pp[k]);
                                            cc.reorder();
                                            ndg_pteq_added(cc, nc1);
                                            parseStep(nc1);
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean onOneCons(int a, int b, cons c1) {
        return c1.contains(a) && c1.contains(b);
    }

    private void addPtNoRedunent(int p, int[] pp) {
        for (int i = 0; i < pp.length; i++) {
            if (pp[i] == p)
                return;
            if (pp[i] == 0) {
                pp[i] = p;
                return;
            }
        }
    }

    private boolean parse_ndg_ll(ndgcs c, cons c1, cons c2) {

        int n1 = c1.getLastPt();
        int n2 = c2.getLastPt();

        if (n1 != n2)
            return false;

        int m, a, b;
        if (c1.ps[1] == c2.ps[1]) {
            m = c1.ps[1];
            a = c1.ps[2];
            b = c2.ps[2];
        } else if (c1.ps[1] == c2.ps[2]) {
            m = c1.ps[1];
            a = c1.ps[2];
            b = c2.ps[1];
        } else if (c1.ps[2] == c2.ps[1]) {
            m = c1.ps[1];
            a = c1.ps[1];
            b = c2.ps[2];
        } else if (c1.ps[2] == c2.ps[2]) {
            m = c1.ps[2];
            a = c1.ps[1];
            b = c2.ps[1];
        } else
            return false;

        //if (ck_recursive_ndg(c, NDG_COLL, m, a, b, 0))
        {
            ndgcs nc1 = new ndgcs(c);                 // ! Collinear A, B, C
            nc1.parent = c;
            c.addChild(nc1);
            cons cc = new cons(C_I_EQ);
            cc.add_pt(n1);
            cc.add_pt(m);
            cc.reorder();
            ndg_pteq_added(cc, nc1);
            nc1.nd = this.add_ndg_coll(m, a, b);
            parseStep(nc1);
        }

        if (!ck_recursive_ndg(1, c, NDG_COLL, m, a, b, 0)) {
            ndgcs nc2 = new ndgcs(c);               // Collinear M A B; A != B
            nc2.parent = c;
            c.addChild(nc2);
            cons cc = new cons(C_O_L);
            cc.add_pt(m);
            cc.add_pt(a);
            cc.add_pt(b);
            ndg_coll_added(cc, nc2);
            nc2.nd = this.add_ndg_neq(a, b);
            parseStep(nc2);
        }


        if (!ck_recursive_ndg(1, c, NDG_NEQ, a, b, 0, 0)) {
            ndgcs nc3 = new ndgcs(c);                 // A = B.
            nc3.parent = c;
            c.addChild(nc3);
            cons cc = new cons(C_I_EQ);
            cc.add_pt(a);
            cc.add_pt(b);
            cc.reorder();
            ndg_pteq_added(cc, nc3);
            nc3.nd = null;
            parseStep(nc3);
        }

        return true;
    }


    private boolean ck_recursive_ndg(int type, ndgcs cs, int t, int a, int b, int c, int d) {

        if (type == 0) {
            for (int i = 0; i < vndgs.size(); i++) {
                cndg dx = (cndg) vndgs.get(i);
                if (ck_ndg(t, a, b, c, d, dx))
                    return true;
            }
            return false;

        } else { // type == 1;

            if (cs == null)
                return false;

            cndg dd = cs.nd;
            if (dd == null)
                return false;


            while (cs != null) {
                if (cs.nd != null && ck_ndg(t, a, b, c, d, cs.nd))
                    return true;
                cs = cs.parent;
            }
        }
        return false;
    }

    private boolean ck_ndg(int t, int a, int b, int c, int d, cndg dd) {
        boolean r = false;

        switch (t) {
            case NDG_COLL:
                if (dd.type == NDG_COLL) {
                    r = dd.contain(a) && dd.contain(b) && dd.contain(c);
                } else if (dd.type == NDG_NEQ || dd.type == NDG_NON_ISOTROPIC) {
//                    int n = 0;
//                    if (dd.contain(a))
//                        n++;
//                    if (dd.contain(b))
//                        n++;
//                    if (dd.contain(c))
//                        n++;
//                    r = n >= 2;
                }
                break;
            case NDG_NEQ:
            case NDG_NON_ISOTROPIC:
                if (dd.type == NDG_COLL)
                    r = dd.contain(a) && dd.contain(b);
                else if (dd.type == NDG_NEQ || dd.type == NDG_NON_ISOTROPIC) {
                    r = dd.contain(a) && dd.contain(b);
                }
                break;
        }
        return r;
    }

    private boolean parse_ndg_lt(ndgcs c, cons c1, cons c2) {

        int n1 = c1.getLastPt();
        int n2 = c2.getLastPt();

        if (c1.type == C_O_T && c2.type == C_O_L) {
            cons x = c1;
            c1 = c2;
            c2 = x;
        }

        if (n1 != n2)
            return false;

        int m, a, b;
        if (c2.ps[0] == c2.ps[2]) {
            m = c2.ps[0];
            a = c2.ps[1];
            b = c2.ps[3];
        } else if (c2.ps[1] == c2.ps[2]) {
            m = c2.ps[1];
            a = c2.ps[0];
            b = c2.ps[3];
        } else if (c2.ps[1] == c2.ps[3]) {
            m = c2.ps[1];
            a = c2.ps[0];
            b = c2.ps[2];
        } else if (c2.ps[0] == c2.ps[3]) {
            m = c2.ps[0];
            a = c2.ps[1];
            b = c2.ps[2];
        } else
            return false;

        if (!(c1.contains(m) && c1.contains(a) && c1.contains(b)))
            return false;

        if (a < b) {
            int x = a;
            a = b;
            b = x;
        }  // b = n1;


        {
            ndgcs nc2 = new ndgcs(c);                // OtherWise.
            nc2.parent = c;
            c.addChild(nc2);
            cons cc2 = new cons(C_I_EQ);
            cc2.add_pt(n1);
            cc2.add_pt(m);
            nc2.add(cc2);
            ndg_pteq_added(cc2, nc2);
            nc2.nd = this.add_ndg_neq(m, b);
            parseStep(nc2);
        }

        if (!ck_recursive_ndg(1, c, NDG_NEQ, m, b, 0, 0)) {
            ndgcs nc2 = new ndgcs(c);                // OtherWise.
            nc2.parent = c;
            c.addChild(nc2);
            cons cc2 = new cons(C_I_EQ);
            cc2.add_pt(m);
            cc2.add_pt(b);
            nc2.add(cc2);
            ndg_pteq_added(cc2, nc2);
            nc2.nd = null;
            parseStep(nc2);

        }
        return true;
    }

    private void ndg_pteq_added(cons c, ndgcs d) {
        int m = c.ps[0];
        int a = c.ps[1];
        for (int i = 0; i <= d.no; i++) {
            cons c2 = d.allcns[i];
            if (c2 == null || c2 == c)
                continue;
            c2.replace(m, a);
        }
        d.add(c);
    }

    private void ndg_coll_added(cons c, ndgcs d) {
        int m = c.ps[0];
        int a = c.ps[1];
        int b = c.ps[2];

        for (int i = 0; i <= d.no; i++) {
            cons c2 = d.allcns[i];
            if (c2 == null || c2 == c)
                continue;

            switch (c2.type) {
                case C_O_L:
                    if (c2.contains(m)) {
                        if (c2.contains(a)) {
                            c2.replace(m, b);
                        } else if (c.contains(b)) {
                            c2.replace(m, a);
                        }
                    }
                    break;
                case C_O_T:
                case C_O_P: {
                    if (c2.ps[0] == m) {
                        if (c2.ps[1] == a)
                            c2.ps[0] = b;
                        else if (c2.ps[1] == b)
                            c2.ps[0] = a;
                    } else if (c2.ps[2] == m) {
                        if (c2.ps[3] == a)
                            c2.ps[2] = b;
                        else if (c2.ps[3] == b)
                            c2.ps[2] = a;
                    } else if (c2.ps[1] == m) {
                        if (c2.ps[0] == a)
                            c2.ps[1] = b;
                        else if (c2.ps[0] == b)
                            c2.ps[1] = a;
                    } else if (c2.ps[3] == m) {
                        if (c2.ps[2] == a)
                            c2.ps[3] = b;
                        else if (c2.ps[2] == b)
                            c2.ps[3] = a;
                    }
                }
                break;
            }
            c2.reorder();

        }
        d.add(c);
    }

//    private boolean parseStep(ndgcs c, int i, int j) {
//        cons c1 = c.allcns[i];
//        cons c2 = c.allcns[j];
//
//        if (c1 == null || c2 == null) {
//            return false;
//        }
//
//        int n1 = c1.getLastPt();
//        int n2 = c2.getLastPt();
//
//        switch (c1.type) {
//            case C_O_L: {
//                switch (c2.type) {
//                    case C_O_L:
//                        if (n1 == n2) {
//                            int m, a, b;
//                            if (c1.ps[1] == c2.ps[1]) {
//                                m = c1.ps[1];
//                                a = c1.ps[2];
//                                b = c2.ps[2];
//                            } else if (c1.ps[1] == c2.ps[2]) {
//                                m = c1.ps[1];
//                                a = c1.ps[2];
//                                b = c2.ps[1];
//                            } else if (c1.ps[2] == c2.ps[1]) {
//                                m = c1.ps[1];
//                                a = c1.ps[1];
//                                b = c2.ps[2];
//                            } else if (c1.ps[2] == c2.ps[2]) {
//                                m = c1.ps[2];
//                                a = c1.ps[1];
//                                b = c2.ps[1];
//                            } else
//                                break;
//
//                            parse_rpt_ndgs(n1, m, c);
//
//                            if (!ck_diff(a, b)) {
//                                ndgcs nc1 = new ndgcs(c);
//                                cndg d = new cndg();
//                                d.type = NDG_NEQ;
//                                d.no = 1;
//                                d.p[0] = a;
//                                d.p[1] = b;
//                                nc1.nd = d;
//                                c.addChild(nc1);
//                                parse_neq1(nc1);
//                            }
//                            return true;
//                        }
//
//                    case C_O_T:
//                        int m, a, b;
//                        if (c2.ps[0] == c2.ps[2]) {
//                            m = c2.ps[0];
//                            a = c2.ps[1];
//                            b = c2.ps[3];
//                        } else if (c2.ps[1] == c2.ps[2]) {
//                            m = c2.ps[1];
//                            a = c2.ps[0];
//                            b = c2.ps[3];
//                        } else if (c2.ps[1] == c2.ps[3]) {
//                            m = c2.ps[1];
//                            a = c2.ps[0];
//                            b = c2.ps[2];
//                        } else if (c2.ps[0] == c2.ps[3]) {
//                            m = c2.ps[0];
//                            a = c2.ps[1];
//                            b = c2.ps[2];
//                        } else
//                            break;
//                        if (!(c1.contains(m) && c1.contains(a) && c1.contains(b)))
//                            break;
//                        int x = a;
//                        if (x < b)
//                            x = b;
//                        if (x < m) {
//                            int t = x;
//                            x = m;
//                            m = t;
//                        }
//                        parse_rpt_ndgs(x, m, c);
//                        break;
//                }
//                break;
//            }
//        }
//        return false;
//    }

    public void parse_rpt_ndgs(int m, int a, ndgcs c) {
        if (m == a)
            return;
        if (m < a) {
            int t = m;
            m = a;
            a = t;
        }

        ndgcs nc1 = new ndgcs(c);
        nc1.parent = c;
        c.addChild(nc1);
        nc1.replace(m, a);
        opt_cons(nc1);
        rm_redundent(nc1);
        nc1.rm_common();
        parseStep(nc1);
    }


    private boolean ck_diff(int a, int b) {
        for (int i = 0; i < vndgs.size(); i++) {
            cndg d = (cndg) vndgs.get(i);
            switch (d.type) {
                case NDG_COLL:
                    if (d.contain(a) && d.contain(b))
                        return true;
                    break;
                case NDG_NEQ:
                case NDG_NON_ISOTROPIC:
                    if (d.contain(a) && d.contain(b))
                        return true;
                case NDG_PARA:
                case NDG_PERP:
                    if (d.contain2(a, b))
                        return true;
                    break;
            }
        }
        return false;
    }

    private void opt_cons(ndgcs c) {

        while (true) {
            boolean r = true;

            for (int i = 0; i <= c.no; i++) {
                cons c1 = c.allcns[i];
                if (c1 != null) {
                    c1.reorder();
                    if (opt_cons(c, c1))
                        r = false;
                }
            }

            if (!r) {
                for (int i = 0; i <= c.no; i++) {
                    cons c1 = c.allcns[i];
                    if (c1 != null) {
                        switch (c1.type) {
                            case C_I_EQ:
                                for (int j = 0; j <= c.no; j++) {
                                    cons c2 = c.allcns[j];
                                    if (c2 != null && c2 != c1)
                                        c2.replace(c1.ps[0], c1.ps[1]);
                                }
                                break;
                        }
                    }
                }
            } else
                break;
        }
    }


    private boolean opt_cons(ndgcs cs, cons c) {
        if (c == null)
            return false;

        switch (c.type) {
            case C_O_P:
                if (c.ps[0] == c.ps[2] || c.ps[1] == c.ps[2]) {
                    c.type = C_O_L;
                    c.no = -1;
                    c.add_pt(c.ps[0]);
                    c.add_pt(c.ps[1]);
                    c.add_pt(c.ps[3]);
                    c.reorder();
                    return true;
                } else if (c.ps[1] == c.ps[3]) {
                    c.type = C_O_L;
                    c.no = -1;
                    c.add_pt(c.ps[0]);
                    c.add_pt(c.ps[1]);
                    c.add_pt(c.ps[2]);
                    c.reorder();
                    return true;
                }
                break;
            case C_O_T:
                if (c.ps[0] == c.ps[2] && c.ps[1] == c.ps[3]) {
                    c.type = C_I_EQ;
                    c.no = -1;
                    c.add_pt(c.ps[0]);
                    c.add_pt(c.ps[1]);
                    c.reorder();
                    return true;
                }
                break;
            case C_CIRCUM: {
                int t = 0;
                boolean r = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = i + 1; j < 4; j++)
                        if (c.ps[i] == c.ps[j]) {
                            r = true;
                            t = c.ps[i];
                            break;
                        }
                    if (r)
                        break;
                }

                if (r) {
                    boolean r1 = false;
                    int min = Integer.MAX_VALUE;

                    for (int i = 0; i < 4; i++)
                        if (c.ps[i] != 0 && c.ps[i] < min)
                            min = c.ps[i];

                    for (int i = 0; i < 4; i++) {
                        if (add_eq(min, c.ps[i], cs))
                            r1 = true;
                    }
                    return r1;
                }
            }
            break;
        }
        return false;
    }

    private boolean add_eq(int a, int b, ndgcs c) {
        if (a == b)
            return false;
        if (a == 0 || b == 0)
            return false;

        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        for (int i = 0; i <= c.no; i++) {
            cons c1 = c.allcns[i];
            if (c1 == null)
                continue;
            if (c1.type != C_I_EQ)
                continue;
            if (c1.contains(a) && c1.contains(b))
                return false;
        }

        cons c2 = new cons(C_I_EQ);
        c2.add_pt(a);
        c2.add_pt(b);
        c.add(c2);
        return true;
    }

    private void rm_redundent(ndgcs c) {
        for (int i = 0; i <= c.no; i++) {
            cons c1 = c.allcns[i];
            if (c1 != null && cons_redundent(c1))
                c.allcns[i] = null;
        }

        for (int i = 0; i <= c.no; i++) {
            cons c1 = c.allcns[i];
            if (c1 == null)
                continue;
            for (int j = 0; j < i; j++) {
                cons c2 = c.allcns[j];
                if (c2 != null && c2.isEqual(c1)) {
                    c.allcns[i] = null;
                    break;
                }
            }
        }
    }

    private boolean cons_redundent(cons c) {
        switch (c.type) {
            case C_O_L:
                return c.ps[0] == c.ps[1] || c.ps[0] == c.ps[2] || c.ps[1] == c.ps[2];
            case C_O_T:
            case C_O_P:
                return c.ps[0] == c.ps[1] || c.ps[2] == c.ps[3];
            case C_O_C:
                return c.ps[0] == c.ps[2];
            case C_CIRCUM:
                return c.ps[0] == c.ps[1] && c.ps[1] == c.ps[2] && c.ps[2] == c.ps[3];
        }
        return false;
    }

    public boolean construct_related(int t) {
        if (t == C_POINT || t == C_TRIANGLE || t == C_POLYGON || t == C_QUADRANGLE
                || t == C_PENTAGON || t == C_LINE || t == C_CIRCLE)
            return false;
        if (t >= 50 && t <= 100)
            return false;
        return true;
    }


    private boolean addNdg(ndgcs d, int no) {

        boolean r = false;
        int n = d.getCSindex();

        if (n >= 0) {
            for (int i = 0; i <= n; i++) {
                if (d.child[i] != null) {
                    if (addNdg(d.child[i], no)) ;
                    r = true;
                }
            }
            return r;
        }

        boolean added = false;

        for (int i = 0; i <= d.no; i++) {
            cons c1 = d.allcns[i];
            if (c1 != null) {
                if (addNdg(c1)) {
                    added = true;
                }
                break;
            }
        }
        return added;
    }

    private boolean addNdg(cons c) {
        switch (c.type) {
            case C_O_L:
                this.add_n_coll(c.ps[0], c.ps[1], c.ps[2], vndgs);
                break;
            case C_O_P: {
                cndg d = add_ndg_para(c.ps[0], c.ps[1], c.ps[2], c.ps[3]);
                add_ndgs(d, vndgs);
            }
            break;
            case C_O_T: {
                cndg d = add_ndg_perp(c.ps[0], c.ps[1], c.ps[2], c.ps[3]);
                add_ndgs(d, vndgs);
            }
            break;
            case C_I_EQ: {
                cndg d = add_ndg_neq(c.ps[0], c.ps[1]);
                add_ndgs(d, vndgs);
            }
            break;
            default:
                return false;
        }
        return true;
    }

    private Vector vndgs = new Vector();


}
