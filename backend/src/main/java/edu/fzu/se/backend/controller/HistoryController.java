package edu.fzu.se.backend.controller;
import edu.fzu.se.backend.bean.History;
import edu.fzu.se.backend.mapper.HistoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "HistoryController", description = "历史记录控制器")
@RequestMapping("/api/history")
public class HistoryController {
  
    @Autowired
    private HistoryMapper historyMapper;

    @Operation(summary = "根据用户ID获取商品历史记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/user/item/{userId}")
    public List<History> getUserSearchHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectIHByUserId(userId);
    }

    @Operation(summary = "根据用户ID获取最新的10条搜索历史记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/latest/search/user/{userId}")
    public List<History> getLatestTenUserSearchHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectLatestTenSHByUserId(userId);
    }

    @Operation(summary = "根据历史记录ID获取历史记录")
    @Parameters({
            @Parameter(name = "searchId", description = "历史记录ID", required = true)
    })
    @GetMapping("/id/{searchId}")
    public History getHistoryById(@PathVariable("searchId") Long searchId) {
        return historyMapper.selectSHById(searchId);
    }

    @Operation(summary = "根据ID删除历史记录")
    @Parameters({
            @Parameter(name = "searchId", description = "历史记录ID", required = true)
    })
    @DeleteMapping("/id/{searchId}")
    public int deleteHistoryById(@PathVariable("searchId") Long searchId) {
        return historyMapper.deleteById(searchId);
    }

    @Operation(summary = "插入历史记录")
    @Parameters({
            @Parameter(name = "history", description = "历史记录", required = true)
    })
    @PostMapping("/insert")
    public int insertSearchHistory(@RequestBody History history) {
        return historyMapper.insertHistory(history);
    }

    @Operation(summary = "更新历史记录")
    @Parameters({
            @Parameter(name = "history", description = "历史记录", required = true)
    })
    @PutMapping("/update")
    public int updateSearchHistory(@RequestBody History history) {
        return historyMapper.updateById(history);
    }

}

