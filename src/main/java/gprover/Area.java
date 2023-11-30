package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-1
 * Time: 13:18:45
 * To change this template use File | Settings | File Templates.
 */
public class Area extends Full
{
      boolean elim_cir = false;

    int pts_pno;
      void prove_area()
      {
            int fpt_no, ptn;

            max_term = 1;
            elim_cir = false;
            last_pr = proof = new gr_term();
            proof.nx = null;
            pts_pno = cons_no;
            pconc();
            if (qerror) return;
            ptn = pts_pno;


            while (ptn >= 1 && !elim_cir)
            {
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  if (elim_cir == false && ATYPE(ptn) == C_CIRCUM)
                        pe_gr(ptn);
                  else if (circle_p(ptn))
                  {
                        elim_cir = true;
                  } else if (ATYPE(ptn) == C_CIRCLE)
                  {
                        elim_cir = true;
                  } else if (ATYPE(ptn) == C_POINT)
                  {
                  } else if (ATYPE(ptn) == -100)
                  {
                  }
                  else
                        pe_gr(ptn);
                  if (qerror) return;
                  ptn--;
            }

            if (end_pr())
            {
                  print_end();
                  return;
            }
            if (elim_cir == true)
            {
                  int k1, k2;
                  gr_term gr1 = last_pr;
                  pe_gr(-13);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  pe_gr(-11);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  if (gr1 != last_pr)
                  {
                        k1 = gr_length(gr1);
                        k2 = gr_length(last_pr);
                        if (k2 >= k1)
                        {
                              put_gr(last_pr);
                              last_pr = gr1;
                        }
                  }
                  pe_gr(-20);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  pe_gr(-21);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  pe_gr(-22);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  pe_gr(-23);
                  elim_cir = false;
                  print_end();
                  return;
            }
            if (vec_gr(last_pr))
            {
                  pe_gr(-30);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  print_end();
                  return;
            }
            fpt_no = 0;
            while (ATYPE(fpt_no + 1) == C_POINT) fpt_no++;
            if (fpt_no < 3)
            {
                  print_end();
                  return;
            }
            if (fpt_no == 3)
            {
                  pe_gr(-10);
                  if (end_pr())
                  {
                        print_end();
                        return;
                  }
                  pe_gr(-11);
                  print_end();
                  return;
            }
            if (area_gr(last_pr))
            {
                  for (ptn = fpt_no; ptn > 3; ptn--)
                  {
                        pe_gr(ptn);
                  }
                  print_end();
                  return;
            }
            pe_gr(-12);
            print_end();
      }

      boolean end_pr()
      {
            return ((last_pr.ps1 == null) && (last_pr.ps2 == null));
      }

      void print_end()
      {
            xterm p1, p2;
            if (last_pr.ps1 == null)
            {
                  if ((last_pr.ps2 == null) && (last_pr.c1 == last_pr.c2)) print_t(); else print_f();
                  return;
            }
            if (last_pr.ps2 == null)
            {
                  if ((last_pr.ps1 == null) && (last_pr.c1 == last_pr.c2)) print_t(); else print_f();
                  return;
            }
            p1 = ps_times(cp_pols(last_pr.ps1));
            p1 = ptimes(get_n(last_pr.c1), p1);
            p2 = ps_times(cp_pols(last_pr.ps2));
            p2 = ptimes(get_n(last_pr.c2), p2);
            if (eq_poly(p1, p2))
            {
                  put_p(p1);
                  conc_gr(1L, null, 1L, null);
                  last_pr.c = -1;
                  last_pr.ps = get_dt(1, p2, null);
                  if (last_pr.c1 == last_pr.c2)
                  {
                        print_t();
                        return;
                  } else
                  {
                        print_f();
                        return;
                  }
            }
            put_p(p1);
            put_p(p2);
            print_f();
            return;
      }

      void print_t()
      {
            if (print_conc)
            { //gprint("\r\n***************The results******          \r\n");
                  gprint(Cm.s2300);
            }
            //pro_result = 1;
      }

      void print_f()
      {
            if (print_conc)
            { //gprint("\n               The results                \n");
                  gprint(Cm.s2301);
            }

            gprint("(" + last_pr.c1 + ")");
            print_ps(last_pr.ps1, (char) 0);
            gprint(" = ");
            gprint("(" + last_pr.c2 + ")");
            print_ps(last_pr.ps2, (char) 0);
            gprint("\r\n");
            //pro_result = -1;
      }

      boolean area_gr(gr_term gr)
      {
            dterm ps;
            ps = gr.ps1;
            while (ps != null) if (area_p(ps.p)) ps = ps.nx; else return (false);
            ps = gr.ps2;
            while (ps != null) if (area_p(ps.p)) ps = ps.nx; else return (false);
            return (true);
      }

      boolean area_p(xterm p)
      {
            dterm ps;
            var v;
            if (p.var == null) return (true);
            v = p.var;
            if (v.nm == 2 || v.nm < 0)
            {
                  ps = p.ps;
                  while (ps != null) if (area_p(ps.p)) ps = ps.nx; else return (false);
                  return (true);
            } else
                  return (false);
      }

      boolean vec_gr(gr_term gr)
      {
            dterm ps;
            ps = gr.ps1;
            while (ps != null) if (vec_p(ps.p)) return (true); else ps = ps.nx;
            ps = gr.ps2;
            while (ps != null) if (vec_p(ps.p)) return (true); else ps = ps.nx;
            return (false);
      }

      boolean vec_p(xterm p)
//xterm  p;
      {
            dterm ps;
            var v;
            if (p.var == null) return (false);
            v = p.var;
            if (v.nm != 4)
            {
                  ps = p.ps;
                  while (ps != null) if (vec_p(ps.p)) return (true); else ps = ps.nx;
                  return (false);
            } else
                  return (true);
      }

      boolean str_p(xterm p)
      {
            dterm ps;
            var v;
            if (p.var == null) return (false);
            v = p.var;
            if (v.nm != 0)
            {
                  ps = p.ps;
                  while (ps != null) if (str_p(ps.p)) return (true); else ps = ps.nx;
                  return (false);
            } else
                  return (true);
      }


/* conclusions */
      void conc_gr(long c1, xterm p1, long c2, xterm p2)
      {
            gr_term gr = mk_gr1(mk_num(c1), p1, mk_num(c2), p2);
            gr.c = 0;
            last_pr.nx = gr;
            last_pr = gr;
      }

