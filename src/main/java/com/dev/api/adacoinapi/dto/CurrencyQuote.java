package com.dev.api.adacoinapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

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
    private String high;

    @JsonAlias({"low"})
    private String low;

    @JsonAlias({"varBid"})
    private String varBid;

    @JsonAlias({"pctChange"})
    private String pctChange;

    @JsonAlias({"bid"})
    private String bid;

    @JsonAlias({"ask"})
    private String ask;

    @JsonAlias({"timestamp"})
    private String timestamp;

    @JsonAlias({"create_date"})
    private String createDate;

}
