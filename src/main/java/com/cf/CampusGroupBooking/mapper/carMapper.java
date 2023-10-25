package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.car;
import com.cf.CampusGroupBooking.entity.order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface carMapper extends BaseMapper<car> {

    @Select("select * from car_tb ")
    @Results({
            @Result(id = true ,column = "id" , property = "id"),
            @Result(column = "start_point" , property = "start_point"),
            @Result(column = "end_point", property = "end_point"),
            @Result(column = "start_time",property = "start_time"),
            @Result(column = "order_id",property = "order_id"),
            @Result(property = "order",
                    column="order_id",
                    javaType = order.class,
                    one = @One(select = "com.cf.CampusGroupBooking.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<car> selectCar();


}
