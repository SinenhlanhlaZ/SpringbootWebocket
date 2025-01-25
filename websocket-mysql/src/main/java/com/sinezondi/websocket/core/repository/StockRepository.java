package com.sinezondi.websocket.core.repository;

import com.sinezondi.websocket.core.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("FROM tbl_stock s WHERE s.stockId=:id")
    Stock findStockById(long id);

}
