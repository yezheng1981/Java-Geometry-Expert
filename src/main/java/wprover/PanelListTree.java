package wprover;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: Nov 25, 2006
 * Time: 7:15:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PanelListTree extends JScrollPane{

    public PanelListTree()
    {
        super(new JTree());
    }


    class listTree extends JTree
    {
        public listTree()
        {
        }
    }
}
