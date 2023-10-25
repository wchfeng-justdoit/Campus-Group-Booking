package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.house;
import com.cf.CampusGroupBooking.entity.order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface houseMapper extends BaseMapper<house> {

    /*
    一对一连表查询
     */
    @Select("select * from house_tb ")
    @Results({
            @Result(id = true ,column = "id" , property = "id"),
            @Result(column = "gender_require" , property = "gender_require"),
            @Result(column = "order_id", property = "order_id"),
            @Result(column = "housing_time",property = "housing_time"),
            @Result(property = "order",
                    column="order_id",
                    javaType = order.class,
                    one = @One(select = "com.cf.CampusGroupBooking.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<house> selectHouse();



    /*
    一对一连表查询
     */
    @Select("select * from house_tb where order_id=#{order_id}")
    @Results({
            @Result(id = true ,column = "id" , property = "id"),
            @Result(column = "gender_require" , property = "gender_require"),
            @Result(column = "order_id", property = "order_id"),
            @Result(column = "housing_time",property = "housing_time"),
            @Result(property = "order",
                    column="order_id",
                    javaType = order.class,
                    one = @One(select = "com.cf.CampusGroupBooking.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<house> selectHouseById(String order_id);
}
