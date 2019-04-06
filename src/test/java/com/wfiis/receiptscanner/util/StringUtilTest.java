package com.wfiis.receiptscanner.util;

import org.junit.Test;

import java.util.List;

import static java.util.List.of;
import static com.wfiis.receiptscanner.util.StringUtil.replaceCharacters;
import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    @Test
    public void shouldReplaceAllAAndBWithC() {
        String sample = "AA BB";
        String correct = "C";
        List<String> wrong = of("A", "B");

        String expected = "CC CC";

        String actual = replaceCharacters(sample, correct, wrong);

        assertEquals(expected, actual);
    }
}