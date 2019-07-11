package com.riches.honour.server;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.riches.honour.advice.MusicException;
import com.riches.honour.bean.Album;
import com.riches.honour.bean.Singer;
import com.riches.honour.bean.Song;
import com.riches.honour.mapper.AlbumMapper;
import com.riches.honour.mapper.SingerMapper;
import com.riches.honour.mapper.SongMapper;
import com.riches.honour.util.GainDataKuwo;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import com.riches.honour.util.ResultStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.security.Key;
import java.text.BreakIterator;
import java.util.*;


/*
 * author: 吴星辰
 * time:2019/7/9 9:26
 * */
@Service("singerServer")
public class SingerServer {

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    SongMapper songMapper;

    @Autowired
    AlbumMapper albumMapper;

    public ResponseEntity<PageResult<Singer>> getPageList(Map<String,String> map) {
         Integer page= Integer.valueOf(map.get("page"));
             Integer  limit=Integer.valueOf(map.get("limit"));

             String keyWord=map.get("searchkey");

        //开始分页
        PageHelper.startPage(page,limit);
        Example example=new Example(Singer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",1);
        if (!StringUtils.isBlank(keyWord)){    //**组合错误
            (criteria.andLike("cName","%"+keyWord+"%").orLike("name","%"+keyWord+"%")).andEqualTo("isDeleted",1);

        }
        Page<Singer> pageInfo= (Page<Singer>) singerMapper.selectByExample(example);
        if (pageInfo==null){
            throw  new MusicException(ResultStatus.RESULT_EXCEPTION_ERROR,"分页结果出错");
        }

        Long total=Long.valueOf(pageInfo.getTotal());
         Long pages=Long.valueOf(pageInfo.getPages());

        return ResponseEntity.ok(new PageResult<Singer>(total,Long.valueOf(map.get("page")),pages,pageInfo));
   }

    public ResponseEntity<String> updateSinger(Map<String, String> map) {


        Singer singer=new Singer();
        singer.setId(Integer.valueOf(map.get("sid")));singer.setInfo(map.get("information"));

        if (singerMapper.updateByPrimaryKeySelective(singer)<1){
            throw new MusicException(299,"更新失败");
        }
            return ResponseEntity.ok("跟新成功");
    }


    public ResponseEntity<String> deleteSinger(Integer[] id) {
          for(Integer i:id){
            Singer singer=new Singer();singer.setId(i);
             singer.setIsDeleted(0);
              if (singerMapper.updateByPrimaryKeySelective(singer)<1){
                  throw new MusicException(500,"删除失败");
              }
          }
        return ResponseEntity.ok("删除成功");
    }


    public ResponseEntity<String> addSinger(Singer singer) {

        if( singerMapper.insertSelective(singer)<1){
            throw new MusicException(500,"添加失败");
        }

        return ResponseEntity.ok("添加成功");
    }

    public ResponseEntity<PageResult<Singer>> getQianTaiPageList(Map<String, String> map) {

        Integer page= Integer.valueOf(map.get("page"));
        String keyWord=map.get("key");
        PageHelper.startPage(page,10);

        Example example=new Example(Singer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",1);
        if (!StringUtils.isBlank(keyWord)){    //**组合错误
            (criteria.andLike("cName","%"+keyWord+"%").orLike("name","%"+keyWord+"%")).andEqualTo("isDeleted",1);

        }
        Page<Singer> pageInfo= (Page<Singer>) singerMapper.selectByExample(example);
        if (pageInfo==null){
            throw  new MusicException(ResultStatus.RESULT_EXCEPTION_ERROR,"分页结果出错");
        }

        Long total=Long.valueOf(pageInfo.getTotal());
        Long pages=Long.valueOf(pageInfo.getPages());

        return ResponseEntity.ok(new PageResult<Singer>(total,Long.valueOf(map.get("page")),pages,pageInfo));


    }

    public ResponseEntity<List<Singer>> getSixSinger() {
        int start=(int)((1+Math.random()*(150-1+1)))/6+1;

        PageHelper.startPage(start,6);
        Example example=new Example(Singer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",1);
        Page<Singer> pageInfo= (Page<Singer>) singerMapper.selectByExample(example);
           if (CollectionUtils.isEmpty(pageInfo)){
               throw new MusicException(404,"查询失败");
           }

        return ResponseEntity.ok(pageInfo);
    }

    public ResponseEntity<Singer> getOneSinger(Map<String, Integer> map) {
            Integer id=map.get("sid");

        Example example=new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sid",id).andEqualTo("isDeleted",1);
        List<Album> albums=albumMapper.selectByExample(example);
        int albumNum=albums.size();

        List<Song> songs=new ArrayList<>();
        for (Album i:albums) {
            Example example2 = new Example(Song.class);
            example2.createCriteria().andEqualTo("aid", i.getId()).andEqualTo("isDeleted", 1);
            List<Song> songs1 = songMapper.selectByExample(example2);
            songs.addAll(songs1);

        }
        int songNum=songs.size();




              Singer singer= singerMapper.selectByPrimaryKey(id);
              singer.setYuliu(String.valueOf(songNum));
              singer.setYuliu_2(String.valueOf(albumNum));
              if (singer==null){
                  throw new MusicException(404,"查询失败");
              }


        return ResponseEntity.ok(singer);
    }

    public ResponseEntity<List<Song>> getTenSong( Map<String,Integer> map) {
        Integer singerId=map.get("sid");
        Example example=new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sid",singerId).andEqualTo("isDeleted",1);
               List<Album> albums=albumMapper.selectByExample(example);
                List<Song> songs=new ArrayList<>();
                   for (Album i:albums) {
                       Example example2 = new Example(Song.class);
                      example2.createCriteria().andEqualTo("aid", i.getId()).andEqualTo("isDeleted", 1);
                       List<Song> songs1 = songMapper.selectByExample(example2);
                       songs.addAll(songs1);
                       if (songs.size() >= 10) {
                           break;
                       }
                   }
                   songs=songs.subList(0,10);
               songs.forEach(i->{
                  String songUrl= GainDataKuwo.getUrlByRid(i.getRid());
                   i.setSongUrl(songUrl);
               });

        return ResponseEntity.ok(songs);
    }
}
