package com.wfiis.receiptscanner.efficiency.util;

import com.wfiis.receiptscanner.ocr.TextRecognizer;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.io.FilenameUtils.removeExtension;

@Component
public class ResultProvider {

    @Autowired
    private MultipartFileLoader multipartFileLoader;

    @Autowired
    private TextRecognizer textRecognizer;

    public List<String> getResults(Metadata metadata) {

        Map<String, BufferedImage> shotsOfReceipt = multipartFileLoader
                .loadAllImages(metadata.getDirectoryName());

        return shotsOfReceipt
                .entrySet()
                .stream()
                .map(entry -> getRecognize(metadata, entry))
                .collect(Collectors.toList());
    }

    public Map<String, String> getMapResults(Metadata metadata) {

        Map<String, BufferedImage> shotsOfReceipt = multipartFileLoader
                .loadAllImages(metadata.getDirectoryName());

        return shotsOfReceipt
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> removeExtension(entry.getKey()), entry -> getRecognize(metadata, entry)));
    }
    
    private String getRecognize(Metadata metadata, Map.Entry<String, BufferedImage> entry) {
        String fileName = removeExtension(entry.getKey());
        metadata.setFileName(fileName);
        return textRecognizer.recognize(entry.getValue(), metadata);
    }

}
