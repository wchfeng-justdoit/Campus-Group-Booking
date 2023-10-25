package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.order;
import com.cf.CampusGroupBooking.mapper.houseMapper;
import com.cf.CampusGroupBooking.mapper.orderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private orderMapper ordermapper;
    @Autowired
    private houseMapper housemapper;
    public int publishOrder(order order){
        return ordermapper.insert(order);
    }

    //查询所有订单
    public List<order> allOrder() {
        return ordermapper.allOrder();
    }

    //根据账号查找stu
    public order findOrderById(String id ){
        return ordermapper.selectById(id);
    }

    //根据订单号删除主表信息
    public int delOrder(String id ){
        return ordermapper.deleteById(id);
    }

    //根据订单号删除从表信息(拼房）
    public int delHouse(String id ){
        return housemapper.deleteById(id);
    }

    /*
 查询拼单
  */
    public List<order> selectMyOrder(Integer id){
        return ordermapper.selectMyOrder(id);
    }
}
