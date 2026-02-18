package com.julien.service.impl;

import com.julien.constant.ErrorMessageConstant;
import com.julien.constant.StatusConstant;
import com.julien.dto.LoginDTO;
import com.julien.entity.Competition;
import com.julien.entity.User;
import com.julien.exception.AccountLockedException;
import com.julien.exception.AccountNotFoundException;
import com.julien.exception.PasswordErrorException;
import com.julien.mapper.CompetitionListMapper;
import com.julien.mapper.UserMapper;
import com.julien.service.PlayerService;
import com.julien.vo.CompetitionListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
    public Competition competitionList(){


    }
}
