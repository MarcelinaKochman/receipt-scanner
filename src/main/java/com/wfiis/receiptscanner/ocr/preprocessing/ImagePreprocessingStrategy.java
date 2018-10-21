package com.wfiis.receiptscanner.ocr.preprocessing;

import com.wfiis.receiptscanner.ocr.alghorithms.ImageAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.List;

@Component
public class ImagePreprocessingStrategy {

    private List<ImageAlgorithm> preprocessingAlghorithms;

    @Autowired
    public ImagePreprocessingStrategy(List<ImageAlgorithm> preprocessingAlghorithms) {
        this.preprocessingAlghorithms = preprocessingAlghorithms;
    }

    public BufferedImage applyPreprocessing(BufferedImage bufferedImage) {
        for (ImageAlgorithm algorithm : preprocessingAlghorithms) {
            bufferedImage = algorithm.process(bufferedImage);
        } // TODO how to achieve it with functional java 8 style
        return bufferedImage;
    }
}