      void pconc()
      {
            switch (conc.pred)
            {
                  case CO_COLL: /* collinear */
                        conc_coll();
                        break;
                  case CO_PARA: /* parallel */
                        conc_gr(1L, trim_a(conc.p[0], conc.p[2], conc.p[3], conc.p[3]),
                                1L, trim_a(conc.p[1], conc.p[2], conc.p[3], conc.p[3]));
                        break;
                  case CO_PERP: /* perpendicular */
                        conc_gr(1L, trim_g(conc.p[0], conc.p[2], conc.p[2], conc.p[3]),
                                1L, trim_g(conc.p[1], conc.p[2], conc.p[2], conc.p[3]));
                        break;
                  case CO_CONG:  /* eqdistance */
                        conc_gr(1L, trim_g(conc.p[0], conc.p[0], conc.p[1], conc.p[1]),
                                1L, trim_g(conc.p[2], conc.p[2], conc.p[3], conc.p[3]));
                        break;
                  case CO_ACONG:  /* eqangle */
                        conc_gr(1L, ptimes(trim_a(conc.p[0], conc.p[1], conc.p[2], conc.p[3]),
                                trim_g(conc.p[4], conc.p[5], conc.p[6], conc.p[7])),
                                1L, ptimes(trim_g(conc.p[0], conc.p[1], conc.p[2], conc.p[3]),
                                        trim_a(conc.p[4], conc.p[5], conc.p[6], conc.p[7])));
                        break;
                  case CO_MIDP:  /*midpoint */
                        conc_gr(1L, trim_r(conc.p[0], conc.p[1], conc.p[0], conc.p[2]), -1L, null);
                        break;
                  case CO_PROD: /*eq-product */
                        conc_eqproduct();
                        break;
                  case CO_CYCLIC: /*cocircle */
                        conc_gr(1L, ptimes(trim_a(conc.p[3], conc.p[1], conc.p[1], conc.p[4]),
                                trim_g(conc.p[3], conc.p[2], conc.p[2], conc.p[4])),
                                1L, ptimes(trim_g(conc.p[3], conc.p[1], conc.p[1], conc.p[4]),
                                        trim_a(conc.p[3], conc.p[2], conc.p[2], conc.p[4])));
                        break;
                  case -9: /*on_radical */
                        conc_gr(1L, pminus(trim_g(conc.p[0], conc.p[1], conc.p[1], conc.p[0]),
                                trim_g(conc.p[1], conc.p[2], conc.p[2], conc.p[1])),
                                1L, pminus(trim_g(conc.p[0], conc.p[3], conc.p[3], conc.p[0]),
                                        trim_g(conc.p[3], conc.p[4], conc.p[4], conc.p[3])));
                        break;
                  case CO_TANGENT: /*tangent */
                        pconc_11(conc.p[0], conc.p[1], conc.p[2], conc.p[3]);
                        break;
                  case -12: /* perp-b */
                        conc_gr(1L, trim_g(conc.p[0], conc.p[0], conc.p[1], conc.p[1]),
                                1L, trim_g(conc.p[0], conc.p[0], conc.p[2], conc.p[2]));
                        break;
                  case CO_HARMONIC: /* harmonic */
                        conc_gr(1L, trim_r(conc.p[2], conc.p[0], conc.p[2], conc.p[1]),
                                -1L, trim_r(conc.p[3], conc.p[0], conc.p[3], conc.p[1]));
                        break;
                  case -16: /* inversion */
                        if (xcoll4(conc.p[1], conc.p[2], conc.p[3], conc.p[0]))
                              conc_gr(1L, trim_r(conc.p[0], conc.p[2], conc.p[0], conc.p[1]),
                                      1L, trim_r(conc.p[0], conc.p[1], conc.p[0], conc.p[3]));
                        else
                              conc_gr(1L, trim_g(conc.p[0], conc.p[1], conc.p[1], conc.p[0]),
                                      1L, trim_g(conc.p[2], conc.p[0], conc.p[0], conc.p[3]));
                        break;
                  case CO_PETRI:  /*-18 pe-traingle */
                        conc_gr(1L, pplus3(trim_vec(conc.p[0], 0),
                                ptimes(get_s("w".toCharArray()), trim_vec(conc.p[1], 0)),
                                ptimes3(get_s("w".toCharArray()), get_s("w".toCharArray()), trim_vec(conc.p[2], 0))),
                                0L, null);
                        break;

/*	 case CO_STRI:
		conc_gr(1L,ptimes(trim_a(conc.p[0],conc.p[1],conc.p[2],conc.p[3]),
								trim_g(conc.p[4],conc.p[5],conc.p[6],conc.p[7])),
				  1L,ptimes(trim_g(conc.p[0],conc.p[1],conc.p[2],conc.p[3]),
								trim_a(conc.p[4],conc.p[5],conc.p[6],conc.p[7])));
		break;
	 case CO_CTRI:
		conc_gr(1L,ptimes(trim_a(conc.p[0],conc.p[1],conc.p[2],conc.p[3]),
								trim_g(conc.p[4],conc.p[5],conc.p[6],conc.p[7])),
				  1L,ptimes(trim_g(conc.p[0],conc.p[1],conc.p[2],conc.p[3]),
								trim_a(conc.p[4],conc.p[5],conc.p[6],conc.p[7])));
		break;
*/
                  case -21:  /* eq-ratio */
                        conc_gr(1L, trim_r(conc.p[0], conc.p[1], conc.p[2], conc.p[3]),
                                1L, trim_r(conc.p[4], conc.p[5], conc.p[6], conc.p[7]));
                        break;
                  case CO_EQ: /* constants8 */
                        if (npoly(conc_p1) && npoly(conc_p2))
                              conc_gr(num_int(conc_p1.c), null, num_int(conc_p2.c), null);
                        if (npoly(conc_p1))
                              conc_gr(num_int(conc_p1.c), null, 1L, conc_p2);
                        else if (npoly(conc_p2))
                              conc_gr(1L, conc_p1, num_int(conc_p2.c), null);
                        else
                              conc_gr(1L, conc_p1, 1L, conc_p2);
                        break;

                  default:
                        {
                              gerror(Cm.s2302 + conc.pred);
                        }
            }
            if (print_geo)
            {
                  gprint("pconc:\r\n");
                  print_gr(last_pr, (char) 0);
            }
      }


      static int f_pt1, f_pt2;

