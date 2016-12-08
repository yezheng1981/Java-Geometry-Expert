package wprover;


import UI.DropShadowBorder;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-8-19
 * Time: 11:35:20
 * To change this template use File | Settings | File Templates.
 */
public class AboutDialog extends JPopupMenu implements MouseListener {

    JLabel b2;
    Color color = new Color(206, 223, 242);
    GExpert gx;
    wuTextPane pane;


    public AboutDialog(GExpert f) {
        gx = f;

        this.setBorder(BorderFactory.createCompoundBorder(new DropShadowBorder(), BorderFactory.createLineBorder(color, 4)));
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.addMouseListener(this);

        JLabel label = new JLabel(GExpert.createImageIcon("images/about/headline.jpg"));
        label.addMouseListener(this);
        label.setBackground(color);
        label.setForeground(color);
        label.setOpaque(false);
        panel.add(label);


        JPanel panel2 = new JPanel();
        panel2.addMouseListener(this);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        JLabel lv = new JLabel(GExpert.getLanguage(1016, "Java Geometry Expert") + " " + Version.getVersion1());
        lv.setHorizontalTextPosition(JLabel.LEFT);
        panel2.add(lv);

        lv = new JLabel(GExpert.getLanguage(1017, "Built on") + "" + Version.getData());
        lv.setHorizontalTextPosition(JLabel.RIGHT);
        panel2.add(lv);

        lv = new JLabel(GExpert.getLanguage(1018, "JDK Version") + " 1.5.0");
        lv.setHorizontalTextPosition(JLabel.LEFT);
        panel2.add(lv);
        pane = new wuTextPane();
        pane.addMouseListener(this);
//        String s = "\n" +
        addString("\n", "regular");
        addString(GExpert.getLanguage(1019, "Java Geometry Expert is free under GNU General Public License(GPL).") + "\n", "regular");
        addString(GExpert.getLanguage(1020, "The user may download and distribute it freely.\n") + "\n", "regular");
//        addString("The software Geometry Expert (GEX) was originally developed around 1994.\n", "regualr");
//        addString("The Java Version of Geometry Expert ", "regular");
//        addString("(JGEX)", "bold");
//
//
//        String s1 = " initially began in early 2004 in\n Wichita State Univerisity. " +
//                "JGEX is a system which combines dynamic geometry software (DGS),\n" +
//                " automated geometry theorem prover (AGTP) and our approach for visually dynamic presentation of proofs (VDPP).\n\n ";

        //  addString(s1, "regular");

        addString("\n", "bold");
        addString(GExpert.getLanguage(1070, "Authors") + ":" + "\n", "bold");

        addString("Shang Ching Chou\t\t", "bold");
        addString("chou@cs.wichita.edu\n", "head");

        addString("Xiao Shan Gao   \t\t", "bold");
        addString("xgao@mmrc.iss.ac.cn" + "\n", "head");

        addString("Zheng Ye        \t\t", "bold");
        addString("yezheng@gmail.com", "head");


        pane.setEditable(false);
        Font fx = gx.getDefaultFont();

        if (fx != null) {
            Font fy = new Font(fx.getName(), Font.PLAIN, 12);
            pane.setFont(fy);
        }

        panel2.add(pane);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.addMouseListener(this);

        JLabel b1 = new JLabel(GExpert.getLanguage(1023, "for more information,please visit:"));
        b1.addMouseListener(this);
        b2 = new JLabel(" http://www.jgex.net");
        b2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        b2.addMouseListener(this);
        panel3.add(b1);
        panel3.add(b2);
        panel2.add(panel3);
        panel3.setBackground(Color.white);
        b2.setForeground(Color.blue);

        b2.addMouseListener(this);
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.add(panel2);
        this.add(panel);
        initLocation();
    }

    public void initLocation() {
        Frame f = gx.getFrame();
        Dimension dm = this.getPreferredSize();
        this.setLocation(f.getX() + f.getWidth() / 2 - (int) dm.getWidth() / 2, f.getY() + f.getHeight() / 2 - (int) dm.getHeight() / 2);
    }

    public void mouseClicked(MouseEvent e) {

    }

    protected void addString(String s, String type) {
        try {
            StyledDocument doc = pane.getStyledDocument();
            doc.insertString(doc.getLength(), s, doc.getStyle(type));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == b2)
            GExpert.openURL("http://woody.cs.wichita.edu");
        this.setVisible(false);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        //Component c = (Component) e.getSource();
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        if (e.getSource() == b2)
            b2.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    }

    public void mouseExited(MouseEvent e) {
        Component c = (Component) e.getSource();
        setCursor(Cursor.getDefaultCursor());

        if (e.getSource() == b2) {
            b2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        }
    }

}
