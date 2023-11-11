package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Trades;
//import edu.fzu.se.mybatisplus.core.mapper.BaseMapper;
//import edu.fzu.se.web.wx_user.entity.Trades;
//import scala.collection.immutable.List;
import java.util.List;

@Mapper
public interface TradesMapper {
    @Select("SELECT * FROM Trades")
    List<Trades> selectAll();
    //新增按交易ID查询、按买家和卖家ID查询（查询该买家向同一位卖家进行的交易记录）
    @Select("SELECT * FROM Trades WHERE Trade_ID = #{tradeId}")
    Trades selectById(Long tradeId);

    @Select("SELECT * FROM Trades WHERE Buyer_ID = #{buyerId} AND Seller_ID = #{sellerId}")
    List<Trades> selectByBuyerAndSellerIds(Long buyerId, Long sellerId);
    @Delete("DELETE FROM Trades WHERE Trade_ID=#{tradeId}")
    int deleteById(Long tradeId);
    
    @Insert("INSERT INTO Trades (Trade_ID, Buyer_ID, Seller_ID, Trade_Time, Paid_Amount) " +
            "VALUES(#{tradeId}, #{buyerId}, #{sellerId}, #{tradeTime}, #{paidAmount})")
    int insertTrade(Trades trade);
    
    @Update("UPDATE Trades SET Trade_Time=#{tradeTime}, Paid_Amount=#{paidAmount} WHERE Trade_ID=#{tradeId}")
    int updateById(Trades trade);
}
