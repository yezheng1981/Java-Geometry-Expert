package wprover;

import gprover.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;
import java.io.IOException;

import UI.EntityButtonUI;
import UI.BlueishButtonUI;
import UI.SolidBorder;

public class RuleApplicationDialog extends JBaseDialog implements ComponentListener, ActionListener, WindowListener {

    private GExpert gxInstance;
    private GApplet1 app1;
    private DPanel dpane;
    private drawTextProcess dpp;


    private drawTextProcess dx;
    private rulePanel rpanel;
    private ruleViewPanel rvpanel;
    private ruleAppPanel rapanel;
    private JSplitPane panel;
    private JScrollPane spanel;
    private JToggleButton buttona;
    private MouseListener listener;
    private itemLabel lselected = null;
    private Hashtable hash;
    private JDialog ruleDialog;


    public RuleApplicationDialog(GExpert gx, DPanel d, drawTextProcess dp) {
        super(gx.getFrame());
        this.dpane = d;
        this.dpp = dp;
        if (CMisc.isApplication())
            this.setAlwaysOnTop(true);
        gxInstance = gx;
        init();
    }

    public RuleApplicationDialog(GApplet1 gx, DPanel d, drawTextProcess dp) {
        super(gx.getFrame());
        this.app1 = gx;
        this.dpane = d;
        this.dpp = dp;
        gxInstance = null;
        init();
    }


    public void init() {
//        if (gxInstance != null)
//            gxInstance.addDependentDialog(this);

        rpanel = new rulePanel(gxInstance);
        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);


        JPanel panel1 = new JPanel() {
            public Dimension getMaximumSize() {
                return super.getPreferredSize();
            }
        };
        rapanel = new ruleAppPanel();
        rapanel.setFloatable(false);
        buttona = createRPaneButton("AlignCenter24.gif", "Align Center");
        buttona.addActionListener(this);
        rapanel.add(Box.createHorizontalGlue());

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(rvpanel = new ruleViewPanel());
        panel1.add(Box.createVerticalStrut(4));
        panel1.add(rapanel);


        spanel = new JScrollPane(rpanel) {
            public Dimension getPreferredSize() {
                return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        };
        panel.setTopComponent(spanel);
        panel.setBottomComponent(panel1);
        this.getContentPane().add(panel);
        this.addComponentListener(this);
        generateListener();
        hash = new Hashtable();
        this.addWindowListener(this);

        if (gxInstance != null) {
            int w = gxInstance.getWidth() / 2;
            if (w > 500)
                w = 500;
            this.setSize(w, w);
            this.setLocation(gxInstance.getX() + gxInstance.getWidth() - w, gxInstance.getY() + gxInstance.getHeight() - w);
        } else {
            this.setSize(500, 500);
        }

    }

