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
    @Schema(name="User_ID",description = "用户ID")
    private Long User_ID;

    @Schema(name="User_Name",description = "用户名称")
    private String User_Name;

    @Schema(name="User_Key",description = "用户密码")
    private String User_Key;

    @Schema(name="FZU_Key",description = "福州大学学号")
    private String FZU_Key;

    @Schema(name="Head_Portrait",description = "头像图片链接")
    private String Head_Portrait;

    @Schema(name="Delivery_Address",description = "默认收货地址")
    private String Delivery_Address;

    @Schema(name="Shipping_Address",description = "默认发货地址")
    private String Shipping_Address;

    @Schema(name = "Like_Count", description = "关注数量")
    private Integer Like_Count;

    @Schema(name = "Beliked_Count", description = "被关注数量")
    private Integer Beliked_Count;

}