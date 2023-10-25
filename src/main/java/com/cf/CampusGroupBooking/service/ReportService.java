package com.cf.CampusGroupBooking.service;

import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.mapper.reportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReportService {
    @Autowired
    private reportMapper reportmapper;

    public int publishReprt(report report){
        return reportmapper.insert(report);
    }


}