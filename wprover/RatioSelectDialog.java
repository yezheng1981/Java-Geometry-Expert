package wprover;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.Owner;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 29, 2006
 * Time: 1:01:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RatioSelectDialog extends JBaseDialog implements ActionListener {
    IntTextField field1, field2;
    JButton button1, button2;
    boolean returnValue;


    public RatioSelectDialog(GExpert f) {
        super(f.getFrame(), true);
        this.setTitle(f.getLanguage(2201, "Set the ratio"));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel labelx = new JLabel(f.getLanguage(2200, "Please input two integers"));
        JPanel px = new JPanel(new FlowLayout());
        px.add(labelx);
        px.add(Box.createHorizontalGlue());
        contentPane.add(px);

        JPanel p = new JPanel(new FlowLayout());
        field1 = new IntTextField(1, 3);
        p.add(field1);
        JLabel label = new JLabel(" / ");
        p.add(label);
        field2 = new IntTextField(1, 3);
        p.add(field2);
        contentPane.add(p);

        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(Box.createHorizontalGlue());
        button1 = new JButton(f.getLanguage(3204, "OK"));
        button2 = new JButton(f.getLanguage("Cancel"));
        p1.add(button1);
        p1.add(button2);

        button1.addActionListener(this);
        button2.addActionListener(this);
        contentPane.add(p1);
        this.setSize(250, 130);
        field1.setSelectionStart(0);
        field1.setSelectionEnd(1);
        centerWindow();

        if (CMisc.isApplication())
            this.setAlwaysOnTop(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1)
            returnValue = true;
        else returnValue = false;
        this.setVisible(false);
    }

    public int getValue1() {
        String s = field1.getText();
        return Integer.parseInt(s);
    }

    public int getValue2() {
        String s = field2.getText();
        return Integer.parseInt(s);
    }

    public void centerWindow() {
        Window wo = this.getOwner();
        int x = wo.getX();
        int y = wo.getY();
        int w = wo.getWidth();
        int h = wo.getHeight();
        this.setLocation(x + w / 2 - 200 / 2, y + h / 2 - 120 / 2);
    }

    class IntTextField extends JTextField {
        public IntTextField(int defval, int size) {
            super("" + defval, size);
        }

        protected Document createDefaultModel() {
            return new IntTextDocument();
        }

        public boolean isValid() {
            try {
                Integer.parseInt(getText());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public int getValue() {
            try {
                return Integer.parseInt(getText());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        class IntTextDocument extends PlainDocument {
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (str == null)
                    return;
                String oldString = getText(0, getLength());
                String newString = oldString.substring(0, offs) + str
                        + oldString.substring(offs);
                try {
                    Integer.parseInt(newString + "0");
                    super.insertString(offs, str, a);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

}