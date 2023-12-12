package UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;


public class EntityButtonUI extends BasicButtonUI {

    private static Color BackgroundOver = new Color(224, 232, 246);
    private static Color BorderOver = new Color(152, 180, 226);

    private static Color BackgroundSelected = new Color(193, 210, 238);
    private static Color BorderSelected = new Color(49, 106, 197);
//    private static DropShadowBorder dsp= new DropShadowBorder();

    private int type = 0; // 0. all, 1. left, 2. right, 3. top, 4, bottom.

    public EntityButtonUI() {
        super();
    }

    public EntityButtonUI(int t) {
        super();
        type = t;
    }

    public void setType(int t) {
        type = t;
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setRolloverEnabled(true);
        button.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        boolean b1 = model.isRollover();
        boolean b2 = model.isArmed();
        boolean b3 = model.isSelected();
        if (b1 || b2 || b3) {

            int w = c.getWidth();
            int h = c.getHeight();

            Color oldColor = g.getColor();
            if (b3)
                g.setColor(BackgroundSelected);
            else
                g.setColor(BackgroundOver);

            g.fillRect(0, 0, w - 1, h - 1);

            if (b3)
                g.setColor(BorderSelected);
            else
                g.setColor(BorderOver);

            if (type == 0)
                g.drawRect(0, 0, w - 1, h - 1);
            else {
                if (type == 1) {
                    g.drawLine(0, 0, w - 1, 0);
                    g.drawLine(0, h - 1, w - 1, h - 1);
                    g.drawLine(w - 1, 0, w - 1, h - 1);
                } else if (type == 2) {
                    g.drawLine(0, 0, w - 1, 0);
                    g.drawLine(0, h - 1, w - 1, h - 1);
                    g.drawLine(0, 0, 0, h - 1);
                } else if (type == 3) {
                    g.drawLine(w - 1, h - 1, w - 1, 0);
                    g.drawLine(0, h - 1, w - 1, h - 1);
                    g.drawLine(w - 1, 0, w - 1, h - 1);
                } else if (type == 4) {
                    g.drawLine(0, 0, w - 1, 0);
                    g.drawLine(0, 0, 0, h - 1);
                    g.drawLine(w - 1, 0, w - 1, h - 1);
                }
            }
            g.setColor(oldColor);
        }

        super.paint(g, c);
    }

}
