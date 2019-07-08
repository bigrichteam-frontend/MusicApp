package com.riches.honour.mapper;

import com.riches.honour.bean.SongList;
import com.riches.honour.bean.SongListLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SongListLineMapperTest {

    @Autowired
    SongListLineMapper songListLineMapper;

    @Test
    public void add(){
        SongListLine songListLine = new SongListLine();
        songListLine.setSid(1);
        songListLine.setsLId(1);

        int flag = songListLineMapper.insertSelective(songListLine);

        System.out.println("flag = " + flag);
    }
}