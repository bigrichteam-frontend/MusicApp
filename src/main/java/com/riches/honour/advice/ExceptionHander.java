package com.riches.honour.advice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/*
 * author: 吴星辰
 * time:2019/7/9 11:32
 * */
@ControllerAdvice
public class ExceptionHander {

    @ExceptionHandler(MusicException.class)
    public ResponseEntity<MusicException> handleException(MusicException t){

        return ResponseEntity.status(t.getCode()).body(t);
    }

}
