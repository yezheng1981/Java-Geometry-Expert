package wprover;

import gprover.Cm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class NumCheckDialog extends JBaseDialog implements DiagramUpdater, ItemListener, WindowListener {

    private JComboBox bx;
    private JComboBox[] bxs = new JComboBox[6]; //bx, bx1, bx2, bx3, bx4, bx5, bx6;
    private GExpert gxInstance;

    private ImageIcon icon1, icon2;

    private JLabel[] labels = new JLabel[6]; //label1, label2, label3, label4, label5, label6;

    private JPanel cards;
    int TYPE;


    public static String[] ST = {"Collinear", "Parallel", "Perpendicular", "Cyclic", "Equal Distance", "Equal Angle"};
    public static int[] SN = {3, 4, 4, 4, 4, 6};

    public NumCheckDialog(GExpert gx) {
        super(gx.getFrame());
        gxInstance = gx;
        this.setTitle(GExpert.getLanguage("Numerical Check"));
        this.addWindowListener(this);

        icon1 = GExpert.createImageIcon("images/ptree/hook.gif");
        icon2 = GExpert.createImageIcon("images/ptree/cross.gif");


        JPanel top = new JPanel();

        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] sss = new String[ST.length];
        for (int i = 0; i < ST.length; i++)
            sss[i] = GExpert.getLanguage(ST[i]);

        bx = new JComboBox(sss);
        panel.add(bx);
        bx.addItemListener(this);

        Vector v = gxInstance.dp.getPointList();
        for (int i = 0; i < bxs.length; i++) {
            bxs[i] = new JComboBox(v);
            panel.add(bxs[i]);
            panel.add(Box.createHorizontalStrut(3));
            bxs[i].addItemListener(this);
        }

        top.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 1));
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            panel2.add(labels[i]);
        }

        top.add(panel2);
        cards = new JPanel(new CardLayout(1, 1));
        cards.add(ST[0], new Panel_Coll());
        cards.add(ST[1], new Panel_Para());
        cards.add(ST[2], new Panel_Perp());
        cards.add(ST[3], new Panel_Cyclic());
        cards.add(ST[4], new Panel_EQDis());
        cards.add(ST[5], new Panel_EQAng());
        top.add(cards);

        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        ActionListener ls = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Clear")) {
                    NumCheckDialog.this.unSelectAllPoints();
                } else if (s.equals("Close")) {
                    NumCheckDialog.this.setVisible(false);
                }
            }
        };
        JButton b1 = new JButton(GExpert.getLanguage("Clear"));
        JButton b2 = new JButton(GExpert.getLanguage("Close"));
        b1.setActionCommand("Clear");
        b2.setActionCommand("Close");

        p3.add(b1);
        p3.add(b2);
        b1.addActionListener(ls);
        b2.addActionListener(ls);
        top.add(Box.createVerticalGlue());
        top.add(p3);
        setVisibleStatus(1);
        bx.setSelectedIndex(-1);
        this.unSelectAllPoints();
        this.getContentPane().add(top);
        this.setSize(400, 250);
        this.UpdateDiagram();
        gxInstance.dp.addDiagramUpdaterListener(this);
    }


    public void UpdateDiagram() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(getStringFromPoint((CPoint) bxs[i].getSelectedItem()));
        }
        int n = bx.getSelectedIndex();
        if (n >= 0) {
            Panel_Basic b = (Panel_Basic) cards.getComponent(n);
            b.DiagramUpdate();
        }
    }

    public void addSelectPoint(CPoint p) {
        for (int i = 0; i < bxs.length; i++) {
            if (bxs[i].getSelectedIndex() < 0) {
                bxs[i].setSelectedItem(p);
                return;
            }
        }

    }

    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if (o == bx) {
            int n = bx.getSelectedIndex();
            setVisibleStatus(n);
            unSelectAllPoints();

            if (n >= 0) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ST[n]);
            }
        } else
            UpdateDiagram();
    }

    public void setVisibleStatus(int n) {
        int num = 0;
        if (n == -1)
            num = 4;
        else num = SN[n];
        for (int i = 0; i < bxs.length; i++) {
            if (i < num) {
                bxs[i].setVisible(true);
                labels[i].setVisible(true);
            } else {
                bxs[i].setVisible(false);
                labels[i].setVisible(false);
            }
        }
    }

    public void unSelectAllPoints() {
         Vector v = gxInstance.dp.getPointList();
        for (int i = 0; i < bxs.length; i++) {
        }

        for (int i = 0; i < bxs.length; i++) {
            bxs[i].setSelectedIndex(-1);
        }
    }

    public CPoint getPt(int n) {
        return (CPoint) bxs[n].getSelectedItem();
    }

    public boolean check_filled() {
        for (int i = 0; i < bxs.length; i++) {
            if ((bxs[i].isVisible() && bxs[i].getSelectedIndex() == -1)) return false;
        }
        return true;
    }

    public String getStringFromPoint(CPoint p) {
        if (p == null) return "      ";

        float x = (float) p.getx();
        float y = (float) p.gety();

        return " " + p.getname() + ":    x = " + x + ",  y = " + y;
    }


    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        gxInstance.dp.RemoveDiagramUpdaterListener(this);
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }


    public void windowDeactivated(WindowEvent e) {
    }


    /////////////////////////////////////////////////////////////////////
    class Panel_Button extends JPanel {

        public Panel_Button() {
            this.setLayout(new FlowLayout(FlowLayout.LEADING));
        }
    }

    abstract class Panel_Basic extends JPanel {
        JLabel lex, ltex;
        String pstring;

        public Panel_Basic(String s) {
            pstring = s;
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            lex = new JLabel();
            this.add(lex);

            ltex = new JLabel();
            this.add(ltex);

        }

        private void setValue(boolean t) {
            if (t) {
                ltex.setIcon(icon1);
                ltex.setText(GExpert.getLanguage("TRUE"));
                ltex.setForeground(Color.green.darker());
            } else {
                ltex.setIcon(icon2);
                ltex.setText(GExpert.getLanguage("FALSE"));
                ltex.setForeground(Color.red.darker());
            }
        }

        private void reset() {
            ltex.setText("");
            ltex.setIcon(null);
        }

        public void DiagramUpdate() {
            boolean t = check_filled();
            if (!t)
                reset();
            else
                setValue(Cal_Value());
            String s = getLex();
            if (t)
                s += "        ";

            lex.setText(s);
        }

        abstract public boolean Cal_Value();

        abstract public String getLex();

        public String toString() {
            return pstring;
        }
    }

    class Panel_Coll extends Panel_Basic {

        public Panel_Coll() {
            super(GExpert.getLanguage(ST[0]));
        }

        public String getLex() {
            if (check_filled())
                return (pstring + ": " + getPt(0) + " " + getPt(1) + " " + getPt(2));
            else
                return ("");
        }

        public boolean Cal_Value() {
            return drawbase.check_Collinear(getPt(0), getPt(1), getPt(2));
        }

    }

    class Panel_Para extends Panel_Basic {
        public Panel_Para() {
            super(ST[1]);
        }

        public boolean Cal_Value() {
            return drawbase.check_para(getPt(0), getPt(1), getPt(2), getPt(3));
        }

        public String getLex() {
            if (check_filled())
                return (getPt(0) + "" + getPt(1) + Cm.s2079 + getPt(2) + "" + getPt(3));
            else
                return ("");
        }
    }

    class Panel_Perp extends Panel_Basic {
        public Panel_Perp() {
            super(ST[2]);
        }

        public boolean Cal_Value() {
            return drawbase.check_perp(getPt(0), getPt(1), getPt(2), getPt(3));
        }

        public String getLex() {
            if (check_filled())
                return (getPt(0) + "" + getPt(1) + Cm.s2077 + getPt(2) + "" + getPt(3));
            else
                return ("");
        }
    }

    class Panel_Cyclic extends Panel_Basic {
        public Panel_Cyclic() {
            super(gxInstance.getLanguage(ST[3]));
        }

        public boolean Cal_Value() {
            return drawbase.check_cyclic(getPt(0), getPt(1), getPt(2), getPt(3));
        }

        public String getLex() {
            if (check_filled())
                return (pstring + ": " + getPt(0) + ", " + getPt(1) + ", " + getPt(2) + ", " + getPt(3));
            else
                return ("");
        }
    }

    class Panel_EQDis extends Panel_Basic {
        public Panel_EQDis() {
            super(ST[4]);
        }

        public boolean Cal_Value() {
            return drawbase.check_eqdistance(getPt(0), getPt(1), getPt(2), getPt(3));
        }

        public String getLex() {
            if (check_filled())
                return (getPt(0) + "" + getPt(1) + " = " + getPt(2) + "" + getPt(3));
            else
                return ("");
        }
    }

    class Panel_EQAng extends Panel_Basic {
        public Panel_EQAng() {
            super(ST[5]);
        }

        public boolean Cal_Value() {
            return drawbase.check_eqangle(getPt(0), getPt(1), getPt(2), getPt(3), getPt(4), getPt(5));
        }

        public String getLex() {
            if (check_filled())
                return (Cm.sangle + getPt(0) + "" + getPt(1) + "" + getPt(2) + " = " + Cm.sangle + getPt(3) + "" + getPt(4) + "" + getPt(5));
            else
                return ("");
        }
    }
}
