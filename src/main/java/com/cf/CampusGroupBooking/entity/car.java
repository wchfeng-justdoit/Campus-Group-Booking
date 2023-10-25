package com.cf.CampusGroupBooking.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/*
拼车类
 */
@Data
@TableName("car_tb")
public class car {
    @TableId("id")
    private int id;
    private String start_point;
    private String end_point;
    private Date start_time;
    private String order_id;
    private order order;
    public car( String start_point, String end_point, Date start_time, String order_id) {
//        this.id = id;
        this.start_point=start_point;
        this.end_point=end_point;
        this.start_time=start_time;
        this.order_id=order_id;
    }

    public car(int id, String start_point, String end_point, Date start_time, String order_id) {
        this.id = id;
        this.start_point=start_point;
        this.end_point=end_point;
        this.start_time=start_time;
        this.order_id=order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_point() {
        return start_point;
    }

    public void setStart_point(String start_point) {
        this.start_point = start_point;
    }

    public String getEnd_point() {
        return end_point;
    }

    public void setEnd_point(String end_point) {
        this.end_point = end_point;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
