package com.dev.api.adacoinapi.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CurrencyComparisonDTO {



  private String currencyCode;
  private String currencyName;
  private BigDecimal currentBid;
  private BigDecimal currentAsk;
  private BigDecimal previousBid;
  private BigDecimal previousAsk;
}
