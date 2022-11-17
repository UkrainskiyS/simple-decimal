package ukrainskiys.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * �����-�������, ����������� ����������� BigDecimal
 */
public class Decimal extends BigDecimal {

    /**
     * <pre>{@code
     * Decimal.ZERO == 0
     * Decimal.ONE == 1
     * Decimal.TEN == 10
     * Decimal.HUNDRED == 100}</pre>
     */
    public static final Decimal ZERO = new Decimal(BigDecimal.ZERO);
    public static final Decimal ONE = new Decimal(BigDecimal.ONE);
    public static final Decimal TEN = new Decimal(BigDecimal.TEN);
    public static final Decimal HUNDRED = new Decimal(BigDecimal.valueOf(100));

    /**
     * <pre>{@code
     * new Decimal() == 0
     * new Decimal(BigDecimal.valueOf(666)) == 666
     * Decimal.of(666) == 666
     * Decimal.of(6.66) == 6.66
     * Decimal.ofKopecks(12345) == 123.45}</pre>
     */
    public Decimal(BigDecimal bigDecimal) {
        super(bigDecimal.toString());
    }

    public Decimal() {
        super(BigDecimal.ZERO.toString());
    }

    public static Decimal of(int value) {
        return new Decimal(BigDecimal.valueOf(value));
    }

    public static Decimal of(long value) {
        return new Decimal(BigDecimal.valueOf(value));
    }

    public static Decimal of(double value) {
        return new Decimal(BigDecimal.valueOf(value));
    }

    public static Decimal of(String value) {
        return new Decimal(new BigDecimal(value));
    }

    public static Decimal of(BigDecimal value) {
        return new Decimal(DecimalUtils.valueOrZero(value));
    }

    public static Decimal ofKopecks(long kopecks) {
        return new Decimal(BigDecimal.valueOf(kopecks)).safeDivide(100);
    }

    /**
     * <pre>{@code
     * Decimal.HUNDRED.more(Decimal.TEN) == true
     * Decimal.HUNDRED.less(Decimal.TEN) == false
     * Decimal.HUNDRED.more(100) == false}</pre>
     */
    public boolean more(BigDecimal other) {
        return compareTo(DecimalUtils.valueOrZero(other)) > 0;
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
        return compareTo(DecimalUtils.valueOrZero(other)) < 0;
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

    public Decimal max(Decimal value) {
        return of(super.max(value));
    }


    public Decimal add(BigDecimal other) {
        return new Decimal(super.add(DecimalUtils.valueOrZero(other)));
    }

    public Decimal add(int value) {
        return add(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal add(long value) {
        return add(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal add(double value) {
        return add(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal add(String value) {
        return add(Decimal.of(new BigDecimal(value)));
    }


    public Decimal subtract(BigDecimal other) {
        return new Decimal(super.subtract(DecimalUtils.valueOrZero(other)));
    }

    public Decimal subtract(int value) {
        return subtract(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal subtract(long value) {
        return subtract(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal subtract(double value) {
        return subtract(Decimal.of(BigDecimal.valueOf(value)));
    }

    public Decimal subtract(String value) {
        return subtract(Decimal.of(new BigDecimal(value)));
    }


    public Decimal multiply(BigDecimal other) {
        return new Decimal(super.multiply(DecimalUtils.valueOrZero(other)));
    }

    public Decimal multiply(int value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public Decimal multiply(long value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public Decimal multiply(double value) {
        return multiply(BigDecimal.valueOf(value));
    }

    public Decimal multiply(String value) {
        return multiply(new BigDecimal(value));
    }

    /**
     * <pre>{@code
     * Decimal.HUNDRED.safeDivide(10) == 10
     * Decimal.HUNDRED.safeDivide(33) == 3.0303030303}</pre>
     */
    public Decimal safeDivide(BigDecimal other) {
        final var result = this.divide(DecimalUtils.valueOrZero(other), 11, RoundingMode.HALF_UP);
        try {
            final var afterDot =  result.toString().split("\\.")[1].toCharArray();
            int count = afterDot.length - 1;
            while (count >= 0) {
                if (afterDot[count] != '0') {
                    break;
                }
                count--;
            }
            return new Decimal(result.setScale(++count, RoundingMode.HALF_UP));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Decimal.ZERO;
        }
    }

    public Decimal safeDivide(int value) {
        return safeDivide(Decimal.of(value));
    }

    public Decimal safeDivide(long value) {
        return safeDivide(Decimal.of(value));
    }

    public Decimal safeDivide(double value) {
        return safeDivide(Decimal.of(value));
    }

    public Decimal safeDivide(String value) {
        return safeDivide(Decimal.of(value));
    }

    /**
     * <pre>{@code
     * Decimal.of(200).ofPercent(50) == 100}</pre>
     */
    public Decimal ofPercent(int percent) {
        return multiply(percent).safeDivide(Decimal.HUNDRED);
    }

    /**
     * <pre>{@code
     * Decimal.of(10).is(BigDecimal.TEN) == true
     * Decimal.of(10.00).is(10) == true}</pre>
     */
    public boolean is(BigDecimal other) {
        return this.compareTo(DecimalUtils.valueOrZero(other)) == 0;
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
    public Decimal safeSetScale(int scale) {
        return new Decimal(setScale(scale, RoundingMode.HALF_UP));
    }

    public Decimal setScaleWithKopecks() {
        return new Decimal(safeSetScale(2));
    }

}
