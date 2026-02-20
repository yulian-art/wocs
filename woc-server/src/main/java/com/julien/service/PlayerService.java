package com.julien.service;

import com.julien.dto.AddMemberDTO;
import com.julien.dto.LoginDTO;
import com.julien.dto.UpdateTeamDTO;
import com.julien.entity.Competition;
import com.julien.entity.Member;
import com.julien.entity.Team;
import com.julien.entity.User;

import java.util.List;

public interface PlayerService {

    User login(LoginDTO loginDTO);

    List<Competition> competitionList(String userCode, Integer role);


    void save(Integer teamId, AddMemberDTO addMemberDTO);

    Team teamList();

    Team update(UpdateTeamDTO updateTeamDTO);
}
