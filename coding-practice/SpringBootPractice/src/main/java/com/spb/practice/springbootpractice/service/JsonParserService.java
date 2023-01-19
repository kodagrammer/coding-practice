package com.spb.practice.springbootpractice.service;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.spb.practice.springbootpractice.domain.JsonTestData;
import com.spb.practice.springbootpractice.gsonUtils.GsonUtils;
import com.spb.practice.springbootpractice.gsonUtils.typeadapter.DoubleTypeAdapter;
import com.spb.practice.springbootpractice.gsonUtils.typeadapter.StringTypeAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JsonParserService {
    public void testJsonNullConvert(){
        String jsonData = getSampleData();
        Map<Type, TypeAdapter<?>> adapterMap = new HashMap<>();
        adapterMap.put(Double.class, new DoubleTypeAdapter());
        adapterMap.put(double.class, new DoubleTypeAdapter());
        adapterMap.put(String.class, new StringTypeAdapter());

        Gson gson = GsonUtils.buildTypeAdapterGson(adapterMap);
        List<JsonTestData> list = gson.fromJson(jsonData, new TypeToken<List<JsonTestData>>(){}.getType());

        for(JsonTestData data : list){
            System.out.println(data);
        }
    }

    public String getSampleData(){
        return "[\n" +
                "  {\n" +
                "    \"id\": \"\",\n" +
                "    \"name\": \"길동스0\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"address\": \"\",\n" +
                "    \"email\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"\",\n" +
                "    \"name\": \"길동스1\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"address\": \"\",\n" +
                "    \"email\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"\",\n" +
                "    \"name\": \"길동스2\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"address\": \"\",\n" +
                "    \"email\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"\",\n" +
                "    \"name\": \"길동스3\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"address\": \"\",\n" +
                "    \"email\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"\",\n" +
                "    \"name\": \"길동스4\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"address\": \"\",\n" +
                "    \"email\": null\n" +
                "  }\n" +
                "]";
    }
}
