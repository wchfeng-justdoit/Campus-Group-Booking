package com.cf.CampusGroupBooking.entity;

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


    public shopping( String order_id, String product_type, Integer price_cad) {
//        this.id = id;
        this.order_id=order_id;
        this.product_type=product_type;
        this.price_cad=price_cad;
    }

    public shopping( int id,String order_id, String product_type, Integer price_cad) {
        this.id = id;
        this.order_id=order_id;
        this.product_type=product_type;
        this.price_cad=price_cad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getPrice_cad() {
        return price_cad;
    }

    public void setPrice_cad(int price_cad) {
        this.price_cad = price_cad;
    }

    public order getOrder() {
        return order;
    }

    public void setOrder(order order) {
        this.order = order;
    }
}
