package wprover;


import gprover.gib;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.util.Vector;


public class RulerDialog extends JBaseDialog implements ChangeListener, ActionListener, MouseListener {

    private GExpert gxInstance;
    private JTree tree, treef;
    private JTabbedPane pane;

    public RulerDialog(GExpert owner) {
        super(owner.getFrame());

        gxInstance = owner;
//        if (gxInstance != null)
//            gxInstance.addDependentDialog(this);

        this.setTitle("Rules for GDD method");

        Object rootNodes[] = new Object[6];
        int i = 0;
        Vector vrule = RuleList.getAllGDDRules();

        rootNodes[0] = createNameVector("Rulers related to Parallel line", vrule, i, i += 3);
        rootNodes[1] = createNameVector("Rulers related to Perpendicular line", vrule, ++i, i += 3);
        rootNodes[2] = createNameVector("Rulers related to Circle", vrule, ++i, i += 6);
        rootNodes[3] = createNameVector("Rulers related to Angles", vrule, ++i, i += 6);
        rootNodes[4] = createNameVector("Rulers related to Triangle", vrule, ++i, i += 14);
        rootNodes[5] = createNameVector("Other rulers", vrule, ++i, i += 5);

        Vector rootVector = new NamedVector("Root", rootNodes);
        tree = new JTree(rootVector);

        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);

//        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(false);
        tree.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(tree);
        pane = new JTabbedPane(JTabbedPane.BOTTOM);
        pane.addTab("Rules for GDD Method", scrollPane);
        pane.addChangeListener(this);

        Vector vfull = RuleList.getAllFullRules();
        Object rNodes[] = new Object[1];
        rNodes[0] = createNameVector("Full Rules", vfull, 0, 28);
        treef = new JTree(new NamedVector("Root", rNodes));
        treef.setCellRenderer(renderer);
        treef.addMouseListener(this);

        JScrollPane scrollPane1 = new JScrollPane(treef);
        pane.addTab("Rules for Full Angle Method", scrollPane1);


