package com.example.evsalesmanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evsalesmanagement.dto.fileupload.FileUploadResponseDTO;

@Service
public class FileUploadService {

    @Value("${image-upload-path}")
    private String imagePathString;

    public FileUploadResponseDTO uploadImage(MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();

        // Tạo một tên file duy nhất bằng cách thêm Timestamp vào.
        // Ví dụ: 20251027150000_originalFileName.jpg
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String newFileName = timestamp + "_" + originalFileName;

        // Paths.get(chuỗi_đường_dẫn_gốc, tên_file_mới)
        // Phương thức resolve() giúp nối đường dẫn một cách an toàn.
        Path destinationPath = Paths.get(imagePathString).resolve(newFileName);

        // Nếu thư mục không tồn tại, tạo nó
        Files.createDirectories(destinationPath.getParent());

        // Thực hiện lưu file
        file.transferTo(destinationPath);

        FileUploadResponseDTO fileUploadResponseDTO = new FileUploadResponseDTO();
        fileUploadResponseDTO.setFileName(newFileName);
        fileUploadResponseDTO.setFileName(destinationPath.toString());

        return fileUploadResponseDTO;
    }

    public FileUploadResponseDTO deleteImage(String fileName) {
        FileUploadResponseDTO fileUploadResponseDTO = new FileUploadResponseDTO();

        Path destinationPath = Paths.get(imagePathString).resolve(fileName);

        File image = destinationPath.toFile();

        fileUploadResponseDTO.setFileName(image.getName());
        fileUploadResponseDTO.setFilePath(image.getPath());

        image.delete();

        return fileUploadResponseDTO;
    }

    public List<FileUploadResponseDTO> uploadMultipleImage(List<MultipartFile> files) throws IOException {
        List<FileUploadResponseDTO> fileUploadResponseDTOs = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            String newFileName = timestamp + "_" + originalFileName;
            Path destinationPath = Paths.get(imagePathString).resolve(newFileName);

            Files.createDirectories(destinationPath.getParent());

            file.transferTo(destinationPath);

            FileUploadResponseDTO fileUploadResponseDTO = new FileUploadResponseDTO();
            fileUploadResponseDTO.setFileName(newFileName);
            fileUploadResponseDTO.setFileName(destinationPath.toString());

            fileUploadResponseDTOs.add(fileUploadResponseDTO);
        }
        return fileUploadResponseDTOs;
    }
}
