package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.Images;
import java.util.List;

@Mapper
public interface ImagesMapper {

    @Select("SELECT * FROM Images")
    List<Images> selectAll();

    @Select("SELECT * FROM Images WHERE Image_ID = #{imageId}")
    Images selectById(Long imageId);


    @Insert("INSERT INTO Images (Image_Name, Image_Data, Image_Class) " +
            "VALUES(#{Image_Name}, #{Image_Data}, #{Image_Class})")
    @Options(useGeneratedKeys = true, keyProperty = "Image_ID")
    @SelectKey(statement = "SELECT last_insert_id() FROM Images", before = false,
                resultType = Long.class, keyColumn = "Image_ID", keyProperty = "Image_ID")
    Long insertImage(Images images);


    @Update("UPDATE Images SET Image_Name=#{Image_Name}, Image_Data=#{Image_Data}, " +
            "Image_Class=#{Image_Class} WHERE Image_ID=#{Image_ID}")
    int updateImageById(Images images);


    @Update("UPDATE Images SET Image_Name=#{Image_Name}, Image_Class=#{Image_Class} WHERE Image_ID=#{Image_ID}")
    int updateNameById(Images images);


    @Delete("DELETE FROM Images WHERE Image_ID=#{imageId}")
    int deleteById(Long imageId);
}