package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/*
管理员类
 */
@Data
@TableName("administrator_tb")
public class administrator implements Serializable {

    @TableId
    int id;
    String name;
    String password;
    String power;

    public administrator(String name, String password, String power) {
        this.name = name;
        this.password = password;
        this.power = power;
    }
}
