package maths;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2007-5-28
 * Time: 22:11:39
 * To change this template use File | Settings | File Templates.
 */
public class GMono {
    public int x = 0;
    public int deg = 0;
    public BigFraction val = null;
    public TMono coef = null;
    public TMono next = null;

    public GMono() {

    }

    public GMono(int x, int val, int deg) {
        this.x = x;
        this.deg = deg;
        if (x == 0 || deg == 0) {
            this.x = 0;
            this.deg = 0;
            this.val = new BigFraction(val,1);
        } else {
            this.coef = new TMono(0, val, 0);
        }

    }

    public GMono(int x, TMono coef, int deg) {
        this.x = x;
        this.deg = deg;
        this.coef = coef;
    }
}
