package wprover;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 15, 2006
 * Time: 2:14:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecondCounterDemo extends JBaseDialog {
    CounterPanel pane;
    boolean visible = false;
    Thread mainThread;

    public SecondCounterDemo(GExpert fr, Thread main) {
        super(fr.getFrame(), "Building fixpoint....");
        mainThread = main;
        //this.setModal(true);
        pane = new CounterPanel();
        this.setContentPane(pane);
        this.setSize(320, 200);
        if (CMisc.isApplication()) {
            this.setAlwaysOnTop(true);
            this.requestFocus();
        }

    }

    public void setMainThread(Thread m) {
        mainThread = m;
    }

    public void startCounting() {
        Window fr = this.getOwner();
        if (fr != null)
            setLocation(fr.getX() + (fr.getWidth() - this.getWidth()) / 2, fr.getY() + (fr.getHeight() - this.getHeight()) / 2);
        pane.startCounting();
    }

    public void stopCounting() {
        pane.stopCounting();
    }


    class CounterPanel extends JPanel {
        private SecondCounterRunnable sc = new SecondCounterRunnable();


        private JButton stopB = new JButton("Stop");

        RenderingHints qualityHints = new RenderingHints(RenderingHints.
                KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
//  qualityHints.put(RenderingHints.key)

        public CounterPanel() {
            stopB.setEnabled(false); // begin with this disabled

            stopB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stopB.setEnabled(false);
                    sc.stopClock();
                    //mainThread.stop1();
                }
            });

            JPanel innerButtonP = new JPanel();
            innerButtonP.setLayout(new GridLayout(0, 1, 0, 3));
            innerButtonP.add(stopB);

            JPanel buttonP = new JPanel();
            buttonP.setLayout(new BorderLayout());
            buttonP.add(innerButtonP, BorderLayout.NORTH);

            this.setLayout(new BorderLayout(10, 10));
            this.setBorder(new EmptyBorder(20, 20, 20, 20));
            this.add(buttonP, BorderLayout.WEST);
            this.add(sc, BorderLayout.CENTER);
        }

        public void startCounting() {

            Thread counterThread = new Thread(sc, "Counter");
            counterThread.start();
            stopB.setEnabled(true);
            stopB.requestFocus();
        }

        public void stopCounting() {
            sc.stopClock();
            SecondCounterDemo.this.setVisible(false);
        }

        class SecondCounterRunnable extends JComponent implements Runnable {
            private volatile boolean keepRunning;

            private Font paintFont = new Font("SansSerif", Font.BOLD, 14);

            private volatile String timeMsg = "never started";

            private volatile int arcLen = 0;
            private long normalSleepTime = 100;

            public SecondCounterRunnable() {
            }

            public void run() {
                runClock();
            }

            public void runClock() {
                DecimalFormat fmt = new DecimalFormat("0.000");


                int counter = 0;
                keepRunning = true;

                while (keepRunning) {
                    try {
                        Thread.sleep(normalSleepTime);
                    } catch (InterruptedException x) {
                        // ignore
                    }
                    counter++;
                    double counterSecs = counter / 10.0;
                    timeMsg = fmt.format(counterSecs);
                    arcLen = (((int) counterSecs) % 60) * 360 / 60;

                    if (visible)
                        repaint();
                    else if (counter > 30) {
                        visible = true;
                        SecondCounterDemo.this.setVisible(true);
                    }
                }
            }

            public void stopClock() {
                keepRunning = false;
            }

            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                // g2.setRenderingHints(qualityHints);

                g2.setColor(Color.black);
                g2.setFont(paintFont);
                g2.drawString(timeMsg, 0, 15);

                g2.fillOval(0, 20, 100, 100);

                g2.setColor(Color.white);
                g2.fillOval(3, 23, 94, 94);

                g2.setColor(Color.red);
                g2.fillArc(2, 22, 96, 96, 90, -arcLen);
            }
        }

    }
}