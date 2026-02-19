package com.julien.mapper;

import com.julien.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where User_Code = #{userCode}")
    User getByUserCode(String userCode);
}
