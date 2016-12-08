package wprover;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-3-4
 * Time: 14:25:06
 * To change this template use File | Settings | File Templates.
 */
public class CCoBox extends JComboBox {
    private static Vector instanceList = new Vector();
    int defaultindex = 0;

    public static CCoBox CreateAInstance() {
        Integer[] intArray = new Integer[drawData.getColorCounter() + 1];
        for (int i = 0; i <= drawData.getColorCounter(); i++) {
            intArray[i] = new Integer(i);
        }
        CCoBox cb = new CCoBox(intArray);

        cb.setMaximumRowCount(30);
        cb.setPreferredSize(new Dimension(40, 20));
        ColorComboRender render = new ColorComboRender(0, 100, 20);
        render.setPreferredSize(new Dimension(40, 20));
        cb.setRenderer(render);
        instanceList.add(cb);
        return cb;
    }

    private CCoBox(final Object items[]) {
        super(items);
    }

    public void setSelectedIndex(int index) {
        ((ColorComboRender) super.getRenderer()).index = index;
        super.setSelectedIndex(index);
    }

    public void setDefaultIndex(int index) {
        defaultindex = index;
    }

    public static void reGenerateAll() {
        for (int i = 0; i < instanceList.size(); i++) {
            CCoBox cb = (CCoBox) instanceList.get(i);
            int co = drawData.getColorCounter();
            int n = cb.getItemCount();

            if (co >= n)
                for (int j = n; j <= co; j++) {
                    cb.addItem(new Integer(j));
                }
        }
    }

    public static void resetAll() {
        drawData.reset();

        for (int i = 0; i < instanceList.size(); i++) {
            CCoBox cb = (CCoBox) instanceList.get(i);
            cb.setSelectedIndex(cb.defaultindex);
            int num = drawData.getColorCounter();
            for(int j=num+1; j<cb.getItemCount(); j++)
                cb.removeItemAt(j);
        }
    }

}
