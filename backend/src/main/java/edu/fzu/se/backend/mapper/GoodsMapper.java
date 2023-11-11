package edu.fzu.se.backend.mapper;

import org.apache.ibatis.annotations.*;


import edu.fzu.se.backend.bean.Goods;

import java.util.List;


@Mapper
public interface GoodsMapper{
    @Select("SELECT * FROM Goods")
    List<Goods> selectAll();
    
    @Delete("DELETE FROM Goods WHERE Goods_ID=#{goodsId}")
    int deleteById(Long goodsId);
    //新增selectById根据商品ID查询商品记录
    @Select("SELECT * FROM Goods WHERE Goods_ID = #{goodsId}")
    Goods selectById(Long goodsId);
//    selectBySellerId根据卖家ID查询商品记录（卖家卖的东西）
    @Select("SELECT * FROM Goods WHERE Seller_ID = #{sellerId}")
    List<Goods> selectBySellerId(Long sellerId);
//    selectByPriceRange根据商品价格范围查询商品记录。
    @Select("SELECT * FROM Goods WHERE Goods_Price BETWEEN #{minPrice} AND #{maxPrice}")
    List<Goods> selectByPriceRange(int minPrice, int maxPrice);
//    selectByKeyword，使用LIKE关键字和CONCAT函数来实现模糊匹配查询，如如查询“书本”，商品名称为“语文书本”和“书本二手”的记录都会被查询到。
    @Select("SELECT * FROM Goods WHERE Goods_Name LIKE CONCAT('%', #{keyword}, '%')")
    List<Goods> selectByKeyword(String keyword);
//  selectGoodsByUserName方法，传入用户名来获取对应用户售卖的商品记录，通过用户ID进行连接查询
    @Select("SELECT g.* FROM Goods g JOIN Users u ON g.Seller_ID = u.User_ID WHERE u.User_Name = #{userName}")
    List<Goods> selectGoodsByUserName(String userName);
    @Insert("INSERT INTO Goods (Goods_Name, Seller_ID, Goods_Price, Goods_Description, Classification, Release_Time, Goods_Pictures) " +
            "VALUES(#{goodsName}, #{sellerId}, #{goodsPrice}, #{goodsDescription}, #{classification}, #{releaseTime}, #{goodsPictures})")
    int insertGood(Goods goods);
    
    @Update("UPDATE Goods SET Goods_Name=#{goodsName}, Goods_Price=#{goodsPrice}, " +
            "Goods_Description=#{goodsDescription}, Classification=#{classification}, Release_Time=#{releaseTime}, " +
            "Goods_Pictures=#{goodsPictures} WHERE Goods_ID=#{goodsId}")
    int updateById(Goods goods);
}
