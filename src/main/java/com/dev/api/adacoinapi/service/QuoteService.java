package com.dev.api.adacoinapi.service;

import com.dev.api.adacoinapi.dto.CurrencyConversionDTO;
import com.dev.api.adacoinapi.model.CurrencyQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class QuoteService {

    private final RestTemplate restTemplate;

    @Autowired
    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, CurrencyQuote> getRealTimeQuotes(List<String> currencies) {
        String currenciesParam = String.join(",", currencies);
        return getQuotes(currenciesParam);
    }

    public CurrencyConversionDTO convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        String currencies = fromCurrency + "-" + toCurrency;
        Map<String, CurrencyQuote> response = getQuotes(currencies);
        CurrencyQuote quote = response.get(fromCurrency + toCurrency);
        BigDecimal convertedAmount = quote != null ? quote.getBid().multiply(amount) : BigDecimal.ZERO;
        return new CurrencyConversionDTO(fromCurrency, toCurrency, amount, convertedAmount);
    }

    public Map<String, CurrencyQuote> getQuotes(String currencies) {
        String url = "https://economia.awesomeapi.com.br/last/" + currencies;
        ResponseEntity<Map<String, CurrencyQuote>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, CurrencyQuote>>() {}
        );
        return response.getBody();
    }
}