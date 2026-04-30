package com.example.cloud.controller;

import com.example.cloud.domain.BoardComment;
import com.example.cloud.domain.User;
import com.example.cloud.dto.CommentDeleteRequest;
import com.example.cloud.dto.CommentRequest;
import com.example.cloud.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/write")
    public Map<String, Object> write(@RequestBody CommentRequest request, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }

        commentService.createComment(request.getBoardId(), request.getContent(), loginUser.getUsername());

        return Map.of("success", true);
    }

    @GetMapping("/list")
    public List<BoardComment> list(@RequestParam("boardId") Long boardId) {
        return commentService.getCommentList(boardId);
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody CommentDeleteRequest request,
                                      HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }

        commentService.deleteComment(request.getId(), loginUser.getUsername());

        return Map.of("success", true);
    }
}
