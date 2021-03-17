package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/*
拼单
 */
@Data
@TableName("shopping_tb")
public class shopping {

    @TableId("id")
    private int id;
    private String order_id;
    private String product_type;
    private int price_cad;
    private order order;

}
