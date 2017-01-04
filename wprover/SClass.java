package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-1-14
 * Time: 11:37:39
 * To change this template use File | Settings | File Templates.
 */
public class SClass {

    public static String[][] ActionString=
    {   // command ,            imagename,          tooltips ,                          altText
        {"new",                 "new",              "start new view to construction",   "New"},
        {"save",                "save",             "save therom",                      "save"},
        {"load",                "load",             "load new therom",                  "Up"},

        {"",                    "Separator"},

        {"select",              "select",           "select mode",                      "Select"},
        {"single-add",          "single-add",       "add a single point",               "Single-add"},
        {"multi-add-middle",    "middle",           "Forward to something-or-other",    "Next"},
        {"multi-add-line",      "multi-add-line",   "draw connecting lines",            "Multi-add-line"},
        {"parallel",            "parallel",         "draw parallel line",               "parallel"},
        {"parallel",            "parallel",         "draw parallel line",               "parallel"},
        {"perp",                "perp",             "draw perpdicular line",            "perp"},
        {"perp2",               "perp2",            "draw perpdicular line with a foot","perp2"},
        {"multi-add-circle",    "multi-add-circle", "draw two points and a circle",     "Multi-add-circle"},
        {"circle-by-radius",    "circle-by-radius", "draw a circle around a point",     "circle-by-radius"},
        {"circle-by-3",         "circle-by-3",      "draw a circle around a point",     "circle-by-3"},
        {"",                    "Separator"},
        {"meet",                "meet",             "meet to decide a point",           "Meet"},
        {"empty",               "empty",            "empty the view",                   "empty"},
        {"",                    "Separator"},
        {"animation",           "animation",        "animate construction",             "Animation"}

    };
    
    public static String[][] RightToolBarString =
            {
                {"printps",     "printPS",      "generate postscript code",     "Printps"},
                {"",            "Separator"},
                {"translate",   "translate",    "translate view",               "Translate"},
                {"zoom-in",     "zoom-in",      "Zoom in view",                 "Zoom-in"},
                {"zoom-out",    "zoom-out",     "Zoom out view",                "Zoom-out"},
                {"fetch",       "fetch",        "Zoom to view all points",      "Fetch"},
                {"lessmesh",    "lessMesh",     "make the mesh less dense",     "LessMesh"},
                {"moremesh",    "moreMesh",     "make the mesh more dense",     "MoreMesh"},
                {"axes",        "axes",         "draw coordinate axes",         "Axes"},
                {"mesh",        "mesh",         "draw the rectangle grid",      "Mesh"},
                {"snap",        "snap",         "snap to the grid",             "Snap"}
            };
    
    public static String[][] MenuString =
            {
                {"Open",    "Open..",  "Open a file to read.."},
                {"Exit",    "Exit",    "Exit the program" },

            };
    public static String[][] BasicPopupMenu =
            {
                {"select",      "Select",       "select mode"},
                {"",            "Separator"},
                {"translate",   "Translate",    "translate view"},
                {"zoom-in",     "Zoom-in",      "Zoom in view"},
                {"zoom-out",    "Zoom-out",     "Zoom out view"},
                {"fetch",       "Fetch",        "Zoom to view all points"},
                {"",            "Separator"},
                {"property",    "Property",     "the property of the view"}
            } ;
    public static String[][] ActionTip =
            {
                {"Select",          "Select element by click on them"},
                {"single-add",      "Add a single point by click the mouse"},
                {"multi-add-middle","Construct two point to define their middle point"},
                {"multi-add-line",  "Construct two point to define a line"},
                {"parallel",        "Select a line and drag to define a parallel line"},
                {"perp",            "Select a line and drag to define a perpedicular line"},
                {"perp2",           "Drag to a line to define a perpedicular line"},
                {"multi-add-circle","Construct two point to define a circle "},
                {"circle-by-radius","Select two points and"},
                {"circle-by-3",     "Select three points and construct a circle"},
                {"angle",           "Select two lines to define a angle"},
                {"polygon",         "Mouse Click to construct  polygon"},
                {"text",            "Click to set the location of the text"},
                {"meet",            "Select two elements to define their meet point"},
                {"mirror",          "Select two element to define mirror"},
                {"clear",           "Clear all elements"},
                {"track",           "Select a point and an element as contrail"},
                {"eqangle",         "Select two angles to define equation"},
                {"square",          "Drag a line to define a square"},
                {"iso_angle",       "Drag a line to define an isoceles triangle"},
                {"pfoot",           "Select two points to define a perpedicular foot"},
                {"defpoly",         "Click points to define a polygon"},
                {"ccline",          "Select two circles to define their common line"}
            };
}
