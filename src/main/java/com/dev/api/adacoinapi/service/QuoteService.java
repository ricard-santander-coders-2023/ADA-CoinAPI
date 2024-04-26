package com.dev.api.adacoinapi.service;

import com.dev.api.adacoinapi.dto.CurrencyQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class QuoteService {

    private final RestTemplate restTemplate;

    @Autowired
    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, CurrencyQuote> getQuotes(String currencies) {
        String url = "https://economia.awesomeapi.com.br/last/" + currencies;
        return restTemplate.getForObject(url, Map.class);
    }
}

