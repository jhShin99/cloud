package com.example.cloud.service;

import com.example.cloud.domain.Board;
import com.example.cloud.dto.BoardUpdateRequest;
import com.example.cloud.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void modifyBoard(Long id, BoardUpdateRequest request) {

        Board board = boardMapper.selectBoardById(id);

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());

        boardMapper.updateBoard(board);
    }

    public void removeBoard(Long id) {
        boardMapper.deleteBoardById(id);
    }

    public void increaseViewCount(Long id) {
        boardMapper.updateViewCount(id);
    }

    public boolean existsBoardLike(Long boardId, String userId) {
        return boardMapper.existsBoardLike(boardId, userId) > 0;
    }

    @Transactional
    public int toggleLike(Long boardId, String userId) {
        int exists = boardMapper.existsBoardLike(boardId, userId);

        if (exists == 0) {
            boardMapper.insertBoardLike(boardId, userId);
            boardMapper.increaseLikeCount(boardId);
        } else {
            boardMapper.deleteBoardLike(boardId, userId);
            boardMapper.decreaseLikeCount(boardId);
        }

        return boardMapper.selectLikeCount(boardId);
    }
}
