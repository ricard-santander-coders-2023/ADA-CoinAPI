package com.dev.api.adacoinapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CurrencyQuote {
    @JsonAlias({"code"})
    private String code;

    @JsonAlias({"codein"})
    private String codein;

    @JsonAlias({"name"})
    private String name;

    @JsonAlias({"high"})
    private BigDecimal high;

    @JsonAlias({"low"})
    private BigDecimal low;

    @JsonAlias({"varBid"})
    private BigDecimal varBid;

    @JsonAlias({"pctChange"})
    private String pctChange;

    @JsonAlias({"bid"})
    private BigDecimal bid;

    @JsonAlias({"ask"})
    private BigDecimal ask;

    @JsonAlias({"timestamp"})
    private String timestamp;

    @JsonAlias({"create_date"})
    private String createDate;

    public BigDecimal convert(BigDecimal amount) {
        return amount.multiply(bid);
    }

}
