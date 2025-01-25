package com.sinezondi.websocket.core.service;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public ResponseEntity createStock(Stock stock){
        URI uri = URI.create("http://localhost:8000/stock");
        stockRepository.save(stock);
        stockRepository.flush();
        return ResponseEntity.created(uri).build();
    }

    public Stock getStockByID(long id){
        return stockRepository.findStockById(id);
    }

    public ResponseEntity updateStock(long id, double newStockPrice){
        Stock _stock = stockRepository.findStockById(id);
        double diff = newStockPrice - _stock.getStockPrice();
        _stock.setStockPrice(newStockPrice);
        _stock.setChangePercentage(Math.round(diff * 100.0) / 100.0);
        _stock.setLastUpdated(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

}
