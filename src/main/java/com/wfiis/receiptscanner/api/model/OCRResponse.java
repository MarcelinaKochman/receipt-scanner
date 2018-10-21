package com.wfiis.receiptscanner.api.model;

public class OCRResponse {

    private String recognizedText;
    private long timeOfExecution;

    public String getRecognizedText() {
        return recognizedText;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public long getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(long timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }
}
