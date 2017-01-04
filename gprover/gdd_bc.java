/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-16
 * Time: 10:30:31
 * To change this template use File | Settings | File Templates.
 */
package gprover;

import java.util.Vector;
                                
public class gdd_bc extends gdd_aux {


    auxpt axptc = null;

    public gdd_bc() {
        P_STATUS = 0;
        depth = 0;
        ck_value = true;
    }

    void prove_fix() {

        axptc = null;
        pts_pno = pts_no;

        fixpoint();

        if (conc.pred != 0 && conc.pred != CO_NANG && !conc_xtrue()) {
            add_aux();
        }

        if (conc.pred == CO_NANG) {
            add_nodes(conc.p);
            if (all_ns.nx != null) {
                parse_llist();
            }
        } else {
            Vector v = new Vector();
            v.addAll(vauxpts);
            int n = v.size();
            if (n > 0) {
                debug_print("Total auxiliary Points: " + v.size());
                time_start();

                for (int i = 0; i < v.size(); i++) {
                    auxpt ax = (auxpt) v.get(i);
                    debug_print(ax.toString());
                    gterm t = gt;
                    init_dbase();
                    setExample(t);
                    sbase();

                    int na = ax.getPtsNo();
                    for (int j = 0; j < na; j++) {
                        Pro_point pt = ax.getPtsbyNo(j);
                        pts_no++;
                        allpts[pts_no] = pt;
                        pt.ps[0] = pts_no;
                        add_auxpt(pt);
                    }
                    fixpoint();
                    if (conc_xtrue()) {
                        for (int j = 0; j < na; j++) {
                            Pro_point pt = ax.getPtsbyNo(j);
                            pt.set_name(fd_aux_name());
                            auxpt_string(pt);
                        }
                        axptc = ax;
                        break;
                    }
                    if (time_over())
                        break;
                }
            }
        }
    }


    int PTY(cond x) {
        return x.u.get_type();
    }

    int PLM(cond x) {
        return (x.u.get_lemma());
    }

    cond PCO(cond x) {
        return (x.u.get_co());
    }

    int PNO(cond x) {
        return (x.u.get_no());
    }


    cond cp_pred(cond co) {
        cond c = new_pr(co.pred);
        co.no = c.no = ++gno;

        c.rule = co.rule;
        c.u.cpv(co.u);
        for (int i = 0; i < cond.MAX_GEO; i++)
            c.p[i] = co.p[i];
        return (c);
    }

    cond fd_pred(cond co) {
        cond pr = all_nd.nx;
        for (; pr != null; pr = pr.nx) {
            if (compare_pred(co, pr)) return (pr);
        }
        return (null);
    }

    cond fd_prep(cond co) {
        cond pr = all_nd.nx;
        for (; pr != null; pr = pr.nx) {
            if (co.u.get_co() == pr.u.get_co()) return (pr);
        }
        return (null);
    }


    cond add_pred(int m, int n, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        tm_pr1.u.setnull();
        tm_pr1.pred = n;
        tm_pr1.no = 0;
        tm_pr1.u.ln = null;
        tm_pr1.p[0] = p1;
        tm_pr1.p[1] = p2;
        tm_pr1.p[2] = p3;
        tm_pr1.p[3] = p4;
        tm_pr1.p[4] = p5;
        tm_pr1.p[5] = p6;
        tm_pr1.p[6] = p7;
        tm_pr1.p[7] = p8;
        if (n == CO_COLL) {
            int[] p = tm_pr1.p;
            int nx = 1;
            for (int i = 1; i < p.length; i++) {
                int k = p[i];
                p[i] = 0;

                int j = 0;
                for (; j < nx; j++)
                    if (k == p[j])
                        break;

                if (j == nx)
                    p[nx++] = k;
            }
            if (p[2] == 0) return null;
        }
        if (n == CO_PARA) {
            if (p1 == p3 && p2 == p4 || p1 == p4 && p2 == p3) return null;
        }


        cond pr3 = fd_pred(tm_pr1);
        if (pr3 != null) {
        } else {
            do_pred(tm_pr1);
            if (PCO((tm_pr1)) == null) {
                gprint(Cm.s2070);
                pr3 = new cond(tm_pr1);
            } else {
                cp_pred(tm_pr1);
                pr3 = last_nd;
            }
        }
        return pr3;

    }

    int finter_ll(l_line l1, l_line l2) {
        char i, j;

        if (l1 == null || l2 == null || l1 == l2) return (0);
        for (i = 0; i <= l1.no; i++)
            for (j = 0; j <= l2.no; j++) {
                if (l1.pt[i] == l2.pt[j]) return (l1.pt[i]);
            }
        return (0);
    }

    int finter_ll1(l_line l1, l_line l2, int p1) {
        char i, j;
        if (l1 == l2) return (0);
        for (i = 0; i <= l1.no; i++)
            for (j = 0; j <= l2.no; j++) {
                if (l1.pt[i] == l2.pt[j] && !meq_pt(l1.pt[i], p1)) return (l1.pt[i]);
            }
        return (0);
    }

    void show_fproof() {
        if (conc_xtrue()) {
            last_nd = all_nd;
            cp_pred(conc);
            if (check_pred(last_nd)) { //"(The fact is trivially true)\r\n";
                return;
            }
            do_pred(last_nd);
            forw_pred(last_nd);
        }
    }


