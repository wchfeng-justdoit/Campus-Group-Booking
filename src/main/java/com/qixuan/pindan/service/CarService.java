package com.qixuan.pindan.service;

import com.qixuan.pindan.entity.car;
import com.qixuan.pindan.mapper.carMapper;
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
}
