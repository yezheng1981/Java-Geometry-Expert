package wprover;

import UI.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-27
 * Time: 13:25:17
 * To change this template use File | Settings | File Templates.
 */

public class CProveBarPanel extends FloatableToolBar {
    ProveBar bar;

    public CProveBarPanel(GExpert gx) {
        this.add(bar = new ProveBar(gx));
    }

    public void Enable(boolean r) {
        bar.Enable(r);
    }

    public void setValue(int n) {
        bar.setValue(n);
    }

    public void stop() {
        bar.AStop();
    }

    public void setVisible(boolean b) {
        stop();
        super.setVisible(b);
    }

    public void AStep() {
        bar.AStep();
    }

    public boolean isRunning() {
        return bar.isRunning();
    }

    public int getInterval() {
        return bar.getInterval();
    }

    public boolean isStepAtEnd() {
        return bar.isStepAtEnd();
    }
}

class ProveBar extends JToolBar implements ActionListener, ChangeListener {

    private GExpert gxInstance;
    private DPanel dpane;
    private drawTextProcess dp;
    private PanelProve1 pproof;

    private JSlider slider;
    private Timer timer;
    private JToggleButton bx1, bx2, bx3, bx4, bx5;
    private long ntime;
    private boolean steped = false;


    public ProveBar(GExpert gex) {
        super();
        gxInstance = gex;
        this.dpane = gex.d;
        this.dp = gex.dp;
        pproof = gex.getpprove();
        init();
    }

    public ProveBar(GExpert gex, DPanel dd, drawTextProcess dp, PanelProve1 pproof) {
        super();
        gxInstance = gex;
        this.dpane = dd;
        this.dp = dp;
        this.pproof = pproof;
        init();
    }

    public boolean isRunning() {
        return timer != null && timer.isRunning();
    }

    public void Enable(boolean r) {
        bx1.setEnabled(r);
        bx2.setEnabled(r);
        bx3.setEnabled(r);
        bx4.setEnabled(r);
        bx5.setEnabled(r);
        slider.setEnabled(r);
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
            AStop();
        }
    }

    public void setValue(int n) {
        if (n < 0)
            n = CMisc.getFlashInterval();

        slider.setValue(300 - n);
    }

    public void step() {
        if (timer == null) {
            dp.startFlash();

            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (dp.all_flash_finished()) {
                        if (ntime > 3) {
                            if (steped && isStepAtEnd()) {
                                onTimerStop();
                                return;
                            }

                            AStep();
                            ntime = 0;

                            if (!steped && isStepAtMid())
                                steped = true;
                        } else
                            ntime++;
                    }
                }
            };
            timer = new Timer(getInterval(), listener);
            timer.start();
        } else {
            timer.start();
        }
        steped = false;
    }

    public void init() {
        this.setFloatable(false);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(null);


        bx1 = makeButton("arrow/step.gif", "step", "Go to the next step of the proof");
        panel.add(bx1);
        bx2 = makeButton("arrow/end.gif", "end", "Go to the end of the proof");
        panel.add(bx2);


        bx3 = makeButton("arrow/play.gif", "play", "Start animation step by step");
        panel.add(bx3);

        bx4 = makeButton("arrow/pause.gif", "pause", "Pause the animation");
        panel.add(bx4);


        bx5 = makeButton("arrow/stop.gif", "stop", "Stop the animation");
        panel.add(bx5);

        int n = CMisc.getFlashInterval();

        slider = new JSlider(40, 300, 300 - n);
        slider.addChangeListener(this);
        slider.setToolTipText("Ajust the speed of animation");

        MouseMotionListener ls1 = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (e.getSource() == slider) {
                    adjustSpeed();
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        };
        slider.addMouseMotionListener(ls1);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel2.add(panel);
        panel2.add(slider);
        panel2.setBorder(null);
        this.add(panel2);
    }

    public Dimension getPreferredSize() {
        Dimension dm = super.getPreferredSize();
        return dm;
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == bx3) {
            if (dp.checkCPfieldExists()) {
                dp.provePlay(slider.getValue() * 100);
            } else {
                step();
                bx1.setEnabled(false);
                bx2.setEnabled(false);
                bx3.setEnabled(false);
                bx3.setSelected(false);
            }
        } else if (src == bx5) {
            AStop();
            bx5.setSelected(false);
        } else if (src == bx4) {
            if (dp.checkCPfieldExists()) {
                dp.proveStop();
            } else {
                if (timer == null) {
                } else if (timer.isRunning())
                    timer.stop();
                else {
                    timer.start();
                }
            }
            if (timer != null && !timer.isRunning()) {
                bx4.setSelected(false);
                bx3.setEnabled(true);
            } else if (timer != null && timer.isRunning()) {
                bx1.setEnabled(false);
                bx2.setEnabled(false);
                bx3.setEnabled(false);
            }
            bx4.setSelected(false);

        } else if (src == bx1) {
            bx1.setSelected(false);
            AStep();
        } else if (src == bx2) {
            dp.prove_run_to_end();
            pproof.m_runtoend();
            bx2.setSelected(false);
            AStop();
        }
        dpane.repaint();
    }


    public void AStep() {
        if (dp.nextProveStep()) {
        } else if (!pproof.mstep()) {
            if (!steped && isStepAtEnd())
                dp.Undo();
            else
                dp.redo_step();
            dpane.repaint();
        }
    }

    public void AStop() {
        if (timer != null && timer.isRunning()) {
            timer.stop();


            if (!pproof.mstep()) {
                dp.redo();
            }

            if (dp.checkCPfieldExists()) {
                dp.proveStop();
            } else {
            }
        }

        bx1.setEnabled(true);
        bx2.setEnabled(true);
        bx3.setEnabled(true);
        bx5.setSelected(false);
    }

    public boolean isStepAtMid() {
        if (pproof.hasProof())
            return pproof.isStepAtMid();
        else
            return !dp.isRedoAtEnd();
    }

    public boolean isStepAtEnd() {
        if (pproof.hasProof())
            return pproof.isStepAtEnd();
        else
            return dp.isRedoAtEnd();

    }


    public void onTimerStop() {
        steped = false;
        timer.stop();
        bx1.setEnabled(true);
        bx2.setEnabled(true);
        bx3.setEnabled(true);
        bx5.setSelected(false);
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b == false) {
            dp.proveStop();
        }
    }

    public void stateChanged(ChangeEvent e) {
        adjustSpeed();

    }

    public void adjustSpeed() {
        int v = getInterval();
        CMisc.setFlashInterval(v);
        dp.updateFlashDelay();
        dp.setTimerDelay(v);
    }

    public int getTimeDelay() {
        return this.getInterval();
    }

    public int getInterval() {
        int v = slider.getValue();
        return 300 - v;
    }


    JToggleButton makeButton(String imageName, String command, String tip) {

        String imgLocation = "images/" + imageName;
        URL imageURL = GExpert.class.getResource(imgLocation);

        JToggleButton button = new JToggleButton();
        button.setActionCommand(command);
        button.setToolTipText(tip);
        button.addActionListener(this);

        if (imageURL != null) {
            button.setIcon(new ImageIcon(imageURL, command));
        } else {
            button.setText(imageName);
        }
        Dimension dm = new Dimension(35, 27);
        button.setMaximumSize(dm);
        button.setPreferredSize(dm);
        button.setUI(new BLeveledButtonUIX());

        return button;
    }
}
