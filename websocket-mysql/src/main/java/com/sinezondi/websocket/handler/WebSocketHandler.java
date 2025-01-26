package com.sinezondi.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private final StockService stockService;

    @Autowired
    public WebSocketHandler(StockService stockService){
        this.stockService = stockService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        try{
            //extract Stock ID from URI
            String query = session.getUri().getQuery();//e.g id=4

            if(query != null && query.startsWith("id=")){
                long stockId = Long.parseLong(query.split("=")[1]);
                Stock stock = stockService.getStockByID(stockId);

                // Send Stock details to client
                if(stock != null){
                    String stockDetails = stock.toString();
                    session.sendMessage(new TextMessage(stockDetails));
                }
                else{
                    session.sendMessage(new TextMessage("Stock ID: "+stockId+" not found"));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
