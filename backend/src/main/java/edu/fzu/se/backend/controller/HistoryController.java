package edu.fzu.se.backend.controller;
import edu.fzu.se.backend.bean.History;
import edu.fzu.se.backend.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/history")
public class HistoryController {
  
    @Autowired
    private HistoryMapper historyMapper;

    // 获取所有历史记录
    @GetMapping("/all")
    public List<History> getAllHistory() {
        return historyMapper.selectAll();
    }

    // 根据用户ID获取历史记录
    @GetMapping("/user/{userId}")
    public List<History> getUserHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectByUserId(userId);
    }

    // 根据用户ID获取最新的10条历史记录
    @GetMapping("/latest/user/{userId}")
    public List<History> getLatestTenUserHistory(@PathVariable("userId") Long userId) {
        return historyMapper.selectLatestTenByUserId(userId);
    }

    // 根据ID获取历史记录
    @GetMapping("/id/{searchId}")
    public History getHistoryById(@PathVariable("searchId") Long searchId) {
        return historyMapper.selectById(searchId);
    }

    // 根据ID删除历史记录
    @DeleteMapping("/id/{searchId}")
    public int deleteHistoryById(@PathVariable("searchId") Long searchId) {
        return historyMapper.deleteById(searchId);
    }

    // 插入历史记录
    @PostMapping("/insert")
    public int insertHistory(@RequestBody History history) {
        return historyMapper.insertHistory(history);
    }

    // 更新历史记录
    @PutMapping("/update")
    public int updateHistory(@RequestBody History history) {
        return historyMapper.updateById(history);
    }
}

