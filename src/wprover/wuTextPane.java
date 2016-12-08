package wprover;

import maths.TMono;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2007-5-25
 * Time: 17:37:54
 * To change this template use File | Settings | File Templates.
 */
public class wuTextPane extends JTextPane implements ActionListener {
    private JButton button;

    public void clearAll() {
        this.setText("");
    }

    public wuTextPane() {
        this.setEditable(false);
        StyledDocument doc = getStyledDocument();
        addStylesToDocument(doc);
    }

    public void setSize(Dimension d) {
        if (d.width < getParent().getSize().width)
            d.width = getParent().getSize().width;
        super.setSize(d);
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    protected void addStylesToDocument(StyledDocument doc) {

        Font defont = CMisc.algebraFont;    //SansSerif
        String ffamily = defont.getFamily();

        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, ffamily);

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);


        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("head", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, new Color(0, 128, 0));


        int sm = defont.getSize();

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, sm);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, sm + 6);

        s = doc.addStyle("large1", regular);
        StyleConstants.setFontSize(s, sm + 3);
        StyleConstants.setBold(s, false);

        s = doc.addStyle("icon1", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon pigIcon = GExpert.createImageIcon("images/dtree/right.gif");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }
        s = doc.addStyle("icon2", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        pigIcon = GExpert.createImageIcon("images/dtree/wrong.gif");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }
        s = doc.addStyle("icon3", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        pigIcon = GExpert.createImageIcon("images/dtree/question.gif");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }
        s = doc.addStyle("icon4", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        pigIcon = GExpert.createImageIcon("images/dtree/warn.gif");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }


        s = doc.addStyle("button", regular);
        button = new JButton("View Remainder");
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setActionCommand("remainder");
        button.addActionListener(this);
        StyleConstants.setComponent(s, button);
    }


    public void addListnerToButton(ActionListener ls) {
        if (ls == null)
            return;

        button.addActionListener(ls);
    }

    public void actionPerformed(ActionEvent e) {


    }


}
