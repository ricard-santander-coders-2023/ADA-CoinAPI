package com.dev.api.service;

import com.dev.api.adacoinapi.dto.CurrencyConversionDTO;
import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.repository.CurrencyQuoteRepository;
import com.dev.api.adacoinapi.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class QuoteServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyQuoteRepository currencyQuoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteService = new QuoteService(restTemplate, currencyQuoteRepository);
    }

    @Test
    void testGetRealTimeQuotes() {
        CurrencyQuote mockQuote = new CurrencyQuote();
        mockQuote.setCurrencyCode("USD-BRL");
        mockQuote.setCurrencyName("US Dollar to Brazilian Real");
        mockQuote.setBid(new BigDecimal("5.20"));
        mockQuote.setAsk(new BigDecimal("5.25"));

        Map<String, CurrencyQuote> expectedQuotes = Collections.singletonMap("USD-BRL", mockQuote);
        ResponseEntity<Map<String, Map<String, Object>>> responseEntity = ResponseEntity.ok(Collections.singletonMap("USD-BRL", Map.of(
                "code", "USD-BRL",
                "name", "US Dollar to Brazilian Real",
                "bid", "5.20",
                "ask", "5.25"
        )));

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(),
                eq(new ParameterizedTypeReference<Map<String, Map<String, Object>>>() {})))
                .thenReturn(responseEntity);

        List<String> currencies = Collections.singletonList("USD-BRL");
        Map<String, CurrencyQuote> quotes = quoteService.getRealTimeQuotes(currencies);

        assertEquals(expectedQuotes.size(), quotes.size());
        assertEquals(expectedQuotes.get("USD-BRL").getCurrencyCode(), quotes.get("USD-BRL").getCurrencyCode());
        assertEquals(expectedQuotes.get("USD-BRL").getCurrencyName(), quotes.get("USD-BRL").getCurrencyName());
        assertEquals(expectedQuotes.get("USD-BRL").getBid(), quotes.get("USD-BRL").getBid());
        assertEquals(expectedQuotes.get("USD-BRL").getAsk(), quotes.get("USD-BRL").getAsk());
    }

    @Test
    void testConvertCurrency() {
        Map<String, Map<String, Object>> quotes = new HashMap<>();
        Map<String, Object> currencyQuoteData = new HashMap<>();
        currencyQuoteData.put("code", "BTCUSD");
        currencyQuoteData.put("name", "Bitcoin to US Dollar");
        currencyQuoteData.put("bid", "68685");
        currencyQuoteData.put("ask", "68700");
        quotes.put("BTCUSD", currencyQuoteData);

        ResponseEntity<Map<String, Map<String, Object>>> responseEntity = ResponseEntity.ok(quotes);

        // Mock da resposta da api
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(),
                eq(new ParameterizedTypeReference<Map<String, Map<String, Object>>>() {})))
                .thenReturn(responseEntity);

        String fromCurrency = "BTC";
        String toCurrency = "USD";
        BigDecimal amount = BigDecimal.valueOf(10.0);
        CurrencyConversionDTO conversionDTO = quoteService.convertCurrency(fromCurrency, toCurrency, amount);

        BigDecimal expectedConvertedAmount = BigDecimal.valueOf(686850); // Expected conversion: 10 * 68685
        assertEquals(0, expectedConvertedAmount.compareTo(conversionDTO.getConvertedAmount()));
    }

}
