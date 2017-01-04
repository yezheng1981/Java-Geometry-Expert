package gprover;


import java.util.Vector;


public class gib {

    //********************pred types*************************

    final public static int C_POINT = 1;
    final public static int C_LINE = 2;
    final public static int C_O_L = 3;
    final public static int C_O_P = 4;
    final public static int C_O_T = 5;
    final public static int C_O_B = 6;

    final public static int C_O_A = 7;
    final public static int C_FOOT = 8;
    final public static int C_CIRCLE = 9;
    final public static int C_O_C = 10;
    final public static int C_CIRCUM = 11;
    final public static int C_O_R = 12;

    final public static int C_MIDPOINT = 13;


    final public static int C_EQDISTANCE = 14;
    final public static int C_EQANGLE = 15;


    final public static int C_TRATIO = 16;
    final public static int C_PRATIO = 17;
    final public static int C_NRATIO = 18;
    final public static int C_LRATIO = 19;

    final public static int C_INVERSION = 20;
    final public static int C_REF = 21;
    final public static int C_SYM = 22;

    final public static int C_TRIANGLE = 23;
    final public static int C_QUADRANGLE = 24;
    final public static int C_PENTAGON = 25;
    final public static int C_POLYGON = 26;

    final public static int C_ISO_TRI = 27;
    final public static int C_R_TRI = 28;
    final public static int C_EQ_TRI = 29;
    final public static int C_TRAPEZOID = 30;
    final public static int C_R_TRAPEZOID = 31;
    final public static int C_PARALLELOGRAM = 32;
    final public static int C_LOZENGE = 33;
    final public static int C_RECTANGLE = 34;
    final public static int C_SQUARE = 35;


    final public static int C_ICENT = 36;
    final public static int C_ORTH = 37;
    final public static int C_CENT = 38;
    final public static int C_CONSTANT = 39;


    final public static int C_PSQUARE = 40;
    final public static int C_NSQUARE = 41;
    final public static int C_SANGLE = 42;
    final public static int C_ANGLE_BISECTOR = 43;
    final public static int C_LC_TANGENT = 44;
    final public static int C_RATIO = 45;
    final public static int C_CCTANGENT = 46;
    final public static int C_O_S = 47;
    final public static int C_O_AB = 48;
    final public static int C_O_D = 49;
    final public static int C_EQANGLE3P = 50;


    final public static int C_ICENT1 = 199;

    final public static int C_I_LL = 101;
    final public static int C_I_LP = 102;
    final public static int C_I_LC = 103;
    final public static int C_I_LB = 104;
    final public static int C_I_LT = 105;
    final public static int C_I_LR = 106;
    final public static int C_I_LS = 107;
    final public static int C_I_PP = 108;
    final public static int C_I_PC = 109;
    final public static int C_I_PT = 110;
    final public static int C_I_PB = 111;
    final public static int C_I_TC = 112;
    final public static int C_I_TT = 113;
    final public static int C_I_TB = 114;
    final public static int C_I_BB = 115;
    final public static int C_I_BC = 116;
    final public static int C_I_CC = 117;
    final public static int C_I_CR = 118;
    final public static int C_I_RR = 119;
    final public static int C_I_SS = 120;
    final public static int C_I_AA = 121;

    final public static int C_I_LA = 122;
    final public static int C_I_PA = 123;
    final public static int C_I_PR = 124;
    final public static int C_I_TA = 125;
    final public static int C_I_TR = 126;
    final public static int C_I_BA = 127;
    final public static int C_I_BR = 128;
    final public static int C_I_EQ = 129; // B = C.                                 


    final public static int C_NETRIANGLE = 151;
    final public static int C_PETRIANGLE = 150;

    //********************end of pred types*************************

    //********************conclutions******************************
    final public static int CO_COLL = 50 + 20;
    final public static int CO_PARA = 51 + 20;
    final public static int CO_PERP = 52 + 20;
    final public static int CO_MIDP = 53 + 20;
    final public static int CO_CYCLIC = 54 + 20;
    final public static int CO_CONG = 55 + 20;
    final public static int CO_ACONG = 56 + 20;
    final public static int CO_PBISECT = 57 + 20;
    final public static int CO_TANGENT = 58 + 20;
    final public static int CO_HARMONIC = 59 + 20;
    final public static int CO_PETRI = 60 + 20;
    final public static int CO_STRI = 61 + 20;
    final public static int CO_CTRI = 62 + 20;
    final public static int CO_PROD = 63 + 20;
    final public static int CO_ORTH = 64 + 20;
    final public static int CO_INCENT = 65 + 20;
    final public static int CO_RATIO = 66 + 20;
    final public static int CO_TANG = 67 + 20;
    final public static int CO_NANG = 68 + 20;
    final public static int CO_NSEG = 69 + 20;

    final public static int CO_ATNG = 1111;
    final public static int CO_EQ = 1112;
    final public static int CO_12 = 1903;
    final public static int CO_PET = 1904;
    //********************end of conclutions******************************
    //****************************Ordered .

    final public static int NDG_NEQ = 200;
    final public static int NDG_COLL = 201;
    final public static int NDG_PARA = 202;
    final public static int NDG_PERP = 203;
    final public static int NDG_CYCLIC = 204;
    final public static int NDG_CONG = 205;
    final public static int NDG_ACONG = 206;
    final public static int NDG_NON_ISOTROPIC = 207;
    final public static int NDG_TRIPLEPI = 208;


    //*********************Inequality Predicates
    final public static int IN_BETWEEN = 301;
    final public static int IN_AG_INSIDE = 302;
    final public static int IN_AG_OUTSIDE = 303;
    final public static int IN_TRI_INSIDE = 304;
    final public static int IN_PARA_INSIDE = 305;
    final public static int IN_OPP_SIDE = 306;
    final public static int IN_SAME_SIDE = 307;
    final public static int IN_PG_CONVEX = 308;

    //*************************************

    //*******************************************************************

    //************special polygons**************

