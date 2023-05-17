package org.example;

// MyFile class representing the data
class MyFile {
    private String fileName;
    private byte[] fileContent;

    public MyFile(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getFileExtension() {
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            return fileName.substring(index + 1);
        } else {
            return "No extension found.";
        }
    }
}
