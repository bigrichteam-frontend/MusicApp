package com.riches.honour.advice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * author: 吴星辰
 * time:2019/7/9 11:32
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicException extends RuntimeException {

    private Integer code;
    private String msg;

}
