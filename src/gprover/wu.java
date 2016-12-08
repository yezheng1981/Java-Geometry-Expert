package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Jan 9, 2007
 * Time: 12:08:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class wu extends poly {
    static boolean div_factor = true;
    static boolean del_linear = false;
    static char pri_ind;
    static xterm[] inits;
    dterm l_set, div_set, tri_set, conc_set, conc_cp;
    boolean print_pind;
    int max_termp, max_term, vec_co = 0;


    void prove_wu() {
        dterm dp1, dp2;

        get_divs();
        l_set.nx = null;
        lt_set();
        tri_set.nx = prem_ps_asc(tri_set.nx, l_set.nx);
        dp1 = conc_set.nx;
        while (dp1 != null) {
            dp1.p = prem_asc(dp1.p, l_set.nx);
            dp1 = dp1.nx;
        }

        dp1 = tri_set.nx;
        while (dp1 != null) {
            dp1.p = cprim(dp1.p);
            dp1 = dp1.nx;
        }
        dp1 = conc_set.nx;
        while (dp1 != null) {
            dp1.p = cprim(dp1.p);
            dp1 = dp1.nx;
        }

        if (print_pind && l_set.nx != null) {
//            gprint(Cm.s2830);
//            dprint(l_set.nx);
//            gprint(Cm.s2831);
//            dprint(tri_set.nx);
//            gprint(Cm.s2832);
//            dprint(conc_set.nx);
        }

        pri_ind = 1;
        if (print_pind) {
//            gprint(Cm.s2833));
        }


        dp1 = conc_set.nx;
        if (print_pind) {
            print_ind(dp1.p);
            gprint("\r\n");
        }
        for (; dp1 != null; dp1 = dp1.nx) {
            dp1.deg = 1;
            dp2 = prem_asc_p(dp1.p, tri_set.nx);
            dp1.p = null;
            if (dp2 == null) {
                dp1.deg = 1;
            } else {
                dp1.deg = 0;
                put_ps(dp2);
            }
        }
        show_conc();
    }

    void show_conc() {
        dterm dp1 = conc_set.nx;
        int count = 1;
        while (dp1 != null) {
            if (dp1.deg == 0) count = 0;
            dp1 = dp1.nx;
        }
        //gprint("\n***************The results****************\n");
        if (count != 0) {
//            pro_result = 1;
//            gprint("\r\n");
//            gprint(Cm.s2050));
        } else {
//            pro_result = -1;
//            gprint("\r\n");
//            if (irr_thm())
//                gprint(Cm.s2056));
//            else
//            gprint(Cm.s2051));

        }
    }


    void get_divs() {
        char i, k;
        dterm dp1, dp2;

        for (i = 0; i < 50; i++) inits[i] = null;
        k = 2;
        dp1 = tri_set.nx;
        if (dp1 == null) return;
        dp2 = dp1.nx;
        while (dp2 != null) {
            if ((dp1.deg == 1) && (dp2.deg == 2)) {
                inits[++k] = cp_init(dp1.p);
                dp2.deg = k;
            } else
                dp2.deg = 0;
            dp1 = dp2;
            dp2 = dp2.nx;
        }
    }

    void lt_set() {
        dterm dp1, dp2, dp3;
        dp1 = tri_set;
        dp2 = dp1.nx;
        l_set.nx = null;
        dp3 = l_set;
        while (dp2 != null) {
            if (del_linear && (ldeg(dp2.p) == 1) && (numberp(init(dp2.p)))) {
                dp1.nx = dp2.nx;
                dp3.nx = dp2;
                dp3 = dp3.nx;
                dp2 = dp1.nx;
            } else if (plength(dp2.p) > 2) {
                dp1 = dp2;
                dp2 = dp1.nx;
            } else if (ldeg(dp2.p) != 1) {
                dp1 = dp2;
                dp2 = dp1.nx;
            } else {
                dp1.nx = dp2.nx;
                dp3.nx = dp2;
                dp3 = dp2;
                dp2 = dp1.nx;
            }
        }
        dp3.nx = null;
    }

    xterm prem_asc(xterm p, dterm asc) {
        dterm dp1;
        for (dp1 = asc; dp1 != null; dp1 = dp1.nx) {
            p = cprim(prem(p, dp1.p));
        }
        return (p);
    }

    dterm prem_ps_asc(dterm ps, dterm asc) {
        dterm dt1, dp1, dp2;
        if (asc == null) return (ps);

        dt1 = new dterm();
        dt1.nx = ps;
        dp1 = dt1;
        dp2 = dp1.nx;
        while (dp2 != null) {
            dp2.p = mv_nfact(prem_asc(dp2.p, asc));
            if (pzerop(dp2.p)) {
                dp1.nx = dp2.nx;
                put_x(dp2.p);
                put_d(dp2);
                dp2 = dp1.nx;
            } else {
                dp1 = dp2;
                dp2 = dp1.nx;
            }
        }
        return (dt1.nx);
    }

/* prem-asc_p: the branching version */
    dterm prem_asc_p(xterm p, dterm asc)
//xterm  p;
//dterm  asc;
    {
        xterm a1, p1, p2;
        dterm dt1, dp1, dp2, dp3;
        int tem;

        if (p == null) return (null);
        if (pzerop(p)) {
            put_p(p);
            return (null);
        } else if (p.var == null) {
            return (get_dt(0, p, null));
        }

        dt1 = new dterm();
        dt1.nx = get_dt(1, p, null);
        /*at.deg=0;  at.nx=asc;  asc0= &at; */
        for (; asc != null; asc = asc.nx) {
            a1 = asc.p;
//		pprint(a1);gprint(" 1\r\n");
            dp1 = dt1;
            dp2 = dp1.nx;
            while (dp2 != null) {
                p1 = dp2.p;
                //pprint(p1);gprint(" 2\r\n");
                //sprintf(txt,"(%d)(%d)\r\n",a1->var,p1->var);
                //gprint(txt);
                if (pzerop(p1)) {
                    dp1.nx = dp2.nx;
                    put_x(p1);
                    put_d(dp2);
                    dp2 = dp1.nx;
                } else if (p1.var == null) {
                    dp1 = dp2;
                    dp2 = dp1.nx;
                } else if (p1.var == a1.var) {
                    /*printf("prem-asc-p1(%d)\n",plength(p1));  */
                    p2 = prem_ev(p1, a1);
                    /*printf("prem-asc-p2(%d)\n",plength(p2));  */
                    dp2.p = cprim(p2);
                    /*printf("prem-asc-p3(%d)\n",plength(dp2->p));  */
                    /*dp2->p=cprim(prem_ev(p1,a1)); */
                    /* printf("prem-asc-p1\n"); pprint(dp2->p);pprint(a1);   */
                    if (print_pind && dp2.deg == 1) {
                        if (pzerop(dp2.p)) {
                            if (pri_ind == 1) {
                                print_ind(dp2.p);
                                gprint("\r\n");
                                pri_ind = 0;
                            }
                        } else {
                            print_ind(dp2.p);
                            gprint("\r\n");
                        }
                    }
                    if (pzerop(dp2.p)) {
                        dp1.nx = dp2.nx;
                        put_x(dp2.p);
                        put_d(dp2);
                        dp2 = dp1.nx;
                    } else {
                        if (max_termp > 0) {
                            tem = plength(dp2.p);
                            if (tem > max_term) max_term = tem;
                        }
                        if (div_factor && asc.deg > 2) {
/*		    printf("1111(%d)\n",asc->deg); pprint(inits[asc->deg]);  */
                            p2 = mv_mfact(prem_asc(cp_poly(inits[asc.deg]), l_set.nx));
                            dp2.p = ppdiv(dp2.p, p2);
                            put_p(p2);
                        }
                        dp1 = dp2;
                        dp2 = dp1.nx;
                    }
                } else if (vless(a1.var, p1.var)) {
                    dp3 = p1.ps;
                    dp3.deg = 1;
                    while (dp3.nx != null) {
                        dp3 = dp3.nx;
                        dp3.deg = 1;
                    }
                    dp3.nx = dp2.nx;
                    dp1.nx = p1.ps;
                    put_d(dp2);
                    dp2 = dp1.nx;
                    put_x(p1);
                } else {
                    dp1 = dp2;
                    dp2 = dp1.nx;
                }
            } /* dp2= null */
            /*asc0=asc;  */
        }	  /*asc=null */
        return (dt1.nx);
    }

    dterm nonzerops(dterm dp) {
        dterm dt1, dp1, dp2;
        dt1 = new dterm();
        dt1.nx = dp;
        dp1 = dt1;
        dp2 = dp1.nx;
        while (dp2 != null) {
            if (pzerop(dp2.p)) {
                dp1.nx = dp2.nx;
                put_x(dp2.p);
                put_d(dp2);
                dp2 = dp1.nx;
            } else {
                dp1 = dp2;
                dp2 = dp1.nx;
            }
        }
        return (dt1.nx);
    }

/* remove all the numerical factors */

    xterm cprim(xterm p1) {
        xterm pp1;
        int i;
        if (pzerop(p1))
            return (p1);
        else if (p1.var == null) {
            put_p(p1);
            return (get_n(1L));
        } else { /*printf("cp1(%d)\n",plength(p1));  */
            i = lcontent(p1);
            /*printf("cp2(%d)(%d)\n",type_of(i),fix(i)); */
            if (num_unit(i)) return (p1);
            if (num_nunit(i)) {
                p1 = ptimes(get_n(-1L), p1);
                return (p1);
            }
            {
                pp1 = get_num(i);
                p1 = pcdiv(p1, pp1);
                put_x(pp1);
                return (p1);
            }
        }
    }

    xterm mv_mfact(xterm p1) {
        xterm pp1;
        if (pzerop(p1))
            return (p1);
        else if (p1.var == null) {
            put_p(p1);
            return (get_n(1L));
        } else {
            pp1 = cprim(cp_init(p1));
            if (pp1.var != null) p1 = ppdiv(p1, pp1);
            put_p(pp1);
            p1 = cprim(p1);
            return (p1);
        }
    }

    xterm mv_nfact(xterm p) {
        xterm p1, p2;
        int d1;
        int c;
        if (pzerop(p))
            return (p);
        else if (p.var == null) {
            put_p(p);
            return (get_n(1L));
        }
        p1 = vars_in_p(init(p));
        while (p1 != null) {
            d1 = mdeg(p, p1.var);
            if (d1 > 0) {
                p2 = get_v(p1.var, d1, get_n(1L));
                p = pdiv(p, p2);
                put_p(p2);
            }
            p2 = p1;
            p1 = p1.p;
            put_x(p2);
        }
        c = lcontent(p);
        if (num_nunit(c)) {
            p1 = get_num(c);
            p = ptimes(p, p1);
        } else if (!num_unit(c)) {
            p1 = get_num(c);
            p = pcdiv(p, p1);
            put_x(p1);
        }
        return (p);
    }

    xterm gq2co(xterm p) {
        var v;
        xterm p1 = new xterm();
        v = p.var;
        while (v != null && v.nm > 0 && v.nm < 100) {
            switch (v.nm) {
                case 1:
                    if (xperp(1, 2, v.pt[0], v.pt[1]))
                        p1 = pminus(ptimes(get_m(v), pminus(yw(v.pt[3]), yw(v.pt[2]))),
                                pminus(yw(v.pt[1]), yw(v.pt[0])));
                    else
                        p1 = pminus(ptimes(get_m(v), pminus(xw(v.pt[3]), xw(v.pt[2]))),
                                pminus(xw(v.pt[1]), xw(v.pt[0])));
                    break;
                case 2:
                    p1 = pminus(ptimes(get_m(v), get_n(2)),
                            pminus(carea(v.pt[0], v.pt[1], v.pt[3]),
                                    carea(v.pt[2], v.pt[1], v.pt[3])));
                    break;
                case 3:
                    p1 = pminus(get_m(v),
                            pminus(pplus(cdis(v.pt[0], v.pt[1]), cdis(v.pt[2], v.pt[3])),
                                    pplus(cdis(v.pt[1], v.pt[2]), cdis(v.pt[3], v.pt[0]))));
                    break;
                case 4:
                    if (v.pt[1] == 0 && vec_co == 1) {
                        p1 = pminus(get_m(v), xw(v.pt[0]));
                    } else if (v.pt[1] == 0) {
                        p1 = pminus(get_m(v), yw(v.pt[0]));
                    } else if (vec_co == 1) {
                        p1 = pminus(get_m(v), pminus(xw(v.pt[1]), xw(v.pt[0])));
                    } else {
                        p1 = pminus(get_m(v), pminus(yw(v.pt[1]), yw(v.pt[0])));
                    }
                    break;
                default:
                    gerror("gq2co");
            }
            p = prem_var(p, p1, v);
            put_p(p1);
            v = p.var;
        }
        return (p);
    }


    xterm cdis(int n1, int n2)
//int n1,n2;
    {
        if (n1 == n2)
            return (get_n(0L));
        else
            return (pplus(ppower(pminus(xw(n1), xw(n2)), 2),
                    ppower(pminus(yw(n1), yw(n2)), 2)));
    }

    xterm carea(int n1, int n2, int n3)
//int n1,n2,n3;
    {
        return (pplus3(pminus(ptimes(xw(n1), yw(n2)), ptimes(xw(n2), yw(n1))),
                pminus(ptimes(xw(n2), yw(n3)), ptimes(xw(n3), yw(n2))),
                pminus(ptimes(xw(n3), yw(n1)), ptimes(xw(n1), yw(n3)))));
    }


/*    */

    void eq_l(xterm q1) {
        dterm dp1;
        xterm pp1;
        xterm p1 = cp_poly(q1);

        pp1 = prem_asc(cp_init(p1), tri_set.nx);
        if (pzerop(pp1)) {
            dp1 = tri_set.nx;
            tri_set.nx = get_dt(0, mv_mfact(rem(p1)), dp1);
        } else {
            dp1 = tri_set.nx;
            tri_set.nx = get_dt(0, mv_mfact(p1), dp1);
        }
        put_p(pp1);
    }

    void eq_ll(xterm q1, xterm q2) {
        xterm p3, p4;
        xterm p1 = cp_poly(q1);
        xterm p2 = cp_poly(q2);
/*
  printf("\eqll1(%ld)(%ld)\n",p1->var,p2->var);
  printf("\neqll2(%d)\n",p1->var==p2->var);
  pprint(p1);pprint(p2);
*/
        if (p1.var == p2.var) {
            if (pls(p2, p1)) {
                p3 = p1;
                p1 = p2;
                p2 = p3;
            }
            p4 = prem_asc(cp_init(p1), tri_set.nx);
            if (pzerop(p4)) {
                p1 = prem(p1, p2);
                if (pzerop(p1)) {
                    gerror("eq_ll: two intersection lines are parallel\n");
                    return;
                }
                tri_set.nx = get_dt(2, mv_mfact(p1), tri_set.nx);
                tri_set.nx = get_dt(1, mv_mfact(p2), tri_set.nx);
            } else {
                p2 = prem(p2, p1);
                if (pzerop(p2)) {
                    gerror("eq_ll: two intersection lines are parallel\n");
                    return;
                }
                tri_set.nx = get_dt(2, mv_mfact(p2), tri_set.nx);
                tri_set.nx = get_dt(1, mv_mfact(p1), tri_set.nx);
            }
            put_p(p4);
        } else if (vless(p2.var, p1.var)) {
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
        } else {
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
        }
    }

    void eq_lq(xterm q1, xterm q2) {
        xterm p3;
        xterm p1 = cp_poly(q1);
        xterm p2 = cp_poly(q2);
        if (p1.var == p2.var) {
            p3 = prem_asc(cp_init(p1), tri_set.nx);
            if (pzerop(p3)) {
                p1 = rem(p1);
                tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
                tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
            } else {
                p2 = prem(p2, p1);
                if (pzerop(p2)) {
                    gerror("eq_lq: two intersection lines are parallel\n");
                    return;
                }
                tri_set.nx = get_dt(2, mv_mfact(p2), tri_set.nx);
                tri_set.nx = get_dt(1, mv_mfact(p1), tri_set.nx);
            }
            put_p(p3);
        } else if (vless(p2.var, p1.var)) {
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
        } else {
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
        }
    }

    void eq_lq1(xterm q1, xterm q2, int pt) {
        xterm p3, pp1;
        xterm p1 = cp_poly(q1);
        xterm p2 = cp_poly(q2);
        if (p1.var == p2.var) {
            p3 = prem_asc(cp_init(p1), tri_set.nx);
            if (pzerop(p3)) {
                p1 = rem(p1);
                p2 = prem(p2, p1);
                pp1 = pminus(get_m(p2.var), yw(pt));
            } else {
                p2 = prem(p2, p1);
                pp1 = pminus(get_m(p2.var), xw(pt));
            }
            p2 = pdiv(p2, pp1);
            put_p(pp1);
            tri_set.nx = get_dt(2, mv_mfact(p2), tri_set.nx);
            tri_set.nx = get_dt(1, mv_mfact(p1), tri_set.nx);
            put_p(p3);
        } else if (vless(p2.var, p1.var)) {
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
        } else {
            tri_set.nx = get_dt(0, mv_mfact(p1), tri_set.nx);
            tri_set.nx = get_dt(0, mv_mfact(p2), tri_set.nx);
        }
    }

    void eq_qq(xterm q1, xterm q2) {
        xterm pp1;
        xterm p1 = cp_poly(q1);
        xterm p2 = cp_poly(q2);
        pp1 = pminus(p2, cp_poly(p1));
        p2 = prem(p1, pp1);
        tri_set.nx = get_dt(2, p2, tri_set.nx);
        tri_set.nx = get_dt(1, pp1, tri_set.nx);
    }

    void eq_qq1(xterm q1, xterm q2, int pt) {
        xterm pp1, pp2;
        xterm p1 = cp_poly(q1);
        xterm p2 = cp_poly(q2);
        pp1 = pminus(p2, cp_poly(p1));
        p2 = prem(p1, pp1);
        pp2 = pminus(get_m(p2.var), xw(pt));
        p2 = pdiv(p2, pp2);
        put_p(pp2);
        tri_set.nx = get_dt(2, mv_mfact(p2), tri_set.nx);
        tri_set.nx = get_dt(1, mv_mfact(pp1), tri_set.nx);
    }

//    PP eq_lq4(xterm p1, xterm p2, int pt) {
//        xterm pp1;
//        PP pp;
//        if (p1.var == p2.var) {
//            p2 = prem(p2, p1);
//            pp1 = pminus(get_m(p2.var), xw(pt));
//            p2 = pdiv(p2, pp1);
//            put_p(pp1);
//            pp.p1 = mv_mfact(p2);
//            pp.p2 = mv_mfact(p1);
//        } else if (vless(p2.var, p1.var)) {
//            pp.p1 = mv_mfact(p2);
//            pp.p2 = mv_mfact(p1);
//        } else {
//            pp.p1 = mv_mfact(p1);
//            pp.p2 = mv_mfact(p2);
//        }
//        return (pp);
//    }

    void init_wu() {
        int i;
        tri_set.nx = null;
        l_set.nx = null;
        conc_set.nx = null;
        div_set.nx = null;
        for (i = 1; i < 100; i++) {
//            hyp_set[i].p1 = null;
//            hyp_set[i].p2 = null;
        }
        for (i = 0; i < 50; i++) inits[i] = null;
    }

    void free_wu() {
        dterm ps1, ps2;
        int i;

        put_ps(tri_set.nx);
        put_ps(l_set.nx);
        ps1 = conc_set.nx;
        while (ps1 != null) {
            if (ps1.p != null) put_p(ps1.p);
            ps2 = ps1.nx;
            put_d(ps1);
            ps1 = ps2;
        }
        tri_set.nx = null;
        l_set.nx = null;
        conc_set.nx = null;

        if (pro_type == PRO_WU) {
            for (i = 0; i < 50; i++) { /*printf("(%d)\n", inits[i]); */
                if (inits[i] != null) {
                    put_p(inits[i]);
                    inits[i] = null;
                }
            }
        }
        if (pro_type == PRO_GB) {
            ps1 = div_set.nx;
            while (ps1 != null) {
                ps2 = ps1;
                ps1 = ps1.nx;
                put_p(ps2.p);
                put_d(ps2);
            }
            div_set.nx = null;
        }
//        for (i = 1; i < 100; i++) {
//            if (hyp_set[i].p1) put_p(hyp_set[i].p1);
//            if (hyp_set[i].p2) put_p(hyp_set[i].p2);
//            hyp_set[i].p1 = null;
//            hyp_set[i].p2 = null;
//        }
    }


    boolean irr_thm() {
        int[] p = new int[10];


        int j;
        boolean th_p = false;
        for (int ptn = 1; ptn <= cons_no; ptn++) {
            for (j = 0; j <= 5; j++) p[j + 1] = APTS(ptn, j);
            switch (ATYPE(ptn)) {
                case C_I_LC:
                    if (p[2] == p[4] || p[1] == p[4]) {
                        th_p = true;
                        break;
                    }
                    break;
                case C_I_PC:
                case C_I_TC:
                    if (p[1] == p[5]) {
                        th_p = true;
                        break;
                    }
                    break;
                case C_I_CC:
                    if (p[2] != p[4]) {
                        th_p = true;
                        break;
                    }
                    break;
                case C_I_LR:
                case C_I_CR:
                case C_I_RR:
                    th_p = true;
                    break;
            }
        }
        return th_p;
    }


    ///////////////////////////////////////////////////////////////////////
    //from eqs.cpp

    xterm coll(int p1, int p2, int p3) {
        xterm xp1;
        xp1 = pminus(ptimes(pminus(yw(p3), yw(p1)), pminus(xw(p2), xw(p1))),
                ptimes(pminus(xw(p3), xw(p1)), pminus(yw(p2), yw(p1))));
        return (xp1);
    }

    xterm para(int p1, int p2, int p3, int p4) {
        xterm xp1;
        xp1 = pminus(ptimes(pminus(yw(p4), yw(p3)),
                pminus(xw(p2), xw(p1))),
                ptimes(pminus(xw(p4), xw(p3)),
                        pminus(yw(p2), yw(p1))));
        return (xp1);
    }

    xterm perp(int p1, int p2, int p3, int p4)
//int p1,p2,p3,p4;
    {
        xterm xp1;
        xp1 = pplus(ptimes(pminus(yw(p4), yw(p3)),
                pminus(yw(p2), yw(p1))),
                ptimes(pminus(xw(p4), xw(p3)),
                        pminus(xw(p2), xw(p1))));
        return (xp1);
    }

    xterm yratio(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8)
//int p1,p2,p3,p4,p5,p6,p7,p8;
    {
        xterm xp1;
        xp1 = pminus(ptimes(pminus(yw(p4), yw(p3)),
                pminus(yw(p6), yw(p5))),
                ptimes(pminus(yw(p8), yw(p7)),
                        pminus(yw(p2), yw(p1))));
        return (xp1);
    }

    xterm xratio(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8)
//int p1,p2,p3,p4,p5,p6,p7,p8;
    {
        xterm xp1;
        xp1 = pminus(ptimes(pminus(xw(p4), xw(p3)),
                pminus(xw(p6), xw(p5))),
                ptimes(pminus(xw(p8), xw(p7)),
                        pminus(xw(p2), xw(p1))));
        return (xp1);
    }

    xterm eqptx(int p1, int p2) {
        xterm xp1;
        xp1 = pminus(xw(p1), xw(p2));
        return (xp1);
    }

    xterm eqpty(int p1, int p2)
//int  p1,p2;
    {
        xterm xp1;
        xp1 = pminus(yw(p1), yw(p2));
        return (xp1);
    }

    xterm midx(int p1, int p2, int p3)
//int  p1,p2,p3;
    {
        xterm xp1;
        xp1 = pminus(pplus(xw(p2), xw(p3)), ptimes(get_n(2L), xw(p1)));
        return (xp1);
    }

    xterm midy(int p1, int p2, int p3)
//int p1,p2,p3;
    {
        xterm xp1;
        xp1 = pminus(pplus(yw(p2), yw(p3)), ptimes(get_n(2L), yw(p1)));
        return (xp1);
    }

    xterm dist(int p1, int p2) {
        xterm xp1;
        xp1 = pplus(ptimes(pminus(yw(p2), yw(p1)),
                pminus(yw(p2), yw(p1))),
                ptimes(pminus(xw(p2), xw(p1)),
                        pminus(xw(p2), xw(p1))));
        return (xp1);
    }

    xterm cong(int p1, int p2, int p3, int p4)
//int p1,p2,p3,p4;
    {
        xterm xp1;
        xp1 = pminus(dist(p1, p2), dist(p3, p4));
        return (xp1);
    }

    xterm acong(int p1, int p2, int p3, int p4, int p5, int p6)
//int p1,p2,p3,p4,p5,p6;
    {
        xterm xp1;
        xp1 = pminus(ptimes(para(p3, p2, p1, p2), perp(p6, p5, p4, p5)),
                ptimes(para(p6, p5, p4, p5), perp(p3, p2, p1, p2)));
        return (xp1);
    }

    xterm tcc(int o1, int p1, int o2, int p2)
//int  o1,p1,o2,p2;
    {
        xterm xp1, rp1, rp2;
        rp1 = ptimes(dist(o1, p1), dist(o1, p1));
        rp2 = ptimes(dist(o2, p2), dist(o2, p2));
        rp1 = pplus(rp1, rp2);
        xp1 = ptimes(dist(o1, o2), dist(o1, o2));
        xp1 = pplus(rp1, xp1);

        rp1 = ptimes(dist(o1, p1), dist(o2, p2));
        rp1 = pctimes(get_n(-2L), rp1);
        xp1 = pplus(xp1, rp1);

        rp1 = ptimes(dist(o1, p1), dist(o1, o2));
        rp1 = pctimes(get_n(-2L), rp1);
        xp1 = pplus(xp1, rp1);

        rp1 = ptimes(dist(o2, p2), dist(o1, o2));
        rp1 = pctimes(get_n(-2L), rp1);
        xp1 = pplus(xp1, rp1);

        return (xp1);
    }

    xterm pratiox(int y, int w, int u, int v, xterm p1, xterm p2)
//int y,w,u,v;
//xterm *p1,*p2;
    {
        return (pminus(ptimes(p2, pminus(xw(y), xw(w))),
                ptimes(p1, pminus(xw(v), xw(u)))));
    }

    xterm pratioy(int y, int w, int u, int v, xterm p1, xterm p2)
//int y,w,u,v;
//xterm *p1,*p2;
    {
        return (pminus(ptimes(p2, pminus(yw(y), yw(w))),
                ptimes(p1, pminus(yw(v), yw(u)))));
    }


//    xterm tratioc(int y, int w, int u, int v, xterm p1, xterm p2)
////int y,w,u,v;
////xterm *p1,*p2;
//    {
//        xterm p0;
//        //xterm *trim_a(), *trim_g();
//        p0 = pminus(ptimes3(get_n(4L), p2, trim_a(y, u, w, v)),
//                ptimes(p1, trim_g(u, u, v, v)));
//        return (p0);
//    }

    void print_coor() {
    }

    int cx(int n) {
        int k;
        if (pro_type == PRO_GB && ATYPE(n) == C_POINT) {
            if (n == 2) k = -1; else k = -2 * (n - 2);
        } else if (pro_type == PRO_GB && ATYPE(n) == C_O_C) {
            k = -2 * (n - 2);
        } else {
            if (n == 2) k = 1; else k = 2 * (n - 2);
        }
        return (k);
    }

    int cy(int n) {
        int k;
        if (pro_type == PRO_GB && ATYPE(n) == C_POINT) {
            k = -2 * (n - 2) - 1;
        } else {
            k = 2 * (n - 2) + 1;
        }
        return (k);
    }

    xterm xw(int n) {
        if (n == 1) return (get_n(0L));
        return (get_m(mk_wvar(cx(n))));
    }

    xterm yw(int n) {
        if (n == 1 || n == 2) return (get_n(0L));
        return (get_m(mk_wvar(cy(n))));
    }

}
