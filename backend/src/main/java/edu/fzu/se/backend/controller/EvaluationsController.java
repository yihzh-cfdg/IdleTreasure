package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.mapper.EvaluationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationsController {
    @Autowired
    private EvaluationsMapper evaluationsMapper;

    // 获取所有评价记录
    @GetMapping
    public List<Evaluations> getAllEvaluations() {
        return evaluationsMapper.selectAll();
    }

    // 根据交易ID查询评价记录
    @GetMapping("/{Trade_ID}")
    public Evaluations getEvaluationByTradeId(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationsMapper.selectByTradeId(Trade_ID);
    }

    // 根据卖家ID查询评价记录
    @GetMapping("/seller/{Seller_ID}")
    public List<Evaluations> getEvaluationsBySellerId(@PathVariable("Seller_ID") Long Seller_ID) {
        return evaluationsMapper.selectBySellerId(Seller_ID);
    }

    // 根据买家ID查询评价记录
    @GetMapping("/buyer/{Buyer_ID}")
    public List<Evaluations> getEvaluationsByBuyerId(@PathVariable("Buyer_ID") Long Buyer_ID) {
        return evaluationsMapper.selectByBuyerId(Buyer_ID);
    }

    // 根据卖家ID和买家ID查询评价记录
    @GetMapping("/buyer-seller/{Buyer_ID}/{Seller_ID}")
    public List<Evaluations> getEvaluationsByBuyerAndSellerId(@PathVariable("Buyer_ID") Long Buyer_ID, @PathVariable("Seller_ID") Long Seller_ID) {
        return evaluationsMapper.selectByBuyerAndSellerId(Buyer_ID, Seller_ID);
    }

    // 根据交易ID删除评价记录
    @DeleteMapping("/{Trade_ID}")
    public int deleteEvaluationById(@PathVariable("Trade_ID") Long Trade_ID) {
        return evaluationsMapper.deleteById(Trade_ID);
    }

    // 插入评价记录
    @PostMapping
    public int insertEvaluation(@RequestBody Evaluations evaluation) {
        return evaluationsMapper.insertEvaluation(evaluation);
    }

    // 根据交易ID更新评价记录
    @PutMapping("/{Trade_ID}")
    public int updateEvaluationById(@PathVariable("Trade_ID") Long Trade_ID, @RequestBody Evaluations evaluation) {
        evaluation.setTrade_ID(Trade_ID);
        return evaluationsMapper.updateById(evaluation);
    }
}
