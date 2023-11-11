package edu.fzu.se.backend.mapper;

import edu.fzu.se.backend.bean.WxUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface WxUserMapper{
    @Select("SELECT * FROM Users")
    List<WxUser> selectAll();
    //新增根据ID查询和根据学号查询
    @Select("SELECT * FROM Users WHERE User_ID=#{userId}")
    WxUser selectById(Long userId);
    @Select("SELECT * FROM Users WHERE FZU_Key=#{FzuKey}")
    WxUser selectByFzuKey(String FzuKey);
//    selectByKeyword的方法，用于根据用户名中的关键字进行模糊匹配查询
    @Select("SELECT * FROM Users WHERE User_Name LIKE CONCAT('%', #{keyword}, '%')")
    List<WxUser> selectByKeyword(String keyword);
    @Delete("DELETE FROM Users WHERE User_ID=#{userId}")
    int deleteById(Long User_ID);
    @Insert("INSERT INTO Users(User_Name, User_Key, FZU_Key, Head_Portrait) " +
        "VALUES(#{user_Name}, #{user_Key}, #{fZU_Key}, #{head_Portrait})")
    int insertUser(WxUser Users);
   @Update("UPDATE Users SET User_Name=#{userName}, User_Key=#{userKey}, Head_Portrait=#{headPortrait} WHERE User_ID=#{userId}")
    int updateById(Long User_ID);
}

