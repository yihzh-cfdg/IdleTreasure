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
@TableName("trades")
@Schema(name = "Trades", description = "交易")
public class Trades {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Trade_ID", description = "交易ID")
    private Long Trade_ID;

    @Schema(name = "Buyer_ID", description = "买家ID")
    private Long Buyer_ID;

    @Schema(name = "Seller_ID", description = "卖家ID")
    private Long Seller_ID;

    @Schema(name = "Goods_ID", description = "商品ID")
    private Long Goods_ID;

    @Schema(name = "Trade_Time", description = "交易时间")
    private String Trade_Time;

    @Schema(name = "Paid_Amount", description = "支付金额")
    private Double Paid_Amount;

    @Schema(name = "Delivery_Address",description = "收货地址")
    private String Delivery_Address;

    @Schema(name = "Shipping_Address",description = "发货地址")
    private String Shipping_Address;

    @Schema(name = "Transaction_Number", description = "交易编号")
    private String Transaction_Number;

    @Schema(name = "Transaction_Status", description = "交易状态")
    private String Transaction_Status;

}
