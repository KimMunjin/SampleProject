package com.hk.sampleproject.service.query;

import com.hk.sampleproject.dto.response.BoardDetailResponse;
import com.hk.sampleproject.dto.response.BoardListResponse;

import java.util.List;

public interface BoardQueryService {
    BoardDetailResponse getBoard(Long boardId);
    List<BoardListResponse> getBoardList();
    boolean existBoard(Long boardId);
}
