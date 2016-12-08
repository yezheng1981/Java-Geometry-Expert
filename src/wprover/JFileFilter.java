package wprover;

//import java.io.FileFilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-10
 * Time: 16:10:45
 * To change this template use File | Settings | File Templates.
 */
public class JFileFilter extends FileFilter {
    String endfix;
    int dep = 0;

//    public JFileFilter(String[] s) {
//        if (s != null) {
//            endfix = new String[s.length];
//            for (int i = 0; i < s.length; i++)
//                endfix[i] = s[i];
//        }
//    }

    private String endfix1;

    public JFileFilter(String s) {
        if (s == null || s.length() == 0) return;
        endfix = s;
        endfix1 = endfix.toUpperCase();
    }

    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        String s = f.getName();
        s = s.toUpperCase();
        return s.endsWith(endfix) || s.endsWith(endfix1);
    }

    public String getDescription() {
        return endfix;
    }

}
