package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.order;
import com.cf.CampusGroupBooking.entity.shopping;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface shoppingMapper extends BaseMapper<shopping> {
    @Select("select * from shopping_tb ")
    @Results({
            @Result(id = true ,column = "id" , property = "id"),
            @Result(column = "price_cad" , property = "price_cad"),
            @Result(column = "product_type", property = "product_type"),
            @Result(column = "order_id",property = "order_id"),
            //一对一关系映射用one注解,这里查询订单信息
            //select 属性中填写执行方法的全限定类名加方法名
            //FetchType.EAGER 是立即加载
            //FetchType.LAZY 是懒加载
            @Result(property = "order",
                    column="order_id",
                    javaType = order.class,
                    one = @One(select = "com.cf.CampusGroupBooking.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<shopping> selectShop();
}
