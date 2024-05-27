package client;

import compute.Task;

import java.math.BigDecimal;
public class Pi implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    public BigDecimal execute() {
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale(digits, roundingMode);
    }

    public static BigDecimal arctan(int X, int scale) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal term;
        int i = 1;
        boolean add = false;

        do {
            term = BigDecimal.valueOf(X).divide(BigDecimal.valueOf(i), scale, roundingMode);
            result = add ? result.add(term) : result.subtract(term);
            add = !add;
            i += 2;
        } while (term.compareTo(BigDecimal.ZERO) != 0);

        return result;
    }
}
