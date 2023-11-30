package wprover;

import maths.PolyBasic;
import maths.TMono;
import maths.TPoly;
import gprover.cons;
import gprover.gib;
import gprover.cndg;

import java.util.Vector;


public class GeoPoly extends PolyBasic {
    static GeoPoly poly = new GeoPoly();
    static int[] zeron = new int[100];


    private GeoPoly() {
    }

    public static boolean addZeroN(int n) {
        if (n == 0) return false;

        for (int i = 0; true; i++) {
            if (zeron[i] == n)
                return false;
            if (zeron[i] == 0) {
                zeron[i] = n;
                return true;
            }
        }
    }

    public static void clearZeroN() {
        int i = 0;
        while (i < zeron.length)
            zeron[i++] = 0;
    }

    public static int[] getZeron() {
        return zeron;
    }

    public static GeoPoly getPoly() {
        return poly;
    }


    public static boolean vzero(int n) {
        for (int i = 0; i < zeron.length && zeron[i] != 0; i++) {
            if (zeron[i] == n)
                return true;
        }
        return false;
    }

    TMono ppth(int x, int c, int d ) {
        if (vzero(x))
            return null;
        else return pth(x, 1, 1);
    }

    TMono ppdd(int x, int y)//new and add
    {
        if (CMisc.POINT_TRANS) {
            if (vzero(x)) {
                if (vzero(y))
                    return (pzero());
                else
                    return (pth(y, -1, 1));
            } else {
                if (vzero(y))
                    return (pth(x, 1, 1));
                else
                    return (padd(pth(x, 1, 1), pth(y, -1, 1)));
            }
        } else {
            if (x == 0) {
                if (y == 0)
                    return (pzero());
                else
                    return (pth(y, -1, 1));
            } else {
                if (y == 0)
                    return (pth(x, 1, 1));
                else
                    return (padd(pth(x, 1, 1), pth(y, -1, 1)));
            }
        }
    }

    TMono sqdistance(int x1, int y1, int x2, int y2) //distance between two point
    {
        return (padd(pRtimes(ppdd(x1, x2), ppdd(x1, x2)),
                pRtimes(ppdd(y1, y2), ppdd(y1, y2))));
    }

    TMono eqdistance(int x1, int y1, int x2, int y2, //poly of equal distance
                     int x3, int y3, int x4, int y4) {
        return (pdif(sqdistance(x1, y1, x2, y2), sqdistance(x3, y3, x4, y4)));
    }

    TMono perpendicular(int x1, int y1, int x2, int y2,
                        int x3, int y3, int x4, int y4) {
        return (padd(pRtimes(ppdd(x1, x2), ppdd(x3, x4)),
                pRtimes(ppdd(y1, y2), ppdd(y3, y4))));
    }

    TMono parallel(int x1, int y1, int x2, int y2,
                   int x3, int y3, int x4, int y4) {
        return (pdif(pRtimes(ppdd(x3, x4), ppdd(y1, y2)),
                pRtimes(ppdd(x1, x2), ppdd(y3, y4))));
    }

    TMono collinear(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (pdif(pRtimes(ppdd(x2, x3), ppdd(y1, y2)),
                pRtimes(ppdd(x1, x2), ppdd(y2, y3))));
    }

    TMono ratio(int x1, int y1, int x2, int y2, int x3, int y3,
                int x4, int y4, int x5, int y5, int x6, int y6,
                int x7, int y7, int x8, int y8) {
        return pdif(ptimes(sqdistance(x1, y1, x2, y2), sqdistance(x7, y7, x8, y8)),
                ptimes(sqdistance(x3, y3, x4, y4), sqdistance(x5, y5, x6, y6)));
    }

