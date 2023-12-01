package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;

import edu.fzu.se.backend.bean.History;

import java.util.List;


@Mapper
public interface HistoryMapper {
    @Operation(summary = "获取所有搜索历史记录")
    @Select("SELECT * FROM historys WHERE type='搜索历史'")
    List<History> selectAllSH();

    @Operation(summary = "获取商品历史记录总数")
    @Select("SELECT COUNT(*) FROM historys WHERE type='商品历史'")
    Integer selectItemCount();
    @Operation(summary = "根据用户ID查询全部搜索历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID} AND type='搜索历史'")
    List<History> selectSHByUserId(Long User_ID);

    @Operation(summary = "根据用户ID查询最新的十条搜索历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID} AND type='搜索历史' ORDER BY Search_ID DESC LIMIT 10 ")
    List<History> selectLatestTenSHByUserId(Long User_ID);

    @Operation(summary = "根据搜索ID查询搜索历史记录")
    @Select("SELECT * FROM Historys WHERE Search_ID=#{Search_ID} AND type='搜索历史'")
    History selectSHById(Long Search_ID);

    @Operation(summary = "根据搜索ID删除历史记录")
    @Delete("DELETE FROM Historys WHERE Search_ID=#{Search_ID}")
    int deleteById(Long Search_ID);

    @Operation(summary = "新增搜索历史记录")
    @Insert("INSERT INTO Historys (User_ID, Keyword, type) VALUES(#{User_ID}, #{Keyword},#{type})")
    int insertHistory(History history);

    @Operation(summary = "根据搜索ID更新搜索历史记录")
    @Update("UPDATE Historys SET User_ID=#{User_ID}, Keyword=#{Keyword} WHERE Search_ID=#{Search_ID} AND type=#{type}")
    int updateById(History history);

    @Operation(summary = "获取所有商品历史记录")
    @Select("SELECT * FROM historys WHERE type='商品历史'")
    List<History> selectAllIH();

    @Operation(summary = "根据用户ID查询全部商品历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID} AND type='商品历史'")
    List<History> selectIHByUserId(Long User_ID);

    @Operation(summary = "根据用户ID查询最新的十条商品历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID} AND type='商品历史' ORDER BY Search_ID DESC LIMIT 10 ")
    List<History> selectLatestTenIHByUserId(Long User_ID);

    @Operation(summary = "根据搜索ID查询商品历史记录")
    @Select("SELECT * FROM Historys WHERE Search_ID=#{Search_ID} AND type='商品历史'")
    History selectIHById(Long Search_ID);


}