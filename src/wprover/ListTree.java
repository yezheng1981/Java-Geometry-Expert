package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-12
 * Time: 11:06:53
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.net.URL;

public class ListTree extends JTabbedPane
        implements ActionListener, MouseListener, ListSelectionListener {

    public GExpert gxInstance;
    public Vector undolist;
    private JList list, listx;
    private DefaultListModel model, modelx;
    private CProperty prop;


    public ListTree(GExpert gx) {
        super(JTabbedPane.BOTTOM);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
        gxInstance = gx;
        undolist = new Vector();
        model = new DefaultListModel();
        list = new JList(model);
        list.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        pane1.add(new JScrollPane(list));
//        list.setCellRenderer();

        ListCellRenderer rener = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                DefaultListCellRenderer d = (DefaultListCellRenderer)
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                d.setText((1 + index) + ". \t" + value.toString());
                return d;
            }

        };
        list.setCellRenderer(rener);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        this.addTab(GExpert.getLanguage(157, "Construct History"), pane1);

        modelx = new DefaultListModel();
        listx = new JList(modelx) {
            public Dimension getPreferredSize() {
                Dimension dm = super.getPreferredSize();
                double w = dm.getWidth();
                if (w < 100)
                    w = 100;
                dm.setSize(w, dm.getHeight());
                return dm;
            }
        };
        listx.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        ListCellRenderer rener1 = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                DefaultListCellRenderer d = (DefaultListCellRenderer)
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                CClass c = (CClass) value;

                d.setText(c.getDescription());
                return d;
            }

        };

        prop = new CProperty(gx.d, gx.getLan());
        prop.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JSplitPane pane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pane2.setLeftComponent(new JScrollPane(listx));
        listx.setCellRenderer(rener1);
        pane2.setRightComponent(prop);
        listx.addListSelectionListener(this);
        this.addTab(GExpert.getLanguage(3115, "Objects"), pane2);
    }


    public void actionPerformed(ActionEvent e) {
    }


    /**
     * Remove the currently selected node.
     */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list)
            gxInstance.dp.setUndoStructForDisPlay((UndoStruct) (list.getSelectedValue()), true);
        else {
            CClass c = (CClass) listx.getSelectedValue();
            if (c != null) {
                prop.SetPanelType(c);
                gxInstance.dp.setObjectListForFlash(c);
            }
        }

    }


    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {


        } else {

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

    public void clearAllTrees() {


    }

    public void reload() {
        undolist.clear();
        model.removeAllElements();
        modelx.removeAllElements();

        drawProcess dp = gxInstance.dp;

        Vector v = dp.undolist;
        undolist.addAll(v);
        for (int i = 0; i < undolist.size(); i++)
            model.addElement(undolist.get(i));

        Vector vx = dp.getAllSolidObj();

        for (int i = 0; i < vx.size(); i++) {
            Object o = vx.get(i);
            if (o != null)
                modelx.addElement(o);
        }

    }

}