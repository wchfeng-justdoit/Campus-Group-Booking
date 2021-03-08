package com.qixuan.pindan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/qixuan/pindan/mapper")
@SpringBootApplication
public class PindanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PindanApplication.class, args);
    }

}
