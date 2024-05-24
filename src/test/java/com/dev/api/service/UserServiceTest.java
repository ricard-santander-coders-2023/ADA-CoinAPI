package com.dev.api.service;

import com.dev.api.adacoinapi.model.User;
import com.dev.api.adacoinapi.repository.UserRepository;
import com.dev.api.adacoinapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

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
}
