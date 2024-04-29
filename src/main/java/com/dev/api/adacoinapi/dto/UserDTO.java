package com.dev.api.adacoinapi.dto;

import com.dev.api.adacoinapi.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private List<CurrencyQuoteDTO> favoriteCurrencies;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.favoriteCurrencies = user.getFavoriteCurrencies().stream()
                .map(quote -> new CurrencyQuoteDTO(quote))
                .collect(Collectors.toList());
    }
}
