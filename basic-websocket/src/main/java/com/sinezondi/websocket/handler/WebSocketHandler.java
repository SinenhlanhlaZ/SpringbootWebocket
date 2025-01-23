package com.sinezondi.websocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try{
            // Handle the message and send a response
            session.sendMessage(new TextMessage("Hello, " + message.getPayload()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}