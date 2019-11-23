package com.example.demo01.mapper;

import com.example.demo01.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users VALUES (null,'${accountID}','${name}','${token}',${gmtCreate},${gmtModified} )")
    void insert(User user);
}
