package gprover;

public class CST {

    final public static String[] cst =
            {
                    "POINT",
                    "LINE",
                    "ON_LINE",
                    "ON_PLINE",
                    "ON_TLINE",
                    "ON_BLINE",
                    "ON_ALINE",

                    "FOOT",
                    "CIRCLE",
                    "ON_CIRCLE",
                    "CIRCUMCENTER",
                    "ON_RCIRCLE",

                    "MIDPOINT",

                    "EQDISTANCE", //"ON_RCIRCLE",
                    "EQANGLE",

                    "TRATIO",
                    "PRATIO",
                    "NRATIO",
                    "LRATIO",

                    "INVERSION",
                    "REFLECTION",
                    "SYM",

                    "TRIANGLE",
                    "QUADRANGLE",
                    "PENTAGON",
                    "POLYGON",
                    "ISO_TRIANGLE",
                    "R_TRIANGLE",
                    "EQ_TRIANGLE",
                    "TRAPEZOID",
                    "R_TRAPEZOID",
                    "PARALLELOGRAM",
                    "LOZENGE",
                    "RECTANGLE",
                    "SQUARE",

                    "INCENTER",
                    "ORTHOCENTER",
                    "CENTROID",

                    "CONSTANT",

                    "PSQUARE",
                    "NSQUARE",
                    "S_ANGLE",
                    "ANGLE_BISECTOR",
                    "LC_TANGENT",
                    "RATIO",
                    "CCTANGENT",
                    "ON_SCIRCLE",
                    "ON_BALINE",
                    "ON_DCIRCLE",
                    "EQANGLE3P"
            };


    final public static String[] inters = {
            "INTERSECTION_LL",
            "INTERSECTION_LP",
            "INTERSECTION_LC",
            "INTERSECTION_LB",
            "INTERSECTION_LT",
            "INTERSECTION_LR",
            "INTERSECTION_LS",
            "INTERSECTION_PP",
            "INTERSECTION_PC",
            "INTERSECTION_PT",
            "INTERSECTION_PB",
            "INTERSECTION_TC",
            "INTERSECTION_TT",
            "INTERSECTION_TB",
            "INTERSECTION_BB",
            "INTERSECTION_BC",
            "INTERSECTION_CC",
            "INTERSECTION_CR",
            "INTERSECTION_RR",
            "INTERSECTION_SS",
            "INTERSECTION_AA",

            "INTERSECTION_LA",
            "INTERSECTION_PA",
            "INTERSECTION_PR",
            "INTERSECTION_TA",
            "INTERSECTION_TR",
            "INTERSECTION_BA",
            "INTERSECTION_BR",
            "PT_EQUAL"
    };

    final public static String[] conclution = {
            "COLLINEAR",
            "PARALLEL",
            "PERPENDICULAR",
            "MIDPOINT",
            "CYCLIC",
            "EQDISTANCE",
            "EQANGLE",
            "PERP_BISECT",
            "TANGENT",
            "HARMONIC_PAIR",
            "EQ_TRIANGLE",
            "SIM_TRIANGLE",
            "CON_TRIANGLE",
            "EQ_PRODUCT",
            "ORTHOCENTER",
            "INCENTER",
            "RATIO",
            "S_ANGLE",
            "N_ANGLES",
            "N_SEGMENTS"

    };

    public static String[] s_conc_detail =
            {
                    "Collinear",
                    "Parallel",
                    "Perpendicular",
                    "Midpoint",
                    "Cyclic",
                    "Equal Distance",
                    "Equal Angle",
                    "Bisect",
                    "Tangent",
                    "Harmonic Pair",
                    "Equalateral Triangle",
                    "Similiar Triangle",
                    "Congruent Triangle",
                    "Equal product",
                    "Orthocenter",
                    "Incenter",
                    "Ratio",
                    "Special angle",
                    "Angles Equation",
                    "Segment Equation"
            };

    private CST() {
    }

    public static String get_detail_info(int n, Object[] p) {
        return null;
    }

    final private static int CONC_INDEX = 70;
    final private static int INTER_INDEX = 100;


    public static int getClu(String s) {
        s = s.toUpperCase();
        for (int i = 0; i < conclution.length; i++)
            if (s.equals(conclution[i]))
                return i + CONC_INDEX;

        if (s.equals("COCIRCLE")) {
            s = "CYCLIC";
            return getClu(s);
        }
        return 0;
    }

    public static int getClu_D(String s) {
        for (int i = 0; i < s_conc_detail.length; i++)
            if (s.equalsIgnoreCase(s_conc_detail[i]))
                return i + CONC_INDEX;
        return 0;
    }

