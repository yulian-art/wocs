package com.julien.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AcademyMapper {
    @Select("select name from academy where id = #{academyId}")
    String getAcademyNameById(Integer academyId);
}
