package com.qixuan.pindan.controller;


import com.qixuan.pindan.entity.ResultMsg.CodeMsg;
import com.qixuan.pindan.entity.ResultMsg.Result;
import com.qixuan.pindan.entity.car;
import com.qixuan.pindan.entity.house;
import com.qixuan.pindan.entity.shopping;
import com.qixuan.pindan.service.CarService;
import com.qixuan.pindan.service.HouseService;
import com.qixuan.pindan.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class orderController {

    @Autowired
    private ShoppingService shoppingservice;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CarService carService;


    /*
    获取购物拼单的信息
     */
    @RequestMapping("/getShop")
    public List<shopping> selectShop(HttpSession session){
//        if(session.getId() == null) {
//            return null;
//        }
        return shoppingservice.selectShop();
    }

    /*
    发布拼单购物信息
     */
    @RequestMapping(value = "/publishShop",method = RequestMethod.POST)
    public Result publicShop(){
        return Result.success("jj");
    }

    /*
    获取拼房的信息
   */
    @RequestMapping("/getHouse")
    public List<house> selectHouse(HttpSession session){
//        if(session.getId() == null) {
//            return null;
//        }
        return houseService.selectHouse();
    }

    /*
    获取拼车的信息
    */
    @RequestMapping("/getCar")
    public List<car> selectCar(HttpSession session){
        if(session.getId() == null){
            return null;
//            car Msg = new car();
//            Msg.setContent("请登录！");
//            List<car> car = new LinkedList<car>();
//            car.add(Msg);
//            return car;
        };
        return carService.selectCar();
    }

//    @RequestMapping("/ifLogin")
//    public Result ifLogin(HttpSession session){
//        if(session.getId() == null){
//            //return Result.error();
//            return
//        }
//        return Result.success(CodeMsg.SUCCESS);
//    }


}

