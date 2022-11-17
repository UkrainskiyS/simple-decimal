package ukrainskiys.decimal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static ukrainskiys.decimal.Decimal.HUNDRED;

public class DecimalUtils {

    public static BigDecimal max(BigDecimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .max(BigDecimal::compareTo)
                .orElse(ZERO);
    }

    public static BigDecimal min(BigDecimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .min(BigDecimal::compareTo)
                .orElse(ZERO);
    }

    public static BigDecimal sum(BigDecimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .reduce(ZERO, BigDecimal::add);
    }

    public static BigDecimal sum(Collection<BigDecimal> decimals) {
        return sum(decimals.toArray(BigDecimal[]::new));
    }

    public static long toKopecks(BigDecimal decimal) {
        return decimal.multiply(HUNDRED).longValue();
    }

    public static BigDecimal fromKopecks(long kopecks) {
        return new BigDecimal(kopecks)
                .divide(HUNDRED, 2, HALF_UP);
    }

    public static BigDecimal valueOrZero(BigDecimal decimal) {
        return decimal == null ? ZERO : decimal;
    }

}
