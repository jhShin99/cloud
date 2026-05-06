package com.example.cloud.controller;

import com.example.cloud.domain.Board;
import com.example.cloud.domain.User;
import com.example.cloud.dto.BoardLikeRequest;
import com.example.cloud.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    public String writeForm() {
        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String write(Board board) {
        boardService.createBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") Long id, @RequestParam("page") int page, Model model, HttpSession session) {
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.removeBoard(id);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/boardUpdate";
    }

    @PostMapping("/update")
    public String update(Board board) {
        Long id = board.getId();
        boardService.modifyBoard(board);
        return "redirect:/board/read/" + id;
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
