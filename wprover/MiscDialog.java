package wprover;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;


public class MiscDialog extends JBaseDialog implements FocusListener, ActionListener {
    private GExpert gxInstance;
    private String lan;
    private JTabbedPane tpane;

    private DisplayPanel pane1;
    private modePanel pane2;
    private colorPanel panelc;
    private FontPanel pane3;
    private AnglePanel pane4;
    private boolean onSetting = false;


    public MiscDialog(GExpert gx) {
        super(gx.getFrame(), ("Preference"), false);
        gxInstance = gx;
        lan = CMisc.lan;


        String s = GExpert.getLanguage("Preference");
        this.setTitle(s);

        gxInstance = gx;
//        if (gxInstance != null)
//            gxInstance.addDependentDialog(this);

        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
        pane.addTab(GExpert.getLanguage("Display"), pane1 = createPanelDisply());

        pane.addTab(GExpert.getLanguage("Mode"), pane2 = new modePanel());

        pane.addTab(GExpert.getLanguage("Color"), panelc = new colorPanel());
        pane.addTab(GExpert.getLanguage("Font"), pane3 = new FontPanel());
        pane.addTab(GExpert.getLanguage("Other"), pane4 = new AnglePanel());
        tpane = pane;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(pane);

        JPanel p2 = new JPanel(new FlowLayout());
        JButton b1 = new JButton("Save Preference");
        b1.setText(GExpert.getLanguage(501, "Save Preference"));

        JButton b3 = new JButton("Default");
        b3.setText(GExpert.getLanguage(505, "Default"));
        b3.setActionCommand("Default");
        b3.addActionListener(this);

        b1.setActionCommand("Save Preference");

        JButton b2 = new JButton("OK");
        b2.setText(GExpert.getLanguage(3204, "OK"));
        b2.setActionCommand("OK");
        b1.addActionListener(this);
        b2.addActionListener(this);
        p2.add(Box.createHorizontalGlue());
        p2.add(b1);
        p2.add(b3);
        p2.add(Box.createHorizontalGlue());
        p2.add(b2);
        panel.add(p2);


        this.addFocusListener(this);
        this.getContentPane().add(panel);
        this.setSize(550, 530);

    }

    public void setSelectedTabbedPane(int n) {
        tpane.setSelectedIndex(n);
    }

    public void init() {
        pane1.init();
        pane2.init();
        pane3.init();
        pane4.init();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("OK")) {
            this.setVisible(false);

        } else if (command.equals("Default")) {
            onSetting = true;
            CMisc.Reset();
            init();
            onSetting = false;
        } else if (command.equals("Save Preference")) {

            String s1 = GExpert.getUserDir();
            String s2 = GExpert.getFileSeparator();

            try {
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(new File(s1 + s2 + "Property.x")), "UTF-8");

                CMisc.SaveProperty(writer);
            } catch (IOException ee) {
                JOptionPane.showMessageDialog(gxInstance, GExpert.getLanguage(502, "Can not Save Preference"), "Fail", JOptionPane.WARNING_MESSAGE);
            }
            JOptionPane.showMessageDialog(gxInstance, GExpert.getLanguage(503,
                    "Save Preference Successfully") + "\n" +
                    GExpert.getLanguage(506, "Please restart the program."),
                    GExpert.getLanguage("Saved"), JOptionPane.WARNING_MESSAGE);

