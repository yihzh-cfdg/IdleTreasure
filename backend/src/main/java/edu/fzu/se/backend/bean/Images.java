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
@TableName("images")
@Schema(name = "Images", description = "图片")
public class Images {
    @TableId(type = IdType.AUTO)
    @Schema(name = "Image_ID", description = "图片ID")
    private Long Image_ID;

    @Schema(name = "Image_Name", description = "图片名称")
    private String Image_Name;

    @Schema(name = "Image_Data", description = "图片数据")
    private String Image_Data;

    @Schema(name = "Image_Class", description = "图片分类")
    private String Image_Class;
}