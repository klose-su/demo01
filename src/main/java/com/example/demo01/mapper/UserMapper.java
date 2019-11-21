package com.example.demo01.mapper;

import com.example.demo01.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users ( id, account_id, name, token, gmt_create, gmt_modified ) " +
            "VALUES ( ,${id}, ${accountID}, ${name}, ${token}, ${gmtCreate}, ${gmtModified})")
    public void insert(User user);
}
