package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.house;
import com.cf.CampusGroupBooking.mapper.houseMapper;
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


    public List<house> selectHouseById(String order_id){
        return housemapper.selectHouseById(order_id);
    }
    /*
       发布拼车信息（插入）
        */
    public int publishHouse(house house) {
        return housemapper.insert(house);
    }
}
