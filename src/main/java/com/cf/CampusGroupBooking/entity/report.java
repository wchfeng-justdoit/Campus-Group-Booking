package com.cf.CampusGroupBooking.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("report_tb")
public class report {
    @TableId("id")
    int id;
    Integer whistleblower_num;
    Integer defendant_num;
    String content;
    String image_route;
//    String publish_date;
    String publish_time;
    String status;
    public report( int whistleblower_num, int defendant_num, String content, String image_route, String publish_time, String status) {
//        this.id = id;
        this.whistleblower_num=whistleblower_num;
        this.defendant_num=defendant_num;
        this.content=content;
        this.image_route=image_route;
        this.publish_time=publish_time;
        this.status=status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getWhistleblower_num() {
        return whistleblower_num;
    }

    public void setWhistleblower_num(Integer whistleblower_num) {
        this.whistleblower_num = whistleblower_num;
    }

    public Integer getDefendant_num() {
        return defendant_num;
    }

    public void setDefendant_num(Integer defendant_num) {
        this.defendant_num = defendant_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_route() {
        return image_route;
    }

    public void setImage_route(String image_route) {
        this.image_route = image_route;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
