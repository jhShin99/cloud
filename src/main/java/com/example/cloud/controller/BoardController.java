package com.example.cloud.controller;

import com.example.cloud.domain.Board;
import com.example.cloud.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {

        int size = 10; // 한 페이지 게시글 수
        int offset = (page - 1) * size;

        int boardCount = boardService.getBoardCount();
        int totalPage = (int) Math.ceil((double) boardCount / size);

        List<Board> boardList = boardService.getBoardList(offset, size);

        model.addAttribute("boardList", boardList);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
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

    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        boardService.increaseViewCount(id);
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "/board/boardRead";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.removeBoard(id);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
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
}
