package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-11-3
 * Time: 10:05:14
 * To change this template use File | Settings | File Templates.
 */
public class SpecificAngleDialog extends JBaseDialog implements ActionListener, ItemListener {
    JCheckBox cb1, cb2, cb3, cb4, cb5,cb6; // 30,45,60,75,120
    JButton bok, bca;

    int type; // 0: define, 1 alread define 2 setangle.

    int rtype = 0;
    GExpert gxInstance;

    public SpecificAngleDialog(GExpert owner, int type, Vector v) {
        super(owner.getFrame(), true);
        gxInstance = owner;

        JPanel panel = new JPanel(new GridLayout(1, 6));
        this.setTitle(GExpert.getLanguage(104, "Angle Specification"));
        cb1 = new JCheckBox("30");
        cb2 = new JCheckBox("45");
        cb3 = new JCheckBox("60");
        cb4 = new JCheckBox("75");
        cb5 = new JCheckBox("120");
        cb6 = new JCheckBox("0");
        panel.add(cb1);
        panel.add(cb2);
        panel.add(cb3);
        panel.add(cb4);
        panel.add(cb5);
        panel.add(cb6);
        cb1.addItemListener(this);
        cb2.addItemListener(this);
        cb3.addItemListener(this);
        cb4.addItemListener(this);
        cb5.addItemListener(this);
        cb6.addItemListener(this);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        bok = new JButton(gxInstance.getLanguage("OK"));
        bca = new JButton(gxInstance.getLanguage("Cancel"));
        bok.setActionCommand("OK");
        bca.setActionCommand("Cancel");
        panel2.add(Box.createHorizontalStrut(80));
        panel2.add(bok);
        panel2.add(bca);
        bok.addActionListener(this);
        bca.addActionListener(this);
        this.add(panel, "North");
        this.add(panel2, "Center");
        this.setSize(300, 100);

        this.type = type;
        if (type == 1) {
            cb1.setEnabled(false);
            cb2.setEnabled(false);
            cb3.setEnabled(false);
            cb4.setEnabled(false);
            cb5.setEnabled(false);
            cb6.setEnabled(false);
            for (int i = 0; i < v.size(); i++) {
                Integer in = (Integer) v.get(i);
                int value = in.intValue();
                if (value == 30)
                    cb1.setEnabled(true);
                else if (value == 45)
                    cb2.setEnabled(true);
                else if (value == 60)
                    cb3.setEnabled(true);
                else if (value == 75)
                    cb4.setEnabled(true);
                else if (value == 120)
                    cb5.setEnabled(true);
                else if(value == 0)
                    cb6.setEnabled(true);
            }
            bok.setEnabled(false);
        } else if (type == 2) {
            cb1.setEnabled(false);
            cb2.setEnabled(false);
            cb3.setEnabled(false);
            cb4.setEnabled(false);
            cb5.setEnabled(false);
            cb6.setEnabled(false);
            for (int i = 0; i < v.size(); i++) {
                Integer in = (Integer) v.get(i);
                int value = in.intValue();
                if (value == 30)
                    cb1.setEnabled(true);
                else if (value == 45)
                    cb2.setEnabled(true);
                else if (value == 60)
                    cb3.setEnabled(true);
                else if (value == 75)
                    cb4.setEnabled(true);
                else if (value == 120)
                    cb5.setEnabled(true);
                else if(value == 0)
                    cb6.setEnabled(true);
            }
        }

        owner.centerDialog(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Object obj = e.getSource();

        if (command.compareTo("OK") == 0) {
            rtype = 1;
            this.setVisible(false);
        } else if (command.compareTo("Cancel") == 0) {
            rtype = 0;
            this.setVisible(false);
        }

    }

    public boolean isOkPressed() {
        return rtype == 1;
    }

    public void itemStateChanged(ItemEvent e) {
        if (type == 0) return;
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object obj = e.getSource();
            if (obj == cb1) {
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
            } else if (obj == cb2) {
                cb1.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);

            } else if (obj == cb3) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);

            } else if (obj == cb4) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb5.setSelected(false);

            } else if (obj == cb5) {
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb1.setSelected(false);
            }
        }
    }


    public Vector getSpecifcAngle() {
        Vector v = new Vector();
        if (cb1.isSelected())
            v.add(new Integer(30));
        if (cb2.isSelected())
            v.add(new Integer(45));
        if (cb3.isSelected())
            v.add(new Integer(60));
        if (cb4.isSelected())
            v.add(new Integer(750));
        if (cb5.isSelected())
            v.add(new Integer(120));
        if (cb6.isSelected())
            v.add(new Integer(0));

        return v;
    }


}

