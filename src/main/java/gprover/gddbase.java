/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:37:28
 * To change this template use File | Settings | File Templates.
 */
package gprover;
                           
public class gddbase extends gib {

    final int ind_3(int p, int p1, int p2, int p3) {
        if (p == p1) return (1);
        if (p == p2) return (2);
        if (p == p3) return (3);
        return (0);
    }

    final int ind_3(int p, int[] pp) {
        if (p == pp[0]) return (1);
        if (p == pp[1]) return (2);
        if (p == pp[2]) return (3);
        return (0);
    }


    final boolean ycoll(int a, int b, int c) {
        boolean i = xcoll(a, b, c);
        if (i) {
            add_codb(CO_COLL, a, b, c, 0, 0, 0, 0, 0);
        }
        return (i);
    }

    final boolean ypara(int a, int b, int p, int q) {
        boolean i = xpara(a, b, p, q);
        if (i) {
            add_codb(CO_PARA, a, b, p, q, 0, 0, 0, 0);
        }
        return (i);
    }

    final boolean yperp(int a, int b, int p, int q) {
        boolean i = xperp(a, b, p, q);
        if (i) {
            add_codb(CO_PERP, a, b, p, q, 0, 0, 0, 0);
        }
        return (i);
    }

    final boolean ycong(int a, int b, int p, int q) {
        boolean i = xcong(a, b, p, q);
        if (i) {
            add_codb(CO_CONG, a, b, p, q, 0, 0, 0, 0);
        }
        return (i);
    }

    final boolean yacong(int a, int b, int c, int p, int q, int r) {
        boolean i = xacong(a, b, c, p, q, r);
        if (i) {
            add_codb(CO_ACONG, a, b, b, c, p, q, q, r);
        }
        return (i);
    }

    final boolean ycir2(int o, int a, int b) {
        boolean i = xcir2(o, a, b);
        if (i) {
            add_codb(CO_CYCLIC, o, a, b, 0, 0, 0, 0, 0);
        }
        return (i);
    }

    final boolean ch_dep(long id) {
        return id < depth || isPFull();
    }

    final void add_cr_pn_as(a_cir cr, int m1, int m2, int p1, int p2) {
        int lm = R_CR_P_EQARC;

        add_codb(CO_PARA, m1, m2, p1, p2, 0, 0, 0, 0);
        add_codb(CO_CYCLIC, 0, m1, m2, p1, p2, 0, 0, 0);
        add_ea_pt_t(lm, m1, p1, p2, p1, p2, m2);
        add_ea_pt_t(lm, m2, p1, p2, p1, p2, m1);
        add_cong(lm, 0, m1, p1, m2, p2);
        add_cong(lm, 0, m1, p2, m2, p1);

        if (cr.o != 0) {
            if (!ck_dr(m1, m2, p1, p2)) {
                int t = p1;
                p1 = p2;
                p2 = t;
            }
            add_ea_pt_t(lm, m1, cr.o, p1, p2, cr.o, m2);
            int t1, t2;
            for (int i = 0; i <= cr.no; i++)
                for (int j = 0; j <= cr.no; j++) {
                    t1 = cr.pt[i];
                    t2 = cr.pt[j];
                    if (t1 != p1 && t1 != m1 && t2 != p2 && t2 != m2)
                        add_ea_pt_t(lm, m1, t1, p1, p2, t2, m2);
                }
        }
        pop_codb();
        pop_codb();
    }

    final void add_type3(int m, int m2, int p2, int m3, int p3) {
        int p1;
        add_codb(CO_CYCLIC, m, p2, p3, 0, 0, 0, 0, 0);
        add_codb(CO_MIDP, m2, m, p2, 0, 0, 0, 0, 0);
        add_codb(CO_MIDP, m3, m, p3, 0, 0, 0, 0, 0);
        add_cir4(61, 0, m2, m3, p2, p3);
        p1 = inter_ll(fd_pline(p2, m, p3), fd_pline(p3, m, p2));
        if (p1 != 0) {
            add_ea_pt_t(62, p2, p1, m2, m3, p1, p3);
            add_ea_pt_t(62, p2, m2, p1, p1, m3, p3);
            if (yperp(m, p2, m, p3)) {
                add_ea_pt_t(63, m, p2, m3, p2, p1, m2);
                add_ea_pt_t(63, p2, m3, m, p1, m2, p2);
                pop_codb();
            }
        }
        pop_codb();
        pop_codb();
        pop_codb();
    }

    final void add_type4(int m, int m1, int p1, int m2, int p2) {
        add_codb(CO_PARA, m1, m2, p1, p2, 0, 0, 0, 0);
        add_codb(CO_CYCLIC, m, p1, p2, 0, 0, 0, 0, 0);
        add_codb(CO_MIDP, m, m1, m2, 0, 0, 0, 0, 0);
        add_cir4(64, 0, m1, m2, p1, p2);
        add_ea_pt_t(64, m1, m, p1, p2, m, m2);
        add_ea_pt_t(64, m1, p1, m, m, p2, m2);
        add_ea_pt_t(64, m2, m, p1, p2, m, m1);
        add_ea_pt_t(64, m2, p1, m, m, p2, m1);
        pop_codb();
        pop_codb();
        pop_codb();
    }

/* Lemmas  */

