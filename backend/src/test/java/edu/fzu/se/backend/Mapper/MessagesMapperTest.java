package edu.fzu.se.backend.Mapper;
import  edu.fzu.se.backend.bean.Messages;
import edu.fzu.se.backend.mapper.MessagesMapper;
import edu.fzu.se.backend.mapper.ShoppingCartMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.Configuration;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessagesMapperTest {

    @Autowired
    private MessagesMapper messagesMapper;

    @Test
    void selectAll() {
        List<Messages> messages = messagesMapper.selectAll();
        assertNotNull(messages);
        assertTrue(!messages.isEmpty());
    }
    @Test
    void selectById() {
        Long Message_ID = 1000000001L;
        Messages message =messagesMapper.selectById(Message_ID);
        assertNotNull(message);

    }

    @Test
    void selectBySenderAndReceiverIds() {
        List<Messages> result = messagesMapper.selectBySenderAndReceiverIds(100000001L,100000002L);
        assertEquals(1, result.size());

    }

    @Test
    void deleteById() {
        Long Message_ID = 1000000006L;
        int result = messagesMapper.deleteById(Message_ID);
        assertEquals(1,result);

    }

    @Test
    void insertMessage() {
        Messages message = new Messages();
        message.setMessage_ID(1000000006L);
        message.setSender_ID(100000001L);
        message.setReceiver_ID(100000002L);
        message.setMessage_Content("你的铅笔还有吗？");
        message.setTime("2022-11-11 00:00:00");
        message.setIsRead(true);
        int result = messagesMapper.insertMessage(message);
        assertEquals(1,result);

    }

    @Test
    void updateById() {
        Messages message = new Messages();
        message.setMessage_ID(1000000007L);
        message.setSender_ID(100000002L);
        message.setReceiver_ID(100000001L);
        message.setMessage_Content("你的水彩笔还有吗？");
        message.setTime("2022-11-11 00:00:00");
        message.setIsRead(true);
        int result = messagesMapper.updateById(message);
        assertEquals(1,result);

    }
}
