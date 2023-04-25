package com.star.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.usercenter.model.domain.User;
import com.star.usercenter.service.UserService;
import com.star.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user(user_table)】的数据库操作Service实现
* @createDate 2023-04-26 01:18:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




