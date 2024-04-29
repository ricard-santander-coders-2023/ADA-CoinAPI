package com.dev.api.adacoinapi.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import lombok.Data;

@Data
public class CurrencyQuoteDTO {


  private Long id;
  private String currencyCode;
  private String currencyName;
  private BigDecimal bid;
  private BigDecimal ask;
  private Instant timestamp;

  public CurrencyQuoteDTO(CurrencyQuote quote) {
    this.id = quote.getId();
    this.currencyCode = quote.getCurrencyCode();
    this.currencyName = quote.getCurrencyName();
  }

}
