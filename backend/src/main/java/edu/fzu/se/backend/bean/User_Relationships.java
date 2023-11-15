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
@TableName("user_relationships")
@Schema(name = "User_Relationships", description = "用户关系")
public class User_Relationships {

    @TableId(type = IdType.AUTO)
    @Schema(name = "Relationship_ID", description = "关系ID")
    private Integer Relationship_ID;

    @Schema(name = "Like_ID", description = "关注者ID")
    private Long Like_ID;

    @Schema(name = "Beliked_ID", description = "被关注者ID")
    private Long Beliked_ID;

    @Schema(name = "Like_Point", description = "关注时间点")
    private String Like_Point;

}