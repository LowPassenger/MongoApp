package com.mate.mongoapp.service;

import com.mate.mongoapp.model.CurrencyPair;
import com.mate.mongoapp.repository.PairRepository;
import com.mate.mongoapp.utils.AppConstants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PairServiceImpl implements PairService {
    private final PairRepository pairRepository;

    @Autowired
    public PairServiceImpl(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    @Override
    public CurrencyPair savePair(CurrencyPair pair) {
        return pairRepository.save(pair);
    }

    @Override
    public CurrencyPair getMinPrice(String pairName) {
        return pairRepository.getFirstByNameOrderByPriceAsc(pairName);
    }

    @Override
    public CurrencyPair getMaxPrice(String pairName) {
        return pairRepository.getFirstByNameOrderByPriceDesc(pairName);
    }

    @Override
    public List<CurrencyPair> getAll(String pairName, int number, int size) {
        Sort sort = AppConstants.DEFAULT_SORT_DIRECTION.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)
                .ascending()
                : Sort.by(AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION)
                .descending();
        Pageable pageable = PageRequest.of(number, size, sort);
        return pairRepository.findByName(pairName, pageable);
    }
}
