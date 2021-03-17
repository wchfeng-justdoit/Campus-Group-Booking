package com.qixuan.pindan.service;

import com.qixuan.pindan.entity.administrator;
import com.qixuan.pindan.mapper.administratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class administratorService {

    @Autowired
    private administratorMapper administratormapper;

    /*
    新增
     */
    public int save(administrator admin){
        return administratormapper.insert(admin);
    }
}
