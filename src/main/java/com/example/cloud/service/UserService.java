package com.example.cloud.service;

import com.example.cloud.domain.User;
import com.example.cloud.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User login(String username, String password) {
        User findUser = userMapper.findByUsername(username);
        if (findUser == null)
            return null;

        if (!passwordEncoder.matches(password, findUser.getPassword()))
            return null;
        return findUser;
    }
}
