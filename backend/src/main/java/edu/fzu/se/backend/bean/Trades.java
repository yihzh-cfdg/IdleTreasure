package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
@TableName("Trades")
public class Trades {
    @TableId(type = IdType.AUTO)
    private Long Trade_ID;
    private Long Buyer_ID;
    private Long Seller_ID;
    private String Trade_Time;
    private Double Paid_Amount;
}
