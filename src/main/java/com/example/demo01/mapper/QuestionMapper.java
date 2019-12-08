package com.example.demo01.mapper;

import com.example.demo01.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into questions (title, description, gmt_create, gmt_modified, creator, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from questions limit #{offest}, #{size}")
    List<Question> selectQuestion(@Param(value = "offest") Integer offest, @Param("size") Integer size);

    @Select("select count(1) from questions")
    Integer count();
}
