package wprover;

import gprover.Cm;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-7-5
 * Time: 13:25:43
 * To change this template use File | Settings | File Templates.
 */
public class concPanel extends JPanel implements ActionListener, ItemListener {

    public static ImageIcon icon_Right = GExpert.createImageIcon("images/dtree/right.gif");
    public static ImageIcon icon_Wrong = GExpert.createImageIcon("images/dtree/wrong.gif");
    public static ImageIcon icon_Question = GExpert.createImageIcon("images/dtree/question.gif");

    private JComboBox bt;
    private Vector vlist = new Vector();
    private Vector vlist1 = new Vector();
    private JLabel ltext1;
    private JButton bbok, bbcancel;
    private JPanel bpanel;
    private TreeCellAssertPanel asspane, asspane_temp;

    private GExpert gxInstance;
    private massertion ass, ass_show, ass_temp;
    private JPanel contentPane;

    private mproveInputPanel ipanel = null;

    public concPanel(GExpert gx) {
        gxInstance = gx;
        init();
        bt.setSelectedIndex(-1);
    }

    public concPanel(GExpert gx, mproveInputPanel ipanel) {
        this(gx);
        this.ipanel = ipanel;
    }


    private void init() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        bt = new JComboBox(massertion.cStrings) {
            public Dimension getMaximumSize() {
                return bt.getPreferredSize();
            }
        };

        bt.setMaximumRowCount(40);
        this.add(bt);
        bt.addItemListener(this);
        JPanel topPane = new JPanel();
        topPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel topPane1 = new JPanel();
        topPane1.setLayout(new FlowLayout(FlowLayout.CENTER));

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
        contentPane.add(topPane);
        contentPane.add(topPane1);

