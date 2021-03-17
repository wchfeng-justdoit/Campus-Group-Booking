package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_tb")
public class student {

    @TableId("id")
    private int id;
    private String name;
    private String dormitory;
    private String room;
    private String gender;
    private String wechat;
    private String password;
    private String status;
    private String email;
    private String avatar;
    private String cardfront;
    public student(int id, String name, String dormitory, String room, String gender, String wechat, String password, String status, String email, String avatar, String cardfront) {
        this.id = id;
        this.name = name;
        this.dormitory = dormitory;
        this.room = room;
        this.gender = gender;
        this.wechat = wechat;
        this.password = password;
        this.status = status;
        this.email = email;
        this.avatar = avatar;
        this.cardfront = cardfront;
    }



}
