package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-12
 * Time: 11:31:35
 * To change this template use File | Settings | File Templates.
 */
public class UndoEditDialog extends JBaseDialog implements WindowListener {
    private ListTree treepanel;

    public UndoEditDialog(GExpert owner) {
        super(owner.getFrame());
        this.setTitle(owner.getLanguage(157, "Construct History"));
        treepanel = new ListTree(owner);
        this.setContentPane(treepanel);
        this.setSize(new Dimension(430, 600));
        owner.centerDialog(this);
        this.addWindowListener(this);
    }


    ListTree getTreePanel() {
        return treepanel;
    }

    public void showDialog() {
        this.setVisible(true);
        treepanel.reload();
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {
    }


    public void windowIconified(WindowEvent e) {

    }


    public void windowDeiconified(WindowEvent e) {

    }


    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}

