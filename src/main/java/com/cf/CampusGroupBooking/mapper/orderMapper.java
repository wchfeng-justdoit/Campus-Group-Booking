package com.cf.CampusGroupBooking.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface orderMapper extends BaseMapper<order> {
    @Select("select * from order_tb where order_id = #{order_id}")
    order selectOrder(String order_id);

    @Select("select * from order_tb ")
    List<order> allOrder();

    @Select("select * from order_tb where  publisher = #{id}")
    List<order> selectMyOrder(Integer id);
}
