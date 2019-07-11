package com.riches.honour.web;

import com.github.pagehelper.Page;
import com.riches.honour.advice.MusicException;
import com.riches.honour.bean.Song;
import com.riches.honour.server.SongServer;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author whg
 * @date 2019/7/9 9:54
 **/
@RestController
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongServer songServer;

    @RequestMapping("AddSong")
    public ResponseEntity<String> addSong(@RequestBody Song song){
        int flag = 1;//songServer.addSong(song);
        if(flag > 0){
            return ResponseEntity.status(200).body("添加成功");
        }else {
            return ResponseEntity.status(500).body("添加失败");
        }
    }

    @RequestMapping("SongList")
    public ResponseEntity<PageResult> songList(@RequestParam("page") int page,@RequestParam("limit") int limit){

        PageParams  pageParams = new PageParams();
        pageParams.setLimit(limit);
        pageParams.setPage(page);
        if((Integer)page != null && (Integer)limit != null){
            PageResult pageResult = songServer.getSongListByPage(pageParams);
            //System.out.println("songs = " + pageResult);
            return ResponseEntity.status(200).body(pageResult);
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }
    }

    @RequestMapping("selectSongByAid")
    public ResponseEntity<PageResult<Song>> selectSongByAid(@RequestBody Map<String,String> map){

        if(!map.containsKey("aid") || !map.containsKey("limit") || !map.containsKey("page")){
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }

        PageResult<Song> pageResult = songServer.getSongListByPage(map);

        return ResponseEntity.status(200).body(pageResult);
    }


    @RequestMapping("delete")
    public ResponseEntity<String>  deleteSong(@RequestParam("id") int id){
//        System.out.println("id = " + id);
        if((Integer)id != null){
            int flag = songServer.deleteSongById(id);
//            System.out.println("flag = " + flag);
            if(flag > 0){
                return ResponseEntity.ok("删除成功");
            }else{
                MusicException musicException = new MusicException();
                musicException.setCode(500);
                musicException.setMsg("删除失败");
                throw musicException;
            }
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数有错误");
            throw musicException;
        }
    }

    @RequestMapping("getRankList")
    public ResponseEntity<PageResult> getRankList(){
        Map<String,String> map = new HashMap<>();
        map.put("limit","9");
        map.put("page","1");

        map.put("key","hot");
        PageResult hotResult = doRankList(map);

        map.put("key","song_long_time");
        PageResult resentResult = doRankList(map);

        map.put("key","song_long_time,hot");
        PageResult resentHotResult = doRankList(map);

        List<PageResult> list = new ArrayList<>();
        list.add(hotResult);
        list.add(resentResult);
        list.add(resentHotResult);


        PageResult pageResult = new PageResult();
        pageResult.setItems(list);

        return ResponseEntity.status(200).body(pageResult);
    }
    @RequestMapping("getRankListByType")
    public ResponseEntity<PageResult> getRankList(@RequestBody Map<String,String> map){

        return ResponseEntity.status(200).body(doRankList(map));

    }

    private PageResult doRankList(Map<String,String> map){
        if(!map.containsKey("key") || !map.containsKey("limit") || !map.containsKey("page")){
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }

        return songServer.getSongRankListByPage(map);

    }



    @RequestMapping("selectByName")
    public ResponseEntity<PageResult> selectByName(@RequestParam("name") String name,@RequestParam(value = "page",required=false,defaultValue = "1") int page,@RequestParam(value = "limit",required=false,defaultValue = "10") int limit){

        if(name != null && (Integer)page != null && (Integer)limit != null){
            PageParams pageParams = new PageParams();
            pageParams.setPage(page);
            pageParams.setLimit(limit);
            PageResult pageResult = songServer.selectSongByName(name,pageParams);
            return ResponseEntity.status(200).body(pageResult);
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }
    }

    @RequestMapping("selectById")
    public ResponseEntity<Song> selectById(@RequestParam("id") Integer id){
        if( id != null){
            Song song = songServer.selectSongById(id);
            return ResponseEntity.ok().body(song);
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }
    }

    @RequestMapping("updateSong")
    public ResponseEntity<String> updateSong(@RequestBody Song song){
        if(song != null){
//            System.out.println("song = " + song);
            int flag = songServer.updateSongById(song);
            if(flag > 0){
                return ResponseEntity.status(200).body("成功");
            }
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }else{
            MusicException musicException = new MusicException();
            musicException.setCode(400);
            musicException.setMsg("参数错误");
            throw musicException;
        }
    }
}
