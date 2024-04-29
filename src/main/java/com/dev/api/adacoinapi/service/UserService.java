package com.dev.api.adacoinapi.service;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addFavoriteCurrency(Long userId, String currencyCode) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CurrencyQuote currencyQuote = currencyQuoteRepository.findById(currencyCode).orElseThrow(() -> new RuntimeException("Currency not found"));
        user.getFavoriteCurrencies().add(currencyQuote);
        return userRepository.save(user);
    }
}