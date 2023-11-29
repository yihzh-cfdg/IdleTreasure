package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;

import edu.fzu.se.backend.bean.Messages;

import java.util.List;


@Mapper
public interface MessagesMapper {
    @Operation(summary = "获取所有消息")
    @Select("SELECT * FROM Messages")
    List<Messages> selectAll();

    @Operation(summary = "根据消息ID查询消息")
    @Select("SELECT * FROM Messages WHERE Message_ID = #{Message_ID}")
    Messages selectById(Long Message_ID);

    @Operation(summary = "根据发送者和接收者ID查询消息记录")
    @Select("SELECT * FROM Messages WHERE Sender_ID = #{Sender_ID} AND Receiver_ID = #{Receiver_ID}")
    List<Messages> selectBySenderAndReceiverIds(Long Sender_ID, Long Receiver_ID);

    @Operation(summary = "根据发送者ID查询消息记录")
    @Select("SELECT * FROM Messages WHERE Sender_ID = #{Sender_ID}")
    List<Messages> selectBySenderId(Long Sender_ID);

    @Operation(summary = "根据接收者ID查询消息记录")
    @Select("SELECT * FROM Messages WHERE Sender_ID = #{Sender_ID} AND Receiver_ID = #{Receiver_ID}")
    List<Messages> selectByReceiverIds(Long Receiver_ID);
    @Operation(summary = "根据消息ID删除消息")
    @Delete("DELETE FROM Messages WHERE Message_ID=#{Message_ID}")
    int deleteById(Long Message_ID);

    @Operation(summary = "删除指定对话的最新一条消息记录")
    @Delete("DELETE FROM Messages WHERE Sender_ID = #{Sender_ID} AND Receiver_ID = #{Receiver_ID} ORDER BY Message_ID DESC LIMIT 1")
    void deleteFirstMessage(@Param("Sender_ID") Long Sender_ID, @Param("Receiver_ID") Long Receiver_ID);
    
    @Operation(summary = "插入消息")
    @Insert("INSERT INTO Messages (Message_ID, Sender_ID, Receiver_ID, Message_Content, Time, IsRead) " +
            "VALUES(#{Message_ID}, #{Sender_ID}, #{Receiver_ID}, #{Message_Content}, #{Time}, #{IsRead})")
    int insertMessage(Messages message);

    @Operation(summary = "根据消息ID更新消息")
    @Update("UPDATE Messages SET Sender_ID=#{Sender_ID}, Receiver_ID=#{Receiver_ID}, " +
            "Message_Content=#{Message_Content}, Time=#{Time}, IsRead=#{IsRead} WHERE Message_ID=#{Message_ID}")
    int updateById(Messages message);
}