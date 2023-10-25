package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.administrator;
import com.cf.CampusGroupBooking.mapper.administratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class administratorService {

    @Autowired
    private administratorMapper administratormapper;

    /*
    新增
     */
//    public int save(administrator admin){
//        return administratormapper.insert(admin);
//    }

    public administrator admLogin(String id ){
        return administratormapper.selectById(id);
    }
}
