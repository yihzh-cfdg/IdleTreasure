package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
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
@SpringBootTest
class EvaluationsMapperTest {
    @Autowired
    private EvaluationsMapper evaluationsMapper;
    @Test
    void selectAll() {
        List<Evaluations> evaluations = evaluationsMapper.selectAll();
        assertNotNull(evaluations);
    }

    @Test
    void selectByTradeId() {
        Long tradeId = 1000000001L;
        Evaluations evaluations = evaluationsMapper.selectByTradeId(tradeId);
        assertNotNull(evaluations);
        assertEquals(tradeId, evaluations.getTrade_ID());
    }

    @Test
    void selectBySellerId() {
        Long sellerId = 100000005L;
        List<Evaluations> evaluations = evaluationsMapper.selectBySellerId(sellerId);
        assertNotNull(evaluations);
        assertTrue(evaluations.size() > 0);
    }

    @Test
    void selectByBuyerId() {
        Long buyerId = 100000002L;
        List<Evaluations> evaluations = evaluationsMapper.selectByBuyerId(buyerId);
        assertNotNull(evaluations);
        assertTrue(evaluations.size() > 0);
    }

    @Test
    void selectByBuyerAndSellerId() {
        Long buyerId = 100000005L;
        Long sellerId = 100000004L;
        List<Evaluations> evaluations = evaluationsMapper.selectByBuyerAndSellerId(buyerId, sellerId);
        assertNotNull(evaluations);
        assertTrue(evaluations.size() > 0);
    }

    @Test
    void deleteById() {
        Long tradeId = 1000000001L;
        int rowsAffected = evaluationsMapper.deleteById(tradeId);
        assertEquals(1, rowsAffected);
    }

    @Test
    void insertEvaluation() {
        Evaluations evaluation = new Evaluations();
        evaluation.setTrade_ID(1000000001L);
        evaluation.setBuyer_Evaluation("好评");
        evaluation.setSeller_Evaluation("非常好评");
        int rowsAffected = evaluationsMapper.insertEvaluation(evaluation);
        assertEquals(1, rowsAffected);
    }

    @Test
    void updateById() {
        Evaluations evaluation = new Evaluations();
        evaluation.setTrade_ID(1000000001L);
        evaluation.setBuyer_Evaluation("好评");
        evaluation.setSeller_Evaluation("非常好评");
        int rowsAffected = evaluationsMapper.updateById(evaluation);
        assertEquals(1, rowsAffected);
    }
}