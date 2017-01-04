package wprover;

import gprover.*;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

import maths.TMono;
import maths.PolyBasic;

public class concDialog extends JBaseDialog implements ActionListener, ItemListener {
    int type = 0; // 0. Conclusion   1. NDGS.
    final static String[] ts = {
            "Collinear",
            "Parallel",
            "Perpendicular",
            "Midpoint",
            "Cyclic",
            "Equal Distance",
            "Equal Angle",

            "Similiar Triangle",
            "Congruent Triangle",
            "Equalateral Triangle",

            "Bisect",
            "Tangent",

            "Equal Product",
            "Ratio",

            "Special Angle",
            "Angles Equation",
            "Segment Equation"
    };//CST.s_conc_detail;


    final public static int CONCLUTION_OK = 0;
    final static Font font = new Font("Dialog", Font.BOLD, 14);
    final public static int CONCLUTION_CANCLE = 1;
    final public static int CONCLUTION_ERROR = -1;


    private Vector vlist = new Vector();
    private Vector vlist1 = new Vector();
    private boolean model = true;
    private int returnValue;
    private GExpert gxInstance;

    private JComboBox bt;
    private JButton bok;
    private JComboBox bx1, bx2;
    private ImageIcon ic1, ic2, ic3;
    private JLabel ltext, ltext1;
    private JPanel cardPane;
    private condPane Pane2;


    public void setType(int t) {
        this.type = t;
    }

    public void changeBOKListener(ActionListener ls) {
        bok.removeActionListener(this);
        bok.addActionListener(ls);
    }

    public String getLanguage(int n, String s) {
        return GExpert.getLanguage(n, s);
    }

    public void setCns(cons c) {
        if (c == null)
            return;
        if (c.type == gib.CO_COLL) {
            bt.setSelectedIndex(0);
            for (int i = 0; i < 3; i++) {
                JComboBox b = (JComboBox) vlist.get(i);
                CPoint px = gxInstance.dp.findPoint(c.pss[i].toString());
                b.setSelectedItem(px);
            }
        }
    }

    public concDialog(GExpert gx, String title) {
        super(gx.getFrame(), title);
        this.setTitle(title);
        gxInstance = gx;
        model = false;
        init();
        this.setPoints(gx.dp.getPointList());
        this.setModal(false);

    }

    private void setLtext1Value(int t) {
        if (model) return;

        if (t == 1) {
            ltext1.setText(getLanguage(230, "True"));
            ltext1.setIcon(ic1);
            ltext1.setForeground(Color.green.darker());
        } else if (t == -1) {
            ltext1.setText(getLanguage(231, "False"));
            ltext1.setIcon(ic2);
            ltext1.setForeground(Color.red.brighter().brighter());
        } else if (t == 0) {
            ltext1.setText("");
            ltext1.setIcon(null);
            ltext1.setForeground(Color.black);
        }
    }

    public concDialog(GExpert frame) {
        super(frame.getFrame(), "Add Conclution");
        this.setModal(false);
        model = false;
        init();
    }

