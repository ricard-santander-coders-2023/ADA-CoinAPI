package com.dev.api.adacoinapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyConversionDTO {

  private String fromCurrency;
  private String toCurrency;
  private BigDecimal amount;
  private BigDecimal convertedAmount;

}
