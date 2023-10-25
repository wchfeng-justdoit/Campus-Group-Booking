package com.cf.CampusGroupBooking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.CampusGroupBooking.entity.student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface studentMapper extends BaseMapper<student> {

    //解冻
    @Update("update student_tb set status = '1' "+
            "where id = #{id}")
    int thawById(Integer id);


    //冻结
    @Update("update student_tb set status = '2' "+
            "where id = #{id}")
    int freezeById(Integer id);

    @Select("select * from student_tb ")
    List<student> allSelect();
}
