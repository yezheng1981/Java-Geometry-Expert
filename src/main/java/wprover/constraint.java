package wprover;

import gprover.gib;
import gprover.cons;

import java.util.Vector;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

import maths.TPoly;
import maths.param;
import maths.TMono;


public class constraint {
    final public static int NULLTYPE = 0;
    final public static int PONLINE = 11;  // collinear
    final public static int PONCIRCLE = 12; // circle
    final public static int PARALLEL = 2;            // para
    final public static int PERPENDICULAR = 3;              // perp
    final public static int PFOOT = 31;                            // foot
    final public static int EQDISTANCE = 4;                    // eqdistance PC

    final public static int COLLINEAR = 1;
    final public static int PERPBISECT = 5;

    final public static int MIDPOINT = 6;      // midpoint

    final public static int EQANGLE = 7;
    final public static int LCTANGENT = 9;
    final public static int CCTANGENT = 10;

    final public static int LRATIO = 19;
    final public static int RCIRCLE = 20;
    final public static int CIRCUMCENTER = 21;
    final public static int BARYCENTER = 22;
    final public static int ORTHOCENTER = 37;
    final public static int INCENTER = 44;
    final public static int BISECT = 13;
    final public static int CCLine = 14;
    final public static int TRATIO = 15;

    final public static int PRATIO = 17;
    final public static int MIRROR = 18;
    final public static int HORIZONAL = 23;
    final public static int VERTICAL = 24;
    final public static int VISIBLE = 25;
    final public static int INVISIBLE = 26;
    final public static int NRATIO = 27;
    final public static int LC_MEET = 28;
    final public static int EQANGLE3P = 29;
    final public static int SPECIFIC_ANGEL = 30;
    final public static int SYMPOINT = 36;
    final public static int P_O_A = 38;
    final public static int INTER_CC1 = 32;
    final public static int PSQUARE = 33;
    final public static int NSQUARE = 34;
    final public static int NTANGLE = 35;
    final public static int PETRIANGLE = 39;
    final public static int NETRIANGLE = 40;
    final public static int CONSTANT = 43;
    final public static int SQUARE = 41;
    final public static int ALINE = 42;
    final public static int PSYM = 45;
    final public static int INTER_LL = 46;
    final public static int INTER_LC = 47;
    final public static int INTER_CC = 48;

    final public static int TRIANGLE = 59;
    final public static int ISO_TRIANGLE = 50;
    final public static int EQ_TRIANLE = 51;
    final public static int RIGHT_ANGLED_TRIANGLE = 52;
    final public static int ISO_RIGHT_ANGLED_TRIANGLE = 53;
    final public static int QUADRANGLE = 54;
    final public static int PARALLELOGRAM = 55;
    final public static int TRAPEZOID = 56;
    final public static int RIGHT_ANGLE_TRAPEZOID = 57;
    final public static int RECTANGLE = 58;
    final public static int PENTAGON = 60;
    final public static int POLYGON = 64;
    final public static int SANGLE = 65;
    final public static int ANGLE_BISECTOR = 66;
    final public static int BLINE = 67;
    final public static int TCLINE = 68;
    final public static int RATIO = 69;
    final public static int TRANSFORM = 70;
    final public static int EQUIVALENCE1 = 71;
    final public static int EQUIVALENCE2 = 72;
    final public static int TRANSFORM1 = 73;


    final public static int LINE = 61;
    final public static int CIRCLE = 62;
    final public static int CIRCLE3P = 63;
    final public static int CCTANGENT_LINE = 74;
    final public static int ONSCIRCLE = 75;
    final public static int ON_ABLINE = 76;
    final public static int ONDCIRCLE = 77;
    final public static int ONRCIRCLE = 78;
    final public static int ONALINE = 79;


    private static GeoPoly poly = GeoPoly.getPoly();

    private static TPoly polylist = null;

    int id = CMisc.id_count++;
    private int ConstraintType = 0;
    private Vector elementlist = new Vector();
    int proportion = 1;
    boolean is_poly_genereate = true;

    cons csd = null;
    cons csd1 = null;

    public static TPoly getPolyListAndSetNull() {
        TPoly pl = polylist;
        polylist = null;
        TPoly p2 = pl;
        while (p2 != null) {
            poly.coefgcd(p2.poly);
            p2 = p2.next;
        }
        return pl;
    }

    public int GetConstraintType() {
        return this.ConstraintType;
    }

    public Object getelement(int i) {
        if (i >= 0 && i < elementlist.size())
            return elementlist.get(i);
        else
            return null;
    }

    public CPoint getLPoints2(CPoint p1, CPoint p2) {
        for (int i = 0; i < elementlist.size(); i++) {
            CPoint p = (CPoint) elementlist.get(i);
            if (p != p1 && p != p2)
                return p;
        }
        return null;
    }

    public boolean cotainPoints(CPoint p1, CPoint p2) {
        return elementlist.contains(p1) && elementlist.contains(p2);
    }

    public Vector getAllElements() {
        Vector v = new Vector();
        v.addAll(elementlist);
        return v;
    }

    public constraint(int id) {
        this.id = id;
    }

    public void calculate(param[] para) {
        if (polylist == null) return;
        poly.calculv(polylist.getPoly(), para);
        is_poly_genereate = false;
    }

    public constraint(int type, Object obj1, Object obj2, boolean gpoly) {
        this.ConstraintType = type;
        elementlist.add(obj1);
        elementlist.add(obj2);
        this.is_poly_genereate = gpoly;
        if (gpoly) {
            PolyGenerate();
        }
    }

    public constraint(int type, Vector olist) {
        this.ConstraintType = type;
        elementlist.addAll(olist);
        PolyGenerate();
    }

    public constraint(int type, Object obj) {
        this.ConstraintType = type;
        elementlist.add(obj);
    }

    public constraint(int type, Object obj1, Object obj2)//
    {
        this.ConstraintType = type;
        elementlist.add(obj1);
        elementlist.add(obj2);
        if (type == VERTICAL || type == HORIZONAL || type == COLLINEAR || type == PONCIRCLE
                || type == PONLINE || type == EQANGLE || type == CCTANGENT
                || type == PERPBISECT || type == LINE || type == CIRCLE)
            PolyGenerate();
        else if (type == PERPENDICULAR) {
            CLine line1 = (CLine) obj1;
            CLine line2 = (CLine) obj2;
            if (line1.points.size() >= 2 && line2.points.size() >= 2)
                PolyGenerate();
        }
    }

    public constraint(int type, Object obj1, Object obj2, Object obj3, int prop) {
        this.ConstraintType = type;
        elementlist.add(obj1);
        elementlist.add(obj2);
        elementlist.add(obj3);
        this.proportion = prop;
        if (is_poly_genereate)
            PolyGenerate();

    }

    public constraint(int type, Object obj1, int prop) {
        this.ConstraintType = type;
        elementlist.add(obj1);
        this.proportion = prop;
        if (is_poly_genereate)
            PolyGenerate();

    }

    public constraint(int type, Object obj1, Object obj2, Object obj3, Object obj4, int prop) {
        this.ConstraintType = type;
        elementlist.add(obj1);
        elementlist.add(obj2);
        elementlist.add(obj3);
        elementlist.add(obj4);
        this.proportion = prop;
        if (is_poly_genereate)
            PolyGenerate();
    }

    public constraint(int type, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) {
        this.ConstraintType = type;
        addelement(obj1);
        addelement(obj2);
        addelement(obj3);
        addelement(obj4);
        addelement(obj5);
        if (is_poly_genereate)
            PolyGenerate();
    }

    public constraint(int type, Object obj1, Object obj2, Object obj3)//
    {
        this.ConstraintType = type;
        addelement(obj1);
        addelement(obj2);
        addelement(obj3);
        if (is_poly_genereate)
            PolyGenerate();
    }

    public void addelement(Object obj) {
        if (obj != null)
            elementlist.add(obj);
    }

    public constraint(int type, Object obj1, Object obj2, Object obj3, Object obj4)//
    {
        this.ConstraintType = type;
        addelement(obj1);
        addelement(obj2);
        addelement(obj3);
        addelement(obj4);
        if (is_poly_genereate)
            PolyGenerate();
    }

    public constraint(int type, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)//
    {
        this.ConstraintType = type;
        addelement(obj1);
        addelement(obj2);
        addelement(obj3);
        addelement(obj4);
        addelement(obj5);
        addelement(obj6);
        if (is_poly_genereate)
            PolyGenerate();
    }


