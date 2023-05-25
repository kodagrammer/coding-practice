package com.spb.practice.springbootpractice.utils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class StringTypeAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter jsonWriter, String s) throws IOException {
        if (s == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(s);
    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String stringValue = jsonReader.nextString();
        return stringValue != null && !stringValue.isEmpty() ? stringValue : null;
    }
}
