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

    @RequestMapping("selectByName")
    public ResponseEntity<PageResult> selectByName(@RequestParam("name") String name,@RequestParam("page") int page,@RequestParam("limit") int limit){

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
