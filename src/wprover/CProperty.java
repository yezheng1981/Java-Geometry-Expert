package wprover;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.net.URL;
import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-2-10
 * Time: 19:38:34
 * To change this template use File | Settings | File Templates.
 */
public class CProperty extends JPanel implements ActionListener {

    private DPanel d;
    private Language lan;

    private JLabel label = new JLabel("No Selected");
    private Panel_CS pcs;
    private Panel_Point ppt;
    private Panel_Line pln;
    private Panel_Circle pcir;
    private Panel_Polygon poly;
    private Panel_Angle pangle;
    private Panel_eqmark peqmk;
    private Panel_trace ptrs;
    private Panel_text ptex;
    private Panel_arrow parrow;


    public CProperty(DPanel dd, Language lan) {
        d = dd;
        this.lan = lan;

        pcs = new Panel_CS(d);
        ppt = new Panel_Point(d);
        pln = new Panel_Line(d);
        pcir = new Panel_Circle(d);
        poly = new Panel_Polygon(d);
        pangle = new Panel_Angle(d);
        peqmk = new Panel_eqmark(d);
        ptrs = new Panel_trace(d);
        ptex = new Panel_text(d);
        parrow = new Panel_arrow(d);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.add(label);
    }

    public String getLanguage(int n, String s) {
        if (lan == null)
            return s;

        String s1 = lan.getString(n);
        if (s1 != null && s1.length() > 0)
            return s1;
        return s;
    }


