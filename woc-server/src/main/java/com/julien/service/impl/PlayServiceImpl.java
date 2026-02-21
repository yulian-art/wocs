package com.julien.service.impl;

import com.julien.constant.ErrorMessageConstant;
import com.julien.constant.RoleConstant;
import com.julien.context.BaseContext;
import com.julien.dto.*;
import com.julien.entity.Competition;
import com.julien.entity.Member;
import com.julien.entity.Team;
import com.julien.entity.User;
import com.julien.exception.AccountNotFoundException;
import com.julien.exception.PasswordErrorException;
import com.julien.mapper.*;
import com.julien.service.PlayerService;
import com.julien.vo.MemberListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private CompetitionMapper competitionMapper;

    @Override
    public List<Competition> competitionList(String userCode, Integer role) {
        if (role.equals(RoleConstant.SUPER_ADMIN) || role.equals(RoleConstant.ACADEMY)) {
            return competitionMapper.listByAdmin();
        } else if (role.equals(RoleConstant.CAPTAIN)) {
            Integer comId = BaseContext.getCurrentComId();
            if (comId == null) {
                throw new RuntimeException("未找到队伍ID");
            }
            return competitionMapper.listByCaptain(comId);
        }
        return new ArrayList<>();
    }

    @Autowired
    private MemberMapper memberMapper;

    public void save(Integer teamId, AddMemberDTO addMemberDTO){
        log.info("开始保存成员信息，teamId: {}, addMemberDTO: {}", teamId, addMemberDTO);
        
        // 创建新的Member对象
        Member member = new Member();
        
        // 复制属性
        BeanUtils.copyProperties(addMemberDTO, member);
        
        // 设置teamId，添加空值检查
        if (teamId != null) {
            member.setTeamId(teamId);
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


    public void delete(Integer teamId, DeleteMemberDTO deleteMemberDTO){
        //List<Member> team = memberMapper.getByTeamId(teamId);


        //if(team == null){
        //    throw new IllegalArgumentException("队伍不存在");
        //}


        Integer id = deleteMemberDTO.getId();

        memberMapper.removeMemberFromTeam(teamId,id);
    }

    public void updateMember(Integer teamId, UpdateMemberDTO updateMemberDTO){

        Member member = new Member();
        BeanUtils.copyProperties(updateMemberDTO,member);

        memberMapper.updateById(member);
    }



    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Team teamList(){
        Integer comId = BaseContext.getCurrentComId();
        Team team = teamMapper.teamList(comId);
        return team;
    }




    public Team updateTeam(UpdateTeamDTO updateTeamDTO){

        teamMapper.insert(updateTeamDTO);

        Team team = new Team();
        BeanUtils.copyProperties(updateTeamDTO, team);


        return team;
    }

    @Autowired
    private AcademyMapper academyMapper;

    public List<MemberListVO> memberList(Integer teamId){
        List<Member> members = memberMapper.getByTeamId(teamId);

        return members.stream()
                .map(member -> {  // 遍历 members 列表
                    MemberListVO vo = new MemberListVO();  // 创建一个新的 MemberVO 对象

                    vo.setStudentId(member.getStudentId());
                    vo.setName(member.getName());

                    String academy = academyMapper.getAcademyNameById(member.getAcademyId());
                    vo.setAcademy(academy);
                    return vo;
                })
                .collect(Collectors.toList());  // 最后将所有的 vo 对象收集到一个 List 中

    }

}
