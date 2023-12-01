package edu.fzu.se.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fzu.se.backend.bean.WxUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface WxUserMapper  extends BaseMapper<WxUser>{
    //获取所有用户
    @Select("SELECT * FROM Users")
    List<WxUser> selectAll();

    //根据用户ID查询用户
    @Select("SELECT * FROM Users WHERE User_ID=#{User_ID}")
    WxUser selectById(Long User_ID);

    //根据学号查询用户
    @Select("SELECT * FROM Users WHERE FZU_Key=#{FzuKey}")
    WxUser selectByFzuKey(String FzuKey);

    //根据用户名模糊匹配查询用户
    @Select("SELECT User_ID, User_Key FROM users WHERE MATCH(User_Name) AGAINST(#{username} IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)")
    WxUser searchByUserName(String username);

    //根据用户名精准查询用户
    @Select("SELECT * FROM users WHERE User_Name LIKE #{username}")
    WxUser selectByUserName(String username);

    //根据关键字查询用户
    @Select("SELECT * FROM Users WHERE User_Name LIKE CONCAT('%', #{keyword}, '%')")
    List<WxUser> selectByKeyword(String keyword);

    //根据用户ID删除用户
    @Delete("DELETE FROM Users WHERE User_ID=#{User_ID}")
    int deleteById(Long User_ID);

    //插入用户
    @Insert("INSERT INTO Users(User_Name, User_Key, FZU_Key, Delivery_Address, Shipping_Address, Like_Count, Beliked_Count, phone, recipient) " +
            "VALUES(#{User_Name}, #{User_Key}, #{FZU_Key}, #{Delivery_Address}, #{Shipping_Address}, #{Like_Count}, #{Beliked_Count}, #{phone}, #{recipient})")
    int insertUser(WxUser user);

    //根据用户ID更新用户信息
    @Update("UPDATE Users SET User_Name=#{User_Name}, User_Key=#{User_Key}, FZU_Key=#{FZU_Key}, " +
            "Head_Portrait=#{Head_Portrait}, Delivery_Address=#{Delivery_Address}, Shipping_Address=#{Shipping_Address}, " +
            "Like_Count=#{Like_Count}, Beliked_Count=#{Beliked_Count}, /*Transaction_Count=#{Transaction_Count},Good_Review_Count=#{Good_Review_Count},Good_Review_Rate=#{Good_Review_Rate},*/phone=#{phone},recipient=#{recipient} " +
            "WHERE User_ID=#{User_ID}")
    int updateById(WxUser user);

    //根据用户ID更新Like_Count
    @Update("UPDATE Users SET Like_Count=#{Like_Count} WHERE User_ID=#{User_ID}")
    int updateLikeCountById(String User_ID, int Like_Count);

    //根据用户ID更新Beliked_Count
    @Update("UPDATE users SET Beliked_Count=#{Beliked_Count} WHERE User_ID=#{User_ID}")
    int updateBelikedCountById(String User_ID, int Beliked_Count);

    //根据ID更新头像URL
    @Update("UPDATE users SET Head_Portrait=#{Header} WHERE User_ID=#{User_ID}")
    int updateHeaderById(String User_ID, String Header);

    //根据手机号查询用户
    @Select("SELECT * FROM users WHERE phone=#{phone}")
    WxUser selectByPhone(String phone);

    //根据用户ID更新用户名
    @Update("UPDATE users SET User_Name=#{User_Name} WHERE User_ID=#{User_ID}")
    Integer updateNameById(Long User_ID, String User_Name);

    //修改收件人
    @Update("UPDATE users SET recipient=#{recipient} WHERE User_ID=#{User_ID}")
    Integer updateRecipientById(Long User_ID, String recipient);

    //修改电话号码
    @Update("UPDATE users SET phone=#{phone} WHERE User_ID=#{User_ID}")
    Integer updatePhoneById(Long User_ID, String phone);
    //修改地址
    @Update("UPDATE users SET Delivery_Address=#{Address},Shipping_Address=#{Address} WHERE User_ID=#{User_ID}")
    Integer updateAddressById(Long User_ID, String Address);
}