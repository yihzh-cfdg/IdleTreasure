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
    
    @Insert("INSERT INTO Goods (Goods_Name, Seller_ID, Goods_Price, Goods_Description, Classification, Release_Time, Goods_Pictures) " +
            "VALUES(#{goodsName}, #{sellerId}, #{goodsPrice}, #{goodsDescription}, #{classification}, #{releaseTime}, #{goodsPictures})")
    int insertGood(Goods goods);
    
    @Update("UPDATE Goods SET Goods_Name=#{goodsName}, Goods_Price=#{goodsPrice}, " +
            "Goods_Description=#{goodsDescription}, Classification=#{classification}, Release_Time=#{releaseTime}, " +
            "Goods_Pictures=#{goodsPictures} WHERE Goods_ID=#{goodsId}")
    int updateById(Goods goods);
}
