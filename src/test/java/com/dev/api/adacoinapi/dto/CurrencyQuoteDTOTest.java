package com.dev.api.adacoinapi.dto;

import com.dev.api.adacoinapi.dto.CurrencyQuoteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class CurrencyQuoteDTOTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testSerialization() throws Exception {
        CurrencyQuoteDTO currencyQuoteDTO = new CurrencyQuoteDTO();
        currencyQuoteDTO.setCurrencyCode("USD");
        currencyQuoteDTO.setCurrencyName("Dólar Americano");
        currencyQuoteDTO.setBid(new BigDecimal("5.20"));
        currencyQuoteDTO.setAsk(new BigDecimal("5.25"));
        currencyQuoteDTO.setTimestamp(Instant.now());

        String json = objectMapper.writeValueAsString(currencyQuoteDTO);

        System.out.println(json);
    }
}
