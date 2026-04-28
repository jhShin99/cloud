package com.example.cloud.service;

import com.example.cloud.domain.Board;
import com.example.cloud.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int getBoardCount() {
        return boardMapper.selectBoardCount();
    }

    public List<Board> getBoardList(int offset, int size) {
        return boardMapper.selectBoardList(offset, size);
    }

    public Board getBoardById(Long id) {
        return boardMapper.selectBoardById(id);
    }

    public void createBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    public void modifyBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    public void removeBoard(Long id) {
        boardMapper.deleteBoardById(id);
    }

    public void increaseViewCount(Long id) {
        boardMapper.updateViewCount(id);
    }
}
