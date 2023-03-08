package com.mate.mongoapp.service;

import com.mate.mongoapp.model.CurrencyPair;
import java.util.List;

public interface PairService {
    CurrencyPair savePair(CurrencyPair pair);

    CurrencyPair getMinPrice(String pairName);

    CurrencyPair getMaxPrice(String pairName);

    List<CurrencyPair> getAll(String pairName, int number, int size);
}
