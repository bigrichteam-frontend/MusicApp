package com.riches.honour.mapper;

import com.riches.honour.bean.SongList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SongListMapperTest {
    @Autowired
    SongListMapper songListMapper;
    
    @Test
    public void add(){
        SongList songList = new SongList();
        songList.setInfo("test songList info");
        songList.setName("name ");
        songList.setStatus(1);
        songList.setUid(10);

        int flag = songListMapper.insertSelective(songList);
        System.out.println("flag = " + flag);
    }
    
    
}