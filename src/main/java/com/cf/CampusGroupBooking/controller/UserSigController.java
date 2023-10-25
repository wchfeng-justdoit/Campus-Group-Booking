package com.cf.CampusGroupBooking.controller;


import com.cf.CampusGroupBooking.service.UserSigService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserSigController {

    @Resource
    UserSigService userSigService;

    @PostMapping("/getUserSig")
    public String getUserSig( String userid){
        return userSigService.generateUserSig(userid);
    }
}
