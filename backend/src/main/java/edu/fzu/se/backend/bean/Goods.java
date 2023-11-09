package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
@TableName("Goods")
public class Goods {
    @TableId(type = IdType.AUTO)
    private Long Goods_ID;
    private String Goods_Name;
    private Long Seller_ID;
    private Double Goods_Price;
    private String Goods_Description;
    private String Classification;
    private String Release_Time;
    private String Goods_Pictures;
}