    private void init() {


        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel Pane1 = new JPanel();
        Pane1.setLayout(new BoxLayout(Pane1, BoxLayout.Y_AXIS));

        ic1 = GExpert.createImageIcon("images/ptree/hook.gif");
        ic2 = GExpert.createImageIcon("images/ptree/cross.gif");
        ic3 = GExpert.createImageIcon("images/ptree/question.gif");


        int len = ts.length;
        String[] ss = new String[len];
        for (int i = 0; i < len; i++)
            ss[i] = this.getLanguage(200 + i, ts[i]);

        bt = new JComboBox(ss) {
            public Dimension getMaximumSize() {
                return bt.getPreferredSize();
            }
        };
        bt.setMaximumRowCount(20);
        bt.addItemListener(this);
        contentPane.add(bt);

        cardPane = new JPanel(new CardLayout(2, 2));
        JPanel topPane = new JPanel();
        topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel topPane1 = new JPanel();
        topPane1.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPane.add(new JLabel(getLanguage(3301, "Set One")));
        topPane1.add(new JLabel(getLanguage(3302, "Set Two")));

        for (int i = 0; i < 4; i++) {
            JComboBox b = new JComboBox();
            b.addItemListener(this);
            topPane.add(b);
            vlist.add(b);
        }
        for (int i = 0; i < 4; i++) {
            JComboBox b = new JComboBox();
            b.addItemListener(this);
            topPane1.add(b);
            vlist1.add(b);
        }
        String[] ls = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        bx1 = new JComboBox(ls);
        bx2 = new JComboBox(ls);
        bx1.addItemListener(this);
        bx2.addItemListener(this);
        topPane.add(bx1);
        topPane1.add(bx2);

        Pane1.add(topPane);
        Pane1.add(topPane1);

        JPanel textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.X_AXIS));
        ltext = new JLabel(getLanguage(3206, "Please select"));

        ltext1 = new JLabel(getLanguage(230, "true"));
        ltext1.setIcon(ic1);
        ltext1.setHorizontalTextPosition(JLabel.LEFT);
        ltext1.setVisible(!model);

        setLtext1Value(0);
        ltext.setAlignmentX(Component.RIGHT_ALIGNMENT);
        textPane.add(Box.createHorizontalStrut(3));
        textPane.add(ltext);
        textPane.add(Box.createHorizontalGlue());
        textPane.add(ltext1);
        Pane1.add(textPane);
        cardPane.add(Pane1, "1");


        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));

        bottomPane.add(Box.createHorizontalGlue());
        bok = new JButton(getLanguage(3204, "OK"));
        bok.addActionListener(this);
        bottomPane.add(bok);
        JButton bclear, bcancel;
        bottomPane.add(bclear = new JButton(getLanguage(301, "Clear")));
        bclear.addActionListener(this);
        bottomPane.add(bcancel = new JButton(getLanguage(300, "Cancel")));
        bcancel.addActionListener(this);
        bok.setActionCommand("OK");
        bclear.setActionCommand("Clear");
        bcancel.setActionCommand("Cancel");


        contentPane.add(cardPane);
        contentPane.add(bottomPane);
        this.add(contentPane);
        Pane2 = new condPane();
        cardPane.add(Pane2, "2");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                returnValue = CONCLUTION_CANCLE;
            }
        });

        this.resetAllItem();
    }

    public void setPoints(Vector v) {
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            b.removeAllItems();
            for (int j = 0; j < v.size(); j++) {
                Object obj = v.get(j);
                b.addItem(obj);
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            b.removeAllItems();
            for (int j = 0; j < v.size(); j++) {
                Object obj = v.get(j);
                b.addItem(obj);
            }
        }

        Pane2.setPoints(v);
    }

    private int ptLeftTobeSelect() {
        int n = 0;

        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() == -1) {
                n++;
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() == -1) {
                n++;
            }
        }
        return n;
    }

    public void itemStateChanged(ItemEvent e) {
        if (!this.isVisible()) return;
        Object source = e.getSource();
        if (source == bt) {
            int id = bt.getSelectedIndex();
            CardLayout layout = ((CardLayout) cardPane.getLayout());
            if (id == 18 || id == 19) {
                {
                    int t = 0;
                    if (id == 18)
                        t = 1;
                    else
                        t = 0;
                    layout.show(cardPane, "2");
                    Pane2.setStatus(t);
                    bok.setEnabled(true);
                }
            } else {
                layout.show(cardPane, "1");
                resetAllItem();
                setItemChanged(id);
                if (id != -1)
                    ltext.setText(getLanguage(3300, "The number of points to be selected: ") + this.getStatePointsCount());
            }
        } else {

            if (model == false && inputFinished()) {
                cons c = this.getProve();

                bok.setEnabled(true);
                if (c != null)
                    ltext.setText(c.toSString());
                boolean v = checkValid();
                if (v)
                    setLtext1Value(1);
                else
                    setLtext1Value(-1);
                if (v)
                    bt.setEnabled(true);
            } else {
                ltext.setText(getLanguage(3300, "The number of points to be selected: ") + this.getStatePointsCount());
                setLtext1Value(0);
            }
        }
    }

    private boolean inputFinished() {
        return 0 == ptLeftTobeSelect();
    }

    private void showTipText() {

    }

    public void selectAPoint(CPoint p) {

        Pane2.selectAPoint(p);


        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                b.setSelectedItem(p);
                return;
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                b.setSelectedItem(p);
                return;
            }
        }

    }

    private void resetAllItem() {
        bok.setEnabled(false);
        ltext1.setIcon(ic3);
        ltext1.setText("");

        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            b.setSelectedIndex(-1);

        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            b.setSelectedIndex(-1);
        }
        bx1.setSelectedIndex(0);
        bx2.setSelectedIndex(0);

    }

    private int getStatePointsCount() {
        switch (bt.getSelectedIndex()) {
            case 0:
            case 3:
            case 9:
            case 10:
                return 3;

            case 1:
            case 2:
            case 4:
            case 5:
            case 11:
            case 13:

                return 4;
            case 7:
            case 8:
                return 6;

            case 6:
            case 12:
                return 8;
        }
        return -1;
    }

    private void setItemChanged(int id) {
        switch (id) {
            case 0:
            case 3:
            case 9:
            case 10:
                this.setVisibleBox(3);
                break;
            case 4:
                this.setVisibleBox(4);
                break;
            case 1:
            case 2:
            case 5:
            case 11:
            case 13:

                this.setVisibleBox1(4);
                break;
            case 7:
            case 8:
                this.setVisibleBox1(6);
                break;
            case 6:
            case 12:
                this.setVisibleBox1(8);
                break;

        }
        if (id == 13) {
            setRatioVisible();
        } else if (id == 14) {
            setSAngle();
        } else if (id == 18) {
            //midPane.setVisible(true);
        } else {
            bx1.setVisible(false);
            bx2.setVisible(false);
        }
    }

    public void setSAngle() {
        bx1.removeAllItems();
        bx1.addItem(new Integer("0"));
        bx1.addItem(new Integer("15"));
        bx1.addItem(new Integer("30"));
        bx1.addItem(new Integer("36"));
        bx1.addItem(new Integer("45"));
        bx1.addItem(new Integer("72"));
        bx1.addItem(new Integer("75"));
        bx1.addItem(new Integer("90"));
        bx1.addItem(new Integer("120"));
        bx1.addItem(new Integer("135"));
        bx1.addItem(new Integer("150"));
        bx1.addItem(new Integer("180"));
        bx1.setVisible(true);
        bx2.setVisible(false);
    }

    public void setRatioVisible() {
        bx1.removeAllItems();
        bx2.removeAllItems();
        for (int i = 1; i <= 9; i++) {
            Object obj = new Integer(i);
            bx1.addItem(obj);
            bx2.addItem(obj);
        }
        bx1.setVisible(true);
        bx2.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("OK")) {
            returnValue = CONCLUTION_OK;
            this.setVisible(false);
            if (type == 0)
                gxInstance.getpprove().set_conclution(getProve(), this.checkValid());
            else
                gxInstance.getpprove().add_ndgs(getProve());

        } else if (command.equalsIgnoreCase("Cancel")) {
            returnValue = CONCLUTION_CANCLE;
            this.setVisible(false);
        } else if (command.equalsIgnoreCase("Clear")) {
            this.resetAllItem();
            Pane2.clear();
        }
    }

    public int showDialog(String s) {
        if (model == false)
            this.setPoints(gxInstance.dp.getPointList());

        bt.setSelectedIndex(-1);
        resetAllItem();
        this.setLocation((int) (this.getOwner().getX()), (int) (this.getOwner().getY() + this.getOwner().getHeight() / 3));
        this.pack();
        this.setVisible(true);
        this.setSize(320, 200);
        bt.setSelectedItem(s);
        ltext.setText("");
        return returnValue;

    }

    private void setVisibleBox1(int num) {

        int k = num / 2;
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox obj = (JComboBox) vlist.get(i);
            if (i < k)
                obj.setEnabled(true);
            else
                obj.setEnabled(false);
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox obj = (JComboBox) vlist1.get(i);
            if (i < k)
                obj.setEnabled(true);
            else
                obj.setEnabled(false);
        }
    }

    private void setVisibleBox(int num) {
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox obj = (JComboBox) vlist.get(i);
            if (i < num)
                obj.setEnabled(true);
            else
                obj.setEnabled(false);
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox obj = (JComboBox) vlist1.get(i);
            obj.setEnabled(false);
        }
    }

    private String vs(int id1, int id2) {
        if (id1 == 0)
            return ((JComboBox) vlist.get(id2)).getSelectedItem().toString();
        else
            return ((JComboBox) vlist1.get(id2)).getSelectedItem().toString();
    }

    private CPoint vspt(int id1, int id2) {
        if (id1 == 0) {
            return (CPoint) ((JComboBox) vlist.get(id2)).getSelectedItem();
        } else {
            return (CPoint) ((JComboBox) vlist1.get(id2)).getSelectedItem();
        }
    }
