package com.wfiis.receiptscanner.ectractors;

import com.wfiis.receiptscanner.ectractors.counters.OccurrencesCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wfiis.receiptscanner.Constants.CORRECT_COMMA;
import static com.wfiis.receiptscanner.Constants.WRONG_CHARS_FOR_DOT_OR_COMMA;
import static com.wfiis.receiptscanner.ectractors.Regexs.PRICE;
import static com.wfiis.receiptscanner.util.StringUtil.removeWhitespaces;
import static com.wfiis.receiptscanner.util.StringUtil.replaceCharacters;
import static java.util.Arrays.asList;
import static java.util.Collections.max;
import static java.util.List.of;

@Component
public class SumExtractor {

    private final OccurrencesCounter occurrencesCounter;

    private static final Logger LOGGER = LoggerFactory.getLogger(SumExtractor.class);
    private static final List<String> CHARACTER_LIST = of("S", "U", "M", "A", "P", "L", "N");
    private static final Pattern PRICE_PATTERN = Pattern.compile(PRICE);

    @Autowired
    public SumExtractor(OccurrencesCounter occurrencesCounter) {
        this.occurrencesCounter = occurrencesCounter;
    }

    public String extractSum(String string) {
        LOGGER.info("Searching \"SUMA PLN\" in string: \n{}", string);

        String sumLine = getLineWithSumPln(string);
        LOGGER.info("Line with SUMA PLN: \n{}", sumLine);

        String sum = getSumFromLine(sumLine);
        LOGGER.info("SUMA PLN: \n{}", sum);
        return sum.replaceAll(",", ".");
    }

    private String getLineWithSumPln(String string) {
        string = string.toUpperCase();
        List<String> textLines = asList(string.split("\n"));
        List<Long> numbersOfOccurence = occurrencesCounter.count(textLines, CHARACTER_LIST);
        int maxOccurrencesIdx = getIndexOfMaxValue(numbersOfOccurence);
        return textLines.get(maxOccurrencesIdx);
    }

    private String getSumFromLine(String sumLine) {
        String sum = "";

        sumLine = prepareSumLine(sumLine);

        Matcher m = PRICE_PATTERN.matcher(sumLine);
        if (m.find()) {
            sum = m.group(0);
        }
        return sum;
    }

    private String prepareSumLine(String sumLine) {
//        sumLine = removeWhitespaces(sumLine);
        sumLine = replaceCharacters(sumLine, CORRECT_COMMA, WRONG_CHARS_FOR_DOT_OR_COMMA);
        return sumLine;
    }

    private int getIndexOfMaxValue(List<Long> numbersOfOccurrence) {
        Long maxVal = max(numbersOfOccurrence);
        return numbersOfOccurrence.indexOf(maxVal);
    }

}
