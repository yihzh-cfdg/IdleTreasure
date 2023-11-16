package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.ShoppingCart;
import edu.fzu.se.backend.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "ShoppingCartController", description = "购物车控制器")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Operation(summary = "获取所有购物车项")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/{userId}/{goodsId}")
    public ShoppingCart selectByUserIdAndGoodsId(@PathVariable Long userId, @PathVariable Long goodsId) {
        return shoppingCartService.selectByUserIdAndGoodsId(userId, goodsId);
    }

    @Operation(summary = "根据用户ID查询购物车列表")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/{userId}")
    public List<ShoppingCart> selectByUserId(@PathVariable Long userId) {
        return shoppingCartService.selectByUserId(userId);
    }

    @Operation(summary = "插入新的购物车项")
    @Parameters({
            @Parameter(name = "shoppingCart", description = "购物车项", required = true)
    })
    @PostMapping
    public int insertShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.insertShoppingCart(shoppingCart);
    }

    @Operation(summary = "更新购物车项数量")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true),
            @Parameter(name = "goodsId", description = "商品ID", required = true),
            @Parameter(name = "shoppingCart", description = "购物车项", required = true)
    })
    @PutMapping("/{userId}/{goodsId}")
    public int updateShoppingCartCount(@PathVariable Long userId, @PathVariable Long goodsId, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUser_ID(userId);
        shoppingCart.setGoods_ID(goodsId);
        return shoppingCartService.updateShoppingCartCount(shoppingCart);
    }

    @Operation(summary = "删除购物车项")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true),
            @Parameter(name = "goodsId", description = "商品ID", required = true)
    })
    @DeleteMapping("/{userId}/{goodsId}")
    public int deleteShoppingCart(@PathVariable Long userId, @PathVariable Long goodsId) {
        return shoppingCartService.deleteShoppingCart(userId, goodsId);
    }

    @Operation(summary = "清空购物车")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @DeleteMapping("/{userId}")
    public int clearShoppingCart(@PathVariable Long userId) {
        return shoppingCartService.clearShoppingCart(userId);
    }
}
