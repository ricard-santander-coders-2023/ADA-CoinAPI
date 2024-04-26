package com.dev.api.adacoinapi.controller;

import com.dev.api.adacoinapi.dto.CurrencyQuote;
import com.dev.api.adacoinapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{currencies}")
    public Map<String, CurrencyQuote> getQuotes(@PathVariable String currencies) {
        return quoteService.getQuotes(currencies);
    }
}
