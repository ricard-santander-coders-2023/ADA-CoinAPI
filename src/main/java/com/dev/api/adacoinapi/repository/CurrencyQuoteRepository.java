package com.dev.api.adacoinapi.repository;

import com.dev.api.adacoinapi.model.CurrencyQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyQuoteRepository extends JpaRepository<CurrencyQuote, String> {


}
