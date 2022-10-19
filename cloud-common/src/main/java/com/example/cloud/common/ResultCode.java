package com.example.cloud.common;

import com.example.cloud.common.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;


@NoArgsConstructor
@Data
public class ResultCode<T> implements Serializable {
    /**
     * 结果编码(500:失败, 200: 成功, 204: 警告)
     */
    private int code;

    /**
     * 信息描述
     */
    private String message = Strings.EMPTY;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private Long timestamp = System.currentTimeMillis();

    /**
     * 是否成功
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private boolean success;

    public static final int CODE_FAIL = 500;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_WARNING = 204;

    public static final String TEXT_FAIL = "操作失败";
    public static final String TEXT_SUCCESS = "操作成功";

    public ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultCode(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setCode(int code, String message) {
        this.setCode(code, message, null);
    }

    public void setCode(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return (this.code == CODE_SUCCESS);
    }

    public static <T> boolean isSuccess(ResultCode<T> resultCode) {
        return (resultCode != null && resultCode.getCode() == CODE_SUCCESS);
    }

    public static <T> ResultCode<T> fail() {
        return new ResultCode<>(CODE_FAIL, TEXT_FAIL);
    }

    public static <T> ResultCode<T> fail(String message) {
        return set(CODE_FAIL, message, null);
    }

    public static <T> ResultCode<T> fail(String message, T data) {
        return set(CODE_FAIL, message, data);
    }

    public static <T> ResultCode<T> success() {
        return new ResultCode<>(CODE_SUCCESS, TEXT_SUCCESS);
    }

    public ResultCode ok(String message) {
        return success(message, null);
    }

    public static <T> ResultCode<T> success(String message, T data) {
        return set(CODE_SUCCESS, message, data);
    }

    public static <T> ResultCode<T> success(T data) {
        return set(CODE_SUCCESS, TEXT_SUCCESS, data);
    }

    public static <T> ResultCode<T> warning(String message) {
        return set(CODE_WARNING, message, null);
    }

    public static <T> ResultCode<T> warning(String message, T data) {
        return new ResultCode<>(CODE_WARNING, message, data);
    }

    public static <T> ResultCode<T> set(int code, String message, T data) {
        return new ResultCode<>(code, message, data);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }
}