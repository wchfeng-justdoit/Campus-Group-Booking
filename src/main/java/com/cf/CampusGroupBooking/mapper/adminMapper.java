package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.entity.student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface adminMapper  extends BaseMapper<student> {

    //查所有
//    @Select("select * from student_tb where status = #{status}")
//    @ResultMap(value = "stuMap")
//    List<student> stuAllSelect(String status);


    @Select("select * from student_tb where status = #{status}")
    @Results(id = "stuMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "dormitory", property = "dormitory"),
            @Result(column = "room", property = "room"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "wechat", property = "wechat"),
            @Result(column = "password", property = "password"),
            @Result(column = "status", property = "status"),
            @Result(column = "email", property = "email"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "cardfront", property = "cardfront")
    })
    List<student> stuAllSelect(String status);


    //修改状态为1
    @Update("update student_tb set status = '1' "+
            "where id = #{id}")
    int stuUpdate(Integer id);

    //修改状态为2,不通过
    @Update("update student_tb set status = '2' "+
            "where id = #{id}")
    int stuUpdate2(Integer id);


    //获取所有举报信息
    @Select("select * from report_tb where status = #{status}")
    @Results(id = "repMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "whistleblower_num", property = "whistleblower_num"),
            @Result(column = "defendant_num", property = "defendant_num"),
            @Result(column = "content", property = "content"),
            @Result(column = "image_route", property = "image_route"),
            @Result(column = "publish_time", property = "publish_time"),
            @Result(column = "status", property = "status")
    })
    List<report> repAllSelect(String status);


    //修改举报信息状态为1
    @Update("update report_tb set status = '1' "+
            "where id = #{id}")
    int repUpdate(Integer id);


    //修改举报信息状态为2
    @Update("update report_tb set status = '2' "+
            "where id = #{id}")
    int repUpdate2(Integer id);
}
