package com.cf.CampusGroupBooking.entity.ResultMsg;

import lombok.Data;

@Data
public class Result<T> {
    private int code;//返回状态码
    private String msg;//返回的message
    private T data;//返回的数据

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg msg) {
        if (msg == null) {
            return;
        }
        this.code = msg.getCode();
        this.msg = msg.getMsg();
    }

    /**
     * 失败的时候调用
     */
    public static <T> Result<T> error(CodeMsg msg) {
        return new Result<T>(msg);
    }

    /**
     *成功的时候调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
}