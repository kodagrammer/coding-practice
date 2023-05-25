package com.spb.practice.springbootpractice.utils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter jsonWriter, Integer integer) throws IOException {
        if (integer == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(integer);
    }

    @Override
    public Integer read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String stringValue = jsonReader.nextString();
        try {
            Integer value = Integer.valueOf(stringValue);
            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
