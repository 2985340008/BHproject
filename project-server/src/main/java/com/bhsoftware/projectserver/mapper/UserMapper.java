package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Mapper
public interface UserMapper {
    @Insert("insert into user(username,password,phone,email) values(#{username},#{password},#{phone},#{email})")
    void register(User user);
}