package com.cf.CampusGroupBooking;

/**
 * @ClassDescription:
 * @JdkVersion: 1.8
 * @Author: wuchenfeng
 * @Created: 2021/5/17 15:56
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/cf/CampusGroupBooking/mapper")
@SpringBootApplication
public class CampusGroupBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusGroupBookingApplication.class, args);
    }

}

