package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-4-20
 * Time: 14:41:03
 * To change this template use File | Settings | File Templates.
 */
public class elim extends gr
{

      el_term all_elim;
      dterm ds_set, last_ds;

      el_term mk_elim(var v, xterm p1, xterm p2)
      {
            el_term v1 = new el_term();
            v1.v = v;
            v1.p1 = p1;
            v1.p2 = p2;
            v1.nx = all_elim.nx;
            all_elim.nx = v1;
            return (v1);
      }

      el_term lratio_md1(var var, int y, int u, int v, xterm p1, xterm p2, xterm q1, xterm q2)
      {
            if (print_geo)
            {
                  gprint("\nlratio_md1" + y + " " + u + " " + v);
                  print_var(var, 0);
                  gprint("\n");
                  pprint(p1);
                  pprint(p2);
                  pprint(q1);
                  pprint(q2);
            }
            if (eq_poly(p2, q2))
            { /*put_p(q2); */
                  return (mk_elim(var, pplus(ptimes(geval(var, y, v), p1),
                          ptimes(geval(var, y, u), q1)), p2));
            } else
            {
                  return (mk_elim(var, pplus(ptimes3(geval(var, y, v), p1, cp_poly(q2)),
                          ptimes3(geval(var, y, u), q1, cp_poly(p2))),
                          ptimes(cp_poly(p2), cp_poly(q2))));
            }
      }

//var = (py a y y c)
      el_term lratio_md2(var var, int y, int u, int v, xterm p1, xterm p2, xterm q1, xterm q2)
      {
            return (mk_elim(var,
                    pplus3(ptimes(geval(var, y, v), ptimes(cp_poly(p1), cp_poly(q2))),
                            ptimes(geval(var, y, u), ptimes(cp_poly(q1), cp_poly(p2))),
                            ptimes(trim_g(v, v, u, u), ptimes(cp_poly(p1), cp_poly(q1)))),
                    ptimes(cp_poly(p2), cp_poly(q2))));
      }


      el_term pratio_md1(var var, int y, int w, int u, int v, xterm p1, xterm p2)
      {

            return (mk_elim(var,
                    pplus(ptimes(geval(var, y, w), cp_poly(p2)),
                            ptimes(cp_poly(p1), geval1(var, y, v, u))),
                    cp_poly(p2)));
      }

      xterm geval1(var var, int y, int v, int u)
      {
            switch (var.nm)
            {
                  case 2:
                  case -2:
                        return (trim_a(v, var.pt[1], u, var.pt[3]));
                  case 3:
                  case -3:
                        return (trim_g(v, var.pt[1], u, var.pt[3]));
                  case 4:
                  case -4:
                        return (trim_vec(v, u));
/*    case 4: case -4: return(pminus(trim_vec(v,0),trim_vec(u,0))); */
                  default:
                        gprint("geval11\n");
                        exit(1);
            }
            return (null);
      }

      el_term pratio_md2(var var, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            return (mk_elim(var,
                    pplus4(ptimes3(geval(var, y, w), cp_poly(p2), cp_poly(p2)),
                            pminus(ptimes3(geval(var, y, v), cp_poly(p1), cp_poly(p2)),
                                    ptimes3(geval(var, y, u), cp_poly(p1), cp_poly(p2))),
                            ptimes4(get_n(2L), trim_g(w, u, u, v), cp_poly(p1), cp_poly(p2)),
                            ptimes3(trim_g(v, v, u, u), cp_poly(p1), pminus(cp_poly(p2), cp_poly(p1)))),
                    ptimes(cp_poly(p2), cp_poly(p2))));
      }


      el_term pe_v_pratio(var var, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            if (print_geo)
            {
                  gprint("pratio: \n");
                  pprint(p1);
                  pprint(p2);
            }
            switch (var.nm)
            {
                  case 1:
                        return (pe_r_pratio(var, var.pt[0], var.pt[1], var.pt[2], var.pt[3], y, w, u, v, p1, p2));
                  case 2:
                        return (pe_a_pratio(var, var.pt[0], var.pt[1], var.pt[2], var.pt[3], y, w, u, v, p1, p2));
                  case 3:
                        return (pe_g_pratio(var, var.pt[0], var.pt[1], var.pt[2], var.pt[3], y, w, u, v, p1, p2));
                  case 4:
                        if (w == u)
                              return (lratio_md1(var, y, u, v, p1, p2, pminus(cp_poly(p2), cp_poly(p1)), cp_poly(p2)));
                        else
                              return (pratio_md1(var, y, w, u, v, p1, p2));
                  default:
                        gerror("pe_v_ipp: (" + var.nm + ")" + "is not a proper variable.");

            }
            return (null);
      }

