package com.riches.honour.web;

import com.riches.honour.advice.MusicException;
import com.riches.honour.bean.Album;
import com.riches.honour.server.AlbumServer;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import com.riches.honour.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 王志坚
 * @createTime 2019.07.09.11:05
 */
@RestController
@RequestMapping("album")
public class AlbumControl {
    @Autowired
    AlbumServer albumServer;

    /**
     * 添加专辑
     * @param album
     * @return
     */
    @RequestMapping("add")
    public ResponseEntity<String> addAlbum(@RequestBody Album album){

        System.out.println("album = " + album);

        int flag = albumServer.addAlbum(album);
        if(flag>0){
            return ResponseEntity.ok("添加成功！");
        }else if(flag == -1){
            return ResponseEntity.status(ResultStatus.RESULT_NAME_EXIST).body("专辑名已存在！");
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
    public ResponseEntity<PageResult> selectAlbum(@RequestParam Map<String,String> map){
        if(!map.containsKey("page") || !map.containsKey("limit")){
            throw  new MusicException(ResultStatus.RESULT_PARAMS_ERROR,"参数错误");
        }

        PageResult pageResult = albumServer.getAlbum(map);

        long currPage = Integer.parseInt(map.get("page"));
        pageResult.setCurrPage(currPage);
        long totalPage = albumServer.getPageCount(null,null,Integer.parseInt(map.get("limit")) );
        pageResult.setTotalPage(totalPage);
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 查询专辑
     * @param map
     * @return
     */
    @RequestMapping("search")
    public ResponseEntity<PageResult<Album>> searchAlbum(@RequestParam Map<String,String> map){
        if(!map.containsKey("page") || !map.containsKey("limit")){
            throw  new MusicException(ResultStatus.RESULT_PARAMS_ERROR,"参数错误");
        }
        String name = null;
        Integer sid = null;
        if(map.containsKey("key")){
            name = map.get("key");
        }
        if(map.containsKey("sid")){

            if(!StringUtil.isEmpty(map.get("sid"))){
                sid = Integer.parseInt(map.get("sid"));
            }

        }

        //PageParams pageParams = new PageParams(map);
        PageResult<Album> pageResult = albumServer.getAlbum(name,sid,map);
        long currPage = Integer.parseInt(map.get("page"));
        pageResult.setCurrPage(currPage);

        long totalPage = albumServer.getPageCount(name,sid,Integer.parseInt(map.get("limit")) );
        pageResult.setTotalPage(totalPage);
        return ResponseEntity.ok(pageResult);
    }
    @RequestMapping("search_1")
    public ResponseEntity<PageResult<Album>> searchAlbum_1(@RequestBody Map<String,String> map){
        return searchAlbum(map);
    }

    @RequestMapping("selectByAlbum")
    public ResponseEntity<Album> selectByAlbum(@RequestParam int id){

        Album album = albumServer.getAlbumById(id);

        System.out.println("album = " + album);
        return ResponseEntity.ok(album);
    }
    @RequestMapping("selectByAlbum_1")
    public ResponseEntity<Album> selectByAlbum_1(@RequestBody int id){
        return selectByAlbum(id);
    }

    /**
     * 修改专辑
     * @param album
     * @return
     */
    @RequestMapping("update")
    public ResponseEntity<String> updateAlbum(@RequestBody Album album){
        int flag = albumServer.update(album);
        if(flag>0){
            return ResponseEntity.ok("修改成功！");
        }else if(flag == -1){
            return ResponseEntity.status(ResultStatus.RESULT_NAME_EXIST).body("专辑名已存在！");
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
    public ResponseEntity<String> deleteAlbum(@RequestBody Integer[] id){
        int flag = albumServer.delete(id);

        System.out.println("flag = " + flag);
        if(flag>0){
            //开启线程删除歌曲
            return ResponseEntity.ok("删除成功！");
        }else {
            return ResponseEntity.status(ResultStatus.RESULT_OPERATE_ERROR).body("删除失败！");
        }
    }

}
