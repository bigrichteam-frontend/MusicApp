package com.riches.honour.mapper;

import com.riches.honour.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    
    @Test
    public void add(){
        User user = new User();
        user.setHeadUrl("test headUrl");
        user.setInfo("my info");
        user.setMail("2374909509@qq.com");
        user.setName("zhangsan");
        user.setType(1);
        user.setPassword("password");

        int flag = userMapper.insertSelective(user);

        System.out.println("flag = " + flag);
    }
}