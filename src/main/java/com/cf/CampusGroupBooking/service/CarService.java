package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.car;
import com.cf.CampusGroupBooking.mapper.carMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private carMapper carmapper;

    public List<car> selectCar(){
        return carmapper.selectCar();
    }
    /*
        发布拼车信息（插入）
         */
    public int publishCar(car car) {
        return carmapper.insert(car);
    }


}
