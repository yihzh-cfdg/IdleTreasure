package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    //根据用户ID和商品ID查询购物车项
    ShoppingCart selectByUserIdAndGoodsId(Long userId, Long goodsId);
    //根据用户ID查询购物车列表
    List<ShoppingCart> selectByUserId(Long userId);
    //插入新的用户和商品的关系到购物车表
    int insertShoppingCart(ShoppingCart shoppingCart);
    //更新对应用户购物车中的对应商品数量
    int updateShoppingCartCount(ShoppingCart shoppingCart);
    //删除购物车项
    int deleteShoppingCart(Long userId, Long goodsId);
    //清空对应用户的购物车
    int clearShoppingCart(Long userId);
}
