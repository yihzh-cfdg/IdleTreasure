package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.Evaluations;

import java.util.List;
import java.util.Map;

public interface EvaluationsService {
    //根据交易ID查询评价记录
    Evaluations getEvaluationByTradeId(Long tradeId);
    //根据卖家ID查询评价记录
    List<Evaluations> getEvaluationsBySellerId(Long sellerId);
    //根据买家ID查询评价记录
    List<Evaluations> getEvaluationsByBuyerId(Long buyerId);
    //根据卖家ID和买家ID查询评价记录
    Map<String, String> getEvaluationsByBuyerAndSellerId(Long Trade_ID);
    //根据交易ID删除评价记录
    int deleteEvaluationById(Long tradeId);

    int updateBuyerEvaluation(String Buyer_Evaluation, String Buyer_Status, Long Trade_ID);

    int updateSellerEvaluation(String Seller_Evaluation, String Seller_Status, Long Trade_ID);

    Map<String, String> getSellerEvaluation(Long tradeId);

    Map<String, String> getBuyerEvaluation(Long tradeId);
}
