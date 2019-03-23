package com.wfiis.receiptscanner.util;

import java.util.ArrayList;
import java.util.Arrays;

public class StringUtil {

    private StringUtil() {
    }

    public static ArrayList<String> splitString(String recognizedText, String delimiter) {
        return new ArrayList<>(Arrays.asList(recognizedText.split(delimiter)));
    }

}
