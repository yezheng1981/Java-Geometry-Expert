package wprover;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-6-8
 * Time: 13:30:48
 * To change this template use File | Settings | File Templates.
 */

public class TreeCellOpaqueRender extends JPanel implements TreeCellRenderer, MouseListener {
    static Icon Icon_etri = GExpert.createImageIcon("images/dtree/etri.gif");
    static Icon Icon_squa = GExpert.createImageIcon("images/dtree/squa.gif");
    static Icon Icon_perp = GExpert.createImageIcon("images/dtree/perp.gif");
    static Icon Icon_parallelogram = GExpert.createImageIcon("images/dtree/parallelogram.gif");


    private Vector vlist = new Vector();

    private JTree tree;

    protected Color backgroundSelectionColor;

    /**
     * Color to use for the background when the node isn't selected.
     */
    protected Color backgroundNonSelectionColor;

    /**
     * Color to use for the focus indicator when the node has focus.
     */
    protected Color borderSelectionColor;


    protected boolean selected;
    protected boolean hasFocus;

    private GExpert gxInstance = null;
    private boolean isRender = true;

    private TreeCellOpaqueLabel labelSelected;
    private Object userObject;
    private DefaultMutableTreeNode node;


    public TreeCellOpaqueRender(GExpert gx, boolean r) {
        this();
        gxInstance = gx;
        isRender = r;

    }


    public TreeCellOpaqueRender() {
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 3, 0);

        this.setBackground(Color.white);

