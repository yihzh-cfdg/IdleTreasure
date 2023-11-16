package edu.fzu.se.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.Goods_Images_Conns;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.bean.Goods;

import java.util.List;


@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    @Operation(summary = "根据卖家ID查询商品记录")
    @Select("SELECT * FROM Goods WHERE Seller_ID = #{Seller_ID}")
    List<Goods> selectGoodsBySellerId(@Param("Seller_ID") Long Seller_ID);

    @Operation(summary = "根据卖家ID查询商品记录数量（我发布的）")
    @Select("SELECT COUNT(*) FROM Goods WHERE Seller_ID = #{Seller_ID}")
    int countGoodsBySellerId(@Param("Seller_ID") Long Seller_ID);
    @Operation(summary = "获取所有商品")
    @Select("SELECT * FROM Goods")
    List<Goods> selectAll();

    @Operation(summary = "根据商品ID删除商品")
    @Delete("DELETE FROM Goods WHERE Goods_ID=#{Goods_ID}")
    int deleteById(Long Goods_ID);

    @Operation(summary = "根据商品ID查询对应的图片信息")
    @Select("SELECT i.* FROM Goods g " +
            "JOIN Goods_Images_Conns c ON g.Goods_ID = c.Goods_ID " +
            "JOIN Images i ON c.Image_ID = i.Image_ID " +
            "WHERE g.Goods_ID = #{Goods_ID}")
    List<Images> selectImagesByGoodsId(Long Goods_ID);

    @Operation(summary = "获取首页商品")
    @Select("SELECT * FROM Goods LIMIT #{limit}")
    List<Goods> getHomePageGoods(@Param("limit") int limit);

    @Operation(summary = "根据商品ID查询商品记录")
    @Select("SELECT * FROM Goods WHERE Goods_ID = #{Goods_ID}")
    Goods selectById(Long Goods_ID);

    @Operation(summary = "根据卖家ID查询商品记录")
    @Select("SELECT * FROM Goods WHERE Seller_ID = #{Seller_ID}")
    List<Goods> selectBySellerId(Long Seller_ID);

    @Operation(summary = "根据商品价格范围查询商品记录")
    @Select("SELECT * FROM Goods WHERE Goods_Price BETWEEN #{minPrice} AND #{maxPrice}")
    List<Goods> selectByPriceRange(int minPrice, int maxPrice);

    @Operation(summary = "根据关键字模糊匹配查询商品记录")
    @Select("SELECT * FROM Goods WHERE Goods_Name LIKE CONCAT('%', #{Keyword}, '%')")
    List<Goods> selectByKeyword(String Keyword);
    @Operation(summary = "根据商品分类查询商品记录")
//  注意，分类属性列当前的数据类型是这样的：Classification ENUM('文娱用品', '学习用品', '生活用品', '交通工具', '奇奇怪怪') NOT NULL
    @Select("SELECT * FROM Goods WHERE Classification = #{Classification}")
    List<Goods> selectByClassification(String Classification);
    @Operation(summary = "根据用户名查询用户售卖的商品记录")
    @Select("SELECT g.* FROM Goods g JOIN Users u ON g.Seller_ID = u.User_ID WHERE u.User_Name = #{userName}")
    List<Goods> selectGoodsByUserName(String userName);

    @Operation(summary = "新增商品记录")
    @Insert("INSERT INTO Goods (Goods_Name, Seller_ID, Goods_Price, Goods_Description, Classification, Release_Time) " +
            "VALUES(#{Goods_Name}, #{Seller_ID}, #{Goods_Price}, #{Goods_Description}, #{Classification}, #{Release_Time})")
    int insertGood(Goods goods);

    @Operation(summary = "根据商品ID更新商品记录")
    @Update("UPDATE Goods SET Goods_Name=#{Goods_Name}, Goods_Price=#{Goods_Price}, " +
            "Goods_Description=#{Goods_Description}, Classification=#{Classification}, Release_Time=#{Release_Time} WHERE Goods_ID=#{Goods_ID}")
    int updateById(Goods goods);
}
