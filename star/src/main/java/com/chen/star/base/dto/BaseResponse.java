package com.chen.star.base.dto;

import com.chen.star.base.exception.Status;
import lombok.Data;


@Data
public class BaseResponse<T> {

    private int code;

    private String msg;

    private T data;

    public BaseResponse() {
    }

    private BaseResponse(Status status, T data) {
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.data = data;
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
