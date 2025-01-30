package com.sinezondi.websocket.config;

import com.sinezondi.websocket.core.service.StockService;
import com.sinezondi.websocket.core.service.WebsocketService;
import com.sinezondi.websocket.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final StockService stockService;
    private final WebsocketService websocketService;

    @Autowired
    public WebsocketConfig(StockService stockService, WebsocketService websocketService){
        this.stockService = stockService;
        this.websocketService = websocketService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(new WebSocketHandler(stockService, websocketService), "/stock")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
}
