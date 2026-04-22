package com.example.cloud.controller;

import com.example.cloud.domain.User;
import com.example.cloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "user/join";
    }

    @PostMapping("/login")
    public String login() {
        return null;
    }

    @PostMapping("/join")
    public String join(User user, RedirectAttributes redirectAttributes) {

        if (!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword()) || !StringUtils.hasText(user.getName())) {
            redirectAttributes.addFlashAttribute("notNull", "빈칸을 입력하세요.");
            return "redirect:/user/join";
        }

        User findByUsername = userService.findByUsername(user.getUsername());

        if (findByUsername != null) {
            redirectAttributes.addFlashAttribute("existUsername", "이미 존재하는 아이디입니다.");
            return "redirect:/user/join";
        }
        userService.join(user);
        return "redirect:/user/join";
    }
}
