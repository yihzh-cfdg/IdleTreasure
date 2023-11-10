package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Evaluations;

import java.util.List;


@Mapper
public interface EvaluationsMapper {
    @Select("SELECT * FROM Evaluations")
    List<Evaluations> selectAll();
//  根据交易ID进行评价记录的查询
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE e.Trade_ID = #{tradeId}")
    Evaluations selectByTradeId(Long tradeId);
//    只根据卖家ID进行评价记录的查询
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Seller_ID = #{sellerId}")
    List<Evaluations> selectBySellerId(Long sellerId);
//  只根据买家ID进行评价记录的查询
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Buyer_ID = #{buyerId}")
    List<Evaluations> selectByBuyerId(Long buyerId);

    //  selectByBuyerAndSellerId：根据卖家ID和买家ID进行评价记录的查询
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Buyer_ID = #{buyerId} AND t.Seller_ID = #{sellerId}")
    List<Evaluations> selectByBuyerAndSellerId(Long buyerId, Long sellerId);
    @Delete("DELETE FROM Evaluations WHERE Trade_ID=#{tradeId}")
    int deleteById(Long tradeId);

    @Insert("INSERT INTO Evaluations (Trade_ID, Buyer_Evaluation, Seller_Evaluation) " +
            "VALUES(#{tradeId}, #{buyerEvaluation}, #{sellerEvaluation})")
    int insertEvaluation(Evaluations evaluation);

    @Update("UPDATE Evaluations SET Buyer_Evaluation=#{buyerEvaluation}, Seller_Evaluation=#{sellerEvaluation} " +
            "WHERE Trade_ID=#{tradeId}")
    int updateById(Evaluations evaluation);
}
