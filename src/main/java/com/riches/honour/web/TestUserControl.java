package com.riches.honour.web;

import com.riches.honour.bean.TestUser;
import com.riches.honour.server.TestUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestUserControl {
@Autowired
    TestUserServer testUserServer;


//    @PostMapping("login")
//    public ResponseEntity<String> loginTest(@RequestBody TestUser user)
//    {
//        //testServer.loginTest(user)
//        System.out.println(user);
////        return ResponseEntity.ok(testUserServer.loginTest(user));
//    }



}
