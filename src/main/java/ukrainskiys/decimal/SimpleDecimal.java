package ukrainskiys.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс-обертка для BigDecimal
 */
public class SimpleDecimal extends BigDecimal {

    /**
     * <pre>{@code
     * Decimal.ZERO == 0
     * Decimal.ONE == 1
     * Decimal.TEN == 10
     * Decimal.HUNDRED == 100}</pre>
     */
    public static final SimpleDecimal ZERO = new SimpleDecimal(BigDecimal.ZERO);
    public static final SimpleDecimal ONE = new SimpleDecimal(BigDecimal.ONE);
    public static final SimpleDecimal TEN = new SimpleDecimal(BigDecimal.TEN);
    public static final SimpleDecimal HUNDRED = new SimpleDecimal(BigDecimal.valueOf(100));

    /**
     * <pre>{@code
     * new Decimal() == 0
     * new Decimal(BigDecimal.valueOf(666)) == 666
     * Decimal.of(666) == 666
     * Decimal.of(6.66) == 6.66
     * Decimal.ofKopecks(12345) == 123.45}</pre>
     */
    public SimpleDecimal(BigDecimal bigDecimal) {
        super(bigDecimal.toString());
    }

    public SimpleDecimal() {
        super(BigDecimal.ZERO.toString());
    }

    public static SimpleDecimal of(int value) {
        return new SimpleDecimal(BigDecimal.valueOf(value));
    }

    public static SimpleDecimal of(long value) {
        return new SimpleDecimal(BigDecimal.valueOf(value));
    }

    public static SimpleDecimal of(double value) {
        return new SimpleDecimal(BigDecimal.valueOf(value));
    }

    public static SimpleDecimal of(String value) {
        return new SimpleDecimal(new BigDecimal(value));
    }

    public static SimpleDecimal of(BigDecimal value) {
        return new SimpleDecimal(DecimalUtils.safeGet(value));
    }

    public static SimpleDecimal ofKopecks(long kopecks) {
        return new SimpleDecimal(BigDecimal.valueOf(kopecks)).safeDivide(100);
    }

    /**
     * <pre>{@code
     * Decimal.HUNDRED.more(Decimal.TEN) == true
     * Decimal.HUNDRED.less(Decimal.TEN) == false
     * Decimal.HUNDRED.more(100) == false}</pre>
     */
    public boolean more(BigDecimal other) {
        return compareTo(DecimalUtils.safeGet(other)) > 0;
    }

    public boolean more(int value) {
        return compareTo(BigDecimal.valueOf(value)) > 0;
    }

    public boolean more(long value) {
        return compareTo(BigDecimal.valueOf(value)) > 0;
    }

    public boolean more(double value) {
        return compareTo(BigDecimal.valueOf(value)) > 0;
    }

    public boolean more(String value) {
        return compareTo(new BigDecimal(value)) > 0;
    }

    public boolean less(BigDecimal other) {
        return compareTo(DecimalUtils.safeGet(other)) < 0;
    }

    public boolean less(int value) {
        return compareTo(BigDecimal.valueOf(value)) < 0;
    }

    public boolean less(long value) {
        return compareTo(BigDecimal.valueOf(value)) < 0;
    }

    public boolean less(double value) {
        return compareTo(BigDecimal.valueOf(value)) < 0;
    }

    public boolean less(String value) {
        return compareTo(new BigDecimal(value)) < 0;
    }

    public SimpleDecimal max(SimpleDecimal value) {
        return of(super.max(value));
    }


    public SimpleDecimal add(BigDecimal other) {
        return new SimpleDecimal(super.add(DecimalUtils.safeGet(other)));
    }

    public SimpleDecimal add(int value) {
        return add(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal add(long value) {
        return add(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal add(double value) {
        return add(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal add(String value) {
        return add(SimpleDecimal.of(new BigDecimal(value)));
    }


    public SimpleDecimal subtract(BigDecimal other) {
        return new SimpleDecimal(super.subtract(DecimalUtils.safeGet(other)));
    }

    public SimpleDecimal subtract(int value) {
        return subtract(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal subtract(long value) {
        return subtract(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal subtract(double value) {
        return subtract(SimpleDecimal.of(BigDecimal.valueOf(value)));
    }

    public SimpleDecimal subtract(String value) {
        return subtract(SimpleDecimal.of(new BigDecimal(value)));
    }


    public SimpleDecimal multiply(BigDecimal other) {
        return new SimpleDecimal(super.multiply(DecimalUtils.safeGet(other)));
    }

    public SimpleDecimal multiply(int value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public SimpleDecimal multiply(long value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public SimpleDecimal multiply(double value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public SimpleDecimal multiply(String value) {
        return multiply(new BigDecimal(value));
    }

    /**
     * <pre>{@code
     * Decimal.HUNDRED.safeDivide(10) == 10
     * Decimal.HUNDRED.safeDivide(33) == 3.0303030303}</pre>
     */
    public SimpleDecimal safeDivide(BigDecimal other) {
        final var result = this.divide(DecimalUtils.safeGet(other), 11, RoundingMode.HALF_UP);
        try {
            final var afterDot =  result.toString().split("\\.")[1].toCharArray();
            int count = afterDot.length - 1;
            while (count >= 0) {
                if (afterDot[count] != '0') {
                    break;
                }
                count--;
            }
            return new SimpleDecimal(result.setScale(++count, RoundingMode.HALF_UP));
        } catch (ArrayIndexOutOfBoundsException e) {
            return SimpleDecimal.ZERO;
        }
    }

    public SimpleDecimal safeDivide(int value) {
        return safeDivide(SimpleDecimal.of(value));
    }

    public SimpleDecimal safeDivide(long value) {
        return safeDivide(SimpleDecimal.of(value));
    }

    public SimpleDecimal safeDivide(double value) {
        return safeDivide(SimpleDecimal.of(value));
    }

    public SimpleDecimal safeDivide(String value) {
        return safeDivide(SimpleDecimal.of(value));
    }

    /**
     * <pre>{@code
     * Decimal.of(200).ofPercent(50) == 100}</pre>
     */
    public SimpleDecimal ofPercent(int percent) {
        return multiply(percent).safeDivide(SimpleDecimal.HUNDRED);
    }

    /**
     * <pre>{@code
     * Decimal.of(10).is(BigDecimal.TEN) == true
     * Decimal.of(10.00).is(10) == true}</pre>
     */
    public boolean is(BigDecimal other) {
        return this.compareTo(DecimalUtils.safeGet(other)) == 0;
    }

    public boolean is(int value) {
        return intValue() == value;
    }

    public boolean is(double value) {
        return doubleValue() == value;
    }

    public boolean is(long value) {
        return longValue() == value;
    }

    /**
     * <pre>{@code
     * Decimal.of(3.0303030303).safeSetScale(1) == 3.0
     * Decimal.of(3.0303030303).setScaleWithKopecks() == 3.03}</pre>
     */
    public SimpleDecimal safeSetScale(int scale) {
        return new SimpleDecimal(setScale(scale, RoundingMode.HALF_UP));
    }

    public SimpleDecimal setScaleWithKopecks() {
        return new SimpleDecimal(safeSetScale(2));
    }

}
