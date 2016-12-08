package wprover;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-1-16
 * Time: 22:05:51
 * To change this template use File | Settings | File Templates.
 */
public class vFontDialog extends JBaseDialog {

    public vFontDialog(GExpert exp) {
        super(exp.getFrame(), "Font for Proof Panel");
        Frame f = exp.getFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.setBorder(BorderFactory.createTitledBorder("Font for theorem"));
        JLabel label = new JLabel("Preview Label");
        label.setBackground(Color.white);
        p1.add(label);
        p1.add(Box.createHorizontalGlue());
        p1.add(new JButton("Font"));
        panel.add(p1);
        this.add(panel);

        this.setSize(400, 300);
    }
}
