package UI;

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-5-10
 * Time: 16:51:26
 * To change this template use File | Settings | File Templates.
 */
public class GBevelUI extends BasicButtonUI {

    GBevelBorder border1 = new GBevelBorder(GBevelBorder.RAISED);
    GBevelBorder border2 = new GBevelBorder(GBevelBorder.LOWERED);

    public GBevelUI() {
        super();
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


        if (b3) {
            border2.paintBorder(button, g, 0, 0, button.getWidth(), button.getHeight());
        } else if(b1)
            border1.paintBorder(button, g, 0, 0, button.getWidth(), button.getHeight());
        super.paint(g,button);
    }
}
