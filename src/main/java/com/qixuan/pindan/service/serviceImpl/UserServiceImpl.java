package com.qixuan.pindan.service.serviceImpl;

import com.qixuan.pindan.mapper.userMapper;
import com.qixuan.pindan.service.userService;
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
