package com.sinezondi.websocket.handler;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.core.service.StockService;
import com.sinezondi.websocket.core.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private final StockService stockService;

    private final WebsocketService websocketService;

    @Autowired
    public WebSocketHandler(StockService stockService, WebsocketService websocketService){
        this.stockService = stockService;
        this.websocketService = websocketService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        try{
            //extract Stock ID from URI
            String query = webSocketSession.getUri().getQuery();//e.g id=4

            if(query != null && query.startsWith("id=")){
                long stockId = Long.parseLong(query.split("=")[1]);
                Stock stock = stockService.getStockByID(stockId);

                websocketService.storeWebSocketSession(stockId, webSocketSession);

                // Send Stock details to client
                if(stock != null){
                    webSocketSession.sendMessage(new TextMessage(stock.toString()));
                }
                else{
                    webSocketSession.sendMessage(new TextMessage("Stock ID: "+stockId+" not found"));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus){
        // remove session from Hashmap
        websocketService.removeWebSocketSession(webSocketSession);
    }
}
