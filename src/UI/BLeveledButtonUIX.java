package UI;

import javax.swing.border.Border;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2007-12-10
 * Time: 17:11:21
 * To change this template use File | Settings | File Templates.
 */
public class BLeveledButtonUIX extends BlueishButtonUI {
    private static Border border1 = BorderFactory.createRaisedBevelBorder();
    private static Border border2 = BorderFactory.createEtchedBorder();//createEtchedBorder(Color.white,Color.gray.brighter());//.createMatteBorder(2,2,2,2,Color.LIGHT_GRAY);//.createEmptyBorder(2, 2, 2, 2);

    public BLeveledButtonUIX() {
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
//        if (b2) {
//        } else 
        if (b2 || b3) {
            button.setBorder(border1);
        } else button.setBorder(border2);

        super.paint(g, c);
    }
}
