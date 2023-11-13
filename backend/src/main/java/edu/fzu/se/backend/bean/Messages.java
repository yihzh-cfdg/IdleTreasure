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
@TableName("messages")
@Schema(name = "Messages", description = "消息")
public class Messages {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Message_ID", description = "消息ID")
    private Long Message_ID;

    @Schema(name = "Sender_ID", description = "发送者ID")
    private Long Sender_ID;

    @Schema(name = "Receiver_ID", description = "接收者ID")
    private Long Receiver_ID;

    @Schema(name = "Message_Content", description = "消息内容")
    private String Message_Content;

    @Schema(name = "Time", description = "时间")
    private String Time;

    @Schema(name = "IsRead", description = "是否已读")
    private Boolean IsRead;
}
