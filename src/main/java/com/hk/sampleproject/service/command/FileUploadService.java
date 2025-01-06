package com.hk.sampleproject.service.command;

import com.hk.sampleproject.dto.request.FileUploadRequest;
import com.hk.sampleproject.dto.response.FileUploadResponse;
import jakarta.validation.Valid;

public interface FileUploadService {
    FileUploadResponse generatePreSignedUrl(FileUploadRequest request);

    void saveFileMetadata(String s3Key, String uploadPath, @Valid FileUploadRequest request);
}
