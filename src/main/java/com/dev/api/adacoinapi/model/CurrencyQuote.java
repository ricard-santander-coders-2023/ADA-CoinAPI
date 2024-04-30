package com.dev.api.adacoinapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "currency_quote")
public class CurrencyQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private String currencyName;

    @Column(nullable = false)
    private BigDecimal bid;

    @Column(nullable = false)
    private BigDecimal ask;

    @Column(nullable = false)
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}