package com.hk.sampleproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

// dto/FileUploadRequest.java
/**
 * 파일 업로드 요청 DTO
 * - 파일 업로드를 위한 Pre-signed URL 생성 요청 정보
 * - 입력값 검증 포함
 */
@Getter
@Builder
public class FileUploadRequest {
    @NotBlank(message = "FILE_NAME_REQUIRED")
    private final String fileName;      // 원본 파일명

    @NotBlank(message = "CONTENT_TYPE_REQUIRED")
    private final String contentType;   // 파일 타입

    @Positive(message = "FILE_SIZE_INVALID")
    private final Long fileSize;        // 파일 크기
}