      el_term pe_r_pratio(var var, int n2, int n1, int d2, int d1, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            if (print_geo) gprint("pe_r_pratio: " + n1 + n2 + d1 + d2 + y + w + u + v);
            if ((n2 == y) && (d2 == y))
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(var,
                                pplus(ptimes(cp_poly(p2), trim_r(n1, w, u, v)), cp_poly(p1)),
                                pplus(ptimes(cp_poly(p2), trim_r(d1, w, u, v)), cp_poly(p1))));
                  else if (xcoll(w, u, v))
                        return (mk_elim(var, trim_a(n1, n1, u, v), trim_a(d1, d1, u, v)));
                  else
                        return (mk_elim(var, trim_a(n1, u, w, v), trim_a(d1, u, w, v)));
            } else if (n2 == y)
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(var,
                                pplus(ptimes(cp_poly(p2), trim_r(n1, w, u, v)), cp_poly(p1)),
                                ptimes(cp_poly(p2), trim_r(d1, d2, u, v))));
                  else if (xcoll(w, u, v))
                        return (mk_elim(var, trim_a(n1, n1, u, v), trim_a(d1, u, d2, v)));
                  else
                        return (mk_elim(var, trim_a(n1, u, w, v), trim_a(d1, u, d2, v)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_pratio(var, d2, d1, n2, n1, y, w, u, v, p1, p2)));
            } else
            {
                  exit(1);
            }
            return (null);
      }

      el_term pe_a_pratio(var var, int n1, int n2, int n3, int n4, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            el_term e1;

            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(var, trim_a(w, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && xpara(y, n2, u, v))
            {
                  n3 = n2;
            }
            if (xpara(y, n3, u, v))
            {
                  return (mk_elim(var, ptimes(trim_a(u, n2, v, n4),
                          pminus(ptimes(trim_r(n3, w, v, u), cp_poly(p2)),
                                  cp_poly(p1))),
                          p2));
            }

            if ((w == u) && ((p1.var == null && p2.var == null)))
            {
                  e1 = lratio_md1(var, y, u, v, p1, p2, c_pminus(p2, p1), cp_poly(p2));
            } else
                  e1 = pratio_md1(var, y, w, u, v, p1, p2);
            return (e1);
      }

      el_term pe_g_pratio(var var, int n1, int n2, int n3, int n4, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            el_term e1;
            int n;
            xterm q1, q2;
            var v1;

            q1 = get_n(1L);
            q2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }

            if (n2 == y && xpara(n2, n4, u, v))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  q1 = ptimes(q1, pminus(ptimes(trim_r(n3, w, v, u), cp_poly(p2)), cp_poly(p1)));
                  q2 = ptimes(q2, cp_poly(p2));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  q1 = ptimes(q1, pminus(ptimes(trim_r(n3, w, v, u), cp_poly(p2)), cp_poly(p1)));
                  q2 = ptimes(q2, cp_poly(p2));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }

            if (n2 == y && n3 == y)
            {
                  if (w == u)
                        e1 = lratio_md2(var, y, u, v, p1, p2, c_pminus(p2, p1), cp_poly(p2));
                  else
                        e1 = pratio_md2(var, y, w, u, v, p1, p2);
            } else if (n1 == y)
            {
                  v1 = mk_var(3, n1, n2, n3, n4);
                  if (w == u)
                        e1 = lratio_md1(v1, y, u, v, p1, p2, c_pminus(p2, p1), cp_poly(p2));
                  else
                        e1 = pratio_md1(v1, y, w, u, v, p1, p2);
                  e1.v = var;
            } else
            {
                  e1 = mk_elim(var, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, q1);
            e1.p2 = ptimes(e1.p2, q2);
            return (e1);
      }

      el_term pe_v_ipp(var var, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            if (w == v)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (r == q)
            {
                  n = p;
                  p = q;
                  q = n;
            }
            if (w == u && w > v)
            {
                  w = v;
                  v = u;
                  u = w;
            }
            if (r == p && r > q)
            {
                  r = q;
                  q = p;
                  p = r;
            }
            if (r > w)
            {
                  n = w;
                  w = r;
                  r = n;
                  n = u;
                  u = p;
                  p = n;
                  n = v;
                  v = q;
                  q = n;
            }
            switch (var.nm)
            {
                  case 1:
                        return (pe_r_ipp(var, var.pt[1], var.pt[0], var.pt[3], var.pt[2], y, w, u, v, r, p, q));
                  case 2:
                        return (pe_a_ipp(var, var.pt[0], var.pt[1], var.pt[2], var.pt[3], y, w, u, v, r, p, q));
                  case 3:
                        return (pe_g_ipp(var, var.pt[0], var.pt[1], var.pt[2], var.pt[3], y, w, u, v, r, p, q));
                  case 4:
                        if (w == u)
                              return (lratio_md1(var, y, u, v,
                                      trim_a(u, p, r, q), trim_a(u, p, v, q),
                                      trim_a(v, q, r, p), trim_a(u, p, v, q)));
                        else
                              return (pratio_md1(var, y, w, u, v, trim_a(w, p, r, q), trim_a(u, p, v, q)));
                  default:
                        gerror("pe_v_ipp: (" + var.nm + ")" + "is not a proper variable.");
            }
            return (null);
      }

      el_term pe_r_ipp(var var, int n1, int n2, int d1, int d2, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            if (print_geo)
                  gprint("pe_r_ipp: " + n1 + n2 + d1 + d2 + y + w + u + v + r + p + q);
            if (xpara(n1, n2, u, v))
            {
                  n = w;
                  w = r;
                  r = n;
                  n = u;
                  u = p;
                  p = n;
                  n = v;
                  v = q;
                  q = n;
            }
            if ((n2 == y) && d2 == y)
                  return (mk_elim(var, trim_a(n1, u, w, v), trim_a(d1, u, w, v)));
            else if (n2 == y)
                  return (mk_elim(var, trim_a(n1, u, w, v), trim_a(d1, u, d2, v)));
            else if (d2 == y)
                  return (mk_elim(var, trim_a(n1, u, n2, v), trim_a(d1, u, w, v)));
            else
                  exit(1);
            return (null);
      }

      el_term pe_a_ipp(var var, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;

            if (r == n2 || r == n3 || r == n4)
            {
                  n = w;
                  w = r;
                  r = n;
                  n = u;
                  u = p;
                  p = n;
                  n = v;
                  v = q;
                  q = n;
            }

            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(var, trim_a(w, n2, n3, n4), get_n(1L)));
            }
            if (xpara(n2, n4, p, q))
            {
                  return (mk_elim(var, trim_a(r, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && xpara(y, n2, u, v))
            {
                  n3 = n2;
            }
            if (xpara(y, n3, u, v))
            {
                  return (mk_elim(var, ptimes(trim_a(u, n2, v, n4), trim_a(n3, q, r, p)), trim_a(v, q, u, p)));
            }
            if (n3 == n4 && xpara(y, n2, p, q))
            {
                  n3 = n2;
            }
            if (xpara(y, n3, p, q))
            {
                  return (mk_elim(var, ptimes(trim_a(p, n2, q, n4), trim_a(n3, u, w, v)), trim_a(q, u, p, v)));
            }

            if (xcoll(w, u, v) && (xpara(u, n3, n2, n4) || xpara(v, n3, n2, n4)))
                  return (lratio_md1(var, y, u, v,
                          trim_a(u, p, r, q), trim_a(u, p, v, q),
                          trim_a(v, q, r, p), trim_a(u, p, v, q)));
            if (xcoll(r, p, q) && (xpara(p, n3, n2, n4) || xpara(q, n3, n2, n4)))
                  return (lratio_md1(var, y, p, q,
                          trim_a(p, u, w, v), trim_a(p, u, q, v),
                          trim_a(q, v, w, u), trim_a(p, u, q, v)));
            if (w == u)
                  return (lratio_md1(var, y, u, v,
                          trim_a(u, p, r, q), trim_a(u, p, v, q),
                          trim_a(v, q, r, p), trim_a(u, p, v, q)));
            return (pratio_md1(var, y, w, u, v, trim_a(w, p, r, q), trim_a(u, p, v, q)));
      }

      el_term pe_g_ipp(var var, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            var v1;
            el_term e1;
            xterm p1, p2;

            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, u, v))
            {
                  n2 = w;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, p, q))
            {
                  n2 = r;
            }
            if (n1 == y && xperp(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }

            if (n2 == y && xpara(n2, n4, u, v))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  p1 = ptimes(p1, trim_a(n3, p, r, q));
                  p2 = ptimes(p2, trim_a(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  p1 = ptimes(p1, trim_a(n3, p, r, q));
                  p2 = ptimes(p2, trim_a(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n2 == y && xpara(n2, n4, p, q))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xpara(n1, n3, p, q))
            {
                  p1 = ptimes(p1, trim_a(n3, u, w, v));
                  p2 = ptimes(p2, trim_a(q, u, p, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = p;
                  n4 = q;
            }
            if (n1 == y && xpara(n1, n3, p, q))
            {
                  p1 = ptimes(p1, trim_a(n3, u, w, v));
                  p2 = ptimes(p2, trim_a(q, u, p, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = p;
                  n4 = q;
            }

            if (n2 == y && n3 == y)
            {
                  if (w == u)
                        e1 = lratio_md2(var, y, u, v,
                                trim_a(u, p, r, q), trim_a(u, p, v, q),
                                trim_a(v, q, r, p), trim_a(u, p, v, q));
                  else
                        e1 = pratio_md2(var, y, w, u, v, trim_a(w, p, r, q), trim_a(u, p, v, q));
            } else if (n1 == y)
            {
                  v1 = mk_var(-3, n1, n2, n3, n4);
                  if (w == u)
                  {
                        e1 = lratio_md1(v1, y, u, v,
                                trim_a(u, p, r, q), trim_a(u, p, v, q),
                                trim_a(v, q, r, p), trim_a(u, p, v, q));
                  } else
                  {
                        e1 = pratio_md1(v1, y, w, u, v, trim_a(w, p, r, q), trim_a(u, p, v, q));
                  }
                  e1.v = var;
            } else
            {
                  e1 = mk_elim(var, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
            return (e1);
      }


      el_term pe_r_ill(var var, int n1, int n2, int d1, int d2, int y, int u, int v, int i, int j)
      {
            int n0;

            if (n2 == y)
            {
                  if (xcoll(n1, u, v))
                  {
                        n0 = i;
                        i = u;
                        u = n0;
                        n0 = j;
                        j = v;
                        v = n0;
                  }
                  if (d2 == y)
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d1, u, v, v)));
                  else
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d1, u, d2, v)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_ill(var, d1, d2, n1, n2, y, u, v, i, j)));
            } else
            {
                  exit(1);
            }
            return (null);
      }

      el_term pe_a_ill(var var, int n1, int n2, int n3, int n4, int y, int u, int v, int i, int j)
      {
            int n;
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (j < i)
            {
                  n = i;
                  i = j;
                  j = n;
            }
            if (j < v)
            {
                  n = u;
                  u = i;
                  i = n;
                  n = v;
                  v = j;
                  j = n;
            }

            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(var, trim_a(u, n2, n3, n4), get_n(1L)));
            }
            if (xpara(n2, n4, i, j))
            {
                  return (mk_elim(var, trim_a(i, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && xcoll(u, v, n2))
            {
                  n3 = n2;
            }
            if (xcoll(u, v, n3))
            {
                  return (mk_elim(var, ptimes(trim_a(u, n2, v, n4), trim_a(n3, i, j, j)), trim_a(v, i, u, j)));
            }
            if (n3 == n4 && xcoll(i, j, n2))
            {
                  n3 = n2;
            }
            if (xcoll(i, j, n3))
            {
                  return (mk_elim(var, ptimes(trim_a(i, n2, j, n4), trim_a(n3, u, v, v)), trim_a(j, u, i, v)));
            }

            if (xpara(u, n3, n2, n4) || xpara(v, n3, n2, n4))
            {
                  return (lratio_md1(var, y, u, v,
                          trim_a(u, i, j, j), trim_a(u, i, v, j),
                          trim_a(v, j, i, i), trim_a(u, i, v, j)));
            }
            if (xpara(i, n3, n2, n4) || xpara(j, n3, n2, n4))
            {
                  return (lratio_md1(var, y, i, j,
                          trim_a(i, u, v, v), trim_a(i, u, j, v),
                          trim_a(j, v, u, u), trim_a(i, u, j, v)));
            }
            return (lratio_md1(var, y, u, v,
                    trim_a(u, i, j, j), trim_a(u, i, v, j),
                    trim_a(v, j, i, i), trim_a(u, i, v, j)));
      }

      el_term pe_g_ill(var var, int n1, int n2, int n3, int n4, int y, int u, int v, int i, int j)
      {
            int n;
            el_term e1;
            xterm p1, p2;
            var v1;

            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (j < i)
            {
                  n = i;
                  i = j;
                  j = n;
            }
            if (j < v)
            {
                  n = u;
                  u = i;
                  i = n;
                  n = v;
                  v = j;
                  j = n;
            }

            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, i, j))
            {
                  n1 = n2;
                  n2 = i;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, i, j))
            {
                  n1 = n2;
                  n2 = i;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }


            if (n2 == y && xcoll(u, v, n4))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, i, j, j));
                  p2 = ptimes(p2, trim_a(v, i, u, j));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, i, j, j));
                  p2 = ptimes(p2, trim_a(v, i, u, j));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }

            if (n2 == y && xcoll(i, j, n4))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xcoll(i, j, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, u, v, v));
                  p2 = ptimes(p2, trim_a(j, u, i, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = i;
                  n4 = j;
            }
            if (n1 == y && xcoll(i, j, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, u, v, v));
                  p2 = ptimes(p2, trim_a(j, u, i, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = i;
                  n4 = j;
            }

            if (n2 == y && n3 == y)
            {
                  e1 = lratio_md2(var, y, u, v,
                          trim_a(u, i, j, j), trim_a(u, i, v, j),
                          trim_a(v, j, i, i), trim_a(u, i, v, j));
            } else if (n1 == y)
            {
                  if (n3 == i || n3 == j || xperp(i, n3, n2, n4) || xperp(j, n3, n2, n4))
                  {
                        n = u;
                        u = i;
                        i = n;
                        n = v;
                        v = j;
                        j = n;
                  }
                  v1 = mk_var(3, n1, n2, n3, n4);
                  e1 = lratio_md1(v1, y, u, v,
                          trim_a(u, i, j, j), trim_a(u, i, v, j),
                          trim_a(v, j, i, i), trim_a(u, i, v, j));
                  e1.v = var;
            } else
            {
                  e1 = mk_elim(var, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
            return (e1);
      }


      el_term pe_vec_ill(var var, int n1, int n2, int y, int u, int v, int i, int j)
      {
            int n;

            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (j < i)
            {
                  n = i;
                  i = j;
                  j = n;
            }
            if (j < v)
            {
                  n = u;
                  u = i;
                  i = n;
                  n = v;
                  v = j;
                  j = n;
            }

            return (lratio_md1(var, y, u, v,
                    trim_a(u, i, j, j), trim_a(u, i, v, j),
                    trim_a(v, j, i, i), trim_a(u, i, v, j)));
      }

      el_term pe_r_ilp(var var, int n1, int n2, int d1, int d2, int y, int u, int v, int r, int p, int q)
      {

            if ((n2 == y) && (d2 == y))
            {
                  if (xcoll(n1, u, v))
                        return (mk_elim(var, trim_a(n1, p, r, q), trim_a(d1, p, r, q)));
                  else
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d1, u, v, v)));
            }
            if (n2 == y)
            {
                  if (xcoll(n1, u, v))
                        return (mk_elim(var, trim_a(n1, p, r, q), trim_a(d1, p, d2, q)));
                  else if (xcoll(d2, u, v))
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d1, u, v, v)));
                  else if (xcoll(d1, u, v))
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d2, v, u, u)));
                  else
                        return (mk_elim(var, trim_a(n1, u, v, v), trim_a(d1, u, d2, v)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_ilp(var, d1, d2, n1, n2, y, u, v, r, p, q)));
            } else
            {
                  exit(1);
            }
            return (null);
      }

      el_term pe_a_ilp(var var, int n1, int n2, int n3, int n4, int y, int u, int v, int r, int p, int q)
      {
            int n;
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(var, trim_a(u, n2, n3, n4), get_n(1L)));
            }
            if (xpara(n2, n4, p, q))
            {
                  return (mk_elim(var, trim_a(r, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && xcoll(u, v, n2))
            {
                  n3 = n2;
            }
            if (xcoll(u, v, n3))
            {
                  return (mk_elim(var, ptimes(trim_a(u, n2, v, n4), trim_a(n3, p, r, q)), trim_a(v, p, u, q)));
            }
            if (xpara(u, n3, n2, n4) || xpara(v, n3, n2, n4))
            {
                  return (lratio_md1(var, y, u, v,
                          trim_a(u, p, r, q), trim_a(u, p, v, q),
                          trim_a(v, q, r, p), trim_a(u, p, v, q)));
            }

            if (n3 == n4 && xpara(y, n2, p, q))
            {
                  n3 = n2;
            }
            if (xpara(y, n3, p, q))
            {
                  return (mk_elim(var, ptimes(trim_a(p, n2, q, n4), trim_a(n3, u, v, v)), trim_a(q, u, p, v)));
            }
            return (lratio_md1(var, y, u, v,
                    trim_a(u, p, r, q), trim_a(u, p, v, q),
                    trim_a(v, q, r, p), trim_a(u, p, v, q)));
      }

      el_term pe_g_ilp(var var, int n1, int n2, int n3, int n4, int y, int u, int v, int r, int p, int q)
      {
            int n;
            var v1;
            el_term e1;
            xterm p1, p2;
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xperp(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, p, r, q));
                  p2 = ptimes(p2, trim_a(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_a(n3, p, r, q));
                  p2 = ptimes(p2, trim_a(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xpara(n1, n3, p, q))
            {
                  p1 = ptimes(p1, trim_a(n3, u, v, v));
                  p2 = ptimes(p2, trim_a(q, u, p, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = p;
                  n4 = q;
            }
            if (n1 == y && xpara(n1, n3, p, q))
            {
                  p1 = ptimes(p1, trim_a(n3, u, v, v));
                  p2 = ptimes(p2, trim_a(q, u, p, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = p;
                  n4 = q;
            }
            if (n2 == y && n3 == y)
            {
                  e1 = lratio_md2(var, y, u, v,
                          trim_a(u, p, r, q), trim_a(u, p, v, q),
                          trim_a(v, q, r, p), trim_a(u, p, v, q));
            } else if (n1 == y)
            {
                  v1 = mk_var(3, n1, n2, n3, n4);
                  e1 = lratio_md1(v1, y, u, v,
                          trim_a(u, p, r, q), trim_a(u, p, v, q),
                          trim_a(v, q, r, p), trim_a(u, p, v, q));
                  e1.v = var;
            } else
            {
                  e1 = mk_elim(var, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
            return (e1);
      }


      //////////////////////////////////////////

/* trim functions */
/*
xterm *mk_trim (v)
var *v;
{ var *v1;
  int i;

  v1 = all_var.nx;
  while ((v1 != null) && !(eq_var(v,v1))) { v1 = v1.nx;}
  if (v1 == null)
	 { v1=(var *)calloc(1,sizeof(var));
		v1.nm = v.nm;
		for (i=0;i<9;i++) v1.pt[i] = v.pt[i];
		v1.nx = null;
		last_var.nx = v1; last_var = v1;
	 }
  return(get_m(v1));
}
*/
      ///////////////////////////////////////////////////////////////from area.cpp trim function.
      xterm geval(var var, int y, int p)
      {
            int k;
            int i, pt[];
            pt = new int[9];

            for (i = 0; i < 4; i++)
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
//int p1,p2,p3,p4;
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
      {
            int p;
            char sn = 1;
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
      /////////////////////////////////////////////////////////////////////////////////////////////from elimt;

      el_term tratio_a_md1(var v_a, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            return (mk_elim(v_a,
                    pplus(ptimes3(get_n(4L), cp_poly(p2), geval(v_a, y, w)),
                            ptimes(cp_poly(p1), trim_g(v, v_a.pt[1], u, v_a.pt[3]))),
                    ptimes(get_n(4L), cp_poly(p2))));
      }

      el_term tratio_g_md1(var v_a, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            return (mk_elim(v_a,
                    pplus(ptimes(cp_poly(p2), geval(v_a, y, w)),
                            ptimes3(get_n(4L), cp_poly(p1), trim_a(u, v_a.pt[1], v, v_a.pt[3]))),
                    cp_poly(p2)));
      }

      el_term tratio_md2(var v_a, int n3, int n4, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            return (mk_elim(v_a,
                    pplus3(ptimes3(cp_poly(p2), cp_poly(p2), geval(v_a, y, w)),
                            ptimes4(get_n(-4L), cp_poly(p1), cp_poly(p2),
                                    pplus(trim_a(n3, u, w, v), trim_a(n4, u, w, v))),
                            ptimes3(cp_poly(p1), cp_poly(p1), trim_g(v, u, u, v))),
                    ptimes(cp_poly(p2), cp_poly(p2))));
      }

      el_term pe_v_tratio(var v_a, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            if (print_geo)
            {  //printf("pe_v_tratio. %s %s %s %s\n",ANAME(y),ANAME(w),ANAME(u),ANAME(v));
                  print_var(v_a, 0);
                  gprint("\n");
                  pprint(p1);
                  pprint(p2);
            }
            switch (v_a.nm)
            {
                  case 1:
                        return (pe_r_tratio(v_a, v_a.pt[1], v_a.pt[0], v_a.pt[3], v_a.pt[2], y, w, u, v, p1, p2));
                  case 2:
                        return (pe_a_tratio(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, p1, p2));
                  case 3:
                        return (pe_g_tratio(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, p1, p2));
                  default:
                        gerror("pe_v_tratio: is not a proper variable.\n");
            }
            return (null);
      }

      el_term pe_r_tratio(var v_a, int n1, int n2, int n3, int n4, int y, int r, int p, int q, xterm p1, xterm p2)
      {
            if (n2 == y && n4 == y)
            {
                  if (xcoll(n1, r, y))
                        return (mk_elim(v_a,
                                pplus(ptimes3(get_n(4L), cp_poly(p2), trim_a(n1, p, r, q)),
                                        ptimes(cp_poly(p1), trim_g(p, p, q, q))),
                                pplus(ptimes3(get_n(4L), p2, trim_a(n3, p, r, q)),
                                        ptimes(p1, trim_g(p, p, q, q)))));
                  else
                        return (mk_elim(v_a, trim_g(n1, p, r, q), trim_g(n3, p, r, q)));
            } else if (n2 == y)
            {
                  if (xcoll(n1, r, y))
                        return (mk_elim(v_a,
                                pplus(ptimes3(get_n(4L), cp_poly(p2), trim_a(n1, p, r, q)),
                                        ptimes(cp_poly(p1), trim_g(p, p, q, q))),
                                ptimes3(get_n(4L), p2, trim_a(n3, p, n4, q))));
                  else
                        return (mk_elim(v_a, trim_g(n1, p, r, q), trim_g(n3, p, n4, q)));
            } else if (n3 == y)
            {
                  return (rev_elim(pe_r_tratio(v_a, n3, n4, n1, n2, y, r, p, q, p1, p2)));
            } else
                  gerror("pe_r_taio");
            return (null);
      }

      el_term pe_a_tratio(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            if (xperp(n2, n4, u, v)) return (mk_elim(v_a, trim_a(w, n2, n3, n4), get_n(1L)));
            return (tratio_a_md1(v_a, y, w, u, v, p1, p2));
      }

      el_term pe_g_tratio(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, xterm p1, xterm p2)
      {
            el_term e = null;
            if (n1 == y && n2 != y)
            {
                  if (xpara(n2, n4, u, v)) return (mk_elim(v_a, trim_g(w, n2, n3, n4), get_n(1L)));
                  return (tratio_g_md1(v_a, y, w, u, v, p1, p2));
            }


            if (n2 == y && n3 == y)
            {
                  if (n1 == w && n4 == w)
                        e = mk_elim(v_a, ptimes3(cp_poly(p1), cp_poly(p1), trim_g(v, u, u, v)), ppower(p2, 2));
                  else
                        e = tratio_md2(v_a, n1, n4, y, w, u, v, p1, p2);
            }
            return (e);
      }


      el_term pe_a_itt1(var v_a, int n1, int n2, int n3, int y, int o, int u, int v)
      {
            int n;
            if ((n1 == u && n2 == v) || (n1 == v && n2 == u))
                  return (mk_elim(v_a, ptimes(trim_g(o, n1, n1, n2), trim_g(o, n2, n2, n1)),
                          ptimes(get_n(-16L), trim_a(n1, n2, o, o))));
            if ((n1 == o && n2 == v) || (n1 == v && n2 == o))
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if ((n1 == o && n2 == u) || (n1 == u && n2 == o))
                  return (mk_elim(v_a, ptimes(trim_g(n1, n2, n2, n1), trim_g(n1, v, v, n2)),
                          ptimes(get_n(16L), trim_a(n1, n2, v, v))));
            return (null);
      }

      el_term pe_a_itt(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            el_term e1;
            if (xperp(n2, n4, u, v)) return (mk_elim(v_a, trim_a(w, n2, n3, n4), get_n(1L)));
            if (xperp(n2, n4, p, q)) return (mk_elim(v_a, trim_a(r, n2, n3, n4), get_n(1L)));

            if (w == v)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (r == q)
            {
                  n = p;
                  p = q;
                  q = n;
            }
            if (n3 == n4 && w == r && w == u && r == p)
            {
                  e1 = pe_a_itt1(v_a, n2, n3, n1, y, w, v, q);
                  if (e1 != null) return (e1);
            }

            if (n3 == n4 && xcoll(r, n2, n3))
            {
                  n3 = n2;
            }
            if (xpara(r, n3, n2, n4))
                  return (tratio_a_md1(v_a, y, r, p, q, trim_g(r, u, w, v), ptimes(get_n(-4L), trim_a(p, u, q, v))));
            return (tratio_a_md1(v_a, y, w, u, v, trim_g(w, p, r, q), ptimes(get_n(-4L), trim_a(u, p, v, q))));
      }


      el_term pe_g_itt1(var v_a, int n1, int n2, int n3, int y, int o, int u, int v)
      {
            int n;
            if ((n1 == u && n3 == v) || (n1 == v && n3 == u))
            {
                  return (mk_elim(v_a, ptimes3(trim_g(u, o, o, v), trim_g(o, u, u, v), trim_g(o, v, v, u)),
                          ptimes3(get_n(-16L), trim_a(o, u, u, v), trim_a(o, u, u, v))));
            }
            if ((n1 == o && n3 == v) || (n1 == v && n3 == o) || (n1 == v && n3 == v))
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if ((n1 == o && n3 == u) || (n1 == u && n3 == o) || (n1 == u && n3 == u))
            {
                  return (mk_elim(v_a, ptimes3(trim_g(u, o, o, u), trim_g(o, v, v, u), trim_g(o, v, v, u)),
                          ptimes3(get_n(16L), trim_a(o, u, u, v), trim_a(o, u, u, v))));
            }
            if (n1 == o && n3 == o)
            {
                  return (mk_elim(v_a, ptimes3(trim_g(v, o, o, v), trim_g(u, o, o, u), trim_g(v, u, u, v)),
                          ptimes3(get_n(16L), trim_a(o, u, u, v), trim_a(o, u, u, v))));
            }
            return (null);
      }

      el_term pe_g_itt(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            el_term e1;
            int n;

            if (n1 == y)
            {
                  if (xpara(n2, n4, u, v)) return (mk_elim(v_a, trim_g(w, n2, n3, n4), get_n(1L)));
                  if (xpara(n2, n4, p, q)) return (mk_elim(v_a, trim_g(r, n2, n3, n4), get_n(1L)));
                  if (xperp(r, n3, n2, n4))
                        return (tratio_g_md1(v_a, y, r, p, q, trim_g(r, u, w, v), ptimes(get_n(-4L), trim_a(p, u, q, v))));
                  return (tratio_g_md1(v_a, y, w, u, v, trim_g(w, p, r, q), ptimes(get_n(-4L), trim_a(u, p, v, q))));
            }
            if (w == v)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (r == q)
            {
                  n = p;
                  p = q;
                  q = n;
            }

            int sn = 1;
//  if (n2==y && n3==y) { n3 = n1; n1 = n2; sn = -1; }


            if (n2 == y && n3 == y && w == r && w == u && r == p)
            {
                  return (pe_g_itt1(v_a, n1, n2, n4, y, w, v, q));
            }

            if (n2 == y && n3 == y)
                  e1 = tratio_md2(v_a, n1, n4, y, w, u, v, trim_g(w, p, r, q), ptimes(get_n(-4L), trim_a(u, p, v, q)));
            else
                  return (null);
            return e1;

      }

/*
el_term  pe_r_itt (v_a,n1,n2,n3,n4,y,w,u,v,r,p,q)
var  v_a;
int n1,n2,n3,n4,y,w,u,v,r,p,q;
{ return(null);}

*/
      el_term pe_v_ipt(var v_a, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            if (w == v)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (r == q)
            {
                  n = p;
                  p = q;
                  q = n;
            }
            if (w == u && w > v)
            {
                  w = v;
                  v = u;
                  u = w;
            }
            switch (v_a.nm)
            {
                  case 1:
                        return (pe_r_ipt(v_a, v_a.pt[1], v_a.pt[0], v_a.pt[3], v_a.pt[2], y, w, u, v, r, p, q));

                  case 2:
                        return (pe_a_ipt(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, r, p, q));
                  case 3:
                        return (pe_g_ipt(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, r, p, q));
                  case 4:
                        if (w == u)
                              return (lratio_md1(v_a, y, u, v,
                                      trim_g(u, p, r, q), trim_g(u, p, v, q),
                                      trim_g(r, p, v, q), trim_g(u, p, v, q)));
                        return (pratio_md1(v_a, y, w, u, v, trim_g(w, p, r, q), trim_g(u, p, v, q)));
                  default:
                        gerror("pe_v_ipt: is not a proper variable.\n");
            }
            return (null);
      }

      el_term pe_r_ipt(var v_a, int n1, int n2, int d1, int d2, int y, int w, int u, int v, int r, int p, int q)
      {
            if (n2 == y && d2 == y)
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(v_a, trim_g(n1, p, r, q), trim_g(d1, p, r, q)));
                  else
                        return (mk_elim(v_a, trim_a(n1, u, w, v), trim_a(d1, u, w, v)));
            } else if (n2 == y)
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(v_a, trim_g(n1, p, r, q), trim_g(d1, p, d2, q)));
                  else if (xcoll(n2, p, q) && xcoll(d2, p, q))
                  {
                        return (mk_elim(v_a, trim_a(n1, p, p, q), trim_a(d1, p, p, q)));
                  } else if (xcoll(n2, p, q) && d2 != y && xcoll(d1, p, q))
                  {
                        return (mk_elim(v_a, trim_a(n1, p, p, q), trim_a(d2, q, q, p)));
                  } else
                        return (mk_elim(v_a, trim_a(n1, u, w, v), trim_a(d1, u, d2, v)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_ipt(v_a, d1, d2, n1, n2, y, w, u, v, r, p, q)));
            } else
                  exit(1);
            return (null);
      }

      el_term pe_a_ipt(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(v_a, trim_a(w, n2, n3, n4), get_n(1L)));
            }
            if (xperp(n2, n4, p, q))
            {
                  return (mk_elim(v_a, trim_a(r, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && xpara(y, n2, u, v))
            {
                  n3 = n2;
            }
            if (xpara(y, n3, u, v))
            {
                  return (mk_elim(v_a, ptimes(trim_a(u, n2, v, n4), trim_g(n3, q, r, p)), trim_g(v, q, u, p)));
            }
            if (w == u || (xcoll(y, u, v) && (xpara(u, n3, n2, n4) || xpara(v, n3, n2, n4))))
                  return (lratio_md1(v_a, y, u, v,
                          trim_g(u, p, r, q), trim_g(u, p, v, q),
                          trim_g(r, p, v, q), trim_g(u, p, v, q)));
            return (pratio_md1(v_a, y, w, u, v, trim_g(w, p, r, q), trim_g(u, p, v, q)));
      }

      el_term pe_g_ipt(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int r, int p, int q)
      {
            int n;
            el_term e1;
            xterm p1, p2;
            var v1;

//  int sn = 1;
//  if (n2==y && n3==y) { n3 = n1; n1 = n2; sn = -1; }


            if (xcoll(y, p, q) && n2 == y && n3 == y && n1 == r && n4 == r)
            {
                  e1 = mk_elim(v_a, ptimes3(get_n(16L), trim_a(r, p, q, q), trim_a(r, p, q, q)), trim_g(p, q, q, p));
                  return e1;
            }
            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, u, v))
            {
                  n2 = w;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, u, v))
            {
                  n2 = w;
            }

            if (n1 == y && xpara(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xpara(n1, n3, p, q))
            {
                  n2 = r;
            }
            if (n1 == y && xpara(n2, n4, p, q))
            {
                  n1 = n2;
                  n2 = r;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xpara(n1, n3, p, q))
            {
                  n2 = r;
            }


            if (n2 == y && xpara(n2, n4, u, v))
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  p1 = ptimes(p1, trim_g(n3, p, r, q));
                  p2 = ptimes(p2, trim_g(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  p1 = ptimes(p1, trim_g(n3, p, r, q));
                  p2 = ptimes(p2, trim_g(v, p, u, q));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }

            if (n2 == y && n3 == y)
            {
                  if (xperp(n1, n3, p, q) && xperp(n2, n4, p, q))
                  {
                        e1 = mk_elim(v_a,
                                ptimes4(get_n(-16L), trim_a(n3, u, w, v), trim_a(n4, u, w, v), trim_g(p, p, q, q)),
                                ptimes(trim_g(p, u, q, v), trim_g(p, u, q, v)));
                  } else
                        e1 = pratio_md2(v_a, y, w, u, v, trim_g(w, p, r, q), trim_g(u, p, v, q));
            } else if (n1 == y)
            {
                  v1 = mk_var(3, n1, n2, n3, n4);
                  if (w == u || (xcoll(y, u, v) && (xpara(u, n3, n2, n4) || xpara(v, n3, n2, n4))))
                        e1 = lratio_md1(v1, y, u, v,
                                trim_g(u, p, r, q), trim_g(u, p, v, q),
                                trim_g(r, p, v, q), trim_g(u, p, v, q));
                  else
                        e1 = pratio_md1(v1, y, w, u, v, trim_g(w, p, r, q), trim_g(u, p, v, q));
                  e1.v = v_a;
            } else
            {
                  e1 = mk_elim(v_a, trim_g(n1, n2, n3, n4), get_n(1L));
            }
            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
//  if (e1 && sn==-1) e1.p2 = neg_poly(e1.p2);
            return (e1);
      }

      el_term pe_v_ipc(var v_a, int y, int w, int u, int v, int o, int p)
      {
            if (print_geo) gprint("\nCP " + " " + y + " " + w + " " + u + " " + v + " " + o + " " + p);
            switch (v_a.nm)
            {
                  case 1:
                        return (pe_r_ipc(v_a, v_a.pt[1], v_a.pt[0], v_a.pt[3], v_a.pt[2], y, w, u, v, o, p));
                  case 2:
                        return (pe_a_ipc(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, o, p));
                  case 3:
                        return (pe_g_ipc(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, w, u, v, o, p));
                  case 4:
                        if (w == u)
                              return (lratio_md1(v_a, y, u, v,
                                      ptimes(get_n(2L), trim_g(u, u, o, v)), trim_g(u, u, v, v),
                                      pminus(trim_g(u, o, o, u), trim_g(v, o, o, v)), trim_g(u, u, v, v)));
                        return (pratio_md1(v_a, y, w, u, v, pplus(trim_g(w, u, o, v), trim_g(w, u, o, v)), trim_g(u, u, v, v)));
                  default:
                        gerror("pe_v_ipc: is not a proper variable.\n");
            }
            return (null);
      }

      el_term pe_r_ipc(var v_a, int n1, int n2, int d1, int d2, int y, int w, int u, int v, int o, int p)
      {
            if (print_geo) gprint("\nr_icp: " + " " + y + " " + w + " " + u + " " + v + " " + o + " " + p);
            if (w != p) gerror("pe_r_ipc");
            if (n2 == y && d2 == y)
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(v_a,
                                pplus(trim_g(n1, u, o, v), trim_g(w, u, o, v)),
                                pplus(trim_g(d1, u, o, v), trim_g(w, u, o, v))));
                  else
                        return (mk_elim(v_a, trim_a(n1, u, w, v), trim_a(d1, u, w, v)));
            } else if (n2 == y)
            {
                  if (xcoll(n1, w, y))
                        return (mk_elim(v_a, pplus(trim_g(n1, u, o, v), trim_g(w, u, o, v)), trim_g(d1, u, d2, v)));
                  else
                        return (mk_elim(v_a, trim_a(n1, u, w, v), trim_a(d1, u, d2, v)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_ipc(v_a, d1, d2, n1, n2, y, w, u, v, o, p)));
            } else
                  exit(1);
            return (null);
      }

      el_term pe_a_ipc(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int o, int p)
      {
            if (w != p) gerror("pe_r_ipc");

            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(v_a, trim_a(w, n2, n3, n4), get_n(1L)));
            }

            if (n3 == n4 && n2 == w)
            {
                  n3 = n2;
            }
            if (n3 == w)
                  return (mk_elim(v_a,
                          ptimes3(get_n(2), trim_g(w, v, o, u), trim_a(u, n2, v, n4)),
                          trim_g(u, u, v, v)));
            if (n3 == n4 && xpara(n1, n2, u, v))
            {
                  n3 = n2;
            }
            if (n1 == y && xpara(n1, n3, u, v))
            {
                  return (mk_elim(v_a,
                          ptimes(pminus(trim_g(n3, o, o, n3), trim_g(w, o, o, w)), trim_a(u, n2, v, n4)),
                          trim_g(u, n3, v, w)));
            }
            if (w == u)
                  return (lratio_md1(v_a, y, u, v,
                          ptimes(get_n(2L), trim_g(u, u, o, v)), trim_g(u, u, v, v),
                          pminus(trim_g(u, o, o, u), trim_g(v, o, o, v)), trim_g(u, u, v, v)));
            return (pratio_md1(v_a, y, w, u, v, pplus(trim_g(w, u, o, v), trim_g(w, u, o, v)), trim_g(u, u, v, v)));
      }


      el_term pe_g_ipc(var v_a, int n1, int n2, int n3, int n4, int y, int w, int u, int v, int o, int p)
      {
            int n;
            var v1;
            el_term e1;
            xterm p1, p2;
            el_term e;

//  int sn = 1;
//  if (n2==y && n3==y) { n3 = n1; n1 = n2; sn = -1; }

            if (n2 == y && n3 == y && n1 == o && n4 == o)
            {
                  e1 = mk_elim(v_a, trim_g(n3, p, p, n4), get_n(1L));
                  return (e1);
            }

            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, u, v))
            {
                  n2 = w;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = w;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }

            if (n2 == y && n4 == w)
            {
                  n = n1;
                  n1 = n2;
                  n2 = n;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && n3 == w)
            {
                  p1 = ptimes3(p1, get_n(2), trim_g(w, v, o, u));
                  p2 = ptimes(p2, trim_g(u, u, v, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && n3 == w)
            {
                  p1 = ptimes3(p1, get_n(2), trim_g(w, v, o, u));
                  p2 = ptimes(p2, trim_g(u, u, v, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
/*  if (n2 == y && xpara(n2,n4,u,v)) {n=n1;n1=n2;n2=n; n=n3;n3=n4;n4=n; }
  if (n1 == y && xpara(n1,n3,u,v))
	 { p1 = ptimes(p1,pminus(trim_g(n3,o,o,n3),trim_g(w,o,o,w)));
		p2 = ptimes(p2,trim_g(u,n3,v,w));
		n1=n2;n3=n4;n2=u,n4=v;
	 }
  if (n1 == y && xpara(n1,n3,u,v))
	 { p1 = ptimes(p1,pminus(trim_g(n3,o,o,n3),trim_g(w,o,o,w)));
		p2 = ptimes(p2,trim_g(u,n3,v,w));
		n1=n2;n3=n4;n2=u,n4=v;
	 }
*/
            if (n2 == y && n3 == y)
            {
                  if (w == u)
                        e1 = lratio_md2(v_a, y, u, v,
                                ptimes(get_n(2L), trim_g(u, u, o, v)), trim_g(u, u, v, v),
                                pminus(trim_g(u, o, o, u), trim_g(v, o, o, v)), trim_g(u, u, v, v));
                  else
                        e1 = pratio_md2(v_a, y, w, u, v, ptimes(get_n(2L), trim_g(w, u, o, v)), trim_g(u, u, v, v));
            } else if (n1 == y)
            {
                  v1 = mk_var(3, n1, n2, n3, n4);
                  if (w == u)
                        e1 = lratio_md1(v1, y, u, v,
                                ptimes(get_n(2L), trim_g(u, u, o, v)), trim_g(u, u, v, v),
                                pminus(trim_g(u, o, o, u), trim_g(v, o, o, v)), trim_g(u, u, v, v));
                  else
                        e1 = pratio_md1(v1, y, w, u, v, ptimes(get_n(2L), trim_g(w, u, o, v)), trim_g(u, u, v, v));
                  e1.v = v_a;
            } else
            {
                  e1 = mk_elim(v_a, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
//  if (e1 && sn==-1) e1.p2 = neg_poly(e1.p2);
            return (e1);
      }


      el_term pe_r_foot(var v_a, int n1, int n2, int d1, int d2, int y, int p, int u, int v)
      {
            int n;
            el_term e1 = null;
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (print_geo) gprint("pe_r_foot: " + " " + n1 + " " + n2 + " " + d1 + " " + d2 + " " + y + " " + p + " " + u + " " + v);
            if (n2 == y)
            {
                  if (xcoll(d2, u, v) && xperp(d1, d2, u, v))
                        e1 = mk_elim(v_a, trim_a(n1, u, v, v), trim_a(d1, u, v, v));
                  else if (d2 == y)
                  {
                        if (xcoll(n1, u, v))
                        {
                              if (u > n1 && u > d1)
                                    e1 = mk_elim(v_a, trim_g(n1, n1, p, d1), trim_g(d1, n1, p, d1));
                              else
                                    e1 = mk_elim(v_a, trim_g(n1, u, p, v), trim_g(d1, u, p, v));
                        } else
                              e1 = mk_elim(v_a, trim_a(n1, u, v, v), trim_a(d1, u, v, v));
                  } else if (xcoll(n1, u, v))
                  {
                        e1 = mk_elim(v_a, trim_g(n1, u, p, v), trim_g(d1, u, d2, v));
                  } else
                  {
                        e1 = mk_elim(v_a, trim_a(n1, u, v, v), trim_a(d1, u, d2, v));
                  }
            } else if (d2 == y)
            {
                  e1 = rev_elim(pe_r_foot(v_a, d1, d2, n1, n2, y, p, u, v));
            } else
                  gerror("pe_r_foot");
            return (e1);
      }

      el_term pe_a_foot(var v_a, int n1, int n2, int n3, int n4, int y, int p, int u, int v)
      {
            int n;
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (xperp(n2, n4, u, v))
            {
                  return (mk_elim(v_a, trim_a(p, n2, n3, n4), get_n(1L)));
            }
            if (xpara(n2, n4, u, v))
            {
                  return (mk_elim(v_a, trim_a(u, n2, n3, n4), get_n(1L)));
            }
            if (n3 == n4 && xcoll(u, v, n2))
            {
                  n3 = n2;
            }
            if (xcoll(u, v, n3))
            {
                  return (mk_elim(v_a, ptimes(trim_a(u, n2, v, n4), trim_g(n3, u, p, v)), trim_g(v, u, u, v)));
            }
            return (lratio_md1(v_a, y, u, v,
                    trim_g(p, u, u, v), trim_g(u, v, v, u),
                    trim_g(p, v, v, u), trim_g(u, v, v, u)));
      }

      el_term pe_g_foot(var v_a, int n1, int n2, int n3, int n4, int y, int p, int u, int v)
      {
            int n;
            var v1;
            el_term e1;
            xterm p1, p2;

//  int sn = 1;
//  if (n2==y && n3==y) { n3 = n1; n1 = n2; sn = -1; }

            if (print_geo) gprint("pe_g_foot: " + " " + n1 + " " + n2 + " " + n3 + " " + n4 + " " + y + " " + p + " " + u + " " + v);
            if (v < u)
            {
                  n = u;
                  u = v;
                  v = n;
            }

            if (n2 == y && n3 == y && n1 == p && n4 == p)
            {
                  e1 = mk_elim(v_a, ptimes(get_n(-16L), ptimes(trim_a(p, u, v, v), trim_a(p, u, v, v))),
                          trim_g(u, u, v, v));
                  return (e1);
            }

            p1 = get_n(1L);
            p2 = get_n(1L);
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xperp(n1, n3, u, v))
            {
                  n2 = u;
            }
            if (n1 == y && xperp(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = u;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (n1 == y && xpara(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = p;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            } else if (n2 == y && xpara(n1, n3, u, v))
            {
                  n2 = p;
            }
            if (n1 == y && xpara(n2, n4, u, v))
            {
                  n1 = n2;
                  n2 = p;
                  n = n3;
                  n3 = n4;
                  n4 = n;
            }
            if (print_geo) gprint("pe_g_foot: " + " " + n1 + " " + n2 + " " + n3 + " " + n4 + " " + y + " " + p + " " + u + " " + v);

            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_g(n3, v, p, u));
                  p2 = ptimes(p2, trim_g(u, u, v, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }
            if (n1 == y && xcoll(u, v, n3))
            {
                  p1 = ptimes(p1, trim_g(n3, v, p, u));
                  p2 = ptimes(p2, trim_g(u, u, v, v));
                  n1 = n2;
                  n3 = n4;
                  n2 = u;
                  n4 = v;
            }

            if (n2 == y && n3 == y)
            {
                  e1 = lratio_md2(v_a, y, u, v,
                          trim_g(p, u, u, v), trim_g(u, v, v, u),
                          trim_g(p, v, v, u), trim_g(u, v, v, u));
            } else if (n1 == y)
            {
                  v1 = mk_var(3, n1, n2, n3, n4);
                  e1 = lratio_md1(v1, y, u, v,
                          trim_g(p, u, u, v), trim_g(u, v, v, u),
                          trim_g(p, v, v, u), trim_g(u, v, v, u));
                  e1.v = v_a;
            } else
            {
                  e1 = mk_elim(v_a, trim_g(n1, n2, n3, n4), get_n(1L));
            }

            e1.p1 = ptimes(e1.p1, p1);
            e1.p2 = ptimes(e1.p2, p2);
//  if(e1 && sn==-1) e1.p2 = neg_poly(e1.p2);
            return (e1);
      }


      ////////////////////////////////////////////////elimm.cpp
      xterm trim_fa(int p1, int p2, int p3)
//int p1,p2,p3;
      {
            if (xcoll(p1, p2, p3)) return (pzero());
            if ((p1 == 3 && p2 == 2) || (p1 == 2 && p2 == 3)) return (trim_a(p1, p2, p3, p3));
            return (get_m(mk_var(-2, p1, p2, p3, p3)));
      }

      xterm fpt_a(int n1, int n2, int n3)
//int n1,n2,n3;
      {
            xterm p1;
            p1 = ptimes(trim_fa(n1, 2, 1), trim_fa(n2, 3, 1));
            p1 = pplus(p1, ptimes(trim_fa(n2, 2, 1), trim_fa(n3, 3, 1)));
            p1 = pplus(p1, ptimes(trim_fa(n3, 2, 1), trim_fa(n1, 3, 1)));
            p1 = pminus(p1, ptimes(trim_fa(n3, 2, 1), trim_fa(n2, 3, 1)));
            p1 = pminus(p1, ptimes(trim_fa(n2, 2, 1), trim_fa(n1, 3, 1)));
            p1 = pminus(p1, ptimes(trim_fa(n1, 2, 1), trim_fa(n3, 3, 1)));
            return (p1);
      }

      el_term pe_a_fpt(var v_a, int n1, int n2, int n3, int n4)
//var  v_a;
//int n1,n2,n3,n4;
      {
            xterm p0, p1, p2;
            if (n3 == n4 && n3 == 1 && (n2 == 2 || n2 == 3))
            {
                  return (mk_elim(v_a, get_m(mk_var(-2, n1, n2, n3, n4)), get_n(1L)));
            }
            if (n3 == n4 && n2 == 3 && n3 == 2)
            {
                  p1 = pminus(trim_a(3, 2, 1, 1), trim_fa(n1, 2, 1));
                  p1 = pplus(p1, trim_fa(n1, 3, 1));
                  return (mk_elim(v_a, p1, get_n(1L)));
            }

            if (n3 == n4)
                  p1 = fpt_a(n1, n2, n3);
            else
                  p1 = pplus(fpt_a(n1, n2, n3), fpt_a(n1, n3, n4));
            p0 = trim_a(1, 2, 3, 3);
            p2 = pdiv(cp_poly(p1), p0);
            if (p2 == null) return (mk_elim(v_a, p1, p0));
            put_p(p1);
            put_p(p0);
            return (mk_elim(v_a, p2, get_n(1L)));
      }

      el_term pe_v_fpt(var v, int pt)
//var  v;
//int pt;
      {
            el_term e1 = null;
            if (v.nm == 2) e1 = pe_a_fpt(v, v.pt[0], v.pt[1], v.pt[2], v.pt[3]);
            return (e1);
      }

      xterm x(int n)
      {
            if (n == 1 || n == 2) return (get_n(0L));
            String s = "x_" + n;
            return (get_m(mk_svar(s.toCharArray())));
      }

      xterm y(int n)
      {
            if (n == 1) return (get_n(0L));
            String s = "y_" + n;
            return (get_m(mk_svar(s.toCharArray())));
      }

      xterm area_t(int n1, int n2, int n3)
      {
            return (pplus3(pminus(ptimes(x(n1), y(n2)), ptimes(x(n2), y(n1))),
                    pminus(ptimes(x(n2), y(n3)), ptimes(x(n3), y(n2))),
                    pminus(ptimes(x(n3), y(n1)), ptimes(x(n1), y(n3)))));
      }

      xterm area_q(int n1, int n2, int n3, int n4)
      {
            return (pplus(area_t(n1, n2, n3), area_t(n1, n3, n4)));
      }

      xterm dis_xy(int n1, int n2)
//int n1,n2;
      {
            if (n1 == n2)
                  return (get_n(0L));
            else
                  return (pplus(ppower(pminus(x(n1), x(n2)), 2), ppower(pminus(y(n1), y(n2)), 2)));
      }

      xterm py_q(int n1, int n2, int n3, int n4)
//int n1,n2,n3,n4;
      {
            return (pminus(pplus(dis_xy(n1, n2), dis_xy(n3, n4)),
                    pplus(dis_xy(n2, n3), dis_xy(n4, n1))));
      }

      xterm oarea(int p1, int p2, int p3)
//int p1,p2,p3;
      {
            int n;
            if (xcir2(p2, p1, p3))
            {
                  n = p1;
                  p1 = p2;
                  p2 = p3;
                  p3 = n;
            }
            if (xcir2(p3, p1, p2))
            {
                  n = p1;
                  p1 = p3;
                  p3 = p2;
                  p2 = n;
            }
            return (ptimes4(get_m(mk_svar("\\d".toCharArray())), get_m(mk_svar("\\d".toCharArray())), trim_s(p2, p3), trim_c(p2, p3)));
      }

      xterm opy(int p1, int p2, int p3)
      {
            if (p1 == p2) return (pzero());
            if (p3 == p2) return (pzero());
            if (xcir2(p1, p2, p3)) return (ptimes3(get_n(2L), trim_l(p2, p3), trim_l(p2, p3)));
            if (xcir2(p3, p2, p1)) return (ptimes3(get_n(2L), trim_l(p2, p1), trim_l(p2, p1)));
            return (pminus(ptimes(get_m(mk_svar("\\d".toCharArray())), get_m(mk_svar("\\d".toCharArray()))),
                    ptimes3(get_n(2L), trim_l(p1, p3), trim_l(p1, p3))));
      }

      el_term pe_circle(var v_a, int p1, int p2, int p3, int p4)
      {
            int n;
            if (v_a.nm == 2)
            {
                  if (xcir4(0, p1, p2, p3, p4))
                        return (mk_elim(v_a,
                                pminus(ptimes3(trim_l(p1, p2), trim_l(p2, p4), trim_l(p4, p1)),
                                        ptimes3(trim_l(p3, p2), trim_l(p2, p4), trim_l(p4, p3))),
                                ptimes(get_n(2L), get_m(mk_svar("\\d".toCharArray())))));
                  else if (p1 == p2)
                        return (mk_elim(v_a, oarea(p1, p3, p4), get_n(4L)));
                  else if (xcir3(p1, p2, p3, p4) || xcir3(p3, p1, p2, p4))
                        return (mk_elim(v_a, pplus(oarea(p1, p2, p3), oarea(p1, p3, p4)), get_n(4L)));
                  else
                        return (mk_elim(v_a, pplus(oarea(p2, p3, p4), oarea(p2, p4, p1)), get_n(4L)));
            }
            if (v_a.nm == 3)
            {
                  if (p3 == p4)
                  {
                        n = p1;
                        p1 = p3;
                        p3 = n;
                        n = p2;
                        p2 = p4;
                        p4 = n;
                  }
                  if (xcir4(0, p1, p2, p3, p4))
                        return (mk_elim(v_a,
                                pminus(ptimes4(get_n(2L), trim_l(p2, p1), trim_l(p2, p4), trim_c(p1, p4)),
                                        ptimes4(get_n(2L), trim_l(p2, p3), trim_l(p2, p4), trim_c(p3, p4))),
                                get_n(1L)));
                  else if (p2 == p3)
                        return (mk_elim(v_a, opy(p1, p2, p4), get_n(2L)));
                  else if (xcir3(p2, p1, p3, p4) || xcir3(p4, p1, p2, p3))
                        return (mk_elim(v_a, pminus(opy(p1, p2, p4), opy(p3, p2, p4)), get_n(2L)));
                  else
                        return (mk_elim(v_a, pminus(opy(p2, p1, p3), opy(p4, p1, p3)), get_n(2L)));
            }
            gerror("pe_circle");
            return (null);
      }

      xterm sc(int n)
//int n;
      {
            if (n == 1) return (get_n(0L));
            return (get_m(mk_var(16, n, 0, 0, 0)));
      }

      xterm cc(int n)
//int n;
      {
            if (n == 1) return (get_n(1L));
            return (get_m(mk_var(17, n, 0, 0, 0)));
      }

      el_term pe_sc(var v_a, int p1, int p2)
//var  v_a;
//int p1,p2;
      {
            if (v_a.nm == 6)
            {
                  return (mk_elim(v_a, pminus(ptimes(sc(p2), cc(p1)), ptimes(sc(p1), cc(p2))), get_n(1L)));
            }
            if (v_a.nm == 7)
            {
                  return (mk_elim(v_a, pplus(ptimes(cc(p1), cc(p2)), ptimes(sc(p2), sc(p1))), get_n(1L)));
            }
            gerror("pe_sc");
            return (null);
      }


      el_term pe_v_bline(var v_a, int y, int u, int v)
//var  v_a;
//int y,u,v;
      {
            int n;
            if (u > v)
            {
                  n = u;
                  u = v;
                  v = n;
            }
            if (v_a.nm == 3)
                  return (pe_g_3bline(v_a, v_a.pt[0], v_a.pt[1], v_a.pt[2], v_a.pt[3], y, u, v));
            gerror("11111111111");
            return (null);
      }

      el_term pe_g_3bline(var v_a, int n1, int n2, int n3, int n4, int y, int u, int v)
      {
            if (n1 == v)
            {
                  return (mk_elim(v_a, get_m(mk_var(-3, n1, n2, n3, n4)), get_n(1L)));
            } else if (n3 == y && n2 == y && n1 == u && n4 == u)
            {
                  return (mk_elim(v_a, get_m(mk_var(-3, n1, n2, n3, n4)), get_n(1L)));
            } else if (n3 == y && n2 == y && n1 == v && n4 == v)
            {
                  return (mk_elim(v_a, get_m(mk_var(-3, n3, u, u, n2)), get_n(1L)));
            } else if (n3 == y && n2 == y && n1 == v && n4 == u)
            {
                  return (mk_elim(v_a, pplus(trim_g(v, u, u, v),
                          ptimes(get_n(2L), get_m(mk_var(-3, y, y, u, u)))),
                          get_n(-2L)));
            } else if (n1 == y && n2 == v && n3 == u && n4 == u)
            {
                  return (mk_elim(v_a, trim_g(v, v, u, u), get_n(2L)));
            } else if (n1 == y && n2 == v && n3 == v && n4 == u)
            {
                  return (mk_elim(v_a, trim_g(v, u, u, v), get_n(2L)));
            }
            gerror("pe_g_3bline");
            return (null);
      }

      el_term pe_v_square(var v_a, int p1, int p2, int p3, int sn)
//var  v_a;
//int p1,p2,p3,sn;
      {
            if (v_a.nm != 4) return (pe_v_tratio(v_a, p1, p2, p2, p3, get_n(1L * sn), get_n(1L)));
            if (v_a.pt[1] == 0)
            {
                  return (mk_elim(v_a,
                          pplus(trim_vec(p2, 0),
                                  ptimes3(get_n(1L * sn), get_s("i".toCharArray()), trim_vec(p3, p2))),
                          get_n(1L)));
            } else
            {
                  return (mk_elim(v_a,
                          pplus(trim_vec(p2, v_a.pt[1]),
                                  ptimes3(get_n(1L * sn), get_s("i".toCharArray()), trim_vec(p3, p2))),
                          get_n(1L)));
            }
      }

      el_term pe_v_ptri(var v_a, int p1, int p2, int p3)
//var  v_a;
//int p1,p2,p3;
      {
            if (v_a.pt[1] == 0)
            {
                  return (mk_elim(v_a,
                          pplus(ptimes3(get_n(-1L), get_s("w".toCharArray()), trim_vec(p2, 0)),
                                  ptimes4(get_n(-1L), get_s("w".toCharArray()), get_s("w".toCharArray()), trim_vec(p3, 0))),
                          get_n(1L)));
            } else
            {
                  return (mk_elim(v_a,
                          pplus3(ptimes3(get_n(-1L), get_s("w".toCharArray()), trim_vec(p2, 0)),
                                  ptimes4(get_n(-1L), get_s("w".toCharArray()), get_s("w".toCharArray()), trim_vec(p3, 0)),
                                  ptimes(get_n(-1L), trim_vec(v_a.pt[1], 0))),
                          get_n(1L)));
            }
      }

      el_term pe_v_ntri(var v_a, int p1, int p2, int p3)
//var  v_a;
//int p1,p2,p3;
      {
            if (v_a.nm != 4)
            {
                  gerror("pe_ntri");
            }
            if (v_a.pt[1] == 0)
            {
                  return (mk_elim(v_a,
                          pplus(ptimes3(get_n(-1L), get_s("w".toCharArray()), trim_vec(p3, 0)),
                                  ptimes4(get_n(-1L), get_s("w".toCharArray()), get_s("w".toCharArray()), trim_vec(p2, 0))),
                          get_n(1L)));
            } else
            {
                  return (mk_elim(v_a,
                          pplus3(ptimes3(get_n(-1L), get_s("w".toCharArray()), trim_vec(p3, 0)),
                                  ptimes4(get_n(-1L), get_s("w".toCharArray()), get_s("w".toCharArray()), trim_vec(p2, 0)),
                                  ptimes(get_n(-1L), trim_vec(v_a.pt[1], 0))),
                          get_n(1L)));
            }
      }

      el_term pe_v_sim(var v_a, int p1, int p2, int p3, int q1, int q2, int q3)
//var  v_a;
//int p1,p2,p3,q1,q2,q3;
      {
            if (v_a.nm != 4) gerror("pe_v_sim");
            if (v_a.pt[1] == 0)
            {
                  return (mk_elim(v_a,
                          pplus(ptimes(trim_vec(p2, 0), trim_vec(q2, q3)),
                                  ptimes(trim_vec(q2, q1), trim_vec(p2, p3))),
                          trim_vec(q2, q3)));
            } else
            {
                  return (mk_elim(v_a,
                          pplus3(ptimes3(get_n(-1L), trim_vec(q2, q3), trim_vec(v_a.pt[1], 0)),
                                  ptimes(trim_vec(p2, 0), trim_vec(q2, q3)),
                                  ptimes(trim_vec(q1, q2), trim_vec(p2, p3))),
                          trim_vec(q2, q3)));
            }
      }

      /////////////////////////////////////////////////////////////////////////elima.cpp
      el_term radius_e = new el_term();

      el_term rcir(int a, int b, int c)
      {
            radius_e.v = mk_svar("x".toCharArray());
            radius_e.p1 = ptimes3(trim_g(a, b, b, a), trim_g(a, c, c, a), trim_g(b, c, c, b));
            radius_e.p2 = ptimes3(get_n(64L), trim_a(a, b, c, c), trim_a(a, b, c, c));
            return (radius_e);
      }

      el_term pe_v_cent(var v_a, int y, int a, int b, int c)
      {
            int n;
            xterm p;

            if (v_a.nm == 2 ||
                    (v_a.nm == 3 && v_a.pt[0] == y && v_a.pt[1] != y) ||
                    v_a.nm == 4)
            {
                  return (mk_elim(v_a, pplus(geval(v_a, y, a),
                          pplus(geval(v_a, y, b), geval(v_a, y, c))), get_n(3L)));
            } else if (v_a.nm == 3 && v_a.pt[1] == y && v_a.pt[2] == y)
            {
                  p = pplus(geval(v_a, y, a), pplus(geval(v_a, y, b), geval(v_a, y, c)));
                  p = ptimes(get_n(3L), p);
                  p = pplus(p, pplus3(trim_g(a, a, b, b), trim_g(a, a, c, c), trim_g(b, b, c, c)));
                  return (mk_elim(v_a, p, get_n(9L)));
            } else if (v_a.nm == 1)
            {
                  if (v_a.pt[0] == y && v_a.pt[2] == y)
                  {
                        if (xcoll(c, y, v_a.pt[1]))
                        {
                              n = c;
                              c = b;
                              b = n;
                        }
                        if (xcoll(c, y, v_a.pt[1]))
                        {
                              n = c;
                              c = a;
                              a = n;
                        }
                        return (mk_elim(v_a,
                                pplus(trim_a(a, a, c, v_a.pt[1]), trim_a(b, b, c, v_a.pt[1])),
                                pplus(trim_a(a, a, c, v_a.pt[3]), trim_a(b, b, c, v_a.pt[3]))));
                  } else if (v_a.pt[0] == y)
                  {
                        return (mk_elim(v_a,
                                pplus(trim_a(a, a, c, v_a.pt[1]), trim_a(b, b, c, v_a.pt[1])),
                                ptimes(get_n(3L), trim_a(c, c, v_a.pt[2], v_a.pt[3]))));
                  } else if (v_a.pt[2] == y)
                  {
                        return (mk_elim(v_a,
                                ptimes(get_n(3L), trim_a(c, c, v_a.pt[0], v_a.pt[1])),
                                pplus(trim_a(a, a, c, v_a.pt[3]), trim_a(b, b, c, v_a.pt[3]))));
                  }
            }
            gerror("pe_v_cent");
            return (null);
      }


      el_term orth_md(var v_a, int y, int a, int b, int c)
      {
            return (mk_elim(v_a,
                    pplus3(ptimes3(geval(v_a, y, a), trim_g(a, b, b, c), trim_g(a, c, c, b)),
                            ptimes3(geval(v_a, y, b), trim_g(b, a, a, c), trim_g(b, c, c, a)),
                            ptimes3(geval(v_a, y, c), trim_g(c, a, a, b), trim_g(c, b, b, a))),
                    ptimes3(get_n(16L), trim_a(a, b, c, c), trim_a(a, b, c, c))));
      }

/*
el_term *pe_r_orth (v_a,n1,n2,n3,n4,y,a,b,c)
var *v_a;
int n1,n2,n3,n4,y,a,b,c;
{ gerror("pe_r_orth"); return(null);}

*/

      el_term pe_a_orth(var v_a, int n1, int n2, int n3, int n4, int y, int a, int b, int c)
//var  v_a;
//int n1,n2,n3,n4,y,a,b,c;
      {
            if (n1 != y) gerror("pe_a_orth");
            if (xperp(n2, n4, a, b))
                  return (mk_elim(v_a, trim_a(c, n2, n3, n4), get_n(1L)));
            else if (xperp(n2, n4, a, c))
                  return (mk_elim(v_a, trim_a(b, n2, n3, n4), get_n(1L)));
            else if (xperp(n2, n4, b, c))
                  return (mk_elim(v_a, trim_a(a, n2, n3, n4), get_n(1L)));
            else
                  return (orth_md(v_a, y, a, b, c));
      }

      el_term pe_g_orth(var v_a, int n1, int n2, int n3, int n4, int y, int a, int b, int c)
      {
            el_term e1, rr;
            int n;
            xterm r1, r2, r3, r4;

            e1 = null;
            if (n1 == y && n2 != y)
            {
                  if (xpara(n2, n4, a, b))
                        e1 = mk_elim(v_a, trim_g(c, n2, n3, n4), get_n(1L));
                  else if (xpara(n2, n4, a, c))
                        e1 = mk_elim(v_a, trim_g(b, n2, n3, n4), get_n(1L));
                  else if (xpara(n2, n4, b, c))
                        e1 = mk_elim(v_a, trim_g(a, n2, n3, n4), get_n(1L));
                  else
                        e1 = orth_md(v_a, y, a, b, c);
            } else if (n2 == y && n3 == y)
            {
                  if (n1 == n4 && (n1 == a || n1 == b || n1 == c))
                  {
                        if (n1 == b)
                        {
                              n = a;
                              a = b;
                              b = n;
                        }
                        if (n1 == c)
                        {
                              n = a;
                              a = c;
                              c = n;
                        }
                        e1 = mk_elim(v_a,
                                ptimes3(trim_g(b, b, c, c), trim_g(b, a, a, c), trim_g(b, a, a, c)),
                                ptimes3(get_n(-16L), trim_a(a, b, c, c), trim_a(a, b, c, c)));
                  } else if (n1 == n4 && xcir3(n1, a, b, c))
                  {
                        rr = rcir(a, b, c);
                        e1 = mk_elim(v_a,
                                pplus4(ptimes(get_n(9L), rr.p1),
                                        ptimes(trim_g(a, a, b, b), cp_poly(rr.p2)),
                                        ptimes(trim_g(b, b, c, c), cp_poly(rr.p2)),
                                        ptimes(trim_g(a, a, c, c), cp_poly(rr.p2))),
                                rr.p2);
                  } else if ((n1 == a || n1 == b || n1 == c) && (n4 == a || n4 == b || n4 == c))
                  {
                        e1 = mk_elim(v_a,
                                ptimes3(trim_g(a, b, b, c), trim_g(a, c, c, b), trim_g(b, a, a, c)),
                                ptimes3(get_n(-16L), trim_a(a, b, c, c), trim_a(a, b, c, c)));
                  } else
                  {
                        r1 = ptimes(trim_g(c, b, b, a), trim_g(c, a, a, b));
                        r2 = ptimes(trim_g(b, a, a, c), trim_g(b, c, c, a));
                        r3 = ptimes(trim_g(a, b, b, c), trim_g(a, c, c, b));
                        r4 = ptimes3(get_n(16L), trim_a(a, b, c, c), trim_a(a, b, c, c));
                        e1 = mk_elim(v_a, pplus(pplus3(ptimes3(cp_poly(r1), cp_poly(r3), trim_g(a, a, c, c)),
                                ptimes3(cp_poly(r2), cp_poly(r3), trim_g(a, a, b, b)),
                                ptimes3(cp_poly(r1), cp_poly(r2), trim_g(b, b, c, c))),
                                pplus3(ptimes3(cp_poly(r1), cp_poly(r4), trim_g(n1, c, c, n4)),
                                        ptimes3(cp_poly(r2), cp_poly(r4), trim_g(n1, b, b, n4)),
                                        ptimes3(cp_poly(r3), cp_poly(r4), trim_g(n1, a, a, n4)))),
                                ptimes(cp_poly(r4), cp_poly(r4)));
                  }
            } else
                  gerror("pe_g_orth");
            return (e1);
      }


      el_term circum_md(var v_a, int y, int a, int b, int c)
      {
            return (mk_elim(v_a,
                    pplus3(ptimes3(geval(v_a, y, a), trim_g(b, c, c, b), trim_g(b, a, a, c)),
                            ptimes3(geval(v_a, y, b), trim_g(a, c, c, a), trim_g(a, b, b, c)),
                            ptimes3(geval(v_a, y, c), trim_g(a, b, b, a), trim_g(a, c, c, b))),
                    ptimes3(get_n(32L), trim_a(a, b, c, c), trim_a(a, b, c, c))));
      }

      el_term pe_a_circum(var v_a, int n1, int n2, int n3, int n4, int y, int a, int b, int c)
      {
            int n;
            if (xperp(n2, n4, b, c))
            {
                  n = a;
                  a = c;
                  c = n;
            }
            if (xperp(n2, n4, a, c))
            {
                  n = b;
                  b = c;
                  c = n;
            }
            if (xperp(n2, n4, a, b))
                  return (mk_elim(v_a, pplus(trim_a(a, n2, n3, n4), trim_a(b, n2, n3, n4)), get_n(2L)));
            if (n3 == n4 && (xcir5(y, n2, n3, a, b, c)))
            {
                  if (a != n2 && a != n3)
                        n = a;
                  else if (b != n2 && b != n3)
                        n = b;
                  else
                        n = c;
                  return (mk_elim(v_a,
                          ptimes(trim_g(n2, n3, n3, n2), trim_g(n2, n, n, n3)),
                          ptimes(get_n(32L), trim_a(n, n2, n3, n4))));
            }
            return (circum_md(v_a, y, a, b, c));
      }

      el_term pe_g_circum(var v_a, int n1, int n2, int n3, int n4, int y, int a, int b, int c)
      {
            el_term e1;
            xterm r1, r2, r3, r4;

            e1 = null;
            if (n1 == y && n2 != y)
            {
                  if (xcir2(y, n2, n4))
                  {
                        e1 = mk_elim(v_a, pplus(trim_g(n2, n2, n3, n4), trim_g(n4, n2, n3, n4)), get_n(2L));
                  } else
                        e1 = circum_md(v_a, y, a, b, c);
            } else if (n2 == y && n3 == y)
            {
                  if (n1 == n4 && xcir4(y, n1, a, b, c))
                  {
                        e1 = rcir(a, b, c);
                        e1 = mk_elim(v_a, e1.p1, e1.p2);
                  } else if (xcir5(y, n1, n4, a, b, c))
                  {
                        r1 = ptimes3(trim_g(a, b, b, a), trim_g(a, c, c, a), trim_g(b, c, c, b));
                        r2 = ptimes3(get_n(64L), trim_a(a, b, c, c), trim_a(a, b, c, c));
                        e1 = mk_elim(v_a,
                                pplus(ptimes(get_n(2L), r1),
                                        ptimes(trim_g(n1, n1, n4, n4), r2)),
                                ptimes(get_n(2L), cp_poly(r2)));
                  } else
                  {
                        r1 = ptimes(trim_g(a, b, b, a), trim_g(a, c, c, b));
                        r2 = ptimes(trim_g(a, c, c, a), trim_g(a, b, b, c));
                        r3 = ptimes(trim_g(b, c, c, b), trim_g(b, a, a, c));
                        r4 = ptimes3(get_n(32L), trim_a(a, b, c, c), trim_a(a, b, c, c));
                        e1 = mk_elim(v_a,
                                pplus(pplus3(ptimes3(cp_poly(r1), cp_poly(r3), trim_g(a, a, c, c)),
                                        ptimes3(cp_poly(r2), cp_poly(r3), trim_g(a, a, b, b)),
                                        ptimes3(cp_poly(r1), cp_poly(r2), trim_g(b, b, c, c))),
                                        pplus3(ptimes3(cp_poly(r1), cp_poly(r4), trim_g(n3, c, c, n4)),
                                                ptimes3(cp_poly(r2), cp_poly(r4), trim_g(n3, b, b, n4)),
                                                ptimes3(cp_poly(r3), cp_poly(r4), trim_g(n3, a, a, n4)))),
                                ptimes(cp_poly(r4), cp_poly(r4)));
                  }
            } else
                  gerror("pe-g-circum2");
            return (e1);
      }


      el_term pe_r_circum(var v_a, int n1, int n2, int d1, int d2, int y, int a, int b, int c)
      {
            int a1 = 0, b1 = 0, c1 = 0;
            if (a == n1 || a == n2 || a == d1 || a == d2) a1 = a;
            if (b == n1 || b == n2 || b == d1 || b == d2) b1 = b;
            if (c == n1 || c == n2 || c == d1 || c == d2) c1 = c;
            if (c1 != 0 && !(a1 != 0))
            {
                  a1 = c;
                  b1 = b;
            } else if (c1 != 0 && !(b1 != 0))
            {
                  a1 = c;
                  b1 = a;
            } else
            {
                  a1 = a;
                  b1 = b;
            }


            if (n2 == y)
            {
                  if (d2 == y)
                        return (mk_elim(v_a,
                                pplus(trim_g(n1, a1, a1, b1), trim_g(n1, a1, b1, b1)),
                                pplus(trim_g(d1, a1, a1, b1), trim_g(d1, a1, b1, b1))));
                  else
                        return (mk_elim(v_a,
                                pplus(trim_g(n1, a1, a1, b1), trim_g(n1, a1, b1, b1)),
                                ptimes(get_n(2L), trim_g(d1, a1, d2, b1))));
            } else if (d2 == y)
                  return (rev_elim(pe_r_circum(v_a, d1, d2, n1, n2, y, a, b, c)));
            else
                  gerror("pe-r-circum1");
            return (null);
      }


      el_term incent_md(var v_a, int y, int i, int a, int b)
      {
            return (mk_elim(v_a,
                    pplus3(ptimes4(get_n(-2L), geval(v_a, y, i), trim_g(i, a, a, b), trim_g(i, b, b, a)),
                            ptimes3(geval(v_a, y, a), trim_g(i, a, a, b), trim_g(b, i, i, b)),
                            ptimes3(geval(v_a, y, b), trim_g(i, b, b, a), trim_g(a, i, i, a))),
                    ptimes(trim_g(a, i, i, b), trim_g(a, b, b, a))));
      }

      el_term pe_a_incent(var v_a, int n1, int n2, int n3, int n4, int y, int i, int a, int b)
      {
            return (incent_md(v_a, y, i, a, b));
      }

      el_term pe_g_incent(var v_a, int n1, int n2, int n3, int n4, int y, int i, int a, int b)
      {
            el_term e1;
            int n, sn;
            xterm r1, r2, r3, r4, r5, r6, ar;
            e1 = null;
            sn = 1;
            if (n1 == y && n2 != y)
            {
                  if (n3 == n4)
                  {
                        n = n2;
                        n2 = n4;
                        n4 = n;
                        sn = -1;
                  }
                  if (n2 == n3 && n4 == b)
                  {
                        n = a;
                        a = b;
                        b = n;
                  }
                  if (n2 == n3 && n2 == i && n4 == a)
                  {
                        e1 = mk_elim(v_a,
                                ptimes4(get_n(16L), trim_g(i, a, a, i), trim_a(i, a, a, b), trim_a(i, a, a, b)),
                                ptimes(trim_g(a, b, b, a), trim_g(a, i, i, b)));
                        if (sn == -1) e1.p1 = neg_poly(e1.p1);
                  } else
                        e1 = incent_md(v_a, y, i, a, b);
            } else if (n2 == y && n3 == y)
            {
                  r1 = trim_g(i, a, a, b);
                  r2 = trim_g(i, b, b, a);
                  r3 = trim_g(a, i, i, b);
                  r4 = trim_g(i, a, a, i);
                  r5 = trim_g(i, b, b, i);
                  r6 = trim_g(a, b, b, a);
                  ar = trim_a(a, i, i, b);
                  if (n1 == i && n4 == i)
                  {
                        e1 = mk_elim(v_a,
                                ptimes4(ptimes(get_n(16L), r4), r5, ar, cp_poly(ar)),
                                ptimes3(r3, cp_poly(r3), r6));
                        put_p(r1);
                        put_p(r2);
                  } else if (n1 == a && n4 == a)
                  {
                        e1 = mk_elim(v_a, ptimes4(r4, cp_poly(r4), r2, cp_poly(r2)),
                                ptimes4(get_n(1L), r6, r3, cp_poly(r3)));
                        put_p(r1);
                        put_p(r5);
                  } else if (n1 == b && n4 == b)
                  {
                        e1 = mk_elim(v_a, ptimes4(r5, cp_poly(r5), r1, cp_poly(r1)),
                                ptimes4(get_n(1L), r6, r3, cp_poly(r3)));
                        put_p(r2);
                        put_p(r4);
                  } else if ((n1 == a && n4 == b) || (n1 == b && n4 == a))
                  {
                        e1 = mk_elim(v_a,
                                ptimes3(r1, r2, pminus(ptimes(r4, r5), ptimes3(get_n(2L), r3, cp_poly(r3)))),
                                ptimes3(cp_poly(r3), cp_poly(r3), r6));
                  } else if ((n1 == a && n4 == i) || (n1 == i && n4 == a))
                  {
                        e1 = mk_elim(v_a, ptimes4(ptimes(get_n(-16L), r4), r2, ar, cp_poly(ar)),
                                ptimes3(r6, r3, cp_poly(r3)));
                        put_p(r1);
                        put_p(r5);
                  } else if ((n1 == b && n4 == i) || (n1 == i && n4 == b))
                  {
                        e1 = mk_elim(v_a, ptimes4(ptimes(get_n(16L), r5), r1, ar, cp_poly(ar)),
                                ptimes3(r6, r3, cp_poly(r3)));
                        put_p(r2);
                        put_p(r4);
                  } else
                  {
                        e1 = mk_elim(v_a,
                                pminus(pplus3(ptimes(ptimes3(get_n(-2L), r1, r2),
                                        ptimes3(cp_poly(r1), r5, trim_g(a, i, i, a))),
                                        ptimes(ptimes3(cp_poly(r1), cp_poly(r5), cp_poly(r2)),
                                                ptimes(r4, trim_g(a, b, b, a))),
                                        ptimes(ptimes3(get_n(-2L), cp_poly(r1), cp_poly(r2)),
                                                ptimes3(cp_poly(r2), cp_poly(r4), trim_g(b, i, i, b)))),
                                        pplus3(ptimes(ptimes3(get_n(-2L), cp_poly(r1), cp_poly(r2)),
                                                ptimes3(r3, r6, trim_g(n1, i, i, n1))), /* check trig_g */
                                                ptimes(ptimes3(cp_poly(r1), cp_poly(r5), cp_poly(r3)),
                                                        ptimes(cp_poly(r6), trim_g(n1, a, a, n1))),
                                                ptimes(ptimes3(cp_poly(r2), cp_poly(r4), cp_poly(r3)),
                                                        ptimes(cp_poly(r6), trim_g(n1, b, b, n1))))),
                                ptimes4(cp_poly(r3), cp_poly(r3), cp_poly(r6), cp_poly(r6)));
                        e1.p1 = neg_poly(e1.p1);
                  }
            } else
                  gerror("pe-g-incent2");

            return (e1);
      }


      el_term pe_r_incent(var v_a, int n1, int n2, int d1, int d2, int y, int i, int a, int b)
      {
            if (n2 == y && d2 == y)
            {
                  return (mk_elim(v_a,
                          pminus(ptimes(get_n(2L), trim_g(n1, a, a, b)), trim_g(a, b, b, a)),
                          pminus(ptimes(get_n(2L), trim_g(d1, a, a, b)), trim_g(a, b, b, a))));
            } else if (n2 == y)
            {
                  return (mk_elim(v_a,
                          pminus(ptimes(get_n(2L), trim_g(n1, a, a, b)), trim_g(a, b, b, a)),
                          trim_g(d1, a, d2, b)));
            } else if (d2 == y)
            {
                  return (rev_elim(pe_r_incent(v_a, d1, d2, n1, n2, y, i, a, b)));
            } else
                  gerror("pe_r_incenter");
            return (null);
      }
}
