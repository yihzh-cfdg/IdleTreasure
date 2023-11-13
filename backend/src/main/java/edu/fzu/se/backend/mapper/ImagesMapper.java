package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.Images;
import java.util.List;

@Mapper
public interface ImagesMapper {
    @Operation(summary = "获取所有图片")
    @Select("SELECT * FROM Images")
    List<Images> selectAll();

    @Operation(summary = "根据图片ID查询图片")
    @Select("SELECT * FROM Images WHERE Image_ID = #{imageId}")
    Images selectById(Long imageId);

    @Operation(summary = "插入图片")
    @Insert("INSERT INTO Images (Image_Name, Image_Data, Image_Class) " +
            "VALUES(#{Image_Name}, #{Image_Data}, #{Image_Class})")
    @Options(useGeneratedKeys = true, keyProperty = "Image_ID")
    int insertImage(Images images);

    @Operation(summary = "根据图片ID更新图片")
    @Update("UPDATE Images SET Image_Name=#{Image_Name}, Image_Data=#{Image_Data}, " +
            "Image_Class=#{Image_Class} WHERE Image_ID=#{Image_ID}")
    int updateImageById(Images images);

    @Operation(summary = "根据图片ID更新图片名称和分类")
    @Update("UPDATE Images SET Image_Name=#{Image_Name}, Image_Class=#{Image_Class} WHERE Image_ID=#{Image_ID}")
    int updateNameById(Images images);

    @Operation(summary = "根据图片ID删除图片")
    @Delete("DELETE FROM Images WHERE Image_ID=#{imageId}")
    int deleteById(Long imageId);
}