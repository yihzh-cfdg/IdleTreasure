package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.FrontendGood;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "GoodsController", description = "商品信息控制器")
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Operation(summary = "返回首页商品")
    @GetMapping("/home")
    List<FrontendGood> returnHomeGoods()
    {
        return goodsService.maingetgoods();
    }
    @Operation(summary = "返回分类商品")
    @Parameters({
            @Parameter(name = "classname", description = "类名", required = true)
    })
    @GetMapping("/class")
    List<Goods> returnClassGoods(@RequestBody String classname)
    {
        return goodsService.classgetgoods(classname);
    }

    @Operation(summary = "返回商品图片")
    @Parameters({
            @Parameter(name = "goodsId", description = "商品ID", required = true)
    })
    @GetMapping("/images")
    List<Images> returnImages(Long goodsId)
    {
        return goodsService.getImages(goodsId);
    }

    @Operation(summary = "返回搜索商品")
    @Parameters({
            @Parameter(name = "value", description = "搜索关键字", required = true)
    })
    @GetMapping("/search")
    List<FrontendGood> returnSearchGoods(String value)
    {
        return goodsService.getSearchGoods(value);
    }
}