//  "Collinear",
//            "Parallel",
//            "Perpendicular",
//            "Midpoint",
//            "Cyclic",
//            "Equal Distance",
//            "Equal Angle",
//
//            "Similiar Triangle",
//            "Congruent Triangle",
//            "Equalateral Triangle",
//
//            "Bisect",
//            "Tangent",
//
//            "Equal Product",
//            "Ratio",
//
//            "Special Angle",
//            "Angles Equation",
//            "Segment Equation"

    public boolean checkValid() {
        int id = bt.getSelectedIndex();
        return checkValid(id);

    }

    public boolean checkValid(int id) {
        switch (id) {
            case 0:  // COLLINEAR
                return drawbase.check_Collinear(vspt(0, 0), vspt(0, 1), vspt(0, 2));
            case 1:  // PARALLEL
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 2:  // PERPENDICULAR
                return drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 3:  // MIDPOINT
                return drawbase.check_mid(vspt(0, 0), vspt(0, 1), vspt(0, 2));
            case 4:  // CYCLIC
                return drawbase.check_cyclic(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3));
            case 5:  // EQDISTANCE
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 6:
                return drawbase.check_eqangle(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3), vspt(1, 0), vspt(1, 1), vspt(1, 2), vspt(1, 3));

            case 7:  //Similiar Triangle
                return drawbase.check_simtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));
            case 8:  //Congruent Triangle
                return drawbase.check_congtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));

            case 9:  //Equalateral Triangle
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 1), vspt(0, 2)) &&
                        drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2));
            case 10:
                return drawbase.check_bisect(vspt(0, 0), vspt(0, 1), vspt(0, 2));
            case 11: //Tangent
                return drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 1));

            case 12: { //Eq_product
                double d1 = drawbase.sdistance(vspt(0, 0), vspt(0, 1));
                double d2 = drawbase.sdistance(vspt(0, 2), vspt(0, 3));
                double d3 = drawbase.sdistance(vspt(1, 0), vspt(1, 1));
                double d4 = drawbase.sdistance(vspt(1, 2), vspt(1, 3));
                return Math.abs(d1 * d2 - d3 * d4) < CMisc.ZERO;
            }
            case 13: { //Ratio
                int t1 = getRatioValue(1);
                int t2 = getRatioValue(2);
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1), t1, t2);
            }

        }
        return false;
    }


    private int getRatioValue(int id) {
        if (id == 1) {
            String s1 = bx1.getSelectedItem().toString();
            int t1 = Integer.parseInt(s1);
            return t1;
        } else {
            String s2 = bx2.getSelectedItem().toString();
            int t2 = Integer.parseInt(s2);
            return t2;
        }
    }

    public massertion getProveM() {
        int id = bt.getSelectedIndex();
        if (id < 0) return null;
        massertion ass = new massertion(id);
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                ass.addItem(b.getSelectedItem());
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                ass.addItem(b.getSelectedItem());
            }
        }
        return ass;
    }

    public cons getProve() {
        JComboBox box1 = (JComboBox) vlist.get(0);
        if (box1.getItemCount() == 0) return null;
        int nd = bt.getSelectedIndex();
        if (nd < 0 || nd >= ts.length)
            return null;

        Object obj = ts[nd];

        if (obj == null) return null;
        int p = CST.getClu_D(obj.toString());

        if (p == 0) return null;

        cons c = new cons(p);
        int index = 0;
        String sn = "";
        if (p == gib.CO_NANG || p == gib.CO_NSEG) {
            sn = Pane2.getValue();
            c.setText(sn);
            return c;
        }

        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() >= 0) {
                Object o = b.getSelectedItem();
                c.add_pt(o, index++);
                sn += " " + o;
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() >= 0) {
                Object o = b.getSelectedItem();
                c.add_pt(o, index++);
                sn += " " + o;
            }
        }

        if (p == gib.CO_RATIO) {
            Object o1 = bx1.getSelectedItem();
            Object o2 = bx2.getSelectedItem();
            c.add_pt(o1, 4);
            c.add_pt(o2, 5);
            sn += " " + o1 + " " + o2;
        } else if (p == gib.CO_TANG) {
            Object o1 = bx1.getSelectedItem();
            c.add_pt(o1, 4);
            sn += " " + o1;
        }
        c.set_conc(true);
        return c;
    }

    public String getSpecilaAngle() {
        Object o = bx1.getSelectedItem();
        if (o != null)
            return o.toString();
        else
            return null;
    }

    class condPane extends JPanel implements ActionListener {

        JComboBox[] bx = new JComboBox[3];
        JTextField field = new JTextField(1);
        JButton badd = new JButton("Add");
        int status = 0; //0: segment 1:angle.
        JButton b1, b2, b3, b4, b5;
        JLabel label;
        JPopupMenu mint;


        public condPane() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            field.setFont(font);
            this.add(field);

            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            b1 = new JButton("+");
            b1.setFont(font);
            b2 = new JButton("-");
            b2.setFont(font);
            b3 = new JButton("*");
            b3.setFont(font);
            b4 = new JButton("=");
            b4.setFont(font);
            b5 = new JButton("Number");
            b5.setFont(font);
            p1.add(b1);
            p1.add(b2);
            p1.add(b3);
            p1.add(b4);
            p1.add(b5);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);
            b5.addActionListener(this);
            this.add(p1);
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
            label = new JLabel();
            p.add(label);
            bx[0] = new JComboBox();
            p.add(bx[0]);
            bx[1] = new JComboBox();
            p.add(bx[1]);
            bx[2] = new JComboBox();
            p.add(bx[2]);
            p.add(badd);
            this.add(p);
            badd.addActionListener(this);
            mint = new JPopupMenu();
            for (int i = 0; i <= 10; i++) {
                JMenuItem item = new JMenuItem(new Integer(i).toString());
                mint.add(item);
                item.addActionListener(this);
            }
            field.setFocusable(true);
        }

        public void clear() {
            field.setText("");
            bx[0].setSelectedIndex(-1);
            bx[1].setSelectedIndex(-1);
            bx[2].setSelectedIndex(-1);

            ltext1.setIcon(ic3);
            ltext1.setText("");
        }

        public void setStatus(int s) {
            if (s == 0) {
                bx[2].setVisible(false);
                bx[2].setEnabled(false);
                label.setText("(Segment) ");
            } else {
                bx[2].setVisible(true);
                bx[2].setEnabled(true);
                label.setText("(Angle) ");
            }
            field.setText("");
            bx[0].setSelectedIndex(-1);
            bx[1].setSelectedIndex(-1);
            bx[2].setSelectedIndex(-1);
        }

        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (o == b3) {
                {
                    addPtsText1();
                    addText(" * ");
                }
            } else if (o == b1) {
                addPtsText1();
                addText(" + ");
            } else if (o == b2) {
                addPtsText1();
                addText(" - ");
            } else if (o == b4) {
                addPtsText1();
                addText(" = ");
            } else if (o == b5)
                mint.show(b5, 0, b5.getHeight());
            else if (o == badd) {
                addText(getPts());
            } else {
                String s = e.getActionCommand();
                addText(s);
            }
        }

        public void addPtsText1() {
            if (!badd.isEnabled())
                return;
            addText(getPts());
        }

        public void addText(String s) {
            field.setText(field.getText() + s);
            unselect();
        }

        public void setPoints(Vector v) {
            for (int i = 0; i < 3; i++) {
                bx[i].removeAllItems();
                for (int j = 0; j < v.size(); j++) {
                    Object obj = v.get(j);
                    bx[i].addItem(obj);
                }
                bx[i].setSelectedIndex(-1);
            }
        }

        public void selectAPoint(Object o) {
            boolean set = false;
            for (int i = 0; i < 3; i++) {
                Object obj = bx[i].getSelectedItem();
                if (obj == null && bx[i].isEnabled()) {
                    if (set == false) {
                        bx[i].setSelectedItem(o);
                        set = true;
                    } else
                        return;
                }
            }
            badd.setEnabled(true);
        }

        public void unselect() {
            for (int i = 0; i < 3; i++) {
                bx[i].setSelectedIndex(-1);
            }
            badd.setEnabled(false);
        }

        public void getSelected(Vector v) {
            for (int i = 0; i < 3 && bx[i].isEnabled(); i++) {
                Object obj = bx[i].getSelectedItem();
                if (obj != null)
                    v.add(obj);
                else {
                    v.clear();
                    return;
                }
            }
        }

        public String getPts() {
            String s = "";
            for (int i = 0; i < 3 && bx[i].isEnabled(); i++) {
                Object obj = bx[i].getSelectedItem();
                s += obj;
            }
            return s;
        }

        public String getValue() {
            return field.getText();
        }
    }

}
