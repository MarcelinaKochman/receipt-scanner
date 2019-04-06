package com.wfiis.receiptscanner.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class TextSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextSaver.class);
    private static final String TXT = "txt";

    public static void saveToFile(String textToSave, String filename, String directoryName, String prefix) {
        String pathname = buildPathname(filename, directoryName, prefix);
        File file = new File(pathname);
        LOGGER.debug("Saved file: {}", pathname);

        try {
            FileUtils.writeStringToFile(file, textToSave, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Error while saving file: {} \n {}", e.getMessage());
        }
    }

    private static String buildPathname(String filename, String directoryName, String prefix) {
        return directoryName + "/" + filename + "_" + prefix + "." + TXT;
    }
}
