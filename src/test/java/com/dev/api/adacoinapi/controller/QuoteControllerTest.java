package com.dev.api.adacoinapi.controller;

import com.dev.api.adacoinapi.dto.CurrencyConversionDTO;
import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetRealTimeQuotes() {
        // Given
        List<String> currencies = List.of("USD", "EUR");
        Map<String, CurrencyQuote> mockResponse = new HashMap<>();
        CurrencyQuote put1 = mockResponse.put("USD", new CurrencyQuote());
        CurrencyQuote put = mockResponse.put("EUR", new CurrencyQuote());

        when(quoteService.getRealTimeQuotes(currencies)).thenReturn(mockResponse);

        // When
        Map<String, CurrencyQuote> response = quoteController.getRealTimeQuotes(currencies);

        // Then
        assertEquals(mockResponse, response);
    }

    @Test
    void testConvertCurrency() {
        // Given
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        BigDecimal amount = new BigDecimal("100");
        CurrencyConversionDTO mockConversion = new CurrencyConversionDTO(fromCurrency, toCurrency, amount, new BigDecimal("85"));

        when(quoteService.convertCurrency(fromCurrency, toCurrency, amount)).thenReturn(mockConversion);

        // When
        CurrencyConversionDTO response = quoteController.convertCurrency(fromCurrency, toCurrency, amount);

        // Then
        assertEquals(mockConversion, response);
    }
}
