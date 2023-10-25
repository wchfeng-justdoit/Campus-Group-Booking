package com.cf.CampusGroupBooking.controller;


import com.cf.CampusGroupBooking.entity.ResultMsg.CodeMsg;
import com.cf.CampusGroupBooking.entity.ResultMsg.Result;
import com.cf.CampusGroupBooking.entity.car;
import com.cf.CampusGroupBooking.entity.house;
import com.cf.CampusGroupBooking.entity.order;
import com.cf.CampusGroupBooking.entity.shopping;
import com.cf.CampusGroupBooking.service.CarService;
import com.cf.CampusGroupBooking.service.HouseService;
import com.cf.CampusGroupBooking.service.OrderService;
import com.cf.CampusGroupBooking.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RestController
public class orderController {

    @Autowired
    private ShoppingService shoppingservice;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private OrderService orderservice;

    /*
    获取购物拼单的信息
     */
    @RequestMapping("/getShop")
    public List<shopping> selectShop(HttpSession session) {
//        if(session.getId() == null) {
//            return null;
//        }
        return shoppingservice.selectShop();
    }

    /*
    发布拼单购物信息
     */
    @RequestMapping(value = "/publishShop", method = RequestMethod.POST)
    public Result publicShop(Integer publisher,
                             String type,
                             String publish_time,
                             String status,
                             String content,
                             String image_route,
                             String order_id,
                             String product_type,
                             Integer price_cad,
                             MultipartFile Pic) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        shopping shopping = new shopping( order_id, product_type, price_cad);

        //   System.out.println(Pic);
        //        System.out.println(studata);
//        student stu = JSONObject.toJavaObject(studata.getJSONObject("stu"), student.class);
        //   System.out.println("---"+stu);
//        stu.setAvatar(studata.getJSONObject("stu").getJSONObject("avatar").getString("data").toString());
//        System.out.println(stu);
        //已存在在好

        //对文件进行处理
        String imgPath = "E:\\毕业\\毕设\\拼团小程序\\publish\\";
        //File filePath = new File(avatarPath);
        File cPath = new File(imgPath);
        // 如果保存资料的地不存在，就先创建目录
//        if (!filePath.exists() ) {
//            filePath.mkdirs();
//        }
        if (!cPath.exists()) {
            cPath.mkdirs();
        }

        // String headFilename  = Pic[0].getOriginalFilename();//头像
        String imgFilename = Pic.getOriginalFilename();
        // 使用UUID重新命名上传的壁纸名(uuid_原始文件名称)
        // String newHeadFilename = stu.getId() + "" + UUID.randomUUID() +  "_" + headFilename;//".jpg";
        String newCardFilename =  UUID.randomUUID() + "_" + imgFilename;
        try {
            // 使用MultipartFile接口的方法完成文件上传到指定位置
            //System.out.println(avatarPath + newFilename);
            //   Pic[0].transferTo(new File(avatarPath + newHeadFilename));
            Pic.transferTo(new File(imgPath + newCardFilename));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数

            order.setOrder_id("s" + time + num);

            order.setPublish_time(currentTime);
            order.setImage_route("publish/" + newCardFilename);

            shopping.setOrder_id( "s" + time + num);

            orderService.publishOrder(order);
            shoppingService.publishShop(shopping);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }

    /*
    发布拼单购物信息(无图片）
     */
    @RequestMapping(value = "/shopWithoutPic", method = RequestMethod.POST)
    public Result shopWithoutPic(Integer publisher,
                             String type,
                             String publish_time,
                             String status,
                             String content,
                             String image_route,
                             String order_id,
                             String product_type,
                             Integer price_cad ) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        shopping shopping = new shopping( order_id, product_type, price_cad);
        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数

            order.setOrder_id("s" + time + num);
            order.setPublish_time(currentTime);
            shopping.setOrder_id( "s" + time + num);

