package com.riches.honour.bean;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

/**
 * @author 吴星辰
 * @createTime 2019.07.08.20:32
 * @version 1.1
 * @author whg
 * 增加了一个额外字段 isDeleted
 */


@Table(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String weight;
    private String brth;
    private Integer artistFans;
    private String country;
    private String sex;
    private String language;
    @Column(name = "head_url")
    private String headUrl;
    private String xingZuo;
    @Column(name ="c_name" )
    private String cName;
    private String name;
    private String info;
    private String s_type;
    private String s_prefix;
    private String yuliu;
    private String yuliu_2;

    @Column(name = "is_delete")
    private Integer isDeleted;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Singer() {
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", weight='" + weight + '\'' +
                ", brth='" + brth + '\'' +
                ", artistFans=" + artistFans +
                ", country='" + country + '\'' +
                ", sex='" + sex + '\'' +
                ", language='" + language + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", xingZuo='" + xingZuo + '\'' +
                ", cName='" + cName + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", s_type='" + s_type + '\'' +
                ", s_prefix='" + s_prefix + '\'' +
                ", yuliu='" + yuliu + '\'' +
                ", yuliu_2='" + yuliu_2 + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBrth() {
        return brth;
    }

    public void setBrth(String brth) {
        this.brth = brth;
    }

    public Integer getArtistFans() {
        return artistFans;
    }

    public void setArtistFans(Integer artistFans) {
        this.artistFans = artistFans;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getXingZuo() {
        return xingZuo;
    }

    public void setXingZuo(String xingZuo) {
        this.xingZuo = xingZuo;
    }

    public String getCName() {
        return cName;
    }

    public void setCNname(String cName) {
        this.cName = cName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getS_type() {
        return s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public String getS_prefix() {
        return s_prefix;
    }

    public void setS_prefix(String s_prefix) {
        this.s_prefix = s_prefix;
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
