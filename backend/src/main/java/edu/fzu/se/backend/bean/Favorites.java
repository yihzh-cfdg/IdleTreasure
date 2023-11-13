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
@TableName("favorites")
@Schema(name = "Favorites", description = "收藏夹")
public class Favorites {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Favorite_ID", description = "收藏ID")
    private Long Favorite_ID;

    @Schema(name = "User_ID", description = "用户ID")
    private Long User_ID;

    @Schema(name = "Goods_ID", description = "商品ID")
    private Long Goods_ID;

    @Schema(name = "Insert_Time", description = "插入时间")
    private String Insert_Time;
}
