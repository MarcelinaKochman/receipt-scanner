package com.wfiis.receiptscanner;

import java.util.List;

import static java.util.List.of;

public class Constants {
    public static final List<String> WRONG_CHARS_FOR_DOT_OR_COMMA = of("<", ">", "'", "›");
    public static final String CORRECT_COMMA = ",";
}
