package com.example.cloud.config;

import com.example.cloud.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("/user/login");
            return false; // 컨트롤러 실행 안함
        }

        return true;
    }
}