        this.setLayout(layout);
        setBackgroundSelectionColor(new Color(204, 255, 204));
        setBackgroundNonSelectionColor(Color.gray);
        setBorderSelectionColor(Color.gray);
        selected = false;
        hasFocus = false;
    }


    /**
     * Sets the color to use for the background if node is selected.
     */

    public void setBackgroundSelectionColor(Color newColor) {
        backgroundSelectionColor = newColor;
    }


    /**
     * Returns the color to use for the background if node is selected.
     */
    public Color getBackgroundSelectionColor() {
        return backgroundSelectionColor;
    }

    /**
     * Sets the background color to be used for non selected nodes.
     */
    public void setBackgroundNonSelectionColor(Color newColor) {
        backgroundNonSelectionColor = newColor;
    }

    /**
     * Returns the background color to be used for non selected nodes.
     */
    public Color getBackgroundNonSelectionColor() {
        return backgroundNonSelectionColor;
    }

    /**
     * Sets the color to use for the border.
     */
    public void setBorderSelectionColor(Color newColor) {
        borderSelectionColor = newColor;
    }

    /**
     * Returns the color the border is drawn.
     */
    public Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    private void paintFocus(Graphics g) {
        Color bsColor = getBorderSelectionColor();

        if (bsColor != null && (selected)) {
            g.setColor(bsColor);
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    }

    public void paintComponent(Graphics g) {
        if (selected) {
            Color bColor = getBackgroundSelectionColor();
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(bColor);
            g.fillRect(0, 0, w - 1, h - 1);
        }
        //  if (hasFocus)
        paintFocus(g);

        super.paintComponent(g);

    }


    public void setSelectionFirst() {
        if (labelSelected != null)
            labelSelected.setSelected(false);
        int n = this.getComponentCount();
        if (n == 0) return;
        for (int i = 0; i < n; i++) {
            Object obj = this.getComponent(i);
            if (obj instanceof TreeCellOpaqueLabel) {
                if (i != 0)
                    ((TreeCellOpaqueLabel) obj).setSelected(false);
                else {
                    TreeCellOpaqueLabel b = (TreeCellOpaqueLabel) obj;
                    this.setSelectedLabel(b);
                    labelSelected = b;
                }
            }
        }

    }

    public void setSelectionLast() {
        if (labelSelected != null)
            labelSelected.setSelected(false);
        int n = this.getComponentCount();
        if (n == 0) return;
        Object obj = this.getComponent(n - 1);
        if (obj instanceof TreeCellOpaqueLabel) {
            TreeCellOpaqueLabel b = (TreeCellOpaqueLabel) obj;
            this.setSelectedLabel(b);
            labelSelected = b;
        } else if (obj instanceof TreeCellAssertPanel) {
            TreeCellAssertPanel aspane = (TreeCellAssertPanel) obj;
            aspane.setAllSelected(true);
        }
    }


    public void setSelectedLabel(TreeCellOpaqueLabel eb) {
        if (labelSelected != null)
            labelSelected.setSelected(false);
        labelSelected = eb;
        labelSelected.setSelected(true);
        Object obj = labelSelected.getUserObject();
        if (obj instanceof mobject) {
            if (gxInstance != null) {
                gxInstance.getMannalInputToolBar().setNodeValue(node, (mobject) obj);
                gxInstance.dp.flashmobj((mobject) obj);
            }
        } else {
            if (gxInstance != null && gxInstance.hasMannualInputBar())
                gxInstance.getMannalInputToolBar().setNodeValue(node, null);
        }
        this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() > 1 && node == tree.get) {
//
//        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (tree != null)
                ((MProveTree) tree).showPopupMenu((JComponent) e.getSource(), e.getX(), e.getY());
        } else {

            TreeCellOpaqueLabel label = (TreeCellOpaqueLabel) e.getSource();
            setSelectedLabel(label);
            Object obj = label.getUserObject();
            if (obj instanceof mrule) {
                JPopExView ex = new JPopExView(gxInstance);
                mrule r = (mrule) obj;
                ex.loadRule(r.getRuleName() + ".gex");

                Point p = label.getLocationOnScreen();
                ex.setLocation((int) p.getX() + label.getWidth(), (int) p.getY() + label.getHeight());
                ex.setVisible(true);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    TreeCellOpaqueLabel getLabelByIndex(int id) {
        if (vlist.size() <= id) {
            TreeCellOpaqueLabel label = new TreeCellOpaqueLabel();
            label.addMouseListener(this);
            vlist.add(label);
        }
        TreeCellOpaqueLabel label = (TreeCellOpaqueLabel) vlist.get(id);
        if (isRender) label.setSelected(false);
        return label;
    }

    public void setAllByValue(Object obj) {
        if (obj instanceof mnode) {
            this.removeAll();
            mnode node = (mnode) obj;
            int k = 0;
            int t = node.getIndex();
            if (t >= 0) {
                TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                label.setUserObject(new Integer(t));
                this.add(label);
            }

            for (int i = 0; i < node.objSize(); i++) {
                Object value = node.getObject(i);
                if (value instanceof massertion) {
                    TreeCellAssertPanel p = new TreeCellAssertPanel();
                    p.setAssertion((massertion) value);
                    p.addMouseListener(this);
                    this.add(p);
                } else if (value instanceof mequation) {
                    TreeCellAssertPanel p = new TreeCellAssertPanel();
                    p.setEquation((mequation) value);
                    p.addMouseListener(this);
                    this.add(p);
                } else if (value instanceof mdraw) {
                    String s[] = value.toString().split(" ");
                    if (s != null && s.length > 1) {
                        String str = "";
                        for (int j = 0; j < s.length; j++) {
                            String st = s[j].trim();
                            if (st.length() == 0) continue;
                            ImageIcon icon = mdrobj.getImageIconFromName(st);
                            if (icon == null)
                                str += st + " ";
                            else {
                                if (str.length() != 0) {
                                    TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                                    label.setForeground(Color.black);
                                    label.setText(str);
                                    label.setIcon(null);
                                    str = "";
                                    label.setUserObjectValue(value);
                                    this.add(label);
                                }
                                j++;

                                TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                                label.setIcon(icon);
                                label.setUserObjectValue(value);
                                label.setForeground(Color.black);
                                while (j < s.length) {
                                    String s2 = s[j].trim();
                                    if (s2.length() != 0) {
                                        label.setText(s2);
                                        break;
                                    } else
                                        j++;
                                }
                                this.add(label);
                            }
                        }
                        if (str.length() != 0) {
                            TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                            label.setForeground(Color.black);
                            label.setText(str);
                            label.setIcon(null);
                            this.add(label);
                            label.setUserObjectValue(value);
                        }
                    } else {
                        TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                        label.setUserObject(value);
                        label.setUserObjectValue(value);
                        label.setForeground(Color.black);
                        this.add(label);
                    }
                } else {
                    TreeCellOpaqueLabel label = this.getLabelByIndex(k++);
                    label.setUserObject(value);
                    this.add(label);
                }
            }
        }
    }

    public Dimension getPreferredSize() {
        Dimension dm = super.getPreferredSize();
        double w = dm.getWidth();
        if (w < 50) w = 50;
        double h = dm.getHeight();
        if (h < 20) h = 20;
        dm.setSize(w, h);
        return dm;
    }

    public Dimension getMaximumSize() {
        Dimension dm = super.getMaximumSize();
        if (dm.getWidth() > 350)
            dm.setSize(350, dm.getHeight());
        return dm;

    }


    Component getTreeCellEditorComponent(JTree tree, Object value,
                                         boolean isSelected, boolean expanded,
                                         boolean leaf, int row) {
        if (tree != null)
            this.tree = tree;
        selected = true;
        this.hasFocus = true;

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();
        this.node = node;
        userObject = obj;

        if (obj instanceof mnode) {

            this.setAllByValue(obj);
            this.revalidate();
            return this;
        } else {
            this.removeAll();
            TreeCellOpaqueLabel label = this.getLabelByIndex(0);
            label.setUserObject(obj);
            this.add(label);
            return this;
        }
    }


    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus) {
        if (tree != null)
            this.tree = tree;
        selected = sel;
        this.hasFocus = hasFocus;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();
        this.node = node;
        userObject = obj;
        if (obj instanceof mnode) {

            this.setAllByValue(obj);
            this.revalidate();
            return this;
        } else {
            this.removeAll();
            TreeCellOpaqueLabel label = this.getLabelByIndex(0);
            label.setUserObject(obj);
            this.add(label);
            return this;
        }
    }
}

class TreeCellOPaqueEditor extends AbstractCellEditor implements TreeCellEditor {
    TreeCellOpaqueRender render;

    GExpert gxInstance;
    Object obj = null;
    JTree tree;

    public TreeCellOPaqueEditor(GExpert gx) {
        gxInstance = gx;
        render = new TreeCellOpaqueRender(gx, false);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        render.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
        obj = value;
        if (tree != null)
            this.tree = tree;
        return render;
    }

    public mnode getEditorValue() {
        if (obj != null && obj instanceof DefaultMutableTreeNode) {
            Object obj2 = ((DefaultMutableTreeNode) obj).getUserObject();
            if (obj2 instanceof mnode) return (mnode) obj2;
        }
        return null;
    }

    public void setSelectionLast() {
        render.setSelectionLast();
    }

    public void setSelectionFirst() {
        render.setSelectionFirst();
    }

    public Object getCellEditorValue() {
        return null;
    }

    public void reset() {
        if (obj != null)
            render.setAllByValue(((DefaultMutableTreeNode) obj).getUserObject());
        if (tree != null) {
            tree.revalidate();
            tree.repaint();
        }
        if (render != null) {
            render.revalidate();
            render.repaint();
        }
    }


}

//class TreeCellOpaquePanel extends JPanel {
//    JLabel label1, label2, label3;
//
//    public TreeCellOpaquePanel() {
//        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        label1 = new JLabel();
//        label2 = new JLabel();
//        label3 = new JLabel();
//        this.add(label1);
//        this.add(label2);
//        this.add(label3);
//    }
//
//    public void setUserObject(Object obj) {
//        massertion ass = (massertion) obj;
//        ass.checkValid();
//    }
//
//}

class TreeCellAssertPanel extends JPanel implements MouseListener {
//    private Color backgroundColor = (UIManager.getColor("Tree.selectionBackground"));
//
//    private Color backgroundNoSelectionColor = UIManager.getColor("Tree.textBackground");

    private static Color bcolor = new Color(204, 255, 204);
    private Vector vlist = new Vector();
    private massertion ass;
    private mequation eq;
    private boolean selected = false;


    public TreeCellAssertPanel() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.setBorder(null);
        for (int i = 0; i < 3; i++) {
            TreeCellOpaqueLabel v = new TreeCellOpaqueLabel();
            v.setBorder(null);
            v.addMouseListener(this);
            vlist.add(v);
        }
    }

    public void paint(Graphics g) {
        if (selected) {
            g.setColor(bcolor);
            g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            g.setColor(bcolor.darker());
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
        super.paint(g);
    }

    public void setAllSelected(boolean selected) {
//        for (int i = 0; i < vlist.size(); i++) {
//            TreeCellOpaqueLabel v = (TreeCellOpaqueLabel) vlist.get(i);
//            v.setSelected(selected);
//        }
        this.selected = selected;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        setAllSelected(true);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void setText(String s) {
        this.removeAll();
        TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
        c1.setIcon(null);
        c1.setText(s);
        this.add(c1);
    }

    public Dimension getPreferredSize() {
        Dimension dm = super.getPreferredSize();
        if (dm.getWidth() < 15)
            dm.setSize(15, 20);
        return dm;
    }

    public void setAssertion(massertion as) {
        this.removeAll();
        ass = as;

        int t = as.getAssertionType();
        switch (t) {
            case massertion.COLL:
            case massertion.MID:
            case massertion.CYCLIC:
            case massertion.CONCURRENT:
            case massertion.PARALLELOGRAM:
            case massertion.PERPBISECT: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.toString());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
            }
            break;

            case massertion.OPPOSITE_SIDE:
            case massertion.SAME_SIDE:


            case massertion.CONVEX:
            case massertion.BETWEEN:
            case massertion.R_TRIANGLE:
            case massertion.R_ISO_TRIANGLE:
            case massertion.RECTANGLE:
            case massertion.TRAPEZOID:
            case massertion.EQ_TRIANGLE:
            case massertion.ISO_TRIANGLE: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.getShowString1());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
//                TreeCellOpaqueLabel c2 = this.getLabelByIndex(1);
//                c2.setIcon(null);
//                c2.setText(as.getShowString2());
//                add(c2);
                if (t == massertion.R_ISO_TRIANGLE ||
                        t == massertion.R_TRIANGLE || t == massertion.EQ_TRIANGLE) {
                    c1.setIcon(msymbol.TRI);
                }
                break;
            }
            case massertion.ANGLE_INSIDE:
            case massertion.ANGLE_OUTSIDE: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.getShowString1());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
                TreeCellOpaqueLabel c2 = this.getLabelByIndex(1);
                c2.setIcon(msymbol.ANGLE);
                c2.setText(as.getShowString2());
                add(c2);
                break;
            }
            case massertion.TRIANGLE_INSIDE: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.getShowString1());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
                TreeCellOpaqueLabel c2 = this.getLabelByIndex(1);
                c2.setIcon(msymbol.TRI);
                c2.setText(as.getShowString2());
                add(c2);
                break;
            }

            case massertion.PARA_INSIDE: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.getShowString1());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
                TreeCellOpaqueLabel c2 = this.getLabelByIndex(1);
                c2.setIcon(TreeCellOpaqueRender.Icon_parallelogram);
                c2.setText(as.getShowString2());
                add(c2);
                break;
            }


            default: {
                TreeCellOpaqueLabel c1 = this.getLabelByIndex(0);
                c1.setText(as.getShowString1());
                c1.setIcon(null);
                c1.setUserObject(as);
                this.add(c1);
                TreeCellOpaqueLabel c2 = this.getLabelByIndex(1);
                c2.setIcon(as.getImageIcon());
                c2.setUserObject(as);
                this.add(c2);
                TreeCellOpaqueLabel c3 = this.getLabelByIndex(2);
                c3.setText(as.getShowString2());
                c3.setIcon(null);
                c3.setUserObject(as);
                this.add(c3);
                if (t == massertion.EQANGLE || t == massertion.ANGLESS //|| t == massertion.DISLESS
                        ) {
                    c1.setIcon(msymbol.ANGLE);
                    c3.setIcon(msymbol.ANGLE);

                } else if (t == massertion.PARA) {
                } else if (t == massertion.PERP) {

                } else if (t == massertion.SIM || t == massertion.CONG) {
                    c1.setIcon(msymbol.TRI);
                    c3.setIcon(msymbol.TRI);
                }
            }
            break;
        }

    }

    public void setEquation(mequation eq) {
        this.removeAll();
        this.eq = eq;
        int k = 0;

        for (int i = 0; i < eq.getTermCount(); i++) {
            meqterm et = eq.getTerm(i);
            int t = et.getEType();
            if (t >= 0) {
                TreeCellOpaqueLabel lb = this.getLabelByIndex(k++);
                lb.setText(meqterm.cStrings[t]);
                this.add(lb);
                lb.setUserObjectValue(eq);
            }
            TreeCellOpaqueLabel lb = this.getLabelByIndex(k++);
            lb.setUserObject(et.getObject());
            this.add(lb);
            lb.setUserObjectValue(eq);
        }

    }

    TreeCellOpaqueLabel getLabelByIndex(int id) {
        if (id >= vlist.size()) {
            JLabel label = new TreeCellOpaqueLabel();
            label.setBorder(null);
            label.addMouseListener(this);
            vlist.add(label);
        }
        return (TreeCellOpaqueLabel) vlist.get(id);
    }

    public void addMouseListener(MouseListener l) {
        for (int i = 0; i < vlist.size(); i++) {
            TreeCellOpaqueLabel v = (TreeCellOpaqueLabel) vlist.get(i);
            v.addMouseListener(l);
        }
    }

}

