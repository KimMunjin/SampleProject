package com.hk.sampleproject.dto.response;

import com.hk.sampleproject.vo.BoardVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardListResponse {
    private final Long boardId;
    private final String title;
    private final String writerId;
    private final Long viewCount;
    private final LocalDateTime createdAt;

    public static BoardListResponse from(BoardVo board) {
        return BoardListResponse.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .writerId(board.getWriterId())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
