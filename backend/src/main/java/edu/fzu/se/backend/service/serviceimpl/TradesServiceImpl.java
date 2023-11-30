package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.bean.WxUser;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.mapper.TradesMapper;
import edu.fzu.se.backend.mapper.WxUserMapper;
import edu.fzu.se.backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TradesServiceImpl implements TradesService {
    @Autowired
    TradesMapper tradesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    WxUserMapper wxUserMapper;
    @Override
    public void addTrade(Trades trades){
        WxUser buyer = wxUserMapper.selectById(trades.getBuyer_ID());
        WxUser seller = wxUserMapper.selectById(goodsMapper.selectById(trades.getGoods_ID()).getSeller_ID());
        trades.setSeller_ID(seller.getUser_ID());
        trades.setDelivery_Address(buyer.getDelivery_Address());
        trades.setShipping_Address(seller.getShipping_Address());
        tradesMapper.insertTrade(trades);
    }

}
