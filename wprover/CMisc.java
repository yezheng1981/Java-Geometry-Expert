package wprover;

import UI.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-1-8
 * Time: 18:57:22
 * To change this template use File | Settings | File Templates.
 */
public class CMisc {
    final public static double version = 0.053;
    public static final boolean DEBUG = false;

    public static int id_count = 1;
    public static int id_count_onfile = 1;
    public static boolean isDiagramChanged = false;

    public static double version_load_now = 0;

    final public static boolean POINT_TRANS = true;  // optimize polynomial.

    ////////////////////////////////////////////////////////////////////////////////
    final public static boolean APPLET = false;
    public static URL homedir;

    final public static void setHomeDirectory(URL l) {
        homedir = l;
    }

    final public static URL getHomeDirectory() {
        return homedir;
    }

    final public static boolean isDebug() {
        return DEBUG;
    }

    final public static boolean isApplet() {
        return APPLET;
    }

    final public static boolean isApplication() {
        return !APPLET;
    }

    final public static void print(String s) {
        if (DEBUG)
            System.out.println(s);
    }

    final public static void eprint(Component t, String s) {
        JOptionPane.showMessageDialog(t, s);
    }

    final public static Border BlueishBorder = BorderFactory.createCompoundBorder(
            new DropShadowBorder(), BorderFactory.createLineBorder(new Color(206, 223, 242), 4));

