package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_tb")
public class order {
    @TableId("order_id")
    int order_id;
    String type;
    String publish_date;
    String publish_time;
    int publisher;
    String image_route;
    String status;
    String content;

    public order(int order_id, String type, String publish_date, String publish_time, int publisher, String image_route, String status, String content) {
        this.order_id = order_id;
        this.type = type;
        this.publish_date = publish_date;
        this.publish_time = publish_time;
        this.publisher = publisher;
        this.image_route = image_route;
        this.status = status;
        this.content = content;
    }
}
