package com.wfiis.receiptscanner.efficiency.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MultipartFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipartFileLoader.class);
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";


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

    private static final String DIGITS_ONLY = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*.jpg$";

    private FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            return name.matches(DIGITS_ONLY);
        }
    };

    public Map<String, BufferedImage> loadAllImages(String directory) {
        Map<String, BufferedImage> bufferedImages = new HashMap<>();
        final File dir = new File(directory);

        for (final File f : dir.listFiles(IMAGE_FILTER)) {
            BufferedImage img = null;

            try {
                img = ImageIO.read(f);
                bufferedImages.put(f.getName(), img);
                System.out.println(bufferedImages.size());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bufferedImages;
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
