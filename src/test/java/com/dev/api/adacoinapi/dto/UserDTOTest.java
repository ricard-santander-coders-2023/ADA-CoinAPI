package com.dev.api.adacoinapi.dto;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOTest {

    @Test
    void testUserDTOConversion() {
        CurrencyQuote quote1 = new CurrencyQuote();
        quote1.setId(1L);
        quote1.setCurrencyCode("USD");
        quote1.setCurrencyName("US Dollar");
        quote1.setBid(BigDecimal.valueOf(1.0));
        quote1.setAsk(BigDecimal.valueOf(1.1));
        quote1.setTimestamp(Instant.now());

        CurrencyQuote quote2 = new CurrencyQuote();
        quote2.setId(2L);
        quote2.setCurrencyCode("EUR");
        quote2.setCurrencyName("Euro");
        quote2.setBid(BigDecimal.valueOf(0.9));
        quote2.setAsk(BigDecimal.valueOf(1.0));
        quote2.setTimestamp(Instant.now());

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFavoriteCurrencies(Arrays.asList(quote1, quote2));

        quote1.setUser(user);
        quote2.setUser(user);

        UserDTO userDTO = new UserDTO(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());

        List<CurrencyQuoteDTO> favoriteCurrenciesDTO = userDTO.getFavoriteCurrencies();
        assertEquals(2, favoriteCurrenciesDTO.size());

        CurrencyQuoteDTO quoteDTO1 = favoriteCurrenciesDTO.stream()
                .filter(dto -> dto.getCurrencyCode().equals(quote1.getCurrencyCode()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("QuoteDTO1 not found"));

        assertEquals(quote1.getCurrencyCode(), quoteDTO1.getCurrencyCode());
        assertEquals(quote1.getCurrencyName(), quoteDTO1.getCurrencyName());
        assertEquals(quote1.getBid(), quoteDTO1.getBid());
        assertEquals(quote1.getAsk(), quoteDTO1.getAsk());
        assertEquals(quote1.getTimestamp(), quoteDTO1.getTimestamp());

        CurrencyQuoteDTO quoteDTO2 = favoriteCurrenciesDTO.stream()
                .filter(dto -> dto.getCurrencyCode().equals(quote2.getCurrencyCode()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("QuoteDTO2 not found"));

        assertEquals(quote2.getCurrencyCode(), quoteDTO2.getCurrencyCode());
        assertEquals(quote2.getCurrencyName(), quoteDTO2.getCurrencyName());
        assertEquals(quote2.getBid(), quoteDTO2.getBid());
        assertEquals(quote2.getAsk(), quoteDTO2.getAsk());
        assertEquals(quote2.getTimestamp(), quoteDTO2.getTimestamp());
    }
}
