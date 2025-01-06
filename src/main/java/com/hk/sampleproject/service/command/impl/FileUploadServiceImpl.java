package com.hk.sampleproject.service.command.impl;

import com.hk.sampleproject.dto.request.FileUploadRequest;
import com.hk.sampleproject.dto.response.FileUploadResponse;
import com.hk.sampleproject.mapper.command.FileMetadataMapper;
import com.hk.sampleproject.service.command.FileUploadService;
import com.hk.sampleproject.vo.FileMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    private final S3Presigner s3Presigner;
    private final FileMetadataMapper fileMetadataMapper;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * Pre-signed URL 생성
     * @param request 파일 업로드 요청 정보
     * @return Pre-signed URL 및 관련 정보
     */
    public FileUploadResponse generatePreSignedUrl(FileUploadRequest request) {
        String key = generateS3Key(request.getFileName());
        String path = generateUploadPath(key);

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .contentType(request.getContentType())
                .build();
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15))
                .putObjectRequest(objectRequest)
                .build();
        PresignedPutObjectRequest presignedRequest =
                s3Presigner.presignPutObject(presignRequest);
        return FileUploadResponse.builder()
                .preSignedUrl(presignedRequest.url().toString())
                .s3Key(key)
                .uploadPath(path)
                .build();
    }

    /**
     * 고유한 S3 키 생성
     * @param fileName 원본 파일명
     * @return UUID 기반의 고유 키
     */
    private String generateS3Key(String fileName) {
        return UUID.randomUUID().toString();
    }

    /**
     * S3 업로드 경로 생성
     * - 연/월/일 기반의 디렉토리 구조
     * @param key 파일 키
     * @return 최종 업로드 경로
     */
    private String generateUploadPath(String key) {
        LocalDateTime now = LocalDateTime.now();
        return String.format("uploads/%d/%02d/%02d/%s",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(), key);
    }

    /**
     * 파일 업로드 완료 후 메타데이터 저장
     * @param s3Key 파일 식별 키
     * @param uploadPath S3 업로드 경로
     * @param request 파일 업로드 요청 정보
     */
    @Transactional
    public void saveFileMetadata(String s3Key, String uploadPath, FileUploadRequest request) {
        FileMetadata metadata = FileMetadata.builder()
                .s3Key(s3Key)
                .filePath(uploadPath)
                .originalName(request.getFileName())
                .contentType(request.getContentType())
                .fileSize(request.getFileSize())
                .build();

        fileMetadataMapper.insertFileMetadata(metadata);
        log.info("파일 메타데이터 저장 완료 - key: {}, path: {}", s3Key, uploadPath);
    }
}
