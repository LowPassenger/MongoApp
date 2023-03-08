package com.mate.mongoapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WineCluster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPair {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDateTime timeStamp;
}
