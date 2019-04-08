package com.wfiis.receiptscanner.efficiency.util;

import com.wfiis.receiptscanner.ocr.TextRecognizer;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.io.FilenameUtils.removeExtension;

@Component
public class TextFromFilesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextFromFilesProvider.class);
    private static final String DIGITS_ONLY = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*.txt$";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @Autowired
    private MultipartFileLoader multipartFileLoader;

    @Autowired
    private TextRecognizer textRecognizer;

    public Map<String, String> getTextFromFiles(Metadata metadata) {

        List<MultipartFile> textFiles = multipartFileLoader
                .loadAllFilesFromDirectory(metadata.getDirectoryName())
                .stream()
                .filter(file -> file.getName().matches(DIGITS_ONLY))
                .collect(Collectors.toList());

        return textFiles.stream()
                .collect(Collectors.toMap(file -> removeExtension(file.getName()), file -> readString(file)));
    }

    private String readString(MultipartFile file) {
        try {
            return new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