    TMono eqangle3p(int x1, int y1, int x2, int y2, int x3, int y3,
                    int x4, int y4, int x5, int y5, int x6, int y6,
                    int x7, int y7, int x8, int y8, int x9, int y9, int xm) {

        TMono sx1 = pdif(pRtimes(ppdd(x3, x2), ppdd(y1, y2)), pRtimes(ppdd(x1, x2), ppdd(y3, y2)));
        TMono sy1 = padd(pRtimes(ppdd(x1, x2), ppdd(x3, x2)), pRtimes(ppdd(y1, y2), ppdd(y3, y2)));

        TMono sx2 = pdif(pRtimes(ppdd(x6, x5), ppdd(y4, y5)), pRtimes(ppdd(x4, x5), ppdd(y6, y5)));
        TMono sy2 = padd(pRtimes(ppdd(x4, x5), ppdd(x6, x5)), pRtimes(ppdd(y4, y5), ppdd(y6, y5)));

        TMono sx3 = pdif(pRtimes(ppdd(x9, x8), ppdd(y7, y8)), pRtimes(ppdd(x7, x8), ppdd(y9, y8)));
        TMono sy3 = padd(pRtimes(ppdd(x7, x8), ppdd(x9, x8)), pRtimes(ppdd(y7, y8), ppdd(y9, y8)));


        TMono mx1 = pRtimes(pcopy(sx1), pRtimes(pcopy(sy2), pcopy(sy3)));
        TMono mx2 = pRtimes(pcopy(sx2), pRtimes(pcopy(sy1), pcopy(sy3)));
        TMono mx3 = pRtimes(pcopy(sx3), pRtimes(pcopy(sy1), pcopy(sy2)));

        TMono t1 = padd(mx1, padd(mx2, mx3));
        TMono t2 = pRtimes(pcopy(sx1), pRtimes(pcopy(sx2), pcopy(sx3)));

        TMono t3 = pRtimes(pcopy(sx1), pRtimes(pcopy(sx2), pcopy(sy3)));
        TMono t4 = pRtimes(pcopy(sx1), pRtimes(pcopy(sx3), pcopy(sy2)));
        TMono t5 = pRtimes(pcopy(sx2), pRtimes(pcopy(sx3), pcopy(sy1)));

        TMono t = pRtimes(pcopy(sy1), pRtimes(pcopy(sy2), pcopy(sy3)));
        TMono mm1 = pdif(t1, t2);
        TMono mm2 = pdif(t, padd(t3, padd(t4, t5)));
        //return pdif(mm1, pRtimes(mm2, new TMono(xm, 1, 1)));
        return pdif(mm1, pRtimes(mm2, ppth(xm, 1, 1)));//new TMono(xm, 1, 1)));
    }

    TMono sangle(int x1, int y1, int x2, int y2, int x3, int y3, int d) {
        TMono m = new TMono(d, -1, 1);
        TMono m1 = pdif(ptimes(ppdd(y3, y2), ppdd(x1, x2)), ptimes(ppdd(y1, y2), ppdd(x3, x2)));
        TMono m2 = padd(ptimes(ppdd(y3, y2), ppdd(y1, y2)), ptimes(ppdd(x1, x2), ppdd(x3, x2)));
        return pdif(m1, ptimes(m2, m));
    }

    TMono specificangle(int x1, int degree) {
        if (degree == 0) {
            TMono m1 = new TMono(x1, 1, 1);
            return m1;
        } else if (degree == 15) {
            TMono m1 = new TMono(x1, 1, 2);
            TMono m2 = new TMono(x1, -4, 1);
            TMono m3 = new TMono(0, 1, 0);
            return padd(pdif(m1, m2), m3);

        } else if (degree == 30) {
            TMono m1 = new TMono(x1, 3, 2);
            TMono m2 = new TMono(0, 1, 0);
            return pdif(m1, m2);

        } else if (degree == 45) {
            TMono m1 = new TMono(x1, 1, 1);
            TMono m2 = new TMono(0, 1, 0);
            return pdif(m1, m2);

        } else if (degree == 60) {
            TMono m1 = new TMono(x1, 1, 2);
            TMono m2 = new TMono(0, 3, 0);
            return pdif(m1, m2);
        } else if (degree == 120) {
            TMono m1 = new TMono(x1, 1, 2);
            TMono m2 = new TMono(0, 3, 0);
            return pdif(m1, m2);

        }
        return null;
    }


