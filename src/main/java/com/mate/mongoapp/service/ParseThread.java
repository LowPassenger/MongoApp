package com.mate.mongoapp.service;

import com.mate.mongoapp.model.CurrencyPair;
import com.mate.mongoapp.model.Pair;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.TimerTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ParseThread extends TimerTask {
    private final PairService pairService;
    private final PriceGetter priceGetter;
    private final CurrencyPair currencyPair = new CurrencyPair();
    private final Pair[] pairs = Pair.values();
    private String pairName;

    @Autowired
    public ParseThread(PairService pairService, PriceGetter priceGetter) {
        this.pairService = pairService;
        this.priceGetter = priceGetter;
    }

    public void run() {
        for (Pair pair : pairs) {
            currencyPair.setId(null);
            pairName = pair.getName();
            currencyPair.setName(pairName);
            currencyPair.setPrice(Objects.requireNonNull(priceGetter).pairPriceGetter(pairName));
            currencyPair.setTimeStamp(LocalDateTime.now());
            Objects.requireNonNull(pairService).savePair(currencyPair);
        }
    }
}
