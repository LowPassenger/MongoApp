package com.mate.mongoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Pair {
    BTC_USD("BTC/USD"),
    ETH_USD("ETH/USD"),
    XRP_USD("XRP/USD");

    private final String name;
}
