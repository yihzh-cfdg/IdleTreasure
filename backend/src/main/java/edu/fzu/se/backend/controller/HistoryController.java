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

    @Operation(summary = "获取所有历史记录")
    @GetMapping("/all")
    public List<History> getAllHistory() {
        return historyMapper.selectAll();
    }

    @Operation(summary = "根据用户ID获取历史记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/user/{userId}")
    public List<History> getUserHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectByUserId(userId);
    }

    @Operation(summary = "根据用户ID获取最新的10条历史记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/latest/user/{userId}")
    public List<History> getLatestTenUserHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectLatestTenByUserId(userId);
    }

    @Operation(summary = "根据ID获取历史记录")
    @Parameters({
            @Parameter(name = "searchId", description = "历史记录ID", required = true)
    })
    @GetMapping("/id/{searchId}")
    public History getHistoryById(@PathVariable("searchId") Long searchId) {
        return historyMapper.selectById(searchId);
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
    public int insertHistory(@RequestBody History history) {
        return historyMapper.insertHistory(history);
    }

    @Operation(summary = "更新历史记录")
    @Parameters({
            @Parameter(name = "history", description = "历史记录", required = true)
    })
    @PutMapping("/update")
    public int updateHistory(@RequestBody History history) {
        return historyMapper.updateById(history);
    }
}

