package com.riches.honour.bean;

import javax.persistence.*;

/**
 * @author 王志坚
 * @createTime 2019.07.08.20:31
 *
 * @version 1.1
 * @author whg
 * 增加了一个额外字段 isDelete
 */
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer aid;
    private Integer rid;
    private Integer hot;
    private String musicHeadUrl;
    private String musicTime;
    private String name;
    private String songLongTime;
    private String yuliu;
    private String yuliu_2;

    @Transient
    private  String songUrl;

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    @Column(name = "is_delete")
    private Integer isDeleted;

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", aid=" + aid +
                ", rid=" + rid +
                ", hot=" + hot +
                ", musicHeadUrl='" + musicHeadUrl + '\'' +
                ", musicTime='" + musicTime + '\'' +
                ", name='" + name + '\'' +
                ", songLongTime='" + songLongTime + '\'' +
                ", yuliu='" + yuliu + '\'' +
                ", yuliu_2='" + yuliu_2 + '\'' +
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getMusicHeadUrl() {
        return musicHeadUrl;
    }

    public void setMusicHeadUrl(String musicHeadUrl) {
        this.musicHeadUrl = musicHeadUrl;
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongLongTime() {
        return songLongTime;
    }

    public void setSongLongTime(String songLongTime) {
        this.songLongTime = songLongTime;
    }

    public String getYuliu() {
        return yuliu;
    }

    public void setYuliu(String yuliu) {
        this.yuliu = yuliu;
    }

    public String getYuliu_2() {
        return yuliu_2;
    }

    public void setYuliu_2(String yuliu_2) {
        this.yuliu_2 = yuliu_2;
    }
}
