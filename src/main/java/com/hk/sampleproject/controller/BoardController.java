package com.hk.sampleproject.controller;

import com.hk.sampleproject.dto.request.CreateBoardRequest;
import com.hk.sampleproject.dto.request.UpdateBoardRequest;
import com.hk.sampleproject.dto.response.BoardDetailResponse;
import com.hk.sampleproject.dto.response.BoardListResponse;
import com.hk.sampleproject.service.command.BoardCommandService;
import com.hk.sampleproject.service.query.BoardQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardCommandService boardCommandService;
    private final BoardQueryService boardQueryService;

    /**
     * 게시물 생성 API
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Long> createBoard(
            @Valid @RequestBody CreateBoardRequest request
    ){
        Long boardId = boardCommandService.createBoard(request);
        return ResponseEntity.ok(boardId);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateBoard(
            @Valid @RequestBody UpdateBoardRequest request
    ) {
        boolean updated = boardCommandService.updateBoard(request);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/delete/{boardId}")
    public ResponseEntity<Boolean> deleteBoard(
            @PathVariable Long boardId
    ) {
        boolean deleted = boardCommandService.deleteBoard(boardId);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponse> getBoard(@PathVariable Long boardId) {
        boardCommandService.increaseViewCount(boardId);
        BoardDetailResponse boardDetailResponse = boardQueryService.getBoard(boardId);
        return ResponseEntity.ok(boardDetailResponse);
    }

    @GetMapping
    public ResponseEntity<List<BoardListResponse>> getBoardList() {
        List<BoardListResponse> responses = boardQueryService.getBoardList();
        return ResponseEntity.ok(responses);
    }
}
