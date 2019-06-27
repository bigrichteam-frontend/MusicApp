package com.riches.honour;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.riches.honour.mapper")
public class MusicAppBootStrart {
    public static void main(String[] args) {
        SpringApplication.run(MusicAppBootStrart.class);
    }
}
