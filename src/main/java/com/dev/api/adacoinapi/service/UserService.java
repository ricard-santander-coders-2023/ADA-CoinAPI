package com.dev.api.adacoinapi.service;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.model.User;
import com.dev.api.adacoinapi.repository.CurrencyQuoteRepository;
import com.dev.api.adacoinapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CurrencyQuoteRepository quoteRepository;

  public User createUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    return userRepository.save(user);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User addFavoriteCurrency(Long userId, Long quoteId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    CurrencyQuote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new RuntimeException("Quote not found"));

    user.getFavoriteCurrencies().add(quote);
    return userRepository.save(user);
  }
}
