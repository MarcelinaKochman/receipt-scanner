package com.wfiis.receiptscanner.efficiency;

import com.wfiis.receiptscanner.ectractors.DateExtractor;
import com.wfiis.receiptscanner.ectractors.SumExtractor;
import com.wfiis.receiptscanner.efficiency.util.TextFromFilesProvider;
import com.wfiis.receiptscanner.efficiency.util.ResultProvider;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.stream.Collectors;

import static com.wfiis.receiptscanner.util.TextSaver.saveToFile;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SumExtractorAccuracy {

    private String accuracyTest = "accuracyTest";
    private String SUM_PREFIX = "SUM_PLN";
    private String DATE_PREFIX = "DATE";

    @Autowired
    private ResultProvider resultProvider;

    @Autowired
    private TextFromFilesProvider sumTextFromFileProvider;

    @Autowired
    private TextFromFilesProvider dateTextFromFileProvider;

    @Autowired
    private SumExtractor sumExtractor;

    @Autowired
    private DateExtractor dateExtractor;

    @Test
    public void calculateAccuracy() {
        Map<String, String> results = resultProvider.getMapResults(getMetadata(accuracyTest, false, ".jpg"));

        Map<String, String> prices = getPrices(results);
        Map<String, String> dates = getDates(results);
        prices.forEach((key, value) -> saveToFile(value, key, accuracyTest, SUM_PREFIX));
        dates.forEach((key, value) -> saveToFile(value, key, accuracyTest, DATE_PREFIX));

        Map<String, String> expectedPrices = sumTextFromFileProvider.getTextFromFiles(getMetadata(accuracyTest, false, ".txt"));
        Map<String, String> expectedDates = dateTextFromFileProvider.getTextFromFiles(getMetadata(accuracyTest, false, ".txt"));

        double accuracy = calculateAccuracy(prices, expectedPrices);
        double accuracyDates = calculateAccuracy(dates, expectedDates);

        System.out.println("Accuracy - SUM - (perfect match): " + accuracy * 100  + "%");
        System.out.println("Accuracy - DATES -(perfect match): " + accuracyDates * 100  + "%");
    }

    private Map<String, String> getPrices(Map<String, String> results) {
        return results.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> sumExtractor.extractSum(x.getValue())));
    }

    private Map<String, String> getDates(Map<String, String> results) {
        return results.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> dateExtractor.extractDate(x.getValue())));
    }

    private double calculateAccuracy(Map<String, String> prices, Map<String, String> expectedPrices) {
        long numberOfPerfectMatch = prices.entrySet().stream()
                .filter(price -> isEquals(expectedPrices, price))
                .count();
        return (double) numberOfPerfectMatch / (double) prices.size();
    }

    private boolean isEquals(Map<String, String> expectedPrices, Map.Entry<String, String> price) {
        return expectedPrices.get(price.getKey()).equals(price.getValue());
    }

    private Metadata getMetadata(String directoryName, boolean saveToFile, String extension) {
        Metadata metadata = new Metadata();
        metadata.setDirectoryName(directoryName);
        metadata.setShoulBeSavedAsFiles(saveToFile);
        metadata.setExtension(extension);
        return metadata;
    }

}
