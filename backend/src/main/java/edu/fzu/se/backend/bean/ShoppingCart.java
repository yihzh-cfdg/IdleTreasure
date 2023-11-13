package edu.fzu.se.backend.bean;
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
@TableName("shoppingcart")
@Schema(name= "ShoppingCart",description = "购物车")
public class ShoppingCart {
    @Schema(name = "User_ID",description = "用户ID")
    private Long User_ID;

    @Schema(name = "Goods_ID",description = "商品ID")
    private Long Goods_ID;

    @Schema(name = "Count",description = "商品数量")
    private Integer Count;
}
