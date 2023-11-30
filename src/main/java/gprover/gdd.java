/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-14
 * Time: 21:37:28
 * To change this template use File | Settings | File Templates.
 */
package gprover;


public class gdd extends gddbase {

    void fixpoint() {
        d_base = 0;
        test_ra = null;

        while (all_nd.nx != null) {
            while (all_nd.nx != null) {
                cond nd = all_nd.nx;
                all_nd.nx = nd.nx;
                co_db.nx = null;

                if (!ck_value)  // Numerical Check Error. Must be somewhere wrong.
                    return;

                if (nd.dep == depth)
                    depth++;

                switch (nd.pred) {
                    case CO_COLL:
                        search_ln(nd.u.ln);
                        break;
                    case CO_PARA:
                        search_pn(nd.u.pn);
                        break;
                    case CO_PERP:
                        search_tn(nd.u.tn);
                        break;
                    case CO_CONG:
                        search_cg(nd.u.cg);
                        break;
                    case CO_ACONG:
                        search_as(nd.u.as);
                        break;
                    case CO_TANG:
                        search_at(nd.u.at);
                        break;
                    case CO_MIDP:
                        search_md(nd.u.md);
                        break;
                    case CO_PROD:
                        search_ra(nd.u.ra);
                        break;
                    case CO_CYCLIC:
                        search_cr(nd.u.cr);
                        break;
                    case CO_STRI:
                        search_st(nd.u.st);
                        break;
                    case CO_CTRI:
                        search_ct(nd.u.st);
                        break;
                    case CO_ATNG:
                        search_atn(nd.u.atn);
                        break;
                    case CO_NANG:
                        search_ns(nd.u.ns);
                        break;
                    default:
                        Cm.print("forward: wrong type of predicates");
                        break;
                }
                if ((nd.u.get_type()) != 0)
                    nd.u.set_type(1);
            }

            test_ra = (test_ra == null) ? all_ra.nx : test_ra.nx;
            adj_ra(test_ra);
            search_ra_cg();
            search_as_pt();
        }
        depth = 0;
        collect_angst();
        collect_sts();
        return;
    }

/* Searching */

    final void search_pn(p_line pn) {

        if (pn == null) return;
        if (pn.type == 0 || pn.no <= 0) return;
        co_db.nx = null;
        search_pn_coll(pn);
        search_pn_pn(pn);
        search_pn_tns(pn);
        search_pn_mds(pn);
        search_pn_cr(pn);

        for (int i = 0; i <= pn.no; i++) {
            l_line ln1 = pn.ln[i];
            for (int j = i + 1; j <= pn.no; j++) {
                l_line ln2 = pn.ln[j];
                search_pn_1(ln1, ln2);
            }
        }
        adj_pn(pn);
    }

    final void search_pn_coll(p_line pn) {
        l_line ln1, ln2;
        int i, j, p1, p2, p3;
        if (pn.type == 0 || pn.no <= 0) return;
        co_db.nx = null;

        for (i = 0; i <= pn.no; i++) {
            ln1 = pn.ln[i];
            for (j = i + 1; j <= pn.no; j++) {
                ln2 = pn.ln[j];
                if (sub_ln(ln1, ln2)) {
                    ch_ln(ln1, ln2);
                    ln1.type = 0;
                    return;
                }
                if (sub_ln(ln2, ln1)) {
                    ch_ln(ln2, ln1);
                    ln2.type = 0;
                    return;
                }
                if ((p1 = inter_ll(ln1, ln2)) != 0) {
                    p2 = get_lpt1(ln1, p1);
                    p3 = get_lpt2(ln2, p1, p2);
                    if (p2 != 0 && p3 != 0) {
                        l_line ln = add_ln2l(ln1, ln2);
                        if (ln != null) { // shall never be null;
                            ch_ln(ln1, ln);
                            ch_ln(ln2, ln);
                            ln1.type = 0;
                            ln2.type = 0;
                            ch_lns(ln);

                            ln.lemma = R_P_COLL;
                            cond c = add_coxy(CO_COLL);
                            c.u.ln = ln1;
                            c = add_coxy(CO_COLL);
                            c.u.ln = ln2;
                            ln.co = c;
                        }
                    }
                    return;
                }
            }
        }
    }


    final void search_pn_mds(p_line pn) {
        midpt md = all_md.nx;

        while (md != null) {
            if (ch_dep(md.dep)) {
                search_pn_md(pn, md);
            }
            md = md.nx;
        }
    }

    final void search_pn_md(p_line pn, midpt md) {

        add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);

        for (int i = 0; i <= pn.no; i++) {
            l_line ln1 = pn.ln[i];
            for (int j = i + 1; j <= pn.no; j++) {
                l_line ln2 = pn.ln[j];
                if (ln1.type != 0 && ln2.type != 0) {
                    lm_md_connection(md, ln1, ln2);
                    lm_parallelogram(md, ln1, ln2);
                }
            }
        }
        pop_codb();

        if (!valid(R_MID_CONNECTION_TRAPZOID))
            return;

        l_line l1, l2, l3;

        l1 = l2 = l3 = null;
        for (int i = 0; i <= pn.no; i++) {
            if (pn.ln[i].type != 0) {
                if (on_ln(md.a, pn.ln[i]))
                    l1 = pn.ln[i];
                if (on_ln(md.b, pn.ln[i]))
                    l2 = pn.ln[i];
                if (on_ln(md.m, pn.ln[i]))
                    l3 = pn.ln[i];
            }
        }
        if (l1 == null || l2 == null || l3 == null) return;
        if (l1 == l2 || l1 == l3 || l2 == l3) return;

