package com.example.demo01.controller;

import com.example.demo01.mapper.QuestionMapper;
import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.Question;
import com.example.demo01.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest httpServletRequest,
                            Model model) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        User user = null;
        if (cookies == null) return "index";
        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                String token = c.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    httpServletRequest.getSession().setAttribute("user", user);
                }
            }
            break;
        }
        if (user == null) {
            model.addAttribute("error","用户没登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}
