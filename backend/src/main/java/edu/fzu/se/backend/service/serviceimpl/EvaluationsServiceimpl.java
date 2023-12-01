package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.bean.WxUser;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.mapper.TradesMapper;
import edu.fzu.se.backend.mapper.WxUserMapper;
import edu.fzu.se.backend.service.EvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EvaluationsServiceimpl implements EvaluationsService {
    @Autowired
    private EvaluationsMapper evaluationsMapper;
    @Autowired
    private TradesMapper tradesMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private GoodsMapper goodsMapper;
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
    public Map<String, String> getEvaluationsByBuyerAndSellerId(Long tradeId) {
        Map<String, String> result = new HashMap<>();
        Trades trades = tradesMapper.selectById(tradeId);
        WxUser seller = wxUserMapper.selectById(trades.getSeller_ID());
        result.put("sellerName", seller.getUser_Name());
        result.put("sellerAvatar", seller.getHead_Portrait());
        result.put("sellerEvaluate", evaluationsMapper.selectByTradeId(tradeId).getSeller_Evaluation());
        result.put("sellerMark", evaluationsMapper.selectByTradeId(tradeId).getSeller_Status());
        WxUser buyer = wxUserMapper.selectById(trades.getBuyer_ID());
        result.put("buyerName", buyer.getUser_Name());
        result.put("buyerAvatar", buyer.getHead_Portrait());
        result.put("buyerEvaluate", evaluationsMapper.selectByTradeId(tradeId).getBuyer_Evaluation());
        result.put("buyerMark", evaluationsMapper.selectByTradeId(tradeId).getBuyer_Status());
        result.put("image", goodsMapper.getImages(trades.getGoods_ID()).get(0).getImage_Data());
        result.put("name", goodsMapper.selectById(trades.getGoods_ID()).getGoods_Name());
        result.put("price", trades.getPaid_Amount().toString());
        return result;
    }
    @Override
    public int deleteEvaluationById(Long tradeId) {
        return evaluationsMapper.deleteById(tradeId);
    }

    @Override
    public int updateBuyerEvaluation(String Buyer_Evaluation, String Buyer_Status, Long Trade_ID) {
        Evaluations evaluation=new Evaluations();
        evaluation.setBuyer_Evaluation(Buyer_Evaluation);
        evaluation.setBuyer_Status(Buyer_Status);
        evaluation.setTrade_ID(Trade_ID);
        Trades trade = tradesMapper.selectById(Trade_ID);
        if(trade == null)
            throw new RuntimeException("交易记录不存在");
        trade.setTransaction_Status("已评价");
        tradesMapper.updateStatusById(trade);
        return evaluationsMapper.updateBuyerEvaluation(evaluation);
    }

    @Override
    public int updateSellerEvaluation(String Seller_Evaluation, String Seller_Status, Long Trade_ID) {
        Evaluations evaluation=new Evaluations();
        evaluation.setSeller_Evaluation(Seller_Evaluation);
        evaluation.setSeller_Status(Seller_Status);
        evaluation.setTrade_ID(Trade_ID);
        Trades trade = tradesMapper.selectById(Trade_ID);
        if(trade == null)
            throw new RuntimeException("交易记录不存在");
        trade.setTransaction_Status("已评价");
        tradesMapper.updateStatusById(trade);
        return evaluationsMapper.updateSellerEvaluation(evaluation);
    }

    @Override
    public Map<String, String> getSellerEvaluation(Long tradeId){
        Map<String, String> result = new HashMap<>();
        Trades trades = tradesMapper.selectById(tradeId);
        WxUser seller = wxUserMapper.selectById(trades.getSeller_ID());
        result.put("sellerName", seller.getUser_Name());
        result.put("sellerAvatar", seller.getHead_Portrait());
        result.put("sellerEvaluate", evaluationsMapper.selectByTradeId(tradeId).getSeller_Evaluation());
        result.put("sellerMark", evaluationsMapper.selectByTradeId(tradeId).getSeller_Status());
        return result;
    }

    @Override
    public Map<String, String> getBuyerEvaluation(Long tradeId){
        Map<String, String> result = new HashMap<>();
        Trades trades = tradesMapper.selectById(tradeId);
        WxUser buyer = wxUserMapper.selectById(trades.getBuyer_ID());
        result.put("buyerName", buyer.getUser_Name());
        result.put("buyerAvatar", buyer.getHead_Portrait());
        result.put("buyerEvaluate", evaluationsMapper.selectByTradeId(tradeId).getBuyer_Evaluation());
        result.put("buyerMark", evaluationsMapper.selectByTradeId(tradeId).getBuyer_Status());
        return result;
    }
}
