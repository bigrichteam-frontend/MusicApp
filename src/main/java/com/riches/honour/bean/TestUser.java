package com.riches.honour.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="2019625user")
public class TestUser {
    @Column(name = "user_name")
    private String userName;

    private String password;

}
