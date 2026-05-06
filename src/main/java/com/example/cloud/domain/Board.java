package com.example.cloud.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    public Board(){}

    public Board(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    private Long id;

    private String title;

    private String content;

    private String userId;

    private int viewCount;

    private int likeCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int page;
}
