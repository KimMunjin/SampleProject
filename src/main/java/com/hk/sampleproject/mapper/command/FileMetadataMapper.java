package com.hk.sampleproject.mapper.command;

import com.hk.sampleproject.vo.FileMetadata;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMetadataMapper {
    /**
     * 파일 메타데이터 저장
     * @param metadata 저장할 메타데이터
     */
    void insertFileMetadata(FileMetadata metadata);
}