    TMono eqangle(int x1, int y1, int x2, int y2, int x21, int y21, int x3, int y3,
                  int x4, int y4, int x5, int y5, int x51, int y51, int x6, int y6) {
        TMono sx1 = ppdd(x1, x2);
        TMono sx2 = ppdd(x3, x21);
        TMono sx3 = ppdd(x4, x5);
        TMono sx4 = ppdd(x6, x51);

        TMono sy1 = ppdd(y1, y2);
        TMono sy2 = ppdd(y3, y21);
        TMono sy3 = ppdd(y4, y5);
        TMono sy4 = ppdd(y6, y51);

        TMono s1 = this.pRtimes(poly.pcopy(sy2), poly.pcopy(sx1));
        TMono s2 = this.pRtimes(poly.pcopy(sy1), poly.pcopy(sx2));
        TMono t1 = pdif(s1, s2);

        TMono s3 = this.pRtimes(poly.pcopy(sx3), poly.pcopy(sx4));
        TMono s4 = this.pRtimes(poly.pcopy(sy3), poly.pcopy(sy4));
        TMono t2 = padd(s3, s4);

        s1 = this.pRtimes((sy4), (sx3));
        s2 = this.pRtimes((sx4), (sy3));
        TMono t3 = pdif(s1, s2);
        s3 = this.pRtimes((sx1), (sx2));
        s4 = this.pRtimes((sy1), (sy2));
        TMono t4 = padd(s3, s4);


        TMono r1 = pRtimes(t1, t2);
        TMono r2 = pRtimes(t3, t4);
        return this.pdif(r1, r2);
    }

    TMono cyclic(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        return eqangle(x1, y1, x3, y3, x2, y2, x1, y1, x4, y4, x2, y2);
    }

    TMono eqangle(int x1, int y1, int x2, int y2, int x3, int y3,
                  int x4, int y4, int x5, int y5, int x6, int y6) {
        TMono sx1 = ppdd(x1, x2);
        TMono sx2 = ppdd(x3, x2);
        TMono sx3 = ppdd(x4, x5);
        TMono sx4 = ppdd(x6, x5);

        TMono sy1 = ppdd(y1, y2);
        TMono sy2 = ppdd(y3, y2);
        TMono sy3 = ppdd(y4, y5);
        TMono sy4 = ppdd(y6, y5);

        TMono s1 = this.pRtimes(poly.pcopy(sy2), poly.pcopy(sx1));
        TMono s2 = this.pRtimes(poly.pcopy(sy1), poly.pcopy(sx2));
        TMono t1 = pdif(s1, s2);

        TMono s3 = this.pRtimes(poly.pcopy(sx3), poly.pcopy(sx4));
        TMono s4 = this.pRtimes(poly.pcopy(sy3), poly.pcopy(sy4));
        TMono t2 = padd(s3, s4);

        s1 = this.pRtimes((sy4), (sx3));
        s2 = this.pRtimes((sx4), (sy3));
        TMono t3 = pdif(s1, s2);
        s3 = this.pRtimes((sx1), (sx2));
        s4 = this.pRtimes((sy1), (sy2));
        TMono t4 = padd(s3, s4);


        TMono r1 = pRtimes(t1, t2);
        TMono r2 = pRtimes(t3, t4);
        // int n1 = plength(r1);
        // int n2 = plength(r2);
        return this.pdif(r1, r2);
    }

    TMono midpoint(int x1, int x2, int x3) {
        return padd(ppdd(x2, x1), ppdd(x2, x3));
    }


    TMono bisect(int x1, int y1, int x2, int y2, int x3, int y3) {
        TMono m1 = padd(ppdd(y3, y2), ppdd(y3, y1));
        TMono m2 = padd(ppdd(x3, x1), ppdd(x3, x2));

        return padd(pRtimes(m1, ppdd(y2, y1)), pRtimes(m2, ppdd(x2, x1)));
    }

    TMono bisect1(int x1, int y1, int x2, int y2, int x3, int y3) {
        TMono m1 = padd(ppdd(y1, y2), ppdd(y1, y3));
        TMono m2 = padd(ppdd(x1, x2), ppdd(x1, x3));

        return padd(pRtimes(m1, ppdd(y3, y2)), pRtimes(m2, ppdd(x3, x2)));
    }

    TMono ccline(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        TMono s1 = ppdd(x, x1);
        TMono s2 = ppdd(y, y1);
        TMono s3 = ppdd(x1, x2);
        TMono s4 = ppdd(y1, y2);
        TMono sa = padd(pRtimes(s1, pcopy(s1)), pRtimes(s2, pcopy(s2)));
        TMono sb = padd(pRtimes(s3, pcopy(s3)), pRtimes(s4, pcopy(s4)));
        TMono s = pdif(sa, sb);

        s1 = ppdd(x, x3);
        s2 = ppdd(y, y3);
        s3 = ppdd(x3, x4);
        s4 = ppdd(y3, y4);
        TMono sc = padd(pRtimes(s1, pcopy(s1)), pRtimes(s2, pcopy(s2)));
        TMono sd = padd(pRtimes(s3, pcopy(s3)), pRtimes(s4, pcopy(s4)));
        TMono t = pdif(sc, sd);
        return pdif(s, t);
    }

