package com.sinezondi.websocket.core.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "tbl_stock")
@Data
@Getter
@Setter
@ToString
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockId;

    private String stockSymbol;

    private double stockPrice, changePercentage;

    private LocalDateTime lastUpdated;

}