    public static String getClus(int n) {

        int i = n - CONC_INDEX;
        if (i >= 0 && i < conclution.length)
            return conclution[i];

        if (n >= CONC_INDEX && n < INTER_INDEX)
            return s_conc_detail[n - CONC_INDEX];

        if (n > INTER_INDEX)
            i = n - INTER_INDEX - 1;
        if (i < 0)
            return "";
        return inters[i];
    }

    public static int get_pred(String s) {

        int n = 0;


        if (n == 0)
            for (int i = 0; i < cst.length; i++)
                if (s.equals(cst[i])) {
                    n = i + 1;
                    break;
                }

        if (n == 0)
            for (int i = 0; i < inters.length; i++)
                if (s.equals(inters[i])) {
                    n = i + INTER_INDEX + 1;
                    break;
                }


        if (n == 0)
            n = getClu(s);

        return n;
    }

    public static String get_preds(int n) {
        if (n >= 1 && n <= cst.length)
            return cst[n - 1];
        if (n > INTER_INDEX && n < 200)
            return inters[n - INTER_INDEX - 1];
        if (n >= 200)
            return " ";

        return getClus(n);
    }

    public static String getDString(Object[] pss, int t) {
        return getDString(pss, t, true);
    }

    public static String getDString(Object[] pss, int t, boolean d) {

        switch (t) {
            case gib.C_POINT: {
                String s = "";
                int i = 0;
                for (i = 0; i < pss.length && pss[i] != null; i++)
                    if (i != 0)
                        s += ", " + pss[i];
                    else
                        s += pss[i];

                return "Point: " + s;
            }
            case gib.C_LINE:
                return "Line " + pss[0] + pss[1];
            case gib.C_O_L:
                if (d)
                    return pss[0] + " : on line " + pss[1] + pss[2];
                else
                    return pss[0] + " is on line " + pss[1] + pss[2];

            case gib.C_O_P:
                if (d)
                    return pss[0] + "" + pss[1] + Cm.s2079 + pss[2] + "" + pss[3];
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3];
            case gib.C_O_T:
                if (d)
                    return pss[0] + "" + pss[1] + Cm.s2077 + pss[2] + "" + pss[3]; //??
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + pss[3];
            case gib.C_O_B:
                if (d)
                    return pss[0] + ": on Bline(" + pss[1] + pss[2] + ")";
                else
                    return pss[0] + " is on the perp-bisector of " + pss[1] + pss[2];
            case gib.C_O_A:
            case gib.C_EQANGLE: {
                if (pss[6] != null && pss[7] != null)
                    return Cm.s2078 + "[" + pss[0] + pss[1] + ", " + pss[2] + pss[3] + "] = " + Cm.s2078 + "[" + pss[4] + pss[5] + ", " + pss[6] + pss[7] + "]";
                else
                    return Cm.s2078 + "[" + pss[0] + pss[1] + pss[2] + "] = " + Cm.s2078 + "[" + pss[3] + pss[4] + pss[5] + "]";
            }
            case gib.C_FOOT:
                return pss[0] + "" + pss[1] + Cm.s2077 + pss[2] + "" + pss[3] + " with foot " + pss[0];
            case gib.C_CIRCLE: {
                String st = "circle(" + pss[0] + ",";
                for (int i = 1; i < pss.length && pss[i] != null; i++)
                    st += pss[i];
                return st + ")";
            }
            case gib.C_O_C:
                if (d)
                    return pss[0] + " :  on circle(" + pss[1] + "," + pss[2] + ")";
                else
                    return pss[0] + " is on circle(" + pss[1] + "," + pss[2] + ")";

            case gib.C_CIRCUM:
                if (d)
                    return pss[0] + " : circumcenter of " + pss[1] + pss[2] + pss[3];
                else
                    return pss[0] + " is the circumcenter of " + pss[1] + pss[2] + pss[3];
            case gib.C_O_R:
                if (d)
                    return pss[0] + " : on circle(" + pss[1] + "," + pss[2] + pss[3] + ")";
                else
                    return pss[0] + " is on circle(" + pss[1] + "," + pss[2] + pss[3] + ")";

            case gib.C_MIDPOINT:
                if (d)
                    return pss[0] + " : midpoint( " + pss[1] + pss[2] + ")";
                else
                    return pss[0] + " is midpoint of " + pss[1] + pss[2] + "";
            case gib.C_EQDISTANCE: {
                String st = "";
                for (int i = 0; i < pss.length / 2; i++) {
                    if (pss[i * 2] == null || pss[i * 2 + 1] == null)
                        break;
                    if (i != 0)
                        st += " = ";
                    st += "|" + pss[i * 2] + pss[i * 2 + 1] + "|";
                }
                return st;
            }
            case 16:
                return "TRatio " + pss[0] + pss[1] + " and " + pss[2] + pss[3];
            case 17:
                return "PRtio " + pss[0] + pss[1] + " and " + pss[2] + pss[3];
            case 18:
                return "NRatio " + pss[0] + pss[1] + " and " + pss[2] + pss[3];
            case 19:
                return "LRatio " + pss[0] + pss[1] + " and " + pss[2] + pss[3];
            case 20:
                return "????";
            case gib.C_TRIANGLE:
                return "triangle " + pss[0] + pss[1] + pss[2];
            case gib.C_QUADRANGLE:
                return "quadrangle " + pss[0] + pss[1] + pss[2] + pss[3];
            case gib.C_PENTAGON:
                return "pentagon " + pss[0] + pss[1] + pss[2] + pss[3] + pss[4];
            case 27:
                return "Isoceless triangle " + pss[0] + pss[1] + pss[2];
            case 28:
                return "Right triangle " + pss[0] + pss[1] + pss[2];
            case 29:
                return "Equilateral triangle " + pss[0] + pss[1] + pss[2];
            case 30:
                return "Trapezoid " + pss[0] + pss[1] + pss[2] + pss[3];
            case 31:
                return "Right Trapezoid " + pss[0] + pss[1] + pss[2] + pss[3];
            case 32:
                return "Parallelogram " + pss[0] + pss[1] + pss[2] + pss[3];
            case 33:
                return "Lozenge " + pss[0] + pss[1] + pss[2] + pss[3];
            case 34:
                return "Rectangle " + pss[0] + pss[1] + pss[2] + pss[3];
            case 35:
                return "Square " + pss[0] + pss[1] + pss[2] + pss[3];

            case 36:
                if (d)
                    return pss[0] + " : incenter of " + pss[1] + pss[2] + pss[3];
                else
                    return pss[0] + " is the incenter of " + pss[1] + pss[2] + pss[3];
            case 37:
                if (d)
                    return pss[0] + " : orthocenter of " + pss[1] + pss[2] + pss[3];
                else
                    return pss[0] + " is the orthocenter of " + pss[1] + pss[2] + pss[3];
            case 38:
                if (d)
                    return pss[0] + " : centroid of " + pss[1] + pss[2] + pss[3];
                else
                    return pss[0] + " is the centroid of " + pss[1] + pss[2] + pss[3];
            case 46:
                return "circle(" + pss[0] + "," + pss[1] + ") tangent to circle(" + pss[2] + "," + pss[3] + ")";

            case gib.C_I_LL:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + pss[3] + pss[4];
                else
                    return pss[0] + " is the intersection of " + pss[1] + pss[2] + " and " + pss[3] + pss[4];
            case gib.C_I_LP:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "P(" + pss[3] + ", " + pss[4] + pss[5] + ")";
                else
                    return pss[0] + " is on " + pss[1] + pss[2] + " and " + pss[0] + pss[3] + " is parallel to " + pss[4] + pss[5] + ")";
            case gib.C_I_LC:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "C(" + pss[3] + pss[4] + ")";
                else
                    return pss[0] + " is the intersection of line " + pss[1] + pss[2] + " and circle(" + pss[3] + pss[4] + ")";

            case gib.C_I_LB:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "B(" + pss[3] + pss[4] + ")";
                else
                    return pss[0] + " is the intersection of " + pss[1] + pss[2] + " and perp-bisector of " + pss[3] + pss[4] + "";

            case gib.C_I_LT:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "T(" + pss[3] + ", " + pss[4] + pss[5] + ")";
                else
                    return pss[0] + " is on line " + pss[1] + pss[2] + " and " + pss[0] + pss[3] + " is parallel to " + pss[4] + pss[5];
            case gib.C_I_LR:
                if (d)
                    return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "R(" + pss[3] + ", " + pss[4] + pss[5] + ")";
                else
                    return pss[0] + " is the intersection of " + pss[1] + pss[2] + " and circle(" + pss[3] + "," + pss[4] + pss[5] + ")";
            case gib.C_I_LS:
                return null;
            case gib.C_I_LA:
                return pss[0] + " = " + pss[1] + pss[2] + Cm.s2084 + "A(" + vprint(3, 9, pss) + ")";
            case gib.C_I_PP:
                if (d)
                    return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "P(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3] + " and " + pss[0] + pss[4] + " is parallel to " + pss[5] + pss[6];
            case gib.C_I_PC:
                if (d)
                    return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "C(" + pss[4] + pss[5] + ")";
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3] + " and " + pss[0] + " is on circle(" + pss[4] + pss[5] + ")";
            case gib.C_I_PR:
                if (d)
                    return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "R(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3] + " and " + pss[0] + " is on circle(" + pss[4] + "," + pss[5] + pss[6] + ")";
            case gib.C_I_PT:
                if (d)
                    return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "T(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3] + " and " + pss[0] + pss[4] + " is perpendicular to" + pss[5] + pss[6];
            case gib.C_I_PB:
                if (d)
                    return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "B(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + pss[3] + " and " + pss[0] + " is on the perep-bisector of" + pss[4] + pss[5];
            case gib.C_I_PA:
                return pss[0] + " = P(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "A(" + vprint(3, 9, pss) + ")";

