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
@TableName("historys")
@Schema(description = "搜索历史记录")
public class History {
    @TableId(type = IdType.AUTO)
    @Schema(description = "搜索ID")
    private Long Search_ID;

    @Schema(description = "用户ID")
    private Long User_ID;

    @Schema(description = "搜索关键字")
    private String Keyword;

}
