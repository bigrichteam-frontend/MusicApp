package com.riches.honour.mapper;


import com.riches.honour.bean.Advert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author 王志坚
 * @createTime 2019.07.08.20:55
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdevertMapperTest {

    @Autowired
    AdevertMapper adevertMapper;

    @Test
    public void addadevert(){
        Advert advert = new Advert();
        Date now = new Date();
        advert.setCreateTime(now);
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE,1);
        advert.setEndTime(cal.getTime());
        advert.setPic("test");
        advert.setName("八个核桃");

        int flag = adevertMapper.insertSelective(advert);

    }

    @Test
    public void query(){
        Advert advert = new Advert();
        advert.setName("六个核桃");

        int count = adevertMapper.selectCount(advert);
        System.out.println("count = " + count);

        List<Advert> adverts = adevertMapper.select(advert);

        if(adverts!=null){
            for (Advert advert1 : adverts) {
                System.out.println("advert1 = " + advert1);
            }
        }



    }
}