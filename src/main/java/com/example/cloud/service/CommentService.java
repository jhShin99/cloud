package com.example.cloud.service;

import com.example.cloud.domain.BoardComment;
import com.example.cloud.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public void createComment(Long boardId, String content, String userId) {
        BoardComment comment = new BoardComment();
        comment.setBoardId(boardId);
        comment.setContent(content);
        comment.setUserId(userId);

        commentMapper.insertComment(comment);
    }

    public List<BoardComment> getCommentList(Long boardId) {
        return commentMapper.selectCommentList(boardId);
    }

    public void deleteComment(Long id, String userId) {
        commentMapper.deleteComment(id, userId);
    }
}
