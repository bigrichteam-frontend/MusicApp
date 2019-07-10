package com.riches.honour.web;

import com.riches.honour.advice.MusicException;
import com.riches.honour.bean.Song;
import com.riches.honour.util.GainDataKuwo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取数据的类
 * @author whg
 * @date 2019/7/10 15:43
 **/
@RestController
@RequestMapping("GainData")
public class GainDataControl {

    @RequestMapping("getMusicPosion")
    public ResponseEntity<String> addSong(@RequestParam("rid") int rid){
        String posion = "";
        if((Integer)rid != null){
            posion = GainDataKuwo.getUrlByRid(rid);
            return ResponseEntity.status(200).body(posion);
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }
    }
}
