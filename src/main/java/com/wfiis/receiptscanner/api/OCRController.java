package com.wfiis.receiptscanner.api;

import com.wfiis.receiptscanner.api.model.OCRResponse;
import com.wfiis.receiptscanner.ocr.TextRecognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OCRController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OCRController.class);

    private final TextRecognizer textRecognizer;

    @Autowired
    public OCRController(TextRecognizer textRecognizer) {
        this.textRecognizer = textRecognizer;
    }

    @PostMapping("/ocr")
    public OCRResponse ocr(@RequestParam("file") MultipartFile multipartFile) {
        OCRResponse response = new OCRResponse();

        long startTime = System.currentTimeMillis();

//        response.setRecognizedText(textRecognizer.recognize(multipartFile));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        response.setTimeOfExecution(elapsedTime);

        LOGGER.info("OCRResponse: {}", response);
        return response;
    }

}
