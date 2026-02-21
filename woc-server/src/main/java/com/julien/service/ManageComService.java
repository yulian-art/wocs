package com.julien.service;

import com.julien.dto.BuildCompetitionDTO;
import com.julien.entity.Competition;
import com.julien.vo.BuildCompetitionVO;

public interface ManageComService {
    Competition buildCompetition(BuildCompetitionDTO buildCompetitionDTO);
}
