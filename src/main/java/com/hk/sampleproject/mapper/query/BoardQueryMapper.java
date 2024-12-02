package com.hk.sampleproject.mapper.query;

import com.hk.sampleproject.dto.response.BoardListResponse;
import com.hk.sampleproject.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardQueryMapper {
    BoardVo selectBoard(Long boardId);
    List<BoardVo> selectBoardList();
    boolean existsBoard(Long boardId);
}
