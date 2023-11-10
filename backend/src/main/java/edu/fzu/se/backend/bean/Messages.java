package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
@TableName("Messages")
public class Messages {
    @TableId(type = IdType.AUTO)
    private Long Message_ID;
    private Long Sender_ID;
    private Long Receiver_ID;
    private String Message_Content;
    private String Time;
    private Boolean IsRead;
}