    public void setPolyGenerate(boolean r) {
        is_poly_genereate = r;
    }

    public void addElement(Object obj) {
        elementlist.add(obj);
    }

    String getMessage() {
        return toString();
    }

    public String toString() {
        if (csd != null)
            return csd.toDString();

        if (this.ConstraintType == constraint.SPECIFIC_ANGEL) {
            param p = (param) elementlist.get(0);
            return "x" + p.xindex + "| angle = " + p.type;
        }

        int num = this.elementlist.size();
        CClass e1, e2, e3, e4;
        e1 = e2 = e3 = e4 = null;

        for (int i = 0; i < num; i++) {
            if (i == 0)
                e1 = (CClass) elementlist.get(0);
            else if (i == 1)
                e2 = (CClass) elementlist.get(1);
            else if (i == 2)
                e3 = (CClass) elementlist.get(2);
            else if (i == 3)
                e4 = (CClass) elementlist.get(3);
        }

        switch (this.ConstraintType) {
            case PONLINE: {
                return e1.TypeString() + " on " + e2.getDescription();
            }
            case PONCIRCLE: {
                return e1.TypeString() + " on " + e2.getDescription();
            }
            case PARALLEL: {
                return "parallel " + e1.getDescription() + " " + e2.getDescription();
            }
            case PERPENDICULAR:
                return e1.TypeString() + " perpendicular to " + e2.TypeString();
            case PFOOT:
                return "Foot " + e1.TypeString() + " " + e2.TypeString() + " " + e3.TypeString() + " " + e4.TypeString();
            case MIDPOINT:
                return e1.TypeString() + "is the middle point of " + e2.TypeString() + " and " + e3.TypeString();
            case EQDISTANCE:
                return e1.TypeString() + " equal to " + e2.TypeString();
            case EQANGLE:
                return e1.TypeString() + " equal to " + e2.TypeString();
            case COLLINEAR:
                return e1.TypeString() + " " + e2.TypeString() + " are collinear";
            case CCTANGENT:
                return e1.TypeString() + " is tangent to " + e2.TypeString();
            case CCLine:
                return e1.TypeString() + " is the axes of " + e2.TypeString() + " and " + e3.TypeString();
            case TRATIO:
                return "T(" + e4.m_name + e3.m_name + ") / " + "T(" + e1.m_name + e2.m_name + ") = 1 : " + new Integer(this.proportion).toString();
            case PERPBISECT:
                return e1.TypeString() + " is on the perpendicular bisector of " + e2.m_name + " " + e3.TypeString();
            case MIRROR:

            case PRATIO:
                return "o(" + e1.m_name + e2.m_name + ") / " + "o(" + e3.m_name + e4.m_name + ") = 1 : " + new Integer(this.proportion).toString();
            case LRATIO:
                return "";
            case CIRCUMCENTER:
                return e1.TypeString() + "is the circumcenter of " + e2.getname() + e3.getname() + e4.getname();
            case BARYCENTER:
                return e1.TypeString() + " is the barycenter" + e2.getname() + e3.getname() + e4.getname();
            case LCTANGENT:
                return e1.TypeString() + " tangent to " + e2.TypeString();
            case HORIZONAL:
                return "set " + e1.TypeString() + e2.TypeString() + " a horizonal line";
            case VERTICAL:
                return "set " + e1.TypeString() + e2.TypeString() + " a vertical line";

        }

        return new String();
    }

    public void clear_all_cons() {
        csd = null;
        csd1 = null;
    }

    TPoly PolyGenerate() {
        TMono tm = null;
        TPoly tp = null;

        switch (this.ConstraintType) {
            case LINE:
//                this.add_des(gib.C_LINE, elementlist);
                break;
            case CIRCLE:
                this.add_des(gib.C_CIRCLE, elementlist);
                break;
            case TRIANGLE:
                add_des(gib.C_TRIANGLE, elementlist);
                break;
            case QUADRANGLE:
                add_des(gib.C_QUADRANGLE, elementlist);
                break;
            case PENTAGON:
                add_des(gib.C_PENTAGON, elementlist);
                break;
            case POLYGON:
                add_des(gib.C_POLYGON, elementlist);
                break;
            case RIGHT_ANGLED_TRIANGLE:
                tm = PolyRightTriangle();
                break;
            case RIGHT_ANGLE_TRAPEZOID:
                tp = PolyRTrapezoid();
                break;
            case PARALLELOGRAM:
                tp = PolyParallelogram();
                break;
            case TRAPEZOID:
                tm = PolyTrapezoid();
                break;
            case RECTANGLE:
                tp = PolyRectangle();
                break;
            case SQUARE:
                add_des(gib.C_SQUARE, elementlist);
                break;
            case PONLINE:  // p_o_L
                tm = this.PolyOnLine();
                break;
            case PONCIRCLE:        // p_o_C
                tm = this.PolyOnCircle();
                break;
            case INTER_LL:
                tp = PolyIntersection_ll();
                break;
            case INTER_LC:
                tp = PolyIntersection_lc();
                break;
            case INTER_CC:
                tp = PolyIntersection_CC();
                break;
            case PARALLEL: // P_O_P
                tm = this.PolyParel();
                break;
            case PERPENDICULAR:    // p_o_T
                tm = this.PolyPerp();
                break;
            case PFOOT:   // LT
                tp = this.PolyPfoot();
                break;
            case MIDPOINT:     // MID
                tp = this.PolyMidPoint();
                break;
            case EQDISTANCE:         //
                tm = this.PolyEqidstance();
                break;
            case EQANGLE:              // P_O_A
                tm = this.PolyEqAngle();
                break;
            case COLLINEAR:         // P_O_L
                tm = this.collinear();
                break;
            case CCTANGENT:
                tm = this.PolyCCTangent();
                break;
            case CCLine: {
            }
            break;
            case TRATIO:
                tp = this.PolyTRatio();
                break;
            case PERPBISECT: // p_O_B
                tm = this.PolyPerpBisect();
                break;
            case ISO_TRIANGLE:
                tm = PolyISOTriangle();
                break;
            case MIRROR:      // P_REF
                tp = this.PolyMirror();
                break;
            case PRATIO:
                tp = this.PolyPratio();
                break;
            case LRATIO:
                tp = this.PolyPropPoint();
                break;
            case CIRCUMCENTER:        // P_CIR
                tp = this.PolyCircumCenter();
                break;
            case BARYCENTER:
                tp = this.PolyBaryCenter();
                break;
            case LCTANGENT:
                tp = this.PolyLCTangent();
                break;
            case HORIZONAL:
                tm = this.PolyHorizonal();
                break;
            case VERTICAL:
                tm = this.PolyVertical();
                break;
            case NRATIO:
                tm = this.polyMulside();
                break;
            case LC_MEET:
                tp = this.PolyLCMeet();
                break;
            case EQANGLE3P:
                tm = this.PolyEqAngle3P();
                break;
            case SPECIFIC_ANGEL:
                tm = this.PolySpecifiAngle();
                break;
            case INTER_CC1:
                tp = this.PolyInterCC();
                break;
            case PSQUARE:
                tp = this.PolyPsquare();
                break;
            case NSQUARE:
                tp = this.PolyQsquare();
                break;
            case NTANGLE:
                tm = this.PolyNTAngle();
                break;
            case SYMPOINT:
                tp = this.PolySymPoint();
                break;
            case ORTHOCENTER:
                tp = this.PolyOrthoCenter();
                break;
            case INCENTER:
                tp = this.PolyInCenter();
                break;
            case P_O_A:
                tm = this.polyPonALine();
                break;
            case PETRIANGLE:
                tp = this.PolyPeTriangle();
                break;
            case NETRIANGLE:
                break;
            case ALINE:
                tm = this.PolyALine();
                break;
            case SANGLE:
                tm = this.PolySAngle();
                break;
            case PSYM:
                tp = this.PolyPsym();
                break;
            case ANGLE_BISECTOR:
                tm = PolyAngleBisector();
                break;
            case CIRCLE3P:
                tp = PolyCircle3P();
                break;
            case BLINE:
                tm = PolyBLine();
                break;
            case TCLINE:
                tm = PolyTCLine();
                break;
            case RATIO:
                tm = PolyRatio();
                break;
            case CCTANGENT_LINE:
                tp = PolyCCTangentLine();
                break;
            case ONSCIRCLE:
                tm = PolyONSCircle();
                break;
            case ON_ABLINE:
                tm = PolyONABLine();
                break;
            case ONDCIRCLE:
                tm = PolyONDCircle();
                break;
            case ONRCIRCLE:
                tm = PolyONRCircle();
                break;
            case ONALINE:
                tm = PolyONALine();
                break;
            case CONSTANT:
                tm = PolyConstant();
                break;

        }

        if (tm != null) {
            TPoly t = new TPoly();
            t.setPoly(tm);
            addPoly(t);
        }
        if (tp != null)
            addPoly(tp);
        return polylist;
    }

