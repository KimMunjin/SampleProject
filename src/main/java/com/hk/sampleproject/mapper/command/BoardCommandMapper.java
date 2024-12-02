package com.hk.sampleproject.mapper.command;

import com.hk.sampleproject.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCommandMapper {
    // insert 후 생성된 boardId 반환
    Long insertBoard(BoardVo board);

    // 수정 성공: 1, 실패: 0 반환
    int updateBoard(BoardVo board);

    // 삭제 성공: 1, 실패: 0 반환
    int deleteBoard(Long boardId);

    // 조회수 증가 성공: 1, 실패: 0 반환
    int increaseViewCount(Long boardId);
    }

