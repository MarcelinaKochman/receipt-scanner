package com.wfiis.receiptscanner.ocr.alghorithms;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
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
        source = gammaCorrection(source);

//        NOT INCREASE ACCURACY
//        threshold(source);
//        morphologicalOpenClose(source);
//        source = contrast(source);

        return matToBufferedImage(source);
    }

    private void morphologicalOpenClose(Mat image) {
        Mat kernel = new Mat(image.size(), CvType.CV_8UC1, new Scalar(255));
        Imgproc.morphologyEx(image, image, Imgproc.MORPH_OPEN, kernel);
        Imgproc.morphologyEx(image, image, Imgproc.MORPH_CLOSE, kernel);
    }


    private void threshold(Mat source) {
        Imgproc.threshold(source, source, 0, 255, Imgproc.THRESH_OTSU);
    }

    private Mat contrast(Mat image) {
        double alpha = 0.6;
        double beta = 80;

        Mat newImage = Mat.zeros(image.size(), image.type());
        byte[] imageData = new byte[(int) (image.total()*image.channels())];
        image.get(0, 0, imageData);
        byte[] newImageData = new byte[(int) (newImage.total()*newImage.channels())];
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                for (int c = 0; c < image.channels(); c++) {
                    double pixelValue = imageData[(y * image.cols() + x) * image.channels() + c];
                    pixelValue = pixelValue < 0 ? pixelValue + 256 : pixelValue;
                    newImageData[(y * image.cols() + x) * image.channels() + c]
                            = saturate(alpha * pixelValue + beta);
                }
            }
        }
        newImage.put(0, 0, newImageData);
        return newImage;
    }

    private Mat gammaCorrection(Mat image) {
        double gammaValue = 1.6;
        Mat lookUpTable = new Mat(1, 256, CvType.CV_8U);
        byte[] lookUpTableData = new byte[(int) (lookUpTable.total()*lookUpTable.channels())];
        for (int i = 0; i < lookUpTable.cols(); i++) {
            lookUpTableData[i] = saturate(Math.pow(i / 255.0, gammaValue) * 255.0);
        }
        lookUpTable.put(0, 0, lookUpTableData);
        Mat img = new Mat();
        Core.LUT(image, lookUpTable, img);
        return img;
    }

    private byte saturate(double val) {
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : (iVal < 0 ? 0 : iVal);
        return (byte) iVal;
    }
}
