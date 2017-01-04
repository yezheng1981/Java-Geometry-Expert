package wprover;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class RightClickPopMenu extends JPopupMenu implements ActionListener {

    private GExpert gxInstance;
    private CClass cc;

    public RightClickPopMenu(CClass c, GExpert gx) {
        this.gxInstance = gx;
        this.cc = c;
        this.setForeground(Color.white);


        JMenuItem item = addAMenuItem(getLanguage(3205, "Cancel Action"), true);
        item.setActionCommand("Cancel Action");
        item.setMnemonic(KeyEvent.VK_ESCAPE);
        KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        item.setAccelerator(ctrlP);
//        item = addAMenuItem(getLanguage(10, "Move"), true);
//        item.setActionCommand("Move");

        addFreezeMenu();
        this.addSeparator();

        int d = 0;
        if (c != null)
            d = c.getColorIndex();
        addAColorMenuItem(getLanguage(350, "Color"), c != null, d);

        addSpecificMenu(c);
        this.addSeparator();
        item = addAMenuItem(getLanguage(309, "Property"), c != null);
        item.setActionCommand("Property");
    }

    public String getLanguage(int n, String s) {
        if (gxInstance == null)
            return s;
        return GExpert.getLanguage(n, s);
    }

    public void addSpecificMenu(CClass c) {
        if (c == null) return;

        JMenuItem item;

        int t = c.get_type();
        switch (t) {
            case CClass.TEXT:
                item = addAMenuItem(getLanguage(4021, "Edit Text"), true);
                item.setActionCommand("Edit Text");
                addFontSizeMenuItem((CText) c);
                break;
            case CClass.POINT: {
                CPoint p = (CPoint) cc;
                if (!p.isFreezed()) {
                    item = addAMenuItem(getLanguage(4022, "Freeze"), true);
                    item.setActionCommand("Freeze");
                } else {
                    item = addAMenuItem(getLanguage(4023, "Unfreeze"), true);
                    item.setActionCommand("Unfreeze");
                }
                if (gxInstance.dp.getTraceByPt(p) != null) {
                    item = addAMenuItem(getLanguage(4024, "Stop Trace"), true);
                    item.setActionCommand("Stop Trace");
                } else {
                    item = addAMenuItem(getLanguage(120, "Trace"), true);
                    item.setActionCommand("Trace");
                }
                item = addAMenuItem(getLanguage(4005, "X Coordinate"), true);
                item.setActionCommand("X Coordinate");
                item = addAMenuItem(getLanguage(4006, "Y Coordinate"), true);
                item.setActionCommand("Y Coordinate");
            }
            break;
            case CClass.LINE:
                item = addAMenuItem("Slope", true);
                item.setActionCommand("Slope");
                break;
            case CClass.CIRCLE:
                item = addAMenuItem(getLanguage(461, "Area"), true);
                item.setActionCommand("Area");

                item = addAMenuItem(getLanguage(460, "Girth"), true);
                item.setActionCommand("Girth");

                item = addAMenuItem(getLanguage(4004, "Radius"), true);
                item.setActionCommand("Radius");

                break;
            case CClass.POLYGON:
                item = addAMenuItem(getLanguage(461, "Area"), true);
                item.setActionCommand("Area");
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Property"))
            gxInstance.dp.viewElement(cc);
        else if (command.equals("Edit Text")) {
            CText t = (CText) cc;
            Point p = t.getLocation();
            gxInstance.dp.dialog_addText(t, (int) p.getX(), (int) p.getY());
        } else if (command.equals("Color")) {
            JMenuItem it = (JMenuItem) e.getSource();
            Color c = it.getForeground();
            int ci = drawData.getColorIndex(c);
            cc.setColor(ci);
            gxInstance.d.repaint();
        } else if (command.equals("Move")) {
            gxInstance.setActionMove();
        } else if (command.equals("Cancel Action")) {
            gxInstance.onKeyCancel();
        } else if (command.equals("Freeze"))
            ((CPoint) cc).setFreezed(true);
        else if (command.equals("Unfreeze"))
            ((CPoint) cc).setFreezed(false);
        else if (command.equals("Stop Trace"))
            gxInstance.dp.stopTrack();
        else if (command.equals("Trace")) {
            gxInstance.dp.startTrackPt((CPoint) cc);
        } else if (command.equals("X Coordinate")) {
            gxInstance.dp.addCalculationPX((CPoint) cc);
        } else if (command.equals("Y Coordinate")) {
            gxInstance.dp.addCalculationPY((CPoint) cc);

        } else if (command.equals("Area")) {

            if (cc instanceof Circle)
                gxInstance.dp.addCalculationCircle((Circle) cc, 0);
            else {
                CPolygon cp = (CPolygon) cc;
                if (cp.ftype == 1) {
                    Circle c = gxInstance.dp.fd_circleOR((CPoint) cp.getElement(0), (CPoint) cp.getElement(1), (CPoint) cp.getElement(2));
                    if (c != null)
                        gxInstance.dp.addCalculationCircle(c, 0);
                } else {
                    gxInstance.dp.addCalculationPolygon((CPolygon) cc);
                }
            }

        } else if (command.equals("Girth")) {
            gxInstance.dp.addCalculationCircle((Circle) cc, 1);

        } else if (command.equals("Radius")) {
            gxInstance.dp.addCalculationCircle((Circle) cc, 2);
        } else if (command.equals("Slope")) {
            gxInstance.dp.addLineSlope((CLine) cc);
        } else if (command.equals("Unfreeze All Points"))
            gxInstance.dp.unfreezeAllPoints();


    }

    private JMenuItem addAMenuItem(String s, boolean t) {
        JMenuItem item = new JMenuItem(s);
        item.setEnabled(t);
        item.addActionListener(this);
        add(item);
        return item;
    }

    JRadioButtonMenuItem t1, t2;

    private void addFontSizeMenuItem(CText t) {
        int f = t.getFontSize();
        int[] fz = CMisc.getFontSizePool();
        int n = fz.length;
        Font fx = t.getFont();

        ActionListener ls = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                CText t = (CText) cc;
                Object obj = e.getSource();

                if (obj == t1) {
                    t.setPlain();
                } else if (obj == t2) {
                    t.setBold();
                } else {
                    int n = Integer.parseInt(s.trim());
                    t.setFontSize(n);
                }
            }
        };

        JMenu m = new JMenu(getLanguage(4027, "Font Size"));
        for (int i = 0; i < n; i++) {
            JMenuItem item = new JMenuItem(" " + fz[i] + " ");
            item.addActionListener(ls);
            item.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            if (fz[i] == f)
                item.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.gray, 1), new LineBorder(Color.white, 1)));
            m.add(item);
        }
        t1 = new JRadioButtonMenuItem(getLanguage(4025, "Plain"));
        t2 = new JRadioButtonMenuItem(getLanguage(4026, "Bold"));

        ButtonGroup g = new ButtonGroup();
        g.add(t1);
        g.add(t2);
        if (fx.isPlain())
            t1.setSelected(true);
        t1.addActionListener(ls);
        if (fx.isBold())
            t2.setSelected(true);
        t2.addActionListener(ls);
        this.add(t1);
        this.add(t2);
        this.add(m);
    }

    private void addFreezeMenu() {
        if (gxInstance.dp.containFreezedPoint()) {
            JMenuItem item = new JMenuItem("Unfreeze All Points");
            item.setEnabled(true);
            item.addActionListener(this);
            this.add(item);
        }
    }

    private void addAColorMenuItem(String s, boolean t, int d) {
        JMenu item = new JMenu(s);
        item.setEnabled(t);
        add(item);
        if (!t)
            return;

        int n = drawData.getColorCounter();
        Dimension dm = new Dimension(90, 15);
        for (int i = 0; i < n; i++) {
            JMenuItem it = new JMenuItem();
            Color c = drawData.getColor(i);
            it.add(new colorPanel(c));
            it.setForeground(c);
            it.setBorder(BorderFactory.createEmptyBorder(2, 1, 2, 1));
            it.setPreferredSize(dm);
            it.setActionCommand("Color");
            it.addActionListener(this);
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            it.setToolTipText("r = " + r + ", g = " + g + ", b = " + b);
            if (d == i && t) {
                it.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.red, 1), new LineBorder(Color.white, 1)));
            }
            item.add(it);
        }
    }

    class colorPanel extends JPanel {
        public colorPanel(Color c) {
            this.setForeground(c);
            this.setBackground(c);
        }
    }

}
