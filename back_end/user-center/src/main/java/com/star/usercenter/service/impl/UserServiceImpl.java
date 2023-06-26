package com.star.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.usercenter.model.domain.User;
import com.star.usercenter.service.UserService;
import com.star.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.star.usercenter.constant.UserContent.USER_LOGIN_STATE;

/**
* @author Administrator
* @description 针对表【user(user_table)】的数据库操作Service实现
* @createDate 2023-04-26 01:18:29
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    private static  final String SALT = "star";

//    public static final String USER_LOGIN_STATE = "userLoginState";

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

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. basic check
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        if (userAccount.length() < 4){
            return null;
        }
        if (userPassword.length() < 8){
            return null;
        }
        // no special characters in user account
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        // 2. encrypt password
        String s = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", s);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            log.info("user login failed, userAccount or userPassword couldn't match");
            return null;
        }
        // 3. desensitization
        User saftyUser = desensitize(user);
        // 4. record user session
        request.getSession().setAttribute(USER_LOGIN_STATE, saftyUser);
        return saftyUser;
    }

    @Override
    public User desensitize (User originUser){
        User saftyUser = new User();
        saftyUser.setId(originUser.getId());
        saftyUser.setUsername(originUser.getUsername());
        saftyUser.setUserAccount(originUser.getUserAccount());
        saftyUser.setAvatarUrl(originUser.getAvatarUrl());
        saftyUser.setGender(originUser.getGender());
        saftyUser.setPhone(originUser.getPhone());
        saftyUser.setEmail(originUser.getEmail());
        saftyUser.setUserRole(originUser.getUserRole());
        saftyUser.setUserStatus(originUser.getUserStatus());
        saftyUser.setCreateTime(originUser.getCreateTime());
        return saftyUser;
    }
}




