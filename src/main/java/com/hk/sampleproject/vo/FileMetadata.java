package com.hk.sampleproject.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadata {
    private Long id;                // 파일 메타데이터 ID
    private String s3Key;           // S3 객체 키
    private String filePath;        // S3 저장 경로
    private String originalName;    // 원본 파일명
    private String contentType;     // 파일 타입
    private Long fileSize;          // 파일 크기
    private LocalDateTime createdAt;// 생성일시
}
