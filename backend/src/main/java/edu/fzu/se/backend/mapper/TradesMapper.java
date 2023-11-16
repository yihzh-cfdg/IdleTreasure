package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.Trades;
//import edu.fzu.se.mybatisplus.core.mapper.BaseMapper;
//import edu.fzu.se.web.wx_user.entity.Trades;
//import scala.collection.immutable.List;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
@Mapper
public interface TradesMapper  extends BaseMapper<Goods>{
    @Operation(summary = "根据买家ID查询交易记录（我买到的）")
    @Select("SELECT * FROM Trades WHERE Buyer_ID = #{Buyer_ID}")
    List<Trades> selectByBuyerId(Long Buyer_ID);

    @Operation(summary = "根据买家ID查询交易记录数量（我买到的）")
    @Select("SELECT COUNT(*) FROM Trades WHERE Buyer_ID = #{Buyer_ID}")
    int countByBuyerId(Long Buyer_ID);

    @Operation(summary = "根据卖家ID查询交易记录（我卖出的）")
    @Select("SELECT * FROM Trades WHERE Seller_ID = #{Seller_ID}")
    List<Trades> selectBySellerId(Long Seller_ID);

    @Operation(summary = "根据卖家ID查询交易记录数量（我卖出的）")
    @Select("SELECT COUNT(*) FROM Trades WHERE Seller_ID = #{Seller_ID}")
    int countBySellerId(Long Seller_ID);
//    以上为11.15更新
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
    @Insert("INSERT INTO Trades (Buyer_ID, Seller_ID, Goods_ID, Trade_Time, Paid_Amount, Delivery_Address, Shipping_Address, Transaction_Number, Transaction_Status) " +
            "VALUES(#{Buyer_ID}, #{Seller_ID}, #{Goods_ID}, #{Trade_Time}, #{Paid_Amount}, #{Delivery_Address}, #{Shipping_Address}, #{Transaction_Number}, #{Transaction_Status})")
    int insertTrade(Trades trade);

    @Operation(summary = "根据交易ID更新交易记录")
    @Update("UPDATE Trades SET Buyer_ID=#{Buyer_ID}, Seller_ID=#{Seller_ID}, Goods_ID=#{Goods_ID}, Trade_Time=#{Trade_Time}, Paid_Amount=#{Paid_Amount}, Delivery_Address=#{Delivery_Address}, Shipping_Address=#{Shipping_Address}, Transaction_Number=#{Transaction_Number}, Transaction_Status=#{Transaction_Status} WHERE Trade_ID=#{Trade_ID}")
    int updateById(Trades trade);

    @Operation(summary = "根据交易ID更新交易记录的交易状态")
    @Update("UPDATE Trades SET Transaction_Status=#{Transaction_Status} WHERE Trade_ID=#{Trade_ID}")
    int updateStatusById(Trades trade);
}