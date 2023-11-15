package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.Evaluations;

import java.util.List;

public interface EvaluationsService {
    //获取所有评价记录
    List<Evaluations> getAllEvaluations();
    //根据交易ID查询评价记录
    Evaluations getEvaluationByTradeId(Long tradeId);
    //根据卖家ID查询评价记录
    List<Evaluations> getEvaluationsBySellerId(Long sellerId);
    //根据买家ID查询评价记录
    List<Evaluations> getEvaluationsByBuyerId(Long buyerId);
    //根据卖家ID和买家ID查询评价记录
    List<Evaluations> getEvaluationsByBuyerAndSellerId(Long buyerId, Long sellerId);
    //根据交易ID删除评价记录
    int deleteEvaluationById(Long tradeId);
    //插入评价记录
    int insertEvaluation(Evaluations evaluation);
    //根据交易ID更新评价记录
    int updateById(Evaluations evaluation);
}
