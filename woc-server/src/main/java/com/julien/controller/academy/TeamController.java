package com.julien.controller.academy;


import com.julien.result.Result;
import com.julien.service.ManageTeamService;
import com.julien.vo.UpdateTeamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
@Api(tags = "管理队伍相关接口")
public class TeamController {

    @Autowired
    private ManageTeamService manageTeamService;

    @GetMapping("/academy/team")
    @ApiOperation("获取队伍信息")
    public Result<List<UpdateTeamVO>> team(@RequestParam Integer comId){
        log.info("comId {}",comId);

        List<UpdateTeamVO> updateTeamVO = manageTeamService.listTeamsByComId(comId);
        return Result.success(updateTeamVO);

    }
}