    public void generateListener() {
        lselected = null;

        listener = new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Object obj = e.getSource();
                if (obj instanceof itemLabel) {
                    itemLabel label = (itemLabel) e.getSource();
                    if (label == lselected) {
                    } else {
                        label.setSelected(true);
                        if (lselected != null)
                            lselected.setSelected(false);
                    }

                    lselected = label;
                    Object o = lselected.getUserObject();
                    startFlashObject(o);

                } else {
                    if (lselected != null)
                        lselected.setSelected(false);
                    lselected = null;
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public void startFlashObject(Object o) {

        Object f = hash.get(o.toString());
        if (f instanceof JFlash) {
            JFlash f1 = (JFlash) f;
            f1.start();
        }
        rpanel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (buttona == e.getSource()) {
            rpanel.centerAllObject();
            rpanel.scrollToCenter();
            rpanel.repaint();
            buttona.setSelected(false);
        }
    }

    private JToggleButton createRPaneButton(String name, String tip) {
        JToggleButton b = new JToggleButton(GExpert.createImageIcon("images/rpane/" + name));
        b.setToolTipText(tip);
        Dimension dm = new Dimension(22, 22);
        b.setPreferredSize(dm);
        b.setMaximumSize(dm);
        b.setUI(new EntityButtonUI());
        return b;
    }

    public void LoadRule(el_term el) {
        this.setTitle("RULE " + el.etype);
        hash.clear();
        rpanel.LoadRule(el);
        rvpanel.loadRule(el);
        rapanel.loadRule(el);
        panel.resetToPreferredSizes();
        rpanel.scrollToCenter();
    }

    public void LoadRule(cond c) {
        this.setTitle("RULE " + c.getRule());
        hash.clear();
        rpanel.LoadRule(c);
        rvpanel.loadRule(c);
        rapanel.loadRule(c);
        rpanel.scrollToCenter();
    }

    public void componentResized(ComponentEvent e) {
        panel.resetToPreferredSizes();

    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    class ruleViewPanel extends JToolBar {
        private Vector vlist = new Vector();

        public ruleViewPanel() {
            super(JToolBar.HORIZONTAL);
            this.setFloatable(false);
            this.addMouseListener(listener);
        }


        private JLabel setLabelObject(int index, int type, Object obj) {
            itemLabel label = null;
            if (index <= vlist.size()) {
                label = new itemLabel(false, true);
                label.setBorder(new EmptyBorder(1, 2, 1, 2));
                label.setForeground(Color.black);
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                label.addMouseListener(listener);
                vlist.add(label);
            } else
                label = (itemLabel) vlist.get(index);
            label.setUserObject(type, obj);
            this.add(label);
            return label;
        }

        public void loadRule(cond c) {
            this.removeAll();
            setLabelObject(0, 4, c);
            this.add(Box.createHorizontalStrut(5));
            int r = c.getRule();
            if (r != 0) {
                RuleLabel label = new RuleLabel();
                label.setRuleValue(0, r);
                this.add(label);
            }
            this.add(Box.createHorizontalGlue());
        }

        public void loadRule(el_term el) {
            this.removeAll();

            Vector v = el.getAllxterm();
            setLabelObject(0, 1, v.get(0));
            setLabelObject(1, 0, " = ");
            for (int i = 1; i < v.size(); i++)
                setLabelObject(i + 1, 1, v.get(i));
            int r = el.getEType();
            if (r != 0) {
                RuleLabel label = new RuleLabel();
                label.setRuleValue(1, r);
                this.add(label);
            }
            this.add(Box.createHorizontalGlue());
        }
    }

    class ruleAppPanel extends JToolBar {
        private Vector vlist = new Vector();

        public ruleAppPanel() {
            super(JToolBar.HORIZONTAL);
            this.setFloatable(false);
            this.addMouseListener(listener);
        }

        private JLabel setLabelObject(int index, int type, Object obj) {
            itemLabel label = null;
            if (index <= vlist.size()) {
                label = new itemLabel(false, true);
                label.setBorder(new EmptyBorder(1, 2, 1, 2));
                label.setForeground(Color.black);
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                label.addMouseListener(listener);
                vlist.add(label);
            } else
                label = (itemLabel) vlist.get(index);
            label.setUserObject(type, obj);
            this.add(label);
            return label;
        }

        public void loadRule(cond c) {
            this.removeAll();

            Vector v = c.vlist;
            int n = v.size();
            if (n != 0) {
                Color cr = new Color(0, 128, 0);
                JLabel label = new JLabel("because  ");
                label.setForeground(cr);
                this.add(label);
                for (int i = 0; i < v.size(); i++) {
                    setLabelObject(0, 4, (cond) v.get(i)).setForeground(Color.darkGray);
                    if (i < n - 1) {
                        label = new JLabel(" , ");
                        this.add(label);
                    }
                }
            }
            this.add(Box.createHorizontalGlue());
            this.add(buttona);
        }

        public void loadRule(el_term el) {
            this.removeAll();
            Vector v = el.getAllCond();
            int n = v.size();

            if (n > 0) {
                JLabel label = new JLabel("because  ");
                Color cr = new Color(0, 128, 0);
                label.setForeground(cr);
                this.add(label);
                for (int i = 0; i < n; i++) {
                    setLabelObject(0, 4, (cond) v.get(i)).setForeground(Color.darkGray);
                    if (i < n - 1) {
                        label = new JLabel(" , ");
                        this.add(label);
                    }
                }
            }
            this.add(Box.createHorizontalGlue());
            this.add(buttona);
        }
    }

    class JruleTreePanel extends JPanel {
        private JEditorPane pane;

        public JruleTreePanel() {
            pane = new JEditorPane();
            this.add(pane);
        }

        public void loadRule(el_term el) {
            URL url = GExpert.getResourceURL("rule/rule" + el.getEType() + ".html");
            try {
                pane.setPage(url);
            } catch (IOException ee) {
            }

        }

    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        if (ruleDialog != null)
            ruleDialog.setVisible(false);
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }


    class rulePanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener {

        int xx, yy;
        double scale = 1.0;
        protected Rectangle rc = new Rectangle(0, 0, 0, 0);

        public Dimension getPreferredSize() {
            return new Dimension((int) rc.getWidth() + 20, (int) rc.getHeight() + 20);
        }

        public void componentResized(ComponentEvent e) {
            if (dx == null) return;
            Vector v1 = dx.pointlist;
            Rectangle rc = this.getPointsBounds(v1);
            double rx = this.getWidth();
            double ry = this.getHeight();
            if (rc.getWidth() > rx || rc.getHeight() > ry) {
                double r1 = rx / (rc.getWidth() * 1.1);
                double r2 = ry / (rc.getHeight() * 1.1);
                scale = r1 < r2 ? r1 : r2;
            } else
                scale = 1.0;
            centerAllObject();
            scrollToCenter();
        }

        public void scrollToCenter() {
            Rectangle rc1 = spanel.getViewport().getBounds();
            JScrollBar b1 = spanel.getHorizontalScrollBar();
            b1.setValue((int) ((b1.getMaximum() - rc1.getWidth()) / 2));
            b1 = spanel.getVerticalScrollBar();
            b1.setValue(((int) (b1.getMaximum() - rc1.getHeight()) / 2));
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentHidden(ComponentEvent e) {
        }

        public void centerAllObject() {
//            Vector v1 = dx.pointlist;
//            this.getPointsBounds(v1);

            xx = (int) ((this.getWidth() - rc.getWidth() * scale) / 2 - rc.getX() * scale);
            yy = (int) ((this.getHeight() - rc.getHeight() * scale) / 2 - rc.getY() * scale);
        }

        public rulePanel(GExpert gx) {
            xx = yy = 0;


            dx = new drawTextProcess();
            dx.setCurrentDrawPanel(this);
            dx.setRecal(false);
            dx.SetCurrentAction(drawProcess.MOVE);
            this.addMouseListener(this);
            this.setBackground(Color.white);
            this.addMouseMotionListener(this);
            this.addComponentListener(this);
            this.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int n = e.getWheelRotation();
                    if (scale < 0.4 && n < 0 || scale > 3.0 && n > 0) return;
                    scale = scale + n * 0.04;
                    centerAllObject();
                    rulePanel.this.repaint();
                }
            });
        }

        public Rectangle getPointsBounds(Vector v) {
            if (v.size() == 0) return rc;
            CPoint p1 = (CPoint) v.get(0);
            double x, y, x1, y1;
            x = x1 = p1.getx();
            y = y1 = p1.gety();
            for (int i = 1; i < v.size(); i++) {
                CPoint p = (CPoint) v.get(i);
                double x0 = p.getx();
                double y0 = p.gety();
                if (x0 > x)
                    x = x0;
                else if (x0 < x1)
                    x1 = x0;
                if (y0 > y)
                    y = y0;
                else if (y0 < y1)
                    y1 = y0;
            }
            rc.setBounds((int) x1, (int) y1, (int) (x - x1), (int) (y - y1));
            return rc;
        }


        public Object addCyclicCircle(cond c, drawProcess dp) {
            Object o = null;
            if (c.pred == gib.CO_CYCLIC) {
                if (c.p[0] == 0) {
                    Circle c1 = dp.fd_circle(c.p[1], c.p[2], c.p[3]);
                    if (c1 != null) {
                        dx.addCircle(c1);
                        o = c1;
                    } else {
                        if (c.p[1] != 0) {
                            JCirFlash f = new JCirFlash(this);
                            for (int i = 1; i <= 4; i++) {
                                if (c.p[i] != 0)
                                    f.addAPoint(dp.fd_point(c.p[i]));
                            }
                            dx.addFlash1(f);
                            o = f;
                        } else {
                            a_cir ac = (a_cir) c.get_attr();
                            JCirFlash f = new JCirFlash(rpanel);
                            f.setCenter(dp.fd_point(ac.o));
                            for (int i = 0; i <= ac.no; i++) {
                                if (ac.pt[i] != 0) {
                                    f.addAPoint(dp.fd_point(ac.pt[i]));
                                    addLine(ac.o, ac.pt[i]);
                                    Cedmark e1 = dx.addedMark(dp.fd_point(ac.o), dp.fd_point(ac.pt[i]));
                                    if (e1 != null)
                                        e1.setdnum(1);
                                }
                            }
                            dx.addFlash1(f);
                            o = f;
                        }
                    }
                } else {

                    Circle c1 = dp.fd_circle(c.p[0], c.p[1]);
                    o = c1;
                    if (c1 != null)
                        dx.addCircle(c1);
                    else {
                        if (c.p[1] != 0) {
                            Circle cc = new Circle(dp.fd_point(c.p[0]), dp.fd_point(c.p[1]));
                            cc.setAttrAux();
                            if (c.p[3] == 0) {
                                addLine(c.p[0], c.p[1]);
                                addLine(c.p[0], c.p[2]);
                            }
                            dx.addCircle(cc);
                            o = cc;
                        } else
                            dx.flashattr(c.get_attr(), rpanel);
                    }

                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[0], c.p[2]);
                    addLine(c.p[0], c.p[3]);
                }
            }
            return o;
        }

        private CLine addLine(int a, int b) {
            drawTextProcess dp = dpp;
            CPoint p1 = dp.fd_point(a);
            CPoint p2 = dp.fd_point(b);
            if (p1 == null || p2 == null) return null;

            CLine ln = dp.fd_line(p1, p2);
            CLine ln1 = dx.fd_line(p1, p2);
            if (ln1 == null) {
                ln1 = new CLine(p1, p2);
                if (ln != null) {
                    ln1.setAttr(ln);
                } else
                    ln1.setAttrAux();
                dx.addLineToList(ln1);
            }

            if (ln != null) {
                Vector v = dx.pointlist;
                for (int i = 0; i < ln.getPtsSize(); i++) {
                    CPoint t = ln.getPoint(i);
                    if (v.contains(t))
                        ln1.addApoint(t);
                }
            }
            return ln1;
        }

        private void addOtherObjects(cond c) {
            drawTextProcess dp = dpp;
            JFlash flash = null;

            switch (c.pred) {
                case gib.CO_ACONG:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[2], c.p[3]);
                    addLine(c.p[4], c.p[5]);
                    addLine(c.p[6], c.p[7]);
//                    obj = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_COLL:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[1], c.p[2]);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_CONG:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[2], c.p[3]);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_PARA:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[2], c.p[3]);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_PERP:
                    CLine ln1 = addLine(c.p[0], c.p[1]);
                    CLine ln2 = addLine(c.p[2], c.p[3]);
                    constraint cs = new constraint(constraint.PERPENDICULAR, ln1, ln2);
                    dx.addConstraintToList(cs);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_CYCLIC:
                    Object obj = addCyclicCircle(c, dp);
                    hash.put(c.toString(), obj);
                    break;
                case gib.CO_MIDP:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[1], c.p[2]);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
                case gib.CO_STRI:
                case gib.CO_CTRI:
                    addLine(c.p[0], c.p[1]);
                    addLine(c.p[1], c.p[2]);
                    addLine(c.p[0], c.p[2]);
                    addLine(c.p[4], c.p[5]);
                    addLine(c.p[5], c.p[6]);
                    addLine(c.p[4], c.p[6]);
                    flash = dp.getFlashCond(rpanel, c);
                    break;
            }
            if (gib.CO_ACONG == c.pred) {
                CPoint p0 = dp.fd_point(c.p[0]);
                CPoint p1 = dp.fd_point(c.p[1]);
                CPoint p2 = dp.fd_point(c.p[2]);
                CPoint p3 = dp.fd_point(c.p[3]);
                CPoint p4 = dp.fd_point(c.p[4]);
                CPoint p5 = dp.fd_point(c.p[5]);
                CPoint p6 = dp.fd_point(c.p[6]);
                CPoint p7 = dp.fd_point(c.p[7]);
                CAngle ag1, ag2;
                ag1 = ag2 = null;

                if ((ag1 = dx.fd_angle_4p(p0, p1, p2, p3)) == null) {
                    ag1 = new CAngle(dx.fd_line(p0, p1), dx.fd_line(p2, p3), p0, p1, p2, p3);
                    ag1.setShowType(2);
                    ag1.ptext.setFont(CMisc.nameFont);
                    dx.addAngleToList2(ag1);
                }
                if ((ag2 = dx.fd_angle_4p(p4, p5, p6, p7)) == null) {
                    ag2 = new CAngle(dx.fd_line(p4, p5), dx.fd_line(p6, p7), p4, p5, p6, p7);
                    ag2.ptext.setFont(CMisc.nameFont);
                    ag2.setShowType(2);
                    dx.addAngleToList2(ag2);
                }
                JObjectFlash f = new JObjectFlash(rpanel);
                f.addFlashObject(ag1);
                f.addFlashObject(ag2);
                flash = f;
            }

