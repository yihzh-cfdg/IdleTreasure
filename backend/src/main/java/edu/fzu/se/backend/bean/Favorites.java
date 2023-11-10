package edu.fzu.se.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
@TableName("Favorites")
public class Favorites {
    @TableId(type = IdType.AUTO)
    private Long Favorite_ID;
    private Long User_ID;
    private Long Goods_ID;
    private String Insert_Time;
}
