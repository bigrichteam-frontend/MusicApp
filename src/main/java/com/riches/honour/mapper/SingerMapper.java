package com.riches.honour.mapper;

import com.riches.honour.bean.Singer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

/**
 * @author 王志坚
 * @createTime 2019.07.08.21:35
 */
public interface SingerMapper extends Mapper<Singer> {

//    @Select("select id,head_url,name,c_name   from singer  where is_delete=1 order by id asc limit #{s},6")
//    List<Singer> getSixSinger(Integer s);
//
//    @Select("select id,head_url,name,c_name   from singer  where is_delete=1 and id=2968")
//    Singer test();

}
