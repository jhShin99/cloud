package com.example.cloud.controller;

import com.example.cloud.domain.Board;
import com.example.cloud.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        int boardCount = boardService.getBoardCount();
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("boardList", boardList);
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
