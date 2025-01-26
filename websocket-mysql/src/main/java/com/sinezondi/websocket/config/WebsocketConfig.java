package com.sinezondi.websocket.config;

import com.sinezondi.websocket.core.service.StockService;
import com.sinezondi.websocket.handler.WebSocketHandler;
import com.sinezondi.websocket.util.ObjectMap;
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

    private final ObjectMap map;

    @Autowired
    public WebsocketConfig(StockService stockService, ObjectMap map){
        this.stockService = stockService;
        this.map = map;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(new WebSocketHandler(stockService, map), "/ws")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
}
