package edu.fzu.se.backend.mapper;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Evaluations;
import java.util.List;


@Mapper
public interface EvaluationsMapper {
    @Operation(summary = "获取所有评价记录", description = "查询数据库中的所有评价记录")
    @Select("SELECT * FROM Evaluations")
    List<Evaluations> selectAll();

    @Operation(summary = "根据交易ID查询评价记录", description = "根据交易ID查询数据库中的评价记录")
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE e.Trade_ID = #{Trade_ID}")
    Evaluations selectByTradeId(Long Trade_ID);

    @Operation(summary = "根据卖家ID查询评价记录", description = "根据卖家ID查询数据库中的评价记录")
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Seller_ID = #{Seller_ID}")
    List<Evaluations> selectBySellerId(Long Seller_ID);

    @Operation(summary = "根据买家ID查询评价记录", description = "根据买家ID查询数据库中的评价记录")
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Buyer_ID = #{Buyer_ID}")
    List<Evaluations> selectByBuyerId(Long Buyer_ID);

    @Operation(summary = "根据卖家ID和买家ID查询评价记录", description = "根据卖家ID和买家ID查询数据库中的评价记录")
    @Select("SELECT e.* FROM Evaluations e " +
            "LEFT JOIN Trades t ON e.Trade_ID = t.Trade_ID " +
            "WHERE t.Buyer_ID = #{Buyer_ID} AND t.Seller_ID = #{Seller_ID}")
    List<Evaluations> selectByBuyerAndSellerId(Long Buyer_ID, Long Seller_ID);

    @Operation(summary = "根据交易ID删除评价记录", description = "根据交易ID从数据库中删除评价记录")
    @Delete("DELETE FROM Evaluations WHERE Trade_ID=#{Trade_ID}")
    int deleteById(Long Trade_ID);

    @Operation(summary = "插入评价记录", description = "向数据库中插入新的评价记录")
    @Insert("INSERT INTO Evaluations (Trade_ID, Buyer_Evaluation, Seller_Evaluation,Buyer_Status,Seller_Status) " +
            "VALUES(#{Trade_ID}, #{Buyer_Evaluation}, #{Seller_Evaluation},#{Buyer_Status},#{Seller_Status})")
    int insertFullEvaluation(Evaluations evaluation);

    @Operation(summary = "根据交易ID更新评价记录", description = "根据交易ID更新数据库中的评价记录")
    @Update("UPDATE Evaluations SET Buyer_Evaluation=#{Buyer_Evaluation}, Seller_Evaluation=#{Seller_Evaluation} " +
            "WHERE Trade_ID=#{Trade_ID}")
    int updateById(Evaluations evaluation);
    @Insert("INSERT INTO Evaluations (Trade_ID) " +
            "VALUES(#{Trade_ID})")
    int insertEvaluation(Long Trade_ID);

    //更新买家评价
    @Update("UPDATE Evaluations SET Buyer_Evaluation=#{Buyer_Evaluation}, Buyer_Status=#{Buyer_Status} WHERE Trade_ID=#{Trade_ID}")
    int updateBuyerEvaluation(Evaluations evaluation);

    //更新卖家评价
    @Update("UPDATE Evaluations SET Seller_Evaluation=#{Seller_Evaluation}, Seller_Status=#{Seller_Status} WHERE Trade_ID=#{Trade_ID}")
    int updateSellerEvaluation(Evaluations evaluation);

    //查询买家是否评价过该交易
    @Select("SELECT Buyer_Status FROM Evaluations WHERE Trade_ID=#{Trade_ID}")
    String selectBuyerStatusById(Long Trade_ID);

    //查询卖家是否评价过该交易
    @Select("SELECT Seller_Status FROM Evaluations WHERE Trade_ID=#{Trade_ID}")
    String selectSellerStatusById(Long Trade_ID);

}
