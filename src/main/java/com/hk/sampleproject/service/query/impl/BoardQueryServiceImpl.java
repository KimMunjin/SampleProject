package com.hk.sampleproject.service.query.impl;

import com.hk.sampleproject.dto.response.BoardDetailResponse;
import com.hk.sampleproject.dto.response.BoardListResponse;
import com.hk.sampleproject.mapper.query.BoardQueryMapper;
import com.hk.sampleproject.service.query.BoardQueryService;
import com.hk.sampleproject.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService {

    private final BoardQueryMapper boardQueryMapper;

    @Override
    public BoardDetailResponse getBoard(Long boardId) {
        BoardVo boardVo = boardQueryMapper.selectBoard(boardId);
        if(boardVo == null) {
            throw new IllegalArgumentException("Board not found: " + boardId);
        }
        return BoardDetailResponse.from(boardVo);
    }

    @Override
    public List<BoardListResponse> getBoardList() {
        return boardQueryMapper.selectBoardList().stream()
                .map(BoardListResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existBoard(Long boardId) {
        return boardQueryMapper.existsBoard(boardId);
    }
}
