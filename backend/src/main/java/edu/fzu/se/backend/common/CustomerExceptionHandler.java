package edu.fzu.se.backend.common;

import edu.fzu.se.backend.common.enums.ResultCode;
import edu.fzu.se.backend.common.response.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Octopus
 * @date 2023/11/14 23:45
 * @description TODO
 * 根据枚举类定义异常
 * 根据异常返回对应的ResultMessage
 */
@Slf4j
@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMessage handleException(Exception e) {
        // 获取异常信息
        ResultCode resultCode = ResultCode.SYSTEM_ERROR;
        // 返回统一的错误结果
        return ResultMessage.failure(resultCode, e.getMessage());
    }
}
