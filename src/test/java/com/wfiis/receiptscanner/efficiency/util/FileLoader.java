package com.wfiis.receiptscanner.efficiency.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileLoader {
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    public List<MultipartFile> loadAllFilesFromDirectory(String directory) {
        List<MultipartFile> filesInDirectory;

        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            filesInDirectory = paths
                    .filter(Files::isRegularFile)
                    .map(file -> load(file, file.getFileName().toString(), MULTIPART_FORM_DATA))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error while loading files from directory {} : {}", directory, e.getMessage());
            return null;
        }

        return filesInDirectory;
    }

    private MultipartFile load(Path path, String fileName, String contentType) {
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            LOGGER.error("Error while loading file: {}", e.getMessage());
            return null;
        }
        return new MockMultipartFile(fileName, fileName, contentType, content);
    }
}
