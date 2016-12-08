package wprover;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-11-6
 * Time: 20:57:00
 * To change this template use File | Settings | File Templates.
 */
public class JPopExView extends JBaseDialog
{
    GExpert gxInstance;
    JExPanel panel;

    public JPopExView(GExpert exp)
    {
        super(exp.getFrame());
        gxInstance = exp;
        this.setSize(600, 400);
        panel = new JExPanel();
        this.add(panel);
    }

    public boolean loadRule(String s)
    {
        this.setTitle(s);

        String f = GExpert.getUserDir();
        String sp = GExpert.getFileSeparator();

        drawProcess dp = new drawProcess();
        dp.clearAll();
        try
        {
            boolean ss = dp.Load(f + sp +"rules" + sp + s);
            panel.setdrawP(dp);
        } catch (IOException ee)
        {
            CMisc.eprint(panel, "can not load rule: " + sp);
            return false;
        }
        return true;
    }


}
