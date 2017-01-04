package wprover;

import gprover.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Vector;
import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-23
 * Time: 13:50:22
 * To change this template use File | Settings | File Templates.
 */
class vcellRender extends JPanel {
    protected static Border eborder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
    protected static Border gborder = BorderFactory.createLineBorder(Color.GRAY, 1);

    protected static DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    boolean selected;
    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;

    public vcellRender() {
        this.setBorder(eborder);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        selected = false;
        defaultRenderer.setFont(itemLabel.font1);
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.
                getBackgroundNonSelectionColor();
    }


    public void paintComponent(Graphics g) {
        if (selected) {
            g.setColor(backgroundSelectionColor.darker());
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
        super.paintComponent(g);
    }
}
                     

class BookCellRenderer extends vcellRender implements TreeCellRenderer {
    Vector renderlist = new Vector();
    Vector renderlist1 = new Vector();

    public BookCellRenderer(int n) {
        super();
        for (int i = 1; i <= n; i++) {
            itemLabel lb = new itemLabel(true, false);
            renderlist.add(lb);
        }
        for (int i = 1; i <= n; i++) {
            itemLabel lb = new itemLabel(true, true);
            renderlist1.add(lb);
        }
    }

    public void setCellFont(Font f) {
        for (int i = 0; i < renderlist.size(); i++) {
            itemLabel lb = (itemLabel) renderlist.get(i);
            lb.setFont(f);
        }
        Font f1 = new Font(f.getFamily(), f.getStyle(), f.getSize() - 1);
        for (int i = 0; i < renderlist1.size(); i++) {
            itemLabel lb = (itemLabel) renderlist1.get(i);
            lb.setFont(f1);
        }
    }

    public void setLabelObject(int index, int t, Object obj) {
        if (obj == null) return;

        itemLabel label = null;
        label = (itemLabel) renderlist.get(index);
        label.setUserObject(t, obj);
        this.add(label);
    }

    public void setLabelObject1(int index, int t, Object obj) {
        itemLabel label = null;
        label = (itemLabel) renderlist1.get(index);
        label.setUserObject(t, obj);
        this.add(label);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected,
                                                  boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus) {
        Component returnValue = null;

        boolean crsp = false;

        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            this.removeAll();
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            vcellRender cell = this;

            if (userObject instanceof gr_term) {
                gr_term gr = (gr_term) userObject;
                Vector v = gr.getAllxterm();
                int index = v.size();
                int k = 0;
                if (index >= 0) {
                    if (row != 1) {
                        setLabelObject(0, 0, " = ");
                    } else {
                        setLabelObject(0, 0, "    ");
                    }
                    k++;
                    for (int i = 0; i < v.size(); i++) {
                        xterm x = (xterm) v.get(i);
                        setLabelObject(k++, 1, x);
                    }
                }
                if (gr.getPTN() == 0) {
                    setLabelObject(k++, 2, gr.el);
                }
                if (v.size() == 1) {
                    xterm x = (xterm) v.get(0);
                    if (x.getPV() == 0)
                        setLabelObject(k++, 0, "   Q.E.D. ");
                }
            } else if (userObject instanceof el_term) {
                el_term el = (el_term) userObject;
                Vector v = el.getAllxterm();
                int k = 0;
                if (node.getParent() != null) {
                    setLabelObject1(k++, 0, "because");
                }
                setLabelObject1(k++, 1, v.get(0));
                setLabelObject1(k++, 0, " = ");
                for (int i = 1; i < v.size(); i++) {
                    setLabelObject1(k++, 1, v.get(i));
                }
                if (el.getEType() > 0)
                    setLabelObject1(k++, 2, el);
                if (selected) {
                    setLabelObject1(k++, 5, el);
                }
            } else if (userObject instanceof dterm) {
            } else if (userObject instanceof cond) {
                cond c = (cond) userObject;
                int k = 0;
                int nt = c.getNo();
                if (nt != 0 && nt == BookCellEditor.cond_no)
                    crsp = true;

                if (c.getNo() != 0) {
                    setLabelObject(k++, 0, c.getNo() + ". ");
                }
                setLabelObject1(k++, 4, userObject);
                if (c.getNo() != 0 && node.getChildCount() != 0 && c.getRule() > 0) {
                    setLabelObject1(k++, 2, c);
                } else if (node.getChildCount() == 0) {
                    int n = c.get_conc_type();
                    if (n != 0) {
                        setLabelObject1(k++, 0, "   (by HYP)");
                    } else if (c.getNo() == 0) {
                        setLabelObject1(k++, 0, "   (in GIB)");
                    }

                }
            } else if (userObject instanceof l_list) {
                l_list ls = (l_list) userObject;
                setLabelObject(0, 7, ls);
            } else if (userObject instanceof rule) {
                rule ls = (rule) userObject;
                setLabelObject(0, 7, ls);
            } else {
                //cell = null;
                setLabelObject(0, 9999, userObject);
            }

            if (cell != null) {
                if (selected || crsp) {
                    cell.setBackground(cell.backgroundSelectionColor);
                } else {
                    cell.setBackground(cell.backgroundNonSelectionColor);
                }
                cell.setEnabled(tree.isEnabled());
                returnValue = cell;
            }
        }
        if (returnValue == null) {
            returnValue = vcellRender.defaultRenderer.
                    getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
        return returnValue;
    }

 
}


class BookCellEditor extends BasicCellEditor implements MouseListener {

