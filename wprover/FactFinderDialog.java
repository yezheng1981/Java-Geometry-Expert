package wprover;

import gprover.cclass;
import gprover.Prover;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class FactFinderDialog extends JBaseDialog implements ActionListener, ItemListener {

    final private static String[] S =
            {"segment", "midpt", "circle", "parallel line", "perpendicular line", "angle", "tirangle"};

    private GExpert gxInstance;
    private JLabel label;
    private JComboBox bs, b1, b2, b3;
    private JButton bsearch, breset, bcancel;
    private int find_type;
    private DefaultListModel model;
    private JList list;


    public FactFinderDialog(GExpert owner, int type, String title) {
        super(owner.getFrame(), title);
        gxInstance = owner;

        find_type = type;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        bs = new JComboBox(S);
        bs.addItemListener(this);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(new JLabel("type "));
        p1.add(bs);
        panel.add(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        b1 = new JComboBox();
        b1.addItemListener(this);
        b2 = new JComboBox();
        b2.addItemListener(this);
        b3 = new JComboBox();
        b3.addItemListener(this);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.setBorder(BorderFactory.createTitledBorder("Please choose"));
        panel.add(p2);
        label = new JLabel();
        panel.add(label);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bsearch = new JButton("Search");
        bsearch.addActionListener(this);
        bcancel = new JButton("Close");
        bcancel.addActionListener(this);
        breset = new JButton("Reset");
        breset.addActionListener(this);

        p3.add(bsearch);
        p3.add(breset);
        p3.add(bcancel);
        panel.add(p3);
        model = new DefaultListModel();
        list = new JList(model);
        list.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Object o = list.getSelectedValue();
                if (o != null)
                    gxInstance.getpprove().high_light_a_fact((cclass) o);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        JScrollPane p = new JScrollPane(list);
        panel.add(p);
        this.add(panel);
        this.setSize(300, 400);
    }

    public void setPoints(Vector v) {
        b1.removeAllItems();
        b2.removeAllItems();
        b3.removeAllItems();
        for (int i = 0; i < v.size(); i++) {
            Object o = v.get(i);
            b1.addItem(o);
            b2.addItem(o);
            b3.addItem(o);
        }
    }

    public void showDialog() {
        reselect();
        bs.setSelectedIndex(-1);
        this.setVisible(true);
    }

    private void reselect() {
        if (find_type == 0 || find_type == 3 || find_type == 4 || find_type == 6)
            b3.setEnabled(false);
        else
            b3.setEnabled(true);

        bsearch.setEnabled(false);

        b1.setSelectedIndex(-1);
        b2.setSelectedIndex(-1);
        b3.setSelectedIndex(-1);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == bcancel) {
            this.setVisible(false);
            return;
        }

        String s1, s2, s3;
        s1 = s2 = s3 = null;
        Object o1 = b1.getSelectedItem();
        Object o2 = b2.getSelectedItem();
        Object o3 = b3.getSelectedItem();
        s1 = o1.toString();
        s2 = o2.toString();
        if (o3 != null)
            s3 = o3.toString();

        Vector v = null;
        if (src == bsearch) {
            v = Prover.search_a_fact(find_type, s1, s2, s3);
            if (v.size() == 0) {
                JOptionPane.showConfirmDialog(gxInstance, "We can not find anything!",
                        "Can not find", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            } else {

                model.clear();
                for (int i = 0; i < v.size(); i++)
                    model.addElement(v.get(i));
                if (v.size() == 1) {
                    gxInstance.getpprove().high_light_a_fact((cclass) v.get(0));
                    list.setSelectedIndex(0);
                }
            }

        }
    }

    public void selectAPoint(Object b) {
        if (b1.getSelectedIndex() == -1)
            b1.setSelectedItem(b);
        else if (b2.getSelectedIndex() == -1)
            b2.setSelectedItem(b);
        else if (b3.getSelectedIndex() == -1)
            b3.setSelectedItem(b);
    }

    public void itemStateChanged(ItemEvent e) {
        JComboBox bx = (JComboBox) e.getSource();
        if (bx == bs) {
            find_type = bs.getSelectedIndex();
            reselect();
        } else {
            if ((b1.getSelectedIndex() != -1 || !b1.isEnabled())
                    && (b2.getSelectedIndex() != -1 || !b2.isEnabled())
                    && (b3.getSelectedIndex() != -1 || !b3.isEnabled()))
                bsearch.setEnabled(true);
            else
                bsearch.setEnabled(false);
        }
    }
}
