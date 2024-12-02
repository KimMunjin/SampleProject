package com.hk.sampleproject.service.command.impl;

import com.hk.sampleproject.context.RequestContextHolder;
import com.hk.sampleproject.dto.request.CreateBoardRequest;
import com.hk.sampleproject.dto.request.UpdateBoardRequest;
import com.hk.sampleproject.mapper.command.BoardCommandMapper;
import com.hk.sampleproject.service.query.BoardQueryService;
import com.hk.sampleproject.service.command.BoardCommandService;
import com.hk.sampleproject.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommandServiceImpl implements BoardCommandService {

    private final BoardCommandMapper boardCommandMapper;
    private final BoardQueryService boardQueryService;


    @Override
    @Transactional
    public Long createBoard(CreateBoardRequest request) {
        BoardVo boardVo = BoardVo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writerId(RequestContextHolder.getContext().getUserId())
                .writerIp(RequestContextHolder.getContext().getUserIp())
                .build();

        Long boardId = boardCommandMapper.insertBoard(boardVo);
        return boardId;
    }

    @Override
    @Transactional
    public boolean updateBoard(UpdateBoardRequest request) {
        if(!boardQueryService.existBoard(request.getBoardId())) {
            throw new IllegalArgumentException("Board not found: "+ request.getBoardId());
        }

        BoardVo boardVo = BoardVo.builder()
                .boardId(request.getBoardId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        int updated = boardCommandMapper.updateBoard(boardVo);
        if(updated != 1) {
            throw new IllegalStateException("Faild to update board: " + request.getBoardId());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBoard(Long boardId) {
        if(!boardQueryService.existBoard(boardId)) {
            throw new IllegalArgumentException("Board not found: " + boardId);
        }
        int deleted = boardCommandMapper.deleteBoard(boardId);
        if(deleted != 1) {
            throw new IllegalStateException("Faild to delete board: " + boardId);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean increaseViewCount(Long boardId) {
        if(!boardQueryService.existBoard(boardId)) {
            throw new IllegalArgumentException("Board not found: " + boardId);
        }
        int updated = boardCommandMapper.increaseViewCount(boardId);
        if(updated != 1) {
            throw new IllegalStateException("Faild to increase view count: " + boardId);
        }
        return true;
    }
}
