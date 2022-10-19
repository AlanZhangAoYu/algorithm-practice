package com.example.cloud.common.exception;


import com.example.cloud.common.ResultCode;

/**
 * @author xuwei
 */
public class ServiceException extends RuntimeException {
    private ResultCode resultCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.fail(message);
    }

    public ServiceException(ResultCode resultCode) {
        this(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ResultCode getResultModel() {
        return resultCode;
    }
}