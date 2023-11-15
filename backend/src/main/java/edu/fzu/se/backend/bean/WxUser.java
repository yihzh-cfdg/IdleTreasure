package edu.fzu.se.backend.bean;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
@Schema(description = "微信用户")
public class WxUser {
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Long User_ID;

    @Schema(description = "用户名称")
    private String User_Name;

    @Schema(description = "用户密码")
    private String User_Key;

    @Schema(description = "福州大学学号")
    private String FZU_Key;

    @Schema(description = "头像图片链接")
    private String Head_Portrait;

    @Schema(description = "收货地址")
    private String Delivery_Address;
}