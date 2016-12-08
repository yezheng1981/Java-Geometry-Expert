package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-1-11
 * Time: 23:41:44
 * To change this template use File | Settings | File Templates.
 */
public class JPointEnlargeFlash extends JFlash implements ActionListener {

    private CPoint pt;
    private static final int LENG = 8;
    private static final Color color = Color.white;
    private int nnn = 0;
//    private int mradius = 0;


    public JPointEnlargeFlash(JPanel p, CPoint pt) {
        super(p);
        this.pt = pt;
        timer = new Timer(TIME_INTERVAL, this);
        nnn = pt.m_radius;
    }

    public boolean draw(Graphics2D g2) {
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        n--;
        if (n <= 0) stop();
        if (n % 2 != 0) {
            pt.m_radius = (LENG);
        } else {
            pt.m_radius = (nnn);
        }

        panel.repaint();

    }

    public void stop() {
        pt.m_radius = (nnn);
        super.stop();
    }

}
