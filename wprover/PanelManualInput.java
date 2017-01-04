package wprover;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-6-8
 * Time: 21:29:10
 * To change this template use File | Settings | File Templates.
 */
public class PanelManualInput extends JPanel implements ActionListener {


    JLabel label_type;
    JLabel label_ind = new JLabel();
    JComboBox comb_ind;
    JList list_select;
    JButton BOK, BCANCEL, BEDIT, BADD, BNEXT;
    TreeCellOpaqueRender trender;
    JButton BsetAttr;
    JPopupMenu popup;

    public PanelManualInput() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        BsetAttr = new JButton("Set...");

        String[] cStrings = {"Collinear", "Parallel", "Perpendicular", "Eqdistance", "Eqangle"
                             , "eqfull_angle", "congruence", "Similarity", "Eqratio", "O_eqratio", "eqarea"};
        popup = new JPopupMenu();
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JMenuItem t = (JMenuItem) e.getSource();
                label_type.setText(t.getText());
            }

        };
        for (int i = 0; i < cStrings.length; i++) {
            JMenuItem item = new JMenuItem(cStrings[i]);
            item.addActionListener(listener);
            popup.add(item);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        BsetAttr.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(BsetAttr);
        label_type = new JLabel("...") {
            public Dimension getMaximumSize() {
                Dimension d = super.getPreferredSize();
                d.setSize(Integer.MAX_VALUE,d.getHeight());
                return d;
            }
        };
        label_type.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        panel.add(label_type);
        BsetAttr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = BsetAttr.getX() + BsetAttr.getWidth();
                int y = BsetAttr.getY();
                popup.show(BsetAttr, x, y);
            }

        });


        this.add(panel);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(new JScrollPane(panel));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(new JTextField() {
            public Dimension getMaximumSize() {
                Dimension dm = this.getPreferredSize();
                dm.setSize(Integer.MAX_VALUE, dm.getHeight());
                return dm;
            }
        });
        panel1.add(new ColorButtonPanel(20, 20));
        panel.add(panel1);
        String[] simage = {"bc.gif", "ind.gif", "etri.gif", "squa.gif"};

        comb_ind = new JComboBox(simage) {
            public Dimension getMaximumSize() {
                Dimension dm = this.getPreferredSize();
                dm.setSize(Integer.MAX_VALUE, dm.getHeight());
                return dm;
            }
        };

        trender = new TreeCellOpaqueRender();
       // trender.setComponentAt(1, "tri ABC = tri CDE");
        trender.setBorder(new LineBorder(Color.gray, 1));

        comb_ind.setRenderer(new ComboBoxRenderer());
        panel.add(comb_ind);
        panel.add(Box.createVerticalStrut(6));
        panel.add(trender);
        comb_ind.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
              //  trender.setComponentAt(0, GExpert.createImageIcon("images/dtree/" + e.getItem().toString()));
                trender.revalidate();
            }
        });

        JLabel label = new JLabel(" Please select 3 points( 0 points selected)");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        panel.add(new TreeCellOpaqueRender());
        String[] s = {"aaaa", "bbbb", "ccccc"};
        list_select = new JList(s);
        //panel.add(new JScrollPane(list_select));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        BOK = new JButton("Ok");
        BEDIT = new JButton("Edit");
        BCANCEL = new JButton("Cancel");
        BADD = new JButton("Add");
        BNEXT = new JButton("Next");
        BOK.addActionListener(this);
        BEDIT.addActionListener(this);
        BADD.addActionListener(this);
        BNEXT.addActionListener(this);
        panel.add(BOK);
        panel.add(BCANCEL);
        panel.add(BEDIT);
        BEDIT.setEnabled(false);
        BNEXT.setEnabled(false);
        panel.add(BADD);
        panel.add(BNEXT);
        this.add(panel);
    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == BOK) {
            BOK.setEnabled(false);
            BEDIT.setEnabled(true);
            comb_ind.setEditable(false);
            list_select.setEnabled(false);
        } else if (obj == BEDIT) {
            BOK.setEnabled(true);
            BEDIT.setEnabled(false);
            comb_ind.setEditable(true);
            list_select.setEnabled(true);
        } else if (obj == BADD) {
            BNEXT.setEnabled(true);
        } else if (obj == BNEXT) {
        }
    }

    class ComboBoxRenderer extends JLabel
            implements ListCellRenderer {
        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        public Component getListCellRendererComponent(JList list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            //Set the icon and text.  If icon was null, say so.
            Icon icon = GExpert.createImageIcon("images/dtree/" + value);
            setIcon(icon);
            if (icon != null) {
            } else {
            }

            return this;
        }

        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }
}
