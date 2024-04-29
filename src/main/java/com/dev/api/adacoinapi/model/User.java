package com.dev.api.adacoinapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;


  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<CurrencyQuote> favoriteCurrencies;
}
