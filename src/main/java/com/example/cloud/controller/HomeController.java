package com.example.cloud.controller;

import com.example.cloud.domain.User;
import com.example.cloud.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/max")
    public String max(HttpSession session) {
        User max = userService.login("max", "1234");
        session.setAttribute("loginUser", max);
        return "redirect:/";
    }
}
