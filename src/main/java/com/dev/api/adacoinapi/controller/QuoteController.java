package com.dev.api.adacoinapi.controller;

import com.dev.api.adacoinapi.dto.CurrencyConversionDTO;
import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

  private final QuoteService quoteService;

  @Autowired
  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping
  public Map<String, CurrencyQuote> getRealTimeQuotes(@RequestParam List<String> currencies) {
    return quoteService.getRealTimeQuotes(currencies);
  }

  @GetMapping("/convert/{fromCurrency}/{toCurrency}/{amount}")
  public CurrencyConversionDTO convertCurrency(
      @PathVariable String fromCurrency,
      @PathVariable String toCurrency,
      @PathVariable BigDecimal amount) {
    return quoteService.convertCurrency(fromCurrency, toCurrency, amount);
  }
}
