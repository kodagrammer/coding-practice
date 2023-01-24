package com.spb.practice.springbootpractice.gsonUtils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {
    @Override
    public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(bigDecimal);
    }

    @Override
    public BigDecimal read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String stringValue = jsonReader.nextString();
        try {
            if("".equals(stringValue)){
                return null;
            } else {
                BigDecimal value = new BigDecimal(stringValue);
                return value;
            }
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