      void fd_mpts(int pt)
      {
            int i, m;
            l_line ln1 = null;

            m = 100;
            l_line ln = all_ln.nx;
            while (ln != null)
            {
                  if (on_ln(pt, ln) && ln.no > 1)
                  {
                        for (i = 0; i <= ln.no; i++)
                              if (ln.pt[i] != pt && ln.pt[i] < m)
                              {
                                    ln1 = ln;
                                    m = ln.pt[i];
                              }
                  }
                  ln = ln.nx;
            }
            f_pt1 = m;
            m = 100;
            for (i = 0; i <= ln1.no; i++)
                  if (ln1.pt[i] != pt && ln1.pt[i] != f_pt1 && ln1.pt[i] < m) m = ln1.pt[i];
            f_pt2 = m;
      }

      void conc_coll()
      {
            int p, p1, p2, p3;
            l_line ln;
            p1 = conc.p[0];
            p2 = conc.p[1];
            p3 = conc.p[2];
            if (p1 > p2)
            {
                  p = p1;
                  p1 = p2;
                  p2 = p;
            }
            if (p1 > p3)
            {
                  p = p1;
                  p1 = p3;
                  p3 = p;
            }
            if (p2 > p3)
            {
                  p = p2;
                  p2 = p3;
                  p3 = p;
            }

            ln = fd_ln1(p3);
            if (ln == null)
            {
                  ln = fd_ln1(p2);
                  if (ln != null)
                  {
                        p = p2;
                        p2 = p3;
                        p3 = p;
                        ;
                  } else
                  {
                        ln = fd_ln1(p1);
                        if (ln != null)
                        {
                              p = p1;
                              p1 = p3;
                              p3 = p;
                              ;
                        } else
                        {
                              conc_gr(1L, trim_a(p1, p2, p3, p3), 0L, null);
                              return;
                        }
                  }
            }
            //l1:
            fd_mpts(p3);
            gprint("conc_coll:  " + p1 + " " + p2 + " " + p3 + " " + f_pt1 + " " + f_pt2);
            pts_pno++;
            String s1 = ("I_{" + ANAME(p3) + "}");
            allpts[pts_pno] = new Pro_point(C_I_LL,s1,f_pt1,f_pt2,p1,p2,0,0,0,0);
//            allpts[pts_pno].name = s1;
//            allpts[pts_pno].type = C_I_LL;
//            allpts[pts_pno].ps[0] = f_pt1;
//            allpts[pts_pno].ps[1] = f_pt2;
//            allpts[pts_pno].ps[2] = p1;
//            allpts[pts_pno].ps[3] = p2;
            add_line(0, pts_pno, APTS(pts_pno, 0), APTS(pts_pno, 1));
            add_line(0, pts_pno, APTS(pts_pno, 2), APTS(pts_pno, 3));
            conc_gr(1L, trim_r(f_pt1, p3, f_pt2, p3), 1L, trim_r(f_pt1, pts_pno, f_pt2, pts_pno));


            gprint("\r\n***************************************\r\n");
            gprint(Cm.s2303 + " " + ANAME(pts_pno) + " " + Cm.s2304);
            //      do_cons(6, pts_pno);  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            gprint("\r\n\r\n");
            gprint(Cm.s2305);
            gprint(ANAME(f_pt1) + " " + ANAME(p3) + " " + ANAME(f_pt2) + " " + ANAME(p3) +
                    ANAME(f_pt1) + " " + ANAME(pts_pno) + " " + ANAME(f_pt2) + " " + ANAME(pts_pno) + "\n" +
                    "\r\n\r\n");

      }

      void conc_eqproduct()
      {
            int p1, p2, p3, p4, p5, p6, p7, p8;
            p1 = conc.p[0];
            p2 = conc.p[1];
            p3 = conc.p[6];
            p4 = conc.p[7];
            p5 = conc.p[2];
            p6 = conc.p[3];
            p7 = conc.p[4];
            p8 = conc.p[5];
            if (xpara(p1, p2, p5, p6) && xpara(p3, p4, p7, p8))
                  conc_gr(1L, trim_r(p1, p2, p5, p6), 1L, trim_r(p7, p8, p3, p4));
            else if (xpara(p1, p2, p7, p8) && xpara(p3, p4, p5, p6))
                  conc_gr(1L, trim_r(p1, p2, p7, p8), 1L, trim_r(p5, p6, p3, p4));
            else if (xpara(p1, p2, p3, p4) && xpara(p5, p6, p7, p8))
                  conc_gr(1L, trim_g(p1, p3, p2, p4), 1L, trim_g(p5, p7, p6, p8));
            else if (xcoll4(p1, p2, p3, p4))
                  conc_gr(1L, ptimes(trim_g(p1, p3, p2, p4), trim_g(p1, p3, p2, p4)),
                          1L, ptimes(trim_g(p5, p6, p6, p5), trim_g(p7, p8, p8, p7)));
            else
                  conc_gr(1L, ptimes(trim_g(p1, p2, p2, p1), trim_g(p3, p4, p4, p3)),
                          1L, ptimes(trim_g(p5, p6, p6, p5), trim_g(p7, p8, p8, p7)));
      }

/*
void pconc_11(p0,p1,p2,p3)
int p0,p1,p2,p3;
{ gr_term *gr;
  xterm *po1,*po2,*po3;
  dterm *ps1, *ps2;
  po1=pminus(trim_g(p2,p3,p3,p2),trim_g(p0,p2,p2,p0));
  po2=trim_g(p0,p1,p1,p0);
  po3=pplus3(ptimes(get_n(2L),trim_g(p0,p2,p2,p0)),
		  ptimes(get_n(2L),trim_g(p2,p3,p3,p2)),
		  ptimes(get_n(-1L),trim_g(p0,p1,p1,p0)));
  ps1 = get_dt(2,po1,null);
  ps2 = get_dt(1,po2,null);
  ps2 = get_dt(1,po3,ps2);

  gr = mk_gr(mk_num(1L),ps1,mk_num(1L),ps2,0,null);
  gr.c = 0;
  last_pr.nx = gr;
  last_pr = gr;
} */

