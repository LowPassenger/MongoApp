package com.mate.mongoapp.repository;

import com.mate.mongoapp.model.CurrencyPair;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairRepository extends MongoRepository<CurrencyPair, String> {
    CurrencyPair getFirstByNameOrderByPriceAsc(String pairName);

    CurrencyPair getFirstByNameOrderByPriceDesc(String pairName);

    List<CurrencyPair> findByName(String pairName, Pageable pageable);
}
