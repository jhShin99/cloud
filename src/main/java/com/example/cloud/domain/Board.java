package com.example.cloud.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private Integer viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
