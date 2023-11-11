package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trades")
public class Trades {
    @TableId(type = IdType.AUTO)
    private Long Trade_ID;
    private Long Buyer_ID;
    private Long Seller_ID;
    private String Trade_Time;
    private Double Paid_Amount;
}
