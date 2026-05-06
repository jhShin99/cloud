package com.example.cloud.dto;

import lombok.Data;

@Data
public class BoardUpdateRequest {

    private String title;
    private String content;
}
