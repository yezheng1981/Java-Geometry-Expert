package wprover;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;


public class JLineFlash extends JFlash implements ActionListener {
    private Vector vlist = new Vector();
    private boolean alter = false;
    private int an = 0;

    public JLineFlash(JPanel p) {
        super(p);
        timer = new Timer(TIME_INTERVAL, this);
    }

    public JLineFlash(JPanel p, CPoint p1, CPoint p2) {
        super(p);
        vlist.add(p1);
        vlist.add(p2);
        timer = new Timer(TIME_INTERVAL, this);
    }

    public int addALine() {
        J_Line ln = new J_Line();
        vlist.add(ln);
        return vlist.size() - 1;
    }

    public void setInfinitLine(int n) {
        J_Line ln = (J_Line) vlist.get(n);
        ln.setDrawInfinite(true);
    }

    public void addAPoint(int index, CPoint p) {
        J_Line ln = (J_Line) vlist.get(index);

        if (p != null && ln != null) {
            ln.addAPoint(p);
        }
    }

    public void setAlternate(boolean a) {
        if (a) {
            alter = true;
            an = 0;
        }
    }

    public boolean draw(Graphics2D g2) {

        int cindex = drawData.RED;


        if (alter == false && n % 2 == 0) {


            g2.setColor(color);
            g2.setStroke(BStroke2);
            for (int i = 0; i < vlist.size(); i++) {
                J_Line ln = (J_Line) vlist.get(i);
                ln.drawLine(g2);
            }

            for (int i = 0; i < vlist.size(); i++) {
                J_Line ln = (J_Line) vlist.get(i);
                ln.fillPt(g2);
                g2.setColor(color);
                ln.drawPt(g2);
            }

        } else if (alter) {
            g2.setColor(color);
            g2.setStroke(BStroke2);
            if (an < vlist.size()) {
                g2.setColor(drawData.getColor(cindex++));
                J_Line ln = (J_Line) vlist.get(an);
                ln.drawLine(g2);
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        n--;

        if (alter == false) {
            if (n <= 0) {
                super.stop();
            } else {
                for (int i = 0; i < vlist.size(); i++) {
                    J_Line ln = (J_Line) vlist.get(i);
                    if (n % 2 == 0) {
                        ln.setInFlashMode(true);
                    } else {
                        ln.setInFlashMode(false);
                    }
                }
            }
        } else {
            if (n <= 0) {
                an++;
                n = 8;
            }
            if (an >= vlist.size()) {
                timer.stop();
            } else {
                J_Line ln = (J_Line) vlist.get(an);
                if (n % 2 == 0) {
                    ln.setInFlashMode(true);
                } else {
                    ln.setInFlashMode(false);
                }
            }
        }

        panel.repaint();
    }

    public void stop() {
        super.stop();
        for (int i = 0; i < vlist.size(); i++) {
            J_Line ln = (J_Line) vlist.get(i);
            ln.setInFlashMode(false);
        }
    }

}