    TPoly squarept1(int x, int y, int x0, int y0, int x1, int y1, int x2, int y2, int ratio) {
        TMono m1, m2;
        m1 = m2 = null;
        if (ratio > 0) {
            m1 = padd(pctimes(ppdd(x, x0), ratio), ppdd(y1, y2));
            m2 = padd(pctimes(ppdd(y, y0), ratio), ppdd(x2, x1));
        } else if (ratio < 0) {
            m1 = padd(ppdd(x, x0), pctimes(ppdd(y1, y2), -ratio));
            m2 = padd(ppdd(y, y0), pctimes(ppdd(x2, x1), -ratio));
        }
        return newTPoly(m1, m2);
    }

    TPoly squarept2(int x, int y, int x0, int y0, int x1, int y1, int x2, int y2, int ratio) {
        TMono m1, m2;
        m1 = m2 = null;

        if (ratio > 0) {
            m1 = padd(pctimes(ppdd(x, x0), ratio), ppdd(y2, y1));
            m2 = padd(pctimes(ppdd(y, y0), ratio), ppdd(x1, x2));
        } else if (ratio < 0) {
            m1 = padd(ppdd(x, x0), pctimes(ppdd(y2, y1), -ratio));
            m2 = padd(ppdd(y, y0), pctimes(ppdd(x1, x2), -ratio));
        }
        return newTPoly(m1, m2);
    }

    TPoly mirrorPL(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        TMono m1 = this.perpendicular(x1, y1, x2, y2, x3, y3, x4, y4);
        TMono s1 = pRtimes(padd(ppdd(y2, y3), ppdd(y1, y3)), ppdd(x4, x3));
        TMono s2 = pRtimes(padd(ppdd(x2, x3), ppdd(x1, x3)), ppdd(y4, y3));
        TMono m2 = pdif(s1, s2);
        return newTPoly(m1, m2);
    }

    TPoly prop_point(int x1, int y1, int x2, int y2, int x3, int y3, int v1, int v2) {
        TMono s1 = pdif(pctimes(ppdd(x2, x1), v2), pctimes(ppdd(x1, x3), v1));
        TMono s2 = pdif(pctimes(ppdd(y2, y1), v2), pctimes(ppdd(y1, y3), v1));
//        printpoly(s1);
//        printpoly(s2);
        return newTPoly(s1, s2);
    }

    TPoly Pratio(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r1, int r2) {
        TMono s1 = pdif(pctimes(ppdd(x1, x2), r2), pctimes(ppdd(x4, x3), r1));
        TMono s2 = pdif(pctimes(ppdd(y1, y2), r2), pctimes(ppdd(y4, y3), r1));
        return newTPoly(s1, s2);
    }

    TPoly Tratio(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r1, int r2) {
        TMono s1 = pdif(pctimes(ppdd(x1, x2), r2), pctimes(ppdd(y4, y3), r1));
        TMono s2 = padd(pctimes(ppdd(y1, y2), r2), pctimes(ppdd(x4, x3), r1));
        return newTPoly(s1, s2);
    }

    TPoly barycenter(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        TMono m1 = padd(padd(ppdd(x1, x2), ppdd(x1, x3)), ppdd(x1, x4));
        TMono m2 = padd(padd(ppdd(y1, y2), ppdd(y1, y3)), ppdd(y1, y4));

        return newTPoly(m1, m2);
    }

    TPoly circumcenter(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        TMono m1 = padd(pRtimes(padd(ppdd(x1, x2), ppdd(x1, x3)), ppdd(x2, x3)),
                pRtimes(padd(ppdd(y1, y2), ppdd(y1, y3)), ppdd(y2, y3)));
        TMono m2 = padd(pRtimes(padd(ppdd(x1, x3), ppdd(x1, x4)), ppdd(x3, x4)),
                pRtimes(padd(ppdd(y1, y3), ppdd(y1, y4)), ppdd(y3, y4)));
        return newTPoly(m1, m2);

    }

