package com.wfiis.receiptscanner.efficiency;

import com.wfiis.receiptscanner.efficiency.util.ResultProvider;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import com.wfiis.receiptscanner.ectractors.ProductsExtractor;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OCREfficiency {

    // TODO read from text file in the same catalog as jpgs
    private String PATTERN = "MARKET PUNKT 4PL 5p. z o.o. 5p. K.\n 30-147 Kraków, ul. Na Błonie 11B\n Pawilon: Kraków, ul. Budryka 6\n Dziękujemy, zapraszamy ponownie !!!\n NIP 677-002-37-73\n 2018-05-21 729649\n PARAGON FISKALNY\n NATKA SZTDD_D__D 1 x1.59 1,59D\n PRZECIER POMID_B 1 x3,95 3,95B\n REKLAMOWKA LEW1A 1 x0,40 0,40A\n KUKU WAFLE KUK_D 1 x1,35 1,35D\n ZYWIEC PIWO 0,5A 1 x3,75 3,75A\n TUŃCZYK KAWAŁKID 1 x5,99 5.99D\n KUKU WAFLE KUK_D 1 x1,35 1,35D\n KUKURYDZA KONS_B 1 x1,99 1,99B\n LAYS PROSTO Z 1B 1 x5,99 5,99B\n JOGURT NAT.GRE_D 1 x2,09 2,09D\n SPRZEDAZ OPODATK. A 4.15\n PTU A 23.00 % 0.78\n SPRZEDAZ OPODATK. B 11.93\n PTU B 8.00 % 0.88\n SPRZEDAZ OPODATK. D 12.37\n PTU D 5.00 % 0.59\n SUMA PTU 2,25\n SUMA PLN 28.45\n 00719 #KA3N1416 3G0 20:32\n 33E95DCC8004405CDE66D5BAEA7E4D4055E37C70\n PL BEV 13514326\n NP sys. 1416877\n Karta Zgoda. ORYZACJI! 28.45 PLN";
    private String directoryName = "sample";
    private DiffMatchPatch dmp = new DiffMatchPatch();
    private boolean shoulBeSavedAsFile = true;

    @Autowired
    private ResultProvider resultProvider;

    @Autowired
    private ProductsExtractor productsExtractor;

    @Test
    public void testEfficiencyForFewShotsOfTheSameReceipt() {
        List<String> results = resultProvider.getResults(getMetadata());

        List<Integer> diffs = compareWithPattern(results, PATTERN);
        assertEquals(PATTERN, results.get(4));
    }

    @Test
    public void testProductsExtraction() {
        List<String> results = resultProvider.getResults(getMetadata());

        String recognizedText = results.get(1);

        String products = productsExtractor.extractProducts(recognizedText);
    }

    private Integer calculateDiff(String result, String pattern) {
        int numberOfDiffs = dmp.diffMain(result, pattern).size();
        System.out.println(numberOfDiffs);
        return numberOfDiffs;
    }

    private List<Integer> compareWithPattern(List<String> results, String pattern) {

        List<Integer> numbersOfDiffs = results.stream()
                .map(result -> calculateDiff(result, PATTERN))
                .collect(Collectors.toList());

        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(results.get(5), pattern);

        double average = numbersOfDiffs
                .stream()
                .mapToDouble(a -> a)
                .average()
                .getAsDouble();

        System.out.println("AVERAGE: " + average);
        return numbersOfDiffs;
    }

    private Metadata getMetadata() {
        Metadata metadata = new Metadata();
        metadata.setDirectoryName(directoryName);
        metadata.setShoulBeSavedAsFiles(shoulBeSavedAsFile);

        return metadata;
    }

}
