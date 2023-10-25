package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface reportMapper extends BaseMapper<report> {
    @Select("select * from report_tb where id = #{id}")
    report selectReport(String order_id);
}
