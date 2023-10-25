package com.cf.CampusGroupBooking;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassDescription:
 * @JdkVersion: 1.8
 * @Author: wuchenfeng
 * @Created: 2023/10/25 11:22
 */
@SpringBootTest
public class MD5Test {

    @Test
    public void md5Password() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String password = "admin";
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        password=base64en.encode(md5.digest(password.getBytes("utf-8")));
        System.out.println("加密后的密码"+password);
    }
}
