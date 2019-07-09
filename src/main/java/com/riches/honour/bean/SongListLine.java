package com.riches.honour.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 歌曲歌单联系类
 * @author 王志坚
 * @createTime 2019.07.08.20:31
 *
 * @version 1.1
 * @author whg
 * 增加了一个额外字段 isDelete
 *
 */
public class SongListLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sid;
    private Integer sLId;

    private Integer isDeleted;

    @Override
    public String toString() {
        return "SongListLine{" +
                "id=" + id +
                ", sid=" + sid +
                ", sLId=" + sLId +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getsLId() {
        return sLId;
    }

    public void setsLId(Integer sLId) {
        this.sLId = sLId;
    }
}
