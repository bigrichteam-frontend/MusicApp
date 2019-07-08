package com.riches.honour.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 王志坚
 * @createTime 2019.07.08.20:31
 */
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sid;
    private String headUrl;
    private String releaseDate;
    private String albumInfo;
    private String lang;
    private String albumName;
    private String yuliu;
    private String yuliu_2;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", sid=" + sid +
                ", headUrl='" + headUrl + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", albumInfo='" + albumInfo + '\'' +
                ", lang='" + lang + '\'' +
                ", albumName='" + albumName + '\'' +
                ", yuliu='" + yuliu + '\'' +
                ", yuliu_2='" + yuliu_2 + '\'' +
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbuminfo() {
        return albumInfo;
    }

    public void setAlbuminfo(String albuminfo) {
        this.albumInfo = albumInfo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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
