package com.wfiis.receiptscanner.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.wfiis.receiptscanner.regex.Regexs.PARAGON_FISKALNY;
import static com.wfiis.receiptscanner.regex.Regexs.PRODUCTS;
import static com.wfiis.receiptscanner.regex.Regexs.SPRZEDAZ_OPODATKOWANA;

@Component
public class ProductsMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsMatcher.class);
    private static final Pattern PRODUCTS_PATTERN = Pattern.compile(PRODUCTS);
    private static final int PRODUCTS_GROUP = 3;

    public String extractProducts(String string) {
        LOGGER.info("Matching string: {} with regex: {}", string);

        String products = "";

        string = string.toUpperCase();
        java.util.regex.Matcher productsMatcher = PRODUCTS_PATTERN.matcher(string);

        if(productsMatcher.find()) {
            products = productsMatcher.group(PRODUCTS_GROUP);
        }

        LOGGER.info("Result of matching: {}", products);
        return products;
    }

}
