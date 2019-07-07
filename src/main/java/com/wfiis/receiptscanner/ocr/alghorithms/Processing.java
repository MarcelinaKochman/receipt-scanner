package com.wfiis.receiptscanner.ocr.alghorithms;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

import static com.wfiis.receiptscanner.util.OpenCVUtil.bufferedImageToMat;
import static com.wfiis.receiptscanner.util.OpenCVUtil.matToBufferedImage;
import static org.opencv.photo.Photo.fastNlMeansDenoising;

@Component
public class Processing implements ImageAlgorithm {

    @Override
    public BufferedImage process(BufferedImage image) {
        Mat source = bufferedImageToMat(image);
        Mat original = source.clone();

        // TODO: consider to move to bufferedImageToMat util

        Imgproc.cvtColor(source, source, Imgproc.COLOR_RGB2GRAY);
        fastNlMeansDenoising(source, source);

        return matToBufferedImage(source);
    }
}
