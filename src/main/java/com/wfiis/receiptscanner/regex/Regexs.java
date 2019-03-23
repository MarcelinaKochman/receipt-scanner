package com.wfiis.receiptscanner.regex;

public class Regexs {
    public static final String NEW_LINE_CHARACTER = "\n";

    public static final  String PARAGON_FISKALNY = "[PARAGON]{6,}|[FISKALNY]{7,}";
    public static final String SPRZEDAZ_OPODATKOWANA = "[SPRZEDAZ]{7,}|[OPODATK]{6,}";
    public static final String PRODUCTS = "([PARAGON]{6,}|[FISKALNY]{7,})([\\s\\S]*?\\n)([\\s\\S]*?)([SPRZEDAZsprzed]{6,}|[OPODATKopodatk]{5,})";
    public static final String PRODUCT_AND_PRICE = "([0-9A-Za-z ])+(?:[x*])+.*\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
}
