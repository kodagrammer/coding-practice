package com.spb.practice.springbootpractice.service;

import com.spb.practice.springbootpractice.utils.JsonSimpleUtils;

import java.util.*;

public class JsonSimpleParserImpl<T> implements JsonParserComp<T> {

    private final static JsonSimpleUtils JSON_SIMPLE_UTILS = new JsonSimpleUtils();

    @Override
    public List<T> parseJsonToObject(String json, Class<T> type) throws Exception {
        long startTime = System.currentTimeMillis();
        List<T> data = JsonSimpleUtils.jsonArrayToList(json, type);
        System.out.println("=== JSON_SIMPLE_PARSER \t\tData Count: " + data.size() + ", 소요시간(s): " + this.getElapsedSec(startTime));
        return data;
    }

    @Override
    public List<Map<String, Object>> parseJsonToListMap(String json) throws Exception {
        long startTime = System.currentTimeMillis();
        List<Map<String, Object>> data = JsonSimpleUtils.jsonArrayToList(json);
        System.out.println("=== JSON_SIMPLE_PARSER \t\tData Count: " + data.size() + ", 소요시간(s): " + this.getElapsedSec(startTime));
        return data;
    }
}
