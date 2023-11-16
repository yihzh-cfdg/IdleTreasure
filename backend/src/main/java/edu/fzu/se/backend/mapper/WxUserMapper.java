package edu.fzu.se.backend.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fzu.se.backend.bean.WxUser;
import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
@Mapper
public interface WxUserMapper  extends BaseMapper<WxUser>{
    @Operation(summary = "获取所有用户")
    @Select("SELECT * FROM Users")
    List<WxUser> selectAll();

    @Operation(summary = "根据用户ID查询用户")
    @Select("SELECT * FROM Users WHERE User_ID=#{User_ID}")
    WxUser selectById(Long User_ID);

    @Operation(summary = "根据学号查询用户")
    @Select("SELECT * FROM Users WHERE FZU_Key=#{FzuKey}")
    WxUser selectByFzuKey(String FzuKey);

    @Operation(summary = "根据关键字查询用户")
    @Select("SELECT * FROM Users WHERE User_Name LIKE CONCAT('%', #  {keyword}, '%')")
    List<WxUser> selectByKeyword(String keyword);

    @Operation(summary = "根据用户ID删除用户")
    @Delete("DELETE FROM Users WHERE User_ID=#{User_ID}")
    int deleteById(Long User_ID);

    @Operation(summary = "插入用户")
    @Insert("INSERT INTO Users(User_Name, User_Key, FZU_Key, Head_Portrait, Delivery_Address, Shipping_Address, Like_Count, Beliked_Count) " +
            "VALUES(#{User_Name}, #{User_Key}, #{FZU_Key}, #{Head_Portrait}, #{Delivery_Address}, #{Shipping_Address}, #{Like_Count}, #{Beliked_Count})")
    int insertUser(WxUser user);

    @Operation(summary = "根据用户ID更新用户信息")
    @Update("UPDATE Users SET User_Name=#{User_Name}, User_Key=#{User_Key}, FZU_Key=#{FZU_Key}, Head_Portrait=#{Head_Portrait}, Delivery_Address=#{Delivery_Address}, Shipping_Address=#{Shipping_Address}, Like_Count=#{Like_Count}, Beliked_Count=#{Beliked_Count} WHERE User_ID=#{User_ID}")
    int updateById(WxUser user);
    @Operation(summary = "根据用户ID更新Like_Count")
    @Update("UPDATE Users SET Like_Count=#{Like_Count} WHERE User_ID=#{User_ID}")
    int updateLikeCountById(@Param("User_ID") String User_ID, @Param("Like_Count") int Like_Count);

    @Operation(summary = "根据用户ID更新Beliked_Count")
    @Update("UPDATE Users SET Beliked_Count=#{Beliked_Count} WHERE User_ID=#{User_ID}")
    int updateBelikedCountById(@Param("User_ID") String User_ID, @Param("Beliked_Count") int Beliked_Count);
}