    public void SetPanelType(CClass obj) {
        if (obj == null)
            return;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        this.removeAll();
        label.setText(obj.getDescription());
        this.add(label);
        int t = obj.get_type();
        pcs.setColorOnly(true);

        switch (t) {
            case CClass.POINT: {
                CPoint p = (CPoint) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                ppt.setVariable(p);
                this.add(ppt);
                break;
            }
            case CClass.LINE: {
                CLine line = (CLine) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                pln.setVariable(line);
                this.add(pln);
                break;
            }
            case CClass.CIRCLE: {
                Circle c = (Circle) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                pcir.setVariable(c);
                this.add(pcir);
                break;
            }
            case CClass.POLYGON: {
                CPolygon cp = (CPolygon) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                poly.setVariable(cp);
                this.add(poly);
                break;
            }
            case CClass.ANGLE: {
                pcs.setVariable(obj);
                this.add(pcs);
                CAngle ca = (CAngle) obj;
                pangle.setVariable(ca);
                this.add(pangle);
                break;
            }
            case CClass.EQMARK: {
                Cedmark m = (Cedmark) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                peqmk.setVariable(m);
                this.add(peqmk);
                break;
            }
            case CClass.TRACE: {
                CTrace m = (CTrace) obj;
                pcs.setVariable(obj);
                this.add(pcs);
                ptrs.setVariable(m);
                this.add(ptrs);
                break;
            }
            case CClass.TEXT: {
                CText tx = (CText) obj;
                pcs.setVariable(obj);
                pcs.setColorOnly(false);
                this.add(pcs);
                if (tx.getType() == CText.VALUE_TEXT) {
                    ptex.setVariable(tx);
                    this.add(ptex);
                }
                break;
            }
            case CClass.ARROW: {
                CArrow ar = (CArrow) obj;
                pcs.setVariable(ar);
                this.add(pcs);
                parrow.setVariable(ar);
                this.add(parrow);
                break;
            }
            default:
                pcs.setVariable(obj);
                this.add(pcs);
                break;
        }
        this.revalidate();
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void setPropertyChanged() {

    }

    public static JButton CreateIconButton(String imageName) {
        String imgLocation = "images/" + imageName;
        URL imageURL = GExpert.class.getResource(imgLocation);

        JButton button = new JButton();


        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL));
        } else {                                     //no image found
            button.setText(imageName);
        }
        button.setMaximumSize(new Dimension(20, 18));
        button.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        return button;
    }

    public static JTable createTable(Object obj1, Object obj2) {
        Object data[][] = {{obj1, obj2}};
        String[] sname = {"", ""};
        JTable tb = new JTable(data, sname);

        tb.setRowHeight(20);
        tb.setPreferredSize(new Dimension(70, 20));
        TableColumn t1 = tb.getColumnModel().getColumn(0);
        t1.setPreferredWidth(20);

        TableColumn cn = tb.getColumnModel().getColumn(1);
        cn.setPreferredWidth(50);
        tb.setSelectionBackground(Color.lightGray);
        tb.setSelectionForeground(Color.black);
        return tb;
    }


    class Panel_CS extends JPanel implements ActionListener {

        DPanel d;
        JComboBox color;
        JComboBox line_type;
        JComboBox line_width;
        ColorComboRender color_render;
        ColorComboRender line_type_render;
        ColorComboRender line_width_render;
        CClass current_data = null;

        JTable tb1, tc1, td1;

        public void setColorOnly(boolean r) {
            tc1.setVisible(r);
            td1.setVisible(r);
        }

        public Panel_CS(DPanel dd) {
            d = dd;


            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(
                    new TitledBorder(getLanguage(3207, "Basic")),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));

            color = CCoBox.CreateAInstance();
            color.addActionListener(this);


            tb1 = CProperty.createTable(getLanguage(350, "Color"), "");
            TableColumn cn = tb1.getColumnModel().getColumn(1);
            color_render = new ColorComboRender(0, 100, 20);
            cn.setCellRenderer(color_render);
            cn.setCellEditor(new DefaultCellEditor(color));
            add(tb1);

            Integer[] array1 = new Integer[drawData.getDashCounter()];
            for (int i = 0; i < drawData.getDashCounter(); i++)
                array1[i] = new Integer(i);
            line_type = new JComboBox(array1);
            line_type.setMaximumRowCount(20);
            ColorComboRender render1 = new ColorComboRender(2, 100, 20);
            render1.setPreferredSize(new Dimension(50, 20));
            line_type.setRenderer(render1);
            line_type.addActionListener(this);

            tc1 = CProperty.createTable(getLanguage(730, "Type"), "");
            TableColumn cn1 = tc1.getColumnModel().getColumn(1);
            line_type_render = new ColorComboRender(2, 100, 20);
            cn1.setCellRenderer(line_type_render);
            cn1.setCellEditor(new DefaultCellEditor(line_type));
            add(tc1);


            Integer[] array2 = new Integer[20];
            for (int i = 0; i < 20; i++)
                array2[i] = new Integer(i);

            line_width = new JComboBox(array2);
            line_width.setMaximumRowCount(20);
            ColorComboRender render2 = new ColorComboRender(1, 100, 20);
            render2.setPreferredSize(new Dimension(50, 20));
            line_width.setRenderer(render2);
            line_width.addActionListener(this);


            td1 = CProperty.createTable(getLanguage(731, "Width"), "");
            TableColumn cn2 = td1.getColumnModel().getColumn(1);
            line_width_render = new ColorComboRender(1, 100, 20);
            cn2.setCellRenderer(line_width_render);
            cn2.setCellEditor(new DefaultCellEditor(line_width));
            add(td1);
        }

        public void setVariable(CClass dp) {
            current_data = null;
            if (dp == null) {
                color.setSelectedIndex(0);
                line_type.setSelectedIndex(0);
                line_width.setSelectedIndex(0);
                return;
            }


            int ci = dp.m_color;
            ((CCoBox) color).setSelectedIndex(ci);
            line_type.setSelectedIndex(dp.m_dash);
            line_width.setSelectedIndex(dp.m_width);
            current_data = dp;

        }

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == color) {

                int index = color.getSelectedIndex();
                if (index >= 0) {
                    if (index == color.getItemCount() - 1) {
                        Color newColor = JColorChooser.showDialog(this,
                                "Choose Color",
                                Color.white);
                        if (newColor != null) {
                            int id = drawData.addColor(newColor);
                            CCoBox.reGenerateAll();
                            color.setSelectedIndex(id - 1);
                            index = id - 1;

                            if (current_data != null)
                                current_data.setColor(index);
                            color_render.index = index;
                        }
                    } else {
                        if (current_data != null)
                            current_data.setColor(index);
                        color_render.index = index;
                    }

                }
            } else if (obj == line_type) {
                int index = line_type.getSelectedIndex();
                if (index >= 0) {
                    line_type_render.index = index;
                    if (current_data != null)
                        current_data.m_dash = (index);
                }
            } else if (obj == line_width) {
                int index = line_width.getSelectedIndex();
                if (index >= 0) {
                    line_width_render.index = index;
                    if (current_data != null)
                        current_data.m_width = (index);
                }
            }
            d.repaint();

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    class Panel_Line extends JPanel implements TableModelListener, ActionListener, ChangeListener {
        DPanel d;
        CLine line;
        JTable table;
        TitledBorder border;
        JButton button1, button2, button3;
        JPopupMenu popup;
        JSlider slider;

        public Panel_Line(DPanel dd) {
            d = dd;

            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder(getLanguage(40, "Line"));

            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));
            table = new JTable(new LineTableModel());
            table.getModel().addTableModelListener(this);
            this.add(table);

            button1 = CProperty.CreateIconButton("line_two_end.gif");
            button2 = CProperty.CreateIconButton("line_more_end.gif");
            button3 = CProperty.CreateIconButton("line_no_end.gif");
            button1.addActionListener(this);
            button2.addActionListener(this);
            button3.addActionListener(this);

            JPanel panel = new JPanel();
            panel.setMaximumSize(new Dimension(200, 50));
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            JLabel label = new JLabel(getLanguage(730, "Type") + ":   ");
            panel.add(label);
            panel.add(Box.createHorizontalStrut(10));
            panel.add(button1);
            panel.add(Box.createHorizontalStrut(10));
            panel.add(button2);
            panel.add(Box.createHorizontalStrut(10));
            panel.add(button3);
            button1.setBackground(Color.white);
            button2.setBackground(Color.white);
            button3.setBackground(Color.white);
            popup = new JPopupMenu();
            popup.add(slider = new JSlider(0, 200));
            slider.setMajorTickSpacing(50);
            slider.setPaintLabels(true);
            slider.addChangeListener(this);
            this.add(panel);

        }

        public void ClearButtonBackGround() {
            button1.setBackground(Color.white);
            button2.setBackground(Color.white);
            button3.setBackground(Color.white);

        }

        public void stateChanged(ChangeEvent e) {
            int n = slider.getValue();
            if (line != null)
                line.setExtent(n);
            d.repaint();
        }

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
        }

        public void actionPerformed(ActionEvent e) {

            Object src = e.getSource();
            ClearButtonBackGround();

            if (src instanceof JButton) {
                if (line == null)
                    return;
                if (src == button1) {
                    line.ext_type = 0;
                    button1.setBackground(Color.lightGray);
                } else if (src == button2) {
                    line.ext_type = 1;
                    button2.setBackground(Color.lightGray);
                    popup.show(button2, 0, button2.getHeight() + 2);
                } else if (src == button3) {
                    line.ext_type = 2;
                    button3.setBackground(Color.lightGray);
                }
            }
            d.repaint();
        }

        public void setVariable(CLine line) {
            table.setValueAt(line.m_name, 0, 1);
            border.setTitle(line.TypeString());
            CPoint[] pl = line.getMaxMinPoint();
            table.setValueAt(line.getAllPointName(), 1, 1);
            if (pl != null) {
                table.setValueAt(new Double(round(pl[0].getx())), 2, 1);
                table.setValueAt(new Double(round(pl[0].gety())), 3, 1);
                table.setValueAt(new Double(round(pl[1].getx())), 4, 1);
                table.setValueAt(new Double(round(pl[1].gety())), 5, 1);
            }
            ClearButtonBackGround();
            if (line.ext_type == 0)
                button1.setBackground(Color.lightGray);
            else if (line.ext_type == 1)
                button2.setBackground(Color.lightGray);
            else
                button3.setBackground(Color.lightGray);
            this.line = line;
            slider.setValue(line.getExtent());
        }


    }

    class Panel_Circle extends JPanel implements TableModelListener {
        DPanel d;

        JTable table;
        Circle circle = null;
        TitledBorder border;

        public Panel_Circle(DPanel dd) {
            d = dd;


            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder(getLanguage(3106, "Circle"));
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));

            table = new JTable(new CircleTableModel());
            TableColumn t1 = table.getColumnModel().getColumn(0);
            t1.setPreferredWidth(20);
            TableColumn cn = table.getColumnModel().getColumn(1);
            cn.setPreferredWidth(50);

            table.getModel().addTableModelListener(this);
            this.add(table);
        }

        public void tableChanged(TableModelEvent e) {
            if (circle == null) return;

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            if (row == 0)
                circle.m_name = data.toString();
            else if (row == 1) {
            } else if (row == 2) {
            }
            d.repaint();
        }

        public void setVariable(Circle c) {
            circle = c;
            border.setTitle(c.TypeString());
            table.setValueAt(circle.m_name, 0, 1);
            table.setValueAt(circle.getAllPointName(), 1, 1);
            table.setValueAt(circle.o.m_name, 2, 1);

            table.setValueAt(new Double(round(circle.o.getx())), 3, 1);
            table.setValueAt(new Double(round(circle.o.gety())), 4, 1);
            table.setValueAt(new Double(round(circle.getRadius())), 5, 1);
        }

    }

    class Panel_Point extends JPanel implements TableModelListener {
        DPanel d;

        JTable table;

        CPoint pt = null;
        TitledBorder border;

        public Panel_Point(DPanel dd) {
            d = dd;


            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder(getLanguage(33, "Point"));

            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));

