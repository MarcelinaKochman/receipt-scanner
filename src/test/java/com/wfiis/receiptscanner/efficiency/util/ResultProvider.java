package com.wfiis.receiptscanner.efficiency.util;

import com.wfiis.receiptscanner.ocr.TextRecognizer;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultProvider.class);
    private static final String DIGITS_ONLY = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*.jpg$";

    @Autowired
    private FileLoader fileLoader;

    @Autowired
    private TextRecognizer textRecognizer;

    public List<String> getResults(Metadata metadata) {

        List<MultipartFile> shotsOfReceipt = fileLoader
                .loadAllFilesFromDirectory(metadata.getDirectoryName())
                .stream()
                .filter(file -> file.getName().matches(DIGITS_ONLY))
                .collect(Collectors.toList());

        return shotsOfReceipt.stream()
                .map(shot -> getRecognize(metadata, shot))
                .collect(Collectors.toList());
    }

    private String getRecognize(Metadata metadata, MultipartFile shot) {
        String fileName = FilenameUtils.removeExtension(shot.getName());
        metadata.setFileName(fileName);
        return textRecognizer.recognize(shot, metadata);
    }

}
