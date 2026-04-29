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

    int existsBoardLike(@Param("boardId") Long boardId, @Param("userId") String userId);

    void insertBoardLike(@Param("boardId") Long boardId, @Param("userId") String userId);

    void deleteBoardLike(@Param("boardId") Long boardId, @Param("userId") String userId);

    void increaseLikeCount(@Param("boardId") Long boardId);

    void decreaseLikeCount(@Param("boardId") Long boardId);

    int selectLikeCount(@Param("boardId") Long boardId);
}
