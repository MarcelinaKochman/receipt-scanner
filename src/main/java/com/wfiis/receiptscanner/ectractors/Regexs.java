package com.wfiis.receiptscanner.ectractors;

public class Regexs {
    public static final String NEW_LINE_CHARACTER = "\n";

    public static final  String PARAGON_FISKALNY = "[PARAGON]{6,}|[FISKALNY]{7,}";
    public static final String SPRZEDAZ_OPODATKOWANA = "[SPRZEDAZ]{7,}|[OPODATK]{6,}";
    public static final String PRICE = "\\d{1,5}(?:[.,]\\d{3})*(?:[.,]\\d{1,2})";
    public static final String DATE_YYYY_MM_DD = "\\d{4}-\\d{2}-\\d{2}";
    public static final String DATE_DD_MM_YYYY = "\\d{2}-\\d{2}-\\d{4}";
    public static final String DATE_YYrMM_DD = "\\d{2}r\\d{2}\\.\\d{2}";
    public static final String PRODUCTS = "([PARAGON]{6,}|[FISKALNY]{7,})([\\s\\S]*?\\n)([\\s\\S]*?)([SPRZEDAZsprzed]{6,}|[OPODATKopodatk]{5,})";
    public static final String PRODUCT_AND_PRICE = "([0-9A-Za-z ])+(?:[x*])+.*\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
}
