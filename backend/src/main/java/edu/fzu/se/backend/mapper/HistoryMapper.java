package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.History;

import java.util.List;


@Mapper
public interface HistoryMapper {
    @Select("SELECT * FROM historys")
    List<History> selectAll();

    @Select("SELECT * FROM Historys WHERE Search_ID=#{searchId}")
    History selectById(Long searchId);
    
    @Delete("DELETE FROM Historys WHERE Search_ID=#{searchId}")
    int deleteById(Long searchId);
    
    @Insert("INSERT INTO Historys (User_ID, Keyword) VALUES(#{userId}, #{keyword})")
    int insertHistory(History history);
    
    @Update("UPDATE Historys SET User_ID=#{userId}, Keyword=#{keyword} WHERE Search_ID=#{searchId}")
    int updateById(History history);
}
