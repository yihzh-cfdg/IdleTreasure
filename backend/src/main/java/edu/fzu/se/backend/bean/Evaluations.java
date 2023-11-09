package edu.fzu.se.backend.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("Evaluations")
public class Evaluations {
    @TableId(type = IdType.AUTO)
    private Long Trade_ID;
    private String Buyer_Evaluation;
    private String Seller_Evaluation;
}
