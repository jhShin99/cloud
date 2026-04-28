package com.example.cloud.controller;


import com.example.cloud.domain.Board;
import com.example.cloud.service.BoardService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardService boardService;

    private final Faker faker = new Faker();

    @Test
    void createBoard() {
        for (int i = 1; i <= 300; i++) {
            Board board = new Board("title " + i, "content " + i, "admin");
            boardService.createBoard(board);
        }
    }
}
