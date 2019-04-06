package com.wfiis.receiptscanner.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JpgImageSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpgImageSaver.class);
    private static final String JPG = "jpg";

    public static void saveToFile(BufferedImage bufferedImage, String filename, String directoryName, String prefix) {
        String pathname = buildPathname(filename, directoryName, prefix);
        File file = new File(pathname);
        LOGGER.debug("Saved file: {}", pathname);

        try {
            ImageIO.write(bufferedImage, JPG, file);
        } catch (IOException e) {
            LOGGER.error("Error while saving file: {} \n {}", e.getMessage());
        }

    }

    private static String buildPathname(String filename, String directoryName, String prefix) {
        return directoryName + "/" + filename + "_" + prefix + "." + JPG;
    }
}
