package com.julien.service;

import com.julien.dto.LoginDTO;
import com.julien.entity.Competition;
import com.julien.entity.User;

import java.util.List;

public interface PlayerService {

    User login(LoginDTO loginDTO);

    List<Competition> competitionList(String userCode, Integer role);


}
