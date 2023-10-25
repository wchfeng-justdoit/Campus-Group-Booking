package com.cf.CampusGroupBooking.controller;


import com.cf.CampusGroupBooking.entity.card;
import com.cf.CampusGroupBooking.service.administratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

@Controller
public class testController {

    @Autowired
    private administratorService administratorservice;

//    @RequestMapping("/toInsertAdm")
//    @ResponseBody
//    public int AdminInsert(){
//        administrator admin = new administrator("吴陈锋","123456","1");
//        return administratorservice.save(admin);
//    }

    @RequestMapping("/toHello")
    public String hello(){
//        Map<String, String> myMap = new HashMap<String, String>();
//        myMap.put("hello","hello qixuan");
        System.out.println("hello!");
//        return myMap;
        return "index";
    }


//     "cardTitle": '主题33',
//                    "cardDesc": '描述4',
//                    "cardPrice": '1',
//                    "cardViews": '0'
    @RequestMapping("/toIndex")
    @ResponseBody
    public List<card> toIndex(){
        card card1 = new card("李琪瑄","肥婆","猪头","hh");
        card card2 = new card("吴陈锋","帅哥","帅","shaui");

        List<card> list = new LinkedList<>();
        list.add(card1);
        list.add(card2);
        System.out.println("hello!");
//        return myMap;
        return list;
    }


    //登录
    @RequestMapping("/logintest")
    public String logintest(HttpSession session){
        //取数据
        System.out.println("logintesting...");
        //  student stu = studentService.Login(id);
        //  System.out.println(stu.getId()+"--"+password);
//        if(stu == null || !stu.getPassword().equals(password)){
//            System.out.println("登录失败!");
//            return Result.error(CodeMsg.login_error);
//        }
        session.setAttribute("wu","test");

// 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
// 遍历attrs中的
        while(attrs.hasMoreElements()){
// 获取session键值
            String name = attrs.nextElement().toString();
// 根据键值取session中的值
            Object vakue = session.getAttribute(name);
// 打印结果
            System.out.println("------" + name + ":" + vakue +"--------\n");}
        return "index";
    }
}


