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
