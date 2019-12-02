package com.example.demo01.mapper;

import com.example.demo01.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users VALUES (null,'${accountID}','${name}','${token}',${gmtCreate},${gmtModified}, '${avatarUrl}')")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param(value = "token") String token);
}
