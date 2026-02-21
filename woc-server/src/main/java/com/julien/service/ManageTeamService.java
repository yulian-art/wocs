package com.julien.service;

import com.julien.vo.UpdateTeamVO;

import java.util.List;

public interface ManageTeamService {

    List<UpdateTeamVO> listTeamsByComId(Integer comId);
}