      void pconc_11(int p0, int p1, int p2, int p3)
      {
            gr_term gr;
            xterm po1, po2, po3;
            dterm ps1, ps2;
            po1 = pminus(pplus(trim_g(p0, p1, p1, p0), trim_g(p2, p3, p3, p2)),
                    trim_g(p0, p2, p2, p0));
            po2 = trim_g(p0, p1, p1, p0);
            po3 = trim_g(p2, p3, p3, p2);
            ps1 = get_dt(2, po1, null);
            ps2 = get_dt(1, po2, null);
            ps2 = get_dt(1, po3, ps2);

            gr = mk_gr(mk_num(1L), ps1, mk_num(4L), ps2, 0, null);
            gr.c = 0;
            last_pr.nx = gr;
            last_pr = gr;
      }



/* eliminations */

      el_term all_elim = new el_term();
      dterm ds_set = new dterm();
      dterm last_ds;

      el_term mk_elim(var v, xterm p1, xterm p2)
      {
            el_term v1 = new el_term();//(el_term  )calloc(1,sizeof(el_term));
            v1.v = v;
            v1.p1 = p1;
            v1.p2 = p2;
            v1.nx = all_elim.nx;
            all_elim.nx = v1;
            return (v1);
      }


      void pe_gr(int ptn)
      {
            gr_term gr, gr0;
            dterm ps, ps1, ps2, qs1, qs2;
            gr = last_pr;

            if (print_geo && ptn > 0)
            {
                  gprint("\r\npe_gr: " + ptn + "(" + pt_name(ptn) + "\r\n");
            }
            all_elim.nx = null;
            ps1 = cp_pols(gr.ps1);
            if (test_pt(ptn))
            {
                  ps1 = get_dt(1, ps_times(ps1), null);
            }
            ps = ps1;
            ds_set.nx = null;
            last_ds = ds_set;
            while (ps != null)
            {
                  ps.p = pe_p(ps.p, ptn, ps.deg);
                  ps = ps.nx;
                  if (qerror)
                  {
                        return;
                  }
            }
            qs1 = ds_set.nx;
            if (qerror) return;

            ps2 = cp_pols(gr.ps2);
            if (test_pt(ptn))
            {
                  ps2 = get_dt(1, ps_times(ps2), null);
            }
            ps = ps2;
            ds_set.nx = null;
            last_ds = ds_set;
            while (ps != null)
            {
                  ps.p = pe_p(ps.p, ptn, ps.deg);
                  ps = ps.nx;
                  if (qerror)
                  {
                        return;
                  }
            }
            qs2 = ds_set.nx;

            if (all_elim.nx != null)
            {
                  gr0 = mk_gr(gr.c1, ps_append(ps1, qs2),
                          gr.c2, ps_append(ps2, qs1), ptn, null);
                  gr0.el = all_elim.nx;
                  last_pr.nx = gr0;
                  last_pr = gr0;
                  gr0 = simp_gr(gr0);
                  if (gr0 != null)
                  {
                        if (num_zop(gr0.c1) && num_zop(gr0.c2))
                        {
                              last_pr = gr0;
                        } else
                        {
                              last_pr.nx = gr0;
                              last_pr = gr0;
                        }
                        last_pr.nx = null;
                  }
            }
      }

      boolean test_pt(int ptn)
      {
            if (ptn > 0) return (ATYPE(ptn) == -1);
            return (ptn == -10 || ptn == -23);
      }

      xterm pe_p(xterm p, int c, int d)
      {
            var v1;
            el_term el1;
            xterm p1;

//  if (print_geo) { wsprintf(txt,"\r\n\r\npe_p: (%d)",c);  gprint(txt); }

            if (c < 0) return (pe_pv(p, c, d));
            if (ATYPE(c) == C_CONSTANT) return (pe_pv(p, c, d));

            p1 = p;
            l1:                                                                ///////////////////////???????????
             //if (c==5)  { gprint("\r\npe_p:"); pprint(p1); }
            while (true)
            {
                  if (p1.var == null)
                  {
                        return (p1);
                  }
                  v1 = p1.var;
                  if (v1.nm <= 0)
                  {
                        return (p1);
                  } else if (lpt(v1) < c)
                  {
                        return (p1);
                  } else if (lpt(v1) == c)
                  {
                        if (elim_varp(v1, c))
                        {
                              el1 = all_elim.nx;
                              while ((el1 != null) && (el1.v != v1)) el1 = el1.nx;
                              if (el1 == null) el1 = pe_v_c(v1, c);
                              if (qerror)
                              {
                                    return (null);
                              }
                              if (el1 == null)
                              {
                                    gerror(Cm.s2314);
                                    return (null);
                              }
                              p1 = eprem(p1, el1);
                              if (init_deg > 0)
                              {
                                    last_ds.nx = get_dt(d * init_deg, cp_poly(el1.p2), null);
                                    last_ds = last_ds.nx;
                              }
                              if (max_termp)
                              {
                                    int tem = plength(p1);
                                    if (tem > max_term) max_term = tem;
                              }
                              //goto l1;
                        } else
                              return (p1);
                  } else
                  {
                        gerror("pe_p: ERRRRROR");
                  }
            }
            // return (null);  //????
      }

      boolean elim_varp(var v, int pt)
      {
            if (ATYPE(pt) == 11)
            {
                  if (v.nm == 3)
                  {
                        if (c_pt(v.pt[0], pt) && c_pt(v.pt[1], pt) &&
                                c_pt(v.pt[2], pt) && c_pt(v.pt[3], pt))
                              return (true);
                        else
                              return (false);
                  } else
                        return (false);
            }
            return (true);
      }

      boolean c_pt(int pt, int c)
      {
            return ((pt == c) || (pt == APTS(c, 0)) || (pt == APTS(c, 1)));
      }


      xterm eprem(xterm p, el_term e)
      {
            xterm p1, p2, p3;
            if (e == null) return p;
            p2 = get_n(1L);
            if (e.p1 == null)
            {
                  p1 = prem_var(p, e.p2, e.v);
                  p3 = init_v(cp_poly(e.p2), e.v);
                  if (eq_poly(p3, p2))
                  {
                        init_deg = 0;
                  }
                  put_p(p3);
            } else
            {
                  p1 = get_m(e.v);
                  p1 = ptimes(p1, cp_poly(e.p2));
                  p1 = pminus(p1, cp_poly(e.p1));
                  p1 = prem_var(p, p1, e.v);
                  if (eq_poly(e.p2, p2))
                  {
                        init_deg = 0;
                  }
            }
            put_x(p2);
            return (p1);
      }

