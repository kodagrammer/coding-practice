package com.spb.practice.springbootpractice.service;

import java.util.*;

public interface JsonParserComp<T> {

    List<T> parseJsonToObject(String json, Class<T> type) throws Exception;
    List<Map<String, Object>> parseJsonToListMap(String json) throws Exception;

    // 테스트용
    default double getElapsedSec(long startTime) {
        long thisTime = System.currentTimeMillis();
        return (thisTime - startTime)/1000D;
    }
}
