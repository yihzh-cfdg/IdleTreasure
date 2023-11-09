package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Messages;

import java.util.List;


@Mapper
public interface MessagesMapper {
    @Select("SELECT * FROM Messages")
    List<Messages> selectAll();

    @Delete("DELETE FROM Messages WHERE Message_ID=#{messageId}")
    int deleteById(Long messageId);

    @Insert("INSERT INTO Messages (Message_ID, Sender_ID, Receiver_ID, Message_Content, Time, IsRead) " +
            "VALUES(#{messageId}, #{senderId}, #{receiverId}, #{messageContent}, #{time}, #{isRead})")
    int insertMessage(Messages message);

    @Update("UPDATE Messages SET Sender_ID=#{senderId}, Receiver_ID=#{receiverId}, " +
            "Message_Content=#{messageContent}, Time=#{time}, IsRead=#{isRead} WHERE Message_ID=#{messageId}")
    int updateById(Messages message);
}