            try {

                ProcessBuilder pb = new ProcessBuilder("java", "-jar",
                        GExpert.getUserDir() + GExpert.getFileSeparator() + "jgex.jar");
                pb.directory(new File(GExpert.getUserDir()));
                Map<String, String> map = pb.environment();
                Process p = pb.start();
                System.exit(0);
            } catch (IOException e0) {
            }
        }

    }


    private DisplayPanel createPanelDisply() {
        return new DisplayPanel();
    }


    class DisplayPanel extends JPanel {

        private JLabel text;
        private JRadioButton b1, b2, b3;
        private JSlider slider, slider1;
        private JCheckBox bts, bft;
        private JSpinner spinner;

        public DisplayPanel() {
            this.setLayout(new GridLayout(5, 1));
            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p1.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(358, "Polygon Alpha")));
            float f = CMisc.getFillCompositeAlpha();
            int n = 100 - (int) (f * 100);

            slider = new JSlider(0, 100);
            slider.setValue(n);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(20);
            slider.setPaintTrack(true);
            slider.setPaintLabels(true);

            p1.add(slider);
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (onSetting) return;
                    JSlider slider = (JSlider) e.getSource();
                    int n = slider.getValue();
                    float f = (100 - n) / 100.0f;
                    CMisc.setFillCompositeAlpha(f);
                    text.setText(new Integer(n).toString());
                    gxInstance.d.repaint();
                }
            });

            p1.add((text = new JLabel()));
            //   p1.add(Box.createHorizontalGlue());
            text.setText(new Integer(n).toString());

            p1.add(Box.createHorizontalGlue());
            this.add(p1);

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            slider1 = new JSlider(0, 20);
            slider1.setValue(CMisc.getPointRadius());
            slider1.setPaintTicks(true);
            slider1.setMinorTickSpacing(1);
            slider1.setMajorTickSpacing(5);
            slider1.setPaintTrack(true);
            slider1.setPaintLabels(true);

            p2.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(359, "Radius of Point")));
            p2.add(slider1);
            slider1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (onSetting) return;
                    JSlider slider = (JSlider) e.getSource();

                    int n = slider.getValue();
                    CMisc.setPointRadius(n);
                    gxInstance.d.repaint();
                }
            });
            p2.add(Box.createHorizontalGlue());
            this.add(p2);

            JPanel p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.LEADING));
            p3.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(360, "Point's Text")));
            JButton button = new JButton(GExpert.getLanguage(362, "Default Font"));
            button.setText(CMisc.nameFont.getName() + " " + CMisc.nameFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.nameFont,
                                    GExpert.getLanguage(1024, "Choose default foot for point's text"), Color.black)) {
                        CMisc.nameFont = vFontChooser.getReturnFont();
                        JButton b = (JButton) e.getSource();
                        b.setText(CMisc.nameFont.getName() + " " + CMisc.nameFont.getSize());
                    }
                }

            });

            JCheckBox b = bts = new JCheckBox(GExpert.getLanguage(361, "Show Text"));
            b.setSelected(CMisc.nameTextShown);
            b.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (onSetting) return;
                    CMisc.nameTextShown = ((JCheckBox) e.getSource()).isSelected();
                    gxInstance.d.repaint();
                }


            });

            p3.add(b);
            p3.add(Box.createHorizontalStrut(10));
            p3.add(button);
            p3.add(Box.createHorizontalStrut(10));
            this.add(p3);


            JPanel p5 = new JPanel();
            p5.setLayout(new FlowLayout(FlowLayout.LEADING));
            p5.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(363, "Angle Text")));
            ButtonGroup bg = new ButtonGroup();
            ItemListener listener = new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (onSetting) return;
                    Object obj = e.getSource();
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (obj == b1) {
                            CMisc.show_angle_type = 0;
                        } else if (obj == b2) {
                            CMisc.show_angle_type = 1;
                        } else if (obj == b3) {
                            CMisc.show_angle_type = 2;
                        }
                    }
                }
            };
            b1 = new JRadioButton(GExpert.getLanguage("None"));
            b1.addItemListener(listener);
            bg.add(b1);
            p5.add(b1);
            p5.add((b2 = new JRadioButton(GExpert.getLanguage("Label"))));
            bg.add(b2);
            b2.addItemListener(listener);
            p5.add((b3 = new JRadioButton(GExpert.getLanguage("Degrees"))));
            bg.add(b3);
            b3.addItemListener(listener);
            switch (CMisc.show_angle_type) {
                case 0:
                    b1.setSelected(true);
                    break;
                case 1:
                    b2.setSelected(true);
                    break;
                case 2:
                    b3.setSelected(true);
                    break;
            }
            button = new JButton(GExpert.getLanguage("Font"));
            button.setText(CMisc.angleNameFont.getName() + " " + CMisc.angleNameFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.angleNameFont, Color.black)) {
                        CMisc.angleNameFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.angleNameFont.getName() + " " + CMisc.angleNameFont.getSize());
                    }
                }

            });
            p5.add(Box.createHorizontalStrut(10));
            p5.add(button);
            this.add(p5);

            JPanel p6 = new JPanel();
            p6.setLayout(new FlowLayout(FlowLayout.LEADING));
            p6.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(367, "Foot Mark")));
            JCheckBox bx = bft = new JCheckBox(GExpert.getLanguage(368, "Show foot mark"));
            bx.setSelected(CMisc.footMarkShown);

            bx.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    CMisc.footMarkShown = ((JCheckBox) e.getSource()).isSelected();
                    gxInstance.d.repaint();
                }
            });
            p6.add(bx);
            p6.add(Box.createHorizontalStrut(10));
            p6.add(new JLabel(GExpert.getLanguage("Length") + ":  "));
            JSpinner spin = spinner = new JSpinner();
            spin.setValue(CMisc.FOOT_MARK_LENGTH);
            spin.addChangeListener(new ChangeListener() {


                public void stateChanged(ChangeEvent e) {
                    if (onSetting) return;
                    Object obj = ((JSpinner) e.getSource()).getValue();
                    int n = Integer.parseInt(obj.toString());
                    CMisc.FOOT_MARK_LENGTH = n;
                    gxInstance.d.repaint();
                }
            });

            p6.add(spin);

            this.add(p6);
        }

        public void init() {
            float f = CMisc.getFillCompositeAlpha();
            int n = 100 - (int) (f * 100);


            bts.setSelected(CMisc.nameTextShown);

            switch (CMisc.show_angle_type) {
                case 0:
                    b1.setSelected(true);
                    break;
                case 1:
                    b2.setSelected(true);
                    break;
                case 2:
                    b3.setSelected(true);
                    break;
            }
            bft.setSelected(CMisc.footMarkShown);
            spinner.setValue(CMisc.FOOT_MARK_LENGTH);


            slider.setValue(n);
            slider1.setValue(CMisc.getPointRadius());
        }

    }

    class AnglePanel extends JPanel implements ItemListener {
        JRadioButton ba, bwa, bma, bfill;
        JSlider slider;


        public AnglePanel() {
            this.setLayout(new GridLayout(5, 1));
            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p1.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage("Angle")));

            ButtonGroup bg = new ButtonGroup();
            ba = new JRadioButton(GExpert.getLanguage(383, "Without Arrow"));
            bg.add(ba);
            bwa = new JRadioButton(GExpert.getLanguage(384, "With Arrow"));
            bg.add(bwa);
            bma = new JRadioButton(GExpert.getLanguage(385, "Multiple Arc"));
            bg.add(bma);
            bfill = new JRadioButton(GExpert.getLanguage(386, "Fill"));


            ba.addItemListener(this);
            bwa.addItemListener(this);
            bma.addItemListener(this);
            bfill.addItemListener(this);
            bg.add(bfill);
            p1.add(ba);
            p1.add(bwa);
            p1.add(bma);
            p1.add(bfill);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(381, "Polygon Moving Interval")));
            p1.setLayout(new FlowLayout(FlowLayout.LEFT));

            int d = CMisc.getMoveStep();
            slider = new JSlider(0, 20);
            slider.setValue(d);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(4);
            slider.setMinimum(2);
            slider.setPaintTrack(true);
            slider.setPaintLabels(true);

            p1.add(slider);
            p1.add(Box.createHorizontalGlue());
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (onSetting) return;

                    JSlider slider = (JSlider) e.getSource();
                    int n = slider.getValue();
                    CMisc.setMoveStep(n);
                    gxInstance.dp.recal_allFlash();
                }
            });
            this.add(p1);
            init();
        }

        public void itemStateChanged(ItemEvent e) {
            if (onSetting) return;
            Object obj = e.getSource();
            if (obj == ba || obj == bwa || obj == bma || obj == bfill) {
                if (ba.isSelected())
                    CMisc.ANGLE_TYPE = 0;
                else if (bwa.isSelected())
                    CMisc.ANGLE_TYPE = 1;
                else if (bma.isSelected())
                    CMisc.ANGLE_TYPE = 2;
                else if (bfill.isSelected())
                    CMisc.ANGLE_TYPE = 3;
            }
        }

        public void init() {
            ba.setSelected(CMisc.ANGLE_TYPE == 0);
            bwa.setSelected(CMisc.ANGLE_TYPE == 1);
            bma.setSelected(CMisc.ANGLE_TYPE == 2);
            bfill.setSelected(CMisc.ANGLE_TYPE == 3);
            int d = CMisc.getMoveStep();
            slider.setValue(d);
        }
    }


    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
        this.setVisible(false);
    }

    class FontPanel extends JPanel {


        public FontPanel() {
            this.setLayout(new GridLayout(3, 2));

            JPanel p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(360, "Point's Text")));
            JButton button = new JButton("PTEXT");
            button.setText(CMisc.nameFont.getName() + " " + CMisc.nameFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.nameFont, Color.black)) {
                        CMisc.nameFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.nameFont.getName() + " " + CMisc.nameFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);


            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("THM - " + GExpert.getLanguage(3100, "Theorem")));
            button = new JButton("THM");
            button.setText(CMisc.thmFont.getName() + " " + CMisc.thmFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.thmFont, Color.black)) {
                        CMisc.thmFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.thmFont.getName() + " " + CMisc.thmFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("F - D"));// + GExpert.getLanguage(3002, "Full Angle Method")
