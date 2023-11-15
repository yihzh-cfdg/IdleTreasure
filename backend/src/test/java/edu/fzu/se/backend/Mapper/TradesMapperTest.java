package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.mapper.TradesMapper;
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
class TradesMapperTest {

    @Autowired
    private TradesMapper tradesMapper;

    @Test
    void selectAll() {
        List<Trades> trades = tradesMapper.selectAll();
        assertNotNull(trades);
    }

    @Test
    void selectById() {
        Long tradeId = 1000000001L;
        Trades trade = tradesMapper.selectById(tradeId);
        assertNotNull(trade);
        assertEquals(tradeId, trade.getTrade_ID());
    }

    @Test
    void selectByBuyerAndSellerIds() {
        Long buyerId = 100000002L;
        Long sellerId = 100000001L;
        List<Trades> trades = tradesMapper.selectByBuyerAndSellerIds(buyerId, sellerId);
        assertNotNull(trades);
    }

    @Test
    void deleteById() {
        Long tradeId = 1000000006L;
        int result = tradesMapper.deleteById(tradeId);
        assertEquals(1, result);
    }

    @Test
    void insertTrade() {
        Trades trade = new Trades();
        trade.setTrade_ID(1000000006L);
        trade.setBuyer_ID(100000001L);
        trade.setSeller_ID(100000005L);
        trade.setTrade_Time("2022-11-11 00:00:00");
        trade.setPaid_Amount(100.0);
        int result = tradesMapper.insertTrade(trade);
        assertEquals(1, result);
    }

    @Test
    void updateById() {
        Trades trade = new Trades();
        trade.setTrade_ID(1000000005L);
        trade.setTrade_Time("2022-11-11 00:00:00");
        trade.setPaid_Amount(60.0);
        int result = tradesMapper.updateById(trade);
        assertEquals(1, result);
    }
}
