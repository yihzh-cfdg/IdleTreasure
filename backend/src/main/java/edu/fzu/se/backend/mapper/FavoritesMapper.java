package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Favorites;

import java.util.List;


import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;



@Mapper
public interface FavoritesMapper {
    @Operation(summary = "获取所有收藏")
    @Select("SELECT * FROM Favorites")
    List<Favorites> selectAll();

    @Operation(summary = "根据收藏id进行查询")
    @Select("SELECT * FROM Favorites WHERE Favorite_ID=#{Favorite_ID}")
    Favorites selectById(Long Favorite_ID);

    @Operation(summary = "根据用户id进行查询")
    @Select("SELECT * FROM Favorites WHERE User_ID=#{User_ID}")
    List<Favorites> selectByUserId(Long User_ID);

    @Operation(summary = "根据收藏商品的关键字进行模糊匹配查询")
    @Select("SELECT * FROM Favorites WHERE Goods_ID IN " +
            "(SELECT Goods_ID FROM Goods WHERE Goods_Name LIKE CONCAT('%', #{Keyword}, '%'))")
    List<Favorites> selectByKeyword(String Keyword);

    @Operation(summary = "根据收藏id删除收藏")
    @Delete("DELETE FROM Favorites WHERE Favorite_ID=#{Favorite_ID}")
    int deleteById(Long Favorite_ID);

    @Operation(summary = "插入收藏信息")
    @Insert("INSERT INTO Favorites (Favorite_ID, User_ID, Goods_ID, Insert_Time) " +
            "VALUES(#{Favorite_ID}, #{User_ID}, #{Goods_ID}, #{Insert_Time})")
    int insertFavorite(Favorites favorite);

    @Operation(summary = "根据收藏id更新收藏信息")
    @Update("UPDATE Favorites SET User_ID=#{User_ID}, Goods_ID=#{Goods_ID}, Insert_Time=#{Insert_Time} " +
            "WHERE Favorite_ID=#{Favorite_ID}")
    int updateById(Favorites favorite);
}