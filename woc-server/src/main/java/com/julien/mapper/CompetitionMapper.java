package com.julien.mapper;

import com.julien.entity.Competition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompetitionMapper {

    List<Competition> listByAdmin();



    List<Competition> listByCaptain(Integer comId);
}
