package com.julien.mapper;

import com.julien.entity.Team;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamListMapper {
    Team teamList(Integer comId);
}
