package wprover;

import UI.EntityButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-6-17
 * Time: 13:11:37
 * To change this template use File | Settings | File Templates.
 */
public class FloatableToolBar extends JPopupMenu {
    int x, y;
    JPanel mpanel;
    JToggleButton bquit;

    public Dimension getMaximumSize() {
        Dimension dm = super.getMaximumSize();
        dm.setSize(Integer.MAX_VALUE, super.getPreferredSize().getHeight());
        return dm;
    }

    public void show(Component invoker) {
        int h = invoker.getHeight();
        int w = invoker.getWidth();
        super.show(invoker, 0, h - (int) this.getPreferredSize().getHeight() - 2);
    }

    public void menuSelectionChanged(boolean isIncluded) {
    }

    public FloatableToolBar() {
        this.setBorder(null);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


        JToggleButton button = new JToggleButton(GExpert.createImageIcon("images/quit.gif")) {
            public Dimension getPreferredSize() {
                return super.getMinimumSize();
            }

            public Dimension getMaximumSize() {
                return super.getMinimumSize();
            }
        };

        button.setBorder(null);
        button.setActionCommand("Close");
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setToolTipText("Close");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FloatableToolBar.this.setVisible(false);
                JToggleButton b = (JToggleButton) e.getSource();
                b.setSelected(false);
            }
        });

        bquit = button;
        button.setUI(new EntityButtonUI());

        mpanel = new JPanel();
        mpanel.setBorder(null);
        mpanel.add(button);
        mpanel.setBackground(new Color(200, 200, 235));
        this.add(mpanel);

        mpanel.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - x;
                int dy = e.getY() - y;
                movetoDxy(dx, dy);

            }

            public void mouseMoved(MouseEvent e) {
            }

        });
        mpanel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

    }

    public void movetoDxy(int xx, int yy) {

        int dx = xx;
        int dy = yy;
        Point pt = FloatableToolBar.this.getLocationOnScreen();
        Component comp = FloatableToolBar.this.getInvoker();
        int tx = (int) pt.getX() + dx;
        int ty = (int) pt.getY() + dy;
        if (comp == null) return;
        Point pt1 = comp.getLocationOnScreen();
        int w = comp.getWidth();
        int h = comp.getHeight();
        Dimension dm = FloatableToolBar.this.getSize();

        int wt = (int) dm.getWidth();
        int ht = (int) dm.getHeight();
        int x, y;
        x = y = 0;
        if (tx < pt1.getX())
            x = (int) pt1.getX();
        else if (tx + wt > pt1.getX() + w)
            x = (int) pt1.getX() + w - wt;
        else
            x = tx;
        if (ty < pt1.getY())
            y = (int) pt1.getY();
        else if (ty + ht > pt1.getY() + h)
            y = (int) pt1.getY() + h - ht;
        else
            y = ty;
        FloatableToolBar.this.setLocation(x, y);

    }
}
