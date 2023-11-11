package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Messages;

import java.util.List;


@Mapper
public interface MessagesMapper {
    @Select("SELECT * FROM Messages")
    List<Messages> selectAll();
    //新增按消息ID查询、按发送者和接收者ID查询（查询一位用户对另一位用户的消息记录）
    @Select("SELECT * FROM Messages WHERE Message_ID = #{messageId}")
    Messages selectById(Long messageId);

    @Select("SELECT * FROM Messages WHERE Sender_ID = #{senderId} AND Receiver_ID = #{receiverId}")
    List<Messages> selectBySenderAndReceiverIds(Long senderId, Long receiverId);

    @Delete("DELETE FROM Messages WHERE Message_ID=#{messageId}")
    int deleteById(Long messageId);

    @Insert("INSERT INTO Messages (Message_ID, Sender_ID, Receiver_ID, Message_Content, Time, IsRead) " +
            "VALUES(#{messageId}, #{senderId}, #{receiverId}, #{messageContent}, #{time}, #{isRead})")
    int insertMessage(Messages message);

    @Update("UPDATE Messages SET Sender_ID=#{senderId}, Receiver_ID=#{receiverId}, " +
            "Message_Content=#{messageContent}, Time=#{time}, IsRead=#{isRead} WHERE Message_ID=#{messageId}")
    int updateById(Messages message);
}