    public BookCellEditor(int n) {
        init(n);

        for (int i = 0; i < renderlist.size(); i++) {
            itemLabel lb = (itemLabel) renderlist.get(i);
            lb.addMouseListener(this);
        }
        for (int i = 0; i < renderlist1.size(); i++) {
            itemLabel lb = (itemLabel) renderlist1.get(i);
            lb.addMouseListener(this);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (selectLabel != null) {
            selectLabel.setSelected(false);
        }
        selectLabel = (itemLabel) e.getSource();
        selectLabel.setSelected(true);
        cell.repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}

                 
class BasicCellEditor extends AbstractCellEditor implements TreeCellEditor {
    public static int cond_no = 0;

    Vector renderlist = new Vector();
    Vector renderlist1 = new Vector();
    vcellRender cell = new vcellRender();
    itemLabel selectLabel = null;


    public void init(int n) {

        for (int i = 1; i <= n; i++) {
            itemLabel lb = new itemLabel(false, false);
            lb.setRenderT(false);

            renderlist.add(lb);
        }
        for (int i = 1; i <= n; i++) {
            itemLabel lb = new itemLabel(false, true);
            lb.setRenderT(false);
            lb.setFont(itemLabel.font1);
            renderlist1.add(lb);
        }
        cell.setBorder(new LineBorder(cell.backgroundSelectionColor.darker(), 1));
    }

    public void setEditorFont(Font f) {
        for (int i = 0; i < renderlist.size(); i++) {
            itemLabel lb = (itemLabel) renderlist.get(i);
            lb.setFont(f);
        }
        Font f1 = new Font(f.getFamily(), f.getStyle(), f.getSize() - 1);
        for (int i = 0; i < renderlist1.size(); i++) {
            itemLabel lb = (itemLabel) renderlist1.get(i);
            lb.setFont(f1);
        }
    }

    public void setLabelObject(int index, int t, Object obj) {
        itemLabel label = null;
        label = (itemLabel) renderlist.get(index);
        label.setUserObject(t, obj);
        cell.add(label);
    }

    public void setLabelObject1(int index, int t, Object obj) {
        itemLabel label = null;
        label = (itemLabel) renderlist1.get(index);
        label.setUserObject(t, obj);
        cell.add(label);
    }

    public void addListenerToAllLabel(MouseListener listener) {
        for (int i = 0; i < renderlist.size(); i++) {
            itemLabel label = (itemLabel) renderlist.get(i);
            label.addMouseListener(listener);
        }
        for (int i = 0; i < renderlist1.size(); i++) {
            itemLabel label = (itemLabel) renderlist1.get(i);
            label.addMouseListener(listener);
        }
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                boolean isSelected,
                                                boolean expanded,
                                                boolean leaf, int row) {
        Component returnValue = null;
        cond_no = 0;
        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            cell.removeAll();
            cell.revalidate();
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            vcellRender cell = this.cell;

            if (userObject instanceof gr_term) {
                gr_term gr = (gr_term) userObject;
                Vector v = gr.getAllxterm();
                int index = v.size();
                int k = 0;
                if (index >= 0) {
                    if (row != 1) {
                        setLabelObject(0, 0, " = ");
                    } else {
                        setLabelObject(0, 0, "    ");
                    }
                    k++;
                    for (int i = 0; i < v.size(); i++) {
                        xterm x = (xterm) v.get(i);
                        setLabelObject(k++, 1, x);
                    }
                }
                if (gr.getPTN() == 0) {
                    el_term e = gr.el;
                    if (e.getEType() > 0) {
                        setLabelObject(k++, 2, gr.el);
                        setLabelObject1(k++, 5, gr.el);
                    }
                }
                if (v.size() == 1) {
                    xterm x = (xterm) v.get(0);
                    if (x.getPV() == 0)
                        setLabelObject(k++, 0, "   Q.E.D. ");
                }
            } else if (userObject instanceof el_term) {
                el_term el = (el_term) userObject;
                Vector v = el.getAllxterm();
                int k = 0;
                if (node.getParent() != null) {
                    setLabelObject1(k++, 0, "because");
                }
                setLabelObject1(k++, 1, v.get(0));
                setLabelObject1(k++, 0, " = ");
                for (int i = 1; i < v.size(); i++) {
                    setLabelObject1(k++, 1, v.get(i));
                }
                if (el.getEType() > 0)
                    setLabelObject1(k++, 2, el);
                setLabelObject1(k++, 5, el);
//                if (node.getParent() != null)
//                    setLabelObject1(k++, 0, " )");
            } else if (userObject instanceof dterm) {
            } else if (userObject instanceof cond) {
                cond c = (cond) userObject;
                int k = 0;
                cond_no = c.getNo();
                if (c.getNo() != 0) {
                    setLabelObject(k++, 0, c.getNo() + ". ");
                }
                setLabelObject1(k++, 4, userObject);
                if (c.getNo() != 0 && node.getChildCount() != 0 &&
                        c.getRule() >= 0) {
                    if (c.getRule() > 0) {
                        setLabelObject1(k++, 2, c);
                    }
                    setLabelObject1(k++, 5, c);
                } else if (node.getChildCount() == 0) {
                    int n = c.get_conc_type();
                    if (n != 0) {
                        setLabelObject1(k++, 0, "   (by HYP)");
                    } else if (c.getNo() == 0) {
                        setLabelObject1(k++, 0, "   (in GIB)");
                    }
                }
            } else {
                //cell = null;
                setLabelObject(0, 9999, userObject);
            }

            if (cell != null) {
                cell.setBackground(cell.backgroundSelectionColor);
                cell.setEnabled(tree.isEnabled());
                returnValue = cell;
            }
        }
        if (returnValue == null) {
            returnValue = vcellRender.defaultRenderer.
                    getTreeCellRendererComponent(tree,
                            value, isSelected, expanded, leaf, row, true);
        }
        return returnValue;

    }


    public void cancelCellEditing() {
        super.cancelCellEditing();
        cond_no = 0;
    }

    public Object getCellEditorValue() {
        return null;
    }
}


class itemLabel extends JLabel {
    public static ImageIcon icon = GExpert.createImageIcon("images/dtree/detail.gif");
    private static ImageIcon icon_bc = GExpert.createImageIcon("images/dtree/because.gif");