    TPoly LCMeet(int xo, int yo, int xc, int yc, int xl, int yl, int x, int y) { //o,pc,pl,p
        TMono m1 = padd(ptimes(padd(ppdd(y, yo), ppdd(yc, yo)), ppdd(yl, yc)),
                ptimes(padd(ppdd(x, xo), ppdd(xc, xo)), ppdd(xl, xc)));
        TMono m2 = pdif(ptimes(ppdd(y, yc), ppdd(xl, xc)),
                ptimes(ppdd(x, xc), ppdd(yl, yc)));
        return newTPoly(m1, m2);
    }

    TPoly pn_eq_triangle(int x, int y, int x1, int y1, int x2, int y2, boolean p) {
        TMono m1 = eqdistance(x, y, x1, y1, x1, y1, x2, y2);
        TMono m2 = eqdistance(x, y, x2, y2, x1, y1, x2, y2);
        return newTPoly(m1, m2);
    }

    TMono ratio0(int x, int y, int z, int n1, int n2) {
        return (pdif(pctimes(ppdd(x, y), n2), pctimes(ppdd(y, z), n1)));
    }

    TMono sum3(int xa, int ya, int xb, int yb, int xc, int yc, int xd, int yd, int xe, int ye, int xf, int yf) {
        TMono x, y, z, x1, y1, z1, s, r;

        x = sqdistance(xa, ya, xb, yb);
        y = sqdistance(xc, yc, xd, yd);
        z = sqdistance(xe, ye, xf, yf);

        x1 = pcopy(x);
        y1 = pcopy(y);
        z1 = pcopy(z);
        TMono s1 = pdif(padd(pcopy(x1), pcopy(y1)), z1);
        s1 = pRtimes(pcopy(s1), s1);
        TMono s2 = pctimes(pRtimes(x1, y1), -4);

        return padd(s1, s2);
    }

    TMono sum3(CPoint a, CPoint b, CPoint c, CPoint d, CPoint e, CPoint f) {
        TMono x, y, z, x1, y1, z1, s, r;

        /* (x-y)^2 + z(z - 2(x + y)) */

        x = sqdistance(a.x1.xindex, a.y1.xindex, b.x1.xindex, b.y1.xindex);
        y = sqdistance(c.x1.xindex, c.y1.xindex, d.x1.xindex, d.y1.xindex);
        z = sqdistance(e.x1.xindex, e.y1.xindex, f.x1.xindex, f.y1.xindex);
        x1 = pcopy(x);
        y1 = pcopy(y);
        z1 = pcopy(z);
        s = pdif(x, y);
        s = pRtimes(s, pcopy(s));
        /* s = (x-y)^2 */

        x1 = pctimes(x1, -2);
        y1 = pctimes(y1, -2);
        r = padd(padd(x1, y1), z1);
        /* r = z - 2(x + y) */
        r = pRtimes(z, r);
        return padd(s, r);
    }

    TMono p_p_mulside(CPoint a, CPoint b, CPoint c, CPoint d, int t1, int t2) {
        TMono m1 = sqdistance(a.x1.xindex, a.y1.xindex, b.x1.xindex, b.y1.xindex);
        TMono m2 = sqdistance(c.x1.xindex, c.y1.xindex, d.x1.xindex, d.y1.xindex);
        return pdif(pctimes(m1, t2 * t2), pctimes(m2, t1 * t1));
    }

    TMono p_p_horizonal(CPoint a, CPoint b) {
        return ppdd(a.y1.xindex, b.y1.xindex);
    }

    TMono p_p_vertical(CPoint a, CPoint b) {
        return ppdd(a.x1.xindex, b.x1.xindex);
    }

    TMono l_c_tangent(CPoint a, CPoint b, CPoint c, CPoint d, CPoint o) {
        TMono p1, p2, p3;
        p1 = this.collinear(a.x1.xindex, a.y1.xindex, b.x1.xindex, b.y1.xindex, o.x1.xindex, o.y1.xindex);
        p2 = this.sqdistance(c.x1.xindex, c.y1.xindex, d.x1.xindex, d.y1.xindex);
        p3 = this.sqdistance(a.x1.xindex, a.y1.xindex, b.x1.xindex, b.y1.xindex);
        return pdif(pRtimes(p1, pcopy(p1)), pRtimes(p2, p3));
    }

