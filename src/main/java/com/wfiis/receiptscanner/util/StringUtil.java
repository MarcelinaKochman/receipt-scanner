package com.wfiis.receiptscanner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    private StringUtil() {
    }

    public static ArrayList<String> splitString(String recognizedText, String delimiter) {
        return new ArrayList<>(Arrays.asList(recognizedText.split(delimiter)));
    }

    public static String removeWhitespaces(String string) {
        return string.replaceAll("\\s","");
    }

    public static String replaceCharacters(String string, String correct, List<String> wrong) {
        for (String character : wrong) {
            string = string.replaceAll(character, correct);
        }

        return string;
    }

}
