package com.qixuan.pindan.service;

import com.qixuan.pindan.entity.house;
import com.qixuan.pindan.mapper.houseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private houseMapper housemapper;

    public List<house> selectHouse(){
        return housemapper.selectHouse();
    }
}
