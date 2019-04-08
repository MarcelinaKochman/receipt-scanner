package com.wfiis.receiptscanner.efficiency;

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
public class SumExtractorAcurracy {

    private String accuracyTest = "accuracyTest";
    private String SUM_PREFIX = "SUM_PLN";

    @Autowired
    private ResultProvider resultProvider;

    @Autowired
    private TextFromFilesProvider textFromFilesProvider;

    @Autowired
    private SumExtractor sumExtractor;

    @Test
    public void calculateAccuracy() {
        Map<String, String> results = resultProvider.getMapResults(getMetadata(accuracyTest, false, ".jpg"));

        Map<String, String> prices = results.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> sumExtractor.extractSum(x.getValue())));
        prices.forEach((key, value) -> saveToFile(value, key, accuracyTest, SUM_PREFIX));

        Map<String, String> expectedPrices = textFromFilesProvider.getTextFromFiles(getMetadata(accuracyTest, false, ".txt"));

        double accuracy = calculateAccuracy(prices, expectedPrices);

        System.out.println("Accuracy (perfect match): " + accuracy * 100  + "%");
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
