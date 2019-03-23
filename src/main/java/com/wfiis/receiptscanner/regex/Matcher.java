package com.wfiis.receiptscanner.regex;

import com.wfiis.receiptscanner.ocr.TextRecognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Matcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Matcher.class);

    public boolean isMatch(String string, String regex) {
        LOGGER.info("Matching string: {} with regex: {}", string, regex);

        string = string.toUpperCase();
        Pattern p = Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(string);

        boolean isMatch = m.find();
        LOGGER.info("Result of matching: {}", isMatch);
        return isMatch;
    }

}
