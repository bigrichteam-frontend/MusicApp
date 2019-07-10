package com.riches.honour.web;

import com.riches.honour.bean.User;
import com.riches.honour.server.UserServer;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import com.riches.honour.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author 王志坚
 * @createTime 2019.07.09.11:05
 */
@RestController
@RequestMapping("user")
public class UserControl {
    @Autowired
    UserServer userServer;

    /**
     * 添加专辑
     * @param user
     * @return
     */
    @RequestMapping("add")
    public ResponseEntity<String> addUser(@RequestBody User user){

        System.out.println("user = " + user);

        int flag = userServer.addUser(user);
        if(flag>0){
            return ResponseEntity.ok("添加成功！");
        }else if(flag == -1){
            return ResponseEntity.status(ResultStatus.RESULT_NAME_EXIST).body("用户名已存在！");
        }else {
            return ResponseEntity.status(ResultStatus.RESULT_OPERATE_ERROR).body("插入失败！");
        }
    }

    /**
     * 查询专辑
     * @param map
     * @return
     */
    @RequestMapping("select")
    public ResponseEntity<PageResult> selectUser(@RequestParam Map<String,Object> map){
        PageParams pageParams = new PageParams(map);
        PageResult pageResult = userServer.getUser(pageParams);
        long currPage = pageParams.getPage();
        pageResult.setCurrPage(currPage);
        long totalPage = userServer.getPageCount(null,pageParams.getLimit());
        pageResult.setTotalPage(totalPage);
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 查询专辑
     * @param map
     * @return
     */
    @RequestMapping("search")
    public ResponseEntity<PageResult> searchUser(@RequestParam Map<String,Object> map){

        String name = null;
        Integer sid = null;
        if(map.containsKey("key")){
            name = map.get("key").toString();
        }
        PageParams pageParams = new PageParams(map);
        PageResult pageResult = userServer.getUser(name,pageParams);
        long currPage = pageParams.getPage();
        pageResult.setCurrPage(currPage);

        long totalPage = userServer.getPageCount(name,pageParams.getLimit());
        pageResult.setTotalPage(totalPage);
        return ResponseEntity.ok(pageResult);
    }

    @RequestMapping("selectByUserId")
    public ResponseEntity<User> selectByUser(@RequestParam int id){

        User user = userServer.getUserById(id);

        System.out.println("user = " + user);
        return ResponseEntity.ok(user);
    }

    /**
     * 修改专辑
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        int flag = userServer.update(user);
        if(flag>0){
            return ResponseEntity.ok("修改成功！");
        }else if(flag == -1){
            return ResponseEntity.status(ResultStatus.RESULT_NAME_EXIST).body("用户名名已存在！");
        }else {
            return ResponseEntity.status(ResultStatus.RESULT_OPERATE_ERROR).body("修改失败！");
        }
    }

    /**
     * 删除专辑
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ResponseEntity<String> deleteUser(@RequestBody Integer[] id){
        int flag = userServer.delete(id);

        System.out.println("flag = " + flag);
        if(flag>0){
            return ResponseEntity.ok("删除成功！");
        }else {
            return ResponseEntity.status(ResultStatus.RESULT_OPERATE_ERROR).body("删除失败！");
        }
    }

}
