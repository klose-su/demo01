package com.example.demo01.controller;

import com.example.demo01.dto.PaginationDTO;
import com.example.demo01.model.User;
import com.example.demo01.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest httpServletRequest,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) httpServletRequest.getSession().getAttribute("user");

        if (user == null) return "redirect:/";

        if ("questions".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName","我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.getList(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);

        return "profile";
    }
}
