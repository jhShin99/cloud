package com.example.cloud.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardComment {

    private Long id;
    private Long boardId;
    private String userId;
    private String content;
    private LocalDateTime createdAt;
}
