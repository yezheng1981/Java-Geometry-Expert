package wprover;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
//import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-10
 * Time: 22:42:17
 * To change this template use File | Settings | File Templates.
 */                       
public class GIFOptionDialog extends JBaseDialog implements ActionListener, ChangeListener {

    JSlider slider;
    JTextField field1, field2;
    JButton bok, bcancel;
    boolean result = false;
    private GExpert gxInstance;

    public GIFOptionDialog(GExpert fr, String title) {
        super(fr.getFrame(), title, true);
        gxInstance = fr;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(new JLabel(fr.getLanguage(2304, "Image Quality")));
        field1 = new JTextField();
        field2 = new JTextField();
        field1.setEditable(false);
        field2.setEditable(false);
        panel1.add(field1);
        panel1.add(field2);
        panel.add(panel1);
        panel.add(Box.createVerticalStrut(5));
        slider = new JSlider(1, 20, 1);
//        slider = new JSlider(0, 20);
        slider.setValue(2);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(4);
        slider.setMinimum(1);
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        panel.add(slider);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(Box.createHorizontalGlue());
        panel2.add(bok = new JButton(fr.getLanguage(3204, "OK")));
        panel2.add(bcancel = new JButton(fr.getLanguage("Cancel")));
        bok.addActionListener(this);
        bcancel.addActionListener(this);
        panel.add(panel2);
        updateValue();
        this.getContentPane().add(panel);
        this.setSize(300, 150);
    }

    public void setDefaultValue(int n) {
        slider.setValue(n);
        this.updateValue();
    }

    public boolean getReturnResult() {
        return result;
    }

    public int getQuality() {
        return 21 - slider.getValue();
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bok)
            result = true;
        else result = false;
        this.setVisible(false);
    }

    public void stateChanged(ChangeEvent e) {
        updateValue();
    }

    private void updateValue() {
        int v = slider.getValue();
        field1.setText(Integer.toString(v));
        field2.setText(getRate(v));
    }

    private int getValue() {
        return 21 - slider.getValue();
    }

    private String getRate(int n) {
        if (n > 18)
            return gxInstance.getLanguage(2305, "Best");
        if (n > 15)
            return gxInstance.getLanguage(2306, "Good");
        if (n > 10)
            return gxInstance.getLanguage(2307, "Medium");
        else if (n > 5)
            return gxInstance.getLanguage(2308, "Low");
        else return gxInstance.getLanguage(2309, "Very Low");
    }


}