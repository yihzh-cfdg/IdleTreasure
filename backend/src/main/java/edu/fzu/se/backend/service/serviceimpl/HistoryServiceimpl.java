package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.History;
import edu.fzu.se.backend.mapper.HistoryMapper;
import edu.fzu.se.backend.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceimpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    //获取所有搜索历史记录
    @Override
    public List<History> getAllHistories() {
        return historyMapper.selectAll();
    }
    //根据用户ID查询全部搜索历史记录
    @Override
    public List<History> getHistoriesByUserId(Long userId) {
        return historyMapper.selectByUserId(userId);
    }
    //根据用户ID查询最新的十条搜索历史记录
    @Override
    public List<History> getLatestTenHistoriesByUserId(Long userId) {
        return historyMapper.selectLatestTenByUserId(userId);
    }
    //根据搜索ID查询搜索历史记录
    @Override
    public History getHistoryById(Long searchId) {
        return historyMapper.selectById(searchId);
    }
    //根据搜索ID删除搜索历史记录
    public int deleteHistoryById(Long searchId) {
        return historyMapper.deleteById(searchId);
    }
    //新增搜索历史记录
    public int insertHistory(History history) {
        return historyMapper.insertHistory(history);
    }
    //根据搜索ID更新搜索历史记录
    public int updateHistoryById(History history) {
        return historyMapper.updateById(history);
    }
}
