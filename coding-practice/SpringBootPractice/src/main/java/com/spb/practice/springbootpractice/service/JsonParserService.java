package com.spb.practice.springbootpractice.service;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.spb.practice.springbootpractice.domain.JsonTestData;
import com.spb.practice.springbootpractice.gsonUtils.GsonUtils;
import com.spb.practice.springbootpractice.gsonUtils.typeadapter.BigDecimalTypeAdapter;
import com.spb.practice.springbootpractice.gsonUtils.typeadapter.DoubleTypeAdapter;
import com.spb.practice.springbootpractice.gsonUtils.typeadapter.StringTypeAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
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
        adapterMap.put(BigDecimal.class, new BigDecimalTypeAdapter());

        Gson gson = GsonUtils.buildTypeAdapterGson(adapterMap);
        List<JsonTestData> list = gson.fromJson(jsonData, new TypeToken<List<JsonTestData>>(){}.getType());

        for(JsonTestData data : list){
            System.out.println(data);
        }
    }

    public String getSampleData(){
        return "[\n" +
                "    {\n" +
                "        \"idx\" : \"idx01\",\n" +
                "        \"name\" : \"홍길동\",\n" +
                "        \"mathScore\" : \"52.25\",\n" +
                "        \"englishScore\" : \"\",\n" +
                "        \"avr\" : \"52.25\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"idx\" : \"idx02\",\n" +
                "        \"name\" : \"김길동\",\n" +
                "        \"mathScore\" : \"100\",\n" +
                "        \"englishScore\" : \"85\",\n" +
                "        \"avr\" : \"92.5\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"idx\" : \"idx03\",\n" +
                "        \"name\" : \"박길동\",\n" +
                "        \"mathScore\" : \"100\",\n" +
                "        \"englishScore\" : \"100\",\n" +
                "        \"avr\" : \"\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"idx\" : \"idx04\",\n" +
                "        \"name\" : \"고길동\",\n" +
                "        \"mathScore\" : \"\",\n" +
                "        \"englishScore\" : \"85\",\n" +
                "        \"avr\" : \"85\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"idx\" : \"idx05\",\n" +
                "        \"name\" : \"\",\n" +
                "        \"mathScore\" : \"\",\n" +
                "        \"englishScore\" : \"\",\n" +
                "        \"avr\" : \"\"\n" +
                "    }\n" +
                "]";
    }
}
