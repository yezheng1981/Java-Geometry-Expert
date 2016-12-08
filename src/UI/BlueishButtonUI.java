package UI;

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-4-11
 * Time: 16:28:51
 * To change this template use File | Settings | File Templates.
 */
public class BlueishButtonUI extends BasicButtonUI
{

      private static Color blueishBackgroundOver = new Color(214, 214, 214);
      private static Color blueishBorderOver = new Color(152, 180, 226);

      private static Color blueishBackgroundSelected = new Color(192, 192, 192);
      private static Color blueishBorderSelected = new Color(49, 106, 197);

      public BlueishButtonUI()
      {
            super();
      }

      public void installUI(JComponent c)
      {

            super.installUI(c);
            AbstractButton b = (AbstractButton) c;
            b.setRolloverEnabled(true);
            b.setOpaque(false);
            b.setHorizontalTextPosition(JButton.CENTER);
            b.setVerticalTextPosition(JButton.BOTTOM);

      }

      public void paint(Graphics g, JComponent c)
      {
            AbstractButton button = (AbstractButton) c;
            if (button.getModel().isRollover() || button.getModel().isArmed()
                    || button.getModel().isSelected())
            {

                  Color oldColor = g.getColor();
                  if (button.getModel().isSelected())
                  {
                        g.setColor(blueishBackgroundSelected);
                  } else
                  {
                        g.setColor(blueishBackgroundOver);
                  }
                  g.fillRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);

                  if (button.getModel().isSelected())
                  {
                        g.setColor(blueishBorderSelected);
                  } else
                  {
                        g.setColor(blueishBorderOver);
                  }
                  g.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);

                  g.setColor(oldColor);
            }

            super.paint(g, c);
      }

}