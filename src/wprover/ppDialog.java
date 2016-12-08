package wprover;

import gprover.gterm;
import gprover.cons;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

import maths.TMono;

public class ppDialog extends JBaseDialog implements ActionListener, MouseMotionListener, MouseListener, ChangeListener {
    private drawProcess dp;
    private static int WD = 600;
    private static int HD = 400;
    private gterm gt;
    private JList list1, list2, list22;
    private JTabbedPane tt;
    private DefaultListModel model1, model2, model22;
    private static Color bcolor = new Color(249,249,255);


    public ppDialog(GExpert gx, gterm gt, drawProcess dp) {
        super(gx.getFrame(), "Nondegenerate Conditions");

        this.gt = gt;
        this.dp = dp;

        tt = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.WRAP_TAB_LAYOUT);

        model1 = new DefaultListModel();
        model2 = new DefaultListModel();
        model22 = new DefaultListModel();

        list1 = new JList(model1);
        list2 = new JList(model2);
        list22 = new JList(model22);
        list1.setBackground(bcolor);
        list2.setBackground(bcolor);
        list22.setBackground(bcolor);
        
        list22.setSelectionModel(list2.getSelectionModel());

        TitledBorder t = BorderFactory.createTitledBorder("Nondegenerate Conditions");
        t.setTitleColor(Color.gray);
        list2.setBorder(t);

        TitledBorder tb = BorderFactory.createTitledBorder("Polynomials of Nondegenerate Conditions");
        tb.setTitleColor(Color.gray);
        list22.setBorder(tb);


        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, list2, list22);

        tt.addChangeListener(this);
        this.getConstructions();
        this.getNDGs();


        JSplitPane panelx = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list1, panel);
        panelx.setDividerLocation(200);
        this.getContentPane().add(new JScrollPane(panelx));
        this.setSize(WD, HD);
        int w = gx.getWidth();
        int h = gx.getHeight();
        int x = gx.getX();
        int y = gx.getY();
        this.setLocation(x + w / 2 - WD / 2, y + h / 2 - HD / 2);

        panel.resetToPreferredSizes();
        panel.revalidate();
    }

    public void setSelectIndex(int t) {
        tt.setSelectedIndex(t);
    }

    public void stateChanged(ChangeEvent e) {
        ppDialog.this.setTitle(tt.getTitleAt(tt.getSelectedIndex()));
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    public void getConstructions() {
    }

    public void getNDGs() {
        if (gt == null)
            return;

        Vector v1 = gt.pc();

        Vector v = gt.getAllNdgs(v1);

        model2.removeAllElements();
        model22.removeAllElements();
        model1.removeAllElements();

        GeoPoly poly = GeoPoly.getPoly();

        for (int i = 0; i < v1.size(); i++) {
            cons c = (cons) v1.get(i);
            model1.addElement(c.toDDString());
        }

        for (int i = 0; i < v.size(); i++) {
            cons c = (cons) v.get(i);
            model2.addElement((i + 1) + ": " + c.toDString());


        }
        for (int i = 0; i < v.size(); i++) {
            cons c = (cons) v.get(i);
//            TMono m = poly.mm_poly(c, dp);
//            String s = (i + 1) + ": ";
//            if (m != null)
//                s += poly.printNPoly(m);
//            model22.addElement(s);
        }
    }

}