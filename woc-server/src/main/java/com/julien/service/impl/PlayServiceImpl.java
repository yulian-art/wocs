package com.julien.service.impl;

import com.julien.constant.ErrorMessageConstant;
import com.julien.constant.RoleConstant;
import com.julien.context.BaseContext;
import com.julien.dto.AddMemberDTO;
import com.julien.dto.LoginDTO;
import com.julien.dto.UpdateTeamDTO;
import com.julien.entity.Competition;
import com.julien.entity.Member;
import com.julien.entity.Team;
import com.julien.entity.User;
import com.julien.exception.AccountNotFoundException;
import com.julien.exception.PasswordErrorException;
import com.julien.mapper.*;
import com.julien.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PlayServiceImpl implements PlayerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO){
        String userCode = loginDTO.getUserCode();
        String password = loginDTO.getPassword();

        User user = userMapper.getByUserCode(userCode);
        //用 userCode 去数据库查用户
        //查到后，返回一个 User 对象

        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(ErrorMessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(ErrorMessageConstant.PASSWORD_ERROR);
        }

        //返回实体对象
        return user;
    }

    @Autowired
    private CompetitionListMapper competitionListMapper;

    @Override
    public List<Competition> competitionList(String userCode, Integer role) {
        if (role.equals(RoleConstant.SUPER_ADMIN) || role.equals(RoleConstant.ACADEMY)) {
            return competitionListMapper.listByAdmin();
        } else if (role.equals(RoleConstant.CAPTAIN)) {
            Integer comId = BaseContext.getCurrentComId();
            if (comId == null) {
                throw new RuntimeException("未找到队伍ID");
            }
            return competitionListMapper.listByCaptain(comId);
        }
        return new ArrayList<>();
    }

    @Autowired
    MemberMapper memberMapper;

    public void save(Integer teamId, AddMemberDTO addMemberDTO){
        log.info("开始保存成员信息，teamId: {}, addMemberDTO: {}", teamId, addMemberDTO);
        
        // 创建新的Member对象
        Member member = new Member();
        
        // 复制属性
        BeanUtils.copyProperties(addMemberDTO, member);
        
        // 设置teamId，添加空值检查
        if (teamId != null) {
            member.setTeamId(teamId.longValue());
            log.info("设置teamId为: {}", member.getTeamId());
        } else {
            log.error("teamId为空，无法设置");
            throw new IllegalArgumentException("teamId不能为空");
        }
        
        // 记录即将插入的成员信息
        log.info("准备插入的成员信息: {}", member);
        
        // 插入数据库
        try {
            memberMapper.insert(member);
            log.info("成员信息插入成功");
        } catch (Exception e) {
            log.error("插入成员信息时发生错误: ", e);
            throw e;
        }
    }



    @Autowired
    private TeamListMapper teamListMapper;

    @Override
    public Team teamList(){

        Integer comId = BaseContext.getCurrentComId();
        Team team = teamListMapper.teamList(comId);

        return team;
    }


    @Autowired
    private UpdateTeamMapper updateTeamMapper;

    public Team update(UpdateTeamDTO updateTeamDTO){

        updateTeamMapper.insert(updateTeamDTO);

        Team team = new Team();
        BeanUtils.copyProperties(updateTeamDTO, team);


        return team;
    }

}
