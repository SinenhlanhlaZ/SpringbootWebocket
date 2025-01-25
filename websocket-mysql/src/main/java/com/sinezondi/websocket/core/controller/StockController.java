package com.sinezondi.websocket.core.controller;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    @GetMapping
    public Stock getStockById(@RequestParam long id){
        return stockService.getStockByID(id);
    }

    @PatchMapping
    public ResponseEntity updateStock(@RequestParam long id, @RequestParam double price){
        return stockService.updateStock(id, price);
    }
}
