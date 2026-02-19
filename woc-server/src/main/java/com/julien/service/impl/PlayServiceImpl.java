package com.julien.service.impl;

import com.julien.constant.ErrorMessageConstant;
import com.julien.constant.RoleConstant;
import com.julien.context.BaseContext;
import com.julien.dto.LoginDTO;
import com.julien.entity.Competition;
import com.julien.entity.User;
import com.julien.exception.AccountNotFoundException;
import com.julien.exception.PasswordErrorException;
import com.julien.mapper.CompetitionListMapper;
import com.julien.mapper.UserMapper;
import com.julien.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

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


}
