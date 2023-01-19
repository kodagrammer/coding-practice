package com.spb.practice.springbootpractice.controller;

import com.spb.practice.springbootpractice.service.JsonParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "test/json",  produces = { "application/json;charset=UTF-8" })
public class JsonParserController {
    public final JsonParserService parserService;

    @GetMapping("/convertNullTest")
    public ResponseEntity<?> testJsonConvertNull() throws Exception {
        parserService.testJsonNullConvert();
        return ResponseEntity.ok("convertNullTest ok.");
    }
}
