package wprover;

import gprover.*;

import javax.swing.*;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

import maths.TMono;
import maths.TPoly;
import maths.CharSet;

public class panelWu extends panelAlgebric implements Runnable, MouseListener {
    private TMono mremainder;


    public panelWu(drawProcess dp, wuTextPane tpane) {
        super(dp, tpane);
        tpane.addMouseListener(this);
    }

    public void setLanguage(Language lan) {
        this.lan = lan;
    }

    public void stopRunning() {
        if (!running)
            return;
    }

    public void prove(gterm tm) {
        if (running)
            return;

        tpane.setText("");
        gt = tm;
        main = new Thread(this, "wuProver");
        running = true;
        main.start();
    }

    protected void addDiv(int index, TMono m1, int x, long t) {
        index++;
        String s = "R_" + (index - 1) + " = prem(R_" + index + ", h_" + (index - 1) + ") =  ["
                + poly.printHead(m1) + ", " + poly.plength(m1) + "]";
        addString(s);
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
        addString("R_" + index + " = [" + poly.printHead(m1) + ", " + poly.plength(m1) + "]");
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
            index--;
            addDiv(index, m1, m.x, t1 - time);
            time = t1;
            if (poly.pzerop(m1)) {
                addString(getLanguage(1110, "Remainder") + " = R_" + (index) + " = 0");
                return 0;
            }
            if (!running)
                return 1;
        }
        String s = poly.printMaxstrPoly(m1);
        if (m1 != null)
            s += " != 0";

        addString(getLanguage(1110, "Remainder") + " = " + s);
        mremainder = m1;

        return 1;
    }

    public TMono getTMono(cons c) {
        TMono m = dp.getTMono(c);

        return m;
    }


    public void run() {
        if (gt == null) {
            running = false;
            return;
        }

        String sc = gt.getConcText();
        cons cc = gt.getConclution();
        TMono mc = getTMono(cc);
        if (mc == null) {
            running = false;
            return;
        }

        addAlgebraicForm();
        addString2(getLanguage(1103, "The Equational Hypotheses:"));

        TPoly pp = null;
        Vector vc = dp.getAllConstraint();
        int n = 1;
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
                        addString("  " + poly.printSPoly(m));
                        pp = poly.ppush(m, pp);
                    }
                    p1 = p1.next;
                }
            }
        }


        addString2(getLanguage(1104, "The Triangulized Hypotheses") + " (TS):");
        TPoly p1 = dp.getPolyList();
        int i = 0;
        while (p1 != null) {
            addString("h" + i++ + ": " + poly.printSPoly(p1.poly));
            p1 = p1.next;
        }


        addString2(getLanguage(1105, "The Conclution") + " (CONC): ");
        addString1(sc + "\n");
        addString(poly.printSPoly(mc));

        addString2(getLanguage(1106, "Successive Pseudo Remainder of CONC wrpt TS :"));
        int r = 0;

        try {
            if (mc != null) {
                r = div(mc, dp.getPolyList());
            }
        } catch (final java.lang.OutOfMemoryError e) {
            running = false;
            JOptionPane.showMessageDialog(panelWu.this, getLanguage(1130, "System Run Out Of Memory") + "\n" +
                    getLanguage(1131, "The Theorem Is Not Proved!"), getLanguage(1130, "System Run Out of Memory"), JOptionPane.WARNING_MESSAGE);
            addString("\n" + getLanguage(1130, "System Run Out of Memory!"));
            addString("icon3", "icon3");
            addString1(getLanguage(1109, "The conclution is not proved"));
            return;
        }
        scrollToEnd();

        if (r == 0) {
            addString("icon1", "icon1");
            addString1(getLanguage(1108, "The conclution is true"));
        } else if (r == 1) {
            addString("icon2", "icon2");
            addString1(getLanguage(1107, "The conclution is false"));
            if (poly.plength(mc) > 2) {
                _mremainder = mc;
                addString("\n");
                addButton();
            }
        }

        running = false;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            if (mremainder != null) {
                String s = poly.getExpandedPrint(mremainder);
                JDialog dlg = new JDialog((Frame) null, "Remainder");
                dlg.setSize(300, 200);
                JTextPane p = new JTextPane();
                p.setText(s);
                dlg.getContentPane().add(new JScrollPane(p));
                dlg.setLocation(200, 200);
                dlg.setVisible(true);
            }
        }
    }


    public void mousePressed(MouseEvent e) {
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }
}

