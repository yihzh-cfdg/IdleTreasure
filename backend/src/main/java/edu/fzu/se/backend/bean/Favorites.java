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
@TableName("favorites")
public class Favorites {
    @TableId(type = IdType.AUTO)
    private Long Favorite_ID;
    private Long User_ID;
    private Long Good_ID;
    private String Insert_Time;
}
