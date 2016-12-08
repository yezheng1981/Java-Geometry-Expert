package wprover;

import gprover.cons;
import gprover.gib;
import gprover.gterm;
import maths.TMono;
import maths.TPoly;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public abstract class panelAlgebric extends JScrollPane implements Runnable, ActionListener {
    protected drawProcess dp;
    protected GeoPoly poly = GeoPoly.getPoly();
    protected boolean running = false;
    protected gterm gt = null;
    protected wuTextPane tpane;
    protected Thread main;
    protected GExpert gxInstance;
    protected Language lan;
    protected TMono _mremainder = null;
    protected RunningDialog rund;


    public String getLanguage(int n, String s) {
        if (lan == null)
            return s;

        return lan.getString(n, s);
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public void setXInstance(GExpert gx) {
        if (gx != null) {
            this.gxInstance = gx;
            lan = gx.getLan();
        }
    }


    public panelAlgebric(drawProcess dp, wuTextPane tpane) {
        super(tpane);
        this.dp = dp;
        this.tpane = tpane;
        tpane.addListnerToButton(this);
    }

    public abstract void stopRunning();


    public void clearAll() {
        running = false;
        tpane.clearAll();
    }

    protected void addString(String s) {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), s + "\n", doc.getStyle("regular"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

    }

    protected void addString(String s, String type) {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), " ", doc.getStyle(type));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

    }

    protected void addButton() {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), "button", doc.getStyle("button"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }

    protected void addString1(String s) {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), s, doc.getStyle("bold"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }

    protected void addString2(String s) {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), "\n" + s + "\n", doc.getStyle("head"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }

    protected void addString2s(String s) {
        try {
            StyledDocument doc = tpane.getStyledDocument();
            doc.insertString(doc.getLength(), s + "\n", doc.getStyle("head"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }


    protected void addAlgebraicForm() {
        addString2s(getLanguage(1101, "The Algebraic Form: "));
        Vector vp = dp.getPointList();
        int n = vp.size();
        if (n == 0)
            return;

        for (int i = 0; i < vp.size(); i++) {
            CPoint pt = (CPoint) vp.get(i);
            String s1 = pt.x1.getString();
            String s2 = pt.y1.getString();
            if (GeoPoly.vzero(pt.x1.xindex))
                s1 = "0";
            if (GeoPoly.vzero(pt.y1.xindex))
                s2 = "0";
            addString1(pt + ": (" + s1 + "," + s2 + ")  ");
            if (i != 0 && i != vp.size() && i % 5 == 0)
                addString1("\n");
        }
        addString1("\n");
    }


    public void actionPerformed(ActionEvent e) {
        JTextArea a = new JTextArea();
//        a.setWrapStyleWord(true);
        a.setLineWrap(true);
        String s = GeoPoly.getInstance().getExpandedPrint(_mremainder);
        a.setText(s);
        JDialog dlg = new JDialog(gxInstance.getFrame());
        dlg.setTitle("Remainder");
        dlg.setSize(400, 300);
        dlg.getContentPane().add(new JScrollPane(a, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        gxInstance.centerDialog(dlg);
        dlg.setVisible(true);
    }

    protected void scrollToEnd() {
        JScrollBar bar = this.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
        repaint();
    }

    protected TMono getTMono(cons c) {
        return dp.getTMono(c);
    }

    public void run() {
    }

    public TPoly get_ndgs(Vector vlist, int z) {
        TPoly pp = null;
        for (int i = 0; i < vlist.size(); i++) {
            cons c = (cons) vlist.get(i);
            if (c.type != gib.C_POINT) {
                TMono mx = dp.getTMono(c);
                if (mx != null) {
                    mx = poly.n_ndg(mx, z++);
                    pp = poly.ppush(mx, pp);
                }
            }
        }
        return pp;
    }

    public Vector get_ndgs(int param, Vector vlist) {

        Vector pp = new Vector();
        for (int i = 0; i < vlist.size(); i++) {
            cons c = (cons) vlist.get(i);
            if (c.type != gib.C_POINT) {
                TMono mx = get_ndg(param--, c.type, dp.getPoints(c));
                if (mx != null)
                    pp.add(0, mx);
            }
        }
        return pp;
    }

    public Vector get_ndgsx(int param, Vector vlist) {

        Vector pp = new Vector();
        for (int i = 0; i < vlist.size(); i++) {
            TMono m = (TMono) vlist.get(i);
            m = poly.n_ndg(m, param--);
            poly.ppush(m, pp);
        }
        return pp;
    }

    public TMono get_ndg(int z, int t, CPoint[] pp) {
        switch (t) {
            case gib.C_I_LL:  // AB // CD.
                return poly.n_ndg(poly.parallel(pp[1], pp[2], pp[3], pp[4]), z);
            case gib.C_I_LP:
                return poly.n_ndg(poly.parallel(pp[1], pp[2], pp[4], pp[5]), z);
            case gib.C_I_LT:
                return poly.n_ndg(poly.perpendicular(pp[1], pp[2], pp[4], pp[5]), z);
            case gib.C_I_PP:
                return poly.n_ndg(poly.parallel(pp[2], pp[3], pp[5], pp[6]), z);
            case gib.C_FOOT:
                return poly.n_ndg(poly.isotropic(pp[2], pp[3]), z);
            case gib.C_I_LB:
                return poly.n_ndg(poly.perpendicular(pp[0], pp[1], pp[2], pp[3]), z);
            case gib.C_SQUARE:
                return poly.n_ndg(poly.isotropic(pp[0], pp[1]), z);
            case gib.C_O_C:
                return poly.n_ndg(poly.isotropic(pp[1], pp[2]), z);
            case gib.C_CIRCUM:
                return poly.n_ndg(poly.collinear(pp[1], pp[2], pp[3]), z);

        }
        return null;
    }
}