package com.sinezondi.websocket.core.controller;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.service.StockService;
import com.sinezondi.websocket.core.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final StockService stockService;

    private final WebsocketService websocketService;

    @Autowired
    public StockController(StockService stockService, WebsocketService websocketService){
        this.stockService = stockService;
        this.websocketService = websocketService;

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    @GetMapping
    public Stock getStockById(@RequestParam long id){
        return stockService.getStockByID(id);
    }

    @PatchMapping
    public ResponseEntity<?> updateStock(@RequestParam long id, @RequestParam double price){

        // update stock
        ResponseEntity<?> response =  stockService.updateStock(id, price);

        // send updated stock to websocket
        websocketService.sendUpdate(stockService.getStockByID(id));

        return  response;
    }
}
