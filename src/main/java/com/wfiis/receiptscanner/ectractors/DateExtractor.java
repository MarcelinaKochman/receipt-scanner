package com.wfiis.receiptscanner.ectractors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wfiis.receiptscanner.ectractors.Regexs.*;

@Component
public class DateExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateExtractor.class);
    private static final Pattern DATE_PATTERN_YYYY_MM_DD = Pattern.compile(DATE_YYYY_MM_DD);
    private static final Pattern DATE_PATTERN_DD_MM_YYYY = Pattern.compile(DATE_DD_MM_YYYY);
    private static final Pattern DATE_PATTERN_YYrMM_DD = Pattern.compile(DATE_YYrMM_DD);

    public String extractDate(String string) {
        LOGGER.info("Searching DATE in format yyyy-mm-dd in string: \n{}", string);
        String date = getDateFormat1(DATE_PATTERN_YYYY_MM_DD, string);
        if (date.isEmpty()) {
            date = getDateFormat1(DATE_PATTERN_DD_MM_YYYY, string);
            if (date.isEmpty()) {
                date = getDateFormat1(DATE_PATTERN_YYrMM_DD, string);
            }
        }
        return date;
    }

    private String getDateFormat1(Pattern pattern, String string) {
        String sum = "";

        Matcher m = pattern.matcher(string);
        if (m.find()) {
            sum = m.group(0);
        }
        return sum;
    }

}
