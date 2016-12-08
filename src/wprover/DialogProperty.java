package wprover;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-7-16
 * Time: 15:47:43
 * To change this template use File | Settings | File Templates.
 */
public class DialogProperty extends JBaseDialog
{
      public DialogProperty(GExpert owner,JPanel panel)
      {
          super(owner.getFrame(),false);
          this.setTitle("Property");
          this.setSize(370,310);
          getContentPane().add(panel);
          this.setBackground(Color.white);
      }
}
