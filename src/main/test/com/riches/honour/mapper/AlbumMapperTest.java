package com.riches.honour.mapper;

import com.riches.honour.bean.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlbumMapperTest {
    @Autowired
    AlbumMapper albumMapper;

    @Test
    public void addAlbum(){
        Album album = new Album();
        album.setSid(5);
        List<Album> albumList = albumMapper.select(album);

        if(albumList!=null){
            for (Album album1 : albumList) {
                System.out.println("album1 = " + album1);
            }
        }
    }

}