    TMono c_c_tangent(CPoint a, CPoint b, CPoint o, CPoint c, CPoint d, CPoint o1) {
        return sum3(a, b, c, d, o, o1);
    }

    TPoly newTPoly(TMono m1, TMono m2) {
        TPoly poly = new TPoly();
        poly.setPoly(m1);
        TPoly poly1 = new TPoly();
        poly1.setPoly(m2);
        poly1.setNext(poly);
        poly.setNext(null);
        return poly1;
    }

//    public boolean nde(TMono m, Vector v)
//    {
//        if(this.plength(m) != 2)    return false;
//

    //    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    TMono n_ndg(TMono m, int z) {
        if (m == null) return null;
        return pdif(ptimes(pth(z, 1, 1), m), pth(0, 1, 0));
    }

    TMono parallel(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return parallel(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex,
                p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TMono perpendicular(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return perpendicular(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex,
                p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TMono eqdistance(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return eqdistance(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex,
                p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TMono collinear(CPoint p1, CPoint p2, CPoint p3) {
        return collinear(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
    }

    TMono cyclic(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return cyclic(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex,
                p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TMono isotropic(CPoint p1, CPoint p2) {
        TMono m1 = ptimes(ppdd(p1.x1.xindex, p2.x1.xindex), ppdd(p1.x1.xindex, p2.x1.xindex));
        TMono m2 = ptimes(ppdd(p1.y1.xindex, p2.y1.xindex), ppdd(p1.y1.xindex, p2.y1.xindex));
        return this.padd(m1, m2);
    }

    TMono triplePI(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        TMono m1 = ptimes(ppdd(p1.y1.xindex, p2.y1.xindex), ppdd(p3.y1.xindex, p4.y1.xindex));
        TMono m2 = ptimes(ppdd(p1.x1.xindex, p2.x1.xindex), ppdd(p3.x1.xindex, p4.x1.xindex));
        TMono m = ptimes(m1, m2);

        m1 = ptimes(ppdd(p1.y1.xindex, p2.y1.xindex), ppdd(p1.y1.xindex, p2.y1.xindex));
        m2 = ptimes(ppdd(p3.x1.xindex, p4.x1.xindex), ppdd(p3.x1.xindex, p4.x1.xindex));
        TMono n = pctimes(ptimes(m1, m2), 3);

        m1 = ptimes(ppdd(p1.y1.xindex, p2.y1.xindex), ppdd(p1.y1.xindex, p2.y1.xindex));
        m2 = ptimes(ppdd(p1.y1.xindex, p2.y1.xindex), ppdd(p1.y1.xindex, p2.y1.xindex));
        TMono x = ptimes(m1, m2);

        return ptimes(m, this.padd(n, x));

    }

    ////////////////////////////////////////////////////
    public TMono mm_poly(cndg c, drawProcess dp) {
        if (c == null) return null;
        switch (c.type) {
            case gib.NDG_NEQ:
                return isotropic(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]));
            case gib.NDG_COLL:
                return this.collinear(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]));
            case gib.NDG_CONG:
                return this.eqdistance(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]), dp.fd_point(c.p[3]));
            case gib.NDG_CYCLIC:
                return this.cyclic(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]), dp.fd_point(c.p[3]));
            case gib.NDG_NON_ISOTROPIC:
                return this.isotropic(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]));
            case gib.NDG_PARA:
                return this.parallel(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]), dp.fd_point(c.p[3]));
            case gib.NDG_PERP:
                return this.perpendicular(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]), dp.fd_point(c.p[3]));
            case gib.NDG_TRIPLEPI:
                return this.triplePI(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]), dp.fd_point(c.p[2]), dp.fd_point(c.p[3]));


            default:
                return null;
        }
    }

    public Vector ndg_gbasis(Vector v, Vector vp) {
        Vector v1 = new Vector();
        if (v.size() == 0)
            return v1;

        int n = v.size() + 2;
        int d = -1;
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            m = this.n_ndg(m, d);
            v1.add(m);
            d--;
        }

        this.upValueTM(v1, n);
        this.upValueTM(vp, n);
        this.g_basis(v1);

        this.upValueTM(v1, -n);
        this.upValueTM(vp, -n);
        return v1;
    }


}
