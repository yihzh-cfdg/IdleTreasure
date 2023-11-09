package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Favorites;

import java.util.List;


@Mapper
public interface FavoritesMapper {
    @Select("SELECT * FROM Favorites")
    List<Favorites> selectAll();

    @Delete("DELETE FROM Favorites WHERE Favorite_ID=#{favoriteId}")
    int deleteById(Long favoriteId);

    @Insert("INSERT INTO Favorites (Favorite_ID, User_ID, Good_ID, Insert_Time) " +
            "VALUES(#{favoriteId}, #{userId}, #{goodsId}, #{insertTime})")
    int insertFavorite(Favorites favorite);

    @Update("UPDATE Favorites SET User_ID=#{userId}, Good_ID=#{goodsId}, Insert_Time=#{insertTime} " +
            "WHERE Favorite_ID=#{favoriteId}")
    int updateById(Favorites favorite);
}
