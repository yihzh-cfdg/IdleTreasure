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
@TableName("evaluations")
@Schema(name = "Evaluations",description = "评价")
public class Evaluations {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Trade_ID",description = "交易ID")
    private Long Trade_ID;
    @Schema(name = "Buyer_Evaluation",description = "买家评价")
    private String Buyer_Evaluation;
    @Schema(name = "Seller_Evaluation",description = "卖家评价")
    private String Seller_Evaluation;
}