//            JComboBox comboBox = new JComboBox();
//        for (int i = 0; i < type.length; i++)
//            comboBox.addItem(type[i]);
//
//        cn1.setCellEditor(new DefaultCellEditor(comboBox));

            table = new JTable(new PointTableModel());
            TableColumn t1 = table.getColumnModel().getColumn(0);
            t1.setPreferredWidth(20);
            TableColumn cn = table.getColumnModel().getColumn(1);
            cn.setPreferredWidth(50);

            table.getModel().addTableModelListener(this);
            this.add(table);
        }

        public void tableChanged(TableModelEvent e) {
            if (pt == null) return;

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            if (data == null) return;

            if (row == 0)
                pt.m_name = data.toString();
            else if (row == 1) {
                Integer r = new Integer(data.toString());
                pt.setRadius(r.intValue());
            } else if (row == 2) {
                Double d = new Double(data.toString());
                pt.setXY(d.doubleValue(), pt.gety());
            } else if (row == 3) {
                Double d = new Double(data.toString());
                pt.setXY(pt.getx(), d.doubleValue());
            } else if (row == 4) {
                pt.setFreezed(Boolean.parseBoolean(data.toString()));
            }
            d.repaint();


        }

        public void setVariable(CPoint p) {
            pt = p;
            table.setValueAt(p.m_name, 0, 1);
            table.setValueAt(new Integer(p.getRadiusValue()), 1, 1);
            table.setValueAt(new Double(round(p.getx())), 2, 1);
            table.setValueAt(new Double(round(p.gety())), 3, 1);
            table.setValueAt(pt.isFreezed(), 4, 1);
            border.setTitle(p.TypeString());

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    class Panel_Angle extends JPanel implements ActionListener, TableModelListener {
        DPanel d;
        JComboBox bcolor;
        TitledBorder border;
        JTable tb1, tb2, tb3, tbt;
        CAngle angle;

        String[] type = {"Without Arrow", "With Arrow", "Multiple Arc", "Fill"};
        String[] text_type = {"Default", "No Text", "Value", "Name", "Name With Value"};

        ColorComboRender color_render;

        public Panel_Angle(DPanel dd) {
            d = dd;
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder(getLanguage(40, "Line"));

            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));
            bcolor = CCoBox.CreateAInstance();
            bcolor.addActionListener(this);

            tb1 = CProperty.createTable(getLanguage(730, "Type"), "");
            TableColumn cn1 = tb1.getColumnModel().getColumn(1);
            JComboBox comboBox = new JComboBox();

            for (int i = 0; i < type.length; i++)
                comboBox.addItem(getLanguage(383 + i, type[i]));

            cn1.setCellEditor(new DefaultCellEditor(comboBox));
            DefaultTableCellRenderer renderer =
                    new DefaultTableCellRenderer();
            // renderer.setToolTipText("Click to select the angle type.");
            cn1.setCellRenderer(renderer);

            tbt = CProperty.createTable(getLanguage(363, "Angle Text"), "");
            TableColumn cnt = tbt.getColumnModel().getColumn(1);
            comboBox = new JComboBox();
            for (int i = 0; i < text_type.length; i++)
                comboBox.addItem(text_type[i]);

            cnt.setCellEditor(new DefaultCellEditor(comboBox));
            renderer = new DefaultTableCellRenderer();
            //  renderer.setToolTipText("Click to select the angle text type.");
            cnt.setCellRenderer(renderer);

            tb2 = CProperty.createTable(getLanguage(350, "Color"), "");
            TableColumn cn = tb2.getColumnModel().getColumn(1);
            ColorComboRender cr = new ColorComboRender(0, 100, 20);
            cn.setCellRenderer(cr);
            cn.setCellEditor(new DefaultCellEditor(bcolor));
            color_render = new ColorComboRender(0, 100, 20);
            cn.setCellRenderer(color_render);

            tb3 = CProperty.createTable(getLanguage(390, "Arc Num"), "");

            tb1.getModel().addTableModelListener(this);
            tb2.getModel().addTableModelListener(this);
            tb3.getModel().addTableModelListener(this);
            tbt.getModel().addTableModelListener(this);
            this.add(tb1);
            this.add(tb2);
            this.add(tb3);
            this.add(tbt);
        }

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == bcolor) {
                int n = bcolor.getSelectedIndex();
                if (angle != null)
                    angle.setValue1(n);
                color_render.index = n;
            }
        }

        public void tableChanged(TableModelEvent e) {
            Object obj = e.getSource();
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);

            if (obj == tb1.getModel()) {
                int n = 0;
                for (int i = 0; i < type.length; i++)
                    if (data.equals(type[i])) {
                        n = i;
                        break;
                    }
                tb2.setValueAt(angle.getValue1(), 0, 1);
                tb3.setValueAt(angle.getValue1(), 0, 1);
                color_render.index = angle.getValue1();
                setAgTabel(n);
                angle.setAngleType(n);
            } else if (obj == tb3.getModel()) {
                try {
                    int n = Integer.parseInt(data.toString());
                    angle.setValue1(n);
                } catch (NumberFormatException ee) {
                    ee.printStackTrace();
                }

            } else if (obj == tbt.getModel()) {
                int n = 0;
                for (int i = 0; i < text_type.length; i++)
                    if (data.equals(text_type[i])) {
                        n = i;
                        break;
                    }
                angle.setTextType(n - 1);
            }

            d.repaint();
        }

        public void setVariable(CAngle ag) {
            angle = ag;
            bcolor.setSelectedIndex(ag.getValue1());
            int n = ag.getAngleType();
            int tn = ag.getTextType() + 1;
            tbt.setValueAt(text_type[tn], 0, 1);
            tb1.setValueAt(type[n], 0, 1);
            tb2.setValueAt(angle.getValue1(), 0, 1);
            tb3.setValueAt(angle.getValue1(), 0, 1);
            color_render.index = angle.getValue1();
            setAgTabel(n);
            border.setTitle(ag.TypeString());
        }

        public void setTableAg(int n) {

        }

        public void setAgTabel(int n) {

            if (n != 3)
                tb2.setVisible(false);
            else {
                tb2.setVisible(true);
            }
            if (n != 2)
                tb3.setVisible(false);
            else {
                tb3.setVisible(true);
            }
        }
    }


    class Panel_Base extends JPanel {
        protected DPanel d;
        protected TitledBorder border;

        public Panel_Base(DPanel d) {
            this.d = d;
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder("");
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)), this.getBorder()));
        }

        public void setBorder(CClass c) {
            border.setTitle(c.getDescription());
        }
    }

    class Panel_text extends Panel_Base implements TableModelListener {
        CText tx;
        JTable table, table1, table2;
        TableModel model1, model, model2;

        public Panel_text(DPanel dd) {
            super(dd);

            Object[][] obj =
                    {
                            {getLanguage(365, "Label"), new String("")}
                    };


            model = new propertyTableModel(obj);

            table = new JTable(model);
            Object[][] obj1 =
                    {
                            {getLanguage(348, "Show Label"), Boolean.TRUE},
                            {getLanguage(349, "Show Text"), Boolean.TRUE},

                    };

            model1 = new propertyTableModel(obj1);
            table1 = new JTable(model1);

            Object[][] obj2 =
                    {
                            {getLanguage(347, "Significant Digit"), new Integer(0)}

                    };

            model2 = new propertyTableModel(obj2);
            table2 = new JTable(model2);

            model2.addTableModelListener(this);
            model.addTableModelListener(this);
            model1.addTableModelListener(this);
            this.add(table);
            this.add(table1);
            this.add(table2);
        }

        public void tableChanged(TableModelEvent e) {
            if (tx == null) return;
            Object src = e.getSource();

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel m = (TableModel) e.getSource();
            Object data = m.getValueAt(row, column);
            if (data == null) return;

            if (row == 0 && src == model) {
                tx.m_name = data.toString();
            } else if (src == model1) {
                String s = data.toString();
                boolean r = Boolean.parseBoolean(s);
                tx.setWidth(0); // 0: default (text) 1.label only . 2.label + text.
                Object data1 = model1.getValueAt(0, 1);
                Object data2 = model1.getValueAt(1, 1);
                boolean b1 = Boolean.parseBoolean(data1.toString());
                boolean b2 = Boolean.parseBoolean(data2.toString());
                int w = 0;

                if (b1) {
                    if (b2)
                        w = 3;
                    else w = 1;
                } else {
                    if (b2)
                        w = 2;
                    else w = 0;
                }
                tx.m_width = w;
            } else {
                String s1 = model2.getValueAt(0, 1).toString();
                int n = Integer.parseInt(s1);
                tx.m_dash = n;
            }
            d.repaintAndCalculate();
        }

        public void setVariable(CText t) {
            tx = t;
            String s = tx.m_name;
            if (s == null)
                s = "";
            table.setValueAt(s, 0, 1);
            int w = tx.m_width;
            table2.setValueAt(tx.m_dash, 0, 1);
            if (w == 0) {
                table1.setValueAt(false, 0, 1);
                table1.setValueAt(false, 1, 1);
            } else if (w == 1) {
                table1.setValueAt(true, 0, 1);
                table1.setValueAt(false, 1, 1);
            } else if (w == 2) {
                table1.setValueAt(false, 0, 1);
                table1.setValueAt(true, 1, 1);
            } else {
                table1.setValueAt(true, 0, 1);
                table1.setValueAt(true, 1, 1);
            }
            d.repaint();
        }

    }

    class Panel_trace extends Panel_Base implements TableModelListener {
        CTrace ts;
        JTable table, table1;
        TableModel model1, model;

        public Panel_trace(DPanel dd) {
            super(dd);

            Object[][] obj =
                    {
                            {getLanguage(450, "Point number"), new Integer("1")},
                    };


            model = new propertyTableModel(obj);

            table = new JTable(model);
            Object[][] obj1 =
                    {
                            {getLanguage(451, "Draw Line"), Boolean.TRUE},
                    };

            model1 = new propertyTableModel(obj1);
            table1 = new JTable(model1);
            model.addTableModelListener(this);
            model1.addTableModelListener(this);
            this.add(table);
            this.add(table1);
        }

        public void tableChanged(TableModelEvent e) {
            if (ts == null) return;
            Object src = e.getSource();

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel m = (TableModel) e.getSource();
            Object data = m.getValueAt(row, column);

            if (row == 0 && src == model) {
                int n = Integer.parseInt(data.toString());
                ts.setNumPts(n);
            } else if (row == 0 && src == model1) {
                ts.setDLns(Boolean.parseBoolean(data.toString()));
            }
            d.repaintAndCalculate();
        }

        public void setVariable(CTrace tc) {
            ts = tc;
            table.setValueAt((tc.getPointSize()), 0, 1);
            table1.setValueAt(tc.isDrawLines(), 0, 1);
            d.repaint();
        }
    }

    class Panel_eqmark extends Panel_Base implements TableModelListener {
        Cedmark mk;
        JTable table;

        public Panel_eqmark(DPanel dd) {
            super(dd);

            Object[][] obj =
                    {
                            {getLanguage(452, "Num"), new Integer("1")},
                            {getLanguage(247, "Length"), new Integer("1")}
                    };

            table = new JTable(new propertyTableModel(obj));
            table.getModel().addTableModelListener(this);
            this.add(table);
        }

        public void tableChanged(TableModelEvent e) {
            if (mk == null) return;

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            if (row == 0) {
                mk.setNum(Integer.parseInt(data.toString()));
            } else if (row == 1) {
                mk.setLength(Integer.parseInt(data.toString()));
            }
            d.repaint();
        }

        public void setVariable(Cedmark mc) {
            mk = mc;
            table.setValueAt((mk.getNum()), 0, 1);
            table.setValueAt((mk.getLength()), 1, 1);
        }
    }

    class Panel_Polygon extends JPanel implements TableModelListener {

        DPanel d;
        JTable table, table1;
        CPolygon polygon = null;
        String[] type = {
                "Fill",
                "Grid",
                "Vertical Line",
                "Horizontal Line"
        };
        TitledBorder border;
        JComboBox comboBox;

        public Panel_Polygon(DPanel dd) {

            d = dd;

            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            border = new TitledBorder(getLanguage(70, "Polygon"));

            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)),
                    this.getBorder()));


            JTable tb1 = CProperty.createTable(getLanguage(730, "Type"), "");
            TableColumn cn1 = tb1.getColumnModel().getColumn(1);
            comboBox = new JComboBox();
            for (int i = 0; i < type.length; i++)
                comboBox.addItem(getLanguage(4000 + i, type[i]));

            cn1.setCellEditor(new DefaultCellEditor(comboBox));
            DefaultTableCellRenderer renderer =
                    new DefaultTableCellRenderer();
