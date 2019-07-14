package com.wfiis.receiptscanner.efficiency.util;

import com.wfiis.receiptscanner.ocr.TextRecognizer;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.io.FilenameUtils.removeExtension;

public class TextFromFilesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextFromFilesProvider.class);
    private static final String DIGITS_ONLY = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*";
    private static final String EXTENSION = ".txt$";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private String suffix;
    private MultipartFileLoader multipartFileLoader;
    private TextRecognizer textRecognizer;

    public TextFromFilesProvider(String suffix, TextRecognizer textRecognizer, MultipartFileLoader multipartFileLoader) {
        this.suffix = suffix;
        this.textRecognizer = textRecognizer;
        this.multipartFileLoader = multipartFileLoader;
    }

    public Map<String, String> getTextFromFiles(Metadata metadata) {
        List<MultipartFile> textFiles = multipartFileLoader
                .loadAllFilesFromDirectory(metadata.getDirectoryName())
                .stream()
                .filter(file -> file.getName().matches(buildMatcher()))
                .collect(Collectors.toList());

        return textFiles.stream()
                .collect(Collectors.toMap(file -> buildKey(file.getName()), file -> readString(file)));
    }

    private String buildMatcher() {
        return DIGITS_ONLY + suffix + EXTENSION;
    }

    private String buildKey(String fileName) {
        fileName = removeExtension(fileName);
        return removeSuffix(fileName, suffix);
    }

    private String readString(MultipartFile file) {
        try {
            return new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String removeSuffix(final String s, final String suffix)
    {
        if (s != null && suffix != null && s.endsWith(suffix)){
            return s.substring(0, s.length() - suffix.length());
        }
        return s;
    }

}