class TreeCellOpaqueLabel extends JLabel implements MouseListener {
//    static Font font = (new Font("Dialog", Font.BOLD, 14));
    protected boolean selected;
    protected boolean mousein;
    private Object userObject;

    transient protected Icon leafIcon;
    protected Color textSelectionColor;
    protected Color textNonSelectionColor;
    protected Color backgroundSelectionColor;
    protected Color backgroundNonSelectionColor;
    protected Color borderSelectionColor;

    public TreeCellOpaqueLabel() {
        setHorizontalAlignment(JLabel.LEFT);
        // setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
        //  setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
        setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
        //setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
        setBorderSelectionColor(UIManager.getColor("Tree.selectionBorderColor"));
        this.addMouseListener(this);
        this.setOpaque(false);
        this.setFont(CMisc.manualFont);
        this.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
        this.setBackground(Color.white);
    }

    public void setUserObjectValue(Object obj) {
        this.userObject = obj;
    }

    public void setUserObject(Object obj) {
        this.userObject = obj;
        if (obj == null) return;

        if (obj instanceof msymbol) {
            this.setText(null);
            this.setIcon(((msymbol) obj).getImage());
        } else if (obj instanceof mdrobj) {
            mdrobj dr = (mdrobj) obj;
            this.setIcon(dr.getImageWithoutLine());
            this.setText(dr.toString());
        } else if (obj instanceof massertion) {
        } else if (obj instanceof Integer) {
            this.setIcon(null);
            this.setText(obj + ": ");
            this.setBackground(Color.blue);
        } else if (obj instanceof meqterm) {
        } else {
            this.setIcon(null);
            this.setText(obj.toString());
            if (obj instanceof mprefix) {
                mprefix f = (mprefix) obj;
                if (f.getPrefixType() == 0 || f.getPrefixType() == 1)
                    this.setForeground(Color.blue);
                else
                    this.setForeground(Color.black);
            } else
                this.setForeground(Color.black);
        }

    }

