package com.cf.CampusGroupBooking.controller;


import com.cf.CampusGroupBooking.entity.ResultMsg.CodeMsg;
import com.cf.CampusGroupBooking.entity.ResultMsg.Result;
import com.cf.CampusGroupBooking.entity.administrator;
import com.cf.CampusGroupBooking.entity.order;
import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.entity.student;
import com.cf.CampusGroupBooking.service.OrderService;
import com.cf.CampusGroupBooking.service.StudentService;
import com.cf.CampusGroupBooking.service.adminService;
import com.cf.CampusGroupBooking.service.administratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;

@Controller

public class adminController {
    @Autowired
    private adminService adminservice;
    @Autowired
    private administratorService administratorservice;
    @Autowired
    private StudentService studentservice;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OrderService orderservice;

    //管理员登录
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)

    public String adminlogin(String id, String password, Model model, HttpSession session) throws  NoSuchAlgorithmException, UnsupportedEncodingException {
        //取数据
        System.out.println("管理员登录...id=" + id);
//        System.out.println("密码...未加密password=" + password);
        administrator adm = administratorservice.admLogin(id);
        //       System.out.println(stu.getId()+"--"+password);
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        password=base64en.encode(md5.digest(password.getBytes("utf-8")));
        System.out.println(password);
        if (adm == null || !adm.getPassword().equals(password) || !adm.getPower().equals("1")) {
            System.out.println("登录失败!");
            model.addAttribute("msg","登录失败，请重新输入！！");
            return "adminLogin";
        }
//        String sessionId = session.getId();
        session.setAttribute("id", adm.getId());
        session.setAttribute("name", adm.getName());
//        model.addAttribute(adm);
        //test
// 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
// 遍历attrs中的
        while (attrs.hasMoreElements()) {
// 获取session键值
            String name = attrs.nextElement().toString();
// 根据键值取session中的值
            Object value = session.getAttribute(name);
// 打印结果
            System.out.println("------" + name + ":" + value + "--------\n");
        }
        model.addAttribute("id",id);
        model.addAttribute("name",adm.getName());
        return "adminIndex";   //跳转到首页
    }


    //查询所有待审核用户
    @RequestMapping("/getAllStu")
    @ResponseBody
    public List<student> getAllStu() {
        return adminservice.stuAllSelect();
    }


    //修改用户状态为1，通过
    @RequestMapping(value = "/updateStatusSuccess")
    @ResponseBody
    public Result updateStatusSuccess(Integer id) {
        System.out.println("输出状态id....." + id);
        try {
            System.out.println("try");
            adminservice.stuUpdate(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success(0);
    }


    //修改用户状态为2，不通过
    @RequestMapping(value = "/updateStatusFail")
    @ResponseBody
    public Result updateStatusFail(Integer id) {
        System.out.println("输出状态id....." + id);
        try {
            System.out.println("try");
            adminservice.stuUpdate2(id);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("catch");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success(0);
    }


    //查询所有举报信息
    @RequestMapping("/getAllReport")
    @ResponseBody
    public List<report> getAllReport() {
        return adminservice.repAllSelect();
    }

    //修改举报信息状态为1，通过
    //发送邮件
    @RequestMapping(value = "/updateReport")
    @ResponseBody
    public Result updateReport(Integer id, Integer whistleblower_num, Integer defendant_num) {
        System.out.println("输出账号id....." + id);
        System.out.println("输出举报人defendant_num...." + defendant_num);
        String defendant_email=defendant_num+"@m.gduf.edu.cn";
        String whistleblower_email=whistleblower_num+"@m.gduf.edu.cn";
        try {
            System.out.println("try");
            adminservice.repUpdate(id);

            //发送邮件给被举报人
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1024795247@qq.com"); //发送者邮箱
            message.setTo(defendant_email);  //接受者邮箱
            message.setSubject("主题：广金拼团小程序提示邮件"); //邮件主题
            message.setText("您在广金拼团小程序里的账号经人举报且核实无误，已被冻结！如有问题，请回复邮箱反馈。"); //邮件内容
            mailSender.send(message);

            //发送邮件给举报人
            SimpleMailMessage message2 = new SimpleMailMessage();
            message2.setFrom("1024795247@qq.com"); //发送者邮箱
            message2.setTo(whistleblower_email);  //接受者邮箱
            message2.setSubject("主题：广金拼团小程序举报结果提示邮件"); //邮件主题
            message2.setText("您提交的举报申请已审核成功，感谢您的举报！如有问题，请回复邮箱反馈。"); //邮件内容
            mailSender.send(message2);

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("catch");
            return Result.error(CodeMsg.SERVER_ERROR);
        }


        return Result.success(0);
    }


    //修改举报信息状态为2，不通过
    //发送邮件
    @RequestMapping(value = "/updateReportFail")
    @ResponseBody
    public Result updateReportFail(Integer id, Integer whistleblower_num) {
        System.out.println("输出账号id....." + id);
        System.out.println("输出举报人whistleblower_num...." + whistleblower_num);
        String whistleblower_email=whistleblower_num+"@m.gduf.edu.cn";
        try {
            System.out.println("try");
            adminservice.repUpdate(id);
            //发送邮件给举报人
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1024795247@qq.com"); //发送者邮箱
            message.setTo(whistleblower_email);  //接受者邮箱
            message.setSubject("主题：广金拼团小程序举报结果提示邮件"); //邮件主题
            message.setText("经核对，你提交的举报申请证明材料不足以证明该用户存在不良行为，故审核失败！如有问题，请回复邮箱反馈。"); //邮件内容
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("catch");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success(0);
    }


    //获取所有用户
    @RequestMapping("/getAll")
    @ResponseBody
    public List<student> getAll() {
        return studentservice.allSelect();
    }

    //根据账号查找用户（用户管理）
    @RequestMapping(value = "/findStuById", method = RequestMethod.POST)
    @ResponseBody
    public student findStuById(Integer id) {
        //取数据
        System.out.println("要查询的账号...id=" + id);
        student stu = studentservice.findStuById(id);
        return stu;
    }


    //解冻
    @RequestMapping(value = "/thawById", method = RequestMethod.POST)
    @ResponseBody
    public int thaw(Integer id) {
        //取数据
        System.out.println("要解冻的账号...id=" + id);

        return studentservice.thawById(id);
    }

    //冻结
    @RequestMapping(value = "/freezeById", method = RequestMethod.POST)
    @ResponseBody
    public int freez(Integer id) {
        //取数据
        System.out.println("要冻结的账号...id=" + id);

        return studentservice.freezeById(id);
    }



    //获取所有订单
    @RequestMapping("/getOrder")
    @ResponseBody
    public List<order> getOrder() {
        return orderservice.allOrder();
    }


    //根据订单号查找订单（拼团信息管理）
    @RequestMapping(value = "/findOrderById", method = RequestMethod.POST)
    @ResponseBody
    public order findOrderById(String order_id) {
        //取数据
        System.out.println("要查询的订单号...id=" + order_id);
        order order = orderservice.findOrderById(order_id);
        return order;
    }

    //根据订单号删除订单（拼团信息管理）
    @RequestMapping(value = "/delOrder", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public int delOrder(String order_id) {
        //取数据
        System.out.println("要查询的订单号...id=" + order_id);
        orderservice.delHouse(order_id);
        orderservice.delOrder(order_id);

        return 1;
    }
}