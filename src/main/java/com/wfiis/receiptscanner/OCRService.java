package com.wfiis.receiptscanner;

import com.wfiis.receiptscanner.api.model.Product;
import com.wfiis.receiptscanner.ocr.TextRecognizer;
import com.wfiis.receiptscanner.ocr.model.Metadata;
import com.wfiis.receiptscanner.regex.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.wfiis.receiptscanner.regex.Regexs.NEW_LINE_CHARACTER;
import static com.wfiis.receiptscanner.util.StringUtil.splitString;

@Service
public class OCRService {

    private final TextRecognizer textRecognizer;
    private final Matcher matcher;

    @Autowired
    public OCRService(TextRecognizer textRecognizer, Matcher matcher) {
        this.textRecognizer = textRecognizer;
        this.matcher = matcher;
    }

    public List<Product> scanReceipt(MultipartFile receiptImage, Metadata metadata) {
        String recognizedText = textRecognizer.recognize(receiptImage, metadata);

        List<String> receiptLines = splitString(recognizedText, NEW_LINE_CHARACTER);

        return new ArrayList<Product>();
    }

}
