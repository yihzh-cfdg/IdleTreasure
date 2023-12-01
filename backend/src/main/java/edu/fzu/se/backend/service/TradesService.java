package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.Trades;

import java.util.List;
import java.util.Map;

public interface TradesService {
    public void addTrade(Trades trades);
    List<Map<String, String>> getTradesByPublisherID(Long publisherID);
    List<Map<String, String>> getTradesByBuyerID(Long buyerID);
    List<Map<String, String>> getTradesBySellerID(Long sellerID);
    //修改订单状态
    boolean updateStatusById(Long tradeID, String status);

    Map<String, String> getTradeByID(Long tradeID, Long userID);
}
