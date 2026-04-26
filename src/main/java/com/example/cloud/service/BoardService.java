package com.example.cloud.service;

import com.example.cloud.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int getBoardCount() {
        return boardMapper.getBoardCount();
    }
}
