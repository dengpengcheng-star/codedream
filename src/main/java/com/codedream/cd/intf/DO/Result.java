package com.codedream.cd.intf.DO;

/**
 * @author DengPengcheng
 * @create 2022/3/815:35
 */
public class Result {
    //响应码
    private int code;

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
