package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
import edu.fzu.se.backend.service.serviceimpl.EvaluationsServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EvaluationsController", description = "评价控制器")
@RestController
@RequestMapping("/evaluations")
public class EvaluationsController {
    @Autowired
    private EvaluationsMapper evaluationsMapper;

    @Operation(summary = "获取所有评价记录")
    @GetMapping
    public List<Evaluations> getAllEvaluations() {
        return evaluationsMapper.selectAll();
    }

    @Operation(summary="根据交易ID查询评价记录")
    @Parameters({
            @Parameter(name="Trade_ID",description="交易ID",required=true)
    })
    @GetMapping("/{Trade_ID}")
    public Evaluations getEvaluationByTradeId(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationsMapper.selectByTradeId(Trade_ID);
    }

    @Operation(summary = "根据卖家ID查询评价记录")
    @Parameters({
            @Parameter(name = "Seller_ID", description = "卖家ID", required = true)
    })
    @GetMapping("/seller/{Seller_ID}")
    public List<Evaluations> getEvaluationsBySellerId(@PathVariable("Seller_ID") Long Seller_ID) {
        return evaluationsMapper.selectBySellerId(Seller_ID);
    }

    @Operation(summary = "根据买家ID查询评价记录")
    @Parameters({
            @Parameter(name = "Buyer_ID", description = "买家ID", required = true)
    })
    @GetMapping("/buyer/{Buyer_ID}")
    public List<Evaluations> getEvaluationsByBuyerId(@PathVariable("Buyer_ID") Long Buyer_ID) {
        return evaluationsMapper.selectByBuyerId(Buyer_ID);
    }

    @Operation(summary = "根据卖家ID和买家ID查询评价记录")
    @Parameters({
            @Parameter(name = "Buyer_ID", description = "买家ID", required = true),
            @Parameter(name = "Seller_ID", description = "卖家ID", required = true)
    })
    @GetMapping("/buyer-seller/{Buyer_ID}/{Seller_ID}")
    public List<Evaluations> getEvaluationsByBuyerAndSellerId(@PathVariable("Buyer_ID") Long Buyer_ID, @PathVariable("Seller_ID") Long Seller_ID) {
        return evaluationsMapper.selectByBuyerAndSellerId(Buyer_ID, Seller_ID);
    }

    @Operation(summary = "根据交易ID删除评价记录")
    @Parameters({
            @Parameter(name = "Trade_ID", description = "交易ID", required = true)
    })
    @DeleteMapping("/{Trade_ID}")
    public int deleteEvaluationById(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationsMapper.deleteById(Trade_ID);
    }

    @Operation(summary = "插入评价记录")
    @Parameters({
            @Parameter(name = "evaluation", description = "评价记录", required = true)
    })
    @PostMapping
    public int insertEvaluation(@RequestBody Evaluations evaluation) {
        return evaluationsMapper.insertEvaluation(evaluation);
    }

    @Operation(summary = "根据交易ID更新评价记录")
    @Parameters({
            @Parameter(name = "Trade_ID", description = "交易ID", required = true),
            @Parameter(name = "evaluation", description = "评价记录", required = true)
    })
    @PutMapping("/{Trade_ID}")
    public int updateEvaluationById(@PathVariable("Trade_ID") Long Trade_ID, @RequestBody Evaluations evaluation) {
        evaluation.setTrade_ID(Trade_ID);
        return evaluationsMapper.updateById(evaluation);
    }
}