    void forw_pred(cond co) {
        cond pr1, pr2, pr3;
        show_dtype = 0;
        all_nd.nx = co;
        last_nd = co;
        co.no = 1;
        co.nx = null;
        gno = 1;

        for (cond pr = all_nd.nx; pr != null; pr = pr.nx) {

            if (!isPFull() && !check_tValid(pr)) {
                last_nd = all_nd;
                all_nd.nx = null;
                return;
            }

            if (pr.u.isnull()) {  //The fact is trivially true
                if (show_detail && pr.pred == gib.CO_ACONG) {
                    forw_eqangle(pr);
                }
                continue;
            } else if ((pr1 = PCO(pr)) == null) { //hyp
                pr.getRuleFromeFacts();
                continue;
            } else if (pr1.p[1] != 0) {

                for (; pr1 != null; pr1 = pr1.nx) {
                    if (pr1.pred == 0) {
                        continue;
                    }
                    if ((pr3 = fd_pred(pr1)) != null) {
                        pr.addcond(pr3);
                    } else {
                        do_pred(pr1);
//                        if (pr_coll) {
//                            if (!add_pr_coll(pr, pr1))
//                                continue;
//                        }
                        if (pr1.u.isnull()) { //obvious
                            pr.addcond(pr1);
                        } else if (PCO(pr1) == null) { //hyp
                            pr.addcond(pr1);
                        } else {
                            cp_pred(pr1);
                            pr1.no = last_nd.no;
                            pr.addcond(pr1);
                        }
                    }
                }
                pr.getRuleFromeFacts();
            } else {
                pr2 = pr1.nx;  /*pr0=last_nd; */
                switch (pr.pred) {
                    case CO_COLL:
                        add_pred_coll(pr, pr1, pr2);
                        break;
                    case CO_PARA:// && (pr1.pred == CO_COLL || pr1.pred == CO_PARA)) {
                        add_pred_para(pr, pr1, pr2);
                        break;
                    case CO_ACONG:
                        add_pred_as(pr, pr1, pr2);
                        break;
                    case CO_TANG:
                        add_pred_at(pr, pr1, pr2);
                        break;
                    case CO_PERP:
                        add_pred_perp(pr, pr1, pr2);
                        break;
                    case CO_ATNG:
                        add_pred_atn(pr, pr1, pr2);
                        break;
                    case CO_CYCLIC:
                        add_pred_cr(pr, pr1, pr2);
                        break;
                    default: {
                        for (; pr1 != null; pr1 = pr1.nx) {
                            pr3 = fd_prep(pr1);
                            if (pr3 != null) {
                                pr.addcond(pr3);
                            } else if ((pr3 = PCO(pr1)) == null) { //"(hyp)";
                                pr.addcond(pr1);
                            } else {
                                cp_pred(pr1);
                                pr.addcond(last_nd);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean add_pr_coll(cond pr, cond pr1) {
        if (pr.pred == CO_PERP && PLM(pr) == 11 && pr1.pred == CO_ACONG) {
            int i1, i2, k1, k2;
            if (xcoll4(pr.p[0], pr.p[1], pr1.p[4], pr1.p[5])) {
                i1 = pr1.p[4];
                i2 = pr1.p[5];
                k1 = pr1.p[6];
                k2 = pr1.p[7];
            } else {
                k1 = pr1.p[4];
                k2 = pr1.p[5];
                i1 = pr1.p[6];
                i2 = pr1.p[7];
            }
            for_c4(pr.p[0], pr.p[1], i1, i2);
            for_c4(pr.p[2], pr.p[3], k1, k2);
        } else if (pr.pred == CO_ACONG && pr1.pred == CO_CYCLIC && PLM(pr) == 41) {
            l_line l1 = fd_ln(pr.p[0], pr.p[1]);
            l_line l2 = fd_ln(pr.p[2], pr.p[3]);
            l_line l3 = fd_ln(pr.p[4], pr.p[5]);
            l_line l4 = fd_ln(pr.p[6], pr.p[7]);
            int i1 = finter_ll(l1, l2);
            int i2 = finter_ll(l3, l4);
            int k1 = finter_ll(l1, l3);
            int k2 = finter_ll(l2, l4);
            if (sdiff(i1, i2, k1, k2, pr.p)) {
                for_c4(pr.p[0], pr.p[1], i1, k1);
                for_c4(pr.p[2], pr.p[3], i1, k2);
                for_c4(pr.p[4], pr.p[5], i2, k1);
                for_c4(pr.p[6], pr.p[7], i2, k2);
                cond prc = add_pred(1, CO_ACONG, i1, k1, i1, k2, i2, k1, i2, k2);
                pr.addcond(prc);
                return false;
            }
        } else if (pr.pred == CO_ACONG && pr1.pred == CO_CYCLIC && PLM(pr) == 22) {
            if (sdiff(pr1.p[0], pr1.p[1], pr1.p[2], 0, pr.p)) {
                l_line l1 = fd_ln(pr.p[0], pr.p[1]);
                l_line l2 = fd_ln(pr.p[2], pr.p[3]);
                l_line l3 = fd_ln(pr.p[4], pr.p[5]);
                l_line l4 = fd_ln(pr.p[6], pr.p[7]);
                if (l1 == l4) {
                    int k1 = finter_ll(l2, l3);
                    int i1 = finter_ll(l1, l2);
                    int i2 = finter_ll(l1, l3);
                    for_c6(pr.p[0], pr.p[1], i1, i2, pr.p[6], pr.p[7]);
                    for_c4(pr.p[2], pr.p[3], k1, i1);
                    for_c4(pr.p[4], pr.p[5], k1, i2);
                    cond prc = add_pred(1, CO_ACONG, i1, i2, i1, k1, i2, k1, i2, i1);
                    pr.addcond(prc);
                } else {
                    int k1 = finter_ll(l1, l4);
                    int i1 = finter_ll(l2, l1);
                    int i2 = finter_ll(l2, l4);
                    for_c6(pr.p[2], pr.p[3], i1, i2, pr.p[4], pr.p[5]);
                    for_c4(pr.p[0], pr.p[1], k1, i1);
                    for_c4(pr.p[6], pr.p[7], k1, i2);
                    cond prc = add_pred(1, CO_ACONG, k1, i1, i1, i2, i1, i2, i2, k1);
                    pr.addcond(prc);

                }
                return false;
            }
        }

        return true;
    }

    public void add_pred_coll(cond pr, cond pr1, cond pr2) {
        int lemma = PLM(pr);
        switch (lemma) {
            case 1: {
                l_line l1 = pr1.u.ln;
                l_line l2 = pr2.u.ln;
                cond c = null;
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        for (int k = 0; k < 3; k++) {
                            if (i != j && j != k && i != k) {
                                if (on_ln(pr.p[i], pr.p[j], l1) && on_ln(pr.p[k], l2))
                                    c = add_pred(0, CO_PARA, pr.p[i], pr.p[j], pr.p[k], get_lpt1(l2, pr.p[k]), 0, 0, 0, 0);
                                else if (on_ln(pr.p[i], pr.p[j], l2) && on_ln(pr.p[k], l1))
                                    c = add_pred(0, CO_PARA, pr.p[i], pr.p[j], pr.p[k], get_lpt1(l1, pr.p[k]), 0, 0, 0, 0);
                                if (c != null) {
                                    pr.addcond(gib.R_P_COLL, c);
                                    return;
                                }
                            }
                        }
            }
            break;
        }
    }

    public void add_pred_para(cond pr, cond pr1, cond pr2) {
        l_line ln;
        p_line pn1;
        int i1, k1, k2;
        k1 = k2 = 0;
        if (pr1 == null) return;
        int lemma = PLM(pr);
        switch (lemma) {
            case 188: {
                p_line pn = pr1.u.pn;
                l_line l1, l2;
                l1 = l2 = null;

                for (int i = 0; i <= pn.no; i++) {
                    if (xcoll_ln(fd_ln(pr.p[0], pr.p[1]), (pn.ln[i])))
                        l1 = pn.ln[i];
                    else if (xcoll_ln(fd_ln(pr.p[2], pr.p[3]), pn.ln[i]))
                        l2 = pn.ln[i];
                }
                cond c = add_pred(0, CO_PARA, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], 0, 0, 0, 0);
                pr.addcond(c);
            }
            break;
            case 165: {
                angles as = pr1.u.as;
                l_line l1, l2, l3, l4;
                if (on_ln(pr.p[0], pr.p[1], as.l3) && on_ln(pr.p[2], pr.p[3], as.l4)) {
                    l1 = as.l1;
                    l2 = as.l2;
                    l3 = as.l3;
                    l4 = as.l4;
                } else {
                    l1 = as.l2;
                    l2 = as.l1;
                    l3 = as.l4;
                    l4 = as.l3;
                }
                cond c1 = add_as_pred_12(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l3, l4, l1, l2);
                cond c2 = add_pred_pntn(0, CO_PARA, pr.p[0], pr.p[1], l3, pr.p[2], pr.p[3], l4);
                pr.addcond(R_AG_PP12, c1, c2);
            }
            break;
            case 166: {
                angles as = pr1.u.as;
                l_line l1, l2, l3, l4;
                if (on_ln(pr.p[0], pr.p[1], as.l2) && on_ln(pr.p[2], pr.p[3], as.l4)) {
                    l1 = as.l1;
                    l2 = as.l2;
                    l3 = as.l3;
                    l4 = as.l4;
                } else {
                    l1 = as.l3;
                    l2 = as.l4;
                    l3 = as.l1;
                    l4 = as.l2;
                }
                cond c1 = add_as_pred_13(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l2, l1, l4, l3);
                cond c2 = add_pred_pntn(0, CO_PARA, pr.p[0], pr.p[1], l1, pr.p[2], pr.p[3], l3);
                pr.addcond(R_AG_PP13, c1, c2);
            }
            break;
            case R_P_COLL: {
                ln = pr1.u.ln;
                pn1 = pr2.u.pn;
                for (i1 = 0; i1 <= pn1.no; i1++) {
                    if ((k1 = finter_ll(ln, pn1.ln[i1])) != 0 && (k2 = finter_ll1(ln, pn1.ln[i1], k1)) != 0)
                        break;
                }
                if (i1 > pn1.no)
                    gexit(103);

                if (on_ln(pr.p[2], ln) && on_ln(pr.p[3], ln)) {
                    i1 = pr.p[2];
                    pr.p[2] = pr.p[0];
                    pr.p[0] = i1;
                    i1 = pr.p[3];
                    pr.p[3] = pr.p[1];
                    pr.p[1] = i1;
                }
                cond co1 = add_pred(0, CO_COLL, pr.p[0], pr.p[1], k1, k2, 0, 0, 0, 0);
                cond co2 = add_pred(1, CO_PARA, pr.p[2], pr.p[3], k1, k2, 0, 0, 0, 0);
                pr.addcond(co1, co2);
            }
            break;
            default: {
                if (pr1.pred == CO_COLL) {
                    ln = pr1.u.ln;
                    pn1 = pr2.u.pn;
                    if (ln == null || pn1 == null) return;
                    for (i1 = 0; i1 <= pn1.no; i1++) {
                        if ((k1 = finter_ll(ln, pn1.ln[i1])) != 0 && (k2 = finter_ll1(ln, pn1.ln[i1], k1)) != 0)
                            break;
                    }
                    if (i1 > pn1.no)
                        gexit(103);

                    if (on_ln(pr.p[2], ln) && on_ln(pr.p[3], ln)) {
                        i1 = pr.p[2];
                        pr.p[2] = pr.p[0];
                        pr.p[0] = i1;
                        i1 = pr.p[3];
                        pr.p[3] = pr.p[1];
                        pr.p[1] = i1;
                    }
                    cond co1 = add_pred(0, CO_COLL, pr.p[0], pr.p[1], k1, k2, 0, 0, 0, 0);
                    cond co2 = add_pred(1, CO_PARA, pr.p[2], pr.p[3], k1, k2, 0, 0, 0, 0);
                    pr.addcond(co1, co2);

                } else {
                    pn1 = pr1.u.pn;
                    for (i1 = 0; i1 <= pn1.no; i1++) {
                        if (on_pn(pn1.ln[i1], pr2.u.pn)) break;
                    }
                    if (i1 > pn1.no) {
                        gexit(104);
                    }
                    ln = pn1.ln[i1];
                    cond co1 = add_pred(0, CO_PARA, pr.p[0], pr.p[1], ln.pt[0], ln.pt[1], 0, 0, 0, 0);
                    cond co2 = add_pred(1, CO_PARA, pr.p[2], pr.p[3], ln.pt[0], ln.pt[1], 0, 0, 0, 0);
                    pr.addcond(co1, co2);
                }
                break;
            }
        }
    }

    public void add_pred_perp(cond pr, cond pr1, cond pr2) {
        int lm = PLM(pr);

        switch (lm) {
            case 401: {
                t_line tn = pr1.u.tn;
                l_line ln1 = tn.l1;
                l_line ln2 = tn.l2;
                cond c = add_pred(0, CO_PERP, ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1], 0, 0, 0, 0);
                pr.addcond(c);
            }
            break;
            case 142: {
                angles as1 = pr1.u.as;
                angles as2 = pr2.u.as;
                cond c = new cond();
                c.pred = CO_ACONG;
                c.p[0] = pr.p[0];
                c.p[1] = pr.p[1];
                c.p[2] = pr.p[2];
                c.p[3] = pr.p[3];
                c.p[4] = pr.p[2];
                c.p[5] = pr.p[3];
                c.p[6] = pr.p[0];
                c.p[7] = pr.p[1];

                add_as82_t(c, as1, as2);
                pr.add_allco(c.vlist);
            }
            break;
            case 145: {
                angles as = pr1.u.as;
                l_line l1, l2, l3, l4;
                if (on_ln(pr.p[0], pr.p[1], as.l3) && on_ln(pr.p[2], pr.p[3], as.l4)) {
                    l1 = as.l1;
                    l2 = as.l2;
                    l3 = as.l3;
                    l4 = as.l4;
                } else {
                    l1 = as.l2;
                    l2 = as.l1;
                    l3 = as.l4;
                    l4 = as.l3;
                }
                cond c1 = add_as_pred_12(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l3, l4, l1, l2);
                cond c2 = add_pred(0, CO_PERP, c1.p[4], c1.p[5], c1.p[6], c1.p[7], 0, 0, 0, 0);
                pr.addcond(R_AG_TT12, c1, c2);
            }
            break;
            case 146: {
                angles as = pr1.u.as;
                l_line l1, l2, l3, l4;
                if (on_ln(pr.p[0], pr.p[1], as.l2) && on_ln(pr.p[2], pr.p[3], as.l4)) {
                    l1 = as.l1;
                    l2 = as.l2;
                    l3 = as.l3;
                    l4 = as.l4;
                } else {
                    l1 = as.l3;
                    l2 = as.l4;
                    l3 = as.l1;
                    l4 = as.l2;
                }
//                cond cx = add_as_pred_12(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l3, l4, l1, l2);

                cond c1 = add_as_pred_13(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l2, l1, l4, l3);
                cond c2 = add_pred_tn13(0, CO_PERP, pr.p[0], pr.p[1], l1, pr.p[2], pr.p[3], l3);

                //        cx.addcond(0, c1);
                pr.addcond(0, c1, c2);
//                pr.addcond(R_AG_TT13, c1, c2);
            }
            break;
            case 402: {
                l_line ln = pr1.u.ln;
                t_line tn = pr2.u.tn;
                cond c = add_pred(0, CO_PERP, tn.l1.pt[0], tn.l1.pt[1], tn.l2.pt[0], tn.l2.pt[1], 0, 0, 0, 0);
                pr.addcond(c);
            }
            break;
        }
    }

    public void add_pred_cr(cond pr, cond pr1, cond pr2) {
        int lm = PLM(pr);
        switch (lm) {
            case 301: {
                a_cir c1 = pr1.u.cr;
                a_cir c2 = pr2.u.cr;
                cond co1 = add_pred_cyclic1(pr, c1);
                cond co2 = add_pred_cyclic1(pr, c2);
                pr.addcond(co1, co2);
            }
            break;
        }

    }

    public cond add_pred_cyclic1(cond pr, a_cir c1) {
        int[] p = new int[4];
        for (int i = 0; i < 4; i++)
            p[i] = 0;
        int k = -1;

        for (int i = 1; i < 4; i++) {
            int t = pr.p[i];
            if (on_cir(t, c1))
                p[++k] = t;
        }
        if (k < 3) {
            for (int i = 0; i <= c1.no; i++) {
                int j;
                for (j = 0; j <= k; j++)
                    if (p[j] == c1.pt[i])
                        break;
                if (j > k)
                    p[++k] = c1.pt[i];
                if (k == 3) break;
            }
        }
        //if (c1.o != 0)
        //  p[3] = 0;
        return add_pred(0, CO_CYCLIC, c1.o, p[0], p[1], p[2], p[3], 0, 0, 0);
    }

    public cond add_pred_pntn(int m, int n, int p1, int p2, l_line l1, int p3, int p4, l_line l2) {
        int m1, m2, m3, m4;

        if (on_ln(p1, l1))
            m1 = p1;
        else if (on_ln(p2, l1))
            m1 = p2;
        else
            m1 = l1.pt[0];
        m2 = get_lpt1(l1, m1);

        if (on_ln(p3, l2))
            m3 = p3;
        else if (on_ln(p4, l2))
            m3 = p4;
        else
            m3 = l2.pt[0];
        m4 = get_lpt1(l2, m3);

        return add_pred(m, n, m1, m2, m3, m4, 0, 0, 0, 0);
    }

    public cond add_pred_tn13(int m, int n, int p1, int p2, l_line l1, int p3, int p4, l_line l2) {
        int m1, m2, m3, m4;
        int o = inter_lls(l1, l2);
        if (o != 0)
            m1 = o;
        else if (on_ln(p1, l1))
            m1 = p1;
        else if (on_ln(p2, l1))
            m1 = p2;
        else
            m1 = l1.pt[0];
        m2 = get_lpt1(l1, m1);

        if (o != 0)
            m3 = o;
        else if (on_ln(p3, l2))
            m3 = p3;
        else if (on_ln(p4, l2))
            m3 = p4;
        else
            m3 = l2.pt[0];
        m4 = get_lpt1(l2, m3);
        return add_pred(m, n, m1, m2, m3, m4, 0, 0, 0, 0);
    }

    public void add_pred_atn(cond pr, cond pr1, cond pr2) {
        int lm = PLM(pr);

        switch (lm) {
            case 188: {
                angtn t = pr1.u.atn;
                int t1 = t.t1;
                int t2 = t.t2;
                cond c = add_pred(0, CO_ATNG, get_lpt1(t.ln1, t1), t1, t1, get_lpt1(t.ln2, t1),
                        get_lpt1(t.ln3, t2), t2, t2, get_lpt1(t.ln4, t2));
                pr.addcond(c);
            }
            break;
            case 133: {
                angles as = pr1.u.as;
                angtn atn = pr2.u.atn;
                l_line l1 = as.l1;
                l_line l2 = as.l2;
                l_line l3 = as.l3;
                l_line l4 = as.l4;
                l_line s1 = atn.ln1;
                l_line s2 = atn.ln2;
                l_line s3 = atn.ln3;
                l_line s4 = atn.ln4;
                if (add_pred_atn_atnas(pr, l1, l2, l3, l4, s1, s2, s3, s4))
                    // A + B = 90 , B = C --> A + C = 90.
                    break;
            }
            break;

            default:
                break;
        }
    }

    public boolean add_pred_atn_atnas(cond pr, l_line l1, l_line l2, l_line l3, l_line l4,
                                      l_line s1, l_line s2, l_line s3, l_line s4) {
        if (on_ln4(pr.p, l1, l2, s1, s2)) {
            cond c1 = add_as_pred1(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l3, l4);
            cond c2 = add_as_pred1(0, CO_ATNG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], s3, s4);
            pr.addcond(c1, c2);
            return true;
        } else if (on_ln4(pr.p, l2, l1, s2, s1)) {
            cond c1 = add_as_pred1(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l4, l3);
            cond c2 = add_as_pred1(0, CO_ATNG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], s4, s3);
            pr.addcond(c1, c2);
            return true;
        } else if (on_ln4(pr.p, s1, s2, l1, l2)) {
            cond c1 = add_as_pred1(0, CO_ACONG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], l3, l4);
            cond c2 = add_as_pred1(0, CO_ATNG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], s3, s4);
            pr.addcond(c1, c2);
            return true;
        } else if (on_ln4(pr.p, s2, s1, l2, l1)) {
            cond c1 = add_as_pred1(0, CO_ACONG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], l4, l3);
            cond c2 = add_as_pred1(0, CO_ATNG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], s4, s3);
            pr.addcond(c1, c2);
            return true;
        }
        return false;
    }

    public void add_pred_at(cond pr, cond pr1, cond pr2) {
        int lm = PLM(pr);

        switch (lm) {
//            case R_AS_AT: {
//                anglet at1 = pr2.u.at;
//                angles as = pr1.u.as;
//                int p1 = get_lpt1(at1.l1, at1.p);
//                int p2 = get_lpt1(at1.l2, at1.p);
//
//                int v = getAtv(p1, at1.p, p2, at1.v);
//
//                l_line l1, l2, l3, l4;
//                l1 = at1.l1;
//                l2 = at1.l2;
//                l3 = as.l3;
//                l4 = as.l4;
//
//                cond co1, co2;
//                if (on_ln(pr.p[0], pr.p[1], l3) && on_ln(pr.p[1], pr.p[2], l4)) {
//                    co1 = add_pred(0, CO_TANG, p1, at1.p, p2, v, 0, 0, 0, 0);
//                    co2 = add_pred(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[1], pr.p[2], p1, at1.p, at1.p, p2);
//                } else {
//                    co1 = add_pred(0, CO_TANG, p2, at1.p, p1, -v, 0, 0, 0, 0);
//                    co2 = add_pred(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[1], pr.p[2], p2, at1.p, at1.p, p1);
//                }
//                pr.addcond(R_AS_AT, co1, co2);
//            }
//            break;
            case 104: {
                anglet at1 = pr2.u.at;
                anglet at2 = pr1.u.at;
                l_line l1 = at1.l1;
                l_line l2 = at2.l1;
                l_line l3 = at2.l2;
                int v1 = at1.v;
                int v2 = at2.v;
                int p1 = at1.p;
                int p2 = at2.p;
                int p3 = inter_ll(l1, l3);

                if (p1 != p2) {
                    v1 = getAtv(p3, p1, p2, v1);
                    v2 = getAtv(p1, p2, p3, v2);
                    cond c1 = add_pred(0, CO_TANG, p3, p1, p2, v1, 0, 0, 0, 0);
                    cond c2 = add_pred(0, CO_TANG, p1, p2, p3, v2, 0, 0, 0, 0);
                    pr.addcond(0, c1, c2);
                } else {
                    int k = get_lpt1(l2, p1);

                    if (on_ln(pr.p[0], pr.p[1], l1) && on_ln(pr.p[1], pr.p[2], l3)) {
                        int t1 = getAtv(pr.p[0], pr.p[1], k, v1);
                        int t2 = getAtv(k, pr.p[1], pr.p[2], v2);
                        if (t1 + t2 == pr.p[3])
                            lm = 0;
                        else
                            lm = R_TRI_ALL_AG_180;
                        cond c1 = add_pred(0, CO_TANG, pr.p[0], pr.p[1], k, t1, 0, 0, 0, 0);
                        cond c2 = add_pred(0, CO_TANG, k, pr.p[1], pr.p[2], t2, 0, 0, 0, 0);
                        pr.addcond(lm, c1, c2);
                    } else {
                        int t1 = getAtv(pr.p[0], pr.p[1], k, -v2);
                        int t2 = getAtv(k, pr.p[1], pr.p[2], -v1);
                        if (t1 + t2 == pr.p[3])
                            lm = 0;
                        else
                            lm = R_TRI_ALL_AG_180;
                        cond c1 = add_pred(0, CO_TANG, pr.p[0], pr.p[1], k, t1, 0, 0, 0, 0);
                        cond c2 = add_pred(0, CO_TANG, k, pr.p[1], pr.p[2], t2, 0, 0, 0, 0);
                        pr.addcond(lm, c1, c2);
                    }
                }
            }
            break;
            case 134: {
                Cm.print("134");
            }
            break;
            case 188: {
                anglet at1 = pr1.u.at;
                int p1 = get_lpt1(at1.l1, at1.p);
                int p2 = get_lpt1(at1.l2, at1.p);
                int v = getAtv(p1, at1.p, p2, at1.v);
                cond co1 = add_pred(0, CO_TANG, p1, at1.p, p2, v, 0, 0, 0, 0);
                pr.addcond(co1);
            }
            break;
            default: {
                if (pr2 == null) {
                    anglet at1 = pr1.u.at;
                    int p1 = get_lpt1(at1.l1, at1.p);
                    int p2 = get_lpt1(at1.l2, at1.p);
                    int v = getAtv(p1, at1.p, p2, at1.v);
                    if (pr1.pred == CO_TANG) {
                        cond co1 = add_pred(0, CO_TANG, p1, at1.p, p2, v, 0, 0, 0, 0);
                        pr.addcond(co1);
                    } else if (pr1.pred == CO_ACONG) {
                        angles as = pr1.u.as;
                        cond co1 = add_pred(0, CO_ACONG, as.l1.pt[0], as.l1.pt[1], as.l2.pt[0], as.l2.pt[1], as.l3.pt[0], as.l3.pt[1], as.l4.pt[0], as.l4.pt[1]);
                        pr.addcond(co1);
                    }
                }
                break;
            }

        }
    }

    public void add_pred_as(cond pr, cond pr1, cond pr2) {
        angles as1, as2;
        as1 = as2 = null;
        if (pr1 != null)
            as1 = pr1.u.as;
        if (pr2 != null)
            as2 = pr2.u.as;


        l_line l1 = null;
        l_line l2 = null;
        l_line l3 = null;
        l_line l4 = null;
        int lemma = PLM(pr);

        switch (lemma) {
            case 188: {
                cond co1 = add_as_pred(0, CO_ACONG, as1.l1, as1.l2, as1.l3, as1.l4);
                pr.addcond(co1);
            }
            break;
            case 181: {
                l_line[] lns = null;
                lns = geti81(pr.p[0], pr.p[1], pr.p[2], pr.p[3], as1);
                if (lns != null) {
                    l1 = lns[0];
                    l2 = lns[1];
                }
                if (l1 == null || l2 == null) {
                    lns = geti81(pr.p[0], pr.p[1], pr.p[2], pr.p[3], as2);
                    if (lns != null) {
                        l1 = lns[0];
                        l2 = lns[1];
                    }
                    lns = geti81(pr.p[4], pr.p[5], pr.p[6], pr.p[7], as1);
                    if (lns != null) {
                        l3 = lns[0];
                        l4 = lns[1];
                    }
                } else {
                    lns = geti81(pr.p[4], pr.p[5], pr.p[6], pr.p[7], as2);
                    if (lns != null) {
                        l3 = lns[0];
                        l4 = lns[1];
                    }
                }
                if (l1 == null || l2 == null || l3 == null || l4 == null) {
                    cond co1 = add_pred(1, CO_ACONG, pr.p[0], pr.p[1], pr.p[4], pr.p[5], pr.p[2], pr.p[3], pr.p[6], pr.p[7]);
                    pr.addcond(co1);
                } else {
                    cond co1 = add_as_pred1(0, CO_ACONG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], l1, l2);
                    cond co2 = add_as_pred1(0, CO_ACONG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], l3, l4);
                    pr.addcond(co1, co2);
                }
            }
            break;
            case 131: {
                anglet at1 = pr1.u.at;
                anglet at2 = pr2.u.at;
                int t1 = get_at2_v(pr.p[0], pr.p[1], pr.p[2], pr.p[3], at1, at2);
                int t2 = get_at2_v(pr.p[4], pr.p[5], pr.p[6], pr.p[7], at1, at2);
                cond co1 = add_pred_4p_tang(1, CO_TANG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], t1);
                cond co2 = add_pred_4p_tang(1, CO_TANG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], t2);
                pr.addcond(co1, co2);
            }
            break;
            case 132: {
                angtn a1 = pr1.u.atn;
                angtn atn = pr2.u.atn;
                l_line[] ls1 = get4lntn(pr.p[0], pr.p[1], pr.p[2], pr.p[3], a1.ln1, a1.ln2, a1.ln3, a1.ln4);
                if (ls1 == null)
                    ls1 = get4lntn(pr.p[0], pr.p[1], pr.p[2], pr.p[3], atn.ln1, atn.ln2, atn.ln3, atn.ln4);

                l_line[] ls2 = get4lntn(pr.p[4], pr.p[5], pr.p[6], pr.p[7], a1.ln1, a1.ln2, a1.ln3, a1.ln4);
                if (ls2 == null)
                    ls2 = get4lntn(pr.p[4], pr.p[5], pr.p[6], pr.p[7], atn.ln1, atn.ln2, atn.ln3, atn.ln4);


                if (ls1 == null || ls2 == null) {
                    cond co1 = add_pred(1, CO_ATNG, pr.p[0], pr.p[1], pr.p[4], pr.p[5], pr.p[2], pr.p[3], pr.p[6], pr.p[7]);
                    pr.addcond(co1);
                } else {
                    cond co1 = add_as_atn(0, CO_ATNG, pr.p[0], pr.p[1], pr.p[2], pr.p[3], ls1[0], ls1[1]);
                    cond co2 = add_as_atn(0, CO_ATNG, pr.p[4], pr.p[5], pr.p[6], pr.p[7], ls2[0], ls2[1]);
                    pr.addcond(co1, co2);
                }

            }
            break;
            case 182:
            case 183: {
                if (as1 != null && as2 != null)
                    add_as82_t(pr, as1, as2);
            }
            break;
            default: {
                if (as1 != null && as2 != null) {
                    if (isPFull())
                        add_as82(pr, as1, as2);
                } else {
                    l_line ls = pr1.u.ln;
                    angles as = pr2.u.as;
                    angles s1 = pr.u.as;
                    l1 = as.l1;
                    l2 = as.l2;
                    l3 = as.l3;
                    l4 = as.l4;
                    l_line ln = new l_line();
                    ln.no = -1;

                    for (int i = 0; i < 4; i++) {
                        if (on_ln(pr.p[2 * i], pr.p[2 * i + 1], ls)) {
                            this.add_pt2l(pr.p[2 * i], ln);
                            this.add_pt2l(pr.p[2 * i + 1], ln);
                        }
                    }
                    for (int i = 0; i <= ls.no && ln.no < 2; i++)
                        add_pt2l(ls.pt[i], ln);
                    cond co1 = add_pred(0, CO_COLL, ln.pt[0], ln.pt[1], ln.pt[2], 0, 0, 0, 0, 0);
                    cond co2 = add_as_pred(1, CO_ACONG, as.l1, as.l2, as.l3, as.l4);
                    pr.addcond(co1, co2);
                }
            }
            break;
        }
    }

    public void add_as82_t(cond pr, angles as1, angles as2) {
        l_line l1, l2, l3, l4, l5, l6, l7, l8;
        l1 = as1.l1;
        l2 = as1.l2;
        l3 = as1.l3;
        l4 = as1.l4;
        l5 = as2.l1;
        l6 = as2.l2;
        l7 = as2.l3;
        l8 = as2.l4;

        boolean b = add_as82t1(pr, l1, l2, l3, l4, l5, l6, l7, l8);
        if (!b)
            b = add_as82t1(pr, l1, l2, l3, l4, l7, l8, l5, l6);
        if (!b)
            b = add_as82t1(pr, l3, l4, l1, l2, l5, l6, l7, l8);
        if (!b)
            b = add_as82t1(pr, l3, l4, l1, l2, l7, l8, l5, l6);

        //
        if (!b)
            b = add_as82t1(pr, l1, l2, l3, l4, l6, l5, l8, l7);
        if (!b)
            b = add_as82t1(pr, l1, l2, l3, l4, l8, l7, l6, l5);
        if (!b)
            b = add_as82t1(pr, l3, l4, l1, l2, l6, l5, l8, l7);
        if (!b)
            b = add_as82t1(pr, l3, l4, l1, l2, l8, l7, l6, l5);

        if (!b) {
            cond c1 = add_as_pred(0, CO_ACONG, as1.l1, as1.l2, as1.l3, as1.l4);
            cond c2 = add_as_pred(0, CO_ACONG, as2.l1, as2.l2, as2.l3, as2.l4);
            pr.addcond(c1, c2);
        }
        return;
    }

    public boolean add_as82t1(cond pr, l_line l1, l_line l2, l_line l3, l_line l4,
                              l_line l5, l_line l6, l_line l7, l_line l8) {

        int[] p = pr.p;
        l_line[] lns = null;
        if (l2 == l5 && l4 == l7 && (lns = get_cond_lns(pr.p, l1, l2, l6, l3, l4, l8)) != null) {
            l2 = lns[0];
            l4 = lns[1];
            cond c1 = add_as_pred1(0, CO_ACONG, p[0], p[1], l2, p[4], p[5], l4);
            cond c2 = add_as_pred1(0, CO_ACONG, p[2], p[3], l2, p[6], p[7], l4);
            pr.addcond(c1, c2);
        } else if (l2 == l5 && l3 == l8 && (lns = get_cond_lns(pr.p, l1, l2, l6, l7, l3, l4)) != null) {
            l2 = lns[0];
            l3 = lns[1];
            cond c1 = add_as_pred1(0, CO_ACONG, p[0], p[1], l2, l3, p[6], p[7]);
            cond c2 = add_as_pred1(0, CO_ACONG, l2, p[2], p[3], p[4], p[5], l3);
            pr.addcond(c1, c2);
        } else if (l1 == l6 && l4 == l7 && (lns = get_cond_lns(pr.p, l5, l1, l2, l3, l4, l8)) != null) {
            l1 = lns[0];
            l4 = lns[1];
            cond c1 = add_as_pred1(0, CO_ACONG, l1, p[2], p[3], p[4], p[5], l4);
            cond c2 = add_as_pred1(0, CO_ACONG, p[0], p[1], l1, l4, p[6], p[7]);
            pr.addcond(c1, c2);
        } else if (l1 == l6 && l3 == l8 && (lns = get_cond_lns(pr.p, l5, l1, l2, l4, l3, l7)) != null) {
            l1 = lns[0];
            l3 = lns[1];
            cond c1 = add_as_pred1(0, CO_ACONG, p[2], p[3], l1, p[6], p[7], l3);
            cond c2 = add_as_pred1(0, CO_ACONG, p[0], p[1], l1, p[4], p[5], l3);
            pr.addcond(c1, c2);
        } else
            return false;
        return true;
    }

    l_line[] get_cond_lns(int[] p, l_line l1, l_line s1, l_line l2, l_line l3, l_line s2, l_line l4) {
        l_line[] lns = get_cond_ln(p, l1, s1, l2, l3, s2, l4);
        if (lns != null) return lns;
        lns = get_cond_ln(p, l2, s1, l1, l4, s2, l3);
        if (lns != null) return lns;
        lns = get_cond_ln(p, l3, s2, l4, l1, s1, l2);
        if (lns != null) return lns;
        lns = get_cond_ln(p, l4, s2, l3, l2, s1, l1);
        if (lns != null) return lns;
        return null;
    }

    public l_line[] get_cond_ln(int[] p, l_line l1, l_line s1, l_line l2, l_line l3, l_line s2, l_line l4) {
        if (!on_ln4(p, l1, l2, l3, l4)) return null;
        l_line[] ns = new l_line[2];
        ns[0] = s1;
        ns[1] = s2;
        return ns;
    }

    public boolean on_ln4(int[] p, l_line l1, l_line l2, l_line l3, l_line l4) {
        return on_ln(p[0], p[1], l1) && on_ln(p[2], p[3], l2) && on_ln(p[4], p[5], l3) && on_ln(p[6], p[7], l4);
    }

    public void add_as82(cond pr, angles as1, angles as2) {
        l_line l1, l2, l3, l4;
        l1 = l2 = l3 = l4 = null;

        l_line[] lns = null;
        lns = geti82(pr.p[0], pr.p[1], pr.p[6], pr.p[7], as1);
        if (lns != null) {
            l1 = lns[0];
            l2 = lns[1];
        }
        lns = geti82(pr.p[4], pr.p[5], pr.p[2], pr.p[3], as2);
        if (lns != null) {
            l3 = lns[0];
            l4 = lns[1];
        }
        if (l1 == null || l2 == null || l3 == null || l4 == null) {
            lns = geti82(pr.p[0], pr.p[1], pr.p[6], pr.p[7], as2);
            if (lns != null) {
                l1 = lns[0];
                l2 = lns[1];
            }
            geti82(pr.p[4], pr.p[5], pr.p[2], pr.p[3], as1);
            if (lns != null) {
                l3 = lns[0];
                l4 = lns[1];
            }
        }
        cond co1 = add_as_pred2(0, CO_ACONG, pr.p[0], pr.p[1], l1, l2, pr.p[6], pr.p[7]);
        cond co2 = add_as_pred2(1, CO_ACONG, pr.p[4], pr.p[5], l3, l4, pr.p[2], pr.p[3]);
        pr.addcond(co1, co2);
    }

    public cond add_pred_4p_tang(int m, int n, int p1, int p2, int p3, int p4, int p5) {
        int t1, t2, t3;
        t1 = t2 = t3 = 0;
        if (p1 == p3) {
            t1 = p2;
            t2 = p1;
            t3 = p4;
        } else if (p1 == p4) {
            t1 = p2;
            t2 = p1;
            t3 = p3;
        } else if (p2 == p3) {
            t1 = p1;
            t2 = p2;
            t3 = p4;
        } else if (p2 == p4) {
            t1 = p1;
            t2 = p2;
            t3 = p3;
        }
        return add_pred(m, n, t1, t2, t3, p5, 0, 0, 0, 0);
    }

    public int get_at2_v(int p1, int p2, int p3, int p4, anglet at1, anglet at2) {
        l_line l1 = at1.l1;
        l_line l2 = at1.l2;
        l_line l3 = at2.l1;
        l_line l4 = at2.l2;
        if (on_ln(p1, p2, l1) && on_ln(p3, p4, l2))
            return at1.v;
        if (on_ln(p1, p2, l2) && on_ln(p3, p4, l1))
            return -at1.v;
        if (on_ln(p1, p2, l3) && on_ln(p3, p4, l4))
            return at2.v;
        if (on_ln(p1, p2, l4) && on_ln(p3, p4, l3))
            return -at2.v;
        return 0;
    }

    l_line[] get4lntn(int a, int b, int c, int d, l_line ln1, l_line ln2, l_line ln3, l_line ln4) {
        l_line[] ls = new l_line[2];
        if (on_ln(a, b, ln1) && on_ln(c, d, ln2)) {
            ls[0] = ln3;
            ls[1] = ln4;
        } else if (on_ln(a, b, ln2) && on_ln(c, d, ln1)) {
            ls[0] = ln4;
            ls[1] = ln3;
        } else if (on_ln(a, b, ln3) && on_ln(c, d, ln4)) {
            ls[0] = ln1;
            ls[1] = ln2;
        } else if (on_ln(a, b, ln4) && on_ln(c, d, ln3)) {
            ls[0] = ln2;
            ls[1] = ln1;
        } else
            return null;
        return ls;
    }

    void forw_eqangle(cond pr) {
        if (pr.pred != gib.CO_ACONG) return;
        l_line ln1 = fd_ln(pr.p[0], pr.p[1]);
        l_line ln2 = fd_ln(pr.p[2], pr.p[3]);
        l_line ln3 = fd_ln(pr.p[4], pr.p[5]);
        l_line ln4 = fd_ln(pr.p[6], pr.p[7]);
        if (ln1 == null || ln2 == null || ln3 == null || ln4 == null) return;
        if (ln1 == ln3 && ln2 == ln4) {
            cond co1 = add_pred(0, CO_COLL, pr.p[0], pr.p[1], pr.p[4], pr.p[5], 0, 0, 0, 0);
            cond co2 = add_pred(0, CO_COLL, pr.p[2], pr.p[3], pr.p[6], pr.p[7], 0, 0, 0, 0);
            pr.addcond(co1, co2);
        }
    }

    void for_c4(int p1, int p2, int p3, int p4) {
        int[] ps = new int[4];
        int i, p = -1;

        if (p1 != 0 && p1 != p2 && p1 != p3 && p1 != p4) {
            p++;
            ps[p] = p1;
        }
        if (p2 != 0 && p2 != p3 && p2 != p4) {
            p++;
            ps[p] = p2;
        }
        if (p3 != 0 && p3 != p4) {
            p++;
            ps[p] = p3;
        }
        if (p4 != 0) {
            p++;
            ps[p] = p4;
        }
        if (p > 1) {

            gprint(Cm.s2707);
            gprint("[");
            for (i = 0; i <= p; i++) {
                gprint(ANAME(ps[i]));
            }
            gprint(Cm.s2070);
            gprint(", ");
        }
    }

    void for_c6(int p1, int p2, int p3, int p4, int p5, int p6) {
        int[] ps = new int[6];
        int i, p = -1;
        if (p1 != 0 && p1 != p2 && p1 != p3 && p1 != p4 && p1 != p5 && p1 != p6) {
            p++;
            ps[p] = p1;
        }
        if (p2 != 0 && p2 != p3 && p2 != p4 && p2 != p5 && p2 != p6) {
            p++;
            ps[p] = p2;
        }
        if (p3 != 0 && p3 != p4 && p3 != p5 && p3 != p6) {
            p++;
            ps[p] = p3;
        }
        if (p4 != 0 && p4 != p5 && p4 != p6) {
            p++;
            ps[p] = p4;
        }
        if (p5 != 0 && p5 != p6) {
            p++;
            ps[p] = p5;
        }
        if (p6 != 0) {
            p++;
            ps[p] = p6;
        }
        if (p > 1) {
            gprint(Cm.s2707);
            gprint("[");
            for (i = 0; i <= p; i++) {
                gprint(ANAME(ps[i]));
            }
            gprint("]");
            gprint(Cm.s2070);
            gprint(", ");
        }
    }

    void show_nco(cond co) {
        gprint("(" + co.no + ")");
        show_dtype = 0;
        show_pred(co);
    }

    boolean show_allpred() {
        cond co = all_nd.nx;
        cond pr1;
        while (co != null) {
            show_pred(co);
            Vector v = co.vlist;
            if (v != null)
                for (int i = 0; i < v.size(); i++) {
                    pr1 = (cond) v.get(i);
                    show_pred(pr1);
                }
            co = co.nx;
        }
        return true;
    }


    boolean show_pred(cond co) {
        switch (co.pred) {
            case 0:
                break;
            case CO_COLL: {
                if (co.p[0] != 0) {

                    int k = 0;
                    for (int i = 0; i <= 5; i++) if (co.p[i] != 0) k = i;

                    String st = "";
                    for (int i = 0; i <= k; i++) {
                        if (co.p[i] != 0) {
                            st += (ANAME(co.p[i]));
                            if (i != k) st += (",");
                        }
                    }
                    co.sd = st + (" " + Cm.s2760);
                } else {
                    this.setPrintToString();
                    this.show_ln(co.u.ln, true);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_PARA: {
                if (co.p[0] != 0) {

                    String str = ANAME(co.p[0]) + ANAME(co.p[1]) + Cm.s2079 +
                            ANAME(co.p[2]) + ANAME(co.p[3]);
                    gprint(str);
                    co.sd = str;
                } else {
                    this.setPrintToString();
                    this.show_pn(co.u.pn);
                    co.sd = sout.toString();
                }
            }
            break;

            case CO_PERP: {
                if (co.p[0] != 0) {
                    String str = ANAME(co.p[0]) + ANAME(co.p[1]) + " " + Cm.s2077 + " "
                            + ANAME(co.p[2]) + ANAME(co.p[3]);
                    co.sd = str;
                } else {
                    this.setPrintToString();
                    this.show_tn(co.u.tn);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_RATIO: {
                int t1 = Sqrt(co.p[4]);
                int t2 = Sqrt(co.p[5]);
                String s = "";
                if (t1 > 0 && t2 > 0)
                    s = t1 + "/" + t2;
                else {
                    if (t1 > 0)
                        s += t1;
                    else
                        s += "sqrt(" + co.p[4] + ")";
                    s += "/";
                    if (t2 > 0)
                        s += t2;
                    else
                        s += "sqrt(" + co.p[5] + ")";
                }

                String str = ANAME(co.p[0]) + ANAME(co.p[1]) + "/" +
                        ANAME(co.p[2]) + ANAME(co.p[3]) + " = " + s;
                gprint(str);
                co.sd = str;
            }
            break;
            case CO_CONG: /* cong */ {
                if (co.p[0] != 0) {
                    if (co.p[4] == co.p[5]) {
                        String str = ANAME(co.p[0]) + ANAME(co.p[1]) + " = " + ANAME(co.p[2]) + ANAME(co.p[3]);
                        gprint(str);
                        co.sd = str;
                    } else {
                        int t1 = Sqrt(co.p[4]);
                        int t2 = Sqrt(co.p[5]);
                        String s = "";
                        if (t1 > 0 && t2 > 0)
                            s = t1 + "/" + t2;
                        else {
                            if (t1 > 0)
                                s += t1;
                            else
                                s += "sqrt(" + co.p[4] + ")";
                            s += "/";
                            if (t2 > 0)
                                s += t2;
                            else
                                s += "sqrt(" + co.p[5] + ")";
                        }

                        String str = ANAME(co.p[0]) + ANAME(co.p[1]) + "/" +
                                ANAME(co.p[2]) + ANAME(co.p[3]) + " = " + s;
                        gprint(str);
                        co.sd = str;
                    }
                } else {
                    this.setPrintToString();
                    this.show_cg(co.u.cg);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_ACONG: {
                if (co.p[0] != 0) {
                    if (isPFull() || check_eqangle_t(co.p[0], co.p[1], co.p[2], co.p[3],
                            co.p[4], co.p[5], co.p[6], co.p[7])) {

                        String str = get_fang_str(co.p[0], co.p[1], co.p[2], co.p[3]);
                        str += (" = ");
                        str += get_fang_str(co.p[4], co.p[5], co.p[6], co.p[7]);
                        co.sd = str;
                    } else {
                        String str = get_fang_str(co.p[0], co.p[1], co.p[2], co.p[3]);
                        str += (" + ");
                        str += get_fang_str(co.p[4], co.p[5], co.p[6], co.p[7]);
                        str += " = 180";
                        co.sd = str;
                    }

                } else {
                    this.setPrintToString();
                    this.show_as(co.u.as);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_TANG:
                if (co.p[0] != 0) {
                    String str = get_fang_str(co.p[0], co.p[1], co.p[1], co.p[2]);
                    int v = getAtv(co.p[0], co.p[1], co.p[2], co.p[3]);
                    if (v == 0)
                        v = co.p[3] * 10;

                    if (v % A_TIME == 0)
                        co.sd = str + " = " + v / A_TIME;
                    else
                        co.sd = str + " = " + ((float) v) / A_TIME;
                } else {
                    this.setPrintToString();
                    this.show_at(co.u.at);
                    co.sd = sout.toString();
                }
                break;
            case CO_ATNG:
                if (co.p[0] != 0) {
                    String str = get_fang_str(co.p[0], co.p[1], co.p[2], co.p[3]);
                    String str1 = get_fang_str(co.p[4], co.p[5], co.p[6], co.p[7]);
                    co.sd = str + " + " + str1 + " = 90";
                } else {
                    this.setPrintToString();
                    this.show_atn(co.u.atn);
                    co.sd = sout.toString();
                }
                break;
            case CO_MIDP: {
                if (co.p[0] != 0) {
                    String str = Cm.s2729 + "(" + ANAME(co.p[0]) + "," + ANAME(co.p[1]) + ANAME(co.p[2]) + ")";
                    co.sd = str;
                } else {
                    this.setPrintToString();
                    this.show_md(co.u.md);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_PROD: /*eq_produc, eq_ratio*/

            {
                if (co.p[0] != 0) {
                    String st = ANAME(co.p[0]) + ANAME(co.p[1]) + "*" +
                            ANAME(co.p[6]) + ANAME(co.p[7]) + " = " +
                            ANAME(co.p[2]) + ANAME(co.p[3]) + "*" +
                            ANAME(co.p[4]) + ANAME(co.p[5]);
                    co.sd = st;
                } else {
                    this.setPrintToString();
                    show_ra(co.u.ra);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_CYCLIC: /*circle */
                if (co.p[0] != 0 && co.p[3] == 0) {
                    String st = "";
                    st = ANAME(co.p[0]) + ANAME(co.p[1]) + " = " +
                            ANAME(co.p[0]) + ANAME(co.p[2]);
                    co.sd = st;
                } else if (co.p[1] != 0) {
                    String st = (Cm.s2732);
                    st += ("(");
                    if (co.p[0] != 0) {
                        st += ("(" + ANAME(co.p[0]) + ")");
                    }
                    st += show_pts(co, 1);
                    st += (")");
                    co.sd = st;

                } else {

                    this.setPrintToString();
                    this.show_cr(co.u.cr);
                    co.sd = sout.toString();
                }
                break;
            case CO_STRI: {
                if (co.p[0] != 0) {
                    String st = Cm.PC_TRI + " " + ANAME(co.p[0]) + ANAME(co.p[1]) + ANAME(co.p[2]) + Cm.s2083 +//" ~ " +
                            Cm.PC_TRI + " " + ANAME(co.p[3]) + ANAME(co.p[4]) + ANAME(co.p[5]);
                    co.sd = st;
                } else {
                    this.setPrintToString();
                    this.show_ct(co.u.st);
                    co.sd = sout.toString();
                }
            }
            break;
            case CO_CTRI: {
                if (co.p[0] != 0) {
                    String st = Cm.PC_TRI + " " + ANAME(co.p[0]) + ANAME(co.p[1]) + ANAME(co.p[2]) + Cm.s2082 +//" = " +
                            Cm.PC_TRI + " " + ANAME(co.p[3]) + ANAME(co.p[4]) + ANAME(co.p[5]);
                    co.sd = st;
                } else {
                    this.setPrintToString();
                    this.show_ct(co.u.st);
                    co.sd = sout.toString();
                }
            }
        }
        return true;
    }

    boolean compare_pred(cond co, cond pr)   // do_pred(x,x,3)   ----------> check two predicate is the same.
    {
        if (co.pred != pr.pred) return (false);
        boolean ex = true;
        for (int i = 0; i <= 7; i++)
            if (co.p[i] != pr.p[i]) {
                ex = false;
                break;
            }
        if (ex)
            return (true);

        switch (co.pred) {
            case 0:
                break;
            case CO_COLL:
                break;
            case CO_PARA: /* para */
                if (((co.p[0] == pr.p[0] && co.p[1] == pr.p[1]) || (co.p[0] == pr.p[1] && co.p[1] == pr.p[0])) &&
                        ((co.p[2] == pr.p[2] && co.p[3] == pr.p[3]) || (co.p[2] == pr.p[3] && co.p[3] == pr.p[2])))
                    ex = true;
                else if (((co.p[0] == pr.p[2] && co.p[1] == pr.p[3]) || (co.p[0] == pr.p[3] && co.p[1] == pr.p[2])) &&
                        ((co.p[2] == pr.p[0] && co.p[3] == pr.p[1]) || (co.p[2] == pr.p[1] && co.p[3] == pr.p[0])))
                    ex = true;
                break;
            case CO_PERP:
                if (((co.p[0] == pr.p[0] && co.p[1] == pr.p[1]) || (co.p[0] == pr.p[1] && co.p[1] == pr.p[0])) &&
                        ((co.p[2] == pr.p[2] && co.p[3] == pr.p[3]) || (co.p[2] == pr.p[3] && co.p[3] == pr.p[2])))
                    ex = true;
                else if (((co.p[0] == pr.p[2] && co.p[1] == pr.p[3]) || (co.p[0] == pr.p[3] && co.p[1] == pr.p[2])) &&
                        ((co.p[2] == pr.p[0] && co.p[3] == pr.p[1]) || (co.p[2] == pr.p[1] && co.p[3] == pr.p[0])))
                    ex = true;
                break;
            case CO_CONG: /* cong */
                if (co.p[0] == pr.p[1] && co.p[1] == pr.p[0] && co.p[2] == pr.p[3] && co.p[3] == pr.p[2])
                    ex = true;
                else if (co.p[0] == pr.p[2] && co.p[1] == pr.p[3] && co.p[2] == pr.p[0] && co.p[3] == pr.p[1])
                    ex = true;
                else if (co.p[0] == pr.p[3] && co.p[1] == pr.p[2] && co.p[2] == pr.p[1] && co.p[3] == pr.p[0])
                    ex = true;
                break;
            case CO_ACONG:
                break;
            case CO_MIDP:
                if (co.p[0] == pr.p[0] && co.p[1] == pr.p[2] && co.p[2] == pr.p[1])
                    ex = true;
                break;
            case CO_PROD:
                break;
            case CO_CYCLIC:
                if (co.p[0] == pr.p[0] && eq_chs(co.p, 7, pr.p, 7))
                    ex = true;
                break;
            case CO_STRI:
            case CO_CTRI:
                break;
            case CO_ORTH:
            case CO_INCENT:
                break;
        }
        return ex;
    }

    boolean check_pred(cond co) // if it obviousely.
    {
        boolean va = false;
        switch (co.pred) {
            case 0:
                break;
            case CO_COLL:
                if (co.p[0] != 0 && (co.p[0] == co.p[1] || co.p[0] == co.p[2] || co.p[1] == co.p[2]))
                    va = true;
                break;
            case CO_PARA: /* para */
                if (co.p[0] != 0 && (co.p[0] == co.p[1] || co.p[2] == co.p[3]))
                    va = true;
                break;
            case CO_PERP: /* perp */
                if (co.p[0] != 0 &&
                        (co.p[0] == co.p[1] || co.p[2] == co.p[3]))
                    va = true;
                break;
            case CO_CONG: /* cong */
                if (co.p[0] != 0 && (co.p[0] == co.p[1] && co.p[2] == co.p[3]))
                    va = true;
                break;

            case CO_ACONG:  /* eqangle */
                break;
            case CO_MIDP: /* midpoint */
                break;
            case CO_PROD: /*eq_produc, eq_ratio*/
                break;
            case CO_CYCLIC: /*circle */
                break;
            default:
                break;
        }
        return va;

    }

    final void do_pred(cond co) {

        switch (co.pred) {
            case 0:
                break;
            case CO_COLL:
                if (co.p[0] != 0)
                    co.u.ln = fo_ln(co.p, 7);
                break;
            case CO_PARA:
                if (co.p[0] != 0)
                    co.u.pn = fo_pn1(co.p[0], co.p[1], co.p[2], co.p[3]);
                break;
            case CO_PERP:
                if (co.p[0] != 0)
                    co.u.tn = fo_tn1(co.p[0], co.p[1], co.p[2], co.p[3]);
                break;
            case CO_CONG:
                if (co.p[0] != 0) {
                    co.u.cg = fo_cg(co.p[0], co.p[1], co.p[2], co.p[3]);
                    if (co.u.cg == null)
                        co.u.cg = fo_cg1(co.p[0], co.p[1], co.p[2], co.p[3]);
                }
                break;
            case CO_ACONG:
                if (co.p[0] != 0)
                    co.u.as = fo_as1(co.p[0], co.p[1], co.p[2], co.p[3], co.p[4], co.p[5], co.p[6], co.p[7]);
                break;
            case CO_TANG:
                if (co.p[0] != 0)
                    co.u.at = fo_at(co.p[0], co.p[1], co.p[2]);
                break;
            case CO_ATNG:
                if (co.p[0] != 0)
                    co.u.atn = fo_atn(co.p[0], co.p[1], co.p[2], co.p[3], co.p[4], co.p[5], co.p[6], co.p[7]);
                break;
            case CO_MIDP:
                if (co.p[0] != 0)
                    co.u.md = fo_md(co.p[0], co.p[1], co.p[2]);
                break;
            case CO_PROD:
                if (co.p[0] != 0)
                    co.u.ra = fo_ra(co.p[0], co.p[1], co.p[2], co.p[3], co.p[4], co.p[5], co.p[6], co.p[7]);
                break;
            case CO_CYCLIC:
                if (co.p[1] != 0)
                    co.u.cr = fo_cr(co.p[0], co.p[1], co.p[2], co.p[3], co.p[4]);
                break;
            case CO_STRI:
            case CO_CTRI:
                if (co.p[0] != 0)
                    co.u.st = fo_st((co.pred == CO_CTRI ? 0 : 1), 1, co.p[0], co.p[1], co.p[2], co.p[3], co.p[4], co.p[5]);
                break;
            case CO_ORTH:
            case CO_INCENT:
                break;
            case CO_RATIO: {
                co.u.cg = fo_cg1(co.p[0], co.p[1], co.p[2], co.p[3]);
                break;
            }
            default:
                gprint("co-pred " + co.pred + "error");
                break;
        }
    }


    String show_pts(cond co, int n) {
        int i, k;
        i = k = 0;
        for (i = n; i <= 5; i++) if (co.p[i] != 0) k = i;

        String s = "";
        for (i = n; i <= k; i++) {
            if (co.p[i] != 0) {
                s += (ANAME(co.p[i]));
                if (i != k) s += (",");
            }
        }
        return s;
    }

    void show_copt(cond co, int n) {
        int i, k;
        i = k = 0;
        for (i = n; i <= 5; i++) if (co.p[i] != 0) k = i;

        for (i = n; i <= k; i++) {
            if (co.p[i] != 0) {
                gprint(ANAME(co.p[i]));
                if (i != k) gprint(",");
            }
        }
    }


    boolean sdiff(int a, int b, int c, int d, int ps[]) {
        int i;
        for (i = 0; i <= 7; i++) {
            if (ps[i] == a || ps[i] == b || ps[i] == c || ps[i] == d) continue;
            return (true);
        }
        return (false);
    }

    boolean eq_chs(int[] ch1, int n1, int[] ch2, int n2)   // Check !!
    {
        int id1 = 1;

        for (int i = 1; i <= n1; i++) {
            int j = 0;
            for (j = 0; j <= (n2 - 1); j++)
                if (ch1[id1] == ch2[j + 1])
                    break;
            if (j > (n2 - 1))
                return (false);
            id1++;
        }
        id1 = 1;
        for (int i = 1; i <= n2; i++) {
            int j = 0;
            for (j = 0; j <= (n1 - 1); j++)
                if (ch2[id1] == ch1[j + 1])
                    break;
            if (j > (n1 - 1))
                return (false);
            id1++;
        }
        return (true);
    }

    l_line[] geti81(int a, int b, int c, int d, angles as) {
        l_line l1, l2, l3, l4;
        l1 = as.l1;
        l2 = as.l2;
        l3 = as.l3;
        l4 = as.l4;
        return get4ln(l1, l2, l3, l4, a, b, c, d);
    }

    l_line[] get4ln(l_line l1, l_line l2, l_line l3, l_line l4, int a, int b, int c, int d) {
        l_line[] ln = new l_line[2];

        if (l1.type == 0)
            l1 = fd_ln(l1.pt[0], l1.pt[1]);
        if (l2.type == 0)
            l2 = fd_ln(l2.pt[0], l2.pt[1]);
        if (l3.type == 0)
            l3 = fd_ln(l3.pt[0], l3.pt[1]);
        if (l4.type == 0)
            l4 = fd_ln(l4.pt[0], l4.pt[1]);

        if (on_ln(a, l1) && on_ln(b, l1) && on_ln(c, l2) && on_ln(d, l2)) {
            ln[0] = l3;
            ln[1] = l4;
        } else if (on_ln(a, l2) && on_ln(b, l2) && on_ln(c, l1) && on_ln(d, l1)) {
            ln[0] = l4;
            ln[1] = l3;
        } else if (on_ln(a, l1) && on_ln(b, l1) && on_ln(c, l3) && on_ln(d, l3)) {
            ln[0] = l2;
            ln[1] = l4;
        } else if (on_ln(a, l3) && on_ln(b, l3) && on_ln(c, l1) && on_ln(d, l1)) {
            ln[0] = l4;
            ln[1] = l2;
        } else if (on_ln(a, l3) && on_ln(b, l3) && on_ln(c, l4) && on_ln(d, l4)) {
            ln[0] = l1;
            ln[1] = l2;
        } else if (on_ln(a, l4) && on_ln(b, l4) && on_ln(c, l3) && on_ln(d, l3)) {
            ln[0] = l2;
            ln[1] = l1;
        } else if (on_ln(a, l2) && on_ln(b, l2) && on_ln(c, l4) && on_ln(d, l4)) {
            ln[0] = l1;
            ln[1] = l3;
        } else if (on_ln(a, l4) && on_ln(b, l4) && on_ln(c, l2) && on_ln(d, l2)) {
            ln[0] = l3;
            ln[1] = l1;
        } else
            return null;
        return ln;

    }

    l_line[] geti82(int a, int b, int c, int d, angles as) {
        l_line l1, l2, l3, l4;
        l1 = as.l1;
        l2 = as.l2;
        l3 = as.l3;
        l4 = as.l4;
        if (l1.type == 0)
            l1 = fd_ln(l1.pt[0], l1.pt[1]);
        if (l2.type == 0)
            l2 = fd_ln(l2.pt[0], l2.pt[1]);
        if (l3.type == 0)
            l3 = fd_ln(l3.pt[0], l3.pt[1]);
        if (l4.type == 0)
            l4 = fd_ln(l4.pt[0], l4.pt[1]);

        l_line[] ln = new l_line[2];
        if (on_ln(a, l1) && on_ln(b, l1) && on_ln(c, l4) && on_ln(d, l4)) {
            ln[0] = l2;
            ln[1] = l3;
        } else if (on_ln(a, l4) && on_ln(b, l4) && on_ln(c, l1) && on_ln(d, l1)) {
            ln[0] = l3;
            ln[1] = l2;
        } else if (on_ln(a, l2) && on_ln(b, l2) && on_ln(c, l3) && on_ln(d, l3)) {
            ln[0] = l1;
            ln[1] = l4;
        } else if (on_ln(a, l3) && on_ln(b, l3) && on_ln(c, l2) && on_ln(d, l2)) {
            ln[0] = l1;
            ln[1] = l4;
        } else
            return null;
        return ln;
    }

    boolean conc_xtrue() {
        return (docc());
    }


    boolean docc() {
        boolean j = false;
        switch (conc.pred) {
            case CO_COLL:  /* collinear */
                j = xcoll(conc.p[0], conc.p[1], conc.p[2]);
                break;
            case CO_PARA:  /* parallel */
                j = xpara(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                break;
            case CO_PERP: /* perpendicular */
                j = xperp(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                break;
            case CO_CONG: /*congruent */
                if (conc.p[4] == conc.p[5])
                    j = xcong(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                else {
                    j = xcong1(conc.p[0], conc.p[1], conc.p[2], conc.p[3], conc.p[4], conc.p[5]);
                }
                break;
            case CO_ACONG: /* angle congruent */
                j = xacong(conc.p[0], conc.p[1], conc.p[2], conc.p[3], conc.p[4], conc.p[5], conc.p[6], conc.p[7]);
                break;
            case CO_TANG:
                j = this.xatcong(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                break;
            case CO_MIDP: /* midpoint */
                j = xmid(conc.p[0], conc.p[1], conc.p[2]);
                break;
            case CO_PROD: /* eq-product */
                j = xeq_ratio(conc.p[0], conc.p[1], conc.p[2], conc.p[3],
                        conc.p[4], conc.p[5], conc.p[6], conc.p[7]);
                break;
            case CO_CYCLIC:  /*cocircle */
                j = xcir4(0, conc.p[1], conc.p[2], conc.p[3], conc.p[4]);
                break;
            case CO_STRI:  // sim_triangle
                j = xsim_tri(conc.p[0], conc.p[1], conc.p[2],
                        conc.p[3], conc.p[4], conc.p[5]);
                break;
            case CO_CTRI:  //con_trinagle
                j = xcon_tri(conc.p[0], conc.p[1], conc.p[2],
                        conc.p[3], conc.p[4], conc.p[5]);
                break;
            case CO_PBISECT: {
                gprint("add here!");
            }
            break;
            case CO_RATIO: {
                j = xcong1(conc.p[0], conc.p[1], conc.p[2], conc.p[3], conc.p[4], conc.p[5]);
            }
            break;
            case CO_ORTH: {

                j = xperp(conc.p[0], conc.p[1], conc.p[2], conc.p[3])
                        && xperp(conc.p[0], conc.p[2], conc.p[1], conc.p[3]);

                break;
            }
            case CO_INCENT: {
                break;
            }


            default:
                gprint("not supported conclusion!");

        }
        return (j);
    }

    ///////////////////////////////////////////
    // pred.


    void show_cos(cond co) {
        if (co != null) {
            gprint(Cm.s2727);
            show_co(co);
            co = co.nx;
            if (co == null) {
            } else if (co.nx == null) {
                gprint(Cm.s2728);
                show_co(co);
            } else {
                while (co != null) {
                    gprint(", ");
                    show_co(co);
                    co = co.nx;
                }
            }
            gprint(".");
        }
    }


    public void gen_dbase_text() {
        this.setPrintToString();

        midpt md = all_md.nx;
        while (md != null) {
            show_md(md);
            md.text = this.getPrintedString();
            md = md.nx;
        }
        l_line ln = all_ln.nx;
        while (ln != null) {
            show_ln(ln, true);
            ln.text = this.getPrintedString();
            ln = ln.nx;
        }
        p_line pn = all_pn.nx;
        while (pn != null) {
            show_pn(pn);
            pn.text = this.getPrintedString();
            pn = pn.nx;
        }
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0) {
                show_tn(tn);
                tn.text = this.getPrintedString();
            }
            tn = tn.nx;
        }
        a_cir cr = all_cir.nx;
        while (cr != null) {
            show_cr(cr);
            cr.text = this.getPrintedString();
            cr = cr.nx;
        }
        angles as = all_as.nx;
        while (as != null) {
            show_as(as);
            as.text = this.getPrintedString();
            as = as.nx;
        }
        angst ast = all_ast.nx;
        while (ast != null) {
            show_ast(ast);
            ast.text = this.getPrintedString();
            ast = ast.nx;
        }
        anglet at = all_at.nx;
        while (at != null) {
            show_at(at);
            at.text = this.getPrintedString();
            at = at.nx;
        }
        angtn atn = all_atn.nx;
        while (atn != null) {
            if (atn.type != 0) {
                show_atn(atn);
                atn.text = this.getPrintedString();
            }
            atn = atn.nx;
        }

        c_segs cgs = all_cgs.nx;
        while (cgs != null) {
            if (cgs.type != 0) {
                show_cseg(cgs);
                cgs.text = this.getPrintedString();
            }
            cgs = cgs.nx;
        }

        cong_seg cg = all_rg.nx;
        while (cg != null) {
            show_cg(cg);
            cg.text = this.getPrintedString();
            cg = cg.nx;
        }
        ratio_seg ra = all_ra.nx;
        while (ra != null) {
            show_ra(ra);
            ra.text = this.getPrintedString();
            ra = ra.nx;
        }
        s_tris sts = all_sts.nx;
        while (sts != null) {
            show_sts(sts);
            sts.text = this.getPrintedString();
            sts = sts.nx;
        }
        sts = all_cts.nx;
        while (sts != null) {
            show_sts(sts);
            sts.text = this.getPrintedString();
            sts = sts.nx;
        }
    }


    void show_co(cond co) {
        show_dtype = 0;
        show_pred(co);
    }


    public void show_ast(angst ast) {
        gprint(new Integer(ast.no).toString() + ".");
        if (ast.no < 10) gprint("  ");
        for (int j = 0; j < ast.no; j++) {
            show_agll(ast.ln1[j], ast.ln2[j]);
            if (j != ast.no - 1)
                gprint(" = ");
        }
        ast.sd = this.getPrintedString();
    }

    void show_at(anglet at) {
        if (at == null) return;
        show_agll(at.l1, at.l2);
        int p = at.p;
        int p1 = get_lpt1(at.l1, p);
        int p2 = get_lpt1(at.l2, p);
        int v = getAtv(p1, p, p2, at.v);
        if (v % A_TIME == 0)
            gprint(" = " + v / A_TIME);
        else
            gprint(" = " + ((float) v) / A_TIME);
    }

    ////////////////////////////////////////////////////////////
    public void insertVector(cclass obj, Vector v) {
        if(obj == null)
            return;
        
        for(int i=0; i < v.size(); i ++)
        {
            cclass c = (cclass)v.get(i);
            if(c.id > obj.id)
            {
                v.add(i,obj);
                return;
            }
        }
        v.add(obj);
    }

    public Vector getAll_md() {
        Vector v = new Vector();
        midpt md = all_md.nx;
        while (md != null) {
            if (md.type != 0)
                insertVector(md, v);
            md = md.nx;
        }
        return v;
    }

    public Vector getAll_ln() {
        Vector v = new Vector();
        l_line ln = all_ln.nx;
        while (ln != null) {
            if (ln.type != 0 && ln.no >= 2)
                insertVector(ln, v);
            ln = ln.nx;
        }
        return v;
    }

    public Vector getAll_pn() {
        Vector v = new Vector();
        p_line pn = all_pn.nx;
        while (pn != null) {
            if (pn.type != 0)
                insertVector(pn, v);
            pn = pn.nx;
        }
        return v;
    }

    public Vector getAll_tn() {
        Vector v = new Vector();
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0)
                insertVector(tn, v);
            tn = tn.nx;
        }
        return v;
    }

    public Vector getAll_cir() {
        Vector v = new Vector();
        a_cir cr = all_cir.nx;
        while (cr != null) {
            if (cr.type != 0 && cr.no >= 2)
                insertVector(cr, v);
            cr = cr.nx;
        }
        return v;
    }

    public Vector getAll_as() {
        Vector v = new Vector();
        angst ast = all_ast.nx;
        while (ast != null) {
            if (ast.type != 0)
                insertVector(ast, v);
            ast = ast.nx;
        }
        return v;
    }

    public Vector getAll_at() {
        Vector v = new Vector();
        anglet at = all_at.nx;
        while (at != null) {
            if (at.type != 0)
                insertVector(at, v);
            at = at.nx;
        }
        return v;
    }

    public Vector getAll_atn() {
        Vector v = new Vector();
        angtn at = all_atn.nx;
        while (at != null) {
            if (at.type != 0)
                insertVector(at, v);
            at = at.nx;
        }
        return v;
    }


    public Vector getAll_cg() {
        Vector v = new Vector();
        c_segs cg = all_cgs.nx;
        while (cg != null) {
            if (cg.type != 0)
                insertVector(cg, v);
            cg = cg.nx;
        }
        return v;
    }

    public Vector getAll_rg() {
        Vector v = new Vector();
        cong_seg cg = all_rg.nx;
        while (cg != null) {
            if (cg.type != 0)
                insertVector(cg, v);
            cg = cg.nx;
        }
        return v;
    }

    public Vector getAll_st() {
        Vector v = new Vector();
        sim_tri st = all_st.nx;
        while (st != null) {
            if (st.type != 0 && !xcoll(st.p1[0], st.p1[1], st.p1[2]))
                insertVector(st, v);
            st = st.nx;
        }
        return v;
    }

    public Vector getAll_ct() {
        Vector v = new Vector();
        sim_tri ct = all_ct.nx;
        while (ct != null) {
            if (ct.type != 0 && !xcoll(ct.p1[0], ct.p1[1], ct.p1[2]))
                insertVector(ct, v);
            ct = ct.nx;
        }
        return v;
    }

    public Vector getAll_ra() {
        Vector v = new Vector();
        ratio_seg ra = all_ra.nx;
        while (ra != null) {
            if (ra.type != 0)
                insertVector(ra, v);
            ra = ra.nx;
        }
        return v;
    }

    public Vector getAll_sts() {
        Vector v = new Vector();
        s_tris sts = all_sts.nx;
        while (sts != null) {
            if (sts.type != 0)
                insertVector(sts, v);
            sts = sts.nx;
        }
        return v;
    }

    public Vector getAll_cts() {
        Vector v = new Vector();
        s_tris sts = all_cts.nx;
        while (sts != null) {
            if (sts.type != 0)
                insertVector(sts, v);
            sts = sts.nx;
        }
        return v;
    }

    public Vector search_a_fact(int t, String s1, String s2, String s3) {
        int t1 = this.fd_pt(s1);
        int t2 = this.fd_pt(s2);
        int t3 = this.fd_pt(s3);
        Vector v = new Vector();
        Object o = null;
        switch (t) {
            case 0:
                o = fd_lnno3(t1, t2);
                fo_cg2(t1, t2, v);
                break;
            case 1:
                o = fo_md(t1, t2, t3);
                break;
            case 2:
                fd_cr_p3(t1, t2, t3);
                break;
            case 3:
                fd_pnl(fd_ln(t1, t2));
                break;
            case 4:
                fd_tn(fd_ln(t1, t2));
                break;
            case 5: {
                angst st = fd_ast(fd_ln(t1, t2), fd_ln(t2, t3));
                if (st != null)
                    v.add(st);

                anglet at = fd_at(t1, t2, t3);
                if (at != null)
                    v.add(at);
                fo_atn2(t1, t2, t3, v);
            }
            break;
            case 6:
                fo_tri2(t1, t2, t3, v);
                break;
        }
        if (o != null)
            v.add(o);
        return v;
    }

    private l_line fd_lnno3(int a, int b) {
        l_line ln = fd_ln(a, b);
        if (ln != null && ln.no >= 2)
            return ln;
        return null;
    }

    private void fo_atn2(int a, int b, int c, Vector v) {
        angtn atn = all_atn.nx;

        while (atn != null) {
            if (atn.type != 0) {
                if (on_ln(a, b, atn.ln1) && on_ln(b, c, atn.ln2) || on_ln(a, b, atn.ln2) && on_ln(b, c, atn.ln1))
                    v.add(atn);
                else if (on_ln(a, b, atn.ln3) && on_ln(b, c, atn.ln4) || on_ln(a, b, atn.ln4) && on_ln(b, c, atn.ln3))
                    v.add(atn);
            }
            atn = atn.nx;
        }
    }

    s_tris fo_tri2(int a, int b, int c, Vector v) {
        s_tris st = all_sts.nx;
        while (st != null) {
            if (on_sts1(a, b, c, st) >= 0) {
                v.add(st);
                break;
            }
            st = st.nx;
        }
        st = all_cts.nx;
        while (st != null) {
            if (on_sts1(a, b, c, st) >= 0) {
                v.add(st);
                break;
            }
            st = st.nx;
        }
        return null;
    }

    cclass fo_cg2(int a, int b, Vector v) {
        c_segs cgs = all_cgs.nx;

        while (cgs != null) {
            if (on_cgs(a, b, cgs) && !v.contains(cgs))
                v.add(cgs);
            cgs = cgs.nx;
        }

        cong_seg cg = all_rg.nx;
        while (cg != null) {
            if (cg.p1 == a && cg.p2 == b || cg.p2 == a && cg.p1 == b || cg.p3 == a && cg.p4 == b || cg.p3 == b && cg.p4 == a) {
                if (!v.contains(cg))
                    v.add(cg);
            }
            cg = cg.nx;
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    cond add_as_pred(int m, int n, l_line l1, l_line l2, l_line l3, l_line l4) {
        int p1, p2, p3, p4, p5, p6, p7, p8;
        int a = inter_ll(l1, l2);
        int b = inter_ll(l3, l4);

        if (a != 0) {
            p2 = a;
            p3 = a;
            p1 = get_lpt1(l1, a);
            p4 = get_lpt1(l2, a);
        } else {
            p1 = l1.pt[0];
            p2 = l1.pt[1];
            p3 = l2.pt[0];
            p4 = l2.pt[1];
        }
        if (b != 0) {
            p6 = b;
            p7 = b;
            p5 = get_lpt1(l3, b);
            p8 = get_lpt1(l4, b);

        } else {
            p5 = l3.pt[0];
            p6 = l3.pt[1];
            p7 = l4.pt[0];
            p8 = l4.pt[1];
        }
        return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    cond add_as_pred1(int m, int n, int p1, int p2, int p3, int p4, l_line l3, l_line l4) {
        int p5, p6, p7, p8;
        int b = inter_ll(l3, l4);

        if (b != 0) {
            p6 = b;
            p7 = b;
            p5 = get_lpt1(l3, b);
            p8 = get_lpt1(l4, b);

            if (!check_eqangle_t(p1, p2, p3, p4, p5, p6, p7, p8)) {
                int t = get_anti_pt(l4, p7, p8);
                if (t != 0)
                    p8 = t;
                else {
                    t = get_anti_pt(l3, p6, p5);
                    if (t != 0)
                        p5 = t;
                }
            }

        } else {
            p5 = l3.pt[0];
            p6 = l3.pt[1];
            p7 = l4.pt[0];
            p8 = l4.pt[1];
        }
        return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    cond add_as_pred1(int m, int n, int p1, int p2, l_line l1, int p5, int p6, l_line l2) {
        int p3, p4, p7, p8;
        if (on_ln(p1, l1)) {
            p3 = p1;
            p4 = get_lpt1(l1, p1);
        } else if (on_ln(p2, l1)) {
            p3 = p2;
            p4 = get_lpt1(l1, p2);
        } else {
            p3 = l1.pt[0];
            p4 = l1.pt[1];
        }

        if (on_ln(p5, l2)) {
            p7 = p5;
            p8 = get_lpt1(l2, p5);
        } else if (on_ln(p6, l2)) {
            p7 = p6;
            p8 = get_lpt1(l2, p6);
        } else {
            p7 = l2.pt[0];
            p8 = l2.pt[1];
        }
        if (!check_eqangle_t(p1, p2, p3, p4, p5, p6, p7, p8)) {
            int t = get_anti_pt(l2, p7, p8);
            if (t != 0)
                p8 = t;
            else {
                t = get_anti_pt(l1, p3, p4);
                if (t != 0)
                    p4 = t;
            }
        }

        return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    cond add_as_pred_12(int m, int n, int m1, int m2, int m3, int m4, l_line l1, l_line l2, l_line l3, l_line l4) {
// m1,m2 on l1,  m3,m4 on l2;  find the most likely predicate
        int p1, p2, p3, p4, p5, p6, p7, p8;
        p2 = p3 = inter_lls(l1, l2);
        if (p2 != 0) {
            if (m1 == p2)
                p1 = m2;
            else if (m2 == p2)
                p1 = m1;
            else
                p1 = m1 < m2 ? m1 : m2;

            if (m3 == p2)
                p4 = m4;
            else if (m4 == p2)
                p4 = m3;
            else
                p4 = m3 < m4 ? m3 : m4;

            p6 = p7 = inter_lls(l3, l4);
            if (p6 != 0) {
                p5 = get_lpt1(l3, p6);
                p8 = get_lpt1(l4, p6);

                if (!check_eqangle_t(p1, p2, p3, p4, p5, p6, p7, p8)) {
                    int t = get_anti_pt(l4, p7, p8);
                    if (t != 0)
                        p8 = t;
                    else {
                        t = get_anti_pt(l3, p6, p5);
                        if (t != 0)
                            p5 = t;
                    }
                }

                return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
            }
        }
        return add_pred(m, n, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
    }

    cond add_as_pred_13(int m, int n, int m1, int m2, int m3, int m4, l_line l1, l_line l2, l_line l3, l_line l4) {
// m1,m2 on l1,  m3,m4 on l3;  find the most likely predicate
        int p1, p2, p3, p4, p5, p6, p7, p8;
        p2 = p3 = inter_lls(l1, l2);
        if (p2 != 0)

        {
            if (m1 == p2)
                p1 = m2;
            else if (m2 == p2)
                p1 = m1;
            else
                p1 = m1 < m2 ? m1 : m2;

            p4 = get_lpt1(l2, p2);

            p6 = p7 = inter_lls(l3, l4);
            if (p6 != 0) {
                if (m3 == p6)
                    p5 = m4;
                else if (m4 == p6)
                    p5 = m3;
                else
                    p5 = get_lpt1(l3, p6);

                p8 = get_lpt1(l4, p6);

                if (!check_eqangle_t(p1, p2, p3, p4, p5, p6, p7, p8)) {
                    int t = get_anti_pt(l4, p7, p8);
                    if (t != 0)
                        p8 = t;
                    else {
                        t = get_anti_pt(l2, p3, p4);
                        if (t != 0)
                            p4 = t;
                    }
                }

                return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);

            }
        }
        return add_pred(m, n, l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1], l3.pt[0], l3.pt[1], l4.pt[0], l4.pt[1]);
    }

    cond add_as_pred1(int m, int n, l_line l1, int p1, int p2, int p5, int p6, l_line l2) {
        int p3, p4, p7, p8;
        if (on_ln(p1, l1)) {
            p3 = p1;
            p4 = get_lpt1(l1, p1);
        } else if (on_ln(p2, l1)) {
            p3 = p2;
            p4 = get_lpt1(l1, p2);
        } else {
            p3 = l1.pt[0];
            p4 = l1.pt[1];
        }

        if (on_ln(p5, l2)) {
            p7 = p5;
            p8 = get_lpt1(l2, p5);
        } else if (on_ln(p6, l2)) {
            p7 = p6;
            p8 = get_lpt1(l2, p6);
        } else {
            p7 = l2.pt[0];
            p8 = l2.pt[1];
        }
        if (!check_eqangle_t(p3, p4, p1, p2, p5, p6, p7, p8)) {
            int t = get_anti_pt(l2, p7, p8);
            if (t != 0)
                p8 = t;
            else {
                t = get_anti_pt(l1, p3, p4);
                if (t != 0)
                    p4 = t;
            }
        }

        return add_pred(m, n, p3, p4, p1, p2, p5, p6, p7, p8);
    }

    cond add_as_pred1(int m, int n, int p1, int p2, l_line l1, l_line l2, int p5, int p6) {
        int p3, p4, p7, p8;
        if (on_ln(p1, l1)) {
            p3 = p1;
            p4 = get_lpt1(l1, p1);
        } else if (on_ln(p2, l1)) {
            p3 = p2;
            p4 = get_lpt1(l1, p2);
        } else {
            p3 = l1.pt[0];
            p4 = l1.pt[1];
        }

        if (on_ln(p5, l2)) {
            p7 = p5;
            p8 = get_lpt1(l2, p5);
        } else if (on_ln(p6, l2)) {
            p7 = p6;
            p8 = get_lpt1(l2, p6);
        } else {
            p7 = l2.pt[0];
            p8 = l2.pt[1];
        }
        if (!check_eqangle_t(p1, p2, p3, p4, p7, p8, p5, p6)) {
            int t = get_anti_pt(l2, p7, p8);
            if (t != 0)
                p8 = t;
            else {
                t = get_anti_pt(l1, p3, p4);
                if (t != 0)
                    p4 = t;
            }
        }
        return add_pred(m, n, p1, p2, p3, p4, p7, p8, p5, p6);
    }

    cond add_as_atn(int m, int n, int p1, int p2, int p3, int p4, l_line l3, l_line l4) {
        int p5, p6, p7, p8;
        int b = inter_ll(l3, l4);
        p5 = p6 = p7 = p8 = 0;

        if (b != 0) {
            p6 = b;
            p7 = b;
            p5 = get_lpt1(l3, b);
            p8 = get_lpt1(l4, b);
            if (!check_angle_ls_90(p5, b, p8)) {
                int t = get_anti_pt(l3, b, p5);
                if (t != 0)
                    p5 = t;
                else {
                    t = get_anti_pt(l4, b, p8);
                    if (t != 0)
                        p8 = t;
                }
            }
        } else {
            p5 = l3.pt[0];
            p6 = l3.pt[1];
            p7 = l4.pt[0];
            p8 = l4.pt[1];
        }
        return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    cond add_as_pred2(int m, int n, int p1, int p2, l_line l2, l_line l3, int p7, int p8) {
        int p3, p4, p5, p6;
        if (on_ln(p1, l2)) {
            p3 = p1;
            if (p3 != l2.pt[0])
                p4 = l2.pt[0];
            else
                p4 = l2.pt[1];
        } else if (on_ln(p2, l2)) {
            p3 = p2;
            if (p3 != l2.pt[0])
                p4 = l2.pt[0];
            else
                p4 = l2.pt[1];
        } else {
            p3 = l2.pt[0];
            p4 = l2.pt[1];
        }

        if (on_ln(p7, l3)) {
            p5 = p7;
            if (p5 != l3.pt[0])
                p6 = l3.pt[0];
            else
                p6 = l3.pt[1];
        } else if (on_ln(p8, l3)) {
            p5 = p8;
            if (p5 != l3.pt[0])
                p6 = l3.pt[0];
            else
                p6 = l3.pt[1];
        } else {
            p5 = l2.pt[0];
            p6 = l2.pt[1];
        }
        return add_pred(m, n, p1, p2, p3, p4, p5, p6, p7, p8);
    }

/////////////////////////////////////////////////////////////////////////////
//*****************************************************************************
// adding auxiliary points

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean check_tValid(cond c) {
        if (isPFull()) return true;
        if (c.pred != CO_ACONG) return true;
        if (c.p[0] == 0) return true;
        if ((c.p[0] == c.p[2] || c.p[0] == c.p[3] || c.p[1] == c.p[2] || c.p[1] == c.p[3]) &&
                (c.p[4] == c.p[6] || c.p[4] == c.p[7] || c.p[5] == c.p[6] || c.p[5] == c.p[7]))
            return true;
        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
///// for backward chaining.


    public void parse_llist() {
        l_list ns = all_ns.nx;
        if (ns == null) return;
        search_ns(ns);

        Vector v = getPVector();
        last_ns = ns;
        ns.nx = null;
        show_llists(v);

        Vector vl = new Vector();

        while (v.size() != 0) {

            for (int i = 0; i < v.size(); i++) {
                l_list ls = (l_list) v.get(i);
                mnde m = ls.mf[0];
                if (m == null) {
                } else if (m.tr != null) {
                    search_ag_split(ls);
                } else if (m.t == 90) {
                    search_t_list(ls);
                } else if (m.t == 180) {
                    search_p_list(ls);
                }
            }
            l_list ls = ns.nx;
            v.clear();
            while (ls != null) {
                if (ls.nd <= 1)
                    vl.add(ls);
                else
                    v.add(ls);
                ls = ls.nx;
            }
            if (vl.size() != 0)
                break;

            last_ns = ns;
            ns.nx = null;
        }
    }


    l_list get_next_ls_prove_head(l_list ls) {
        l_list ns = all_ns.nx;
        if (ns == null) return null;
        ns = ns.nx;

        l_list rs = null;
        if (ls == null)
            rs = ns;
        else {
            while (ns != ls)
                ns = ns.nx;
            rs = ns;
        }
        if (rs == null) return null;
        parse_bk_list(rs);
        show_lprv(rs);
        return rs;
    }

    void show_lprv(l_list ls) {

        setPrintToString();
        while (ls != null) {
            show_llist(ls);
            ls.text = getPrintedString();
            for (int i = 0; i < ls.rl.length; i++) {
                rule r = ls.rl[i];
                if (r == null) break;
                show_rule(r);
                r.text = getPrintedString();
            }
            ls = ls.fr;
        }
    }

    public void search_ag_split(l_list ls) {
        if (ls.nd == 1) return;

        mnde m = ls.mf[0];
        angtr t = m.tr;
        if (t == null) return;

        for (int j = 0; j < ls.nd; j++) {
            mnde m1 = ls.md[j];
            angtr t2 = m1.tr;
            if (t == null || t2 == null) {
            } else if (t.v == t2.v) {
                if (t.l1 == t2.l1) {
                    rule r = add_rule_spag(t.v, t.l1, t2.l2, t.l2);
                    list_sub(ls, r);
                }
                if (t.l2 == t2.l2) {
                    rule r = add_rule_spag(t.v, t.l1, t2.l1, t.l2);
                    list_sub(ls, r);
                }
            } else {
                if (t.l1 == t2.l1) {
                    rule r = add_rule_exag(t.v, t.l1, t2.l2, t.l2);
                    list_sub(ls, r);
                }
                if (t.l2 == t2.l2) {
                    rule r = add_rule_exag(t.v, t.l1, t2.l1, t.l2);
                    list_sub(ls, r);
                }
            }
        }
    }

    public void search_t_list(l_list ls) {

        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type != 0) {
                int t = inter_lls(tn.l1, tn.l2);
                if (t != 0) {
                    search_list_tn(ls, tn.l1, tn.l2, t);
                    search_list_tn(ls, tn.l2, tn.l1, t);
                }
            }
            tn = tn.nx;
        }
    }

    public void search_p_list(l_list ls) {
        for (int i = 0; i < ls.nd; i++) {
            mnde m = ls.md[i];
            angtr t = m.tr;
            int p1 = get_lpt1(t.l1, t.v);
            int p2 = get_lpt1(t.l2, t.v);
            if (p1 != 0 && get_anti_pt(t.l1, t.v, p1) != 0 || p2 != 0 && get_anti_pt(t.l2, t.v, p2) != 0) {
                rule r = add_rule_p_ag(t);
                list_sub(ls, r);
            }
        }
    }

    public void search_list_tn(l_list ls, l_line l1, l_line l2, int v) {
        if (v != 0) {
            for (int i = 0; i < ls.nd; i++) {
                angtr t = ls.md[i].tr;
                if (t.v == v) {
                    if (t.l1 == l1) {
//                        rule r = add_rule_spag(v, l1, t.l2, l2);
//                        list_sub(ls, r);
                        rule r = add_rule_tag(l1, l2);
                        list_sub(ls, r);

                    } else if (t.l2 == l2) {
//                        rule r = add_rule_spag(v, l1, t.l1, l2);
//                        list_sub(ls, r);
                        rule r = add_rule_tag(l2, l1);
                        list_sub(ls, r);
                    }
                }
            }
        }
    }

    public void search_p_list(Vector v) {

        for (int i = 0; i < v.size(); i++) {
            l_list ls = (l_list) v.get(i);

        }
    }

    public void search_list_ln(l_list ls) {
        for (int i = 0; i < ls.nd; i++) {
            angtr t = ls.md[i].tr;
            if (t.v == ls.pt) {

            }

        }
    }

    public rule add_rule_tag(l_line l1, l_line l2) {
        rule r = new rule(rule.T_ANGLE);
        mnde m = new mnde();
        m.tr = add_tr(inter_lls(l1, l2), l1, l2);
        r.mr1[0] = m;

        mnde m2 = new mnde();
        m2.tr = null;
        m2.t = 90;
        r.mr = m2;
        r.no = 1;
        return r;
    }

    public rule add_rule_spag(int v, l_line l1, l_line l2, l_line l3) {
        rule r = new rule(rule.SPLIT_ANGLE);
        mnde m = new mnde();
        m.tr = add_tr(v, l1, l2);
        r.mr1[0] = m;

        mnde m1 = new mnde();
        m1.tr = add_tr(v, l2, l3);
        r.mr1[1] = m1;

        mnde m2 = new mnde();
        m2.tr = add_tr(v, l1, l3);
        r.mr = m2;

        r.no = 2;
        return r;
    }

    public rule add_rule_p_ag(angtr t) {

        int v = t.v;
        l_line l1 = t.l1;
        l_line l2 = t.l2;

        rule r = new rule(rule.P_ANGLE);
        mnde m = new mnde();
        m.tr = add_tr(v, l1, l2);
        m.tr.t1 = t.t1;
        m.tr.t2 = t.t2;

        r.mr1[0] = m;

        mnde m1 = new mnde();
        m1.tr = add_tr(v, l2, l1);

        r.mr1[1] = m1;

        mnde m2 = new mnde();
        m2.tr = null;
        m2.t = 180;
        r.mr = m2;
        r.no = 2;

        return r;

    }


    public rule add_rule_exag(int v, l_line l1, l_line l2, l_line l3) {
        rule r = new rule(rule.EX_ANGLE);
        mnde m = new mnde();
        int v1 = inter_lls(l1, l2);
        if (v1 == 0) return null;

        m.tr = add_tr(v1, l1, l2);
        r.mr1[0] = m;

        int v2 = inter_lls(l2, l3);
        if (v2 == 0) return null;
        mnde m1 = new mnde();
        m1.tr = add_tr(v2, l2, l3);
        r.mr1[1] = m1;


        mnde m2 = new mnde();
        m2.tr = add_tr(v, l1, l3);
        r.mr = m2;
        r.no = 2;
        return r;
    }

    private static rule test_r = new rule(0);

    public void list_sub(l_list ls, rule r) {
        if (r == null) return;

        l_list ls1 = cp_nodes(ls);
        test_r.cp_rule(r);

        for (int i = 0; i < ls1.nd; i++) {
            mnde m = ls1.md[i];
            for (int j = 0; j < test_r.no; j++) {
                mnde m1 = r.mr1[j];

                if (m == null || m1 == null) {
                    int k = 0;
                } else if (m.tr == null && m1.tr == null) {
                    int t = m.t - m1.t;
                    if (t == 0)
                        ls1.md[i] = null;
                    else
                        ls1.md[i].t = t;
                    test_r.mr1[j] = null;
                } else if (m.tr != null && m1.tr != null && m.tr.l1 == m1.tr.l1 && m.tr.l2 == m1.tr.l2) {
                    ls1.md[i] = null;
                    test_r.mr1[j] = null;
                }
            }
        }

        int k = 0;
        for (int i = 0; i < test_r.no; i++) {
            if (test_r.mr1[i] != null) {
                ls1.mf[k++] = test_r.mr1[i];
            }
        }
        ls1.nf = k;

        int id = 0;
        for (int i = 0; i < ls1.nd; i++) {
            if (ls1.md[i] != null)
                ls1.md[id++] = ls1.md[i];
        }

        ls1.nd = id;
        ls1.fr = ls;
        ls1.add_rule(r);
        add_nodes(ls1);

    }

    public boolean equal_md(mnde m1, mnde m2) {
        if (m1.tr == null && m2.tr == null) {
            if (m1.t == m2.t) return true;
            return false;
        }
        return false;
    }

    public Vector getPVector() {
        Vector v = new Vector();
        l_list ns = all_ns.nx;
        while (ns != null) {
            int n = ns.get_npt();
            int i = 0;
            for (; i < v.size(); i++) {
                l_list ls = (l_list) v.get(i);
                if (ls.npt < n)
                    break;
            }
            v.add(i, ns);
            ns = ns.nx;
        }
        return v;
    }

    public void show_llist(l_list ls) {
        for (int i = 0; i < ls.nd; i++) {
            mnde m = ls.md[i];
            if (i != 0)
                gprint("+");
            show_mnde(m);
        }
        if (ls.nd == 0)
            gprint("0");

        gprint(" = ");

        for (int i = 0; i < ls.nf; i++) {
            mnde m = ls.mf[i];
            if (i != 0)
                gprint("+");
            show_mnde(m);
        }
        if (ls.nf == 0)
            gprint("0");

//        gprint("\t\t\tbecause  ");
//        show_rule(ls.rl[0]);
//        gprint("\n");

    }

//    public void show_rule(rule r) {
//        if (r.type == rule.)
//    }

    public void show_llists(Vector v) {

        for (int i = 0; i < v.size(); i++) {
            l_list ls = (l_list) v.get(i);
            show_llist(ls);
        }
    }

    public void show_mnde(mnde m) {

        if (m.tr == null)
            gprint(" " + m.t);
        else
            show_tr(m.tr);
    }


    void show_bk_list(l_list ls) {

        setPrintToString();
        if (ls == null) return;
        while (ls != null) {
            show_llist(ls);
            ls.text = this.getPrintedString();
            show_rule(ls.rl[0]);

            ls = ls.fr;
        }
    }

    l_list fd_nx_list(l_list ls) {
        if (ls == null) return all_ns.nx;
        l_list ls1 = all_ns.nx;
        while (ls1 != ls)
            ls1 = ls1.nx;
        return ls1.nx;
    }

    void parse_bk_list(l_list ls) {
        Vector v = new Vector();
        while (ls != null) {
            v.add(0, ls);
            ls = ls.fr;
        }


        l_list ls1 = null;
        for (int i = 0; i < v.size(); i++) {
            ls = (l_list) v.get(i);

            rule r = ls.rl[0];

            if (r != null) {
                switch (r.type) {
                    case rule.SPLIT_ANGLE:
                        break;
                    case rule.EX_ANGLE: {
                        angtr t = r.mr.tr;
                        angtr t1 = r.mr1[0].tr;
                        angtr t2 = r.mr1[1].tr;
                        angtr t3 = find_tr_in_ls(t1, ls1);
                        angtr t4 = find_tr_in_ls(t2, ls1);
                        if (t3 != null) {
                            t1.t1 = t3.t1;
                            t1.t2 = t3.t2;
                        } else {
                            t1.t1 = t.v;
                            t1.t2 = t2.v;
                        }
                        if (t4 != null) {
                            t2.t1 = t4.t1;
                            t2.t2 = t4.t2;
                        } else {
                            t2.t1 = t1.v;
                            t2.t2 = t.v;
                        }
                        angtr tf = ls1.mf[0].tr;
                        t.t1 = tf.t1;
                        t.t2 = tf.t2;
                    }
                    break;
                    case rule.P_ANGLE: {
                        angtr t1 = r.mr1[0].tr;
                        angtr t2 = r.mr1[1].tr;
                        angtr t3 = find_tr_in_ls(t1, ls1);
                        if (t3 != null) {
                            t1.t1 = t3.t1;
                            t1.t2 = t3.t2;
                        }
                        t2.t1 = t1.t2;
                        t2.t2 = get_anti_pt(t2.l2, t2.v, t1.t1);
                    }
                    break;
                    case rule.T_ANGLE: {

                    }
                    break;
                }
            }

            ls1 = ls;
        }
    }


    public angtr find_tr_in_ls(angtr t, l_list ls) {
        for (int i = 0; i < ls.nd; i++) {
            angtr t1 = ls.md[i].tr;
            if (t1.l1 == t.l1 && t1.l2 == t.l2) return t1;
        }
        return null;
    }

    void show_rule(rule r) {
        if (r == null) return;

        if (r.type == rule.SPLIT_ANGLE) {
            angtr t1 = r.mr1[0].tr;
            angtr t2 = r.mr1[1].tr;

            show_tr(t1);
            gprint("+");
            show_tr(t2);
            gprint("=");
            show_tr(r.mr.tr);

        } else if (r.type == rule.EX_ANGLE) {
            angtr t1 = r.mr1[0].tr;
            angtr t2 = r.mr1[1].tr;

            show_tr(t1);
            gprint("+");
            show_tr(t2);
            gprint("=");
            show_tr(r.mr.tr);
        } else if (r.type == rule.P_ANGLE) {
            angtr t1 = r.mr1[0].tr;
            angtr t2 = r.mr1[1].tr;

            show_tr(t1);
            gprint("+");
            show_tr(t2);
            gprint("=");
            show_mnde(r.mr);
        } else if (r.type == rule.T_ANGLE) {
            show_mnde(r.mr1[0]);
            gprint("=");
            show_mnde(r.mr);
        } else if (r.type == rule.EQ_ANGLE) {
            show_mnde(r.mr1[0]);
            gprint("=");
            show_mnde(r.mr);
        }
    }


}
