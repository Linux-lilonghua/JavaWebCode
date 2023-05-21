package org.example.dao;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

public interface UserMapper {
    @Select("select * from user where email=#{email} and password=#{password} ")
    //用户邮箱和用户密码正确即可登入
    User login(User user);
}
