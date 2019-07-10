package com.riches.honour.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riches.honour.bean.Song;
import com.riches.honour.mapper.SongMapper;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author whg
 * @date 2019/7/9 9:54
 **/
@Service
public class SongServer {

    @Autowired
    private SongMapper songMapper;

    public List<Song> songList(PageParams pageParams){

        return null;
    }

    /**
     * 获得总页数
     * @param size
     * @return
     */
    public int getPageNumber(int size){
        int number = songMapper.selectCount(null);
        return number%size > 0 ? number/size+1 :number/size;
    }

    /**
     *  查询歌曲信息
     * @param pageParams
     * @return
     */
    public PageResult getSongListByPage(PageParams pageParams){
        // 开始分页
        PageHelper.startPage(pageParams.getPage(), pageParams.getLimit());
        //过滤条件
        Example example=new Example(Song.class);

        example.createCriteria().andEqualTo("isDeleted",1);
        //查询
        Page<Song> pageInfo = (Page<Song>) songMapper.selectByExample(example);
        //System.out.println("song:" + pageInfo);
        // 返回结果
        PageResult<Song> songPageResult = new PageResult<>(pageInfo.getTotal(), pageInfo);
        songPageResult.setTotalPage((long) getPageNumber(pageParams.getLimit()));
        return songPageResult;

    }

    /**
     * 通过主键删除歌曲
     * @param id
     * @return
     */
    public int deleteSongById(int id){
        Song song = new Song();
        song.setId(id);
        song.setIsDeleted(0);
        int flag = songMapper.updateByPrimaryKeySelective(song);
        return flag;
    }

    //PageHeper 没有参数（初始化），还有，传参应该还有page和limit＋name，应用map接收

    /**
     * 通过名字模糊查询歌曲
     * @param name
     * @param pageParams
     * @return
     */
    public PageResult<Song> selectSongByName(String name,PageParams pageParams){
        PageHelper.startPage(pageParams.getPage(), pageParams.getLimit());
        Example example=new Example(Song.class);
        example.createCriteria().andLike("name", "%" + name + "%").andEqualTo("isDeleted",1);

        //System.out.println("name = " + name);
        Page<Song> pageInfo = (Page<Song>) songMapper.selectByExample(example);
        PageResult<Song> songPageResult = new PageResult<>(pageInfo.getTotal(), pageInfo);
        songPageResult.setCurrPage((long) pageParams.getPage());
        //System.out.println("getTotal:" + pageInfo.getTotal());
        songPageResult.setTotalPage(pageInfo.getTotal()% pageParams.getLimit()>0
                                                        ? pageInfo.getTotal()/pageParams.getLimit()+1
                                                        : pageInfo.getTotal()/pageParams.getLimit());
        //System.out.println("list = " + songPageResult);
        return songPageResult;
    }

    /**
     * 通过id搜索歌曲
     * @param id
     * @return
     */
    public Song selectSongById(Integer id) {
        Example example=new Example(Song.class);
        example.createCriteria().andEqualTo("id",id);
        List<Song> songs = songMapper.selectByExample(example);
        //System.out.println("songs = " + songs);
        return songs.get(0);
    }

    /**
     * 通过id修改歌曲信息
     * @param song
     * @return
     */
    public int updateSongById(Song song) {
        int id = song.getId();
        Song song1 = selectSongById(id);
        song1.setMusicTime(song.getMusicTime());
        song1.setName(song.getName());
        Example example=new Example(Song.class);
        example.createCriteria().andEqualTo("id",id);
        int flag = songMapper.updateByPrimaryKey(song1);
        return flag;
    }
}