    //////////////////////STROKE/////////////////////////////////////////////////////
    final public static Color SelectObjectColor = new Color(195, 195, 245);
    final public static BasicStroke NormalLineStroke = new BasicStroke(1.0f);
    final public static BasicStroke SelectObjectStroke = new BasicStroke(5.0f);
    final public static BasicStroke SelectPolygonStroke = new BasicStroke(3.0f);
    final public static float[] DefautlDash = {2.0f};
    final public static BasicStroke DashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, DefautlDash, 0.0f);
    final public static float[] DefautlDash1 = {3.0f};
    final public static BasicStroke DashedStroke1 = new BasicStroke(1.6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 7.0f, DefautlDash1, 0.0f);

    //////////////////font.////////////////////////////////////////////////
    ////////////////////////////////////////////////
    //** begin of Font.

    public static String defaultFont = "Dialog";
    public static Font angleNameFont, nameFont, font, smallnameFont, button_label_font;
    public static Font thmFont, fullFont, gddFont, areaFont, manualFont, fixFont, algebraFont;
    private static int[] FontSizePool = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 24, 26, 28, 30, 36, 40, 48, 64, 72};

    final public static void setDefaultFont(String str) {
        if (defaultFont.equalsIgnoreCase(str))
            return;
        defaultFont = str;
    }

    final public static void initFont() {   // rebuild the font.
        angleNameFont = new Font(defaultFont, Font.PLAIN, 12);
        nameFont = new Font(defaultFont, Font.BOLD, 16);
        font = new Font(defaultFont, Font.PLAIN, 12);
        smallnameFont = new Font(defaultFont, Font.BOLD, 10);
        button_label_font = new Font(defaultFont, Font.BOLD, 12);
        thmFont = new Font(defaultFont, Font.BOLD, 12);
        fullFont = new Font(defaultFont, Font.BOLD, 13);
        gddFont = new Font(defaultFont, Font.BOLD, 13);
        areaFont = new Font(defaultFont, Font.BOLD, 13);
        manualFont = new Font(defaultFont, Font.BOLD, 16);
        fixFont = new Font(defaultFont, Font.BOLD, 12);
        algebraFont = new Font(defaultFont, Font.BOLD, 14);
    }

    //*****  end of Font.


    final public static Color ButtonColor = new Color(192, 192, 192);
    final public static Color frameColor = new Color(214, 214, 214);
    public static int ColorMode = 0; // 1. Gray, 2.Black and white.
    public static boolean AntiAlias = true;


    public static int[] getFontSizePool() {
        return FontSizePool;
    }

    //////////////////////////color/////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////

    final public static int LINDE_DRAW_EXT = 50;
    final public static double ZOOM_RATIO = 1.10;
    final public static int PIXEPS = 6;
    final public static int PIXEPS_PT = 8;
    final public static double ZERO = 0.001;
    final public static double DOUBLE_ZERO = 0.00000001;
    final public static double HV_ZERO = 0.01;
    final public static double ANIMATE_GAP = 15.0;
    final public static double MAX_K = 999;
    final public static int MAX_DRAW_LEN = 10000;
    final public static double rlength = 50;
    final public static double MAX_SLOPE = 9999.0;
    final public static int POLYGON_ANIMATION_LAG = 3;


    public static int ANGLE_TYPE = 1; // 0: traditional ;  1: Full angle, 2: Multiple arc, 3. Fill.
    public static int show_angle_type = 2;    // 0: no , 1: name 2: value;
    public static boolean show_angle_text = false;
    public static boolean SMART_HV_LINE_CATCH = false;
    public static boolean DRAW_CATCH_OBJECT_NAME = false;


    /////////////////////////////////////////////////////////////////////
    private static AlphaComposite fillac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.70f);
    private static Color BackGroundColor = Color.white;    //OK
    private static Color GridColor = new Color(220, 220, 220);
    public static boolean nameTextShown = true;
    public static boolean footMarkShown = true;
    private static double Alpha = 1.0;
    private static int FLASH_MOVE_STEP = 8;
    public static int FOOT_MARK_LENGTH = 10;
    private static int FLASH_INTERVAL = 100;
    private static int POINT_RADIUS = 4;


    public static int getFlashInterval() {
        return FLASH_INTERVAL;
    }

    public static void setFlashInterval(int n) {
        FLASH_INTERVAL = n;
    }

    public static Color getGridColor() {
        return GridColor;
    }

    public static void setGridColor(Color c) {
        GridColor = c;
    }

    public static Color getBackGroundColor() {
        return BackGroundColor;
    }

    public static int getMoveStep() {
        return FLASH_MOVE_STEP;
    }

    public static void setMoveStep(int n) {
        FLASH_MOVE_STEP = n;
    }

    public static void showMessage(String s) {
        if (DEBUG) {
            CMisc.print(s);
        }
    }

    public static int getPointRadius() {
        return POINT_RADIUS;
    }

    public static void setPointRadius(int n) {
        POINT_RADIUS = n;
    }

    public static AlphaComposite getFillComposite() {
        return fillac;
    }

    public static float getFillCompositeAlpha() {
        return fillac.getAlpha();
    }

    public static void setFillCompositeAlpha(float f) {
        if (f == fillac.getAlpha()) {
            return;
        }
        fillac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
    }

    public static int getObjectId() {
        return id_count++;
    }

    public static void onFileSavedOrLoaded() {
        id_count_onfile = id_count;
        isDiagramChanged = false;
    }

    public static void setDiagramChaged() {
        isDiagramChanged = true;
    }

    public static boolean isIdCountChanged() {
        return id_count != id_count_onfile;
    }

    public static boolean needSave() {
        return id_count != id_count_onfile || isDiagramChanged;
    }

    public static boolean isFootMarkShown() {
        return footMarkShown;
    }

    public static void setAlpha(double r) {
        Alpha = r;
    }

    public static double getAlpha() {
        return Alpha;
    }

    public static void setBackGroundColor(Color c) {
        BackGroundColor = c;
    }

    final public static boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ee) {
            return false;
        }
    }

    public static void setFont(String f) {
        if (f == null || f.length() < 1) return;
        if (defaultFont.equalsIgnoreCase(f))
            return;
        defaultFont = f;

        angleNameFont = new Font(defaultFont, Font.PLAIN, 12);
        nameFont = new Font(defaultFont, Font.BOLD, 16);
        font = new Font(defaultFont, Font.PLAIN, 12);
        smallnameFont = new Font(defaultFont, Font.BOLD, 10);
        button_label_font = new Font(defaultFont, Font.BOLD, 14);
    }


    public static void Reset() {
        nameTextShown = true;
        show_angle_text = true;
        show_angle_type = 2;
        POINT_RADIUS = 4;
        fillac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.70f);
        FLASH_MOVE_STEP = 8;
        FOOT_MARK_LENGTH = 10;
        footMarkShown = true;
        FLASH_INTERVAL = 100;
        GridColor = new Color(220, 220, 220);
        screenx = 1000;
        screeny = 750;
        lan = "English";
        full_screen = false;
        ANGLE_TYPE = 1;
        AntiAlias = true;
        lookAndFeel = "Default";
        BackGroundColor = Color.white;
    }


    public static int screenx = 1000;
    public static int screeny = 750;
    public static String lan = "English";
    public static boolean full_screen = false;
    public static String lookAndFeel = "Default";

    public static void SaveProperty(OutputStreamWriter out) throws IOException {
        out.write("ScreenX : " + screenx + "\r\n");
        out.write("ScreenY : " + screeny + "\r\n");

        out.write("FullScreen : " + full_screen + "\r\n");
        out.write("Language : " + lan + "\r\n");
        out.write("Alpha : " + fillac.getAlpha() + "\r\n");
        out.write("Radius : " + POINT_RADIUS + "\r\n");
        out.write("GridColor : " + GridColor.getRGB() + "\r\n");
        out.write("NameTextShown : " + nameTextShown + "\r\n");
        out.write("ShowAngleText : " + show_angle_text + "\r\n");
        out.write("ShowAngleType : " + show_angle_type + "\r\n");
        out.write("FlashMoveStep : " + FLASH_MOVE_STEP + "\r\n");
        out.write("FootMarkLength : " + FOOT_MARK_LENGTH + "\r\n");
        out.write("footMarkShown : " + footMarkShown + "\r\n");
        out.write("AngleType : " + ANGLE_TYPE + "\r\n");
        out.write("AntiAlias : " + AntiAlias + "\r\n");
        out.write("LookAndFeel : " + lookAndFeel + "\r\n");

        writeFont(out, "angleNameFont", angleNameFont);
        writeFont(out, "nameFont", nameFont);
        writeFont(out, "font", font);
        writeFont(out, "smallnameFont", smallnameFont);
        writeFont(out, "button_label_font", button_label_font);
        writeFont(out, "thmFont", thmFont);
        writeFont(out, "gddFont", gddFont);
        writeFont(out, "fullFont", fullFont);
        writeFont(out, "areaFont", areaFont);
        writeFont(out, "manualFont", manualFont);
        writeFont(out, "fixFont", fixFont);
        writeFont(out, "algebraFont", algebraFont);


        out.close();
    }

    public static void writeFont(OutputStreamWriter out, String name, Font f) throws IOException {
        out.write(name + " : " + f.getName() + " " + f.getStyle() + " " + f.getSize() + "\r\n");
    }

    public static Font getFont(String s) {
        String[] ss = s.split(" ");
        int n1 = Integer.parseInt(ss[1].trim());
        int n2 = Integer.parseInt(ss[2].trim());
        return new Font(ss[0].trim(), n1, n2);
    }

    public static void LoadProperty(BufferedReader in) throws IOException {
        String s1 = in.readLine();
        while (s1 != null) {
            if (s1.length() > 0) {
                String[] sx = s1.split(":");
                String m1 = sx[0].trim();
                String m2 = sx[1].trim();

                if (m1.equalsIgnoreCase("ScreenX")) {
                    screenx = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("ScreenY")) {
                    screeny = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("FullScreen")) {
                    full_screen = Boolean.parseBoolean(m2);
                } else if (m1.equalsIgnoreCase("Language")) {
                    lan = m2;
                } else if (m1.equalsIgnoreCase("Alpha")) {
                    float f = Float.parseFloat(m2);
                    fillac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
                } else if (m1.equalsIgnoreCase("Radius")) {
                    POINT_RADIUS = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("GridColor")) {
                    GridColor = new Color(Integer.parseInt(m2));
                } else if (m1.equalsIgnoreCase("NameTextShown")) {
                    nameTextShown = Boolean.parseBoolean(m2);
                } else if (m1.equalsIgnoreCase("ShowAngleText")) {
                    show_angle_text = Boolean.parseBoolean(m2);
                } else if (m1.equalsIgnoreCase("ShowAngleType")) {
                    show_angle_type = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("FlashMoveStep")) {
                    FLASH_MOVE_STEP = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("FootMarkLength")) {
                    FOOT_MARK_LENGTH = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("FootMarkShown")) {
                    footMarkShown = Boolean.parseBoolean(m2);
                } else if (m1.equalsIgnoreCase("LookAndFeel")) {
                    lookAndFeel = (m2);
                } else if (m1.equals("AngleType")) {
                    ANGLE_TYPE = Integer.parseInt(m2);
                } else if (m1.equalsIgnoreCase("AntiAlias")) {
                    AntiAlias = Boolean.parseBoolean(m2);
                } else if (m1.equalsIgnoreCase("LookAndFeel")) {
                    lookAndFeel = (m2);
                } else if (m1.equalsIgnoreCase("angleNameFont")) {
                    angleNameFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("nameFont")) {
                    nameFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("font")) {
                    font = getFont(m2);
                } else if (m1.equalsIgnoreCase("smallnameFont")) {
                    smallnameFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("button_label_font")) {
                    button_label_font = getFont(m2);
                } else if (m1.equalsIgnoreCase("thmFont")) {
                    thmFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("gddFont")) {
                    gddFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("areaFont")) {
                    areaFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("manualFont")) {
                    manualFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("fixFont")) {
                    fixFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("fullFont")) {
                    fullFont = getFont(m2);
                } else if (m1.equalsIgnoreCase("algebraFont")) {
                    algebraFont = getFont(m2);
                } else
                    CMisc.print("Not Found: " + m1);
            }
            s1 = in.readLine();
        }
        in.close();
    }


    public static boolean TransComfirmed = false;

    public static void Save(DataOutputStream out) throws IOException {
        out.writeBoolean(nameTextShown);
        out.writeBoolean(show_angle_text);
        out.writeInt(show_angle_type);
        out.writeInt(POINT_RADIUS);

        float f = getFillCompositeAlpha();
        out.writeFloat(f);
        out.writeInt(FLASH_MOVE_STEP);
        out.writeBoolean(footMarkShown);
        out.writeInt(FOOT_MARK_LENGTH);
    }

    public static void Load(DataInputStream in) throws IOException {
        nameTextShown = in.readBoolean();
        show_angle_text = in.readBoolean();
        show_angle_type = in.readInt();
        POINT_RADIUS = in.readInt();

        float f = in.readFloat();
        setFillCompositeAlpha(f);
        FLASH_MOVE_STEP = in.readInt();
        footMarkShown = in.readBoolean();
        FOOT_MARK_LENGTH = in.readInt();
    }

}
