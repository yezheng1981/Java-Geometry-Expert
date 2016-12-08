package wprover;


import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-3-3
 * Time: 13:54:48
 * To change this template use File | Settings | File Templates.
 */
public class drawType {
    int color_index;
    int dash;
    int width;


    public drawType()
    {
    }
    public drawType(int ci,int dash,int width)
    {
        color_index = ci;
        this.dash = dash;
        this.width = width;
    }
    public void Save(DataOutputStream out) throws IOException
    {
        out.writeInt(color_index);
        out.writeInt(dash);
        out.writeInt(width);
    }
    public void Load(DataInputStream in) throws IOException
    {
        color_index = in.readInt();
        dash = in.readInt();
        width = in.readInt();
    }

    public void setdrawType(Graphics2D g2)
    {
        if(color_index >= 0)
            g2.setColor(drawData.getColor(color_index));

        float d =0;
        if(dash >=0 )
           d = (float)drawData.getDash(dash);
        float w = (float)drawData.getWidth(width);

        if(dash >0)
        {
            float dash[] = {d};
            g2.setStroke(new BasicStroke(w, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));
        }else g2.setStroke(new BasicStroke(w));

    }


    public void setColorIndex(int id)
    {
      color_index = id;
    }
    public void setDash(int dash)
    {
        this.dash = dash;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getColorIndex()
    {
        return color_index;
    }
    public Color getColor()
    {
        return drawData.getColor(color_index);
    }
    public int getDashIndex()
    {
        return dash;
    }
    public int getWidthIndex()
    {
        return width;
    }
////////////// ///////////


}
