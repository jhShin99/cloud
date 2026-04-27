package com.example.cloud.mapper;

import com.example.cloud.domain.Board;

import java.util.List;

public interface BoardMapper {

    int getBoardCount();

    List<Board> selectBoardList();

    Board selectBoardById(Long id);

    void insertBoard(Board board);

    void deleteBoardById(Long id);

    void updateBoard(Board board);

    void updateViewCount(Board board);
}