      el_term pe_v_c(var v, int ptn)
      {
            int etype = 0;

            int[] p = new int[9];
            el_term e1 = null;
            int j;
            for (j = 0; j <= 5; j++) p[j + 1] = APTS(ptn, j);

            switch (ATYPE(ptn))
            {
                  case C_POINT:  /*free point*/
                        e1 = pe_v_fpt(v, ptn);
                        break;
                  case C_MIDPOINT:  /* midpoint */
                        e1 = pe_v_pratio(v, ptn, p[1], p[1], p[2], get_n(1L), get_n(2L));
                        break;
                  case C_SYM:  /* sym */
                        e1 = pe_v_pratio(v, ptn, p[1], p[1], p[2], get_n(1L), get_n(1L));
                        break;
                  case C_LRATIO:
                        e1 = pe_v_pratio(v, ptn, p[1], p[1], p[2], cp_poly(P1(ptn)), cp_poly(P2(ptn)));
                        break;
                  case 100://C_MRATIO:
                        e1 = pe_v_pratio(v, ptn, p[1], p[1], p[2], cp_poly(P1(ptn)),
                                c_pplus(P1(ptn), P2(ptn)));
                        break;
                  case C_PRATIO:
                        e1 = pe_v_pratio(v, ptn, p[1], p[2], p[3], cp_poly(P1(ptn)), cp_poly(P2(ptn)));
                        break;
                  case 71:  /* tratio */
                        e1 = pe_v_tratio(v, ptn, p[1], p[2], p[3], cp_poly(P1(ptn)), cp_poly(P2(ptn)));
                        break;

                  case C_O_L: /* on-line */
                        e1 = pe_v_pratio(v, ptn, p[1], p[1], p[2], get_m(mk_var(-1, p[1], ptn, p[1], p[2])), get_n(1L));
                        break;
                  case C_O_T:  /*on-tline*/
                        String s = ("t_{" + ANAME(ptn) + "}");
                        e1 = pe_v_tratio(v, ptn, p[1], p[2], p[3], get_s(s.toCharArray()), get_n(1L));
                        break;
                  case C_O_P: /* on-pline */
                        e1 = pe_v_pratio(v, ptn, p[1], p[2], p[3], get_m(mk_var(-1, p[1], ptn, p[3], p[4])), get_n(1L));
                        break;
//	 case C_O_C: /* on-circle */
//		break;
                  case C_O_A: /* on-aline */
                        e1 = pe_v_tratio(v, ptn, p[2], p[2], p[1],
                                ptimes(get_n(-4L), trim_a(p[3], p[4], p[4], p[5])),
                                trim_g(p[3], p[4], p[4], p[5]));
                        break;
                  case C_I_LL:  /* intersection-ll */
                        e1 = pe_v_ipp(v, ptn, p[1], p[1], p[2], p[3], p[3], p[4]);
/*
		switch(v.nm)
	{ case 1:
		 e1 = pe_r_ill(v,v.pt[1],v.pt[0],v.pt[3],v.pt[2],ptn,p[1],p[2],p[3],p[4]);
		 break;
	  case 2:
		 e1 = pe_a_ill(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3],p[4]);
		 break;
	  case 3:
		 e1 = pe_g_ill(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3],p[4]);
		 break;
	  case 4:
		 e1 = pe_vec_ill(v,v.pt[0],v.pt[1],ptn,p[1],p[2],p[3],p[4]);
		 break;
	  }
*/
                        break;
                  case C_FOOT: /* foot */
                        e1 = pe_v_ipt(v, ptn, p[2], p[2], p[3], p[1], p[2], p[3]);
/*      switch(v.nm)
	{ case 1:
		 e1 = pe_r_foot(v,v.pt[1],v.pt[0],v.pt[3],v.pt[2],ptn,p[1],p[2],p[3]);
		 break;
	  case 2:
		 e1 = pe_a_foot(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3]);
		 break;
	  case 3:
		 e1 = pe_g_foot(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3]);
		 break;
	  }
*/
                        break;
                  case C_I_TT:  /* intersection-tt */
                        switch (v.nm)
                        {
                              case 1:
                                    /* e1 = pe_r_itt(v,v.pt[1],v.pt[0],v.pt[3],v.pt[2],ptn,p[1],p[2],p[3],p[4],p[5],p[6]); */
                                    //sprintf(txt, "%s %s %s", ANAME(ptn), PDSTR(2307), PDSTR(2308));
                                    gerror(ANAME(ptn) + Cm.s2307 + " " + Cm.s2308);
                                    e1 = null;
                                    break;
                              case 2:
                                    e1 = pe_a_itt(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3], p[4], p[5], p[6]);
                                    break;
                              case 3:
                                    e1 = pe_g_itt(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3], p[4], p[5], p[6]);
                                    break;
                              default:
                                    gerror("pe_v_c: itt");
                        }
                        break;
                  case C_I_LT:  /* intersection-lt */
                        e1 = pe_v_ipt(v, ptn, p[1], p[1], p[2], p[3], p[4], p[5]);
                        break;
                  case C_I_LP:  /* intersection-lp */
                        e1 = pe_v_ipp(v, ptn, p[1], p[1], p[2], p[3], p[4], p[5]);
/*      switch(v.nm)
	{ case 1:
		 e1 = pe_r_ilp(v,v.pt[1],v.pt[0],v.pt[3],v.pt[2],ptn,p[1],p[2],p[3],p[4],p[5]);
		 break;
	  case 2:
		 e1 = pe_a_ilp(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3],p[4],p[5]);
		 break;
	  case 3:
		 e1 = pe_g_ilp(v,v.pt[0],v.pt[1],v.pt[2],v.pt[3],ptn,p[1],p[2],p[3],p[4],p[5]);
		 break;
	  }
*/
                        break;
                  case C_I_PP:  /* intersection-pp */
                        e1 = pe_v_ipp(v, ptn, p[1], p[2], p[3], p[4], p[5], p[6]);
                        break;
                  case C_I_PT:  /* intersection-pt */
                        e1 = pe_v_ipt(v, ptn, p[1], p[2], p[3], p[4], p[5], p[6]);
                        break;

/* intersection of line and circle */
                  case C_I_LC:  /* intersection-lc */
                        if (p[2] == p[4])
                              e1 = pe_v_ipc(v, ptn, p[2], p[2], p[1], p[3], p[4]);
                        else if (p[1] == p[4])
                              e1 = pe_v_ipc(v, ptn, p[1], p[1], p[2], p[3], p[4]);
                        //else goto err3;
                        else
                              etype = 3;
                        break;
                  case C_I_PC:  /* intersection-pc */
                        if (p[1] == p[5])
                              e1 = pe_v_ipc(v, ptn, p[1], p[2], p[3], p[4], p[5]);
                        //else goto err3;
                        else
                              etype = 3;
                        break;
                  case C_I_CC:  /* intersection-cc */
                        if (p[2] != p[4])
                              etype = 3;
                        else
                              e1 = pe_v_tratio(v, ptn, p[2], p[1], p[3],
                                      ptimes(get_n(-8L), trim_a(p[2], p[1], p[3], p[3])),
                                      trim_g(p[1], p[3], p[3], p[1]));
                        break;
                  case C_REF:  /* reflection */
                        e1 = pe_v_tratio(v, ptn, p[1], p[2], p[3],
                                ptimes(get_n(-8L), trim_a(p[1], p[2], p[3], p[3])),
                                trim_g(p[2], p[3], p[3], p[2]));
                        break;
                  case C_CENT:  /* CENTROID */
                        e1 = pe_v_cent(v, ptn, p[1], p[2], p[3]);
                        break;
                  case C_ORTH:  /* orthocenter */
                        switch (v.nm)
                        {
                              case 1:
/*		 e1 = pe_r_orth(v,v.pt[1],v.pt[0],v.pt[3],v.pt[2],ptn,p[1],p[2],p[3]); */
                                    //goto err3;
                                    etype = 3;
                                    break;
                              case 2:
                                    e1 = pe_a_orth(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3]);
                                    break;
                              case 3:
                                    e1 = pe_g_orth(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3]);
                                    break;
                              case 4:
                                    e1 = orth_md(v, ptn, p[1], p[2], p[3]);
                                    break;
                        }
                        break;
                  case C_CIRCUM:  /* circumcenter */
                        switch (v.nm)
                        {
                              case 1:
                                    e1 = pe_r_circum(v, v.pt[1], v.pt[0], v.pt[3], v.pt[2], ptn, p[1], p[2], p[3]);
                                    break;
                              case 2:
                                    e1 = pe_a_circum(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3]);
                                    break;
                              case 3:
                                    e1 = pe_g_circum(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[1], p[2], p[3]);
                                    break;
                              case 4:
                                    e1 = circum_md(v, ptn, p[1], p[2], p[3]);
                                    break;
                              default:
                                    gerror("pe_v_c111");
                        }
                        break;
                  case C_ICENT1:  /* incenter */
                        gerror(ANAME(ptn) + " " + Cm.s2311 + " " + Cm.s2312);
                        e1 = null;
                        return null;

                  case C_ICENT:  /* incenter */
                        switch (v.nm)
                        {
                              case 1:
                                    e1 = pe_r_incent(v, v.pt[1], v.pt[0], v.pt[3], v.pt[2], ptn, p[3], p[1], p[2]);
                                    break;
                              case 2:
                                    e1 = pe_a_incent(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[3], p[1], p[2]);
                                    break;
                              case 3:
                                    e1 = pe_g_incent(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3], ptn, p[3], p[1], p[2]);
                                    break;
                              case 4:
                                    e1 = incent_md(v, ptn, p[3], p[1], p[2]);
                                    break;
                              default:
                                    gerror("pe_v_c111");
                        }
                        break;
                  case C_INVERSION:  /* inversion */
                        if (xcoll(p[1], p[2], p[3]))
                              e1 = pe_v_pratio(v, ptn, p[2], p[2], p[3], trim_r(p[2], p[3], p[2], p[1]), get_n(1L));
                        else
                              e1 = pe_v_pratio(v, ptn, p[2], p[2], p[1],
                                      trim_g(p[2], p[3], p[3], p[2]), trim_g(p[2], p[1], p[1], p[2]));
                        break;
                  case C_PSQUARE:  /* psquare */
                        e1 = pe_v_square(v, ptn, p[1], p[2], 1);
                        break;
                  case C_NSQUARE:  /* nsquare */
                        e1 = pe_v_square(v, ptn, p[1], p[2], -1);
                        break;
                  case C_PETRIANGLE:  /* pe-triangle */
                        if (v.nm != 4)
                        {
                              gerror(Cm.s2313);
                              return null;
                        }
                        e1 = pe_v_ptri(v, ptn, p[1], p[2]);
                        break;

