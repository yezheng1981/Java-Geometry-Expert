package wprover;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-11
 * Time: 13:28:05
 * To change this template use File | Settings | File Templates.
 */
public class JObjectFlash extends JFlash implements ActionListener {
    private static int TIMERS = 130;
    private static int MAXFLASHTIMES = 12;
    private Vector vlist;
    private int count = 0;

    public JObjectFlash(JPanel p) {
        super(p);
        panel = p;
        vlist = new Vector();
        timer = new Timer(TIME_INTERVAL, this);
        vType = true;
    }

    public boolean draw(Graphics2D g2) {
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (count % 2 == 0)
            setListInFlashing(vlist, false);
        else
            setListInFlashing(vlist, true);
        if (panel != null)
            panel.repaint();
        count++;
        if (count == MAXFLASHTIMES)
            this.stop();

    }

    private void setListInFlashing(Vector v, boolean inflash) {
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            cc.setInFlashing(inflash);
        }
    }

    private void stopListFlash(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            cc.stopFlash();
        }

    }

    public void setAt(JPanel p, Vector list) {
        panel = p;
        stopListFlash(vlist);
        vlist.clear();
        for (int i = 0; i < list.size(); i++) {
            CClass c = (CClass) list.get(i);
            if (c != null && c.visible())
                vlist.add(c);
        }
    }

    public void addFlashObject(CClass obj) {
        if (obj != null && !vlist.contains(obj) && obj.visible())
            vlist.add(obj);
    }

    public void start() {
        if (vlist.size() == 0) {
            stop();
            return;
        }

        count = 0;
        setListInFlashing(vlist, true);
        super.start();
    }

    public void stop() {
        stopListFlash(vlist);
        super.stop();
    }
}
