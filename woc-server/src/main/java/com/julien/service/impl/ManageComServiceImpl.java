package com.julien.service.impl;

import com.julien.dto.BuildCompetitionDTO;
import com.julien.entity.Competition;
import com.julien.mapper.CompetitionMapper;
import com.julien.service.ManageComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ManageComServiceImpl implements ManageComService {

    @Autowired
    private CompetitionMapper competitionMapper;

    public Competition buildCompetition(BuildCompetitionDTO buildCompetitionDTO){
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
