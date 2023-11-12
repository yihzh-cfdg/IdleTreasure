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
@TableName("goods_images_conns")
@Schema(name = "Goods_Images_Conns", description = "商品图片连接")
public class Goods_Images_Conns {
    @Schema(name = "Goods_ID", description = "商品ID")
    private Integer Goods_ID;

    @Schema(name = "Image_ID", description = "图片ID")
    private Integer Image_ID;
}