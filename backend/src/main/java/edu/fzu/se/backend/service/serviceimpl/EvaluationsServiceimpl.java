package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
import edu.fzu.se.backend.service.EvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationsServiceimpl implements EvaluationsService {
    @Autowired
    private EvaluationsMapper evaluationsMapper;
    @Override
    public List<Evaluations> getAllEvaluations() {
        return evaluationsMapper.selectAll();
    }
    @Override
    public Evaluations getEvaluationByTradeId(Long tradeId) {
        return evaluationsMapper.selectByTradeId(tradeId);
    }
    @Override
    public List<Evaluations> getEvaluationsBySellerId(Long sellerId) {
        return evaluationsMapper.selectBySellerId(sellerId);
    }
    @Override
    public List<Evaluations> getEvaluationsByBuyerId(Long buyerId) {
        return evaluationsMapper.selectByBuyerId(buyerId);
    }
    @Override
    public List<Evaluations> getEvaluationsByBuyerAndSellerId(Long buyerId, Long sellerId) {
        return evaluationsMapper.selectByBuyerAndSellerId(buyerId, sellerId);
    }
    @Override
    public int deleteEvaluationById(Long tradeId) {
        return evaluationsMapper.deleteById(tradeId);
    }
    @Override
    public int insertEvaluation(Evaluations evaluation) {
        return evaluationsMapper.insertEvaluation(evaluation);
    }
    @Override
    public int updateById(Evaluations evaluation){
        return evaluationsMapper.updateById(evaluation);
    }
}
