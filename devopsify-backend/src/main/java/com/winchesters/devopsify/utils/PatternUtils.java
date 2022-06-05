package com.winchesters.devopsify.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    public static List<Integer> getAllIntegers(String s){
        List<Integer> list = new ArrayList<>();

        Matcher m = Pattern.compile("\\d+").matcher(s);
        while(m.find())
        {
            int num = Integer.parseInt(m.group());
            list.add(num);
        }
        return list;
    }
}
