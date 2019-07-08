package com.riches.honour.mapper;

import com.riches.honour.bean.Advert;
import com.riches.honour.bean.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SingerMapperTest {
    @Autowired
    SingerMapper singerMapper;

    @Test
    public void query(){
        Singer singer = new Singer();
        singer.setId(35);

        List<Singer> singers = singerMapper.select(singer);

        if(singers!=null){
            for (Singer singer1 : singers) {
                System.out.println("singer1 = " + singer1);
            }
        }



    }

}