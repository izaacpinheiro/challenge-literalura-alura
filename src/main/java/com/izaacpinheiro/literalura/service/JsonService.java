package com.izaacpinheiro.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonService implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDados(String json, Class<T> tClass) {
        try {
           return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
