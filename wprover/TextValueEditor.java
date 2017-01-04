package wprover;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-5-31
 * Time: 21:47:16
 * To change this template use File | Settings | File Templates.
 */
public class TextValueEditor extends JBaseDialog implements ActionListener, KeyListener {

    wuTextPane pane1, pane;
    String[] sbutton = {"7", "8", "9", "+", "^", "4", "5", "6", "-",
            "(", "1", "2", "3", "*", ")", "0", "/", ".", "<="};
    JPopupMenu mdata, mfunc;
    CTextValue value;
    GExpert gxInstance;
    CText text = null;

    public void setText(CText t) {
        text = t;
        //pane.setText(t.getString());
        try {
            StyledDocument doc = pane.getStyledDocument();
            doc.insertString(doc.getLength(), t.getString(), doc.getStyle("large1"));
            onValueUpdated();
        } catch (Exception ee) {

        }
        onValueUpdated();
    }

    public TextValueEditor(GExpert gx) {
        super(gx.getFrame(), GExpert.getLanguage(132, "Calculation"));
        gxInstance = gx;



        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        pane1 = new wuTextPane() {
            public Dimension getPreferredSize() {
                Dimension dm = super.getPreferredSize();
                dm.setSize(dm.getWidth(), 30);
                return dm;
            }
        };

        JPanel pt = new JPanel();
        pt.setLayout(new BoxLayout(pt, BoxLayout.Y_AXIS));
        pt.add(pane1);
        pane1.setBackground(this.getBackground());
        pane1.setEditable(false);
        pane = new wuTextPane();
        pane.setEnabled(true);
        pane.setEditable(true);
        pt.add(Box.createVerticalStrut(6));
        pt.add(pane);
        Border bb = new SoftBevelBorder(SoftBevelBorder.LOWERED);
        pane1.setBorder(bb);
        pane.setBorder(bb);
        pane.addKeyListener(this);
        panel.add(pt);
//        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

//        StyledDocument doc = pane.getStyledDocument();
//        Style regular = doc.addStyle("regular", def);
//        StyleConstants.setForeground(regular, Color.black);


        JPanel pp1 = new JPanel();
        pp1.setLayout(new FlowLayout());
        pp1.setBorder(null);

        Font bf = new Font("SansSerif", Font.BOLD, 14);

        JPanel p3 = new JPanel(new GridLayout(4, 5));
        for (int i = 0; i < sbutton.length; i++) {
            JButton b = new JButton(sbutton[i]);
            b.addActionListener(this);
            b.setFont(bf);
            p3.add(b);
        }
        p3.setBorder(null);
        pp1.add(p3);

        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));

        ImageIcon im = GExpert.createImageIcon("images/ptree/downsel.gif");
        JButton bdata = new JButton("Data", im);
//        bdata.setFont(bf);
        JButton bfunc = new JButton("Function", im);
