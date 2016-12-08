package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: ${Yezheng}
 * Date: 2004-12-21
 * Time: 16:12:36
 * To change this template use File | Settings | File Templates.
 */
public class CTransform {
    private double dx = 0;
    private double dy = 0;
    private double sina = 0;
    private double cosa = 0;

    public CTransform()
    {}
    public void setTrans(double x, double y, double sx,double sy)
    {
        dx = x;
        dy = y;
        sina = sy;
        cosa = sx;
    }
    public double[] transWL(double x,double y)
    {

       double[] result = new double[2];
       result[0] -= dx;
       result[1] -=dy;
       result[0] = x*cosa - y*sina;
       result[1] = x*sina + y*cosa;

        return result;
    }
    public double transWLX(double x,double y)
    {
        x = x - dx;
        y = y - dy;
        return x*cosa -y*sina;
    }
    public double transWLY(double x,double y)
    {
        x = x - dx;
        y = y - dy;
        return x*sina + y*cosa;
    }

    public double[] transLW(double x,double y)
    {

        double[] result = new double[2];

        result[0] = x * cosa + y * sina;
        result[1] = - x * sina + y * cosa;
        result[0] += dx;
        result[1] += dy;
        return result;
    }
    public double transLWX(double x,double y)
    {
        return x*cosa + y*sina + dx;
    }
    public double transLWY(double x,double y)
    {
        return -x*sina + y*cosa + dy;
    }

}
