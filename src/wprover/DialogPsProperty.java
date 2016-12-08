package wprover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPsProperty extends JBaseDialog implements ActionListener {
    private int type = -1;
    private JCheckBox pfill;
    private GExpert gxInstance;

    public String getLanguage(String s) {
        String s1 = "";
        if (gxInstance != null)
            s1 = gxInstance.getLanguage(s);
        if (s1.length() > 0)
            return s1;
        return s;
    }

    public DialogPsProperty(GExpert owner) {
        super(owner.getFrame(), true);

        gxInstance = owner;

        this.setTitle(getLanguage("Save as PS"));
//        this.setSize(350, 120);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JToggleButton button1 = new JToggleButton(gxInstance.getLanguage(1015, "Color"));
        button1.setActionCommand("Color");
        JToggleButton button2 = new JToggleButton(getLanguage("Gray"));
        button2.setActionCommand("Gray");
        JToggleButton button3 = new JToggleButton(getLanguage("Black and White"));
        button3.setActionCommand("Black and White");
        JToggleButton button4 = new JToggleButton(getLanguage("Cancel"));
        button4.setActionCommand("Cancel");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(button1);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(button2);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(button3);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(button4);
        this.getContentPane().add(panel);

        JPanel ppanel = new JPanel();
        ppanel.setLayout(new BoxLayout(ppanel, BoxLayout.Y_AXIS));
        pfill = new JCheckBox(gxInstance.getLanguage(1013, "Point filled with background color"));
//        ptext = new JCheckBox(gxInstance.getLanguage(1014, "Proof text"));
//        ptext.setSelected(true);
        ppanel.add(pfill);
//        ppanel.add(ptext);
        this.getContentPane().add(ppanel, "South");
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.compareTo("Color") == 0)
            type = 0;
        else if (command.compareTo("Gray") == 0)
            type = 1;
        else if (command.compareTo("Black and White") == 0)
            type = 2;
        else if (command.compareTo("Cancel") == 0)
            type = 3;
        this.setVisible(false);
    }

    int getSavePsType() {
        return type;
    }

    boolean getPointfilled() {
        return pfill.isSelected();
    }

//    boolean getisProveTextSaved() {
//        return ptext.isSelected();
//    }
}
