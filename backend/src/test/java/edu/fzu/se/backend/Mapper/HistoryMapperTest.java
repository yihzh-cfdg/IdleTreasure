package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.History;
import edu.fzu.se.backend.mapper.HistoryMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.Configuration;


@SpringBootTest
class HistoryMapperTest {
    @Autowired
    private HistoryMapper historyMapper;

    @Test
    void selectAll() {
        List<History> histories =historyMapper.selectAll();
        assertNotNull(histories);
        assertTrue(!histories.isEmpty());

    }

    @Test
    void selectById() {
        Long searchID = 1000000001L;
        History history = historyMapper.selectById(searchID);
        assertNotNull(history);
        assertEquals(searchID,history.getSearch_ID());
    }

    @Test
    void deleteById() {
        Long searchID = 1000000014L;
        int result =historyMapper.deleteById(searchID);
        assertEquals(1,result);
    }

    @Test
    void insertHistory() {
        History history = new History();
        history.setUser_ID(100000005L);
        history.setSearch_ID(1000000016L);
        history.setKeyword("足球");
        int result = historyMapper.insertHistory(history);
        assertEquals(1,result);

    }

    @Test
    void updateById() {
        History history =new History();
        history.setSearch_ID(1000000001L);
        history.setUser_ID(100000002L);
        history.setKeyword("篮球");
        int result = historyMapper.updateById(history);
        assertEquals(1,result);
    }
}