package maths;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigSquareRoot {

    private static BigDecimal ZERO = BigDecimal.ZERO;
    private static BigDecimal ONE = BigDecimal.ONE;
    private static BigDecimal TWO = new BigDecimal("2");
    private static BigDecimal THREE = new BigDecimal("3");
    public static final int DEFAULT_MAX_ITERATIONS = 50;
    public static final int DEFAULT_SCALE = 10;

    private static int maxIterations = DEFAULT_MAX_ITERATIONS;

    //---------------------------------------
    // The error is the original number minus
    // (sqrt * sqrt). If the original number
    // was a perfect square, the error is 0.
    //---------------------------------------

    //-------------------
    // Maximum iterations
    //-------------------

    private static int getMaxIterations() {
        return maxIterations;
    }

    private static void setMaxIterations(int maxIterations) {
        maxIterations = maxIterations;
    }

    //--------------------------
    // Get initial approximation
    //--------------------------

    private static BigDecimal getInitialApproximation(BigDecimal n) {
        BigInteger integerPart = n.toBigInteger();
        int length = integerPart.toString().length();
        if ((length % 2) == 0) {
            length--;
        }
        length /= 2;
        BigDecimal guess = ONE.movePointRight(length);
        return guess;
    }

    //----------------
    // Get square root
    //----------------

    public static BigDecimal get(BigInteger n) {
        return get(new BigDecimal(n));
    }

    private static BigDecimal get(BigDecimal n) {

        // Make sure n is a positive number

        if (n.compareTo(ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal initialGuess = getInitialApproximation(n);
        BigDecimal lastGuess = ZERO;
        BigDecimal guess = new BigDecimal(initialGuess.toString());

        int iterations = 0;
        int scale = DEFAULT_SCALE;
        int length = n.toString().length();
        if (length > 20)
            scale = (length / 2);

        // Iterate

        iterations = 0;
        boolean more = true;
        BigDecimal error;
        while (more) {
            lastGuess = guess;
            guess = n.divide(guess, scale, BigDecimal.ROUND_HALF_UP);
            guess = guess.add(lastGuess);
            guess = guess.divide(TWO, scale, BigDecimal.ROUND_HALF_UP);
            error = n.subtract(guess.multiply(guess));
            if (++iterations >= maxIterations) {
                more = false;
            } else if (lastGuess.equals(guess)) {
                more = error.abs().compareTo(ONE) >= 0;
            }
        }
        return guess;

    }

    //----------------------
    // Get random BigInteger
    //----------------------

    private static BigInteger getRandomBigInteger(int nDigits) {
        StringBuffer sb = new StringBuffer();
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < nDigits; i++) {
            sb.append(r.nextInt(10));
        }
        return new BigInteger(sb.toString());
    }

    //-----
    // Test
    //-----

//    public static void main(String[] args) {
//
//        BigInteger n;
//        BigDecimal sqrt;
//        BigSquareRoot app = new BigSquareRoot();
//        // Generate a random big integer with a hundred digits
//
//        n = BigSquareRoot.getRandomBigInteger(100);
//
//        // Build an array of test numbers
//
//        String testNums[] = {"9", "30", "720", "1024", n.multiply(n).toString()};
//
//        for (int i = 0; i < testNums.length; i++) {
//            n = new BigInteger(testNums[i]);
//            if (i > 0) {
//                System.out.println("----------------------------");
//            }
//            System.out.println("Computing the square root of");
//            System.out.println(n.toString());
//            int length = n.toString().length();
//            if (length > 20) {
//                app.setScale(length / 2);
//            }
//            sqrt = app.get(n);
//            BigInteger dd = sqrt.multiply(sqrt).toBigInteger().subtract(n);
//            System.out.println("Iterations " + app.getIterations());
//            System.out.println("Sqrt " + sqrt.toString());
//            System.out.println(sqrt.multiply(sqrt).toString());
//            System.out.println(n.toString());
//            System.out.println("Error " + app.getError().toString());
//        }
//
//    }

    public static BigDecimal sqrt(BigInteger b) {

        return get(b);
    }

    public static BigInteger sqrtI(BigInteger b) {
        BigDecimal b1 = sqrt(b);
        BigInteger b2 = b1.toBigInteger();
        if (b2.multiply(b2).subtract(b).compareTo(BigInteger.ZERO) == 0)
            return b2;
        return null;
    }

//    Newton Method to get the cube root on BigIntegers :

    public static BigDecimal CubeRoot(BigDecimal B, int iter) {                            //?????????????
        BigDecimal[] cubique = new BigDecimal[2];
        cubique[0] = B.divide(THREE);
        for (int i = 0; i < iter; i++) {
            cubique[1] = ((cubique[0].multiply(TWO)).add(B.divide(cubique[0].pow(2)))).divide(THREE);
            cubique[0] = cubique[1];
        }
        return cubique[0];
    }


}
