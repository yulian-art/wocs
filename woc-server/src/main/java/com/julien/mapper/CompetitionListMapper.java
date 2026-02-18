package com.julien.mapper;

import com.julien.entity.Competition;
import com.julien.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompetitionListMapper {

    List<Competition> listByAdmin();

    List<Competition> listByCaptain(User com_id);

}