            orderService.publishOrder(order);
            shoppingService.publishShop(shopping);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }

    /*
    发布拼车信息
     */
    @RequestMapping(value = "/publishCar", method = RequestMethod.POST)
    public Result publicCar(Integer publisher,
                             String type,
                             String publish_time,
                             String status,
                             String content,
                             String image_route,
                             String order_id,
                             String start_point,
                             String end_point,
                            String starttime,
                             MultipartFile Pic) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        Date start_time=new Date();
        car car = new car( start_point, end_point, start_time,order_id);

            //对文件进行处理
            String imgPath = "E:\\毕业\\毕设\\拼团小程序\\publish\\";
            File cPath = new File(imgPath);

            if (!cPath.exists()) {
                cPath.mkdirs();
            }
            String imgFilename = Pic.getOriginalFilename();
            // 使用UUID重新命名上传的壁纸名(uuid_原始文件名称)

            String newCardFilename = UUID.randomUUID() + "_" + imgFilename;
            try {
                Pic.transferTo(new File(imgPath + newCardFilename));

            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(CodeMsg.SERVER_ERROR);
            }

        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //格式化出发时间
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            start_time = ft.parse(starttime);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数

            order.setOrder_id("C" + time + num);

            order.setPublish_time(currentTime);
            order.setImage_route("publish/" + newCardFilename);

            car.setOrder_id( "C" + time + num);
            car.setStart_time(start_time);
            orderService.publishOrder(order);
            carService.publishCar(car);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }

    /*
        发布拼车信息(无图）
         */
    @RequestMapping(value = "/carWithoutPic", method = RequestMethod.POST)
    public Result carWithoutPic(Integer publisher,
                            String type,
                            String publish_time,
                            String status,
                            String content,
                            String image_route,
                            String order_id,
                            String start_point,
                            String end_point,
                            String starttime) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        Date start_time=new Date();
        car car = new car( start_point, end_point, start_time,order_id);
        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //格式化出发时间
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            start_time = ft.parse(starttime);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数
            order.setOrder_id("C" + time + num);
            order.setPublish_time(currentTime);
            car.setOrder_id( "C" + time + num);
            car.setStart_time(start_time);
            orderService.publishOrder(order);
            carService.publishCar(car);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }
    /*
    发布拼房信息
     */
    @RequestMapping(value = "/publishHouse", method = RequestMethod.POST)
    public Result publicHouse(Integer publisher,
                            String type,
                            String publish_time,
                            String status,
                            String content,
                            String image_route,
                            String order_id,
                            String gender_require,
                            String housingtime,
                            MultipartFile Pic) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        Date housing_time=new Date();
        house house = new house( order_id, gender_require, housing_time);
        System.out.println("收到的数据===="+publisher+"  "+type+"   "+status+"   "+content+"  "+image_route+"  "+order_id+"  "+gender_require+"  "+housingtime);

        //对文件进行处理
        String imgPath = "E:\\毕业\\毕设\\拼团小程序\\publish\\";
        File cPath = new File(imgPath);
        if (!cPath.exists()) {
            cPath.mkdirs();
        }

        String imgFilename = Pic.getOriginalFilename();
        // 使用UUID重新命名上传的壁纸名(uuid_原始文件名称)
        String newCardFilename =  UUID.randomUUID() + "_" + imgFilename;
        try {
            Pic.transferTo(new File(imgPath + newCardFilename));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //格式化入住时间
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            housing_time = ft.parse(housingtime);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数
            //性别
            if(gender_require.equals("男")){
                gender_require="0";
            }else {
                gender_require="1";
            }
            order.setOrder_id("H" + time + num);
            order.setPublish_time(currentTime);
            order.setImage_route("publish/" + newCardFilename);

            house.setOrder_id( "H" + time + num);
            house.setHousing_time(housing_time);
            house.setGender_require(gender_require);
            orderService.publishOrder(order);
            houseService.publishHouse(house);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }
    /*
        发布拼房信息
         */
    @RequestMapping(value = "/houseWithoutPic", method = RequestMethod.POST)
    public Result houseWithoutPic(Integer publisher,
                              String type,
                              String publish_time,
                              String status,
                              String content,
                              String image_route,
                              String order_id,
                              String gender_require,
                              String housingtime) throws IOException {
        order order = new order(order_id, type, publish_time, publisher, image_route, status, content);
        Date housing_time=new Date();
        house house = new house( order_id, gender_require, housing_time);
        try {
            //获取当前时间
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //格式化入住时间
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            housing_time = ft.parse(housingtime);
            //生成订单号
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(dt); //当前系统时间字符串
            Random r = new Random();
            int num = r.nextInt(1000000 + 1);  //六位随机数
            //性别
            if(gender_require.equals("男")){
                gender_require="0";
            }else {
                gender_require="1";
            }
            order.setOrder_id("H" + time + num);
            order.setPublish_time(currentTime);
            house.setOrder_id( "H" + time + num);
            house.setHousing_time(housing_time);
            house.setGender_require(gender_require);
            orderService.publishOrder(order);
            houseService.publishHouse(house);
        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(order);
        return Result.success("发布完成！");
    }

    /*
    获取拼房的信息
   */
    @RequestMapping("/getHouse")
    public List<house> selectHouse(HttpSession session) {
//        if(session.getId() == null) {
//            return null;
//        }
        return houseService.selectHouse();
    }

    /*
    获取拼车的信息
    */
    @RequestMapping("/getCar")
    public List<car> selectCar(HttpSession session) {
        if (session.getId() == null) {
            return null;
//            car Msg = new car();
//            Msg.setContent("请登录！");
//            List<car> car = new LinkedList<car>();
//            car.add(Msg);
//            return car;
        }
        ;
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


    /*
  获取我的发布信息
   */
    @RequestMapping("/getMyOrder")
    public List<order> selectMyOrder(Integer id) {
        return orderservice.selectMyOrder(id);
    }


    /*
  通过订单号获取拼房的信息
 */
    @RequestMapping("/getHouseById")
    public List<house> selectHouseById(String order_id) {

        return houseService.selectHouseById(order_id);
    }
}

