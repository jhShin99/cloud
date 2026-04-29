package com.example.cloud.mapper;

import com.example.cloud.domain.BoardComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    void insertComment(BoardComment comment);

    List<BoardComment> selectCommentList(Long boardId);

    void deleteComment(@Param("id") Long id,
                       @Param("userId") String userId);
}
