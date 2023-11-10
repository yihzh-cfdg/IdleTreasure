package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Evaluations;

import java.util.List;


@Mapper
public interface EvaluationsMapper {
    @Select("SELECT * FROM Evaluations")
    List<Evaluations> selectAll();

    @Delete("DELETE FROM Evaluations WHERE Trade_ID=#{tradeId}")
    int deleteById(Long tradeId);

    @Insert("INSERT INTO Evaluations (Trade_ID, Buyer_Evaluation, Seller_Evaluation) " +
            "VALUES(#{tradeId}, #{buyerEvaluation}, #{sellerEvaluation})")
    int insertEvaluation(Evaluations evaluation);

    @Update("UPDATE Evaluations SET Buyer_Evaluation=#{buyerEvaluation}, Seller_Evaluation=#{sellerEvaluation} " +
            "WHERE Trade_ID=#{tradeId}")
    int updateById(Evaluations evaluation);
}
