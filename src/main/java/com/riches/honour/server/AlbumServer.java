package com.riches.honour.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riches.honour.bean.Album;
import com.riches.honour.bean.Song;
import com.riches.honour.mapper.AlbumMapper;
import com.riches.honour.mapper.SongMapper;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author 王志坚
 * @createTime 2019.07.09.9:12
 */
@Service
public class AlbumServer {
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    SongMapper songMapper;

    /**
     *
     * 获取专辑数量
     *
     * @param name 为null则查询所有的count，否则则查有name的count
     * @return
     */
    public Integer getPageCount(String name,Integer sid,Integer pageSize){
        Integer count = 0;
        if(name != null || sid != null){
            Example example = new Example(Album.class);
            Example.Criteria criteria = example.createCriteria();



            if(name !=null){
                criteria.andLike("albumName","%"+name+"%");
            }
            if(sid != null){
                criteria.andEqualTo("sid",sid);
            }
            criteria.andEqualTo("isDeleted",1);

            count = albumMapper.selectCountByExample(example);
        }else {
            count = albumMapper.selectCount(null);
        }

        Integer pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        return pageCount;
    }

    /**
     * @param album
     * @return  flag=1:成功 flag=-1:专辑名重复 flag=0：失败
     */
    public Integer update(Album album){

        if(album.getAlbumName() != null){
            Example example = new Example(album.getClass());
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("albumName",album.getAlbumName());
            criteria.andNotEqualTo("id",album.getId());
            int count = albumMapper.selectCountByExample(example);
            if(count > 0){
                return -1;
            }
        }
        Integer flag = albumMapper.updateByPrimaryKeySelective(album);
        return flag;
    }
    /**
     * @param id
     * @return  flag=1:成功 flag=0：失败
     */
    public Integer delete(Integer id){
        Album album = new Album();
        album.setId(id);
        album.setIsDelete(0);

        Integer flag = albumMapper.updateByPrimaryKeySelective(album);

        if(flag>0){
            Song song = new Song();
            song.setAid(id);
            List<Song> songList = songMapper.select(song);

            for(Song song1 : songList){
                song1.setIsDeleted(0);
                songMapper.updateByPrimaryKeySelective(song1);
            }

        }
        return flag;
    }

    /**
     * @param id
     * @return  flag=1:成功 flag=0：失败
     */
    public Integer delete(Integer[] id){
        int flag = 0;
        for(int i=0;i<id.length;i++){
            flag += delete(id[i]);
        }

        return flag;
    }

    /**
     * @param album
     * @return  flag=1:成功 flag=-1:专辑名重复 flag=0：失败
     */
    public Integer insert(Album album){
        Album album1 = new Album();
        album1.setAlbumName(album.getAlbumName());

        List<Album> albumList = albumMapper.select(album1);
        if(albumList.size()>0){
           return -1;
        }

        int flag = albumMapper.insertSelective(album);
        return flag;
    }

    /**
     * 根据album的非空属性值查询album
     * @return  符合条件的
     */
    public PageResult<Album> getAlbum(Map<String,String> map){
        // 开始分页
        PageHelper.startPage(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("limit")));

        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",1);
        //查询
        Page<Album> pageInfo = (Page<Album>) albumMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    /**
     *
     * @param
     * @return
     */
    public PageResult<Album> getAlbum(String name,Integer sid,Map<String,String> map){

        // 开始分页
        PageHelper.startPage(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("limit")));
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if(name != null){
            criteria.andLike("albumName","%"+name+"%");
        }
        if(sid != null){
            criteria.andEqualTo("sid",sid);
        }
        criteria.andEqualTo("isDeleted",1);
        //查询
        Page<Album> pageInfo = (Page<Album>) albumMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    public Album getAlbumById(Integer id){
        Album album = new Album();
        album.setId(id);
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加专辑
     * @param album
     * @return
     */
    public Integer addAlbum(Album album){
        //检查歌手是否存在

        Example example = new Example(album.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("albumName",album.getAlbumName());

        int count = albumMapper.selectCountByExample(example);

        if(count > 0){
            return -1;
        }

        Integer flag = albumMapper.insertSelective(album);
        return flag;
    }



}