    public void addPoly(TPoly tp) {
        TPoly t = tp;
        while (t.getNext() != null)
            t = t.getNext();
        t.setNext(polylist);
        polylist = tp;
    }

    public boolean optimizePolygon(TPoly p) {
        boolean a = false;
        while (p != null) {
            TMono m = p.getPoly();
            if (m != null && poly.plength(m) == 1) {
                boolean d = poly.addZeroN(m.x);
                if (d)
                    a = true;
            }
            p = p.getNext();
        }
        return a;
    }

    TMono PolyConstant() {
        String t1 = elementlist.get(0).toString();
        String t2 = elementlist.get(1).toString();
        param p1 = (param) elementlist.get(2);

        return parseTMonoString(t1, t2, p1.xindex);
    }

    TMono PolyONALine() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        CPoint p5 = (CPoint) elementlist.get(4);
        CPoint p6 = (CPoint) elementlist.get(5);
        if (elementlist.size() > 6) {
            CPoint p7 = (CPoint) elementlist.get(6);
            CPoint p8 = (CPoint) elementlist.get(7);
            add_des(gib.C_O_A, p1, p2, p3, p4, p5, p6, p7, p8);
            return poly.eqangle(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                    p4.x1.xindex, p4.y1.xindex, p5.x1.xindex, p5.y1.xindex, p6.x1.xindex, p6.y1.xindex,
                    p7.x1.xindex, p7.y1.xindex, p8.x1.xindex, p8.y1.xindex);

        } else {
            add_des(gib.C_O_A, p1, p2, p3, p4, p5, p6);
            return poly.eqangle(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                    p4.x1.xindex, p4.y1.xindex, p5.x1.xindex, p5.y1.xindex, p6.x1.xindex, p6.y1.xindex);
        }
    }

    TMono PolyONRCircle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_O_R, p1, p2, p3, p4);
        return poly.eqdistance(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p4.x1.xindex, p4.y1.xindex, p3.x1.xindex, p3.y1.xindex);
    }

    TMono PolyONDCircle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        add_des(gib.C_O_D, p1, p2, p3);
        return poly.perpendicular(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p3.x1.xindex, p3.y1.xindex);
    }

    TMono PolyONABLine() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_O_AB, p1, p2, p3, p4);
        return poly.eqangle(p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p1.x1.xindex, p1.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TMono PolyONSCircle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_O_S, p1, p2, p3, p4);
        return poly.cyclic(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    TPoly PolyCCTangentLine() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        Circle c1 = (Circle) elementlist.get(2);
        Circle c2 = (Circle) elementlist.get(3);
        CPoint t1 = c1.getSidePoint();
        CPoint t2 = c2.getSidePoint();
        CPoint o1 = c1.o;
        CPoint o2 = c2.o;

        TMono m1 = poly.perpendicular(o1.x1.xindex, o1.y1.xindex, p1.x1.xindex, p1.y1.xindex, p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        TMono m2 = poly.perpendicular(o2.x1.xindex, o2.y1.xindex, p2.x1.xindex, p2.y1.xindex, p2.x1.xindex, p2.y1.xindex, p1.x1.xindex, p1.y1.xindex);
        TMono m3 = eqdistance(o1, p1, o1, t1);
        TMono m4 = eqdistance(o2, p2, o2, t2);
        TPoly poly1 = this.NewTPoly(m1, m2);
        TPoly poly2 = this.NewTPoly(m3, m4);
        poly1.getNext().setNext(poly2);
        return poly1;

    }

    TMono PolyRatio() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        CPoint p5 = (CPoint) elementlist.get(4);
        CPoint p6 = (CPoint) elementlist.get(5);
        CPoint p7 = (CPoint) elementlist.get(6);
        CPoint p8 = (CPoint) elementlist.get(7);
        add_des(gib.C_RATIO, p1, p2, p3, p4, p5, p6, p7, p8);
        return poly.ratio(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex,
                p5.x1.xindex, p5.y1.xindex, p6.x1.xindex, p6.y1.xindex,
                p7.x1.xindex, p7.y1.xindex, p8.x1.xindex, p8.y1.xindex);
    }

    TMono PolyBLine() {
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);
        CLine ln = (CLine) elementlist.get(0);
        int n = ln.getPtsSize();
        if (n == 0 || n > 2) return null;
        {
            CPoint p = (CPoint) ln.getPoint(n - 1);
            add_des(gib.C_O_B, p, p1, p2);
            return poly.bisect1(p.x1.xindex, p.y1.xindex, p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        }

    }

    TMono PolyTCLine() {
        Circle c = (Circle) elementlist.get(0);
        CLine ln = (CLine) elementlist.get(1);
        int n = ln.getPtsSize();

        if (n < 2) return null;
        {
            CPoint p1 = null;
            for (int i = 0; i < c.psize(); i++)
                if (ln.containPT((p1 = c.getP(i))))
                    break;

            CPoint p = (CPoint) ln.get_Lpt1(p1);
            CPoint p2 = c.o;
            add_des(gib.C_LC_TANGENT, p, p1, p2);
            return poly.perpendicular(p.x1.xindex, p.y1.xindex, p1.x1.xindex, p1.y1.xindex,
                    p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        }

    }

    TPoly PolyCircle3P() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_CIRCUM, p1, p2, p3, p4);

        return poly.circumcenter(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex);
    }

    TMono PolyAngleBisector() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CLine ln = (CLine) elementlist.get(3);
        CPoint p = ln.getSecondPoint(p2);
        if (p != null) {
            add_des(gib.C_ANGLE_BISECTOR, p, p1, p2, p3);

            return poly.eqangle(p2.x1.xindex, p2.y1.xindex, p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p.x1.xindex, p.y1.xindex,
                    p2.x1.xindex, p2.y1.xindex, p.x1.xindex, p.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
        }
        return null;
    }

    TPoly PolyPsym()  // p1 is the mirror of p2 through p3
    {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        add_des(gib.C_REF, p1, p2, p3);
        TMono m1 = poly.midpoint(p2.x1.xindex, p3.x1.xindex, p1.x1.xindex); // p3 in mid of p1, p2
        TMono m2 = poly.midpoint(p2.y1.xindex, p3.y1.xindex, p1.y1.xindex);
        return this.NewTPoly(m1, m2);
    }

    TPoly PolySquare() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_SQUARE, p1, p2, p3, p4);
        TPoly t1 = poly.squarept1(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, this.proportion);
        TPoly t2 = poly.squarept2(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p4.x1.xindex, p4.y1.xindex, this.proportion);
        TPoly t = t2;
        while (t2.getNext() != null)
            t2 = t2.getNext();
        t2.setNext(t1);
        return t1;
    }

    TPoly PolyPeTriangle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        add_des(gib.C_EQ_TRI, p1, p2, p3);
        return poly.pn_eq_triangle(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, true);
    }

    TMono polyPonALine() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        CPoint p5 = (CPoint) elementlist.get(4);
        CPoint p6 = (CPoint) elementlist.get(5);
        add_des(gib.C_O_A, p1, p2, p3, p4, p5);
        return poly.eqangle(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex, p5.x1.xindex, p5.y1.xindex, p6.x1.xindex, p6.y1.xindex);
    }

    TPoly PolyInCenter() {
        CPoint p = (CPoint) elementlist.get(0);
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);
        CPoint p3 = (CPoint) elementlist.get(3);
        add_des(gib.C_ICENT, p, p1, p2, p3);
        TMono m1 = poly.eqangle(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p.x1.xindex, p.y1.xindex, p.x1.xindex, p.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);

        TMono m2 = poly.eqangle(p1.x1.xindex, p1.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p.x1.xindex, p.y1.xindex, p.x1.xindex, p.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p2.x1.xindex, p2.y1.xindex);

        return this.NewTPoly(m1, m2);
    }

    TPoly PolyOrthoCenter() {
        CPoint p = (CPoint) elementlist.get(0);
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);
        CPoint p3 = (CPoint) elementlist.get(3);
        TMono m1 = poly.perpendicular(p.x1.xindex, p.y1.xindex, p1.x1.xindex, p1.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
        TMono m2 = poly.perpendicular(p.x1.xindex, p.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p3.x1.xindex, p3.y1.xindex);

        add_des(gib.C_ORTH, p, p1, p2, p3);

        return this.NewTPoly(m1, m2);

    }

    TPoly PolySymPoint() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint po = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);

        TMono m1 = poly.midpoint(p1.x1.xindex, po.x1.xindex, p2.x1.xindex);
        TMono m2 = poly.midpoint(p1.y1.xindex, po.y1.xindex, p2.y1.xindex);

        return this.NewTPoly(m1, m2);
    }

    TMono polyMulside() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        Integer i1 = (Integer) elementlist.get(4);
        Integer i2 = (Integer) elementlist.get(5);
        add_des(gib.C_NRATIO, p1, p2, p3, p4, i1, i2);
        return poly.p_p_mulside(p1, p2, p3, p4, i1.intValue(), i2.intValue());


    }

    TMono PolyHorizonal() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        return poly.p_p_horizonal(p1, p2);
    }

    TMono PolyVertical() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        return poly.p_p_vertical(p1, p2);
    }


    TPoly PolyLCTangent() {
        CLine line1;
        Circle c;
        Object obj1 = elementlist.get(0);
        Object obj2 = elementlist.get(1);
        if (obj1 instanceof CLine) {
            line1 = (CLine) obj1;
            c = (Circle) obj2;
        } else {
            line1 = (CLine) obj2;
            c = (Circle) obj1;
        }

        CPoint[] pl = line1.getTowSideOfLine();
        CPoint[] cl = c.getRadiusPoint();
        if (pl == null) return null;
        TMono m = poly.l_c_tangent(pl[0], pl[1], cl[0], cl[1], c.o);
        TPoly tp = new TPoly();
        tp.setNext(null);
        tp.setPoly(m);

        return tp;
    }

    TPoly PolyCircumCenter() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);

        add_des(gib.C_CIRCUM, p1, p2, p3, p4);

        return poly.circumcenter(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex);
    }

    TPoly PolyBaryCenter() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_CENT, p1, p2, p3, p4);
        return poly.barycenter(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex);
    }


    TPoly PolyPropPoint() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        Integer i1 = (Integer) elementlist.get(3);
        Integer i2 = (Integer) elementlist.get(4);

        add_des(gib.C_LRATIO, p1, p2, p1, p3, i1, i2);

        return poly.prop_point(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, i1.intValue(), i2.intValue());
    }

    TPoly PolyPratio() {  // p1   is p2 + /p3p4/
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        int r1 = 1;
        int r2 = 1;
        if (elementlist.size() == 6) {
            Integer i1 = (Integer) elementlist.get(4);
            Integer i2 = (Integer) elementlist.get(5);
            r1 = i1.intValue();
            r2 = i2.intValue();
            add_des(gib.C_PRATIO, p1, p2, p3, p4, i1, i2);
        } else
            add_des(gib.C_PRATIO, p1, p2, p3, p4);

        return poly.Pratio(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex, r1, r2);
    }

    TPoly PolyInterCC() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        Circle c1 = (Circle) elementlist.get(2);
        Circle c2 = (Circle) elementlist.get(3);
        CPoint p3 = c1.o;
        CPoint p4 = c2.o;
