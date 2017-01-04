package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-6-9
 * Time: 9:36:35
 * To change this template use File | Settings | File Templates.
 */
public class ColorButtonPanel extends JPanel implements MouseListener {

        private ColorMenu cm = new ColorMenu("color");

        public ColorButtonPanel(int x, int y)
        {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 1));

            Dimension dm = new Dimension(x, y);

            this.setPreferredSize(dm);
            this.setMinimumSize(dm);
            this.setMaximumSize(dm);
            this.addMouseListener(this);
        }

        public ColorMenu getColorMenu()
        {
            return cm;
        }

        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            cm.colorPane = null;
            cm.setColor(this.getBackground());
            cm.show(this, x, y);
        }

        public void mousePressed(MouseEvent e)
        {

        }

        public void mouseReleased(MouseEvent e)
        {

        }

        public void mouseEntered(MouseEvent e)
        {

        }

        public void mouseExited(MouseEvent e)
        {

        }

        public Color setNewColor()
        {
            if (cm.colorPane != null)
            {
                this.setBackground(cm.getColor());
                return cm.getColor();
            }
            return null;
        }
}
