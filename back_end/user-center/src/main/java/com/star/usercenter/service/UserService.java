package com.star.usercenter.service;

import com.star.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @description 针对表【user(user_table)】的数据库操作Service
 * @createDate 2023-04-26 01:18:29
 */
public interface UserService extends IService<User> {

    /**
     * User Register
     *
     * @param userAccount   account
     * @param userPassword  password
     * @param checkPassword double check
     * @return user id
     */


    long userRegister(String userAccount, String userPassword, String checkPassword);


    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User desensitize (User originUser);

    int userLogout(HttpServletRequest request);
}
