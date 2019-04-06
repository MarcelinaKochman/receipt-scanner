package com.wfiis.receiptscanner.ocr;

import com.wfiis.receiptscanner.ocr.model.Metadata;
import com.wfiis.receiptscanner.ocr.preprocessing.ImagePreprocessingStrategy;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

import static com.wfiis.receiptscanner.util.JpgImageSaver.saveToFile;
import static com.wfiis.receiptscanner.util.TextSaver.saveToFile;
import static com.wfiis.receiptscanner.util.MultipartFileToBufferedImageConverter.convert;

@Component
public class TextRecognizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextRecognizer.class);
    private static final String PREFIX_PREPROCESSING = "AFTER_PROCESSING";
    private static final String PREFIX_OCR = "OCR";

    private final ITesseract instance;
    private final ImagePreprocessingStrategy strategy;

    @Autowired
    public TextRecognizer(ITesseract instance, ImagePreprocessingStrategy strategy) {
        this.instance = instance;
        this.strategy = strategy;
    }

    public String recognize(MultipartFile imageFile, Metadata metadata) { // TODO przerobic na strategie bo jest okropne
        LOGGER.info("OCR of image: {}", imageFile.getName());

        BufferedImage image = strategy.applyPreprocessing(convert(imageFile));

        if (image == null) {
            LOGGER.warn("Error while loading image file!");
            return null;
        }

        String result = null;

        try {
            result = instance.doOCR(image);
        } catch (TesseractException e) {
            LOGGER.error("Error while OCR recognizing: {}", e.getMessage());
        }

        pipelineSaveIfNeeded(metadata, image, result);

        return result;
    }

    private void pipelineSaveIfNeeded(Metadata metadata, BufferedImage image, String text) {
        if(metadata.isShouldBeSavedAsFiles()) {

            saveToFile(image, metadata.getFileName(), metadata.getDirectoryName(), PREFIX_PREPROCESSING);
            saveToFile(text, metadata.getFileName(), metadata.getDirectoryName(), PREFIX_OCR);

        }
    }

}
