package com.qixuan.pindan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixuan.pindan.entity.house;
import com.qixuan.pindan.entity.order;
import com.qixuan.pindan.entity.shopping;
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
            @Result(column = "identify_id", property = "identify_id"),
            @Result(column = "housing_time",property = "housing_time"),
            @Result(property = "order",
                    column="identify_id",
                    javaType = order.class,
                    one = @One(select = "com.qixuan.pindan.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<house> selectHouse();


}
