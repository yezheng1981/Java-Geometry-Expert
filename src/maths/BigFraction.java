package maths;

import java.io.Serializable;
import java.math.BigInteger;
import java.math.BigDecimal;


public class BigFraction implements Cloneable, Comparable, Serializable {
    protected final BigInteger numerator_;
    protected final BigInteger denominator_;

    final public static BigFraction ZERO = new BigFraction("0/1");
    final public static BigFraction ONE = new BigFraction("1/1");


    public long intNumerator() {
        return numerator_.intValue();
    }

    public long intDenominator() {
        return denominator_.intValue();
    }

    public BigInteger numerator() {
        return numerator_;
    }

    public BigInteger denominator() {
        return denominator_;
    }

    public BigFraction(BigInteger num, BigInteger den) {

        boolean numNonnegative = gteq(num, BigInteger.ZERO);
        boolean denNonnegative = gteq(den, BigInteger.ZERO);
        BigInteger a = numNonnegative ? num : num.negate();
        BigInteger b = denNonnegative ? den : den.negate();
        BigInteger g = a.gcd(b);
        if (numNonnegative == denNonnegative) {
            numerator_ = a.divide(g);
        } else {
            numerator_ = a.negate().divide(g);
        }
        denominator_ = b.divide(g);
    }


    public BigFraction(long n) {
        this(BigInteger.valueOf(n), BigInteger.valueOf(1));
    }

    public BigFraction(BigInteger b) {
        this(b, BigInteger.valueOf(1));
    }

    public BigFraction(BigFraction f) {
        numerator_ = f.numerator();
        denominator_ = f.denominator();
    }

    public BigFraction(String s) {
        this(new BigInteger(s.substring(0, s.indexOf('/'))),
                new BigInteger(s.substring(s.indexOf('/') + 1)));
    }

    public BigFraction(long num, long den) {
        this(new BigInteger(Long.toString(num)),
                new BigInteger(Long.toString(den)));
    }

    //------------------
    // Override toString
    //------------------

    public String toString() {
        BigInteger b2 = denominator();

        if (b2.compareTo(BigInteger.ONE) != 0)
            return numerator().toString() + "/" + denominator().toString();
        else
            return numerator().toString();
    }

    //--------------------------------
    // Required to implement Cloneable
    //--------------------------------

    public Object clone() {
        return new BigFraction(this);
    }

    //----------------------------
    // Utility comparison routines
    //----------------------------

    private boolean gt(BigInteger x, BigInteger y) {
        return x.compareTo(y) > 0;
    }

    private boolean gteq(BigInteger x, BigInteger y) {
        return x.compareTo(y) >= 0;
    }

    private boolean gteq(BigInteger y) {
        return numerator_.compareTo(y) >= 0;
    }

    private boolean lt(BigInteger x, BigInteger y) {
        return x.compareTo(y) < 0;
    }

    private boolean lteq(BigInteger x, BigInteger y) {
        return x.compareTo(y) <= 0;
    }

    //------------
    // Get minimum
    //------------

    public BigFraction min(BigFraction val) {
        if (compareTo(val) <= 0) {
            return this;
        } else {
            return val;
        }
    }

    //------------
    // Get maximum
    //------------

    public BigFraction max(BigFraction val) {
        if (compareTo(val) > 0) {
            return this;
        } else {
            return val;
        }
    }

    //-------------------------------------------------------
    // Convert to BigDecimal
    // Rounding mode is any of BigDecimal.ROUND_xxx constants
    //-------------------------------------------------------

    public BigDecimal asBigDecimal(int scale, int roundingMode) {
        BigDecimal num = new BigDecimal(numerator());
        BigDecimal den = new BigDecimal(denominator());
        return num.divide(den, scale, roundingMode);
    }

    //------------------
    // Get negated value
    //------------------

    public BigFraction negate() {
        return new BigFraction(numerator().negate(), denominator());
    }

    //---------------------------
    // Get multiplicative inverse
    //---------------------------

    public BigFraction inverse() {
        return new BigFraction(denominator(), numerator());
    }

