package com.scor.bulktransfer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class JsonService {

    private static final JsonService INSTANCE = new JsonService();
    private final ObjectMapper objectMapper;

    private JsonService() {
        this.objectMapper = new ObjectMapper();
    }

    public static JsonService getInstance() {
        return INSTANCE;
    }

    public String convertToJson(Map<String, Object> metadata) {
        try {
            return objectMapper.writeValueAsString(metadata);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error converting metadata to JSON", e);
        }
    }
}
