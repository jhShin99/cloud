package com.example.cloud.dto;

import lombok.Data;

@Data
public class CommentRequest {

    private Long boardId;
    private String content;
}
