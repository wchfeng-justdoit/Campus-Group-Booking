package com.cf.CampusGroupBooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class htmlController {
    //登录页面
    @RequestMapping("/tologin")
    public String toLogin(){

        return "adminLogin";
    }

    //主页
    @RequestMapping("/toadmin")
    public String toAdmin(HttpSession session){

        Object id=session.getAttribute("id");
        Object name=session.getAttribute("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);


        if(id!=null && name!=null) {
            return "adminIndex";
        }else{
            return "adminLogin";
        }
    }

    //进入身份审核界面
    @RequestMapping("/toStu")
    public String toStu(HttpSession session){
        Object id=session.getAttribute("id");
        Object name=session.getAttribute("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        if(id!=null && name!=null) {
            return "stuList";
        }else{
            return "adminLogin";
        }

    }

    //进入举报审核界面
    @RequestMapping("/toReport")
    public String toReport(HttpSession session){
        Object id=session.getAttribute("id");
        Object name=session.getAttribute("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        if(id!=null && name!=null) {
            return "report";
        }else{
            return "adminLogin";
        }

    }

    //进入用户管理界面
    @RequestMapping("/toRole")
    public String toRole(HttpSession session){
        Object id=session.getAttribute("id");
        Object name=session.getAttribute("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        if(id!=null && name!=null) {
            return "roleList";
        }else{
            return "adminLogin";
        }

    }

    //进入拼团信息管理界面
    @RequestMapping("/toOrder")
    public String toOrder(HttpSession session){
        Object id=session.getAttribute("id");
        Object name=session.getAttribute("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        if(id!=null && name!=null) {
            return "orderList";
        }else{
            return "adminLogin";
        }
    }
}

