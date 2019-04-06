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
            "ABC III",
            "AAAAAAA",
            "BCA    ",
            "A IIII ",
            "IIIII  "
    );

    @Test
    public void shouldCorrectCountOccurences() {
        List<Long> expexted = of(3L, 1L, 3L, 1L, 0L);

        List<Long> count = occurrencesCounter.count(textList, mather);

        assertEquals(expexted, count);
    }
}