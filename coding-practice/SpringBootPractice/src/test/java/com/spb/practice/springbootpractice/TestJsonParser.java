package com.spb.practice.springbootpractice;

import com.spb.practice.springbootpractice.domain.DsrData;
import com.spb.practice.springbootpractice.domain.FareData;
import com.spb.practice.springbootpractice.service.GsonParserImpl;
import com.spb.practice.springbootpractice.service.JacksonParserImpl;
import com.spb.practice.springbootpractice.service.JsonParserComp;
import com.spb.practice.springbootpractice.service.JsonSimpleParserImpl;
import com.spb.practice.springbootpractice.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@TestComponent
public class TestJsonParser {
    private JsonParserComp _gsonParser;
    private JsonParserComp _jacksonParser;
    private JsonParserComp _jsonSimpleParser;

    private final String jsonDirPath = "D:\\NextTF\\항공정산 고도화 자료\\stella_api_json\\";
    private final String dsrFileName = "CRS발권원본_AMADEUS_20230210";        // size: 1,024KB -- Count: 314
    private final String fareFileName = "탑승객티켓판매가 조회.json";           // size: 5,773KB -- Count: 2,114
    private final String fareMonthFileName = "FARE_20230301_20230331.json"; // size: 271,922KB --Count: 99,601

//    @Test
    void loadJsonFile() throws Exception {
        String jsonData2 = CommonUtils.loadFile(jsonDirPath + dsrFileName);
        String jsonData3 = CommonUtils.loadFile(jsonDirPath + fareFileName);

//        System.out.println(jsonData1);
        System.out.println(jsonData2);
        System.out.println(jsonData3);
    }

    @Test
    void convertSmallJson() throws Exception {
        String jsonData = CommonUtils.loadFile(jsonDirPath + dsrFileName);
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
        String jsonData = CommonUtils.loadFile(jsonDirPath + fareFileName);
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
        String jsonData = CommonUtils.loadFile(jsonDirPath + fareMonthFileName);
        Class<FareData> type = FareData.class;

        _gsonParser = new GsonParserImpl();
        List<FareData> gsonData = _gsonParser.parseJsonToObject(jsonData, type);

        _jacksonParser = new JacksonParserImpl();
        List<FareData> jacksonData = _jacksonParser.parseJsonToObject(jsonData, type);

        _jsonSimpleParser = new JsonSimpleParserImpl();
        List<FareData> jsonSimple = _jsonSimpleParser.parseJsonToObject(jsonData, type);
        System.out.println("=========================================================================");
    }
}
