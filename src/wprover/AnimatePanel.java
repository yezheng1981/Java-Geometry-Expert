package wprover;

import UI.EntityButtonUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.*;
import java.awt.*;
import java.net.URL;


public class AnimatePanel extends JToolBar implements ActionListener, ChangeListener {

    Timer timer;
    JSlider framesPerSecond;
    GExpert expert;
    AnimateC ant;
    JToggleButton bx1, bx2, bx3;

                      
                      
    boolean first = true;
    JPanel d;
    drawProcess dp;
    JPopupMenu menu;

    public AnimatePanel(GExpert exp, JPanel pn, drawProcess d) {

        expert = exp;
        this.d = pn;
        this.dp = d;

        this.setOrientation(JToolBar.VERTICAL);
        this.setFloatable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        bx1 = makeAButton("play", "play", "start the animation", "Play");
        panel.add(bx1);

        bx3 = makeAButton("pause", "pause", "pause the animation", "Pause");
        panel.add(bx3);

        bx2 = makeAButton("stop", "stop", "stop the animation", "stop");
        panel.add(bx2);

        framesPerSecond = new JSlider(JSlider.HORIZONTAL, 5, 30, 15);

        framesPerSecond.setMajorTickSpacing(10);
        framesPerSecond.setPaintTicks(false);

        framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
        framesPerSecond.addChangeListener(this);


        int delay = 50;
        timer = new Timer(delay, this);
        timer.setInitialDelay(delay * 7); //We pause animation twice per cycle
        timer.setCoalesce(true);
        this.add(panel);
        this.add(framesPerSecond);
        this.setBorder(BorderFactory.createEtchedBorder()); //Color.lightGray));


        menu = new JPopupMenu();
        JMenuItem it = new JMenuItem("Close");
        menu.add(it);
        it.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnimatePanel.this.setVisible(false);
            }
        });

        MouseListener ls = new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        bx1.addMouseListener(ls);
        bx2.addMouseListener(ls);
        bx3.addMouseListener(ls);
        framesPerSecond.addMouseListener(ls);

        MouseMotionListener ls1 = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (e.getSource() == framesPerSecond && timer != null) {
                    adjustSpeed();
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        };
        framesPerSecond.addMouseMotionListener(ls1);

    }

    public void setEnableAll() {
        bx1.setEnabled(true);
        bx2.setEnabled(true);
        bx3.setEnabled(true);
    }

    public Dimension getPreferredSize() {
        return super.getMinimumSize();
    }

    public void setAttribute(AnimateC ant) {
        this.ant = ant;
        first = true;
        framesPerSecond.setValue(ant.getInitValue());
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String command = e.getActionCommand();

        if (obj instanceof Timer)
            ant.onTimer();

        else if (command.compareTo("play") == 0) {
            ant.startAnimate();
            timer.start();
        } else if (command.compareTo("stop") == 0) {
            ant.stopAnimate();
            timer.stop();
        } else if (command.compareTo("pause") == 0) {
            if (timer.isRunning())
                timer.stop();
            else
                timer.start();
        }
        setStatus();

        dp.setCalMode1();

        if (!dp.reCalculate()) {
            ant.resetXY();
        }

        dp.setCalMode0();
        d.repaint();
    }

    public void startA() {
        ant.startAnimate();
        timer.start();
    }

    public void stopA() {
        if (timer.isRunning()) {
            if (ant != null) {
                ant.stopAnimate();
                dp.reCalculate();
                dp.stopTrack();
            }
            timer.stop();
        }
    }

    public int getSpeed() {
        return 1000 / (int) framesPerSecond.getValue();
    }

    public void adjustSpeed() {
        int n = getSpeed();
        ant.Setstep(framesPerSecond.getValue());
        timer.setDelay(n);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        if (!source.getValueIsAdjusting()) {
            adjustSpeed();
        }
    }

    protected JToggleButton makeAButton(String imageName,
                                        String actionCommand,
                                        String toolTipText,
                                        String altText) {
        String imgLocation = "images/arrow/" + imageName + ".gif";
        URL imageURL = GExpert.class.getResource(imgLocation);

        JToggleButton button = new JToggleButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }
        button.setUI(new EntityButtonUI());
        return button;
    }

    public void setStatus() {
        bx1.setSelected(false);
        bx2.setSelected(false);
        bx3.setSelected(false);

        if (timer != null) {
            if (timer.isRunning()) {
                bx1.setEnabled(false);
                bx2.setEnabled(true);
                bx3.setEnabled(true);
            } else {
                bx1.setEnabled(true);
                bx2.setEnabled(true);
                bx3.setEnabled(true);
            }
        }
    }

    public void windowOpened(WindowEvent e) {
    }

    public void setVisible(boolean aFlag) {
        if (aFlag == false) {
            ant.stopAnimate();
            timer.stop();
            setStatus();
        }
    }

    public boolean isRunning() {
        return timer.isRunning();
    }

}
