package xyz.ukrainskiys.decimal.converter;

import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import xyz.ukrainskiys.decimal.Decimal;

@Converter(autoApply = true)
public class DecimalConverter implements AttributeConverter<Decimal, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(Decimal decimal) {
    return decimal.getBigDecimal();
  }

  @Override
  public Decimal convertToEntityAttribute(BigDecimal bigDecimal) {
    return new Decimal(bigDecimal);
  }

}
