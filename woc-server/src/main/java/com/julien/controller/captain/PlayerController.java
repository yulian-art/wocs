package com.julien.controller.captain;

import com.julien.constant.JwtClaimsConstant;
import com.julien.context.BaseContext;
import com.julien.dto.*;
import com.julien.entity.Competition;
import com.julien.entity.Team;
import com.julien.entity.User;
import com.julien.properties.JwtProperties;
import com.julien.result.Result;
import com.julien.service.PlayerService;
import com.julien.utils.JwtUtil;
import com.julien.vo.LoginVO;
import com.julien.vo.UpdateTeamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.julien.result.Result.success;

@RestController
@RequestMapping
@Slf4j
@Api(tags = "队长相关接口")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private JwtProperties jwtProperties;


    @PostMapping("/login")
    @ApiOperation(value = "队长登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("登录参数：{}", loginDTO);

        User user = playerService.login(loginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_CODE, user.getUserCode());
        claims.put(JwtClaimsConstant.ROLE, user.getRole());
        claims.put(JwtClaimsConstant.COM_ID,user.getComId());
        String token = JwtUtil.createJwt(
                jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                claims);

        LoginVO loginVO = LoginVO.builder()
                .token(token)
                .build();

        return success(loginVO);
    }

    @GetMapping("/competitions")
    @ApiOperation("获取比赛列表")
    public Result<List<Competition>> competitionList(){
        log.info("获取比赛列表");

        String userCode = BaseContext.getCurrentId();
        Integer role = BaseContext.getCurrentRole();

        List<Competition> competition = playerService.competitionList(userCode,role);


        return Result.success(competition);
    }

    @PostMapping("/captain/team/{teamId}/member")
    @ApiOperation("添加成员")

    public Result save(@PathVariable Integer teamId, @RequestBody AddMemberDTO addMemberDTO){

        log.info("队伍id: {} 新增成员：{}",teamId,addMemberDTO);

        playerService.save(teamId,addMemberDTO);

        return Result.success();
    }


    @DeleteMapping("/captain/team/{teamId}/member")
    @ApiOperation("删除队员")
    public Result deleteMember(@PathVariable Integer teamId,@RequestBody DeleteMemberDTO deleteMemberDTO){
        log.info("队伍id:{} 删除成员：{}",teamId,deleteMemberDTO);

        playerService.delete(teamId,deleteMemberDTO);

        return Result.success();
    }

    @PatchMapping("/captain/team/{teamId}/member")
    @ApiOperation("修改成员信息")
    public Result updateMember(@PathVariable Integer teamId,@RequestBody UpdateMemberDTO updateMemberDTO){
        log.info("队伍id:{} 修改成员：{}",teamId,updateMemberDTO);


        playerService.updateMember(teamId,updateMemberDTO);

        return success();

    }


    @GetMapping("/captain/team")
    @ApiOperation("获取队伍信息")

    public Result<Team> teamList(){
        log.info("获取队伍信息");

        Team team = playerService.teamList();
        return Result.success(team);
    }

    @PostMapping("/captain/team")
    @ApiOperation("更新队伍信息")
    public Result<UpdateTeamVO> update(@RequestBody UpdateTeamDTO updateTeamDTO){
        log.info("更新{}", updateTeamDTO);
        
        // 调用服务方法获取Team对象
        Team updatedTeam = playerService.updateTeam(updateTeamDTO);
        
        // 将Team对象转换为UpdateTeamVO
        UpdateTeamVO updateTeamVO = UpdateTeamVO.builder()
            .id(updatedTeam.getId())
                .comId(updatedTeam.getComId())
            .name(updatedTeam.getName())
            .captainId(updatedTeam.getCaptainId())
            .captainName(updatedTeam.getCaptainName())
            .status(updatedTeam.getStatus())
                .memberIds(updatedTeam.getMemberIds())
                .instructorIds(updatedTeam.getInstructorIds())
                .createTime(updatedTeam.getCreateTime())
            .build();
    
        return Result.success(updateTeamVO);
    }

    @GetMapping("/captain/team/{teamId}/member")
    @ApiOperation("获取成员列表")
    public Result<MemberListVO>


}
