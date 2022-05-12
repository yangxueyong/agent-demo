package com.yxy.cloud.entity.res;

import com.yxy.cloud.exception.ExceptionMember;

import java.io.Serializable;
import java.util.Optional;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private boolean success;

    private T data;

    private String msg;

    private R(ExceptionMember resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private R(ExceptionMember resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(ExceptionMember resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private R(ExceptionMember resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ExceptionMember.SUCCESS.getCode() == code;
    }



    public static <T> R<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> R<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> R<T> data(int code, T data, String msg) {
        return new R(code, data, data == null ? "暂无承载数据" : msg);
    }

    public static <T> R<T> success(String msg) {
        return new R(ExceptionMember.SUCCESS, msg);
    }

    public static <T> R<T> success(ExceptionMember resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(ExceptionMember resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> fail(String msg) {
        return new R(ExceptionMember.FAILURE, msg);
    }


    public static <T> R<T> fail(int code, String msg) {
        return new R(code, (Object) null, msg);
    }

    public static <T> R<T> fail(ExceptionMember resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> fail(ExceptionMember resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "R(code=" + this.getCode() + ", success=" + this.isSuccess() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public R() {
    }
}
