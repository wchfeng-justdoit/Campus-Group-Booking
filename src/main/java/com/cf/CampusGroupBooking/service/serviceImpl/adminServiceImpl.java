package com.cf.CampusGroupBooking.service.serviceImpl;

import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.entity.student;
import com.cf.CampusGroupBooking.mapper.adminMapper;
import com.cf.CampusGroupBooking.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminServiceImpl implements adminService {
    @Autowired
    adminMapper adminmapper;

    @Override
    public List<student> stuAllSelect() {
        return adminmapper.stuAllSelect("3");
    }

    @Override
    public int stuUpdate(Integer id) {
        return adminmapper.stuUpdate(id);
    }

    @Override
    public int stuUpdate2(Integer id) {
        return adminmapper.stuUpdate2(id);
    }


    @Override
    public List<report> repAllSelect() {
        return adminmapper.repAllSelect("0");
    }

    @Override
    public int repUpdate(Integer id) {
        return adminmapper.repUpdate(id);
    }

    @Override
    public int repUpdate2(Integer id) {
        return adminmapper.repUpdate2(id);
    }
}
