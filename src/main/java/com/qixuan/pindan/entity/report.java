package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("report_tb")
public class report {
    @TableId("id")
    int id;
    String whistleblower_num;
    String defendant_num;
    String content;
    String image_route;
    String publish_date;
    String publish_time;

}