    public static Font font = CMisc.fullFont;
    public static Font font1 = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
    public GExpert gxInstance;


    boolean isrender = true;
    boolean mouse_inside = false;
    protected boolean iselm = false;
    int type; // 0.Normal text 1. xterm 2.rule.3.el_term,4:cond  , 5: icon.
    private Object userValue;

    boolean selected = false;

    public int getType() {
        return type;
    }


    public static int getItemLabelFontSize() {
        return font.getSize();
    }

    public Object getUserObject() {
        return userValue;
    }

    public itemLabel(boolean r, boolean e) {
        super();
        isrender = r;
        iselm = e;
        if (e) {
            this.setFont(font1);
        } else {
            this.setFont(font);
            this.setForeground(Color.black);
        }

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                selected = true;
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                mouse_inside = true;
                if (type == 5) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                itemLabel.this.repaint();
            }

            public void mouseExited(MouseEvent e) {
                mouse_inside = false;
                if (type == 5) {
                    setCursor(Cursor.getDefaultCursor());
                }
                itemLabel.this.repaint();
            }
        });
    }


    public void setRenderT(boolean r) {
        this.isrender = r;
    }

    public void setSelected(boolean s) {
        selected = s;
        if (iselm) {
            {
                if (s) {
                    this.setForeground(Color.black);
                } else {
                    this.setForeground(Color.black);
                }
            }
        }
        this.repaint();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setUserObject(int type, Object obj) {
        this.type = type;
        userValue = obj;
        if (type == 2) {
            if (obj instanceof el_term) {
                el_term e = (el_term) obj;
                if (!iselm) {
                    this.setForeground(Color.black);
                }
                if (e.etype != 5) {
                    this.setText("  (rule" + e.etype + ")");
                } else {
                    this.setText("  (addition)");
                }
            } else if (obj instanceof cond) {
                cond c = (cond) obj;
                if (!iselm) {
                    this.setForeground(Color.black);
                }
                int r = c.getRule();
                setText("  (r" + r + ")");
            }
            this.setIcon(null);
        } else if (type == 5) {
            this.setToolTipText("Click to see the detail ");
            this.setIcon(icon);
            this.setText(null);
        } else if (type == 6) {
            cond c = (cond) obj;
            this.setHorizontalTextPosition(JLabel.LEFT);
            this.setIcon(icon);
            this.setText("   DR" + c.getRule());
        } else {
            String s = obj.toString();
            if (type == 0 && s.trim().equals("because")) {
                this.setIcon(icon_bc);
                this.setText(null);
            } else {
                this.setText(s);
                this.setIcon(null);
            }
        }
    }

    public void paint(Graphics g) {
        if (isrender) {
        } else {

            if (type == 5) {
                if (mouse_inside || selected) {
                    g.setColor(Color.pink.brighter());
                    g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
                    g.setColor(Color.pink);
                    g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
                }
            } else if (type != 0) {
                {
                    if (selected) {
                        g.setColor(Color.pink);
                        g.fillRect(0, 0, this.getWidth() - 1,
                                this.getHeight() - 1);
                    }
                    if (mouse_inside || selected) {
                        g.setColor(Color.pink.darker());
                        g.drawRect(0, 0, this.getWidth() - 1,
                                this.getHeight() - 1);
                    }
                }
            }
        }
        super.paint(g);
    }
}
