package com.qixuan.pindan.service;

import com.qixuan.pindan.entity.student;
import com.qixuan.pindan.mapper.studentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private studentMapper studentmapper;

    public student Login(int id ){
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
}
