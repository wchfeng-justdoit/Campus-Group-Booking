package com.qixuan.pindan.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.qixuan.pindan.entity.ResultMsg.CodeMsg;
import com.qixuan.pindan.entity.ResultMsg.Result;
import com.qixuan.pindan.entity.student;
import com.qixuan.pindan.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

@Controller
@ResponseBody
public class StudentController {
    @Autowired
    private StudentService studentService;

    //注册
    @RequestMapping( value = "/assign",method = RequestMethod.POST)

    public Result assign(
             Integer id,
             String name,
             String dormitory,
             String room,
             String gender,
             String wechat,
             String password,
             String status,
             String email,
             String avatar,
             String cardfront,
             MultipartFile Pic) throws IOException{
        student stu = new student(id, name, dormitory, room, gender, wechat, password, status, email, avatar, cardfront);
     //   System.out.println(Pic);
        //        System.out.println(studata);
//        student stu = JSONObject.toJavaObject(studata.getJSONObject("stu"), student.class);
     //   System.out.println("---"+stu);
//        stu.setAvatar(studata.getJSONObject("stu").getJSONObject("avatar").getString("data").toString());
//        System.out.println(stu);
        //已存在在好
        if(studentService.Login(stu.getId()) != null){
            return Result.error(CodeMsg.USER_EXITS);
        };

        //对文件进行处理
        //String dirPath = request.getServletContext().getRealPath("/upload/");
        //String avatarPath = "E:\\小程序聊天室\\head\\";
        String cardPath = "E:\\小程序聊天室\\identify\\";
        //File filePath = new File(avatarPath);
        File cPath = new File(cardPath);
        // 如果保存资料的地不存在，就先创建目录
//        if (!filePath.exists() ) {
//            filePath.mkdirs();
//        }
        if (!cPath.exists() ) {
            cPath.mkdirs();
        }

       // String headFilename  = Pic[0].getOriginalFilename();//头像
        String cardFilename = Pic.getOriginalFilename();//身份证
            // 使用UUID重新命名上传的壁纸名(上传人_uuid_原始文件名称)

       // String newHeadFilename = stu.getId() + "" + UUID.randomUUID() +  "_" + headFilename;//".jpg";
        String newCardFilename = stu.getId() + "" + UUID.randomUUID() + "_" + cardFilename;
        try {
            // 使用MultipartFile接口的方法完成文件上传到指定位置
            //System.out.println(avatarPath + newFilename);
         //   Pic[0].transferTo(new File(avatarPath + newHeadFilename));
            Pic.transferTo(new File(cardPath+newCardFilename));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        try{
            stu.setAvatar("head/head.jpg");
            stu.setCardfront("identify/"+newCardFilename);
            stu.setStatus("3");
            studentService.assign(stu);
        }catch(Exception e){
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(stu);
        return Result.success("注册完成！");
    }

    //上传
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile avatarPic){


        String oriFilename = "../avatar/head.jpg";
        String newFilename = oriFilename;
        if(avatarPic != null ) {
            oriFilename = avatarPic.getOriginalFilename();
            // 使用UUID重新命名上传的壁纸名(上传人_uuid_原始文件名称)
            newFilename =  UUID.randomUUID() +  "_" + oriFilename;//".jpg";

        }
        //String dirPath = request.getServletContext().getRealPath("/upload/");
        String avatarPath = "E:\\小程序聊天室\\head\\";
        File filePath = new File(avatarPath);
        // 如果保存资料的地不存在，就先创建目录
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            // 使用MultipartFile接口的方法完成文件上传到指定位置
            System.out.println(avatarPath + newFilename);
            avatarPic.transferTo(new File(avatarPath + newFilename));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success("head/"+newFilename);
    }


    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(int id , String password , Model model , HttpSession session){
        //取数据
        System.out.println("logining...");
        student stu = studentService.Login(id);
 //       System.out.println(stu.getId()+"--"+password);
        if(stu == null || !stu.getPassword().equals(password) || !stu.getStatus().equals("1")){
            System.out.println("登录失败!");
            return Result.error(CodeMsg.login_error);
        }

        String sessionId = session.getId();
        session.setAttribute("id",stu.getId());
        session.setAttribute("name",stu.getName());

        model.addAttribute("sessionID",sessionId);
        model.addAttribute(stu);

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
        return Result.success(model);
    }



    //注销登录
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Result logout(HttpSession session)
    {
        System.out.println("logouting");
        //session失效
        Object stu = session.getAttribute("name");
        session.invalidate();
        System.out.println(stu);
        return Result.success(stu);
    }






}