    final public static int TRIANGLE = 1;
    final public static int QUADRANGLE = 2;
    final public static int PENTAGON = 3;
    final public static int POLYGON = 4;

    final public static int ISO_TRI = 5;
    final public static int R_TRI = 6;
    final public static int EQ_TRI = 7;
    final public static int TRAPEZOID = 8;
    final public static int R_TRAPEZOID = 9;
    final public static int PARALLELOGRAM = 10;
    final public static int LOZENGE = 11;
    final public static int RECTANGLE = 12;
    final public static int SQUARE = 13;

    //******************end of special polgons***************

    //******************angles**************************
    final public static int A_TIME = 12;
    final public static int A_360 = 360 * A_TIME;
    final public static int A_180 = 180 * A_TIME;
    final public static int A_90 = 90 * A_TIME;
    final public static int A_60 = 60 * A_TIME;
    final public static int A_45 = 45 * A_TIME;
    final public static int A_30 = 30 * A_TIME;
    //******************end of angles**************************

    //****************************rules*******************************
    final public static int R_P_COLL = 1;
    final public static int R_PL_AS = 2;
    final public static int R_P_RA = 3;
    final public static int R_PT_T = 4;

    final public static int R_T_AT90 = 5;
    final public static int R_TT_MDCY = 6;
    final public static int R_TT_PP = 7;
    final public static int R_TT_CY = 8;

    final public static int R_CR_DM_MD = 9;
    final public static int R_CR_DM_T = 10;//
    final public static int R_CR_AS2 = 11;
    final public static int R_CR_P_EQARC = 12;
    //cr_iso?
    final public static int R_CR_INSCRIBE_AS = 13;
    final public static int R_CR_TAN_AS = 14;
    final public static int R_CR_OO_B = 15;

    final public static int R_AS_PLUS = 16;
    final public static int R_AG_PP12 = 17;
    final public static int R_AG_PP13 = 18;
    final public static int R_AG_TT12 = 19;
    final public static int R_AG_TT13 = 20;
    final public static int R_AG_ATN = 21;
    final public static int R_AG_SPECIAL = 22;


    final public static int R_ISOCELES = 23;
    final public static int R_ISO_3L = 24;
    final public static int R_CTRI = 25;

    final public static int R_AAS = 26;
    final public static int R_SAS = 27;
    final public static int R_SSS = 28;
    final public static int R_TTCG2_CT = 29;

    final public static int R_STRI = 30;
    final public static int R_AA_STRI = 31;
    final public static int R_ASRA_STRI = 32;
    final public static int R_ST_RAAS = 33;
    final public static int R_RA_ST_CT = 34;

    final public static int R_MID_CONNECTION = 35;
    final public static int R_RTRI_MD_CY = 36;
    final public static int R_TRI_EQ = 37;
    final public static int R_PYTH_THM = 38;
    final public static int R_TRI_ALL_AG_180 = 39;

    final public static int R_PARALLELOGRAM = 40;
    final public static int R_MID_CONNECTION_TRAPZOID = 41;

    final public static int R_RATIO = 42;
    final public static int R_AG_BISECTOR_ATIO = 43;

    protected static boolean R_SEARCH_ALL_LN = true;
    protected static boolean R_AG_ALL = true;
    public static boolean[] RValue = new boolean[100];

    //aux

    //****************************end of rules************************

    //**************Full Angle Rules.


    final public static int RF_GIB = -1;
    final public static int RF_DEFINITION = 1;
    final public static int RF_MINUS = 2;
    final public static int RF_PARA = 3;
    final public static int RF_PERP = 4;
    final public static int RF_ADDITION = 5;
    final public static int RF_PERP_SPLIT = 6;
    final public static int RF_ISO = 7;
    final public static int RF_INSCRIBE = 8;
    final public static int RF_9 = 9;
    final public static int RF_10 = 10;
    final public static int RF_DM_PERP = 11;
    final public static int RF_12 = 12;
    final public static int RF_13 = 13;
    final public static int RF_14 = 14;
    final public static int RF_15 = 15;
    final public static int RF_16 = 16;
    final public static int RF_17 = 17;
    final public static int RF_18 = 18;
    final public static int RF_ORTH = 19;
    final public static int RF_20 = 20;
    final public static int RF_21 = 21;
    final public static int RF_22 = 22;
    final public static int RF_TT = 23;
    final public static int RF_TT2 = 24;
    final public static int RF_PPO = 25;
    final public static int RF_26 = 26;
    final public static int RF_DM2 = 27;
    final public static int RF_CY = 28;
    final public static int RF_CY2 = 29;

    //////////////////////////////////////////////////////////////////

    final public static int FE_TYPE_ERROR = 1;

    //end of Full Angle Rules.

    //****************************facts*******************************
    protected midpt all_md, last_md;
    protected l_line all_ln, last_ln;
    protected p_line all_pn, last_pn;
    protected t_line all_tn, last_tn;
    protected a_cir all_cir, last_cir;
    protected angles all_as, last_as;
    protected angst all_ast, last_ast;
    protected cong_seg all_cg, last_cg;
    protected cong_seg all_rg, last_rg;
    protected anglet all_at, last_at;
    protected angtn all_atn, last_atn;
    protected sim_tri all_st, last_st;
    protected sim_tri all_ct, last_ct;
    protected ratio_seg all_ra, last_ra;
    protected polygon all_pg, last_pg;

    protected cond all_nd, last_nd;
    protected cond co_db, co_xy;
    // for conclution;
    protected angtr all_tr, last_tr;
    protected l_list all_ns, last_ns;
    // for collection.
    protected s_tris all_sts, last_sts;
    protected s_tris all_cts, last_cts;
    protected c_segs all_cgs, last_cgs;
    //****************************end of facts*******************************

    //***************************inputs******************
    protected gterm gt;
    protected Pro_point[] allpts = new Pro_point[100];
    protected cons[] allcns = new cons[100];
    protected int pts_no, pts_pno;
    protected int gno = 0;
    protected int cns_no;

