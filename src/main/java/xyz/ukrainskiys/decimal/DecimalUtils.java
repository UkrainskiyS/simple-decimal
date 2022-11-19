package xyz.ukrainskiys.decimal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static xyz.ukrainskiys.decimal.constant.Decimals.HUNDRED;
import static xyz.ukrainskiys.decimal.constant.Decimals.ZERO;

public class DecimalUtils {

    public static Decimal max(Decimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .max(Decimal::compareTo)
                .orElse(ZERO);
    }

    public static Decimal max(Collection<Decimal> decimals) {
        return max(decimals.toArray(Decimal[]::new));
    }


    public static Decimal min(Decimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .min(Decimal::compareTo)
                .orElse(ZERO);
    }

    public static Decimal min(Collection<Decimal> decimals) {
        return min(decimals.toArray(Decimal[]::new));
    }


    public static Decimal sum(Decimal... decimals) {
        return Arrays.stream(decimals)
                .filter(Objects::nonNull)
                .reduce(ZERO, Decimal::add);
    }

    public static Decimal sum(Collection<Decimal> decimals) {
        return sum(decimals.toArray(Decimal[]::new));
    }

    public static long toKopecks(Decimal decimal) {
        return decimal.multiply(HUNDRED).longValue();
    }

    public static Decimal safeGet(Decimal decimal) {
        return decimal == null ? ZERO : decimal;
    }

    public static BigDecimal safeGet(BigDecimal bigDecimal) {
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal;
    }

}
