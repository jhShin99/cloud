package com.example.cloud.mapper;

import com.example.cloud.domain.User;

public interface UserMapper {

    void insert(User user);

    User findByUsername(String username);

}
