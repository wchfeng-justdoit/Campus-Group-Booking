package com.qixuan.pindan.controller;

import com.qixuan.pindan.service.UserSigService;
import org.springframework.web.bind.annotation.*;

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
