package com.riches.honour.mapper;

import com.riches.honour.bean.Advert;
import com.riches.honour.bean.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @author 王志坚
 * @createTime 2019.07.08.21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SongMapperTest {

    @Autowired
    SongMapper songMapper;

    @Test
    public void query(){
        Song song = new Song();
        song.setAid(10);



        List<Song> songs = songMapper.select(song);

        if(songs!=null){
            for (Song song1 : songs) {
                System.out.println("song1 = " + song1);
            }
        }



    }

}