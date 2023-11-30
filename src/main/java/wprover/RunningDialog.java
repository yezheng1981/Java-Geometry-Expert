package wprover;

import UI.DropShadowBorder;
import UI.EntityButtonUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-10
 * Time: 13:30:36
 * To change this template use File | Settings | File Templates.
 */
public class RunningDialog extends JPopupMenu implements ActionListener {
    public static Timer timer = null;
    private int counter = 0;
    private JLabel label;
    private JLabel labelt;
    private String str;
    private long start_time;
    private panelGB panegb;

    private static Color color = new Color(206, 223, 242);


    public RunningDialog(GExpert gx, String s) {
        this.setBorder(BorderFactory.createCompoundBorder(
                new DropShadowBorder(), BorderFactory.createLineBorder(color, 10)));

        counter = 0;
        str = s;
        label = new JLabel(s);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        labelt = new JLabel() {
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                if (d.getWidth() < 20)
                    d.setSize(20, d.getHeight());
                return d;
            }
        };

        JButton b = new JButton(GExpert.createImageIcon("images/other/stop1.gif"));
        b.setUI(new EntityButtonUI());
        panel.add(b);
        b.setToolTipText("Stop the running.");
        b.addActionListener(this);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        panel.add(labelt);
        this.add(panel);

    }

    public void menuSelectionChanged(boolean isIncluded) {
    }

    public void setPanelGB(panelGB gb) {
        panegb = gb;
    }

    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(300, d.getHeight());
        return d;
    }

    public void setShownString(String s) {
        str = s;
    }

    public void startCount() {
        start_time = System.currentTimeMillis();
    }

    public static RunningDialog startTimer(GExpert gx, String s) {
        if (timer != null && timer.isRunning())
            return null;

        RunningDialog r = new RunningDialog(gx, s);
        r.setShownString(s);

        Dimension dm = r.getPreferredSize();
        Frame f = gx.getFrame();

        int x = (int) (f.getWidth() - dm.getWidth()) / 2;
        int y = (int) (f.getHeight() - dm.getHeight()) / 2;
        r.setLocation(x, y);

        if (timer == null) {
            timer = new Timer(300, r);
            timer.start();
        }

        r.show(gx, x, y);
        r.startCount();

        return r;
    }

    public void stopTimer() {
        if (timer != null)
            timer.stop();
        label.setText("");
        this.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            counter++;
            String s = str;

            if (counter > 1) {
                int n = (counter - 1) % 8;
                for (int i = 0; i < n; i++)
                    s += ".";
                label.setText(s);
            }
            long t = System.currentTimeMillis();
            long m = (t - start_time) / 1000;
            labelt.setText(Long.toString(m) + " Seconds");
        } else {
            timer.stop();
            this.setVisible(false);
            if (panegb != null)
                panegb.stopRunning();
        }
    }
}
