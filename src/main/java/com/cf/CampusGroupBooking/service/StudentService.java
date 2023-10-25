package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.student;
import com.cf.CampusGroupBooking.mapper.studentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private studentMapper studentmapper;

    public student Login(int id ){
        return studentmapper.selectById(id);
    }
    //根据账号查找信息
    public student GetInfo(int id ){
        return studentmapper.selectById(id);
    }

    //注册
    /*
    String id,
    String name,
    String dormitory,
    String room,
    String gender,
    String wechat,
    String password,
    String status,
    String email,
    String avatar
     */
    public int assign(student stu){
        return studentmapper.insert(stu);
    }

    //根据账号查找stu
    public student findStuById(Integer id ){
        return studentmapper.selectById(id);
    }

    //解冻
    public int thawById(Integer id){

        return studentmapper.thawById(id);
    }

    //冻结
    public int freezeById(Integer id){

        return studentmapper.freezeById(id);
    }

    //查询所有用户
    public List<student> allSelect() {
        return studentmapper.allSelect();
    }
}
