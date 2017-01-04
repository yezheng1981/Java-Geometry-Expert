package wprover;

import javax.swing.*;
import java.security.acl.Group;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Mar 18, 2007
 * Time: 7:57:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RightTransformPopupMenu extends JPopupMenu implements ItemListener, ActionListener {

    private drawProcess dp;
    private JRadioButtonMenuItem m1, m2, m3;
    private JMenuItem m;
    private int xx, yy;

    public RightTransformPopupMenu(drawProcess d) {
        dp = d;

        ButtonGroup g = new ButtonGroup();
        m1 = new JRadioButtonMenuItem("Translate");
        g.add(m1);
        m2 = new JRadioButtonMenuItem("Rotate");
        g.add(m2);
        m3 = new JRadioButtonMenuItem("Flap");
        g.add(m3);
        m = new JMenuItem("Cancel");

        int t = d.getStatus();
        if (t == 1)
            m1.setSelected(true);
        else
            m2.setSelected(true);

        m1.addItemListener(this);
        m2.addItemListener(this);
        m.addActionListener(this);
        this.add(m1);
        this.add(m2);
        this.add(m3);
        addSeparator();
        this.add(m);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m) {
            dp.setTransformStatus(0);
            return;
        }
    }

    public void show(Component invoker, int x, int y) {
        super.show(invoker, x, y);
        xx = x;
        yy = y;
    }

    public void itemStateChanged(ItemEvent e) {

        if (m1.isSelected()) {
            dp.setTransformStatus(1);

            dp.setFirstPnt(xx, yy);

        } else if (m2.isSelected())
            dp.setTransformStatus(2);
        else
            dp.setTransformStatus(3);
    }

}
