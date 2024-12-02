package com.hk.sampleproject.dto.response;

import com.hk.sampleproject.vo.BoardVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDetailResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final Long viewCount;
    private final String writerId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static BoardDetailResponse from(BoardVo boardVo) {
        return BoardDetailResponse.builder()
                .boardId(boardVo.getBoardId())
                .title(boardVo.getTitle())
                .content(boardVo.getContent())
                .viewCount(boardVo.getViewCount())
                .writerId(boardVo.getWriterId())
                .createdAt(boardVo.getCreatedAt())
                .updatedAt(boardVo.getUpdatedAt())
                .build();
    }
}
