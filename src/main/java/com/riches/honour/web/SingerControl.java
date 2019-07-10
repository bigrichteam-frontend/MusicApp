package com.riches.honour.web;




/*
* author: 吴星辰
* time:2019/7/9 9:26
* */


import com.riches.honour.bean.Singer;
import com.riches.honour.server.SingerServer;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("singer")
public class SingerControl {

    @Autowired
    SingerServer singerServer;

    @RequestMapping("page")
 public ResponseEntity<PageResult<Singer>> getPageList(@RequestParam Map<String,String> map) {

     return singerServer.getPageList( map);
 }

    @RequestMapping("addSinger")
    public  ResponseEntity<String> addSinger(@RequestBody Singer singer){


        return singerServer.addSinger(singer);
    }


    @RequestMapping("delete")
    public ResponseEntity<String> deleteSinger(@RequestBody Integer[] id) {




        return singerServer.deleteSinger(id);
    }
    @RequestMapping("update")
    public ResponseEntity<String> updateSinger(@RequestBody Map<String,String> map) {

        return singerServer.updateSinger(map);
    }




}
