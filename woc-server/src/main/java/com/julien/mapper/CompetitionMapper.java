package com.julien.mapper;

import com.julien.entity.Competition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompetitionMapper {

    List<Competition> listByAdmin();



    List<Competition> listByCaptain(Integer comId);

    @Insert("INSERT INTO competition(name, description, min_team_members, max_team_members, work_code, start_time, end_time, review_begin_time, review_end_time, create_time) " +
            "VALUES(#{name}, #{description}, #{minTeamMembers}, #{maxTeamMembers}, #{workCode}, #{startTime}, #{endTime}, #{reviewBeginTime}, #{reviewEndTime}, #{createTime})")
    void insert(Competition competition);
}