//            renderer.setToolTipText("Click to select the fill type.");
            cn1.setCellRenderer(renderer);
            this.add(tb1);
            table1 = tb1;
            table1.getModel().addTableModelListener(this);

            table = new JTable(new PolygonTableModel());
            TableColumn t1 = table.getColumnModel().getColumn(0);
            t1.setPreferredWidth(20);
            TableColumn cn = table.getColumnModel().getColumn(1);
            cn.setPreferredWidth(50);

            table.getModel().addTableModelListener(this);
            this.add(table);
        }

        public void tableChanged(TableModelEvent e) {
            if (polygon == null) return;

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            if (row == 0 && model == table1.getModel()) {
                int t = -1;
//                String s = data.toString();
//                if (s.compareTo("Fill") == 0)
//                    t = 0;
//                else if (s.compareTo("Grid") == 0)
//                    t = 1;
//                else if (s.compareTo("Line") == 0)
//                    t = 2;
//                else if (s.compareTo("V Line") == 0)
//                    t = 3;
                t = comboBox.getSelectedIndex();
                if (t >= 0)
                    polygon.setType(t);
            } else if (row == 0 && model == table.getModel()) {
                polygon.setGrid(((Integer) data).intValue());
            } else if (row == 1 && model == table.getModel()) {
                Integer d = new Integer(data.toString());
                polygon.setSlope(d.intValue());
            }
            d.repaint();


        }

        public void setVariable(CPolygon c) {
            polygon = c;

            table1.setValueAt(type[polygon.getType()], 0, 1);

            table.setValueAt(new Integer(polygon.grid), 0, 1);
            table.setValueAt(new Integer(polygon.slope), 1, 1);
        }

    }

    class Panel_arrow extends Panel_Base implements TableModelListener {
        CArrow arrow;
        JTable table;

        public Panel_arrow(DPanel dd) {
            super(dd);

            Object[][] obj =
                    {
                            {getLanguage(452, "Angle"), new Integer("30")},
                            {getLanguage(247, "Length"), new Integer("1")}
                    };

            table = new JTable(new propertyTableModel(obj));
            table.getModel().addTableModelListener(this);
            this.add(table);
        }

        public void tableChanged(TableModelEvent e) {
            if (arrow == null) return;

            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            if (row == 0) {
                arrow.angle = (Integer.parseInt(data.toString()));
            } else if (row == 1) {
                arrow.length = (Integer.parseInt(data.toString()));
            }
            d.repaint();
        }

        public void setVariable(CArrow mc) {
            arrow = mc;
            table.setValueAt(arrow.angle, 0, 1);
            table.setValueAt(arrow.length, 1, 1);
        }
    }


    class DefaultTableModel extends AbstractTableModel {
        private Object[][] data;

        public DefaultTableModel(Object[][] o) {
            data = o;
        }

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return "";
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class propertyTableModel extends AbstractTableModel {
        private String[] names = {"", ""};
        private Object[][] data = null;

        public propertyTableModel(Object[][] d) {
            data = d;
        }

        public int getColumnCount() {
            return names.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return names[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            Object o = getValueAt(0, c);
            if (o != null)
                return o.getClass();
            else
                return null;
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }

    class PointTableModel extends AbstractTableModel {
        private String[] names = {"", ""};
        private Object[][] data = {
                {getLanguage(245, "Name"), new String()},
                {getLanguage(4004, "Radius"), new Integer(-1)},
                {getLanguage(4005, "X Coordinate"), new Double(0)},
                {getLanguage(4006, "Y Coordinate"), new Double(0)},
                {getLanguage(4007, "Freezed"), new Boolean(false)}
        };

        public int getColumnCount() {
            return names.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return names[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class LineTableModel extends AbstractTableModel {
        private String[] names = {"", ""};
        private Object[][] data = {
                {getLanguage(245, "Name"), new String()},
                {getLanguage(4008, "Point on Line"), new String()},
                {"X1 ", new Double(0)},
                {"Y1 ", new Double(0)},
                {"X2 ", new Double(0)},
                {"Y2", new Double(0)}
        };

        public int getColumnCount() {
            return names.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return names[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }


    class CircleTableModel extends AbstractTableModel {
        private String[] names = {"", ""};
        private Object[][] data = {
                {getLanguage(245, "Name"), new String()},
                {getLanguage(4009, "Point on Circle"), new String()},
                {getLanguage(4012, "Center"), new String()},
                {getLanguage(4010, "Center X"), new Double(0)},
                {getLanguage(4011, "Center Y"), new Double(0)},
                {getLanguage(4004, "Radius"), new Double(0)}
        };

        public int getColumnCount() {
            return names.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return names[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1 || row >= 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class PolygonTableModel extends AbstractTableModel {
        private String[] names = {"", ""};
        private Object[][] data = {
                {getLanguage(4013, "Grid Step"), new Integer(0)},
                {getLanguage(4014, "Slope Angle"), new Integer(0)},
        };

        public int getColumnCount() {
            return names.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return names[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    private double round(double r) {
        int t = (int) (100 * r);
        return t / 100.0;
    }

    class CPropertyTableCellRender implements TableCellRenderer {
        TableCellRenderer deditor;

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return deditor.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

    }

    class CPropertyTableCellEditor implements TableCellEditor {
        DefaultCellEditor editor;
        DefaultCellEditor booleanEditor;
        DefaultCellEditor selected;

        public CPropertyTableCellEditor() {
            selected = editor = new DefaultCellEditor(new JTextField());
            booleanEditor = new DefaultCellEditor(new JCheckBox());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected,
                                                     int row, int column) {
            if (value instanceof Boolean) {
                selected = booleanEditor;
                return booleanEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
            selected = editor;
            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        }

        public void setEditorAt(int row, TableCellEditor editor) {

        }

        public Object getCellEditorValue() {
            return selected.getCellEditorValue();
        }

        public boolean stopCellEditing() {
            return selected.stopCellEditing();
        }

        public void cancelCellEditing() {
            selected.cancelCellEditing();
        }

        public boolean isCellEditable(EventObject anEvent) {
            return selected.isCellEditable(anEvent);
        }

        public void addCellEditorListener(CellEditorListener l) {
            selected.addCellEditorListener(l);
        }

        public void removeCellEditorListener(CellEditorListener l) {
            selected.removeCellEditorListener(l);
        }

        public boolean shouldSelectCell(EventObject anEvent) {
            return selected.shouldSelectCell(anEvent);
        }
    }
}