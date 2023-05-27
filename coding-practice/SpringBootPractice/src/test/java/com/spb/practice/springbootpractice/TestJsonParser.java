package com.spb.practice.springbootpractice;

import com.spb.practice.springbootpractice.domain.*;
import com.spb.practice.springbootpractice.service.*;
import com.spb.practice.springbootpractice.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;
import java.util.Map;

@TestComponent
public class TestJsonParser {
    private JsonParserComp _gsonParser;
    private JsonParserComp _jacksonParser;
    private JsonParserComp _jsonSimpleParser;
    private JsonParserService _jsonParserService = new JsonParserService();

    private final String JSON_DIR_PATY = "D:\\NextTF\\항공정산 고도화 자료\\stella_api_json\\";
    private final String DSR_FILE_NM = "CRS발권원본_AMADEUS_20230210";        // size: 1,024KB -- Count: 314
    private final String FARE_FILE_NM = "탑승객티켓판매가 조회.json";           // size: 5,773KB -- Count: 2,114
    private final String FARE_MONTH_FILE_NM = "FARE_20230301_20230331.json"; // size: 271,922KB --Count: 99,601

//    @Test
    void loadJsonFile() throws Exception {
        String jsonData2 = CommonUtils.loadFile(JSON_DIR_PATY + DSR_FILE_NM);
        String jsonData3 = CommonUtils.loadFile(JSON_DIR_PATY + FARE_FILE_NM);

//        System.out.println(jsonData1);
        System.out.println(jsonData2);
        System.out.println(jsonData3);
    }

    @Test
    void convertSmallJson() throws Exception {
        String jsonData = CommonUtils.loadFile(JSON_DIR_PATY + DSR_FILE_NM);
        Class<DsrData> type = DsrData.class;

        _gsonParser = new GsonParserImpl();
        List<FareData> gsonData = _gsonParser.parseJsonToObject(jsonData, type);

        _jacksonParser = new JacksonParserImpl();
        List<FareData> jacksonData = _jacksonParser.parseJsonToObject(jsonData, type);

        _jsonSimpleParser = new JsonSimpleParserImpl();
        List<FareData> jsonSimple = _jsonSimpleParser.parseJsonToObject(jsonData, type);
        System.out.println("=========================================================================");
    }

    @Test
    void convertMiddleJson() throws Exception {
        String jsonData = CommonUtils.loadFile(JSON_DIR_PATY + FARE_FILE_NM);
        Class<FareData> type = FareData.class;

        _gsonParser = new GsonParserImpl();
        List<FareData> gsonData = _gsonParser.parseJsonToObject(jsonData, type);

        _jacksonParser = new JacksonParserImpl();
        List<FareData> jacksonData = _jacksonParser.parseJsonToObject(jsonData, type);

        _jsonSimpleParser = new JsonSimpleParserImpl();
        List<FareData> jsonSimple = _jsonSimpleParser.parseJsonToObject(jsonData, type);

        System.out.println("=========================================================================");
    }

    @Test
    void convertBigJson() throws Exception {
        String jsonData = CommonUtils.loadFile(JSON_DIR_PATY + FARE_MONTH_FILE_NM);
        Class<FareData> type = FareData.class;

        _gsonParser = new GsonParserImpl();
        List<FareData> gsonData = _gsonParser.parseJsonToObject(jsonData, type);

        _jacksonParser = new JacksonParserImpl();
        List<FareData> jacksonData = _jacksonParser.parseJsonToObject(jsonData, type);

        _jsonSimpleParser = new JsonSimpleParserImpl();
        List<FareData> jsonSimple = _jsonSimpleParser.parseJsonToObject(jsonData, type);
        System.out.println("=========================================================================");
    }

    @Test
    void convertSampleJson() throws Exception {
        String jsonData = _jsonParserService.getSampleData();
        _gsonParser = new GsonParserImpl();
        List<Map<String, Object>> gsonData = _gsonParser.parseJsonToListMap(jsonData);

        _jacksonParser = new JacksonParserImpl();
        List<Map<String, Object>> jacksonData = _jacksonParser.parseJsonToListMap(jsonData);

        _jsonSimpleParser = new JsonSimpleParserImpl();
        List<Map<String, Object>> jsonSimple = _jsonSimpleParser.parseJsonToListMap(jsonData);
        System.out.println("=========================================================================");
    }
}
