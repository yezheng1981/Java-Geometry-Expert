package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-8-3
 * Time: 8:44:14
 * To change this template use File | Settings | File Templates.
 */
public class npResult {
    public CPoint pt;
    double x, y;
    public CPoint[] child = new CPoint[4];

    public void translate_back(double x1, double y1, double sin, double cos) {
        double t1, t2;
        sin = -sin;
        t1 = x;
        t2 = y;
        x = t1 * cos - t2 * sin;
        y = t1 * sin + t2 * cos;

        x += x1;
        y += y1;
    }
}
