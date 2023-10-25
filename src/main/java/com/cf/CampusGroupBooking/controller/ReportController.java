package com.cf.CampusGroupBooking.controller;

import com.cf.CampusGroupBooking.entity.ResultMsg.CodeMsg;
import com.cf.CampusGroupBooking.entity.ResultMsg.Result;
import com.cf.CampusGroupBooking.entity.report;
import com.cf.CampusGroupBooking.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;
    /*
   发布举报信息
    */
    @RequestMapping(value = "/publishReport", method = RequestMethod.POST)
    public Result publicReport(Integer whistleblower_num,
                              Integer defendant_num,
                              String content,
                              String image_route,
                              String publish_time,
                              String status,
                              MultipartFile Pic) throws IOException {
        report report = new report(whistleblower_num, defendant_num, image_route, content,publish_time,status);

        System.out.println("收到的数据===="+whistleblower_num+"  "+defendant_num+"   "+image_route+"   "+content+"  "+image_route+"  "+publish_time+"  "+status);
//        System.out.println("自己的邮箱："+selfEmail+"  "+"别人的邮箱："+otherEmail);
        //对文件进行处理
        String imgPath = "E:\\毕业\\毕设\\拼团小程序\\report\\";
        File cPath = new File(imgPath);
        if (!cPath.exists()) {
            cPath.mkdirs();
        }

        String imgFilename = Pic.getOriginalFilename();
        // 使用UUID重新命名上传的壁纸名(uuid_原始文件名称)
        String newCardFilename =  report.getWhistleblower_num()+""+UUID.randomUUID() + "_" + imgFilename;
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
            report.setPublish_time(currentTime);
            report.setImage_route("report/" + newCardFilename);
            reportService.publishReprt(report);

        } catch (Exception e) {
            System.err.println(e);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        System.out.println(report);
        return Result.success("提交完成！");
    }

}
