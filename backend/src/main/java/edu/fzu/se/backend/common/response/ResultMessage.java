package edu.fzu.se.backend.common.response;

import edu.fzu.se.backend.common.enums.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "统一返回结果")
public class ResultMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "响应业务状态")
    private Integer code;

    @Schema(name = "响应消息")
    private String msg;

    @Schema(name = "响应中的数据")
    private Object data;

    public ResultMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultMessage success() {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static ResultMessage success(Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultMessage failure(ResultCode resultCode) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        return result;
    }
    public static ResultMessage failure(ResultCode resultCode, String msg) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setMsg(msg);
        return result;
    }
    public static ResultMessage failure(ResultCode resultCode, Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMessage();
    }
}