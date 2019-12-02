package com.example.demo01.service;

import com.example.demo01.dto.QuestionDTO;
import com.example.demo01.mapper.QuestionMapper;
import com.example.demo01.mapper.UserMapper;
import com.example.demo01.model.Question;
import com.example.demo01.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    public List<QuestionDTO> getList() {
        List<Question> questions = questionMapper.selectQuestion();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question:questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
