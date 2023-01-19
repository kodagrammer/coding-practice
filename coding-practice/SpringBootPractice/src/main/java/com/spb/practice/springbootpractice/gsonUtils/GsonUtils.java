package com.spb.practice.springbootpractice.gsonUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtils {
    public static Gson buildTypeAdapterGson(Map<Type, TypeAdapter<?>> adapters){
        GsonBuilder builder = new GsonBuilder();

        for(Map.Entry<Type, TypeAdapter<?>> entry : adapters.entrySet()){
            builder.registerTypeAdapter(entry.getKey(), entry.getValue());
        }

        return builder.create();
    }
}
