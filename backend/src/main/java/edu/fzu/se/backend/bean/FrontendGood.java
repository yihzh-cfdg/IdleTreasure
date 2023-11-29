package edu.fzu.se.backend.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontendGood {
    // in table Goods,users,Images
    private Long Goods_Id;
    private Double Goods_Price;
    private String Goods_Name;
    private String User_Name;
    private String Image_Data;
    private String Head_Portrait;
}