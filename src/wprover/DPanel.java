package wprover;

import UI.GBevelBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ComponentListener {

    final public static int DRAW = 0;
    final public static int CONS = 1;    // construction.
    drawTextProcess dp = new drawTextProcess();
    private int Input_type = DRAW; // draw  ,1 : prove
    GExpert gxInstance;

    public void SetInputType(int type) {
        this.Input_type = type;
    }

    public void clearAll() {
        this.removeAll();
    }

    public void SetDrawType(int type) {
        this.Input_type = DRAW;
        dp.SetCurrentAction(type);
    }

    public void setStep(double step) {
        dp.animate.Setstep(step);
    }

    public boolean actionNeedProceed() {
        return gxInstance == null || !gxInstance.getpprove().isProverRunning();
    }

    public DPanel(GExpert gx) {
        gxInstance = gx;

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.addComponentListener(this);
//        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
//        this.setBorder(new GBevelBorder(GBevelBorder.RAISED));
        this.setBackground(CMisc.getBackGroundColor());
    }


    public void repaintAndCalculate() {
        dp.reCalculate();
        this.repaint();
    }


    public void onAnimate(int type) {
        if (type == 1) //start
            dp.animationStart();
        else if (type == 2)
            dp.animationStop();
        else if (type == 0)
            dp.animationOntime();

        repaint();

    }

    public Dimension getPreferredSize() {
//        Rectangle rc = dp.getBounds();
//
//        int x = (int) (Math.abs(rc.getX() + rc.getWidth())) + 20;
//        int y = (int) (Math.abs(rc.getY() + rc.getHeight())) + 20;
//        return new Dimension(x, y);
        return super.getPreferredSize();
    }

    public void mousePressed(MouseEvent e) {
        if (!this.actionNeedProceed())
            return;

        int button = e.getButton();
        if (button == MouseEvent.BUTTON3) {
            dp.DWMouseRightDown(e.getX(), e.getY());
            return;
        }
        if (Input_type == 0) {
            if (dp.GetCurrentAction() == drawProcess.CONSTRUCT_FROM_TEXT) {
                dp.mouseDown(e.getX(), e.getY());
            } else
                dp.DWButtonDown(e.getX(), e.getY());
            repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (!this.actionNeedProceed())
            return;

        int button = e.getButton();
        if (button == MouseEvent.BUTTON3) {

            return;
        }
        if (Input_type == 0) {
            dp.DWMouseDrag(e.getX(), e.getY());
            repaint();
        } else {
        }
    }


    public void mouseReleased(MouseEvent e) {
        if (!this.actionNeedProceed())
            return;

        int button = e.getButton();
        if (button == MouseEvent.BUTTON3)
            return;

        if (Input_type == 0) {
            dp.DWButtonUp(e.getX(), e.getY());
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {


        int button = e.getButton();

        if (button == MouseEvent.BUTTON3)
            return;

        if (Input_type == 0) {
            dp.DWMouseMove(e.getX(), e.getY());
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (!this.actionNeedProceed())
            return;

        if (e.getButton() == MouseEvent.BUTTON3)
            dp.DWMouseRightClick(e.getX(), e.getY());
        else if (e.getClickCount() > 1)
            dp.DWMouseDbClick(e.getX(), e.getY());

    }

    public void mouseExited(MouseEvent e) {
//        if (!this.actionNeedProceed())
//            return;

        dp.setMouseInside(false);
        this.repaint();
    }

    public void mouseEntered(MouseEvent e) {
//        if (!this.actionNeedProceed())
//            return;

        dp.setMouseInside(true);
        this.repaint();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
//        if (!this.actionNeedProceed())
//            return;

        int n = e.getScrollAmount();
        dp.DWMouseWheel(e.getX(), e.getY(), n, e.getWheelRotation());
        this.repaint();
    }


    public void componentResized(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension dm = this.getSize();
        dp.SetDimension(dm.getWidth(), dm.getHeight());
        dp.paintPoint(g);
//        paintBD(g);
    }


    final private static BasicStroke bstroke = new BasicStroke(1.0f);

    public void paintBD(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(bstroke);
        g2.setColor(Color.LIGHT_GRAY);
        int w = this.getWidth() - 1;
        int h = this.getHeight() - 1;

        g2.drawLine(0, h, w, h);
        g2.drawLine(w, 0, w, h);
    }
}
