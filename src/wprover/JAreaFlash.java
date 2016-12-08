package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-7-24
 * Time: 16:32:16
 * To change this template use File | Settings | File Templates.
 */
public class JAreaFlash extends JFlash implements ActionListener{
    private Vector vlist = new Vector();
    private int color = drawData.LIGHTCOLOR;

    public JAreaFlash(JPanel p,int cindex)
    {
        super(p);

        color = drawData.LIGHTCOLOR + cindex-1;
        timer = new Timer(TIME_INTERVAL, this);
    }
    public  boolean draw(Graphics2D g2)
    {
        if (n % 2 != 0) return true;

        int n = vlist.size();
        Composite ac = g2.getComposite();
        g2.setComposite(CMisc.getFillComposite());
        if(n == 0) return true;
        
        int []x = new int[n];
        int []y = new int[n];
        for(int i=0; i <n; i++)
        {
            CPoint p1 = (CPoint)vlist.get(i);
            x[i] = (int)p1.getx();
            y[i] = (int)p1.gety();
        }
        g2.setColor(Color.black);
        g2.drawPolygon(x,y,n);
        g2.setColor(drawData.getColor(color));
        g2.fillPolygon(x,y,n);
        g2.setComposite(ac);
        return true;
    }

    public void addAPoint(CPoint p)
    {
        if(p != null) vlist.add(p);
    }
    public void actionPerformed(ActionEvent e)
    {
        n--;
        if (n <= 0) super.stop();
        panel.repaint();
    }


}
