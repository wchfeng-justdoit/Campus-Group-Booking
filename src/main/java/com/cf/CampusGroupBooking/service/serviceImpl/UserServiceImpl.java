package com.cf.CampusGroupBooking.service.serviceImpl;

import com.cf.CampusGroupBooking.mapper.userMapper;
import com.cf.CampusGroupBooking.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements userService {

    @Autowired
    userMapper usermapper;

    @Override
    public int assign(String username, String password) {
        return usermapper.assign(username , password);
    }



}
