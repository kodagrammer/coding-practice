package com.spb.practice.springbootpractice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.spb.practice.springbootpractice.utils.deserializer.NullBigDecimalDeserializer;
import com.spb.practice.springbootpractice.utils.deserializer.NullIntegerDeserializer;
import com.spb.practice.springbootpractice.utils.deserializer.NullLongDeserializer;
import com.spb.practice.springbootpractice.utils.deserializer.NullStringDeserializer;

import java.math.BigDecimal;
import java.util.List;

public class JacksonParserImpl<T> implements JsonParserComp<T> {
    @Override
    public List<T> parseJsonToObject(String json, Class<T> type) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new NullStringDeserializer());
        module.addDeserializer(Integer.class, new NullIntegerDeserializer());
        module.addDeserializer(Long.class, new NullLongDeserializer());
        module.addDeserializer(BigDecimal.class, new NullBigDecimalDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        List<T> data = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, type));
        System.out.println("=== JACKSON_PARSER \t\tData Count: " + data.size() + ", 소요시간(s): " + this.getElapsedSec(startTime));
        return data;
    }
}
