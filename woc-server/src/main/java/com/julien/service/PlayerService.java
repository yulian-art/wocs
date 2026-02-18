package com.julien.service;

import com.julien.dto.LoginDTO;
import com.julien.entity.Competition;
import com.julien.entity.User;

public interface PlayerService {

    User login(LoginDTO loginDTO);

    Competition competitionList();
}
