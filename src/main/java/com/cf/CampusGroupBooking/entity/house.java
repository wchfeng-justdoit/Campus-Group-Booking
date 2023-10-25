package com.cf.CampusGroupBooking.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("house_tb")
public class house {
    @TableId("id")
    private int id;
    private String gender_require;
    private String content;
    private Date housing_time;
    private String order_id;
    private order order;
    public house( String order_id, String gender_require, Date housing_time) {
//        this.id = id;
        this.order_id=order_id;
        this.gender_require=gender_require;
        this.housing_time=housing_time;
    }

//    public house(int id, String order_id, String gender_require, Date housing_time) {
//        this.id = id;
//        this.order_id=order_id;
//        this.gender_require=gender_require;
//        this.housing_time=housing_time;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender_require() {
        return gender_require;
    }

    public void setGender_require(String gender_require) {
        this.gender_require = gender_require;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getHousing_time() {
        return housing_time;
    }

    public void setHousing_time(Date housing_time) {
        this.housing_time = housing_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
