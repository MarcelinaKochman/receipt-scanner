package com.wfiis.receiptscanner.ocr.model;

public class Metadata {
    private String directoryName;
    private String fileName;
    private boolean shoulBeSavedAsFiles;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isShouldBeSavedAsFiles() {
        return shoulBeSavedAsFiles;
    }

    public void setShoulBeSavedAsFiles(boolean shoulBeSavedAsFiles) {
        this.shoulBeSavedAsFiles = shoulBeSavedAsFiles;
    }
}
