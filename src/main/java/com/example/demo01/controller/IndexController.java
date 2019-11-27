package com.example.demo01.controller;

import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        System.out.println(cookies.length);
        if (cookies.length == 0) return "index";
        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                String token = c.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    httpServletRequest.getSession().setAttribute("user", user);
                }
            }
            break;
        }

        return "index";
    }
}