    public Dimension getPreferredSize() {
        Dimension dm = super.getPreferredSize();
        if (dm.getWidth() < 10)
            dm.setSize(10, dm.getHeight());
        return dm;
    }

    public Object getUserObject() {
        return userObject;
    }

    private void setLeafIcon(Icon newIcon) {
        leafIcon = newIcon;
        this.setIcon(leafIcon);
    }

    private Icon getLeafIcon() {
        return leafIcon;
    }

    private void setTextSelectionColor(Color newColor) {
        textSelectionColor = newColor;
    }

    private Color getTextSelectionColor() {
        return textSelectionColor;
    }

    private void setTextNonSelectionColor(Color newColor) {
        textNonSelectionColor = newColor;
    }

    private Color getTextNonSelectionColor() {
        return textNonSelectionColor;
    }

    private void setBackgroundSelectionColor(Color newColor) {
        backgroundSelectionColor = newColor;
    }

    private Color getBackgroundSelectionColor() {
        return backgroundSelectionColor;
    }

    private void setBackgroundNonSelectionColor(Color newColor) {
        backgroundNonSelectionColor = newColor;
    }

    private Color getBackgroundNonSelectionColor() {
        return backgroundNonSelectionColor;
    }

    private void setBorderSelectionColor(Color newColor) {
        borderSelectionColor = newColor;
    }

