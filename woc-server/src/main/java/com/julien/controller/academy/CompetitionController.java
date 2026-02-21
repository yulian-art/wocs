package com.julien.controller.academy;


import com.julien.dto.BuildCompetitionDTO;
import com.julien.entity.Competition;
import com.julien.result.Result;
import com.julien.service.ManageComService;
import com.julien.vo.BuildCompetitionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@Api(tags = "管理com相关接口")
public class CompetitionController {

    @Autowired
    private ManageComService manageComService;

    @PostMapping("/admin/competition")
    @ApiOperation("创建比赛")
    public Result<BuildCompetitionVO> buildCompetition(@RequestBody BuildCompetitionDTO buildCompetitionDTO){

        log.info("创建比赛");
        Competition competition = manageComService.buildCompetition(buildCompetitionDTO);

        BuildCompetitionVO buildCompetitionVO = new BuildCompetitionVO();
        BeanUtils.copyProperties(competition,buildCompetitionVO);
        return Result.success(buildCompetitionVO);
    }
}
