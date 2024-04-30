package com.dev.api.adacoinapi.controller;

import com.dev.api.adacoinapi.dto.UserDTO;
import com.dev.api.adacoinapi.model.User;
import com.dev.api.adacoinapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserService userService;

  public ResponseEntity<User> createUser(
      @RequestParam String username, @RequestParam String password) {
    User user = userService.createUser(username, password);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return ResponseEntity.ok(createdUser);
  }

  @PostMapping("/{userId}/favoriteCurrencies/{quoteId}")
  public ResponseEntity<UserDTO> addFavoriteCurrency(@PathVariable Long userId, @PathVariable Long quoteId) {
    User user = userService.addFavoriteCurrency(userId, quoteId);
    UserDTO userDTO = new UserDTO(user);
    return ResponseEntity.ok(userDTO);
  }
}
