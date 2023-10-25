package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.entity.student;

import java.util.List;

public interface adminService {

    //查询所有用户
    List<student> stuAllSelect();


    //查询所有举报信息
    List<report> repAllSelect();

    //修改
    int stuUpdate(Integer id);
    //修改
    int stuUpdate2(Integer id);

    //修改举报信息状态为1
    int repUpdate(Integer id);

    //修改举报信息状态为1
    int repUpdate2(Integer id);



}
