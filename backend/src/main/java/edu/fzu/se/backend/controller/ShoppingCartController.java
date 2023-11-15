package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.ShoppingCart;
import edu.fzu.se.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // 根据用户ID和商品ID查询购物车项
    @GetMapping("/{userId}/{goodsId}")
    public ShoppingCart selectByUserIdAndGoodsId(@PathVariable Long userId, @PathVariable Long goodsId) {
        return shoppingCartService.selectByUserIdAndGoodsId(userId, goodsId);
    }

    // 根据用户ID查询购物车列表
    @GetMapping("/{userId}")
    public List<ShoppingCart> selectByUserId(@PathVariable Long userId) {
        return shoppingCartService.selectByUserId(userId);
    }

    // 插入新的用户和商品的关系到购物车表
    @PostMapping
    public int insertShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.insertShoppingCart(shoppingCart);
    }

    // 更新对应用户购物车中的对应商品数量
    @PutMapping("/{userId}/{goodsId}")
    public int updateShoppingCartCount(@PathVariable Long userId, @PathVariable Long goodsId, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUser_ID(userId);
        shoppingCart.setGoods_ID(goodsId);
        return shoppingCartService.updateShoppingCartCount(shoppingCart);
    }

    // 删除购物车项
    @DeleteMapping("/{userId}/{goodsId}")
    public int deleteShoppingCart(@PathVariable Long userId, @PathVariable Long goodsId) {
        return shoppingCartService.deleteShoppingCart(userId, goodsId);
    }

    // 清空对应用户的购物车
    @DeleteMapping("/{userId}")
    public int clearShoppingCart(@PathVariable Long userId) {
        return shoppingCartService.clearShoppingCart(userId);
    }
}
