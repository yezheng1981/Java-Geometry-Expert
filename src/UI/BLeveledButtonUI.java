package UI;

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-7-22
 * Time: 15:25:17
 * To change this template use File | Settings | File Templates.
 */

public class BLeveledButtonUI extends BasicButtonUI {

    private static Border border1 = BorderFactory.createRaisedBevelBorder();
    private static Border border2 = BorderFactory.createEtchedBorder();//createEtchedBorder(Color.white,Color.gray.brighter());//.createMatteBorder(2,2,2,2,Color.LIGHT_GRAY);//.createEmptyBorder(2, 2, 2, 2);

    public BLeveledButtonUI() {
        super();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setRolloverEnabled(true);
        button.setBorder(border2);
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        boolean b1 = button.getModel().isRollover();
        boolean b2 = button.getModel().isArmed();
        boolean b3 = button.getModel().isSelected();
        if (b1) {
            //border1.paintBorder(button,g,button.getX(),button.getY(),button.getWidth(),button.getHeight());
            button.setBorder(border1);
        }else button.setBorder(border2);

        super.paint(g, c);
    }

}
