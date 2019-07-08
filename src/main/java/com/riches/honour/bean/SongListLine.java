package com.riches.honour.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 王志坚
 * @createTime 2019.07.08.20:31
 */
public class SongListLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sid;
    private Integer sLId;

    @Override
    public String toString() {
        return "SongSingerLine{" +
                "id=" + id +
                ", sid=" + sid +
                ", sLId=" + sLId +
                '}';
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
