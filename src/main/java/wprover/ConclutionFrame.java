package wprover;

import maths.CharSet;
import maths.TPoly;
import maths.TMono;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

public class ConclutionFrame extends JBaseDialog implements  ActionListener, WindowListener {

    private static GeoPoly poly = GeoPoly.getPoly();
    private static CharSet charset = CharSet.getinstance();
    DPanel d;

    drawProcess dp;

    JPanel pinf;

    JLabel label;
    JButton pbutton = new JButton("Prove");

    public ConclutionFrame(drawProcess dp) {

        this.dp = dp;
        this.setTitle("Conclusion");

        Dimension VD = new Dimension(15, 40);
        Dimension HD = new Dimension(40, 15);


        JPanel panelAll = new JPanel();
        panelAll.setLayout(new BoxLayout(panelAll, BoxLayout.X_AXIS));

        panelAll.add(Box.createRigidArea(VD));

        JPanel innerPanel = new JPanel() {
            public Dimension getMaximumSize() {
                return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
            }
        };
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        panelAll.add(innerPanel);
        panelAll.add(Box.createRigidArea(VD));

        label = new JLabel();
        innerPanel.add(Box.createRigidArea(new Dimension(40, 30)));
        innerPanel.add(new JLabel("Conclusion: "));
        innerPanel.add(label);
        innerPanel.add(Box.createRigidArea(HD));
        innerPanel.add(pbutton);
        innerPanel.add(Box.createRigidArea(HD));


        this.setSize(800, 600);
        pinf = new JPanel();
        pinf.setSize(200, 400);
        pbutton.addActionListener(this);

        getContentPane().add(panelAll, BorderLayout.LINE_START);
        panelAll.add(pinf);
        this.setLocation(300, 200);
        this.addWindowListener(this);
    }


    public void actionPerformed(ActionEvent e) {
    }


    public void windowClosed(WindowEvent e) {
        int i = 0;
    }

    ;

    public void windowIconified(WindowEvent e) {
    }

    ;

    public void windowDeiconified(WindowEvent e) {
    }

    ;

    public void windowActivated(WindowEvent e) {

    }

    ;

    public void windowDeactivated(WindowEvent e) {
        int i = 0;
    }

    ;

    public void windowClosing(WindowEvent e) {


    }

    ;

    public void windowOpened(WindowEvent e) {
    }

    ;

    private void prove(int type) {
        Vector vl = dp.getSelectList();
        TPoly plist = null;

        Object[] obj = new Object[4];

        for (int i = 0; i < 4; i++)
            obj[i] = null;

        for (int i = 0; i < vl.size(); i++)
            obj[i] = vl.get(i);

        TPoly pclist = dp.getCopyPolyBacklist();


        pclist = poly.OptimizePoly(pclist);
        plist = poly.OptimizePoly(plist);
        pclist = charset.charset(pclist);
        pclist = charset.reverse(pclist);


        TMono tp = plist.getPoly();
        int i = 0;
        TPoly tl = pclist;
        while (tl != null) {
            i++;
            tl = tl.getNext();
        }
        int n = i;
        StyledDocument doc = null;//field.getStyledDocument();
        int result = -1;

        try {

            doc.insertString(doc.getLength(), "Step 1 convert the geometric conditions into polynomial form: \n" + dp.getPolyString(0), doc.getStyle("small"));

            doc.insertString(doc.getLength(), "The conclusion polynomial is: \nc = " + poly.printSPoly(tp) + "\n", doc.getStyle("small"));

            doc.insertString(doc.getLength(), "Step 2 Transform the hypothesis polynomial set into trianguar form:\n"

                    + dp.getPolyString(1), doc.getStyle("small"));

            doc.insertString(doc.getLength(), "\nStep 3 Successive Pseudo Division:\n"
                    + "Let R_" + n + " = c (the conclusion polynomial)\n"
                    , doc.getStyle("small"));
        } catch (BadLocationException ble) {

            System.err.println("Couldn't insert initial text into text pane.");
        }

        try {
            long time = System.currentTimeMillis();

            while (pclist != null) {
                // CMisc.print("step");
                // poly.print(tp);
                // poly.print(pclist.getPoly());
                tp = poly.prem(tp, pclist.getPoly());
                long t = System.currentTimeMillis() - time;
                doc.insertString(doc.getLength(), "Length of R_" + (i - 1) + " = prem(R_" + i + ", f_" + (i - 1) + ") =  " + poly.plength(tp)
                        + "\t\ttime = " + (float) t / 1000 + '\n', doc.getStyle("small"));
                i--;
                pclist = pclist.getNext();

            }

            time = System.currentTimeMillis() - time;

            result = poly.plength(tp);
            doc.insertString(doc.getLength(), "\nStep 4 check the final remainder:", doc.getStyle("small"));

            if (result == 0)
                doc.insertString(doc.getLength(),
                        "Since the final remainder R0 is zero, and we have"
                                + "\n   the following remainder formula for "
                                + "successive pseudo division:\n"
                                + "\n     "
                                + "I0^s0 I1^s1 ... I" + n + "^s"
                                + n + " c = Q0 f0 + Q1 f1 + ... + Q"
                                + n + " f" + n + " + R0\n"
                                + "\nthe conclusion polynomial c must be 0 "
                                + "if all the I_i are not zero."
                                + "\nThe " + " theorem is proved under non-degenerate conditions "
                                + "\n\n     I_0 != 0, ..., I_" + n + " != 0\n\n"
                                + "where I_i is the leading coefficient of  f_i."
                                + "used time =  " + (double) time / 1000 + " seconds \n"
                                + "\n\nFor an elemntary exposition of the underlying"
                                + " theory see Chou's 1984 paper included in this CD "
                                + "distribution.\n Also see Chou and Gao's paper "
                                + "'A Class of Geometry Statements of Constructive "
                                + "Type and Geometry Theorem Proving', "
                                + "TR-89-37, CS department, UT, Austin, 1989. QED.\n"
                                + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n",

                        doc.getStyle("small"));
            else {

                doc.insertString(doc.getLength(), "since the final remainder R_" + i + " is not zero,\n"
                        + "the statement is not confirmed\n", doc.getStyle("small"));
                doc.insertString(doc.getLength(), "The remain polynomial is: \nc = " + poly.printSPoly(tp) + "\n", doc.getStyle("small"));

            }
        } catch (BadLocationException ble) {

            System.err.println("Couldn't insert initial text into text pane.");
        }

    }

    public void runc(final int type) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                {
                    prove(type);
                }
            }
        });
    }
}

