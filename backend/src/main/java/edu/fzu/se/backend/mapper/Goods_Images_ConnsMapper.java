package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;

import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.Goods_Images_Conns;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.Images;
import java.util.List;

@Mapper
public interface Goods_Images_ConnsMapper {

    // 根据给定的商品 ID和图片 ID，从数据库中查询匹配的商品图片连接信息并返回结果
    @Operation(summary = "根据商品 ID和图片 ID查询商品图片连接信息")
    @Select("SELECT g.*, i.* FROM Goods g " +
            "JOIN Goods_Images_Conns c ON g.Goods_ID = c.Goods_ID " +
            "JOIN Images i ON c.Image_ID = i.Image_ID " +
            "WHERE g.Goods_ID = #{Goods_ID} AND i.Image_ID = #{Image_ID}")
    Goods_Images_Conns selectByGoodsAndImageIds(@Param("Goods_ID") Long Goods_ID, @Param("Image_ID") Long Image_ID);

    // 查询该商品关联的所有商品图片连接信息（Goods_Images_Conns）并返回结果列表
    @Operation(summary = "根据商品 ID查询商品图片连接信息列表")
    @Select("SELECT g.*, i.* FROM Goods g " +
            "JOIN Goods_Images_Conns c ON g.Goods_ID = c.Goods_ID " +
            "JOIN Images i ON c.Image_ID = i.Image_ID " +
            "WHERE g.Goods_ID = #{Goods_ID}")
    List<Goods_Images_Conns> selectByGoodsId(Long Goods_ID);

    @Operation(summary = "插入商品图片连接信息")
    @Insert("INSERT INTO Goods_Images_Conns (Goods_ID, Image_ID) " +
            "VALUES(#{Goods_ID}, #{Image_ID})")
    int insertConnection(Goods_Images_Conns connection);

    @Operation(summary = "删除商品图片连接信息")
    @Delete("DELETE FROM Goods_Images_Conns WHERE Goods_ID=#{Goods_ID} AND Image_ID=#{Image_ID}")
    int deleteConnection(@Param("Goods_ID") Long Goods_ID, @Param("Image_ID") Long Image_ID);
}