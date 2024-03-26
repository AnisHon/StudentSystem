package com.anishan.exception;


import com.anishan.entity.RestEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException e) {
        return RestEntity.failure(400, "请求参数错误" + e.getMessage()).toJson();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFound(HttpServletRequest req, Exception e) {
        return RestEntity.failure(404, req.getRequestURI() + "不存在").toJson();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleInternalServerError(HttpServletRequest req, Exception ex) {
        if (ex instanceof NoResourceFoundException noResourceFoundException) {
            return RestEntity.failure(403, "错误请求：" + noResourceFoundException.getMessage()).toJson();
        }
        System.out.println(ex.getMessage());
        return  RestEntity.failure(500, "致命错误").toJson();
    }

}