//        add_des(gib.C_O_C, p1, p3, p2);
//        add_des(gib.C_O_C, p1, p4, p2);
        add_des(gib.C_I_CC, p1, p3, p2, p4, p2);

        return poly.mirrorPL(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex);
    }

    TPoly PolyMirror()  // p1 to p2 through obj
    {
        Object obj = elementlist.get(2);
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);

        if (obj instanceof CLine) {
            CLine line = (CLine) obj;
            CPoint[] pl = line.getTowSideOfLine();
            CPoint p3 = pl[0];
            CPoint p4 = pl[1];

            add_des(gib.C_SYM, p1, p2, p3, p4);
            return poly.mirrorPL(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                    p4.x1.xindex, p4.y1.xindex);
        } else {
            CPoint p3 = (CPoint) elementlist.get(2);
            CPoint p4 = (CPoint) elementlist.get(3);

            add_des(gib.C_SYM, p1, p2, p3, p4);

            return poly.mirrorPL(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                    p4.x1.xindex, p4.y1.xindex);
        }

    }

    TMono PolyISOTriangle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);

        add_des(gib.C_ISO_TRI, p1, p2, p3);

        return poly.bisect(p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p1.x1.xindex, p1.y1.xindex);
    }

    TMono PolyPerpBisect() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        add_des(gib.C_O_B, p1, p2, p3);


        return poly.bisect(p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p1.x1.xindex, p1.y1.xindex);
    }

    TPoly PolyTRatio() {
        int n = elementlist.size();
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        Integer I, I1;
        if (n == 6) {
            I = (Integer) elementlist.get(4);
            I1 = (Integer) elementlist.get(5);
        } else {
            I = new Integer(1);
            I1 = new Integer(1);
        }
        add_des(gib.C_TRATIO, p1, p2, p3, p4, I, I1);
        int t1 = I.intValue();
        int t2 = I1.intValue();

        return poly.Tratio(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex, t1, t2);

    }

    TPoly PolyNTSegment() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        //des = new Act(Act.QSQUAR, p1, p2, p3, p4);
        return poly.squarept2(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex, this.proportion);

    }

    TMono PolyNTAngle() {
        CLine ln1 = (CLine) elementlist.get(0);
        CLine ln2 = (CLine) elementlist.get(1);
        CLine ln3 = (CLine) elementlist.get(2);
        CLine ln = (CLine) elementlist.get(3);
        CPoint pt = (CPoint) elementlist.get(4);
        CPoint[] l1 = ln1.getTowSideOfLine();
        CPoint[] l2 = ln2.getTowSideOfLine();
        CPoint[] l3 = ln3.getTowSideOfLine();
        if (l1 == null || l2 == null || l3 == null) return null;
        CPoint c = ln.getfirstPoint();
        if (c == pt) return null;
        return poly.eqangle(l1[0].x1.xindex, l1[0].y1.xindex, l1[1].x1.xindex, l1[1].y1.xindex,
                l2[0].x1.xindex, l2[0].y1.xindex, l2[1].x1.xindex, l2[1].y1.xindex,
                l3[0].x1.xindex, l3[0].y1.xindex, l3[1].x1.xindex, l3[1].y1.xindex,
                c.x1.xindex, c.y1.xindex, pt.x1.xindex, pt.y1.xindex);
    }

    TPoly PolyPsquare() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = p2;
        CPoint p4 = (CPoint) elementlist.get(2);

        return poly.squarept1(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex, this.proportion);

    }

    TPoly PolyQsquare() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = p2;
        CPoint p4 = (CPoint) elementlist.get(2);
        return poly.squarept2(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex, this.proportion);

    }

    TMono PolyCCLine() {
        CPoint p = (CPoint) this.getelement(0);
        Circle c1 = (Circle) this.getelement(1);
        Circle c2 = (Circle) this.getelement(2);
        CPoint po1 = c1.o;
        CPoint po2 = c2.o;
        CPoint pc1 = c1.getSidePoint();
        CPoint pc2 = c2.getSidePoint();

        return poly.ccline(p.x1.xindex, p.y1.xindex, po1.x1.xindex, po1.y1.xindex, pc1.x1.xindex, pc1.y1.xindex,
                po2.x1.xindex, po2.y1.xindex, pc2.x1.xindex, pc2.y1.xindex);

    }

    TMono PolySAngle() {
        CLine ln1 = (CLine) elementlist.get(0);
        CLine ln2 = (CLine) elementlist.get(1);
        Integer I = (Integer) elementlist.get(2);

        CPoint p = CLine.commonPoint(ln1, ln2);
        CPoint lp = ln1.get_Lpt1(p);
        CPoint lp1 = ln2.get_Lpt1(p);
        if (p != null && lp != null && lp1 != null) {
            add_des(gib.C_SANGLE, lp, p, lp1, new Integer(Math.abs(I.intValue())));
            return poly.sangle(lp.x1.xindex, lp.y1.xindex, p.x1.xindex, p.y1.xindex, lp1.x1.xindex, lp1.y1.xindex, this.proportion);
        } else
            return null;
    }

    TMono PolyALine() {
        CLine ln1 = (CLine) elementlist.get(0);
        CLine ln2 = (CLine) elementlist.get(1);
        CLine ln3 = (CLine) elementlist.get(2);
        CLine ln4 = (CLine) elementlist.get(3);
        CPoint p1, p2, p3, p4, c1, c2;

        c1 = CLine.commonPoint(ln1, ln2);
        CPoint[] lp = ln1.getTowSideOfLine();
        if (lp[0] == c1)
            p1 = lp[1];
        else
            p1 = lp[0];

        lp = ln2.getTowSideOfLine();
        if (lp[0] == c1)
            p2 = lp[1];
        else
            p2 = lp[0];
        c2 = CLine.commonPoint(ln3, ln4);
        lp = ln3.getTowSideOfLine();
        if (lp[0] == c2)
            p3 = lp[1];
        else
            p3 = lp[0];

        lp = ln4.getTowSideOfLine();

        if (lp != null) {
            if (lp[0] == c2)
                p4 = lp[1];
            else
                p4 = lp[0];
            add_des(gib.C_O_A, p4, c2, p3, p2, c1, p1);
            return poly.eqangle(p4.x1.xindex, p4.y1.xindex, c2.x1.xindex, c2.y1.xindex,
                    p3.x1.xindex, p3.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                    c1.x1.xindex, c1.y1.xindex, p1.x1.xindex, p1.y1.xindex);
        }
        return null;
    }

    TMono PolyEqAngle() {
        if (elementlist.size() == 2) {
            CAngle ag1 = (CAngle) elementlist.get(0);
            CAngle ag2 = (CAngle) elementlist.get(1);

            CPoint pa = CLine.commonPoint(ag1.lstart, ag1.lend);
            CPoint pb = CLine.commonPoint(ag2.lstart, ag2.lend);
            CPoint pa1 = ag1.pstart;
            CPoint pa2 = ag1.pend;
            CPoint pb1 = ag2.pstart;
            CPoint pb2 = ag2.pend;
            CLine ln1 = ag1.lstart;
            CLine ln2 = ag1.lend;
            CLine ln3 = ag2.lstart;
            CLine ln4 = ag2.lend;
            CPoint[] lp1 = ln1.getTowSideOfLine();
            CPoint[] lp2 = ln2.getTowSideOfLine();
            CPoint[] lp3 = ln3.getTowSideOfLine();
            CPoint[] lp4 = ln4.getTowSideOfLine();
            if (lp1 == null || lp2 == null || lp3 == null || lp4 == null) return null;

            add_des(gib.C_EQANGLE, pa1, pa, pa2, pb1, pb, pb2);
            return poly.eqangle(lp1[0].x1.xindex, lp1[0].y1.xindex, lp1[1].x1.xindex, lp1[1].y1.xindex,
                    lp2[0].x1.xindex, lp2[0].y1.xindex, lp2[1].x1.xindex, lp2[1].y1.xindex,
                    lp3[0].x1.xindex, lp3[0].y1.xindex, lp3[1].x1.xindex, lp3[1].y1.xindex,
                    lp4[0].x1.xindex, lp4[0].y1.xindex, lp4[1].x1.xindex, lp4[1].y1.xindex);
        } else // four lines.
        {

            CLine ln1 = (CLine) elementlist.get(0);
            CLine ln2 = (CLine) elementlist.get(1);
            CLine ln3 = (CLine) elementlist.get(2);
            CLine ln4 = (CLine) elementlist.get(3);

            CPoint pa = CLine.commonPoint(ln1, ln2);
            CPoint pb = CLine.commonPoint(ln3, ln4);

            CPoint pa1 = ln1.getSecondPoint(pa);
            CPoint pa2 = ln2.getSecondPoint(pa);
            CPoint pb1 = ln3.getSecondPoint(pb);
            CPoint pb2 = ln4.getSecondPoint(pb);

            CPoint[] lp1 = ln1.getTowSideOfLine();
            CPoint[] lp2 = ln2.getTowSideOfLine();
            CPoint[] lp3 = ln3.getTowSideOfLine();
            CPoint[] lp4 = ln4.getTowSideOfLine();
            if (lp1 == null || lp2 == null || lp3 == null || lp4 == null) return null;

            add_des(gib.C_EQANGLE, pa1, pa, pa2, pb1, pb, pb2);
            return poly.eqangle(lp1[0].x1.xindex, lp1[0].y1.xindex, lp1[1].x1.xindex, lp1[1].y1.xindex,
                    lp2[0].x1.xindex, lp2[0].y1.xindex, lp2[1].x1.xindex, lp2[1].y1.xindex,
                    lp3[0].x1.xindex, lp3[0].y1.xindex, lp3[1].x1.xindex, lp3[1].y1.xindex,
                    lp4[0].x1.xindex, lp4[0].y1.xindex, lp4[1].x1.xindex, lp4[1].y1.xindex);

        }
    }

    TMono PolyEqAngle3P() {
        CAngle ag1 = (CAngle) elementlist.get(0);
        CAngle ag2 = (CAngle) elementlist.get(1);
        CAngle ag3 = (CAngle) elementlist.get(2);
        param pm = (param) elementlist.get(3);

        CPoint p1 = ag1.pstart;
        CPoint p2 = ag1.getVertex();
        CPoint p3 = ag1.pend;
        CPoint p4 = ag2.pstart;
        CPoint p5 = ag2.getVertex();
        CPoint p6 = ag2.pend;
        CPoint p7 = ag3.pstart;
        CPoint p8 = ag3.getVertex();
        CPoint p9 = ag3.pend;

        this.add_des(gib.C_EQANGLE3P, p1, p2, p3, p4, p5, p6, p7, p8, p9, pm.type);

        return poly.eqangle3p(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                p4.x1.xindex, p4.y1.xindex, p5.x1.xindex, p5.y1.xindex, p6.x1.xindex, p6.y1.xindex,
                p7.x1.xindex, p7.y1.xindex, p8.x1.xindex, p8.y1.xindex, p9.x1.xindex, p9.y1.xindex,
                pm.xindex);

    }

    TMono PolySpecifiAngle() {
        param pm = (param) elementlist.get(0);
        return poly.specificangle(pm.xindex, this.proportion);
    }

    TMono PolyEqidstance() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);

        add_des(gib.C_EQDISTANCE, p1, p2, p3, p4);
        return poly.eqdistance(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);

    }

    TPoly PolyMidPoint() {
        CPoint po = (CPoint) elementlist.get(0);
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);

        TMono m1 = poly.midpoint(p1.x1.xindex, po.x1.xindex, p2.x1.xindex);
        TMono m2 = poly.midpoint(p1.y1.xindex, po.y1.xindex, p2.y1.xindex);

        add_des(gib.C_MIDPOINT, po, p1, p2);

        TPoly poly = new TPoly();
        poly.setPoly(m1);
        TPoly poly2 = new TPoly();
        poly2.setPoly(m2);
        poly2.setNext(poly);
        return poly2;

    }


    TPoly PolyRectangle() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_RECTANGLE, p1, p2, p3, p4);

        TMono m1 = poly.parallel(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        TMono m2 = poly.perpendicular(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
        TMono m3 = poly.perpendicular(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        TPoly p = NewTPoly(m1, m2);
        TPoly pp = new TPoly();
        pp.setPoly(m3);
        pp.setNext(p);
        return pp;
    }

    TMono PolyTrapezoid() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_TRAPEZOID, p1, p2, p3, p4);
        TMono m1 = poly.parallel(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        return m1;
    }

    TPoly PolyParallelogram() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);

        int n = p4.x1.xindex;
        if (n > p1.x1.xindex && n > p2.x1.xindex) {
            CPoint pt = p4;
            p4 = p1;
            p1 = pt;
            pt = p3;
            p3 = p2;
            p2 = pt;
        }
        add_des(gib.C_PARALLELOGRAM, p1, p2, p3, p4);
        TMono m1 = poly.parallel(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        TMono m2 = poly.parallel(p1.x1.xindex, p1.y1.xindex, p4.x1.xindex, p4.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
        return this.NewTPoly(m1, m2);

    }

    TPoly PolyRTrapezoid() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);
        CPoint p4 = (CPoint) elementlist.get(3);
        add_des(gib.C_R_TRAPEZOID, p1, p2, p3, p4);
        TMono m1 = poly.parallel(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex,
                p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        TMono m2 = poly.perpendicular(p1.x1.xindex, p1.y1.xindex, p4.x1.xindex, p4.y1.xindex,
                p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        return this.NewTPoly(m1, m2);

    }

    TMono PolyRightTriangle() {
        CPoint po = (CPoint) elementlist.get(0);
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);
        add_des(gib.C_R_TRI, po, p1, p2);
        return poly.perpendicular(po.x1.xindex, po.y1.xindex, p1.x1.xindex, p1.y1.xindex,
                po.x1.xindex, po.y1.xindex, p2.x1.xindex, p2.y1.xindex);
    }

    TMono PolyOnLine() {
        CPoint p = (CPoint) this.getelement(0);
        CLine line = (CLine) this.getelement(1);
        int x, y;
        x = p.x1.xindex;
        y = p.y1.xindex;

        if (line.type == CLine.CCLine) {
            constraint cs = line.getcons(0);

            Circle c1 = (Circle) cs.getelement(1);
            Circle c2 = (Circle) cs.getelement(2);
            CPoint po1 = c1.o;
            CPoint po2 = c2.o;
            CPoint pc1 = c1.getSidePoint();
            CPoint pc2 = c2.getSidePoint();
            //?????
            return poly.ccline(p.x1.xindex, p.y1.xindex, po1.x1.xindex, po1.y1.xindex, pc1.x1.xindex, pc1.y1.xindex,
                    po2.x1.xindex, po2.y1.xindex, pc2.x1.xindex, pc2.y1.xindex);


        } else {
            CPoint[] plist = line.getTowSideOfLine();
            if (plist == null) return null;
            CPoint p1, p2;
            p1 = plist[0];
            p2 = plist[1];
            add_des(gib.C_O_L, p, p1, p2);
            return poly.collinear(x, y, p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        }
    }

    TPoly PolyIntersection_CC() {
        CPoint p1 = (CPoint) elementlist.get(0);
        Circle c1 = (Circle) elementlist.get(1);
        Circle c2 = (Circle) elementlist.get(2);
        Vector v = Circle.CommonPoints(c1, c2);
        TPoly tp = null;
        int n = v.size();
        CPoint p2 = null;

        if (n == 2) {
            CPoint t1 = (CPoint) v.get(0);
            CPoint t2 = (CPoint) v.get(1);
            if (p1 == t1)
                p2 = t2;
            else
                p2 = t1;
        } else if (n == 1 && v.get(0) != p1)
            p2 = (CPoint) v.get(0);


        if (p2 != null && p2.x1.xindex < p1.x1.xindex) {
            CPoint p3 = c1.o;
            CPoint p4 = c2.o;
            this.add_des(gib.C_I_CC, p1, p3, p2, p4, p2);
            return poly.mirrorPL(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex,
                    p4.x1.xindex, p4.y1.xindex);
        }

        p2 = c1.getSidePoint();
        CPoint p3 = c2.getSidePoint();
        if (p2 != null && p3 != null) {
            this.add_des(gib.C_I_CC, p1, c1.o, p2, c2.o, p3);
            TMono m1 = poly.eqdistance(p1.x1.xindex, p1.y1.xindex, c1.o.x1.xindex, c1.o.y1.xindex,
                    p2.x1.xindex, p2.y1.xindex, c1.o.x1.xindex, c1.o.y1.xindex);
            TMono m2 = poly.eqdistance(p1.x1.xindex, p1.y1.xindex, c2.o.x1.xindex, c2.o.y1.xindex,
                    p3.x1.xindex, p3.y1.xindex, c2.o.x1.xindex, c2.o.y1.xindex);
            return this.NewTPoly(m1, m2);
        } else {
            CPoint[] l1 = c1.getRadiusPoint();
            CPoint[] l2 = c2.getRadiusPoint();
            this.add_des(gib.C_I_RR, p1, c1.o, l1[0], l1[1], c2.o, l2[0], l2[1]);

            TMono m1 = poly.eqdistance(p1.x1.xindex, p1.y1.xindex, c1.o.x1.xindex, c1.o.y1.xindex,
                    l1[0].x1.xindex, l1[0].y1.xindex, l1[1].x1.xindex, l1[1].y1.xindex);
            TMono m2 = poly.eqdistance(p1.x1.xindex, p1.y1.xindex, c2.o.x1.xindex, c2.o.y1.xindex,
                    l2[0].x1.xindex, l2[0].y1.xindex, l2[1].x1.xindex, l2[1].y1.xindex);
            return this.NewTPoly(m1, m2);
        }
    }

    TPoly PolyIntersection_ll() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CLine ln1 = (CLine) elementlist.get(1);
        CLine ln2 = (CLine) elementlist.get(2);
        if (compareLN(ln1, ln2)) {
            CLine ln = ln1;
            ln1 = ln2;
            ln2 = ln;
        }

        CPoint[] ps1 = ln1.getTowSideOfLine();
        if (ps1 == null) return null;
        CPoint[] ps2 = ln2.getTowSideOfLine();
        if (ps2 == null) return null;
        add_des(gib.C_I_LL, p1, ps1[0], ps1[1], ps2[0], ps2[1]);
        TMono m1 = poly.collinear(p1.x1.xindex, p1.y1.xindex, ps1[0].x1.xindex, ps1[0].y1.xindex, ps1[1].x1.xindex, ps1[1].y1.xindex);
        addZeron(m1);
        TMono m2 = poly.collinear(p1.x1.xindex, p1.y1.xindex, ps2[0].x1.xindex, ps2[0].y1.xindex, ps2[1].x1.xindex, ps2[1].y1.xindex);
        return this.NewTPoly(m1, m2);
    }

    TPoly PolyIntersection_lc() {
        CPoint p1 = (CPoint) elementlist.get(0);
        CLine ln = (CLine) elementlist.get(1);
        Circle c = (Circle) elementlist.get(2);
        CPoint[] ls = CLine.commonPoint(ln, c);
        CPoint[] np = ln.getTowSideOfLine();
        CPoint o = c.o;

        CPoint p2;

        if (ls.length == 2) {
            if (p1 == ls[0])
                p2 = ls[1];
            else
                p2 = ls[0];
        } else if (ls.length == 1 && ls[0] != p1)
            p2 = ls[0];
        else
            p2 = null;

        if (p2 != null && p1.x1.xindex > p2.x1.xindex) {
            add_des(gib.C_I_LC, p1, np[0], np[1], c.o, c.getSidePoint());
            CPoint pl = ln.getSecondPoint(p2);
            return poly.LCMeet(o.x1.xindex, o.y1.xindex, p2.x1.xindex,
                    p2.y1.xindex, pl.x1.xindex, pl.y1.xindex, p1.x1.xindex, p1.y1.xindex);
        }

        CPoint pl = c.getSidePoint();
        if (pl != null) {
            add_des(gib.C_I_LC, p1, np[0], np[1], c.o, c.getSidePoint());
            TMono m1 = poly.collinear(p1.x1.xindex, p1.y1.xindex, np[0].x1.xindex, np[0].y1.xindex, np[1].x1.xindex, np[1].y1.xindex);
            addZeron(m1);
            TMono m2 = poly.eqdistance(o.x1.xindex, o.y1.xindex, p1.x1.xindex, p1.y1.xindex, o.x1.xindex, o.y1.xindex, pl.x1.xindex, pl.y1.xindex);
            return this.NewTPoly(m1, m2);
        } else {
            CPoint[] ll = c.getRadiusPoint();
            add_des(gib.C_I_LR, p1, np[0], np[1], c.o, ll[0], ll[1]);
            TMono m1 = poly.collinear(p1.x1.xindex, p1.y1.xindex, np[0].x1.xindex, np[0].y1.xindex, np[1].x1.xindex, np[1].y1.xindex);
            addZeron(m1);
            TMono m2 = poly.eqdistance(o.x1.xindex, o.y1.xindex, p1.x1.xindex, p1.y1.xindex, ll[0].x1.xindex, ll[0].y1.xindex, ll[1].x1.xindex, ll[1].x1.xindex);
            return this.NewTPoly(m1, m2);
        }

    }

    TMono collinear() {
        CPoint p1, p2, p3;
        p1 = p2 = p3 = null;

        for (int i = 0; i < this.elementlist.size(); i++) {
            CPoint p = (CPoint) this.getelement(i);
            if (p == null) continue;

            if (p1 == null)
                p1 = p;
            else if (p1.x1.xindex < p.x1.xindex) {
                if (p2 == null)
                    p2 = p1;
                else
                    p3 = p1;
                p1 = p;

            } else if (p2 == null)
                p2 = p;
            else
                p3 = p;
        }

        add_des(gib.C_O_L, p1, p2, p3);
        return poly.collinear(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex);
    }

    TPoly PolyLCMeet() {

        CPoint pl = null;
        CPoint p = (CPoint) this.getelement(0);
        CPoint pc = (CPoint) this.getelement(1);
        CLine ln = (CLine) this.getelement(2);
        Circle c = (Circle) this.getelement(3);
        CPoint o = c.o;
        Vector pts = ln.points;
        for (int i = 0; i < pts.size(); i++)
            if (pts.get(i) != pc) {
                pl = (CPoint) pts.get(i);
                break;
            }
        if (pl == null) return null;
        add_des(gib.C_I_LC, p, pc, pl, o, pc);
        return poly.LCMeet(o.x1.xindex, o.y1.xindex, pc.x1.xindex, pc.y1.xindex, pl.x1.xindex, pl.y1.xindex, p.x1.xindex, p.y1.xindex);
    }

    TMono PolyOnCircle() {
        CPoint p = (CPoint) this.getelement(0);
        Circle c = (Circle) this.getelement(1);
        CPoint o = c.o;

        if (c.type == Circle.RCircle && (c.points.size() == 0 || c.points.size() == 1 && c.points.contains(p))) {
            constraint cs = null;
            for (int i = 0; i < c.cons.size(); i++) {
                constraint cc = (constraint) c.cons.get(i);
                if (cc.GetConstraintType() == constraint.RCIRCLE && c == cc.getelement(2)) {
                    cs = cc;
                    break;
                }
            }
            if (cs == null) return null;
            CPoint p1 = (CPoint) cs.getelement(0);
            CPoint p2 = (CPoint) cs.getelement(1);

            add_des(gib.C_O_R, p, o, p1, p2);

            return poly.eqdistance(o.x1.xindex, o.y1.xindex, p.x1.xindex, p.y1.xindex, p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex);
        } else if (c.points.size() != 0)//|| c.type == Circle.PCircle || c.type == Circle.SCircle)
        {
            CPoint pt = c.getSidePoint();
            if (pt == null)
                return null;

            add_des(gib.C_O_C, p, o, pt);

            return poly.eqdistance(o.x1.xindex, o.y1.xindex, p.x1.xindex, p.y1.xindex, o.x1.xindex, o.y1.xindex, pt.x1.xindex, pt.y1.xindex);

        } else {
            CMisc.print("ERROR CIRCLE CONSTRAINT");
        }
        return null;
    }

    TMono PolyPerp() {
        if (elementlist.size() == 2) {
            CLine line1 = (CLine) this.getelement(0);
            CLine line2 = (CLine) this.getelement(1);

            if (line1.points.size() < 2)
                return null;
            CPoint[] pl1 = line1.getTowSideOfLine();
            CPoint[] pl2 = line2.getTowSideOfLine();

            if (pl1 == null || pl2 == null)
                return null;

            add_desx1(gib.C_O_T, pl1[0], pl1[1], pl2[0], pl2[1]);

            return poly.perpendicular(pl1[0].x1.xindex, pl1[0].y1.xindex, pl1[1].x1.xindex, pl1[1].y1.xindex,
                    pl2[0].x1.xindex, pl2[0].y1.xindex, pl2[1].x1.xindex, pl2[1].y1.xindex);
        } else if (elementlist.size() == 4) {
            CPoint p1 = (CPoint) elementlist.get(0);
            CPoint p2 = (CPoint) elementlist.get(1);
            CPoint p3 = (CPoint) elementlist.get(2);
            CPoint p4 = (CPoint) elementlist.get(3);
            int x1, y1, x2, y2, x3, y3, x4, y4;
            x1 = p1.x1.xindex;
            y1 = p1.y1.xindex;
            x2 = p2.x1.xindex;
            y2 = p2.y1.xindex;
            x3 = p3.x1.xindex;
            y3 = p3.y1.xindex;
            x4 = p4.x1.xindex;
            y4 = p4.y1.xindex;
            add_des(gib.C_O_T, p1, p2, p3, p4);
            return poly.perpendicular(x1, y1, x2, y2, x3, y3, x4, y4);
        }
        return null;

    }

    TPoly PolyPfoot() {
        CPoint p1 = (CPoint) this.getelement(0);
        CPoint p2 = (CPoint) this.getelement(1);
        CPoint p3 = (CPoint) this.getelement(2);
        CPoint p4 = (CPoint) this.getelement(3);
        add_des(gib.C_FOOT, p1, p2, p3, p4);
        TMono m1 = poly.collinear(p1.x1.xindex, p1.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        this.addZeron(m1);
        TMono m2 = poly.perpendicular(p1.x1.xindex, p1.y1.xindex,
                p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
        return mpoly(m1, m2);


    }

    TMono PolyParel() {
        CLine line1 = (CLine) this.getelement(0);
        CLine line2 = (CLine) this.getelement(1);

        if (line1.points.size() < 2)
            return null;
        CPoint[] pl1 = line1.getTowSideOfLine();
        CPoint[] pl2 = line2.getTowSideOfLine();

        if (pl1 == null || pl2 == null)
            return null;

        add_desx1(gib.C_O_P, pl1[0], pl1[1], pl2[0], pl2[1]);

        return poly.parallel(pl1[0].x1.xindex, pl1[0].y1.xindex, pl1[1].x1.xindex, pl1[1].y1.xindex,
                pl2[0].x1.xindex, pl2[0].y1.xindex, pl2[1].x1.xindex, pl2[1].y1.xindex);
    }

    TMono PolyCCTangent() {
        Circle c1 = (Circle) this.getelement(0);
        Circle c2 = (Circle) this.getelement(1);

        CPoint[] pl1 = c1.getRadiusPoint();
        CPoint[] pl2 = c2.getRadiusPoint();
        add_des(gib.C_CCTANGENT, c1.o, pl1[0], pl1[1], c2.o, pl2[0], pl2[1]);

        return poly.c_c_tangent(pl1[0], pl1[1], c1.o, pl2[0], pl2[1], c2.o);
    }

    public static boolean PolyConstraint(int type, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        int x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = p1.x1.xindex;
        y1 = p1.y1.xindex;
        x2 = p2.x1.xindex;
        y2 = p2.y1.xindex;
        x3 = p3.x1.xindex;
        y3 = p3.y1.xindex;
        TMono mpoly = null;
        TMono mpoly1 = null;
        switch (type) {
            case constraint.COLLINEAR: // 3 obj
                mpoly = poly.collinear(x1, y1, x2, y2, x3, y3);
                break;
            case constraint.PARALLEL: // 4 obj
                x4 = p4.x1.xindex;
                y4 = p4.y1.xindex;
                mpoly = poly.parallel(x1, y1, x2, y2, x3, y3, x4, y4);
                break;
            case constraint.PERPENDICULAR:    //4
                x4 = p4.x1.xindex;
                y4 = p4.y1.xindex;
                mpoly = poly.perpendicular(x1, y1, x2, y2, x3, y3, x4, y4);
                break;
            case constraint.EQDISTANCE:       //4
                x4 = p4.x1.xindex;
                y4 = p4.y1.xindex;
                mpoly = poly.eqdistance(x1, y1, x2, y2, x3, y3, x4, y4);
                break;
            case constraint.BISECT:
                mpoly = poly.bisect(x1, y1, x2, y2, x3, y3);
                break;
            case constraint.MIDPOINT:
                mpoly = poly.midpoint(x1, x2, x3);
                mpoly1 = poly.midpoint(y1, y2, y3);
            default:
                break;
        }

        if (mpoly == null)
            return false;
        TPoly tp = new TPoly();
        tp.setPoly(mpoly);
        tp.setNext(polylist);
        polylist = tp;
        if (type == constraint.MIDPOINT) {
            tp = new TPoly();
            tp.setPoly(mpoly1);
            tp.setNext(polylist);
            polylist = tp;
        }
        return true;
    }

    public TPoly NewTPoly(TMono m1, TMono m2) {
        TPoly poly = new TPoly();
        poly.setPoly(m1);
        TPoly poly2 = new TPoly();
        poly2.setPoly(m2);
        poly2.setNext(poly);
        return poly2;
    }

    public void Save(DataOutputStream out) throws IOException {

        out.writeInt(id);
        out.writeInt(ConstraintType);
        int size = elementlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            Object obj = elementlist.get(i);
            if (obj == null) {
                CMisc.print("Constraint Null");
            } else if (obj instanceof CPoint) {
                CPoint p = (CPoint) obj;
                out.writeInt(1);
                out.writeInt(p.m_id);
            } else if (obj instanceof CLine) {
                CLine ln = (CLine) obj;
                out.writeInt(2);
                out.writeInt(ln.m_id);
            } else if (obj instanceof Circle) {
                Circle c = (Circle) obj;
                out.writeInt(3);
                out.writeInt(c.m_id);
            } else if (obj instanceof CDistance) {
                CDistance dis = (CDistance) obj;
                out.writeInt(4);
                out.writeInt(dis.m_id);
            } else if (obj instanceof CAngle) {
                CAngle ag = (CAngle) obj;
                out.writeInt(5);
                out.writeInt(ag.m_id);
            } else if (obj instanceof param) {
                param pm = (param) obj;
                out.writeInt(6);
                out.writeInt(pm.xindex);
            } else if (obj instanceof Integer) {
                Integer I = (Integer) obj;
                out.writeInt(20);
                out.writeInt(I.intValue());
            } else if (obj instanceof CPolygon) {
                CPolygon p = (CPolygon) obj;
                out.writeInt(7);
                out.writeInt(p.m_id);
            } else {
                CClass cc = (CClass) obj;
                out.writeInt(99);
                out.writeInt(cc.m_id);
            }

        }
        out.writeInt(proportion);
        out.writeBoolean(this.is_poly_genereate);


    }

    private void add_des(cons s) {
        if (this.csd == null)
            this.csd = s;
        else
            this.csd1 = s;
    }


    public void add_des(int t, CPoint p1, CPoint p2, CPoint p3, Object obj) {

        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(obj);
        add_des(csd);
    }

    public void add_des(int t, CPoint p1, CPoint p2, CPoint p3) {

        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        add_des(csd);
    }

    public void add_des(int t, Vector v) {
        cons csd = new cons(t);
        csd.setId(id);
        for (int i = 0; i < v.size(); i++)
            csd.add_pt(v.get(i));
        add_des(csd);
    }

    public boolean less(CPoint a, CPoint b) {
        return a.x1.xindex < b.x1.xindex;
    }

    public void add_desx1(int t, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {   //parallel ,perpendicular
        if (less(p1, p2)) {
            CPoint a = p1;
            p1 = p2;
            p2 = a;
        }
        if (less(p3, p4)) {
            CPoint a = p3;
            p3 = p4;
            p4 = a;
        }
        if (less(p1, p3)) {
            CPoint a = p1;
            p1 = p3;
            p3 = a;
            a = p2;
            p2 = p4;
            p4 = a;
        }
        add_des(t, p1, p2, p3, p4);
    }

    public void add_des(int t, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {

        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        add_des(csd);
    }

    public void add_des(int t, Object p1, Object p2, Object p3, Object p4, Object p5) {
        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        csd.add_pt(p5);
        add_des(csd);
    }

    public void add_des(int t, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        csd.add_pt(p5);
        csd.add_pt(p6);
        add_des(csd);
    }

    public void add_des(int t, Object p1, Object p2, Object p3,
                        Object p4, Object p5, Object p6, Object p7, Object p8) {
        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        csd.add_pt(p5);
        csd.add_pt(p6);
        csd.add_pt(p7);
        csd.add_pt(p8);
        add_des(csd);
    }

    public void add_des(int t, Object p1, Object p2, Object p3,
                        Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10) {
        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        csd.add_pt(p5);
        csd.add_pt(p6);
        csd.add_pt(p7);
        csd.add_pt(p8);
        csd.add_pt(p9);
        csd.add_pt(p10);
        add_des(csd);
    }

    public void add_des(int t, Object p1, Object p2, Object p3,
                        Object p4, Object p5, Object p6, Object p7) {
        cons csd = new cons(t);
        csd.setId(id);
        csd.add_pt(p1);
        csd.add_pt(p2);
        csd.add_pt(p3);
        csd.add_pt(p4);
        csd.add_pt(p5);
        csd.add_pt(p6);
        csd.add_pt(p7);
        add_des(csd);
    }


    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        id = in.readInt();
        ConstraintType = in.readInt();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            int t = in.readInt();
            int d = in.readInt();

            switch (t) {
                case 1: {
                    CPoint p = dp.getPointById(d);
                    elementlist.add(p);
                }
                break;
                case 2:
                    elementlist.add(dp.getLineByid(d));
                    break;
                case 3:
                    elementlist.add(dp.getCircleByid(d));
                    break;
                case 4:
                    elementlist.add(dp.getOjbectById(d));
                    break;
                case 5:
                    elementlist.add(dp.getAngleByid(d));
                    break;
                case 6:
                    elementlist.add(dp.getParameterByindex(d));
                    break;
                case 7:
                    elementlist.add(dp.getOjbectById(d));
                    break;
                case 20:
                    elementlist.add(new Integer(d));
                    break;
                default:
                    elementlist.add(dp.getOjbectById(d));
                    break;
            }
        }
        proportion = in.readInt();
        this.is_poly_genereate = in.readBoolean();
        if (CMisc.version_load_now <= 0.032) {
            if (ConstraintType == 16) {
                this.ConstraintType = NSQUARE;
                elementlist.remove(1);
            }

        }
    }

    int pidx(CPoint p) {
        return p.x1.xindex;
    }

    int pidy(CPoint p) {
        return p.y1.xindex;
    }

    TPoly mpoly(TMono m1, TMono m2) {
        TPoly p1 = new TPoly();
        p1.setPoly(m1);
        TPoly p2 = new TPoly();
        p2.setPoly(m2);
        p2.setNext(p1);
        return p2;
    }

    public static double get_sp_ag_value(int v) {
        double val = 0;
        if (v == 90)
            val = 0xfffff;
        else
            val = Math.tan((v * Math.PI) / 180.0);

        return val;
    }

    public double get_sangle_v() {
        return get_sp_ag_value(proportion);
    }

    public boolean check_constraint(double x, double y) {
        switch (ConstraintType) {
            case ANGLE_BISECTOR:
                return check_agbisector(x, y);
            case INCENTER:
                return check_incenter(x, y);
            default:
                return true;
        }
    }

    public boolean check_incenter(double x, double y) {
        CPoint p1 = (CPoint) elementlist.get(1);
        CPoint p2 = (CPoint) elementlist.get(2);
        CPoint p3 = (CPoint) elementlist.get(3);
        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();
        double x3 = p3.getx();
        double y3 = p3.gety();

        double r1 = dr_pr(x2, y2, x1, y1, x, y);
        double r2 = dr_pr(x2, y2, x, y, x3, y3);

        double r3 = dr_pr(x3, y3, x1, y1, x, y);
        double r4 = dr_pr(x3, y3, x, y, x2, y2);
        return r1 * r2 > 0 && r3 * r4 > 0;
    }

    public boolean check_agbisector(double x, double y) {
        CPoint p1 = (CPoint) elementlist.get(0);
        CPoint p2 = (CPoint) elementlist.get(1);
        CPoint p3 = (CPoint) elementlist.get(2);

        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();
        double x3 = p3.getx();
        double y3 = p3.gety();

        double r1 = dr_pr(x2, y2, x1, y1, x, y);
        double r2 = dr_pr(x2, y2, x, y, x3, y3);
        return r1 * r2 > 0;
    }

    public double dr_pr(double x1, double y1, double x2, double y2, double x, double y) {
        return (y2 - y1) * (x - x2) - (y - y2) * (x2 - x1);

    }

    public TMono eqdistance(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        return poly.eqdistance(p1.x1.xindex, p1.y1.xindex, p2.x1.xindex, p2.y1.xindex, p3.x1.xindex, p3.y1.xindex, p4.x1.xindex, p4.y1.xindex);
    }

    public boolean compareLN(CLine ln1, CLine ln2) {
        return
                (ln1.getfirstPoint().m_id > ln2.getfirstPoint().m_id);
    }

    public TMono parseTMonoString(String name, String func, int x) {
        parser p = new parser(name, func, x);
        TMono m = p.parse();
        return m;
    }

    public boolean addZeron(TMono m1) {
        if (m1 != null && poly.plength(m1) == 1)
            return poly.addZeroN(m1.x);
        return false;
    }
}