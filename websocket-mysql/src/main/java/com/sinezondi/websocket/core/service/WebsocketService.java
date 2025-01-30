package com.sinezondi.websocket.core.service;

import com.sinezondi.websocket.core.dto.Stock;
import com.sinezondi.websocket.handler.WebSocketHandler;
import com.sinezondi.websocket.util.ObjectMap;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebsocketService {

    private final Map<Long, WebSocketSession> webSocketSessionStore = new HashMap<>();

    public void storeWebSocketSession(long stockId, WebSocketSession session){
        webSocketSessionStore.put(stockId, session);
    }

    public Map<Long, WebSocketSession> getAllSessions(){
        return webSocketSessionStore;
    }

    public void removeWebSocketSession(WebSocketSession webSocketSession){
        webSocketSessionStore.values().remove(webSocketSession);
    }

    public void sendUpdate(Stock stock){
        try {

            Map<Long, WebSocketSession> allSessions = getAllSessions();

            if(allSessions.containsKey(stock.getStockId())){
                WebSocketSession session = allSessions.get(stock.getStockId());
                session.sendMessage(new TextMessage(stock.toString()));
            }
            else{
                System.out.println("No session was found");
            }

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
