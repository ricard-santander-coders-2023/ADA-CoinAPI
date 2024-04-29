package com.dev.api.adacoinapi.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
public class CurrencyQuoteDTO {

  private String currencyCode;
  private String currencyName;
  private BigDecimal bid;
  private BigDecimal ask;
  private Instant timestamp;
}
