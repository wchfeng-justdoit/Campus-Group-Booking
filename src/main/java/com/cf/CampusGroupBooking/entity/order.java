package com.cf.CampusGroupBooking.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_tb")
public class order {
    @TableId("order_id")
    String order_id;
    String type;
    String publish_time;
    int publisher;
    String image_route;
    String status;
    String content;

    public String getOrder_id() {
        return order_id;
    }

    public String getType() {
        return type;
    }


    public String getPublish_time() {
        return publish_time;
    }

    public int getPublisher() {
        return publisher;
    }

    public String getImage_route() {
        return image_route;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public void setImage_route(String image_route) {
        this.image_route = image_route;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public order(String order_id, String type,  String publish_time, int publisher, String image_route, String status, String content) {
        this.order_id = order_id;
        this.type = type;
        this.publish_time = publish_time;
        this.publisher = publisher;
        this.image_route = image_route;
        this.status = status;
        this.content = content;
    }
}
