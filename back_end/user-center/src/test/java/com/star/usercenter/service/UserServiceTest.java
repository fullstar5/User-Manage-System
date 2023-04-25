package com.star.usercenter.service;
import java.util.Date;

import com.star.usercenter.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/*
*   user basic functions test
* */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("star");
        user.setUserAccount("123");
        user.setGender(0);
        user.setUserPassword("star");
        boolean success = userService.save(user);
        assertTrue(success);
        System.out.println(user.getId());
    }
}