    final void search_pn_tn(p_line pn, t_line tn) {
        l_line ln, l1, l2;
        int i;
        if (on_pn(tn.l1, pn)) {
            l1 = tn.l2;
            l2 = tn.l1;

        } else if (on_pn(tn.l2, pn)) {
            l1 = tn.l1;
            l2 = tn.l2;
        } else {
            return;
        }
        for (i = 0; i <= pn.no; i++) {
            ln = pn.ln[i];
            if (ln != l2 && !ln_perp(l1, ln)) ;
            {
                add_codb(CO_PERP, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], 0, 0, 0, 0);
                add_codb(CO_PARA, ln.pt[0], ln.pt[1], l2.pt[0], l2.pt[1], 0, 0, 0, 0);
                add_tline(R_PT_T, ln.pt[0], ln.pt[1], l1.pt[0], l1.pt[1]);
                pop_codb();
                pop_codb();
            }
        }
    }

    final void lm_md_connection(midpt md, l_line l1, l_line l2) {
        int lm = R_MID_CONNECTION;
        if (!valid(lm)) return;

        int p1, p2, k, m1, m2;
        l_line n;
        if (on_ln(md.m, l1)) {
            n = l1;
            l1 = l2;
            l2 = n;
        }
        if (on_ln(md.m, l2)) {
            if (on_ln(md.a, l1)) {
                m1 = md.a;
                m2 = md.b;
            } else if (on_ln(md.b, l1)) {
                m1 = md.b;
                m2 = md.a;
            } else
                return;
            for (k = 0; k <= l2.no; k++) {
                p2 = l2.pt[k];
                if (p2 != md.m && (p1 = inter_ll(l1, fd_ln(p2, m2))) != 0 && p1 != p2) {
                    add_codb(CO_PARA, p1, m1, p2, md.m, 0, 0, 0, 0);
                    add_mid(lm, p2, p1, m2);
                    add_cong(lm, 0, p2, p1, p2, m2);
                    pop_codb();
                }
            }
        }
    }

    boolean ch_it(int n) {
        return ((d_base != 0) ? (n != 0) : ((n) == 1));

    }

    final void lm_parallelogram(midpt md, l_line l1, l_line l2) {

        if (!valid(R_PARALLELOGRAM)) return;

        if (l1 == l2) return;

        l_line l0 = fd_ln(md.a, md.b);
        if (on_ln(md.a, l2)) {
            l_line n = l1;
            l1 = l2;
            l2 = n;
        }
        if (on_ln(md.a, l1) && on_ln(md.b, l2)) {

            {
                p_line pn = all_pn.nx;
                while (pn != null) {
                    if (!ch_it(pn.type) || pn.no == 0 || on_pn(l1, pn) || on_pn(l0, pn)) {
                        pn = pn.nx;
                        continue;
                    }
                    for (int k = 0; k <= pn.no; k++)
                        for (int l = k + 1; l <= pn.no; l++) {
                            l_line l3 = pn.ln[k];
                            l_line l4 = pn.ln[l];
                            if (on_ln(md.a, l4)) {
                                l_line n = l3;
                                l3 = l4;
                                l4 = n;
                            }
                            int p1, p2;
                            if (l3 != l4 && on_ln(md.a, l3) && on_ln(md.b, l4) &&
                                    (p1 = inter_ll(l2, l3)) != 0 && p1 != md.a && p1 != md.b &&
                                    (p2 = inter_ll(l1, l4)) != 0 && p2 != md.a && p2 != md.b && p1 != p2) {
                                add_codb(CO_PARA, p1, md.a, p2, md.b, 0, 0, 0, 0);
                                add_codb(CO_PARA, p1, md.b, p2, md.a, 0, 0, 0, 0);
                                add_line(R_PARALLELOGRAM, md.m, p1, p2);//  @@
                                add_mid(R_PARALLELOGRAM, md.m, p1, p2);
                                add_cong(R_PARALLELOGRAM, 0, md.m, p1, md.m, p2);
                                pop_codb();
                                pop_codb();
                            }
                        }
                    pn = pn.nx;
                }
            }
        }
    }


    final void lm_RATIO(int lm, int p1, int p2, int p3, int p4) {
        if (!valid(R_RATIO)) return;

        int o;
        o = inter_ll(fd_ln(p1, p3), fd_ln(p2, p4));
        if (o != 0) {
            add_codb(CO_PARA, p1, p2, p3, p4, 0, 0, 0, 0);
            add_ratioo(lm, o, p1, p2, p3, p4);
            pop_codb();
        }
        o = inter_ll(fd_ln(p1, p4), fd_ln(p2, p3));
        if (o != 0) {
            add_codb(CO_PARA, p1, p2, p3, p4, 0, 0, 0, 0);
            add_ratioo(lm, o, p1, p2, p4, p3);
            pop_codb();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    /// this frome pred.cpp

    final void copy_pred(cond p1, cond p2) {
        p2.pred = p1.pred;
        p2.u.cpv(p1.u);
        for (int i = 0; i <= 7; i++) p2.p[i] = p1.p[i];
    }

    boolean new_eq(cond n1, cond n2) {
        return (n1.pred == n2.pred && n1.u.equal(n2.u));
    }

    final cond new_pr(int pred) {
        cond nd = new cond();
        nd.pred = pred;
        nd.no = 0;
        nd.nx = null;
        for (int i = 0; i <= 7; i++)
            nd.p[i] = 0;

        if (all_nd.nx == null) {
            last_nd = all_nd;
        }
        last_nd.nx = nd;
        last_nd = nd;
        return nd;
    }

    final void new_ot() {
        int i = 0;
        cond nd1, nd = all_nd.nx;
        nd1 = null;
        if (nd == null) return;
        while (nd.nx != null) {
            if (new_eq(nd, last_nd)) i = 1;
            nd1 = nd;
            nd = nd.nx;
        }
        if (i == 1) {
            last_nd = nd1;
        }
    }

    cond add_codb(int n, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {

//        if(p1 == 1 && p2 ==4 && p3 == 1 && p4 == 9)
//        {
//            int k = 0;
//        }
        cond co = new cond();

        co.pred = n;
        co.no = 0;
        co.u.ln = null;
        co.p[0] = p1;
        co.p[1] = p2;
        co.p[2] = p3;
        co.p[3] = p4;
        co.p[4] = p5;
        co.p[5] = p6;
        co.p[6] = p7;
        co.p[7] = p8;

        if (n == CO_TANG && p3 == p4) {
            int k = 0;
        }

        if (n == CO_PARA && xcoll4(p1, p2, p3, p4)) n = CO_COLL;
        if (n == CO_COLL) {
            int i, j, k = 0;
            for (i = 1; i <= 5; i++) {
                boolean gt = false;
                for (j = 0; j <= k; j++)
                    if (co.p[j] == co.p[i]) {
                        gt = true;
                        j = 1;
                        break;
                    }

                if (gt != true)
                    if (co.p[i] != 0) {
                        k++;
                        co.p[k] = co.p[i];
                    }
                j = 1;
            }

            for (i = k + 1; i <= 5; i++) co.p[i] = 0;
            if (k <= 1) co.pred = 0;
        }

        co.nx = co_db.nx;
        if (co.pred != 0) {
            co.nx = co_db.nx;
            co_db.nx = co;
        }
        return (co);

    }

    final void pop_codb() {
        cond co = co_db.nx;
        if (co != null) {
            co_db.nx = co.nx;
        } /* free((cond *)co);  */
    }

    cond add_coxy(int n) {
        cond co = new cond();
        co.pred = n;
        co.p[0] = 0;
        co.p[1] = 0;
        co.p[2] = 0;
        co.p[3] = 0;
        co.p[4] = 0;
        co.p[5] = 0;
        co.p[6] = 0;
        co.p[7] = 0;
        co.nx = co_xy.nx;
        co_xy.nx = co;
        return (co);
    }

    final void add_codx(int n, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        cond co = add_codb(n, p1, p2, p3, p4, p5, p6, p7, p8);
        if (co.pred != 0) {
            co_db.nx = co.nx;
            co.nx = co_xy.nx;
            co_xy.nx = co;
        }
    }


    final void new_para(p_line pn) {
        cond nd = new cond();
        nd.pred = CO_PARA;
        nd.u.pn = pn;
        for (int i = 0; i <= 7; i++) nd.p[i] = 0;
        nd.nx = all_nd.nx;
        all_nd.nx = nd;
        if (nd.nx == null) {
            last_nd = nd;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*  geometry predictaes */

    final boolean xcoll(int p1, int p2, int p3) {
        l_line ln;

        if (!check_coll(p1, p2, p3)) return false;

        if (p1 == p2 || p1 == p3 || p2 == p3) return (true);
        ln = fo_ln3(p1, p2, p3);
        return (ln != null);
    }


    final l_line fo_ln3(int p1, int p2, int p3) {
        l_line ln;
        ln = all_ln.nx;
        while (ln != null) {
            if (ln.no > 1 && on_ln(p1, ln) && on_ln(p2, ln) && on_ln(p3, ln)) {
                return (ln);
            }
            ln = ln.nx;
        }
        return (null);
    }

    final l_line fo_ln(int ps[], int n)   ///xxxxx
    {
        l_line ln;
        int i;
        ln = all_ln.nx;
        while (ln != null) {
            if (ln.no <= 1) {
                ln = ln.nx;
                continue;
            }
            for (i = 0; i <= n; i++) {
                if (ps[i] != 0 && !on_ln(ps[i], ln))
                    break;
            }
            if (i == n + 1) return (ln);

            ln = ln.nx;
        }
        return (null);
    }

    final boolean xcoll4(int p1, int p2, int p3, int p4) {
        if (p1 == p2) return (xcoll(p2, p3, p4));
        return (xcoll(p1, p2, p3) && xcoll(p1, p2, p4));
    }

    final boolean xpara(int p1, int p2, int p3, int p4) {

        if (p1 == p2 || p3 == p4 || xcoll4(p1, p2, p3, p4)) return (true);
        return (fo_pn1(p1, p2, p3, p4) != null);
    }

    final boolean ln_para(l_line l1, l_line l2) {
        p_line pn;
        if (l1 == l2) return (true);
        if ((l1.type == 0 || l2.type == 0) && xcoll_ln(l1, l2)) return true;
        if (l1.type == 0)
            l1 = fd_lnl(l1);
        if (l2.type == 0)
            l2 = fd_lnl(l2);
        pn = all_pn.nx;
        while (pn != null) {
            if (on_pn(l1, pn) && on_pn(l2, pn)) return (true);
            pn = pn.nx;
        }
        return (false);
    }

    final p_line fo_pn(int p1, int p2, int p3, int p4) {
        p_line pn = all_pn.nx;
        while (pn != null) {
            if (pn.no == 0) {
                pn = pn.nx;
                continue;
            }
            for (int i = 0; i <= pn.no; i++) {
                if (on_ln(p1, pn.ln[i]) && on_ln(p2, pn.ln[i])) {
                    for (int j = i + 1; j <= pn.no; j++)
                        if (on_ln(p3, pn.ln[j]) && on_ln(p4, pn.ln[j])) return (pn);
                    if (pn.type != 0) return (null); //??
                } else if (on_ln(p3, pn.ln[i]) && on_ln(p4, pn.ln[i])) {
                    for (int j = i + 1; j <= pn.no; j++)
                        if (on_ln(p1, pn.ln[j]) && on_ln(p2, pn.ln[j])) return (pn);
                    if (pn.type != 0) return (null);//??
                }
            }
            pn = pn.nx;
        }
        return (null);
    }

    final boolean xperp(int p1, int p2, int p3, int p4) {
        if (p1 == p2 || p3 == p4) return (true);
        return fo_tn1(p1, p2, p3, p4) != null;
    }


    final boolean ln_perp(l_line l1, l_line l2) {
        if (l1 == l2) return false;
        if (l1.type == 0)
            l1 = fd_lnl(l1);
        if (l2.type == 0)
            l2 = fd_lnl(l2);

        t_line tn = all_tn.nx;
        while (tn != null) {
            if ((l1 == tn.l1 && l2 == tn.l2) || (l1 == tn.l2 && l2 == tn.l1)) return (true);
            tn = tn.nx;
        }
        return (false);
    }

    final t_line fo_tn(int p1, int p2, int p3, int p4) {
        l_line ln1, ln2;
        t_line tn;
        if (p1 == p2 || p3 == p4) return (null);
        ln1 = fd_ln(p1, p2);
        if (ln1 == null) return (null);
        ln2 = fd_ln(p3, p4);
        if (ln2 == null) return (null);
        tn = all_tn.nx;
        while (tn != null) {
            if ((tn.l1 == ln1 && tn.l2 == ln2) || (tn.l1 == ln2 && tn.l2 == ln1)) return (tn);
            tn = tn.nx;
        }
        return (null);
    }


    final a_cir xcir(a_cir c1) {
        a_cir c2;
        c2 = all_cir.nx;
        while (c2 != null) {
            if (sub_cir(c1, c2)) return (c2);
            c2 = c2.nx;
        }
        return (null);
    }

    final a_cir fo_cr(int o, int p1, int p2, int p3, int p4) {
        test_c.o = o;
        test_c.no = 3;
        test_c.pt[0] = p1;
        test_c.pt[1] = p2;
        test_c.pt[2] = p3;
        test_c.pt[3] = p4;
        return (xcir(test_c));
    }

    final boolean xcir2(int o, int p1, int p2) {
        test_c.o = o;
        test_c.no = 1;
        test_c.pt[0] = p1;
        test_c.pt[1] = p2;
        return (xcir(test_c) != null);
    }

    final boolean xcir3(int o, int p1, int p2, int p3) {
        test_c.o = o;
        test_c.no = 2;
        test_c.pt[0] = p1;
        test_c.pt[1] = p2;
        test_c.pt[2] = p3;
        return (xcir(test_c) != null);
    }

    final boolean xcir4(int o, int p1, int p2, int p3, int p4) {
        test_c.o = o;
        test_c.no = 3;
        test_c.pt[0] = p1;
        test_c.pt[1] = p2;
        test_c.pt[2] = p3;
        test_c.pt[3] = p4;
        return (xcir(test_c) != null);
    }

    final boolean xcir5(int o, int p1, int p2, int p3, int p4, int p5) {
        test_c.o = o;
        test_c.no = 4;
        test_c.pt[0] = p1;
        test_c.pt[1] = p2;
        test_c.pt[2] = p3;
        test_c.pt[3] = p4;
        test_c.pt[4] = p5;
        return (xcir(test_c) != null);
    }

    final boolean xmid(int m, int a, int b) {
        return (fo_md(m, a, b) != null);
    }

    final midpt fo_md(int m, int a, int b) {
        midpt md = all_md.nx;
        while (md != null) {
            if (m == md.m &&
                    ((a == md.a && b == md.b) || (a == md.b && b == md.a))) {
                return (md);
            }
            md = md.nx;
        }
        return (null);
    }

    final midpt fo_md(int a, int b) {
        midpt md = all_md.nx;
        while (md != null) {
            if (((a == md.a && b == md.b) || (a == md.b && b == md.a))) {
                return (md);
            }
            md = md.nx;
        }
        return (null);
    }

    final boolean xacong(int a, int b, int c, int p, int q, int r) {
        if (!check_eqangle(a, b, c, p, q, r)) {
            return false;
        }

        l_line l1, l2, l3, l4;
        l1 = fd_ln(a, b);
        l2 = fd_ln(b, c);
        l3 = fd_ln(p, q);
        l4 = fd_ln(q, r);
        if (l1 == null || l2 == null || l3 == null || l4 == null) return (false);
        return (ln_acong(l1, l2, l3, l4));
    }

    final boolean xacong(int a, int b, int c, int d, int p, int q, int r, int s) {
        if (!check_eqangle(a, b, c, d, p, q, r, s)) return false;

        l_line l1, l2, l3, l4;
        l1 = fd_ln(a, b);
        l2 = fd_ln(c, d);
        l3 = fd_ln(p, q);
        l4 = fd_ln(r, s);
        if (l1 == null || l2 == null || l3 == null || l4 == null) return (false);
        return (ln_acong(l1, l2, l3, l4));
    }


    final int line_compare(l_line l1, l_line l2) {
        if (l1.id > l2.id) return 1;
        if (l1.id == l2.id) return 0;
        if (l1.id < l2.id) return -1;
        return 0;
    }

    final angles fo_las(l_line l1, l_line l2, l_line l3, l_line l4) {
        angles as;
        if (isPFull())
            as = fo_las0(l1, l2, l3, l4);
        else
            as = fo_las_t(l1, l2, l3, l4);
        return as;
    }

    final angles fo_las0(l_line l1, l_line l2, l_line l3, l_line l4)    //????
    {
        l_line n1, n2;
        angles as;
        if (l1 == l2 || l3 == l4) {
            l_line t = l2;
            l2 = l3;
            l3 = t;

        }
        if (l1 == l4) {
            l_line t = l1;
            l1 = l3;
            l3 = t;
            t = l2;
            l2 = l4;
            l4 = t;
        }
        if (l2 == l3) {
            if (line_compare(l1, l4) > 0) {
                n1 = l4;
                l4 = l1;
                l1 = n1;
            }
        } else if (l1 == l3) {
            if (line_compare(l2, l4) > 0) {
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
        } else if (l2 == l4) {
            n1 = l1;
            l1 = l2;
            l2 = n1;
            n1 = l3;
            l3 = l4;
            l4 = n1;
            if (line_compare(l2, l4) > 0) {
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
        } else {
            if (line_compare(l1, l2) < 0) n1 = l1;
            else n1 = l2;
            if (line_compare(l3, l4) < 0) n2 = l3;
            else n2 = l4;
            if (line_compare(n1, n2) > 0) {
                n1 = l3;
                l3 = l1;
                l1 = n1;
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
            if (line_compare(l1, l2) > 0) {
                n1 = l1;
                l1 = l2;
                l2 = n1;
                n1 = l3;
                l3 = l4;
                l4 = n1;
            }
        }
        as = all_as.nx;
        while (as != null) {
            if (l1 == as.l1 && l4 == as.l4 && ((l2 == as.l2 && l3 == as.l3) || (l2 == as.l3 && l3 == as.l2)))
                return (as);
            as = as.nx;
        }
        return (null);
    }

    final angles fo_as(int a, int b, int c, int d, int p, int q, int r, int s) {
        l_line l1, l2, l3, l4;
        l1 = fd_ln(a, b);
        l2 = fd_ln(c, d);
        l3 = fd_ln(p, q);
        l4 = fd_ln(r, s);
        if (l1 == null || l2 == null || l3 == null || l4 == null) return (null);
        return (fo_las(l1, l2, l3, l4));
    }


    final boolean ln_acong(l_line l1, l_line l2, l_line l3, l_line l4) {
        if (l1 == null || l2 == null || l3 == null || l4 == null) {
            int k = 0;
        }
        if (l1 == l3 && l2 == l4) return (true);
        if (l1 == l2 || l3 == l4) return true;
        if (l1.type == 0)
            l1 = fd_lnl(l1);
        if (l2.type == 0)
            l2 = fd_lnl(l2);
        if (l3.type == 0)
            l3 = fd_lnl(l3);
        if (l4.type == 0)
            l4 = fd_lnl(l4);

        if (l1 == l3 && l2 == l4) return (true);
        if (l1 == l2 || l3 == l4) return true;
        if (ln_para(l1, l2) && ln_para(l3, l4)) return (true);
        if (ln_perp(l1, l2) && ln_perp(l3, l4)) return (true);
        return (fo_las(l1, l2, l3, l4) != null);
    }


    final boolean xcong(int a, int b, int p, int q) {
        if (!check_eqdistance(a, b, p, q)) return false;

        cong_seg cg;
        int o;
        if (a > b) {
            o = a;
            a = b;
            b = o;
        }
        if (p > q) {
            o = p;
            p = q;
            q = o;
        }
        if (a > p || (a == b && b > q)) {
            o = a;
            a = p;
            p = o;
            o = b;
            b = q;
            q = o;
        }

        if (a == p && b == q) return (true);

        cg = all_cg.nx;
        while (cg != null) {
            if (cg.type != 0 && on_cg(a, b, p, q, cg) && cg.t1 == cg.t2) return (true);
            cg = cg.nx;
        }

        return (false);
    }

    final boolean xcong1(int a, int b, int p, int q, int t1, int t2) {
        cong_seg cg;
        int o;
        if (a > b) {
            o = a;
            a = b;
            b = o;
        }
        if (p > q) {
            o = p;
            p = q;
            q = o;
        }
        if (a > p || (a == p && b > q)) {
            o = a;
            a = p;
            p = o;
            o = b;
            b = q;
            q = o;
            o = t1;
            t1 = t2;
            t2 = o;
        }

        if (a == p && b == q) return (true);

        cg = all_rg.nx;
        while (cg != null) {
            if (cg.type != 0 && on_cg(a, b, p, q, cg)) break;
            cg = cg.nx;
        }
        if (cg == null) return false;
        if (cg.p1 == a && cg.p2 == b || cg.p2 == a && cg.p1 == b)
            return cg.t1 * t2 == cg.t2 * t1;
        else
            return cg.t1 * t1 == cg.t2 * t2;
    }

    final boolean xcong_all(int a, int b, int c, int d) {
        return xcong(a, b, c, d) || xcong1(a, b, c, d);
    }

    final boolean xcong1(int a, int b, int p, int q) {
        cong_seg cg;
        int o;
        if (a > b) {
            o = a;
            a = b;
            b = o;
        }
        if (p > q) {
            o = p;
            p = q;
            q = o;
        }
        if (a > p || (a == p && b > q)) {
            o = a;
            a = p;
            p = o;
            o = b;
            b = q;
            q = o;
        }

        if (a == p && b == q) return (true);

        cg = all_rg.nx;
        while (cg != null) {
            if (cg.type != 0 && on_cg(a, b, p, q, cg)) return (true);
            cg = cg.nx;
        }
        return (false);
    }

    final cong_seg fo_rg1(int a, int b, int p, int q) {
        cong_seg cg = all_rg.nx;
        while (cg != null) {
            if (cg.type != 0 && on_cg(a, b, p, q, cg)) return (cg);
            cg = cg.nx;
        }
        return null;
    }

    final cong_seg fo_cg1(int a, int b, int p, int q) {
        cong_seg cg;
        int o;
        if (a > b) {
            o = a;
            a = b;
            b = o;
        }
        if (p > q) {
            o = p;
            p = q;
            q = o;
        }
        if (a == p && b == q) return (null);
        cg = all_rg.nx;
        while (cg != null) {
            if (on_cg(a, b, cg) && on_cg(p, q, cg)) return (cg);
            cg = cg.nx;
        }
        return (null);
    }

    final cong_seg fo_cg(int a, int b, int p, int q) {
        cong_seg cg;
        int o;
        if (a > b) {
            o = a;
            a = b;
            b = o;
        }
        if (p > q) {
            o = p;
            p = q;
            q = o;
        }
        if (a == p && b == q) return (null);
        cg = all_cg.nx;
        while (cg != null) {
            if (on_cg(a, b, cg) && on_cg(p, q, cg)) return (cg);
            cg = cg.nx;
        }
        return (null);
    }

    boolean xsim_tri(int a, int b, int c, int p, int q, int r) {
        if (!check_simtri(a, b, c, p, q, r)) return false;

        return (fo_st(1, 0, a, b, c, p, q, r) != null);
    }

    boolean xcon_tri(int a, int b, int c, int p, int q, int r) {
        if (!this.check_simtri(a, b, c, p, q, r)) return false;
        return (fo_st(0, 0, a, b, c, p, q, r) != null);
    }

    sim_tri fo_st(int xsim_2, int xsim_1, int a, int b, int c, int p, int q, int r) {
        sim_tri st = (xsim_2 != 0) ? all_st.nx : all_ct.nx; //???
        while (st != null) {
            if ((xsim_1 != 0 || st.type != 0) &&
                    ((on_st(a, b, c, p, q, r, st))))
                return (st);
            st = st.nx;
        }
        return (null);
    }

    boolean xeq_ratio(int a, int b, int c, int d, int p, int q, int r, int s) {
        if ((xcong(a, b, p, q) && xcong(c, d, r, s)) ||
                (xcong(a, b, c, d) && xcong(p, q, r, s)) ||
                (xcong(a, b, r, s) && xcong(c, d, p, q)))
            return (true);
        return (fo_ra(a, b, c, d, p, q, r, s) != null);
    }

    ratio_seg fo_ra(int a, int b, int c, int d, int p, int q, int r, int s) {
        ratio_seg ra = all_ra.nx;
        for (; ra != null; ra = ra.nx) {
            if ((((xcong(a, b, ra.r[1], ra.r[2]) && xcong(r, s, ra.r[7], ra.r[8])) ||
                    (xcong(a, b, ra.r[7], ra.r[8]) && xcong(r, s, ra.r[1], ra.r[2]))) &&
                    ((xcong(c, d, ra.r[3], ra.r[4]) && xcong(p, q, ra.r[5], ra.r[6])) ||
                            (xcong(c, d, ra.r[5], ra.r[6]) && xcong(p, q, ra.r[3], ra.r[4])))) ||
                    (((xcong(a, b, ra.r[3], ra.r[4]) && xcong(r, s, ra.r[5], ra.r[6])) ||
                            (xcong(a, b, ra.r[5], ra.r[6]) && xcong(r, s, ra.r[3], ra.r[4]))) &&
                            ((xcong(c, d, ra.r[1], ra.r[2]) && xcong(p, q, ra.r[7], ra.r[8])) ||
                                    (xcong(c, d, ra.r[7], ra.r[8]) && xcong(p, q, ra.r[1], ra.r[2])))))
                return (ra);
        }
        return (null);
    }


    boolean ln_less(l_line l1, l_line l2) {
        if (l1 == l2) return (false);
        if (l1.pt[1] < l2.pt[1]) return (true);
        if (l1.pt[1] > l2.pt[1]) return (false);
        if (l1.pt[0] < l2.pt[0]) return (true);
        return (false);
    }

    boolean l2_less(l_line l1, l_line l2, l_line l3, l_line l4) {
        l_line ln;
        if (ln_less(l2, l1)) {
            ln = l1;
            l1 = l2;
            l2 = ln;
        }
        if (ln_less(l4, l3)) {
            ln = l3;
            l3 = l4;
            l4 = ln;
        }
        if (ln_less(l2, l4)) return (true);
        if (l2 == l4 && ln_less(l1, l3)) return (true);
        return (false);
    }


    int get_cpt2(a_cir c1, int p1, int p2) {
        char j;
        if (c1 == null) return 0;  /// 2006.7.10
        for (j = 0; j <= c1.no; j++) {
            if (c1.pt[j] != p1 && c1.pt[j] != p2) return (c1.pt[j]);
        }
        return (0);
    }

    int get_cpt3(a_cir c1, int p1, int p2, int p3) {
        char j;
        for (j = 0; j <= c1.no; j++) {
            if (c1.pt[j] != p1 && c1.pt[j] != p2 && c1.pt[j] != p3)
                return (c1.pt[j]);
        }
        return (0);
    }

    int inter_ll(l_line l1, l_line l2) {
        if (l1 == null || l2 == null || l1 == l2) return (0);
        l_line ln1, ln2;
        ln1 = ln2 = null;

        if (l1.type == 0) ln1 = fd_ln(l1.pt[0], l1.pt[1]);
        if (l2.type == 0) ln2 = fd_ln(l2.pt[0], l2.pt[1]);
        if (l1 == l2) return 0;
        if (l1 == null || l2 == null) {
            int k = 0;
        }
        if (ln1 != null)
            l1 = ln1;
        if (ln2 != null)
            l2 = ln2;
        for (int i = 0; i <= l1.no; i++)
            for (int j = 0; j <= l2.no; j++) {
                if (l1.pt[i] == l2.pt[j]) return (l1.pt[i]);
            }
        return (0);
    }

    int inter_ll1(l_line l1, l_line l2, int p1) {
        char i, j;
        if (l1 == l2) return (0);
        if (l1.type == 0) l1 = fd_ln(l1.pt[0], l1.pt[1]);
        if (l2.type == 0) l2 = fd_ln(l2.pt[0], l2.pt[1]);
        for (i = 0; i <= l1.no; i++)
            for (j = 0; j <= l2.no; j++) {
                if (l1.pt[i] == l2.pt[j] && !meq_pt(l1.pt[i], p1)) return (l1.pt[i]);
            }
        return (0);
    }

    int inter_lc(l_line l1, a_cir c1) {
        char i, j;
        if (l1 == null || c1 == null) return (0);
        for (i = 0; i <= l1.no; i++)
            for (j = 0; j <= c1.no; j++) {
                if (l1.pt[i] == c1.pt[j]) return (l1.pt[i]);
            }
        return (0);
    }

    int inter_lc1(l_line l1, a_cir c1, int p1) {

        if (l1 == null || c1 == null) return (0);
        for (int i = 0; i <= l1.no; i++)
            for (int j = 0; j <= c1.no; j++) {
                if (l1.pt[i] == c1.pt[j] && !meq_pt(l1.pt[i], p1)) return (l1.pt[i]);
            }
        return (0);
    }

    int inter_cc(a_cir c1, a_cir c2) {
        char i, j;
        for (i = 0; i <= c1.no; i++)
            for (j = 0; j <= c2.no; j++) {
                if (c1.pt[i] == c2.pt[j]) return (c1.pt[i]);
            }
        return (0);
    }

    int inter_cc1(a_cir c1, a_cir c2, int p1) {
        char i, j;
        for (i = 0; i <= c1.no; i++)
            for (j = 0; j <= c2.no; j++) {
                if (c1.pt[i] == c2.pt[j] && !meq_pt(c1.pt[i], p1)) return (c1.pt[i]);
            }
        return (0);
    }

    int fd_pt_la(int o, int p1, int p2) {
        int i, p3;
        l_line ln;
        ln = fd_ln(p1, p2);
        if (ln == null || ln.no <= 1) return (0);
        for (i = 0; i <= ln.no; i++) {
            p3 = ln.pt[i];
            if (!meq_pt(p3, p1) && !meq_pt(p3, p2) && xacong(p1, o, p3, p3, o, p2)) return (p3);
        }
        return (0);
    }

    int common_pp(p_line pl1, p_line pl2) {
        char i, j;
        for (i = 0; i <= pl1.no; i++)
            for (j = 0; j <= pl2.no; j++) {
                if (pl1.ln[i] == pl2.ln[j]) return (1);
            }
        return (0);
    }


    int fd_pt_md(int a, int b) {
        midpt md = all_md.nx;
        while (md != null) {
            if ((a == md.a && b == md.b) || (a == md.b && b == md.a)) return (md.m);
            md = md.nx;
        }
        return (0);
    }

    int fd_pt_ref(int a, int o) {
        midpt md = all_md.nx;
        while (md != null) {
            if (md.m == o && a == md.a) return (md.b);
            if (md.m == o && a == md.b) return (md.a);
            md = md.nx;
        }
        return (0);
    }


    midpt fd_md_ml(int m, l_line l) {
        midpt md = all_md.nx;
        while (md != null) {
            if (md.m == m && on_ln(md.a, l)) return (md);
            md = md.nx;
        }
        return (null);
    }

    final void add_mid(int lm, int pt, int p1, int p2) {
        midpt md;
        if (p1 == p2 || !check_mid(pt, p1, p2)) {
            this.add_checkError();
            return;
        }

        if (!xmid(pt, p1, p2)) {
            md = new midpt();
            md.m = pt;
            md.a = p1;
            md.b = p2;
            md.type = 1;
            md.lemma = lm;
            md.nx = null;
            md.co = co_db.nx;
            last_md.nx = md;
            last_md = md;
            new_pr(CO_MIDP);
            last_nd.u.md = md;
        }
    }

    boolean on_ln(int p, l_line ln) {
        int i;
        if (ln == null) return (false);
        for (i = 0; i <= ln.no; i++) if (ln.pt[i] == p) return (true);
        return (false);
    }

    boolean sub_ln(l_line l1, l_line l2) {
        int i;
        for (i = 0; i <= l1.no; i++) if (!on_ln(l1.pt[i], l2)) return (false);
        return (true);
    }

    final void add_pt2l(int p, l_line ln) {
        int j, i = 0;
        while (i <= ln.no && ln.pt[i] < p) i++;
        if (i > ln.no) {
            ln.no += 1;
            ln.pt[ln.no] = p;
        } else {
            if (ln.pt[i] == p) return;
            for (j = ln.no; j >= i; j--) {
                ln.pt[j + 1] = ln.pt[j];
            }
            ln.pt[i] = p;
            ln.no += 1;
        }
    }

    l_line fd_ln(int p1, int p2) {
        l_line ln = all_ln.nx;
        while (ln != null && !(ln.type != 0 && on_ln(p1, ln) && on_ln(p2, ln))) {
            ln = ln.nx;
        }
        return (ln);
    }

    l_line fd_ln3(int p1, int p2, int p3) {
        l_line ln = all_ln.nx;
        while (ln != null) {
            if (ln.type != 0) {
                if (on_ln(p1, p2, ln) && on_ln(p3, ln))
                    return ln;
            }
            ln = ln.nx;
        }
        return (null);
    }

    l_line fd_pline(int p1, int p2, int p3) {
        int i;
        p_line pn;
        pn = fd_pn(p2, p3);
        if (pn == null) return (null);
        for (i = 0; i <= pn.no; i++)
            if (on_ln(p1, pn.ln[i])) return (pn.ln[i]);
        return (null);
    }

    l_line fd_tline(int p1, int p2, int p3) {
        l_line ln;
        t_line tn;
        ln = fd_ln(p2, p3);
        if (ln == null) return (null);
        tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0) //??
            {
                if (tn.l1 == ln && on_ln(p1, tn.l2)) return (tn.l2);
                if (tn.l2 == ln && on_ln(p1, tn.l1)) return (tn.l1);
            }
            tn = tn.nx;
        }
        return (null);
    }

    l_line fd_ln_pn1(p_line pn, int p) {
        int i;
        if (pn == null) return (null);
        for (i = 0; i <= pn.no; i++) if (on_ln(p, pn.ln[i])) return (pn.ln[i]);
        return (null);
    }

    l_line fd_ln1(int p1) {
        l_line ln = all_ln.nx;
        while (ln != null) {
            if (on_ln(p1, ln) && ln.type != 0 && ln.no > 1) return (ln);
            ln = ln.nx;
        }
        return (null);
    }

    l_line fd_lnl(l_line l1) {
        l_line ln = all_ln.nx;
        int p1, p2;
        while (ln != null) {
            if (ln != l1 && ln.type != 0) {
                p1 = inter_ll(l1, ln);
                p2 = inter_ll1(l1, ln, p1);
                if (p1 != 0 && p2 != 0) return (ln);
            }
            ln = ln.nx;
        }
        return (null);
    }

    l_line fd_ln_rl(l_line l1, int p) {
        if (l1.type != 0) return l1;

        l_line l = fd_lnl(l1);
        if (l == l1) return l;

        test_ln.cp_ln(l1);
        int p1 = get_lpt1(l1, p);
        for (int i = 0; i <= l.no; i++) {
            if (!on_ln(l.pt[i], test_ln) && ck_dr(p, p1, p, l.pt[i]))
                add_pt2l(l.pt[i], test_ln);
        }
        if (test_ln.no == l.no) return l;

        l_line ln = all_ln.nx;
        while (ln != null) {
            if (ln != null)
                if (sub_ln(test_ln, ln) && test_ln.no == ln.no)
                    return ln;
            ln = ln.nx;
        }
        ln = add_ln(test_ln.pt[0], test_ln.pt[1]);
        for (int i = 2; i <= test_ln.no; i++)
            add_pt2l(test_ln.pt[i], ln);
        ln.type = 0;
        return ln;


    }


    l_line add_ln(int p1, int p2) {

        if (p1 == p2) {
            int k = 0;
            return null;
        }
        l_line ln;
        ln = new l_line();
        ln.type = 1;
        ln.co = null;
        ln.no = 1;
        if (p1 < p2) {
            ln.pt[0] = p1;
            ln.pt[1] = p2;
        } else {
            ln.pt[0] = p2;
            ln.pt[1] = p1;
        }
        ln.nx = null;
        last_ln.nx = ln;
        last_ln = ln;
        return (ln);
    }

    l_line add_ln2l(l_line l1, l_line l2) {
        test_ln.cp_ln(l1);
        l_line ln = test_ln;
        for (int i = 0; i <= l2.no; i++)
            add_pt2l(l2.pt[i], ln);

        l_line l = all_ln.nx;
        while (l != null) {
            if (l.type != 0 && sub_ln(ln, l))
                return null;
            l = l.nx;
        }

        ln = cp_ln(test_ln);
        ln.type = 2;
        return ln;
    }

    l_line fadd_ln(int p1, int p2) {
        l_line ln;
        ln = fd_ln(p1, p2);
        if (ln != null) return ln;
        return (add_ln(p1, p2));
    }


    l_line cp_ln(l_line ln) {
        l_line ln1;
        ln1 = new l_line();
        ln1.no = ln.no;
        for (int i = 0; i <= ln.no; i++) {
            ln1.pt[i] = ln.pt[i];
        }
        last_ln.nx = ln1;
        last_ln = ln1;
        return (ln1);
    }

    final void ch_ln(l_line ln1, l_line ln2) {

        l_line l1, l2, l3, l4;

        p_line pn = all_pn.nx;
        while (pn != null) {
            if (pn.type != 0 && on_pn(ln1, pn)) {
                p_line pn1 = cp_pn(pn);
                pn.type = 0;
                if (on_pn(ln2, pn1)) {
                    int i = 0;
                    while (pn1.ln[i] != ln1) i++;
                    for (int j = i; j <= pn1.no; j++)
                        pn1.ln[j] = pn1.ln[j + 1];

                    pn1.no--;
                } else {
                    for (int i = 0; i <= pn1.no; i++) if (pn1.ln[i] == ln1) pn1.ln[i] = ln2;
                }
                {
                    co_xy.nx = null;
                    cond co = add_coxy(CO_PARA);
                    co.u.pn = pn;
                    co = add_coxy(CO_COLL);
                    co.u.ln = ln2;
                    pn1.co = co_xy.nx;
                }
                if (pn1.no == 0) {
                    pn1.type = 0;
                } else {
                    new_para(pn1);
                }
            }
            pn = pn.nx;
        }
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0 && on_tn(ln1, tn)) {

                if (tn.l1 == ln1) {
                    l1 = ln2;
                    l2 = tn.l2;
                } else if (tn.l2 == ln1) {
                    l1 = tn.l1;
                    l2 = ln2;
                } else
                    l1 = l2 = null;

                if (l1 != null && l2 != null) {
                    t_line tn1 = add_tline(402, l1, l2);
                    tn.type = 0;
                    if (tn1 != null) {
                        co_xy.nx = null;
                        cond co = add_coxy(CO_PERP);
                        co.u.tn = tn;
                        co = add_coxy(CO_COLL);
                        co.u.ln = ln2;
                        tn1.co = co_xy.nx;
                        new_pr(CO_PERP);
                        last_nd.u.tn = tn1;
                        new_ot();
                    }

                }
            }
            tn = tn.nx;
        }
        angles as = all_as.nx;
        while (as != null) {
            if (as.type != 0) {
                int i = 0;
                l1 = as.l1;
                l2 = as.l2;
                l3 = as.l3;
                l4 = as.l4;

                if (as.l1 == ln1) {
                    l1 = ln2;
                    i = 1;
                }
                if (as.l2 == ln1) {
                    l2 = ln2;
                    i = 1;
                }
                if (as.l3 == ln1) {
                    l3 = ln2;
                    i = 1;
                }
                if (as.l4 == ln1) {
                    l4 = ln2;
                    i = 1;
                }
                if (i == 1) {
                    angles as1 = add_ea_ln(0, l1, l2, l3, l4);
                    as.type = 0;
                    if (as1 != null) {
                        co_xy.nx = null;
                        cond co = add_coxy(CO_ACONG);
                        co.u.as = as;
                        co = add_coxy(CO_COLL);
                        co.u.ln = ln2;
                        as1.co = co_xy.nx;

                        new_pr(CO_ACONG);
                        last_nd.u.as = as1; /*  new_ot();*/
                    }
                }
            }
            as = as.nx;
        }

        angtn atn = all_atn.nx;
        co_db.nx = null;

        while (atn != null) {

            if (atn.type == 0 || atn.ln1 != ln1 && atn.ln2 != ln1 && atn.ln3 != ln1 && atn.ln4 != ln1) {
                atn = atn.nx;
                continue;
            }
            ch_ln_atn(ln1, ln2, atn);
            atn = atn.nx;
        }
    }

    final void add_line(int lm, int p1, int p2, int p3) {
        l_line ln1;
        if (xcoll(p1, p2, p3)) return;

        if (p1 == 0 || p2 == 0 || p3 == 0) {
            int k = 0;
            return;
        }
        if (!check_coll(p1, p2, p3)) {
            add_checkError();
            return;
        }
        ln1 = fd_ln3(p1, p2, p3);
        if (ln1 != null) return;

        if (false || co_db.nx == null && (ln1 = fd_ln3(p1, p2, p3)) != null && ln1.co == null) {
            add_pt2l(p1, ln1);
            add_pt2l(p2, ln1);
            add_pt2l(p3, ln1);
        } else {
            ln1 = add_ln(p1, p2);
            add_pt2l(p3, ln1);
            ln1.co = co_db.nx;
            ln1.lemma = lm;
        }
        ch_lns(ln1);
    }

    final void add_line1(int lm, int p1, int p2, int p3) {      // for hypothesis only.
        if (!check_coll(p1, p2, p3)) {
            add_checkError();
            return;
        }
        l_line ln1 = fd_ln(p2, p3);
        if (ln1 != null) {
            add_pt2l(p1, ln1);
            ch_lns(ln1);
            return;
        }
        add_line(lm, p1, p2, p3);
    }

    public void ch_lns(l_line ln1) {
        l_line ln2, ln3, ln4;
        ln2 = ln1;
        while ((ln3 = fd_lnl(ln2)) != null) {
            if (sub_ln(ln2, ln3)) {
                ch_ln(ln2, ln3);
                ln2.type = 0;
                ln2 = ln3;
            } else if (sub_ln(ln3, ln2)) {
                ch_ln(ln3, ln2);
                ln3.type = 0;

            } else {
                ln4 = cp_ln(ln2);

                ln4.type = 1;
                ln4.lemma = 0;
                for (int i = 0; i <= ln3.no; i++) {
                    add_pt2l(ln3.pt[i], ln4);
                }
                if (ln2.co == null && ln3.co == null) {
                    ln4.co = null;
                } else {
                    co_xy.nx = null;
                    cond co = add_coxy(CO_COLL);
                    co.u.ln = ln2;
                    co = add_coxy(CO_COLL);
                    co.u.ln = ln3;
                    ln4.co = co_xy.nx;
                }
                ch_ln(ln2, ln4);
                ch_ln(ln3, ln4);
                ln2.type = 0;
                ln3.type = 0;
                ln2 = ln4;
            }
        }
        new_pr(CO_COLL);
        last_nd.u.ln = ln2;
    }

    boolean on_pn(l_line ln, p_line pn) {
        int i;
        for (i = 0; i <= pn.no; i++) if (pn.ln[i] == ln) return (true);
        return (false);
    }

    p_line fd_pnl(l_line ln) {
        p_line pn;
        pn = all_pn.nx;
        while (pn != null && !(pn.type == 1 && on_pn(ln, pn))) {
            pn = pn.nx;
        }
        return (pn);
    }

    l_line fd_lpp2(int p, l_line ln) {
        p_line pn;
        pn = all_pn.nx;
        while (pn != null && !(pn.type == 1 && on_pn(ln, pn))) {
            pn = pn.nx;
        }
        if (pn == null) return null;
        for (int i = 0; i <= pn.no; i++)
            if (on_ln(p, pn.ln[i]))
                return pn.ln[i];
        return (null);
    }

    p_line fd_pn(int p1, int p2) {
        l_line ln = fd_ln(p1, p2);
        if (ln == null) return (null);
        return (fd_pnl(ln));
    }

    p_line fd_pnp(p_line pn, l_line ln) {
        p_line pn1;
        pn1 = all_pn.nx;
        while (pn1 != null)
            if (pn1 != pn && pn1.type != 0 && on_pn(ln, pn1)) return (pn1);
            else pn1 = pn1.nx;
        return (null);
    }

    p_line cp_pn(p_line pn) {
        p_line pn1;
        char i;
        pn1 = new p_line();
        pn1.type = pn.type;
        pn1.nx = null;
        pn1.co = null;
        pn1.no = pn.no;
        for (i = 0; i <= pn.no; i++) {
            pn1.ln[i] = pn.ln[i];
        }
        last_pn.nx = pn1;
        last_pn = pn1;
        return (pn1);
    }

    final void pn_un(p_line pn1, p_line pn2) {
        for (int i = 0; i <= pn2.no; i++)
            if (!on_pn(pn2.ln[i], pn1)) {
                pn1.no += 1;
                pn1.ln[pn1.no] = pn2.ln[i];
                if (!check_para(pn1.ln[0].pt[0], pn1.ln[0].pt[1], pn2.ln[i].pt[0], pn2.ln[i].pt[1])) {
                    int k = 0;
                }
            }
    }


    final p_line fd_pn(l_line ln1, l_line ln2) {
        p_line pn1 = all_pn.nx;
        while (pn1 != null)
            if (pn1.type != 0 && on_pn(ln1, pn1) && on_pn(ln2, pn1))
                return (pn1);
            else
                pn1 = pn1.nx;
        return null;
    }


    final void add_pline(int lm, int p1, int p2, int p3, int p4) {
        if (!valid(lm)) return;

        p_line pn;
        if (xpara(p1, p2, p3, p4)) return;
        if (!check_para(p1, p2, p3, p4)) {
            add_checkError();
            return;
        }
        {
            if (p1 == p3 || p1 == p4 || p2 == p3 || p2 == p4) {
                int k = 0;
            }

            pn = new p_line();
            pn.type = 2;
            pn.lemma = lm;
            pn.co = co_db.nx;
            pn.no = 1;
            l_line ln1 = fadd_ln(p1, p2);
            l_line ln2 = fadd_ln(p3, p4);
            if (ln1.pt[0] < ln2.pt[0] || (ln1.pt[0] == ln2.pt[0] && ln1.pt[1] < ln2.pt[1])) {
                pn.ln[0] = ln1;
                pn.ln[1] = ln2;
            } else {
                pn.ln[0] = ln2;
                pn.ln[1] = ln1;
            }

            pn.nx = null;
            last_pn.nx = pn;
            last_pn = pn;
        }
        new_pr(CO_PARA);
        last_nd.u.pn = pn;
        //adj_pn(pn);
    }


    p_line add_px(int lm, l_line ln1, l_line ln2) {
        if (!valid(lm)) return null;

        p_line pn;
        if (ln_para(ln1, ln2)) return (null);
        {
            pn = new p_line();
            pn.type = 2;
            pn.lemma = lm;
            pn.nx = null;
            pn.co = co_xy.nx;
            pn.no = 1;
            pn.ln[0] = ln1;
            pn.ln[1] = ln2;
            last_pn.nx = pn;
            last_pn = pn;
        }
        new_pr(CO_PARA);
        last_nd.u.pn = pn;
        return (pn);
    }


    boolean on_tn(l_line ln, t_line tn) {
        if ((ln == tn.l1) || (ln == tn.l2)) return (true);
        return (false);
    }

    t_line fd_tn(l_line ln) {
        t_line tn;
        tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0 && (tn.l1 == ln || tn.l2 == ln))
                return tn;
            tn = tn.nx;
        }


        return (tn);
    }

    t_line add_tn(l_line ln1, l_line ln2) {
        t_line tn = new t_line();
        tn.l1 = ln1;
        tn.l2 = ln2;
        tn.type = 2;
        tn.co = null;
        tn.nx = null;
        last_tn.nx = tn;
        last_tn = tn;
        return (tn);
    }

    final t_line add_tline(int lm, l_line l1, l_line l2) {
        if (!valid(lm)) return null;

        if (l1 == null || l2 == null) return null;
        if (ln_perp(l1, l2)) return null;
        if (!check_perp(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1])) {
            int k = 0;
        }
        t_line tn1 = add_tn(l1, l2);

        tn1.lemma = lm;
        tn1.co = co_db.nx;
        new_pr(CO_PERP);
        last_nd.u.tn = tn1;
        return tn1;
    }

    final void add_tline(int lm, int p1, int p2, int p3, int p4) {
        if (!valid(lm)) return;

        l_line ln1, ln2;
        t_line tn1;
        if (xperp(p1, p2, p3, p4)) return;
        if (!check_perp(p1, p2, p3, p4)) {
            int k = 0;
        }
        if (search_only_exists_ln() && (fd_ln(p1, p2) == null || fd_ln(p3, p4) == null))
            return;

        ln1 = fadd_ln(p1, p2);
        ln2 = fadd_ln(p3, p4);
        if (ln1 == null || ln2 == null) return;

        tn1 = add_tn(ln1, ln2);
        tn1.lemma = lm;
        tn1.co = co_db.nx;
        new_pr(CO_PERP);
        last_nd.u.tn = tn1;
    }

    final t_line add_tx(int lm, l_line l1, l_line l2) {
        if (!valid(lm)) return null;

        t_line tn1;
        if (l1 == null || l2 == null) return null;
        if (ln_perp(l1, l2)) return null;

        tn1 = add_tn(l1, l2);
//        if (l1.type == 0 || l2.type == 0)
//            tn1.type = 0;
        tn1.lemma = lm;
        tn1.co = co_xy.nx;
        new_pr(CO_PERP);
        last_nd.u.tn = tn1;
        return tn1;
    }


    public void add_tline_t(int lm, int p1, int p2, int p3, int p4) {
        if (!valid(lm)) return;

        if (xperp(p1, p2, p3, p4)) return;
        if (!check_perp(p1, p2, p3, p4)) {
            add_checkError();
            return;
        }
        if (search_only_exists_ln() && (fd_ln(p1, p2) == null || fd_ln(p3, p4) == null))
            return;

        l_line ln1 = fadd_ln_t(p1, p2);
        l_line ln2 = fadd_ln_t(p3, p4);

        if (ln1 == null || ln2 == null) return;

        t_line tn1 = add_tn(ln1, ln2);
//        if (ln1.type == 0 || ln2.type == 0)
//            tn1.type = 0;

        tn1.lemma = lm;
        tn1.co = co_db.nx;
        new_pr(CO_PERP);
        last_nd.u.tn = tn1;
    }


    boolean on_cir(int a, a_cir cr) {
        char i;
        if (a == 0) return (true);
        for (i = 0; i <= cr.no; i++) if (cr.pt[i] == a) return (true);
        return (false);
    }

    int eq_cir(int o, int a, a_cir cr) {
        char i;
        if (cr.o != o) return (0);
        for (i = 0; i <= cr.no; i++) if (cr.pt[i] == a) return (1);
        return (0);
    }

    boolean sub_cir(a_cir c1, a_cir c2) {
        char i;
        if (c1.o == c2.o || c1.o == 0) {
            for (i = 0; i <= c1.no; i++) {
                if (!on_cir(c1.pt[i], c2)) return (false);
            }
            return (true);
        }
        return (false);
    }

    boolean circle_p(int ptn) {
        char i;
        a_cir c1 = all_cir.nx;
        if (ptn < 3) return (false);

        if (ptn == 3 && ATYPE(3) == C_POINT && ATYPE(2) == C_POINT
                && ATYPE(1) == C_POINT)
            return (false);
        for (i = 1; i <= ptn; i++)
            if (!(ATYPE(i) == C_POINT || ATYPE(i) == C_O_C || ATYPE(i) == C_CIRCLE || ATYPE(i) == C_CIRCUM))
                return (false);

        while (c1 != null) {
            for (i = 1; i <= ptn; i++)
                if (i != c1.o && !(on_cir(i, c1))) {
                    break;
                } else
                    return true;
            l1:
            c1 = c1.nx;
        }

        Cm.print("function : circle_p. careful! ");
        return (false);
    }

    int fd_co(int p1, int p2, int p3) {
        a_cir c2;
        c2 = all_cir.nx;
        while (c2 != null) {
            if (c2.o != 0 && on_cir(p1, c2) && on_cir(p2, c2) && on_cir(p3, c2)) return (c2.o);
            c2 = c2.nx;
        }
        return (0);
    }

    a_cir fd_cr_op(int o, int p) {
        a_cir c2 = all_cir.nx;
        while (c2 != null) {
            if (c2.type != 0 && c2.o == o && on_cir(p, c2)) return (c2);
            c2 = c2.nx;
        }
        return (null);
    }

    a_cir fd_cr_p3(int p1, int p2, int p3) {
        a_cir c2 = all_cir.nx;
        while (c2 != null) {
            if (c2.type != 0 && on_cir(p1, c2) && on_cir(p2, c2) && on_cir(p3, c2)) return (c2);
            c2 = c2.nx;
        }
        return (null);
    }

    a_cir fd_cir(a_cir c1) {
        a_cir c2;
        char i, j;
        c2 = all_cir.nx;
        while (c2 != null) {
            if (c2 != c1 && c2.type != 0)
                if (c2.o == c1.o && c1.o != 0) {
                    for (i = 0; i <= c1.no; i++) if (on_cir(c1.pt[i], c2)) return (c2);
                } else {
                    j = 0;
                    for (i = 0; i <= c1.no; i++) if (on_cir(c1.pt[i], c2)) j++;
                    if (j > 2) return (c2);
                }
            c2 = c2.nx;
        }
        return (null);
    }

    a_cir fd_cir(int o, int a) {
        if (o == 0 || a == 0) return null;

        a_cir cr = all_cir.nx;
        while (cr != null) {
            if (cr.type != 0 && cr.o == o) {
                for (int i = 0; i <= cr.no; i++)
                    if (cr.pt[i] == a) return cr;
            }
            cr = cr.nx;
        }
        return null;

    }

    final void add_pt2c(int p, a_cir cr) {
        int j, i = 0;
        if (p <= 0) return;


        while (i <= cr.no && cr.pt[i] < p) i++;
        if (i > cr.no) {
            cr.no += 1;
            cr.pt[cr.no] = p;
        } else {
            if (cr.pt[i] == p) return;
            for (j = cr.no; j >= i; j--) {
                cr.pt[j + 1] = cr.pt[j];
            }
            cr.pt[i] = p;
            cr.no += 1;
        }
    }

    a_cir add_c2(int lm, int o, int a, int b) {
        if (!valid(lm)) return null;

        a_cir cr = new a_cir();
        cr.type = 2;
        cr.lemma = lm;
        cr.co = null;
        cr.o = o;
        cr.no = 1;
        cr.nx = null;
        if (a < b) {
            cr.pt[0] = a;
            cr.pt[1] = b;
        } else {
            cr.pt[0] = b;
            cr.pt[1] = a;
        }
        last_cir.nx = cr;
        last_cir = cr;
        return (cr);
    }

    a_cir cp_cr(a_cir cir) {
        a_cir cir1;
        char i;
        cir1 = new a_cir();
        cir1.type = cir.type;
        cir1.o = cir.o;
        cir1.co = cir.co;
        cir1.no = cir.no;
        for (i = 0; i <= cir.no; i++) {
            cir1.pt[i] = cir.pt[i];
        }
        last_cir.nx = cir1;
        last_cir = cir1;
        return (cir1);
    }

    final void adj_cir(a_cir cr) {
        a_cir cr1, cr2, cr3;
        cond co;
        char i;
        cr1 = cr;
        while ((cr2 = fd_cir(cr1)) != null) {
            if (sub_cir(cr2, cr1)) {
                cr2.type = 0;
            } else if (sub_cir(cr1, cr2)) {
                cr1.type = 0;
                cr1 = cr2;
            } else {
                cr3 = cp_cr(cr1);
                cr1.type = 0;
                cr2.type = 0;
                if (cr3.o == 0) cr3.o = cr2.o;
                for (i = 0; i <= cr2.no; i++) add_pt2c(cr2.pt[i], cr3);
                if (cr1.co == null && cr2.co == null)
                    cr3.co = null;
                else {
                    co_xy.nx = null;
                    co = add_coxy(CO_CYCLIC);
                    co.u.cr = cr1;
                    co = add_coxy(CO_CYCLIC);
                    co.u.cr = cr2;
                    cr3.co = co_xy.nx;
                }
                cr1 = cr3;
            }
        }
        new_pr(CO_CYCLIC);
        last_nd.u.cr = cr1;
    }

    final void add_cir2(int lm, int o, int a, int b) {
        if (!valid(lm)) return;

        if (xcir_n(o, a, b, 0, 0, 0)) return;
        a_cir cr = add_c2(lm, o, a, b);
        cr.co = co_db.nx;
        new_pr(CO_CYCLIC);
        last_nd.u.cr = cr;
    }

    final void add_cir3(int lm, int o, int a, int b, int c) {
        if (!valid(lm)) return;

        a_cir cr;
        if (xcir_n(o, a, b, c, 0, 0)) return;
        {
            cr = add_c2(lm, o, a, b);
            add_pt2c(c, cr);
            cr.co = co_db.nx;
        }
        new_pr(CO_CYCLIC);
        last_nd.u.cr = cr;
    }

    final void add_cir4(int lm, int o, int a, int b, int c, int d) {
        if (!valid(lm)) return;

        a_cir cr;
        //if (xcir4(o, a, b, c, d)) return;
        if (xcir_n(o, a, b, c, d, 0)) return;
        {
            cr = add_c2(lm, o, a, b);
            add_pt2c(c, cr);
            add_pt2c(d, cr);
            cr.co = co_db.nx;
        }
        //adj_cir(cr);
        new_pr(CO_CYCLIC);
        last_nd.u.cr = cr;
    }


    boolean onl_as(l_line l1, l_line l2, angles as) {
        if (l1 == as.l1 && l2 == as.l2) return (true);
        if (l1 == as.l3 && l2 == as.l4) return (true);
        return (false);
    }

    angles fd_as(l_line l1, l_line l2) {
        angles as;
        for (as = all_as.nx; as != null; as = as.nx) {
            if (
                    (l1 == as.l1 && l2 == as.l2) || (l1 == as.l2 && l2 == as.l1) ||
                            (l1 == as.l3 && l2 == as.l4) || (l1 == as.l4 && l2 == as.l3) ||
                            (l1 == as.l1 && l2 == as.l3) || (l1 == as.l3 && l2 == as.l1) ||
                            (l1 == as.l2 && l2 == as.l4) || (l1 == as.l4 && l2 == as.l2))
                return (as);
        }
        return (null);
    }

    angles add_as0(int lm, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (!valid(lm)) return null;

        l_line n1, n2;
        angles as;

        if (line_compare(l1, l4) == 0) {
            n1 = l3;
            l3 = l1;
            l1 = n1;
            n1 = l2;
            l2 = l4;
            l4 = n1;
        }
        if (line_compare(l2, l3) == 0) {
            if (line_compare(l1, l4) > 0) {
                n1 = l4;
                l4 = l1;
                l1 = n1;
            }
        } else if (line_compare(l1, l3) == 0) {
            if (line_compare(l2, l4) > 0) {
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
        } else if (line_compare(l2, l4) == 0) {
            n1 = l1;
            l1 = l2;
            l2 = n1;
            n1 = l3;
            l3 = l4;
            l4 = n1;
            if (line_compare(l2, l4) > 0) {
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
        } else {
            if (line_compare(l1, l2) < 0) n1 = l1;
            else n1 = l2;
            if (line_compare(l3, l4) < 0) n2 = l3;
            else n2 = l4;
            if (line_compare(n1, n2) > 0) {
                n1 = l3;
                l3 = l1;
                l1 = n1;
                n1 = l2;
                l2 = l4;
                l4 = n1;
            }
            if (line_compare(l1, l2) > 0) {
                n1 = l1;
                l1 = l2;
                l2 = n1;
                n1 = l3;
                l3 = l4;
                l4 = n1;
            }
        }
        if (l1 == l2 && l3 == l4 || l1 == l3 && l2 == l4) return null;
        //if (!valid(R_AG_ALL) && !valid(R_AG_NO_INTER) && (inter_lls(l1, l2) == 0 || inter_lls(l3, l4) == 0)) return null;

//        if (!search_only_exists_ln(l1, l2, l3, l4)) return null;
//        if (!check_eqangle(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1])) {
//            int k = 0;
//        }
//        if (check_perp(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1])) {
//            int k = 0;
//        }

        {
            as = new angles();
            as.type = 2;
            as.lemma = lm;
            as.co = co_db.nx;
            as.sa = 0;
            as.nx = null;
            as.l1 = l1;
            as.l2 = l2;
            as.l3 = l3;
            as.l4 = l4;
            last_as.nx = as;
            last_as = as;
            new_pr(CO_ACONG);
            last_nd.u.as = as;


            if (l1 == l2 || l3 == l4 || l1 == l3 && l2 == l4 || l1 == l4 && l2 == l3) {
                int k = 0;
            }
            if (l1.type == 0 || l2.type == 0 || l3.type == 0 || l4.type == 0)
                as.type = 0;
        }
        return (as);
    }

    final void add_co_as(l_line l1, l_line l2, l_line l3, l_line l4) {
        int p1, p2, p3, p4, p5, p6, p7, p8;
        int a = inter_ll(l1, l2);
        int b = inter_ll(l3, l4);
        //    a = b = 0;
        if (a != 0) {
            p2 = a;
            p3 = a;
            if (l1.pt[0] == a)
                p1 = l1.pt[1];
            else
                p1 = l1.pt[0];

            if (l2.pt[0] == a)
                p4 = l2.pt[1];
            else
                p4 = l2.pt[0];
        } else {
            p1 = l1.pt[0];
            p2 = l1.pt[1];
            p3 = l2.pt[0];
            p4 = l2.pt[1];
        }
        if (b != 0) {
            p6 = b;
            p7 = b;
            if (l3.pt[0] == b)
                p5 = l3.pt[1];
            else
                p5 = l3.pt[0];

            if (l4.pt[0] == b)
                p8 = l4.pt[1];
            else
                p8 = l4.pt[0];
        } else {
            p5 = l3.pt[0];
            p6 = l3.pt[1];
            p7 = l4.pt[0];
            p8 = l4.pt[1];
        }
        add_codx(CO_ACONG, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    final void ck_as(angles as, l_line l1, l_line l2, l_line l3, l_line l4) {
        co_xy.nx = null;
        if (ln_para(l1, l2) && l3 != l4) {
            as.type = 0;
            p_line pn = add_px(165, l3, l4);
            if (pn != null) {
                pn.co = add_acoxy(new angles(l1, l2, l3, l4));
            }
        } else if (ln_perp(l1, l2)) {
            as.type = 0;
            t_line tn = add_tx(145, l3, l4);
            if (tn != null) {
                tn.co = add_acoxy(new angles(l1, l2, l3, l4));
            }
        } else if (ln_para(l1, l3) && l2 != l4) {
            p_line pn = add_px(166, l2, l4);
            if (pn != null) {
                pn.co = add_acoxy(new angles(l1, l2, l3, l4));
            }
        } else if (false && ln_perp(l1, l3)) {
            t_line tn = add_tx(146, l2, l4);
            if (tn != null) {
                tn.co = add_acoxy(new angles(l1, l2, l3, l4));
            }
        }
        co_xy.nx = null;
    }

    final cond add_acoxy(angles as) {
        co_xy.nx = null;
        cond c = add_coxy(CO_ACONG);
        c.u.as = as;
        return c;
    }

    final void adj_as(int t, angles as) {
        if (as == null) return;
        if (as.type == 0) return;


        if (t == 1)
            for (angles r1 = all_as.nx; (r1 != null && ch_dep(r1.dep)); r1 = r1.nx) {
                if (r1 == as) continue;
                if (r1.type == 0) continue;
                adj_as1(r1.l1, r1.l2, r1.l3, r1.l4, as, r1);
                adj_as1(r1.l3, r1.l4, r1.l1, r1.l2, as, r1);
            }
        else if (t == 0) {
            if (R_AG_ALL && isPFull()) {
                for (angles r1 = all_as.nx; (r1 != null && r1 != as && ch_dep(r1.dep)); r1 = r1.nx) {
                    if (r1.type == 0) continue;
                    if (r1 == as) continue;
                    adj_as1(r1.l1, r1.l3, r1.l2, r1.l4, as, r1);
                    adj_as1(r1.l2, r1.l4, r1.l1, r1.l3, as, r1);
                    adj_as2(r1.l1, r1.l4, r1.l2, r1.l3, as, r1);
                    adj_as2(r1.l2, r1.l3, r1.l1, r1.l4, as, r1);
                }
            } else if (!isPFull()) {
                for (angles r1 = all_as.nx; (r1 != null && r1 != as && ch_dep(r1.dep)); r1 = r1.nx) {
                    if (r1.type == 0) continue;
                    if (r1 == as) continue;
                    adj_as_plus(r1.l1, r1.l2, r1.l3, r1.l4, as);
                    adj_as_plus(r1.l2, r1.l1, r1.l4, r1.l3, as);
                }
            }
        }
    }

    public void adj_as_tn(int m1, int m2, angles as) {

        if (m1 == 0 || m2 == 0) return;
        t_line tn1, tn2, tn3, tn4;
        tn1 = fd_tn(as.l1);
        tn2 = fd_tn(as.l2);
        tn3 = fd_tn(as.l3);
        tn4 = fd_tn(as.l4);
        search_as_tn1(tn1, tn3, as);
        search_as_tn1(tn1, tn4, as);
        search_as_tn1(tn2, tn3, as);
        search_as_tn1(tn2, tn4, as);

    }

    public void search_as_tn1(t_line tn1, t_line tn2, angles as) {
        if (tn1 == null || tn2 == null) return;

        adj_as_plus(tn1.l1, tn1.l2, tn2.l1, tn2.l2, as);
        adj_as_plus(tn1.l1, tn1.l2, tn2.l2, tn2.l1, as);
        adj_as_plus(tn2.l1, tn2.l2, tn1.l2, tn1.l1, as);
        adj_as_plus(tn2.l1, tn2.l2, tn1.l2, tn1.l1, as);
    }

    final void adj_bisector(l_line l1, l_line l2, l_line l3, l_line l4, angles r1, angles r2) {
        l_line ln1, ln2, ln3, ln4, ln5, ln6;
        if (l2 == l3) {
            ln1 = l1;
            ln2 = l2;
            ln3 = l4;
        } else if (l1 == l4) {
            ln1 = l2;
            ln2 = l1;
            ln3 = l3;
        } else
            return;
        if (r1.l2 == r1.l3) {
            ln1 = r1.l1;
            ln2 = r1.l2;
            ln3 = r1.l4;
        } else if (l1 == l4) {
            ln1 = r1.l2;
            ln2 = r1.l1;
            ln3 = r1.l3;
        } else
            return;

    }

    final void adj_as1(l_line l1, l_line l2, l_line l3, l_line l4, angles r1, angles r2) {
        cond co;
        l_line t1, t2;

        if (l1 == r1.l1 && l2 == r1.l2) {
            t1 = r1.l3;
            t2 = r1.l4;
        } else if (l1 == r1.l2 && l2 == r1.l1) {
            t1 = r1.l4;
            t2 = r1.l3;
        } else if (l1 == r1.l3 && l2 == r1.l4) {
            t1 = r1.l1;
            t2 = r1.l2;
        } else if (l1 == r1.l4 && l2 == r1.l3) {
            t1 = r1.l2;
            t2 = r1.l1;
        }
//maked for orthocenter 97,7
        else if (isPFull()) {
            if (l1 == r1.l1 && l2 == r1.l3) {
                t1 = r1.l2;
                t2 = r1.l4;
            } else if (l1 == r1.l3 && l2 == r1.l1) {
                t1 = r1.l4;
                t2 = r1.l2;
            } else if (l1 == r1.l2 && l2 == r1.l4) {
                t1 = r1.l1;
                t2 = r1.l3;
            } else if (l1 == r1.l4 && l2 == r1.l2) {
                t1 = r1.l3;
                t2 = r1.l1;
            } else
                return;
        } else
            return;

        if (t1 == t2 || l3 == l4 || t1 == l3 && t2 == l4 || t1 == l4 && t2 == l3) return;
//        if (ln_acong(t1, t2, l3, l4)) return;

        angles as = add_ea_ln(181, t1, t2, l3, l4);
        if (as != null) {
            co_xy.nx = null;
            co = add_coxy(CO_ACONG);
            co.u.as = r2;
            co = add_coxy(CO_ACONG);
            co.u.as = r1;
            as.co = co_xy.nx;
        }
    }

    final void adj_as2(l_line l1, l_line l2, l_line l3, l_line l4, angles r1, angles r2) {
        cond co;
        l_line t1, t2;

        if ((l1 == r1.l1 && l2 == r1.l4) || (l1 == r1.l4 && l2 == r1.l1)) {
            t1 = r1.l2;
            t2 = r1.l3;
        } else if ((l1 == r1.l2 && l2 == r1.l3) || (l1 == r1.l3 && l2 == r1.l2)) {
            t1 = r1.l1;
            t2 = r1.l4;
        } else
            return;
        if (t1 == l3 || l4 == t2 || t1 == l4 && l3 == t2 || t1 == t2 && l3 == l4) return;
        if (ln_acong(t1, l3, l4, t2)) return;

        angles as = add_ea_ln(182, t1, l3, l4, t2);
        if (as != null) {
            co_xy.nx = null;
            co = add_coxy(CO_ACONG);
            co.u.as = r2;
            co = add_coxy(CO_ACONG);
            co.u.as = r1;
            last_as.co = co_xy.nx;
        }
    }

    final void adj_as_plus(l_line l1, l_line l2, l_line l3, l_line l4, angles r1) {


        l_line t1, t2, ln1, ln2, ln3, ln4;
        ln1 = ln2 = ln3 = ln4 = null;

        t1 = get_82l0(l1, l2, r1.l1, r1.l2);
        t2 = get_82l0(l3, l4, r1.l3, r1.l4);

        if (t1 != null && t2 != null) {
            if (l2 == r1.l1) {
                ln1 = l1;
                ln2 = r1.l2;
            } else if (l1 == r1.l2) {
                ln1 = r1.l1;
                ln2 = l2;
            }

            if (l4 == r1.l3) {
                ln3 = l3;
                ln4 = r1.l4;
            } else if (l3 == r1.l4) {
                ln3 = r1.l3;
                ln4 = l4;
            }
        } else {
            t1 = get_82l0(l1, l2, r1.l3, r1.l4);
            t2 = get_82l0(l3, l4, r1.l1, r1.l2);

            if (t1 != null && t2 != null) {
                if (l2 == r1.l3) {
                    ln1 = l1;
                    ln2 = r1.l4;
                } else if (l1 == r1.l4) {
                    ln1 = r1.l3;
                    ln2 = l2;
                }

                if (l4 == r1.l1) {
                    ln3 = l3;
                    ln4 = r1.l2;
                } else if (l3 == r1.l2) {
                    ln3 = r1.l1;
                    ln4 = l4;
                }
            }
        }
        if (t1 == null || t2 == null) return;

        if (ln1 == ln3 && ln2 == ln4) return;


        int m1 = inter_lls(ln1, t1);
        int m2 = inter_lls(t1, ln2);
        int m3 = inter_lls(ln1, ln2);

        int p1 = inter_lls(ln3, t2);
        int p2 = inter_lls(t2, ln4);
        int p3 = inter_lls(ln3, ln4);
        if (p1 == 0 || p2 == 0 || p3 == 0 || m1 == 0 || m2 == 0 || m3 == 0)
            return;

        if (m1 != m2 && m1 != m3 && m3 != m2) {
            ln1 = fadd_ln_t(m1, m3);
            ln2 = fadd_ln_t(m2, m3);
        }
        if (p1 != p2 && p1 != p3 && p2 != p3) {
            ln3 = fadd_ln_t(p1, p3);
            ln4 = fadd_ln_t(p2, p3);
        }
        if (ln1 == ln2 || ln3 == ln4) return;

        t_line tn = null;
        angles as = null;
        if (xcoll_ln(ln1, ln2) || xcoll_ln(ln3, ln4)) return;

        if (ln1 == ln4 && ln2 == ln3) {
            if (!check_para(ln1, ln2))
                tn = add_tline(142, ln1, ln2);
        } else
            as = add_ea_ln(183, ln1, ln2, ln3, ln4);

        if (tn != null || as != null) {
            co_xy.nx = null;
            cond co = add_coxy(CO_ACONG);
            co.u.as = new angles(l1, l2, l3, l4);
            co = add_coxy(CO_ACONG);
            co.u.as = r1;
            if (tn != null)
                tn.co = co_xy.nx;
            if (as != null)
                as.co = co_xy.nx;
        }

    }

    public l_line get_82l0(l_line l1, l_line l2, l_line l3, l_line l4) {
        if (l1 == l4) return l1;
        if (l2 == l3) return l2;
        return null;
    }

    public l_line get_82l1(l_line l1, l_line l2, l_line l) {
        if (l1 == l) return l2;
        if (l2 == l) return l1;
        return null;
    }

    final void add_ea_pt(int lm, int a, int b, int c, int p, int q, int r) {
        add_ea_ln(lm, fadd_ln(a, b), fadd_ln(b, c), fadd_ln(p, q), fadd_ln(q, r));
    }

    final angles add_ea_ln(int lm, l_line l1, l_line l2, l_line l3, l_line l4) {

        if (d_base == 1) return null;
        if (l1 == null || l2 == null || l3 == null || l4 == null) return null;
        if (ln_acong(l1, l2, l3, l4)) return null;
        angles as = add_as(lm, l1, l2, l3, l4);
        if (l1 == l4 && l2 == l3) return null;

        return as;
    }


    boolean on_st(int p1, int p2, int p3, int p, int q, int r, sim_tri st) {                         //??????????????????
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (i != j)
                    for (int k = 0; k < 3; k++)
                        if (k != i && k != j) {
                            if (p1 == st.p1[i] && p2 == st.p1[j] && p3 == st.p1[k] && p == st.p2[i] && q == st.p2[j] && r == st.p2[k])
                                return true;
                            if (p1 == st.p2[i] && p2 == st.p2[j] && p3 == st.p2[k] && p == st.p1[i] && q == st.p1[j] && r == st.p1[k])
                                return true;
                        }
        return (false);
    }


    final void add_stri(int lm, int dr, int a, int b, int c, int p, int q, int r) {
        if (!valid(lm)) return;

        if (a == p && b == q && c == r) return;

        if (tri_type == 0 && !valid(R_STRI)) return;
        if (tri_type == 1 && !valid(R_CTRI)) return;

        if (same_tri(a, b, c, p, q, r)) return;

        if (search_only_exists_ln(a, b, b, c, a, c)) return;
        if (search_only_exists_ln(p, q, p, r, q, r)) return;

        if (tri_type == 0 && (xsim_tri(a, b, c, p, q, r) || xcon_tri(a, b, c, p, q, r))) return;
        if (tri_type == 1 && xcon_tri(a, b, c, p, q, r)) return;


        if (tri_type == 0 && !check_simtri(a, b, c, p, q, r)) {
            this.add_checkError();
            return;
        }

        if (tri_type == 1 && !check_congtri(a, b, c, p, q, r)) {
            this.add_checkError();
            return;
        }

        sim_tri st = new sim_tri();
        st.type = 2;
        st.lemma = lm;
        st.co = co_db.nx;
        st.nx = null;
        st.dr = dr;
        st.p1[0] = a;
        st.p1[1] = b;
        st.p1[2] = c;
        st.p2[0] = p;
        st.p2[1] = q;
        st.p2[2] = r;
        st.st = tri_type;

        if (tri_type == 0) {
            last_st.nx = st;
            last_st = st;
        } else {
            last_ct.nx = st;
            last_ct = st;
        }
        if (tri_type == 0)
            new_pr(CO_STRI);
        else
            new_pr(CO_CTRI);
        last_nd.u.st = st;

        return;
    }


    final boolean on_cg(int p1, int p2, cong_seg cg) {
        int i;
        if (p2 < p1) {
            i = p1;
            p1 = p2;
            p2 = i;
        }
        if (p1 == cg.p1 && p2 == cg.p2 || p1 == cg.p3 && p2 == cg.p4) return (true);
        return (false);
    }

    final boolean on_cg(int p1, int p2, int p3, int p4, cong_seg cg) {
        int i;
        if (p2 < p1) {
            i = p1;
            p1 = p2;
            p2 = i;
        }
        if (p4 < p3) {
            i = p3;
            p3 = p4;
            p4 = i;
        }
        if (p1 == cg.p1 && p2 == cg.p2 && p3 == cg.p3 && p4 == cg.p4) return (true);
        if (p1 == cg.p3 && p2 == cg.p4 && p3 == cg.p1 && p4 == cg.p2) return (true);
        return (false);
    }


    final cong_seg fd_cg2(int p1, int p2, cong_seg cg) {
        cong_seg cg1 = all_cg.nx;
        boolean f = false;
        while (cg1 != null) {
            if (cg1.type != 0) {
                if (cg1 == cg)
                    f = true;
                else if ((f || cg == null) && on_cg(p1, p2, cg1)) return (cg1);
            }
            cg1 = cg1.nx;
        }
        return (null);
    }


    final void adj_cg(int c, cong_seg cg) {
        cong_seg cg1 = (c == 1) ? all_cg.nx : all_rg.nx;

        while (cg1 != null && ch_dep(cg1.dep)) {
            if (cg.p1 == cg1.p1 && cg.p2 == cg1.p2) {
                add_codb(CO_CONG, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2, 0, 0);
                add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                add_cong(0, 0, cg.p3, cg.p4, cg1.p3, cg1.p4, cg.t2 * cg1.t1, cg1.t2 * cg.t1);
                pop_codb();
                pop_codb();
            } else if (cg.p1 == cg1.p3 && cg.p2 == cg1.p4) {
                add_codb(CO_CONG, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2, 0, 0);
                add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                add_cong(0, 0, cg.p3, cg.p4, cg1.p1, cg1.p2, cg.t2 * cg1.t2, cg.t1 * cg1.t1);
                pop_codb();
                pop_codb();
            } else if (cg.p3 == cg1.p1 && cg.p4 == cg1.p2) {
                add_codb(CO_CONG, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2, 0, 0);
                add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                add_cong(0, 0, cg.p1, cg.p2, cg1.p3, cg1.p4, cg.t1 * cg1.t1, cg.t2 * cg1.t2);
                pop_codb();
                pop_codb();

            } else if (cg.p3 == cg1.p3 && cg.p4 == cg1.p4) {
                add_codb(CO_CONG, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2, 0, 0);
                add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                add_cong(0, 0, cg.p1, cg.p2, cg1.p1, cg1.p2, cg.t1 * cg1.t2, cg.t2 * cg1.t1);
                pop_codb();
                pop_codb();
            }

            if (cg.t1 * cg1.t2 == cg.t2 * cg1.t1)
                search_cg3(cg.p1, cg.p2, cg.p3, cg.p4, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg.t1, cg.t2, cg1.t1, cg1.t2);
            if (cg.t1 * cg1.t1 == cg.t2 * cg1.t2)
                search_cg3(cg.p1, cg.p2, cg.p3, cg.p4, cg1.p3, cg1.p4, cg1.p1, cg1.p2, cg.t1, cg.t2, cg1.t2, cg1.t1);
            cg1 = cg1.nx;
        }
    }


    final boolean search_cg3(int a, int b, int c, int d, int a1, int b1, int c1, int d1, int k1, int k2, int k3, int k4) {

        int t1, t2, t3, t4, m1, m2;
        if (a == a1) {
            t1 = b;
            t2 = b1;
            m1 = a;
        } else if (a == b1) {
            t1 = b;
            t2 = a1;
            m1 = a;
        } else if (b == a1) {
            t1 = a;
            t2 = b1;
            m1 = b;
        } else if (b == b1) {
            t1 = a;
            t2 = a1;
            m1 = b;
        } else
            return false;

        if (c == c1) {
            t3 = d;
            t4 = d1;
            m2 = c;
        } else if (c == d1) {
            t3 = d;
            t4 = c1;
            m2 = c;
        } else if (d == c1) {
            t3 = c;
            t4 = d1;
            m2 = d;
        } else if (d == d1) {
            t3 = c;
            t4 = c1;
            m2 = d;
        } else
            return false;

        if (check_eqdistance(t1, t2, t3, t4) && xcoll(t1, t2, m1) && xcoll(t3, t4, m2)
                && !xcong(t1, t2, t3, t4)) {
            add_codb(CO_CONG, a, b, c, d, k1, k2, 0, 0);
            add_codb(CO_CONG, a1, b1, c1, d1, k3, k4, 0, 0);
            add_cong(0, 0, t1, t2, t3, t4);
            pop_codb();
            pop_codb();
        }
        return true;
    }

    final int get_coll43(int a, int b, int c, int d) {
        if (a == b || a == c || a == d) return a;
        if (b == c || b == d) return b;
        if (c == d) return c;
        return 0;
    }

    final void add_cong(int lm, int l_no, int a, int b, int p, int q, int t1, int t2) {
        cong_seg cg;
        if (!valid(lm)) return;

        if (a == b || p == q) return;

        if (t1 == t2 && xcong(a, b, p, q))
            return;

        if (xcong1(a, b, p, q)) return;

        if (search_only_exists_ln(a, b, p, q)) return;

        if (!check_eqdistance(a, b, p, q, t1, t2)) {
            add_checkError();
            return;
        }

        cg = new cong_seg();
        cg.co = co_db.nx;
        if (l_no != 0) {
            cond co = cg.co;
            if (co != null) co.nx = null;
        }
        cg.type = 2;
        cg.lemma = lm;
        cg.nx = null;
        if (a < b) {
            cg.p1 = a;
            cg.p2 = b;
        } else {
            cg.p1 = b;
            cg.p2 = a;
        }
        if (p < q) {
            cg.p3 = p;
            cg.p4 = q;
        } else {
            cg.p3 = q;
            cg.p4 = p;
        }
        if (cg.p1 > cg.p3) {
            int t = cg.p1;
            cg.p1 = cg.p3;
            cg.p3 = t;
            t = cg.p2;
            cg.p2 = cg.p4;
            cg.p4 = t;
            t = t1;
            t1 = t2;
            t2 = t;
        }

        int g = (int) gcd(t1, t2);
        cg.t1 = t1 / g;
        cg.t2 = t2 / g;
        if (t1 == 0 || t2 == 0) {
            int k = 0;
        }
        if (t1 == t2 && xcong(a, b, p, q))
            return;

        if (t1 == t2) {
            last_cg.nx = cg;
            last_cg = cg;
            add_cgs(cg.p1, cg.p2, cg.p3, cg.p4);
        } else {
            last_rg.nx = cg;
            last_rg = cg;
        }

        new_pr(CO_CONG);
        last_nd.u.cg = cg;
    }

    final void add_cong(int lm, int l_no, int a, int b, int p, int q) {
        cong_seg cg;
        if (a == b || p == q) return;
        if (!valid(lm)) return;

        if (search_only_exists_ln(a, b, p, q)) return;
        if (!check_eqdistance(a, b, p, q)) {
            this.add_checkError();
            return;
        }

        if (xcong(a, b, p, q)) return;
        {
            cg = new cong_seg();
            cg.co = co_db.nx;
            if (l_no != 0) {
                cond co = cg.co;
                if (co != null) co.nx = null;
            }
            cg.type = 2;
            cg.lemma = lm;
            cg.nx = null;
            if (a < b) {
                cg.p1 = a;
                cg.p2 = b;
            } else {
                cg.p1 = b;
                cg.p2 = a;
            }
            if (p < q) {
                cg.p3 = p;
                cg.p4 = q;
            } else {
                cg.p3 = q;
                cg.p4 = p;
            }
            if (cg.p1 > cg.p3) {
                int t1 = cg.p1;
                cg.p1 = cg.p3;
                cg.p3 = t1;
                t1 = cg.p2;
                cg.p2 = cg.p4;
                cg.p4 = t1;
            }
            if (cg.p3 >= 5) {
                int k = 0;
            }
            cg.t1 = cg.t2 = 1;
            last_cg.nx = cg;
            last_cg = cg;
        }
        add_cgs(cg.p1, cg.p2, cg.p3, cg.p4);
        new_pr(CO_CONG);
        last_nd.u.cg = cg;
    }

    final void add_cong1(int lm, int p1, int p2, int p3, int p4, int a, int b, int p, int q) {
        cong_seg cg;
        cond co;

        if (!valid(lm)) return;
        if (search_only_exists_ln(a, b, p, q)) return;
        if (!check_eqdistance(a, b, p, q)) {
            this.add_checkError();
            return;
        }
        if (xcong(a, b, p, q)) return;
        {
            cg = new cong_seg();
            cg.type = 2;
            cg.lemma = lm;
            cg.nx = null;
            if (a < b) {
                cg.p1 = a;
                cg.p2 = b;
            } else {
                cg.p1 = b;
                cg.p2 = a;
            }
            if (p < q) {
                cg.p3 = p;
                cg.p4 = q;
            } else {
                cg.p3 = q;
                cg.p4 = p;
            }
            if (cg.p1 > cg.p3) {
                int t1 = cg.p1;
                cg.p1 = cg.p3;
                cg.p3 = t1;
                t1 = cg.p2;
                cg.p2 = cg.p4;
                cg.p4 = t1;
            }
            cg.t1 = cg.t2 = 1;
            last_cg.nx = cg;
            last_cg = cg;

            add_cgs(cg.p1, cg.p2, cg.p3, cg.p4);
        }
        if (p2 > p1) {
            lm = p1;
            p1 = p2;
            p2 = lm;
            //ex_ch(lm, p1, p2)
        }
        if (p4 > p3) {
            lm = p3;
            p3 = p4;
            p4 = lm;
            //ex_ch(lm, p3, p4)
        }
        if (p1 != p3 || p2 != p4) {
            co = add_coxy(CO_CONG);
            co.p[0] = p1;
            co.p[1] = p2;
            co.p[2] = p3;
            co.p[3] = p4;
        }
        cg.co = co_xy.nx;
        //adj_cg(cg);
        new_pr(CO_CONG);
        last_nd.u.cg = cg;
    }

    int ind_ra(int a, int b, ratio_seg ra, int m) {
        int c = 0;
        if (xcong(a, b, ra.r[1], ra.r[2])) {
            c = 1;
            if (m == 1 && xcong(a, b, ra.r[7], ra.r[8])) c = 4;
        } else if (xcong(a, b, ra.r[3], ra.r[4])) {
            c = 2;
            if (m == 1 && xcong(a, b, ra.r[5], ra.r[6])) c = 3;
        } else if (xcong(a, b, ra.r[5], ra.r[6]))
            c = 3;
        else if (xcong(a, b, ra.r[7], ra.r[8])) c = 4;
        return (c);
    }

    ratio_seg add_ra(int lm, int o, int a, int b, int c, int d, int p, int q, int r, int s) {
        ratio_seg ra;
        cond co;
        if (!valid(lm) || !valid(R_RATIO)) return null;


        if (!check_ratio(a, b, c, d, p, q, r, s)) {
            this.add_checkError();
            return null;
        }
        {
            ra = new ratio_seg();
            ra.type = 2;
            ra.lemma = lm;
            ra.r[0] = o;
            ra.co = co_db.nx;
            ra.r[1] = a;
            ra.r[2] = b;
            ra.r[3] = c;
            ra.r[4] = d;
            ra.r[5] = p;
            ra.r[6] = q;
            ra.r[7] = r;
            ra.r[8] = s;
            ra.nx = null;
            last_ra.nx = ra;
            last_ra = ra;
            new_pr(CO_PROD);
            last_nd.u.ra = ra;
        }
        co_xy.nx = null;
        if (xcong(a, b, p, q)) {
            co = add_coxy(CO_PROD);
            co.u.ra = ra;
            ra.type = 0;
            add_cong1(0, a, b, p, q, c, d, r, s);
        } else if (xcong(c, d, r, s)) {
            co = add_coxy(CO_PROD);
            co.u.ra = ra;
            ra.type = 0;
            add_cong1(0, c, d, r, s, a, b, p, q);
        } else if (xcong(a, b, c, d)) {
            co = add_coxy(CO_PROD);
            co.u.ra = ra;
            ra.type = 0;
            add_cong1(0, a, b, c, d, p, q, r, s);
        } else if (xcong(p, q, r, s)) {
            co = add_coxy(CO_PROD);
            co.u.ra = ra;
            ra.type = 0;
            add_cong1(0, p, q, r, s, a, b, c, d);
        } else if (xcong(a, b, r, s) && xcong(c, d, p, q)) {
            co = add_coxy(CO_PROD);
            co.u.ra = ra;
            ra.type = 0;
            add_cong1(0, a, b, r, s, c, d, p, q);
        }
        return (ra);
    }

    final void adj_ra(ratio_seg ra) {
        ratio_seg r1;
        int i1, i2, i3, i4;
        while (ra != null) {
            if (ra.type == 0) {
                ra = ra.nx;
                continue;
            }
            r1 = all_ra.nx;

            while (r1 != null && r1 != ra) {
                if (r1.type == 0) {
                    r1 = r1.nx;
                    continue;
                }
                i1 = ind_ra(r1.r[1], r1.r[2], ra, 0);
                i2 = ind_ra(r1.r[3], r1.r[4], ra, 0);
                i3 = ind_ra(r1.r[5], r1.r[6], ra, 1);
                i4 = ind_ra(r1.r[7], r1.r[8], ra, 1);

                if (i1 != 0 && i2 != 0 && i1 != i2 && i1 + i2 != 5) {
                    adj_ra1(r1.r[5], r1.r[6], r1.r[7], r1.r[8], i1, i2, ra, r1);
                } else if (i3 != 0 && i4 != 0 && i3 != i4 && i3 + i4 != 5) {
                    adj_ra1(r1.r[1], r1.r[2], r1.r[3], r1.r[4], i3, i4, ra, r1);
                }

                if (i1 != 0 && i3 != 0 && i1 != i3 && i1 + i3 != 5) {
                    adj_ra1(r1.r[3], r1.r[4], r1.r[7], r1.r[8], i1, i3, ra, r1);
                } else if (i2 != 0 && i4 != 0 && i2 != i4 && i2 + i4 != 5) {
                    adj_ra1(r1.r[1], r1.r[2], r1.r[5], r1.r[6], i2, i4, ra, r1);
                }

                if (i1 != 0 && i4 != 0 && i1 + i4 == 5) {
                    adj_ra2(r1.r[3], r1.r[4], r1.r[5], r1.r[6], i1, ra, r1);
                } else if (i2 != 0 && i3 != 0 && i2 + i3 == 5) {
                    adj_ra2(r1.r[1], r1.r[2], r1.r[7], r1.r[8], i2, ra, r1);
                }
                m1:
                r1 = r1.nx;
            }
            m2:
            ra = ra.nx;
        }
        test_ra = last_ra;
    }

    final void adj_ra1(int a, int b, int c, int d, int i1, int i2, ratio_seg r1, ratio_seg r2) {
        int p, q, r, s;
        cond co;
        ratio_seg ra;
        p = q = r = s = 0;

        switch (5 - i2) {
            case 1:
                p = r1.r[1];
                q = r1.r[2];
                break;
            case 2:
                p = r1.r[3];
                q = r1.r[4];
                break;
            case 3:
                p = r1.r[5];
                q = r1.r[6];
                break;
            case 4:
                p = r1.r[7];
                q = r1.r[8];
                break;
            default:
                Cm.print("adj_ra1");
                break; /// exit
        }
        switch (5 - i1) {
            case 1:
                r = r1.r[1];
                s = r1.r[2];
                break;
            case 2:
                r = r1.r[3];
                s = r1.r[4];
                break;
            case 3:
                r = r1.r[5];
                s = r1.r[6];
                break;
            case 4:
                r = r1.r[7];
                s = r1.r[8];
                break;
            default:
                Cm.print("adj_ra11");
                break;
        }
        if (xeq_ratio(a, b, c, d, p, q, s, r)) return;
        ra = add_ra(0, 0, a, b, c, d, p, q, r, s);
        if (ra == null) {
        } else if (r1.co == null && r2.co == null)
            last_ra.co = null;
        else {
            co_xy.nx = null;
            co = add_coxy(CO_PROD);
            co.u.ra = r2;
            co = add_coxy(CO_PROD);
            co.u.ra = r1;
            last_ra.co = co_xy.nx;
        }
    }

    final void adj_ra2(int a, int b, int c, int d, int i1, ratio_seg r1, ratio_seg r2) {
        int p, q, r, s;
        cond co;
        ratio_seg ra;

        if (i1 == 1 || i1 == 4) {
            p = r1.r[3];
            q = r1.r[4];
            r = r1.r[5];
            s = r1.r[6];
        } else {
            p = r1.r[1];
            q = r1.r[2];
            r = r1.r[7];
            s = r1.r[8];
        }

        if (xeq_ratio(a, b, p, q, r, s, c, d)) return;
        ra = add_ra(0, 0, a, b, p, q, r, s, c, d);
        if (ra == null) {
        } else if (r1.co == null && r2.co == null)
            last_ra.co = null;
        else {
            co_xy.nx = null;
            co = add_coxy(CO_PROD);
            co.u.ra = r2;
            co = add_coxy(CO_PROD);
            co.u.ra = r1;
            last_ra.co = co_xy.nx;
        }
    }

    final void add_ratio(int lm, int o, int a, int b, int c, int d, int p, int q, int r, int s) {
        if (xeq_ratio(a, b, c, d, p, q, r, s)) return;
        add_ra(lm, o, a, b, c, d, p, q, r, s);
    }

    final void add_ratioo(int lm, int o, int a, int b, int c, int d) {
        if (!xeq_ratio(o, a, o, c, o, b, o, d))
            add_ra(lm, 1, o, a, o, c, o, b, o, d);

        if (!xeq_ratio(o, a, o, c, a, b, c, d))
            add_ra(lm, 1, o, a, o, c, a, b, c, d);

        if (!xeq_ratio(o, b, o, d, a, b, c, d))
            add_ra(lm, 1, o, b, o, d, a, b, c, d);

        if (!xeq_ratio(o, a, a, c, o, b, b, d))
            add_ra(lm, 1, o, a, a, c, o, b, b, d);

        if (!xeq_ratio(o, c, c, a, o, d, d, b))
            add_ra(lm, 1, o, c, c, a, o, d, d, b);
    }

    boolean meq_pt(int p1, int p2) {
        return p1 == p2;
    }

    final void free_dbase() {
        init_dbase();
    }

////////////////////////////////////////////////////////////

    public boolean sbase() {
//        for (int i = 1; i <= cns_no; i++) {
//            if (!isConclution(i))
//                do_i_ln(i);
//        }
        for (int i = 1; i <= cns_no; i++) {
            if (!isConclution(i))
                do_i_cons(i);
        }

        return true;
    }

    final public void setExample(gterm tm) {
        gt = tm;
        cons_no = tm.getCons_no();
        cond tco = tm.getConc();
        setConc(tco);
        tm.setAllpts(allpts);
        cns_no = tm.setAllcons(allcns);
        pts_no = tm.getPointsNum();
    }


    final public void setConc(cond tco) {
        if (tco == null) return;

        conc.pred = tco.pred;
        for (int i = 0; i < cond.MAX_GEO; i++)
            conc.p[i] = tco.p[i];
        conc.no = tco.no;
        if (conc.pred == CO_COLL) {
            for (int i = 0; i < 3; i++)
                for (int j = i + 1; j < 3; j++) {
                    if (conc.p[i] < conc.p[j]) {
                        int t = conc.p[i];
                        conc.p[i] = conc.p[j];
                        conc.p[j] = t;
                    }
                }
        } else if (conc.pred == CO_CYCLIC) {
            if (conc.p[0] != 0 && conc.p[4] == 0) {
                for (int i = 4; i >= 1; i--)
                    conc.p[i] = conc.p[i - 1];
                conc.p[0] = 0;
            }
        }
    }


    public void add_nln(int[] p, int n) {
        for (int i = 0; i < n; i++)
            fadd_ln(p[i * 2], p[i * 2 + 1]);
    }


    public boolean do_i_cons(int ptn) {
        do_pd(ptn, allcns[ptn].type, allcns[ptn].ps);
        return true;
    }

    public boolean do_i_ln(int ptn) {
        do_pdln(ptn, allcns[ptn].type, allcns[ptn].ps);
        return true;
    }


    public boolean do_pd(int ptn, int t, int[] p) {
        if (t == 0) return true;
        switch (t) {
            case C_POINT:
                break;
            case C_CONSTANT:
                break;
            case C_MIDPOINT:
                add_line1(0, p[0], p[1], p[2]);
                add_mid(0, p[0], p[1], p[2]);
                break;
            case C_FOOT:    // foot
                add_line1(0, p[0], p[2], p[3]);
                add_tline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_O_C: {
                a_cir cr = fd_cr_op(p[1], p[2]);

                if (cr == null)
                    add_cir2(0, p[1], p[0], p[2]);
                else {
                    for (int i = 0; i <= cr.no; i++)
                        add_cong(0, 0, cr.o, cr.pt[i], cr.o, p[0]);
                    add_pt2c(p[0], cr);
                }
                add_cong(0, 0, p[0], p[1], p[1], p[2]);
            }
            break;
            case C_O_L:
                add_line1(0, p[0], p[1], p[2]);
                break;
            case C_O_T:
                add_tline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_O_P:
                add_pline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_EQANGLE:
            case C_O_A:
                add_ea_pt(0, p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case C_O_R:
                add_cong(0, 0, p[0], p[1], p[2], p[3]);
                break;
            case C_O_B:
                add_cir2(0, p[0], p[1], p[2]);
                break;
            case C_NSQUARE: {
                add_tline(0, p[0], p[1], p[1], p[2]);
                add_cir2(0, p[1], p[0], p[2]);
                add_cong(0, 0, p[0], p[1], p[1], p[2]);
            }
            break;
            case C_PSQUARE: {
                add_tline(0, p[0], p[1], p[1], p[2]);
                add_cir2(0, p[1], p[0], p[2]);
                add_cong(0, 0, p[0], p[1], p[1], p[2]);
            }
            break;
            case C_I_CC:
                add_cir2(0, p[1], p[0], p[2]);
                add_cir2(0, p[3], p[0], p[4]);
                break;
            case C_CIRCUM:
                add_cir3(0, p[0], p[1], p[2], p[3]);
                break;
            case C_ORTH:
                add_tline(0, p[0], p[1], p[2], p[3]);
                add_tline(0, p[0], p[2], p[1], p[3]);
                add_tline(0, p[0], p[3], p[1], p[2]);
                break;
            case C_PETRIANGLE:
                add_cir2(0, p[0], p[1], p[2]);
                add_cir2(0, p[1], p[2], p[0]);
                add_cir2(0, p[2], p[1], p[0]);
                add_cong(0, 0, p[0], p[1], p[0], p[2]);
                add_cong(0, 0, p[1], p[0], p[1], p[2]);
                add_cong(0, 0, p[2], p[1], p[2], p[0]);
                add_ea_pt(0, p[0], p[1], p[2], p[1], p[2], p[0]);
                add_ea_pt(0, p[0], p[1], p[2], p[2], p[0], p[1]);
                add_ea_pt(0, p[1], p[2], p[0], p[2], p[0], p[1]);
                break;
            case C_NETRIANGLE:
                add_cir2(0, p[0], p[1], p[2]);
                add_cir2(0, p[1], p[2], p[0]);
                add_cir2(0, p[2], p[1], p[0]);
                add_ea_pt(0, p[0], p[1], p[2], p[1], p[2], p[0]);
                add_ea_pt(0, p[0], p[1], p[2], p[2], p[0], p[1]);
                add_ea_pt(0, p[1], p[2], p[0], p[2], p[0], p[1]);
                break;
            case C_REF:  //Point wrpt Point
                add_line1(0, p[0], p[1], p[2]);
                add_mid(0, p[2], p[1], p[0]);
                break;
            case C_SYM:  // Point wrpt LINE
                fadd_ln(p[0], p[2]);
                fadd_ln(p[0], p[3]);
                fadd_ln(p[1], p[2]);
                fadd_ln(p[1], p[3]);
                fadd_ln(p[0], p[1]);
                fadd_ln(p[2], p[3]);
                add_cir2(0, p[2], p[0], p[1]);
                add_cir2(0, p[3], p[0], p[1]);
                add_cong(0, 0, p[2], p[0], p[2], p[1]);
                add_cong(0, 0, p[3], p[0], p[3], p[1]);

                add_tline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_ICENT:
                add_ea_pt(0, p[2], p[1], p[0], p[0], p[1], p[3]);
                add_ea_pt(0, p[1], p[3], p[0], p[0], p[3], p[2]);
                add_ea_pt(0, p[3], p[2], p[0], p[0], p[2], p[1]);
                break;
            case C_PRATIO:
                add_pline(0, p[0], p[1], p[2], p[3]);
                add_ratio(p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case C_TRATIO:
                add_tline(0, p[0], p[1], p[2], p[3]);
                add_ratio(p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case C_EQDISTANCE:
                add_cong(0, 0, p[0], p[1], p[2], p[3]);
                break;
            case C_SANGLE:
                add_at(0, p[0], p[1], p[2], p[3] * A_TIME);
                break;
            case C_LRATIO:
                add_laratio(p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case C_RATIO:
                add_ratio(0, 0, p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
                break;
            case C_NRATIO:
                add_ratio(p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case C_LINE:
                break;
            case C_TRIANGLE:
                break;
            case C_ISO_TRI:
                add_pg_ln(p);
                add_cong(0, 0, p[0], p[1], p[0], p[2]);
                add_cir2(0, p[0], p[1], p[2]);
                break;
            case C_R_TRI:
                add_tline(0, p[0], p[1], p[0], p[2]);
                break;
            case C_EQ_TRI:
                add_e_triangle(ptn, p);
                break;
            case C_QUADRANGLE:
                break;
            case C_PENTAGON:
                break;
            case C_POLYGON:
                break;
            case C_TRAPEZOID:
                add_pline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_R_TRAPEZOID:
                add_r_trapezoid(p);
                break;
            case C_PARALLELOGRAM:
                add_parallelogram(p);
                break;
            case C_LOZENGE:
                add_lozenge(p);
                break;
            case C_RECTANGLE:
                add_rectangle(p);
                break;
            case C_SQUARE:
                add_square(p);
                break;
            case C_ANGLE_BISECTOR:
                add_ea_pt(0, p[1], p[2], p[0], p[0], p[2], p[3]);
                break;
            case C_CIRCLE:
                add_cir_n(p);
                break;
            case C_LC_TANGENT:
                add_tline(0, p[0], p[1], p[1], p[2]);
                break;

            case CO_CONG:
                add_cong(0, 0, p[0], p[1], p[2], p[3]);
                break;
            case CO_COLL:
                add_line(0, p[0], p[1], p[2]);
                break;
            case CO_PARA:
                add_pline(0, p[0], p[1], p[2], p[3]);
                break;
            case CO_PERP:
                add_tline(0, p[0], p[1], p[2], p[3]);
                break;
            case CO_MIDP:
                add_line1(0, p[0], p[1], p[2]);
                add_mid(0, p[0], p[1], p[2]);
                break;
            case CO_ACONG:
                add_ea_pt(0, p[0], p[1], p[2], p[3], p[4], p[5]);
                break;
            case CO_PBISECT:
                add_cong(0, 0, p[0], p[1], p[0], p[2]);
                break;
            default:
                do_pred2(p, t, ptn);
                return false;
        }
        return true;

    }


    public void add_cir_n(int p[]) {
        if (p[2] == 0) return;

        add_cir2(0, p[0], p[1], p[2]);
        a_cir c = fo_cr(p[0], p[1], p[2], 0, 0);
        for (int i = 1; i < p.length; i++) {
            if (p[i] != 0)
                add_pt2c(p[i], c);
        }
        for (int i = 0; i <= c.no; i++)
            for (int j = 0; j <= c.no; j++) {
                if (i != j)
                    add_cong(0, 0, c.o, c.pt[i], c.o, c.pt[j]);
            }
    }

    public void add_laratio(int p1, int p2, int p3, int p4, int t1, int t2) {
        add_line1(0, p1, p2, p3);
        add_line1(0, p1, p2, p4);
        add_cong(0, 0, p1, p2, p3, p4, t1 * t1, t2 * t2);
    }

    public void add_ratio(int p1, int p2, int p3, int p4, int t1, int t2) {
        add_cong(0, 0, p1, p2, p3, p4, t1 * t1, t2 * t2);
    }

    public boolean do_pdln(int ptn, int t, int[] p) {
        if (t == 0) return true;
        switch (t) {
            case C_POINT:
                break;
            case C_CONSTANT:
                break;
            case C_MIDPOINT:
                break;
            case C_FOOT:    // foot
                fadd_ln(p[0], p[1]);
                break;
            case C_O_C:
                break;
            case C_O_L:
                fadd_ln(p[1], p[2]);
                break;
            case C_O_T:
                add_nln(p, 2);
                break;
            case C_O_P:
                add_nln(p, 2);
                break;
            case C_EQANGLE:
            case C_O_A:
                fadd_ln(p[0], p[1]);
                fadd_ln(p[1], p[2]);
                fadd_ln(p[3], p[4]);
                fadd_ln(p[4], p[5]);
                break;
            case C_O_R:
                break;
            case C_O_B:
                break;
            case C_NSQUARE:
                break;
            case C_PSQUARE:
                break;
            case C_I_CC:
                break;
            case C_CIRCUM:
                break;
            case C_ORTH:
                fadd_ln(p[0], p[1]);
                fadd_ln(p[0], p[2]);
                fadd_ln(p[0], p[3]);
                fadd_ln(p[1], p[2]);
                fadd_ln(p[1], p[3]);
                fadd_ln(p[2], p[3]);
                break;
            case C_PETRIANGLE:
                fadd_ln(p[0], p[1]);
                fadd_ln(p[1], p[2]);
                fadd_ln(p[2], p[3]);
                break;
            case C_NETRIANGLE:
                break;
            case C_REF:  //Point wrpt POINT
                fadd_ln(p[1], p[2]);
                break;
            case C_SYM:  // Point wrpt LINE
                add_nln(p, 2);
                break;
            case C_ICENT:
                break;
            case C_PRATIO:
                add_nln(p, 2);
                break;
            case C_TRATIO:
                add_nln(p, 2);
                break;
            case C_EQDISTANCE:
                add_nln(p, 2);
                break;
            case C_SANGLE:
                fadd_ln(p[0], p[1]);
                fadd_ln(p[1], p[2]);
                break;
            case C_LRATIO:
                break;
            case C_NRATIO:
                break;
            case C_LINE:
                fadd_ln(p[0], p[1]);
                break;
            case C_TRIANGLE:
            case C_ISO_TRI:
            case C_R_TRI:
            case C_EQ_TRI:
            case C_QUADRANGLE:
            case C_PENTAGON:
            case C_POLYGON:
            case C_TRAPEZOID:
            case C_R_TRAPEZOID:
            case C_PARALLELOGRAM:
            case C_LOZENGE:
            case C_RECTANGLE:
            case C_SQUARE:
                add_pg_ln(p);
                break;
            default:
                return false;
        }
        return true;
    }

    public void add_pg_ln(int[] p) {
        int t = p[0];
        if (t == 0) return;

        for (int i = 1; i < p.length && p[i] != 0; i++) {
            fadd_ln(t, p[i]);
            t = p[i];
        }
        fadd_ln(p[0], t);
    }


    public void add_r_trapezoid(int[] p) {
        add_pline(0, p[0], p[1], p[2], p[3]);
        add_tline(0, p[0], p[1], p[0], p[3]);
        add_tline(0, p[0], p[3], p[2], p[3]);
    }

    public void add_parallelogram(int[] p) {
        add_pline(0, p[0], p[1], p[2], p[3]);
        add_pline(0, p[0], p[3], p[1], p[2]);
        add_cong(0, 0, p[0], p[1], p[2], p[3]);
        add_cong(0, 0, p[0], p[3], p[1], p[2]);
        tri_type = 1;
        add_stri(0, 1, p[0], p[1], p[2], p[2], p[3], p[0]);
        add_stri(0, 1, p[0], p[1], p[3], p[2], p[3], p[1]);
    }

    public void add_lozenge(int[] p) {
        add_pline(0, p[0], p[1], p[2], p[3]);
        add_pline(0, p[0], p[3], p[1], p[2]);
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 4 && j != i; j++) {
                add_cong(0, 0, p[i], p[(i + 1) % 4], p[j], p[(j + 1) % 4]);
            }

        tri_type = 1;
        add_stri(0, 1, p[0], p[1], p[2], p[2], p[3], p[0]);
        add_stri(0, 1, p[0], p[1], p[3], p[2], p[3], p[1]);
    }

    public void add_rectangle(int[] p) {
        add_pline(0, p[0], p[1], p[2], p[3]);
        add_pline(0, p[0], p[3], p[2], p[1]);
        add_tline(0, p[0], p[1], p[0], p[3]);
        add_tline(0, p[0], p[1], p[1], p[2]);
        add_tline(0, p[0], p[3], p[2], p[3]);
        add_tline(0, p[2], p[3], p[2], p[1]);
        add_cong(0, 0, p[0], p[1], p[2], p[3]);
        add_cong(0, 0, p[0], p[3], p[1], p[2]);
        tri_type = 1;
        add_stri(0, 1, p[0], p[1], p[2], p[2], p[3], p[0]);
        add_stri(0, 1, p[0], p[1], p[3], p[2], p[3], p[1]);
    }

    public void add_square(int[] p) {
        add_pline(0, p[0], p[1], p[2], p[3]);
        add_pline(0, p[0], p[3], p[2], p[1]);
        add_tline(0, p[0], p[1], p[0], p[3]);
        add_tline(0, p[0], p[1], p[1], p[2]);
        add_tline(0, p[0], p[3], p[2], p[3]);
        add_tline(0, p[2], p[3], p[2], p[1]);

        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 4 && j != i; j++) {
                add_cong(0, 0, p[i], p[(i + 1) % 4], p[j], p[(j + 1) % 4]);
            }


        for (int i = 0; i < 4; i++) {
            int i1 = i;
            int i2 = (i + 1) % 4;
            int i3 = (i + 2) % 4;
            add_at0(0, p[i2], p[i1], p[i3], A_45);
            add_at0(0, p[i2], p[i3], p[i1], A_45);
            add_at0(0, p[i1], p[i2], p[i3], A_90);
        }
        tri_type = 1;
        for (int i = 0; i < 4; i++) {
            int a = p[i];
            int b = p[(i + 1) % 4];
            int c = p[(i + 3) % 4];
            add_ea_pt_t(0, a, b, c, b, c, a);
            for (int j = i + 1; j < 4; j++) {
                int a1 = p[j];
                int b1 = p[(j + 1) % 4];
                int c1 = p[(j + 3) % 4];
                add_stri(0, 1, a, b, c, a1, b1, c1);
                add_ea_pt_t(0, a, b, c, a1, b1, c1);
                add_ea_pt_t(0, b, c, a, b1, c1, a1);
                add_ea_pt_t(0, a, b, c, b1, c1, a1);
                add_ea_pt_t(0, b, c, a, a1, b1, c1);

            }
        }


    }

    public boolean add_auxpt(Pro_point pt) {

        int[] p = pt.ps;
        int mk = pt.type;
        switch (mk) {
            case C_MIDPOINT:
                add_mid(0, p[0], p[1], p[2]);
                add_line(0, p[0], p[1], p[2]);
                break;
            case C_I_LL:
                add_line(0, p[0], p[1], p[2]);
                add_line(0, p[0], p[3], p[4]);
                break;
            case C_FOOT:
                add_line(0, p[0], p[2], p[3]);
                add_tline(0, p[0], p[1], p[2], p[3]);
                break;
            case C_I_LC:
                add_line(0, p[0], p[1], p[2]);
                add_cir2(0, p[3], p[0], p[4]);
                break;
            case C_I_LP:
                add_line(0, p[0], p[1], p[2]);
                add_pline(0, p[0], p[3], p[4], p[5]);
                break;
            case C_I_LT:
                add_line(0, p[0], p[1], p[2]);
                add_tline(0, p[0], p[3], p[4], p[5]);
                break;
            case C_I_PC:
                add_pline(0, p[0], p[1], p[2], p[3]);
                add_cir2(0, p[4], p[0], p[5]);
                break;
            case C_CIRCUM:
                break;
            default:
                break;
        }

        return true;
    }

    public boolean do_pred2(int[] p1, int mk, int ptn) {
        ptn = p1[0];
        int[] p = new int[p1.length];

        for (int i = 1; i < p.length; i++)
            p[i - 1] = p1[i];

        switch (mk) {
            case C_I_LL:
                add_line(0, ptn, p[0], p[1]);
                add_line(0, ptn, p[2], p[3]);
                break;
            case C_FOOT:    // foot
                add_line(0, ptn, p[1], p[2]);
                add_tline(0, ptn, p[0], p[1], p[2]);
                break;
            case C_I_LT:    // perp D A B C
                add_line(0, ptn, p[0], p[1]);
                add_tline(0, ptn, p[2], p[3], p[4]);
                break;
            case C_I_LP:    //p line
                add_line(0, ptn, p[0], p[1]);
                add_pline(0, ptn, p[2], p[3], p[4]);
                break;
            case C_I_PP:   // pp
                add_pline(0, ptn, p[0], p[1], p[2]);
                add_pline(0, ptn, p[3], p[4], p[5]);
                break;
            case C_I_PT:   //pt
                add_pline(0, ptn, p[0], p[1], p[2]);
                add_tline(0, ptn, p[3], p[4], p[5]);
                break;
            case C_I_TT:
                add_tline(0, ptn, p[0], p[1], p[2]);
                add_tline(0, ptn, p[3], p[4], p[5]);
                break;
            case C_I_LB:
                add_line(0, ptn, p[0], p[1]);
                add_cir2(0, ptn, p[2], p[3]);
                break;
            case C_I_LC:
                add_line(0, ptn, p[0], p[1]);
                add_cir2(0, p[2], ptn, p[3]);
                if (p[0] == p[2] && p[1] == p[3]) {
                    add_mid(0, p[2], ptn, p[3]);
                } else if (p[1] == p[2] && p[0] == p[3]) {
                    add_mid(0, p[2], ptn, p[3]);
                }
                break;

            case C_I_PC:
                add_pline(0, ptn, p[0], p[1], p[2]);
                add_cir2(0, p[3], ptn, p[4]);
                break;
            case C_I_PB:
                add_pline(0, ptn, p[0], p[1], p[2]);
                add_cir2(0, ptn, p[3], p[4]);
                break;
            case C_I_TC:
                add_tline(0, ptn, p[0], p[1], p[2]);
                add_cir2(0, p[3], ptn, p[4]);
                break;
            case C_I_BC:
                add_cir2(0, ptn, p[0], p[1]);
                add_cir2(0, p[2], ptn, p[3]);
                break;
            case C_I_BB:
                add_cong(0, 0, ptn, p[0], ptn, p[1]);
                add_cong(0, 0, ptn, p[2], ptn, p[3]);
                break;
            case C_I_TB:
                add_tline(0, ptn, p[0], p[1], p[2]);
                add_cir2(0, ptn, p[3], p[4]);
                break;
            case C_I_LR:// L RCircle
                add_line(0, ptn, p[0], p[1]);
                add_cong(0, 0, p[2], ptn, p[3], p[4]);
                break;
            case C_I_TR:
                add_tline(0, ptn, p[0], p[1], p[2]);
                add_cong(0, 0, ptn, p[3], p[3], p[4]);
                break;
            case C_I_CC:
                add_cir2(0, p[0], ptn, p[1]);
                add_cir2(0, p[2], ptn, p[3]);
                break;
            case C_I_CR:
                add_cir2(0, p[0], ptn, p[1]);
                add_cong(0, 0, p[2], ptn, p[3], p[4]);
                break;
            case C_I_RR:
                add_cong(0, 0, p[0], ptn, p[1], p[2]);
                add_cong(0, 0, p[3], ptn, p[4], p[5]);
                break;
            case C_I_LA:
                this.add_line(0, p1[0], p1[1], p1[2]);
                this.add_ea_pt(0, p1[0], p1[3], p1[4], p1[5], p1[6], p1[7]);
                break;
            case C_I_PA:
                this.add_pline(0, p1[0], p1[1], p1[2], p1[3]);
                this.add_ea_pt(0, p1[4], p1[5], p1[6], p1[7], p1[8], p1[9]);
                break;
            case C_I_TA:
                this.add_tline(0, p1[0], p1[1], p1[2], p1[3]);
                this.add_ea_pt(0, p1[4], p1[5], p1[6], p1[7], p1[8], p1[9]);
                break;
            case C_I_BA:
                this.add_cong(0, 0, p1[0], p1[1], p1[0], p1[2]);
                this.add_ea_pt(0, p1[0], p1[3], p1[4], p1[5], p1[6], p1[7]);
                break;
            case C_I_AA:
                this.add_ea_pt(0, p1[0], p1[1], p1[2], p1[3], p1[4], p1[5]);
                this.add_ea_pt(0, p1[0], p1[6], p1[7], p1[8], p1[9], p1[10]);
                break;
            case C_NSQUARE:
                add_tline(0, ptn, p[0], p[0], p[1]);
                add_cir2(0, p[0], ptn, p[1]);
                break;
            case C_PSQUARE:
                add_tline(0, ptn, p[0], p[0], p[1]);
                add_cir2(0, p[0], ptn, p[1]);
                break;
            case C_ICENT:
            case CO_INCENT:
                add_ea_pt(0, p[1], p[0], p[2], p[2], p[0], ptn);
                add_ea_pt(0, p[0], p[1], p[2], p[2], p[1], ptn);
                add_ea_pt(0, p[0], ptn, p[2], p[2], ptn, p[1]);
                break;
            case C_ICENT1:
                add_ea_pt(0, p[1], p[0], ptn, ptn, p[0], p[2]);
                add_ea_pt(0, p[0], p[1], ptn, ptn, p[1], p[2]);
                add_ea_pt(0, p[1], p[2], ptn, ptn, p[2], p[0]);
                break;
            case C_CENT:
                break;
            case C_ORTH:
                add_tline(0, ptn, p[0], p[1], p[2]);
                add_tline(0, ptn, p[1], p[0], p[2]);
                add_tline(0, ptn, p[2], p[1], p[0]);
                break;
            case C_PETRIANGLE:
                add_cir2(0, ptn, p[0], p[1]);
                add_cir2(0, p[0], p[1], ptn);
                add_cir2(0, p[1], p[0], ptn);
                add_ea_pt(0, ptn, p[0], p[1], p[0], p[1], ptn);
                add_ea_pt(0, ptn, p[0], p[1], p[1], ptn, p[0]);
                add_ea_pt(0, p[0], p[1], ptn, p[1], ptn, p[0]);
                break;
            case C_NETRIANGLE:
                add_cir2(0, ptn, p[0], p[1]);
                add_cir2(0, p[0], p[1], ptn);
                add_cir2(0, p[1], p[0], ptn);
                add_ea_pt(0, ptn, p[0], p[1], p[0], p[1], ptn);
                add_ea_pt(0, ptn, p[0], p[1], p[1], ptn, p[0]);
                add_ea_pt(0, p[0], p[1], ptn, p[1], ptn, p[0]);
                break;
            case C_I_SS: {
                int pt1 = fd_co(p[0], p[1], p[2]);
                int pt2 = fd_co(p[3], p[4], p[5]);
                add_cir4(0, pt1, ptn, p[0], p[1], p[2]);
                add_cir4(0, pt2, ptn, p[3], p[4], p[5]);
            }
            break;
            default: {
                Cm.print("Error input type: " + mk);
                return false;
            }

        }
        return true;
    }

    final public void add_square(Pro_point p) {


        int i = 0;
        for (; allpts[i + 1] != p; i++) ;
        Pro_point p1 = allpts[i];

        if (!(p1.ps[1] == p.ps[1] && p1.ps[2] == p.ps[2] || p1.ps[1] == p.ps[2] && p1.ps[2] == p.ps[1])) return;

        {
            int a = p1.ps[0];
            int b = p1.ps[1];
            int c = p1.ps[2];
            int d = p.ps[0];
            add_at(0, a, b, c, A_90);
            add_at(0, b, c, d, A_90);
            add_at(0, c, d, a, A_90);
            add_at(0, d, a, b, A_90);
            add_at(0, a, b, d, A_45);
            add_at(0, a, d, b, A_45);
            add_at(0, a, c, d, A_45);
            add_at(0, a, c, b, A_45);
            add_at(0, c, b, d, A_45);
            add_at(0, c, d, b, A_45);
            add_at(0, c, a, b, A_45);
            add_at(0, c, a, d, A_45);
        }


        if (p.type == C_PSQUARE && p1.type == C_NSQUARE || p.type == C_NSQUARE && p1.type == C_PSQUARE) {
            if (p1.ps[1] == p.ps[2] && p1.ps[2] == p.ps[1]) {
                int a = p1.ps[0];
                int b = p1.ps[1];
                int c = p1.ps[2];
                int d = p.ps[0];
                add_pline(0, a, b, c, d);
                add_pline(0, a, d, b, c);

                add_tline(0, a, b, b, c);
                add_tline(0, b, c, c, d);
                add_tline(0, c, d, d, a);
                add_tline(0, a, b, a, d);

                add_cong(0, 0, a, b, b, c);
                add_cong(0, 0, a, b, c, d);
                add_cong(0, 0, a, b, a, d);
                add_cong(0, 0, b, c, c, d);
                add_cong(0, 0, b, c, a, d);
                add_cong(0, 0, c, d, a, d);
            }
        }

    }

    final public void add_e_triangle(int ptn, int[] ps) {
        int a = ps[0];
        int b = ps[1];
        int c = ps[2];

        add_at0(0, a, b, c, A_60);
        add_at0(0, b, c, a, A_60);
        add_at0(0, c, a, b, A_60);
        add_cong(0, 0, a, b, a, c);
        add_cong(0, 0, a, b, b, c);
        add_cong(0, 0, a, c, b, c);
        for (int i = 1; true; i++) {
            if (i == ptn) break;
            cons p1 = (cons) allcns[i];
            if (p1.type != C_EQ_TRI) continue;

            int d = check_tri_dr(ps[0], ps[1], ps[2], p1.ps[0], p1.ps[1], p1.ps[2]);
            int[] pp = new int[3];
            if (d > 0) {
                pp[0] = ps[0];
                pp[1] = ps[1];
                pp[2] = ps[2];
            } else {
                pp[0] = ps[0];
                pp[1] = ps[2];
                pp[2] = ps[1];
            }
            add_ea_pt(0, p1.ps[0], p1.ps[1], p1.ps[2], pp[0], pp[1], pp[2]);
            add_ea_pt(0, p1.ps[0], p1.ps[1], p1.ps[2], pp[2], pp[0], pp[1]);
            add_ea_pt(0, p1.ps[0], p1.ps[1], p1.ps[2], pp[1], pp[2], pp[0]);

            add_ea_pt(0, p1.ps[2], p1.ps[0], p1.ps[1], pp[0], pp[1], pp[2]);
            add_ea_pt(0, p1.ps[2], p1.ps[0], p1.ps[1], pp[2], pp[0], pp[1]);
            add_ea_pt(0, p1.ps[2], p1.ps[0], p1.ps[1], pp[1], pp[2], pp[0]);

            add_ea_pt(0, p1.ps[1], p1.ps[2], p1.ps[0], pp[0], pp[1], pp[2]);
            add_ea_pt(0, p1.ps[1], p1.ps[2], p1.ps[0], pp[2], pp[0], pp[1]);
            add_ea_pt(0, p1.ps[1], p1.ps[2], p1.ps[0], pp[1], pp[2], pp[0]);
        }
    }

    final public int on_sts1(int a, int b, int c, s_tris st) {
        int p, q, r;
        for (int i = 0; i <= st.no; i++) {
            p = st.p1[i];
            q = st.p2[i];
            r = st.p3[i];
            if (a == p && b == q && c == r ||
                    a == p && b == r && c == q ||
                    a == q && b == p && c == r ||
                    a == q && b == r && c == p ||
                    a == r && b == p && c == q ||
                    a == r && b == q && c == p) {
                return i;
            }
        }
        return -1;
    }

    final public int on_sts(int a, int b, int c, s_tris st) {
        int p, q, r;
        for (int i = 0; i <= st.no; i++) {
            p = st.p1[i];
            q = st.p2[i];
            r = st.p3[i];
            if (a == p && b == q && c == r) {
                return i;
            }
        }
        return -1;
    }

    final public void add_to_sts(int d, int a, int b, int c, s_tris st) {

        if (on_sts1(a, b, c, st) >= 0) return;

        int n = ++st.no;
        st.dr[n] = d;
        st.p1[n] = a;
        st.p2[n] = b;
        st.p3[n] = c;
    }


    final public void cb_sts(s_tris s, s_tris s1, int t) {
        s1.type = 0;

        int o = s.no;
        while (true) {
            if (s.p1[o] == s1.p2[t]) {
                for (int i = 0; i <= s1.no; i++) {
                    int x = s1.p1[i];
                    s1.p1[i] = s1.p2[i];
                    s1.p2[i] = x;
                }
            } else if (s.p1[o] == s1.p3[t]) {
                for (int i = 0; i <= s1.no; i++) {
                    int x = s1.p1[i];
                    s1.p1[i] = s1.p3[i];
                    s1.p3[i] = x;
                }
            } else if (s.p2[o] == s1.p3[t]) {
                for (int i = 0; i <= s1.no; i++) {
                    int x = s1.p2[i];
                    s1.p2[i] = s1.p3[i];
                    s1.p3[i] = x;
                }
            } else
                break;
        }
        for (int i = 0; i <= s1.no; i++) {
            if (i != t && (on_sts1(s1.p1[i], s1.p2[i], s1.p3[i], s) < 0)) {
                s.no++;
                s.p1[s.no] = s1.p1[i];
                s.p2[s.no] = s1.p2[i];
                s.p3[s.no] = s1.p3[i];
                s.dr[s.no] = s1.dr[i];
            }
        }


    }

    final public boolean add_sts1(sim_tri t) {
        s_tris st = null;

        if (tri_type == 0)
            st = all_sts.nx;
        else
            st = all_cts.nx;

        int o1, o2;
        s_tris st1;
        o1 = o2 = -1;
        st1 = null;

        while (st != null) {
            if (st.type != 0)
                for (int k = 0; k < 3; k++) {
                    for (int m = 0; m < 3; m++) {
                        if (k != m)
                            for (int n = 0; n < 3; n++)
                                if (k != n && m != n) {
                                    int t1 = on_sts(t.p1[k], t.p1[m], t.p1[n], st);
                                    int t2 = on_sts(t.p2[k], t.p2[m], t.p2[n], st);

                                    if (t1 >= 0 && t2 >= 0) {
                                        o1 = t1;
                                        o2 = t2;
                                        st1 = st;
                                        return true;
                                    } else if (t1 >= 0) {
                                        o1 = t1;
                                        add_to_sts(t.dr * st.dr[o1], t.p2[k], t.p2[m], t.p2[n], st);
                                        st1 = st;
                                        break;
                                    } else if (t2 >= 0) {
                                        o2 = t2;
                                        add_to_sts(t.dr * st.dr[o2], t.p1[k], t.p1[m], t.p1[n], st);
                                        st1 = st;
                                        break;
                                    }
                                }
                        if (st == st1) break;
                    }
                    if (st == st1) break;
                }
            if (st == st1) break;
            st = st.nx;
        }

        if (st1 == null) return false;

        int a, b, c;
        a = st1.p1[st1.no];
        b = st1.p2[st1.no];
        c = st1.p3[st1.no];

        int nd = -1;
        while (st != null) {
            if (st.type != 0 && st != st1 && (nd = on_sts1(a, b, c, st)) >= 0) {
                cb_sts(st1, st, nd);
            }
            st = st.nx;
        }

        return true;
    }

    final public void add_sts(sim_tri st) {

        if (add_sts1(st)) return;

        s_tris sts = new s_tris();
        sts.st = tri_type;

        if (tri_type == 0) {
            last_sts.nx = sts;
            last_sts = sts;
        } else {
            last_cts.nx = sts;
            last_cts = sts;
        }
        sts.type = 2;
        sts.no = 1;
        sts.dr[0] = 1;
        sts.dr[1] = st.dr;
        sts.p1[0] = st.p1[0];
        sts.p2[0] = st.p1[1];
        sts.p3[0] = st.p1[2];
        sts.p1[1] = st.p2[0];
        sts.p2[1] = st.p2[1];
        sts.p3[1] = st.p2[2];
//        sts.dep[0] = sts.dep[1] = depth;
        return;
    }

    public void collect_sts() {
        sim_tri st = all_st.nx;
        tri_type = 0;
        while (st != null) {
            if (st.type != 0)
                add_sts(st);
            st = st.nx;
        }

        st = all_ct.nx;
        tri_type = 1;
        while (st != null) {
            if (st.type != 0)
                add_sts(st);
            st = st.nx;
        }
    }

    ///////////////////////////////////////////////////////////////////////
    public void add_to_cg(int p1, int p2, c_segs cg) {
        cg.no++;
        cg.p1[cg.no] = p1;
        cg.p2[cg.no] = p2;
    }

    public void cb_cgs(c_segs cg, c_segs cg1) {
        cg1.type = 0;
        for (int i = 0; i <= cg1.no; i++) {
            if (!on_cgs(cg1.p1[i], cg1.p2[i], cg))
                add_to_cg(cg1.p1[i], cg1.p2[i], cg);
        }
    }

    public boolean on_cgs(int p1, int p2, c_segs cgs) {
        for (int i = 0; i <= cgs.no; i++)
            if (p1 == cgs.p1[i] & p2 == cgs.p2[i]) return true;
        return false;
    }

    public void add_cgs(int p1, int p2, int p3, int p4) {
        c_segs cg = all_cgs.nx;
        boolean t1, t2;
        t1 = t2 = false;
        while (cg != null) {
            if (cg.type != 0) {
                for (int i = 0; i <= cg.no; i++) {
                    if (p1 == cg.p1[i] && p2 == cg.p2[i])
                        t1 = true;
                    else if (p3 == cg.p1[i] && p4 == cg.p2[i])
                        t2 = true;
                    if (t1 && t2) return;
                }
                if (t1 && t2) return;
                if (t1) {
                    add_to_cg(p3, p4, cg);
                    break;
                } else if (t2) {
                    add_to_cg(p1, p2, cg);
                    break;
                }
            }
            cg = cg.nx;
        }

        if (t1) {
        } else if (t2) {
            int t = p1;
            p1 = p3;
            p3 = t;
            t = p2;
            p2 = p4;
            p4 = t;
        } else {
            c_segs c = new c_segs();
            c.no = 1;
            c.type = 2;
            c.p1[0] = p1;
            c.p2[0] = p2;
            c.p1[1] = p3;
            c.p2[1] = p4;
            last_cgs.nx = c;
            last_cgs = c;
            return;
        }
        c_segs cg1 = cg.nx;
        while (cg1 != null) {
            if (cg1.type != 0 && on_cgs(p3, p4, cg1))
                cb_cgs(cg, cg1);
            cg1 = cg1.nx;
        }

    }


    /////////////////////////////////////
    final p_line fo_pn1(int p, int q, int r, int s) {
        p_line pn = all_pn.nx;
        boolean r1, r2;

        while (pn != null) {
            r1 = r2 = false;
            for (int i = 0; i <= pn.no; i++) {
                if (on_ln(p, q, pn.ln[i])) r1 = true;
                if (on_ln(r, s, pn.ln[i])) r2 = true;
            }
            if (r1 && r2) return pn;
            pn = pn.nx;
        }
        return null;

    }

    final t_line fo_tn1(int p1, int p2, int p3, int p4) {
        if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0) return null;

        t_line tn = all_tn.nx;
        while (tn != null) {
            if (on_ln(p1, p2, tn.l1) && on_ln(p3, p4, tn.l2) ||
                    on_ln(p1, p2, tn.l2) && on_ln(p3, p4, tn.l1))
                return (tn);
            tn = tn.nx;
        }
        return tn;
    }

    final angles fo_as1(int a, int b, int c, int d, int p, int q, int r, int s) {
        angles as = all_as.nx;
        while (as != null) {
            if (on_ln(a, b, as.l1) && on_ln(c, d, as.l2) && on_ln(p, q, as.l3) && on_ln(r, s, as.l4)) return as;
            if (on_ln(a, b, as.l2) && on_ln(c, d, as.l1) && on_ln(p, q, as.l4) && on_ln(r, s, as.l3)) return as;
            if (on_ln(a, b, as.l3) && on_ln(c, d, as.l4) && on_ln(p, q, as.l1) && on_ln(r, s, as.l2)) return as;
            if (on_ln(a, b, as.l4) && on_ln(c, d, as.l3) && on_ln(p, q, as.l2) && on_ln(r, s, as.l1)) return as;

            if (isPFull()) {
                if (on_ln(a, b, as.l1) && on_ln(c, d, as.l3) && on_ln(p, q, as.l2) && on_ln(r, s, as.l4)) return as;
                if (on_ln(a, b, as.l3) && on_ln(c, d, as.l1) && on_ln(p, q, as.l4) && on_ln(r, s, as.l2)) return as;
                if (on_ln(a, b, as.l2) && on_ln(c, d, as.l4) && on_ln(p, q, as.l1) && on_ln(r, s, as.l3)) return as;
                if (on_ln(a, b, as.l4) && on_ln(c, d, as.l2) && on_ln(p, q, as.l3) && on_ln(r, s, as.l1)) return as;
            }
            as = as.nx;
        }
        return null;
    }

    boolean on_ln(int p, int q, l_line ln) {

        int i, n;
        n = 0;
        if (ln == null) return (false);
        for (i = 0; i <= ln.no; i++)
            if (ln.pt[i] == p || ln.pt[i] == q) n++;

        return (n == 2);
    }

    l_line fd_ln1(int p1, int p2, l_line lnx) {
        l_line ln = all_ln.nx;

        while (ln != null) {
            if (lnx != null && ln != lnx)
                ln = ln.nx;
            else if (lnx != null && ln == lnx) {
                ln = ln.nx;
                lnx = null;
            } else if (on_ln(p1, ln) && on_ln(p2, ln))
                break;
            else
                ln = ln.nx;
        }
        return (ln);
    }

    public l_line fd_ln_lp(l_line ln, int p) {  // a line contian ln, and p , no others.
        int n = 0;
        if (on_ln(p, ln))
            n = ln.no;
        else
            n = ln.no + 1;
        l_line l1 = all_ln.nx;
        while (l1 != null) {
            if (sub_ln(ln, l1) && on_ln(p, l1) && l1.no == n)
                return l1;
            l1 = l1.nx;
        }
        return null;
    }

    final public boolean xcir_n(int o, int a, int b, int c, int d, int e) {
        test_c.no = -1;
        test_c.o = o;
        add_pt2c(a, test_c);
        add_pt2c(b, test_c);
        add_pt2c(c, test_c);
        add_pt2c(d, test_c);
        add_pt2c(e, test_c);
        a_cir cr = xcir(test_c);
        if (cr != null)
            return true;
        return false;
    }

    final void adj_cir1(a_cir cr) {
        a_cir cr1, cr2, cr3;
        cond co;
        char i;
        cr1 = cr;
        while ((cr2 = fd_cir(cr1)) != null) {
            if (cr2 == cr1) break;

            if (sub_cir(cr2, cr1)) {
                cr2.type = 0;
            } else if (sub_cir(cr1, cr2)) {
                cr1.type = 0;
                cr1 = cr2;
            } else {
                cr3 = cp_cr(cr1);
                cr1.type = 0;
                cr2.type = 0;
                cr3.type = 2;
                if (cr3.o == 0) cr3.o = cr2.o;
                for (i = 0; i <= cr2.no; i++) add_pt2c(cr2.pt[i], cr3);
                if (cr1.co == null && cr2.co == null)
                    cr3.co = null;
                else {
                    co_xy.nx = null;
                    cr3.lemma = 301;
                    co = add_coxy(CO_CYCLIC);
                    co.u.cr = cr1;
                    co = add_coxy(CO_CYCLIC);
                    co.u.cr = cr2;
                    cr3.co = co_xy.nx;
                }
                cr1 = cr3;
                new_pr(CO_CYCLIC);
                last_nd.u.cr = cr1;
            }
        }
    }

    public l_line fd_ln1(int p1, int p2) {
        l_line ln = all_ln.nx;
        while (ln != null) {

            if (on_ln(p1, p2, ln) && ln.no == 1)
                return ln;
            ln = ln.nx;
        }
        return ln;
    }


    final void add_pline1(int lm, int p1, int p2, int p3, int p4) {
        p_line pn;
        if (!valid(lm)) return;
        if (xpara(p1, p2, p3, p4)) return;
        if (p1 == p2 || p3 == p4) return;

        if (!check_para(p1, p2, p3, p4)) {
            add_checkError();
            return;
        }
        {
            if (p1 == p3 || p1 == p4 || p2 == p3 || p2 == p4) {
                int k = 0;
            }
            if (p1 > p2) {
                int k = p1;
                p1 = p2;
                p2 = k;
            }
            if (p3 > p4) {
                int k = p3;
                p3 = p4;
                p4 = k;
            }
            if (p1 > p3) {
                int k = p1;
                p1 = p3;
                p3 = k;
                k = p2;
                p2 = p4;
                p4 = k;
            }

            l_line ln1 = fd_ln1(p1, p2);
            l_line ln2 = fd_ln1(p3, p4);

            l_line l1 = fd_ln(p1, p2);
            l_line l2 = fd_ln(p3, p4);
            if ((l1 == null || l2 == null) && search_only_exists_ln())
                return;

            pn = new p_line();
            pn.type = 2;
            pn.lemma = lm;
            pn.co = co_db.nx;
            pn.no = 1;

            if (ln1 == null)
                ln1 = add_ln(p1, p2);
            if (ln2 == null)
                ln2 = add_ln(p3, p4);

            if (l1 != null && l1 != ln1)
                ln1.type = 0;
            if (l2 != null && l2 != ln2)
                ln2.type = 0;

            if (ln1.pt[0] < ln2.pt[0] || (ln1.pt[0] == ln2.pt[0] && ln1.pt[1] < ln2.pt[1])) {
                pn.ln[0] = ln1;
                pn.ln[1] = ln2;
            } else {
                pn.ln[0] = ln2;
                pn.ln[1] = ln1;
            }

            pn.nx = null;
            last_pn.nx = pn;
            last_pn = pn;
            new_pr(CO_PARA);
            last_nd.u.pn = pn;
            //adj_pn(pn);
        }

    }

    final void adj_pn(p_line pn) {
        p_line pn1, pn2, pn3;
        cond co;
        if (pn.no < 1 || pn.type == 0) return;

        if (pn.no == 1) {
            l_line l1 = pn.ln[0];
            l_line l2 = pn.ln[1];
            pn1 = fd_pnp(pn, l1);
            pn2 = fd_pnp(pn, l2);

            if (l1.type == 0 || l2.type == 0) {
                l_line ln1 = l1;
                l_line ln2 = l2;
                if (l1.type == 0)
                    ln1 = fd_lnl(ln1);
                if (ln2.type == 0)
                    ln2 = fd_lnl(ln2);
                if (!sub_ln(l1, ln1) || !sub_ln(l2, ln2)) {
                    int k = 0;
                }
                p_line pnn = add_px(188, ln1, ln2);
                if (pnn != null) {
                    cond c = add_coxy(CO_PARA);
                    c.u.pn = new p_line(l1, l2);

                    pnn.co = c;
                }
                pn.type = 0;
            }

            if (pn1 == null && pn2 == null)
                return;

            if (pn1 == null) {
                pn3 = pn1;
                pn1 = pn2;
                pn2 = pn3;
            }

            co_xy.nx = null;
            pn3 = cp_pn(pn1);
            pn1.type = 0;
            pn_un(pn3, pn);
            pn.type = 0;
            pn3.type = 2;
            pn3.lemma = 2;
            co = add_coxy(CO_PARA);
            co.u.pn = pn1;
            co = add_coxy(CO_PARA);
            co.u.pn = pn;
            pn3.co = co_xy.nx;//(pn.co == null) ? co_xy.nx : pn.co;
            if (pn2 != null) {
                co_xy.nx = null;
                pn1 = cp_pn(pn3);
                pn3.type = 0;
                pn_un(pn1, pn2);
                pn2.type = 0;
                pn1.lemma = 2;
                co = add_coxy(CO_PARA);
                co.u.pn = pn3;
                co = add_coxy(CO_PARA);
                co.u.pn = pn2;
                pn1.co = co_xy.nx;//(pn.co == null) ? null : co_xy.nx;
                pn3 = pn1;
            }
            new_para(pn3);
        }
    }

    final void add_as_codb(l_line l1, l_line l2, l_line l3, l_line l4) {
        int p1, p2, p3, p4, p5, p6, p7, p8;
        int a = inter_ll(l1, l2);
        int b = inter_ll(l3, l4);

        if (a != 0) {
            p2 = a;
            p3 = a;
            if (l1.pt[0] == a)
                p1 = l1.pt[1];
            else
                p1 = l1.pt[0];

            if (l2.pt[0] == a)
                p4 = l2.pt[1];
            else
                p4 = l2.pt[0];
        } else {
            p1 = l1.pt[0];
            p2 = l1.pt[1];
            p3 = l2.pt[0];
            p4 = l2.pt[1];
        }
        if (b != 0) {
            p6 = b;
            p7 = b;
            if (l3.pt[0] == b)
                p5 = l3.pt[1];
            else
                p5 = l3.pt[0];

            if (l4.pt[0] == b)
                p8 = l4.pt[1];
            else
                p8 = l4.pt[0];
        } else {
            p5 = l3.pt[0];
            p6 = l3.pt[1];
            p7 = l4.pt[0];
            p8 = l4.pt[1];
        }
        add_codb(CO_ACONG, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    // traditional prove


    angles add_as_t(int lm, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (l1 == l2 && l3 == l4) return null;
        if (!valid(lm)) return null;

        l_line n1;
        if (line_compare(l1, l2) > 0) {
            n1 = l1;
            l1 = l2;
            l2 = n1;
            n1 = l3;
            l3 = l4;
            l4 = n1;
        }

        if (line_compare(l1, l3) > 0) {
            n1 = l1;
            l1 = l3;
            l3 = n1;
            n1 = l2;
            l2 = l4;
            l4 = n1;
        }

        if (l1 == l2 && l3 == l4 || l1 == l3 && l2 == l4) return null;

        if (!search_only_exists_ln(l1, l2, l3, l4)) return null;

        if (!check_eqangle(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1])) {
            int k = 0;
        }
        if (check_perp(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1])) {
            int k = 0;
        }

        if (inter_lls(l1, l2) == 0 || inter_lls(l3, l4) == 0) {
            int k = 0;
        }

        angles as = new angles();
        as.type = 2;
        as.lemma = lm;
        as.co = co_db.nx;
        as.sa = 0;
        as.nx = null;
        as.l1 = l1;
        as.l2 = l2;
        as.l3 = l3;
        as.l4 = l4;
        as.atp = 0;
        last_as.nx = as;
        last_as = as;

        new_pr(CO_ACONG);
        last_nd.u.as = as;
        if (l1 == l2 || l3 == l4 || l1 == l3 && l2 == l4 || l1 == l4 && l2 == l3) {
            int k = 0;
        }
        if (l1.type == 0 || l2.type == 0 || l3.type == 0 || l4.type == 0)
            as.type = 0;
        return (as);
    }

    final boolean xacongt(int a, int b, int c, int p, int q, int r) {
        l_line l1, l2, l3, l4;
        l1 = fd_ln(a, b);
        l2 = fd_ln(b, c);
        l3 = fd_ln(p, q);
        l4 = fd_ln(q, r);

        if (l1 == null || l2 == null || l3 == null || l4 == null) return (false);
        if (xcoll4(a, b, p, q) && xcoll4(b, c, q, r)) return true;
        if (xpara(a, b, b, c) && xpara(p, q, q, r)) return (true);
        if (xperp(a, b, b, c) && xperp(p, q, q, r)) return (true);
        return (this.fo_as1_t(a, b, b, c, p, q, q, r) != null);
    }

    final angles fo_as1_t(int a, int b, int c, int d, int p, int q, int r, int s) {

        angles as = all_as.nx;
        while (as != null) {
            if (on_ln(a, b, as.l1) && on_ln(c, d, as.l2) && on_ln(p, q, as.l3) && on_ln(r, s, as.l4)) return as;
            if (on_ln(a, b, as.l2) && on_ln(c, d, as.l1) && on_ln(p, q, as.l4) && on_ln(r, s, as.l3)) return as;
            if (on_ln(a, b, as.l3) && on_ln(c, d, as.l4) && on_ln(p, q, as.l1) && on_ln(r, s, as.l2)) return as;
            if (on_ln(a, b, as.l4) && on_ln(c, d, as.l3) && on_ln(p, q, as.l2) && on_ln(r, s, as.l1)) return as;
            as = as.nx;
        }
        return null;
    }

    angles fd_as_t(l_line l1, l_line l2) {
        angles as;
        for (as = all_as.nx; as != null; as = as.nx) {
            if (
                    (l1 == as.l1 && l2 == as.l2) || (l1 == as.l2 && l2 == as.l1) ||
                            (l1 == as.l3 && l2 == as.l4) || (l1 == as.l4 && l2 == as.l3))
                return (as);
        }
        return (null);
    }

    final angles fo_las_t(l_line l1, l_line l2, l_line l3, l_line l4) {
        angles as = all_as.nx;
        while (as != null) {
            if (l1 == as.l1 && l2 == as.l2 && l3 == as.l3 && l4 == as.l4) return as;
            if (l1 == as.l2 && l2 == as.l1 && l3 == as.l4 && l4 == as.l3) return as;
            if (l1 == as.l3 && l2 == as.l4 && l3 == as.l1 && l4 == as.l2) return as;
            if (l1 == as.l4 && l2 == as.l3 && l3 == as.l2 && l4 == as.l1) return as;
            as = as.nx;
        }
        return (null);
    }

    public angles add_as(int lm, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (isPFull())
            return add_as0(lm, l1, l2, l3, l4);
        else
            return add_as_t(lm, l1, l2, l3, l4);
    }

    final void add_ea_pt_t(int lm, int a, int b, int c, int p, int q, int r) {        // for inscrible angle.

        if (d_base == 1) return;
        if (!valid(lm)) return;
        if (this.isPFull()) {
            this.add_ea_pt(lm, a, b, c, p, q, r);
            return;
        }
        if (xacongt(a, b, c, p, q, r)) return;
        l_line l1 = fadd_ln_t(a, b);
        l_line l2 = fadd_ln_t(b, c);
        l_line l3 = fadd_ln_t(p, q);
        l_line l4 = fadd_ln_t(q, r);
        if (l1 == null || l2 == null || l3 == null || l4 == null) return;
        if (ln_acong(l1, l2, l3, l4)) return;
        angles as = add_as(lm, l1, l2, l3, l4);
    }

    final l_line add_ln_t0(int a, int b) {
        if (a == 0 || b == 0) return null;

        l_line ln = fd_ln1(a, b);
        if (ln != null) return ln;

        l_line ln1 = fd_ln(a, b);

        if (ln1 == null && search_only_exists_ln()) return null;

        ln = add_ln(a, b);

        if (ln1 != null)
            ln.type = 0;
        return ln;
    }

    l_line fadd_ln_t(int p1, int p2) {
        return add_ln_t0(p1, p2);
    }

    public int get_anti_pt(l_line ln, int p, int p1) { // p is imdpoint of x and p1
        for (int i = 0; i <= ln.no; i++) {
            if (ln.pt[i] != p && ln.pt[i] != p1 && (x_inside(p, ln.pt[i], p1)))
                return ln.pt[i];
        }
        return 0;
    }


    boolean xcoll_ln(l_line l1, l_line l2) {
        if (l1.type == 0 && l2.type == 0) {
            int r = 0;
        }
        if (l1 == l2) return true;

        if (l1.type != 0 && l2.type != 0)
            return false;

        if (l1.type == 0)
            l1 = fd_lnl(l1);
        if (l2.type == 0)
            l2 = fd_lnl(l2);
        return l1 == l2;
    }
    ////////////////////////////////////////////////////////////////////
    //// for multipul prove.

    //////////////////////////////////////////////////////////////////////////////////

    public anglet add_at(int lemma, int a, int b, int c, int v) {

        return add_at(lemma, fadd_ln_t(a, b), fadd_ln_t(b, c), v);
    }

    public anglet add_at0(int lemma, int a, int b, int c, int v) {

        return add_at(lemma, fd_ln(a, b), fd_ln(b, c), v);
    }


    public anglet new_at(int v, int p, l_line l1, l_line l2) {
        anglet at = new anglet();
        at.v = v;
        at.p = p;
        at.l1 = l1;
        at.l2 = l2;
        return at;
    }

    public anglet add_at(int lemma, l_line l1, l_line l2, int v) {
        if (isPFull()) return null;
        if (!valid(R_AG_SPECIAL)) return null;
        if (!valid(lemma)) return null;


        if (l1 == null || l2 == null) return null;

        if (xatcong(l1, l2)) return null;

        int p = inter_lls(l1, l2);
        if (p == 0) return null;

        if (line_compare(l1, l2) > 0) {
            l_line t = l1;
            l1 = l2;
            l2 = t;
            v = -v;
        }
        if (v > A_180)
            v = v - A_360;
        else if (v <= -A_180)
            v = v + A_360;

        if (!check_ll_dr(p, l1, l2, v))
            v = -v;


        if (!check_ateq(get_lpt1(l1, p), p, get_lpt1(l2, p), v)) {
            int k = 0;
        }

        anglet at = new anglet();
        at.type = 1;
        at.l1 = l1;
        at.l2 = l2;
        at.p = p;
        at.v = v;
        at.lemma = lemma;
        at.co = co_db.nx;
        last_at.nx = at;
        last_at = at;
        new_pr(CO_TANG);
        last_nd.u.at = at;
        return at;
    }

    public boolean xatcong(int a, int b, int c, int v) {
        anglet at = fo_at(a, b, c);
        return check_ll_dr(at.p, at.l1, at.l2, v);
    }

    public boolean xatcong(l_line l1, l_line l2) {
        if (l1 == l2 || xcoll_ln(l1, l2)) return true;

        return fd_at(l1, l2) != null;
    }

    public anglet fd_at(int p1, int p2, int p3) {
        return fd_at(fd_ln(p1, p2), fd_ln(p2, p3));
    }

    public anglet fo_at(int p1, int p2, int p3) {
        anglet at = all_at.nx;
        while (at != null) {
            if (on_ln(p1, p2, at.l1) && on_ln(p2, p3, at.l2) || on_ln(p1, p2, at.l2) && on_ln(p2, p3, at.l1)) return at;
            at = at.nx;
        }
        return null;
    }

    public anglet fd_at(l_line l1, l_line l2) {
        if (l1 == null || l2 == null) return null;

        if (l1 == l2 || xcoll_ln(l1, l2)) return null;

        anglet at = all_at.nx;
        while (at != null) {
            if (at.l1 == l1 && at.l2 == l2 || at.l2 == l1 && at.l1 == l2) return at;
            at = at.nx;
        }
        return null;
    }

    public boolean check_ll_dr(int p, l_line l1, l_line l2, int d) {
        double r = getAngleValue(p, l1, l2);
        double x = Math.abs(r * A_180 / Math.PI - d);
        return x < ZERO || Math.abs(x - A_180) < ZERO || Math.abs(x + A_180) < ZERO;
    }

    public int check_ll_type(int a, int b, int c, int d) {
        double r = this.getAngleValue(a, b, c);
        double x = Math.abs(r * A_180 / Math.PI - d);
        if (x < ZERO) return 0;
        if (Math.abs(x - A_180) < ZERO) return 1;
        return -1;
    }

    public boolean check_at_eq(int t1, int t2) {
        return t1 == t2 || Math.abs(Math.abs(t1 - t2) - A_180) < ZERO;
    }

    ////////////////////////////////////////////////////
    // polygon
    public void add_polygon(int t, int p[]) {
        polygon pg = new polygon(t);
        for (int i = 0; i < p.length && p[i] != 0; i++)
            pg.p[i] = p[i];
        add_pg(pg);
    }

    public polygon fd_trix(int t, int a, int b, int c) {
        polygon pg = all_pg.nx;
        while (pg != null) {
            if (pg.qtype == t) {
                if (pg.p[0] == a && (pg.p[1] == b && pg.p[2] == c || pg.p[1] == c && pg.p[2] == b)) return pg;
                if (pg.p[0] == b && (pg.p[1] == a && pg.p[2] == c || pg.p[1] == c && pg.p[2] == a)) return pg;
                if (pg.p[0] == c && (pg.p[1] == b && pg.p[2] == a || pg.p[1] == a && pg.p[2] == b)) return pg;
            }
            pg = pg.nx;
        }
        return null;
    }

    public void add_pg(polygon pg) {
        last_pg.nx = pg;
        last_pg = pg;
        this.new_pr(pg.qtype);
        last_nd.u.pg = pg;
    }

    /////////////////////////////////////////////////////////////////////

    public angtn fo_atn(l_line l1, l_line l2, l_line l3, l_line l4) {
        angtn atn = all_atn.nx;
        while (atn != null) {

            if (atn.ln1 == l1 && atn.ln2 == l2 && atn.ln3 == l3 && atn.ln4 == l4) return atn;
            if (atn.ln1 == l3 && atn.ln2 == l4 && atn.ln3 == l1 && atn.ln4 == l2) return atn;
            if (atn.ln1 == l2 && atn.ln2 == l1 && atn.ln3 == l4 && atn.ln4 == l3) return atn;
            if (atn.ln1 == l4 && atn.ln2 == l3 && atn.ln3 == l2 && atn.ln4 == l1) return atn;

            atn = atn.nx;
        }
        return null;
    }

    public angtn fo_atn(int a, int b, int c, int d, int a1, int b1, int c1, int d1) {
        angtn atn = all_atn.nx;
        while (atn != null) {
            if ((on_ln(a, b, atn.ln1) && on_ln(c, d, atn.ln2) && on_ln(a1, b1, atn.ln3) && on_ln(c1, d1, atn.ln4)))
                return atn;
            if (on_ln(a, b, atn.ln2) && on_ln(c, d, atn.ln1) && on_ln(a1, b1, atn.ln4) && on_ln(c1, d1, atn.ln3))
                return atn;
            if ((on_ln(a, b, atn.ln3) && on_ln(c, d, atn.ln4) && on_ln(a1, b1, atn.ln1) && on_ln(c1, d1, atn.ln2)))
                return atn;
            if ((on_ln(a, b, atn.ln4) && on_ln(c, d, atn.ln3) && on_ln(a1, b1, atn.ln2) && on_ln(c1, d1, atn.ln1)))
                return atn;
            atn = atn.nx;
        }
        return null;
    }


    public angtn add_atn(int lemma, int a, int b, int c, int a1, int b1, int c1) {
        if (fo_atn(a, b, b, c, a1, b1, b1, c1) != null) return null;

        return add_atn(lemma, fadd_ln_t(a, b), fadd_ln_t(b, c), fadd_ln_t(a1, b1), fadd_ln_t(b1, c1));
    }

    public void adj_atn(angtn atn) {
        if (atn.type == 0) return;
        l_line l1 = atn.ln1;
        l_line l2 = atn.ln2;
        l_line l3 = atn.ln3;
        l_line l4 = atn.ln4;

        if (l1.type != 0 && l2.type != 0 && l3.type != 0 && l4.type != 0)
            return;


        int p1 = inter_lls(l1, l2);
        int p2 = inter_lls(l3, l4);

        if (atn.ln1.type == 0)
            l1 = fd_ln_rl(l1, p1);
        else
            l1 = atn.ln1;
        if (atn.ln2.type == 0)
            l2 = fd_ln_rl(l2, p1);
        else
            l2 = atn.ln2;
        if (atn.ln3.type == 0)
            l3 = fd_ln_rl(l3, p2);
        else
            l3 = atn.ln3;
        if (atn.ln4.type == 0)
            l4 = fd_ln_rl(l4, p2);
        else
            l4 = atn.ln4;

        if (l1 == atn.ln1 && l2 == atn.ln2 && l3 == atn.ln3 && l4 == atn.ln4)
            return;
        angtn atn1 = add_atn(188, l1, l2, l3, l4);
        if (atn1 != null) {
            co_xy.nx = null;
            cond c = add_coxy(CO_ATNG);
            c.u.atn = atn;
            atn1.co = c;
        }
        atn.type = 0;
    }

    final void ch_ln_atn(l_line ln1, l_line ln2, angtn atn) {
        if (atn.type == 0) return;
        l_line l1 = atn.ln1;
        l_line l2 = atn.ln2;
        l_line l3 = atn.ln3;
        l_line l4 = atn.ln4;

        int p1 = atn.t1;//inter_lls(l1, l2);
        int p2 = atn.t2;//inter_lls(l3, l4);

        if (l1 == ln1)
            l1 = fd_ln_rl(l1, p1);
        else
            l1 = atn.ln1;
        if (l2 == ln1)
            l2 = fd_ln_rl(l2, p1);
        else
            l2 = atn.ln2;
        if (l3 == ln1)
            l3 = fd_ln_rl(l3, p2);
        else
            l3 = atn.ln3;
        if (l4 == ln1)
            l4 = fd_ln_rl(l4, p2);
        else
            l4 = atn.ln4;

        if (l1 == atn.ln1 && l2 == atn.ln2 && l3 == atn.ln3 && l4 == atn.ln4)
            return;
        angtn atn1 = add_atn(188, l1, l2, l3, l4);
        if (atn1 != null) {
            co_xy.nx = null;
            cond c = add_coxy(CO_ATNG);
            c.u.atn = atn;
            atn1.co = c;
        }
        atn.type = 0;
    }

    public angtn add_atn(int lemma, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (isPFull()) return null;
        if (!valid(R_AG_ATN)) return null;
        if (!valid(lemma)) return null;

        if (l1 == null || l2 == null || l3 == null || l4 == null) return null;
        if (xcoll_ln(l1, l2) || xcoll_ln(l3, l4)) return null;
        if (fo_atn(l1, l2, l3, l4) != null) return null;

        int p1 = inter_lls(l1, l2);
        int p2 = inter_lls(l3, l4);
        if (p1 == 0 || p2 == 0) return null;
        int dr = check_atn_dr(get_lpt1(l1, p1), p1, get_lpt1(l2, p1), get_lpt1(l3, p2), p2, get_lpt1(l4, p2));
        switch (dr) {
            case-1:
                return null;
            case 0:
                break;
            case 1: {
                l_line t = l1;
                l1 = l2;
                l2 = t;
                t = l3;
                l3 = l4;
                l4 = t;
            }
            break;
            case 2: {
                l_line t = l3;
                l3 = l4;
                l4 = t;
            }
            break;
            case 3: {
                l_line t = l1;
                l1 = l2;
                l2 = t;
            }
            break;
        }
        if (l1 == l3 && l2 == l4 || l1 == l2 || l3 == l4) return null;
        if (!check_atn(get_lpt1(l1, p1), p1, get_lpt1(l2, p1), get_lpt1(l3, p2), p2, get_lpt1(l4, p2))) {
            int l = 0;
        }

        if (fo_atn(l1, l2, l3, l4) != null) return null;

//        if (true)
//          return null;
        angtn atn = new angtn();
        atn.lemma = lemma;
        atn.ln1 = l1;
        atn.ln2 = l2;
        atn.ln3 = l3;
        atn.ln4 = l4;
        atn.t1 = inter_lls(l1, l2);
        atn.t2 = inter_lls(l3, l4);
        atn.type = 1;
        atn.co = co_db.nx;
        last_atn.nx = atn;
        last_atn = atn;
        new_pr(CO_ATNG);
        last_nd.u.atn = atn;
        return atn;
    }

    public int check_atn_dr(int a, int b, int c, int a1, int b1, int c1) {
        double t1 = getAngleValue(a, b, c);
        double t2 = getAngleValue(a1, b1, c1);
        double t = t1 + t2;
        if (Math.abs(t - Math.PI / 2) < ZERO) return 0; //positive
        if (Math.abs(t + Math.PI / 2) < ZERO) return 1; //negtive
        if (Math.abs(t1 - t2 - Math.PI / 2) < ZERO) return 2; //negtive
        if (Math.abs(t1 - t2 + Math.PI / 2) < ZERO) return 3; //negtive
        return -1; // false
    }


    /////////////////////////////////////////////////////////////////
    public int getAtv(int a, int b, int c, anglet at) {
        int v = at.get_val(a, c);
        return getAtv(a, b, c, v);
    }

    public int getAtv(int a, int b, int c, int v) {
        double r = getAngleValue(a, b, c) * A_180 / Math.PI;
        if (v > A_180)
            v -= A_360;
        if (v <= -A_180)
            v += A_360;
        double t = r - v;
        if (Math.abs(t) < ZERO) return v;
        if (Math.abs(t - A_180) < ZERO) return A_180 + v;
        if (Math.abs(t + A_180) < ZERO) return -A_180 + v;
        return 0;
    }

    public boolean search_only_exists_ln() {
        return (!R_SEARCH_ALL_LN);// && depth != 0);
    }

    public boolean search_only_exists_ln(int a, int b) {
        return (!R_SEARCH_ALL_LN) && fd_ln(a, b) == null;
    }

    public boolean search_only_exists_ln(int a, int b, int c, int d) {
        return (!R_SEARCH_ALL_LN) && (fd_ln(a, b) == null || fd_ln(c, d) == null);
    }

    public boolean search_only_exists_ln(int a, int b, int c, int d, int e, int f) {
        return (!R_SEARCH_ALL_LN) && (fd_ln(a, b) == null || fd_ln(c, d) == null || fd_ln(e, f) == null);
    }

    public boolean search_only_exists_ln(l_line l1, l_line l2, l_line l3, l_line l4) {
        if (!R_AG_ALL && (inter_lls(l1, l2) == 0 || inter_lls(l3, l4) == 0))
            return false;
        return true;
    }

/////////////////////////////////////////////////////////////////////////////////////////////

    public void add_nodes(int[] p) { // for conclution;

        l_list d = new l_list();
        mnde m = null;
        int n = 0;
        while (n < p.length && p[n] != 0)
            n++;
        int num = n / 3;
        for (int i = 0; i < num; i++) {
            mnde m1 = new mnde();
            int a = p[i * 3];
            int b = p[3 * i + 1];
            int c = p[3 * i + 2];
            if (a != 0 && b != 0 && c != 0) {
                m1.tr = fadd_tr(b, a, c, fadd_ln(a, b), fadd_ln(b, c));
                m1.t = 1;
                m1.type = l_list.ANGLE;
                d.add_md(m1);
            }
        }
        if (n == num * 3 + 1) {
            m = new mnde();
            m.type = l_list.ANGLE;
            m.tr = null;
            m.t = p[n - 1];
            d.add_mf(m);
        }
        d.type = l_list.ANGLE;
        last_ns.nx = d;
        last_ns = d;
    }

    public l_list cp_nodes(l_list ns) {
        l_list d = new l_list();
        d.cp(ns);
        return d;
    }

    public void add_nodes(l_list d) {
        if (d == null) return;
        if (x_list(d)) return;

        last_ns.nx = d;
        last_ns = d;
    }

    public boolean x_list(l_list ls) {
        if (ls.nd == 0 && ls.nf == 0) return false;

        l_list ls1 = all_ns.nx;
        while (ls1 != null) {
            if (eq_list(ls1, ls)) return true;
            ls1 = ls1.nx;
        }
        return false;
    }

    public boolean eq_list(l_list ls, l_list ls1) {
        if (ls.nd != ls1.nd) return false;
        for (int i = 0; i < ls.nd; i++) {
            if (!eq_mnde(ls.md[i], ls1.md[i])) return false;
        }

        if (ls.nf != ls1.nf) return false;

        for (int i = 0; i < ls.nf; i++) {
            if (!eq_mnde(ls.mf[i], ls1.mf[i])) return false;
        }
        return true;
    }

    public boolean eq_mnde(mnde m1, mnde m2) {
        if (m1.t != m2.t) return false;
        if (m1.tr == null && m2.tr == null) return true;
        if (m1.tr == null && m2.tr != null || m1.tr != null && m2.tr == null) return false;

        if (m1.tr.l1 == m2.tr.l1 && m1.tr.l2 == m2.tr.l2) return true;
        return false;
    }
    /////////////////////////////////////////////////////////


    public angtr fadd_tr(int v, int t1, int t2, l_line l1, l_line l2) {
        angtr tr = fd_tr(v, l1, l2);
        if (tr != null) return tr;

        angtr t = add_tr(v, l1, l2);
        t.t1 = t1;
        t.t2 = t2;
        return t;
    }

    public angtr add_tr(int v, l_line l1, l_line l2) {
        angtr tr = new angtr();
        tr.v = v;
        tr.l1 = l1;
        tr.l2 = l2;
        last_tr.nx = tr;
        last_tr = tr;
        return tr;
    }

    public angtr fd_tr(int v, l_line l1, l_line l2) {
        angtr tr = all_tr.nx;
        while (tr != null) {
            if (tr.l1 == l1 && tr.l2 == l2) return tr;
            tr = tr.nx;
        }
        return null;
    }

}