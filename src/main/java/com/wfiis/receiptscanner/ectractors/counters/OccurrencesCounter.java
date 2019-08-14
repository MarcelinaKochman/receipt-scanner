package com.wfiis.receiptscanner.ectractors.counters;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.wfiis.receiptscanner.util.ListUtil.isSorted;

@Component
public class OccurrencesCounter {

    private static final double PERCENT_OF_IGNORED = 0.5;

    public List<Long> count(List<String> textLines, List<String> matcher) {
        List<Long> result = new ArrayList<>();

        int numberOfElements = textLines.size();
        int firstLineToCheck = (int) (numberOfElements * PERCENT_OF_IGNORED);

        for (int i = 0; i < numberOfElements; ++i) {
            long counter = 0L;

            if (i >= firstLineToCheck) {
                String line = textLines.get(i);
                List<Integer> indexes = getIndexesOfLettersFromSet(line, matcher);

                counter = countLongestSortedSublist(indexes);
            }

            result.add(counter);
        }

        return result;
    }

    public long countLongestSortedSublist(List<Integer> indexes) {
        int maxLength = 0;
        int currentCounter = 1;

        for (int i = 1; i < indexes.size(); ++i) {
            if (indexes.get(i) > indexes.get(i-1)) {
                currentCounter++;
            } else {
                if (currentCounter > maxLength) {
                    maxLength = currentCounter;
                }
                currentCounter = 1;
            }
        }

        if (currentCounter > maxLength) {
            maxLength = currentCounter;
        }

        return maxLength;
    }

    private List<Integer> getIndexesOfLettersFromSet(String line, List<String> matcher) {
        return matcher.stream()
                .map(line::indexOf)
                .filter(index -> index != -1)
                .collect(Collectors.toList());
    }


}
