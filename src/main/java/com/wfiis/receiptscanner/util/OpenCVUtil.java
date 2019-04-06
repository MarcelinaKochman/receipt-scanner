package com.wfiis.receiptscanner.util;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class OpenCVUtil {
    public static Mat bufferedImageToMat(BufferedImage in) {
        Mat out;

        int height = in.getHeight();
        int width = in.getWidth();
        out = new Mat(height, width, CvType.CV_8UC1);
        byte[] pixels = ((DataBufferByte) in.getRaster().getDataBuffer()).getData();

        out.put(0, 0, pixels);
        return out;
    }

    public static BufferedImage matToBufferedImage(Mat in) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", in, mob);
        byte ba[] = mob.toArray();

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(ba));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
