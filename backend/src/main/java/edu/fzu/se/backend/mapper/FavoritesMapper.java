package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Favorites;

import java.util.List;


@Mapper
public interface FavoritesMapper {
    @Select("SELECT * FROM Favorites")
    List<Favorites> selectAll();
//  selectById：根据收藏id进行查询。
    @Select("SELECT * FROM Favorites WHERE Favorite_ID=#{favoriteId}")
    Favorites selectById(Long favoriteId);
//  selectByUserId：根据用户id进行查询。
    @Select("SELECT * FROM Favorites WHERE User_ID=#{userId}")
    List<Favorites> selectByUserId(Long userId);
//  selectByKeyword：根据收藏商品的关键字进行模糊匹配查询。该方法使用了子查询和LIKE函数来实现。
    @Select("SELECT * FROM Favorites WHERE Good_ID IN " +
            "(SELECT Good_ID FROM Goods WHERE Goods_Name LIKE CONCAT('%', #{keyword}, '%'))")
    List<Favorites> selectByKeyword(String keyword);
    @Delete("DELETE FROM Favorites WHERE Favorite_ID=#{favoriteId}")
    int deleteById(Long favoriteId);

    @Insert("INSERT INTO Favorites (Favorite_ID, User_ID, Good_ID, Insert_Time) " +
            "VALUES(#{favoriteId}, #{userId}, #{goodsId}, #{insertTime})")
    int insertFavorite(Favorites favorite);

    @Update("UPDATE Favorites SET User_ID=#{userId}, Good_ID=#{goodsId}, Insert_Time=#{insertTime} " +
            "WHERE Favorite_ID=#{favoriteId}")
    int updateById(Favorites favorite);
}
