package com.example.evsalesmanagement.dto.fileupload;

public class FileUploadResponseDTO {

    private String fileName;

    private String filePath;
    

    public FileUploadResponseDTO(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public FileUploadResponseDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
}