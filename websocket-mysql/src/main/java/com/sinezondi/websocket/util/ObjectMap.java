package com.sinezondi.websocket.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinezondi.websocket.core.dto.Stock;

public class ObjectMap {
    public String serializeStock(Stock stock) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(stock);
    }
}