    //----
    // Add
    //----

    public BigFraction pow(int d) {
        BigInteger an = numerator();
        BigInteger ad = denominator();
        return new BigFraction(an.pow(d), (ad.pow(d)));
    }

    public BigFraction add(BigFraction b) {
        BigInteger an = numerator();
        BigInteger ad = denominator();
        BigInteger bn = b.numerator();
        BigInteger bd = b.denominator();
        return new BigFraction(an.multiply(bd).add(bn.multiply(ad)), ad.multiply(bd));
    }

    public BigFraction add(BigInteger n) {
        return add(new BigFraction(n, BigInteger.ONE));
    }

    public BigFraction add(long n) {
        return add(new BigInteger(Long.toString(n)));
    }

    //---------
    // Subtract
    //---------

    public BigFraction subtract(BigFraction b) {
        BigInteger an = numerator();
        BigInteger ad = denominator();
        BigInteger bn = b.numerator();
        BigInteger bd = b.denominator();
        return new BigFraction(an.multiply(bd).subtract(bn.multiply(ad)), ad.multiply(bd));
    }

    public BigFraction subtract(BigInteger n) {
        return subtract(new BigFraction(n, BigInteger.ONE));
    }

    public BigFraction subtract(long n) {
        return subtract(new BigInteger(Long.toString(n)));
    }

    //---------
    // Multiply
    //---------

    public BigFraction multiply(BigFraction b) {
        BigInteger an = numerator();
        BigInteger ad = denominator();
        BigInteger bn = b.numerator();
        BigInteger bd = b.denominator();
        return new BigFraction(an.multiply(bn), ad.multiply(bd));
    }

    public BigFraction multiply(BigInteger n) {
        return multiply(new BigFraction(n, BigInteger.ONE));
    }

    public BigFraction multiply(long n) {
        return multiply(new BigInteger(Long.toString(n)));
    }

    //-------
    // Divide
    //-------

    public BigFraction divide(BigFraction b) {
        BigInteger an = numerator();
        BigInteger ad = denominator();
        BigInteger bn = b.numerator();
        BigInteger bd = b.denominator();
        return new BigFraction(an.multiply(bd), ad.multiply(bn));
    }

    public BigFraction divide(BigInteger n) {
        return divide(new BigFraction(n, BigInteger.ONE));
    }

    public BigFraction divide(long n) {
        return divide(new BigInteger(Long.toString(n)));
    }

    public BigFraction sqrt() {
        if (!gteq(BigInteger.ZERO))
            return null;

        BigInteger an = numerator();
        BigInteger ad = denominator();
        an = BigSquareRoot.sqrtI(an);
        ad = BigSquareRoot.sqrtI(ad);
        if (an != null && ad != null)
            return new BigFraction(an, ad);

        return null;
    }
    //---------------------------------
    // Required to implement Comparable
    //---------------------------------

    public boolean isZero() {
        return numerator().compareTo(BigInteger.ZERO) == 0;
    }

    public int compareTo(Object other) {
        BigFraction b = (BigFraction) (other);
        BigInteger an = numerator();
        BigInteger ad = denominator();
        BigInteger bn = b.numerator();
        BigInteger bd = b.denominator();
        BigInteger left = an.multiply(bd);
        BigInteger right = bn.multiply(ad);
        if (lt(left, right)) {
            return -1;
        }
        if (left.equals(right)) {
            return 0;
        } else {
            return 1;
        }
    }

    public int compareTo(BigInteger n) {
        Object obj = new BigFraction(n, BigInteger.ONE);
        return compareTo(obj);
    }

    //----------------
    // Override equals
    //----------------

    public boolean equals(Object other) {
        return compareTo((BigFraction) other) == 0;
    }

    public boolean equals(BigInteger n) {
        return compareTo(n) == 0;
    }

    public boolean equals(long n) {
        return equals(new BigInteger(Long.toString(n)));
    }

    //------------------
    // Override hashCode
    //------------------

    public int hashCode() {
        int num = numerator().intValue();
        int den = denominator().intValue();
        return num ^ den;
    }
}
