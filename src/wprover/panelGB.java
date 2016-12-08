package wprover;

import gprover.cndg;
import gprover.cons;
import gprover.gterm;
import maths.PolyBasic;
import maths.TDono;
import maths.TMono;
import maths.TPoly;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class panelGB extends panelAlgebric implements MouseListener {

    private Vector vndgs;
    private boolean prs = false;
    private static long TIME = 1000000;
    private JPopupMenu menu;

    public panelGB(drawProcess dp, wuTextPane tpane) {
        super(dp, tpane);
        menu = new JPopupMenu();
        JMenuItem it = new JMenuItem("Save as Maple Format");
        menu.add(it);
        it.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAsMaple();
            }
        });
        tpane.addMouseListener(this);
    }


    public void stopRunning() {
        running = false;
        PolyBasic.setbbStop(true);
        this.addString("\n");
        this.addString("icon4", "icon4");
        this.addString("The Process Is Stopped By The User.");

    }


    public void prove(gterm tm, drawProcess dp) {
        if (running)
            return;

        tpane.setText("");
        _mremainder = null;
        gt = tm;
        main = new Thread(this, "GbProver");
        running = true;
        main.start();
        startTimer();
    }

    public void startTimer() {
        if (gxInstance != null) {
            Timer t = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (running) {
                        rund = RunningDialog.startTimer(gxInstance, "GBasis is Running");
                        rund.setPanelGB(panelGB.this);
                    }
                    Timer t = (Timer) e.getSource();
                    t.stop();
                }
            });

            t.start();
        }

    }


    protected int div(TMono m1, TPoly p1) {
        if (poly.pzerop(m1))
            return 0;
        Vector vt = new Vector();

        while (p1 != null) {
            TMono t = p1.poly;
            vt.add(0, t);
            if (t.x == m1.x)
                break;
            p1 = p1.next;
        }

        int index = vt.size();

        long time = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (i >= vt.size())
                break;

            TMono m = (TMono) vt.get(i++);
            TMono md = poly.pcopy(m);
            m1 = poly.prem(m1, md);
            if (m1 != null && m1.x == 9) {
                int k = 0;
            }
            long t1 = System.currentTimeMillis();
//            addDiv(--index, m1, m.x, t1 - time);
            time = t1;
            if (poly.pzerop(m1))
                return 0;
            if (!running)
                return 1;
        }
        String s = poly.printSPoly(m1);
        addString("Remainder:  " + s);

        return 1;
    }

    protected TMono getTMono(cons c) {
        return dp.getTMono(c);
    }

    public int addMM(TMono m, Vector v, int param) {
        GeoPoly gp = GeoPoly.getPoly();

        if (gp.plength(m) == 1) {
            while (m != null && m.deg > 0 && m.x > 0) {
                TMono m1 = gp.pth(m.x, 1, 1);
                m1 = gp.n_ndg(m1, param--);
                v.add(m1);
                m = m.coef;
            }
        } else {
            m = gp.n_ndg(m, param--);
            v.add(m);
        }

        return param;

    }

    private boolean is_ndg_set() {
        return gt != null && gt.getNcons().size() > 0;
    }

    public void getNDGS(Vector v3) {
        int t = 3;
        int param = -1;
        GeoPoly gp = GeoPoly.getPoly();

        if (t == 3) {
            vndgs = gt.getNcons();
        }


        if (t == 0 || t == 3) {     // from FULL
            for (int i = 0; i < vndgs.size(); i++) {
                cndg nd = (cndg) vndgs.get(i);
                TMono m = gp.mm_poly(nd, dp);
                addString1(nd.toString() + "\n");
                if (m != null) {
                    param = addMM(m, v3, param);


                }
            }
        } else if (t == 1) { // from DB
            Vector v = dp.getNDGS();
            for (int i = 0; i < v.size(); i++) {
                TMono m = (TMono) v.get(i);
                if (m != null) {
                    param = addMM(m, v3, param);
                }
            }
        }
    }


    public void run() {

        if (gt == null) {
            running = false;
            if (rund != null)
                rund.stopTimer();
            return;
        }

//        /if (is_ndg_set())
//            this.gbasis1();
//        else
        gbasis();

        PolyBasic.setbbStop(false);
        running = false;
        if (rund != null)
            rund.stopTimer();
    }

    public void gbasis1() {

        String sc = gt.getConcText();
        cons cc = gt.getConclution();

        TMono mc = getTMono(cc);
        if (mc == null) {
            running = false;
            return;
        }


        addAlgebraicForm();
        addString2("The equational hypotheses:");

        Vector vc = dp.getAllConstraint();
        int n = 1;
        Vector pp = new Vector();


        for (int i = 0; i < vc.size(); i++) {
            constraint c = (constraint) vc.get(i);
            if (c.is_poly_genereate) {
                c.PolyGenerate();
                TPoly p1 = constraint.getPolyListAndSetNull();
                if (p1 != null)
                    addString1(n++ + ": " + c.toString() + "\n");
                while (p1 != null) {
                    TMono m = p1.getPoly();
                    if (m != null) {
                        poly.ppush(m, pp);
                        addString("  " + poly.printSPoly(m));
                    }
                    p1 = p1.next;
                }
            }
        }


        addString2("Nondegenerate Conditions");
        Vector v3 = new Vector();
        this.getNDGS(v3);
        Vector p = v3;
        printTP(p);


        for (int i = 0; i < p.size(); i++) {
            TMono m = (TMono) p.get(i);
            poly.ppush(m, pp);
        }

        if (prs) {
            addString2("Poly set before gbasis");
            printTP(pp);
        }

        int dx = p.size() + 2;
        poly.upValueTM(pp, dx);

        int index = 1;
        long t = System.currentTimeMillis();

        boolean r1 = false;
//        if (true) {
//            sbasis();
//            return;
//        }

        while (true) {
            if (index == 2) {
                int k = 0;
            }

            if (!prs) {
                addString2(index + ": Poly set before bb-reduce");
                printTP(pp);
            }
            r1 = true;


            pp = poly.bb_reduce(pp, t);
            //           if (System.currentTimeMillis() - t > 10000)
            //             break;

            if (!prs) {
                addString2(index++ + ": Poly set after bb-reduce");
                printTP(pp);
            }
//            }
            if (gb_finished(pp))
                break;
            //       if (pp.size() > 30)
            //            break;

            Vector tp = poly.s_polys(pp);
            if (tp.size() != 0) {
                tp = poly.bb_reduce(tp, t);
                if (!prs) {
                    addString2("S - Polynomials");
                    printTP(tp);
                }
//                }
                for (int i = 0; i < tp.size(); i++)
                    poly.ppush((TMono) tp.get(i), pp);
            } else {
                break;
            }
            if (!running)
                return;
        }


        String s1 = poly.printSPoly(mc);
        poly.upValueTM(mc, dx);
        mc = poly.b_reduce(mc, pp);
        poly.upValueTM(mc, -dx);
        poly.upValueTM(pp, -dx);
        String s2 = poly.printSPoly(mc);

        if (prs) {
            addString2("Poly set after gbasis");
            printTP(pp);
        }


        addString2("The conclution: ");
        addString1(sc + "\n");

        addString(s1);
        addString2("The conclution after reduce:");
        addString(s2);

        if (mc == null) {
            addString("icon1", "icon1");
            addString1("The conclution is true");
        } else {
            addString("icon2", "icon2");
            addString1("The conclution is false");
        }
        running = false;
    }

    public void printTP(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (m != null)
                addString(poly.printSPoly(m));
        }
    }

    public boolean gb_finished(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            TMono m = (TMono) v.get(i);
            if (poly.plength(m) == 1 && m.x == 0)
                return true;
        }
        return false;
    }

    public void test(Vector pp, int dx) {
        int size = pp.size();


        if (size < 2) return;

        int index = size - 3;
        Vector vp = new Vector();
        for (int i = size - 2; i < size; i++)
            vp.add(pp.get(i));


        for (int i = index; i >= 0; i--) {
            addString2(i + "GBASIS");
            printTP(vp);

            vp.add(0, pp.get(i));
            gbasis(vp);
        }

        addString2(-1 + "GBASIS");
        poly.upValueTM(vp, -dx);
        printTP(vp);


    }


    public void gbasis(Vector pp) {
//        long t = System.currentTimeMillis();

        while (true) {
            pp = poly.bb_reduce(pp, 10000);
            if (!isRunning())
                break;

            if (gb_finished(pp))
                break;


            Vector tp = poly.s_polys(pp);

            if (tp.size() != 0) {
                for (int i = 0; i < tp.size(); i++)
                    poly.ppush((TMono) tp.get(i), pp);

            } else {
                break;
            }
        }
    }

    public void dbasis(Vector pp) {

        GeoPoly basic = GeoPoly.getPoly();

        Vector v = new Vector();
        int size = pp.size();
        for (int i = 0; i < size - 1; i++) {
            TMono m1 = (TMono) pp.get(i);
            for (int j = i + 1; j < size; j++) {
                TMono m2 = (TMono) pp.get(j);
                v.clear();
                v.add(basic.p_copy(m1));
                v.add(basic.p_copy(m2));

                basic.printVpoly(v);
                while (true) {
                    v = poly.bb_reduce(v, -1);

                    if (gb_finished(v))
                        break;


                    Vector tp = poly.s_polys(v);

                    if (v.size() >= 1) {
                        basic.printVpoly(v);
                        basic.printVpoly(tp);
                    }
                    tp = poly.bb_reduce(tp, -1);


                    if (tp.size() != 0) {
                        for (int k = 0; k < tp.size(); k++)
                            poly.ppush((TMono) tp.get(k), v);
                    } else {
                        break;
                    }
                }
                basic.printVpoly(v);
            }
        }
    }


    public TMono sbasis(int x, Vector v, TMono mc) {

        Vector vg = new Vector();
        if (v.size() == 0) return mc;
        GeoPoly basic = GeoPoly.getPoly();

        int nn = x;
        int dx = nn / 2 + 2;
        TMono m1, m2;
        int param = 0;
        Vector vrs = new Vector();


        for (int n = 1; n < nn / 2 + 1; n++) {
            m1 = m2 = null;

            for (int i = 0; i < v.size(); i++) {
                TMono m = (TMono) v.get(i);
                if (m.x == 2 * n || m.x == 2 * n - 1) {
                    if (m1 == null)
                        m1 = m;
                    else m2 = m;
                }
            }

            if (m1 != null)
                v.remove(m1);
            if (m2 != null)
                v.remove(m2);

            if (m1 != null || m2 != null) {
                TMono t = basic.ll_delta(2 * n, m1, m2);
                if (basic.plength(t) == 1 && t.x == 0 && t.val.intValue() != 0)
                    t = null;
                --param;
                int dd = -param + 3;
                t = basic.n_ndg(t, param);

                vg.clear();

                basic.ppush(m2, vg);
                basic.ppush(m1, vg);
                basic.ppush(t, vg);
//                System.out.println(basic.getAllPrinted(m1));
//                System.out.println(basic.getAllPrinted(m2));

                basic.upValueTM(vg, dd);
//
//                if(false)
//                {
//                    Vector vtp = basic.specialTreatment(m1, m2, param + dd);
//                    if (vtp.size() != 0) {
//                        vg.clear();
//                        for (int i = 0; i < vtp.size(); i++)
//                            basic.ppush((TMono) vtp.get(i), vg);
//                        basic.ppush(t, vg);
//                    }
//                }

                this.gbasis(vg);
                basic.upValueTM(vg, -dd);
                for (int i = 0; i < vg.size(); i++) {
                    TMono tt = (TMono) vg.get(i);
                    basic.ppush(tt, vrs);
                }
            }
        }

        basic.upValueTM(vrs, dx);

        Vector vnds = basic.getcnds(vrs, dx);
        basic.bb_reduce(vrs, 10000, true);
        if (!running)
            return null;

        Vector vnn = new Vector();
        for (int i = 0; i < vnds.size(); i++) {

            TMono m = (TMono) vnds.get(i);
            m = basic.b_reduce(basic.p_copy(m), vrs);
            if (!running)
                return null;
            TDono d = basic.splitDono(m, dx);

            if (d != null)
                vnn.add(d);
        }

        Vector vco = basic.parseCommonDono(vnn, dx);
        for (int i = 0; i < vnds.size(); i++) {
            TMono d = (TMono) vnds.get(i);
            basic.ppush(d, vrs);
        }

        if (vco.size() != 0) {
            for (int i = 0; i < vco.size(); i++) {
                TMono m = (TMono) vco.get(i);
                basic.ppush(m, vrs);
            }

        }

        basic.bb_reduce(vrs, 10000, true);
        mc = basic.b_reduce(mc, vrs);

        while (basic.ctLessdx(mc, dx)) {
            mc = basic.reduceMDono(mc, vnn, dx); // reduced all u parameters.
            mc = basic.b_reduce(mc, vrs);
            if (!running)
                return null;
        }

        TMono mcr = basic.p_copy(mc);
        basic.eraseCommonDono(vnn);


        Vector vnn1 = new Vector();
        for (int i = 0; i < vnn.size(); i++) {
            TDono d = (TDono) vnn.get(i);
            TMono m = basic.p_copy(d.p2);
            m = basic.reduceMDono(m, vnn, dx);
            if (!running)
                return null;
            m = basic.b_reduce(m, vrs);
            if (!running)
                return null;
            vnn1.add(m);
        }

        basic.upValueTM(vrs, -dx);
        basic.upValueDM(vnn, -dx);
        basic.upValueTM(mc, -dx);
        basic.upValueTM(mcr, -dx);
        basic.upValueTM(vnn1, -dx);

        this.printTP(vrs);
//        this.printVectorExpanded(vrs, 0);

        addSVdd(vnn1);
        v.clear();
        v.addAll(vrs);
        return mc;
    }

    public void printVectorExpanded(Vector vrs, int dx) {
        GeoPoly basic = GeoPoly.getPoly();

        basic.upValueTM(vrs, -dx);
        for (int i = 0; i < vrs.size(); i++) {
            TMono ma = (TMono) vrs.get(i);
            String st = basic.getExpandedPrint(ma);
            if (st.endsWith("*"))
                st = st.substring(0, st.length() - 1);
            else if (st.endsWith("-") || st.endsWith("+"))
                st += "1";
            System.out.println(st);
        }
        basic.upValueTM(vrs, dx);
    }

    public void addSVdd(Vector v) {
        GeoPoly basic = GeoPoly.getPoly();

        addString2(getLanguage(1116, "The Nondegenerate Conditions"));
        for (int i = 0; i < v.size(); i++) {
//            TDono d = (TDono) v.get(i);
            TMono m = (TMono) v.get(i);
            basic.coefgcd(m);
            TMono mf = basic.get_factor1(m);
            if (mf == null) {
                basic.factor1(m);
                String s = basic.printNPoly(m);
                this.addString(s);
            } else {
                TMono ff = mf;
                while (mf != null) {
                    basic.div_factor1(m, mf.x, mf.deg);
                    mf = mf.coef;
                }
                String s = basic.printNPoly(ff, m);
                this.addString(s);

            }
        }

    }

    public void printVDD(Vector v) {
        GeoPoly basic = GeoPoly.getPoly();

        for (int i = 0; i < v.size(); i++) {

            TDono d = (TDono) v.get(i);
            basic.sprint(d.p1);
            System.out.print("*( ");
            basic.sprint(d.p2);
            System.out.print(" )");
            if (d.c.value() > 0)
                System.out.print(" + ");
            basic.sprint(d.c);
            System.out.print("\n");


        }

    }

    public void gbasis() {
        GeoPoly basic = GeoPoly.getPoly();
        String sc = gt.getConcText();
        cons cc = gt.getConclution();
        TMono mc = getTMono(cc);
        if (mc == null) {
            running = false;
            return;
        }


        addAlgebraicForm();
        addString2(getLanguage(1103, "The Equational Hypotheses:"));

        Vector vc = dp.getAllConstraint();
        int n = 1;
        Vector pp = new Vector();


        for (int i = 0; i < vc.size(); i++) {
            constraint c = (constraint) vc.get(i);
            if (c.is_poly_genereate) {
                c.PolyGenerate();
                TPoly p1 = constraint.getPolyListAndSetNull();
                if (p1 != null)
                    addString1(n++ + ": " + c.toString() + "\n");
                while (p1 != null) {
                    TMono m = p1.getPoly();
                    if (m != null) {
                        poly.ppush(m, pp);
                        addString("  " + poly.printSPoly(m));
                    }
                    p1 = p1.next;
                }
            }
        }

        addString2(getLanguage(1114, "The Initial Polynomial Set"));
        printTP(pp);

        String s1 = poly.printSPoly(mc);

        addString2(getLanguage(1115, "The Groebner Basis: ") + "GB = ");
//        addString2("GB = ");
        Vector v = dp.getPBMono();

        int x = basic.getMaxX(v);
        int dx = x / 2 + 2;
        basic.upValueTM(mc, dx);

        mc = sbasis(x, v, mc);
        if (!running)
            return;
        pp = v;

        String s2 = poly.printSPoly(mc);
        addString2(getLanguage(1105, "The Conclution: "));
        addString1(sc + "\n");
        addString(s1);
        addString2(getLanguage(1117, "The Conclution After Reduce:"));
        addString(s2);

        if (mc == null) {
            addString("icon1", "icon1");
            addString1(getLanguage(1108, "The conclution is true"));
        } else {
            addString("icon2", "icon2");
            addString1(getLanguage(1109, "The conclution is false"));
            if (poly.plength(mc) > 2) {
                _mremainder = mc;
                addString("\n");
                addButton();
            }
        }

        running = false;
    }


    ////////////////////////////////////////////////////////////
    public void mouseClicked(MouseEvent e) {
        menu.show((JComponent) e.getSource(), e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void saveAsMaple() {
        JFileChooser filechooser1 = new JFileChooser();
        String dr = GExpert.getUserDir();
        filechooser1.setCurrentDirectory(new File(dr));
        int result = filechooser1.showDialog(this, "Save");
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = filechooser1.getSelectedFile();
            FileOutputStream fp;
            try {
                if (f.exists()) {
                    f.delete();
                    fp = new FileOutputStream(f, true);
                } else {
                    f.createNewFile();
                    fp = new FileOutputStream(f, false);
                }
                if (fp == null) {
                    return;
                }
                writeMaple(fp);
                fp.close();
            } catch (IOException ee) {
                JOptionPane.showMessageDialog(this, ee.getMessage(),
                        "Save Failed", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void writeMaple(FileOutputStream out) throws IOException {
        GeoPoly basic = GeoPoly.getPoly();
        cons cc = gt.getConclution();
        TMono mc = getTMono(cc);

        // Part 1: Order for the variables.
        boolean fr = true;
        out.write("vars := [".getBytes());

        Vector vp = dp.getPointList();
        for (int i = vp.size() - 1; i >= 0; i--) {
            CPoint pt = (CPoint) vp.get(i);
            String s1 = pt.x1.getString();
            String s2 = pt.y1.getString();
            if (GeoPoly.vzero(pt.x1.xindex))
                continue;
            if (GeoPoly.vzero(pt.y1.xindex))
                continue;
            if (fr) {
                fr = false;
            } else out.write(", ".getBytes());
            out.write((s2 + ", " + s1).getBytes());
        }


        Vector v = dp.getPBMono();
        int x = basic.getMaxX(v);

        Vector vg = new Vector();
        int nn = x;
        TMono m1, m2;
        int param = 0;

        for (int n = 1; n < nn / 2 + 1; n++) {
            m1 = m2 = null;

            for (int i = 0; i < v.size(); i++) {
                TMono m = (TMono) v.get(i);
                if (m.x == 2 * n || m.x == 2 * n - 1) {
                    if (m1 == null)
                        m1 = m;
                    else m2 = m;
                }
            }

            if (m1 != null)
                v.remove(m1);
            if (m2 != null)
                v.remove(m2);

            if (m1 != null || m2 != null) {
                TMono t = basic.ll_delta(2 * n, m1, m2);
                if (basic.plength(t) == 1 && t.x == 0 && t.val.intValue() != 0)
                    t = null;
                --param;
                t = basic.n_ndg(t, param);
                if (t != null)
                    out.write((", u" + (-param)).getBytes());

                basic.ppush(m2, vg);
                basic.ppush(m1, vg);
                basic.ppush(t, vg);
            }
        }
        out.write("];".getBytes());

// Part 2: All polynomials.
//        out.write(("\n" + vg.size()).getBytes());
        for (int i = 0; i < vg.size(); i++) {
            TMono m = (TMono) vg.get(i);
            out.write("\n".getBytes());
            out.write(("P" + i + " := " + poly.getExpandedPrint(m) +" ;").getBytes());
        }
        String st = poly.getExpandedPrint(mc);
        out.write(("\n C := " + st +" ;").getBytes());

    }
}