package com.julien.service.impl;

import com.julien.dto.BuildCompetitionDTO;
import com.julien.entity.Competition;
import com.julien.mapper.CompetitionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ManageComServiceImpl {

    @Autowired
    private CompetitionMapper competitionMapper;

    public Competition bulidCompetition(BuildCompetitionDTO buildCompetitionDTO){
        //将DTO转化为实体类
        Competition competition = new Competition();
        BeanUtils.copyProperties(buildCompetitionDTO, competition);
        //设置创建时间
        //TODO:研究自动填充AOP
        competition.setCreateTime(LocalDateTime.now());
        competitionMapper.insert(competition);

        return competition;
    }
    
}
