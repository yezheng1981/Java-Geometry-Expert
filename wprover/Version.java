package wprover;


public class Version {

    private static float version = 0.80f;
    private static String sversion = "0.80";
    private static String data = " 05/25/2009 ";
    private static String project = "Geometry Expert";


    public static final float getVersionf() {
        return version;
    }

    public static final String getVersion1() {
        return sversion;
    }

    public static final String getNameAndVersion() {
        return project + " " + sversion;
    }

    public static final String getVersion() {
        return " " + project + " ";
    }

    public static final String getProject() {
        return project;
    }

    public static final String getData() {
        return data;
    }
}
