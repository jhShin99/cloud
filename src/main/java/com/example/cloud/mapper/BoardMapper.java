package com.example.cloud.mapper;

import com.example.cloud.domain.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    int selectBoardCount();

    List<Board> selectBoardList(@Param("offset") int offset, @Param("size") int size);

    Board selectBoardById(Long id);

    void insertBoard(Board board);

    void deleteBoardById(Long id);

    void updateBoard(Board board);

    void updateViewCount(Long id);
}
