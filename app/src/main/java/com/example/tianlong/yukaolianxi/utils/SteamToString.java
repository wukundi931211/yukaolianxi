package com.example.tianlong.yukaolianxi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SteamToString {
    //字节流转字符流
    public static String getString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String s = null;
        StringBuilder builder = new StringBuilder();
        while ((s=reader.readLine())!=null) {
            builder.append(s);
        }
        reader.close();
        return  builder.toString();


    }
}
