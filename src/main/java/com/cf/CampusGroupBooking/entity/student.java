package com.cf.CampusGroupBooking.entity;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDormitory() {
        return dormitory;
    }

    public String getRoom() {
        return room;
    }

    public String getGender() {
        return gender;
    }

    public String getWechat() {
        return wechat;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCardfront() {
        return cardfront;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCardfront(String cardfront) {
        this.cardfront = cardfront;
    }
}