//                    + "-" + GExpert.getLanguage(3001, "Deductive Datab?ase Method")));
            // p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            button = new JButton("Full");
            button.setText(CMisc.fullFont.getName() + " " + CMisc.fullFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.fullFont, Color.black)) {
                        CMisc.fullFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.fullFont.getName() + " " + CMisc.fullFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Area - " + GExpert.getLanguage(3004, "Area Method")));
            // p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            button = new JButton("Area");
            button.setText(CMisc.areaFont.getName() + " " + CMisc.areaFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.areaFont, Color.black)) {
                        CMisc.areaFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.areaFont.getName() + " " + CMisc.areaFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Manual - " + GExpert.getLanguage(3007, "Manual Method")));
            // p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            button = new JButton("Manual");
            button.setText(CMisc.manualFont.getName() + " " + CMisc.manualFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.manualFont, Color.black)) {
                        CMisc.manualFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.manualFont.getName() + " " + CMisc.manualFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Fix - " + GExpert.getLanguage(307, "Fixpoint")));
            // p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            button = new JButton("Fixpoint");
            button.setText(CMisc.fixFont.getName() + " " + CMisc.fixFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.fixFont, Color.black)) {
                        CMisc.fixFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.fixFont.getName() + " " + CMisc.fixFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);

            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Algebra - " + GExpert.getLanguage(1111, "Algebra")));
            //
            //  p1.setLayout(new FlowLayout(FlowLayout.LEFT));
            button = new JButton("Algebra");
            button.setText(CMisc.algebraFont.getName() + " " + CMisc.algebraFont.getSize());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.OK_OPTION ==
                            vFontChooser.showDialog(gxInstance, CMisc.algebraFont, Color.black)) {
                        CMisc.algebraFont = vFontChooser.getReturnFont();
                        JButton button = (JButton) e.getSource();
                        button.setText(CMisc.algebraFont.getName() + " " + CMisc.algebraFont.getSize());
                    }
                }
            });
            p1.add(button);
            this.add(p1);


        }


        public void init() {

        }

    }

    class colorPanel extends JPanel implements ItemListener, MouseListener {

        private JRadioButton b1, b2, b3;
        private ColorPane pbk, pgrid;

        public colorPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p1.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(388, "Color Mode")));

            ButtonGroup bg = new ButtonGroup();
            b1 = new JRadioButton(GExpert.getLanguage("Colorful"));
            b2 = new JRadioButton(GExpert.getLanguage("Gray"));
            b3 = new JRadioButton(GExpert.getLanguage(352, "Black and White"));
            {
                int n = CMisc.ColorMode;
                if (n == 0)
                    b1.setSelected(true);
                else if (n == 1)
                    b2.setSelected(true);
                else b3.setSelected(true);
            }


            bg.add(b1);
            bg.add(b2);
            bg.add(b3);
            p1.add(b1);
            p1.add(b2);
            p1.add(b3);
            b1.addItemListener(this);
            b2.addItemListener(this);
            b3.addItemListener(this);
            p1.add(Box.createHorizontalGlue());


            JPanel p3 = new JPanel(new GridLayout(1, 2));
            p3.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage("Color")));

            pbk = new ColorPane(100, 30);
            pbk.addMouseListener(this);
            JPanel p31 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p31.add(new JLabel(GExpert.getLanguage(377, "BackGroud Color")));
            p31.add(pbk);

            pgrid = new ColorPane(100, 30);
            pgrid.addMouseListener(this);
            JPanel p32 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p32.add(new JLabel(GExpert.getLanguage("Grid")));
            p32.add(pgrid);

            p3.add(p31);
            p3.add(p32);

            this.add(p1);
            this.add(p3);
            init();

        }


        public void init() {

            int n = CMisc.ColorMode;
            if (n == 0)
                b1.setSelected(true);
            else if (n == 1)
                b2.setSelected(true);
            else b3.setSelected(true);

            pbk.setBackground(CMisc.getBackGroundColor());
            pgrid.setBackground(CMisc.getGridColor());
//            gxInstance.d.setBackground(CMisc.getBackGroundColor());
            this.repaint();
        }

        public void itemStateChanged(ItemEvent e) {
            Object o = e.getSource();
            if (o == b1 || o == b2 || o == b3) {
                if (b1.isSelected()) {
                    CMisc.ColorMode = 0;
                } else if (b2.isSelected()) {
                    CMisc.ColorMode = 1;
                } else if (b3.isSelected()) {
                    CMisc.ColorMode = 2;
                }
            }
            gxInstance.d.repaint();


        }

        public void mouseClicked(MouseEvent e) {
            Object o = e.getSource();
            if (o == pbk) {
                Color newColor = JColorChooser.showDialog(gxInstance,
                        GExpert.getLanguage(379, "Choose Color"), CMisc.getBackGroundColor());
                if (newColor != null) {
                    Color c = newColor;
                    CMisc.setBackGroundColor(c);
                    gxInstance.d.setBackground(c);
                    pbk.setBackground(c);
                    pbk.repaint();
                }

            } else if (o == pgrid) {
                Color newColor = JColorChooser.showDialog(gxInstance,
                        GExpert.getLanguage(379, "Choose Color"), CMisc.getBackGroundColor());
                if (newColor != null) {
                    Color c = newColor;
                    CMisc.setGridColor(c);
                    gxInstance.d.repaint();
                    pgrid.setBackground(c);
                    pgrid.repaint();
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


    }

    class ColorPane extends JPanel {
        int w, h;

        public ColorPane(int w, int h) {
            this.w = w;
            this.h = h;
            this.setBorder(new LineBorder(Color.lightGray, 1));
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public Dimension getPreferredSize() {
            return new Dimension(w, h);
        }
    }

    class modePanel extends JPanel implements ItemListener {
        private JRadioButton r1, r2;
        private JComboBox blanguage, blook;

        modePanel() {

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p2.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage(371, "AntiAlias")));
            ButtonGroup bg1 = new ButtonGroup();
            r1 = new JRadioButton(GExpert.getLanguage("ON"));
            r2 = new JRadioButton(GExpert.getLanguage("OFF"));
            if (CMisc.AntiAlias)
                r1.setSelected(true);
            else r2.setSelected(true);
            r1.addItemListener(this);
            r2.addItemListener(this);
            bg1.add(r1);
            bg1.add(r2);
            p2.add(r1);
            p2.add(r2);


            JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

            p4.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage("Language")));
            String[] lan = {
                    "English",
                    "Chinese",
                    "Japanese",
                    "French",
                    "German",
                    "Italian",
                    "Spaish",
                    "Persian"
            };
            blanguage = new JComboBox(lan);
            blanguage.setSelectedItem(CMisc.lan);
            p4.add(blanguage);
            blanguage.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (blanguage.getSelectedIndex() != -1)
                        CMisc.lan = blanguage.getSelectedItem().toString();
                }
            });

            JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p5.setBorder(BorderFactory.createTitledBorder(GExpert.getLanguage("LookAndFeel")));
            UIManager.LookAndFeelInfo[] ff = UIManager.getInstalledLookAndFeels();
            String ss[] = new String[ff.length + 1];
            ss[0] = "Default";


            for (int i = 1; i < ff.length + 1; i++)
                ss[i] = ff[i - 1].getName();

            blook = new JComboBox(ss);
            blook.setSelectedItem(CMisc.lookAndFeel);
            p5.add(blook);
            blook.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (onSetting) return;
                    if (blook.getSelectedIndex() != -1)
                        CMisc.lookAndFeel = blook.getSelectedItem().toString();
                }
            });


            this.add(p2);
            this.add(p4);
            this.add(p5);
            this.revalidate();
        }

        public void itemStateChanged(ItemEvent e) {
            Object o = e.getSource();
            if (o == r1 || o == r2) {
                if (r1.isSelected())
                    CMisc.AntiAlias = true;
                else CMisc.AntiAlias = false;
            }
            gxInstance.d.repaint();
        }


        public void init() {


            blanguage.setSelectedItem(CMisc.lan);
            blook.setSelectedItem(CMisc.lookAndFeel);
            gxInstance.d.setBackground(CMisc.getBackGroundColor());
        }
    }
}
