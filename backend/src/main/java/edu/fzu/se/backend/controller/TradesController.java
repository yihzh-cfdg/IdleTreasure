package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.service.serviceimpl.TradesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "TradesController", description = "交易记录控制器")
@RestController
@RequestMapping("/api/trades")
public class TradesController {
    @Autowired
    private TradesServiceImpl tradesService;

    @Operation(summary = "新增交易")
    @Parameters({
            @Parameter(name = "trades", description = "交易", required = true)
    })
    @PostMapping("/add")
    public String addTrade(@RequestBody Trades trades){
        tradesService.addTrade(trades);
        return "success";
    }

    @Operation(summary = "根据ID获取交易")
    @Parameters({
            @Parameter(name = "tradeID", description = "交易id", required = true),
            @Parameter(name = "userID", description = "用户id", required = true)
    })
    @GetMapping("/id")
    public Map<String, String> getTradeByID(@RequestParam(value = "tradeID") Long tradeID, @RequestParam(value = "userID") Long userID){
        return tradesService.getTradeByID(tradeID, userID);
    }


    @Operation(summary = "获取我买到的")
    @Parameters({
            @Parameter(name = "buyerID", description = "买家id", required = true)
    })
    @GetMapping("/buyer")
    public List<Map<String, String>> getTradeByBuyerID(@RequestParam(value = "buyerID") Long buyerID){
        return tradesService.getTradesByBuyerID(buyerID);
    }

    @Operation(summary = "获取我卖出的")
    @Parameters({
            @Parameter(name = "sellerID", description = "卖家id", required = true)
    })
    @GetMapping("/seller")
    public List<Map<String, String>> getTradeBySellerID(@RequestParam(value = "sellerID") Long sellerID){
        return tradesService.getTradesBySellerID(sellerID);
    }

    @Operation(summary = "获取我发布的")
    @Parameters({
            @Parameter(name = "sellerID", description = "卖家id", required = true)
    })
    @GetMapping("/publish")
    public List<Map<String, String>> getTradeByPublisherID(@RequestParam(value = "publisherID") Long publisherID){
        return tradesService.getTradesByPublisherID(publisherID);
    }

    //订单付款
    @Operation(summary = "订单付款")
    @Parameters({
            @Parameter(name = "tradeID", description = "交易id", required = true)
    })
    @GetMapping("/trade-pay")
    public String pay(@RequestParam(value = "tradeID") Long tradeID){
        tradesService.updateStatusById(tradeID, "已付款");
        return "success";
    }
    //订单发货
    @Operation(summary = "订单发货")
    @Parameters({
            @Parameter(name = "tradeID", description = "交易id", required = true)
    })
    @GetMapping("/trade-deliver")
    public String deliver(@RequestParam(value = "tradeID") Long tradeID){
        tradesService.updateStatusById(tradeID, "已发货");
        return "success";
    }

    //订单收货
    @Operation(summary = "订单收货")
    @Parameters({
            @Parameter(name = "tradeID", description = "交易id", required = true)
    })
    @GetMapping("/trade-receive")
    public String receive(@RequestParam(value = "tradeID") Long tradeID){
        tradesService.updateStatusById(tradeID, "交易成功");
        return "success";
    }

    //订单完成
    @Operation(summary = "订单完成")
    @Parameters({
            @Parameter(name = "tradeID", description = "交易id", required = true)
    })
    @GetMapping("/trade-finish")
    public String finish(@RequestParam(value = "tradeID") Long tradeID){
        tradesService.updateStatusById(tradeID, "已评价");
        return "success";
    }
}
