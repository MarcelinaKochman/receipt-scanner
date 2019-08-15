package com.wfiis.receiptscanner;

import com.wfiis.receiptscanner.ectractors.Matcher;
import com.wfiis.receiptscanner.ocr.TextRecognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OCRService {

    private final TextRecognizer textRecognizer;
    private final Matcher matcher;

    @Autowired
    public OCRService(TextRecognizer textRecognizer, Matcher matcher) {
        this.textRecognizer = textRecognizer;
        this.matcher = matcher;
    }

//    public List<Product> scanReceipt(MultipartFile receiptImage, Metadata metadata) {
//        String recognizedText = textRecognizer.recognize(receiptImage, metadata);
//
//        List<String> receiptLines = splitString(recognizedText, NEW_LINE_CHARACTER);
//
//        return new ArrayList<Product>();
//    }

}
