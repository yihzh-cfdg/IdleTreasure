package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;

import edu.fzu.se.backend.bean.Trades;
//import edu.fzu.se.mybatisplus.core.mapper.BaseMapper;
//import edu.fzu.se.web.wx_user.entity.Trades;
//import scala.collection.immutable.List;
import java.util.List;

@Mapper
public interface TradesMapper {
    @Operation(summary = "获取所有交易记录")
    @Select("SELECT * FROM Trades")
    List<Trades> selectAll();

    @Operation(summary = "根据交易ID查询交易记录")
    @Select("SELECT * FROM Trades WHERE Trade_ID = #{Trade_ID}")
    Trades selectById(Long Trade_ID);

    @Operation(summary = "根据买家和卖家ID查询交易记录")
    @Select("SELECT * FROM Trades WHERE Buyer_ID = #{Buyer_ID} AND Seller_ID = #{Seller_ID}")
    List<Trades> selectByBuyerAndSellerIds(Long Buyer_ID, Long Seller_ID);

    @Operation(summary = "根据交易ID删除交易记录")
    @Delete("DELETE FROM Trades WHERE Trade_ID=#{Trade_ID}")
    int deleteById(Long Trade_ID);

    @Operation(summary = "插入交易记录")
    @Insert("INSERT INTO Trades (Trade_ID, Buyer_ID, Seller_ID, Trade_Time, Paid_Amount,Delivery_Address,Shipping_Address) " +
            "VALUES(#{Trade_ID}, #{Buyer_ID}, #{Seller_ID}, #{Trade_Time}, #{Paid_Amount}, #{Delivery_Address},#{Shipping_Address})")
    int insertTrade(Trades trade);

    @Operation(summary = "根据交易ID更新交易记录")
    @Update("UPDATE Trades SET Trade_Time=#{Trade_Time}, Paid_Amount=#{Paid_Amount}, Delivery_Address=#{Delivery_Address},Shipping_Address=#{Shipping_Address} WHERE Trade_ID=#{Trade_ID}")
    int updateById(Trades trade);
}