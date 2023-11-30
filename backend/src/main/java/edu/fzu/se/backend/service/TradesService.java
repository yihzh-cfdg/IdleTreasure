package edu.fzu.se.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.Trades;
import org.springframework.stereotype.Service;

public interface TradesService {
    public void addTrade(Trades trades);
}