            case gib.C_I_TT:
                if (d)
                    return pss[0] + " = T(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "T(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + pss[3] + " and " + pss[0] + pss[4] + " is perependicular to " + pss[5] + pss[6];

            case gib.C_I_TC:
                if (d)
                    return pss[0] + " = T(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "C(" + pss[4] + pss[5] + ")";
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + pss[3] + " and " + pss[0] + " is on circle(" + pss[4] + pss[5] + ")";

            case gib.C_I_TR:
                if (d)
                    return pss[0] + " = T(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "R(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + pss[3] + " and " + pss[0] + " is on circle(" + pss[4] + "," + pss[5] + pss[6] + ")";
            case gib.C_I_TB:
                if (d)
                    return pss[0] + " = T(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "B(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + pss[3] + " and " + pss[0] + " is on the perp-bisecotr of " + pss[4] + pss[5];
            case gib.C_I_TA:
                return pss[0] + " = T(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "A(" + vprint(3, 9, pss) + ")";

            case gib.C_I_BB:
                if (d)
                    return pss[0] + " = B(" + pss[1] + pss[2] + ")" + Cm.s2084 + "B(" + pss[3] + pss[4] + ")";
                else
                    return pss[0] + " is the intersection of perp-bisector of " + pss[1] + pss[2] + " and " + "perp-bisector of " + pss[3] + pss[4];
            case gib.C_I_BC:
                if (d)
                    return pss[0] + " = B(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "C(" + pss[4] + pss[5] + ")";
                else
                    return pss[0] + " is the intersection of perp-bisector of " + pss[1] + pss[2] + " and circle(" + pss[3] + pss[4] + ")";
            case gib.C_I_BR:
                if (d)
                    return pss[0] + " = B(" + pss[1] + "," + pss[2] + pss[3] + ")" + Cm.s2084 + "R(" + pss[4] + "," + pss[5] + pss[6] + ")";
                else
                    return pss[0] + " is the intersection of perp-bisector of " + pss[1] + pss[2] + " and circle(" + pss[3] + "," + pss[4] + pss[5] + ")";
            case gib.C_I_CC:
                if (d)
                    return pss[0] + " = C(" + pss[1] + pss[2] + ")" + Cm.s2084 + "C(" + pss[3] + pss[4] + ")";
                else
                    return pss[0] + " is the intersection of circle(" + pss[1] + pss[2] + ") and circle(" + pss[3] + pss[4] + ")";
            case gib.C_I_CR:
                if (d)
                    return pss[0] + " = C(" + pss[1] + pss[2] + ")" + Cm.s2084 + "R(" + pss[3] + ", " + pss[4] + pss[5] + ")";
                else
                    return pss[0] + " is the intersection of circle(" + pss[1] + pss[2] + ") and circle(" + pss[3] + "," + pss[4] + pss[5] + ")";

            case gib.CO_COLL:
                return pss[0] + ", " + pss[1] + ", " + pss[2] + " are collinear";
            case gib.CO_PARA:
                if (d)
                    return pss[0] + "" + pss[1] + Cm.s2079 + pss[2] + "" + pss[3];
                else
                    return pss[0] + "" + pss[1] + " is parallel to " + pss[2] + "" + pss[3];
            case gib.CO_PERP:
                if (d)
                    return pss[0] + "" + pss[1] + Cm.s2077 + pss[2] + "" + pss[3];
                else
                    return pss[0] + "" + pss[1] + " is perpendicular to " + pss[2] + "" + pss[3];
            case gib.CO_MIDP:
                if (d)
                    return pss[0] + " : midpoint(" + pss[1] + pss[2] + ")";
                else
                    return pss[0] + " is the midpoint of " + pss[1] + pss[2];
            case gib.CO_CYCLIC:
                if (d)
                    return "Cyclic(" + pss[0] + "," + pss[1] + "," + pss[2] + "," + pss[3] + ")";
                else
                    return pss[0] + ", " + pss[1] + ", " + pss[2] + ", " + pss[3] + " are cyclic";
            case gib.CO_CONG:
                return "|" + pss[0] + pss[1] + "| = |" + pss[2] + pss[3] + "|";
            case gib.CO_ACONG: {
                if (pss[6] != null && pss[7] != null)
                    return Cm.s2078 + "[" + pss[0] + pss[1] + ", " + pss[2] + pss[3] + "] = " + Cm.s2078 + "[" + pss[4] + pss[5] + ", " + pss[6] + pss[7] + "]";
                else
                    return Cm.s2078 + "[" + pss[0] + pss[1] + pss[2] + "] = " + Cm.s2078 + "[" + pss[3] + pss[4] + pss[5] + "]";
            }
            case gib.CO_PBISECT:
                return Cm.P_SHOWD + "??";
            case gib.CO_STRI:
                return Cm.s2080 + pss[0] + pss[1] + pss[2] + Cm.s2083 + Cm.s2080 + pss[3] + pss[4] + pss[5];
            case gib.CO_CTRI:
                return Cm.s2080 + pss[0] + pss[1] + pss[2] + Cm.s2082 + Cm.s2080 + pss[3] + pss[4] + pss[5];


            case gib.NDG_NEQ:
                return pss[0] + " != " + pss[1];
            case gib.NDG_COLL:
                return pss[0] + "," + pss[1] + "," + pss[2] + " are not collinear";
            case gib.NDG_CONG:
                return pss[0] + "" + pss[1] + " != " + pss[2] + pss[3];
            case gib.NDG_PARA:
                return pss[0] + "" + pss[1] + " is not parallel to " + pss[2] + "" + pss[3];
            case gib.NDG_PERP:
                return pss[0] + "" + pss[1] + " is not perpendicular to " + pss[2] + "" + pss[3];
            case gib.NDG_NON_ISOTROPIC:
                return pss[0] + "" + pss[1] + " is non-isotropic";
            case gib.NDG_ACONG: {
                if (pss[6] != null && pss[7] != null)
                    return Cm.s2078 + "[" + pss[0] + pss[1] + ", " + pss[2] + pss[3] + "] != " + Cm.s2078 + "[" + pss[4] + pss[5] + ", " + pss[6] + pss[7] + "]";
                else
                    return Cm.s2078 + "[" + pss[0] + pss[1] + pss[2] + "] != " + Cm.s2078 + "[" + pss[3] + pss[4] + pss[5] + "]";
            }
            case gib.NDG_CYCLIC:
                return pss[0] + "" + pss[1] + "" + pss[2] + "" + pss[3] + " is not cyclic";
            case gib.IN_AG_INSIDE:
                return pss[0] + " is inside " + Cm.s2078 + pss[1] + pss[2] + pss[3];
            case gib.IN_AG_OUTSIDE:
                return pss[0] + " is outside " + Cm.s2078 + pss[1] + pss[2] + pss[3];
            case gib.IN_BETWEEN:
                return pss[0] + " is between " + pss[1] + pss[2];
            case gib.IN_OPP_SIDE:
                return pss[0] + "" + pss[1] + " are on the opposite side of " + pss[2] + pss[3];
            case gib.IN_PARA_INSIDE:
                return pss[0] + " is inside the parallelogram " + pss[1] + pss[2] + pss[3] + pss[4];
            case gib.IN_PG_CONVEX: {
                String str = "polygon ";
                int i = 0;
                while (i < pss.length && pss[i] != null) {
                    str += pss[i];
                    i++;
                }
                return str + " is convex";
            }
            case gib.IN_SAME_SIDE:
                return pss[0] + " and " + pss[1] + " is on the same side of line " + pss[2] + pss[3];
            case gib.IN_TRI_INSIDE:
                return pss[0] + " is inside the triangle " + pss[1] + pss[2] + pss[3];
            case gib.C_O_S:
                return pss[0] + " is on circle(" + pss[1] + pss[2] + pss[3] + ")";
            case gib.C_O_AB:
                return pss[0] + " is on the bisector of " + Cm.s2078 + "[" + pss[1] + pss[2] + pss[3] + "]";
            case gib.C_O_D:
                return pss[0] + " is on the circle D(" + pss[1] + pss[2] + ")";

            default: {
                String st = CST.get_preds(t);
                for (int x = 0; x < pss.length && pss[x] != null; x++)
                    st += " " + pss[x];
                return st;
            }


        }
    }

    public static String vprint(int m, int n, Object[] ps) {
        String s = "";
        for (int i = m; i <= n; i++)
            if (ps[i] != null)
                s += ps;
        return s;
    }

    public static cons charCons(int pt, cons c1, cons c2, Object[] pss) {
        if (c1 == null) {
            c1 = c2;
            c2 = null;
        } else if (c1 != null && c2 != null && c1.type > c2.type) {
            cons c = c1;
            c1 = c2;
            c2 = c;
        }
        int[] p = new int[c1.ps.length];
        p[0] = pt;
        int rt = 0;
        int t1, t2;
        int[] p1, p2;
        p1 = c1.ps;
        t1 = c1.type;
        if (c2 != null) {
            p2 = c2.ps;
            t2 = c2.type;
        } else {
            p2 = null;
            t2 = 0;
        }

        p1 = pcopy(p1);
        t1 = validate_all(pt, t1, p1);
        if (p2 != null) {
            p2 = pcopy(p2);
            t2 = validate_all(pt, t2, p2);
        }

        if (pt == p1[0]) {
            int k = 1;
            for (int i = 1; i < p1.length && p1[i] != 0; i++)
                p[k++] = p1[i];
            if (p2 != null) {
                for (int i = 1; i < p2.length && p2[i] != 0; i++)
                    p[k++] = p2[i];
            }
        } else {
            int k = 0;
            for (int i = 0; i < p1.length && p1[i] != 0; i++)
                p[k++] = p1[i];
        }


        if (p2 == null) {
            rt = t1;
        } else {
            switch (t1) {
                case gib.C_O_L: {
                    switch (t2) {
                        case gib.C_O_L:
                            rt = gib.C_I_LL;
                            break;
                        case gib.C_O_P:
                            rt = gib.C_I_LP;
                            break;
                        case gib.C_O_T:
                            rt = gib.C_I_LT;
                            rt = ge_lt_foot(rt, p);
                            break;
                        case gib.C_O_B:
                            rt = gib.C_I_LB;
                            break;
                        case gib.C_O_A:
                            rt = gib.C_I_LA;
                            break;
                        case gib.C_O_C:
                            rt = gib.C_I_LC;
                            break;
                        case gib.C_O_R:
                            rt = gib.C_I_LR;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_P: {
                    switch (t2) {
                        case gib.C_O_P:
                            rt = gib.C_I_PP;
                            break;
                        case gib.C_O_T:
                            rt = gib.C_I_PT;
                            break;
                        case gib.C_O_B:
                            rt = gib.C_I_PB;
                            break;
                        case gib.C_O_A:
                            rt = gib.C_I_PA;
                            break;
                        case gib.C_O_C:
                            rt = gib.C_I_PC;
                            break;
                        case gib.C_O_R:
                            rt = gib.C_I_PR;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_T: {
                    switch (t2) {
                        case gib.C_O_T:
                            rt = gib.C_I_TT;
                            break;
                        case gib.C_O_B:
                            rt = gib.C_I_TB;
                            break;
                        case gib.C_O_A:
                            rt = gib.C_I_TA;
                            break;
                        case gib.C_O_C:
                            rt = gib.C_I_TC;
                            break;
                        case gib.C_O_R:
                            rt = gib.C_I_TR;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_B: {
                    switch (t2) {
                        case gib.C_O_B:
                            rt = gib.C_I_BB;
                            break;
                        case gib.C_O_A:
                            rt = gib.C_I_BA;
                            break;
                        case gib.C_O_C:
                            rt = gib.C_I_BC;
                            break;
                        case gib.C_O_R:
                            rt = gib.C_I_BR;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_A: {
                    switch (t2) {
                        case gib.C_O_A:
                            rt = gib.C_I_AA;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_C: {
                    switch (t2) {
                        case gib.C_O_C:
                            rt = gib.C_I_CC;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case gib.C_O_R: {
                    switch (t2) {
                        case gib.C_O_R:
                            rt = gib.C_I_RR;
                            break;
                        default:
                            break;
                    }
                    break;
                }
                default:
                    break;  // failed.
            }
        }

        if (rt == 0)
            return null;

        if (t1 == gib.C_O_L && t2 == gib.C_O_A) {

            int i = 0;
            for (; p2[i] != 0; i++) ;

            if (i == 6) {
                if (p2[1] == p2[4] && p2[2] == pt) {
                    int a, b, c;

                    if (p2[2] == p2[3] && p2[0] != p2[5]) {
                        a = p2[0];
                        b = p2[5];
                        c = p2[2];
                    } else if (p2[0] == p2[5] && p2[2] != p2[3]) {
                        a = p2[2];
                        b = p2[3];
                        c = p2[0];
                    } else {
                        a = b = c = 0;
                    }
                    if (a != 0) {
                        rt = gib.C_FOOT;
                        p[0] = pt;
                        p[1] = c;
                        p[2] = a;
                        p[3] = b;
                    }
                }
            }
        }


        cons c = new cons(rt, p.length);
        int i = 0;
        for (i = 0; i < p.length && p[i] != 0; i++)
            c.ps[i] = p[i];
        c.no = i - 1;
        spec(pt, c);
        addPss(c, pss);
        return c;
    }


    public static void spec(int pt, cons c) {
        if (c == null) return;

        switch (c.type) {
            case gib.C_O_A: {
                int n = c.getPts();
                if (n == 8) {
                    if (c.ps[0] == c.ps[4] && c.ps[3] == c.ps[7] && pt == c.ps[1] && pt == c.ps[2] && c.ps[5] == c.ps[6]) {
                        c.type = gib.C_O_S;
                        c.ps[0] = pt;
                        c.ps[1] = c.ps[4];
                        c.ps[2] = c.ps[5];
                        c.ps[3] = c.ps[7];
                        c.ps[4] = c.ps[5] = c.ps[6] = c.ps[7] = 0;
                    } else
                    if (pt == c.ps[0] && pt == c.ps[7] && c.ps[1] == c.ps[2] && c.ps[5] == c.ps[6] && c.ps[1] == c.ps[5] && c.ps[3] != 4) {
                        c.type = gib.C_O_AB;
                        c.ps[0] = pt;
                        c.ps[1] = c.ps[3];
                        c.ps[2] = c.ps[2];
                        c.ps[3] = c.ps[4];
                        c.ps[4] = c.ps[5] = c.ps[6] = c.ps[7] = 0;
                    } else
                    if (pt == c.ps[0] && pt == c.ps[7] && c.ps[1] == c.ps[2] && c.ps[5] == c.ps[6] && c.ps[1] == c.ps[4] && c.ps[3] == c.ps[5]) {
                        c.type = gib.C_O_B;
                        c.ps[0] = pt;
                        c.ps[2] = c.ps[3];
                        c.ps[4] = c.ps[5] = c.ps[6] = c.ps[7] = 0;
                    }
                } else if (n == 6) {
                    if (pt == c.ps[0] && pt == c.ps[5] && c.ps[1] == c.ps[3] && c.ps[2] == c.ps[4]) {
                        c.type = gib.C_O_B;
                        c.ps[0] = pt;
                        if (c.ps[1] > c.ps[2]) {
                            int t = c.ps[1];
                            c.ps[1] = c.ps[2];
                            c.ps[2] = t;
                        }
                        c.ps[3] = c.ps[4] = c.ps[5] = c.ps[6] = 0;
                    } else if (c.ps[0] == c.ps[3] && c.ps[2] == c.ps[5] && pt == c.ps[1]) {
                        c.type = gib.C_O_S;
                        c.ps[0] = pt;
                        c.ps[1] = c.ps[3];
                        c.ps[2] = c.ps[4];
                        c.ps[3] = c.ps[5];
                        c.ps[4] = c.ps[5] = c.ps[6] = 0;
                    } else if (pt == c.ps[0] && pt == c.ps[5] && c.ps[1] == c.ps[4] && c.ps[2] != c.ps[3]) {
                        c.type = gib.C_O_AB;
                        c.ps[0] = pt;
                        c.ps[4] = c.ps[5] = c.ps[6] = c.ps[7] = 0;
                    }

                }
                break;
            }
            case gib.C_O_T: {
                if (c.ps[0] == c.ps[2]) {
                    c.type = gib.C_O_D;
                    c.ps[2] = c.ps[3];
                    c.ps[3] = 0;
                } else if (c.ps[0] == c.ps[3]) {
                    c.type = gib.C_O_D;
                    c.ps[3] = 0;
                }
            }
            break;

            default:
                return;
        }
    }

    public static void addPss(cons c, Object[] pss) {
        int[] p = c.ps;
        int i = 0;
        for (i = 0; i < p.length && p[i] != 0; i++) {
            c.ps[i] = p[i];
            int n = p[i] - 1;
            if (n < pss.length) {
                Object s = pss[p[i] - 1];
                c.pss[i] = s;
            }
        }
        c.no = i - 1;
    }

    public static void reval(int pt, int[] p, int n) {
        int n1 = n / 2;
        boolean c = false;

        for (int i = n1; i < n; i++) {
            if (p[i] == pt) {
                c = true;
                break;
            }
        }
        if (c) {
            for (int i = 0; i < n1; i++) {
                int t = p[i];
                p[i] = p[i + n1];
                p[i + n1] = t;
                if (2 * n < p.length) {
                    t = p[i + n];
                    p[i + n] = p[i + n + n1];
                    p[i + n + n1] = t;
                }
            }
        }
        if (n1 == 1)
            return;
        else
            reval(pt, p, n1);
    }

    public static int ge_lt_foot(int t, int[] p) {
        if (p[1] == p[4] && p[2] == p[5] || p[1] == p[5] && p[2] == p[4]) {
            p[4] = p[5] = 0;
            int t1 = p[3];
            p[3] = p[2];
            p[2] = p[1];
            p[1] = t1;
            return gib.C_FOOT;
        }
        return t;
    }

    public static int validate_all(int pt, int t1, int[] p1) {

        if (t1 == gib.C_EQDISTANCE || t1 == gib.CO_CONG) {
            t1 = validate_cg(pt, p1);
        } else if (t1 == gib.C_EQANGLE || t1 == gib.CO_ACONG || t1 == gib.C_O_A) {
            t1 = validate_ea(pt, p1);
        } else if (t1 == gib.CO_COLL)
            t1 = validate_coll(pt, p1);
        else if (t1 == gib.CO_PARA || t1 == gib.C_O_P)
            t1 = validate_p(pt, p1);
        else if (t1 == gib.CO_PERP || t1 == gib.C_O_T)
            t1 = validate_t(pt, p1);
        return t1;
    }

    public static int validate_ea(int pt, int[] ps) {
        int t1 = gib.C_O_A;
        int i = 0;
        for (; ps[i] != 0; i++) ;
        if (i == 6) {
            if (pt == ps[3] || pt == ps[4] || pt == ps[5]) {
                int t = ps[0];
                ps[0] = ps[3];
                ps[3] = t;
                t = ps[1];
                ps[1] = ps[4];
                ps[4] = t;
                t = ps[2];
                ps[2] = ps[5];
                ps[5] = t;
            }
            if (pt == ps[2]) {
                int t = ps[0];
                ps[0] = ps[2];
                ps[2] = t;
                t = ps[3];
                ps[3] = ps[5];
                ps[5] = t;
            }
            if (ps[1] == ps[5] && ps[2] == ps[4]) {
                ps[4] = ps[5] = 0;
                t1 = gib.C_O_P;
            } else if (ps[0] == ps[5] && ps[1] == ps[4] && ps[2] == ps[3]) {
                t1 = gib.C_O_T;
                ps[3] = ps[1];
                ps[4] = ps[5] = ps[6] = 0;
            } else if (ps[0] == ps[5] && ps[1] == ps[3] && ps[2] == ps[4]) {
                t1 = gib.C_O_B;
                ps[3] = ps[4] = ps[5] = ps[6] = 0;
            }


        } else if (i == 8) {
            reval(pt, ps, 8);
        }
        return t1;
    }

    public static int validate_coll(int pt, int[] ps) {
        if (ps[0] < ps[1])
            exchange(0, 1, ps);
        if (ps[0] < ps[2])
            exchange(0, 2, ps);
        if (ps[1] < ps[2])
            exchange(1, 2, ps);
        return gib.C_O_L;
    }

    public static int validate_p(int pt, int[] ps) {
        if (ps[0] < ps[1])
            exchange(0, 1, ps);
        if (ps[2] < ps[3])
            exchange(2, 3, ps);
        if (ps[0] < ps[2]) {
            exchange(0, 2, ps);
            exchange(1, 3, ps);
        }
        return gib.C_O_P;
    }

    public static int validate_t(int pt, int[] ps) {
        if (ps[0] < ps[1])
            exchange(0, 1, ps);
        if (ps[2] < ps[3])
            exchange(2, 3, ps);
        if (ps[0] < ps[2]) {
            exchange(0, 2, ps);
            exchange(1, 3, ps);
        }
        return gib.C_O_T;
    }

    public static int validate_cg(int pt, int[] ps) {
        if (ps[0] < ps[1])
            exchange(0, 1, ps);
        if (ps[2] < ps[3])
            exchange(2, 3, ps);
        if (ps[0] < ps[2]) {
            exchange(0, 2, ps);
            exchange(1, 3, ps);
        }

        if (ps[0] == ps[2] && ps[0] == pt) {
            ps[2] = ps[3];
            ps[3] = 0;
            return gib.C_O_B;
        }
        return gib.C_O_R;
    }

    public static void exchange(int i, int j, int[] ps) {
        int t = ps[i];
        ps[i] = ps[j];
        ps[j] = t;
    }

    public static int[] pcopy(int[] p) {
        if (p == null)
            return null;

        int n = p.length;
        int[] p1 = new int[n];
        for (int i = 0; i < n; i++)
            p1[i] = p[i];
        return p1;
    }

}
