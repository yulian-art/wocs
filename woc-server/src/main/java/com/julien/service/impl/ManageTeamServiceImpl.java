package com.julien.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julien.entity.Team;
import com.julien.mapper.TeamMapper;
import com.julien.service.ManageTeamService;
import com.julien.vo.UpdateTeamVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ManageTeamServiceImpl implements ManageTeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ObjectMapper objectMapper;//用来做 JSON 和 Java 对象互相转换的工具。

    @Override
    public List<UpdateTeamVO> listTeamsByComId(Integer comId) {
        Team team = teamMapper.getOneByComId(comId);
        if (team == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(UpdateTeamVO.builder()
                .id(team.getId())
                .comId(team.getComId())
                .name(team.getName())
                .captainId(team.getCaptainId())
                .captainName(team.getCaptainName())
                .status(team.getStatus())
                .memberIds(team.getMemberIds())
                .instructorIds(team.getInstructorIds())
                .build()
        );
    }
}
