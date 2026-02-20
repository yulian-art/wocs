package com.julien.mapper;

import com.julien.annotation.AutoFill;
import com.julien.dto.UpdateMemberDTO;
import com.julien.entity.Member;
import com.julien.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("select * from member where team_id = #{teamId}")
    List<Member> getByTeamId(Integer teamId);

    @Insert("INSERT INTO member( name, team_id, student_id, academy_id, phone, is_captain)" +
            "values" +
            "(#{name},#{teamId}, #{studentId}, #{academyId}, #{phone}, #{isCaptain})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Member member);

    @Delete("delete from member where id = #{id} and team_id = #{teamId}")
    void removeMemberFromTeam(Integer teamId, Integer id);

    @Update("UPDATE member SET name = #{name}, student_id = #{studentId}, team_id = #{teamId}, " +
            "academy_id = #{academyId}, phone = #{phone}, is_captain = #{isCaptain} WHERE id = #{id}")
    void updateById(Member member);

}
