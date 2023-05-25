package com.spb.practice.springbootpractice.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.spb.practice.springbootpractice.utils.typeadapter.BigDecimalTypeAdapter;
import com.spb.practice.springbootpractice.utils.typeadapter.IntegerTypeAdapter;
import com.spb.practice.springbootpractice.utils.typeadapter.LongTypeAdapter;
import com.spb.practice.springbootpractice.utils.typeadapter.StringTypeAdapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GsonUtils {
    public static Gson buildTypeAdapterGson(Map<Type, TypeAdapter<?>> adapters){
        GsonBuilder builder = new GsonBuilder();

        for(Map.Entry<Type, TypeAdapter<?>> entry : adapters.entrySet()){
            builder.registerTypeAdapter(entry.getKey(), entry.getValue());
        }

        return builder.create();
    }

    public static Gson buildNullableGson(){
        Map<Type, TypeAdapter<?>> adapterMap = new HashMap<>();
        adapterMap.put(Integer.class, new IntegerTypeAdapter());
        adapterMap.put(String.class, new StringTypeAdapter());
        adapterMap.put(BigDecimal.class, new BigDecimalTypeAdapter());
        adapterMap.put(Long.class, new LongTypeAdapter());

        return GsonUtils.buildTypeAdapterGson(adapterMap);
    }
}
