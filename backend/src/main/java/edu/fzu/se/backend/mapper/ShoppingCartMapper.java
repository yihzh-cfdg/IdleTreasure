package edu.fzu.se.backend.mapper;

import edu.fzu.se.backend.bean.ShoppingCart;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface ShoppingCartMapper {

    @Operation(summary = "根据用户ID和商品ID查询购物车项")
    @Select("SELECT * FROM shoppingcart WHERE User_ID = #{User_ID} AND Goods_ID = #{Goods_ID}")
    ShoppingCart selectByUserIdAndGoodsId(@Param("User_ID") Long User_ID, @Param("Goods_ID") Long Goods_ID);

    @Operation(summary = "根据用户ID查询购物车列表")
    @Select("SELECT * FROM shoppingcart WHERE User_ID = #{User_ID}")
    List<ShoppingCart> selectByUserId(@Param("User_ID") Long User_ID);

    @Operation(summary = "插入新的用户和商品的关系到购物车表")
    @Insert("INSERT INTO shoppingcart (User_ID, Goods_ID, Count) " +
            "VALUES(#{User_ID}, #{Goods_ID}, #{Count})")
    int insertShoppingCart(ShoppingCart shoppingCart);

    @Operation(summary = "更新对应用户购物车中的对应商品数量")
    @Update("UPDATE shoppingcart SET Count = #{Count} " +
            "WHERE User_ID = #{User_ID} AND Goods_ID = #{Goods_ID}")
    int updateShoppingCartCount(ShoppingCart shoppingCart);

    @Operation(summary = "删除购物车项")
    @Delete("DELETE FROM shoppingcart WHERE User_ID = #{User_ID} AND Goods_ID = #{Goods_ID}")
    int deleteShoppingCart(@Param("User_ID") Long User_ID, @Param("Goods_ID") Long Goods_ID);
    @Operation(summary = "清空对应用户的购物车")
    @Delete("DELETE FROM shoppingcart WHERE User_ID = #{User_ID}")
    int clearShoppingCart(@Param("User_ID") Long User_ID);

}