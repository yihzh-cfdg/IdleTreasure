package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;

import edu.fzu.se.backend.bean.History;

import java.util.List;


@Mapper
public interface HistoryMapper {
    @Operation(summary = "获取所有搜索历史记录")
    @Select("SELECT * FROM historys")
    List<History> selectAll();

    @Operation(summary = "根据用户ID查询全部搜索历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID}")
    List<History> selectByUserId(Long User_ID);

    @Operation(summary = "根据用户ID查询最新的十条搜索历史记录")
    @Select("SELECT * FROM Historys WHERE User_ID = #{User_ID} ORDER BY Search_ID DESC LIMIT 10")
    List<History> selectLatestTenByUserId(Long User_ID);

    @Operation(summary = "根据搜索ID查询搜索历史记录")
    @Select("SELECT * FROM Historys WHERE Search_ID=#{Search_ID}")
    History selectById(Long Search_ID);

    @Operation(summary = "根据搜索ID删除搜索历史记录")
    @Delete("DELETE FROM Historys WHERE Search_ID=#{Search_ID}")
    int deleteById(Long Search_ID);

    @Operation(summary = "新增搜索历史记录")
    @Insert("INSERT INTO Historys (User_ID, Keyword) VALUES(#{User_ID}, #{Keyword})")
    int insertHistory(History history);

    @Operation(summary = "根据搜索ID更新搜索历史记录")
    @Update("UPDATE Historys SET User_ID=#{User_ID}, Keyword=#{Keyword} WHERE Search_ID=#{Search_ID}")
    int updateById(History history);
}