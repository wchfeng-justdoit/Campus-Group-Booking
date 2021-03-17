package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private String content;
    private String start_time;
    private String identify_id;
    private order order;

}
