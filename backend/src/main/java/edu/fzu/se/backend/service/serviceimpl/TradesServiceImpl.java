package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.*;
import edu.fzu.se.backend.mapper.*;
import edu.fzu.se.backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradesServiceImpl implements TradesService {
    @Autowired
    TradesMapper tradesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    Goods_Images_ConnsMapper goodsImagesConnsMapper;
    @Autowired
    ImagesMapper imagesMapper;
    @Autowired
    EvaluationsMapper evaluationsMapper;
    @Override
    public void addTrade(Trades trades){
        WxUser buyer = wxUserMapper.selectById(trades.getBuyer_ID());
        WxUser seller = wxUserMapper.selectById(goodsMapper.selectById(trades.getGoods_ID()).getSeller_ID());
        trades.setSeller_ID(seller.getUser_ID());
        trades.setDelivery_Address(buyer.getDelivery_Address());
        trades.setShipping_Address(seller.getShipping_Address());
        tradesMapper.insertTrade(trades);
        //增加对应的评价\
        evaluationsMapper.insertEvaluation(trades.getTrade_ID());
    }

    //获取我发布的
    @Override
    public List<Map<String, String>> getTradesByPublisherID(Long publisherID) {
        List<Goods> goodsList = goodsMapper.selectBySellerId(publisherID);
        List<Map<String, String>> res = new ArrayList<>();
        WxUser user = wxUserMapper.selectById(publisherID);
        Images header = null;
        for(Goods good: goodsList){
            Map<String, String> item = new HashMap<>();
            List<Goods_Images_Conns> imagesList = goodsImagesConnsMapper.selectByGoodsId(good.getGoods_ID());
            if(!imagesList.isEmpty()) {
                header = imagesMapper.selectById(imagesList.get(0).getImage_ID());
                item.put("productImage", header.getImage_Data());
                item.put("productPrice", good.getGoods_Price().toString());
                if (tradesMapper.selectTotalCountByGoodsId(good.getGoods_ID()) > 0) {
                    item.put("transactionStatus", "已售出");
                    item.put("tradesID", tradesMapper.selectByGoodsId(good.getGoods_ID()).get(0).getTrade_ID().toString());
                } else
                    item.put("transactionStatus", "未售出");
                item.put("orderid", good.getGoods_ID().toString());
                item.put("seller", user.getUser_Name());
                item.put("avatar", wxUserMapper.selectById(publisherID).getHead_Portrait());
                item.put("productDescription", good.getGoods_Name());
                item.put("type", "2");
                res.add(item);
            }
        }
        return res;
    }

    //获取我买到的
    public List<Map<String, String>> getTradesByBuyerID(Long buyerID){
        List<Trades> tr = tradesMapper.selectByBuyerId(buyerID);
        List<Map<String, String>> res = new ArrayList<>();
        for (Trades trade:tr) {
            Map<String, String> item = new HashMap<>();
            WxUser user = wxUserMapper.selectById(trade.getBuyer_ID());// 这里是买家
            Goods good = goodsMapper.selectById(trade.getGoods_ID());
            List<Goods_Images_Conns> imagesList = goodsImagesConnsMapper.selectByGoodsId(good.getGoods_ID());
            Images header =  imagesMapper.selectById(imagesList.get(0).getImage_ID());
            item.put("orderid", trade.getTrade_ID().toString());
            item.put("seller", user.getUser_Name());
            item.put("avatar", user.getHead_Portrait());
            item.put("productImage", header.getImage_Data());
            item.put("productPrice", trade.getPaid_Amount().toString());
            item.put("transactionStatus", trade.getTransaction_Status());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            item.put("tradetime", sdf.format(trade.getTrade_Time()));
            item.put("shipping", trade.getShipping_Address());
            item.put("delivery", trade.getDelivery_Address());
            item.put("productDescription", good.getGoods_Name());
            item.put("type", "1");
            res.add(item);
        }
        return res;
    }
    //获取我卖出的
    public List<Map<String, String>> getTradesBySellerID(Long sellerID){
        List<Trades> tr = tradesMapper.selectBySellerId(sellerID);
        List<Map<String, String>> res = new ArrayList<>();
        for (Trades trade:tr) {
            Map<String, String> item = new HashMap<>();
            WxUser user = wxUserMapper.selectById(trade.getSeller_ID()); // 这里是卖家
            Goods good = goodsMapper.selectById(trade.getGoods_ID());
            List<Goods_Images_Conns> imagesList = goodsImagesConnsMapper.selectByGoodsId(good.getGoods_ID());
            Images header =  imagesMapper.selectById(imagesList.get(0).getImage_ID());
            item.put("orderid", trade.getTrade_ID().toString());
            item.put("seller", user.getUser_Name());
            item.put("avatar", user.getHead_Portrait());
            item.put("productImage", header.getImage_Data());
            item.put("productPrice", trade.getPaid_Amount().toString());
            item.put("transactionStatus", trade.getTransaction_Status());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            item.put("tradetime", sdf.format(trade.getTrade_Time()));
            item.put("shipping", trade.getShipping_Address());
            item.put("delivery", trade.getDelivery_Address());
            item.put("productDescription", good.getGoods_Name());
            item.put("type", "3");
            res.add(item);
        }
        return res;
    }

    @Override
    public boolean updateStatusById(Long tradeID, String status) throws RuntimeException{
        Trades trade = tradesMapper.selectById(tradeID);
        if(trade == null)
            throw new RuntimeException("交易记录不存在");
        trade.setTransaction_Status(status);
        if(tradesMapper.updateStatusById(trade) < 0)
            throw new RuntimeException("更新交易状态失败");
        return true;
    }

    @Override
    public Map<String, String> getTradeByID(Long tradeID, Long userID) {
        // 如果userid和sellerid相同，就返回买家信息
        // 否则返回卖家信息
        WxUser user;
        String type;
        if(tradesMapper.selectById(tradeID).getSeller_ID().equals(userID)) {
            user = wxUserMapper.selectById(tradesMapper.selectById(tradeID).getBuyer_ID());
            type = "1";
        }
        else {
            user = wxUserMapper.selectById(tradesMapper.selectById(tradeID).getSeller_ID());
            type = "3";
        }
        Trades trade = tradesMapper.selectById(tradeID);
        Map<String, String> item = new HashMap<>();
        Goods good = goodsMapper.selectById(trade.getGoods_ID());
        List<Goods_Images_Conns> imagesList = goodsImagesConnsMapper.selectByGoodsId(good.getGoods_ID());
        Images header =  imagesMapper.selectById(imagesList.get(0).getImage_ID());
        item.put("orderid", trade.getTrade_ID().toString());
        item.put("seller", user.getUser_Name());
        item.put("avatar", user.getHead_Portrait());
        item.put("productImage", header.getImage_Data());
        item.put("productPrice", trade.getPaid_Amount().toString());
        item.put("transactionStatus", trade.getTransaction_Status());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        item.put("tradetime", sdf.format(trade.getTrade_Time()));
        item.put("shipping", trade.getShipping_Address());
        item.put("delivery", trade.getDelivery_Address());
        item.put("productDescription", good.getGoods_Name());
        item.put("type", type);
        return item;
    }
}