        for (int i = 0; i <= l1.no; i++)
            for (int j = 0; j <= l2.no; j++) {
                if (l1.pt[i] != md.a && l2.pt[j] != md.b) {
                    l_line ln = fd_ln(l1.pt[i], l2.pt[j]);
                    int p;
                    if (ln != null && (p = inter_ll(ln, l3)) != 0) {
                        add_codb(CO_PARA, md.a, l1.pt[i], md.b, l2.pt[j], 0, 0, 0, 0);
                        add_codb(CO_PARA, md.a, l1.pt[i], md.m, p, 0, 0, 0, 0);
                        add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
                        add_mid(R_MID_CONNECTION_TRAPZOID, p, l1.pt[i], l2.pt[j]);
                        pop_codb();
                        pop_codb();
                        pop_codb();
                    }
                }
            }
    }

    final void search_pn_1(l_line ln1, l_line ln2) {
        if (check_coll(ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1])) return;

        for (int k1 = 0; k1 <= ln1.no; k1++)
            for (int k2 = 0; k2 <= ln2.no; k2++) {
                l_line ln3 = fadd_ln(ln1.pt[k1], ln2.pt[k2]);
                if (ln3 != null) {
                    add_codb(CO_PARA, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                    add_ea_ln(R_PL_AS, ln3, ln1, ln3, ln2);
                    pop_codb();
                }
                for (int t1 = k1 + 1; t1 <= ln1.no; t1++)
                    for (int t2 = k2 + 1; t2 <= ln2.no; t2++)
                        lm_RATIO(R_P_RA, ln1.pt[k1], ln1.pt[t1], ln2.pt[k2], ln2.pt[t2]);

            }

    }

    final void search_pn_md(p_line pn1, l_line ln1, l_line ln2) {
        midpt md = all_md.nx;
        if (md == null) return;

        add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
        for (; md != null; md = md.nx) {
            if (!ch_dep(md.dep)) break;
            lm_md_connection(md, ln1, ln2);
            lm_parallelogram(md, ln1, ln2);
        }
        pop_codb();
    }

    final void search_pn_tns(p_line pn) {
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (!ch_dep(tn.dep)) break;
            if (ch_it(tn.type))
                search_pn_tn(pn, tn);
            tn = tn.nx;
        }
    }

    public void search_pn_cr(p_line pn) {
        l_line l1, l2;
        a_cir cr;
        int p1, p2, p3, p4;

        cr = all_cir.nx;
        while (cr != null) {
            if (cr.type != 0) {
                for (int i = 0; i <= pn.no; i++) {
                    l1 = pn.ln[i];
                    p1 = inter_lc(l1, cr);
                    p2 = inter_lc1(l1, cr, p1);
                    if (p1 != 0 && p2 != 0)
                        for (int j = i + 1; j <= pn.no; j++) {
                            l2 = pn.ln[j];
                            p3 = inter_lc(l2, cr);
                            p4 = inter_lc1(l2, cr, p3);
                            if (p3 != 0 && p4 != 0)
                                add_cr_pn_as(cr, p1, p2, p3, p4);
                        }
                }
            }
            cr = cr.nx;
        }

    }

    final void search_tn(t_line tn) {

        co_db.nx = null;
        l_line ln1 = tn.l1;
        l_line ln2 = tn.l2;
        int p1 = inter_ll(ln1, ln2);
        if (p1 != 0) {
            search_tn_at(tn);
            search_tn_mds(tn);

            search_tn_st(p1, ln1, ln2, tn);
            search_tn_st(p1, ln2, ln1, tn);

            search_tn_iso(tn);
            search_tn_crs(ln1, ln2, p1, tn);
        }
        search_tn_atn(tn);
        search_tn_pns(tn);
        search_tn_tn(tn, ln1, ln2, p1);
        search_tn0(tn);
    }

    final void search_tn0(t_line tn) {
        l_line ln1, ln2;
        if (tn.type == 0) return;
        ln1 = tn.l1;
        ln2 = tn.l2;
        if (tn.l1.type == 0)
            ln1 = fd_lnl(tn.l1);
        if (tn.l2.type == 0)
            ln2 = fd_lnl(tn.l2);
        if (ln1 == tn.l1 && ln2 == tn.l2) return;

        t_line tn1 = add_tx(401, ln1, ln2);
        if (tn1 != null) {
            co_xy.nx = null;
            cond c = add_coxy(CO_PERP);
            c.u.tn = tn;
            tn1.co = c;
        }
    }

    final void search_tn_iso(t_line tn) {
        int p = inter_lls(tn.l1, tn.l2);
        if (p == 0) return;
        search_tn_iso1(p, tn.l1, tn.l2);
        search_tn_iso1(p, tn.l2, tn.l1);
    }

    final void search_tn_iso1(int p, l_line l1, l_line l2) {
        int p1, p2, p3;
        for (int i = 0; i <= l1.no; i++)
            for (int j = i + 1; j <= l1.no; j++) {
                p1 = l1.pt[i];
                p2 = l1.pt[j];
                if (p1 != p && p2 != p) {
                    if (xmid(p, p1, p2)) {
                        for (int k = 0; k <= l2.no; k++) {
                            if (l2.pt[k] != p) {
                                add_codb(CO_PERP, l2.pt[k], p, p1, p2, 0, 0, 0, 0);
                                add_codb(CO_MIDP, p, p1, p2, 0, 0, 0, 0, 0);
                                add_iso_3lines_concur(0, l2.pt[k], p1, p2, p);
                                pop_codb();
                                pop_codb();
                            }
                        }

                    } else {
                        for (int k = 0; k <= l2.no; k++) {
                            p3 = l2.pt[k];
                            if (p3 != p) {
                                if (xcong(p3, p1, p3, p2)) {
                                    add_codb(CO_PERP, p3, p, p1, p2, 0, 0, 0, 0);
                                    add_codb(CO_CONG, p3, p1, p3, p2, 0, 0, 0, 0);
                                    add_iso_3lines_concur(0, p3, p1, p2, p);
                                    pop_codb();
                                    pop_codb();
                                } else if (xacong(p1, p3, p, p, p3, p2)) {
                                    add_codb(CO_PERP, p3, p, p1, p2, 0, 0, 0, 0);
                                    add_codb(CO_ACONG, p1, p3, p3, p, p, p3, p3, p2);
                                    add_iso_3lines_concur(0, p3, p1, p2, p);
                                    pop_codb();
                                    pop_codb();
                                }
                            }
                        }
                    }
                }
            }

    }

    final void search_tn_crs(l_line ln1, l_line ln2, int m, t_line tn) {
        int p2, p3, m2, m3;
        a_cir cr = all_cir.nx;

        while (cr != null) {
            if (!ch_dep(cr.dep)) break;

            if (!ch_it(cr.type) || cr.o == 0) {
                cr = cr.nx;
                continue;
            }
            /* tangent */
            if (m != 0 && on_cir(m, cr))
                search_cr_tan(cr, tn);

            /* square */
            if (cr.o == m &&
                    (p2 = inter_lc(ln1, cr)) != 0 && (p3 = inter_lc(ln2, cr)) != 0 &&
                    (m2 = fd_pt_md(m, p2)) != 0 && (m3 = fd_pt_md(m, p3)) != 0) {
                add_type3(m, m2, p2, m3, p3);
            }

            cr = cr.nx;
        }
    }


    final private void search_tn_dim(t_line tn, int m, a_cir cr) {
        if (m == 0 || !on_cir(m, cr)) return;
        l_line l1 = tn.l1;
        l_line l2 = tn.l2;
        int p1 = inter_lc1(l1, cr, m);
        if (p1 == 0) return;
        int p2 = inter_lc1(l2, cr, m);
        if (p2 == 0) return;

    }

    final void search_tn_mds(t_line tn) {
        for (midpt md = all_md.nx; md != null && ch_dep(md.dep); md = md.nx)
            search_tn_md(tn, md);
    }

    final void search_md_tns(midpt md) {
        for (t_line tn = all_tn.nx; tn != null && tn.nx != null && ch_dep(tn.dep); tn = tn.nx)
            if (tn.type != 0)
                search_tn_md(tn, md);
    }

    final void search_tn_md(t_line tn, midpt md) {
        l_line ln1 = tn.l1;
        l_line ln2 = tn.l2;
        int m = inter_lls(ln1, ln2);
        if (m == 0) return;

        if (md.a != m && md.b != m) {
            if (on_ln(md.a, ln2)) {
                l_line ln = ln1;
                ln1 = ln2;
                ln2 = ln;
            }
            int lm = R_RTRI_MD_CY;
            if (on_ln(md.a, ln1) && on_ln(md.b, ln2)) {
                add_codb(CO_PERP, m, md.a, m, md.b, 0, 0, 0, 0);
                add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
                add_cir3(lm, md.m, m, md.a, md.b);
                add_cong(lm, 0, md.a, md.m, md.m, m);
                add_cong(lm, 0, md.b, md.m, md.m, m);
                pop_codb();
                pop_codb();
            }
        }
    }

    final void search_tn_pns(t_line tn) {
        p_line pn;
        pn = all_pn.nx;
        while (pn != null && ch_dep(pn.dep)) {
            if (ch_it(pn.type))
                search_pn_tn(pn, tn);
            pn = pn.nx;
        }
    }

    final void serach_tn2_as(t_line tn1, t_line tn2) {
        angles as = all_as.nx;
        if (tn1.type == 0) return;
        while (as != null) {
            if (as != null)
                if (as.type != 0)
                    search_as_tn1(tn1, tn2, as);
            as = as.nx;
        }
    }

    final void search_tn_tn(t_line tn, l_line ln1, l_line ln2, int p1) {
        t_line tn1;
        p_line pn;
        l_line ln3, ln4;
        int p2, p3, p4;


        for (tn1 = all_tn.nx; tn1 != null && ch_dep(tn1.dep); tn1 = tn1.nx) {
            if (tn1 == tn || tn1.type == 0) continue;
            ln3 = tn1.l1;
            ln4 = tn1.l2;

            serach_tn2_as(tn, tn1);
            if (ln1 == ln3) {
                pn = add_px(R_TT_PP, ln2, ln4);
            } else if (ln1 == ln4) {
                pn = add_px(R_TT_PP, ln2, ln3);
            } else if (ln2 == ln3) {
                pn = add_px(R_TT_PP, ln1, ln4);
            } else if (ln2 == ln4) {
                pn = add_px(R_TT_PP, ln1, ln3);
            } else
                pn = null;
            if (pn != null) {
                add_codb(CO_PERP, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                add_codb(CO_PERP, ln3.pt[0], ln3.pt[1], ln4.pt[0], ln4.pt[1], 0, 0, 0, 0);
                pn.co = co_db.nx;
                pop_codb();
                pop_codb();
            }
            /* */
            p2 = inter_ll(ln3, ln4);
            p3 = inter_ll(ln1, ln3);
            p4 = inter_ll(ln2, ln4);
            search_as_tn_as(p1, p2, ln1, ln2, ln3, ln4);

            if (p1 == p2 && p1 != 0 && !isPFull()) {
                adj_as0(p1, ln1, ln2, ln3, ln4);
                adj_as0(p1, ln1, ln2, ln4, ln3);
            }
            if (fd_pt_md(p3, p4) == 0) {
                if (p1 != 0 && p2 != 0 && p3 != 0 && p4 != 0 &&
                        p1 != p2 && p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
                    add_codb(CO_PERP, p1, p3, p1, p4, 0, 0, 0, 0);
                    add_codb(CO_PERP, p2, p3, p2, p4, 0, 0, 0, 0);
                    add_cir4(R_TT_CY, 0, p1, p2, p3, p4);
                    pop_codb();
                    pop_codb();
                } else if (isPFull()) {
                    add_codb(CO_PERP, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                    add_codb(CO_PERP, ln3.pt[0], ln3.pt[1], ln4.pt[0], ln4.pt[1], 0, 0, 0, 0);
                    add_ea_ln(0, ln1, ln3, ln2, ln4);
                    pop_codb();
                    pop_codb();
                }
            }
            p3 = inter_ll(ln1, ln4);
            p4 = inter_ll(ln2, ln3);
            if (fd_pt_md(p3, p4) == 0) {
                if (p1 != 0 && p2 != 0 && p3 != 0 && p4 != 0 &&
                        p1 != p2 && p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
                    add_codb(CO_PERP, p1, p3, p1, p4, 0, 0, 0, 0);
                    add_codb(CO_PERP, p2, p3, p2, p4, 0, 0, 0, 0);
                    add_cir4(R_TT_CY, 0, p1, p2, p3, p4);
                    pop_codb();
                    pop_codb();
                } else if (isPFull()) {
                    add_codb(CO_PERP, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                    add_codb(CO_PERP, ln3.pt[0], ln3.pt[1], ln4.pt[0], ln4.pt[1], 0, 0, 0, 0);
                    add_ea_ln(0, ln1, ln4, ln2, ln3);
                    pop_codb();
                    pop_codb();
                }
            }
        }
    }

    final void search_cr(a_cir cr1) {
        int p1, p2, p3, p4, i, j, k;
        co_db.nx = null;
        if (cr1 == null) return;


        for (i = 0; i <= cr1.no; i++) {
            p1 = cr1.pt[i];
            for (j = i + 1; j <= cr1.no; j++) {
                p2 = cr1.pt[j];
                if (cr1.o != 0) {
                    /*  diamater  */
                    if (ycoll(cr1.o, p1, p2)) {
                        add_codb(CO_CYCLIC, cr1.o, p1, p2, 0, 0, 0, 0, 0);
                        add_mid(R_CR_DM_MD, cr1.o, p1, p2);
                        pop_codb();
                        for (k = 0; k <= cr1.no; k++) {
                            p3 = cr1.pt[k];
                            if (p3 != p1 && p3 != p2) {
                                add_codb(CO_CYCLIC, cr1.o, p1, p2, p3, 0, 0, 0, 0);
                                add_tline_t(R_CR_DM_T, p1, p3, p2, p3);
                                pop_codb();
                            }
                        }
                        //pop_codb();
                    } else {
                        search_cr_iso(cr1, p1, p2);
                    }
                }
                search_cr_pn(cr1, p1, p2);
                /* inscribed angle */
                if (p1 != p2 && cr1.no > 2 && valid(R_CR_INSCRIBE_AS)) {
                    for (int r = 0; r <= cr1.no; r++) {
                        p3 = cr1.pt[r];
                        if (p3 != p1 && p3 != p2)
                            for (k = 0; k <= cr1.no; k++) {
                                p4 = cr1.pt[k];
                                if (p4 != p1 && p4 != p2 && p4 != p3) {
                                    add_codb(CO_CYCLIC, 0, p1, p2, p3, p4, 0, 0, 0);
                                    add_ea_pt_t(R_CR_INSCRIBE_AS, p1, p3, p2, p1, p4, p2);
                                    pop_codb();
                                }
                            }
                    }
                }
            }
        }
        t_line tn = all_tn.nx;
        while (tn != null && ch_dep(tn.dep)) {
            if (tn.type != 0)
                search_cr_tan(cr1, tn);
            tn = tn.nx;
        }

        search_cr_md(cr1);
        search_cr_cr(cr1);
        search_cr_cg(cr1);
        adj_cir1(cr1);
    }

    final void search_cr_md(a_cir cr) {
        midpt md = all_md.nx;
        if (cr.pt[1] == 0) return;

        while (md != null) {
            if (cr.o == md.m) {
                if (on_cir(md.a, cr) && !on_cir(md.b, cr)) {
                    add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, cr.o, cr.pt[0], cr.pt[1], 0, 0, 0, 0, 0);
                    this.add_cir3(0, cr.o, cr.pt[0], cr.pt[1], md.b);
                    pop_codb();
                    pop_codb();

                } else if (on_cir(md.b, cr) && !on_cir(md.a, cr)) {
                    add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
                    add_codb(CO_CYCLIC, cr.o, cr.pt[0], cr.pt[1], 0, 0, 0, 0, 0);
                    this.add_cir3(0, cr.o, cr.pt[0], cr.pt[1], md.a);
                    pop_codb();
                    pop_codb();

                }
            }
            md = md.nx;
        }
    }

    final void search_cr_pn(a_cir cr1, int p1, int p2) {
        int i, p3, p4;
        l_line l1, l2;
        p_line pn;
        l1 = fd_ln(p1, p2);
        if (l1 == null) return;
        pn = fd_pnl(l1);
        if (pn == null || pn.no == 0) return;
        for (i = 0; i <= pn.no; i++) {
            l2 = pn.ln[i];
            if (l2 != l1 && (p3 = inter_lc(l2, cr1)) != 0 && p3 != p1 && p3 != p2 &&
                    (p4 = inter_lc1(l2, cr1, p3)) != 0 && p4 != p1 && p4 != p2) {
                add_cr_pn_as(cr1, p1, p2, p3, p4);
            }
        }
    }


    final void search_cr_tan(a_cir cr1, t_line tn) {
        if (cr1.o == 0 || cr1.no < 1)
            return;

        l_line l1 = tn.l1;
        l_line l2 = tn.l2;
        int p = inter_lls(l1, l2);
        if (p == 0 || !on_cir(p, cr1)) return;

        if (on_ln(cr1.o, l1)) {
            l_line ln = l1;
            l1 = l2;
            l2 = ln;
        }
        if (!on_ln(cr1.o, l2)) return;

        l_line[] ls = split_ln(p, l1);
        int p1, p2, p3;
        for (int i = 0; i < ls.length; i++) {
            l1 = ls[i];
            p1 = get_lpt1(l1, p);
            for (int j = 0; j <= cr1.no; j++) {
                p2 = cr1.pt[j];
                if (p2 != p) {
                    for (int k = 0; k <= cr1.no; k++) {
                        p3 = cr1.pt[k];
                        if (p3 != p && p3 != p) {
                            if (check_eqangle_t(p1, p, p, p2, p, p3, p3, p2)) {
                                add_codb(CO_PERP, p1, p, p, cr1.o, 0, 0, 0, 0);
                                add_codb(CO_CYCLIC, cr1.o, p, p2, p3, 0, 0, 0, 0);
                                add_ea_ln(R_CR_TAN_AS, l1, fadd_ln_t(p, p2), fadd_ln_t(p, p3), fadd_ln_t(p2, p3));
                                pop_codb();
                                pop_codb();
                            }
                        }
                    }
                }
            }

        }
    }

    final void search_cr_cr(a_cir cr1) {
        a_cir cr2;
        int p1, p2;
        cr2 = all_cir.nx;

        while (cr2 != null) {
            if (cr2 == cr1 || cr1.o == 0 || cr2.o == 0 || cr1.o == cr2.o ||
                    !ch_it(cr2.type) || sub_cir(cr1, cr2) || sub_cir(cr2, cr1)) {
                cr2 = cr2.nx;
                continue;
            }

            p1 = inter_cc(cr1, cr2);
            if (p1 == 0) {
                cr2 = cr2.nx;
                continue;
            }

            p2 = inter_cc1(cr1, cr2, p1);
            if (p2 == 0) {
                cr2 = cr2.nx;
                continue;
            }

            add_codb(CO_CYCLIC, cr1.o, p1, p2, 0, 0, 0, 0, 0);
            add_codb(CO_CYCLIC, cr2.o, p1, p2, 0, 0, 0, 0, 0);
            add_tline(R_CR_OO_B, p1, p2, cr1.o, cr2.o);
            int m = inter_ll(fd_ln(cr1.o, cr2.o), fd_ln(p1, p2));
            if (m != 0)
                add_mid(R_CR_OO_B, m, p1, p2);
            pop_codb();
            pop_codb();

            cr2 = cr2.nx;
        }
    }

    final void search_cr_iso(a_cir cr1, int p1, int p2) {
        int p3 = fd_pt_md(p1, p2);
        if (p3 != 0) {
            midpt m = fo_md(p1, p2);
            if (m != null)
                search_cr_md(cr1, m);
        }
    }

    final void search_cr_md(a_cir cr, midpt md) {
        int o = cr.o;
        if (o == 0) return;

        int p1 = md.a;
        int p2 = md.b;
        int m = md.m;
        if (!on_cir(p1, cr) || !on_cir(p2, cr)) return;

        for (int i = 0; i <= cr.no; i++) {
            if (cr.pt[i] != p1 && cr.pt[i] != p2 && m != o) {
                add_codb(CO_CYCLIC, o, p1, p2, cr.pt[i], 0, 0, 0, 0);
                add_codb(CO_MIDP, m, p1, p2, 0, 0, 0, 0, 0);
                add_ea_pt_t(R_CR_AS2, p1, o, m, p1, cr.pt[i], p2);
                add_ea_pt_t(R_CR_AS2, m, o, p2, p1, cr.pt[i], p2);
                pop_codb();
                pop_codb();
            }
        }
    }

    final void search_cr_cg(a_cir cr1) {
        if (cr1.o != 0) {
            for (int i = 0; i <= cr1.no; i++)
                for (int j = i + 1; j <= cr1.no; j++) {
                    if (!xcong(cr1.o, cr1.pt[i], cr1.o, cr1.pt[j])) {
                        add_codb(CO_CYCLIC, cr1.o, cr1.pt[i], cr1.pt[j], 0, 0, 0, 0, 0);
                        add_cong(0, 0, cr1.o, cr1.pt[i], cr1.o, cr1.pt[j]);
                        pop_codb();
                    }
                }
        }
    }


    final void search_md(midpt md) {

        co_db.nx = null;

        search_md_cong(md);
        search_md_tns(md);

        add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);

        search_md_mds(md);
        search_md_pns(md);
        add_cong(0, 0, md.m, md.a, md.m, md.b);
        add_cong(0, 0, md.a, md.m, md.a, md.b, 1, 4);
        add_cong(0, 0, md.b, md.m, md.a, md.b, 1, 4);


        for (int p1 = 1; p1 <= cons_no; p1++) {

            if (mcoll(p1, md.a, md.b)) continue;

            if (xperp(p1, md.a, p1, md.b) && !xcir3(md.m, md.a, md.b, p1)) {
                add_codb(CO_PERP, p1, md.a, p1, md.b, 0, 0, 0, 0);
                add_cir3(R_TT_MDCY, md.m, md.a, md.b, p1);
                pop_codb();
            }

            if (xcong(p1, md.a, p1, md.b)) {  // ISO
                add_codb(CO_CONG, p1, md.a, p1, md.b, 0, 0, 0, 0);
                add_iso_3lines_concur(0, p1, md.a, md.b, md.m);
                pop_codb();
            } else if (xperp(p1, md.m, md.a, md.b)) {
                add_codb(CO_PERP, p1, md.m, md.a, md.b, 0, 0, 0, 0);
                add_iso_3lines_concur(0, p1, md.a, md.b, md.m);
                pop_codb();
            } else if (xacong(md.a, p1, md.m, md.m, p1, md.b)) {
                add_codb(CO_ACONG, md.a, p1, p1, md.m, md.m, p1, p1, md.b);
                add_iso_3lines_concur(0, p1, md.a, md.b, md.m);
                pop_codb();
            }
        }
        pop_codb();
        a_cir cr = all_cir.nx;
        while (cr != null) {
            if (cr.type != 0)
                search_cr_md(cr, md);
            cr = cr.nx;
        }
    }

    final void search_md_mds(midpt md) {

        midpt md1;
        l_line ln = fd_ln(md.m, md.a);
        md1 = all_md.nx;
        while (md1 != null && ch_dep(md1.dep)) {
            if (md1 != md && !(on_ln(md1.a, ln) && on_ln(md1.b, ln))) {

                if (valid(R_MID_CONNECTION)) {
                    add_codb(CO_MIDP, md1.m, md1.a, md1.b, 0, 0, 0, 0, 0);
                    if (md.m != md1.m) {
                        int p, q;
                        p = q = 0;
                        if (md.a == md1.a) {
                            p = md.b;
                            q = md1.b;
                        } else if (md.a == md1.b) {
                            p = md.b;
                            q = md1.a;
                        } else if (md.b == md1.a) {
                            p = md.a;
                            q = md1.b;
                        } else if (md.b == md1.b) {
                            p = md.a;
                            q = md1.a;
                        }
                        if (p != 0 && q != 0) {
                            add_pline1(R_MID_CONNECTION, md.m, md1.m, p, q);
                            add_cong(R_MID_CONNECTION, 0, md.m, md1.m, p, q, 1, 4);
                        }
                    }
                    if (md.m == md1.m) {
                        add_pline1(R_MID_CONNECTION, md.a, md1.a, md.b, md1.b);
                        add_pline1(R_MID_CONNECTION, md.a, md1.b, md.b, md1.a);
                    }
                    pop_codb();
                }
                if (valid(R_MID_CONNECTION_TRAPZOID)) {
                    if (md1 != md) {
                        if (xpara(md.a, md1.a, md.b, md1.b) && md.m != md1.m &&
                                md.a != md1.a && md.b != md1.b && md.a != md1.b && md.b != md1.a) {
                            add_codb(CO_PARA, md.a, md1.a, md.b, md1.b, 0, 0, 0, 0);
                            add_codb(CO_MIDP, md1.m, md1.a, md1.b, 0, 0, 0, 0, 0);
                            add_pline1(R_MID_CONNECTION_TRAPZOID, md.a, md1.a, md.m, md1.m);
                            add_pline1(R_MID_CONNECTION_TRAPZOID, md.b, md1.b, md.m, md1.m);
                            pop_codb();
                            pop_codb();
                        } else if (xpara(md.a, md1.b, md.b, md1.a) && md.m != md1.m &&
                                md.a != md1.a && md.b != md1.b && md.a != md1.b && md.b != md1.a) {
                            add_codb(CO_PARA, md.a, md1.b, md.b, md1.a, 0, 0, 0, 0);
                            add_codb(CO_MIDP, md1.m, md1.a, md1.b, 0, 0, 0, 0, 0);
                            add_pline1(R_MID_CONNECTION_TRAPZOID, md.a, md1.b, md.m, md1.m);
                            add_pline1(R_MID_CONNECTION_TRAPZOID, md.b, md1.a, md.m, md1.m);
                            pop_codb();
                            pop_codb();
                        }

                    }
                }
            }
            md1 = md1.nx;
        }
    }

    final void search_md_pns(midpt md) {
        if (!valid(R_PARALLELOGRAM)) return;
        l_line l0 = fd_ln(md.a, md.b);

        for (p_line pn1 = all_pn.nx; pn1 != null && ch_dep(pn1.dep); pn1 = pn1.nx) {
            if (!ch_it(pn1.type) || pn1.no == 0 || on_pn(l0, pn1)) {
            } else
                search_pn_md(pn1, md);
        }
    }

    final void search_as(angles as1) {
        l_line l1 = as1.l1;
        l_line l2 = as1.l2;
        l_line l3 = as1.l3;
        l_line l4 = as1.l4;

        if (l1.type == 0 || l2.type == 0 || l3.type == 0 || l4.type == 0) {
            if (l1.type == 0)
                l1 = fd_lnl(l1);
            if (l2.type == 0)
                l2 = fd_lnl(l2);
            if (l3.type == 0)
                l3 = fd_lnl(l3);
            if (l4.type == 0)
                l4 = fd_lnl(l4);
            angles as = add_ea_ln(188, l1, l2, l3, l4);
            as1.type = 0;
            if (as != null) {
                co_xy.nx = null;
                cond co = add_coxy(CO_ACONG);
                co.u.as = as1;
                as.co = co;
            }
        }


        if (d_base != 1)
            adj_as(1, as1);

        if (d_base != 1)
            adj_as(0, as1);

        //***********add_as_at(as1);


        ck_as(as1, l1, l2, l3, l4);
        ck_as(as1, l4, l3, l2, l1);

        if (as1.type == 0) return;

        int p1 = inter_ll(l1, l2);
        int p2 = inter_ll(l3, l4);

        adj_as_tn(p1, p2, as1);

        if (p1 == p2 && p1 != 0 && !isPFull()) {
            adj_as0(p1, l1, l2, l3, l4);
        }

        search_as_tn_as(p1, p2, l1, l2, l3, l4);
        search_as_ct(p1, p2, l1, l2, l3, l4);
        search_as_st(p1, p2, l1, l2, l3, l4);

        if (l1 == l4) {
            l_line l = l1;
            l1 = l2;
            l2 = l;
            l = l3;
            l3 = l4;
            l4 = l;
        }

        if (l2 == l3) {
            search_as_1(p1, p2, l1, l2, l4);
            if (p1 != 0 && p2 != 0) search_as_sim(p1, p2, l1, l2, l3, l4);
        } else {
            if (p1 != 0 && p2 != 0) {
                search_as_2(p1, p2, l1, l2, l3, l4);
                lm_43(p1, p2, l1, l2, l3, l4);
                lm_43(p2, p1, l3, l4, l1, l2);
                search_as_3(p1, p2, l1, l2, l3, l4);
                search_as_3(p2, p1, l3, l4, l1, l2);
                search_as_sim(p1, p2, l1, l2, l3, l4);
            }
            if ((p1 = inter_ll(l1, l3)) != 0 && (p2 = inter_ll(l2, l4)) != 0) {
                search_as_3(p1, p2, l1, l3, l2, l4);
                search_as_3(p2, p1, l2, l4, l1, l3);
                search_as_sim(p1, p2, l1, l3, l2, l4);
            }
        }


    }

    final void search_as_0(angles as, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (ln_para(l1, l2) && l3 != l4) {
            as.type = 0;
            add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
            add_codb(CO_PARA, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], 0, 0, 0, 0);
            add_px(R_AG_PP12, l3, l4);
            pop_codb();
            pop_codb();
        } else if (ln_perp(l1, l2)) {
            as.type = 0;
            add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
            add_codb(CO_PERP, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], 0, 0, 0, 0);
            add_tline(R_AG_TT12, l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
            pop_codb();
            pop_codb();
        } else if (isPFull()) {
            if (ln_para(l1, l3) && l2 != l4) {
                add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l3.pt[0], l3.pt[1], l2.pt[0], l2.pt[1], l4.pt[0], l4.pt[1]);
                add_codb(CO_PARA, l1.pt[0], l1.pt[1], l3.pt[0], l3.pt[1], 0, 0, 0, 0);
                add_px(10, l2, l4);
                pop_codb();
                pop_codb();
            } else if (ln_perp(l1, l3)) {
                add_codb(CO_ACONG, l1.pt[0], l1.pt[1], l3.pt[0], l3.pt[1], l2.pt[0], l2.pt[1], l4.pt[0], l4.pt[1]);
                add_codb(CO_PERP, l1.pt[0], l1.pt[1], l3.pt[0], l3.pt[1], 0, 0, 0, 0);
                add_tline(11, l2.pt[0], l2.pt[1], l4.pt[0], l4.pt[1]);
                pop_codb();
                pop_codb();
            }
        }
    }

    final void search_as_1(int p1, int p2, l_line l1, l_line l2, l_line l3) {
        if (p1 == 0 || p2 == 0) return;
        int o = inter_ll(l1, l3);
        if (o == 0) return;
//        if (o != p2) return;
        if (o != p2) {
//            if (DEBUG)
//                Cm.print("o != p2, collinear angle!");//degenerate type;
            return;
        }

        /* eqangle  -. isosecles trinagle */

        if (!mcoll(o, p1, p2) && !check_coll(o, p1, p2)) {
            add_codb(CO_ACONG, o, p1, p1, p2, p1, p2, p2, o);
            add_cir2(R_ISOCELES, o, p1, p2);
            add_cong(R_ISOCELES, 0, o, p1, o, p2);
            pop_codb();
            return;
        }
        if (o != p2) {
            if (DEBUG)
                Cm.print("o != p2, collinear angle!");//degenerate type;
            return;
        }


        int p3;
        for (int i = 0; i <= l1.no; i++)                     // iso
            for (int j = 0; j <= l3.no; j++) {
                p1 = l1.pt[i];
                p3 = l3.pt[j];
                if (p1 != o && p3 != o) {
                    p2 = inter_ll(l2, fd_ln(p1, p3));
                    if (o != p2 && p2 != 0) {
                        if (xperp(p1, p3, o, p2)) {
                            add_codb(CO_ACONG, p1, o, o, p2, p2, o, o, p3);
                            add_codb(CO_PERP, p1, p3, o, p2, 0, 0, 0, 0);
                            add_iso_3lines_concur(0, o, p1, p3, p2);
                            pop_codb();
                            pop_codb();
                        } else if (xmid(p2, p1, p3) && !mcoll(o, p1, p3)) {
                            add_codb(CO_ACONG, p1, o, o, p2, p2, o, o, p3);
                            add_codb(CO_MIDP, p2, p1, p3, 0, 0, 0, 0, 0);
                            add_iso_3lines_concur(0, o, p1, p3, p2);
                            pop_codb();
                            pop_codb();
                        } else if (xcong(o, p1, o, p3)) {
                            add_codb(CO_ACONG, p1, o, o, p2, p2, o, o, p3);
                            add_codb(CO_CONG, o, p1, o, p3, 0, 0, 0, 0);
                            add_iso_3lines_concur(0, o, p1, p3, p2);
                            pop_codb();
                            pop_codb();
                        } else if (p2 != 0) {
                            add_codb(CO_ACONG, p1, o, o, p2, p2, o, o, p3);
                            add_ratio(R_AG_BISECTOR_ATIO, 0, o, p1, o, p3, p2, p1, p2, p3);
                            pop_codb();
                        }
                    }
                }
            }
    }

    final void search_as_2(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
        int o, p3, p4;
        o = 0;
        p3 = inter_ll(l1, l3);
        p4 = inter_ll(l2, l4);

        /* inscribed angle theorem */
        if (p3 != 0 && p4 != 0 &&
                p1 != p2 && p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4 &&
                !mcoll(p1, p2, p3) && !mcoll(p1, p2, p4)) {
            add_codb(CO_ACONG, p1, p3, p1, p4, p2, p3, p2, p4);
            add_cir4(R_CR_INSCRIBE_AS, 0, p1, p2, p3, p4);
            pop_codb();
            return;
        }
        /* taggent */
        if (p4 != 0 && ((p2 == p3 && p1 != p2 && p1 != p4 && p2 != p4) ||
                (p1 == p3 && p1 != p2 && p1 != p4 && p2 != p4)) && (o = fd_co(p1, p2, p4)) != 0) {
            if (p2 == p3) {
                add_codb(CO_ACONG, p2, p1, p1, p4, get_lpt1(l3, p2), p2, p2, p4);
                add_codb(CO_CYCLIC, o, p1, p2, p4, 0, 0, 0, 0);
                add_tline(R_CR_TAN_AS, o, p3, p3, get_lpt1(l3, p3));
                pop_codb();
                pop_codb();
            } else {
                add_codb(CO_ACONG, p1, p2, p2, p4, get_lpt1(l1, p1), p1, p1, p4);
                add_codb(CO_CYCLIC, o, p1, p2, p4, 0, 0, 0, 0);
                add_tline(R_CR_TAN_AS, o, p3, p3, get_lpt1(l1, p3));
                pop_codb();
                pop_codb();
            }

            return;
        }
    }

    final void search_as_3(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
        int p3, p4, m;
        if ((p3 = inter_ll(l1, l3)) != 0 && xcir2(p2, p1, p3) &&
                (p4 = inter_lc1(l2, fd_cr_op(p2, p1), p1)) != 0 &&
                (m = inter_ll(l4, fd_ln(p3, p4))) != 0 &&
                p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
            add_codb(CO_ACONG, p3, p1, p1, p4, p3, p2, p2, get_lpt1(l4, p2));
            add_codb(CO_CYCLIC, p2, p1, p3, p4, 0, 0, 0, 0);
            add_mid(45, m, p3, p4);
            pop_codb();
            pop_codb();
        }
        if ((p4 = inter_ll(l2, l4)) != 0 && xcir2(p2, p1, p4) &&
                (p3 = inter_lc1(l1, fd_cr_op(p2, p1), p1)) != 0 &&
                (m = inter_ll(l3, fd_ln(p3, p4))) != 0 &&
                p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
            add_codb(CO_ACONG, p3, p1, p1, p4, get_lpt1(l3, p2), p2, p2, p4);
            add_codb(CO_CYCLIC, p2, p1, p3, p4, 0, 0, 0, 0);
            add_mid(0, m, p3, p4);
            pop_codb();
            pop_codb();
        }
    }

    final void lm_43(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
        int p3, p4, o;

        if (inter_ll(l1, l3) == p1 && p2 != p1 &&
                (p3 = inter_ll(l2, l4)) != 0 && p3 != p1 && p3 != p2 &&
                (o = fd_co(p1, p2, p3)) != 0) {
            p4 = get_lpt1(l1, p1);
            add_codb(CO_ACONG, p4, p1, p1, p3, p1, p2, p2, p3);
            add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
            add_tline(R_CR_TAN_AS, o, p1, p1, p4);
            pop_codb();
            pop_codb();
        } else if (inter_ll(l2, l4) == p1 && p2 != p1 &&
                (p3 = inter_ll(l1, l3)) != 0 && p3 != p1 && p3 != p2 &&
                (o = fd_co(p1, p2, p3)) != 0) {
            p4 = get_lpt1(l2, p1);
            add_codb(CO_ACONG, p4, p1, p1, p3, p1, p2, p2, p3);
            add_codb(CO_CYCLIC, o, p1, p2, p3, 0, 0, 0, 0);
            add_tline(R_CR_TAN_AS, o, p1, p1, p4);
            pop_codb();
            pop_codb();
        }
    }

    final void search_as_sim(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
//        if (!valid(R_STRI)) return;
        int p3, p4, p5, p6;
        l_line ln1, ln2;
        tri_type = 0;
        int pm = get_lpt1(l1, p1);
        int pn = get_lpt1(l2, p1);
        if (mcoll(p1, pm, pn)) return;
        if (check_coll(p1, pm, pn)) return;


        for (ln1 = all_ln.nx; ln1 != null; ln1 = ln1.nx) {
            if (ch_it(ln1.type) && ln1 != l1 && ln1 != l2 &&
                    (p3 = inter_ll(ln1, l1)) != 0 && p3 != p1 &&
                    (p5 = inter_ll(ln1, l2)) != 0 && p5 != p1) {

                for (ln2 = all_ln.nx; ln2 != null; ln2 = ln2.nx) {
                    if (ch_it(ln2.type) && ln2 != l3 && ln2 != l4 &&
                            (p4 = inter_ll(ln2, l3)) != 0 && p4 != p2 &&
                            (p6 = inter_ll(ln2, l4)) != 0 && p6 != p2) {

                        if (ln_acong(l1, ln1, l3, ln2) && !(p1 == p2 && p3 == p4 && p5 == p6) && !tri_sim(p1, p3, p5, p2, p4, p6)) {
                            add_codb(CO_ACONG, p1, p3, p3, p5, p2, p4, p4, p6);
                            add_codb(CO_ACONG, p3, p1, p1, p5, p4, p2, p2, p6);
                            if (!search_st_ct(1, p1, p3, p5, p2, p4, p6))
                                add_stri(R_AA_STRI, 1, p1, p3, p5, p2, p4, p6);

                            pop_codb();
                            pop_codb();
                        } else if (ln_acong(l2, ln1, l4, ln2) && !(p1 == p2 && p3 == p4 && p5 == p6) && !tri_sim(p1, p3, p5, p2, p4, p6)) {
                            add_codb(CO_ACONG, p1, p5, p5, p3, p2, p6, p6, p4);
                            add_codb(CO_ACONG, p3, p1, p1, p5, p4, p2, p2, p6);
                            if (!search_st_ct(1, p1, p3, p5, p2, p4, p6))
                                add_stri(R_AA_STRI, 1, p1, p3, p5, p2, p4, p6);
                            pop_codb();
                            pop_codb();

                        } else if (ln_acong(l1, ln1, ln2, l4) && !tri_sim(p1, p3, p5, p2, p6, p4)) {
                            add_codb(CO_ACONG, p1, p3, p3, p5, p4, p6, p6, p2);
                            add_codb(CO_ACONG, p3, p1, p1, p5, p4, p2, p2, p6);
                            if (!search_st_ct(-1, p1, p3, p5, p2, p6, p4))
                                add_stri(R_AA_STRI, -1, p1, p3, p5, p2, p6, p4);
                            pop_codb();
                            pop_codb();

                        } else if (ln_acong(l2, ln1, ln2, l3) && !tri_sim(p1, p3, p5, p2, p6, p4)) {
                            add_codb(CO_ACONG, p1, p5, p5, p3, p6, p4, p4, p2);
                            add_codb(CO_ACONG, p3, p1, p1, p5, p4, p2, p2, p6);
                            if (!search_st_ct(-1, p1, p3, p5, p2, p6, p4))
                                add_stri(R_AA_STRI, -1, p1, p3, p5, p2, p6, p4);
                            pop_codb();
                            pop_codb();

                        }
                    }
                }
            }
        }
    }

    boolean tri_sim(int p1, int p2, int p3, int p4, int p5, int p6) {
        int p;
        if (ind_3(p1, p4, p5, p6) != 0 && ind_3(p2, p4, p5, p6) != 0 && ind_3(p3, p4, p5, p6) != 0)//???
            return true;
        if (p2 == p5) {
            p = p1;
            p1 = p2;
            p2 = p3;
            p3 = p;
            p = p4;
            p4 = p5;
            p5 = p6;
            p6 = p;
        } else if (p3 == p6) {
            p = p1;
            p1 = p3;
            p3 = p2;
            p2 = p;
            p = p4;
            p4 = p6;
            p6 = p5;
            p5 = p;
        }
        if (p1 != p4) return (false);
        if (p2 == p6 && p3 == p5) return (true);
        if (p3 == p6) {
            p = p2;
            p2 = p3;
            p3 = p;
            p = p5;
            p5 = p6;
            p6 = p;
        }
        if (p2 != p5) return (false);
        if (p3 == p6) return (true);
        if (xperp(p3, p6, p1, p2) || xcir2(p1, p3, p6) || xcir2(p2, p3, p6)) return (true);
        return (false);
    }

    final void search_as_pt() {
        angles as;
        as = all_as.nx;
        while (as != null) {
            if (!ch_it(as.type)) {
                as = as.nx;
                continue;
            }
            search_as_0(as, as.l1, as.l2, as.l3, as.l4);
            if (as.type != 0) search_as_0(as, as.l4, as.l3, as.l2, as.l1);
            as = as.nx;
        }
    }

    boolean search_st_ct(int dr, int p1, int p2, int p3, int p4, int p5, int p6) {
        int oldt = tri_type;
        boolean mk = false;
        tri_type = 1;
        if (xcong(p1, p2, p4, p5)) {
            add_codb(CO_CONG, p1, p2, p4, p5, 0, 0, 0, 0);
            add_stri(R_RA_ST_CT, dr, p1, p2, p3, p4, p5, p6);
            pop_codb();
            mk = true;
        } else if (xcong(p1, p3, p4, p6)) {
            add_codb(CO_CONG, p1, p3, p4, p6, 0, 0, 0, 0);
            add_stri(R_RA_ST_CT, dr, p1, p2, p3, p4, p5, p6);
            pop_codb();
            mk = true;
        } else if (xcong(p2, p3, p5, p6)) {
            add_codb(CO_CONG, p2, p3, p5, p6, 0, 0, 0, 0);
            add_stri(R_RA_ST_CT, dr, p1, p2, p3, p4, p5, p6);
            pop_codb();
            mk = true;
        }
        tri_type = oldt;
        return mk;
    }

    void search_st_rg(int dr, int p1, int p2, int p3, int p4, int p5, int p6) {
        cong_seg rg = fo_rg1(p1, p2, p4, p5);
        int t1, t2;
        if (rg != null) {
            if (rg.p1 == p1 && rg.p2 == p2 || rg.p1 == p2 && rg.p2 == p1) {
                t1 = rg.t1;
                t2 = rg.t2;
            } else {
                t1 = rg.t2;
                t2 = rg.t1;
            }
            add_codb(CO_CONG, p1, p2, p4, p5, t1, t2, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p2, p3, p5, p6, t1, t2);
            add_cong(R_ST_RAAS, 0, p1, p3, p4, p6, t1, t2);
            pop_codb();
            pop_codb();
        }
        rg = fo_rg1(p1, p3, p4, p6);
        if (rg != null) {
            if (rg.p1 == p1 && rg.p2 == p3 || rg.p1 == p3 && rg.p2 == p1) {
                t1 = rg.t1;
                t2 = rg.t2;
            } else {
                t1 = rg.t2;
                t2 = rg.t1;
            }
            add_codb(CO_CONG, p1, p3, p4, p6, t1, t2, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p2, p3, p5, p6, t1, t2);
            add_cong(R_ST_RAAS, 0, p1, p2, p4, p5, t1, t2);
            pop_codb();
            pop_codb();
        }
        rg = fo_rg1(p2, p3, p5, p6);
        if (rg != null) {
            if (rg.p1 == p2 && rg.p2 == p3 || rg.p1 == p3 && rg.p2 == p2) {
                t1 = rg.t1;
                t2 = rg.t2;
            } else {
                t1 = rg.t2;
                t2 = rg.t1;
            }
            add_codb(CO_CONG, p2, p3, p5, p6, t1, t2, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p1, p3, p4, p6, t1, t2);
            add_cong(R_ST_RAAS, 0, p1, p2, p4, p5, t1, t2);
            pop_codb();
            pop_codb();
        }
    }

    final void search_st(sim_tri st) {
        int dr, p1, p2, p3, p4, p5, p6;
        ratio_seg ra1, ra = null;
        tri_type = 1;
        adj_st(st);

        p1 = st.p1[0];
        p2 = st.p1[1];
        p3 = st.p1[2];
        p4 = st.p2[0];
        p5 = st.p2[1];
        p6 = st.p2[2];
        dr = st.dr;

        search_st1(dr, p1, p2, p3, p4, p5, p6);
        search_st_rg(dr, p1, p2, p3, p4, p5, p6);

        add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);

        if (tri_sim(p1, p2, p3, p4, p5, p6) || xcon_tri(p1, p2, p3, p4, p5, p6)) {
        } else if (search_st_ct(dr, p1, p2, p3, p4, p5, p6)) {
        } else {
            if (valid(R_RATIO)) {

                if (!xeq_ratio(p1, p2, p1, p3, p4, p5, p4, p6)) {
                    ra1 = add_ra(R_ST_RAAS, 0, p1, p2, p1, p3, p4, p5, p4, p6);
                    if (ra == null) ra = ra1;
                }
                if (!xeq_ratio(p1, p2, p2, p3, p4, p5, p5, p6)) {
                    ra1 = add_ra(R_ST_RAAS, 0, p1, p2, p2, p3, p4, p5, p5, p6);
                    if (ra == null) ra = ra1;
                }
                if (!xeq_ratio(p1, p3, p2, p3, p4, p6, p5, p6)) {
                    ra1 = add_ra(R_ST_RAAS, 0, p1, p3, p2, p3, p4, p6, p5, p6);
                    if (ra == null) ra = ra1;
                }
            }

        }
        pop_codb();
    }

    final void search_ct(sim_tri st) {
        tri_type = 1;
        adj_ct(st);

        int p1 = st.p1[0];
        int p2 = st.p1[1];
        int p3 = st.p1[2];
        int p4 = st.p2[0];
        int p5 = st.p2[1];
        int p6 = st.p2[2];
        add_codb(CO_CTRI, p1, p2, p3, p4, p5, p6, 0, 0);
        add_cong(0, 0, p1, p2, p4, p5);
        add_cong(0, 0, p1, p3, p4, p6);
        add_cong(0, 0, p2, p3, p5, p6);

        if (st.dr > 0) {
            add_ea_pt_t(0, p1, p2, p3, p4, p5, p6);
            add_ea_pt_t(0, p1, p3, p2, p4, p6, p5);
            add_ea_pt_t(0, p2, p1, p3, p5, p4, p6);
        } else {
            add_ea_pt_t(0, p1, p2, p3, p6, p5, p4);
            add_ea_pt_t(0, p1, p3, p2, p5, p6, p4);
            add_ea_pt_t(0, p2, p1, p3, p6, p4, p5);
        }
        int dr = st.dr;

        add_stct_at(dr, p1, p2, p3, p4, p5, p6);
        add_stct_at(dr, p2, p3, p1, p5, p6, p4);
        add_stct_at(dr, p3, p1, p2, p6, p4, p5);

        pop_codb();
    }

    final void search_cg(cong_seg cg) {
        int o, p1, p2, p3, p4;
        if (cg.t1 == 0 || cg.t2 == 0) {
            int k = 0;
        }
        if (cg.t1 == cg.t2)
            adj_cg(1, cg);
        if (cg.t1 != cg.t2)
            adj_cg(1, cg);
        adj_cg(0, cg);

        search_tn_cg(cg);
        p1 = cg.p1;
        p2 = cg.p2;
        p3 = cg.p3;
        p4 = cg.p4;
        search_rg_at(cg);
        search_cgs(cg);

        while (cg.t1 == cg.t2) {
            search_cg_ct(p1, p2, p3, p4);
            search_cg_md(p1, p2, p3, p4);
            search_cg_aas(p1, p2, p3, p4);
//            this.search_cg_(p1,p2,p3,p4);
//            search_cg_st(p1, p2, p3, p4);

            if (p1 == p3) {
                o = p1;
                p1 = p4;
            } else if (p1 == p4) {
                o = p1;
                p1 = p3;
            } else if (p2 == p3) {
                o = p2;
                p2 = p4;
            } else if (p2 == p4) {
                o = p2;
                p2 = p3;
            } else {
                o = 0;
                break;//continue;
            }
            if (p1 == p2) {
                o = 0;
                break;//continue;
            }
            search_cg_iso(o, p1, p2);

            add_codb(CO_CONG, o, p1, o, p2, 0, 0, 0, 0);
            if (!xmid(o, p1, p2) && ycoll(o, p1, p2)) {
                add_mid(R_ISOCELES, o, p1, p2);
                pop_codb();
            }
            if (!mcoll(o, p1, p2)) {
                add_ea_pt_t(R_ISOCELES, o, p1, p2, p1, p2, o);
                add_cir2(R_ISOCELES, o, p1, p2);
            }
            pop_codb();
            break;
        }
        search_cg_st0(cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2);

        if (cg.t1 == cg.t2)
            search_cg_st(cg.p1, cg.p2, cg.p3, cg.p4);

    }

    final void search_cg_iso(int o, int a, int b) {
        midpt m = fo_md(a, b);
        if (m != null && m.m != o) { // mid
            add_codb(CO_CONG, o, a, o, b, 0, 0, 0, 0);
            add_codb(CO_MIDP, m.m, m.a, m.b, 0, 0, 0, 0, 0);
            add_iso_3lines_concur(0, o, a, b, m.m);
            pop_codb();
            pop_codb();
            return;
        }
        l_line ln = fd_tline(o, a, b);
        l_line l1 = fd_ln(a, b);
        int p;
        if (ln != null && l1 != null && (p = inter_ll(ln, l1)) != 0 && o != p) { // perp
            add_codb(CO_CONG, o, a, o, b, 0, 0, 0, 0);
            add_codb(CO_PERP, o, p, a, b, 0, 0, 0, 0);
            add_iso_3lines_concur(0, o, a, b, p);
            pop_codb();
            pop_codb();
            return;
        }
        if (l1 != null) // as
        {
            for (int i = 0; i <= l1.no; i++) {
                p = l1.pt[i];
                if (p != a && p != b && o != p && xacong(a, o, p, p, o, b)) {
                    add_codb(CO_CONG, o, a, o, b, 0, 0, 0, 0);
                    add_codb(CO_ACONG, a, o, o, p, p, o, o, b);
                    add_iso_3lines_concur(0, o, a, b, p);
                    pop_codb();
                    pop_codb();
                    return;
                }
            }
        }


    }

    final void search_cg_st(int p1, int p2, int p3, int p4) {

        sim_tri st;
        int dr, t1, t2, t3, t4, t5, t6, p, i1, i2, i3, i4;

        for (st = all_st.nx; st != null && ch_dep(st.dep); st = st.nx) {
            {
                t1 = st.p1[0];
                t2 = st.p1[1];
                t3 = st.p1[2];
                t4 = st.p2[0];
                t5 = st.p2[1];
                t6 = st.p2[2];


                i1 = ind_3(p1, st.p1);
                i2 = ind_3(p2, st.p1);
                if (i1 == 0 || i2 == 0) {
                    i1 = ind_3(p1, st.p2);
                    i2 = ind_3(p2, st.p2);
                    i3 = ind_3(p3, st.p1);
                    i4 = ind_3(p4, st.p1);
                    if (i1 != 0 && i2 != 0) {
                        p = p1;
                        p1 = p3;
                        p3 = p;
                        p = p2;
                        p2 = p4;
                        p4 = p;
                    }
                } else {
                    i3 = ind_3(p3, st.p2);
                    i4 = ind_3(p4, st.p2);
                }

                if (i1 == 0 || i2 == 0 || i3 == 0 || i4 == 0)
                    continue;
                {
                    dr = st.dr;//[i] * st.dr[j];
                    if (i1 == i3 && i2 == i4 || i1 == i4 && i2 == i3) {
                        add_codb(CO_STRI, t1, t2, t3, t4, t5, t6, 0, 0);
                        add_codb(CO_CONG, p1, p2, p3, p4, 0, 0, 0, 0);
                        tri_type = 1;
                        add_stri(R_RA_ST_CT, dr, t1, t2, t3, t4, t5, t6);
                        pop_codb();
                        pop_codb();
                    }
                }
            }
        }
    }


    final void search_ra(ratio_seg ra) {
        int n, a1, b1, a2, b2, a3, b3, a4, b4;
        if (ra.type == 0) return;
        serach_ra_st(ra);
        if (ra.r[0] == 0) return;

        if (ra.r[2] == ra.r[4] || ra.r[1] == ra.r[4]) {
            a1 = ra.r[2];
            b1 = ra.r[1];
            a2 = ra.r[4];
            b2 = ra.r[3];
        } else {
            a1 = ra.r[1];
            b1 = ra.r[2];
            a2 = ra.r[3];
            b2 = ra.r[4];
        }
        if (ra.r[6] == ra.r[8] || ra.r[5] == ra.r[8]) {
            a3 = ra.r[6];
            b3 = ra.r[5];
            a4 = ra.r[8];
            b4 = ra.r[7];
        } else {
            a3 = ra.r[5];
            b3 = ra.r[6];
            a4 = ra.r[7];
            b4 = ra.r[8];
        }
        if (b1 == a2 && b3 == a4) {
            n = a1;
            a1 = b1;
            b1 = n;
            n = a3;
            a3 = b3;
            b3 = n;
        }

        add_codb(CO_PROD, a1, b1, a2, b2, a3, b3, a4, b4);
        if (a1 == a2 && a1 == a3 && a1 == a4) {
            if (xcoll(a1, b1, b2) && xcoll(a1, b3, b4)) {
                add_pline1(74, b1, b3, b2, b4);
            } else if (xcoll(a1, b1, b3) && xcoll(a1, b2, b4)) {
                add_pline1(74, b1, b2, b3, b4);
            } else if (xcoll(a1, b1, b4) && xcoll(a1, b2, b3)) {
                add_cir4(0, 0, b1, b2, b3, b4);
            }
        } else if (a1 == a2 && a3 == a4)
            if (xcoll(a1, b1, b2) && xcoll(a3, b3, b4)) {
                add_pline1(76, b1, b3, b2, b4);
                add_pline1(76, b1, b3, a1, a3);
            }
    }


    final void adj_ras(ratio_seg ra) {
        if (ra.type == 0) return;
        cong_seg cg;
        int p1, p2;
        int[] p;
        test_ra1.cp_ratio(ra);
        p = test_ra1.r;
        cg = null;

        for (int n = 0; n < 4; n++) {
            p1 = p[n * 2 + 1];
            p2 = p[n * 2 + 2];

            cg = fd_cg2(p1, p2, cg);
            while (cg != null) {
                if (p1 == cg.p1 && p2 == cg.p2 || p1 == cg.p2 && p2 == cg.p1) {
                    p[n * 2 + 1] = cg.p3;
                    p[n * 2 + 2] = cg.p4;
                } else {
                    p[n * 2 + 1] = cg.p1;
                    p[n * 2 + 2] = cg.p2;
                }

                if (!xeq_ratio(p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8])) {     // mod here.

                    add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, 0, 0, 0, 0);
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_ra(0, 0, p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8]);
                    pop_codb();
                    pop_codb();
                }
                cg = cg.nx;
            }
            p[n * 2 + 1] = p1;
            p[n * 2 + 2] = p2;
        }
    }

    final void serach_ra_st(ratio_seg ra) {
        int a, a1, a2, b, b1, b2;
        int[] p = ra.r;
        a = b = a1 = b1 = a2 = b2 = 0;

        if (p[1] == p[5]) {
            a = p[1];
            a1 = p[2];
            a2 = p[6];
        } else if (p[1] == p[6]) {
            a = p[1];
            a1 = p[2];
            a2 = p[5];
        } else if (p[2] == p[5]) {
            a = p[2];
            a1 = p[1];
            a2 = p[6];
        } else if (p[2] == p[6]) {
            a = p[2];
            a1 = p[1];
            a2 = p[5];
        }

        if (p[3] == p[7]) {
            b = p[3];
            b1 = p[4];
            b2 = p[8];
        } else if (p[3] == p[8]) {
            b = p[3];
            b1 = p[4];
            b2 = p[7];
        } else if (p[4] == p[7]) {
            b = p[4];
            b1 = p[3];
            b2 = p[8];
        } else if (p[4] == p[8]) {
            b = p[4];
            b1 = p[3];
            b2 = p[7];
        }
        if (a != 0 && b != 0) {
            if (check_simtri(a1, a, a2, b1, b, b2)) {
                tri_type = 0;
                if (xacong(a1, a, a2, b1, b, b2)) {
                    add_codb(CO_ACONG, a1, a, a, a2, b1, b, b, b2);
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_stri(0, 1, a1, a, a2, b1, b, b2);
                    pop_codb();
                    pop_codb();
                } else if (xacong(a1, a, a2, b2, b, b1)) {
                    add_codb(CO_ACONG, a1, a, a, a2, b2, b, b, b1);
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_stri(0, -1, a1, a, a2, b1, b, b2);
                    pop_codb();
                    pop_codb();
                }
            }
        }

        a = b = a1 = b1 = a2 = b2 = 0;

        if (p[1] == p[7]) {
            a = p[1];
            a1 = p[2];
            a2 = p[8];
        } else if (p[1] == p[8]) {
            a = p[1];
            a1 = p[2];
            a2 = p[7];
        } else if (p[2] == p[7]) {
            a = p[2];
            a1 = p[1];
            a2 = p[8];
        } else if (p[2] == p[8]) {
            a = p[2];
            a1 = p[1];
            a2 = p[7];
        }

        if (p[3] == p[5]) {
            b = p[3];
            b1 = p[4];
            b2 = p[6];
        } else if (p[3] == p[6]) {
            b = p[3];
            b1 = p[4];
            b2 = p[5];
        } else if (p[4] == p[5]) {
            b = p[4];
            b1 = p[3];
            b2 = p[6];
        } else if (p[4] == p[6]) {
            b = p[4];
            b1 = p[3];
            b2 = p[5];
        }
        if (a != 0 && b != 0) {
            if (check_simtri(a1, a, a2, b1, b, b2)) {
                tri_type = 0;
                if (xacong(a1, a, a2, b1, b, b2)) {
                    add_codb(CO_ACONG, a1, a, a, a2, b1, b, b, b2);
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_stri(0, 1, a1, a, a2, b1, b, b2);
                    pop_codb();
                    pop_codb();
                } else if (xacong(a1, a, a2, b2, b, b1)) {
                    add_codb(CO_ACONG, a1, a, a, a2, b2, b, b, b1);
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_stri(0, -1, a1, a, a2, b1, b, b2);
                    pop_codb();
                    pop_codb();
                }
            }
        }
    }

    final void search_ra_cg() {

        ratio_seg ra;
        ra = all_ra.nx;
        while (ra != null && ch_dep(ra.dep)) {
            if (ch_it(ra.type)) {
                if (ycong(ra.r[1], ra.r[2], ra.r[3], ra.r[4])) {
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_cong(71, 0, ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    pop_codb();
                    pop_codb();
                    ra.type = 0;
                } else if (ycong(ra.r[1], ra.r[2], ra.r[5], ra.r[6])) {
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_cong(71, 0, ra.r[3], ra.r[4], ra.r[7], ra.r[8]);
                    pop_codb();
                    pop_codb();
                    ra.type = 0;
                } else if (ycong(ra.r[3], ra.r[4], ra.r[7], ra.r[8])) {
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_cong(71, 0, ra.r[1], ra.r[2], ra.r[5], ra.r[6]);
                    pop_codb();
                    pop_codb();
                    ra.type = 0;
                } else if (ycong(ra.r[5], ra.r[6], ra.r[7], ra.r[8])) {
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_cong(71, 0, ra.r[1], ra.r[2], ra.r[3], ra.r[4]);
                    pop_codb();
                    pop_codb();
                    ra.type = 0;
                } else if (xcong(ra.r[1], ra.r[2], ra.r[7], ra.r[8]) &&
                        xcong(ra.r[3], ra.r[4], ra.r[5], ra.r[6])) {
                    add_codb(CO_PROD, ra.r[1], ra.r[2], ra.r[3], ra.r[4], ra.r[5], ra.r[6], ra.r[7], ra.r[8]);
                    add_cong(72, 0, ra.r[1], ra.r[2], ra.r[3], ra.r[4]);
                    ra.type = 0;
                    pop_codb();
                }
            }
            m1:
            ra = ra.nx;
        }
    }

    ///////////////////////////////////////////////////////////////
    public boolean mcoll(int p1, int p2, int p3) {
        return xcoll(p1, p2, p3);
    }

    public boolean mperp(int p1, int p2, int p3, int p4) {
        return xperp(p1, p2, p3, p4);
    }

    public boolean mpara(int p1, int p2, int p3, int p4) {
        return xpara(p1, p2, p3, p4);
    }

    ////////////////////////////////////////////////////////////////////
    // added by yezheng 2006.9.26


    final void add_iso_3lines_concur(int lm, int p1, int p2, int p3, int m) {
        if (p1 == m) return;
        if (check_coll(p1, p2, p3)) {
            return;
        }

        lm = R_ISO_3L;
        add_cong(lm, 0, p1, p2, p1, p3);
        add_cong(lm, 0, p2, m, p3, m);
        add_mid(lm, m, p2, p3);
        add_ea_pt_t(lm, p2, p1, m, m, p1, p3);
        add_ea_pt_t(lm, p3, p2, p1, p1, p3, p2);
        add_tline(lm, p1, m, p2, p3);
    }

    public final void search_cg_ct(int p1, int p2, int p3, int p4) {
        search_cg_ct0(p1, p2, p3, p4);      //SSS
        search_cg_ct0(p3, p4, p1, p2);      //SSS
    }

    public final void search_cg_aas(int p1, int p2, int p3, int p4) {
        for (int i = 1; i <= pts_no; i++)
            for (int j = 1; j <= pts_no; j++) {
                if (i != p1 && i != p2 && j != p3 && j != p4 && !xcoll(i, p1, p2) && !xcoll(j, p3, p4)) {
                    if (xacong(i, p1, p2, j, p3, p4) && xacong(i, p2, p1, j, p4, p3)) {
                        tri_type = 1;
                        add_codb(CO_ACONG, i, p1, p1, p2, j, p3, p3, p4);
                        add_codb(CO_CONG, p1, p2, p3, p4, 0, 0, 0, 0);
                        add_codb(CO_ACONG, i, p2, p2, p1, j, p4, p4, p3);
                        add_stri(R_AAS, 1, i, p1, p2, j, p3, p4);
                        pop_codb();
                        pop_codb();
                        pop_codb();
                    } else if (xacong(i, p2, p1, j, p3, p4) && xacong(i, p1, p2, j, p4, p3)) {
                        tri_type = 1;
                        add_codb(CO_ACONG, i, p2, p2, p1, j, p3, p3, p4);
                        add_codb(CO_CONG, p1, p2, p3, p4, 0, 0, 0, 0);
                        add_codb(CO_ACONG, i, p1, p1, p2, j, p4, p4, p3);
                        add_stri(R_AAS, 1, i, p2, p1, j, p3, p4);
                        pop_codb();
                        pop_codb();
                        pop_codb();
                    }
                }
            }
    }

    public final void search_cg_ct0(int p1, int p2, int p3, int p4) {
        //   if (!valid(R_SSS)) return;

        cong_seg cg = all_cg.nx;
        int t1, t2, t3;
        t1 = t2 = t3 = 0;

        while (cg != null) {
            if (cg.type == 0 || cg.t1 != cg.t2) {
                cg = cg.nx;
                continue;
            }
            t1 = t2 = t3 = 0;

            if (p1 == cg.p1 && p2 != cg.p2) {
                t1 = p1;
                t2 = p2;
                t3 = cg.p2;
            } else if (p1 == cg.p2 && p2 != cg.p1) {
                t1 = p1;
                t2 = p2;
                t3 = cg.p1;
            } else if (p2 == cg.p1 && p1 != cg.p2) {
                t1 = p2;
                t2 = p1;
                t3 = cg.p2;
            } else if (p2 == cg.p2 && p1 != cg.p1) {
                t1 = p2;
                t2 = p1;
                t3 = cg.p1;
            }
            if (t1 != 0 && t2 != 0 && t3 != 0) {
                int t4, t5, t6;
                t4 = t5 = t6 = 0;
                if (p3 == cg.p3 && p4 != cg.p4) {
                    t4 = p3;
                    t5 = p4;
                    t6 = cg.p4;
                } else if (p3 == cg.p4 && p4 != cg.p3) {
                    t4 = p3;
                    t5 = p4;
                    t6 = cg.p3;
                } else if (p4 == cg.p3 && p3 != cg.p4) {
                    t4 = p4;
                    t5 = p3;
                    t6 = cg.p4;
                } else if (p4 == cg.p4 && p3 != cg.p3) {
                    t4 = p4;
                    t5 = p3;
                    t6 = cg.p3;
                }

                if (t4 != 0 && t5 != 0 && t6 != 0) {
                    if (!same_tri(t1, t2, t3, t4, t5, t6) && !xcoll(t1, t2, t3) && !xcoll(t4, t5, t6)
                            && !xcon_tri(t1, t2, t3, t4, t5, t6)) {
                        if (xcong(t2, t3, t5, t6)) {//sss
                            int d = check_tri_dr(t1, t2, t3, t4, t5, t6);
                            tri_type = 1;
                            add_codb(CO_CONG, t1, t2, t4, t5, 0, 0, 0, 0);
                            add_codb(CO_CONG, t2, t3, t5, t6, 0, 0, 0, 0);
                            add_codb(CO_CONG, t1, t3, t4, t6, 0, 0, 0, 0);
                            add_stri(R_SSS, d, t1, t2, t3, t4, t5, t6);
                            pop_codb();
                            pop_codb();
                            pop_codb();
                        } else if (check_eqangle(t2, t1, t3, t5, t4, t6) && xacong(t2, t1, t3, t5, t4, t6)) {//sas
                            add_codb(CO_CONG, t1, t2, t4, t5, 0, 0, 0, 0);
                            add_codb(CO_ACONG, t2, t1, t1, t3, t5, t4, t4, t6);
                            add_codb(CO_CONG, t1, t3, t4, t6, 0, 0, 0, 0);
                            tri_type = 1;
                            add_stri(R_SAS, 1, t1, t2, t3, t4, t5, t6);
                            pop_codb();
                            pop_codb();
                            pop_codb();
                        } else if (check_eqangle(t2, t1, t3, t6, t4, t5) && xacong(t2, t1, t3, t6, t4, t5)) {//sas
                            add_codb(CO_CONG, t1, t2, t4, t5, 0, 0, 0, 0);
                            add_codb(CO_ACONG, t2, t1, t1, t3, t6, t4, t4, t5);
                            add_codb(CO_CONG, t1, t3, t4, t6, 0, 0, 0, 0);
                            tri_type = 1;
                            add_stri(R_SAS, -1, t1, t2, t3, t4, t5, t6);
                            pop_codb();
                            pop_codb();
                            pop_codb();
                        } else if (xperp(t1, t2, t2, t3) && xperp(t4, t5, t5, t6)) {
                            add_codb(CO_PERP, t1, t2, t2, t3, 0, 0, 0, 0);
                            add_codb(CO_PERP, t4, t5, t5, t6, 0, 0, 0, 0);
                            add_codb(CO_CONG, t1, t2, t4, t5, 0, 0, 0, 0);
                            add_codb(CO_CONG, t1, t3, t4, t6, 0, 0, 0, 0);
                            tri_type = 1;
                            int d = check_tri_dr(t1, t2, t3, t4, t5, t6);
                            add_stri(R_TTCG2_CT, d, t1, t2, t3, t4, t5, t6);
                            pop_codb();
                            pop_codb();
                            pop_codb();

                        } else if (xperp(t1, t3, t3, t2) && xperp(t4, t6, t6, t5)) {
                            add_codb(CO_PERP, t1, t3, t3, t2, 0, 0, 0, 0);
                            add_codb(CO_PERP, t4, t6, t6, t5, 0, 0, 0, 0);
                            add_codb(CO_CONG, t1, t2, t4, t5, 0, 0, 0, 0);
                            add_codb(CO_CONG, t1, t3, t4, t6, 0, 0, 0, 0);
                            tri_type = 1;
                            int d = check_tri_dr(t1, t2, t3, t4, t5, t6);
                            add_stri(R_TTCG2_CT, d, t1, t2, t3, t4, t5, t6);
                            pop_codb();
                            pop_codb();
                            pop_codb();
                        }
                        t4 = t5 = t6 = 0;
                    }
                }
            }

            cg = cg.nx;
        }
    }

    final public void search_as_ct(int p1, int p2, l_line ln1, l_line ln2, l_line ln3, l_line ln4) {
//        if (!valid(R_SAS)) return;
        if (p1 == 0 || p2 == 0) return;
        int a, b, c, d;

        for (int i = 0; i <= ln1.no; i++) {
            if (p1 != ln1.pt[i])
                for (int j = 0; j <= ln2.no; j++) {
                    if (p1 != ln2.pt[j])
                        for (int k = 0; k <= ln3.no; k++) {
                            if (p2 != ln3.pt[k])
                                for (int m = 0; m <= ln4.no; m++) {
                                    if (p2 != ln4.pt[m])
                                        if (
                                                ln1.pt[i] != 0 && ln2.pt[j] != 0 && ln3.pt[k] != 0 && ln4.pt[m] != 0) {
                                            if (!same_tri(p1, ln1.pt[i], ln2.pt[j], p2, ln3.pt[k], ln4.pt[m]) &&
                                                    check_eqangle(ln1.pt[i], p1, ln2.pt[j], ln3.pt[k], p2, ln4.pt[m])) {
                                                a = ln1.pt[i];
                                                b = ln2.pt[j];
                                                c = ln3.pt[k];
                                                d = ln4.pt[m];

                                                if (!xcon_tri(p1, a, b, p2, c, d)) {
                                                    if (xcong(p1, a, p2, c) && xcong(p1, b, p2, d)) {
                                                        add_codb(CO_CONG, p1, a, p2, c, 0, 0, 0, 0);
                                                        add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                        add_codb(CO_CONG, p1, b, p2, d, 0, 0, 0, 0);
                                                        tri_type = 1;
                                                        add_stri(R_SAS, 1, p1, a, b, p2, c, d);
                                                        pop_codb();
                                                        pop_codb();
                                                        pop_codb();
                                                    } else if (xcong(p1, b, p2, c) && xcong(p1, a, p2, d)) {
                                                        add_codb(CO_CONG, p1, b, p2, c, 0, 0, 0, 0);
                                                        add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                        add_codb(CO_CONG, p1, a, p2, d, 0, 0, 0, 0);
                                                        tri_type = 1;
                                                        add_stri(R_SAS, -1, p1, a, b, p2, d, c);
                                                        pop_codb();
                                                        pop_codb();
                                                        pop_codb();
                                                    } else if (xcong(p1, a, p2, c) && xacong(p1, a, b, p2, c, d)) {
                                                        add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                        add_codb(CO_CONG, p1, a, p2, c, 0, 0, 0, 0);
                                                        add_codb(CO_ACONG, p1, a, a, b, p2, c, c, d);
                                                        tri_type = 1;
                                                        add_stri(R_AAS, 1, p1, a, b, p2, c, d);
                                                        pop_codb();
                                                        pop_codb();
                                                        pop_codb();
                                                    } else if (xcong(p1, b, p2, d) && xacong(p1, b, a, p2, d, c)) {
                                                        add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                        add_codb(CO_CONG, p1, b, p2, d, 0, 0, 0, 0);
                                                        add_codb(CO_ACONG, p1, b, b, a, p2, d, d, c);
                                                        tri_type = 1;
                                                        add_stri(R_AAS, 1, p1, a, b, p2, c, d);
                                                        pop_codb();
                                                        pop_codb();
                                                        pop_codb();

                                                    }
                                                }
                                            }
                                        }
                                }
                        }
                }
        }
    }


    final public void search_tn_st(int p1, l_line ln1, l_line ln2, t_line tn1) {


        t_line tn = all_tn.nx;
        while (tn != null && ch_dep(tn.dep)) {
            if (true) {
                l_line ln3 = tn.l1;
                l_line ln4 = tn.l2;
                int p2 = inter_ll(ln3, ln4);
                if (p2 != 0) {
                    search_as_st(p1, p2, ln1, ln2, ln3, ln4);

                    for (int i = 0; i <= ln1.no; i++) {
                        if (p1 != ln1.pt[i])
                            for (int j = 0; j <= ln2.no; j++) {
                                if (p1 != ln2.pt[j])
                                    for (int k = 0; k <= ln3.no; k++) {
                                        if (p2 != ln3.pt[k])
                                            for (int m = 0; m <= ln4.no; m++) {
                                                if (p2 != ln4.pt[m]) {
                                                    int t1 = ln1.pt[i];
                                                    int t2 = ln2.pt[j];
                                                    int t3 = ln3.pt[k];
                                                    int t4 = ln4.pt[m];
                                                    if (t1 != 0 && t2 != 0 && t3 != 0 && t4 != 0 && !same_tri(p1, t1, t2, p2, t3, t4)) {
                                                        if (xcong(t1, t2, t3, t4) && !xcon_tri(p1, t1, t2, p2, t3, t4)) {


                                                            if (xcong(p1, t1, p2, t3))
                                                                add_codb(CO_CONG, p1, t1, p2, t3, 0, 0, 0, 0);
                                                            else if (xcong(p1, t1, p2, t4)) {
                                                                add_codb(CO_CONG, p1, t1, p2, t4, 0, 0, 0, 0);
                                                                int t = t3;
                                                                t3 = t4;
                                                                t4 = t;
                                                            } else if (xcong(p1, t2, p2, t3)) {
                                                                add_codb(CO_CONG, p1, t2, p2, t3, 0, 0, 0, 0);
                                                                int t = t3;
                                                                t3 = t4;
                                                                t4 = t;
                                                            } else if (xcong(p1, t2, p2, t4))
                                                                add_codb(CO_CONG, p1, t2, p2, t4, 0, 0, 0, 0);
                                                            else
                                                                continue;
                                                            add_codb(CO_CONG, t1, t2, t3, t4, 0, 0, 0, 0);
                                                            add_codb(CO_PERP, p1, t1, p1, t2, 0, 0, 0, 0);
                                                            add_codb(CO_PERP, p2, t3, p2, t4, 0, 0, 0, 0);
                                                            int d = check_tri_dr(p1, t1, t2, p2, t3, t4);
                                                            tri_type = 1;
                                                            add_stri(R_TTCG2_CT, d, p1, t1, t2, p2, t3, t4);
                                                            pop_codb();
                                                            pop_codb();
                                                            pop_codb();
                                                            pop_codb();
                                                        }
                                                    }
                                                }
                                            }
                                    }
                            }
                    }
                }
            }
            tn = tn.nx;
        }

    }

    final public void search_cg_md(int p1, int p2, int p3, int p4) {
        int t1 = fd_pt_md(p1, p2);
        int t2 = fd_pt_md(p3, p4);
        if (t1 != 0 && t2 != 0) {
            add_codb(CO_MIDP, t1, p1, p2, 0, 0, 0, 0, 0);
            add_codb(CO_MIDP, t2, p3, p4, 0, 0, 0, 0, 0);
            add_codb(CO_CONG, p1, p2, p3, p4, 0, 0, 0, 0);
            add_cong(0, 0, p1, t1, p3, t2);
            add_cong(0, 0, p1, t1, p4, t2);
            add_cong(0, 0, p2, t1, p3, t2);
            add_cong(0, 0, p2, t1, p4, t2);
            pop_codb();
            pop_codb();
            pop_codb();
        }
    }

    final public void search_cg_mds(int p1, int p2, int p3, int p4) {
        midpt md = all_md.nx;
        while (md != null) {

            md = md.nx;
        }
    }

    public void search_cgs(cong_seg cg) {
        cong_seg cg1 = all_cg.nx;
        while (cg1 != null) {
            search_cg_cg(cg, cg1);
            cg1 = cg1.nx;
        }
        cg1 = all_rg.nx;
        while (cg1 != null) {
            search_cg_cg(cg, cg1);
            cg1 = cg1.nx;
        }

        search_2cong1(cg);
    }

    public void search_cg_cg(cong_seg cg, cong_seg cg1) {
        int p1, p2, p3, p4, t1, t2, t3, t4;
        if (cg == cg1) return;
        search_cg__plus_or_minus(cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2,
                cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2);

        search_cg__plus_or_minus(cg.p3, cg.p4, cg.p1, cg.p2, cg.t2, cg.t1,
                cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2);

        if (ck_4peq(cg.p1, cg.p2, cg1.p1, cg1.p2)) {
            p1 = cg.p3;
            p2 = cg.p4;
            p3 = cg1.p3;
            p4 = cg1.p4;
            t1 = cg.t2;
            t2 = cg.t1;
            t3 = cg1.t2;
            t4 = cg1.t1;
        } else if (ck_4peq(cg.p1, cg.p2, cg1.p3, cg1.p4)) {
            p1 = cg.p3;
            p2 = cg.p4;
            p3 = cg1.p1;
            p4 = cg1.p2;
            t1 = cg.t2;
            t2 = cg.t1;
            t3 = cg1.t1;
            t4 = cg1.t2;

        } else if (ck_4peq(cg.p3, cg.p4, cg1.p1, cg1.p2)) {
            p1 = cg.p1;
            p2 = cg.p2;
            p3 = cg1.p3;
            p4 = cg1.p4;
            t1 = cg.t1;
            t2 = cg.t2;
            t3 = cg1.t2;
            t4 = cg1.t1;

        } else if (ck_4peq(cg.p3, cg.p4, cg1.p3, cg1.p4)) {
            p1 = cg.p1;
            p2 = cg.p2;
            p3 = cg1.p1;
            p4 = cg1.p2;
            t1 = cg.t1;
            t2 = cg.t2;
            t3 = cg1.t1;
            t4 = cg1.t2;
        } else
            return;
        int m1 = t1 * t4;
        int m2 = t2 * t3;
//        while (m1 % 2 == 0 && m2 % 2 == 0) {
//            m1 = m1 / 2;
//            m2 = m2 / 2;
//        }

        if (xcong_all(p1, p2, p3, p4)) return;

        add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
        add_codb(CO_CONG, cg1.p1, cg1.p2, cg1.p3, cg1.p4, cg1.t1, cg1.t2, 0, 0);
        add_cong(R_PYTH_THM, 0, p1, p2, p3, p4, m1, m2);
        pop_codb();
        pop_codb();
    }

    public void search_cg__plus_or_minus(int a, int b, int c, int d, int t1, int t2,
                                         int a1, int b1, int c1, int d1, int t11, int t22) {
        int m1, m2, m3, m4, m5, m6;
        if (t1 * t22 != t2 * t11) return;

        if (a == a1) {
            m1 = b;
            m2 = a;
            m3 = b1;
        } else if (a == b1) {
            m1 = b;
            m2 = a;
            m3 = a1;
        } else if (b == a1) {
            m1 = a;
            m2 = b;
            m3 = b1;
        } else if (b == b1) {
            m1 = a;
            m2 = b;
            m3 = a1;
        } else
            return;

        if (c == c1) {
            m4 = d;
            m5 = c;
            m6 = d1;
        } else if (c == d1) {
            m4 = d;
            m5 = c;
            m6 = c1;
        } else if (d == c1) {
            m4 = c;
            m5 = d;
            m6 = d1;
        } else if (d == d1) {
            m4 = c;
            m5 = d;
            m6 = c1;
        } else
            return;

        if (!xcoll(m1, m2, m3) || !xcoll(m4, m5, m6)) return;
        if (m1 == m4 && m3 == m6 || m1 == m6 && m4 == m3) return;
        if (!ck_dr(m1, m2, m2, m3) || !ck_dr(m4, m5, m5, m6)) return;
        if (xcong_all(m1, m3, m4, m6)) return;
        add_codb(CO_CONG, a, b, c, d, t1, t2, 0, 0);
        add_codb(CO_CONG, a1, b1, c1, d1, t11, t22, 0, 0);
        add_cong(0, 0, m1, m3, m4, m6, t1, t2);
        pop_codb();
        pop_codb();
    }


    public void search_2cong1(cong_seg cg) {
        if (!xcoll4(cg.p1, cg.p2, cg.p3, cg.p4)) return;
        int p1, p2, p3;
        if (cg.p1 == cg.p3) {
            p1 = cg.p2;
            p2 = cg.p1;
            p3 = cg.p4;
        } else if (cg.p1 == cg.p4) {
            p1 = cg.p2;
            p2 = cg.p1;
            p3 = cg.p3;
        } else if (cg.p2 == cg.p3) {
            p1 = cg.p1;
            p2 = cg.p2;
            p3 = cg.p4;
        } else if (cg.p2 == cg.p4) {
            p1 = cg.p1;
            p2 = cg.p2;
            p3 = cg.p3;
        } else
            return;

        int t1 = cg.t1;
        int t2 = cg.t2;
        int m1 = (int) Math.sqrt(t1);
        int m2 = (int) Math.sqrt(t2);
        if (m1 * m1 != t1 || m2 * m2 != t2) return;
        int m3;
        if (ck_dr(p1, p2, p2, p3))
            m3 = Math.abs(m1 + m2);
        else
            m3 = Math.abs(m1 - m2);

        if (m3 != 0) {
            add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
            add_cong(0, 0, p1, p2, p1, p3, m1 * m1, m3 * m3);
            add_cong(0, 0, p2, p3, p1, p3, m2 * m2, m3 * m3);
            pop_codb();
        }
    }

    final public void search_md_cong(midpt md) {
        midpt md1 = all_md.nx;
        while (md1 != null && ch_dep(md1.dep)) {
            if (md1 != md && xcong(md.a, md.b, md1.a, md1.b)) {
                add_codb(CO_MIDP, md.m, md.a, md.b, 0, 0, 0, 0, 0);
                add_codb(CO_MIDP, md1.m, md1.a, md1.b, 0, 0, 0, 0, 0);
                add_codb(CO_CONG, md.a, md.b, md1.a, md1.b, 0, 0, 0, 0);
                add_cong(0, 0, md.m, md.a, md1.m, md1.a);
                add_cong(0, 0, md.m, md.a, md1.m, md1.b);
                add_cong(0, 0, md.m, md.b, md1.m, md1.a);
                add_cong(0, 0, md.m, md.b, md1.m, md1.b);
                pop_codb();
                pop_codb();
                pop_codb();
            }
            md1 = md1.nx;
        }
    }

    public void search_pn_pn(p_line pn) {
        if (!valid(R_PARALLELOGRAM)) return;
        if (pn.type == 0 || pn.no <= 0) return;
        co_db.nx = null;

        int lm = R_PARALLELOGRAM;

        p_line pn1 = all_pn.nx;
        while (pn1 != null && ch_dep(pn1.dep)) {
            if (pn1 != pn) {
                for (int i = 0; i <= pn.no; i++) {
                    l_line ln1 = pn.ln[i];
                    for (int j = i + 1; j <= pn.no; j++) {
                        l_line ln2 = pn.ln[j];
                        for (int l = 0; l <= pn1.no; l++) {
                            l_line ln3 = pn1.ln[l];
                            for (int k = l + 1; k <= pn1.no; k++) {
                                l_line ln4 = pn1.ln[k];
                                int a = inter_ll(ln1, ln3);
                                int b = inter_ll(ln1, ln4);
                                int d = inter_ll(ln2, ln3);
                                int c = inter_ll(ln2, ln4);
                                if (a != 0 && b != 0 && c != 0 && d != 0 && a != b && a != c
                                        && a != d && b != c && b != d && c != d) {
                                    add_codb(CO_PARA, a, b, c, d, 0, 0, 0, 0);
                                    add_codb(CO_PARA, a, d, b, c, 0, 0, 0, 0);
                                    add_ea_pt_t(lm, d, a, b, b, c, d);
                                    add_ea_pt_t(lm, a, d, c, c, b, a);
                                    add_cong(lm, 0, a, b, c, d);
                                    add_cong(lm, 0, a, d, b, c);
                                    tri_type = 1;
                                    add_stri(lm, 1, a, b, c, c, d, a);
                                    add_stri(lm, 1, a, b, d, c, d, b);

                                    l_line l1 = fd_ln(a, c);
                                    l_line l2 = fd_ln(b, d);
                                    int o = inter_ll(l1, l2);
                                    if (o != 0) {
                                        add_codb(CO_COLL, a, o, c, 0, 0, 0, 0, 0);
                                        add_codb(CO_COLL, b, o, d, 0, 0, 0, 0, 0);
                                        add_mid(lm, o, a, c);
                                        add_mid(lm, o, b, d);
                                        pop_codb();
                                        pop_codb();
                                    }
                                    pop_codb();
                                    pop_codb();
                                }

                            }
                        }
                    }
                }
            }
            pn1 = pn1.nx;
        }
    }


    final public void search_as_st(int p1, int p2, l_line ln1, l_line ln2, l_line ln3, l_line ln4) { //sas for st
        if (!valid(R_SAS)) return;
        if (p1 == 0 || p2 == 0) return;
        int a, b, c, d;

        for (int i = 0; i <= ln1.no; i++) {
            for (int j = 0; j <= ln2.no; j++) {
                for (int k = 0; k <= ln3.no; k++) {
                    for (int m = 0; m <= ln4.no; m++) {
                        if (p1 != ln1.pt[i] && p1 != ln2.pt[j] && p2 != ln3.pt[k] && p2 != ln4.pt[m]) {
                            if (!same_tri(p1, ln1.pt[i], ln2.pt[j], p2, ln3.pt[k], ln4.pt[m]) &&
                                    check_eqangle(ln1.pt[i], p1, ln2.pt[j], ln3.pt[k], p2, ln4.pt[m])) {
                                if (!xsim_tri(p1, ln1.pt[i], ln2.pt[j], p2, ln3.pt[k], ln4.pt[m])
                                        && !xcon_tri(p1, ln1.pt[i], ln2.pt[j], p2, ln3.pt[k], ln4.pt[m])) {
                                    a = ln1.pt[i];
                                    b = ln2.pt[j];
                                    c = ln3.pt[k];
                                    d = ln4.pt[m];
                                    if (xcong(p1, a, p1, b) && xcong(p2, c, p2, d) && a != b && c != d) {
                                        add_codb(CO_CONG, p1, a, p1, b, 0, 0, 0, 0);
                                        add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                        add_codb(CO_CONG, p2, c, p2, d, 0, 0, 0, 0);
                                        tri_type = 0;
                                        int dr = check_tri_dr(p1, a, b, p2, c, d);
                                        add_stri(R_ASRA_STRI, dr, p1, a, b, p2, c, d);
                                        pop_codb();
                                        pop_codb();
                                        pop_codb();
                                    } else {
                                        if (check_simtri(p1, a, b, p2, c, d)) {
                                            if (xacong(p1, a, b, p2, c, d)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, a, a, b, p2, c, c, d);
                                                tri_type = 0;
                                                int dr = check_tri_dr(p1, a, b, p2, c, d);
                                                add_stri(R_AA_STRI, dr, p1, a, b, p2, c, d);
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(p1, b, a, p2, d, c)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, b, b, a, p2, d, d, c);
                                                tri_type = 0;
                                                int dr = check_tri_dr(p1, a, b, p2, c, d);
                                                add_stri(R_AA_STRI, dr, p1, a, b, p2, c, d);
                                                pop_codb();
                                                pop_codb();
                                            }
                                        } else if (check_simtri(p1, a, b, p2, d, c)) {
                                            if (xacong(p1, a, b, p2, d, c)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, a, a, b, p2, d, d, c);
                                                tri_type = 0;
                                                int dr = check_tri_dr(p1, a, b, p2, d, c);
                                                add_stri(R_AA_STRI, dr, p1, a, b, p2, d, c);
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(p1, b, a, p2, c, d)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, b, b, a, p2, c, c, d);
                                                tri_type = 0;
                                                int dr = check_tri_dr(p1, a, b, p2, d, c);
                                                add_stri(R_AA_STRI, dr, p1, a, b, p2, d, c);
                                                pop_codb();
                                                pop_codb();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public final void search_cg_st0(int p1, int p2, int p3, int p4, int r1, int r2) {
//        if (!valid(R_SSS) || !valid(R_STRI)) return;

        cong_seg cg = all_cg.nx;
        if (r1 != r2)
            cg = all_rg.nx;

        int t1, t2, t3;
        t1 = t2 = t3 = 0;
        if (p1 == p3) {
            t1 = p1;
            t2 = p2;
            t3 = p4;
        } else if (p1 == p4) {
            t1 = p1;
            t2 = p2;
            t3 = p3;
        } else if (p2 == p3) {
            t1 = p2;
            t2 = p1;
            t3 = p4;
        } else if (p2 == p4) {
            t1 = p2;
            t2 = p1;
            t3 = p3;
        } else
            return;

        while (cg != null && ch_dep(cg.dep)) {
            if (cg.type == 0) {
                cg = cg.nx;
                continue;
            }
            int p5 = cg.p1;
            int p6 = cg.p2;
            int p7 = cg.p3;
            int p8 = cg.p4;
            int t4, t5, t6;
            t4 = t5 = t6 = 0;
            if (p5 == p7) {
                t4 = p5;
                t5 = p6;
                t6 = p8;
            } else if (p5 == p8) {
                t4 = p5;
                t5 = p6;
                t6 = p7;
            } else if (p6 == p7) {
                t4 = p6;
                t5 = p5;
                t6 = p8;
            } else if (p6 == p8) {
                t4 = p6;
                t5 = p5;
                t6 = p7;
            } else {
                cg = cg.nx;
                continue;
            }
            if (t1 == 0 || t2 == 0 || t3 == 0 || t4 == 0 || t5 == 0 || t6 == 0
                    || same_tri(t1, t2, t3, t4, t5, t6)) {
                cg = cg.nx;
                continue;
            }
            if (t1 == t4 && t2 == t5 && t3 == t6) {
                cg = cg.nx;
                continue;
            }
            if (xcoll(t1, t2, t3) || xcoll(t4, t5, t6)) {//|| xsim_tri(t2, t1, t3, t5, t4, t6) || xcon_tri(t2, t1, t3, t5, t4, t6)) {
                cg = cg.nx;
                continue;
            }
            int r3 = cg.t1;
            int r4 = cg.t2;


            if (t1 == t4 && t1 == 4) {
                int k = 0;
            }
            if (r1 * r4 == r2 * r3) {
                search_cong_st(t1, t2, t3, t4, t5, t6, r1, r2);
            }
            if (r1 * r3 == r2 * r4) {
                search_cong_st(t1, t2, t3, t4, t6, t5, r1, r2);
            }

            cg = cg.nx;
        }
    }

    public void search_cong_st(int t1, int t2, int t3, int t4, int t5, int t6, int r1, int r2) {
        if (!check_eqangle_t(t2, t1, t1, t3, t5, t4, t4, t6)) return;

        if (xacong(t2, t1, t3, t5, t4, t6)) {
            add_codb(CO_CONG, t1, t2, t1, t3, r1, r2, 0, 0);
            add_codb(CO_CONG, t4, t6, t4, t5, r1, r2, 0, 0);
            add_codb(CO_ACONG, t2, t1, t1, t3, t5, t4, t4, t6);
            tri_type = 0;
            add_stri(R_ASRA_STRI, 1, t2, t1, t3, t5, t4, t6);
            pop_codb();
            pop_codb();
            pop_codb();
        }
        if (xacong(t2, t1, t3, t6, t4, t5)) {
            add_codb(CO_CONG, t1, t2, t1, t3, r1, r2, 0, 0);
            add_codb(CO_CONG, t4, t5, t4, t6, r1, r2, 0, 0);
            add_codb(CO_ACONG, t2, t1, t1, t3, t6, t4, t4, t5);
            tri_type = 0;
            add_stri(R_ASRA_STRI, -1, t2, t1, t3, t5, t4, t6);
            pop_codb();
            pop_codb();
            pop_codb();
        }

    }

    public void search_st1(int dr, int p1, int p2, int p3, int p4, int p5, int p6) {
        if (xcong(p1, p2, p2, p3) && !xcong(p4, p5, p5, p6)) {
            add_codb(CO_CONG, p1, p2, p2, p3, 0, 0, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p4, p5, p5, p6);
            pop_codb();
            pop_codb();
        }
        if (xcong(p1, p2, p1, p3) && !xcong(p4, p5, p4, p6)) {
            add_codb(CO_CONG, p1, p2, p1, p3, 0, 0, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p4, p5, p4, p6);
            pop_codb();
            pop_codb();
        }
        if (xcong(p1, p3, p2, p3) && !xcong(p4, p5, p5, p6)) {
            add_codb(CO_CONG, p1, p3, p2, p3, 0, 0, 0, 0);
            add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);
            add_cong(R_ST_RAAS, 0, p4, p6, p5, p6);
            pop_codb();
            pop_codb();
        }
        add_codb(CO_STRI, p1, p2, p3, p4, p5, p6, 0, 0);

        if (dr > 0) {
            add_ea_pt_t(R_ST_RAAS, p1, p2, p3, p4, p5, p6);
            add_ea_pt_t(R_ST_RAAS, p1, p3, p2, p4, p6, p5);
            add_ea_pt_t(R_ST_RAAS, p2, p1, p3, p5, p4, p6);
        } else {
            add_ea_pt_t(R_ST_RAAS, p1, p2, p3, p6, p5, p4);
            add_ea_pt_t(R_ST_RAAS, p1, p3, p2, p5, p6, p4);
            add_ea_pt_t(R_ST_RAAS, p2, p1, p3, p6, p4, p5);
        }
        add_stct_at(dr, p1, p2, p3, p4, p5, p6);
        add_stct_at(dr, p2, p3, p1, p5, p6, p4);
        add_stct_at(dr, p3, p1, p2, p6, p4, p5);

        pop_codb();
    }


    public boolean get_at_dr(anglet at, int p1, int p2) {
        if (on_ln(p1, at.l1) && on_ln(p2, at.l2)) return true;
        return false;
    }

    public void add_stct_at(int dr, int p1, int p2, int p3, int p4, int p5, int p6) {

        anglet at1 = fd_at(p2, p1, p3);
        anglet at2 = fd_at(p5, p4, p6);

        if (at1 == null && at2 != null && ch_dep(at2.id)) {

            add_codb(CO_TANG, p5, p4, p6, at2.get_val(p5, p6), 0, 0, 0, 0);
            if (dr > 0)
                add_at(R_ST_RAAS, p2, p1, p3, at2.v);
            else
                add_at(R_ST_RAAS, p2, p1, p3, -at2.v);
            pop_codb();
        } else if (at1 != null && at2 == null && ch_dep(at1.id)) {
            add_codb(CO_TANG, p2, p1, p3, at1.get_val(p2, p3), 0, 0, 0, 0);
            if (dr > 0)
                add_at(R_ST_RAAS, p5, p4, p6, at1.v);
            else
                add_at(R_ST_RAAS, p5, p4, p6, -at1.v);
            pop_codb();
        }
    }

    public void adj_st(sim_tri st) {
        sim_tri st1 = all_st.nx;
        tri_type = 0;

        while (st1 != null && ch_dep(st1.dep)) {
            if (st1 != st)
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        if (i != j)
                            for (int k = 0; k < 3; k++)
                                if (k != i && k != j) {

                                    if (st.p1[0] == st1.p1[i] && st.p1[1] == st1.p1[j] && st.p1[2] == st1.p1[k] &&
                                            (st.p2[0] != st1.p2[i] || st.p2[1] != st1.p2[j] || st.p2[2] != st1.p2[k])) {
                                        add_codb(CO_STRI, st.p1[0], st.p1[1], st1.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_STRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();

                                    } else if (st.p1[0] == st1.p2[i] && st.p1[1] == st1.p2[j] && st.p1[2] == st1.p2[k] &&
                                            (st.p2[0] != st1.p1[i] || st.p2[1] != st.p1[j] || st.p2[2] != st.p1[k])) {
                                        add_codb(CO_STRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_STRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();
                                    } else if (st.p2[0] == st1.p1[i] && st.p2[1] == st1.p1[j] && st.p2[2] == st1.p1[k] &&
                                            (st.p1[0] != st1.p2[i] || st.p1[1] != st1.p2[j] || st.p1[2] != st1.p2[k])) {
                                        add_codb(CO_STRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_STRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();
                                    } else if (st.p2[0] == st1.p2[i] && st.p2[1] == st1.p2[j] && st.p2[2] == st1.p2[k] &&
                                            (st.p1[0] != st1.p1[i] || st.p1[1] != st1.p1[j] || st.p1[2] != st1.p1[k])) {
                                        add_codb(CO_STRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_STRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        pop_codb();
                                        pop_codb();
                                    }
                                }
            st1 = st1.nx;
        }

    }

    public void adj_ct(sim_tri st) {
        sim_tri st1 = all_ct.nx;
        tri_type = 1;

        while (st1 != null && ch_dep(st1.dep)) {
            if (st1 != st)
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        if (i != j)
                            for (int k = 0; k < 3; k++)
                                if (k != i && k != j) {

                                    if (st.p1[0] == st1.p1[i] && st.p1[1] == st1.p1[j] && st.p1[2] == st1.p1[k] &&
                                            (st.p2[0] != st1.p2[i] || st.p2[1] != st1.p2[j] || st.p2[2] != st1.p2[k])) {
                                        add_codb(CO_CTRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_CTRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();

                                    } else if (st.p1[0] == st1.p2[i] && st.p1[1] == st1.p2[j] && st.p1[2] == st1.p2[k] &&
                                            (st.p2[0] != st1.p1[i] || st.p2[1] != st.p1[j] || st.p2[2] != st.p1[k])) {
                                        add_codb(CO_CTRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_CTRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();
                                    } else if (st.p2[0] == st1.p1[i] && st.p2[1] == st1.p1[j] && st.p2[2] == st1.p1[k] &&
                                            (st.p1[0] != st1.p2[i] || st.p1[1] != st1.p2[j] || st.p1[2] != st1.p2[k])) {
                                        add_codb(CO_CTRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_CTRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        pop_codb();
                                        pop_codb();
                                    } else if (st.p2[0] == st1.p2[i] && st.p2[1] == st1.p2[j] && st.p2[2] == st1.p2[k] &&
                                            (st.p1[0] != st1.p1[i] || st.p1[1] != st1.p1[j] || st.p1[2] != st1.p1[k])) {
                                        add_codb(CO_CTRI, st.p1[0], st.p1[1], st.p1[2], st.p2[0], st.p2[1], st.p2[2], 0, 0);
                                        add_codb(CO_CTRI, st1.p1[0], st1.p1[1], st1.p1[2], st1.p2[0], st1.p2[1], st1.p2[2], 0, 0);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        add_stri(0, st.dr * st1.dr, st.p1[0], st.p1[1], st.p1[2], st1.p2[i], st1.p2[j], st1.p2[k]);
                                        add_stri(0, st.dr * st1.dr, st.p2[0], st.p2[1], st.p2[2], st1.p1[i], st1.p1[j], st1.p1[k]);
                                        pop_codb();
                                        pop_codb();
                                    }
                                }
            st1 = st1.nx;
        }

    }

    public void search_tn_cg(cong_seg cg) {
        int o = 0;
        int p1 = cg.p1;
        int p2 = cg.p2;
        int p3 = cg.p3;
        int p4 = cg.p4;
        int t1, t2;

        if (p1 == p3) {
            o = p1;
            p1 = p4;
            t1 = cg.t2;
            t2 = cg.t1;
        } else if (p1 == p4) {
            o = p1;
            p1 = p3;
            t1 = cg.t2;
            t2 = cg.t1;
        } else if (p2 == p3) {
            o = p2;
            p2 = p4;
            t1 = cg.t1;
            t2 = cg.t2;
        } else if (p2 == p4) {
            o = p2;
            p2 = p3;
            t1 = cg.t1;
            t2 = cg.t2;
        } else {
            o = 0;
            return;
        }
        if (p1 == p2) {
            o = 0;
            return;
        }
        if (xperp(o, p1, o, p2)) {
            add_codb(CO_CONG, o, p1, o, p2, 0, 0, 0, 0);
            add_codb(CO_PERP, o, p1, o, p2, 0, 0, 0, 0);
            add_cong(R_PYTH_THM, 0, o, p1, p1, p2, t1, (t1 + t2));
            add_cong(R_PYTH_THM, 0, o, p2, p1, p2, t2, (t1 + t2));
            pop_codb();
            pop_codb();
        }
    }


    final void search_ln(l_line ln) {

        if (ln != null && ln.type != 0) {
            l_line l = fd_lnl(ln);
            if (l != null && ln != l) {
                int k = 0;
            }
        }
        cong_seg cg = all_cg.nx;
        while (cg != null && ch_dep(cg.dep)) {
            if (cg.type == 0) {
                cg = cg.nx;
                continue;
            }
            int t1, t2, t3;
            t1 = t2 = t3 = 0;
            int p1 = cg.p1;
            int p2 = cg.p2;
            int p3 = cg.p3;
            int p4 = cg.p4;

            if (p1 == p3) {
                t1 = p1;
                t2 = p2;
                t3 = p4;
            } else if (p1 == p4) {
                t1 = p1;
                t2 = p2;
                t3 = p3;
            } else if (p2 == p3) {
                t1 = p2;
                t2 = p1;
                t3 = p4;
            } else if (p2 == p4) {
                t1 = p2;
                t2 = p1;
                t3 = p3;
            }
            if (t1 != 0 && t2 != 0 && t3 != 0 &&
                    on_ln(t1, ln) && on_ln(t2, ln) && on_ln(t3, ln) && !xmid(t1, t2, t3)) {
                add_codb(CO_CONG, t1, t2, t1, t3, 0, 0, 0, 0);
                add_codb(CO_COLL, t1, t2, t3, 0, 0, 0, 0, 0);
                add_mid(CO_MIDP, t1, t2, t3);
                pop_codb();
                pop_codb();
            }
            cg = cg.nx;
        }
    }

    public void adj_as0(int p, l_line l1, l_line l2, l_line l3, l_line l4) {
        if (p == 0) return;

        for (int i = 0; i <= l1.no; i++) {
            if (l1.pt[i] != p)
                for (int j = 0; j <= l2.no; j++) {
                    if (l2.pt[j] != p)
                        for (int k = 0; k <= l3.no; k++) {
                            if (l3.pt[k] != p)
                                for (int m = 0; m <= l4.no; m++) {
                                    if (l4.pt[m] != p) {
                                        int a = l1.pt[i];
                                        int b = l2.pt[j];
                                        int c = l3.pt[k];
                                        int d = l4.pt[m];
                                        if (check_eqangle_t(a, p, b, p, c, p, d, p)) {
                                            add_codb(CO_ACONG, a, p, p, b, c, p, p, d);
                                            add_ea_pt_t(0, a, p, c, b, p, d);
                                            pop_codb();
                                        }
                                    }
                                }
                        }
                }
        }


    }

    protected void search_as_tn_as(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {

        if (this.isPFull() && d_base == 0) return;

        if (p1 == 0 || p2 == 0) return;

        for (int i = 0; i <= l1.no; i++) {
            if (l1.pt[i] != p1)
                for (int j = 0; j <= l2.no; j++) {
                    if (l2.pt[j] != p1)
                        for (int k = 0; k <= l3.no; k++) {
                            if (l3.pt[k] != p2)
                                for (int m = 0; m <= l4.no; m++) {
                                    if (l4.pt[m] != p2) {
                                        int a = l1.pt[i];
                                        int b = l2.pt[j];
                                        int c = l3.pt[k];
                                        int d = l4.pt[m];
                                        if (check_eqangle_t(a, p1, b, p1, c, p2, d, p2)) {
                                            if (xperp(p1, a, a, b) && xperp(p2, c, c, d)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_PERP, p1, a, a, b, 0, 0, 0, 0);
                                                add_codb(CO_PERP, p2, c, c, d, 0, 0, 0, 0);
                                                add_ea_pt_t(0, p1, b, a, p2, d, c);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            } else if (xperp(p1, b, b, a) && xperp(p2, d, d, c)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_PERP, p1, b, b, a, 0, 0, 0, 0);
                                                add_codb(CO_PERP, p2, d, d, c, 0, 0, 0, 0);
                                                add_ea_pt_t(0, p1, a, b, p2, c, d);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(p1, a, b, p2, c, d)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, a, a, b, p2, c, c, d);
                                                add_ea_pt_t(0, p1, b, a, p2, d, c);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(p1, b, a, p2, d, c)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, b, b, a, p2, d, d, c);
                                                add_ea_pt_t(0, p1, a, b, p2, c, d);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(p1, b, a, d, c, p2)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, p1, b, b, a, d, c, c, p2);
                                                add_ea_pt_t(0, b, a, p1, p2, d, c);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            } else if (xacong(b, a, p1, p2, d, c)) {
                                                add_codb(CO_ACONG, a, p1, p1, b, c, p2, p2, d);
                                                add_codb(CO_ACONG, b, a, a, p1, p2, d, d, c);
                                                add_ea_pt_t(0, p1, b, a, d, c, p2);
                                                pop_codb();
                                                pop_codb();
                                                pop_codb();
                                            }
                                        }
                                    }
                                }
                        }
                }
        }
    }


    public void search_at(anglet at) {
        search_at0(at);
        search_at1(at);
        search_at_tn(at);
        search_at_ass(at);
        adj_at(at);
    }

    public void search_at_tn(anglet at) {
        l_line l1 = at.l1;
        l_line l2 = at.l2;
        int p = at.p;
        int p1, p2;


        for (int i = 0; i <= l1.no; i++)
            for (int j = 0; j <= l2.no; j++) {
                p1 = l1.pt[i];
                p2 = l2.pt[j];
                if (p1 != p && p2 != p && p1 != p2) {
                    l_line ln = fd_ln(p1, p2);
                    if (ln != null) {
                        if (ln_perp(ln, l1)) {
                            int v = getAtv(p1, p, p2, at);
                            add_tri_tn_at(p1, p, p2, v);
                        } else if (ln_perp(ln, l2)) {
                            int v = getAtv(p2, p, p1, at);
                            add_tri_tn_at(p2, p, p1, v);
                        } else
                            continue;
                    }
                }
            }
    }

    public void search_tn_at(t_line tn) {
        l_line l1 = tn.l1;
        l_line l2 = tn.l2;
        int p = inter_lls(l1, l2);

        add_at(0, l1, l2, A_90);
        search_tn_ats(l1, l2, p, tn);
        search_tn_ats(l2, l1, p, tn);
    }

    public void search_tn_ats(l_line l1, l_line l2, int p, t_line tn) {
        if (p == 0) return;
        anglet at1;
        int p1, p2;

        for (int i = 0; i <= l1.no; i++)
            for (int j = 0; j <= l2.no; j++) {
                p1 = l1.pt[i];
                p2 = l2.pt[j];
                if (p1 != p && p2 != p && p1 != p2) {
                    at1 = fd_at(p, p1, p2);
                    if (at1 != null) {
                        int v = getAtv(p, p1, p2, at1);
                        add_tri_tn_at(p, p1, p2, v);
                    }
                }
            }

    }

    public void add_tri_tn_at(int p, int p1, int p2, int v) // pp1p2 = v;
    {
        int lm;
        if (v == A_30 || v == -A_30) {
            lm = R_AG_SPECIAL;
            add_codb(CO_PERP, p, p1, p, p2, 0, 0, 0, 0);
            add_codb(CO_TANG, p, p1, p2, v, 0, 0, 0, 0);
            if (valid(R_PYTH_THM)) {
                add_cong(lm, 0, p, p1, p1, p2, 3, 4);
                add_cong(lm, 0, p, p1, p, p2, 3, 1);
                add_cong(lm, 0, p, p2, p1, p2, 1, 4);
            }
            pop_codb();
            pop_codb();
        } else if (v == A_45 || v == -A_45) {
            lm = R_AG_SPECIAL;
            add_codb(CO_PERP, p, p1, p, p2, 0, 0, 0, 0);
            add_codb(CO_TANG, p, p1, p2, v, 0, 0, 0, 0);
            if (valid(R_PYTH_THM)) {
                add_cong(lm, 0, p, p1, p1, p2, 1, 2);
                add_cong(lm, 0, p, p2, p1, p2, 1, 2);
                add_cong(lm, 0, p, p1, p, p2);
            }
            pop_codb();
            pop_codb();
        } else if (v == A_60 || v == -A_60) {
            lm = R_AG_SPECIAL;
            add_codb(CO_PERP, p, p1, p, p2, 0, 0, 0, 0);
            add_codb(CO_TANG, p, p1, p2, v, 0, 0, 0, 0);
            if (valid(R_PYTH_THM)) {
                add_cong(lm, 0, p, p1, p1, p2, 1, 4);
                add_cong(lm, 0, p, p2, p1, p2, 3, 4);
                add_cong(lm, 0, p, p1, p, p2, 1, 3);
            }
            pop_codb();
            pop_codb();
        }
    }

    public void search_at0(anglet at) {
        anglet at1 = all_at.nx;
        while (at1 != null && ch_dep(at1.dep)) {
            if (at != at1)
                search_at_at(at, at1);
            at1 = at1.nx;
        }
    }


    public void search_at1(anglet at) {
        l_line l1 = at.l1;
        l_line l2 = at.l2;
        int p = at.p;
        for (int i = 0; i <= l1.no; i++)
            if (l1.pt[i] != p)
                for (int j = 0; j <= l2.pt[j]; j++)
                    if (l2.pt[j] != p) {
                        int a = l1.pt[i];
                        int b = l2.pt[j];
                        if (xcong(p, a, p, b) && !xcoll(p, a, b) && !check_coll(p, a, b)) {
                            int t = getAtv(a, p, b, at.v);
                            int v = (A_180 - Math.abs(t));
                            if (v % 2 == 0) {
                                v = v / 2;
                                add_codb(CO_CONG, p, a, p, b, 0, 0, 0, 0);
                                add_codb(CO_TANG, get_lpt1(l1, at.p), at.p, get_lpt1(l2, at.p), at.v, 0, 0, 0, 0);
                                add_at(R_ISOCELES, p, a, b, v);
                                add_at(R_ISOCELES, a, b, p, v);
                                pop_codb();
                                pop_codb();
                            }
                        }
                    }
    }

    public void search_at_at(anglet at, anglet at1) {
        if (at == at1) return;
        l_line l1 = at.l1;
        l_line l2 = at.l2;
        l_line l3 = at1.l1;
        l_line l4 = at1.l2;
        l_line ln1, ln2, ln3;
        int t1, t2, p1, p2;

        ln1 = ln2 = ln3 = null;
        t1 = t2 = 0;
        p1 = at.p;
        p2 = at1.p;

        if (l2 == l3) {
            ln1 = l1;
            ln2 = l2;
            ln3 = l4;
            t1 = at.v;
            t2 = at1.v;
        } else if (l1 == l3) {
            ln1 = l2;
            ln2 = l1;
            ln3 = l4;
            t1 = -at.v;
            t2 = at1.v;
        } else if (l2 == l4) {
            ln1 = l1;
            ln2 = l2;
            ln3 = l3;
            t1 = at.v;
            t2 = -at1.v;
        } else if (l1 == l4) {
            ln1 = l2;
            ln2 = l1;
            ln3 = l3;
            t1 = -at.v;
            t2 = -at1.v;
        }
        int p3 = inter_lls(ln1, ln3);
        if (p3 != 0 && ln1 != null && ln2 != null && ln3 != null) {
            anglet att = add_at(104, ln1, ln3, t1 + t2);
            if (att != null) {
                co_db.nx = null;
                co_xy.nx = null;
                cond co = add_coxy(CO_TANG);
                co.u.at = new anglet(p1, ln1, ln2, t1);
                co = add_coxy(CO_TANG);
                co.u.at = new anglet(p2, ln2, ln3, t2);
                att.co = co;
            }

        }


        if (check_at_eq(at.v, at1.v)) {
        } else if (check_at_eq(at.v, -at1.v)) {
            l3 = at1.l2;
            l4 = at1.l1;
        } else
            return;

        angles as = add_ea_ln(131, l1, l2, l3, l4);
        if (as != null) {
            co_db.nx = null;
            co_xy.nx = null;
            cond co = add_coxy(CO_TANG);
            co.u.at = at;
            co = add_coxy(CO_TANG);
            co.u.at = at1;
            as.co = co;
        }
    }

    public void search_rg_at(cong_seg cg) {
        int t1 = cg.p1;
        int t2 = cg.p2;
        int t3 = cg.p3;
        int t4 = cg.p4;
        int p1, p2, o;
        o = p1 = p2 = 0;

        if (t1 == t3) {
            o = t1;
            p1 = t2;
            p2 = t4;
        } else if (t1 == t4) {
            o = t1;
            p1 = t2;
            p2 = t3;
        } else if (t2 == t3) {
            o = t2;
            p1 = t1;
            p2 = t4;
        } else if (t2 == t4) {
            o = t2;
            p1 = t1;
            p2 = t3;
        } else
            o = 0;
        if (p1 == p2)
            o = 0;
        if (o != 0) {
            l_line l1, l2, l3;
            if (search_only_exists_ln()) {
                l1 = fd_ln(o, p1);
                l2 = fd_ln(o, p2);
                l3 = fd_ln(p1, p2);
            } else {
                l1 = fadd_ln(o, p1);
                l2 = fadd_ln(o, p2);
                l3 = fadd_ln(p1, p2);
            }
            if (l1 != null && l2 != null && l3 != null) {
                if (cg.t1 == cg.t2) {
                    anglet at = fd_at(l1, l2);
                    if (at != null) {

                        int v;
                        if (l1 == at.l1 && l2 == at.l2)
                            v = at.v;
                        else
                            v = -at.v;

                        int t = getAtv(p1, o, p2, v);
                        t = (A_180 - Math.abs(t));
                        if (t % 2 == 0) {

                            t = t / 2;
                            add_codb(CO_TANG, p1, o, p2, v, 0, 0, 0, 0);
                            add_codb(CO_CONG, o, p1, o, p2, 0, 0, 0, 0);
                            add_at(R_ISOCELES, o, p1, p2, t);
                            add_at(R_ISOCELES, o, p2, p1, t);
                            pop_codb();
                            pop_codb();
                        }
                    }
                    if (xcong(o, p1, p1, p2)) {
                        add_codb(CO_CONG, o, p1, o, p2, 0, 0, 0, 0);
                        add_codb(CO_CONG, o, p1, p1, p2, 0, 0, 0, 0);
                        add_codb(CO_CONG, o, p2, p1, p2, 0, 0, 0, 0);
                        add_at(R_TRI_EQ, o, p1, p2, A_60);
                        add_at(R_TRI_EQ, p1, p2, o, A_60);
                        add_at(R_TRI_EQ, p2, o, p1, A_60);
                        pop_codb();
                        pop_codb();
                        pop_codb();
                    }
                }
                if (cg.t1 == 4 * cg.t2 && xperp(p1, p2, o, p2)) {
                    add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                    add_codb(CO_PERP, p1, p2, o, p2, 0, 0, 0, 0);
                    add_at(R_AG_SPECIAL, p1, o, p2, A_60);
                    add_at(R_AG_SPECIAL, p2, p1, o, A_30);
                    pop_codb();
                    pop_codb();
                } else if (cg.t2 == 4 * cg.t1 && xperp(p1, p2, o, p1)) {
                    add_codb(CO_CONG, cg.p1, cg.p2, cg.p3, cg.p4, cg.t1, cg.t2, 0, 0);
                    add_codb(CO_PERP, p1, p2, o, p1, 0, 0, 0, 0);
                    add_at(R_AG_SPECIAL, p1, o, p2, A_60);
                    add_at(R_AG_SPECIAL, p1, p2, o, A_30);
                    pop_codb();
                    pop_codb();
                }
            }
        }
    }

    public void search_at_as(anglet at, angles as) {
        l_line l1, l2, l3, l4;
        l1 = at.l1;
        l2 = at.l2;
        int vs = at.v;
        int v = vs;
        l3 = l4 = null;
        if (l1 == as.l1 && l2 == as.l2) {
            l3 = as.l3;
            l4 = as.l4;
        } else if (l1 == as.l2 && l2 == as.l1) {
            l3 = as.l4;
            l4 = as.l3;
        } else if (l1 == as.l3 && l2 == as.l4) {
            l3 = as.l1;
            l4 = as.l2;
        } else if (l1 == as.l4 && l2 == as.l3) {
            l3 = as.l2;
            l4 = as.l1;
        }
        if (l3 != null && l4 != null && !xatcong(l3, l4)) {
            int t1 = inter_lls(l1, l2);
            int t2 = inter_lls(l3, l4);

            if (t1 != 0 && t2 != 0) {
                anglet at1 = add_at(0, l3, l4, v);
                if (at1 != null) {
                    co_xy.nx = null;
                    cond co = add_coxy(CO_TANG);
                    co.u.at = at;
                    co = add_coxy(CO_ACONG);
                    co.u.as = new angles(l1, l2, l3, l4);
                    at1.co = co;
                }
            }
        }
    }

    public void search_at_ass(anglet at) {
        angles as = all_as.nx;

        while (as != null && ch_dep(as.dep)) {
            if (as.type == 0) {
                as = as.nx;
                continue;
            }
            search_at_as(at, as);
            as = as.nx;
        }
    }

    public void add_as_at(angles as) {
        l_line l1 = as.l1;
        l_line l2 = as.l2;
        l_line l3 = as.l3;
        l_line l4 = as.l4;
        if (l1 == l2 || xcoll_ln(l1, l2)) return;
        if (l3 == l4 || xcoll_ln(l3, l4)) return;
        anglet at1 = fd_at(l1, l2);
        anglet at2 = fd_at(l3, l4);
        if (at1 == at2) return;

        anglet at = null;
        if (at1 != null && at2 == null && ch_dep(at1.dep)) {
            if (at1.l1 == l1 && at1.l2 == l2)
                at = add_at(0, l3, l4, at1.v);
            else
                at = add_at(0, l4, l3, at1.v);
        } else if (at1 == null && at2 != null && ch_dep(at2.dep)) {
            if (at2.l1 == l3 && at2.l2 == l4)
                at = add_at(0, l1, l2, at2.v);
            else
                at = add_at(0, l2, l1, at2.v);
        }
        if (at != null) {
            co_xy.nx = null;
            cond co = add_coxy(CO_TANG);
            if (at1 != null)
                co.u.at = at1;
            else if (at2 != null)
                co.u.at = at2;
            co = add_coxy(CO_ACONG);
            co.u.as = as;
            at.co = co;
        }
    }


    public void adj_at(anglet at) {
        l_line l1 = at.l1;
        l_line l2 = at.l2;

        if (l1.type != 0 && l2.type != 0) return;

        if (l1.type == 0)
            l1 = fd_ln(l1.pt[0], l1.pt[1]);
        if (l2.type == 0)
            l2 = fd_ln(l2.pt[0], l2.pt[1]);


        anglet at1 = add_at(188, l1, l2, at.v);
        at.type = 0;
        if (at1 != null) {
            co_xy.nx = null;
            cond co = add_coxy(CO_TANG);
            co.u.at = at;
            at1.co = co;
        }
    }

    public boolean ck_ateq(int a, int b) {
        return a == b || Math.abs(a - b) == A_180;
    }

//////////////////////////////////////////////////////

    public void search_tn_atn(t_line tn) {
        if (tn.type == 0) return;

        l_line l1 = tn.l1;
        l_line l2 = tn.l2;

        int p1, p2, p;
        p = inter_lls(l1, l2);

        l_line ln;

        for (int t1 = 0; t1 <= l1.no; t1++)
            for (int t2 = 0; t2 <= l2.no; t2++) {
                p1 = l1.pt[t1];
                p2 = l2.pt[t2];
                if (p1 != p2) {
                    ln = fadd_ln(p1, p2);
                    l_line[] ls = split_ln(p1, ln);
                    l_line[] xs = split_ln(p2, ln);
                    l_line[] ls1 = split_ln(p1, l1);
                    l_line[] ls2 = split_ln(p2, l2);

                    for (int i = 0; i < ls.length; i++)
                        for (int l = 0; l < xs.length; l++)
                            for (int j = 0; j < ls1.length; j++)
                                for (int k = 0; k < ls2.length; k++) {
                                    if (check_llatn(p1, p2, ls1[j], ls[i], xs[l], ls2[k])) {
                                        add_codb(CO_PERP, get_lpt1(ls1[j], p1), p1, p2, get_lpt1(ls2[k], p2), 0, 0, 0, 0);
                                        add_atn(0, ls1[j], ls[i], xs[l], ls2[k]);
                                        pop_codb();
                                    }
                                }
                }
            }
    }

    public void search_atn(angtn atn) {

        if (atn.type == 0) return;

        adj_atn(atn);
        search_atn_atn(atn);
        search_atn_as(atn);
        search_atn_at(atn);
    }


    public void search_atn_atn(angtn atn) {
        angtn a1 = all_atn.nx;
        l_line ln1, ln2, ln3, ln4;
        while (a1 != null && ch_dep(a1.dep)) {

            if (a1.type == 0 || a1 == atn) {
                a1 = a1.nx;
                continue;
            }

            if (a1.ln1 == atn.ln1 && a1.ln2 == atn.ln2) {
                ln1 = a1.ln3;
                ln2 = a1.ln4;
                ln3 = atn.ln3;
                ln4 = atn.ln4;
            } else if (a1.ln1 == atn.ln3 && a1.ln2 == atn.ln4) {
                ln1 = a1.ln3;
                ln2 = a1.ln4;
                ln3 = atn.ln1;
                ln4 = atn.ln2;
            } else if (a1.ln3 == atn.ln1 && a1.ln4 == atn.ln2) {
                ln1 = a1.ln1;
                ln2 = a1.ln2;
                ln3 = atn.ln3;
                ln4 = atn.ln4;
            } else if (a1.ln3 == atn.ln3 && a1.ln4 == atn.ln4) {
                ln1 = a1.ln1;
                ln2 = a1.ln2;
                ln3 = atn.ln1;
                ln4 = atn.ln2;
            } else
                ln1 = ln2 = ln3 = ln4 = null;

            if (ln1 != null && ln1 != ln2 && ln3 != ln4) {
                angles as = add_ea_ln(132, ln1, ln2, ln3, ln4);
                if (as != null) {
                    co_xy.nx = null;
                    cond co = add_coxy(CO_ATNG);
                    co.u.atn = atn;
                    co = add_coxy(CO_ATNG);
                    co.u.atn = a1;
                    as.co = co;
                }
            }
            a1 = a1.nx;
        }
    }

    public void search_atn_as(angtn atn) {
        angles as = all_as.nx;
        while (as != null && ch_dep(as.dep)) {
            if (as.type == 0) {
                as = as.nx;
                continue;
            }
            search_atnas(atn, as.l1, as.l2, as.l3, as.l4, as);
            search_atnas(atn, as.l3, as.l4, as.l1, as.l2, as);
            as = as.nx;
        }
    }

    public void search_as_atn(angles as) {
        angtn a1 = all_atn.nx;

        while (a1 != null && ch_dep(a1.dep)) {
            if (a1.type == 0) {
                a1 = a1.nx;
                continue;
            }
            search_atnas(a1, as.l1, as.l2, as.l3, as.l4, as);
            search_atnas(a1, as.l3, as.l4, as.l1, as.l2, as);
            a1 = a1.nx;
        }
    }


    public void search_atn_at(angtn atn) {
        anglet at = all_at.nx;
        while (at != null && ch_dep(at.dep)) {
            if (at.type == 0) {
                at = at.nx;
                continue;// at should be implemented in ch_ln()
            }
            search_atatn(at, atn);
            at = at.nx;
        }

    }

    public void search_at_atn(anglet at) {
        angtn a1 = all_atn.nx;
        while (a1 != null && ch_dep(a1.dep)) {
            if (a1.type == 0) {
                a1 = a1.nx;
                continue;
            }
            search_atatn(at, a1);
            a1 = a1.nx;
        }

    }

    public void search_atnas(angtn atn, l_line l1, l_line l2, l_line l3, l_line l4, angles as) {

        l_line ln1, ln2, ln3, ln4, s1, s2, s3, s4;
        if (atn.ln1 == l1 && atn.ln2 == l2) {
            ln1 = atn.ln3;
            ln2 = atn.ln4;
            ln3 = l3;
            ln4 = l4;
            s1 = atn.ln1;
            s2 = atn.ln2;
            s3 = l1;
            s4 = l2;
        } else if (atn.ln1 == l2 && atn.ln2 == l1) {
            ln1 = atn.ln3;
            ln2 = atn.ln4;
            ln3 = l4;
            ln4 = l3;
            s1 = atn.ln1;
            s2 = atn.ln2;
            s3 = l2;
            s4 = l1;

        } else if (atn.ln3 == l1 && atn.ln4 == l2) {
            ln1 = atn.ln1;
            ln2 = atn.ln2;
            ln3 = l3;
            ln4 = l4;
            s1 = atn.ln3;
            s2 = atn.ln4;
            s3 = l1;
            s4 = l2;

        } else if (atn.ln3 == l2 && atn.ln4 == l1) {
            ln1 = atn.ln1;
            ln2 = atn.ln2;
            ln3 = l4;
            ln4 = l3;
            s1 = atn.ln3;
            s2 = atn.ln4;
            s3 = l2;
            s4 = l1;
        } else
            ln1 = ln2 = ln3 = ln4 = s1 = s2 = s3 = s4 = null;
        if (ln1 != null) {
            int p1 = inter_lls(ln1, ln2);
            int p = inter_lls(ln3, ln4);
            l_line ls3[] = split_ln(p, ln3);
            l_line ls4[] = split_ln(p, ln4);
            for (int i = 0; i < ls3.length; i++)
                for (int j = 0; j < ls4.length; j++) {
                    if (this.check_llatn(p1, p, ln1, ln2, ls3[i], ls4[j])) {
                        angtn a1 = add_atn(133, ln1, ln2, ls3[i], ls4[j]);
                        if (a1 != null) {
                            co_xy.nx = null;
                            cond co = add_coxy(CO_ATNG);
                            co.u.atn = new angtn(ln1, ln2, s1, s2);
                            co = add_coxy(CO_ACONG);
                            co.u.as = new angles(ls3[i], ls4[j], s3, s4);
                            a1.co = co;
                        }
                    }
                }
        }

    }

    public void search_atatn(anglet at, angtn a1) {
        l_line l1 = at.l1;
        l_line l2 = at.l2;
        l_line l3, l4;
        int v = 0;
        if (l1 == a1.ln1 && l2 == a1.ln2) {
            l3 = a1.ln3;
            l4 = a1.ln4;
            v = A_90 - at.v;
        } else if (l1 == a1.ln2 && l2 == a1.ln1) {
            l3 = a1.ln4;
            l4 = a1.ln3;
            v = -A_90 - at.v;
        } else if (l1 == a1.ln3 && l2 == a1.ln4) {
            l3 = a1.ln1;
            l4 = a1.ln2;
            v = A_90 - at.v;
        } else if (l1 == a1.ln4 && l2 == a1.ln3) {
            l3 = a1.ln2;
            l4 = a1.ln1;
            v = -A_90 - at.v;
        } else
            l3 = l4 = null;
        if (l3 != null) {
            anglet at1 = add_at(134, l3, l4, v);
            if (at1 != null) {
                co_xy.nx = null;
                cond co = add_coxy(CO_TANG);
                co.u.at = at;
                co = add_coxy(CO_ATNG);
                co.u.atn = a1;
                at1.co = co;
            }
        }
    }

    public boolean ptdr(int p, int o, int o1) {
        return (x_inside(p, o, o1) || x_inside(o1, p, o));
    }

    l_line[] split_ln(int p, l_line ln) {
        int o1 = get_lpt1(ln, p);
        int o2 = get_anti_pt(ln, p, o1);

        l_line lx1 = fadd_ln_t(p, o1);
        l_line lx2 = fadd_ln_t(p, o2);
        for (int i = 0; i <= ln.no; i++) {
            int n = ln.pt[i];
            if (n != 0 && n != p) {
                if (o1 != 0 && n != o1 && ptdr(n, p, o1)) {
                    l_line l1 = fd_ln_lp(lx1, n);
                    if (l1 == null) {
                        lx1 = cp_ln(lx1);
                        lx1.type = 0;
                        add_pt2l(n, lx1);
                    } else
                        lx1 = l1;
                } else if (o2 != 0 && n != o2 && ptdr(n, p, o2)) {
                    l_line l2 = fd_ln_lp(lx2, n);
                    if (l2 == null) {
                        lx2 = cp_ln(lx2);
                        lx2.type = 0;
                        add_pt2l(n, lx2);
                    } else
                        lx2 = l2;
                }
            }
        }
        l_line ls[];


        if (lx1 == null && lx2 == null) return null;
        if (lx1 != null && lx2 != null) {
            ls = new l_line[2];
            ls[0] = lx1;
            ls[1] = lx2;
            return ls;
        }
        ls = new l_line[1];
        if (lx1 != null)
            ls[0] = lx1;
        else if (lx2 != null)
            ls[0] = lx2;
        return ls;
    }


    public boolean check_llatn(int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
        int n1 = get_lpt1(l1, p1);
        int n2 = get_lpt1(l2, p1);
        int n3 = get_lpt1(l3, p2);
        int n4 = get_lpt1(l4, p2);

        double r1 = getAngleValue(n1, p1, n2);
        double r2 = getAngleValue(n3, p2, n4);

        if (Math.abs(Math.abs(r1) + Math.abs(r2) - Math.PI / 2) < ZERO) return true;
        return false;
    }

///////////////////////////////////////////////////////////////////////////
///// nodes;


    public void search_ns(l_list ns) {
        if (last_ns.solved) return;
        search_bk(ns);
    }

    public void search_bk(l_list ls) {
        search_bk_as(ls);
    }

    public void search_bk_as(l_list ls) {
        l_list last;
        l_line[] ln1, ln2;

        for (int i = 0; i < ls.nd; i++) {
            last = last_ns;
            mnde m = ls.md[i];
            angtr t = m.tr;
            angst ast = fd_ast(t.l1, t.l2);
            if (ast != null) {
                int d = ast.get_dr(t.l1, t.l2);

                ln1 = ln2 = null;
                if (d == 1) {
                    ln1 = ast.ln1;
                    ln2 = ast.ln2;

                } else if (d == -1) {
                    ln1 = ast.ln2;
                    ln2 = ast.ln1;
                }

                for (int k = 0; k <= ast.no; k++) {
                    l_line l1 = ln1[k];
                    l_line l2 = ln2[k];
                    l_list ls1 = all_ns.nx;
                    while (ls1 != null) {
                        add_ls_et(ls1, l1, l2, i);
                        if (last == ls1) break;
                        ls1 = ls1.nx;
                    }
                }
            }


        }
    }

    public void add_ls_et(l_list ls, l_line l1, l_line l2, int n) {
        angtr tr = ls.md[n].tr;
        if (tr.l1 == l1 && tr.l2 == l2) return;
        int v = inter_lls(l1, l2);

        for (int i = 0; i < ls.nd; i++) {
            angtr t = ls.md[i].tr;

            if (v != 0) {
                angtr tx = add_tr(v, l1, l2);
                l_list lsx = add_ls_node_sub(ls, t, tx, n);
                if (lsx != null) {
                    lsx.fr = ls;
                    lsx.add_rule(add_rule_eqag(ls.md[n].tr, tx));
                }
                return;
            }
        }
    }

    public rule add_rule_eqag(angtr t1, angtr t2) {
        rule r = new rule(rule.EQ_ANGLE);
        mnde m1 = new mnde();
        m1.tr = t1;
        r.mr1[0] = m1;

        mnde m2 = new mnde();
        m2.tr = t2;
        r.mr = m2;
        return r;
    }

    public l_list add_ls_node_sub(l_list ls, angtr t1, angtr t2, int n) {
        l_list ls1 = new l_list();
        ls1.cp(ls);
        ls1.md[n].tr = t2;
        last_ns.nx = ls1;
        last_ns = ls1;
        return ls1;
    }

    public l_list cp_l_list(l_list ls) {
        l_list ls1 = new l_list();
        ls1.type = l_list.ANGLE;
        return ls1;
    }

    void show_tr(angtr tr) {
        if (tr == null) return;
        if (tr.t1 != 0 && tr.t2 != 0)
            print_fang(tr.t1, tr.v, tr.v, tr.t2);
        else if (tr.t1 != 0) {
            print_fang(tr.t1, tr.v, tr.v, get_lpt1(tr.l2, tr.v));
        } else if (tr.t2 != 0) {
            print_fang(get_lpt1(tr.l1, tr.t1), tr.v, tr.v, tr.t2);
        } else
            show_agll(tr.l1, tr.l2);
    }
}
