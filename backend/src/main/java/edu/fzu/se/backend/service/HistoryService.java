package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.History;

import java.util.List;

public interface HistoryService {
    //获取所有搜索历史记录
    List<History> getAllHistories();
    //根据用户ID查询全部搜索历史记录
    List<History> getHistoriesByUserId(Long userId);
    //根据用户ID查询最新的十条搜索历史记录
    List<History> getLatestTenHistoriesByUserId(Long userId);
    //根据搜索ID查询搜索历史记录
    History getHistoryById(Long searchId);
    //根据搜索ID删除搜索历史记录
    int deleteHistoryById(Long searchId);
    //新增搜索历史记录
    int insertHistory(History history);
    //根据搜索ID更新搜索历史记录
    int updateHistoryById(History history);
}
