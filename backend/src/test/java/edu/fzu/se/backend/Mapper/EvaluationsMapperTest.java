package edu.fzu.se.backend.Mapper;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EvaluationsMapperTest {

    @Autowired
    EvaluationsMapper evaluationsMapper;
    @Test
    void selectByTradeId() {
        Long tradeId = 1000000002L;
        Evaluations evaluations;
        evaluations = evaluationsMapper.selectByTradeId(tradeId);
        //assertNotNull(evaluations);
        assertEquals(tradeId, evaluations.getTrade_ID());
    }
}