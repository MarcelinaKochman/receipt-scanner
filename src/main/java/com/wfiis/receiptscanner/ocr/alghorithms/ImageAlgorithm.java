package com.wfiis.receiptscanner.ocr.alghorithms;

import java.awt.image.BufferedImage;

public interface ImageAlgorithm {
    BufferedImage process(BufferedImage image);
}