            if (flash != null) {
                hash.put(c.toString(), flash);
                dx.addFlash1(flash);
            }
        }

        private void addCondPts(Vector v1, cond c) {
            drawProcess dp = dpp;
            if (c != null) {
                int n = c.p.length;
                if (c.pred == gib.CO_CONG)
                    n = 4;
                for (int i = 0; i < n; i++) {
                    if (c.p[i] != 0) {
                        CPoint pt = dp.fd_point(c.p[i]);
                        if (!v1.contains(pt) && pt != null)
                            v1.add(pt);
                    }
                }
            }
        }

        public void LoadRule(cond conc) {
            cond c = conc;
            Vector v1 = new Vector();
            addCondPts(v1, c);
            Vector v2 = c.vlist;

            if (v2 != null) {
                for (int i = 0; i < v2.size(); i++) {
                    cond c1 = (cond) v2.get(i);
                    addCondPts(v1, c1);
                }
            }
            dx.pointlist.addAll(v1);
            for (int i = 0; i < v1.size(); i++) {
                CPoint p = (CPoint) v1.get(i);
                dx.textlist.add(p.ptext);
            }

            v2 = c.vlist;
            if (v2 != null) {
                for (int i = 0; i < v2.size(); i++) {
                    cond c1 = (cond) v2.get(i);
                    this.addOtherObjects(c1);
                }
            }
            this.addOtherObjects(c);
            rulePanel.this.setBackground(dpane.getBackground());
            dx.gridColor = dpp.gridColor;
            dx.GridX = dpp.GridX;
            dx.GridY = dpp.GridY;
            dx.SetGrid(dpp.isDrawGrid());
            dx.Width = 1000;
            dx.Height = 1000;

            centerAllObject();
        }

        public void LoadRule(el_term el) {
            drawProcess dp = dpp;
            cond c = el.co;
            Vector v1 = new Vector();
            while (c != null) {
                for (int i = 0; i < c.p.length; i++) {
                    if (c.p[i] != 0) {
                        CPoint pt = dp.fd_point(c.p[i]);
                        if (!v1.contains(pt)) v1.add(pt);
                    }
                }
                addOtherObjects(c);
                c = c.nx;
            }

            el_term e1 = el.et;
            while (e1 != null) {
                cond c1 = e1.co;
                while (c1 != null) {
                    addOtherObjects(c1);
                    c1 = c1.nx;
                }
                e1 = e1.nx;
            }
            Vector aglist = el.getAllxterm();

            for (int i = 0; i < aglist.size(); i++) {
                xterm x = (xterm) aglist.get(i);
                var v = x.var;
                if (v == null) continue;
                CPoint p1 = dp.fd_point(v.pt[0]);
                CPoint p2 = dp.fd_point(v.pt[1]);
                CPoint p3 = dp.fd_point(v.pt[2]);
                CPoint p4 = dp.fd_point(v.pt[3]);
                if (!v1.contains(p1)) v1.add(p1);
                if (!v1.contains(p2)) v1.add(p2);
                if (!v1.contains(p3)) v1.add(p3);
                if (!v1.contains(p4)) v1.add(p4);
                CLine ln1 = dp.fd_line(p1, p2);

                if (ln1 == null) {
                    if ((ln1 = dx.fd_line(p1, p2)) == null) {
                        ln1 = new CLine(p1, p2, CLine.LLine);
                        dx.addLine(ln1);
                        ln1.m_dash = 15;
                        ln1.m_width = 2;
                    }
                } else {
                    dx.addLine(ln1);
                }
                CLine ln2 = dp.fd_line(p3, p4);
                if (ln2 == null) {
                    if ((ln2 = dx.fd_line(p3, p4)) == null) {
                        dx.addLine((ln2 = new CLine(p3, p4, CLine.LLine)));
                        ln2.m_dash = 15;
                        ln2.m_width = 2;
                    }
                } else {
                    dx.addLine(ln2);
                }
            }
            Vector vl = dx.linelist;
            for (int i = 0; i < vl.size(); i++) {
                CLine ln = (CLine) vl.get(i);
                Vector v2 = ln.points;
                if (v1.containsAll(v2))
                    continue;

                CLine tn = new CLine(ln.type);
                tn.setAttr(ln);
                for (int j = 0; j < v2.size(); j++) {
                    CPoint p = (CPoint) v2.get(j);
                    if (v1.contains(p))
                        tn.addApoint(p);
                }
                vl.remove(i);
                vl.add(i, tn);
            }

            for (int i = 0; i < aglist.size(); i++) {
                xterm x = (xterm) aglist.get(i);
                var v = x.var;
                if (v == null) {
                    continue;
                }
                CPoint p1, p2, p3, p4;
                if (x.getPV() >= 0) {
                    p1 = dp.fd_point(v.pt[0]);
                    p2 = dp.fd_point(v.pt[1]);
                    p3 = dp.fd_point(v.pt[2]);
                    p4 = dp.fd_point(v.pt[3]);
                } else {
                    p1 = dp.fd_point(v.pt[2]);
                    p2 = dp.fd_point(v.pt[3]);
                    p3 = dp.fd_point(v.pt[0]);
                    p4 = dp.fd_point(v.pt[1]);
                }
                CLine ln1 = dx.fd_line(p1, p2);
                CLine ln2 = dx.fd_line(p3, p4);

                CAngle ang = new CAngle(ln1, ln2, p1, p2, p3, p4);
                ang.ptext.setFont(CMisc.nameFont);
                ang.setShowType(2);

                double[] p = CLine.Intersect(ln1, ln2);
                Vector va = dx.anglelist;
                if (p != null) {
                    int num = 0;
                    for (int k = 0; k < va.size(); k++) {
                        CAngle ag = (CAngle) va.get(k);
                        double[] pp = CLine.Intersect(ag.lstart, ag.lend);
                        if (pp == null) continue;
                        if (Math.abs(p[0] - pp[0]) < CMisc.ZERO && Math.abs(p[1] - pp[1]) < CMisc.ZERO)
                            num++;
                    }
                    if (num != 0)
                        ang.setRadius(15 * num + ang.getRadius());
                    else {
                        if ((p1.getx() - p[0]) * (p2.getx() - p[0]) > 0 && (p1.gety() - p[1]) * (p2.gety() - p[1]) > 0 &&
                                (p3.getx() - p[0]) * (p4.getx() - p[0]) > 0 && (p3.gety() - p[1]) * (p4.gety() - p[1]) > 0) {
                            double l1 = Math.sqrt(Math.pow(p1.getx() - p[0], 2) + Math.pow(p1.gety() - p[1], 2));
                            double l2 = Math.sqrt(Math.pow(p2.getx() - p[0], 2) + Math.pow(p2.gety() - p[1], 2));
                            double l3 = Math.sqrt(Math.pow(p3.getx() - p[0], 2) + Math.pow(p3.gety() - p[1], 2));
                            double l4 = Math.sqrt(Math.pow(p4.getx() - p[0], 2) + Math.pow(p4.gety() - p[1], 2));
                            int radius = ang.getRadius();
                            double t1 = l1 > l2 ? l2 : l1;
                            double t2 = l3 > l4 ? l4 : l3;
                            double t = t1 > t2 ? t2 : t1;
                            if (radius < t && t > 300)
                                ang.setRadius((int) t - 50);
                        }
                    }
                }
                va.add(ang);  //  va = dx.anglelist;
                dx.textlist.add(ang.ptext);
                ang.ptext.setText(x.getString());
                JObjectFlash f = new JObjectFlash(rpanel);
                f.addFlashObject(ang);
                dx.addFlash1(f);
                hash.put(x.toString(), f);
            }

            dx.pointlist.addAll(v1);
            for (int i = 0; i < v1.size(); i++) {
                CPoint p = (CPoint) v1.get(i);
                dx.textlist.add(p.ptext);
            }
            v1.clear();
            centerAllObject();
        }

        public void addVectorWithoutDuplicate(Vector v1, Vector v2) {
            for (int i = 0; i < v2.size(); i++) {
                Object obj = v2.get(i);
                if (!v1.contains(obj))
                    v1.add(obj);
            }
        }

        public void mouseDragged(MouseEvent e) {
            dx.DWMouseDrag((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dpp.reCalculate();
            dx.recal_allFlash();
            dpane.repaint();
            this.repaint();
        }

        public void mouseMoved(MouseEvent e) {
            dx.DWMouseMove((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dpane.repaint();
            this.repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }


        public void mousePressed(MouseEvent e) {
            dx.DWButtonDown((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dpp.reCalculate();
            dx.recal_allFlash();
            dpane.repaint();
            this.repaint();
        }


        public void mouseReleased(MouseEvent e) {
            dx.DWButtonUp((e.getX() - xx) / scale, (e.getY() - yy) / scale);
            dpp.reCalculate();
            dx.recal_allFlash();
            dpane.repaint();
            this.repaint();
        }


        public void mouseEntered(MouseEvent e) {
        }


        public void mouseExited(MouseEvent e) {
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(xx, yy);
            g2.scale(scale, scale);
            dx.paintPoint(g);
        }
    }

    class RuleLabel extends JLabel implements MouseListener {
        private int RULE;
        private int type;
        private Border border2 = new SoftBevelBorder(SoftBevelBorder.LOWERED);
        private Border border1 = new SoftBevelBorder(SoftBevelBorder.RAISED);
        private String str = null;


        public RuleLabel() {
            Color cr = new Color(0, 128, 0);
            this.setForeground(cr);
            this.setBorder(border1);
            this.addMouseListener(this);
        }

        public void setRuleValue(int type, int r) {
            this.type = type;
            this.RULE = r;
            // "<HTML><u>underline</u>"
            str = "( RULE " + RULE + " )";
            this.setText(str);
        }

        public void mouseClicked(MouseEvent e) {
            RuleListDialog dlg = null;

            if (gxInstance != null)
                dlg = new RuleListDialog(gxInstance);
            else dlg = new RuleListDialog(app1);

            if (dlg.loadRule(type, RULE))
                dlg.setVisible(true);
            if (ruleDialog != null)
                ruleDialog.setVisible(false);
            ruleDialog = dlg;
        }


        public void mousePressed(MouseEvent e) {
            this.setBorder(border2);
        }

        public void mouseReleased(MouseEvent e) {
            this.setBorder(border1);
        }

        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            this.setText("<HTML><u>" + str + "</u>");
        }

        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
//            this.setText(str);
            this.setBorder(border1);
        }
    }

}
