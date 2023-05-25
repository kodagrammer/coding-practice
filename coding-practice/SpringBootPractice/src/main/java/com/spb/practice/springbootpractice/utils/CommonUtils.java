package com.spb.practice.springbootpractice.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtils {
    public static String loadFile(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        if(Files.exists(Paths.get(path))){
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = null;
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
        }
        return stringBuilder.toString();
    }
}
