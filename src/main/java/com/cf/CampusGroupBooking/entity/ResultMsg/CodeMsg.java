package com.cf.CampusGroupBooking.entity.ResultMsg;

import lombok.Data;

@Data
public class CodeMsg {
    private int code;
    private String msg;
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {//带自定义格式化参数的错误信息
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code,message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 通用异常
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "SUCESS");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常:%s");
    /**
     * 登录注册模块5002XX
     */
    public static final CodeMsg USER_EXITS = new CodeMsg(500211, "用户已存在");
    public static final CodeMsg NO_LOGIN = new CodeMsg(500211, "未登录");
    public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "密码不能为空");
    public static final CodeMsg MOBILE_EMPTY = new CodeMsg(500211, "手机号不能为空");
    public static final CodeMsg MOBILE_ERROR = new CodeMsg(500211, "手机号格式错误");
    public static final CodeMsg USER_NOT_EXITS = new CodeMsg(500211, "用户不存在");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500211, "密码错误");
    public static final CodeMsg login_error = new CodeMsg(500, "用户名或者密码错误！");
}