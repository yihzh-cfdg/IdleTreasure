//商品上传接口功能实现
package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.service.TradesService;
import edu.fzu.se.backend.service.serviceimpl.TradesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Tag(name = "TradesController", description = "交易记录控制器")
@RestController
@RequestMapping("/api/trades")
public class TradesController {
    @Autowired
    private TradesServiceImpl tradesService;

    @Operation(summary = "新增交易")
    @Parameters({
            @Parameter(name = "goods", description = "商品", required = true)
    })
    @PostMapping("/add")
    public String addTrade(@RequestBody Trades trades){
        tradesService.addTrade(trades);
        return "success";
    }
}
