package com.cf.CampusGroupBooking.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/*
管理员类
 */
@Data
@TableName("administrator_tb")
public class administrator  {

    @TableId
//    int id;
    String id;  //账号
    String name;  //姓名
    String password;
    String power;  //1为管理员

//    public administrator(String name, String password, String power) {
//        this.name = name;
//        this.password = password;
//        this.power = power;
//    }

    public administrator(String id, String name, String password, String power) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.power = power;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
