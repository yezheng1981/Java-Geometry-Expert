package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-1-12
 * Time: 1:28:53
 * To change this template use File | Settings | File Templates.
 */
public class JArrowFlash extends JFlash implements ActionListener {

    private CPoint p1, p2;
    private int type = 0; // PI/6 and PI*5/6.
    private static BasicStroke stroke = new BasicStroke(2.0f);

    public JArrowFlash(JPanel panel, CPoint p1, CPoint p2, int type) {
        super(panel);
        this.p1 = p1;
        this.p2 = p2;
        timer = new Timer(TIME_INTERVAL, this);
        this.type = type;

    }

//    public JArrowFlash(JPanel panel, CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
//        super(panel);
//        this.p1 = p1;
//        this.p2 = p2;
//        this.p3 = p3;
//        this.p4 = p4;
//        timer = new Timer(TIME_INTERVAL, this);
//
//    }

    public boolean draw(Graphics2D g2) {

        double alpha = Math.PI / 6;
        if (type == 1)
            alpha *= 5;

        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();
        double x = x2 - x1;
        double y = y2 - y1;
        if (x == 0 && y == 0) return false;


        double dis = Math.sqrt(x * x + y * y);
        double dx = x / dis;
        double dy = y / dis;

        double sin = Math.sin(alpha);
        double cos = Math.cos(alpha);
        double ddx = dx * 26;
        double ddy = dy * 26;
        double px1 = x1 + ddx * cos - ddy * sin;
        double py1 = y1 + ddx * sin + ddy * cos;
        double px2 = x1 + ddx * cos + ddy * sin;
        double py2 = y1 - ddx * sin + ddy * cos;


        ddx = -ddx;
        ddy = -ddy;
        double qx1 = x2 + ddx * cos - ddy * sin;
        double qy1 = y2 + ddx * sin + ddy * cos;
        double qx2 = x2 + ddx * cos + ddy * sin;
        double qy2 = y2 - ddx * sin + ddy * cos;

        g2.setStroke(stroke);

//        g2.drawLine((int) x1, (int) y1, (int) (x1 + 18 * dy), (int) (y1 - 18 * dx));
//        g2.drawLine((int) x2, (int) y2, (int) (x2 + 18 * dy), (int) (y2 - 18 * dx));
        g2.drawLine((int) x1, (int) y1, (int) px1, (int) py1);
        g2.drawLine((int) x1, (int) y1, (int) px2, (int) py2);
        g2.drawLine((int) x2, (int) y2, (int) qx1, (int) qy1);
        g2.drawLine((int) x2, (int) y2, (int) qx2, (int) qy2);

        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        n--;
        if (n <= 0) super.stop();

        panel.repaint();
    }
}
