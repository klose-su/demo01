package com.example.demo01.controller;

import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.mapper.QuestionMapper;
import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.Question;
import com.example.demo01.model.User;
import com.example.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) return "index";
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

        List<QuestionDTO> questions = questionService.getList();
        model.addAttribute("questions", questions);

        return "index";
    }
}
