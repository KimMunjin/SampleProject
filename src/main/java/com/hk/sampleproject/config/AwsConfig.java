package com.hk.sampleproject.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;



// config/AwsConfig.java
/**
 * AWS 서비스 설정 클래스
 * - S3 클라이언트 구성
 * - AWS 자격증명 관리
 * - Secrets Manager 통합
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class AwsConfig {
    @Value("${aws.region}")
    private String region;


    /**
     * S3 클라이언트 빈 생성
     * - AWS Secrets Manager에서 가져온 자격증명 사용
     * - 지정된 리전으로 설정
     */
    @Bean
    public S3Client s3Client() {
        S3Client client = S3Client.builder()
                .region(Region.of(region))
                .build();

        // 자격증명이 정상적으로 로드되는지 테스트
        try {
            client.listBuckets();
            log.info("AWS 자격증명 정상 확인");
        } catch (Exception e) {
            log.error("AWS 자격증명 오류", e);
        }
        return client;
    }

    /**
     * S3 Presigner 빈 생성
     * - Pre-signed URL 생성에 사용
     * - S3 클라이언트와 동일한 자격증명 사용
     */
    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .region(Region.of(region))
                .build();
    }
}
