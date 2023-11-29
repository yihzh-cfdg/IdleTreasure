package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.ShoppingCart;
import edu.fzu.se.backend.mapper.ShoppingCartMapper;
import edu.fzu.se.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    //根据用户ID和商品ID查询购物车项
    @Override
    public ShoppingCart selectByUserIdAndGoodsId(Long userId, Long goodsId) {
        return shoppingCartMapper.selectByUserIdAndGoodsId(userId, goodsId);
    }
    //根据用户ID查询购物车列表
    @Override
    public List<ShoppingCart> selectByUserId(Long userId) {
        return shoppingCartMapper.selectByUserId(userId);
    }
    //插入新的用户和商品的关系到购物车表
    @Override
    public int insertShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.insertShoppingCart(shoppingCart);
    }
    //更新对应用户购物车中的对应商品数量
    @Override
    public int updateShoppingCartCount(ShoppingCart shoppingCart) {
        return shoppingCartMapper.updateShoppingCartCount(shoppingCart);
    }
    //删除购物车项
    @Override
    public int deleteShoppingCart(Long userId, Long goodsId) {
        return shoppingCartMapper.deleteShoppingCart(userId, goodsId);
    }
    //清空对应用户的购物车
    @Override
    public int clearShoppingCart(Long userId) {
        return shoppingCartMapper.clearShoppingCart(userId);
    }
}
