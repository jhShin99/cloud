package com.example.cloud.controller;

import com.example.cloud.domain.Board;
import com.example.cloud.domain.User;
import com.example.cloud.dto.BoardLikeRequest;
import com.example.cloud.dto.BoardUpdateRequest;
import com.example.cloud.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        pagination(page, model);

        return "board/boardList";
    }

    @GetMapping("/write")
    public String writeForm(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        pagination(page, model);

        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String write(Board board) {
        boardService.createBoard(board);
        Long id = board.getId();
        int page = board.getPage();
        return "redirect:/board/" + id + "?page=" + page;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, @RequestParam("page") int page, Model model, HttpSession session) {
        boardService.increaseViewCount(id);
        Board board = boardService.getBoardById(id);

        pagination(page, model);

        boolean liked = false;
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            liked = boardService.existsBoardLike(id, loginUser.getUsername());
        }

        model.addAttribute("board", board);
        model.addAttribute("liked", liked);

        return "board/boardRead";
    }

    private void pagination(int page, Model model) {
        int size = 50; // 한 페이지 게시글 수
        int offset = (page - 1) * size;

        int boardCount = boardService.getBoardCount();
        int totalPage = (int) Math.ceil((double) boardCount / size);

        int pageBlockSize = 10;
        int startPage = ((page - 1) / pageBlockSize) * pageBlockSize + 1;
        int endPage = Math.min(startPage + pageBlockSize - 1, totalPage);

        List<Board> boardList = boardService.getBoardList(offset, size);

        model.addAttribute("boardList", boardList);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boardService.removeBoard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        pagination(page, model);

        return "board/boardUpdate";
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody BoardUpdateRequest request) {
        boardService.modifyBoard(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> like(@RequestBody BoardLikeRequest request, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }

        int likeCount = boardService.toggleLike(request.getBoardId(), loginUser.getUsername());

        return Map.of("success", true, "likeCount", likeCount);
    }
}
