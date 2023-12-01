package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.service.EvaluationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "EvaluationsController", description = "评价控制器")
@RestController
@RequestMapping("/api/evaluations")
public class EvaluationsController {
    @Autowired
    private EvaluationsService evaluationService;

    @Operation(summary="根据交易ID查询评价记录")
    @Parameters({
            @Parameter(name="Trade_ID",description="交易ID",required=true)
    })
    @GetMapping("/{Trade_ID}")
    public Evaluations getEvaluationByTradeId(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationService.getEvaluationByTradeId(Trade_ID);
    }

    @Operation(summary = "查询评价记录")
    @Parameters({
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @GetMapping("/buyer-seller/{Trade_ID}")
    public Map<String, String> getEvaluationsByBuyerAndSellerId(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationService.getEvaluationsByBuyerAndSellerId(Trade_ID);
    }

    @Operation(summary = "更新买家评价记录")
    @Parameters({
            @Parameter(name = "Buyer_Evaluation", description = "买家评价", required = true),
            @Parameter(name = "Buyer_Status", description = "买家评价状态", required = true),
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @PostMapping("/buyer")
    public String updateBuyerEvaluation(@RequestBody String Buyer_Evaluation, @RequestBody String Buyer_Status, @RequestBody Long Trade_ID) {
        if(evaluationService.updateBuyerEvaluation(Buyer_Evaluation, Buyer_Status, Trade_ID)>0)
            return "success";
        else
            throw new RuntimeException("更新买家评价记录失败");
    }


    @Operation(summary = "更新卖家评价记录")
    @Parameters({
            @Parameter(name = "Seller_Evaluation", description = "卖家评价", required = true),
            @Parameter(name = "Seller_Status", description = "卖家评价状态", required = true),
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @PostMapping("/seller")
    public String updateSellerEvaluation(@RequestBody String Seller_Evaluation, @RequestBody String Seller_Status, @RequestBody Long Trade_ID) {
        if(evaluationService.updateSellerEvaluation(Seller_Evaluation, Seller_Status, Trade_ID)>0)
            return "success";
        else
            throw new RuntimeException("更新卖家评价记录失败");
    }

    @Operation(summary = "根据卖家ID和交易ID查询评价")
    @Parameters({
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @GetMapping("/seller/{Trade_ID}")
    public Map<String, String> getSellerEvaluation(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationService.getSellerEvaluation(Trade_ID);
    }

    @Operation(summary = "根据买家ID和交易ID查询评价")
    @Parameters({
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @GetMapping("/buyer/{Trade_ID}")
    public Map<String, String> getBuyerEvaluation( @PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationService.getBuyerEvaluation(Trade_ID);
    }
}
