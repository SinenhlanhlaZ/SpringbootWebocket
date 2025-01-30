package com.sinezondi.websocket.core.service;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@Transactional
public class StockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

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
        try{
            LOG.info("Updating Stock {}", id);
            Stock _stock = stockRepository.findStockById(id);
            double diff = newStockPrice - _stock.getStockPrice();
            _stock.setStockPrice(newStockPrice);
            _stock.setChangePercentage(Math.round((diff / _stock.getStockPrice()) * 100.0 * 100.0) / 100.0);
            _stock.setLastUpdated(LocalDateTime.now());
            stockRepository.save(_stock);
            stockRepository.flush();
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            LOG.error("Error occurred while updating stock {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