//	 case C_NETRIANGLE:  /* ne-triangle */
//		e1 = pe_v_ntri(v,ptn,p[1],p[2]);
//		break;
/*
	 case -71:  // harmonic
		e1 = pe_v_pratio(v,ptn,p[2],p[2],p[3],
				 trim_r(p[2],p[1],p[3],p[1]),
				 pplus(trim_r(p[2],p[1],p[3],p[1]),get_n(1L)));
		break;
	 case -76:  // ps-triangle  similar
		e1 = pe_v_sim(v,ptn,p[1],p[2],p[3],p[4],p[5]);

*/
                  default:
                        gerror(ANAME(ptn) + " " + Cm.s2307 + " " + Cm.s2308);
                        e1 = null;
            }
            if (etype == 0)
                  return (e1);

            err3:
            gerror(ANAME(ptn) + "  " + Cm.s2310);
            e1 = null;
            return null;
      }


      xterm pe_pv(xterm p, int c, int d)
      {
            var v1;
            el_term e1;
            xterm p1, p2, p3;

            if (c > 0)
                  p1 = get_xt(P1(c).var, null);
            else
                  p1 = vars_in_p(p);
            {
                  //sprintf(txt,"\r\npe_pv: %d %d\r\n",c,d); gprint(txt); pprint(p);
            }
            while (p1 != null)
            {
                  v1 = p1.var;
                  if (test_pv(v1, c))
                  {
                        e1 = all_elim.nx;
                        while ((e1 != null) && (e1.v != v1)) e1 = e1.nx;
                        if (e1 == null) e1 = pe_v_nc(v1, c);
                        if (e1 == null)
                        {
                              p2 = p1;
                              p1 = p1.p;
                              put_x(p2);
                              continue;
                        }
                        p = eprem(p, e1);
                        if (init_deg > 0)
                        {
                              if (e1.p1 == null)
                                    p3 = init_v(cp_poly(e1.p2), e1.v);
                              else
                                    p3 = cp_poly(e1.p2);
                              {
                                    last_ds.nx = get_dt(d * init_deg, p3, null);
                                    last_ds = last_ds.nx;
                              }
                        }
                        if (max_termp)
                        {
                              int tem = plength(p);
                              if (tem > max_term) max_term = tem;
                        }
                  }
                  p2 = p1;
                  p1 = p1.p;
                  put_x(p2);
            }
            return (p);
      }

      boolean test_pv(var v, int c)
      {
            switch (c)
            {
                  case -10:
                        return (v.nm == 2);
                  case -11:
                        return (v.nm == 3 && !(v.pt[1] == v.pt[2] && v.pt[0] == v.pt[3]));
                  case -12:
                        return (v.nm > 0);
                  case -13:
                        return (v.nm == 3);
                  case -20:
                        return (v.nm == 2 || v.nm == 3);
                  case -21:
                        return (v.nm == 5);
                  case -22:
                        return (v.nm == 6 || v.nm == 7);
                  case -23:
                        return (v.nm == 17);
                  case -30:
                        return (v.nm == 4 && v.pt[1] != 0);

            }
            return (true);
      }

      el_term pe_v_nc(var v, int ptn)
      {
            el_term e1 = null;

/*  if (print_geo) { printf("\r\npe_v_nc in \r\n");print_var(v); printf("\r\n"); } */
            if (ptn > 0) return (mk_elim(v, null, P1(ptn)));
            switch (ptn)
            {
                  case -10: /* herron */
                        e1 = mk_elim(v, null,
                                pplus(ptimes(get_n(16L), get_v(v, 2, get_n(1L))),
                                        pplus(ptimes(trim_g(v.pt[0], v.pt[2], v.pt[2], v.pt[0]),
                                                trim_g(v.pt[1], v.pt[1], v.pt[3], v.pt[3])),
                                                ptimes(trim_g(v.pt[0], v.pt[1], v.pt[2], v.pt[3]),
                                                        trim_g(v.pt[0], v.pt[1], v.pt[2], v.pt[3])))));
                        break;
                  case -11: /* d2s */
                        e1 = mk_elim(v, pminus(pplus(trim_g(v.pt[0], v.pt[1], v.pt[1], v.pt[0]),
                                trim_g(v.pt[2], v.pt[3], v.pt[3], v.pt[2])),
                                pplus(trim_g(v.pt[1], v.pt[2], v.pt[2], v.pt[1]),
                                        trim_g(v.pt[3], v.pt[0], v.pt[0], v.pt[3]))),
                                get_n(2L));
                        break;
                  case -12: /* o-xy */
                        switch (v.nm)
                        {
                              case 2:
                                    e1 = mk_elim(v, area_q(v.pt[0], v.pt[1], v.pt[2], v.pt[3]), get_n(2L));
                                    break;
                              case 3:
                                    e1 = mk_elim(v, py_q(v.pt[0], v.pt[1], v.pt[2], v.pt[3]), get_n(1L));
                                    break;
                              default:
                                    gerror("pe_v_nc1");
                        }
                        break;
                  case -13: /* for the circumcenter */
                        {
                              if (xcir3(v.pt[0], v.pt[1], v.pt[2], v.pt[3]))
                                    e1 = mk_elim(v, pminus(trim_g(v.pt[2], v.pt[3], v.pt[3], v.pt[2]),
                                            trim_g(v.pt[1], v.pt[2], v.pt[2], v.pt[1])),
                                            get_n(2L));
                              else if (xcir3(v.pt[1], v.pt[0], v.pt[2], v.pt[3]))
                                    e1 = mk_elim(v, pminus(trim_g(v.pt[2], v.pt[3], v.pt[3], v.pt[2]),
                                            trim_g(v.pt[0], v.pt[3], v.pt[3], v.pt[0])), get_n(2L));
                              else if (xcir3(v.pt[2], v.pt[0], v.pt[1], v.pt[3]))
                                    e1 = mk_elim(v, pminus(trim_g(v.pt[0], v.pt[1], v.pt[1], v.pt[0]),
                                            trim_g(v.pt[0], v.pt[3], v.pt[3], v.pt[0])), get_n(2L));
                              else if (xcir3(v.pt[3], v.pt[0], v.pt[1], v.pt[2]))
                                    e1 = mk_elim(v, pminus(trim_g(v.pt[0], v.pt[1], v.pt[1], v.pt[0]),
                                            trim_g(v.pt[1], v.pt[2], v.pt[2], v.pt[1])), get_n(2L));

                        }
                        break;
                  case -20: /* circle */
                        e1 = pe_circle(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3]);
                        break;
                  case -21: /* chord */
                        e1 = mk_elim(v, ptimes(get_m(mk_svar("\\d".toCharArray())), trim_s(v.pt[0], v.pt[1])), get_n(1L));
                        break;
                  case -22: /* sin-cos */
                        e1 = pe_sc(v, v.pt[0], v.pt[1]);
                        break;
                  case -23: /* sin^2 + cos^2=1 */
                        e1 = mk_elim(v, null, pplus3(get_n(-1L), ppower(sc(v.pt[0]), 2), ppower(cc(v.pt[0]), 2)));
                        break;
                  case -30: /* vector */
                        e1 = mk_elim(v, pminus(trim_vec(v.pt[0], 0), trim_vec(v.pt[1], 0)), get_n(1L));
                        break;
                  default:
                        gerror("pe_v_nc2");
            }
            if (print_geo)
            {
                  gprint("\r\npe_v_nc = \r\n");
                  print_elim(e1, (char) 0);
                  gprint("\r\n");
            }
            return (e1);
      }

      xterm geval(var var, int y, int p)
      {
            int[] pt = new int[9];

            for (int i = 0; i < 4; i++)
                  if (var.pt[i] == y)
                        pt[i] = p;
                  else
                        pt[i] = var.pt[i];
            switch (var.nm)
            {
                  case 1:
                  case -1:
                        return (trim_r(pt[0], pt[1], pt[2], pt[3]));
                  case 2:
                  case -2:
                        return (trim_a(pt[0], pt[1], pt[2], pt[3]));
                  case 3:
                  case -3:
                        return (trim_g(pt[0], pt[1], pt[2], pt[3]));
                  case 4:
                  case -4:
                        return (trim_vec(pt[0], pt[1]));
                  default:
                        exit(1);
            }
            return (null);
      }


      xterm trim_r(int p1, int p2, int p3, int p4)
      {
            int p;
            char sn = 1;
/*  if (print_geo) printf("trim_r %d %d %d %d\r\n",p1,p2,p3,p4); */
            if (p1 == p2) return (pzero());
            if (p3 == p4) gerror("rtrim: denominator of ratio is zero.~%");
            if ((p1 == p3) && (p2 == p4)) return (get_n(1L));
            if ((p2 == p3) && (p1 == p4)) return (get_n(-1L));
            if (p1 < p2)
            {
                  sn *= -1;
                  p = p1;
                  p1 = p2;
                  p2 = p;
            }
            if (p3 < p4)
            {
                  sn *= -1;
                  p = p3;
                  p3 = p4;
                  p4 = p;
            }
            if (sn == 1)
                  return (get_m(mk_var(1, p1, p2, p3, p4)));
            else
                  return (neg_poly(get_m(mk_var(1, p1, p2, p3, p4))));

      }

      xterm trim_a(int p1, int p2, int p3, int p4)