    protected cond conc = new cond();
    protected int cons_no = 0;
    //***************************end of inputs******************


    protected final static int PFULL = 0;
    protected final static int PTRADITION = 1;

    //*****************************othres***********************
    final protected static a_cir test_c = new a_cir();
    final protected static l_line test_ln = new l_line();
    final protected static ratio_seg test_ra1 = new ratio_seg();
    protected ratio_seg test_ra;
    static cond tm_pr1 = new cond();
    public static long depth = 0;
    final protected static double ZERO = 0.001;
    protected static boolean show_detail = false;


    protected static boolean ck_value = true;

    protected static int P_STATUS = 0;
    protected int tri_type;
    protected int d_base = 1;
    protected int show_dtype = 0;
    protected int printype = 0; // screen  1: text
    protected StringBuffer sout = null;
    protected boolean DEBUG = true;

    //*****************************end of othres***********************

    public static void initRulers() {
        for (int i = 0; i < RValue.length; i++)
            RValue[i] = true;

//        RValue[R_RATIO - 1] = false;
        RValue[R_AG_BISECTOR_ATIO - 1] = false;
        RValue[R_PARALLELOGRAM - 1] = false;
        RValue[R_PYTH_THM-1] = false;
       // RValue[R_RATIO - 1] = false;
        RValue[R_RA_ST_CT - 1] = false;
        //      RValue[R_AA_STRI -1] = false;

    }

    public gib() {

        co_db = new cond();
        co_xy = new cond();

        all_md = new midpt();
        all_ln = new l_line();
        all_pn = new p_line();
        all_tn = new t_line();
        all_cir = new a_cir();
        all_as = new angles();
        all_ast = new angst(0);
        all_at = new anglet();
        all_cg = new cong_seg();
        all_rg = new cong_seg();
        all_st = new sim_tri();
        all_ct = new sim_tri();
        all_ra = new ratio_seg();
        all_nd = new cond();

        co_db = new cond();

        d_base = 0;
        all_sts = new s_tris();
        all_cts = new s_tris();
        all_cgs = new c_segs();
        all_pg = new polygon();
        all_atn = new angtn();


        all_ns = new l_list();
        all_tr = new angtr();

        gt = null;
    }

    public void init_dbase() {

        depth = 0;

        all_md.nx = null;
        all_ln.nx = null;
        all_pn.nx = null;
        all_tn.nx = null;
        all_cir.nx = null;
        all_as.nx = null;
        all_ast.nx = null;
        all_cg.nx = null;
        all_rg.nx = null;
        all_at.nx = null;
        all_atn.nx = null;

        all_st.nx = null;
        all_ct.nx = null;
        all_ra.nx = null;
        all_nd.nx = null;
        all_pg.nx = null;

        co_db.nx = null;
        co_xy.nx = null;
        all_tr.nx = null;
        all_nd.nx = null;
        last_nd = all_nd;

        all_sts.nx = null;
        all_cts.nx = null;
        all_cgs.nx = null;

        last_md = all_md;
        last_ln = all_ln;
        last_pn = all_pn;
        last_tn = all_tn;
        last_cir = all_cir;
        last_as = all_as;
        last_ast = all_ast;
        last_at = all_at;
        last_cg = all_cg;
        last_rg = all_rg;
        last_st = all_st;
        last_ct = all_ct;
        last_ra = all_ra;
        last_nd = all_nd;
        last_pg = all_pg;
        last_atn = all_atn;

        last_sts = all_sts;
        last_cts = all_cts;
        last_cgs = all_cgs;


        last_ns = all_ns;
        last_tr = all_tr;

        co_db.nx = null;

        d_base = 0;
        pts_no = pts_pno = 0;

        for (int i = 0; i < 100; i++) {
            allpts[i] = null;
            allcns[i] = null;
        }
        cns_no = 0;
        conc.pred = 0;
        cons_no = 0;
        vauxpts.clear();
        vauxptf.clear();
        gt = null;
        tm_pr1 = new cond();
    }

    boolean valid(int i) {
        if (i == 0 || i > 100) return true;
        if (i < 0 || i >= RValue.length) return false;
        return RValue[i - 1];
    }

    cond add_e_codb(int n, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
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
        co.cd = co_db.nx;
        return co;
    }

    final double VPTX(int p) {
        return allpts[p].x;
    }

    final double VPTY(int p) {
        return allpts[p].y;
    }

    final String ANAME(int p) {
        if (p < 0) return null;

        if (p > 0) {
            Pro_point t = allpts[p];
            if (t == null) {
                int k = 0;
            }
            return t.name;
//            return allpts[p].name;
        } else
            return null;
    }

    final int SPT(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 1; true; i++) {
            if (allpts[i] == null) break;
            if (s.equals(allpts[i].getName())) return i;
        }
        return 0;
    }

