package com.hk.sampleproject.service.command;

import com.hk.sampleproject.dto.request.CreateBoardRequest;
import com.hk.sampleproject.dto.request.UpdateBoardRequest;


public interface BoardCommandService {
    Long createBoard(CreateBoardRequest request);
    boolean updateBoard(UpdateBoardRequest request);
    boolean deleteBoard(Long boardId);
    boolean increaseViewCount(Long boardId);

}
