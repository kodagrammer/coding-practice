package com.spb.practice.springbootpractice.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonSimpleUtils {
    private final static JSONParser JSON_PARSER = new JSONParser();

    public static Map jsonToMap (String jsonData) throws ParseException {
        return (JSONObject) JSON_PARSER.parse(jsonData);
    }

    public static List<Map<String, Object>> jsonArrayToList(String jsonData) throws ParseException {
        List<Map<String, Object>> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) JSON_PARSER.parse(jsonData);

        for(Object item : jsonArray) {
            list.add((JSONObject)item);
        }
        return list;
    }

    public static <T> List<T> jsonArrayToList(String jsonData, Class<T> targetClass) throws Exception {
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) JSON_PARSER.parse(jsonData);

        for(Object item : jsonArray) {
            list.add(jsonObjectToObj((JSONObject)item, targetClass));
        }

        return list;
    }

    public static <T> T jsonObjectToObj(String jsonData, Class<T> targetClass) throws Exception {
        return jsonObjectToObj((JSONObject) JSON_PARSER.parse(jsonData), targetClass);
    }

    public static <T> T jsonObjectToObj(JSONObject jsonData, Class<T> targetClass) throws Exception {

        Field[] fieldArr = targetClass.getDeclaredFields();
        T data = targetClass.getConstructor().newInstance();

        for(Field field : fieldArr) {
            if(jsonData.containsKey(field.getName())) {
                field.setAccessible(true);
                field.set(data, castString(field.getType(), (String) jsonData.get(field.getName())));
            }
        }
        return data;
    }

    public static <T> T castString (Class<?> clazz, String string) {
        T value = null;

        if(!"".equals(string)) {
            if (clazz.isAssignableFrom(Integer.class)) {
                value = (T) Integer.valueOf(string);
            } else if (clazz.isAssignableFrom(Boolean.class)) {
                value = (T) Boolean.valueOf(string);
            } else if (clazz.isAssignableFrom(Double.class)) {
                value = (T) Double.valueOf(string);
            } else if (clazz.isAssignableFrom(Long.class)) {
                value = (T) Long.valueOf(string);
            } else if (clazz.isAssignableFrom(BigDecimal.class)) {
                value = (T) new BigDecimal(string);
            } else {
                value = (T) string;
            }
        }
        return value;
    }
}