//int p1,p2,p3,p4;
      {
            int p;
            char sn = 1;
/*  if (print_geo) printf("trim_a1 %d %d %d %d\r\n",p1,p2,p3,p4); */
            if (xpara(p1, p3, p2, p4)) return (pzero());
            if (xcoll(p1, p2, p3))
            {
                  p2 = p1;
            } else if (xcoll(p4, p2, p3))
            {
                  p3 = p4;
            } else if (xcoll(p4, p1, p3))
            {
                  p4 = p3;
            } else if (xcoll(p4, p2, p1))
            {
                  p1 = p4;
            }
/*  if (print_geo)  printf("trim_a2 %d %d %d %d\r\n",p1,p2,p3,p4); */
            if (p1 < p3)
            {
                  sn *= -1;
                  p = p1;
                  p1 = p3;
                  p3 = p;
            }
            if (p2 < p4)
            {
                  sn *= -1;
                  p = p2;
                  p2 = p4;
                  p4 = p;
            }
            if (p1 < p2)
            {
                  sn *= -1;
                  p = p1;
                  p1 = p2;
                  p2 = p;
                  p = p3;
                  p3 = p4;
                  p4 = p;
            } else if ((p1 == p2) && (p3 < p4))
            {
                  sn *= -1;
                  p = p3;
                  p3 = p4;
                  p4 = p;
            }

            if (p1 == p2)
            {
                  p2 = p3;
                  p3 = p4;
            } else if (p2 == p3)
            {
                  p3 = p4;
            }

            if (sn == 1)
                  return (get_m(mk_var(2, p1, p2, p3, p4)));
            else
                  return (neg_poly(get_m(mk_var(2, p1, p2, p3, p4))));
      }

      xterm trim_g(int p1, int p2, int p3, int p4)
      {
            int p;
            char sn = 1;
/*  if (print_geo)  printf("\r\ntrim_g %d %d %d %d\r\n",p1,p2,p3,p4); */
            if (xperp(p1, p3, p2, p4)) return (get_n(0L));
            if (p1 < p3)
            {
                  sn *= -1;
                  p = p1;
                  p1 = p3;
                  p3 = p;
            }
            if (p2 < p4)
            {
                  sn *= -1;
                  p = p2;
                  p2 = p4;
                  p4 = p;
            }
            if (p1 < p2)
            {
                  p = p1;
                  p1 = p2;
                  p2 = p;
                  p = p3;
                  p3 = p4;
                  p4 = p;
            } else if ((p1 == p2) && (p3 < p4))
            {
                  p = p3;
                  p3 = p4;
                  p4 = p;
            }

            if (p1 == p2)
            {
                  sn *= -1;
                  p1 = p3;
                  p3 = p2;
            } else if (p3 == p4)
            {
                  sn *= -1;
                  p4 = p2;
                  p2 = p3;
            }
            //The largest index is either p1 or p2
            if (sn == 1)
                  return (get_m(mk_var(3, p1, p2, p3, p4)));
            else
                  return (neg_poly(get_m(mk_var(3, p1, p2, p3, p4))));
      }

      xterm trim_f(int p1, int p2, int p3, int p4)
      {
            int p;
            char sn = 1;
            if (print_geo)
            {
                  gprint("trim_a1 " + p1 + p2 + p3 + p4 + "\r\n");
            }
            if (xcoll4(p1, p2, p3, p4)) return (pzero());
            if (p1 < p2)
            {
                  p = p1;
                  p1 = p2;
                  p2 = p;
            }
            if (p3 < p4)
            {
                  p = p3;
                  p3 = p4;
                  p4 = p;
            }
            if (p1 < p3)
            {
                  sn *= -1;
                  p = p1;
                  p1 = p3;
                  p3 = p;
                  p = p2;
                  p2 = p4;
                  p4 = p;
            } else if ((p1 == p3) && (p2 < p4))
            {
                  sn *= -1;
                  p = p2;
                  p2 = p4;
                  p4 = p;
            }
            if (sn == 1)
                  return (get_m(mk_var(10, p1, p2, p3, p4)));
            else
                  return (neg_poly(get_m(mk_var(10, p1, p2, p3, p4))));
      }

      xterm trim_fl(l_line l1, l_line l2)
      {
            if (l1 == l2) return (pzero());
            return (trim_f(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1]));
      }

      xterm trim_vec(int p1, int p2)
      {
            if (p2 == 0)
            {
                  return (get_m(mk_var(4, p1, 0, 0, 0)));
            }
            if (p1 == p2) return (pzero());
            if (p1 < p2) return (neg_poly(get_m(mk_var(4, p2, p1, 0, 0))));
            return (get_m(mk_var(4, p1, p2, 0, 0)));
      }

      xterm trim_l(int p1, int p2)
      {
            if (p1 == p2) return (pzero());
            if (p1 < p2) return (neg_poly(get_m(mk_var(5, p2, p1, 0, 0))));
            return (get_m(mk_var(5, p1, p2, 0, 0)));
      }

      xterm trim_s(int p1, int p2)
      {
            if (p1 == p2) return (pzero());
            if (p1 < p2) return (neg_poly(get_m(mk_var(6, p2, p1, 0, 0))));
            return (get_m(mk_var(6, p1, p2, 0, 0)));
      }

      xterm trim_c(int p1, int p2)
      {
            if (p1 == p2) return (get_n(1L));
            if (p1 < p2) return (neg_poly(get_m(mk_var(7, p2, p1, 0, 0))));
            return (get_m(mk_var(7, p1, p2, 0, 0)));
      }


}
