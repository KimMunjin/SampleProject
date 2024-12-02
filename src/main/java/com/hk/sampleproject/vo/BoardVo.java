package com.hk.sampleproject.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardVo {
    private final Long boardId;
    private final String title;
    private final String content;
    private final Long viewCount;
    private final String writerId;
    private final String writerIp;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
}
