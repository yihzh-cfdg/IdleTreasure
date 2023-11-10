package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
@TableName("History")
public class History {
    @TableId(type = IdType.AUTO)
    private Long Search_ID;
    private Long User_ID;
    private String Keyword;
}
