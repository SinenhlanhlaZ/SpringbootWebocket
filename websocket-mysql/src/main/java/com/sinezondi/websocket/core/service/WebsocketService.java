package com.sinezondi.websocket.core.service;

import com.sinezondi.websocket.handler.WebSocketHandler;
import org.springframework.stereotype.Service;
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
}
