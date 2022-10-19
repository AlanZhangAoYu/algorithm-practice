package com.example.cloud.common.exception;

import com.example.cloud.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.StringJoiner;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultCode handle(Exception e) {
        log.error("{}", e);

        return ResultCode.fail(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResultCode handleBindException(BindException e) {
        log.error("{}", e);

        BindingResult result = e.getBindingResult();
        StringJoiner joiner = new StringJoiner("；");

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                joiner.add(error.getDefaultMessage());
            });
        }

        return ResultCode.fail(joiner.toString());
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResultCode handleServiceException(ServiceException e) {
        log.error("{}", e);
        if (e.getResultModel() != null) {
            return e.getResultModel();
        }
        return ResultCode.fail(e.getMessage());
    }
}
