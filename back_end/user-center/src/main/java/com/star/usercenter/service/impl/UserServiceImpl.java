package com.star.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.usercenter.model.domain.User;
import com.star.usercenter.service.UserService;
import com.star.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Administrator
* @description 针对表【user(user_table)】的数据库操作Service实现
* @createDate 2023-04-26 01:18:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. basic check
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return -1;
        }
        if (userAccount.length() < 4){
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }
        // no special characters in user account
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        // password and double check password
        if (!userPassword.equals(checkPassword)){
            return -1;
        }
        // no repeat user account
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            return -1;
        }
        // 2. encrypt password
        final String SALT = "star";
        String s = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. save user into database
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(s);
        boolean isSuccess = this.save(user);
        if (!isSuccess){
            return -1;
        }
        return user.getId();
    }
}




