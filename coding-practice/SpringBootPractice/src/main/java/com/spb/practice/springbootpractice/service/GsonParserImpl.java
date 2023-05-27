package com.spb.practice.springbootpractice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spb.practice.springbootpractice.utils.GsonUtils;

import java.util.*;

public class GsonParserImpl<T> implements JsonParserComp<T> {
    @Override
    public List<T> parseJsonToObject(String json, Class<T> type) {
        long startTime = System.currentTimeMillis();

        Gson gson = GsonUtils.buildNullableGson();
        List<T> data = gson.fromJson(json, TypeToken.getParameterized(List.class, type).getType());
        System.out.println("=== GSON_PARSER \t\tData Count: " + data.size() + ", 소요시간(s): " + this.getElapsedSec(startTime));

        return data;
    }

    @Override
    public List<Map<String, Object>> parseJsonToListMap(String json) throws Exception {
        long startTime = System.currentTimeMillis();
        Gson gson = new Gson();
        List<Map<String, Object>> data = gson.fromJson(json,TypeToken.getParameterized(List.class, Map.class).getType());
        System.out.println("=== GSON_PARSER \t\tData Count: " + data.size() + ", 소요시간(s): " + this.getElapsedSec(startTime));
        return data;
    }
}
