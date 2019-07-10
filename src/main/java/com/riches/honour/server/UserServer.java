package com.riches.honour.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riches.honour.bean.Album;
import com.riches.honour.bean.User;
import com.riches.honour.mapper.AlbumMapper;
import com.riches.honour.mapper.UserMapper;
import com.riches.honour.util.PageParams;
import com.riches.honour.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 王志坚
 * @createTime 2019.07.09.9:12
 */
@Service
public class UserServer {
    @Autowired
    UserMapper userMapper;

    /**
     *
     * 获取专辑数量
     *
     * @param name 为null则查询所有的count，否则则查有name的count
     * @return
     */
    public Integer getPageCount(String name,Integer pageSize){
        Integer count = 0;
        if(name != null){
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            if(name !=null){
                criteria.andLike("name","%"+name+"%");
            }

            count = userMapper.selectCountByExample(example);
        }else {
            count = userMapper.selectCount(null);
        }

        Integer pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        return pageCount;
    }

    /**
     * @param user
     * @return  flag=1:成功 flag=-1:专辑名重复 flag=0：失败
     */
    public Integer update(User user){

        if(user.getName() != null){
            Example example = new Example(user.getClass());
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name",user.getName());
            criteria.andNotEqualTo("id",user.getId());
            int count = userMapper.selectCountByExample(example);
            if(count > 0){
                return -1;
            }
        }
        Integer flag = userMapper.updateByPrimaryKeySelective(user);
        return flag;
    }
    /**
     * @param id
     * @return  flag=1:成功 flag=0：失败
     */
    public Integer delete(Integer id){
        User user = new User();
        user.setId(id);
        user.setIsDeleted(0);

        Integer flag = userMapper.updateByPrimaryKeySelective(user);

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
     * @param user
     * @return  flag=1:成功 flag=-1:专辑名重复 flag=0：失败
     */
    public Integer insert(User user){
        User user1 = new User();
        user1.setName(user.getName());

        List<User> userList = userMapper.select(user1);
        if(userList.size()>0){
           return -1;
        }

        int flag = userMapper.insertSelective(user);
        return flag;
    }

    /**
     * 根据album的非空属性值查询album
     * @return  符合条件的
     */
    public PageResult<User> getUser( PageParams pageParams){
        // 开始分页
        PageHelper.startPage(pageParams.getPage(), pageParams.getLimit());

        //查询
        Page<User> pageInfo = (Page<User>) userMapper.selectAll();
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    /**
     *
     * @param pageParams
     * @return
     */
    public PageResult<User> getUser(String name,PageParams pageParams){
        // 开始分页
        PageHelper.startPage(pageParams.getPage(), pageParams.getLimit());

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(name != null){
            criteria.andLike("name","%"+name+"%");
        }
        //查询
        Page<User> pageInfo = (Page<User>) userMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    public User getUserById(Integer id){
        Album album = new Album();
        album.setId(id);
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加专辑
     * @param user
     * @return
     */
    public Integer addUser(User user){
        //检查歌手是否存在

        Example example = new Example(user.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",user.getName());

        int count = userMapper.selectCountByExample(example);

        if(count > 0){
            return -1;
        }

        Integer flag = userMapper.insertSelective(user);
        return flag;
    }



}

