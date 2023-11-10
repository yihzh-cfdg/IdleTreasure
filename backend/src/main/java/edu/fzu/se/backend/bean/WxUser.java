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
@TableName("Users")
public class WxUser {
    @TableId(type = IdType.AUTO)
    private Long User_ID;
    private String User_Name;
    private String User_Key;
    private String FZU_Key;
    private String Head_Portrait;
    private String statue;
}