    private Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    public void setFont(Font font) {
        if (font instanceof FontUIResource)
            font = null;
        super.setFont(font);
    }

    public Font getFont() {
        Font font = super.getFont();
        return font;
    }

    public void setBackground(Color color) {
        if (color instanceof ColorUIResource)
            color = null;
        super.setBackground(color);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean s) {
        selected = s;
        this.repaint();
    }

    /**
     * Paints the value.  The background is filled based on selected.
     */

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
    }

    public void paint(Graphics g) {
        Color bColor = null;
        if (selected || mousein) {
            bColor = getBackgroundSelectionColor();
        }

        int imageOffset = -1;

        if (selected && bColor != null) {

            imageOffset = 0;
            g.setColor(bColor);
            int w = getWidth();
            int h = getHeight();

            if (getComponentOrientation().isLeftToRight()) {
                g.fillRect(imageOffset, 0, w - imageOffset, h);
            } else {
                g.fillRect(0, 0, w - imageOffset, h);
            }
        }
        if (mousein || selected) {
            bColor = bColor.darker();
            imageOffset = 0;
            g.setColor(bColor);
            if (getComponentOrientation().isLeftToRight()) {
                g.drawRect(imageOffset, 0, getWidth() - imageOffset - 1, getHeight() - 1);
            } else {
                g.drawRect(0, 0, getWidth() - imageOffset - 1, getHeight() - 1);
            }
        }
        super.paint(g);
    }

    private int getLabelStart() {
        Icon currentI = getIcon();
        if (currentI != null && getText() != null) {
            return currentI.getIconWidth() + Math.max(0, getIconTextGap() - 1);
        }
        return 0;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        mousein = true;
        this.repaint();

    }

    public void mouseExited(MouseEvent e) {
        mousein = false;
        this.repaint();
    }

}
