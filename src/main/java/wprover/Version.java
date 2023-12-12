package wprover;


public class Version {

    private static float version = 0.81f;
    private static String sversion = "0.81";
    private static String data = "2023-12-10";
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
