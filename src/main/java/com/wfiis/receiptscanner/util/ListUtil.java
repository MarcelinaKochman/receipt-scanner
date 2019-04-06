package com.wfiis.receiptscanner.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
    public static boolean isSorted(List<Integer> list) {
        return list.stream()
                .sorted()
                .collect(Collectors.toList())
                .equals(list);
    }
}
