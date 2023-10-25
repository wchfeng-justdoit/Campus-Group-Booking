package com.cf.CampusGroupBooking.controller;


import com.cf.CampusGroupBooking.entity.ResultMsg.CodeMsg;
import com.cf.CampusGroupBooking.entity.ResultMsg.Result;
import com.cf.CampusGroupBooking.entity.student;
import com.cf.CampusGroupBooking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
             MultipartFile Pic) throws IOException,NoSuchAlgorithmException, UnsupportedEncodingException{
        student stu = new student(id, name, dormitory, room, gender, wechat, password, status, email, avatar, cardfront);

        //已存在在好
        if(studentService.Login(stu.getId()) != null){
            return Result.error(CodeMsg.USER_EXITS);
        };

        //使用MD5加密
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        password=base64en.encode(md5.digest(password.getBytes("utf-8")));


        //对文件进行处理
        //String dirPath = request.getServletContext().getRealPath("/upload/");
        String cardPath = "E:\\JAVA\\pintuan\\identify\\";
        //File filePath = new File(avatarPath);
        File cPath = new File(cardPath);
        // 如果保存资料的地不存在，就先创建目录
//        if (!filePath.exists() ) {
//            filePath.mkdirs();
//        }
        if (!cPath.exists() ) {
            cPath.mkdirs();
        }

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
            stu.setPassword(password);
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
        String avatarPath = "E:\\JAVA\\pintuan\\head\\";
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
    public Result<Object> login(int id , String password , Model model , HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //取数据
        System.out.println("logining...");
        student stu = studentService.Login(id);
 //       System.out.println(stu.getId()+"--"+password);

        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        password=base64en.encode(md5.digest(password.getBytes("utf-8")));
        System.out.println("加密后的密码"+password);
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


    //查询好友的个人信息
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public Result<Object> getInfo(int id , Model model ){
        //取数据
//        System.out.println("logining...");
        student stu = studentService.GetInfo(id);

        model.addAttribute(stu);

        return Result.success(model);
    }



}
