package edu.fzu.se.backend.bean;
import java.util.Date;

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
@TableName("goods")
@Schema(name = "Goods", description = "商品")
public class Goods {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Goods_ID", description = "商品ID")
    private Long Goods_ID;

    @Schema(name = "Goods_Name", description = "商品名称")
    private String Goods_Name;

    @Schema(name = "Seller_ID", description = "卖家ID")
    private Long Seller_ID;

    @Schema(name = "Goods_Price", description = "商品价格")
    private Double Goods_Price;

    @Schema(name = "Goods_Description", description = "商品描述")
    private String Goods_Description;

    @Schema(name = "Classification", description = "分类")
    private String Classification;

    @Schema(name = "Release_Time", description = "发布时间")
    private Date Release_Time;
}
