package com.mate.mongoapp.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PairRequestDto {
    private String name;
    private BigDecimal price;
    private LocalDateTime timeStamp;
}
