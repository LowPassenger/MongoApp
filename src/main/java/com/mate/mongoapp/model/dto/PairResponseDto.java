package com.mate.mongoapp.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PairResponseDto {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDateTime timeStamp;
}
