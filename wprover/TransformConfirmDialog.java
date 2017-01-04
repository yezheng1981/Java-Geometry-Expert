package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-6-25
 * Time: 21:12:36
 * To change this template use File | Settings | File Templates.
 */
public class TransformConfirmDialog extends JBaseDialog implements ActionListener {

    int result = -1;

    public TransformConfirmDialog(Frame f, String s1, String s2) {
        super(f, "Confirm", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel(s1);
        label1.setBorder(BorderFactory.createEmptyBorder(3, 2, 3, 16));
        panel.add(label1);
        JLabel label2 = new JLabel(s2);
        label2.setBorder(BorderFactory.createEmptyBorder(3, 2, 3, 16));
        panel.add(label2);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createHorizontalGlue());
        JButton b1 = new JButton("Yes");
        JButton b2 = new JButton("NO");
        JButton b3 = new JButton("Cancel");
        p.add(b1);
        p.add(Box.createHorizontalStrut(5));
        p.add(b2);
        p.add(Box.createHorizontalStrut(5));
        p.add(b3);
        p.add(Box.createHorizontalStrut(5));
        panel.add(p);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        AbstractAction aaa = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equalsIgnoreCase("Y"))
                    result = 0;
                else if (s.equalsIgnoreCase("N"))
                    result = 1;
                else
                    result = 2;

                TransformConfirmDialog.this.setVisible(false);
            }
        };

        b1.getInputMap().put(KeyStroke.getKeyStroke("Y"), "action");
        b1.getActionMap().put("action", aaa);
        b2.getInputMap().put(KeyStroke.getKeyStroke("N"), "action1");
        b2.getActionMap().put("action1", aaa);
        b3.getInputMap().put(KeyStroke.getKeyStroke("C"), "action2");
        b3.getActionMap().put("action2", aaa);

        this.getContentPane().add(panel);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equalsIgnoreCase("Yes"))
            result = 0;
        else if (s.equalsIgnoreCase("No"))
            result = 1;
        else
            result = 2;
        this.setVisible(false);

//        super.keyPressed();
    }

    public int getResult() {
        return result;
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            result = 0;
            this.setVisible(false);
        } else if (code == KeyEvent.VK_N) {
            result = 1;
            this.setVisible(false);
        } else if (code == KeyEvent.VK_C) {
            result = 2;
            this.setVisible(false);
        }
        super.keyPressed(e);
    }


}
