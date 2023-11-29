package edu.fzu.se.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResultCode {

    BODY_NOT_MATCH(400,"请求的数据格式不符"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配"),
    PERMISSION_NO_ACCESS(403, "无访问权限"),
    NOT_FOUND(404, "未找到该资源"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试"),

    SUCCESS(200, "成功"),

    NOT_FOUNT_RESOURCE(1001,"没有找到相关资源"),
    PARAMETER_ERROR(1002,"请求参数有误"),
    PARAMETER_MISSING_ERROR(1003,"缺少必要请求参数"),
    REQUEST_MISSING_BODY_ERROR(1004,"缺少请求体"),

    USER_NOT_LOGGED_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在"),

    RESULE_DATA_NONE(3001, "数据未找到"),
    DATA_IS_WRONG(3002, "数据有误"),
    DATA_ALREADY_EXISTED(3003, "数据已存在"),

    INTERFACE_INNER_INVOKE_ERROR(4001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(4002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(4003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(4004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(4005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(4006, "接口负载过高"),

    SYSTEM_ERROR(5001,"系统错误"),
    UNKNOWN_ERROR(5002,"未知的错误");

    private static Map<Integer,ResultCode > searchMap = new HashMap<>();
    static {
        for (ResultCode item : ResultCode.values()) {
            searchMap.put(item.getCode(), item);
        }
    }
    public static ResultCode getByCode(Integer code){
        return searchMap.get(code);
    }

    int code;
    String message;

}
