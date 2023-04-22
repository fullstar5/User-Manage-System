package com.star.usercenter;

import com.star.usercenter.mapper.UserMapper;
import com.star.usercenter.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserCenterApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

}
