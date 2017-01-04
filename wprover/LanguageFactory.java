package wprover;

import java.io.OutputStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-5-3
 * Time: 15:19:15
 * To change this template use File | Settings | File Templates.
 */
public class LanguageFactory {
    Language[] lan = new Language[20];
    int dlan = 0;


    public String[] getLanguageInfo() {
        int n = 0;
        for (int i = 0; i < lan.length; i++)
            if (lan[i] != null)
                n++;
        String[] ln = new String[n];
        for (int i = 0; i < n; i++) {
            if (lan[i] == null)
                break;
            ln[i] = lan[i].getName();
        }
        return ln;
    }

    public void setDefaultLanguage(int n) {
        if (n < 0 || n > lan.length)
            return;
        if (lan[n] == null)
            return;
        dlan = n;
    }


  


}
