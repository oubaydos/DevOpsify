package com.winchesters.devopsify.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
    public static String InputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader stdInput
                = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder outputBuilder = new StringBuilder();
        String s;
        while ((s = stdInput.readLine()) != null) {
            outputBuilder.append(s);
        }
        return outputBuilder.toString();
    }

}
