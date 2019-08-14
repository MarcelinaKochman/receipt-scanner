package com.wfiis.receiptscanner.ectractors.counters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.List.of;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OccurrencesCounterTest {

    @InjectMocks
    private OccurrencesCounter occurrencesCounter;
    private List<String> mather = of("A", "B", "C");
    private List<String> textList = of(
            "IGNORED",
            "IGNORED",
            "IGNORED",
            "IGNORED",
            "ABC III",
            "AAAAAAA",
            "BCA    ",
            "A IIII ",
            "IIIII  "
    );

    @Test
    public void shouldCorrectCountOccurences() {
        List<Long> expexted = of(0L, 0L, 0L, 0L, 3L, 1L, 2L, 1L, 1L);

        List<Long> count = occurrencesCounter.count(textList, mather);

        assertEquals(expexted, count);
    }

    @Test
    public void countLongestSortedSublist() {
        List<Integer> list = of(4, 2, 3, 1, 7, 8, 0);

        long actual = occurrencesCounter.countLongestSortedSublist(list);

        assertEquals(actual, 3L);
    }
}