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
    //根据买家ID查询交易记录（我买到的）
    @Select("SELECT * FROM Trades WHERE Buyer_ID = #{Buyer_ID}")
    List<Trades> selectByBuyerId(Long Buyer_ID);

    //根据买家ID查询交易记录数量（我买到的）
    @Select("SELECT COUNT(*) FROM Trades WHERE Buyer_ID = #{Buyer_ID}")
    Integer countByBuyerId(Long Buyer_ID);

    //根据卖家ID查询交易记录（我卖出的）
    @Select("SELECT * FROM Trades WHERE Seller_ID = #{Seller_ID}")
    List<Trades> selectBySellerId(Long Seller_ID);

    //根据卖家ID查询交易记录数量（我卖出的）
    @Select("SELECT COUNT(*) FROM Trades WHERE Seller_ID = #{Seller_ID}")
    Integer countBySellerId(Long Seller_ID);
//    以上为11.15更新
    //获取所有交易记录
    @Select("SELECT * FROM Trades")
    List<Trades> selectAll();

    //根据交易ID查询交易记录
    @Select("SELECT * FROM Trades WHERE Trade_ID = #{Trade_ID}")
    Trades selectById(Long Trade_ID);

    //根据买家和卖家ID查询交易记录
    @Select("SELECT * FROM Trades WHERE Buyer_ID = #{Buyer_ID} AND Seller_ID = #{Seller_ID}")
    List<Trades> selectByBuyerAndSellerIds(Long Buyer_ID, Long Seller_ID);

    //根据交易ID删除交易记录
    @Delete("DELETE FROM Trades WHERE Trade_ID=#{Trade_ID}")
    Integer deleteById(Long Trade_ID);

    //插入交易记录
    @Insert("INSERT INTO Trades (Buyer_ID, Seller_ID, Goods_ID, Trade_Time, Paid_Amount, Delivery_Address, Shipping_Address, Transaction_Number, Transaction_Status) " +
            "VALUES(#{Buyer_ID}, #{Seller_ID}, #{Goods_ID}, #{Trade_Time}, #{Paid_Amount}, #{Delivery_Address}, #{Shipping_Address}, #{Transaction_Number}, #{Transaction_Status})")
    @Options(useGeneratedKeys = true, keyProperty = "Trade_ID")
    @SelectKey(statement = "SELECT last_insert_id() FROM Trades LIMIT 1", before = false,
            resultType = Long.class, keyColumn = "Trade_ID", keyProperty = "Trade_ID")
    Long insertTrade(Trades trade);

    //根据交易ID更新交易记录
    @Update("UPDATE Trades SET Buyer_ID=#{Buyer_ID}, Seller_ID=#{Seller_ID}, Goods_ID=#{Goods_ID}, Trade_Time=#{Trade_Time}, Paid_Amount=#{Paid_Amount}, Delivery_Address=#{Delivery_Address}, Shipping_Address=#{Shipping_Address}, Transaction_Number=#{Transaction_Number}, Transaction_Status=#{Transaction_Status} WHERE Trade_ID=#{Trade_ID}")
    Integer updateById(Trades trade);

    //根据交易ID更新交易记录的交易状态
    @Update("UPDATE Trades SET Transaction_Status=#{Transaction_Status} WHERE Trade_ID=#{Trade_ID}")
    Integer updateStatusById(Trades trade);

    //根据卖家id查询卖家总成交金额
    @Select("SELECT SUM(Paid_Amount) FROM Trades WHERE Seller_ID = #{Seller_ID}")
    Double selectTotalAmountBySellerId(Long Seller_ID);

    //根据卖家id查询卖家成交单数
    @Select("SELECT COUNT(*) FROM Trades WHERE Seller_ID = #{Seller_ID}")
    Integer selectTotalCountBySellerId(Long Seller_ID);

    //根据买家id查询买家成交单数
    @Select("SELECT COUNT(*) FROM Trades WHERE Buyer_ID = #{Buyer_ID}")
    Integer selectTotalCountByBuyerId(Long Buyer_ID);
    
    //根据商品id查询是否有交易记录
    @Select("SELECT COUNT(*) FROM Trades WHERE Goods_ID = #{Goods_ID}")
    Integer selectTotalCountByGoodsId(Long Goods_ID);

    //根据商品id查询交易记录
    @Select("SELECT * FROM Trades WHERE Goods_ID = #{Goods_ID}")
    List<Trades> selectByGoodsId(Long Goods_ID);
}