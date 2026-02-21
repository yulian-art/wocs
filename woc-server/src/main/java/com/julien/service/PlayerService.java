package com.julien.service;

import com.julien.dto.*;
import com.julien.entity.Competition;
import com.julien.entity.Team;
import com.julien.entity.User;
import com.julien.vo.MemberListVO;

import java.util.List;

public interface PlayerService {

    User login(LoginDTO loginDTO);

    List<Competition> competitionList(String userCode, Integer role);


    void save(Integer teamId, AddMemberDTO addMemberDTO);

    Team teamList();

    Team updateTeam(UpdateTeamDTO updateTeamDTO);

    void delete(Integer teamId, DeleteMemberDTO deleteMemberDTO);

    void updateMember(Integer teamId, UpdateMemberDTO updateMemberDTO);

    List<MemberListVO> memberList(Integer teamId);
}
