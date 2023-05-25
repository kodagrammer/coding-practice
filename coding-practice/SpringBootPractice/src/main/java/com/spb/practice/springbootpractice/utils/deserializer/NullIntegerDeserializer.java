package com.spb.practice.springbootpractice.utils.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class NullIntegerDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.readValueAsTree();
        if (node.asText().isEmpty()) {
            return null;
        }
        return Integer.valueOf(node.intValue());
    }
}
