package wprover;

import gprover.Prover;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GProver implements Runnable {

    GExpert gxInstance;
    PanelProve1 pprove;
    Thread main;
    private int Status = 0;
    private boolean isRunning = false;

    Timer timer = null;
    int number = 0;
    long ftime = 0;

    public GProver(PanelProve1 p, GExpert fr) {
        pprove = p;
        gxInstance = fr;
    }

    public void setFix() {
        Status = 0;
    }

    public void setProve() {
        Status = 1;
    }

    public void startTimer() {
        number = 0;
        ftime = System.currentTimeMillis();

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // int n = Prover.getNumberofProperties();
                if (!isRunning)
                    timer.stop();
                else {
                    double t = System.currentTimeMillis() - ftime;
                    int ft = (int) (t / 1000);
                    gxInstance.setLabelText2("Building fixpoint (" + ft + " seconds"
                            + ";  " + Prover.getNumberofProperties() + " facts)");
                }
            }
        });
        timer.start();
    }

    public void run() {
        isRunning = true;
        try {
            if (Status == 0) {
                long n1 = System.currentTimeMillis();
                Prover.run();
                n1 = System.currentTimeMillis() - n1;
                pprove.displayDatabase(n1);
            } else {
                boolean t = Prover.prove();
                pprove.displayGDDProve(t);
            }
        } catch (OutOfMemoryError ee) {
            if (CMisc.DEBUG)
                ee.printStackTrace();
            CMisc.print(ee.getMessage());
            if (gxInstance != null)
                gxInstance.setTextLabel2("System run out of memory!", -1);
            Prover.reset();
            isRunning = false;
            JOptionPane.showMessageDialog(gxInstance,
                    "System run out of memory!\n The theorem is not proved.",
                    "Not Proved", JOptionPane.WARNING_MESSAGE);

        } catch (Error ee) {
            JOptionPane.showMessageDialog(gxInstance,
                    "The theorem is not proved.\n" + ee.getMessage(),
                    "Not Proved", JOptionPane.WARNING_MESSAGE);
            Prover.reset();

        } catch (Exception ee) {
            JOptionPane.showMessageDialog(gxInstance,
                    "The theorem is not proved.\n" + ee.getMessage(),
                    "Not Proved", JOptionPane.WARNING_MESSAGE);
            Prover.reset();
        }
        if (gxInstance != null)
            gxInstance.stopTimer();

        isRunning = false;
    }

    public void start() {
        if (isRunning) return;
        main = new Thread(this, "Prover");
        main.start();
        startTimer();
    }

    public boolean isRunning() {
        return isRunning;
    }
}