        JPanel textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.X_AXIS));

        ltext1 = new JLabel();
        setLtext1Value(true);
        asspane = new TreeCellAssertPanel();

        textPane.add(Box.createHorizontalStrut(3));
        textPane.add(asspane);
        textPane.add(Box.createHorizontalGlue());
        textPane.add(ltext1);
        contentPane.add(textPane);

        bpanel = new JPanel();
        bpanel.setBorder(BorderFactory.createTitledBorder("Do you mean...."));
        bpanel.setLayout(new BoxLayout(bpanel, BoxLayout.Y_AXIS));

        asspane_temp = new TreeCellAssertPanel();
        bpanel.add(asspane_temp);
        bbok = new JButton("Yes");
        bbok.addActionListener(this);
        bbcancel = new JButton("Cancel");
        bbcancel.addActionListener(this);
        JPanel pt = new JPanel();
        pt.setLayout(new BoxLayout(pt, BoxLayout.X_AXIS));
        pt.add(Box.createHorizontalGlue());
        bbok.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bbcancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pt.add(bbok);
        pt.add(bbcancel);
        bpanel.add(pt);
        bpanel.setVisible(false);
        contentPane.add(bpanel);

        this.add(contentPane);
        this.resetAllItem();
        ass_show = new massertion(0);
    }

    public void setTypeSelection(int k) {
        bt.setSelectedIndex(k);
        this.revalidateValidState();
    }

    public void setUserObject(massertion as) {
        this.resetAllItem();
        ass = as;

        if (as != null) {
            bt.setSelectedIndex(as.getAssertionType());
            for (int i = 0; i < as.getobjNum(); i++)
                this.selectAPoint((CPoint) as.getObject(i));
        } else {
            bt.setSelectedIndex(-1);
        }


    }

    public mobject getUserObject() {
        if (ass == null)
            ass = new massertion(bt.getSelectedIndex());
        else
            ass.setAssertionType(bt.getSelectedIndex());
        ass.clearObjects();
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.isVisible() && b.getSelectedIndex() >= 0) {
                ass.addObject((CPoint) b.getSelectedItem());
            } else
                break;
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.isVisible() && b.getSelectedIndex() >= 0) {
                ass.addObject((CPoint) b.getSelectedItem());
            } else
                break;
        }
        return ass;
    }

    public void update() {
        this.setPoints(gxInstance.dp.getPointList());
    }

    private void setLtext1Value(boolean t) {
        if (t) {
            ltext1.setText("");
            ltext1.setIcon(icon_Right);
        } else {
            ltext1.setText("");
            ltext1.setIcon(icon_Wrong);
        }
    }

    public int getPointsCount() {
        JComboBox b = (JComboBox) vlist.get(0);
        return b.getItemCount();
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
    }

    private int ptLeftTobeSelect() {
        int n = this.getStatePointsCount();

        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() >= 0) {
                n--;
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() >= 0) {
                n--;
            }
        }
        return n;
    }

    public void revalidateValidState() {
        if (inputFinished()) {
            boolean v = checkValid();
            setLtext1Value(v);
            if (v)
                bt.setEnabled(true);
            if (v == false) {
                showCorrentOrder();
            }
        } else
            ltext1.setIcon(icon_Question);
    }

    public void itemStateChanged(ItemEvent e) {
        if (!this.isVisible()) return;

        Object source = e.getSource();
        if (source == bt) {
            this.resetAllItem();
            int id = bt.getSelectedIndex();
            setItemChanged(id);
            if (id == bt.getItemCount() - 1) {
            }
        }
        showTipText();
        if (inputFinished()) {
            createAssertion();
            boolean v = checkValid();
            setLtext1Value(v);
            asspane.setAssertion(ass_show);
            if (v)
                bt.setEnabled(true);
            if (v == false) {
                showCorrentOrder();
            }
        } else
            ltext1.setIcon(icon_Question);

        updateBState();

    }

    public void updateBState() {
        if (ipanel != null) {
            if (inputFinished() || bt.getSelectedIndex() == massertion.CONVEX)
                ipanel.setBState(true);
            else
                ipanel.setBState(false);
        }
    }

    private boolean createAssertion() {
        if (ass_show == null)
            ass_show = new massertion(bt.getSelectedIndex());
        else
            ass_show.setAssertionType(bt.getSelectedIndex());

        ass_show.clearObjects();

        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.isVisible()) ass_show.addObject((CPoint) b.getSelectedItem());
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.isVisible()) ass_show.addObject((CPoint) b.getSelectedItem());
        }
        return ass_show.checkValid();
    }

    private boolean inputFinished() {
//        return 0 == ptLeftTobeSelect();
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                return false;
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            if (b.isEnabled() && b.getSelectedIndex() < 0) {
                return false;
            }
        }
        return true;
    }

    private void showTipText() {
        int n = ptLeftTobeSelect();

        if (n == 0) {
        } else {
            if (n > 0)
                asspane.setText("(" + n + " points left)");
            else
                asspane.setText("please select");
            asspane.repaint();
        }
    }

    public void selectAPoint(CPoint p) {

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
        asspane.repaint();
//        this.repaint();
    }

    private void resetAllItem() {
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox b = (JComboBox) vlist.get(i);
            b.setSelectedIndex(-1);
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox b = (JComboBox) vlist1.get(i);
            b.setSelectedIndex(-1);
        }

    }

    private int getStatePointsCount() {
        switch (bt.getSelectedIndex()) {
            case massertion.COLL:
            case massertion.MID:
            case massertion.R_TRIANGLE:
            case massertion.ISO_TRIANGLE:
            case massertion.R_ISO_TRIANGLE:
            case massertion.BETWEEN:
            case massertion.EQ_TRIANGLE:
                return 3;
            case massertion.PARA:
            case massertion.PERP:
            case massertion.EQDIS:
            case massertion.CYCLIC:
            case massertion.DISLESS:
            case massertion.PERPBISECT:
            case massertion.PARALLELOGRAM:
                return 4;
            case massertion.EQANGLE:
            case massertion.SIM:
            case massertion.CONG:
            case massertion.ANGLESS:
            case massertion.CONCURRENT:
                return 6;
        }
        return -1;
    }

    private void setItemChanged(int id) {
        switch (id) {
            case massertion.COLL:
            case massertion.MID:
            case massertion.R_TRIANGLE:
            case massertion.ISO_TRIANGLE:
            case massertion.R_ISO_TRIANGLE:
            case massertion.EQ_TRIANGLE:
                this.setVisibleBox(3);
                break;

            case massertion.PARA:
            case massertion.PERP:
            case massertion.EQDIS:
            case massertion.DISLESS:
            case massertion.PERPBISECT:
            case massertion.OPPOSITE_SIDE:
            case massertion.SAME_SIDE:
                this.setVisibleBox1(4);
                break;
            case massertion.CYCLIC:
            case massertion.PARALLELOGRAM:
            case massertion.TRAPEZOID:
            case massertion.RECTANGLE:
            case massertion.SQUARE:
                this.setVisibleBox(4);
                break;
            case massertion.EQANGLE:
            case massertion.SIM:
            case massertion.CONG:
            case massertion.ANGLESS:
            case massertion.CONCURRENT:
                this.setVisibleBox1(6);
                break;
            case massertion.ANGLE_INSIDE:
            case massertion.ANGLE_OUTSIDE:
            case massertion.TRIANGLE_INSIDE:

                this.setVisibleBox2(3);
                break;
            case massertion.BETWEEN:
                this.setVisibleBox2(2);
                break;
            case massertion.PARA_INSIDE:
                this.setVisibleBox2(4);
                break;
            case massertion.CONVEX:
                this.setVisibleBox1(8);
                break;
            default:
                CMisc.print("massertion " + id + " not found");
                break;

        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Object obj = e.getSource();
        if (obj == bbok) {
            this.resetAllItem();
            ass.clearObjects();
            ass.addAll(ass_temp);
            int n = ass_temp.getobjNum();
            for (int i = 0; i < n; i++) {
                CPoint pt = (CPoint) ass_temp.getObject(i);
                ass.addObject(pt);
                this.selectAPoint(pt);
            }
        } else {
        }
        bpanel.setVisible(false);

    }

    private void setVisibleBox2(int num) {
        int k = 1;
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox obj = (JComboBox) vlist.get(i);
            if (i < k) {
                obj.setEnabled(true);
                obj.setVisible(true);
            } else {
                obj.setEnabled(false);
                obj.setVisible(false);
            }
        }

        k = num;

        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox obj = (JComboBox) vlist1.get(i);
            if (i < k) {
                obj.setEnabled(true);
                obj.setVisible(true);
            } else {
                obj.setEnabled(false);
                obj.setVisible(false);
            }
        }

    }

    private void setVisibleBox1(int num) {
        int k = num / 2;
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox obj = (JComboBox) vlist.get(i);
            if (i < k) {
                obj.setEnabled(true);
                obj.setVisible(true);
            } else {
                obj.setEnabled(false);
                obj.setVisible(false);
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox obj = (JComboBox) vlist1.get(i);
            if (i < k) {
                obj.setEnabled(true);
                obj.setVisible(true);
            } else {
                obj.setEnabled(false);
                obj.setVisible(false);
            }
        }
    }

    private void setVisibleBox(int num) {
        for (int i = 0; i < vlist.size(); i++) {
            JComboBox obj = (JComboBox) vlist.get(i);
            if (i < num) {
                obj.setEnabled(true);
                obj.setVisible(true);
            } else {
                obj.setEnabled(false);
                obj.setVisible(false);
            }
        }
        for (int i = 0; i < vlist1.size(); i++) {
            JComboBox obj = (JComboBox) vlist1.get(i);
            obj.setEnabled(false);
            obj.setVisible(false);
        }
    }

    private String vs(int id1, int id2) {
        if (id1 == 0) {
            JComboBox box = ((JComboBox) vlist.get(id2));
            if (box.getItemCount() <= id2)
                return "";
            else {
                Object obj = box.getSelectedItem();
                if (obj == null)
                    return null;
                else
                    return obj.toString();
            }

        } else {
            JComboBox box = ((JComboBox) vlist1.get(id2));
            if (box.getItemCount() <= id2)
                return "";
            else {
                Object obj = box.getSelectedItem();
                if (obj == null)
                    return null;
                else
                    return obj.toString();
            }
        }

    }

    private CPoint vspt(int id1, int id2) {
        if (id1 == 0) {
            JComboBox box = ((JComboBox) vlist.get(id2));
            if (box.getItemCount() <= id2)
                return null;
            else {
                Object obj = box.getSelectedItem();
                if (obj == null)
                    return null;
                else
                    return (CPoint) obj;
            }
        } else {
            JComboBox box = ((JComboBox) vlist1.get(id2));
            if (box.getItemCount() <= id2)
                return null;
            else {
                Object obj = box.getSelectedItem();
                if (obj == null)
                    return null;
                else
                    return (CPoint) obj;
            }
        }
    }

    private boolean checkValid() {
        int id = bt.getSelectedIndex();
        if (!inputFinished()) return false;
        switch (id) {
            case 0:
                return drawbase.check_Collinear(vspt(0, 0), vspt(0, 1), vspt(0, 2));
            case 1:
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 2:
                return drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 3:
                return drawbase.check_mid(vspt(0, 0), vspt(0, 1), vspt(0, 2));
            case 4:
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 5:
                return drawbase.check_cyclic(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3));
            case 6:
                return drawbase.check_eqangle(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));
            case 7:
                return drawbase.check_congtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));

            case 8:
                return drawbase.check_simtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));

            case 9:
                return drawbase.check_distance_less(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case 10:
                return drawbase.check_angle_less(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2));
            case 11:
