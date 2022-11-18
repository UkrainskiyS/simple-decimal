package xyz.ukrainskiys.decimal.constant;

import java.math.BigDecimal;
import xyz.ukrainskiys.decimal.Decimal;

/**
 * <pre>{@code
 * Decimal.ZERO == 0
 * Decimal.ONE == 1
 * Decimal.TEN == 10
 * Decimal.HUNDRED == 100
 * Decimal.THOUSAND == 1000}</pre>
 */
public class Decimals {

  public static final Decimal ZERO = new Decimal(BigDecimal.ZERO);
  public static final Decimal ONE = new Decimal(BigDecimal.ONE);
  public static final Decimal TEN = new Decimal(BigDecimal.TEN);
  public static final Decimal HUNDRED = new Decimal(BigDecimal.valueOf(100));
  public static final Decimal THOUSAND = new Decimal(BigDecimal.valueOf(100));

}
