package com.julien.mapper;

import com.julien.annotation.AutoFill;
import com.julien.entity.Member;
import com.julien.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("select * from member where team_id = #{teamId}")
    Member getByTeamId(Integer teamId);

    @Insert("INSERT INTO member(id, name, student_id, academy_id, phone, is_captain)" +
            "values" +
            "(#{id},#{name}, #{studentId}, #{academyId}, #{phone}, #{isCaptain})")

    @AutoFill(value = OperationType.INSERT)
    void insert(Member member);
}
