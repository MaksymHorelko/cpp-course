package client;

import compute.Task;

import java.math.BigDecimal;

public class E implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int digits;

    public E(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computeE(digits);
    }
    public static BigDecimal computeE(int digits) {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal factorial = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;

        for (int i = 1; i <= digits; i++) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
            term = BigDecimal.ONE.divide(factorial, digits + 2, roundingMode);
            e = e.add(term);
        }

        return e.setScale(digits, roundingMode);
    }
}

