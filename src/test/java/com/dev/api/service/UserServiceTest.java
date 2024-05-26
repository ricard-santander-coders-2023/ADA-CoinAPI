package com.dev.api.service;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import com.dev.api.adacoinapi.model.User;
import com.dev.api.adacoinapi.repository.CurrencyQuoteRepository;
import com.dev.api.adacoinapi.repository.UserRepository;
import com.dev.api.adacoinapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private CurrencyQuoteRepository quoteRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserWithUsernameAndPassword() {
        User expectedUser = new User();
        expectedUser.setUsername("test_user");
        expectedUser.setPassword("test_password");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User createdUser = userService.createUser("test_user", "test_password");

        assertEquals(expectedUser, createdUser);
    }

    @Test
    void testCreateUserWithUserObject() {
        User expectedUser = new User();
        expectedUser.setUsername("test_user");
        expectedUser.setPassword("test_password");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User user = new User();
        user.setUsername("test_user");
        user.setPassword("test_password");
        User createdUser = userService.createUser(user);

        assertEquals(expectedUser, createdUser);
    }

    @Test
    void testAddFavoriteCurrency() {
        Long userId = 1L;
        Long quoteId = 1L;

        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setFavoriteCurrencies(new ArrayList<>()); // Use ArrayList instead of HashSet

        CurrencyQuote mockQuote = new CurrencyQuote();
        mockQuote.setId(quoteId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(quoteRepository.findById(quoteId)).thenReturn(Optional.of(mockQuote));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = userService.addFavoriteCurrency(userId, quoteId);

        assertEquals(userId, updatedUser.getId());
        assertTrue(updatedUser.getFavoriteCurrencies().contains(mockQuote));

        verify(userRepository, times(1)).findById(userId);
        verify(quoteRepository, times(1)).findById(quoteId);
        verify(userRepository, times(1)).save(mockUser);
    }
}
