package com.example.cloud.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
