package com.mate.mongoapp.model.mapper;

import com.mate.mongoapp.model.CurrencyPair;
import com.mate.mongoapp.model.dto.PairResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PairMapper {
    public PairResponseDto mapToto(CurrencyPair pair) {
        PairResponseDto responseDto = new PairResponseDto();
        responseDto.setId(pair.getId());
        responseDto.setName(pair.getName());
        responseDto.setPrice(pair.getPrice());
        responseDto.setTimeStamp(pair.getTimeStamp());
        return responseDto;
    }

}