//        bfunc.setFont(bf);
        bdata.setText(GExpert.getLanguage(254, "Data"));
        bfunc.setText(GExpert.getLanguage(255, "Function"));
        bdata.setActionCommand("Data");
        bfunc.setActionCommand("Function");

        p4.add(bdata);
        p4.add(bfunc);
        bdata.addActionListener(this);
        bfunc.addActionListener(this);
        p4.add(Box.createVerticalGlue());

        pp1.add(p4);
        panel.add(pp1);

        JPanel p5 = new JPanel();
        p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
        JButton bok = new JButton(GExpert.getLanguage(3204, "OK"));
        JButton bcancel = new JButton(GExpert.getLanguage("Cancel"));
        bok.setActionCommand("OK");
        bcancel.setActionCommand("Cancel");
        bok.addActionListener(this);
        bcancel.addActionListener(this);
        p5.add(Box.createHorizontalGlue());
        p5.add(bcancel);
        p5.add(bok);


        panel.add(Box.createVerticalStrut(10));
        panel.add(p5);

        panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.getContentPane().add(panel);
        this.setSize(400, 400);

        mdata = new JPopupMenu();
        JMenu m = new JMenu(GExpert.getLanguage(4015, "Coordinates"));
        mdata.add(m);
        Vector v = gxInstance.dp.getPointList();
        for (int i = 0; i < v.size(); i++) {
            CPoint p = (CPoint) v.get(i);
            String s = p.getname();
            int x1 = p.x1.xindex;
            int y1 = p.y1.xindex;
            String space = "   ";
            String space1 = "   ";
            if (x1 >= 10)
                space = "  ";
            if (y1 >= 10)
                space1 = "  ";


            JMenuItem item = new JMenuItem("x" + x1 + space + "(" + s + ".x)");
            JMenuItem item1 = new JMenuItem("x" + y1 + space1 + "(" + s + ".y)");
            item.setActionCommand("x" + x1);
            item1.setActionCommand("x" + y1);
            item.addActionListener(this);
            item1.addActionListener(this);
            m.add(item);
            m.add(item1);
        }

        JMenuItem it = new JMenuItem(CTextValue.SPI);
        it.addActionListener(this);
        mdata.add(it);
        it = new JMenuItem(CTextValue.SE);
        it.addActionListener(this);
        mdata.add(it);

        mfunc = new JPopupMenu();
        for (int i = 0; i < CTextValue.sfunction.length; i++) {
            String s = CTextValue.sfunction[i];
            JMenuItem t = new JMenuItem(s);
            t.addActionListener(this);
            t.setActionCommand(s + "(");
            mfunc.add(t);
            if (i == 5 || i == 12)
                mfunc.addSeparator();
        }


    }

    public void setText(String s) {
        pane.setText(s);
        this.onValueUpdated();
    }

    public void onValueUpdated() {

        String s = pane.getText();
        if (s == null || s.length() == 0) {
            pane1.setText("");
        } else {
            value = CTextValue.parseString(s);
            double r = CTextValue.calvalue(value, gxInstance.dp);
            r = CTextValue.roud3(r);
            pane1.setText(Double.toString(r));
            StyledDocument doc = pane1.getStyledDocument();
            try {
                doc.remove(0, doc.getLength());
                doc.insertString(0, Double.toString(r), doc.getStyle("large"));
            } catch (Exception ee) {
            }

        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("OK")) {
            if (text == null) {
                CText tx = new CText(0, 0, pane.getText());
                tx.setTextType(CText.VALUE_TEXT);
                tx.m_width = 2;
                tx.m_dash = 3;
                tx.m_color = 16;

                if (tx.tvalue != null)
                    tx.tvalue.calculate(gxInstance.dp);
                gxInstance.dp.addText(tx);
            } else {
                text.setText(pane.getText());
                if (text.tvalue != null)
                    text.tvalue.calculate(gxInstance.dp);
            }
            this.setVisible(false);
        } else if (command.equalsIgnoreCase("Cancel")) {
            this.setVisible(false);
        } else if (command.equalsIgnoreCase("Data")) {
            JButton b = (JButton) e.getSource();
            mdata.show(b, 0, b.getHeight());
        } else if (command.equalsIgnoreCase("Function")) {
            JButton b = (JButton) e.getSource();
            mfunc.show(b, 0, b.getHeight());
        } else if (command.equalsIgnoreCase("<=")) {
            try {
                StyledDocument doc = pane.getStyledDocument();
                int len = doc.getLength() - 1;
                if (len >= 0) {
                    doc.remove(len, 1);
                    onValueUpdated();
                }
            } catch (Exception ee) {
                ee.printStackTrace();

            }
        } else {
            if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/"))
                command = " " + command + " ";

            try {
                StyledDocument doc = pane.getStyledDocument();
                doc.insertString(doc.getLength(), command, doc.getStyle("large1"));
                onValueUpdated();
            } catch (Exception ee) {

            }

        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        onValueUpdated();
    }
}