        this.getContentPane().add(pane, BorderLayout.CENTER);
        expandAll();
        this.setSize(600, owner.getHeight());
    }

    public void setSelected(int n) {
        pane.setSelectedIndex(n);
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == pane) {
            this.setTitle(pane.getTitleAt(pane.getSelectedIndex()));
        }
    }


    private Vector createNameVector(String n, Vector vlist, int t1, int t2) {
        CheckBoxNode[] list1 = new CheckBoxNode[t2 - t1 + 1];
        createCheckBox(list1, vlist, t1, t2);
        Vector v1 = new NamedVector(n, list1);
        return v1;
    }

    private void createCheckBox(CheckBoxNode[] list, Vector vlist, int t1, int t2) {
        int index = 0;
        for (int i = t1; i < vlist.size() && i <= t2; i++) {
            grule r = (grule) vlist.get(i);
            int t = r.type;
            list[index++] = new CheckBoxNode(t, t + ".  " + r.discription, true, r);
        }
    }

    private void expandAll() {
        int n = tree.getRowCount();
        for (int i = n - 1; i >= 0; i--)
            tree.expandRow(i);
        n = treef.getRowCount();
        for (int i = n - 1; i >= 0; i--)
            treef.expandRow(i);
    }

    public grule getSelectedRule() {
        DefaultMutableTreeNode nd = null;
        JTree tt = null;

        if (pane.getSelectedIndex() == 0) {
            nd = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            tt = tree;
        } else {
            nd = (DefaultMutableTreeNode) treef.getLastSelectedPathComponent();
            tt = treef;
        }

        if (nd != null) {
            Object obj = nd.getUserObject();
            if (obj instanceof CheckBoxNode) {
                CheckBoxNode ch = (CheckBoxNode) obj;
                grule r = ch.getRule();
                return r;
            }
        }
        return null;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            DefaultMutableTreeNode nd = null;
            JTree tt = null;

            if (pane.getSelectedIndex() == 0) {
                tt = tree;
            } else {
                tt = treef;
            }

            grule r = this.getSelectedRule();
            if (r != null) {
                ppMenu m = new ppMenu(r);
                m.show(tt, e.getX(), e.getY());
            }
        } else {
            if (e.getClickCount() > 1) {
                grule r = this.getSelectedRule();
                this.showRuleDialog(r);
            }
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

    class CheckBoxNodeRenderer implements TreeCellRenderer {
        private JCheckBox leafRenderer = new JCheckBox();
        private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();
        Color selectionBorderColor, selectionForeground, selectionBackground, textForeground, textBackground;
        CheckBoxNode leafNode = null;

        protected JCheckBox getLeafRenderer() {
            return leafRenderer;
        }

        protected CheckBoxNode getLeafNode() {
            return leafNode;
        }

        public CheckBoxNodeRenderer() {
            Font fontValue;
            fontValue = UIManager.getFont("Tree.font");
            if (fontValue != null) {
                leafRenderer.setFont(fontValue);
            }
            Boolean booleanValue = (Boolean) UIManager.get("Tree.drawsFocusBorderAroundIcon");
            leafRenderer.setFocusPainted((booleanValue != null) && (booleanValue.booleanValue()));


            selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
            selectionForeground = UIManager.getColor("Tree.selectionForeground");
            selectionBackground = UIManager.getColor("Tree.selectionBackground");
            textForeground = UIManager.getColor("Tree.textForeground");
            textBackground = UIManager.getColor("Tree.textBackground");
            leafRenderer.setBorder(null);
            leafRenderer.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                }
            });
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean selected, boolean expanded, boolean leaf, int row,
                                                      boolean hasFocus) {

            Component returnValue;
            if (leaf) {
                String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, false);
                leafRenderer.setText(stringValue);
                leafRenderer.setSelected(false);
                leafRenderer.setEnabled(tree.isEnabled());

                if (selected) {
                    leafRenderer.setForeground(selectionForeground);
                    leafRenderer.setBackground(selectionBackground);
                } else {
                    leafRenderer.setForeground(textForeground);
                    leafRenderer.setBackground(textBackground);
                }

                if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
                    Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
                    if (userObject instanceof CheckBoxNode) {
                        CheckBoxNode node = (CheckBoxNode) userObject;
                        leafRenderer.setText(node.getText());
                        leafRenderer.setSelected(node.isSelected());
                        leafNode = node;
                    }
                }
                returnValue = leafRenderer;
            } else {
                returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree,
                        value, selected, expanded, leaf, row, hasFocus);
            }
            return returnValue;
        }
    }

    class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

        ChangeEvent changeEvent = null;

        JTree tree;

        public CheckBoxNodeEditor(JTree tree) {
            this.tree = tree;
//            renderer.leafRenderer.addChangeListener();
            renderer.leafRenderer.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int n = 0;
                    if (renderer.leafNode != null)
                        renderer.leafNode.updateValue(renderer.leafRenderer.isSelected());
                }

            });
        }

        public Object getCellEditorValue() {
            JCheckBox checkbox = renderer.getLeafRenderer();
            CheckBoxNode nd = renderer.getLeafNode();
            if (nd != null && checkbox != null)
                nd.setSelected(checkbox.isSelected());
            return nd;
        }

        public boolean isCellEditable(EventObject event) {
            boolean returnValue = false;
            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                TreePath path = tree.getPathForLocation(mouseEvent.getX(),
                        mouseEvent.getY());
                if (path != null) {
                    Object node = path.getLastPathComponent();
                    if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                        Object userObject = treeNode.getUserObject();
                        returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
                    }
                }
            }
            return returnValue;
        }

        public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                    boolean selected, boolean expanded, boolean leaf, int row) {

            Component editor = renderer.getTreeCellRendererComponent(tree, value,
                    true, expanded, leaf, row, true);

            // editor always selected / focused
            ItemListener itemListener = new ItemListener() {
                public void itemStateChanged(ItemEvent itemEvent) {
                    if (stopCellEditing()) {
                        fireEditingStopped();
                    }
                }
            };
            if (editor instanceof JCheckBox) {
                ((JCheckBox) editor).addItemListener(itemListener);
            }

            return editor;
        }
    }

    class CheckBoxNode {

        private String text;
        private boolean selected;
        private int v;
        private grule rule;

        public CheckBoxNode(int n, String text, boolean selected, grule rl) {
            this.text = text;
            this.selected = selected;
            this.v = n;
            rule = rl;
        }

        public grule getRule() {
            return rule;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean newValue) {
            selected = newValue;
        }

        public String getText() {
            return text;
        }

        public void setText(String newValue) {
            text = newValue;
        }

        public void updateValue(boolean r) {
            selected = r;
            gib.setValue(v, r);

        }

        public String toString() {
            return getClass().getName() + "[" + text + "/" + selected + "]";
        }
    }

    class NamedVector extends Vector {
        String name;

        public NamedVector(String name) {
            this.name = name;
        }

        public NamedVector(String name, Object elements[]) {
            this.name = name;
            for (int i = 0, n = elements.length; i < n; i++) {
                add(elements[i]);
            }
        }

        public String toString() {
            return name;
        }
    }


    public void showRuleDialog(grule r) {
        if (r != null) {
            if (r.isGDDRule()) {
                RuleListDialog dlg = new RuleListDialog(gxInstance);
                if (dlg.loadRule(0, r.type))
                    dlg.setVisible(true);
            } else if (r.isFullRule()) {
                RuleListDialog dlg = new RuleListDialog(gxInstance);
                if (dlg.loadRule(1, r.type))
                    dlg.setVisible(true);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        if (cm.equals("Show Detail")) {
            JMenuItem m1 = (JMenuItem) e.getSource();
            ppMenu m = (ppMenu) m1.getParent();
            grule r = m.getRule();
            showRuleDialog(r);
        }

    }

    class ppMenu extends JPopupMenu {

        private grule rule;

        public grule getRule() {
            return rule;
        }

        public ppMenu(grule r) {
            super();
            rule = r;

            JMenuItem it = new JMenuItem("Show Detail");
            it.addActionListener(RulerDialog.this);
            add(it);
            addSeparator();
            it = new JMenuItem("Enable");
            it.addActionListener(RulerDialog.this);
            it.setEnabled(false);
            add(it);
            it = new JMenuItem("Disable");
            it.addActionListener(RulerDialog.this);
            it.setEnabled(false);
            add(it);
            addSeparator();
            it = new JMenuItem("Help..");
            it.addActionListener(RulerDialog.this);
            it.setEnabled(false);
            add(it);
        }
    }
}
