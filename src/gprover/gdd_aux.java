package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Jan 10, 2007
 * Time: 2:27:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class gdd_aux extends gdd {

/* main entry */
    static long time;

    boolean add_aux() {
        gno = cons_no;
        ax_backward();
        aux_rules();

        ax_orth();
        ax_md();
        ax_pn();
        ax_tn();
        ax_as();
        ax_cr();
        ax_tn_1();
        ax_cg();
        return true;
    }


    public void add_aux(auxpt ax) {
        if (aux_exists(ax))
            return;
        vauxpts.add(ax);
    }

    private boolean aux_exists(auxpt ax) {
        int n = ax.getPtsNo();
        if (n > 1) return false;
        Pro_point pt = ax.getPtsbyNo(0);
        if (fd_pt(pt.getdx(), pt.getdy()) != null) return true;

        for (int i = 0; i < vauxpts.size(); i++) {
            auxpt ax1 = (auxpt) vauxpts.get(i);
            if (isaux_contpt(ax1, pt))
                return true;
        }
        return false;
    }

    private boolean isaux_contpt(auxpt ax, Pro_point pt) {
        int n = ax.getPtsNo();
        for (int i = 0; i < n; i++) {
            Pro_point p = (Pro_point) ax.getPtsbyNo(i);
            if (isSamePt(pt, p))
                return true;
        }
        return false;
    }

    private boolean isSamePt(Pro_point p1, Pro_point p2) {
        if (p1.type != p2.type) return false;
        return Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2) < ZERO * ZERO;

    }


    public boolean time_over() {
        long t = System.currentTimeMillis() - time;
        if (t > 200000) return true;
        return false;
    }


    public void time_start() {
        time = System.currentTimeMillis();
    }

    Pro_point aux_pt(int aux, int type) {
        Pro_point p = new Pro_point(type);
        p.aux = aux;
        return p;
    }


    //******************************************************************

    private void add_as_aux(int t, Pro_point pt) {
        if (pt == null)
            return;
        auxpt ax = new auxpt(t);
        auxpt_string(pt);
        ax.addAPt(pt);

        add_aux(ax);
        return;
    }

    Pro_point aux_tline(int aux, int p1, int p2, int p3) {
        Pro_point pt = aux_pt(aux, C_O_T);
        pt.ps[0] = 0;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        cal_ax_tn(pt, p1, p2, p3);
        return (pt);
    }

    Pro_point auxpt_tline(int aux, int p1, int p2, int p3) {
        Pro_point pt = aux_tline(aux, p1, p2, p3);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_mid(int aux, int p1, int p2) {
        Pro_point pt = aux_pt(aux, C_MIDPOINT);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        cal_ax_md(pt, p1, p2);
        return (pt);
    }

    Pro_point auxpt_mid(int aux, int p1, int p2) {
        Pro_point pt = aux_mid(aux, p1, p2);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }


    Pro_point aux_ill(int aux, int p1, int p2, int p3, int p4) {
        Pro_point pt = aux_pt(aux, C_I_LL);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        pt.ps[4] = p4;
        if (cal_ax_ill(pt, p1, p2, p3, p4))
            return (pt);
        return null;
    }

    Pro_point auxpt_ill(int aux, int p1, int p2, int p3, int p4) {
        Pro_point pt = aux_ill(aux, p1, p2, p3, p4);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_ilp(int aux, int p1, int p2, int p3, int p4, int p5) {
        Pro_point pt = aux_pt(aux, C_I_LP);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        pt.ps[4] = p4;
        pt.ps[5] = p5;
        if (cal_ax_ilp(pt, p1, p2, p3, p4, p5))
            return (pt);
        return null;
    }

    Pro_point auxpt_ilp(int aux, int p1, int p2, int p3, int p4, int p5) {
        Pro_point pt = aux_ilp(aux, p1, p2, p3, p4, p5);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_ilt(int aux, int p1, int p2, int p3, int p4, int p5) {
        Pro_point pt = aux_pt(aux, C_I_LT);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        pt.ps[4] = p4;
        pt.ps[5] = p5;
        cal_ax_ilt(pt, p1, p2, p3, p4, p5);

        return (pt);
    }

    Pro_point auxpt_ilt(int aux, int p1, int p2, int p3, int p4, int p5) {
        Pro_point pt = aux_ilt(aux, p1, p2, p3, p4, p5);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_foot(int aux, int p1, int p2, int p3) {
        Pro_point pt = aux_pt(aux, C_FOOT);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        cal_ax_foot(pt, p1, p2, p3);
        return (pt);
    }

    Pro_point auxpt_foot(int aux, int p1, int p2, int p3) {
        Pro_point pt = aux_foot(aux, p1, p2, p3);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_ilc(int aux, int p1, int p2, a_cir cr) {
        Pro_point p = aux_pt(aux, C_I_LC);
        p.ps[0] = cons_no;
        p.ps[1] = p1;
        p.ps[2] = p2;
        p.ps[3] = cr.o;
        p.ps[4] = cr.pt[0];
        if (cal_ax_ilc(p, p1, p2, cr.o, cr.pt[0]))
            return p;
        else
            return null;
    }

    Pro_point auxpt_ilc(int aux, int p1, int p2, a_cir cr) {
        Pro_point pt = aux_ilc(aux, p1, p2, cr);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_ipc(int aux, int p1, int p2, int p3, a_cir cr) {
        Pro_point pt = aux_pt(aux, C_I_PC);
        pt.ps[0] = cons_no;
        pt.ps[1] = p1;
        pt.ps[2] = p2;
        pt.ps[3] = p3;
        pt.ps[4] = cr.o;
        pt.ps[5] = p1;
        if (cal_ax_ipc(pt, p1, p2, p3, cr.o, cr.pt[0]))
            return pt;
        return (null);
    }

    Pro_point auxpt_ipc(int aux, int p1, int p2, int p3, a_cir cr) {
        Pro_point pt = aux_ipc(aux, p1, p2, p3, cr);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }

    Pro_point aux_co(int aux, a_cir cr) {
        Pro_point pt = aux_pt(aux, C_CIRCUM); //62
        pt.ps[0] = 0;
        pt.ps[1] = cr.pt[0];
        pt.ps[2] = cr.pt[1];
        pt.ps[3] = cr.pt[2];
        cal_ax_co(pt, cr.pt[0], cr.pt[1], cr.pt[2]);
        return (pt);
    }

    Pro_point auxpt_co(int aux, a_cir cr) {
        Pro_point pt = aux_co(aux, cr);
        if (pt != null)
            add_as_aux(aux, pt);
        return pt;
    }


    void ax_predicate() {
        int[] p = conc.p;
        switch (conc.pred) {
            case 0:
                return;
            case CO_PERP:
                {
                    l_line l1 = fd_ln(p[0], p[1]);
                    l_line l2 = fd_ln(p[2], p[3]);
                    if (l1 != null && l2 != null && inter_lls(l1, l2) == 0) {
                        Pro_point pt = auxpt_ill(1, p[0], p[1], p[2], p[3]);
                    }
                }
                break;
            default:
                break;
        }
    }

    void aux_rules() {

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void ax_md() {
        midpt md = all_md.nx;
        while (md != null) {
            l_line ln = fd_ln(md.a, md.b);
            ax_mdpn(md);
            midpt md1 = md.nx;
            while (md1 != null) {
                if (md1 != md && on_ln(md1.a, ln) && on_ln(md1.b, ln)) {
                    ax_mml(md, md1, ln);
                } else if (md1 != md) {
                    if (md.m != md1.m && !(md.a == md1.a || md.a == md1.b || md.b == md1.a || md.b == md1.b))
                        ax_mm(md, md1);
                }
                md1 = md1.nx;
            }
            md = md.nx;
        }
    }

    void ax_mdpn(midpt md) {
        p_line pn = all_pn.nx;
        while (pn != null) {
            if (pn.type == 0 || pn.no <= 1) {
                pn = pn.nx;
                continue;
            }
            l_line ln1 = fd_ln_pn1(pn, md.m);
            if (ln1 == null) {
                pn = pn.nx;
                continue;
            }
            l_line ln2 = fd_ln_pn1(pn, md.a);
            if (ln2 == null || ln2 == ln1) {
                pn = pn.nx;
                continue;
            }
            l_line ln3 = fd_ln_pn1(pn, md.b);
            if (ln3 == null || ln3 == ln1 || ln3 == ln2) {
                pn = pn.nx;
                continue;
            }
            ax_p3m(pn, md.m, md.a, md.b, ln1, ln2, ln3);
            pn = pn.nx;
        }
    }

    void ax_p3m(p_line pn, int p1, int p2, int p3, l_line ln1, l_line ln2, l_line ln3) {
        int m1, m2, m3;
        l_line ln;
        {
            ln = all_ln.nx;
            while (ln != null) {
                if (ln.type != 0 && ln != ln1 && ln != ln2 && ln != ln3 && (m1 = inter_ll(ln, ln1)) != 0
                        && m1 != p1 && (m2 = inter_ll(ln, ln2)) != 0 && m2 != p2 &&
                        (m3 = inter_ll(ln, ln3)) != 0 && m3 != p3 && !xmid(m1, m2, m3)) {

                    if (inter_ll(ln1, fd_ln(p2, m3)) == 0 && inter_ll(ln1, fd_ln(p3, m2)) == 0) {
                        auxpt_ill(8, p1, m1, p2, m3);
                        auxpt_ill(8, p1, m1, p3, m2);
                    }
                }
                ln = ln.nx;
            }
        }
    }

    void ax_mml(midpt md, midpt md1, l_line l1) {
        int p1, p2;

        if (md.a == md1.a || md.a == md1.b) {
            p1 = md.a;
        } else if (md.b == md1.a || md.b == md1.b) {
            p1 = md.b;
        } else
            return;
        {
            l_line ln = all_ln.nx;
            while (ln != null) {
                if (ln.type != 0 && ln != l1 && on_ln(p1, ln)) {
                    p2 = get_lpt1(ln, p1);
                    if (fd_pt_md(p1, p2) == 0) {
//                        if (aux_mid(2, p1, p2) != 0) return;
                        auxpt_mid(2, p1, p2);//???????
                    }
                }
                ln = ln.nx;
            }
        }
    }

    void ax_mm(midpt md, midpt md1) {
        int m1, m2, p1, p2, p3, p4;
        p1 = md.a;
        p2 = md.b;
        m1 = md.m;
        m2 = md1.m;


        if (xpara(md.a, md1.b, md.b, md1.a)) {
            p3 = md1.b;
            p4 = md1.a;
        } else {
            p3 = md1.a;
            p4 = md1.b;
        }

        if (!xpara(p1, p3, m1, m2) && xpara(p1, p3, p2, p4)) {

            l_line l1 = fd_ln(md.a, p1);
            l_line l2 = fd_ln(md.b, p2);

            if (inter_ll(l2, fd_ln(p3, m1)) == 0)
                auxpt_ill(18, p2, p4, p3, m1);
            if (inter_ll(l2, fd_ln(p1, m2)) == 0)
                auxpt_ill(18, p2, p4, p1, m2);
            if (inter_ll(l1, fd_ln(p4, m1)) == 0)
                auxpt_ill(18, p1, p3, p4, m1);
            if (inter_ll(l1, fd_ln(p2, m2)) == 0)
                auxpt_ill(18, p1, p3, p2, m2);
        }else if(md.dep < 2 && md1.dep <2 )
        {
            if(md.a != md1.a && md.a != md1.b && md.b != md1.a && md.b != md1.b)
            {
                this.auxpt_mid(0,md.a,md1.a);
                this.auxpt_mid(0,md.a,md1.b);
                this.auxpt_mid(0,md.b,md1.a);
                this.auxpt_mid(0,md.b,md1.b);
            }
        }
    }


    void ax_orth() {

        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type == 0) {
                tn = tn.nx;
                continue;
            }
            ax_orth1(tn, tn.l1, tn.l2);
            tn = tn.nx;
        }
    }

    void ax_orth1(t_line tn1, l_line ln1, l_line ln2) {
        int a, b, c, h;

        if (inter_ll(ln1, ln2) != 0) return;
        t_line tn = all_tn.nx;

        while (tn != null) {
            if (tn.type == 0 || tn == tn1) {
                tn = tn.nx;
                continue;
            }

            l_line ln3 = tn.l1;
            l_line ln4 = tn.l2;
            if (inter_ll(ln3, ln4) != 0) {
                tn = tn.nx;
                continue;
            }

            if ((h = inter_ll(ln1, ln3)) != 0 &&
                    (b = inter_ll(ln1, ln4)) != 0 && b != h &&
                    (c = inter_ll(ln2, ln3)) != 0 && c != b && c != h &&
                    (a = inter_ll(ln2, ln4)) != 0 && a != b && a != c && a != h) {
                auxpt_ill(10, c, h, a, b);
                auxpt_ill(10, b, h, a, c);
            }
            ln4 = null;
            tn = tn.nx;
        }
    }


    void ax_tn() {
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type == 0) {
                tn = tn.nx;
                continue;
            }

            l_line ln1 = tn.l1;
            l_line ln2 = tn.l2;
            ax_tn_cr(tn);
            ax_tn_as(tn);
            ax_tn_md(tn);
            ax_tn_21(tn, ln1, ln2);
            ax_tn_21(tn, ln2, ln1);
            tn = tn.nx;
        }
    }


    void ax_tn_cr(t_line tn) {
        int p2, p3, m;
        l_line ln;

        l_line ln1 = tn.l1;
        l_line ln2 = tn.l2;

        a_cir cr = all_cir.nx;
        m = inter_ll(ln1, ln2);
        while (cr != null) {
            if (cr.type == 0 || cr.o == 0) {
                cr = cr.nx;
                continue;
            }
            if (m != 0) {
                if (!on_cir(m, cr)) {
                    cr = cr.nx;
                    continue;
                }
                p2 = inter_lc1(ln1, cr, m);
                if (p2 == 0) {
                    p2 = inter_lc1(ln2, cr, m);
                    ln = ln1;
                    ln1 = ln2;
                    ln2 = ln;
                }
                if (p2 == 0) {
                    cr = cr.nx;
                    continue;
                }
                p3 = inter_ll(ln2, fd_ln(p2, cr.o));
                if (p3 == 0) {
                    auxpt_ilc(30, m, get_lpt1(ln2, m), cr); //2007.1.10
                    cr = cr.nx;
                    continue;
                }
                if (!xmid(cr.o, p2, p3)) {
                    auxpt_mid(14, p2, m);
                }
            } else {
                if (on_ln(cr.o, ln2)) {
                    ln = ln1;
                    ln1 = ln2;
                    ln2 = ln;
                }
                if (on_ln(cr.o, ln1) && (p2 = inter_lc(ln2, cr)) != 0 &&
                        (p3 = inter_lc1(ln2, cr, p2)) != 0 && p2 != p3 && fd_pt_md(p2, p3) == 0) {
                    auxpt_ill(21, p2, p3, cr.o, get_lpt1(ln1, cr.o));   //y1
                }
            }
            cr = cr.nx;
        }
    }


    void ax_tn_as(t_line tn) {
        int p1, p2, p3;
        l_line ln, n1, n2, l1, l2, l3, l4;
        Pro_point pt;


        l_line ln1 = tn.l1;
        l_line ln2 = tn.l2;
        int m = inter_ll(ln1, ln2);
        angles as1 = all_as.nx;
        while (as1 != null) {
            if (as1.type == 0)// goto m1;
            {
                as1 = as1.nx;
                continue;
            }
            l1 = as1.l1;
            l2 = as1.l2;
            l3 = as1.l3;
            l4 = as1.l4;
            if (ln1 == l2 && ln1 == l3) {
                n1 = l1;
                n2 = l4;
            } else if (ln2 == l2 && ln2 == l3) {
                ln = ln1;
                ln1 = ln2;
                ln2 = ln;
                n1 = l1;
                n2 = l4;
            } else {
                as1 = as1.nx;
                continue;
            }
            p1 = inter_ll(n1, n2);
            if (p1 == 0 || p1 == m || !on_ln(p1, ln1)) {
                as1 = as1.nx;
                continue;
            }
            p2 = inter_ll(ln2, n1);
            p3 = inter_ll(ln2, n2);
            if (m != 0 && p2 != 0 && p3 != 0) return;

            if (m == 0 && p2 != 0 && p3 != 0) {
                pt = auxpt_ill(3, p2, p3, p1, get_lpt1(ln1, p1));
            } else if (m != 0 && p2 != 0 && p3 == 0) {
                pt = auxpt_ill(1, m, p2, p1, get_lpt1(n2, p1));
            } else if (m != 0 && p2 == 0 && p3 != 0) {
                pt = auxpt_ill(1, m, p3, p1, get_lpt1(n1, p1));
            } else {
                as1 = as1.nx;
                continue;
            }
            as1 = as1.nx;
        }
    }

    void ax_tn_md(t_line tn) {
        int p1, p2;

        l_line ln1 = tn.l1;
        l_line ln2 = tn.l2;

        int m = inter_ll(ln1, ln2);
        if (m == 0) return;
        midpt md = all_md.nx;

        while (md != null) {
            if (on_ln(md.m, ln1)) {
                l_line ln = ln1;
                ln1 = ln2;
                ln2 = ln;
            }
            if (!on_ln(md.m, ln2)) {
                md = md.nx;
                continue;
            }
            if (on_ln(md.a, ln1)) {
                p1 = md.a;
                p2 = md.b;
            } else if (on_ln(md.b, ln1)) {
                p1 = md.b;
                p2 = md.a;
            } else {
                md = md.nx;
                continue;
            }
            if (m == md.m || m == p1) {
                md = md.nx;
                continue;
            }
            if (inter_ll(ln2, fd_tline(p2, m, md.m)) == 0) {
                auxpt_foot(22, p2, m, md.m);
            }
            md = md.nx;
        }
    }


    void ax_tn_1() {
        t_line tn = all_tn.nx;
        while (tn != null) {
            if (tn.type == 0) {
                tn = tn.nx;
                continue;
            }
            ax_tn_ln(tn.l1, tn.l2);
            tn = tn.nx;
        }
    }

    void ax_tn_ln(l_line ln1, l_line ln2) {
        int p1, p2, p3;

        int m = inter_ll(ln1, ln2);
        if (m == 0) return;
        l_line ln = all_ln.nx;

        while (ln != null) {
            if (ln.type == 0 || ln == ln1 || ln == ln2) {
                ln = ln.nx;
                continue;
            }
            p1 = inter_ll(ln, ln1);
            if (p1 == 0 || ln_para(ln, ln2)) {
                p1 = inter_ll(ln, ln2);
                l_line l1 = ln1;
                ln1 = ln2;
                ln2 = l1;
            }
            if (p1 == 0 || p1 == m)// goto m1;
            {
                ln = ln.nx;
                continue;
            }
            if (ln_para(ln, ln2))// goto m1;
            {
                ln = ln.nx;
                continue;
            }
            l_line l2;
            for (int i = 0; i <= ln2.no; i++) {
                p2 = ln2.pt[i];
                if (p2 != m && !on_ln(p2, ln) && (l2 = fd_ln(p1, p2)) != null && !ln_perp(ln, l2) &&
                        inter_ll(ln, fd_tline(p2, ln.pt[0], ln.pt[1])) == 0) {
                    p3 = get_lpt1(ln, p1);
                    auxpt_foot(12, p2, p1, p3);
                }
            }
            ln = ln.nx;
        }
    }

    void ax_tn_21(t_line tn, l_line ln1, l_line ln2) {
        int p2, p3, p4, p5;
        int p1 = inter_ll(ln1, ln2);
        if (p1 == 0) return;

        p_line pn1 = fd_pnl(ln1);
        if (pn1 == null) return;

        for (int i = 0; i <= pn1.no; i++) {
            l_line l1 = pn1.ln[i];
            if (l1 == ln1)
                continue;
            p2 = inter_ll(l1, ln2);
            if (p2 == 0)
                continue;

            l_line l2 = all_ln.nx;

            while (l2 != null) {
                if (l2.type != 0 && l2 != ln1 && l2 != l1 && l2 != ln2 &&
                        (p3 = inter_ll(ln1, l2)) != 0 && p3 != p1 &&
                        (p4 = inter_ll(l1, l2)) != 0 && p4 != p2) {
                    if ((p5 = fd_pt_md(p3, p4)) != 0 && fd_pt_md(p1, p2) == 0 &&
                            inter_ll(ln2, fd_tline(p5, p1, p2)) == 0) {

                        auxpt_foot(23, p5, p1, p2);

                    }
                    if (!ln_perp(l2, ln1) && (inter_ll(l1, fd_tline(p3, p3, p1))) == 0 &&
                            (inter_ll(ln1, fd_tline(p4, p4, p2))) == 0 &&
                            (inter_ll(l2, ln2)) == 0 &&
                            (inter_ll(l1, fd_pline(p1, p3, p4))) == 0) {

                        auxpt_ilp(24, p2, p4, p1, p3, p4);

                    }
                }
                l2 = l2.nx;
            }
        }
    }

    void ax_as() {
        angles as1;
        l_line l1, l2, l3, l4;
        as1 = all_as.nx;
        while (as1 != null) {
            if (as1.type == 0) {
                as1 = as1.nx;
                continue;
            }
            l1 = as1.l1;
            l2 = as1.l2;
            l3 = as1.l3;
            l4 = as1.l4;
            if (l2 == l3)
                ax_as_1(as1, l1, l2, l4);
            else
                ax_as_2(as1, l1, l2, l3, l4);

            as1 = as1.nx;
        }
    }

    void ax_as_1(angles as, l_line l1, l_line l2, l_line l3) {
        int p1, p2, p3, p4;

        int o = inter_ll(l1, l3);
        if (o == 0) return;
        if (!on_ln(o, l2)) return;


        a_cir cr = all_cir.nx;
        while (cr != null) {
            if (cr.type == 0) {
                cr = cr.nx;
                continue;
            }
            if (!on_cir(o, cr) || ((p1 = inter_lc1(l1, cr, o)) == 0) || ((p3 = inter_lc1(l3, cr, o)) == 0)) //goto st1;
            {
                cr = cr.nx;
                continue;
            }
            p2 = inter_lc1(l2, cr, o);
            if (p2 == 0 && cr.o != 0) {//&& !ln_perp(l2, fd_ln(p1, p3)) && 0 != 0)  /* d123 */ {??????????
                auxpt_ilc(25, o, get_lpt1(l2, o), cr);
            }
            cr = cr.nx;
        }
        p4 = get_lpt1(l2, o);
        int i = p4;
        for (i = 0; i <= l1.no; i++)
            for (int j = 0; j <= l3.no; j++) {
                p1 = l1.pt[i];
                p3 = l3.pt[j];
                if (p1 != o && p3 != o && inter_ll(l2, fd_ln(p1, p3)) == 0) {
                    ax_at(as, o, p1, p3, l2);
                }
            }

        for (i = 0; i <= l2.no; i++) {
            int p = l2.pt[i];
            if (p != o) {
                l_line n1 = fd_lpp2(p, l1);
                l_line n2 = fd_lpp2(p, l3);
                if (n1 != null && n2 == null) {
                    int t3 = inter_ll(n1, l3);
                    if (t3 != 0)
                        auxpt_ilp(31, o, get_lpt1(l1, o), p, o, t3);
                } else if (n1 == null && n2 != null) {
                    if (n1 != null && n2 == null) {
                        int t4 = inter_ll(n2, l1);
                        if (t4 != 0)
                            auxpt_ilp(31, o, get_lpt1(l3, o), p, o, t4);
                    }
                }
            }
        }

    }

/* [op1,l2]=[l2,op3] */

    void ax_at(angles as, int o, int p1, int p3, l_line l2) {
        if (inter_ll(l2, fd_ln(p1, p3)) != 0) return;

        for (int k = 0; k <= l2.no; k++) {
            int p2 = l2.pt[k];
            if (p2 != o && !xmid(p2, p1, p3)) {
                auxpt_ill(26, p1, p3, o, p2);
            }
        }
    }

    void ax_as_2(angles as, l_line l1, l_line l2, l_line l3, l_line l4) {
        int p1, p2;
        if (((p1 = inter_ll(l1, l2)) == 0) || ((p2 = inter_ll(l3, l4)) == 0)) return;

        ax_as_21(as, p1, p2, l1, l2, l3, l4);
        ax_as_21(as, p2, p1, l3, l4, l1, l2);
    }

    void ax_as_21(angles as, int p1, int p2, l_line l1, l_line l2, l_line l3, l_line l4) {
        int p3, p4, m;

        if ((p3 = inter_ll(l1, l3)) != 0 && xcir2(p2, p1, p3) &&
                (p4 = inter_lc1(l2, fd_cr_op(p2, p1), p1)) != 0 &&
                p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
//            add_codb(CO_ACONG, p3, p1, p1, p4, p3, p2, p2, get_lpt1(l4, p2));
//            add_codb(CO_CYCLIC, p2, p1, p3, p4, 0, 0, 0, 0);
            m = inter_ll(l4, fd_ln(p3, p4));
            if (m == 0) {
                auxpt_ill(20, p3, p4, p2, get_lpt1(l4, p2));
            }
        }
        if ((p4 = inter_ll(l2, l4)) != 0 && xcir2(p2, p1, p4) &&
                (p3 = inter_lc1(l1, fd_cr_op(p2, p1), p1)) != 0 &&
                p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4) {
//            add_codb(CO_ACONG, p3, p1, p1, p4, get_lpt1(l3, p2), p2, p2, p4);
//            add_codb(CO_CYCLIC, p2, p1, p3, p4, 0, 0, 0, 0);
            m = inter_ll(l3, fd_ln(p3, p4));
            if (m == 0) {
//m =
                auxpt_ill(21, p3, p4, p2, get_lpt1(l3, p2));
            }
//            if (m != 0) add_mid(0, m, p3, p4);
//            pop_codb();
//            pop_codb();
        }
    }

    void ax_cr() {
        a_cir cr = all_cir.nx;
        while (cr != null) {
            if (cr.type != 0) {
                ax_cr1(cr);
                ax_cr2(cr);
                ax_center(cr);
                ax_cr_cr(cr);
                ax_tan(cr);
                ax_diameter(cr);
            }
            cr = cr.nx;
        }
    }

    void ax_diameter(a_cir cr) {
        for (int i = 0; i <= cr.no; i++) {
            l_line ln = fd_ln(cr.o, cr.pt[i]);
            if (ln != null && inter_lc1(ln, cr, cr.pt[i]) == 0) {
                auxpt_ilc(33, cr.o, cr.pt[i], cr);
            }
        }
    }

    void ax_tan(a_cir cr1) {
        int i;
        if (cr1.o == 0 || cr1.no <= 1) return;
        if (!in_conc(cr1.o)) return;

        for (i = 0; i <= cr1.no; i++)
            if (in_conc(cr1.pt[i]) && fd_tline(cr1.pt[i], cr1.pt[i], cr1.o) == null) {
                auxpt_tline(11, cr1.pt[i], cr1.pt[i], cr1.o);
            }
    }

    boolean in_conc(int p) {
        for (int i = 0; i <= 7; i++) if (conc.p[i] == p) return (true);
        return (false);
    }


    void ax_cr_cr(a_cir cr1) {
        a_cir cr2 = all_cir.nx;
        int p1, p2;
        while (cr2 != null) {
            if (cr2.type == 0) //goto m1;
            {
                cr2 = cr2.nx;
                continue;
            }
            p1 = inter_cc(cr1, cr2);
            if (p1 == 0)// goto m1;
            {
                cr2 = cr2.nx;
                continue;
            }
            p2 = inter_cc1(cr1, cr2, p1);
            if (p2 == 0)// goto m1;
            {
                cr2 = cr2.nx;
                continue;
            }
            if (cr1.no >= 3)
                ax_cr_cr1(cr1, cr2, p1, p2);
            if (cr2.no >= 3)
                ax_cr_cr1(cr2, cr1, p2, p1);
            cr2 = cr2.nx;
        }
    }

    void ax_cr_cr1(a_cir cr1, a_cir cr2, int p1, int p2) {
        int p3, p4, p5, p6;
        Pro_point pt1, pt2, pt3, pt4;

        if (cr2.o == 0) return;

        for (int i = 0; i <= cr1.no; i++) {
            p3 = cr1.pt[i];
            if (p3 != p1 && p3 != p2)
                for (int j = 0; j <= cr1.no; j++) {
                    p4 = cr1.pt[j];
                    if (p4 != p1 && p4 != p2 && p4 != p3) {
                        p5 = inter_lc1(fd_ln(p1, p3), cr2, p1);
                        p6 = inter_lc1(fd_ln(p2, p4), cr2, p2);
                        pt1 = pt2 = pt3 = pt4 = null;
                        if (p5 == 0 && p6 != 0 && !mperp(p1, cr2.o, p1, p3)) {
                            pt1 = auxpt_ilc(27, p1, p3, cr2);
                        } else if (p5 != 0 && p6 == 0 && !mperp(p2, cr2.o, p2, p4)) {
                            pt2 = auxpt_ilc(27, p2, p4, cr2);
                        }
                        p5 = inter_lc1(fd_ln(p1, p4), cr2, p1);
                        p6 = inter_lc1(fd_ln(p2, p3), cr2, p2);
                        if (p5 == 0 && p6 != 0 && !mperp(p1, p4, p1, cr2.o)) {
                            pt3 = auxpt_ilc(27, p1, p4, cr2);
                        } else if (p5 != 0 && p6 == 0 && !mperp(p2, p3, p2, cr2.o)) {
                            pt4 = auxpt_ilc(27, p2, p3, cr2);
                        }
                    }
                }
        }
    }

    void ax_cr1(a_cir cr) {
        int p1, p2, p3, p4;
        l_line l1;
        int o = cr.o;

        Pro_point pt = null;

        if (o == 0) return;
        for (p1 = 1; p1 <= cons_no; p1++) {
            if (p1 != o && !on_cir(p1, cr) && (l1 = fd_tline(p1, p1, o)) != null) {
                p3 = get_lpt1(l1, p1);
                for (int i = 0; i <= cr.no; i++) {
                    p2 = cr.pt[i];
                    if (!xcoll(p1, p2, p3) && !xcoll(p1, p2, o) && !xperp(o, p2, o, p1) &&
                            inter_lc1(fd_ln(p1, p2), cr, p2) != 0) {
                        l1 = fd_pline(p2, p1, p3);
                        if (l1 == null) {
                            pt = auxpt_ipc(28, p2, p1, p3, cr);
                        } else if ((p4 = inter_lc1(l1, cr, p2)) == 0) {
                            pt = auxpt_ilc(28, p2, get_lpt1(l1, p2), cr);
                        }
                        return;
                    }
                }
            }
        }
    }

    void ax_cr2(a_cir cr) {
        l_line l1;
        int o = cr.o;
        if (o == 0) return;

        for (int i = 0; i <= cr.no; i++) {
            int p1 = cr.pt[i];
            if (inter_lc1(fd_ln(o, p1), cr, p1) == 0) {
                if ((l1 = fd_tline(o, o, p1)) != null && cr.no > 1 && (!(inter_lc(l1, cr) != 0) || cr.no > 2)) {
                    auxpt_ilc(29, p1, o, cr);
                }
            }
        }
    }

    void ax_center(a_cir cr) {
        l_line l1, l2;
        p_line pn;
        int k, p1, p2, p3, p4;

        if (cr.o != 0) return;

        for (int i = 0; i <= cr.no; i++) {
            p1 = cr.pt[i];
            for (int j = i + 1; j <= cr.no; j++) {
                p2 = cr.pt[j];
                p3 = fd_pt_md(p1, p2);
                if (p3 == 0)//    goto m1;
                {
                    k = 0;
                    continue;
                }
                l1 = fd_tline(p3, p1, p2);
                if (l1 == null)// goto m1;
                {
                    k = 0;
                    continue;
                }
                pn = fd_pn(p1, p2);
                if (pn == null || pn.no == 0)// goto m1;
                {
                    k = 0;
                    continue;
                }
                k = 0;
                while (k <= pn.no && (l2 = pn.ln[k]) != null &&
                        !(!on_ln(p1, l2) &&
                        (p3 = inter_lc(l2, cr)) != 0 &&
                        (p4 = inter_lc1(l2, cr, p3)) != 0 &&
                        fd_pt_md(p3, p4) == 0))
                    k++;
                if (k > pn.no)// goto m1;
                {
                    k = 0;
                    continue;
                }
                auxpt_co(30, cr);
                k = 0;
            }
        }
    }


    void ax_pn() {
        int p1, p2, p3, p4;

        p_line pn = all_pn.nx;
        while (pn != null) {
            if (pn.type == 0 || pn.no <= 0) //goto m0;
            {
                pn = pn.nx;
                continue;
            }
            for (int i = 0; i <= pn.no; i++) {
                l_line ln1 = pn.ln[i];
                for (int j = i + 1; j <= pn.no; j++) {
                    l_line ln2 = pn.ln[j];
                    if (ln1 == ln2) //goto m1;
                    {
                        p1 = 1;
                        continue;
                    }
                    for (int k1 = 0; k1 <= ln2.no; k1++)
                        for (int k2 = k1 + 1; k2 <= ln2.no; k2++)
                            for (int k = 1; k <= cons_no; k++) {
                                if (k != k1 && k != k2 && k1 != k2 && !on_ln(k, ln1) && !on_ln(k, ln2) &&
                                        (p1 = inter_ll(ln1, fd_ln(k, ln2.pt[k1]))) != 0 && (p2 = inter_ll(ln1, fd_ln(k, ln2.pt[k2]))) != 0) {

                                    l_line ln = all_ln.nx;
                                    while (ln != null) {
                                        if (ln.type == 0 || ln == ln1 || ln == ln2)// goto m2;
                                        {
                                            ln = ln.nx;
                                            continue;
                                        }
                                        if (!on_ln(k, ln)) //goto m2;
                                        {
                                            ln = ln.nx;
                                            continue;
                                        }
                                        p3 = inter_ll(ln1, ln);
                                        p4 = inter_ll(ln2, ln);
                                        if (p3 != 0 && p4 == 0)
                                            auxpt_ill(26, k, p3, ln2.pt[k1], ln2.pt[k2]);

                                        if (p3 == 0 && p4 != 0)
                                            auxpt_ill(27, k, p4, p1, p2);

                                        ln = ln.nx;
                                    }
                                }
                            }
                    p1 = 1;
                }
            }
            pn = pn.nx;
        }
    }

    public void add_ax_t1() {
        cong_seg cg = all_cg.nx;
        while (cg != null) {
            if (cg.p1 != cg.p3 && cg.p1 != cg.p4 && cg.p2 != cg.p3 && cg.p2 != cg.p4) {
                if (!xpara(cg.p1, cg.p2, cg.p3, cg.p4) && !xcong(cg.p1, cg.p3, cg.p2, cg.p4)
                        && !xcong(cg.p1, cg.p4, cg.p2, cg.p3)) {
//   auxpt_ill(0,)

                }
            }
            cg = cg.nx;
        }
    }

    public void ax_cg() {
        cong_seg cg = all_cg.nx;
        while (cg != null) {
            cg = cg.nx;
        }
    }



    //////////////////////////////////////////////////////////////////////
    //backward;

    public void ax_backward() {
        int[] p = conc.p;

        switch (conc.pred) {
            case 0:
                return;
            case CO_CONG:
                {
                    if (xcoll4(p[0], p[1], p[2], p[3])) {
                        //abx_mid()
                    }
                }
                break;
            case CO_MIDP:
                {
                    ax_bk_mid(p[0], p[1], p[2]);
                }
                break;

        }
    }


    public void ax_bk_mid(int m, int p1, int p2) {
        //1: rotat.

        

    }




////////////////////////////////////////////////////////////////
    public void cal_ax_tn(Pro_point pt, int p1, int p2, int p3) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);

        double y = y1 + -(x3 - x2) * (0 - x1) / (y3 - y2);
        pt.setXY(0, y);
    }

    public void cal_ax_md(Pro_point pt, int p1, int p2) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        pt.setXY((x1 + x2) / 2, (y1 + y2) / 2);
    }

    public boolean cal_ax_ill(Pro_point pt, int p1, int p2, int p3, int p4) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double x4 = VPTX(p4);
        double y4 = VPTY(p4);
        double xt1 = x2 - x1;
        double xt2 = x4 - x3;
        double yt1 = y2 - y1;
        double yt2 = y4 - y3;

        if (Math.abs(yt1 * xt2 - yt2 * xt1) < ZERO)
            return false;

        if (Math.abs(xt1) > ZERO) {
            double x = (xt1 * xt2 * (y3 - y1) + yt1 * x1 * xt2 - yt2 * x3 * xt1) / (yt1 * xt2 - yt2 * xt1);
            double y = y1 + (x - x1) * yt1 / xt1;
            pt.setXY(x, y);
        } else {
            double x = x1;
            double y = y4 + yt2 * (x - x4) / xt2;
            pt.setXY(x, y);
        }
        return true;
    }

    public void cal_ax_foot(Pro_point pt, int p1, int p2, int p3) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double b = y3 - y2;
        double a = x3 - x2;
        double m = a * a + b * b;
        double x = (a * b * y1 + b * b * x3 + a * a * x1 - a * b * y3) / m;
        double y = (b * b * y1 + a * a * y3 + a * b * x1 - a * b * x3) / m;
        pt.setXY(x, y);
    }

    public boolean cal_ax_ilp(Pro_point pt, int p1, int p2, int p3, int p4, int p5) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double x4 = VPTX(p4);
        double y4 = VPTY(p4);
        double x5 = VPTX(p5);
        double y5 = VPTY(p5);

        double xt1 = x2 - x1;
        double xt2 = x5 - x4;
        double yt1 = y2 - y1;
        double yt2 = y5 - y4;
        if (Math.abs(yt1 * xt2 - yt2 * xt1) < ZERO)
            return false;

        if (Math.abs(xt1) > ZERO) {
            double x = (xt1 * xt2 * (y3 - y1) + yt1 * x1 * xt2 - yt2 * x3 * xt1) / (yt1 * xt2 - yt2 * xt1);
            double y = y1 + (x - x1) * yt1 / xt1;
            pt.setXY(x, y);
        } else {
            double x = x1;
            double y = y3 + yt2 * (x - x3) / xt2;
            pt.setXY(x, y);
        }
        return true;
    }

    public void cal_ax_ilt(Pro_point pt, int p1, int p2, int p3, int p4, int p5) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double x4 = VPTX(p4);
        double y4 = VPTY(p4);
        double x5 = VPTX(p5);
        double y5 = VPTY(p5);

        double xt1 = x2 - x1;
        double xt2 = -(y5 - y4);
        double yt1 = y2 - y1;
        double yt2 = x5 - x4;
        if (Math.abs(xt1) > ZERO) {
            double x = (xt1 * xt2 * (y3 - y1) + yt1 * x1 * xt2 - yt2 * x3 * xt1) / (yt1 * xt2 - yt2 * xt1);
            double y = y1 + (x - x1) * yt1 / xt1;
            pt.setXY(x, y);
        } else {
            double x = x1;
            double y = y3 + yt2 * (x - x3) / xt2;
            pt.setXY(x, y);
        }
    }

    public void cal_ax_co(Pro_point pt, int p1, int p2, int p3) {
        double x_1 = VPTX(p1);
        double x_2 = VPTY(p1);
        double x_3 = VPTX(p2);
        double x_4 = VPTY(p2);
        double x_5 = VPTX(p3);
        double x_6 = VPTY(p3);
        double m = (2 * (x_3 - x_1) * x_6 + (-2 * x_4 + 2 * x_2) * x_5 + 2 * x_1 * x_4 - 2 * x_2 * x_3);

        double x = (x_4 - x_2) * x_6 * x_6 + (-1 * x_4 * x_4 - x_3 * x_3 + x_2 * x_2 + x_1 * x_1) * x_6
                + (x_4 - x_2) * x_5 * x_5 + x_2 * x_4 * x_4 + (-1 * x_2 * x_2 - x_1 * x_1) * x_4 + x_2 * x_3 * x_3;

        x = (-1) * x / m;

        double y = (-1) * ((2 * x_5 - 2 * x_1) * x - x_6 * x_6 - x_5 * x_5 + x_2 * x_2 + x_1 * x_1)
                / ((2 * x_6 - 2 * x_2));

        pt.setXY(x, y);
    }

    public boolean cal_ax_ipc(Pro_point pt, int p1, int p2, int p3, int p4, int p5) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double x4 = VPTX(p4);
        double y4 = VPTY(p4);
        double x5 = VPTX(p5);
        double y5 = VPTY(p5);
        double dx = x2 - x1;
        double dy = y2 - y1;
        double x0 = x3 + dx;
        double y0 = y3 + dy;
        double[] r = cal_inter_lc(x0, y0, x3, y3, x4, y4, x5, y5);
        if (r.length == 0) return false;
        if (fd_pt(r[0], r[1]) == null)
            pt.setXY(r[0], r[1]);
        else
            pt.setXY(r[2], r[3]);
        return true;
    }

    public boolean cal_ax_ilc(Pro_point pt, int p1, int p2, int p3, int p4) {
        double x1 = VPTX(p1);
        double y1 = VPTY(p1);
        double x2 = VPTX(p2);
        double y2 = VPTY(p2);
        double x3 = VPTX(p3);
        double y3 = VPTY(p3);
        double x4 = VPTX(p4);
        double y4 = VPTY(p4);
        double[] r = cal_inter_lc(x1, y1, x2, y2, x3, y3, x4, y4);
        if (r.length == 0) return false;
        if (fd_pt(r[0], r[1]) == null)
            pt.setXY(r[0], r[1]);
        else
            pt.setXY(r[2], r[3]);
        return true;
    }

    double[] cal_inter_lc(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        double r2 = (y4 - y3) * (y4 - y3) + (x4 - x3) * (x4 - x3);
        if (Math.abs(x2 - x1) > ZERO) {
            double k = (y2 - y1) / (x2 - x1);
            double t = y2 - y3 - k * x2;
            double a = k * k + 1;
            double b = 2 * k * t - 2 * x3;
            double c = t * t + x3 * x3 - r2;
            double d = b * b - 4 * a * c;
            if (d < 0) return new double[0];
            d = Math.sqrt(d);
            double t1 = (-b + d) / (2 * a);
            double t2 = (-b - d) / (2 * a);
            double m1 = (t1 - x2) * k + y2;
            double m2 = (t2 - x2) * k + y2;
            double[] r = new double[4];
            r[0] = t1;
            r[1] = m1;
            r[2] = t2;
            r[3] = m2;
            return r;

        } else {
            double t1 = x1;
            double d = Math.sqrt(r2 - (t1 - x3) * (t1 - x3));
            double m1 = y3 + d;
            double t2 = t1;
            double m2 = y3 - d;
            double[] r = new double[4];
            r[0] = t1;
            r[1] = m1;
            r[2] = t2;
            r[3] = m2;
            return r;

        }
    }

    void auxpt_string(Pro_point pt) {
        String s = pt.toString();
        if (s == null || s.length() == 0) {
        } else
            s += " is ";

        switch (pt.type) {
            case C_MIDPOINT:
                pt.text = s + "midpoint of " + ANAME(pt.ps[1]) + ANAME(pt.ps[2]);
                break;
            case C_I_LL:
                pt.text = s + "intersection of " + ANAME(pt.ps[1]) + ANAME(pt.ps[2])
                        + " and " + ANAME(pt.ps[3]) + ANAME(pt.ps[4]);
                break;
            case C_FOOT:
                pt.text = s + "the foot of " + ANAME(pt.ps[1]) + "," + ANAME(pt.ps[2]) + "," + ANAME(pt.ps[3]);
                break;
            case C_I_LP:
                pt.text = s + "on line " + ANAME(pt.ps[1]) + ANAME(pt.ps[2]) +
                        " and" + pt + ANAME(pt.ps[3]) + " // " + ANAME(pt.ps[4]) + ANAME(pt.ps[5]);
                break;
            case C_I_LT:
                pt.text = s + "on line " + ANAME(pt.ps[1]) + ANAME(pt.ps[2]) +
                        " and" + pt + ANAME(pt.ps[3]) + " perp " + ANAME(pt.ps[4]) + ANAME(pt.ps[5]);
                break;
            case C_I_LC:
                pt.text = s + "the intersection of  " + ANAME(pt.ps[1]) + ANAME(pt.ps[2]) +
                        " and circle(" + ANAME(pt.ps[3]) + ANAME(pt.ps[4]) + ")";
                break;

        }
    }

    protected String fd_aux_name() {
        char c[] = new char[1];//= new char[3];
        int len = 1;
        char n = 1;

        while (true) {
            int j = 1;
            c[0] = 'A';

            while (c[0] <= 'Z') {
                if (len == 1) {
                } else {
                    c[1] = (char) ('0' + n);
                    n++;
                }
                String t = new String(c);
                for (j = 1; j <= cons_no; j++)
                    if (ANAME(j).equals(t))
                        break;
                if (j > cons_no)
                    break;
                c[0] += 1;
            }
            if (j >= cons_no)
                break;
            len++;
            c = new char[len];
            c[0] = 'A';
        }
        return new String(c);
    }

}
