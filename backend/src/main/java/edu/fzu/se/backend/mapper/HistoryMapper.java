package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.History;

import java.util.List;


@Mapper
public interface HistoryMapper {
    @Select("SELECT * FROM historys")
    List<History> selectAll();
    //新增根据用户ID查询全部搜索历史记录，根据用户ID查询该用户最新的十条搜索历史记录的方法
    @Select("SELECT * FROM Historys WHERE User_ID = #{userId}")
    List<History> selectByUserId(Long userId);

    @Select("SELECT * FROM Historys WHERE User_ID = #{userId} ORDER BY Search_ID DESC LIMIT 10")
    List<History> selectLatestTenByUserId(Long userId);
    @Select("SELECT * FROM Historys WHERE Search_ID=#{searchId}")
    History selectById(Long searchId);
    
    @Delete("DELETE FROM Historys WHERE Search_ID=#{searchId}")
    int deleteById(Long searchId);
    
    @Insert("INSERT INTO Historys (User_ID, Keyword) VALUES(#{userId}, #{keyword})")
    int insertHistory(History history);
    
    @Update("UPDATE Historys SET User_ID=#{userId}, Keyword=#{keyword} WHERE Search_ID=#{searchId}")
    int updateById(History history);
}
