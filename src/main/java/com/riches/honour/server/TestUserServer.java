package com.riches.honour.server;

import com.riches.honour.bean.TestUser;
import com.riches.honour.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TestUserServer {

    @Autowired
    TestUserMapper testUserMapper;

    public String loginTest(TestUser user){
        List<TestUser> usr1=  testUserMapper.select(user);

        TestUser user2=new TestUser();
        usr1.forEach(item->{
            System.out.println(item);
        });
        String name="错误";
        if (!CollectionUtils.isEmpty(usr1)){
//            usr1.forEach(item->{
//                System.out.println(item);
//                user2.setUerName(item.getUerName());
//            });
//            name=user2.getUerName();
        }
        return name;
    }


}
