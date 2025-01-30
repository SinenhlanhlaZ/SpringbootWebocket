package com.sinezondi.websocket.core.controller;

import com.sinezondi.websocket.core.dto.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    private StockController stockController;

    @BeforeEach
    void setUp() {
        stockController = mock(StockController.class);
    }

    @Test
    void testStockUpdatesInRandomOrder() throws InterruptedException {

        Stock stock1 = new Stock();
        stock1.setStockId(1);
        stock1.setStockPrice(217.46);

        Stock stock2 = new Stock();
        stock2.setStockId(1);
        stock2.setStockPrice(223.84);

        Stock stock3 = new Stock();
        stock3.setStockId(2);
        stock3.setStockPrice(237.54);

        Stock stock4 = new Stock();
        stock4.setStockId(2);
        stock4.setStockPrice(264.24);

        List<Stock> updates = new ArrayList<>();
        updates.add(stock1);
        updates.add(stock2);
        updates.add(stock3);
        updates.add(stock4);

        Collections.shuffle(updates);

        for (Stock update : updates) {
            long id = update.getStockId();
            double price = update.getStockPrice();

            stockController.updateStock(id, price);
            System.out.println(update);

            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 6000));
        }
    }
}
