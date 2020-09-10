package com.bhsoftware.projectserver.result;

import lombok.Data;

@Data
public class Result {

    private int code;
    String  message;
    Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