//                return drawbase.check_concurrent();
            case 12:
                return drawbase.check_perp(vspt(0, 0), vspt(1, 1), vspt(1, 0), vspt(0, 1));
            case 13:
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3))
                        && drawbase.check_para(vspt(0, 0), vspt(0, 3), vspt(0, 1), vspt(0, 2));
            case massertion.R_TRIANGLE:
                return drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2));
            case massertion.ISO_TRIANGLE:
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2));
            case massertion.R_ISO_TRIANGLE:
                return drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2))
                        && drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2));
            case massertion.EQ_TRIANGLE:
                return drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2))
                        && drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 1), vspt(0, 2));
            case massertion.TRAPEZOID:
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3));
            case massertion.RECTANGLE:
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3))
                        && drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(0, 1), vspt(0, 2));
            case massertion.SQUARE:
                return drawbase.check_para(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(0, 3))
                        && drawbase.check_perp(vspt(0, 0), vspt(0, 1), vspt(0, 1), vspt(0, 2))
                        && drawbase.check_eqdistance(vspt(0, 0), vspt(0, 1), vspt(0, 0), vspt(0, 2));
            case massertion.BETWEEN:
                return drawbase.check_between(vspt(0, 0), vspt(1, 0), vspt(1, 1));
            case massertion.ANGLE_INSIDE:
                return drawbase.check_angle_less(vspt(0, 0), vspt(1, 1), vspt(1, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2))
                        && drawbase.check_angle_less(vspt(0, 0), vspt(1, 1), vspt(1, 0), vspt(1, 0), vspt(1, 1), vspt(1, 2));

            case massertion.ANGLE_OUTSIDE:
                return !(drawbase.check_angle_less(vspt(0, 0), vspt(1, 1), vspt(1, 2), vspt(1, 0), vspt(1, 1), vspt(1, 2))
                        && drawbase.check_angle_less(vspt(0, 0), vspt(1, 1), vspt(1, 0), vspt(1, 0), vspt(1, 1), vspt(1, 2)));
            case massertion.TRIANGLE_INSIDE:
                return drawbase.check_triangle_inside(vspt(0, 0), vspt(1, 0), vspt(1, 1), vspt(1, 2));
            case massertion.PARA_INSIDE:
                return drawbase.check_triangle_inside(vspt(0, 0), vspt(1, 0), vspt(1, 1), vspt(1, 2))
                        || drawbase.check_triangle_inside(vspt(0, 0), vspt(1, 0), vspt(1, 2), vspt(1, 3));

            case massertion.OPPOSITE_SIDE:
                return !drawbase.check_same_side(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case massertion.SAME_SIDE:
                return drawbase.check_same_side(vspt(0, 0), vspt(0, 1), vspt(1, 0), vspt(1, 1));
            case massertion.CONVEX:
                return true;

        }
        return true;
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

    public String getProve() {
        JComboBox box1 = (JComboBox) vlist.get(0);
        if (box1.getItemCount() == 0) return "";
        if (!this.inputFinished()) return "";

        int id = bt.getSelectedIndex();

        switch (id) {
            case massertion.COLL:
                return Cm.PC_COLL + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + ";";
            case massertion.PARA:
                return Cm.PC_PARA + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.PERP:
                return Cm.PC_PERP + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.MID:
                return Cm.PC_MIDP + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + ";";
            case massertion.CYCLIC:
                return Cm.PC_CYCLIC + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " " + vs(0, 3) + ";";
            case massertion.EQDIS:
                return Cm.PC_CONG + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.EQANGLE:
                return Cm.PC_ACONG + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " " + vs(0, 3) + " " + vs(1, 0) + " " + vs(1, 1) + " " + vs(1, 2) + " " + vs(1, 3) + ";";
            case massertion.SIM:
                return Cm.PC_STRI + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " " + vs(1, 0) + " " + vs(1, 1) + " " + vs(1, 2) + ";";
            case massertion.CONG:
                return Cm.PC_CTRI + " " + vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " " + vs(1, 0) + " " + vs(1, 1) + " " + vs(1, 2) + ";";
        }
        return "Not Yet Supported Conclution";
    }

    public String getProveDescription() {
        JComboBox box1 = (JComboBox) vlist.get(0);
        if (box1.getItemCount() == 0) return "";
        if (!this.inputFinished()) return "";

        int id = bt.getSelectedIndex();

        switch (id) {
            case massertion.COLL:
                return vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " are collinear;";
            case massertion.PARA:
                return vs(0, 0) + " " + vs(0, 1) + " is Parallel to " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.PERP:
                return vs(0, 0) + " " + vs(0, 1) + " is Perpendicular to " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.MID:
                return vs(0, 0) + " is the midpoint of " + vs(0, 1) + " " + vs(0, 2) + ";";
            case massertion.CYCLIC:
                return vs(0, 0) + " " + vs(0, 1) + " " + vs(0, 2) + " " + vs(0, 3) + " are cyclic;";
            case massertion.EQDIS:
                return vs(0, 0) + " " + vs(0, 1) + " equals to " + vs(1, 0) + " " + vs(1, 1) + ";";
            case massertion.EQANGLE:
                return Cm.s2078 + vs(0, 0) + vs(0, 1) + vs(0, 2) + " = " + Cm.s2078 + vs(1, 0) + vs(1, 1) + vs(1, 2);

            case massertion.SIM:
                return "tri " + vs(0, 0) + vs(0, 1) + vs(0, 2) + " is similiar to " + "tri " + vs(1, 0) + vs(1, 1) + vs(1, 2);
            case massertion.CONG:
                return "tri " + vs(0, 0) + vs(0, 1) + vs(0, 2) + " is equal to " + "tri " + vs(1, 0) + vs(1, 1) + vs(1, 2);
        }
        return "Not Yet Supported Conclution";
    }

    private void showCorrentOrder() {
        JComboBox box1 = (JComboBox) vlist.get(0);
        if (box1.getItemCount() == 0) return;
        if (!this.inputFinished()) return;

        int id = bt.getSelectedIndex();

        boolean t = false;

        if (id == massertion.SIM || id == massertion.CONG) {
            int i, j, k;
            i = j = k = 0;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (i != j)
                        for (k = 0; k < 3; k++) {
                            if (i != k && j != k) {
                                if (id == massertion.SIM)
                                    t = drawbase.check_simtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, i), vspt(1, j), vspt(1, k));
                                else if (id == massertion.CONG)
                                    t = drawbase.check_congtri(vspt(0, 0), vspt(0, 1), vspt(0, 2), vspt(1, i), vspt(1, j), vspt(1, k));
                                if (t)
                                    break;
                            }
                        }
                    if (t) break;
                }
                if (t) break;
            }
            if (t) {
                if (ass_temp == null)
                    ass_temp = new massertion(bt.getSelectedIndex());
                ass_temp.clearObjects();
                ass_temp.addObject(vspt(0, 0));
                ass_temp.addObject(vspt(0, 1));
                ass_temp.addObject(vspt(0, 2));
                ass_temp.addObject(vspt(1, i));
                ass_temp.addObject(vspt(1, j));
                ass_temp.addObject(vspt(1, k));
                asspane_temp.setAssertion(ass_temp);
                bpanel.setVisible(true);
            }
        }


    }

    public void cancel() {
        ass = null;
        this.resetAllItem();
    }

}
