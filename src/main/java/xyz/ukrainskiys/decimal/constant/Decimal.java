package xyz.ukrainskiys.decimal.constant;

import java.math.BigDecimal;
import xyz.ukrainskiys.decimal.SimpleDecimal;

/**
 * <pre>{@code
 * Decimal.ZERO == 0
 * Decimal.ONE == 1
 * Decimal.TEN == 10
 * Decimal.HUNDRED == 100
 * Decimal.THOUSAND == 1000}</pre>
 */
public class Decimal {

  public static final SimpleDecimal ZERO = new SimpleDecimal(BigDecimal.ZERO);
  public static final SimpleDecimal ONE = new SimpleDecimal(BigDecimal.ONE);
  public static final SimpleDecimal TEN = new SimpleDecimal(BigDecimal.TEN);
  public static final SimpleDecimal HUNDRED = new SimpleDecimal(BigDecimal.valueOf(100));
  public static final SimpleDecimal THOUSAND = new SimpleDecimal(BigDecimal.valueOf(100));

}
