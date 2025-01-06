package com.hk.sampleproject.controller;

import com.hk.sampleproject.dto.request.FileUploadRequest;
import com.hk.sampleproject.dto.response.FileUploadResponse;
import com.hk.sampleproject.service.command.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 파일 업로드 컨트롤러
 * - Pre-signed URL 발급 API 제공
 */
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileUploadController {
    private final FileUploadService fileUploadService;

    /**
     * Pre-signed URL 발급 엔드포인트
     * @param request 파일 업로드 요청 정보
     * @return Pre-signed URL 응답
     */
    @PostMapping("/pre-signed-url")
    public ResponseEntity<FileUploadResponse> getPreSignedUrl(
            @RequestBody @Valid FileUploadRequest request) {
        return ResponseEntity.ok(fileUploadService.generatePreSignedUrl(request));
    }
    /**
     * 파일 업로드 완료 확인 및 메타데이터 저장
     */
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmUpload(
            @RequestParam String s3Key,
            @RequestParam String uploadPath,
            @RequestBody @Valid FileUploadRequest request) {
        fileUploadService.saveFileMetadata(s3Key, uploadPath, request);
        return ResponseEntity.ok().build();
    }
}