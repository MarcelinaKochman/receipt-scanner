package com.wfiis.receiptscanner.efficiency;

import com.wfiis.receiptscanner.ectractors.SumExtractor;
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
public class Pipeline {

    private String pipeline = "pipeline";
    private String SUM_PREFIX = "SUM_PLN";

    @Autowired
    private ResultProvider resultProvider;

    @Autowired
    private SumExtractor sumExtractor;

    @Test
    public void testExtractSumFromReceipt() {
        Map<String, String> results = resultProvider.getMapResults(getMetadata(pipeline, true));

        Map<String, String> prices = results.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> sumExtractor.extractSum(x.getValue())));

        prices.forEach((key, value) -> saveToFile(value, key, pipeline, SUM_PREFIX));
    }

    private Metadata getMetadata(String directoryName, boolean saveToFile) {
        Metadata metadata = new Metadata();
        metadata.setDirectoryName(directoryName);
        metadata.setShoulBeSavedAsFiles(saveToFile);
        return metadata;
    }

}
