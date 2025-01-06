package com.hk.sampleproject.dto.response;

import lombok.Builder;
import lombok.Getter;

// dto/FileUploadResponse.java
/**
 * 파일 업로드 응답 DTO
 * - Pre-signed URL 및 관련 정보 포함
 */
@Getter
@Builder
public class FileUploadResponse {
    private final String preSignedUrl;  // S3 업로드용 Pre-signed URL
    private final String s3Key;         // 파일 식별용 키
    private final String uploadPath;    // S3 저장 경로
}
