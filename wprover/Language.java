package wprover;

import java.io.*;
import java.awt.*;
import java.util.Locale;

public class Language {

    private static int MAX_LEN = 500;

    private static Language laninstance = null;

    private String stype;
    private Font font;
    private lnode[] ndlist = new lnode[MAX_LEN];

    public static void setLanugage(Language lan) {
        laninstance = lan;
    }

    public boolean isEnglish() {
        return "English".equalsIgnoreCase(stype);
    }

    public void setLocal() {
        if (stype.equalsIgnoreCase("Chinese"))
            Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        else if(stype.equalsIgnoreCase("Italian"))
            Locale.setDefault(Locale.ITALIAN);
        else if(stype.equalsIgnoreCase("Persian"))
        {
         //Locale.setDefault(Locale.P);   
        }

        
    }

    public void load(File f) {

        try {
            //  OutputStreamWriter writer = this.outputBlank(f);

            InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");//
            BufferedReader reader = new BufferedReader(read);
            String h = reader.readLine();
            stype = h.trim();
            h = readFont(reader);

            //   writer.write(stype);
            //   writer.write("\nFont: " + font.getFontName() + " # " + font.getStyle() + " # " + font.getSize() + "\n");

            int i = 0;
            String s1, s2, s3;

            while (h != null) {
                s1 = s2 = s3 = null;

                if (h.length() > 1) {
                    if (h.trim().startsWith("*")) {
                        h = reader.readLine();
                        continue;
                    }

                    String[] st = h.split("#");
                    int n = Integer.parseInt(st[0].trim());
                    s1 = st[1].trim();
                    int sz = st.length;
                    if (sz >= 3)
                        s2 = st[2].trim();
                    if (sz >= 4)
                        s3 = st[3].trim();

                    lnode ln = new lnode(n, s1, s2, s3);
                    ndlist[i++] = ln;
                    //       writer.write(n + " = " + s1 +"\n");
//                    ln.writeen(writer);
                }
                //   else  writer.write("\n");
                h = reader.readLine();
            }

            reader.close();
            //     writer.close();

        } catch (IOException ee) {
        }

    }

    public String readFont(BufferedReader reader) throws IOException {
        String h = reader.readLine();
        while (h == null || h.length() < 1) {
            h = reader.readLine();
        }

        String[] sf = h.split(":");
        if (sf.length == 2) {
            String sh = sf[0].trim();

            if (sh.equalsIgnoreCase("Font")) {
                String[] sk = sf[1].trim().split("#");
                String name = sk[0].trim();
                String style = sk[1].trim();
                String size = sk[2].trim();
                Font f = new Font(name, Integer.parseInt(style), Integer.parseInt(size));
                font = f;
            }

            h = reader.readLine();
        }
        return h;
    }

    public Font getFont() {
        return font;
    }

    public String getName() {
        return stype;
    }

    class lnode {
        public int index;
        String en;
        String tx;
        String tip;

        public lnode(int n, String e, String t1, String t2) {
            index = n;
            en = e;
            tx = t1;
            tip = t2;
        }

        public lnode(String s) {
            index = -1;
            tip = s;
        }

        public void write(OutputStreamWriter out) throws IOException {
            if (index >= 0) {
                String s = index + " \t#\t" + en + " \t#\t" + tx;
                out.write(s);
                if (tip != null) {
                    out.write(" #\t" + tip);
                }
            } else
                out.write(tip + "\n");
            out.write("\n");

        }

        public void writeen(OutputStreamWriter out) throws IOException {
            out.write(index + " \t#\t");
            out.write(en);
            out.write("\n");
        }

        public void combine(lnode ln) {
            if (index < 0)
                return;

            if (ln == null) {
                tx = tip = null;
                return;
            }

            en = ln.en;
            tx = ln.tx;
            tip = ln.tip;
        }

    }

    public String getString(String s) {
        for (int i = 0; i < MAX_LEN; i++) {
            lnode ln = ndlist[i];
            if (ln == null)
                break;
            if (ln.en.equalsIgnoreCase(s))
                return ln.tx;
        }
        return s;
    }


    public String getString1(String s) {
        for (int i = 0; i < MAX_LEN; i++) {
            lnode ln = ndlist[i];
            if (ln == null)
                break;
            if (ln.en.equalsIgnoreCase(s))
                return ln.tip;
        }
        return "";
    }

    public String getString(int m, String s) {
        String s1 = getString(m);
        if (s1 != null && s1.length() > 0)
            return s1;
        return s;
    }

    public String getString(int m) {
        for (int i = 0; i < MAX_LEN; i++) {
            lnode ln = ndlist[i];
            if (ln == null)
                break;

            if (ln.index == m)
                return ln.tx;
        }
        return "";
    }

    public String getEnglish(String s) {
        if (stype.equalsIgnoreCase("English"))
            return s;
        for (int i = 0; i < MAX_LEN; i++) {
            lnode ln = ndlist[i];
            if (ln == null)
                break;

            if (ln.tx.equalsIgnoreCase(s))
                return ln.en;
        }
        return s;
    }


    public static String getLs(String s) {
        if (laninstance == null)
            return s;
        return laninstance.getString(s);
    }

    public static String getLs(int m, String s) {
        if (laninstance == null)
            return s;
        return laninstance.getString(m, s);
    }

    public static String getLs(int n) {
        if (laninstance == null)
            return "";
        return laninstance.getString(n);
    }

    public static String getLs1(String s) {
        if (laninstance == null)
            return s;
        return laninstance.getString1(s);
    }


    public OutputStreamWriter outputBlank(File f) throws IOException {
        String path = f.getPath();
        String name = f.getName();

        String n = path + "1";
        File f1 = new File(n);
        f1.createNewFile();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f1), "UNICODE");//����UNICODE,UTF-16
        return writer;
    }

    public void writeFile(File f) {
        try {
            OutputStreamWriter writer = this.outputBlank(f);
            writer.write("Font: " + font.getFontName() + " # " + font.getStyle() + " # " + font.getSize());
            for (int i = 0; i < ndlist.length; i++) {
                if (ndlist[i] == null)
                    break;
                lnode ln = ndlist[i];
                ln.write(writer);

            }
            writer.close();


        } catch (IOException ee) {
        }


    }


    public void writeOut(OutputStreamWriter writer, String[] ss) throws IOException {
        if (ss.length == 0)
            writer.write("\n");
        else {
            for (int i = 0; i < ss.length; i++) {
                writer.write(ss[i].trim());
                if (i != ss.length - 1)
                    writer.write(" #\t");
            }
            writer.write("\n");
        }
    }

    public lnode getlnode(int n) {

        for (int i = 0; i < ndlist.length; i++) {
            if (ndlist[i] == null)
                break;
            lnode ln = ndlist[i];
            if (ln.index == n)
                return ln;
        }
        return null;
    }

    public void combine(Language lan) {
        for (int i = 0; i < ndlist.length; i++) {
            if (ndlist[i] == null)
                break;
            lnode ln = ndlist[i];
            lnode ln1 = lan.getlnode(ln.index);
            ln.combine(ln1);
        }
    }
}
