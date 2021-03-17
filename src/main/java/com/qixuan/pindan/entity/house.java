package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("house_tb")
public class house {
    @TableId("id")
    private int id;
    private String gender_require;
    private String content;
    private String housing_time;
    private String identify_id;

    private order order;

}
