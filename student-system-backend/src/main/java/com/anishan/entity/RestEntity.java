package com.anishan.entity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import lombok.Data;

@Data
public class RestEntity<T> {

    private int code;
    private T data;
    private String message;

    private RestEntity(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public static <T> RestEntity<T> success(T data, String message) {
        return new RestEntity<T>(200, data, message);
    }

    public static <T> RestEntity<T> failure(int code, String message) {
        return new RestEntity<T>(code, null, message);
    }

    public String toJson() {
        return JSON.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }


}
