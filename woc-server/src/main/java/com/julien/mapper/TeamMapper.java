package com.julien.mapper;

import com.julien.annotation.AutoFill;
import com.julien.dto.UpdateTeamDTO;
import com.julien.entity.Team;
import com.julien.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper {
    
    @Insert("insert into team (com_id, name, captain_id, status, member_ids, instructor_ids) " +
            "values (#{comId}, #{name}, #{captainId}, #{status}, " +
            "#{memberIds, typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler}, " +
            "#{instructorIds, typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler})")
    @AutoFill(value = OperationType.INSERT)
    void insert(UpdateTeamDTO updateTeamDTO);


    Team teamList(Integer comId);
}
