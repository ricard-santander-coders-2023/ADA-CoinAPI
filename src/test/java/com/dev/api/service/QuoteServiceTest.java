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
    private CurrencyQuoteRepository currencyQuoteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteService = new QuoteService(restTemplate, currencyQuoteRepository);
    }

    @Test
    void testGetRealTimeQuotes() {
        // Mocking the restTemplate.exchange() method
        Map<String, CurrencyQuote> expectedQuotes = Collections.singletonMap("USD-BRL", new CurrencyQuote());
        ResponseEntity<Map<String, CurrencyQuote>> responseEntity = ResponseEntity.ok(expectedQuotes);

        // Explicitly specify the type for the ParameterizedTypeReference
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(),
                eq(new ParameterizedTypeReference<Map<String, CurrencyQuote>>() {})))
                .thenReturn(responseEntity);

        // Calling the method under test
        List<String> currencies = Collections.singletonList("USD-BRL");
        Map<String, CurrencyQuote> quotes = quoteService.getRealTimeQuotes(currencies);

        // Asserting the result
        assertEquals(expectedQuotes, quotes);
    }

    @Test
    void testConvertCurrency() {
        // Mocking the restTemplate.exchange() method
        Map<String, CurrencyQuote> quotes = new HashMap<>();
        CurrencyQuote currencyQuote = new CurrencyQuote();
        currencyQuote.setBid(BigDecimal.valueOf(5.0)); // Mocking a bid rate of 5.0
        quotes.put("USD-BRL", currencyQuote);
        ResponseEntity<Map<String, CurrencyQuote>> responseEntity = ResponseEntity.ok(quotes);

        // Explicitly specify the type for the ParameterizedTypeReference
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(),
                eq(new ParameterizedTypeReference<Map<String, CurrencyQuote>>() {})))
                .thenReturn(responseEntity);

        // Calling the method under test
        String fromCurrency = "USD";
        String toCurrency = "BRL";
        BigDecimal amount = BigDecimal.valueOf(10.0);
        CurrencyConversionDTO conversionDTO = quoteService.convertCurrency(fromCurrency, toCurrency, amount);

        // Asserting the result
        BigDecimal expectedConvertedAmount = BigDecimal.valueOf(50.0); // Expected conversion: 10 * 5
        assertEquals(expectedConvertedAmount, conversionDTO.getConvertedAmount());
    }

}