//    final double get_ptx(int i) {
//        Pro_point pt = allpts[i];
//        if (pt != null)
//            return pt.x;
//        else {
//            //#######
//            //Cm.print(i + " error");
//            return 0.0;
//        }
//    }
//
//    final double get_pty(int i) {
//        Pro_point pt = allpts[i];
//        if (pt != null)
//            return pt.y;
//        else {
//            //#######
//            //Cm.print(i + " error");
//            return 0.0;
//        }
//    }

    final int getNumberofProperties() {
        int n = 0;
        midpt md = all_md.nx;
        while (md != null) {
            md = md.nx;
            if(md != null && md.type != 0)
                n++;
        }
        
        l_line ln = all_ln.nx;
        while (ln != null) {
            ln = ln.nx;
            if(ln != null && ln.type != 0 && ln.no > 2)
            n++;
        }
        p_line pn = all_pn;
        while (pn != null) {
            pn = pn.nx;
            if(pn != null && pn.type != 0)
                n++;
        }
        t_line tn = all_tn;
        while (tn != null) {
            tn = tn.nx;
            if(tn != null && tn.type != 0)
                n++;
        }
        a_cir cr = all_cir.nx;
        while (cr != null) {
            cr = cr.nx;
            if(cr != null && cr.type != 0)
                n++;
        }
        angles ag = all_as.nx;
        while (ag != null) {
            ag = ag.nx;
            if(ag != null && ag.type != 0)
                n++;
        }
//        angst ags = all_ast.nx;
//        while (ags != null) {
//            ags = ags.nx;
//            if(ags != null && ags.type != 0)
//                n++;
//        }
        cong_seg cg = all_cg.nx;
        while (cg != null) {
            cg = cg.nx;
            if(cg != null && cg.type != 0)
                n++;
        }
        cong_seg csg = all_rg.nx;
        while (csg != null) {
            csg = csg.nx;
            if(csg != null && csg.type != 0)
                n++;
        }
//        anglet at = all_at.nx;
//        while (at != null) {
//            at = at.nx;
//            if(at != null && at.type != 0)
//                n++;
//        }
//        angtn atn = all_atn;
//        while (atn != null) {
//            atn = atn.nx;
//            if(atn != null && atn.type != 0)
//                n++;
//        }
        sim_tri st = all_st.nx;
        while (st != null) {
            st = st.nx;
            if(st != null && st.type != 0)
                n++;
        }
        sim_tri ct = all_ct.nx;
        while (ct != null) {
            ct = ct.nx;
            if(ct != null && ct.type != 0)
                n++;
        }
        ratio_seg ra = all_ra;
        while (ra != null) {
            ra = ra.nx;
            if(ra != null && ra.type != 0)
                n++;
        }
        polygon pg = all_pg;
        while (pg != null) {
            pg = pg.nx;
            if(pg != null && pg.type != 0)
                n++;
        }
        return n;
    }

    final Pro_point APT(int i) {
        Pro_point pt = allpts[i];
        if (pt == null) {
            int k = 0;
        }
        return pt;
    }

    final int ATYPE(int p, int t) {
        return (allpts)[p].type = t;
    }

    final int ATYPE(int t) {
        return (allpts)[t].type;
    }

    final int AUX(int p, int t) {
        return (allpts)[p].aux = t;
    }

    final int APTS(int p, int i) {
        return ((allpts)[p].ps[i]);
    }

    final double aptx(int p) {
        if (allpts[p] != null)
            return allpts[p].x;
        else {
            eprint("allpts[" + p + "] overflow!");
            return 0.0;
        }
    }

    final double apty(int p) {
        if (allpts[p] != null)
            return allpts[p].y;
        else {
            eprint("allpts[" + p + "] overflow!");
            return 0.0;
        }
    }

    final boolean isConclution(int n) {
        if (allcns[n] != null)
            return allcns[n].is_conc();
        return false;
    }

    final xterm P1(int p) {
        return null;
    }

    final xterm P2(int p) {
        return null;
    }

    public int fd_pt(String s) {
        return SPT(s);
    }

    public Pro_point fd_pt(double x, double y) {
        double m, n;
        for (int i = 1; i <= pts_no; i++) {
            m = APT(i).x;
            n = APT(i).y;
            if ((m - x) * (m - x) + (n - y) * (n - y) < ZERO)
                return APT(i);
        }
        return null;
    }

    void setPrintToString() {
        printype = 1;
        sout = new StringBuffer();//"";
    }

    void setPrintToScreen() {
        printype = 0;
        sout = new StringBuffer();//"";
    }

    public void setNoPrint() {
        printype = -1;
    }

    StringBuffer getFileProve() {
        return sout;
    }

    String getPrintedString() {
        if (sout == null) return "";
        String s = sout.toString();
        sout = new StringBuffer();
        return s;
    }

    void eprint(String s) {
        if (Cm.DEBUG)
            System.out.println(s);
    }

    void gprint(String s) {
        if (printype == 0) {
            if (Cm.DEBUG)
                System.out.print(s);
        } else if (printype == 1) {
            sout.append(s);
        } else {
        }
    }

    void debug_print(String s) {
        if (DEBUG)
            Cm.print(s);
    }

    public boolean isPFull() {
        return P_STATUS == 0;
    }

    public boolean isPTra() {
        return P_STATUS == 1;
    }

    public void gexit(int id) {
        gprint("exit " + id);
        Cm.print("Error, exit " + id);
    }

    void gdb_help() {
    }

    void show_pn(p_line pn) {
        gprint(Cm.s2709);

        gprint("[");
        for (int i = 0; i <= pn.no; i++) {
            show_ln(pn.ln[i], false);
            if (i != pn.no) gprint(";  ");
        }
        gprint("]");
    }

    void show_ln(l_line ln, boolean nk) {
        int i;
        if (ln == null)
            gprint(Cm.s2713);
        else {

            for (i = 0; i <= ln.no; i++) {
                gprint(ANAME(ln.pt[i]));
                if (i != ln.no) gprint(",");
            }
            if (nk) gprint(" " + Cm.s2760);
        }
    }

    void show_tn(t_line tn) {
        l_line l1, l2;

        l1 = tn.l1;
        l2 = tn.l2;
        gprint(Cm.s2715);
        show_ln(l1, false);
        gprint("; ");
        show_ln(l2, false);
        gprint("]");
    }

    void show_atn(angtn atn) {
        show_agll(atn.ln1, atn.ln2);
        gprint(" + ");
        show_agll(atn.ln3, atn.ln4);
        gprint(" = 90");
    }

    void show_cseg(c_segs cg) {
        if (cg.no < 0) {
            gprint("NULL");
            return;
        }

        gprint(ANAME(cg.p1[0]) + ANAME(cg.p2[0]));
        for (int i = 1; i <= cg.no; i++) {
            gprint(" = " + ANAME(cg.p1[i]) + ANAME(cg.p2[i]));
        }
    }

    void show_cg(cong_seg cg) {
        if (cg == null) {
            Cm.print("cong_seg is null");
            return;
        }

        if (cg.t1 == cg.t2) {
            String str = ANAME(cg.p1) + ANAME(cg.p2) + " ";
            gprint(str);
            gprint("= ");
            String s = ANAME(cg.p3) + ANAME(cg.p4) + " ";
            gprint(s);
        } else {

            int p1, p2, p3, p4;
            int m1, m2;
            p1 = p2 = p3 = p4 = m1 = m2 = 0;
            if (cg.t1 <= cg.t2) {
                p1 = cg.p1;
                p2 = cg.p2;
                p3 = cg.p3;
                p4 = cg.p4;
                m1 = cg.t1;
                m2 = cg.t2;
            } else {
                p1 = cg.p3;
                p2 = cg.p4;
                p3 = cg.p1;
                p4 = cg.p2;
                m1 = cg.t2;
                m2 = cg.t1;
            }
            int t1 = Sqrt(m1);
            int t2 = Sqrt(m2);
            String s = "";
            if (t1 > 0 && t2 > 0)
                s = t1 + " / " + t2;
            else {
                if (t1 > 0)
                    s += t1;
                else
                    s += "s(" + m1 + ")";
                s += " / ";
                if (t2 > 0)
                    s += t2;
                else
                    s += "s(" + m2 + ")";
            }
            String str = ANAME(p1) + ANAME(p2) + "/" + ANAME(p3) + ANAME(p4) + " = " + s;
            gprint(str);
        }
    }

    void print_fang(int p1, int p2, int p3, int p4) {
        gprint(get_fang_str(p1, p2, p3, p4));
    }

    String get_fang_str(int p1, int p2, int p3, int p4) {
        int p0;

        if (p1 == p4) {
            p0 = p4;
            p4 = p3;
            p3 = p0;
        } else if (p2 == p3) {
            p0 = p1;
            p1 = p2;
            p2 = p0;
        } else if (p2 == p4) {
            p0 = p1;
            p1 = p2;
            p2 = p0;
            p0 = p4;
            p4 = p3;
            p3 = p0;
        }
        if (p1 != p3)
            return Cm.s2078 + "[" + pt_name(p1) + pt_name(p2) + "," + pt_name(p3) + pt_name(p4) + "]";
        else
            return Cm.s2078 + "[" + pt_name(p2) + pt_name(p3) + pt_name(p4) + "]";

    }

    final String pt_name(int p) {
        if (p != 0) return (APT(p).name);
        else return ("");
    }

    final public void show_agll(l_line ln1, l_line ln2) {  // overrited.
        int n = inter_lls(ln1, ln2);
        if (n == 0)
            print_fang(ln1.pt[0], ln1.pt[1], ln2.pt[0], ln2.pt[1]);
        else {
            int p1 = get_lpt1(ln1, n);
            int p2 = get_lpt1(ln2, n);
            print_fang(p1, n, n, p2);
        }
    }

    final void show_as(angles as) {
        if (as == null) return;
        show_agll(as.l1, as.l2);
        gprint(" = ");
        show_agll(as.l3, as.l4);
    }

    final void show_md(midpt md) {
        String st = Cm.s2705 +
                "[" + ANAME(md.m) + ", " + ANAME(md.a) + ANAME(md.b) + "]";
        gprint(st);
    }

    final void show_ra(ratio_seg ra) {
        if (show_dtype != 0) {
            gprint("[" + ra.type + "]:");
        }
        String str =
                ANAME(ra.r[1]) + ANAME(ra.r[2]) + "*" + ANAME(ra.r[7]) + ANAME(ra.r[8]) +
                        " = " + ANAME(ra.r[3]) + ANAME(ra.r[4]) + "*" + ANAME(ra.r[5]) + ANAME(ra.r[6]);
        gprint(str);
    }

    final void show_cr(a_cir cr) {
        char i;
        if (cr == null) {
            Cm.print("a_cir == null");
            return;
        }
        if (show_dtype != 0) {
            gprint("[" + cr.type + "]");
        }
        gprint(Cm.s2716);
        if (cr.o > 0) {
            String st = st = "[" + ANAME(cr.o) + ", ";
            gprint(st);
        } else {
            gprint("[");
        }
        for (i = 0; i <= cr.no; i++)
            gprint(ANAME(cr.pt[i]));
        gprint("]");
    }

    final void show_ct(sim_tri st) {
        gprint(Cm.s2722);
        if (show_dtype != 0) {
            gprint("[" + st.type + "]");
        }
        String s = "[" + st.dr + "." +
                ANAME(st.p1[0]) + ANAME(st.p1[1]) + ANAME(st.p1[2]) +
                "." + ANAME(st.p2[0]) + ANAME(st.p2[1]) + ANAME(st.p2[2]) + "] ";
        gprint(s);
    }

    final void show_st(sim_tri st) {
        gprint(Cm.s2720);

        String s = "[" + st.dr + "." +
                ANAME(st.p1[0]) + ANAME(st.p1[1]) + ANAME(st.p1[2]) +
                "." + ANAME(st.p2[0]) + ANAME(st.p2[1]) + ANAME(st.p2[2]) + "] ";
        gprint(s);
    }

    final void show_sts(s_tris st) {
        String s = "";
        int i = 0;
        for (; i <= st.no; i++) {
            s += "[" + st.dr[i] + "," +
                    ANAME(st.p1[i]) + ANAME(st.p2[i]) + ANAME(st.p3[i]) + "] ";
        }
        s = i + ". " + s;
        gprint(s);
    }


    final public void exit(int v) {
        Cm.print("System exit: " + v);
        System.exit(v);
    }

    final public void gerror(String s) {
        Cm.print("Error: " + s);
        System.exit(0);
    }

    final int inter_lls(l_line l1, l_line l2) {
        int i, j;
        if (l1 == null || l2 == null) return (0);
        if (l1 == l2) return 0;
        for (i = 0; i <= l1.no; i++)
            for (j = 0; j <= l2.no; j++) {
                if (l1.pt[i] == l2.pt[j]) return (l1.pt[i]);
            }
        return (0);
    }

    final int get_lpt1(l_line l1, int p1) {
        char j;
        for (j = 0; j <= l1.no; j++) {
            if (l1.pt[j] != p1) return (l1.pt[j]);
        }
        return (0);
    }

    final int get_lpt2(l_line l1, int p1, int p2) {
        char j;
        for (j = 0; j <= l1.no; j++) {
            if (l1.pt[j] != p1 && l1.pt[j] != p2) return (l1.pt[j]);
        }
        return (0);
    }

    final public cond getDefaultCond(cclass cc) {
        cond co = new cond();
        co.pred = 0;

        if (cc instanceof l_line) {
            l_line ln = (l_line) cc;
            co.pred = CO_COLL;
            co.nx = null;
            co.p[0] = ln.pt[0];
            co.p[1] = ln.pt[1];
            co.p[2] = ln.pt[2];
        } else if (cc instanceof p_line) {
            p_line ln = (p_line) cc;
            co.pred = CO_PARA;
            co.nx = null;
            co.p[0] = ln.ln[0].pt[0];
            co.p[1] = ln.ln[0].pt[1];
            co.p[2] = ln.ln[1].pt[0];
            co.p[3] = ln.ln[1].pt[1];
        } else if (cc instanceof t_line) {
            t_line ln = (t_line) cc;
            co.pred = CO_PERP;
            co.nx = null;
            co.p[0] = ln.l1.pt[0];
            co.p[1] = ln.l1.pt[1];
            co.p[2] = ln.l2.pt[0];
            co.p[3] = ln.l2.pt[1];
        } else if (cc instanceof a_cir) {
            a_cir c = (a_cir) cc;
            co.pred = CO_CYCLIC;
            co.nx = null;
            for (int i = 0; i < 4; i++)
                co.p[i + 1] = c.pt[i];
        } else if (cc instanceof c_segs) {
            c_segs cg = (c_segs) cc;
            co.pred = CO_CONG;
            co.p[0] = cg.p1[0];
            co.p[1] = cg.p2[0];
            co.p[2] = cg.p1[1];
            co.p[3] = cg.p2[1];
        } else if (cc instanceof angst) {
            angst ag = (angst) cc;
            co.pred = CO_ACONG;
            co.nx = null;

            l_line l1 = ag.ln1[0];
            l_line l2 = ag.ln2[0];

            int t = inter_lls(l1, l2);
            int p1 = get_lpt1(l1, t);
            int p2 = get_lpt1(l2, t);

            l1 = ag.ln1[1];
            l2 = ag.ln2[1];

            int t1 = inter_lls(l1, l2);
            int p3 = get_lpt1(l1, t1);
            int p4 = get_lpt1(l2, t1);

            co.p[0] = p1;
            co.p[1] = t;
            co.p[2] = t;
            co.p[3] = p2;
            co.p[4] = p3;
            co.p[5] = t1;
            co.p[6] = t1;
            co.p[7] = p4;

        } else if (cc instanceof midpt) {
            midpt md = (midpt) cc;
            co.pred = CO_MIDP;
            co.nx = null;
            co.p[0] = md.m;
            co.p[1] = md.a;
            co.p[2] = md.b;
        } else if (cc instanceof s_tris) {
            s_tris sm = (s_tris) cc;
            //if(sm.dr)
            if (sm.st == 1)
                co.pred = CO_CTRI;
            else
                co.pred = CO_STRI;
            co.nx = null;
            co.p[0] = sm.p1[0];
            co.p[1] = sm.p2[0];
            co.p[2] = sm.p3[0];
            co.p[3] = sm.p1[1];
            co.p[4] = sm.p2[1];
            co.p[5] = sm.p3[1];
        } else if (cc instanceof ratio_seg) {
            ratio_seg seg = (ratio_seg) cc;
            co.pred = CO_PROD;
            co.nx = null;
            for (int i = 0; i < 8; i++)
                co.p[i] = seg.r[i + 1];
        } else if (cc instanceof anglet) {
            anglet at = (anglet) cc;
            co.pred = CO_TANG;
            co.nx = null;
            co.p[0] = at.get_pt1();
            co.p[1] = at.p;
            co.p[2] = at.get_pt2();
            co.p[3] = at.v;
        } else if (cc instanceof cong_seg) {
            cong_seg cg = (cong_seg) cc;
            co.pred = CO_CONG;
            co.p[0] = cg.p1;
            co.p[1] = cg.p2;
            co.p[2] = cg.p3;
            co.p[3] = cg.p4;
            co.p[4] = cg.t1;
            co.p[5] = cg.t2;
        } else
            return null;
        return co;
    }

    //show
    ///////////////////////////////////

    int check_tri_dr(int p1, int p2, int p3, int p4, int p5, int p6) {
        double r1 = (aptx(p2) - aptx(p1)) * (apty(p3) - apty(p1)) -
                (aptx(p3) - aptx(p1)) * (apty(p2) - apty(p1));

        double r2 = (aptx(p5) - aptx(p4)) * (apty(p6) - apty(p4)) -
                (aptx(p6) - aptx(p4)) * (apty(p5) - apty(p4));

        if (r1 * r2 >= 0)
            return 1;
        else
            return -1;
    }

    final void add_checkError() {
        ck_value = false;
        gprint("On Check Error!");
    }

    final boolean check_coll(int p1, int p2, int p3) {
        return Math.abs((apty(p2) - apty(p1)) * (aptx(p3) - aptx(p1)) -
                (aptx(p2) - aptx(p1)) * (apty(p3) - apty(p1))) < ZERO;
    }

    final boolean check_coll(int p1, int p2, int p3, int p4) {
        return check_coll(p1, p2, p3) && check_coll(p1, p2, p4);
    }

    final boolean check_para(l_line l1, l_line l2) {
        return check_para(l1.pt[0], l1.pt[1], l2.pt[0], l2.pt[1]);
    }

    final boolean check_para(int p1, int p2, int p3, int p4) {
        return Math.abs((apty(p2) - apty(p1)) * (aptx(p4) - aptx(p3)) -
                (aptx(p2) - aptx(p1)) * (apty(p4) - apty(p3))) < ZERO;
    }

    final boolean check_perp(int p1, int p2, int p3, int p4) {
        return Math.abs((apty(p2) - apty(p1)) * (apty(p4) - apty(p3)) + (aptx(p4) - aptx(p3)) *
                (aptx(p2) - aptx(p1))) < ZERO;
    }

    protected boolean check_eqangle(int p1, int p2, int p3, int p4, int p5, int p6) {
        if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0 || p5 == 0 || p6 == 0) {
            Cm.print("null point in eqangle");
            return false;
        }
        return Math.abs(getAngleValue(p1, p2, p2, p3) - getAngleValue(p4, p5, p5, p6)) < ZERO;
    }

    protected boolean check_atn(int p1, int p2, int p3, int p4, int p5, int p6) {
        double r = getAngleValue(p1, p2, p3) + getAngleValue(p4, p5, p6);
        return Math.abs(r - Math.PI / 2) < ZERO || Math.abs(r + Math.PI / 2) < ZERO;
    }

    protected boolean check_eqangle(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0 || p5 == 0 || p6 == 0) {
            Cm.print("null point in eqangle");
            return false;
        }
        double r1 = getAngleValue(p1, p2, p3, p4);
        double r2 = getAngleValue(p5, p6, p7, p8);
        double r = Math.abs(r1 - r2);
        return r < ZERO || Math.abs(r - Math.PI) < ZERO;
    }

    protected boolean check_eqangle_t(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0 || p5 == 0 || p6 == 0) {
            Cm.print("Check EQANgle ==0");
            return false;
        }

        double r1 = getAngleValue_t(p1, p2, p3, p4);
        double r2 = getAngleValue_t(p5, p6, p7, p8);
        double r = Math.abs(Math.abs(r1) - Math.abs(r2));
        return r < ZERO;
    }

    public boolean check_angle_ls_90(int p1, int p2, int p3) {

        double r1 = getAngleValue(p1, p2, p3);
        return Math.abs(Math.abs(r1) - Math.PI / 2) < ZERO;
    }

    protected boolean x_inside(int p1, int p2, int p3) {
        double r1 = ((aptx(p1) - aptx(p2)) * (aptx(p1) - aptx(p3)));
        double r2 = ((apty(p1) - apty(p2)) * (apty(p1) - apty(p3)));
        if (r1 < 0 && r2 < 0) return true;
        if (Math.abs(r1) < ZERO && r2 < 0) return true;
        if (Math.abs(r2) < ZERO && r1 < 0) return true;
        return false;
    }

    protected double getAngleValue_t(int p1, int p2, int p3, int p4) {
        int p0;
        if (p1 == p4) {
            p0 = p4;
            p4 = p3;
            p3 = p0;
        } else if (p2 == p3) {
            p0 = p1;
            p1 = p2;
            p2 = p0;
        } else if (p2 == p4) {
            p0 = p1;
            p1 = p2;
            p2 = p0;
            p0 = p4;
            p4 = p3;
            p3 = p0;
        }
        if (p1 != p3)
            return getAngleValue(p2, p3, p4);
        else
            return getAngleValue(p1, p2, p3, p4);
    }

    protected boolean check_eqdistance(int p1, int p2, int p3, int p4, double t1, double t2) {
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        double x3 = aptx(p3);
        double y3 = apty(p3);
        double x4 = aptx(p4);
        double y4 = apty(p4);
        double r = Math.abs(Math.pow(x2 - x1, 2) * t2 + Math.pow(y2 - y1, 2) * t2
                - Math.pow(x4 - x3, 2) * t1 - Math.pow(y4 - y3, 2) * t1);

        return Math.abs(r) < ZERO;
    }

    protected boolean check_eqdistance(int p1, int p2, int p3, int p4) {
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        double x3 = aptx(p3);
        double y3 = apty(p3);
        double x4 = aptx(p4);
        double y4 = apty(p4);
        return Math.abs(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
                - Math.pow(x4 - x3, 2) - Math.pow(y4 - y3, 2)) < ZERO;
    }

    protected boolean check_ratio(int a, int b, int c, int d, int p, int q, int r, int s) {
        return Math.abs(length2(a, b) * length2(r, s) - length2(c, d) * length2(p, q)) < ZERO;
    }

    public boolean ck_4peq(int p1, int p2, int p3, int p4) {
        return p1 == p3 && p2 == p4 || p1 == p4 && p2 == p3;
    }

    public boolean ck_dr(int p1, int p2, int p3, int p4) {
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        double x3 = aptx(p3);
        double y3 = apty(p3);
        double x4 = aptx(p4);
        double y4 = apty(p4);
        double r1 = (y2 - y1) * (y4 - y3);
        double r2 = (x2 - x1) * (x4 - x3);
        return (r1 > 0 && r2 > 0) || (r1 == 0 && r2 > 0) || (r1 > 0 && r2 == 0);
    }

    protected double length2(int p1, int p2) {
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
    }

    protected double getAngleValue(int p1, int p2, int p3) {
        return getAngleValue(p1, p2, p3, p2);
    }

    protected double getAngleValue(int p, l_line l1, l_line l2) {
        int a, b;
        if (p == l1.pt[0])
            a = l1.pt[1];
        else
            a = l1.pt[0];
        if (p == l2.pt[0])
            b = l2.pt[1];
        else
            b = l2.pt[0];
        return getAngleValue(a, p, b, p);
    }

    protected boolean check_ateq(int a, int b, int c, int v) {
        double r = getAngleValue(a, b, c);
        double x = Math.abs(r * A_180 / Math.PI - v);
        boolean d = x < 0.01 || Math.abs(x - A_180) < ZERO;
        if (!d) {
            int k = 0;
        }
        return d;
    }

    protected boolean check_tn_eq(l_line l1, l_line l2, l_line l3, l_line l4, int p1, int p2) {
        double r = getAngleValue(p1, l1, l2);
        double r1 = getAngleValue(p2, l3, l4);
        if (Math.abs(r - r1) < 0.01) return true;
        if (Math.abs(Math.abs(r - r1) - Math.PI) < ZERO)
            return false;
        else
            return false;
    }

    protected double getAngleValue(int p1, int p2, int p3, int p4) {
        if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0) {
            int k = 0;
            return 0;
        }
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        double x3 = aptx(p3);
        double y3 = apty(p3);
        double x4 = aptx(p4);
        double y4 = apty(p4);


        double dx1 = x1 - x2;
        double dy1 = y1 - y2;
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0)
            r1 += Math.PI;

        double t = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        dx1 = dx1 / t;
        dy1 = dy1 / t;

        double dx2 = x3 - x4;
        double dy2 = y3 - y4;

        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0)
            r2 += Math.PI;

        t = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        dx2 = dx2 / t;
        dy2 = dy2 / t;
        double dr = ((r2 - r1));
        if (dr >= Math.PI)
            dr = dr - Math.PI * 2;
        else if (dr < -Math.PI) dr = dr + Math.PI * 2;
        return dr;
    }

    boolean check_simtri(int p1, int p2, int p3, int p4, int p5, int p6) {

        double r1 = getRatio(p1, p2, p4, p5);
        double r2 = getRatio(p1, p3, p4, p6);
        double r3 = getRatio(p2, p3, p5, p6);

        return (Math.abs(r1 - r2) < ZERO) && (Math.abs(r1 - r3) < ZERO);
    }

    boolean check_congtri(int p1, int p2, int p3, int p4, int p5, int p6) {

        double r1 = getRatio(p1, p2, p4, p5);
        double r2 = getRatio(p1, p3, p4, p6);
        double r3 = getRatio(p2, p3, p5, p6);

        return (Math.abs(r1 - r2) < ZERO) && (Math.abs(r1 - r3) < ZERO) &&
                (Math.abs(length2(p1, p2) - length2(p4, p5)) < ZERO);
    }

    boolean check_mid(int p1, int p2, int p3) {
        double x1 = aptx(p1);
        double y1 = apty(p1);
        double x2 = aptx(p2);
        double y2 = apty(p2);
        double x3 = aptx(p3);
        double y3 = apty(p3);
        return Math.abs(x2 + x3 - 2 * x1) < ZERO && Math.abs(y2 + y3 - 2 * y1) < ZERO;
    }

    double getRatio(int p1, int p2, int p3, int p4) {
        double r1 = (Math.pow(aptx(p1) - aptx(p2), 2) + Math.pow(apty(p1) - apty(p2), 2)) /
                (Math.pow(aptx(p3) - aptx(p4), 2) + Math.pow(apty(p3) - apty(p4), 2));
        return r1;
    }


    int Sqrt(int n) {
        int i = 1;
        while (i * i < n)
            i++;
        if (i * i == n) return i;
        return -1;

    }

    final public boolean same_tri(int p1, int p2, int p3, int q1, int q2, int q3) {
        if (
                p1 == q1 && (p2 == q2 && p3 == q3 || p2 == q3 && p3 == q2) ||
                        p1 == q2 && (p2 == q1 && p3 == q3 || p2 == q3 && p3 == q1) ||
                        p1 == q3 && (p2 == q1 && p3 == q2 || p2 == q2 && p3 == q1))
            return true;
        return false;
    }


    public void add_ast(angles as) {
        angst ast = all_ast.nx;
        while (ast != null) {
            if (ast.addAngle(as))
                return;
            ast = ast.nx;
        }
        ast = new angst();
        ast.addAngle(as);
        return;
    }

    public void collect_angst() {

        Vector v = new Vector();
        angles as = all_as.nx;
        Vector vt = new Vector();
        while (as != null) {
            if (as.type != 0 && as.l1 != as.l2 && as.l3 != as.l4) {
                vt.add(as);
            }
            as = as.nx;
        }

        while (vt.size() != 0) {
            angles a1 = (angles) vt.get(0);
            addAngst(a1, v);
            vt.remove(0);
            int len = 0;
            do {
                len = vt.size();
                for (int i = 0; i < vt.size(); i++) {
                    angles a = (angles) vt.get(i);
                    if (insertAngle(a))
                        vt.remove(i);
                }

            } while (len > vt.size());
        }
    }

    public void addAngst(angles ag, Vector v) {
        angst a = new angst();
        a.addAngle(ag);
        last_ast.nx = a;
        last_ast = a;
    }

    public boolean insertAngle(angles ag) {
        if (ag.l1 == ag.l3 && ag.l2 == ag.l4 || ag.l1 == ag.l2 && ag.l3 == ag.l4)
            return true;

        angst ast = all_ast.nx;

        while (ast != null) {
            if (ast.addAngle(ag))
                return true;
            ast = ast.nx;
        }
        return false;
    }

    public angst fd_ast(l_line l1, l_line l2) {
        angst ast = all_ast.nx;
        while (ast != null) {
            if (ast.contain(l1, l2)) return ast;
            ast = ast.nx;
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    /// auxpoint list

    long gcd(long l1, long l2) {
        long l;
        if (l1 < 0L) {
            l1 = -l1;
        }
        if (l2 < 0L) {
            l2 = -l2;
        }
        if (l1 > l2) {
            l = l1;
            l1 = l2;
            l2 = l;
        }
        while (l1 != 0L) {
            l = l2 % l1;
            l2 = l1;
            l1 = l;
        }
        if (l2 == 0) {
            int k = 0;
        }
        return (l2);
    }

    protected Vector vauxpts = new Vector();
    protected Vector vauxptf = new Vector();

    int get_auxptn() {
        return vauxpts.size();
    }

    final public static void setValue(int n, boolean v) {
        RValue[n - 1] = v;
    }
}
