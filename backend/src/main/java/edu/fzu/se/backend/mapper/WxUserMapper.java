package edu.fzu.se.backend.mapper;

import edu.fzu.se.backend.bean.WxUser;
import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
@Mapper
public interface WxUserMapper {
    @Operation(summary = "获取所有用户")
    @Select("SELECT * FROM Users")
    List<WxUser> selectAll();

    @Operation(summary = "根据用户ID查询用户")
    @Select("SELECT * FROM Users WHERE User_ID=#{userId}")
    WxUser selectById(Long userId);

    @Operation(summary = "根据学号查询用户")
    @Select("SELECT * FROM Users WHERE FZU_Key=#{FzuKey}")
    WxUser selectByFzuKey(String FzuKey);

    @Operation(summary = "根据关键字查询用户")
    @Select("SELECT * FROM Users WHERE User_Name LIKE CONCAT('%', #{keyword}, '%')")
    List<WxUser> selectByKeyword(String keyword);

    @Operation(summary = "根据用户ID删除用户")
    @Delete("DELETE FROM Users WHERE User_ID=#{userId}")
    int deleteById(Long User_ID);

    @Operation(summary = "插入用户")
    @Insert("INSERT INTO Users(User_Name, User_Key, FZU_Key, Head_Portrait, Delivery_Address,Shipping_Address) " +
            "VALUES(#{User_Name}, #{User_Key}, #{FZU_Key}, #{Head_Portrait}, #{Delivery_Address},#{Shipping_Address})")
    int insertUser(WxUser user);

    @Operation(summary = "根据用户ID更新用户信息")
    @Update("UPDATE Users SET User_Name=#{User_Name}, User_Key=#{User_Key},FZU_Key=#{FZU_Key}, Head_Portrait=#{Head_Portrait}, Delivery_Address=#{Delivery_Address},Shipping_Address=#{Shipping_Address} WHERE User_ID=#{User_ID}")
    int updateById(WxUser user);
}